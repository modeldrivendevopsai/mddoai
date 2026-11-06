"""The prompt config and upload folders are required settings: the service does
not start without either, and an empty value is refused too (it would be read
as the current folder). Checked in a fresh process, so no other test sees a
reloaded module."""
import os
import subprocess
import sys
from pathlib import Path

import pytest

SERVICE_DIR = Path(__file__).resolve().parents[1]
# Where the shared generation_toolkit is imported from.
AI_DIR = SERVICE_DIR.parent
SETTINGS = ["ACCELEO_AGENT_PROMPT_CONFIG_DIR", "ACCELEO_AGENT_ATTACHMENT_UPLOADS_DIR"]


@pytest.mark.parametrize("name", SETTINGS)
@pytest.mark.parametrize("value", [None, "", "   "], ids=["unset", "empty", "blank"])
def test_the_service_does_not_start_without_its_folder_settings(name, value):
    env = {**os.environ, **{setting: "/some/folder" for setting in SETTINGS}, "PYTHONPATH": str(AI_DIR)}
    if value is None:
        del env[name]
    else:
        env[name] = value

    result = subprocess.run(
        [sys.executable, "-c", "import prompt_paths"],
        cwd=SERVICE_DIR,
        env=env,
        capture_output=True,
        text=True,
    )

    assert result.returncode != 0
    expected = f"KeyError: '{name}'" if value is None else f"{name} is set but empty"
    assert expected in result.stderr
