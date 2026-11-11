from fastapi import APIRouter, Depends, HTTPException
from pydantic import BaseModel

from journal.dependencies import Stores, get_stores
from journal.prompt_configs import PromptConfigNotFound

router = APIRouter(prefix="/prompt-configs")


class Save(BaseModel):
    config: dict


class Constraints(BaseModel):
    constraints: list[str]


def _not_found(error: PromptConfigNotFound) -> HTTPException:
    return HTTPException(status_code=404, detail=str(error))


@router.get("/{agent}/{name}")
def read_config(agent: str, name: str, stores: Stores = Depends(get_stores)):
    """The live config, or the shipped one when none was saved."""
    try:
        return stores.prompt_configs.live(agent, name)
    except PromptConfigNotFound as e:
        raise _not_found(e) from e


@router.put("/{agent}/{name}")
def save_config(agent: str, name: str, body: Save, stores: Stores = Depends(get_stores)):
    """Keeps the config as a new version. A config with the live one's attachments is
    no change, and the live one comes back."""
    try:
        return stores.prompt_configs.save(agent, name, body.config)
    except PromptConfigNotFound as e:
        raise _not_found(e) from e


@router.get("/{agent}/{name}/shipped")
def read_shipped_config(agent: str, name: str, stores: Stores = Depends(get_stores)):
    try:
        return stores.prompt_configs.shipped(agent, name)
    except PromptConfigNotFound as e:
        raise _not_found(e) from e


@router.get("/{agent}/{name}/versions")
def list_versions(agent: str, name: str, stores: Stores = Depends(get_stores)):
    """The saved versions, newest first, and whether a shipped config exists."""
    try:
        return {
            "versions": stores.prompt_configs.versions(agent, name),
            "has_shipped": stores.prompt_configs.has_shipped(agent, name),
        }
    except PromptConfigNotFound as e:
        raise _not_found(e) from e


@router.get("/{agent}/{name}/versions/{version}")
def read_version(agent: str, name: str, version: str, stores: Stores = Depends(get_stores)):
    try:
        return stores.prompt_configs.version(agent, name, version)
    except PromptConfigNotFound as e:
        raise _not_found(e) from e


@router.get("/{agent}/{name}/constraints")
def read_constraints(agent: str, name: str, stores: Stores = Depends(get_stores)):
    """The learned constraints. A 404 when none were ever kept."""
    try:
        return {"constraints": stores.prompt_configs.constraints(agent, name)}
    except PromptConfigNotFound as e:
        raise _not_found(e) from e


@router.put("/{agent}/{name}/constraints")
def replace_constraints(agent: str, name: str, body: Constraints, stores: Stores = Depends(get_stores)):
    try:
        stores.prompt_configs.set_constraints(agent, name, body.constraints)
    except PromptConfigNotFound as e:
        raise _not_found(e) from e
    return {"constraints": body.constraints}
