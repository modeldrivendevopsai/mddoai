"""Generic, stage-agnostic attempt introspection: applies to any stage's
own real on-disk attempt tree (stages/_attempts_read.py), not just psm's,
so it doesn't live in routes/psm.py alongside that stage's own
prompt-config pass-throughs. Not part of core.py either: core.py's own
charter is "whichever stage is current" pipeline-lifecycle routes, this is
a different concern, run-history/attempt introspection, that never cares
which stage is current.
"""
from fastapi import APIRouter, HTTPException

from integration_runner.stages._attempts_read import AttemptNotFoundError, read_attempt, read_manifest

router = APIRouter()


@router.get("/runs/{run_id}/manifest")
def manifest_endpoint(run_id: str):
    try:
        return {"attempts": read_manifest(run_id)}
    except AttemptNotFoundError as e:
        raise HTTPException(status_code=404, detail=str(e))


@router.get("/runs/{run_id}/{stage}/{attempt}")
def attempt_endpoint(run_id: str, stage: str, attempt: str):
    try:
        return read_attempt(run_id, stage, attempt)
    except AttemptNotFoundError as e:
        raise HTTPException(status_code=404, detail=str(e))
