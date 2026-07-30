import logging
import os
import threading
import time
from typing import Callable

import httpx
from dotenv import load_dotenv

load_dotenv()

logger = logging.getLogger(__name__)

AI_LAYER_URL = os.environ.get("AI_LAYER_URL", "http://localhost:8000")
RETRIEVAL_URL = os.environ.get("RETRIEVAL_URL", "http://localhost:8010")
# retrieval's own AdaptiveCrawler stops early on a poor crawl (min_gain_threshold),
# so a fetch that returns can still have found essentially nothing useful. This is
# the floor below which that's treated as a hard failure, not a low-quality result
# for a human to review, an engineering guess, not measured against a real corpus.
_DOCS_MIN_CONFIDENCE = 0.15
# Long fields (notably docs_agent's real fetched markdown) get cut to this length
# before being fed into the reactor's prompt history, so re-sending history on
# every subsequent narration call doesn't balloon the prompt. self.events keeps
# the untruncated original, this only affects what the reactor sees.
_REACTION_FIELD_MAX_CHARS = 2000
# Opt-in only (unset by default, so a real `docker compose up` still runs the
# real crawl): when set, docs_agent() skips retrieval entirely and returns
# canned output instantly. For iterating on the orchestrator UI/mechanics
# (approve/reject/retry/nudge) without needing a real, valid documentation
# URL or waiting on a real 90+ second crawl each time. Set in orchestrator's
# own .env (gitignored, not docker-compose.yml's committed defaults), so it
# never silently affects a real run.
_STUB_DOCS = os.environ.get("ORCHESTRATOR_STUB_DOCS", "").strip().lower() in ("1", "true", "yes")


def chat(
    messages: list[dict],
    model: str | None = None,
    tools: list[dict] | None = None,
    tool_choice: str | None = None,
) -> dict:
    """POST to ai-layer's /chat endpoint, returns its parsed JSON response
    directly: {"model": ..., "content": str | None, "tool_calls": [{"function":
    {"name": ..., "arguments": "..."}}] | None}. content is None when the
    model responded with only tool calls."""
    payload = {"messages": messages, "model": model}
    if tools is not None:
        payload["tools"] = tools
    if tool_choice is not None:
        payload["tool_choice"] = tool_choice
    response = httpx.post(f"{AI_LAYER_URL}/chat", json=payload, timeout=120.0)
    response.raise_for_status()
    return response.json()


_ERROR_MARKERS = ("i cannot", "i don't know", "i do not know", "an error occurred", "sorry, an error")


def is_good_enough(response: str) -> bool:
    """Non-empty and free of obvious refusal/error markers."""
    if not response or not response.strip():
        return False
    lowered = response.lower()
    return not any(marker in lowered for marker in _ERROR_MARKERS)


# --- Stage-based pipeline generation ---------------------------------------
#
# A docs -> PIM -> PSM -> ATL -> Acceleo -> generation pipeline (see the repo
# root CLAUDE.md's own description of the real chain: SWArch -> PIM -> PSM ->
# YAML). docs is a real call to the retrieval service; the other five are
# placeholder LLM prompts standing in for future real agents. A human
# reviews each stage's output and either approves it (advancing to the next
# stage) or rejects it with a correction that's recorded as a constraint for
# the stage's next run.

STAGES = ["docs", "pim", "psm", "atl", "acceleo", "generation"]

# One line per stage, used to build the Orchestrator's own narration/tool-
# routing system prompt (see pipeline_tools.py) so that prompt never needs a
# manual edit when a stage is added, only this dict does, alongside STAGES
# and stage_agents below.
STAGE_DESCRIPTIONS: dict[str, str] = {
    "docs": "fetches the platform's real documentation (a real call to retrieval).",
    "pim": "a PIM (Platform-Independent Model) description of the platform.",
    "psm": "a PSM (Platform-Specific Model) description of the platform.",
    "atl": "the ATL transformation rules needed to build that PSM.",
    "acceleo": "the Acceleo code-generation template for that ATL.",
    "generation": "a final summary tying all prior stages together.",
}

