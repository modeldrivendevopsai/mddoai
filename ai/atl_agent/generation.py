"""ATL Transformation Agent: given the real PIM metamodel and a target
platform's own real PSM metamodel (this run's own psm_output), generates a
new ATL model-to-model transformation from PIM to that platform's PSM,
refined against real validator-agent feedback rather than accepted on the
first attempt.

Matches the paper's (Karlovs-Karlovskis) validated "Step 2 (ATL)" approach:
master-example ATL transformation + source metamodel + target metamodel ->
LLM generates a new .atl -> checked against the real ATL compiler's own
pass criterion, refined one constraint per round on failure. Unlike PSM
generation, there's no grounding call and no generation-vs-knowledge-mode
routing: the real, validated experiments always generated a fresh
transformation from this run's own real PIM/PSM artifacts, there's no
"platform already has one" concept the way PSM's per-platform metamodels
have.

The system prompt and the parts fed into it are not hardcoded here: they
come from a real, UI-editable prompt config (generation_toolkit.prompt_config),
resolved the same way psm_agent's own generation.py does. See
ai/atl_agent/prompts/ for the real, git-committed starting content.
"""
from generation_toolkit.generation_agent import run_with_retry
from generation_toolkit.prompt_builder import build_prompt
from generation_toolkit.prompt_config import rendering
from generation_toolkit.prompt_config import resolution as prompt_resolution

from clients import validator_agent_client

import prompt_paths

CONFIG_NAME = "generation"

# 0, not a lower-but-nonzero value: ATL is a fixed, unambiguous grammar,
# never a place where creative phrasing helps - a real generation this
# project produced repeatedly invented syntactically-plausible-but-wrong
# constructs (a bare ternary missing `if`/`endif`, `=` where a `to` block
# needs `<-`) that a lower temperature reduces the odds of, without helping
# with a genuine knowledge gap the model would get wrong at any setting.
GENERATION_TEMPERATURE = 0

_FILES_ROOT = [prompt_paths.REFERENCE_EXAMPLE_PATH.parent, prompt_paths.ATTACHMENT_UPLOADS_DIR]

# mock=True's fixed stand-in output: a minimal, already-proven-valid ATL
# module (matching validator_agent/tests/fixtures/valid.atl, which that
# service's own real test suite already asserts passes real compilation),
# so a mocked round-trip still exercises the real validator-agent call and
# the real attempt-persistence path, just never the real (slow, billed) LLM
# call. Named distinctly so it's never mistaken for a real generated
# transformation.
_MOCK_ARTIFACT = """module MockPim2Psm;
create OUT : PSM from IN : PIM;

rule MockPipelineBlock2MockPipeline {
\tfrom
\t\ts : PIM!MockPipelineBlock
\tto
\t\tt : PSM!MockPipeline (
\t\t\tstages <- s.name
\t\t)
}
"""


def _validate(
    artifact: str,
    run_id: str | None = None,
    stage: str | None = None,
    attempt: str | None = None,
    metamodel_ecore: str | None = None,
) -> dict:
    return validator_agent_client.validate_atl(
        artifact, "generated.atl", run_id=run_id, stage=stage, attempt=attempt, metamodel_ecore=metamodel_ecore
    )


def generate(
    pim_artifact: str,
    psm_artifact: str,
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
    `config.get("learned_constraints", ...)` above, a permanent, cross-run
    concept a human explicitly promotes - the caller (integration_runner's
    own atl_stage) is expected to persist round_constraints as real,
    per-run constraints so the next real retry builds on everything this
    call already spent real LLM rounds discovering, instead of starting
    over blank.

    mock=True (the per-run "Mock" override, same opt-in as psm_agent's own)
    still resolves the real config/attachments and still runs the real
    validator-agent call against a fixed, already-valid artifact, so the
    prompt-builder mechanism and the real attempt-persistence path are
    both exercised for real - it only skips the real, slow, billed LLM
    call, for fast local iteration on a config without spending it."""
    context_values = {"pim_ecore": pim_artifact, "psm_ecore": psm_artifact}
    config, parts = prompt_resolution.resolve_for_call(
        prompt_paths.PROMPT_CONFIG_DIR, CONFIG_NAME, context_values, _FILES_ROOT
    )
    combined_constraints = [*config.get("learned_constraints", []), *(constraints or [])]

    if mock:
        # No metamodel_ecore here: _MOCK_ARTIFACT always targets a fixed,
        # fictional "PSM" package name, never this run's own real
        # psm_artifact - forwarding a real, unrelated target metamodel
        # alongside it would make the real execution smoke test run the
        # mock ATL against the wrong metamodel entirely (mirrors
        # acceleo_agent's own mock branch never forwarding metamodel_ecore,
        # for the identical reason - see its test_mock_mode_does_not_forward_
        # a_metamodel_since_it_always_targets_gitlab).
        prompt = build_prompt(parts, combined_constraints)
        validation = _validate(_MOCK_ARTIFACT, run_id, stage, attempt)
        return {
            "artifact": _MOCK_ARTIFACT,
            "prompt": prompt,
            "validation": validation,
            "rounds": 1,
            "prompt_version": config.get("_version"),
            "round_constraints": combined_constraints,
        }

    result = run_with_retry(
        config["system_prompt"],
        parts,
        constraints=combined_constraints,
        validate_fn=lambda artifact: _validate(artifact, run_id, stage, attempt, metamodel_ecore=psm_artifact),
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
