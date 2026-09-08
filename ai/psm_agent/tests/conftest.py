"""Shared pytest fixtures for psm_agent's own test suite."""
import pytest

import prompt_paths


@pytest.fixture
def isolated_prompt_config_dir(tmp_path, monkeypatch):
    """Redirects every real prompt-config read/write to a fresh tmp_path,
    for a test that wants to freely create/mutate/delete configs without
    touching the real, git-committed ai/psm_agent/prompts/ directory.

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
