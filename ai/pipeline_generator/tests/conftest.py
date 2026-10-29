import pytest

import main
from generation_jobs import GenerationJobs
from pipeline_history import PipelineHistory
from platform_registry import PlatformRegistry
from routes.dependencies import get_history, get_jobs, get_registry, get_workspace
from workspace import Workspace


@pytest.fixture
def workspace_dir(tmp_path):
    folder = tmp_path / "workspace"
    folder.mkdir()
    return folder


@pytest.fixture
def history(tmp_path):
    return PipelineHistory(tmp_path / "data")


@pytest.fixture
def registry(tmp_path):
    return PlatformRegistry(tmp_path / "platforms")


@pytest.fixture(autouse=True)
def isolated_storage(workspace_dir, history, registry):
    """Every test gets its own workspace folder, history, published
    platforms and generation jobs, so none of them reads or writes the service's real ones."""
    main.app.dependency_overrides[get_workspace] = lambda: Workspace(workspace_dir)
    main.app.dependency_overrides[get_history] = lambda: history
    main.app.dependency_overrides[get_registry] = lambda: registry
    jobs = GenerationJobs()
    main.app.dependency_overrides[get_jobs] = lambda: jobs
    yield
    main.app.dependency_overrides.clear()
