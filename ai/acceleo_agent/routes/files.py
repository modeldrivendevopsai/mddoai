"""Real files a "file" attachment can reference - see available_files.py's
own docstring for what counts and why. Mirrors psm_agent/routes/files.py.
"""
from fastapi import APIRouter

from available_files import list_available_files

router = APIRouter()


@router.get("/available-files")
def available_files_endpoint():
    return {"files": list_available_files()}
