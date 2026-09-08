"""Generic, stage-agnostic attempt introspection: thin proxy to
integration_runner's own /runs/{run_id}/manifest and
/runs/{run_id}/{stage}/{attempt} (see that service's own routes/attempts.py).
integration_runner's own IntegrationRunnerError (404 for an unknown run/
attempt) is handled once, globally, by main.py's own exception handler -
no try/except needed here.
"""
from fastapi import APIRouter

from clients import integration_runner_client

router = APIRouter()


@router.get("/runs/{run_id}/manifest")
def manifest_endpoint(run_id: str):
    return {"attempts": integration_runner_client.get_run_manifest(run_id)}


@router.get("/runs/{run_id}/{stage}/{attempt}")
def attempt_endpoint(run_id: str, stage: str, attempt: str):
    return integration_runner_client.get_attempt(run_id, stage, attempt)
