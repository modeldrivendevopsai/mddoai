"""Real files a "file" attachment can reference - see available_files.py's
own docstring for what counts and why. Its own router, separate from
routes/prompt_config.py, since "what files exist to attach" is a
different concern from "how a prompt config is stored." Uses
generation_toolkit.routes.files.build_files_router for the shared, generic
`GET /available-files` HTTP surface every generation-capable service
exposes identically.
"""
from generation_toolkit.routes.files import build_files_router

from available_files import list_available_files

router, available_files_endpoint = build_files_router(list_available_files)
