from fastapi import APIRouter, Depends, HTTPException, Query

from routes.dependencies import get_workspace
from settings import MAX_MODEL_BYTES, MAX_PATH_CHARS
from workspace import Workspace, WorkspaceError

router = APIRouter(prefix="/workspace")


@router.get("/browse")
def browse(
    path: str = Query("/", max_length=MAX_PATH_CHARS),
    dirs_only: bool = False,
    workspace: Workspace = Depends(get_workspace),
):
    """A folder's contents, for the Browse dialogs."""
    try:
        return workspace.browse(path, dirs_only)
    except WorkspaceError as e:
        raise HTTPException(status_code=e.status, detail=e.message)


@router.get("/file")
def read_file(
    path: str = Query(..., max_length=MAX_PATH_CHARS),
    workspace: Workspace = Depends(get_workspace),
):
    """A model file's content, so it can be loaded exactly like an upload."""
    try:
        return workspace.read_text(path, MAX_MODEL_BYTES)
    except WorkspaceError as e:
        raise HTTPException(status_code=e.status, detail=e.message)
