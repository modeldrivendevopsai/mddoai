from fastapi import APIRouter, Depends, HTTPException
from pydantic import BaseModel, Field

from platform_registry import MAX_NAME_CHARS, MAX_OUTPUT_FILENAME_CHARS, Artifacts, PlatformRegistry, PublishError
from routes.dependencies import get_registry
from settings import MAX_ARTIFACT_BYTES

router = APIRouter(prefix="/platforms")


class PublishRequest(BaseModel):
    name: str = Field(..., min_length=1, max_length=MAX_NAME_CHARS, description="The platform's display name; its id is derived from it.")
    output_filename: str = Field(
        ..., min_length=1, max_length=MAX_OUTPUT_FILENAME_CHARS, description="The file the template generates, one file name."
    )
    output_model_name: str = Field(
        ..., min_length=1, max_length=MAX_NAME_CHARS, description="The transformation's declared output model name."
    )
    metamodel: str = Field(..., min_length=1, description="The platform's metamodel, Ecore text.")
    transformation: str = Field(..., min_length=1, description="PIM to platform model, .atl source.")
    template: str = Field(..., min_length=1, description="Platform model to pipeline file, .mtl source.")
    source_run_id: str | None = Field(None, max_length=64, description="The integration run it was approved in.")


def _require_artifact_size(request: PublishRequest) -> None:
    for field in ("metamodel", "transformation", "template"):
        if len(getattr(request, field).encode("utf-8")) > MAX_ARTIFACT_BYTES:
            raise HTTPException(status_code=413, detail=f"{field} exceeds {MAX_ARTIFACT_BYTES} bytes")


@router.get("")
def list_platforms(registry: PlatformRegistry = Depends(get_registry)):
    return {
        "platforms": [
            {"id": p.id, "name": p.name, "output_filename": p.output_filename} for p in registry.latest()
        ]
    }


@router.post("", status_code=201)
def publish_platform(request: PublishRequest, registry: PlatformRegistry = Depends(get_registry)):
    """Stores a platform approved in an integration run as its next version,
    so pipelines can be generated for it."""
    _require_artifact_size(request)
    try:
        published = registry.publish(
            name=request.name,
            output_filename=request.output_filename,
            output_model_name=request.output_model_name,
            artifacts=Artifacts(request.metamodel, request.transformation, request.template),
            source_run_id=request.source_run_id,
        )
    except PublishError as e:
        raise HTTPException(status_code=422, detail=str(e))
    return {
        "id": published.id,
        "name": published.name,
        "version": published.version,
        "output_filename": published.output_filename,
    }
