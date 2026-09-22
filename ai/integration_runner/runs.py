"""Process-wide run registry: which run is current, and the history of
every run this process has seen. Separate from pipeline.py's class
IntegrationRun: this is "how many runs exist and which one is current,"
IntegrationRun is "how does one run advance through its stages" — a real,
different job, the one already flagged as a likely future persistence-layer
change point, independent of stage-advancement rules.

Everything about operating on the CURRENT run — running a stage, reviewing
it, adding a constraint, fetching documentation — is a real method on
IntegrationRun itself (see pipeline.py). This module doesn't duplicate any
of that as its own proxy functions; callers fetch the real instance via
current() and call its own methods directly (runs.current().review(...),
runs.current().add_constraint(...), ...), so there's exactly one place each
operation is implemented, not two.
"""
import threading

from integration_runner.pipeline import STAGES, IntegrationRun

# Serializes the functions that reassign _default (start_pipeline,
# reset_pipeline, resume_run) against each other. Without it, two
# near-simultaneous /start (or /start + /reset + /resume) in the idle gap
# between stages could each swap _default for a different IntegrationRun,
# leaving one orphaned in _runs and _default pointing at the other.
# Reentrant because start_pipeline() calls reset_pipeline().
#
# Residual, not closed here: a mutating handler that captured runs.current()
# before one of these swaps could still act on the now-previous _default (a
# wasted stage run on a run no longer current, not corruption of the live
# one). Closing that needs every mutating handler to hold this lock across
# its whole "read current, act on it" step - a broader change than the
# per-run busy claim this commit is scoped to.
_registry_lock = threading.RLock()

_default = IntegrationRun()
# Every IntegrationRun that's ever been "the" current run, keyed by run_id, kept
# for the life of this process (see reset_pipeline() — it no longer clears
# this). The mutating functions below still only ever act on _default, that
# one-active-run-at-a-time behavior is unchanged; this dict is the read side
# for history — list_runs() and a run_id-scoped events read, so the sidebar
# can show past runs without needing real persistence (in-memory only, gone
# on restart, that's fine).
_runs: dict[str, "IntegrationRun"] = {_default.run_id: _default}


def current() -> IntegrationRun:
    """The real, live IntegrationRun instance callers should operate on
    directly — the one thing every endpoint in main.py actually needs from
    this registry. Everything else in this module is about the SET of
    runs, not any one of them."""
    return _default


def get_run(run_id: str) -> "IntegrationRun | None":
    return _runs.get(run_id)


def current_run_id() -> str:
    return _default.run_id


def _reject_if_busy(run: "IntegrationRun") -> None:
    """Claims and immediately releases busy on `run`, the atomic backstop
    every function that reassigns _default needs before discarding or
    stepping away from a possibly-still-executing run — a route's own
    pre-flight `if run.busy` check (routes/core.py) is a plain read, racing
    against any other caller's claim_busy() (run_stage_async(), review(),
    /docs/extend) in the gap between that read and the swap actually
    running. Raises BusyError instead of silently orphaning that caller's
    still-running background thread under a run nothing points to as
    current anymore. Shared by reset_pipeline(), resume_run(), and
    start_pipeline()'s own reset branch, all three of which need exactly
    this same check before the same kind of swap."""
    run.claim_busy()
    run.release_busy()


def _platform_name(run: "IntegrationRun") -> str | None:
    for event in run.events:
        data = event.get("data") or {}
        if "platform_description" in data:
            return data["platform_description"]
    return None


def list_runs() -> list[dict]:
    """Summaries of every run this process has seen, newest first, for the
    sidebar's session list. is_current tells the caller which one is safe
    to interact with (approve/reject/retry) vs read-only history.

    Snapshots _runs and the current run_id under _registry_lock before
    building the summary list: reset_pipeline() inserts into _runs under
    that same lock, and iterating a dict's own .values() view while another
    thread inserts into it is a real "dictionary changed size during
    iteration" RuntimeError, not just a staleness concern - reading the
    lock-free snapshot outside the lock afterward keeps the lock held only
    as long as the actual shared-state read needs it."""
    with _registry_lock:
        current_id = _default.run_id
        runs_snapshot = list(_runs.values())
    return [
        {
            "run_id": run.run_id,
            "platform_name": _platform_name(run),
            "current_stage": run.current_stage,
            "busy": run.busy,
            "is_current": run.run_id == current_id,
        }
        for run in reversed(runs_snapshot)
    ]


