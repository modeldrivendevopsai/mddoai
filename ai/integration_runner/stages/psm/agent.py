"""The PSM (Platform-Specific Model) stage: a thin proxy to psm_agent's real
/psm capability (routes between generating a new metamodel and checking an
existing one for drift - see psm_agent/psm_flow.py's own docstring), matching
every other real stage agent's shape (compare stages/serialization/agent.py).
The one real, non-mock stage agent besides docs/serialization/pim - unlike
pim/atl/acceleo (still fixed mock content + a single real validator-agent
call, see stages/_validation.py), psm's own real validation already happens
inside psm_agent's own retry loop (generation_toolkit's run_with_retry).

Returns (output, extra) instead of the plain str every other stage agent
returns - see pipeline.py's run_stage() for the backward-compatible handling
of this, and ai/CLAUDE.md's stage-agent recipe for why this narrow extension
exists: unlike every other stage, this one has real structured data (the
prompt actually used, validation/gap results) the chat-ui needs to show
alongside the artifact, not just the final text.

Also persists every real attempt to disk via stages/_validation.py's
persist_attempt(), the same on-disk audit trail pim/atl/acceleo's own mock
calls get - see psm_stage()'s own body for why raise_if_invalid() is
deliberately NOT called here on a generation-mode failure, unlike those
three stages' unconditional use of it.
"""
from clients import psm_agent_client
from integration_runner.stages._validation import persist_attempt

_FILENAME = "psm.ecore"


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
    artifact = result["artifact"]
    run_id = context.get("run_id", "unknown")
    if result["mode"] == "generation":
        # Real per-round validation already happened inside generate()'s own
        # retry loop (psm_agent/generation.py) - this just also gives the
        # final round's real artifact + validation result the same on-disk
        # attempt record pim/atl/acceleo's own single-shot validator call
        # gets. Deliberately NOT raise_if_invalid() here, even when every
        # retry round is exhausted and validation still fails: unlike those
        # three stages' one-shot mock call, generate() already retried up to
        # 3 times, and a human reviewing a still-failed result needs the real
        # detail (which round, what the validator actually said) a bare
        # raised failure would throw away - chat-ui's own failure view
        # already surfaces exactly that from a normal, non-raised return.
        persist_attempt(run_id, "psm", _FILENAME, artifact, result["validation"])
    else:
        # Knowledge mode: the existing metamodel is returned unchanged and
        # gaps are informational only - there's no real pass/fail to raise
        # on, but the attempt still gets the same on-disk record.
        persist_attempt(run_id, "psm", _FILENAME, artifact, {"valid": True, "issues": []})
    return artifact, {k: v for k, v in result.items() if k != "artifact"}