_STAGE_SYSTEM_PROMPTS = {
    "pim": (
        "You are the MDDOAI PIM (Platform-Independent Model) agent. Given a platform's real "
        "documentation, produce a clear PIM-level description: express the platform's CI/CD "
        "concepts (jobs, stages, triggers, artifacts, agents/runners) in MDDOAI's "
        "platform-independent metamodel terms, without committing to any one platform's "
        "specific syntax yet. Be precise and structured."
    ),
    "psm": (
        "You are the MDDOAI PSM (Platform-Specific Model) agent. Given a PIM-level description "
        "of a CI/CD platform, produce a clear PSM-level description: express the same concepts "
        "(jobs, stages, triggers, artifacts, agents/runners) in MDDOAI's platform-specific "
        "metamodel terms. Be precise and structured."
    ),
    "atl": (
        "You are the MDDOAI ATL transformation agent. Given a PSM-level description, describe "
        "the ATL (ATLAS Transformation Language) transformation rules needed to map the "
        "platform-independent model to this PSM: name the rules, and describe their source "
        "and target patterns and the mapping logic between them."
    ),
    "acceleo": (
        "You are the MDDOAI Acceleo template agent. Given a description of ATL transformation "
        "rules, describe the Acceleo code-generation template needed to turn the transformed "
        "model into real pipeline configuration files: the template's structure, its key "
        "generation blocks, and the output files it targets."
    ),
    "generation": (
        "You are the MDDOAI generation summary agent. Given the PSM description, the ATL "
        "transformation rules, and the Acceleo template plan produced in the prior stages, "
        "produce a final, concise summary of the full pipeline generation plan, from the "
        "original platform input through to the generated CI/CD configuration."
    ),
}


def _constraints_note(context: dict, stage: str) -> str:
    constraints = context.get("constraints", {}).get(stage, [])
    if not constraints:
        return ""
    bullet_list = "\n".join(f"- {c}" for c in constraints)
    return f"\n\nApply these corrections from prior review:\n{bullet_list}"


# Retrieval's own two real capabilities (ai/retrieval's POST /fetch and POST
# /fetch/page), called directly here by docs_agent (the docs stage's normal
# path). pipeline_tools.py also wraps these as stage-scoped tools (so nudge() can
# call them directly too, e.g. "just grab that one missing page" mapping onto
# fetch_page precisely instead of the generic rerun_stage), reusing these same
# functions, not duplicating the HTTP calls.


def fetch_documentation(
    url: str,
    hint: str | None = None,
    exclude_urls: list[str] | None = None,
    max_pages: int | None = None,
    max_depth: int | None = None,
    force_refresh: bool | None = None,
) -> dict:
    """Calls retrieval's real POST /fetch. hint is retrieval's own retry lever
    (see its fetch_documentation docstring: "a retry lever for a caller
    (human or orchestrator)"), passed straight through, no translation
    needed. Returns the raw FetchResult dict."""
    payload = {"url": url}
    if hint:
        payload["hint"] = hint
    for key, value in (
        ("exclude_urls", exclude_urls),
        ("max_pages", max_pages),
        ("max_depth", max_depth),
        ("force_refresh", force_refresh),
    ):
        if value is not None:
            payload[key] = value
    response = httpx.post(f"{RETRIEVAL_URL}/fetch", json=payload, timeout=180.0)
    response.raise_for_status()
    return response.json()


def fetch_page(url: str, force_refresh: bool = False) -> dict:
    """Calls retrieval's real POST /fetch/page: the targeted retry, pulling in
    one specific known page directly rather than re-running a full crawl.
    Returns the raw Page dict."""
    response = httpx.post(
        f"{RETRIEVAL_URL}/fetch/page", json={"url": url, "force_refresh": force_refresh}, timeout=60.0
    )
    response.raise_for_status()
    return response.json()


