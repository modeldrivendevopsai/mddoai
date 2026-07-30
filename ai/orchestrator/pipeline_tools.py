"""MDDOAI's actual abilities: its system prompt and its 7 declared tools.
This is the file to open to see what the orchestrator can do, tool_calling.py
is just the generic engine these get plugged into (it has no idea these 7
specifically exist); assistant.py is what actually calls
tool_calling.build_reply() with this SYSTEM_PROMPT_TEMPLATE and TOOLS. The
stage agents themselves (what actually runs when a tool advances a stage)
live in stage_agents.py, only STAGE_DESCRIPTIONS is used here directly.

Each Tool bundles its own schema and real Python implementation as a single
object, so adding a new ability means adding one entry here, nothing else,
and a schema can never drift from its impl the way a separately maintained
config file and impl dict could. Most impls are orchestrator.py functions
directly; fetch_documentation/fetch_page go through a local summarizing
wrapper first (see below) so a tool-call result doesn't dump a whole crawl
into a nudge() reply, but still end in a real orchestrator.py call.

What's currently declared, at a glance:
  Global (every stage):  run_stage, rerun_stage, stage_result,
                          add_constraint, start_pipeline
  docs only:              fetch_documentation, fetch_page
    (real wrappers around retrieval's own POST /fetch and POST /fetch/page)
"""
import orchestrator
import stage_agents
import tool_calling

# Built from orchestrator.STAGES/stage_agents.STAGE_DESCRIPTIONS, not hand-
# copied here, so adding a stage never leaves this prompt describing a stale
# pipeline.
_STAGE_LIST_TEXT = "\n".join(
    f"  {i}. {stage}: {stage_agents.STAGE_DESCRIPTIONS[stage]}"
    for i, stage in enumerate(orchestrator.STAGES, start=1)
)

SYSTEM_PROMPT_TEMPLATE = f"""
You are the MDDOAI Orchestrator. MDDOAI turns a CI/CD platform description into
generated pipeline tooling through {len(orchestrator.STAGES)} fixed stages, always in this order:

{_STAGE_LIST_TEXT}

The current pending stage (the one whose output a human is reviewing right now) is: {{current_stage}}.

You receive either a structured event (a call starting, completing, or failing) or a
free-form message from a human, and reply.

If you have NO tools available right now, you are commenting only. Reply with ONE
short, plain-language sentence describing what happened, e.g. "Fetching GitLab CI's
documentation now." or "Retrieval completed: found 4 pages, confidence 0.81." Never
suggest or imply you're about to take an action yourself, only describe what already
happened.

If you DO have tools available, decide whether the input calls for one. Given a
human's message, decide which tool(s) to call, and in what order:
  - Feedback the user wants applied immediately ("fix X", "the ATL output is wrong, do
    Y instead") -> call add_constraint for that stage, then rerun_stage.
  - A plain redo with no specific correction ("redo the psm stage") -> call rerun_stage
    alone.
  - Approval ("looks good", "approve the acceleo stage") -> call
    stage_result(approved=true).
  - A rejection that should just be recorded, not rerun yet -> call
    stage_result(approved=false, correction=...).
  - Starting over or switching to a different platform entirely ("let's do this for
    GitLab instead", "start over with a new platform") -> call start_pipeline with the
    new platform description and its documentation URL. Do NOT use rerun_stage or
    run_stage for this, those act on the current in-progress pipeline, not a fresh one.
  - Anything that doesn't map to a pipeline action -> don't call any tool; reply with a
    clarifying question instead.

Prior events/messages, if any, are for context only, only react to the LAST one in the
conversation.
""".strip()


def _fetch_documentation_tool(
    url: str,
    hint: str | None = None,
    exclude_urls: list[str] | None = None,
    max_pages: int | None = None,
    max_depth: int | None = None,
    force_refresh: bool | None = None,
) -> dict:
    """Tool-call wrapper around orchestrator.fetch_documentation(): summarized,
    not the full raw content, so a nudge() reply doesn't dump a whole crawl
    into a tool-call result. Doesn't touch pipeline state, unlike run_stage/
    stage_result, this is a direct retrieval action, not a stage advance."""
    result = orchestrator.fetch_documentation(
        url, hint=hint, exclude_urls=exclude_urls, max_pages=max_pages,
        max_depth=max_depth, force_refresh=force_refresh,
    )
    pages = [p for p in result["pages"] if p["success"]]
    return {
        "seed_url": result["seed_url"],
        "pages_fetched": len(pages),
        "confidence": result["meta"]["confidence"],
        "pending_links": len(result["meta"]["pending_links"]),
    }


