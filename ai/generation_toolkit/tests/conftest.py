"""Pytest setup for this suite: the clients need their links, which a service takes from its
environment, and every test gets a real journal over folders of its own."""
import clients.testing_urls  # noqa: F401
import pytest

from clients.journal_testing import folders_in, journal_in_process
from helpers import AGENT


@pytest.fixture(autouse=True)
def journal(tmp_path):
    """The real journal, in this process, over folders of this test alone. Yields the
    prompts and attachments folders."""
    folders = folders_in(tmp_path / "journal")
    with journal_in_process(folders):
        yield folders.prompts, folders.attachments


@pytest.fixture
def config_dir(journal):
    """The folder the journal keeps the prompt configs of the test's agent in, for a test
    that puts a shipped config there or reads what was kept."""
    folder = journal[0] / AGENT
    folder.mkdir(parents=True, exist_ok=True)
    return folder
