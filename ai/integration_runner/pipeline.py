"""IntegrationRun: the stage state machine only — advancing through STAGES,
running the current stage's agent, and the review/constraint/threading
mechanics around that. Every method here mutates one of the state
machine's own core fields (current_stage_index, busy, last_context,
last_output, last_completed_stage, constraints, model) as part of what it does; nearly all also
record_event() as a byproduct of that real transition (add_constraint ->
constraint_added, record_review -> review_approved/review_rejected, the
run_stage_async worker -> call_started/call_completed/call_failed).

Deliberately does NOT hold stage-specific ad-hoc actions (like
stages/docs/actions.py's extend_with_page): those don't mutate any of this
class's own fields, aren't dispatched through stage_agents the way a
stage's own agent is, and are specific to one stage, not general run
mechanics — routes/ calls them directly against runs.current(), see
stages/__init__.py's own docstring for why they live with their stage
instead of accumulating here as every stage grows its own extras.

Composes an EventLog (event_log.py) for this run's full real history as raw
events, rather than implementing storage itself — that split IS different
in kind (storage, not a state transition). This module has no knowledge of
HTTP, chat, LLM tool-calling, or narration — it only ever records what
happened. Turning those raw events into a human-readable comment is
orchestrator's own job, done entirely by polling this service's own
GET /events over HTTP, never by this module calling out.
"""
import threading
import uuid

from integration_runner import stages
from integration_runner.event_log import EventLog

# A docs -> serialization -> pim -> psm -> atl -> acceleo -> generation
# pipeline (see the repo root CLAUDE.md's own description of the real chain:
# SWArch -> PIM -> PSM -> YAML). docs and serialization are real; the rest
# are placeholder LLM prompts standing in for future real agents. A human
# reviews each stage's output and either approves it (advancing to the next
# stage) or rejects it with a correction that's recorded as a constraint for
# the stage's next run.
STAGES = ["docs", "serialization", "pim", "psm", "atl", "acceleo", "generation"]