def _fetch_page_tool(url: str, force_refresh: bool = False) -> dict:
    """Tool-call wrapper around orchestrator.fetch_page(): summarized, doesn't
    touch pipeline state, same reasoning as _fetch_documentation_tool."""
    page = orchestrator.fetch_page(url, force_refresh=force_refresh)
    return {
        "url": page["url"],
        "success": page["success"],
        "status_code": page["status_code"],
        "markdown_length": len(page["markdown"]),
    }


TOOLS: list[tool_calling.Tool] = [
    tool_calling.Tool(
        name="run_stage",
        description=(
            "Start or continue the CURRENT pending pipeline stage using the given context. "
            "Use this to kick off the pipeline from scratch (context should include "
            "'seed_url', the platform's real documentation URL) or to re-run the current "
            "stage with fresh/updated input. Do NOT use this for approving, rejecting, or "
            "redoing existing output, use stage_result or rerun_stage for that."
        ),
        parameters={
            "type": "object",
            "properties": {
                "context": {
                    "type": "object",
                    "description": (
                        "Input for the current stage's agent. For the docs stage this should "
                        "include 'seed_url'. Later stages build their context automatically "
                        "and rarely need this supplied manually."
                    ),
                },
            },
            "required": ["context"],
        },
        impl=orchestrator.start_stage_run,
    ),
    tool_calling.Tool(
        name="rerun_stage",
        description=(
            "Re-run the CURRENT pending stage's agent, reusing the context from its last "
            "run and folding in any constraints recorded via add_constraint since then. Use "
            "this when the user wants the current stage redone right now, e.g. 'redo the "
            "ATL stage', or immediately after calling add_constraint to apply a correction "
            "the user just gave."
        ),
        parameters={"type": "object", "properties": {}},
        impl=orchestrator.rerun_stage,
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
                    "enum": orchestrator.STAGES,
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
        impl=orchestrator.review,
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
                    "enum": orchestrator.STAGES,
                    "description": "Which stage this correction applies to.",
                },
                "constraint": {"type": "string", "description": "The human's correction/instruction for this stage."},
            },
            "required": ["stage", "constraint"],
        },
        impl=orchestrator.add_constraint,
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
            "that)."
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
            },
            "required": ["platform_description", "seed_url"],
        },
        impl=orchestrator.start_pipeline,
    ),
    # The two tools below wrap retrieval's own real capabilities (ai/retrieval's
    # POST /fetch and POST /fetch/page) directly, only offered while docs is the
    # pending stage. Prefer these over the generic rerun_stage when the human's
    # request maps onto one of retrieval's own specific retry levers, e.g.
    # "just grab that one missing page" -> fetch_page, not a full re-crawl.
    tool_calling.Tool(
        name="fetch_documentation",
        stages=["docs"],
        description=(
            "Crawl a documentation site for CI/CD pipeline syntax reference content "
            "(retrieval's real POST /fetch). Use this to steer or narrow a fresh crawl, "
            "e.g. the human gives a hint about what's missing, or wants to exclude a "
            "known-bad page. Does not advance the pipeline or replace the docs stage's "
            "approved output, use rerun_stage for that once the fetch looks right."
        ),
        parameters={
            "type": "object",
            "properties": {
                "url": {"type": "string", "description": "The documentation URL to crawl."},
                "hint": {"type": "string", "description": "Free text steering the crawl toward something specific."},
                "exclude_urls": {
                    "type": "array",
                    "items": {"type": "string"},
                    "description": "URLs already known to be wrong, ruled out of this crawl.",
                },
                "max_pages": {"type": "integer", "description": "Cap on how many pages to crawl."},
                "max_depth": {"type": "integer", "description": "Cap on how many link-hops deep to crawl."},
                "force_refresh": {"type": "boolean", "description": "Bypass retrieval's page cache and refetch from scratch."},
            },
            "required": ["url"],
        },
        impl=_fetch_documentation_tool,
    ),
    tool_calling.Tool(
        name="fetch_page",
        stages=["docs"],
        description=(
            "Fetch exactly one specific URL directly, no crawling (retrieval's real "
            "POST /fetch/page). Use this when the human names a specific page they "
            "know is missing, rather than asking for a broader re-crawl."
        ),
        parameters={
            "type": "object",
            "properties": {
                "url": {"type": "string", "description": "The specific page URL to fetch."},
                "force_refresh": {"type": "boolean", "description": "Bypass retrieval's page cache and refetch from scratch."},
            },
            "required": ["url"],
        },
        impl=_fetch_page_tool,
    ),
]
