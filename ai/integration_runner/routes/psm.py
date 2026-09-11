"""psm-stage-specific routes: two different kinds of real HTTP surface,
kept in one file since both are psm's own, neither generic enough for
core.py nor big enough yet to split further.

1. Thin pass-throughs to psm_agent's own real prompt-config capability
   (clients/psm_agent_client.py), the same "integration_runner proxies,
   psm_agent owns the actual data" shape stages/psm/agent.py's own real
   /psm call already uses. No run awareness at all: a prompt config is
   editable any time, independent of any one run.
2. promote_constraints, which IS run-aware (stages/psm/actions.py), the
   real HTTP target for turning a verified run's own live corrections into
   psm_agent's permanent config.
"""
from fastapi import APIRouter, HTTPException, UploadFile
from pydantic import BaseModel

from clients import psm_agent_client

from integration_runner import runs
from integration_runner.stages.psm import actions

router = APIRouter(prefix="/psm")

_BUSY_DETAIL = "A stage is still running, try again shortly."


class SaveConfigRequest(BaseModel):
    # No system_prompt field: a config's own first "text" attachment IS
    # the system message (see psm_agent's own PromptConfigBody, the real
    # schema this pass-through mirrors).
    attachments: list[dict]
    learned_constraints: list[str] = []


class LearnedConstraintsRequest(BaseModel):
    constraints: list[str]


class RemoveLearnedConstraintRequest(BaseModel):
    constraint: str


class PromoteConstraintsRequest(BaseModel):
    constraints: list[str]


@router.get("/prompt-config/{name}")
def get_prompt_config_endpoint(name: str):
    return psm_agent_client.get_prompt_config(name)


@router.put("/prompt-config/{name}")
def save_prompt_config_endpoint(name: str, request: SaveConfigRequest):
    return psm_agent_client.save_prompt_config(name, request.model_dump())


@router.get("/prompt-config/{name}/history")
def prompt_config_history_endpoint(name: str):
    return {"versions": psm_agent_client.get_prompt_config_history(name)}


@router.get("/prompt-config/{name}/diff")
def prompt_config_diff_endpoint(name: str, a: str, b: str):
    return psm_agent_client.diff_prompt_config_versions(name, a, b)


@router.post("/prompt-config/{name}/restore/{version}")
def restore_prompt_config_endpoint(name: str, version: str):
    return psm_agent_client.restore_prompt_config_version(name, version)


@router.post("/prompt-config/{name}/revert")
def revert_prompt_config_endpoint(name: str):
    return psm_agent_client.revert_prompt_config(name)


@router.post("/prompt-config/{name}/promote-to-default")
def promote_prompt_config_to_default_endpoint(name: str):
    return psm_agent_client.promote_prompt_config_to_default(name)


@router.get("/prompt-config/{name}/check-references")
def check_prompt_config_references_endpoint(name: str):
    return {"broken": psm_agent_client.check_prompt_config_references(name)}


@router.post("/prompt-config/{name}/preview")
def preview_prompt_config_endpoint(name: str):
    return psm_agent_client.preview_prompt_config(name)


@router.post("/prompt-config/{name}/learned-constraints")
def add_learned_constraints_endpoint(name: str, request: LearnedConstraintsRequest):
    return psm_agent_client.add_learned_constraints(name, request.constraints)


@router.delete("/prompt-config/{name}/learned-constraints")
def remove_learned_constraint_endpoint(name: str, request: RemoveLearnedConstraintRequest):
    return psm_agent_client.remove_learned_constraint(name, request.constraint)


@router.get("/available-files")
def available_files_endpoint():
    return {"files": psm_agent_client.list_available_files()}


@router.get("/resolve-mode")
def resolve_mode_endpoint(platform_description: str):
    return psm_agent_client.resolve_psm_mode(platform_description)


@router.post("/attachment-uploads")
async def upload_attachment_endpoint(file: UploadFile):
    content = await file.read()
    return {"path": psm_agent_client.upload_attachment_file(file.filename or "upload", content)}


@router.post("/promote-constraints")
def promote_constraints_endpoint(request: PromoteConstraintsRequest):
    """Promotes constraints from the current run's own latest, real,
    successfully-validated psm result into psm_agent's permanent config -
    the real HTTP target for a human clicking "Save these corrections for
    future runs" in the UI. 409 while a stage is running (the "latest
    result" this reads could be mid-write); 400 when there's no verified
    result to promote from (see actions.promote_constraints's own
    docstring for the exact gate)."""
    if runs.current().busy:
        raise HTTPException(status_code=409, detail=_BUSY_DETAIL)
    try:
        return actions.promote_constraints(runs.current(), request.constraints)
    except ValueError as e:
        raise HTTPException(status_code=400, detail=str(e))
