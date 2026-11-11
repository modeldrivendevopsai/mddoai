from fastapi import APIRouter, Depends, HTTPException
from pydantic import BaseModel, Field

from journal.dependencies import Stores, get_stores

router = APIRouter(prefix="/pipelines")


class Record(BaseModel):
    key: str = Field(..., description="What the model was generated from. A newer generation for a key replaces the older.")
    swarch_name: str
    source_path: str | None = None
    content: str = Field(..., description="The model it was generated from.")
    results: list[dict] = Field(
        ..., description="One per platform: its platform id, output file name, pipeline text and intermediates."
    )
    generated_at: str | None = None


@router.post("", status_code=201)
def record_pipeline(body: Record, stores: Stores = Depends(get_stores)):
    """Keeps a generation, and returns its id: the earlier generation's for the same key."""
    try:
        return {"id": stores.pipelines.record(**body.model_dump())}
    except ValueError as e:
        raise HTTPException(status_code=422, detail=str(e)) from e


@router.get("")
def list_pipelines(stores: Stores = Depends(get_stores)):
    """Every pipeline, newest first, without the bulky parts."""
    return {"pipelines": stores.pipelines.summaries()}


@router.get("/{pipeline_id}")
def read_pipeline(pipeline_id: str, stores: Stores = Depends(get_stores)):
    """One pipeline in full, its files read back into the record."""
    record = stores.pipelines.get(pipeline_id)
    if record is None:
        raise HTTPException(status_code=404, detail="No such pipeline.")
    return record
