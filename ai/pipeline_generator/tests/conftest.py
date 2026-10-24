import pytest

import main
from pipeline_history import PipelineHistory
from routes.dependencies import get_history, get_workspace
from workspace import Workspace


@pytest.fixture
def workspace_dir(tmp_path):
    folder = tmp_path / "workspace"
    folder.mkdir()
    return folder


@pytest.fixture
def history(tmp_path):
    return PipelineHistory(tmp_path / "data")


@pytest.fixture(autouse=True)
def isolated_storage(workspace_dir, history):
    """Every test gets its own workspace folder and history, so none of them
    reads or writes the service's real ones."""
    main.app.dependency_overrides[get_workspace] = lambda: Workspace(workspace_dir)
    main.app.dependency_overrides[get_history] = lambda: history
    yield
    main.app.dependency_overrides.clear()
