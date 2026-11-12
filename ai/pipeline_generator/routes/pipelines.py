import httpx
from fastapi import APIRouter, Depends, HTTPException

from pipeline_history import PipelineHistory
from routes.dependencies import get_history

router = APIRouter(prefix="/pipelines")

_JOURNAL_UNAVAILABLE = "The pipelines cannot be read right now, try again shortly."


def _public(entry: dict) -> dict:
    # `key` is how the history tells inputs apart, an internal detail.
    return {name: value for name, value in entry.items() if name != "key"}


@router.get("")
def list_pipelines(history: PipelineHistory = Depends(get_history)):
    """The pipelines generated so far, newest first."""
    try:
        return {"pipelines": [_public(meta) for meta in history.summaries()]}
    except httpx.HTTPError as e:
        raise HTTPException(status_code=502, detail=_JOURNAL_UNAVAILABLE) from e


@router.get("/{pipeline_id}")
def get_pipeline(pipeline_id: str, history: PipelineHistory = Depends(get_history)):
    """One pipeline in full, so it can be reopened."""
    try:
        record = history.get(pipeline_id)
    except httpx.HTTPError as e:
        raise HTTPException(status_code=502, detail=_JOURNAL_UNAVAILABLE) from e
    if record is None:
        raise HTTPException(status_code=404, detail="No such pipeline.")
    return _public(record)
