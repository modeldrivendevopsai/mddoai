import base64
import binascii

from fastapi import APIRouter, Depends, HTTPException
from pydantic import BaseModel, Field

from journal.attempts import AttemptConflict, AttemptNotFound
from journal import settings
from journal.dependencies import Stores, get_stores

router = APIRouter(prefix="/attempts")


class Reserve(BaseModel):
    stage: str


class Compiled(BaseModel):
    content_base64: str = Field(..., description="The zip of what the validators compiled, base64 encoded.")


class Record(BaseModel):
    filename: str = Field(..., description="The artifact's file name.")
    content: str
    result: dict = Field(..., description="The validator's result. `valid` is required.")
    prompt: dict | None = None
    prompt_version: str | None = None
    extra_files: dict[str, str] = Field(
        default_factory=dict, description="Other files of the attempt, by path inside its folder."
    )


@router.post("/{run_id}", status_code=201)
def reserve_attempt(run_id: str, body: Reserve, stores: Stores = Depends(get_stores)):
    try:
        return {"attempt": stores.attempts.reserve(run_id, body.stage)}
    except AttemptNotFound as e:
        raise HTTPException(status_code=404, detail=str(e)) from e


@router.get("/{run_id}/manifest")
def read_manifest(run_id: str, stores: Stores = Depends(get_stores)):
    try:
        return {"attempts": stores.attempts.manifest(run_id)}
    except AttemptNotFound as e:
        raise HTTPException(status_code=404, detail=str(e)) from e


@router.put("/{run_id}/{stage}/{attempt}")
def record_attempt(run_id: str, stage: str, attempt: str, body: Record, stores: Stores = Depends(get_stores)):
    try:
        stores.attempts.record(run_id, stage, attempt, **body.model_dump())
    except AttemptNotFound as e:
        raise HTTPException(status_code=404, detail=str(e)) from e
    except AttemptConflict as e:
        raise HTTPException(status_code=409, detail=str(e)) from e
    except ValueError as e:
        raise HTTPException(status_code=422, detail=str(e)) from e
    return {"status": "recorded"}


@router.put("/{run_id}/{stage}/{attempt}/compiled")
def keep_compiled(run_id: str, stage: str, attempt: str, body: Compiled, stores: Stores = Depends(get_stores)):
    """Keeps what the validators compiled during an attempt that is not recorded yet."""
    try:
        data = base64.b64decode(body.content_base64, validate=True)
    except (binascii.Error, ValueError) as e:
        raise HTTPException(status_code=422, detail="content_base64 is not base64") from e
    if len(data) > settings.MAX_COMPILED_BYTES:
        raise HTTPException(status_code=413, detail=f"the compiled output is over {settings.MAX_COMPILED_BYTES} bytes")
    try:
        stores.attempts.keep_compiled(run_id, stage, attempt, data)
    except AttemptNotFound as e:
        raise HTTPException(status_code=404, detail=str(e)) from e
    except AttemptConflict as e:
        raise HTTPException(status_code=409, detail=str(e)) from e
    return {"status": "kept"}


@router.get("/{run_id}/{stage}/{attempt}/compiled")
def read_compiled(run_id: str, stage: str, attempt: str, stores: Stores = Depends(get_stores)):
    try:
        return {"content_base64": base64.b64encode(stores.attempts.compiled(run_id, stage, attempt)).decode("ascii")}
    except AttemptNotFound as e:
        raise HTTPException(status_code=404, detail=str(e)) from e


@router.delete("/{run_id}/{stage}/{attempt}")
def discard_attempt(run_id: str, stage: str, attempt: str, stores: Stores = Depends(get_stores)):
    try:
        stores.attempts.discard(run_id, stage, attempt)
    except AttemptNotFound as e:
        raise HTTPException(status_code=404, detail=str(e)) from e
    except AttemptConflict as e:
        raise HTTPException(status_code=409, detail=str(e)) from e
    return {"status": "discarded"}


@router.get("/{run_id}/{stage}/{attempt}")
def read_attempt(run_id: str, stage: str, attempt: str, stores: Stores = Depends(get_stores)):
    try:
        return stores.attempts.read(run_id, stage, attempt)
    except AttemptNotFound as e:
        raise HTTPException(status_code=404, detail=str(e)) from e
