"""The Acceleo code-generation template stage: a thin proxy to
acceleo_agent's real /generate capability (generates a new Acceleo
model-to-text template from this run's own real PSM metamodel and target
platform documentation), matching every other real stage agent's shape
(compare stages/atl/agent.py, stages/psm/agent.py). Its own opt-in mock
(context["mock"], the same per-run override docs_stage reads) skips only
acceleo_agent's own real LLM call, still resolving the real prompt config
and running the real validator-agent call against a fixed valid artifact -
see acceleo_agent's generation.py generate() docstring for exactly what
mock does and doesn't skip.

Returns (output, extra) instead of the plain str every other stage agent
returns - see pipeline.py's run_stage() for the backward-compatible handling
of this. Also persists every real attempt to disk via
stages/_validation.py's persist_attempt(). Deliberately does NOT call
raise_if_invalid() even when the real regenerate loop exhausts every round
and still fails - see stages/atl/agent.py's own docstring for why (the
same reasoning applies here unchanged).
"""
from clients import acceleo_agent_client
from integration_runner.stages._validation import attempt_scope_kwargs, persist_attempt, reserved_attempt

_FILENAME = "generate.mtl"


def acceleo_stage(context: dict) -> tuple[str, dict]:
    # psm_output is what a live run actually has once "psm" precedes
    # "acceleo" - no fallback for it, matching stages/atl/agent.py's own
    # reasoning. docs prefers serialization_output (the serialization
    # stage's own labeled restructuring), falling back to docs_output (the
    # raw crawl), then platform_description, matching the same real
    # precedence stages/psm/agent.py's own docs fallback chain uses.
    platform_description = context.get("platform_description", "")
    psm_artifact = context.get("psm_output", "")
    docs = context.get("serialization_output") or context.get("docs_output") or platform_description
    constraints = context.get("constraints", {}).get("acceleo", [])
    # Reserved before run_acceleo() runs, not after: run_acceleo() is what
    # triggers acceleo_agent's own real validator-agent call, once per
    # retry round, and each one's real compiled .emtl module needs the
    # real attempt path to nest inside, not land as an unlinked sibling of
    # it. reserved_attempt() undoes the reservation if run_acceleo() raises
    # before persist_attempt() records anything.
    run_id = context.get("run_id")
    with reserved_attempt(run_id, "acceleo") as attempt_dir:
        result = acceleo_agent_client.run_acceleo(
            psm_artifact,
            docs,
            constraints=constraints,
            model=context.get("model"),
            run_id=run_id,
            # The same per-run "Mock" override docs_stage's own context["mock"]
            # already reads (see RerunOverrides.mock/StartRequest.mock).
            mock=bool(context.get("mock")),
            **attempt_scope_kwargs("acceleo", attempt_dir),
        )
        artifact = result["artifact"]
        persist_attempt(
            run_id or "unknown",
            "acceleo",
            _FILENAME,
            artifact,
            result["validation"],
            attempt_dir=attempt_dir,
            prompt=result.get("prompt"),
            prompt_version=result.get("prompt_version"),
        )
    return artifact, {k: v for k, v in result.items() if k != "artifact"}
