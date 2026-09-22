"""PSM Generation Agent: onboards a NEW platform with no existing PSM
metamodel yet. Given the target platform's own docs, generates a new
.ecore, refined against real validator-agent feedback rather than accepted
on the first attempt.

Matches the paper's (Karlovs-Karlovskis) validated "Step 1 (Metamodel)"
approach: master-example metamodel + target docs -> LLM generates a new
.ecore -> checked against the real "loads in Eclipse EMF" pass criterion,
refined one constraint per round on failure.

`pim_artifact` is taken as a parameter but deliberately NOT included as
prompt content: MDDOAI's own pim stage is still a placeholder that ignores
its real input entirely and always returns the same fixed content, so its
output carries no real signal yet, and the paper's own validated prompts
never attached a PIM artifact either. Re-add it as a real prompt attachment
(see ai/psm_agent/prompts/) once a real PIM stage exists, not before.

The actual generate-validate-retry loop is generation_toolkit's own
run_with_retry() (shared, stage-agnostic) - this module's job is PSM-specific:
which files to read, and how validator-agent's result maps to a
"valid"/pass-fail check and a root-cause constraint.

The system prompt and the parts fed into it are no longer hardcoded here:
they come from a real, UI-editable prompt config
(generation_toolkit.prompt_config). See ai/psm_agent/prompts/ for the
real, git-committed starting content.
"""

from generation_toolkit.generation_agent import run_with_retry
from generation_toolkit.prompt_builder import build_prompt
from generation_toolkit.prompt_config import rendering
from generation_toolkit.prompt_config import resolution as prompt_resolution

from clients import validator_agent_client

from comparison import files_root
import prompt_paths

# mock=True's fixed stand-in output: the same minimal, already-proven-valid
# shape the pim stage's own placeholder mock content uses (matching
# validator_agent/tests/fixtures/valid.ecore, which that service's own
# real test suite already asserts passes reflective validation), so a
# mocked round-trip still exercises the real validator-agent call and the
# real attempt-persistence path, just never the real (slow, billed) LLM call.
# Named distinctly so it's never mistaken for a real generated metamodel.
_MOCK_ARTIFACT = """<?xml version="1.0" encoding="UTF-8"?>
<ecore:EPackage xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:ecore="http://www.eclipse.org/emf/2002/Ecore" name="mockPsm" nsURI="http://mddoai.com/mock/psm" nsPrefix="mockPsm">
  <eClassifiers xsi:type="ecore:EClass" name="MockPipelineBlock">
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="name" eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"/>
  </eClassifiers>
</ecore:EPackage>
"""


def _validate(artifact: str, run_id: str | None = None, stage: str | None = None, attempt: str | None = None) -> dict:
    # Every regeneration round calls this once (run_with_retry's own
    # validate_fn) - stage/attempt stay the same across all of them, so
    # every round's own real compiled Ecore classes nest inside the one
    # attempt directory the calling stage reserved, each in its own
    # uniquely-named subfolder (see validator_agent's own OUTPUT_ROOT/UUID
    # comment for why that per-call uniqueness still matters even when the
    # attempt directory itself is already unique).
    return validator_agent_client.validate_ecore(artifact, mode="codegen", run_id=run_id, stage=stage, attempt=attempt)


def generate(
    pim_artifact: str,
    platform_docs: str,
    constraints: list[str] | None = None,
    model: str | None = None,
    run_id: str | None = None,
    stage: str | None = None,
    attempt: str | None = None,
    mock: bool = False,
) -> dict:
    """Returns {"artifact": str, "prompt": dict, "validation": dict,
    "rounds": int, "prompt_version": str, "round_constraints": list[str]}.
    `prompt_version` names exactly which saved config produced this
    output, the real link an attempt's own persisted record (see
    integration_runner/stages/_validation.py's persist_attempt) and a
    later "restore the config that produced this" UI action both need.
    `round_constraints` is run_with_retry()'s own per-run concept (see its
    own docstring), deliberately distinct from this function's own
    `config.get("learned_constraints", ...)` below, a permanent, cross-run
    concept a human explicitly promotes.

    mock=True (the per-run "Mock" override, same opt-in as docs_stage's own
    context["mock"]) still resolves the real config/attachments and still
    runs the real validator-agent call against a fixed, already-valid
    artifact, so the prompt-builder mechanism and the real attempt-
    persistence path are both exercised for real - it only skips the real
    LLM call, for fast local iteration on a config without spending it."""
    if mock:
        context_values = {"pim_ecore": pim_artifact, "psm_docs": platform_docs}
        config, parts = prompt_resolution.resolve_for_call(
            prompt_paths.PROMPT_CONFIG_DIR, "generation", context_values, files_root()
        )
        combined_constraints = [*config.get("learned_constraints", []), *(constraints or [])]
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

    context_values = {"pim_ecore": pim_artifact, "psm_docs": platform_docs}
    config, parts = prompt_resolution.resolve_for_call(
        prompt_paths.PROMPT_CONFIG_DIR, "generation", context_values, files_root()
    )

    # A promoted, permanent constraint (see
    # generation_toolkit.prompt_config.learned_constraints) applies to
    # every future run; this run's own live corrections
    # (integration_runner's IntegrationRun.constraints, an entirely
    # separate, per-run, ephemeral mechanism) still apply on top, for this
    # call only.
    combined_constraints = [*config.get("learned_constraints", []), *(constraints or [])]

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
        "prompt_version": config.get("_version"),
        "round_constraints": result["round_constraints"],
    }
