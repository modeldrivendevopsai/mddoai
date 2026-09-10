"""The ATL transformation stage: a thin proxy to atl_agent's real
/generate capability (generates a new ATL model-to-model transformation
from this run's own real PIM artifact and target platform PSM metamodel),
matching every other real stage agent's shape (compare stages/psm/agent.py).
Its own opt-in mock (context["mock"], the same per-run override docs_stage
reads) skips only atl_agent's own real LLM call, still resolving the real
prompt config and running the real validator-agent call against a fixed
valid artifact - see atl_agent's generation.py generate() docstring for
exactly what mock does and doesn't skip.

Returns (output, extra) instead of the plain str every other stage agent
returns - see pipeline.py's run_stage() for the backward-compatible handling
of this, and ai/CLAUDE.md's stage-agent recipe for why this narrow
extension exists: this stage has real structured data (the prompt actually
used, validation results) the chat-ui needs to show alongside the artifact,
not just the final text.

Also persists every real attempt to disk via stages/_validation.py's
persist_attempt(). Deliberately does NOT call raise_if_invalid() even when
the real regenerate loop exhausts every round and still fails - unlike
pim/acceleo's own one-shot mock call, atl_agent's generate() already
retried up to 3 times internally, and a human reviewing a still-failed
result needs the real detail (which round, what the validator actually
said) a bare raised failure would throw away, matching psm's own real
generation-mode choice.
"""
from clients import atl_agent_client
from integration_runner.stages._validation import attempt_scope_kwargs, persist_attempt, reserve_attempt_dir

_FILENAME = "atl.atl"


def atl_stage(context: dict) -> tuple[str, dict]:
    # pim_output/psm_output are what a live run actually has once "pim"/
    # "psm" precede "atl" - no fallback for either: there's no reasonable
    # stand-in for a caller that skips straight to atl, so a direct/test
    # call without them deliberately gets empty artifacts, not a silently
    # wrong substitute.
    platform_description = context.get("platform_description", "")
    pim_artifact = context.get("pim_output", "")
    psm_artifact = context.get("psm_output", "")
    constraints = context.get("constraints", {}).get("atl", [])
    # Reserved before run_atl() runs, not after: run_atl() is what triggers
    # atl_agent's own real validator-agent call, once per retry round, and
    # each one's real compiled .asm bytecode needs the real attempt path
    # to nest inside, not land as an unlinked sibling of it.
    run_id = context.get("run_id")
    attempt_dir = reserve_attempt_dir(run_id, "atl") if run_id else None
    result = atl_agent_client.run_atl(
        pim_artifact,
        psm_artifact,
        platform_description,
        constraints=constraints,
        model=context.get("model"),
        run_id=run_id,
        # The same per-run "Mock" override docs_stage's own context["mock"]
        # already reads (see RerunOverrides.mock/StartRequest.mock).
        mock=bool(context.get("mock")),
        **attempt_scope_kwargs("atl", attempt_dir),
    )
    artifact = result["artifact"]
    persist_attempt(
        run_id or "unknown",
        "atl",
        _FILENAME,
        artifact,
        result["validation"],
        attempt_dir=attempt_dir,
        prompt=result.get("prompt"),
        prompt_version=result.get("prompt_version"),
    )
    return artifact, {k: v for k, v in result.items() if k != "artifact"}
