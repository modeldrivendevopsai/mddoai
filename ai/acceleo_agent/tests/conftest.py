"""Shared pytest fixtures for acceleo_agent's own test suite."""
import clients.testing_urls  # noqa: F401  (the clients' links, which a service takes from its environment)
import os
import shutil
from pathlib import Path

import pytest

from clients.journal_testing import journal_in_process

import prompt_settings  # noqa: E402

_AI_DIR = Path(__file__).resolve().parents[2]


@pytest.fixture(autouse=True)
def journal(tmp_path):
    """The real journal, in this process, over folders of this test alone. Its prompts
    folder starts as a copy of the shipped starting configs, so no test writes into the
    committed files, and a test that saves a config or uploads a file keeps it there and
    nowhere else. Yields the prompts and attachments folders."""
    prompts, attachments = tmp_path / "journal-prompts", tmp_path / "journal-attachments"
    shutil.copytree(_AI_DIR / "data" / "prompts" / prompt_settings.AGENT, prompts / prompt_settings.AGENT)
    with journal_in_process(tmp_path / "runs", tmp_path / "chats", tmp_path / "platforms", prompts, attachments):
        yield prompts, attachments


@pytest.fixture
def isolated_prompt_config_dir(journal):
    """The folder the journal keeps this agent's prompt configs in, emptied: the shipped
    starting configs are gone, so a test builds the configs it needs on disk itself."""
    folder = journal[0] / prompt_settings.AGENT
    shutil.rmtree(folder)
    folder.mkdir()
    return folder


@pytest.fixture
def isolated_attachment_uploads_dir(journal):
    """The folder the journal keeps this agent's uploaded files in, for a test that puts
    a file there directly."""
    folder = journal[1] / prompt_settings.AGENT
    folder.mkdir(parents=True, exist_ok=True)
    return folder
