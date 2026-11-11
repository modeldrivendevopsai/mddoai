"""The shared `POST /attachment-uploads` HTTP surface every generation-
capable service exposes identically: a real, human-uploaded file becoming
a real `type: "file"` attachment, kept by the journal under that service's own
agent name - see generation_toolkit.attachments.uploads for the
actual save/sanitize/size-cap logic this router is a thin HTTP surface
over.

`agent` names the agent the journal keeps the uploads under. `max_upload_bytes`
is a zero-arg getter, not a plain value, called fresh on every request rather
than captured once at construction time: a service's own test suite exercises
the real size-cap rejection by monkeypatching its own prompt_settings module
attribute for the duration of one test, which only works if this router
re-reads it through that same module reference on every call, not once up front.
"""
from typing import Callable

from fastapi import APIRouter, HTTPException, UploadFile

from ..attachments.uploads import UploadError, save_uploaded_file


def build_uploads_router(agent: str, max_upload_bytes: Callable[[], int]) -> APIRouter:
    router = APIRouter()

    # A plain def, not async: saving calls the journal, which would block the event loop,
    # and a plain def runs in the threadpool.
    @router.post("/attachment-uploads")
    def upload_attachment_endpoint(file: UploadFile):
        content = file.file.read()
        try:
            stored_path = save_uploaded_file(agent, file.filename or "upload", content, max_upload_bytes())
        except UploadError as e:
            raise HTTPException(status_code=400, detail=str(e)) from e
        return {"path": stored_path}

    return router
