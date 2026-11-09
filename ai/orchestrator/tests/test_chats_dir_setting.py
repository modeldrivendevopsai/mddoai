"""The orchestrator stops at start if its chats folder setting is unset or empty."""
import os
import subprocess
import sys
from pathlib import Path

import pytest

# The folder the service's own modules are imported from.
SERVICE_DIR = Path(__file__).resolve().parents[1]


@pytest.mark.parametrize("value", [None, "", "   "], ids=["unset", "empty", "blank"])
def test_the_service_does_not_start_without_a_chats_folder(value):
    env = {name: setting for name, setting in os.environ.items() if name != "ORCHESTRATOR_CHATS_DIR"}
    if value is not None:
        env["ORCHESTRATOR_CHATS_DIR"] = value

    result = subprocess.run(
        [sys.executable, "-c", "import settings"],
        cwd=SERVICE_DIR,
        env=env,
        capture_output=True,
        text=True,
    )

    assert result.returncode != 0
    expected = "KeyError: 'ORCHESTRATOR_CHATS_DIR'" if value is None else "ORCHESTRATOR_CHATS_DIR is set but empty"
    assert expected in result.stderr
