"""Real, git-committable, UI-editable prompt config for this service's one
real LLM capability (generation.py's generate()) - see
generation_toolkit.prompt_config for the actual persistence mechanism this
router is a thin HTTP surface over. Mirrors psm_agent/routes/prompt_config.py,
minus anything specific to PSM's own generation/knowledge-mode duality: this
service only ever has one real config name, "generation".

Every real path (config_dir, files_root) and the real sample context values
a dry-run validation or a static preview needs both live here, not in
generation_toolkit itself, which stays fully generic: it takes a config_dir
and context_values from its caller, it has no idea this service's own
directory layout or which context keys generation.py actually supplies for
a real call.
"""
from fastapi import APIRouter, HTTPException
from pydantic import BaseModel

from generation_toolkit.prompt_builder import build_prompt
from generation_toolkit.prompt_config import history, learned_constraints, presets, references, rendering, resolution, storage

from generation import CONFIG_NAME, _FILES_ROOT
import prompt_paths

router = APIRouter(prefix="/prompt-config")

# The real context keys generate() itself supplies for a real call (see
# generation.py's own context_values inside generate()) - kept here, not
# re-derived, so a dry-run validation or a static preview checks against
# the same shape a real call actually uses.
_SAMPLE_CONTEXT_VALUES: dict[str, str] = {"pim_ecore": "", "psm_ecore": ""}


class PromptConfigBody(BaseModel):
    # No system_prompt field: a config's own first "text" attachment IS
    # the system message (see generation_toolkit.prompt_config.resolution's
    # own resolve_for_call), built and edited the same way as every other
    # attachment, not a separate required field a human can't remove or
    # reorder.
    attachments: list[dict]
    learned_constraints: list[str] = []
    label: str | None = None
    platform_hints: list[str] = []


class LearnedConstraintsBody(BaseModel):
    constraints: list[str]


class RemoveLearnedConstraintBody(BaseModel):
    constraint: str


def _known_name(name: str) -> None:
    if name != CONFIG_NAME:
        raise HTTPException(status_code=404, detail=f"unknown prompt config name {name!r}")


@router.get("/{name}/presets")
def list_presets_endpoint(name: str):
    _known_name(name)
    return {"presets": presets.list_preset_metadata(prompt_paths.PROMPT_CONFIG_DIR, name)}


@router.get("/{name}/{preset}")
def get_config_endpoint(name: str, preset: str):
    _known_name(name)
    try:
        return storage.load_config(prompt_paths.PROMPT_CONFIG_DIR, name, preset)
    except FileNotFoundError as e:
        raise HTTPException(status_code=404, detail=str(e)) from e


@router.put("/{name}/{preset}")
def save_config_endpoint(name: str, preset: str, body: PromptConfigBody):
    _known_name(name)
    try:
        return storage.save_config(
            prompt_paths.PROMPT_CONFIG_DIR, name, preset, body.model_dump(), _SAMPLE_CONTEXT_VALUES, _FILES_ROOT
        )
    except storage.PromptConfigValidationError as e:
        raise HTTPException(status_code=400, detail=str(e)) from e


@router.get("/{name}/{preset}/history")
def history_endpoint(name: str, preset: str):
    _known_name(name)
    return {"versions": history.list_history(prompt_paths.PROMPT_CONFIG_DIR, name, preset)}


@router.get("/{name}/{preset}/diff")
def diff_endpoint(name: str, preset: str, a: str, b: str):
    _known_name(name)
    try:
        return history.diff_versions(prompt_paths.PROMPT_CONFIG_DIR, name, preset, a, b)
    except FileNotFoundError as e:
        raise HTTPException(status_code=404, detail=str(e)) from e


@router.post("/{name}/{preset}/restore/{version}")
def restore_endpoint(name: str, preset: str, version: str):
    _known_name(name)
    try:
        return history.restore_version(prompt_paths.PROMPT_CONFIG_DIR, name, preset, version, _SAMPLE_CONTEXT_VALUES, _FILES_ROOT)
    except FileNotFoundError as e:
        raise HTTPException(status_code=404, detail=str(e)) from e


@router.post("/{name}/{preset}/revert")
def revert_endpoint(name: str, preset: str):
    _known_name(name)
    try:
        return history.revert_to_default(prompt_paths.PROMPT_CONFIG_DIR, name, preset, _SAMPLE_CONTEXT_VALUES, _FILES_ROOT)
    except FileNotFoundError as e:
        raise HTTPException(status_code=404, detail=str(e)) from e


@router.post("/{name}/{preset}/promote-to-default")
def promote_to_default_endpoint(name: str, preset: str):
    _known_name(name)
    try:
        return history.promote_live_to_default(prompt_paths.PROMPT_CONFIG_DIR, name, preset)
    except FileNotFoundError as e:
        raise HTTPException(status_code=404, detail=str(e)) from e


@router.get("/{name}/{preset}/check-references")
def check_references_endpoint(name: str, preset: str):
    _known_name(name)
    try:
        broken = references.check_references(prompt_paths.PROMPT_CONFIG_DIR, name, preset, _SAMPLE_CONTEXT_VALUES, _FILES_ROOT)
    except FileNotFoundError as e:
        raise HTTPException(status_code=404, detail=str(e)) from e
    return {"broken": broken}


@router.post("/{name}/{preset}/learned-constraints")
def add_learned_constraints_endpoint(name: str, preset: str, body: LearnedConstraintsBody):
    _known_name(name)
    try:
        return learned_constraints.add_learned_constraints(
            prompt_paths.PROMPT_CONFIG_DIR, name, preset, body.constraints, _SAMPLE_CONTEXT_VALUES, _FILES_ROOT
        )
    except FileNotFoundError as e:
        raise HTTPException(status_code=404, detail=str(e)) from e


@router.delete("/{name}/{preset}/learned-constraints")
def remove_learned_constraint_endpoint(name: str, preset: str, body: RemoveLearnedConstraintBody):
    _known_name(name)
    try:
        return learned_constraints.remove_learned_constraint(
            prompt_paths.PROMPT_CONFIG_DIR, name, preset, body.constraint, _SAMPLE_CONTEXT_VALUES, _FILES_ROOT
        )
    except FileNotFoundError as e:
        raise HTTPException(status_code=404, detail=str(e)) from e


@router.post("/{name}/{preset}/preview")
def preview_endpoint(name: str, preset: str):
    """The exact text a real call for this config would send the LLM,
    without spending a real call. `attachments` carries each body
    attachment's own real resolved content, keyed by its id, so a UI can
    preview one file/context block in isolation."""
    _known_name(name)
    try:
        config, parts = resolution.resolve_for_call(prompt_paths.PROMPT_CONFIG_DIR, name, preset, _SAMPLE_CONTEXT_VALUES, _FILES_ROOT)
    except FileNotFoundError as e:
        raise HTTPException(status_code=404, detail=str(e)) from e

    prompt = build_prompt(parts, constraints=config.get("learned_constraints"))
    return {
        "system_prompt": config["system_prompt"],
        "user_content": rendering.render_user_content(config, prompt),
        "attachments": parts,
    }
