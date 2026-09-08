"""Thin proxy to integration_runner's own psm prompt-config surface (see
that service's own routes/psm.py), the same one-hop-further-out shape
every other endpoint in this service already has: ui-host talks only to
this service, this service talks only to integration_runner, never
directly to psm_agent. integration_runner's own IntegrationRunnerError is
handled once, globally, by main.py's own exception handler.
"""
from fastapi import APIRouter
from pydantic import BaseModel

from clients import integration_runner_client

router = APIRouter(prefix="/psm")


class SaveConfigRequest(BaseModel):
    system_prompt: str
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
    return {"presets": integration_runner_client.list_psm_presets(name)}


@router.get("/prompt-config/{name}/{preset}")
def get_prompt_config_endpoint(name: str, preset: str):
    return integration_runner_client.get_psm_prompt_config(name, preset)


@router.put("/prompt-config/{name}/{preset}")
def save_prompt_config_endpoint(name: str, preset: str, request: SaveConfigRequest):
    return integration_runner_client.save_psm_prompt_config(name, preset, request.model_dump())


@router.get("/prompt-config/{name}/{preset}/history")
def prompt_config_history_endpoint(name: str, preset: str):
    return {"versions": integration_runner_client.get_psm_prompt_config_history(name, preset)}


@router.get("/prompt-config/{name}/{preset}/diff")
def prompt_config_diff_endpoint(name: str, preset: str, a: str, b: str):
    return integration_runner_client.diff_psm_prompt_config_versions(name, preset, a, b)


@router.post("/prompt-config/{name}/{preset}/restore/{version}")
def restore_prompt_config_endpoint(name: str, preset: str, version: str):
    return integration_runner_client.restore_psm_prompt_config_version(name, preset, version)


@router.post("/prompt-config/{name}/{preset}/revert")
def revert_prompt_config_endpoint(name: str, preset: str):
    return integration_runner_client.revert_psm_prompt_config(name, preset)


@router.post("/prompt-config/{name}/{preset}/promote-to-default")
def promote_prompt_config_to_default_endpoint(name: str, preset: str):
    return integration_runner_client.promote_psm_prompt_config_to_default(name, preset)


@router.get("/prompt-config/{name}/{preset}/check-references")
def check_prompt_config_references_endpoint(name: str, preset: str):
    return {"broken": integration_runner_client.check_psm_prompt_config_references(name, preset)}


@router.post("/prompt-config/{name}/{preset}/preview")
def preview_prompt_config_endpoint(name: str, preset: str):
    return integration_runner_client.preview_psm_prompt_config(name, preset)


@router.post("/prompt-config/{name}/{preset}/learned-constraints")
def add_learned_constraints_endpoint(name: str, preset: str, request: LearnedConstraintsRequest):
    return integration_runner_client.add_psm_learned_constraints(name, preset, request.constraints)


@router.delete("/prompt-config/{name}/{preset}/learned-constraints")
def remove_learned_constraint_endpoint(name: str, preset: str, request: RemoveLearnedConstraintRequest):
    return integration_runner_client.remove_psm_learned_constraint(name, preset, request.constraint)


@router.get("/available-files")
def available_files_endpoint():
    return {"files": integration_runner_client.list_psm_available_files()}


@router.post("/promote-constraints")
def promote_constraints_endpoint(request: PromoteConstraintsRequest):
    return integration_runner_client.promote_psm_constraints(request.constraints)
