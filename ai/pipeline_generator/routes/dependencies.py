"""The shared objects the routes depend on, built once from the settings.
Routes ask for them through FastAPI's dependency injection, so a test can
swap in a temporary folder."""
from pipeline_history import PipelineHistory
from settings import DATA_DIR, MAX_PIPELINES, WORKSPACE_DIR
from workspace import Workspace

_workspace = Workspace(WORKSPACE_DIR)
_history = PipelineHistory(DATA_DIR / "pipelines", max_entries=MAX_PIPELINES)


def get_workspace() -> Workspace:
    return _workspace


def get_history() -> PipelineHistory:
    return _history
