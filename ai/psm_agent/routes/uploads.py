"""A real, human-uploaded file becoming a real `type: "file"` attachment,
saved under this service's own writable ATTACHMENT_UPLOADS_DIR (see
prompt_paths.py) - see generation_toolkit.routes.uploads.build_uploads_router
for the shared, generic HTTP surface every generation-capable service
exposes identically over generation_toolkit.attachments.uploads' own
save/sanitize/size-cap logic. Its own file, not routes/files.py: that one
only ever reads real, already-existing repo files (available_files.py's
own scan of META_MODELS_DIR); this one accepts new, untrusted, client-
supplied bytes, a genuinely different concern with its own real risk (an
untrusted filename on write, not just an untrusted path on read).
"""
from generation_toolkit.routes.uploads import build_uploads_router

import prompt_paths

router = build_uploads_router(
    lambda: prompt_paths.ATTACHMENT_UPLOADS_DIR, lambda: prompt_paths.MAX_UPLOAD_BYTES
)
