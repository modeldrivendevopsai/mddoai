"""Generic, stage-agnostic attempt introspection: what any stage's tries left in
the journal, not just psm's, so it doesn't live in routes/psm.py alongside that
stage's own prompt-config pass-throughs. Not part of core.py either: core.py's
charter is "whichever stage is current" pipeline-lifecycle routes, this is a
different concern, run-history introspection, that never cares which stage is
current. An unknown run or attempt is the journal's own 404, passed on by the
app's AgentServiceError handler."""
from fastapi import APIRouter

from clients import journal_client

router = APIRouter()


@router.get("/runs/{run_id}/manifest")
def manifest_endpoint(run_id: str):
    return {"attempts": journal_client.get_manifest(run_id)}


@router.get("/runs/{run_id}/{stage}/{attempt}")
def attempt_endpoint(run_id: str, stage: str, attempt: str):
    return journal_client.get_attempt(run_id, stage, attempt)
