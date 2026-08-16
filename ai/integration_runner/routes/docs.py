"""Docs-stage-specific routes. Mirrors orchestrator/tools/docs.py, and
exposes stages/docs/actions.py's real extend_with_page() over HTTP —
called directly against runs.current(), not through an IntegrationRun
method (see stages/docs/actions.py's own docstring for why this action
lives with its stage instead).
"""
from fastapi import APIRouter, HTTPException
from pydantic import BaseModel

from integration_runner import runs
from integration_runner.stages.docs import actions

router = APIRouter(prefix="/docs")

_BUSY_DETAIL = "A stage is still running, try again shortly."


class ExtendRequest(BaseModel):
    url: str
    force_refresh: bool = False


@router.post("/extend")
def extend_endpoint(request: ExtendRequest):
    """Fetches one specific page for real and appends it to the docs
    stage's current pending output — the add_page_to_docs tool's real HTTP
    target. 409 if a stage is currently running (last_output could be
    mid-write); 400 if docs isn't the current pending stage (see
    extend_with_page()'s own docstring for why that's a real, not
    cosmetic, restriction) or if the fetch itself failed (a bad/dead URL is
    a client-supplied problem, the same 4xx-for-a-real-expected-failure
    convention clients/integration_runner_client.py's own business-error
    handling already uses, not a genuine server-side 500).

    Holds busy for the real fetch + append itself, not just the initial
    check: this does a real, potentially slow network call and then a
    read-modify-write of last_output, the same real mutation
    run_stage_async() protects with busy for its own background thread. A
    concurrent /rerun or /review approval starting a fresh stage run while
    this fetch is still in flight would otherwise race on last_output with
    no error at all, silently losing one side's update."""
    run = runs.current()
    if run.busy:
        raise HTTPException(status_code=409, detail=_BUSY_DETAIL)
    run.busy = True
    try:
        return actions.extend_with_page(run, request.url, force_refresh=request.force_refresh)
    except (ValueError, RuntimeError) as e:
        raise HTTPException(status_code=400, detail=str(e))
    finally:
        run.busy = False
