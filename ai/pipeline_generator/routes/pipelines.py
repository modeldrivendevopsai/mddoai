from fastapi import APIRouter, Depends, HTTPException

from pipeline_history import PipelineHistory
from routes.dependencies import get_history

router = APIRouter(prefix="/pipelines")


def _public(entry: dict) -> dict:
    # `key` is how the history tells inputs apart, an internal detail.
    return {name: value for name, value in entry.items() if name != "key"}


@router.get("")
def list_pipelines(history: PipelineHistory = Depends(get_history)):
    """The pipelines generated so far, newest first."""
    return {"pipelines": [_public(meta) for meta in history.summaries()]}


@router.get("/{pipeline_id}")
def get_pipeline(pipeline_id: str, history: PipelineHistory = Depends(get_history)):
    """One pipeline in full, so it can be reopened."""
    record = history.get(pipeline_id)
    if record is None:
        raise HTTPException(status_code=404, detail="No such pipeline.")
    return _public(record)