def docs_agent(context: dict) -> str:
    """The docs stage's normal (non-tool-call) path: calls fetch_documentation
    with whatever context supplies, formats the result as the stage's output
    string, and fails hard if the crawl found essentially nothing. An
    explicit context["hint"] (from a /rerun override) takes priority over the
    constraints-derived one. Short-circuits to canned output when
    ORCHESTRATOR_STUB_DOCS is set (see _STUB_DOCS), skipping retrieval
    entirely, real fetch_documentation()/fetch_page() (the tool-call paths
    nudge() can reach directly) are untouched either way."""
    seed_url = context.get("seed_url", "")
    if _STUB_DOCS:
        return (
            f"[STUBBED] ORCHESTRATOR_STUB_DOCS is set, skipped the real crawl of {seed_url}. "
            f"This is placeholder output for testing the pipeline's mechanics, not real documentation."
        )
    constraints = context.get("constraints", {}).get("docs", [])
    hint = context.get("hint") or (" ".join(constraints) if constraints else None)

    result = fetch_documentation(
        seed_url,
        hint=hint,
        exclude_urls=context.get("exclude_urls"),
        max_pages=context.get("max_pages"),
        max_depth=context.get("max_depth"),
        force_refresh=context.get("force_refresh"),
    )
    pages = [p for p in result["pages"] if p["success"]]
    confidence = result["meta"]["confidence"]
    if not pages or confidence < _DOCS_MIN_CONFIDENCE:
        raise RuntimeError(
            f"Fetch for {seed_url} found essentially nothing useful "
            f"(confidence {confidence:.2f}, {len(pages)} usable page(s))."
        )
    content = "\n\n".join(f"# {p['url']}\n{p['markdown']}" for p in pages)
    return f"Fetched {len(pages)} page(s) from {seed_url}, confidence {confidence:.2f}.\n\n{content}"


def pim_agent(context: dict) -> str:
    # docs_output (the real fetched documentation) is what a live run actually
    # has once "docs" precedes "pim"; platform_description is the fallback for
    # a caller (or a unit test) that invokes pim_agent directly without it.
    platform_description = context.get("platform_description", "")
    primary_input = context.get("docs_output") or platform_description
    user_content = primary_input + _constraints_note(context, "pim")
    messages = [
        {"role": "system", "content": _STAGE_SYSTEM_PROMPTS["pim"]},
        {"role": "user", "content": user_content},
    ]
    return chat(messages, model=context.get("model"))["content"]


def psm_agent(context: dict) -> str:
    # pim_output is what a live run actually has once "pim" precedes "psm";
    # docs_output/platform_description are fallbacks for a caller (or a unit
    # test) that invokes psm_agent directly without a pim stage having run.
    platform_description = context.get("platform_description", "")
    primary_input = context.get("pim_output") or context.get("docs_output") or platform_description
    user_content = primary_input + _constraints_note(context, "psm")
    messages = [
        {"role": "system", "content": _STAGE_SYSTEM_PROMPTS["psm"]},
        {"role": "user", "content": user_content},
    ]
    return chat(messages, model=context.get("model"))["content"]


def atl_agent(context: dict) -> str:
    psm_output = context.get("psm_output", "")
    user_content = psm_output + _constraints_note(context, "atl")
    messages = [
        {"role": "system", "content": _STAGE_SYSTEM_PROMPTS["atl"]},
        {"role": "user", "content": user_content},
    ]
    return chat(messages, model=context.get("model"))["content"]


def acceleo_agent(context: dict) -> str:
    atl_output = context.get("atl_output", "")
    user_content = atl_output + _constraints_note(context, "acceleo")
    messages = [
        {"role": "system", "content": _STAGE_SYSTEM_PROMPTS["acceleo"]},
        {"role": "user", "content": user_content},
    ]
    return chat(messages, model=context.get("model"))["content"]


def gen_agent(context: dict) -> str:
    user_content = (
        f"PSM description:\n{context.get('psm_output', '')}\n\n"
        f"ATL transformation rules:\n{context.get('atl_output', '')}\n\n"
        f"Acceleo template plan:\n{context.get('acceleo_output', '')}"
    ) + _constraints_note(context, "generation")
    messages = [
        {"role": "system", "content": _STAGE_SYSTEM_PROMPTS["generation"]},
        {"role": "user", "content": user_content},
    ]
    return chat(messages, model=context.get("model"))["content"]


stage_agents = {
    "docs": docs_agent,
    "pim": pim_agent,
    "psm": psm_agent,
    "atl": atl_agent,
    "acceleo": acceleo_agent,
    "generation": gen_agent,
}


def validate(output: str) -> bool:
    return is_good_enough(output)


