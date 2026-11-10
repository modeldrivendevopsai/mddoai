from fastapi import APIRouter, Depends, HTTPException
from pydantic import BaseModel, Field

from journal import settings
from journal.dependencies import Stores, get_stores
from journal.platform_meta import (
    MAX_NAME_CHARS,
    MAX_OUTPUT_FILENAME_CHARS,
    NotPurgeable,
    PlatformNotFound,
    PlatformVersion,
    PublishError,
    VersionTaken,
)

router = APIRouter(prefix="/platforms")

# The longest version, MAJOR.MINOR.PATCH with six digits each part, in characters.
_MAX_VERSION_CHARS = 20
# The longest run id a publish records, in characters.
_MAX_RUN_ID_CHARS = 64


class Publish(BaseModel):
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
    source_run_id: str | None = Field(None, max_length=_MAX_RUN_ID_CHARS, description="The integration run it was approved in.")
    version: str | None = Field(
        None, max_length=_MAX_VERSION_CHARS, description="MAJOR.MINOR.PATCH. The patch after the highest one when left out."
    )


def _require_size(body: Publish) -> None:
    for field in ("metamodel", "transformation", "template"):
        if len(getattr(body, field).encode("utf-8")) > settings.MAX_ARTIFACT_BYTES:
            raise HTTPException(status_code=413, detail=f"{field} exceeds {settings.MAX_ARTIFACT_BYTES} bytes")


def _with_versions(stores: Stores, platform: PlatformVersion) -> dict:
    """A version as a dict, with every version its platform has and which of them
    were published, since only those can be purged."""
    every = stores.platforms.versions(platform.id)
    return {
        **platform.as_dict(),
        "versions": [v.version for v in every],
        "published_versions": [v.version for v in every if v.published],
    }


@router.get("")
def list_platforms(stores: Stores = Depends(get_stores)):
    """Every platform's latest version, with the versions it has."""
    return {"platforms": [_with_versions(stores, platform) for platform in stores.platforms.latest()]}


@router.get("/pim-transformation")
def read_pim_transformation(stores: Stores = Depends(get_stores)):
    try:
        return {"transformation": stores.platforms.pim_transformation()}
    except OSError as e:
        raise HTTPException(status_code=404, detail="The platforms folder has no SWArch to PIM transformation.") from e


@router.get("/{platform_id}")
def read_platform(platform_id: str, version: str | None = None, stores: Stores = Depends(get_stores)):
    """A platform's version (its latest when none is named) and its files."""
    platform = stores.platforms.get(platform_id, version)
    if platform is None:
        which = f" version {version}" if version else ""
        raise HTTPException(status_code=404, detail=f"No platform '{platform_id}'{which}.")
    try:
        files = stores.platforms.files(platform_id, platform.version)
    except (PlatformNotFound, OSError) as e:
        # Purged between looking the version up and reading its files.
        raise HTTPException(status_code=404, detail=f"No platform '{platform_id}' version {platform.version}.") from e
    return {**_with_versions(stores, platform), "files": files}


@router.post("", status_code=201)
def publish_platform(body: Publish, stores: Stores = Depends(get_stores)):
    _require_size(body)
    try:
        return stores.platforms.publish(**body.model_dump()).as_dict()
    except PublishError as e:
        raise HTTPException(status_code=422, detail=str(e)) from e
    except VersionTaken as e:
        raise HTTPException(status_code=409, detail=str(e)) from e


def _purge(stores: Stores, platform_id: str, version: str | None) -> dict:
    try:
        return {"purged": stores.platforms.purge(platform_id, version)}
    except PlatformNotFound as e:
        raise HTTPException(status_code=404, detail=str(e)) from e
    except NotPurgeable as e:
        raise HTTPException(status_code=409, detail=str(e)) from e


@router.delete("/{platform_id}/versions/{version}")
def purge_version(platform_id: str, version: str, stores: Stores = Depends(get_stores)):
    return _purge(stores, platform_id, version)


@router.delete("/{platform_id}")
def purge_platform(platform_id: str, stores: Stores = Depends(get_stores)):
    return _purge(stores, platform_id, None)
