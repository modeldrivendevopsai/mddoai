"""Acceleo Template Agent: given a target platform's own real PSM metamodel
(this run's own psm_output) and that platform's own real documentation
(this run's own serialization_output), generates a new Acceleo
model-to-text template that generates that platform's real CI/CD YAML
config from a PSM model instance, refined against real validator-agent
feedback rather than accepted on the first attempt.

Matches the paper's (Karlovs-Karlovskis) validated "Step 3 (Acceleo)"
approach: master-example Acceleo template + target metamodel + target docs
-> LLM generates a new .mtl -> checked against the real Acceleo compiler's
own pass criterion, refined one constraint per round on failure. Unlike PSM
generation, there's no grounding call and no generation-vs-knowledge-mode
routing: the real, validated experiments always generated a fresh template
from this run's own real artifacts, there's no "platform already has one"
concept the way PSM's per-platform metamodels have.

The system prompt and the parts fed into it are not hardcoded here: they
come from a real, UI-editable prompt config (generation_toolkit.prompt_config),
resolved the same way psm_agent's own generation.py does. The prompt folder this
service is given holds them, beginning from the starting content the repository ships.
"""
from generation_toolkit.generation_agent import run_with_retry
from generation_toolkit.mock_chain import MOCK_ATL, MOCK_MTL, MOCK_PSM_ECORE, mock_generation_result
from generation_toolkit.prompt_builder import build_prompt
from generation_toolkit.prompt_config import rendering
from generation_toolkit.prompt_config import resolution as prompt_resolution

from clients import validator_agent_client

import prompt_paths

CONFIG_NAME = "generation"

# 0, not a lower-but-nonzero value: Acceleo/MTL is a fixed, unambiguous
# grammar, never a place where creative phrasing helps - a real generation
# this project produced repeatedly invented syntactically-plausible-but-wrong
# constructs (a raw OCL query missing one of several required `endif`
# keywords, a reserved word used as a plain identifier) that a lower
# temperature reduces the odds of, without helping with a genuine knowledge
# gap the model would get wrong at any setting.
GENERATION_TEMPERATURE = 0

_FILES_ROOT = [prompt_paths.REFERENCE_EXAMPLE_PATH.parent, prompt_paths.ATTACHMENT_UPLOADS_DIR]


def _validate(
    artifact: str,
    run_id: str | None = None,
    stage: str | None = None,
    attempt: str | None = None,
    metamodel_ecore: str | None = None,
    atl_source: str | None = None,
) -> dict:
    return validator_agent_client.validate_acceleo(
        artifact,
        "generate.mtl",
        run_id=run_id,
        stage=stage,
        attempt=attempt,
        metamodel_ecore=metamodel_ecore,
        atl_source=atl_source,
    )


def generate(
    psm_artifact: str,
    platform_docs: str,
    atl_artifact: str | None = None,
    constraints: list[str] | None = None,
    model: str | None = None,
    run_id: str | None = None,
    stage: str | None = None,
    attempt: str | None = None,
    mock: bool = False,
) -> dict:
    """Returns {"artifact": str, "prompt": dict, "validation": dict,
    "rounds": int, "prompt_version": str, "round_constraints": list[str]} -
    the same shape psm_agent.generation.generate() returns, for the same
    reason: `prompt_version` names exactly which saved config produced this
    output, the real link an attempt's own persisted record and a later
    "restore the config that produced this" UI action both need.
    `round_constraints` is run_with_retry()'s own per-run concept (see its
    own docstring), deliberately distinct from this function's own
    `config.get("learned_constraints", ...)` below, a permanent, cross-run
    concept a human explicitly promotes.

    atl_artifact, when given, is this run's own already-generated,
    already-approved ATL (context["atl_output"] - "atl" precedes "acceleo"
    in the pipeline, so a live run always has one). Forwarded to
    validator-agent alongside psm_artifact so the real Acceleo template can
    actually be RUN against a real PSM model instance (produced by running
    atl_artifact itself), not just compiled - see validator_agent_client's
    own validate_acceleo docstring for why compiling alone can miss a real
    runtime-only failure. Omitted, this behaves exactly as before
    (compile-only checking).

    mock=True (the per-run "Mock" override, same opt-in as psm_agent's own)
    still resolves the real config/attachments and still runs the real
    validator-agent call, so the prompt-builder mechanism and the real
    attempt-persistence path are both exercised for real. It returns the
    mock chain's fixed template (see generation_toolkit's mock_chain) and
    never calls a model, so no constraint changes it. The validator is given
    the mock chain's own metamodel and transformation, not this run's: the
    mock template reads the mock metamodel only."""
    context_values = {"psm_ecore": psm_artifact, "platform_docs": platform_docs}
    config, parts = prompt_resolution.resolve_for_call(
        prompt_paths.PROMPT_CONFIG_DIR, CONFIG_NAME, context_values, _FILES_ROOT
    )
    combined_constraints = [*config.get("learned_constraints", []), *(constraints or [])]

    if mock:
        prompt = build_prompt(parts, combined_constraints)
        validation = _validate(
            MOCK_MTL, run_id, stage, attempt, metamodel_ecore=MOCK_PSM_ECORE, atl_source=MOCK_ATL
        )
        return mock_generation_result(MOCK_MTL, prompt, validation, config.get("_version"))

    result = run_with_retry(
        config["system_prompt"],
        parts,
        constraints=combined_constraints,
        validate_fn=lambda artifact: _validate(
            artifact, run_id, stage, attempt, metamodel_ecore=psm_artifact, atl_source=atl_artifact
        ),
        render_user_content=lambda prompt: rendering.render_user_content(config, prompt),
        model=model,
        temperature=GENERATION_TEMPERATURE,
    )
    return {
        "artifact": result["output"],
        "prompt": result["prompt"],
        "validation": result["validation"],
        "rounds": result["rounds"],
        "prompt_version": config.get("_version"),
        "round_constraints": result["round_constraints"],
    }
