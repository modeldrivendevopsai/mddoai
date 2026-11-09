import atexit
import os
import shutil
import tempfile
from pathlib import Path

# Required settings, set before the service modules read them.
_session_dir = Path(tempfile.mkdtemp(prefix="generator-tests-"))
atexit.register(shutil.rmtree, _session_dir, ignore_errors=True)
os.environ["GENERATOR_WORKSPACE_DIR"] = str(_session_dir / "workspace")
os.environ["GENERATOR_WORKSPACE_LABEL"] = "the test workspace"
os.environ["GENERATOR_PIPELINES_DIR"] = str(_session_dir / "pipelines")

import pytest  # noqa: E402

import main  # noqa: E402
from clients.journal_testing import journal_in_process
from pipeline_history import PipelineHistory
from platform_registry import PlatformRegistry
from generation_jobs import GenerationJobs
from routes.dependencies import get_history, get_jobs, get_registry, get_workspace
from workspace import Workspace

# The platforms that ship with the stack.
COMMITTED_PLATFORMS = Path(__file__).resolve().parents[2] / "data" / "platforms"


@pytest.fixture
def workspace_dir(tmp_path):
    folder = tmp_path / "workspace"
    folder.mkdir()
    return folder


@pytest.fixture
def history(tmp_path):
    return PipelineHistory(tmp_path / "data")


@pytest.fixture
def platforms_dir(tmp_path):
    """A copy of the committed platforms folder, so a test can add a platform
    or damage one without touching the committed files."""
    shutil.copytree(COMMITTED_PLATFORMS, tmp_path / "platforms")
    return tmp_path / "platforms"


@pytest.fixture(autouse=True)
def journal(tmp_path, platforms_dir):
    """The real journal, over the copy of the platforms and folders of its own."""
    with journal_in_process(tmp_path / "runs", tmp_path / "chats", platforms_dir):
        yield


@pytest.fixture
def registry(journal):
    return PlatformRegistry()


@pytest.fixture(autouse=True)
def isolated_storage(workspace_dir, history, registry):
    """Every test gets its own workspace folder, history, platforms and
    generation jobs, so none of them reads or writes the service's real ones."""
    main.app.dependency_overrides[get_workspace] = lambda: Workspace(workspace_dir)
    main.app.dependency_overrides[get_history] = lambda: history
    main.app.dependency_overrides[get_registry] = lambda: registry
    # Runs each generation at once, so a test's GET finds it finished.
    jobs = GenerationJobs(max_finished=10, run=lambda task: task())
    main.app.dependency_overrides[get_jobs] = lambda: jobs
    yield
    main.app.dependency_overrides.clear()
