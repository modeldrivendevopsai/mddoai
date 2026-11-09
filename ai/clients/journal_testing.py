"""For tests only: sends journal_client's requests to the real journal app,
running in the same process over temporary folders, so a service's tests use
the journal as it really behaves and not a stand-in.

The journal's folder settings are required, and are read when its modules are
first imported, so this module sets them before it imports anything of the
journal. A caller needs no setting of its own."""
import atexit
import os
import shutil
import tempfile
from collections.abc import Iterator
from contextlib import contextmanager
from pathlib import Path
from unittest.mock import MagicMock, patch

_session_dir: Path | None = None
# The folders of the journal in use, so it can be started again over them.
_folders: tuple[Path, Path, Path] | None = None


def _import_journal():
    global _session_dir
    if _session_dir is None:
        _session_dir = Path(tempfile.mkdtemp(prefix="journal-session-"))
        atexit.register(shutil.rmtree, _session_dir, ignore_errors=True)
    for name, folder in (("JOURNAL_RUNS_DIR", "runs"), ("JOURNAL_CHATS_DIR", "chats"), ("JOURNAL_PLATFORMS_DIR", "platforms")):
        os.environ.setdefault(name, str(_session_dir / folder))
    from journal import dependencies
    from journal.main import app

    return dependencies, app


@contextmanager
def journal_in_process(runs_dir: Path, chats_dir: Path, platforms_dir: Path) -> Iterator[None]:
    """Until the block ends, journal_client talks to the real journal app, which
    keeps its files in these folders and remembers nothing from before."""
    from fastapi.testclient import TestClient

    from clients import journal_client

    global _folders
    dependencies, app = _import_journal()
    platforms_dir.mkdir(parents=True, exist_ok=True)
    _folders = (runs_dir, chats_dir, platforms_dir)
    dependencies.use_folders(*_folders)
    test_client = TestClient(app)

    def request(method: str, url: str, **kwargs):
        kwargs.pop("timeout", None)
        return test_client.request(method, url.removeprefix(journal_client.JOURNAL_URL), **kwargs)

    fake_httpx = MagicMock()
    fake_httpx.request.side_effect = request
    with patch.object(journal_client, "httpx", fake_httpx):
        yield
    dependencies.use_folders(*_folders)


def restart_journal() -> None:
    """Starts the journal again over the same folders: its files stay and
    what it remembered of them is gone, as after a restart of the service."""
    from journal import dependencies

    dependencies.use_folders(*_folders)
