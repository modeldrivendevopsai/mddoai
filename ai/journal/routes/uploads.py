from fastapi import APIRouter, Depends, HTTPException
from pydantic import BaseModel

from journal.dependencies import Stores, get_stores
from journal.uploads import UploadExists, UploadNotFound, UploadTooLarge

router = APIRouter(prefix="/uploads")


class Upload(BaseModel):
    content: str


@router.get("/{agent}")
def list_uploads(agent: str, stores: Stores = Depends(get_stores)):
    try:
        return {"files": stores.uploads.names(agent)}
    except UploadNotFound as e:
        raise HTTPException(status_code=404, detail=str(e)) from e


@router.put("/{agent}/{name}", status_code=201)
def save_upload(agent: str, name: str, body: Upload, stores: Stores = Depends(get_stores)):
    try:
        stores.uploads.save(agent, name, body.content)
    except UploadNotFound as e:
        raise HTTPException(status_code=404, detail=str(e)) from e
    except UploadExists as e:
        raise HTTPException(status_code=409, detail=str(e)) from e
    except UploadTooLarge as e:
        raise HTTPException(status_code=413, detail=str(e)) from e
    return {"name": name}


@router.get("/{agent}/{name}")
def read_upload(agent: str, name: str, stores: Stores = Depends(get_stores)):
    try:
        return {"content": stores.uploads.read(agent, name)}
    except UploadNotFound as e:
        raise HTTPException(status_code=404, detail=str(e)) from e
    except UnicodeDecodeError as e:
        raise HTTPException(status_code=422, detail=f"the file {name!r} is not UTF-8 text") from e
