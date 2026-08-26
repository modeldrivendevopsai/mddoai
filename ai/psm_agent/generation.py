"""PSM Generation Agent: onboards a NEW platform with no existing PSM
metamodel yet. Given the real per-run PIM artifact and target platform docs,
generates a new .ecore, refined against real validator-agent feedback rather
than accepted on the first attempt.

Matches the paper's (Karlovs-Karlovskis) validated "Step 1 (Metamodel)"
approach: master-example metamodel + target docs -> LLM generates a new
.ecore -> checked against the real "loads in Eclipse EMF" pass criterion,
refined one constraint per round on failure. Grounding (the AC's "calls ...
for grounding before generating") uses pim_agent's existing ground()
mechanism - no separate RAG agent exists yet (Phase 1, not this pass).

The actual generate-validate-retry loop is generation_toolkit's own
run_with_retry() (shared, stage-agnostic) - this module's job is PSM-specific:
which files to read, how to fold in grounding, and how validator-agent's
result maps to a "valid"/pass-fail check and a root-cause constraint.
"""
from concurrent.futures import ThreadPoolExecutor
from pathlib import Path

from clients import pim_agent_client, validator_agent_client
from generation_toolkit.generation_agent import run_with_retry

from comparison import DEFAULT_PSM_MASTER_EXAMPLE_PATH

# The 9 PIM concept categories pim_agent already knows about (concepts()'s
# own keys) - grounding one query per category, mirroring
# serialization_agent's own _concept_context(), rather than a single grounding
# call against the whole platform_docs blob, which would only ever surface
# whichever single concept the text happens to read closest to.
_GROUNDING_TOP_K = 1

_SYSTEM_PROMPT = """You are the MDDOAI PSM (Platform-Specific Model) generation agent. Given \
a real PIM (Platform-Independent Model) artifact, a target platform's documentation, and a \
master example metamodel (another platform's real, working PSM metamodel, as raw Ecore XML), \
generate a NEW Ecore metamodel for the target platform.

Follow the master example's structural conventions (its package/class/attribute/reference \
shape) but express the TARGET platform's own concepts, drawn from its documentation and the \
PIM artifact, not the master example's platform-specific details.

Respond with ONLY the raw Ecore XML for the new metamodel, no prose, no markdown code fences."""


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


def _render_user_content(prompt: dict) -> str:
    content = (
        f"PIM artifact:\n{prompt['pim_ecore']}\n\n"
        f"Target platform documentation:\n{prompt['psm_docs']}\n\n"
        f"Master example metamodel:\n{prompt['psm_example']}"
    )
    if prompt["constraints"]:
        content += f"\n\nApply these corrections from prior rounds:\n{prompt['constraints']}"
    return content


def _validate(artifact: str) -> dict:
    return validator_agent_client.validate_ecore(artifact, mode="reflective")


def generate(
    pim_artifact: str,
    platform_docs: str,
    psm_example_path: str | None = None,
    constraints: list[str] | None = None,
    model: str | None = None,
) -> dict:
    """Returns {"artifact": str, "prompt": dict, "validation": dict, "rounds": int}."""
    example_path = psm_example_path or DEFAULT_PSM_MASTER_EXAMPLE_PATH
    psm_docs = platform_docs
    grounding = _grounding_context(pim_artifact)
    if grounding:
        grounding_lines = "\n".join(f"- {g['title']}: {g['content'].splitlines()[0]}" for g in grounding)
        psm_docs = f"{platform_docs}\n\nRelevant PIM concept grounding:\n{grounding_lines}"

    parts = {
        "pim_ecore": pim_artifact,
        "psm_docs": psm_docs,
        "psm_example": Path(example_path).read_text(),
    }
    result = run_with_retry(
        _SYSTEM_PROMPT,
        parts,
        constraints=constraints,
        validate_fn=_validate,
        render_user_content=_render_user_content,
        model=model,
    )
    return {"artifact": result["output"], "prompt": result["prompt"], "validation": result["validation"], "rounds": result["rounds"]}
