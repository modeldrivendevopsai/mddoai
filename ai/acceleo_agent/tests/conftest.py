"""Shared pytest fixtures for acceleo_agent's own test suite."""
import pytest

import prompt_paths


@pytest.fixture
def isolated_prompt_config_dir(tmp_path, monkeypatch):
    """Redirects every real prompt-config read/write to a fresh tmp_path,
    for a test that wants to freely create/mutate/delete configs without
    touching the real, git-committed ai/acceleo_agent/prompts/ directory.

    Deliberately NOT autouse: test_generation.py exists specifically to
    prove the real, shipped default prompt config integrates correctly -
    forcing isolation there would defeat its entire point.

    Patching prompt_paths.PROMPT_CONFIG_DIR itself is enough: generation.py
    and routes/prompt_config.py both reference it via module-qualified
    access (prompt_paths.PROMPT_CONFIG_DIR), not a bare
    `from prompt_paths import PROMPT_CONFIG_DIR`, specifically so this one
    patch reaches both - a bare import would each bind its own separate
    copy at import time, silently unaffected by this."""
    monkeypatch.setattr(prompt_paths, "PROMPT_CONFIG_DIR", tmp_path)
    return tmp_path


@pytest.fixture
def isolated_attachment_uploads_dir(tmp_path, monkeypatch):
    """Same reasoning as isolated_prompt_config_dir above, for real file
    uploads (routes/uploads.py, available_files.py) instead of prompt
    configs - redirects to a fresh tmp_path so a test can freely upload
    without touching the real ai/acceleo_agent/attachments/ directory."""
    monkeypatch.setattr(prompt_paths, "ATTACHMENT_UPLOADS_DIR", tmp_path)
    return tmp_path
