"""The PSM (Platform-Specific Model) stage: a thin proxy to psm_agent's real
/psm capability (routes between generating a new metamodel and checking an
existing one for drift - see psm_agent/psm_flow.py's own docstring), matching
every other real stage agent's shape (compare stages/serialization/agent.py).

Returns (output, extra) instead of the plain str every other stage agent
returns - see pipeline.py's run_stage() for the backward-compatible handling
of this, and ai/CLAUDE.md's stage-agent recipe for why this narrow extension
exists: unlike every other stage, this one has real structured data (the
prompt actually used, validation/gap results) the chat-ui needs to show
alongside the artifact, not just the final text.
"""
from clients import psm_agent_client


def psm_stage(context: dict) -> tuple[str, dict]:
    # pim_output is what a live run actually has once "pim" precedes "psm" -
    # no fallback for it (unlike docs below): there's no reasonable stand-in
    # PIM artifact for a caller that skips straight to psm, so a direct/test
    # call without one deliberately gets an empty pim_artifact, not a silently
    # wrong substitute. docs_output/platform_description ARE real fallbacks,
    # for a caller (or a unit test) that invokes psm_stage directly without a
    # pim stage having run.
    platform_description = context.get("platform_description", "")
    pim_artifact = context.get("pim_output", "")
    docs = context.get("docs_output") or platform_description
    constraints = context.get("constraints", {}).get("psm", [])
    result = psm_agent_client.run_psm(
        platform_description, pim_artifact, docs, constraints=constraints, model=context.get("model")
    )
    return result["artifact"], {k: v for k, v in result.items() if k != "artifact"}
