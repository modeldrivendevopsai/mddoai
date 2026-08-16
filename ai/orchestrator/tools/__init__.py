"""MDDOAI's declared LLM-tool-calling abilities, grouped by what they
operate on, not left flat in one file: pipeline_control.py (generic,
stage-agnostic pipeline lifecycle — run/rerun/review/constrain/restart) and
docs.py (the docs stage's own retrieval actions). These are already two
distinct, real groups today, that's the actual justification for the split,
not a guess at what a future group might look like. get_tools() below is
the one place that assembles every group into the single list
tool_calling.load_tools() filters by stage.

A future agent that gets its own real tools (e.g. a validation agent, once
ai/integration_agent's already-real POST /validate/ecore and
POST /validate/atl endpoints get wired into the live pipeline) adds one new
sibling module here and one line in get_tools() below, not a growing edit
to an existing file. Not built speculatively now: there's no second real
per-stage-tool example yet to generalize a shared pattern from, only
docs.py's, and generalizing from one case is exactly the guesswork this
repo's own YAGNI rule warns against.

stage_metadata() is shared by pipeline_tools.py (the system prompt's stage
list) and pipeline_control.py (stage_result/add_constraint's enum
parameters) — fetched from integration_runner's real GET /stages once and
cached here, never a second, hardcoded copy that could drift from the real
source.
"""
from clients import integration_runner_client
from tools import docs, pipeline_control

_stage_metadata_cache: dict | None = None


def stage_metadata() -> dict:
    """{"stages": [...], "descriptions": {...}}."""
    global _stage_metadata_cache
    if _stage_metadata_cache is None:
        _stage_metadata_cache = integration_runner_client.get_stage_metadata()
    return _stage_metadata_cache


_tools_cache: list | None = None


def get_tools() -> list:
    global _tools_cache
    if _tools_cache is None:
        _tools_cache = pipeline_control.get_tools(stage_metadata()) + docs.get_tools()
    return _tools_cache
