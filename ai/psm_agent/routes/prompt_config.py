"""Real, git-committable, UI-editable prompt config for this service's two
real LLM capabilities (generation.py's "generation" mode, comparison.py's
"comparison" mode) - see generation_toolkit.prompt_config for the actual
persistence mechanism this router is a thin HTTP surface over.

Every real path (config_dir, files_root) and the real sample context
values a dry-run validation or a static preview needs both live here, not
in generation_toolkit itself, which stays fully generic: it takes a
config_dir and context_values from its caller, it has no idea this
service's own directory layout or which context keys generation.py/
comparison.py actually supply for a real call.
"""
from fastapi import APIRouter, HTTPException
from pydantic import BaseModel

from generation_toolkit.prompt_config import history, learned_constraints, presets, references, rendering, resolution, storage

from comparison import META_MODELS_DIR
import prompt_paths

router = APIRouter(prefix="/prompt-config")

# The real context keys generate()/compare() themselves supply for a real
# call (see generation.py's own `context_values` inside generate(),
# comparison.py's own inside _load_and_resolve()) - kept here, not
# re-derived, so a dry-run validation or a static preview checks against
# the same shape a real call actually uses. If either of those two
# functions' own context keys ever change, this mapping needs updating
# too - there's no single source of truth to read it from otherwise,
# since generation_toolkit.prompt_config itself is deliberately generic
# and has no concept of "psm_agent's own context keys."
_SAMPLE_CONTEXT_VALUES: dict[str, dict[str, str]] = {
    "generation": {"pim_ecore": "", "psm_docs": ""},
    "comparison": {"psm_metamodel": "", "serialized_docs": ""},
}


class PromptConfigBody(BaseModel):
    system_prompt: str
    attachments: list[dict]
    learned_constraints: list[str] = []
    label: str | None = None
    platform_hints: list[str] = []


class LearnedConstraintsBody(BaseModel):
    constraints: list[str]


class RemoveLearnedConstraintBody(BaseModel):
    constraint: str


def _sample_context(name: str) -> dict[str, str]:
    if name not in _SAMPLE_CONTEXT_VALUES:
        raise HTTPException(status_code=404, detail=f"unknown prompt config name {name!r}")
    return _SAMPLE_CONTEXT_VALUES[name]


@router.get("/{name}/presets")
def list_presets_endpoint(name: str):
    _sample_context(name)
    return {"presets": presets.list_preset_metadata(prompt_paths.PROMPT_CONFIG_DIR, name)}


@router.get("/{name}/{preset}")
def get_config_endpoint(name: str, preset: str):
    _sample_context(name)
    try:
        return storage.load_config(prompt_paths.PROMPT_CONFIG_DIR, name, preset)
    except FileNotFoundError as e:
        raise HTTPException(status_code=404, detail=str(e)) from e


@router.put("/{name}/{preset}")
def save_config_endpoint(name: str, preset: str, body: PromptConfigBody):
    context_values = _sample_context(name)
    try:
        return storage.save_config(
            prompt_paths.PROMPT_CONFIG_DIR, name, preset, body.model_dump(), context_values, META_MODELS_DIR
        )
    except storage.PromptConfigValidationError as e:
        raise HTTPException(status_code=400, detail=str(e)) from e


@router.get("/{name}/{preset}/history")
def history_endpoint(name: str, preset: str):
    _sample_context(name)
    return {"versions": history.list_history(prompt_paths.PROMPT_CONFIG_DIR, name, preset)}


@router.get("/{name}/{preset}/diff")
def diff_endpoint(name: str, preset: str, a: str, b: str):
    _sample_context(name)
    try:
        return history.diff_versions(prompt_paths.PROMPT_CONFIG_DIR, name, preset, a, b)
    except FileNotFoundError as e:
        raise HTTPException(status_code=404, detail=str(e)) from e


@router.post("/{name}/{preset}/restore/{version}")
def restore_endpoint(name: str, preset: str, version: str):
    context_values = _sample_context(name)
    try:
        return history.restore_version(prompt_paths.PROMPT_CONFIG_DIR, name, preset, version, context_values, META_MODELS_DIR)
    except FileNotFoundError as e:
        raise HTTPException(status_code=404, detail=str(e)) from e


@router.post("/{name}/{preset}/revert")
def revert_endpoint(name: str, preset: str):
    context_values = _sample_context(name)
    try:
        return history.revert_to_default(prompt_paths.PROMPT_CONFIG_DIR, name, preset, context_values, META_MODELS_DIR)
    except FileNotFoundError as e:
        raise HTTPException(status_code=404, detail=str(e)) from e


@router.post("/{name}/{preset}/promote-to-default")
def promote_to_default_endpoint(name: str, preset: str):
    _sample_context(name)
    try:
        return history.promote_live_to_default(prompt_paths.PROMPT_CONFIG_DIR, name, preset)
    except FileNotFoundError as e:
        raise HTTPException(status_code=404, detail=str(e)) from e


@router.get("/{name}/{preset}/check-references")
def check_references_endpoint(name: str, preset: str):
    context_values = _sample_context(name)
    try:
        broken = references.check_references(prompt_paths.PROMPT_CONFIG_DIR, name, preset, context_values, META_MODELS_DIR)
    except FileNotFoundError as e:
        raise HTTPException(status_code=404, detail=str(e)) from e
    return {"broken": broken}


@router.post("/{name}/{preset}/learned-constraints")
def add_learned_constraints_endpoint(name: str, preset: str, body: LearnedConstraintsBody):
    context_values = _sample_context(name)
    try:
        return learned_constraints.add_learned_constraints(
            prompt_paths.PROMPT_CONFIG_DIR, name, preset, body.constraints, context_values, META_MODELS_DIR
        )
    except FileNotFoundError as e:
        raise HTTPException(status_code=404, detail=str(e)) from e


@router.delete("/{name}/{preset}/learned-constraints")
def remove_learned_constraint_endpoint(name: str, preset: str, body: RemoveLearnedConstraintBody):
    context_values = _sample_context(name)
    try:
        return learned_constraints.remove_learned_constraint(
            prompt_paths.PROMPT_CONFIG_DIR, name, preset, body.constraint, context_values, META_MODELS_DIR
        )
    except FileNotFoundError as e:
        raise HTTPException(status_code=404, detail=str(e)) from e


@router.post("/{name}/{preset}/preview")
def preview_endpoint(name: str, preset: str):
    """The exact text a real call for this config would send the LLM,
    without spending a real call: generation_toolkit's own
    resolution.render_prompt already resolves attachments and folds in
    learned_constraints identically for any (name, preset), so this
    endpoint needs no per-mode branching of its own."""
    context_values = _sample_context(name)
    try:
        config = storage.load_config(prompt_paths.PROMPT_CONFIG_DIR, name, preset)
        prompt = resolution.render_prompt(prompt_paths.PROMPT_CONFIG_DIR, name, preset, context_values, META_MODELS_DIR)
    except FileNotFoundError as e:
        raise HTTPException(status_code=404, detail=str(e)) from e

    return {
        "system_prompt": config["system_prompt"],
        "user_content": rendering.render_user_content(config, prompt),
    }
