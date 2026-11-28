"""Generic, stage-agnostic attempt introspection: what any stage's tries left in
the journal, not just psm's, so it doesn't live in routes/psm.py alongside that
stage's own prompt-config pass-throughs. Not part of core.py either: core.py's
charter is "whichever stage is current" pipeline-lifecycle routes, this is a
different concern, run-history introspection, that never cares which stage is
current. An unknown run or attempt is the journal's own 404, passed on by the
app's AgentServiceError handler."""
import httpx
from fastapi import APIRouter, HTTPException

from clients import journal_client
from integration_runner import runs

router = APIRouter()

_JOURNAL_UNAVAILABLE = "The journal is unavailable, try again shortly."


@router.get("/runs/{run_id}/manifest")
def manifest_endpoint(run_id: str):
    """The journal's entries, each with the tries and rules its call spent. 502 when the
    journal cannot be reached."""
    try:
        return {"attempts": runs.annotated_manifest(run_id, journal_client.get_manifest(run_id))}
    except httpx.HTTPError:
        raise HTTPException(status_code=502, detail=_JOURNAL_UNAVAILABLE)


@router.get("/runs/{run_id}/evolution")
def evolution_endpoint(run_id: str):
    """What the run took, from its own events: per stage, each call with the tries it
    spent and the rules it ran with and learned, and whether the run was forked. Small,
    unlike the events, which hold every generated file. 404 for an unknown run, 502 when
    the journal cannot be reached for the attempt numbers."""
    try:
        result = runs.get_run_evolution(run_id)
    except httpx.HTTPError:
        raise HTTPException(status_code=502, detail=_JOURNAL_UNAVAILABLE)
    if result is None:
        raise HTTPException(status_code=404, detail=f"No run with id {run_id!r}")
    return result


@router.get("/runs/{run_id}/{stage}/{attempt}")
def attempt_endpoint(run_id: str, stage: str, attempt: str):
    return journal_client.get_attempt(run_id, stage, attempt)
