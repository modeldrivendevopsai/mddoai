"""A real, human-uploaded file becoming a real `type: "file"` attachment,
saved under this service's own writable ATTACHMENT_UPLOADS_DIR (see
prompt_paths.py) - see generation_toolkit.attachments.uploads for the
actual save/sanitize/size-cap logic this router is a thin HTTP surface
over. Mirrors psm_agent/routes/uploads.py.
"""
from fastapi import APIRouter, HTTPException, UploadFile

from generation_toolkit.attachments.uploads import UploadError, save_uploaded_file

import prompt_paths

router = APIRouter()


@router.post("/attachment-uploads")
async def upload_attachment_endpoint(file: UploadFile):
    content = await file.read()
    try:
        stored_path = save_uploaded_file(
            prompt_paths.ATTACHMENT_UPLOADS_DIR, file.filename or "upload", content, prompt_paths.MAX_UPLOAD_BYTES
        )
    except UploadError as e:
        raise HTTPException(status_code=400, detail=str(e)) from e
    return {"path": stored_path}
