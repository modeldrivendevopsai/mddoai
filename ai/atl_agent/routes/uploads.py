"""A real, human-uploaded file becoming a real `type: "file"` attachment,
kept by the journal under this service's own agent name (see
prompt_settings.py) - see generation_toolkit.routes.uploads.build_uploads_router
for the shared, generic HTTP surface every generation-capable service
exposes identically over generation_toolkit.attachments.uploads' own
save/sanitize/size-cap logic.
"""
from generation_toolkit.routes.uploads import build_uploads_router

import prompt_settings

router = build_uploads_router(
    prompt_settings.AGENT, lambda: prompt_settings.MAX_UPLOAD_BYTES
)
