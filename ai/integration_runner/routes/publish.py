import httpx
from fastapi import APIRouter, HTTPException
from pydantic import BaseModel

from clients.agent_service_errors import AgentServiceError
from integration_runner import publishing, runs
from integration_runner.pipeline import BusyError

router = APIRouter()

_BUSY_DETAIL = "A stage is still running, try again shortly."
_NOT_CURRENT_RUN_DETAIL = "This is no longer the current run. Resume it to publish its platform."


class PublishRequest(BaseModel):
    # Unset name and output_filename mean the defaults of GET /publish. A run_id
    # that is not the current run refuses the publish, so a page showing an
    # older run cannot publish another one.
    name: str | None = None
    output_filename: str | None = None
    # MAJOR.MINOR.PATCH. Unset means the patch after the platform's highest version.
    version: str | None = None
    run_id: str | None = None


@router.get("/publish")
def publish_defaults_endpoint():
    """What POST /publish uses when given nothing."""
    return publishing.defaults(runs.current())


@router.post("/publish")
def publish_endpoint(request: PublishRequest):
    """Publishes the current, completed run's platform to the journal.
    The run is busy for the whole publish, so a double click or a chat tool
    call cannot publish two versions at once."""
    run = runs.current()
    if request.run_id is not None and request.run_id != run.run_id:
        raise HTTPException(status_code=409, detail=_NOT_CURRENT_RUN_DETAIL)
    try:
        run.claim_busy()
    except BusyError:
        raise HTTPException(status_code=409, detail=_BUSY_DETAIL)
    try:
        return publishing.publish(run, request.name, request.output_filename, request.version)
    except publishing.NotPublishableError as e:
        raise HTTPException(status_code=409, detail=str(e))
    except AgentServiceError as e:
        raise HTTPException(status_code=400, detail=e.detail)
    except httpx.HTTPError:
        raise HTTPException(status_code=502, detail="The journal is unavailable, try again shortly.")
    finally:
        run.release_busy()