def _summarize_for_reaction(event: dict) -> dict:
    """Truncates long string fields in event's data (notably docs_agent's real
    fetched markdown) before it's fed into the reactor's prompt, so re-
    sending history on every subsequent call doesn't balloon the prompt.
    Builds a copy, self.events keeps the original untruncated."""
    data = event.get("data")
    if not isinstance(data, dict):
        return event
    summarized = {
        key: (value[:_REACTION_FIELD_MAX_CHARS] + "... (truncated)")
        if isinstance(value, str) and len(value) > _REACTION_FIELD_MAX_CHARS
        else value
        for key, value in data.items()
    }
    return {**event, "data": summarized}


# record_event() reacts to every event it records (see Orchestrator.record_event
# below), but this file has no knowledge of tool-calling or LLM reply-building,
# that lives in pipeline_tools.py/assistant.py. main.py wires assistant.py's
# react_to_event() in here via set_reactor() explicitly at startup, not as an
# import-time side effect, so this file never imports pipeline_tools.py or
# assistant.py (avoiding a circular import: pipeline_tools.py needs this
# file's pipeline operations to build its tool list). A late-bound module
# global, not a constructor argument, so reset_pipeline()'s fresh
# Orchestrator() instances don't each need it threaded through.
_reactor: Callable[[dict, list[dict] | None], dict] | None = None


def set_reactor(reactor: Callable[[dict, list[dict] | None], dict]) -> None:
    global _reactor
    _reactor = reactor


