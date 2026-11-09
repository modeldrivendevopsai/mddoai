"""The validator stops at start if its output folder setting is unset or empty."""
import os
import subprocess
import sys
from pathlib import Path

import pytest

SERVICE_DIR = Path(__file__).resolve().parents[1]


@pytest.mark.parametrize("value", [None, "", "   "], ids=["unset", "empty", "blank"])
def test_the_service_does_not_start_without_an_output_folder(value):
    env = {name: setting for name, setting in os.environ.items() if name != "VALIDATOR_OUTPUT_DIR"}
    if value is not None:
        env["VALIDATOR_OUTPUT_DIR"] = value

    result = subprocess.run(
        [sys.executable, "-c", "import validator_runner"],
        cwd=SERVICE_DIR,
        env=env,
        capture_output=True,
        text=True,
    )

    assert result.returncode != 0
    expected = "KeyError: 'VALIDATOR_OUTPUT_DIR'" if value is None else "VALIDATOR_OUTPUT_DIR is set but empty"
    assert expected in result.stderr
