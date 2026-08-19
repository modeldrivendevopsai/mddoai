"""The generic, stage-agnostic pipeline-lifecycle tools: they operate on
"whichever stage is current," not any specific stage's own abilities, so
they carry no stages= restriction (see tool_calling.Tool's own docstring).
Every impl is an integration_runner_client function directly, except
rerun_stage and start_pipeline, which each need a small wrapper (see their
own docstrings below) for the same reason: the real REST endpoint groups
the docs stage's structured overrides into one nested object, but a tool
schema exposes them as flat, individually-described parameters for the
LLM.
"""
import tool_calling
from clients import integration_runner_client


def _docs_overrides(hint, exclude_urls, max_pages, max_depth, force_refresh, mock=None) -> dict:
    """Assembles integration_runner's real docs-stage override shape
    (RerunOverrides/the docs_options part of StartRequest) from individual
    tool arguments, dropping anything the LLM didn't actually supply.
    Shared by _rerun_stage_tool and _start_pipeline_tool below since both
    wrap a REST endpoint accepting the identical override fields."""
    overrides = {
        "hint": hint, "exclude_urls": exclude_urls, "max_pages": max_pages,
        "max_depth": max_depth, "force_refresh": force_refresh, "mock": mock,
    }
    return {k: v for k, v in overrides.items() if v is not None}


def _rerun_stage_tool(
    hint: str | None = None,
    exclude_urls: list[str] | None = None,
    max_pages: int | None = None,
    max_depth: int | None = None,
    force_refresh: bool | None = None,
    mock: bool | None = None,
) -> dict:
    """Tool-call wrapper around integration_runner_client.rerun_stage():
    reads the current stage fresh via GET /status first (the real POST
    /rerun/{stage_id} endpoint needs a stage_id in its path for its own
    staleness check, the same real check a direct REST caller's stage_id
    would be validated against), then forwards only the override fields the
    LLM actually supplied. These are real, structured docs stage overrides
    (see integration_runner's own RerunOverrides) — the REST endpoint has
    always accepted them, this tool's own schema just exposes them now so a
    chat-triggered rerun has the same real steering ability a direct
    REST/manual caller already had. integration_runner's own rerun()
    rejects them with a real ValueError (surfaced here as this call's own
    error result) on any stage but docs, since only docs has a structured
    override shape today."""
    current_stage = integration_runner_client.get_status()["current_stage"]
    overrides = _docs_overrides(hint, exclude_urls, max_pages, max_depth, force_refresh, mock)
    return integration_runner_client.rerun_stage(current_stage, overrides)


def _start_pipeline_tool(
    platform_description: str,
    seed_url: str,
    model: str | None = None,
    hint: str | None = None,
    exclude_urls: list[str] | None = None,
    max_pages: int | None = None,
    max_depth: int | None = None,
    force_refresh: bool | None = None,
    mock: bool | None = None,
) -> dict:
    """Tool-call wrapper around integration_runner_client.start_pipeline():
    the real POST /start endpoint accepts the docs stage's structured
    overrides (including mock, for a fast canned run with no real crawl,
    e.g. when a human explicitly wants to test the pipeline's mechanics)
    as flat top-level fields, but the client function groups them into one
    docs_options dict — this assembles that from whichever of the tool's
    own optional arguments the LLM actually supplied, same pattern
    _rerun_stage_tool already uses."""
    docs_options = _docs_overrides(hint, exclude_urls, max_pages, max_depth, force_refresh, mock)
    return integration_runner_client.start_pipeline(platform_description, seed_url, model, docs_options)