class Orchestrator:
    """Tracks progress through STAGES and runs each stage's agent."""

    def __init__(self):
        self.current_stage_index = 0
        self.constraints: dict[str, list[str]] = {}
        self.last_context: dict = {}
        self.last_output: str | None = None
        self.events: list[dict] = []
        # Chosen once via start_pipeline()'s optional model param, applied to
        # every real chat() call for this run: the four stage agents (docs
        # doesn't call chat() at all, it's a real retrieval crawl, not an LLM
        # choice) and the reply/narration mechanism (see assistant.py).
        # None means ai-layer's own automatic routing, same as before this
        # existed.
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

    def run_stage(self, context: dict) -> dict:
        stage = self.current_stage
        agent = stage_agents[stage]
        self.last_context = context
        # self.constraints is looked up fresh (not snapshotted at run_stage() call time),
        # so a correction recorded via add_constraint() after this call is picked up the
        # next time run_stage()/rerun() runs this same stage.
        enriched_context = {**context, "constraints": self.constraints, "model": self.model}
        output = agent(enriched_context)
        self.last_output = output
        return {"stage": stage, "output": output, "valid": validate(output)}

    def rerun(self, overrides: dict | None = None) -> dict:
        """Re-run the current stage in the background, reusing last_context
        plus any given overrides (only meaningful for the docs stage) and
        picking up constraints recorded since the last run. Used by both
        /rerun and the rerun_stage tool, the only difference between a human
        clicking Retry and an LLM deciding to call rerun_stage is who (if
        anyone) supplies overrides."""
        overrides = overrides or {}
        if overrides and self.current_stage != "docs":
            raise ValueError(
                f"'{self.current_stage}' has no structured parameters to override, only 'docs' does."
            )
        context = {**self.last_context, **overrides}
        self.run_stage_async(context)
        return {"status": "started", "stage": self.current_stage}

    def add_constraint(self, stage: str, constraint: str) -> None:
        self.constraints.setdefault(stage, []).append(constraint)

    def advance_stage(self) -> str | None:
        self.current_stage_index += 1
        return self.current_stage

    def _validate_review(self, stage_id: str, approved: bool, correction: str | None) -> None:
        """A stale or hallucinated stage_id, or an approval=False with no
        correction, can't silently corrupt pipeline state."""
        if stage_id != self.current_stage:
            raise ValueError(
                f"'{stage_id}' is not the current pending stage (current: {self.current_stage!r})."
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
        """Appends event, then reacts to it via the wired-in reactor (see
        set_reactor()) and appends that reply too, as its own "message"
        event. The reaction is a side effect of recording, callers never
        trigger it themselves. A narration failure (including no reactor
        wired in at all, e.g. a bare Orchestrator() in a unit test) is
        swallowed, with a fallback message recorded instead, so it can't mask
        the real event, especially inside a background thread."""
        event = {"type": event_type, "stage": stage, "data": data, "timestamp": time.time()}
        self.events.append(event)
        model = None
        try:
            if _reactor is None:
                raise RuntimeError("no reactor wired in, see set_reactor()")
            reply = _reactor(_summarize_for_reaction(event), self.events[:-1])
            text = reply.get("message") or "(no reply)"
            model = reply.get("model")
        except Exception:
            logger.exception("reactor failed for event %s", event_type)
            text = "(narration unavailable)"
        self.events.append({"type": "message", "stage": stage, "text": text, "model": model, "timestamp": time.time()})
        return event

    def run_stage_async(self, context: dict) -> None:
        """Starts the current stage's agent on a background thread and
        returns immediately: calls and stops. The only way a stage ever
        starts running, REST endpoints and nudge()'s tool dispatch both call
        this directly, neither has its own copy of "run it in the
        background." Setting busy here (before the thread even starts, not
        inside it) means a poller can never observe a run that's already been
        triggered as not-busy."""
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


_default = Orchestrator()


def run_stage(context: dict) -> dict:
    return _default.run_stage(context)


def rerun_stage(overrides: dict | None = None) -> dict:
    return _default.rerun(overrides)


def add_constraint(stage: str, constraint: str) -> None:
    _default.add_constraint(stage, constraint)


def advance_stage() -> str | None:
    return _default.advance_stage()


def record_review(stage_id: str, approved: bool, correction: str | None = None) -> dict:
    return _default.record_review(stage_id, approved, correction)


def review(stage_id: str, approved: bool, correction: str | None = None) -> dict:
    return _default.review(stage_id, approved, correction)


def run_stage_async(context: dict) -> None:
    _default.run_stage_async(context)


def start_stage_run(context: dict) -> dict:
    """Start the current stage running in the background with the given
    context, and report that it started. Used directly by /start (via
    start_pipeline) and is the run_stage tool's impl."""
    run_stage_async(context)
    return {"status": "started", "stage": current_stage()}


def events() -> list[dict]:
    return _default.events


def append_event(event: dict) -> None:
    """Appends a raw event with no reaction triggered, unlike record_event().
    Used by assistant.nudge() for the human's own message and the LLM's
    reply, neither of which is itself something to react to."""
    _default.events.append(event)


def is_busy() -> bool:
    return _default.busy


def last_context() -> dict:
    return dict(_default.last_context)


def current_stage() -> str | None:
    return _default.current_stage


def current_model() -> str | None:
    return _default.model


def set_model(model: str | None) -> None:
    """Changes the model for the rest of this run, not just what /start
    chose: every subsequent real chat() call (a stage run, a retry, or a
    nudge) picks this up, since they all read Orchestrator.model fresh each
    time, not a value snapshotted at start_pipeline() time."""
    _default.model = model


def list_providers() -> list[dict]:
    """Proxies ai-layer's real GET /providers. The frontend never calls
    ai-layer directly (see ai/chat-ui/CLAUDE.md), this is the one place that
    does, on its behalf, so the model picker can show real, current
    provider/tier options rather than a hardcoded list that could drift."""
    response = httpx.get(f"{AI_LAYER_URL}/providers", timeout=10.0)
    response.raise_for_status()
    return response.json()


def reset_pipeline() -> None:
    """Start a fresh pipeline run: replace the default Orchestrator instance,
    dropping any progress and constraints from a prior run."""
    global _default
    _default = Orchestrator()


def start_pipeline(platform_description: str, seed_url: str, model: str | None = None) -> dict:
    """Reset the pipeline and start the docs stage running in the background.
    Used directly by both /start and the start_pipeline tool (which never
    supplies model, that choice is REST/UI-driven at Start time, not
    something the LLM decides via nudge). model, once set, applies to every
    real chat() call for the rest of this run (see Orchestrator.model)."""
    reset_pipeline()
    _default.model = model
    context = {"platform_description": platform_description, "seed_url": seed_url}
    return start_stage_run(context)


def wait_for_idle(timeout: float = 5.0) -> None:
    """Blocks until any in-flight background stage run finishes. Not used by
    the API itself (a real client polls GET /events instead); exists so
    tests can synchronize deterministically instead of sleeping/polling."""
    thread = _default._last_thread
    if thread is not None:
        thread.join(timeout=timeout)
