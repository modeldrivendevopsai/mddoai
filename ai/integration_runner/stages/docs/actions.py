"""Docs-stage-specific ad-hoc actions: real extra capabilities a human or
an LLM tool call can trigger on a run beyond running/rerunning the docs
stage itself. Not a stage agent (never in stage_agents, never dispatched by
run_stage()) — this takes `run` directly and mutates it, a dependency
agent.py in this same folder deliberately doesn't have (see agent.py's own
docstring for why that split matters).

extend_with_page() is the one action here today: fetches one specific page
for real (retrieval's own POST /fetch/page, no crawling) and appends its
real content onto the docs stage's current pending output — an ADD, not a
replace. Different from calling rerun_stage with different crawl parameters
(which redoes the whole crawl and replaces last_output entirely): this is
for when the docs stage already produced a mostly-good result and is
missing one specific page whose URL is already known (by a human reviewing
it, or, later, by the Orchestrator itself noticing a gap on its own). Real
effect, not a summary log: last_output actually changes, so what a human
reviews next reflects it.

Scoped to while docs is still the current, not-yet-approved pending stage —
amending an already-approved docs output after later stages have run is
real, bigger future work (would need to cascade re-runs downstream), not
attempted here.
"""
from clients import retrieval_client


def extend_with_page(run, url: str, force_refresh: bool = False) -> dict:
    """Fetches url for real and appends it to run.last_output. Raises
    ValueError if docs isn't the current pending stage — there's no
    reviewable output to extend otherwise, and appending to a stage that
    hasn't produced output yet (or has already advanced past docs) would
    silently corrupt what a human is reviewing. Raises RuntimeError if the
    fetch itself failed, rather than silently appending nothing."""
    if run.current_stage != "docs":
        raise ValueError(
            f"Can only extend the docs stage's output while it's the current pending "
            f"stage (current: {run.current_stage!r})."
        )
    page = retrieval_client.fetch_page(url, force_refresh=force_refresh)
    if not page["success"]:
        raise RuntimeError(f"Fetch for {url} failed (status {page['status_code']}).")
    addition = f"# {page['url']}\n{page['markdown']}"
    run.last_output = f"{run.last_output or ''}\n\n{addition}".strip()
    summary = {
        "url": page["url"],
        "success": page["success"],
        "status_code": page["status_code"],
        "markdown_length": len(page["markdown"]),
    }
    run.record_event("documentation_extended", run.current_stage, summary)
    return summary
