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
from integration_runner.pipeline import IntegrationRun

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


def _platform_name(run: "IntegrationRun") -> str | None:
    for event in run.events:
        data = event.get("data") or {}
        if "platform_description" in data:
            return data["platform_description"]
    return None


def list_runs() -> list[dict]:
    """Summaries of every run this process has seen, newest first, for the
    sidebar's session list. is_current tells the caller which one is safe
    to interact with (approve/reject/retry) vs read-only history."""
    return [
        {
            "run_id": run.run_id,
            "platform_name": _platform_name(run),
            "current_stage": run.current_stage,
            "busy": run.busy,
            "is_current": run.run_id == _default.run_id,
        }
        for run in reversed(list(_runs.values()))
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
    across a restart, in-memory only, that's the deliberate MVP scope."""
    global _default
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
    convention as IntegrationRun.review()/rerun()."""
    global _default
    run = get_run(run_id)
    if run is None:
        raise ValueError(f"No run with id {run_id!r}")
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
    if _default.events:
        reset_pipeline()
    _default.set_model(model)
    context = {"platform_description": platform_description, "seed_url": seed_url, **(docs_options or {})}
    return _default.start_stage_run(context)


def wait_for_idle(timeout: float = 5.0) -> None:
    """Blocks until any in-flight background stage run finishes. Not used by
    the API itself (a real client polls GET /events instead); exists so
    tests can synchronize deterministically instead of sleeping/polling."""
    thread = _default._last_thread
    if thread is not None:
        thread.join(timeout=timeout)
