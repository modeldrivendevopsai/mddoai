"""Shared pytest fixtures for psm_agent's own test suite."""
import atexit
import os
import shutil
import tempfile
from pathlib import Path

import pytest

# Before the service's modules are imported, which read their settings once.
# Both folders are required settings. The prompt config folder is a copy of the
# starting one the repository ships, because several tests exist to prove the
# real default config works and none of them may write into the committed files. Uploads go to a folder of their own, never one a real
# stack was given.
_AI_DIR = Path(__file__).resolve().parents[2]
_session_dir = Path(tempfile.mkdtemp(prefix="psm-agent-tests-"))
atexit.register(shutil.rmtree, _session_dir, ignore_errors=True)
shutil.copytree(_AI_DIR / "data" / "prompts" / "psm", _session_dir / "prompts")
os.environ["PSM_PROMPT_CONFIG_DIR"] = str(_session_dir / "prompts")
os.environ["PSM_ATTACHMENT_UPLOADS_DIR"] = str(_session_dir / "attachments")

import prompt_paths  # noqa: E402


@pytest.fixture
def isolated_prompt_config_dir(tmp_path, monkeypatch):
    """Redirects every real prompt-config read/write to a fresh tmp_path,
    for a test that wants to freely create/mutate/delete configs without
    touching the prompt folder the suite starts from.

    Deliberately NOT autouse: unlike integration_runner's own RUNS_DIR
    isolation fixture (safe to apply blanket, no test there depends on
    pre-existing on-disk content), several of this service's own tests
    (test_generation.py, test_comparison.py) exist specifically to prove
    the real, shipped default prompt config integrates correctly - forcing
    isolation on those would defeat their entire point. Request this
    fixture by name only in a test that genuinely needs a clean sandbox.

    Patching prompt_paths.PROMPT_CONFIG_DIR itself is enough: generation.py,
    comparison.py, and routes/prompt_config.py all reference it via
    module-qualified access (`prompt_paths.PROMPT_CONFIG_DIR`), not a bare
    `from prompt_paths import PROMPT_CONFIG_DIR`, specifically so this one
    patch reaches every one of them - a bare import would each bind its
    own separate copy at import time, silently unaffected by this."""
    monkeypatch.setattr(prompt_paths, "PROMPT_CONFIG_DIR", tmp_path)
    return tmp_path


@pytest.fixture
def isolated_attachment_uploads_dir(tmp_path, monkeypatch):
    """Same reasoning as isolated_prompt_config_dir above, for real file
    uploads (routes/uploads.py, available_files.py) instead of prompt
    configs - redirects to a fresh tmp_path so a test can freely upload
    without touching a real uploads folder."""
    monkeypatch.setattr(prompt_paths, "ATTACHMENT_UPLOADS_DIR", tmp_path)
    return tmp_path
