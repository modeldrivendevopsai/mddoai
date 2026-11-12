"""The shared objects the routes depend on, built once from the settings.
Routes ask for them through FastAPI's dependency injection, so a test can
swap in a temporary folder."""
from generation_jobs import GenerationJobs
from pipeline_history import PipelineHistory
from platform_registry import PlatformRegistry
from settings import MAX_FINISHED_JOBS, MAX_RUNNING_JOBS, WORKSPACE_DIR
from workspace import Workspace

_workspace = Workspace(WORKSPACE_DIR)
_history = PipelineHistory()
_registry = PlatformRegistry()
_jobs = GenerationJobs(max_finished=MAX_FINISHED_JOBS, max_running=MAX_RUNNING_JOBS)


def get_workspace() -> Workspace:
    return _workspace


def get_history() -> PipelineHistory:
    return _history


def get_registry() -> PlatformRegistry:
    return _registry


def get_jobs() -> GenerationJobs:
    return _jobs
