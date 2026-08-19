"""Unit tests for validator_runner.py.

Mocks only the real boundary — subprocess.run, standing in for the Java
process — matching orchestrator's own test convention (mock the network/
process boundary, let everything else execute for real).
"""
import json
from pathlib import Path
from unittest.mock import MagicMock, patch

import pytest

from validator_runner import ValidatorInfraError, run_atl_validator, run_ecore_validator


def fake_completed_process(returncode=0, stdout="", stderr=""):
    proc = MagicMock()
    proc.returncode = returncode
    proc.stdout = stdout
    proc.stderr = stderr
    return proc


def test_builds_expected_argv_and_invokes_correct_class():
    valid_json = json.dumps({"valid": True, "mode": "reflective", "issues": []})
    with patch("validator_runner.subprocess.run", return_value=fake_completed_process(stdout=valid_json)) as mock_run:
        run_ecore_validator("<ecore/>", "model.ecore", "reflective")

    argv = mock_run.call_args.args[0]
    assert argv[0] == "java"
    assert argv[1] == "-cp"
    assert argv[2].endswith("/*")
    assert argv[3] == "main.java.mddoai.validation.ecore.EcoreValidatorCli"
    assert argv[4] == "reflective"
    assert argv[5].endswith("model.ecore")


def test_writes_content_to_temp_file_and_cleans_up_afterward():
    valid_json = json.dumps({"valid": True, "mode": "reflective", "issues": []})
    written_path_holder = {}

    def capture_and_respond(argv, **kwargs):
        written_path_holder["path"] = Path(argv[5])
        assert written_path_holder["path"].read_text(encoding="utf-8") == "<ecore-content/>"
        return fake_completed_process(stdout=valid_json)

    with patch("validator_runner.subprocess.run", side_effect=capture_and_respond):
        run_ecore_validator("<ecore-content/>", "model.ecore", "reflective")

    assert not written_path_holder["path"].exists()


def test_file_not_found_error_becomes_infra_error():
    with patch("validator_runner.subprocess.run", side_effect=FileNotFoundError("no java")):
        with pytest.raises(ValidatorInfraError, match="java executable not found"):
            run_ecore_validator("<ecore/>", "model.ecore", "reflective")


def test_timeout_becomes_infra_error():
    import subprocess as subprocess_module
    with patch("validator_runner.subprocess.run",
               side_effect=subprocess_module.TimeoutExpired(cmd="java", timeout=60)):
        with pytest.raises(ValidatorInfraError, match="timed out"):
            run_ecore_validator("<ecore/>", "model.ecore", "reflective")


def test_nonzero_exit_becomes_infra_error_with_stderr():
    with patch("validator_runner.subprocess.run",
               return_value=fake_completed_process(returncode=1, stderr="boom")):
        with pytest.raises(ValidatorInfraError, match="boom"):
            run_ecore_validator("<ecore/>", "model.ecore", "reflective")


def test_unparseable_stdout_becomes_infra_error():
    with patch("validator_runner.subprocess.run",
               return_value=fake_completed_process(stdout="not json")):
        with pytest.raises(ValidatorInfraError, match="unparseable stdout"):
            run_ecore_validator("<ecore/>", "model.ecore", "reflective")


def test_duration_ms_is_injected_into_successful_result():
    valid_json = json.dumps({"valid": True, "mode": "reflective", "issues": []})
    with patch("validator_runner.subprocess.run", return_value=fake_completed_process(stdout=valid_json)):
        result = run_ecore_validator("<ecore/>", "model.ecore", "reflective")

    assert "duration_ms" in result
    assert result["duration_ms"] >= 0
    assert result["valid"] is True


def test_atl_builds_expected_argv_and_invokes_correct_class():
    valid_json = json.dumps({"valid": True, "issues": []})
    with patch("validator_runner.subprocess.run", return_value=fake_completed_process(stdout=valid_json)) as mock_run:
        run_atl_validator("module M; create OUT : PIM from IN : SWArch;", "sample.atl")

    argv = mock_run.call_args.args[0]
    assert argv[0] == "java"
    assert argv[1] == "-cp"
    assert argv[2].endswith("/*")
    assert argv[3] == "main.java.mddoai.validation.atl.AtlValidatorCli"
    # Unlike run_ecore_validator, there's no mode arg — just the file path.
    assert argv[4].endswith("sample.atl")
    assert len(argv) == 5


def test_atl_writes_content_to_temp_file_and_cleans_up_afterward():
    valid_json = json.dumps({"valid": True, "issues": []})
    written_path_holder = {}

    def capture_and_respond(argv, **kwargs):
        written_path_holder["path"] = Path(argv[4])
        assert written_path_holder["path"].read_text(encoding="utf-8") == "module M;"
        return fake_completed_process(stdout=valid_json)

    with patch("validator_runner.subprocess.run", side_effect=capture_and_respond):
        run_atl_validator("module M;", "sample.atl")

    assert not written_path_holder["path"].exists()


def test_atl_nonzero_exit_becomes_infra_error_with_stderr():
    with patch("validator_runner.subprocess.run",
               return_value=fake_completed_process(returncode=1, stderr="boom")):
        with pytest.raises(ValidatorInfraError, match="boom"):
            run_atl_validator("module M;", "sample.atl")


def test_atl_duration_ms_is_injected_into_successful_result():
    valid_json = json.dumps({"valid": True, "issues": []})
    with patch("validator_runner.subprocess.run", return_value=fake_completed_process(stdout=valid_json)):
        result = run_atl_validator("module M;", "sample.atl")

    assert "duration_ms" in result
    assert result["duration_ms"] >= 0
    assert result["valid"] is True
