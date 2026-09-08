"""Real files a "file" attachment can reference - see available_files.py's
own docstring for what counts and why. Its own router, separate from
routes/prompt_config.py, since "what files exist to attach" is a
different concern from "how a prompt config is stored."
"""
from fastapi import APIRouter

from available_files import list_available_files

router = APIRouter()


@router.get("/available-files")
def available_files_endpoint():
    return {"files": list_available_files()}
