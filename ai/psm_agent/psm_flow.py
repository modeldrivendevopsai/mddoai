"""The PSM stage's single real entrypoint: routes between the two distinct
PSM capabilities this service has, rather than exposing them as two
endpoints integration_runner has to choose between. The routing decision
(does the target platform already have a real .ecore?) belongs here, since
this module already owns META_MODELS_DIR/the platform->metamodel mapping via
comparison.py.

- No existing .ecore for the platform -> PSM Generation Agent (generation.py):
  a genuinely new capability, generates a fresh metamodel.
- An existing .ecore -> PSM Knowledge Agent (comparison.py's compare()):
  already built, but per this service's own history never called by the live
  pipeline until this module wires it in - a real drift/gap-check against
  docs, informational only, never an automatic .ecore edit.
"""
from dataclasses import asdict
from pathlib import Path

from generation_toolkit.prompt_builder import build_prompt

from comparison import compare, resolve_platform_metamodel
from generation import generate


def run(
    platform_description: str,
    pim_artifact: str,
    platform_docs: str,
    constraints: list[str] | None = None,
    model: str | None = None,
) -> dict:
    """Returns either:
      {"mode": "generation", "artifact": str, "prompt": dict, "validation": dict, "rounds": int}
    or:
      {"mode": "knowledge", "artifact": str, "gaps": list[dict], "prompt": dict}
    `artifact` is always what belongs in the pipeline's psm_output - the newly
    generated .ecore, or (knowledge mode) the existing .ecore's own content,
    unchanged, since no automatic edit happens on a gap finding.
    """
    existing_metamodel_path = resolve_platform_metamodel(platform_description)
    if existing_metamodel_path is None:
        result = generate(pim_artifact, platform_docs, constraints=constraints, model=model)
        return {"mode": "generation", **result}

    existing_artifact = Path(existing_metamodel_path).read_text()
    suggestions = compare(platform_docs, existing_metamodel_path, model=model)
    gaps = [asdict(s) for s in suggestions]
    prompt = build_prompt(
        {"pim_ecore": pim_artifact, "psm_docs": platform_docs, "psm_example": existing_artifact},
        constraints=constraints,
    )
    return {"mode": "knowledge", "artifact": existing_artifact, "gaps": gaps, "prompt": prompt}
