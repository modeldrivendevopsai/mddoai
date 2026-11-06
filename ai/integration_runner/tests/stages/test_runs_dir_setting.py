"""The runs folder is a required setting: the service does not start without
it, an empty value is refused too (it would be read as the current folder), and
it never picks a folder of its own. Checked in a fresh process, so no other test
sees a reloaded module."""
import os
import subprocess
import sys
from pathlib import Path

import pytest

# The folder the service's own package and the shared clients are imported from.
AI_DIR = Path(__file__).resolve().parents[3]


@pytest.mark.parametrize("value", [None, "", "   "], ids=["unset", "empty", "blank"])
def test_the_service_does_not_start_without_a_runs_folder(value):
    env = {name: setting for name, setting in os.environ.items() if name != "INTEGRATION_RUNNER_RUNS_DIR"}
    if value is not None:
        env["INTEGRATION_RUNNER_RUNS_DIR"] = value

    result = subprocess.run(
        [sys.executable, "-c", "import integration_runner.stages._validation"],
        cwd=AI_DIR,
        env=env,
        capture_output=True,
        text=True,
    )

    assert result.returncode != 0
    expected = "KeyError: 'INTEGRATION_RUNNER_RUNS_DIR'" if value is None else "INTEGRATION_RUNNER_RUNS_DIR is set but empty"
    assert expected in result.stderr
