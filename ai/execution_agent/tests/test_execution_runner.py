"""Unit tests for execution_runner.py.

Mocks only the real boundary, subprocess.run, standing in for the Java
process, matching validator_agent's own test convention (mock the
network/process boundary, let everything else execute for real).
"""
import json
from pathlib import Path
from unittest.mock import MagicMock, patch

import pytest

from execution_runner import (
    ExecutionFailedError,
    ExecutionInfraError,
    run_acceleo_executor,
    run_atl_executor,
)


def fake_completed_process(returncode=0, stdout="", stderr=""):
    proc = MagicMock()
    proc.returncode = returncode
    proc.stdout = stdout
    proc.stderr = stderr
    return proc


def _write_output_file(argv, **kwargs):
    """Real AtlExecutorCli's own contract: the output file (argv[-1]) is
    where the real output actually lands, not stdout - a fake subprocess
    call has to write it too, or the real read-back afterward finds
    nothing, matching the real Java side's own documented contract."""
    Path(argv[-1]).write_text("<real-output/>", encoding="utf-8")
    return fake_completed_process(stdout='{"success":true}\n')


def test_run_atl_executor_reads_the_real_output_file_back(tmp_path):
    with patch("execution_runner.subprocess.run", side_effect=_write_output_file):
        result = run_atl_executor("atl source", "<pim/>", "<ecore/>", "OUT")

    assert result == {"output_xmi": "<real-output/>"}


def test_run_atl_executor_raises_execution_failed_for_a_real_reported_failure():
    with patch(
        "execution_runner.subprocess.run",
        return_value=fake_completed_process(stdout='{"success":false,"error":"not a real Pipeline instance"}\n'),
    ):
        with pytest.raises(ExecutionFailedError, match="not a real Pipeline instance"):
            run_atl_executor("atl source", "<pim/>", "<ecore/>", "OUT")


def test_run_atl_executor_raises_infra_error_for_a_nonzero_exit():
    with patch(
        "execution_runner.subprocess.run",
        return_value=fake_completed_process(returncode=1, stderr="AtlExecutorCli: unexpected error"),
    ):
        with pytest.raises(ExecutionInfraError, match="unexpected error"):
            run_atl_executor("atl source", "<pim/>", "<ecore/>", "OUT")


def test_run_atl_executor_raises_infra_error_when_java_is_missing():
    with patch("execution_runner.subprocess.run", side_effect=FileNotFoundError("java")):
        with pytest.raises(ExecutionInfraError, match="java executable not found"):
            run_atl_executor("atl source", "<pim/>", "<ecore/>", "OUT")


def test_run_atl_executor_raises_infra_error_for_a_generic_oserror():
    # Matches validator_runner.py's own defense-in-depth OSError case (e.g.
    # a real "Argument list too long").
    with patch("execution_runner.subprocess.run", side_effect=OSError("Argument list too long")):
        with pytest.raises(ExecutionInfraError, match="Argument list too long"):
            run_atl_executor("atl source", "<pim/>", "<ecore/>", "OUT")


def _write_generated_files(argv, **kwargs):
    output_dir = Path(argv[-1])
    (output_dir / ".gitlab-ci.yml").write_text("stages: []\n", encoding="utf-8")
    return fake_completed_process(stdout='{"success":true,"files":[".gitlab-ci.yml"]}\n')


def test_run_acceleo_executor_reads_every_real_generated_file_back():
    with patch("execution_runner.subprocess.run", side_effect=_write_generated_files):
        result = run_acceleo_executor("mtl source", "<psm/>", "<ecore/>")

    assert result == {"generated_files": {".gitlab-ci.yml": "stages: []\n"}}


def test_run_acceleo_executor_raises_execution_failed_for_a_real_reported_failure():
    with patch(
        "execution_runner.subprocess.run",
        return_value=fake_completed_process(stdout='{"success":false,"error":"target metamodel could not be loaded"}\n'),
    ):
        with pytest.raises(ExecutionFailedError, match="target metamodel could not be loaded"):
            run_acceleo_executor("mtl source", "<psm/>", "<ecore/>")


def test_run_acceleo_executor_raises_infra_error_for_unparseable_stdout():
    with patch(
        "execution_runner.subprocess.run",
        return_value=fake_completed_process(stdout="not json"),
    ):
        with pytest.raises(ExecutionInfraError, match="unparseable stdout"):
            run_acceleo_executor("mtl source", "<psm/>", "<ecore/>")