def get_run_events(run_id: str) -> dict | None:
    """Same shape as the live /events response, for a specific (possibly
    past) run rather than always _default. None if run_id is unknown."""
    run = get_run(run_id)
    if run is None:
        return None
    return {
        "events": run.events,
        "current_stage": run.current_stage,
        "busy": run.busy,
        "model": run.model,
        "is_current": run.run_id == _default.run_id,
    }


def reset_pipeline() -> None:
    """Start a fresh pipeline run: replace the default IntegrationRun instance.
    The prior run's instance stays in _runs (see list_runs()) so the sidebar
    can show it as history for the life of this process — no persistence
    across a restart, in-memory only, that's the deliberate MVP scope.

    Raises BusyError (via _reject_if_busy()) instead of swapping the run
    being replaced out from under its own in-flight thread."""
    global _default
    with _registry_lock:
        _reject_if_busy(_default)
        _default = IntegrationRun()
        _runs[_default.run_id] = _default


def resume_run(run_id: str) -> dict:
    """Make an existing run current again, so it can be approved/retried
    like any other live run — the counterpart to reset_pipeline(), which
    replaces _default with a blank run instead of an existing one. The run's
    own state (constraints, events, current_stage_index) is untouched,
    nothing is replayed or reset, it just picks up exactly where it left
    off. Raises ValueError for an unknown run_id, left for the caller (this
    service's own main.py) to turn into the right HTTP status, same
    convention as IntegrationRun.review()/rerun(). Raises BusyError (via
    _reject_if_busy()) if the run being replaced is busy, same as
    reset_pipeline()."""
    global _default
    with _registry_lock:
        run = get_run(run_id)
        if run is None:
            raise ValueError(f"No run with id {run_id!r}")
        _reject_if_busy(_default)
        _default = run
        return {"run_id": _default.run_id, "current_stage": _default.current_stage}


def start_pipeline(
    platform_description: str,
    seed_url: str,
    model: str | None = None,
    docs_options: dict | None = None,
) -> dict:
    """Start the docs stage running in the background, against a genuinely
    new run only if the current one isn't already a blank slot — the one
    place this registry still does more than pure bookkeeping.

    A blank slot (zero events: nothing has run on it yet) can come from
    reset_pipeline() itself, or from resume_run() bringing back a past run
    that was reset-and-then-abandoned before ever being started. Reusing it
    in place, same run_id, rather than discarding it for yet another fresh
    IntegrationRun, is what lets "Resume this run" -> fill in the start
    form actually continue that resumed run: without this, the instant
    Start was clicked it would silently swap in a different run anyway,
    stranding the one that was just resumed as a second, permanently-empty
    history entry. A run that already has events (mid-pipeline, or fully
    complete) still always gets a fresh IntegrationRun, e.g. Restart's own
    "re-run the same platform from scratch" call to this same function.

    docs_options is the same shape rerun()'s overrides accepts for the docs
    stage (hint, exclude_urls, max_pages, max_depth, force_refresh) — set
    once here up front instead of only being reachable via a retry."""
    with _registry_lock:
        # Claimed before the events check, not just read: busy flips True
        # (inside claim_busy(), called by run_stage_async()/review()/
        # /docs/extend) strictly before that caller's first event is ever
        # recorded, so a plain `if _default.busy` read here could still see
        # busy=False and events=[] in that gap and wrongly treat an
        # already-claimed run as a blank slate to reuse. Claiming for real
        # closes that: a concurrent claim on this same instance now loses
        # the race cleanly (BusyError propagates, nothing below runs) rather
        # than racing set_model()/the reused instance's own thread.
        _default.claim_busy()
        if _default.events:
            # Discarding this instance for a fresh one - release the claim
            # first so reset_pipeline() (which runs its own _reject_if_busy()
            # check) doesn't see it as already busy and refuse itself.
            _default.release_busy()
            reset_pipeline()
            # A freshly constructed IntegrationRun is never busy - this
            # always succeeds, and is what actually protects the new
            # instance's model/thread-start below.
            _default.claim_busy()
        _default.set_model(model)
        context = {"platform_description": platform_description, "seed_url": seed_url, **(docs_options or {})}
        # start_claimed_stage_run(), not start_stage_run(): busy is already
        # claimed above, before the reset-or-reuse decision, earlier than
        # start_stage_run()'s own claim_busy() would run.
        return _default.start_claimed_stage_run(context)


