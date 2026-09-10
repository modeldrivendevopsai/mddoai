"""acceleo-stage-specific routes: two different kinds of real HTTP surface,
kept in one file since both are acceleo's own, neither generic enough for
core.py nor big enough yet to split further. Mirrors routes/atl.py, same
reasoning.

1. Thin pass-throughs to acceleo_agent's own real prompt-config capability
   (clients/acceleo_agent_client.py), the same "integration_runner proxies,
   acceleo_agent owns the actual data" shape stages/acceleo/agent.py's own
   real /generate call already uses. No run awareness at all: a prompt
   config is a platform-preset resource, not scoped to any one run,
   editable any time.
2. promote_constraints, which IS run-aware (stages/acceleo/actions.py),
   the real HTTP target for turning a verified run's own live corrections
   into acceleo_agent's permanent config.
"""
from fastapi import APIRouter, HTTPException, UploadFile
from pydantic import BaseModel

from clients import acceleo_agent_client

from integration_runner import runs
from integration_runner.stages.acceleo import actions

router = APIRouter(prefix="/acceleo")

_BUSY_DETAIL = "A stage is still running, try again shortly."


class SaveConfigRequest(BaseModel):
    # No system_prompt field: a config's own first "text" attachment IS
    # the system message (see acceleo_agent's own PromptConfigBody, the
    # real schema this pass-through mirrors).
    attachments: list[dict]
    learned_constraints: list[str] = []
    label: str | None = None
    platform_hints: list[str] = []


class LearnedConstraintsRequest(BaseModel):
    constraints: list[str]


class RemoveLearnedConstraintRequest(BaseModel):
    constraint: str


class PromoteConstraintsRequest(BaseModel):
    constraints: list[str]


@router.get("/prompt-config/{name}/presets")
def list_presets_endpoint(name: str):
    return {"presets": acceleo_agent_client.list_presets(name)}


@router.get("/prompt-config/{name}/{preset}")
def get_prompt_config_endpoint(name: str, preset: str):
    return acceleo_agent_client.get_prompt_config(name, preset)


@router.put("/prompt-config/{name}/{preset}")
def save_prompt_config_endpoint(name: str, preset: str, request: SaveConfigRequest):
    return acceleo_agent_client.save_prompt_config(name, preset, request.model_dump())


@router.get("/prompt-config/{name}/{preset}/history")
def prompt_config_history_endpoint(name: str, preset: str):
    return {"versions": acceleo_agent_client.get_prompt_config_history(name, preset)}


@router.get("/prompt-config/{name}/{preset}/diff")
def prompt_config_diff_endpoint(name: str, preset: str, a: str, b: str):
    return acceleo_agent_client.diff_prompt_config_versions(name, preset, a, b)


@router.post("/prompt-config/{name}/{preset}/restore/{version}")
def restore_prompt_config_endpoint(name: str, preset: str, version: str):
    return acceleo_agent_client.restore_prompt_config_version(name, preset, version)


@router.post("/prompt-config/{name}/{preset}/revert")
def revert_prompt_config_endpoint(name: str, preset: str):
    return acceleo_agent_client.revert_prompt_config(name, preset)


@router.post("/prompt-config/{name}/{preset}/promote-to-default")
def promote_prompt_config_to_default_endpoint(name: str, preset: str):
    return acceleo_agent_client.promote_prompt_config_to_default(name, preset)


@router.get("/prompt-config/{name}/{preset}/check-references")
def check_prompt_config_references_endpoint(name: str, preset: str):
    return {"broken": acceleo_agent_client.check_prompt_config_references(name, preset)}


@router.post("/prompt-config/{name}/{preset}/preview")
def preview_prompt_config_endpoint(name: str, preset: str):
    return acceleo_agent_client.preview_prompt_config(name, preset)


@router.post("/prompt-config/{name}/{preset}/learned-constraints")
def add_learned_constraints_endpoint(name: str, preset: str, request: LearnedConstraintsRequest):
    return acceleo_agent_client.add_learned_constraints(name, preset, request.constraints)


@router.delete("/prompt-config/{name}/{preset}/learned-constraints")
def remove_learned_constraint_endpoint(name: str, preset: str, request: RemoveLearnedConstraintRequest):
    return acceleo_agent_client.remove_learned_constraint(name, preset, request.constraint)


@router.get("/available-files")
def available_files_endpoint():
    return {"files": acceleo_agent_client.list_available_files()}


@router.post("/attachment-uploads")
async def upload_attachment_endpoint(file: UploadFile):
    content = await file.read()
    return {"path": acceleo_agent_client.upload_attachment_file(file.filename or "upload", content)}


@router.post("/promote-constraints")
def promote_constraints_endpoint(request: PromoteConstraintsRequest):
    """Promotes constraints from the current run's own latest, real,
    successfully-validated acceleo result into acceleo_agent's permanent
    config - the real HTTP target for a human clicking "Save these
    corrections for future runs" in the UI. 409 while a stage is running
    (the "latest result" this reads could be mid-write); 400 when there's
    no verified result to promote from (see actions.promote_constraints's
    own docstring for the exact gate)."""
    if runs.current().busy:
        raise HTTPException(status_code=409, detail=_BUSY_DETAIL)
    try:
        return actions.promote_constraints(runs.current(), request.constraints)
    except ValueError as e:
        raise HTTPException(status_code=400, detail=str(e))
