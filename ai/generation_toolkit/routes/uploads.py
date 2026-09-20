"""The shared `POST /attachment-uploads` HTTP surface every generation-
capable service exposes identically: a real, human-uploaded file becoming
a real `type: "file"` attachment, saved under that service's own writable
uploads directory - see generation_toolkit.attachments.uploads for the
actual save/sanitize/size-cap logic this router is a thin HTTP surface
over.

`uploads_dir`/`max_upload_bytes` are zero-arg getters, not plain values,
called fresh on every request rather than captured once at construction
time: each service's own test suite isolates uploads (and, separately,
exercises the real size-cap rejection) by monkeypatching its own
prompt_paths module attributes for the duration of one test, which only
works if this router re-reads them through that same module reference on
every call, not once up front.
"""
from typing import Callable

from fastapi import APIRouter, HTTPException, UploadFile

from ..attachments.uploads import UploadError, save_uploaded_file


def build_uploads_router(uploads_dir: Callable[[], object], max_upload_bytes: Callable[[], int]) -> APIRouter:
    router = APIRouter()

    @router.post("/attachment-uploads")
    async def upload_attachment_endpoint(file: UploadFile):
        content = await file.read()
        try:
            stored_path = save_uploaded_file(uploads_dir(), file.filename or "upload", content, max_upload_bytes())
        except UploadError as e:
            raise HTTPException(status_code=400, detail=str(e)) from e
        return {"path": stored_path}

    return router