def _seed_context_from(source_run: IntegrationRun, from_stage: str) -> dict:
    """The real context from_stage would have received the first time
    source_run reached it, reconstructed from source_run's own final
    last_context rather than replayed from history: record_review() only
    ever adds one f"{stage}_output" key per approval, purely additively
    (see its own docstring), so subtracting from_stage's own key and every
    later stage's from a run that got at least that far reproduces exactly
    the context that stage originally ran with, no on-disk attempt replay
    needed. Raises ValueError if source_run never actually got far enough
    for this to be meaningful: forking a run that died at "docs" into
    "atl" would otherwise silently hand atl blank pim_output/psm_output
    instead of erroring (see stages/atl/agent.py's own context.get(...,"")
    defaults - designed for a direct/test caller skipping context on
    purpose, not for this)."""
    boundary = STAGES.index(from_stage)
    required = [f"{s}_output" for s in STAGES[:boundary]]
    missing = [key for key in required if key not in source_run.last_context]
    if missing:
        raise ValueError(
            f"Run {source_run.run_id!r} never produced {', '.join(missing)}, can't fork it into {from_stage!r}."
        )
    drop = {f"{s}_output" for s in STAGES[boundary:]}
    return {k: v for k, v in source_run.last_context.items() if k not in drop}


def fork_run(source_run_id: str, from_stage: str) -> dict:
    """Starts a genuinely new run that reuses a past run's own real output
    up to (not including) from_stage, then pauses there for a human to
    review/correct before it runs for real - the real answer to "the
    problem was actually in an earlier stage, not generation itself, and I
    don't want to redo the whole pipeline to fix it." Rather than rewinding
    source_run in place (current_stage_index only ever advances, by design,
    and nothing else in this codebase assumes otherwise), this creates a
    sibling run, so source_run's own real history, including its own
    failure, stays exactly as it happened, inspectable via
    GET /events?run_id=source_run_id forever, while the new run gets a
    clean shot at from_stage onward. from_stage can be any real stage, not
    only the one right before generation: the real problem behind a
    generation failure could just as easily be the platform's own fetched
    docs or its target metamodel as the ATL/Acceleo built from them.

    Deliberately takes no "reason"/correction parameter of its own: the
    caller uses the existing real POST /constraint/{stage} against the
    newly-forked run afterward, the same real mechanism a normal rejection
    already uses, rather than a second, parallel way to record one.

    Always leaves from_stage pending rather than auto-starting it, even for
    a stage outside _REQUIRES_MANUAL_START: a fork is exactly the moment a
    human just made a real judgment call about where the pipeline actually
    went wrong, they should get the same pause every other manual-start
    stage already gets, to add that reasoning as a constraint before it
    fires - auto-starting immediately would race a POST /constraint/{stage}
    call sent right after this one, with no guarantee run_stage()'s own
    first attempt reads it before it starts.

    Raises ValueError for an unknown source_run_id or an unreal from_stage,
    left for the caller (this service's own main.py) to turn into the right
    HTTP status, same convention as resume_run()/rerun(). Raises BusyError
    (via _reject_if_busy()) if the run being replaced is busy, same as
    reset_pipeline()/resume_run()."""
    if from_stage not in STAGES:
        raise ValueError(f"{from_stage!r} isn't a real stage, choose one of {STAGES}.")
    global _default
    with _registry_lock:
        source_run = get_run(source_run_id)
        if source_run is None:
            raise ValueError(f"No run with id {source_run_id!r}")
        seed_context = _seed_context_from(source_run, from_stage)
        _reject_if_busy(_default)
        new_run = IntegrationRun()
        new_run.current_stage_index = STAGES.index(from_stage)
        new_run.last_context = seed_context
        new_run.record_event("forked_from", from_stage, {"source_run_id": source_run_id})
        _default = new_run
        _runs[new_run.run_id] = new_run
        return {"run_id": new_run.run_id, "stage": new_run.current_stage}


def wait_for_idle(timeout: float = 5.0) -> None:
    """Blocks until any in-flight background stage run finishes. Not used by
    the API itself (a real client polls GET /events instead); exists so
    tests can synchronize deterministically instead of sleeping/polling."""
    thread = _default._last_thread
    if thread is not None:
        thread.join(timeout=timeout)
