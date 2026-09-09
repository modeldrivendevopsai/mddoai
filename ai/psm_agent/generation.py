"""PSM Generation Agent: onboards a NEW platform with no existing PSM
metamodel yet. Given the target platform's own docs, generates a new
.ecore, refined against real validator-agent feedback rather than accepted
on the first attempt.

Matches the paper's (Karlovs-Karlovskis) validated "Step 1 (Metamodel)"
approach: master-example metamodel + target docs -> LLM generates a new
.ecore -> checked against the real "loads in Eclipse EMF" pass criterion,
refined one constraint per round on failure. Grounding (the AC's "calls ...
for grounding before generating") uses pim_agent's existing ground()
mechanism - no separate RAG agent exists yet (Phase 1, not this pass).

`pim_artifact` is taken as a parameter and still feeds each grounding
query below (_grounding_context), but is deliberately NOT included as
prompt content: MDDOAI's own pim stage is still a placeholder that ignores
its real input entirely and always returns the same fixed content, so its
output carries no real signal yet, and the paper's own validated prompts
never attached a PIM artifact either. Re-add it as a real prompt attachment
(see ai/psm_agent/prompts/) once a real PIM stage exists, not before.

The actual generate-validate-retry loop is generation_toolkit's own
run_with_retry() (shared, stage-agnostic) - this module's job is PSM-specific:
which files to read, how to fold in grounding, and how validator-agent's
result maps to a "valid"/pass-fail check and a root-cause constraint.

The system prompt and the parts fed into it are no longer hardcoded here:
they come from a real, UI-editable prompt config
(generation_toolkit.prompt_config), one per real target platform
("preset", resolved from the free-text platform_description), falling back
to a generic default for a platform with no preset of its own yet. See
ai/psm_agent/prompts/ for the real, git-committed starting content.
"""
from concurrent.futures import ThreadPoolExecutor

from generation_toolkit.generation_agent import run_with_retry
from generation_toolkit.prompt_builder import build_prompt
from generation_toolkit.prompt_config import presets, rendering
from generation_toolkit.prompt_config import resolution as prompt_resolution

from clients import pim_agent_client, validator_agent_client

from comparison import META_MODELS_DIR
import prompt_paths

# The 9 PIM concept categories pim_agent already knows about (concepts()'s
# own keys) - grounding one query per category, mirroring
# serialization_agent's own _concept_context(), rather than a single grounding
# call against the whole platform_docs blob, which would only ever surface
# whichever single concept the text happens to read closest to.
_GROUNDING_TOP_K = 1

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


def _grounding_context(pim_artifact: str) -> list[dict]:
    concepts = pim_agent_client.concepts()
    # One ground() call per concept category, run concurrently: sequential
    # HTTP round-trips here would add up to ~9x pim_agent's own per-call
    # latency before the real LLM call even starts. A thread pool is enough
    # (each call is pure I/O wait, no shared state between them) without
    # pulling this otherwise-synchronous module onto asyncio.
    with ThreadPoolExecutor(max_workers=len(concepts) or 1) as pool:
        results = pool.map(
            lambda concept: pim_agent_client.ground(f"{concept} {pim_artifact[:200]}", top_k=_GROUNDING_TOP_K),
            concepts,
        )
    grounding = []
    for matches in results:
        grounding.extend(matches)
    return grounding


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
    platform_description: str,
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
    "rounds": int, "preset": str, "prompt_version": str}. `preset` and
    `prompt_version` name exactly which saved config produced this
    output, the real link an attempt's own persisted record (see
    integration_runner/stages/_validation.py's persist_attempt) and a
    later "restore the config that produced this" UI action both need.

    mock=True (the per-run "Mock" override, same opt-in as docs_stage's own
    context["mock"]) still resolves the real preset/config/attachments and
    still runs the real validator-agent call against a fixed, already-valid
    artifact, so the prompt-builder mechanism and the real attempt-
    persistence path are both exercised for real - it only skips the two
    slow, billed steps (grounding's pim_agent calls and the real LLM call),
    for fast local iteration on a config without spending either."""
    preset_id = presets.resolve_preset(
        platform_description, presets.list_preset_metadata(prompt_paths.PROMPT_CONFIG_DIR, "generation")
    )

    if mock:
        context_values = {"pim_ecore": pim_artifact, "psm_docs": platform_docs}
        config, parts = prompt_resolution.resolve_for_call(
            prompt_paths.PROMPT_CONFIG_DIR, "generation", preset_id, context_values, META_MODELS_DIR
        )
        combined_constraints = [*config.get("learned_constraints", []), *(constraints or [])]
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

    psm_docs = platform_docs
    grounding = _grounding_context(pim_artifact)
    if grounding:
        grounding_lines = "\n".join(f"- {g['title']}: {g['content'].splitlines()[0]}" for g in grounding)
        psm_docs = f"{platform_docs}\n\nRelevant PIM concept grounding:\n{grounding_lines}"

    context_values = {"pim_ecore": pim_artifact, "psm_docs": psm_docs}
    config, parts = prompt_resolution.resolve_for_call(
        prompt_paths.PROMPT_CONFIG_DIR, "generation", preset_id, context_values, META_MODELS_DIR
    )

    # A promoted, permanent constraint (see
    # generation_toolkit.prompt_config.learned_constraints) applies to
    # every run of this preset from now on; this run's own live
    # corrections (integration_runner's IntegrationRun.constraints, an
    # entirely separate, per-run, ephemeral mechanism) still apply on top,
    # for this call only.
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
        "preset": preset_id,
        "prompt_version": config.get("_version"),
    }
