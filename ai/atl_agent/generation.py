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
from generation_toolkit.prompt_config import presets, rendering
from generation_toolkit.prompt_config import resolution as prompt_resolution

from clients import validator_agent_client

import prompt_paths

CONFIG_NAME = "generation"

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


def _validate(artifact: str, run_id: str | None = None, stage: str | None = None, attempt: str | None = None) -> dict:
    return validator_agent_client.validate_atl(artifact, "generated.atl", run_id=run_id, stage=stage, attempt=attempt)


def generate(
    pim_artifact: str,
    psm_artifact: str,
    platform_description: str = "",
    constraints: list[str] | None = None,
    model: str | None = None,
    run_id: str | None = None,
    stage: str | None = None,
    attempt: str | None = None,
    mock: bool = False,
) -> dict:
    """Returns {"artifact": str, "prompt": dict, "validation": dict,
    "rounds": int, "preset": str, "prompt_version": str} - the same shape
    psm_agent.generation.generate() returns, for the same reason: `preset`
    and `prompt_version` name exactly which saved config produced this
    output, the real link an attempt's own persisted record and a later
    "restore the config that produced this" UI action both need.

    mock=True (the per-run "Mock" override, same opt-in as psm_agent's own)
    still resolves the real preset/config/attachments and still runs the
    real validator-agent call against a fixed, already-valid artifact, so
    the prompt-builder mechanism and the real attempt-persistence path are
    both exercised for real - it only skips the real, slow, billed LLM
    call, for fast local iteration on a config without spending it."""
    preset_id = presets.resolve_preset(
        platform_description, presets.list_preset_metadata(prompt_paths.PROMPT_CONFIG_DIR, CONFIG_NAME)
    )
    context_values = {"pim_ecore": pim_artifact, "psm_ecore": psm_artifact}
    config, parts = prompt_resolution.resolve_for_call(
        prompt_paths.PROMPT_CONFIG_DIR, CONFIG_NAME, preset_id, context_values, _FILES_ROOT
    )
    combined_constraints = [*config.get("learned_constraints", []), *(constraints or [])]

    if mock:
        prompt = build_prompt(parts, combined_constraints)
        validation = _validate(_MOCK_ARTIFACT, run_id, stage, attempt)
        return {
            "artifact": _MOCK_ARTIFACT,
            "prompt": prompt,
            "validation": validation,
            "rounds": 1,
            "preset": preset_id,
            "prompt_version": config.get("_version"),
        }

    result = run_with_retry(
        config["system_prompt"],
        parts,
        constraints=combined_constraints,
        validate_fn=lambda artifact: _validate(artifact, run_id, stage, attempt),
        render_user_content=lambda prompt: rendering.render_user_content(config, prompt),
        model=model,
    )
    return {
        "artifact": result["output"],
        "prompt": result["prompt"],
        "validation": result["validation"],
        "rounds": result["rounds"],
        "preset": preset_id,
        "prompt_version": config.get("_version"),
    }
