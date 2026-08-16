"""The docs stage: the one real (non-placeholder) stage agent besides
serialization_agent, calling retrieval_client.fetch_documentation() and
formatting the result. Pure and run-agnostic, like every stage agent (see
stages/__init__.py's own docstring): takes a plain context dict, knows
nothing about IntegrationRun.

Not related to actions.py's extend_with_page() in this same folder, despite
both wrapping retrieval_client calls: docs_stage() replaces the docs stage's
whole output via a real crawl, dispatched through run_stage()/rerun();
extend_with_page() appends one specific page to an already-produced output,
called directly against a run, never dispatched through stage_agents. See
actions.py's own module docstring for the real reasoning behind that split.
"""
import os

from clients import retrieval_client

# Opt-in only (unset by default, so a real `docker compose up` still runs the
# real crawl): when set, docs_stage() skips retrieval entirely and returns
# canned output instantly. For iterating on the orchestrator UI/mechanics
# (approve/reject/retry/message) without needing a real, valid documentation
# URL or waiting on a real 90+ second crawl each time. Set in orchestrator's
# own .env (gitignored, not docker-compose.yml's committed defaults), so it
# never silently affects a real run.
_STUB_DOCS = os.environ.get("ORCHESTRATOR_STUB_DOCS", "").strip().lower() in ("1", "true", "yes")
# retrieval's own AdaptiveCrawler stops early on a poor crawl (min_gain_threshold),
# so a fetch that returns can still have found essentially nothing useful. This is
# the floor below which that's treated as a hard failure, not a low-quality result
# for a human to review, an engineering guess, not measured against a real corpus.
_DOCS_MIN_CONFIDENCE = 0.15


def docs_stage(context: dict) -> str:
    """The docs stage's normal (non-tool-call) path: calls
    retrieval_client.fetch_documentation with whatever context supplies,
    formats the result as the stage's output string, and fails hard if the
    crawl found essentially nothing. An explicit context["hint"] (from a
    /rerun override) takes priority over the constraints-derived one.
    Short-circuits to canned output, skipping retrieval entirely, when
    either the process-wide ORCHESTRATOR_STUB_DOCS env var is set (see
    _STUB_DOCS) or the caller passed context["mock"] (the per-run "Mock"
    checkbox on the Start/Retry form, see integration_runner/routes/core.py's
    StartRequest/RerunOverrides — real crawls are slow enough during local
    dev that a permanent env var is too blunt, this is opt-in per run
    instead).
    """
    seed_url = context.get("seed_url", "")
    if _STUB_DOCS or context.get("mock"):
        return (
            f"[MOCKED] Skipped the real crawl of {seed_url}. "
            f"This is placeholder output for testing the pipeline's mechanics, not real documentation."
        )
    constraints = context.get("constraints", {}).get("docs", [])
    hint = context.get("hint") or (" ".join(constraints) if constraints else None)

    result = retrieval_client.fetch_documentation(
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