class IntegrationRun:
    """Tracks progress through STAGES and runs each stage's agent."""

    def __init__(self, run_id: str | None = None):
        # A stable identity for this run, independent of process memory (a
        # module-level Python variable has none). Not surfaced through any
        # endpoint yet, and nothing keys real behavior off it today, MVP is
        # still exactly one implicit "current" run (see runs.py's
        # _runs/_default) — this exists so a future persistence layer or
        # multi-run UI has something to address a run by without a rewrite,
        # not because anything needs it right now.
        self.run_id = run_id or uuid.uuid4().hex
        self.current_stage_index = 0
        self.constraints: dict[str, list[str]] = {}
        self.last_context: dict = {}
        self.last_output: str | None = None
        # Which stage last_output actually belongs to, set only on a real
        # call_completed, never on call_failed or while a run is still in
        # flight. review()'s own guard reads this: without it, approving a
        # stage whose last attempt failed (or hasn't run at all yet) would
        # silently forward the PREVIOUS stage's last_output onward, mislabeled
        # under the failed stage's own output key (observed for real: a
        # serialization-agent 500 left last_output holding docs' raw text,
        # and a naive review(approved=True) would have forwarded that to pim
        # as "serialization_output" without error).
        self.last_completed_stage: str | None = None
        self.event_log = EventLog()
        # Chosen once via start_pipeline()'s optional model param, applied to
        # every real chat() call the placeholder stage agents make for this
        # run (docs doesn't call chat() at all, it's a real retrieval crawl,
        # not an LLM choice). None means ai-layer's own automatic routing.
        # Narration's own model choice is separate, tracked by orchestrator
        # itself, not this field — orchestrator reads this one via GET
        # /status when it needs to know what a run is currently set to.
        self.model: str | None = None
        # Set synchronously by run_stage_async(), before the background thread
        # even starts, so a poller can never race a run that's already been
        # triggered. Cleared by the thread itself in a finally block. A guard
        # against a single impatient user double-clicking, not a task queue,
        # this is single-session-only.
        self.busy: bool = False
        # The most recently started background thread, if any. Not used by
        # the API itself (callers poll GET /events instead), only exposed so
        # tests/tools can deterministically wait_for_idle() rather than sleep.
        self._last_thread: threading.Thread | None = None

    @property
    def current_stage(self) -> str | None:
        if self.current_stage_index >= len(STAGES):
            return None
        return STAGES[self.current_stage_index]

    @property
    def events(self) -> list[dict]:
        return self.event_log.events

    def run_stage(self, context: dict) -> dict:
        stage = self.current_stage
        agent = stages.stage_agents[stage]
        self.last_context = context
        # self.constraints is looked up fresh (not snapshotted at run_stage() call time),
        # so a correction recorded via add_constraint() after this call is picked up the
        # next time run_stage()/rerun() runs this same stage. run_id is threaded in the
        # same way, for the pim/psm/atl/acceleo stage agents' own on-disk persistence
        # (stages/_validation.py) — a real run identity, not something those agents
        # invent or track themselves.
        enriched_context = {**context, "constraints": self.constraints, "model": self.model, "run_id": self.run_id}
        raw = agent(enriched_context)
        # Every stage agent's normal contract is (context: dict) -> str (see
        # ai/CLAUDE.md's stage-agent recipe). psm is the one narrow, documented
        # exception: it returns (output, extra) since it has real structured
        # data (the prompt actually used, validation/gap results) the chat-ui
        # needs alongside the artifact - a plain string has nowhere to put
        # that. Backward compatible: every other stage's plain str return is
        # unaffected, extra is just {} for them.
        output, extra = raw if isinstance(raw, tuple) else (raw, {})
        self.last_output = output
        self.last_completed_stage = stage
        return {"stage": stage, "output": output, **extra}

    def rerun(self, overrides: dict | None = None) -> dict:
        """Re-run the current stage in the background, reusing last_context
        plus any given overrides (only meaningful for the docs stage) and
        picking up constraints recorded since the last run. Used by both
        /rerun and the rerun_stage tool, the only difference between a human
        clicking Retry and an LLM deciding to call rerun_stage is who (if
        anyone) supplies overrides."""
        overrides = overrides or {}
        # "only docs" is true today, not permanently: docs is the only stage
        # wrapping a real API (retrieval's /fetch) with real typed
        # parameters to override, pim/psm/atl/acceleo/generation are still
        # placeholder chat() prompts with no structured shape of their own
        # yet (see stages/). Whoever makes the next stage real needs
        # to design ITS real override shape from ITS real API, the same way
        # docs's was, then extend this guard, don't just delete it.
        if overrides and self.current_stage != "docs":
            raise ValueError(
                f"'{self.current_stage}' has no structured parameters to override, only 'docs' does."
            )
        context = {**self.last_context, **overrides}
        self.run_stage_async(context)
        return {"status": "started", "stage": self.current_stage}

    def add_constraint(self, stage: str, constraint: str) -> None:
        self.constraints.setdefault(stage, []).append(constraint)
        self.record_event("constraint_added", stage, {"constraint": constraint})

    def set_model(self, model: str | None) -> None:
        """Changes the model for the rest of this run, not just what
        start_pipeline() chose: every subsequent real chat() call a stage
        agent makes (a stage run or a retry) picks this up, since they all
        read self.model fresh each time, not a value snapshotted at
        start_pipeline() time."""
        self.model = model

    def start_stage_run(self, context: dict) -> dict:
        """Start the current stage running in the background with the given
        context, and report that it started. The run_stage tool's real
        target, and what runs.start_pipeline() uses to kick off the docs
        stage."""
        self.run_stage_async(context)
        return {"status": "started", "stage": self.current_stage}

    def advance_stage(self) -> str | None:
        self.current_stage_index += 1
        return self.current_stage

    def _validate_review(self, stage_id: str, approved: bool, correction: str | None) -> None:
        """A stale or hallucinated stage_id, or an approval=False with no
        correction, can't silently corrupt pipeline state. Nor can approving
        a stage that hasn't actually produced a fresh output yet — still
        running, never started, or its last real attempt failed — since
        there'd be nothing real for the human to have reviewed."""
        if stage_id != self.current_stage:
            raise ValueError(
                f"'{stage_id}' is not the current pending stage (current: {self.current_stage!r})."
            )
        if approved and self.last_completed_stage != stage_id:
            raise ValueError(
                f"Cannot approve '{stage_id}': it hasn't completed successfully yet (still "
                f"running, never started, or its last attempt failed). Rerun it first."
            )
        if not approved and not correction:
            raise ValueError("correction is required when approved is False.")

    def record_review(self, stage_id: str, approved: bool, correction: str | None = None) -> dict:
        """Validates and records a review_approved/review_rejected event. On
        approval, returns the next stage's context WITHOUT running it, so the
        caller decides whether/when to start that run (review(), below,
        starts it immediately; a caller that just wants the state transition
        without triggering a run can call this directly)."""
        self._validate_review(stage_id, approved, correction)
        self.record_event("review_approved" if approved else "review_rejected", stage_id, {
            "correction": correction,
        })
        if approved:
            approved_output = self.last_output
            next_stage = self.advance_stage()
            if next_stage is None:
                return {"status": "complete"}
            next_context = {**self.last_context, f"{stage_id}_output": approved_output}
            return {"status": "advanced", "stage": next_stage, "context": next_context}
        self.add_constraint(stage_id, correction)
        return {"status": "rerun", "stage": stage_id}

    def review(self, stage_id: str, approved: bool, correction: str | None = None) -> dict:
        """Records a review decision and, if it advances the pipeline, starts
        the next stage running in the background right away. Used by both
        /review and the stage_result tool, the only difference between a
        human clicking Approve/Reject and an LLM deciding to call
        stage_result is who's asking."""
        result = self.record_review(stage_id, approved, correction)
        if result["status"] == "advanced":
            self.run_stage_async(result["context"])
            return {"status": "started", "stage": result["stage"]}
        return result

    def record_event(self, event_type: str, stage: str | None, data: dict | None = None) -> dict:
        """Appends a raw fact about this run and returns it, via this run's
        own EventLog. Nothing else: no reaction, no narration, no LLM call.
        This module only reports what happened; turning that into a
        human-readable comment is orchestrator's job, done by polling
        GET /events, not by this method calling out to anything."""
        return self.event_log.record(event_type, stage, data)

    def run_stage_async(self, context: dict) -> None:
        """Starts the current stage's agent on a background thread and
        returns immediately: calls and stops. The only way a stage ever
        starts running — every one of this service's own REST endpoints that
        can trigger a run (POST /start, /review, /rerun, /stage/run) calls
        this directly, none has its own copy of "run it in the background."
        Setting busy here (before the thread even starts, not inside it)
        means a poller can never observe a run that's already been triggered
        as not-busy."""
        self.busy = True
        self._last_thread = threading.Thread(target=self._run_stage_worker, args=(context,), daemon=True)
        self._last_thread.start()

    def _run_stage_worker(self, context: dict) -> None:
        stage = self.current_stage
        try:
            self.record_event("call_started", stage, context)
            try:
                result = self.run_stage(context)
                self.record_event("call_completed", stage, result)
            except Exception as e:
                self.record_event("call_failed", stage, {"error": str(e)})
        finally:
            self.busy = False