def get_tools(stage_metadata: dict) -> list["tool_calling.Tool"]:
    stages = stage_metadata["stages"]
    return [
        tool_calling.Tool(
            name="run_stage",
            description=(
                "Run the CURRENT pending pipeline stage of an already-started run, with extra or "
                "overriding context beyond what it would normally use. This does NOT start a new "
                "run and does NOT set up the docs stage's seed_url on its own — use start_pipeline "
                "for that, whether the human gave a real platform/URL or asked you to just pick "
                "one. Only reach for this when a run already exists and the human wants the "
                "current stage's agent given some specific extra input right now. Do NOT use this "
                "for approving, rejecting, or redoing existing output, use stage_result or "
                "rerun_stage for that. In particular, 'next'/'next stage'/'continue'/'moving on' "
                "mean approve and advance (stage_result), not this tool."
            ),
            parameters={
                "type": "object",
                "properties": {
                    "context": {
                        "type": "object",
                        "description": (
                            "Extra or overriding input for the current stage's agent, layered onto "
                            "its normal context. Rarely needs anything supplied manually."
                        ),
                    },
                },
                "required": ["context"],
            },
            impl=integration_runner_client.start_stage_run,
        ),
        tool_calling.Tool(
            name="rerun_stage",
            description=(
                "Re-run the CURRENT pending stage's agent, reusing the context from its last "
                "run and folding in any constraints recorded via add_constraint since then. Use "
                "this when the user wants the current stage redone right now, e.g. 'redo the "
                "ATL stage', or immediately after calling add_constraint to apply a correction "
                "the user just gave. On the docs stage specifically, the optional parameters "
                "below steer a brand-new crawl (a different hint, excluding known-bad pages, a "
                "narrower/wider crawl) — this REPLACES the docs stage's current output with a "
                "fresh crawl, it does not add to what's already there. To add one specific known "
                "page without redoing the whole crawl, use add_page_to_docs instead. These "
                "parameters only mean anything on the docs stage; using them on any other stage "
                "is rejected."
            ),
            parameters={
                "type": "object",
                "properties": {
                    "hint": {
                        "type": "string",
                        "description": "Docs stage only: free text steering a fresh crawl toward something specific.",
                    },
                    "exclude_urls": {
                        "type": "array",
                        "items": {"type": "string"},
                        "description": "Docs stage only: URLs already known to be wrong, ruled out of the fresh crawl.",
                    },
                    "max_pages": {"type": "integer", "description": "Docs stage only: cap on how many pages to crawl."},
                    "max_depth": {"type": "integer", "description": "Docs stage only: cap on how many link-hops deep to crawl."},
                    "force_refresh": {
                        "type": "boolean",
                        "description": "Docs stage only: bypass retrieval's page cache and refetch from scratch.",
                    },
                    "mock": {
                        "type": "boolean",
                        "description": (
                            "Docs stage only: skip the real crawl entirely and use canned "
                            "placeholder output instead. Only set this when the human explicitly "
                            "asks for a mock/test/fake rerun."
                        ),
                    },
                },
            },
            impl=_rerun_stage_tool,
        ),
        tool_calling.Tool(
            name="stage_result",
            description=(
                "Record a human review decision for a named stage. approved=true advances the "
                "pipeline and immediately starts the next stage running, use this when the user "
                "approves/accepts a stage's output. approved=false with a correction records "
                "the correction for later (it does NOT rerun immediately), use this only when "
                "the user explicitly rejects a stage without asking for an immediate redo; if "
                "they want it fixed right now, prefer add_constraint followed by rerun_stage "
                "instead."
            ),
            parameters={
                "type": "object",
                "properties": {
                    "stage_id": {
                        "type": "string",
                        "enum": stages,
                        "description": "Which stage this decision applies to.",
                    },
                    "approved": {"type": "boolean", "description": "true to approve and advance, false to reject."},
                    "correction": {
                        "type": ["string", "null"],
                        "description": "Required when approved is false: the human's correction.",
                    },
                },
                "required": ["stage_id", "approved"],
            },
            impl=integration_runner_client.review,
        ),
        tool_calling.Tool(
            name="add_constraint",
            description=(
                "Record a correction against a stage without rerunning it yet. The correction "
                "is automatically folded into that stage's prompt the next time it runs (via "
                "rerun_stage, or the pipeline advancing into it). Call this immediately before "
                "rerun_stage when the user gives feedback and wants a stage fixed now, e.g. "
                "'the ATL rules are wrong, use kebab-case names' -> "
                "add_constraint('atl', 'Use kebab-case rule names') then rerun_stage()."
            ),
            parameters={
                "type": "object",
                "properties": {
                    "stage": {
                        "type": "string",
                        "enum": stages,
                        "description": "Which stage this correction applies to.",
                    },
                    "constraint": {"type": "string", "description": "The human's correction/instruction for this stage."},
                },
                "required": ["stage", "constraint"],
            },
            impl=integration_runner_client.add_constraint,
        ),
        tool_calling.Tool(
            name="start_pipeline",
            description=(
                "Reset the pipeline and start fresh at the docs stage for a NEW platform "
                "description and its real documentation URL, discarding all progress, approved "
                "stages, and recorded constraints from the current run. Use this when the user "
                "wants to start over or switch to a different platform entirely, e.g. 'let's do "
                "this for GitLab instead' or 'start over with a new platform', NOT for "
                "continuing or redoing the current pipeline (use run_stage or rerun_stage for "
                "that). The optional parameters below steer the very first real crawl the same "
                "way rerun_stage's own optional parameters steer a later one."
            ),
            parameters={
                "type": "object",
                "properties": {
                    "platform_description": {
                        "type": "string",
                        "description": "Description of the new CI/CD platform to generate a pipeline for.",
                    },
                    "seed_url": {
                        "type": "string",
                        "description": "The platform's real CI/CD documentation URL, for the docs stage to fetch.",
                    },
                    "model": {
                        "type": "string",
                        "description": "Which model to use for the rest of this run. Omit for automatic routing.",
                    },
                    "hint": {"type": "string", "description": "Free text steering the initial crawl toward something specific."},
                    "exclude_urls": {
                        "type": "array",
                        "items": {"type": "string"},
                        "description": "URLs already known to be wrong, ruled out of the initial crawl.",
                    },
                    "max_pages": {"type": "integer", "description": "Cap on how many pages to crawl."},
                    "max_depth": {"type": "integer", "description": "Cap on how many link-hops deep to crawl."},
                    "force_refresh": {"type": "boolean", "description": "Bypass retrieval's page cache and refetch from scratch."},
                    "mock": {
                        "type": "boolean",
                        "description": (
                            "Skip the real crawl entirely and use canned placeholder output "
                            "instead, for quickly testing the pipeline's mechanics. Only set "
                            "this when the human explicitly asks for a mock/test/fake run."
                        ),
                    },
                },
                "required": ["platform_description", "seed_url"],
            },
            impl=_start_pipeline_tool,
        ),
    ]
