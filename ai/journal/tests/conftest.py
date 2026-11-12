"""Shared pytest configuration for the journal's tests."""
import atexit
import os
import shutil
import tempfile
from pathlib import Path

import pytest

# The required folder settings, set before the service's modules are imported.
_session_dir = Path(tempfile.mkdtemp(prefix="journal-tests-"))
atexit.register(shutil.rmtree, _session_dir, ignore_errors=True)
os.environ["JOURNAL_RUNS_DIR"] = str(_session_dir / "runs")
os.environ["JOURNAL_CHATS_DIR"] = str(_session_dir / "chats")
os.environ["JOURNAL_PLATFORMS_DIR"] = str(_session_dir / "platforms")
os.environ["JOURNAL_PROMPTS_DIR"] = str(_session_dir / "prompts")
os.environ["JOURNAL_ATTACHMENTS_DIR"] = str(_session_dir / "attachments")
os.environ["JOURNAL_PIPELINES_DIR"] = str(_session_dir / "pipelines")

from fastapi.testclient import TestClient  # noqa: E402

from journal import dependencies  # noqa: E402
from journal.main import app  # noqa: E402


@pytest.fixture
def folders(tmp_path):
    """The folders of one test, one of each kind, each its own."""
    folders = dependencies.Folders.under(tmp_path)
    folders.platforms.mkdir()
    return folders


@pytest.fixture
def client(folders):
    """The real app, over its own temporary folders."""
    dependencies.use_folders(folders)
    yield TestClient(app)
    dependencies.use_folders(folders)
