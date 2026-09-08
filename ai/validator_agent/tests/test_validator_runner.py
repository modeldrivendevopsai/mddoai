"""Unit tests for validator_runner.py.

Mocks only the real boundary — subprocess.run, standing in for the Java
process — matching orchestrator's own test convention (mock the network/
process boundary, let everything else execute for real).
"""
import json
from pathlib import Path
from unittest.mock import MagicMock, patch

import pytest

from validator_runner import (
    ValidatorInfraError,
    _scoped_output_env,
    run_acceleo_validator,
    run_atl_validator,
    run_ecore_validator,
)


def fake_completed_process(returncode=0, stdout="", stderr=""):
    proc = MagicMock()
    proc.returncode = returncode
    proc.stdout = stdout
    proc.stderr = stderr
    return proc


def test_scoped_output_env_is_none_when_run_id_is_none():
    # No run_id means no run tree to scope by at all - unaffected by
    # whatever stage/attempt happen to be passed.
    assert _scoped_output_env(None) is None
    assert _scoped_output_env(None, "atl", "attempt_1") is None


def test_scoped_output_env_joins_only_run_id_when_stage_and_attempt_are_omitted():
    with patch.dict("validator_runner.os.environ", {"VALIDATOR_OUTPUT_DIR": "/validator-output"}):
        env = _scoped_output_env("run-123")

    assert Path(env["VALIDATOR_OUTPUT_DIR"]).as_posix() == "/validator-output/run-123"


def test_scoped_output_env_joins_run_id_and_stage_when_attempt_is_omitted():
    # Not the real calling convention (atl_stage/acceleo_stage always pass
    # both together or neither), but the function itself shouldn't require
    # that pairing to behave sensibly.
    with patch.dict("validator_runner.os.environ", {"VALIDATOR_OUTPUT_DIR": "/validator-output"}):
        env = _scoped_output_env("run-123", "atl")

    assert Path(env["VALIDATOR_OUTPUT_DIR"]).as_posix() == "/validator-output/run-123/atl"


def test_scoped_output_env_joins_run_id_stage_and_attempt_when_all_given():
    # This is the real calling convention: atl_stage/acceleo_stage forward
    # their own stage name and reserved attempt name together, so the
    # compiled output nests inside runs/<run_id>/<stage>/attempt_N/ exactly
    # like persist_attempt()'s own artifact and result.json do - not merely
    # scoped by run_id, and not missing the stage segment either.
    with patch.dict("validator_runner.os.environ", {"VALIDATOR_OUTPUT_DIR": "/validator-output"}):
        env = _scoped_output_env("run-123", "atl", "attempt_2")

    assert Path(env["VALIDATOR_OUTPUT_DIR"]).as_posix() == "/validator-output/run-123/atl/attempt_2"


def test_run_cli_wraps_a_generic_oserror_as_infra_error():
    # Not FileNotFoundError (java missing) or TimeoutExpired (already
    # covered elsewhere) - a plain OSError, matching what a real
    # "Argument list too long" (E2BIG) failure raises when the OS's own
    # execve() argv+envp size limit is exceeded, confirmed directly against
    # a real Linux container. main.py's own _ID_MAX_LENGTH cap on
    # run_id/stage/attempt is the primary defense; this is the
    # defense-in-depth half, so anything else that ever grows the real
    # environment past that limit still fails clean instead of as an
    # unhandled 500.
    with patch("validator_runner.subprocess.run", side_effect=OSError("Argument list too long")):
        with pytest.raises(ValidatorInfraError, match="Argument list too long"):
            run_ecore_validator("<ecore/>", "model.ecore", "reflective")


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


def test_generated_source_path_is_none_when_java_omits_it():
    # Reflective mode (and any codegen attempt that generated nothing) never
    # has this key in the Java-side JSON at all.
    valid_json = json.dumps({"valid": True, "mode": "reflective", "issues": []})
    with patch("validator_runner.subprocess.run", return_value=fake_completed_process(stdout=valid_json)):
        result = run_ecore_validator("<ecore/>", "model.ecore", "reflective")

    assert result["generated_source_path"] is None


def test_generated_source_path_is_translated_from_camel_case_java_key():
    codegen_json = json.dumps({
        "valid": True, "mode": "codegen", "issues": [],
        "generatedOutputPath": "/validator-output/ecore-validate-abc123",
    })
    with patch("validator_runner.subprocess.run", return_value=fake_completed_process(stdout=codegen_json)):
        result = run_ecore_validator("<ecore/>", "model.ecore", "codegen")

    assert result["generated_source_path"] == "/validator-output/ecore-validate-abc123"
    assert "generatedOutputPath" not in result


def test_codegen_scopes_java_output_directory_to_run_id():
    codegen_json = json.dumps({"valid": True, "mode": "codegen", "issues": []})
    with patch.dict("validator_runner.os.environ", {"VALIDATOR_OUTPUT_DIR": "/validator-output"}), \
         patch("validator_runner.subprocess.run", return_value=fake_completed_process(stdout=codegen_json)) as mock_run:
        run_ecore_validator("<ecore/>", "model.ecore", "codegen", "run-123")

    output_dir = Path(mock_run.call_args.kwargs["env"]["VALIDATOR_OUTPUT_DIR"])
    assert output_dir.as_posix() == "/validator-output/run-123"


def test_codegen_scopes_java_output_directory_to_run_id_stage_and_attempt_when_all_given():
    codegen_json = json.dumps({"valid": True, "mode": "codegen", "issues": []})
    with patch.dict("validator_runner.os.environ", {"VALIDATOR_OUTPUT_DIR": "/validator-output"}), \
         patch("validator_runner.subprocess.run", return_value=fake_completed_process(stdout=codegen_json)) as mock_run:
        run_ecore_validator("<ecore/>", "model.ecore", "codegen", "run-123", "pim", "attempt_2")

    output_dir = Path(mock_run.call_args.kwargs["env"]["VALIDATOR_OUTPUT_DIR"])
    assert output_dir.as_posix() == "/validator-output/run-123/pim/attempt_2"


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


def test_atl_generated_source_path_is_none_when_nothing_produced():
    # A broken-enough source produces no .asm at all - AtlValidator's own
    # keepOutput check (target.exists()) is false, so Java never reports a path.
    invalid_json = json.dumps({"valid": False, "issues": []})
    with patch("validator_runner.subprocess.run", return_value=fake_completed_process(stdout=invalid_json)):
        result = run_atl_validator("garbage", "sample.atl")

    assert result["generated_source_path"] is None


def test_atl_generated_source_path_is_translated_from_camel_case_java_key():
    compiled_json = json.dumps({
        "valid": True, "issues": [],
        "generatedOutputPath": "/validator-output/atl-validate-abc123/sample.asm",
    })
    with patch("validator_runner.subprocess.run", return_value=fake_completed_process(stdout=compiled_json)):
        result = run_atl_validator("module M;", "sample.atl")

    assert result["generated_source_path"] == "/validator-output/atl-validate-abc123/sample.asm"
    assert "generatedOutputPath" not in result


def test_atl_scopes_java_output_directory_to_run_id():
    valid_json = json.dumps({"valid": True, "issues": []})
    with patch.dict("validator_runner.os.environ", {"VALIDATOR_OUTPUT_DIR": "/validator-output"}), \
         patch("validator_runner.subprocess.run", return_value=fake_completed_process(stdout=valid_json)) as mock_run:
        run_atl_validator("module M;", "sample.atl", "run-123")

    output_dir = Path(mock_run.call_args.kwargs["env"]["VALIDATOR_OUTPUT_DIR"])
    assert output_dir.as_posix() == "/validator-output/run-123"


def test_atl_scopes_java_output_directory_to_run_id_stage_and_attempt_when_all_given():
    # This is the real calling convention atl_stage uses: stage="atl" and
    # its own reserved attempt name forwarded together, so the compiled
    # .asm nests inside runs/<run_id>/atl/attempt_N/, not merely under
    # run_id and not missing the stage segment.
    valid_json = json.dumps({"valid": True, "issues": []})
    with patch.dict("validator_runner.os.environ", {"VALIDATOR_OUTPUT_DIR": "/validator-output"}), \
         patch("validator_runner.subprocess.run", return_value=fake_completed_process(stdout=valid_json)) as mock_run:
        run_atl_validator("module M;", "sample.atl", "run-123", "atl", "attempt_3")

    output_dir = Path(mock_run.call_args.kwargs["env"]["VALIDATOR_OUTPUT_DIR"])
    assert output_dir.as_posix() == "/validator-output/run-123/atl/attempt_3"


def test_acceleo_builds_expected_argv_and_invokes_correct_class():
    valid_json = json.dumps({"valid": True, "issues": []})
    with patch("validator_runner.subprocess.run", return_value=fake_completed_process(stdout=valid_json)) as mock_run:
        run_acceleo_validator("[module generate('http://example.com/mm')]", "generate.mtl")

    argv = mock_run.call_args.args[0]
    assert argv[0] == "java"
    assert argv[1] == "-cp"
    assert argv[2].endswith("/*")
    assert argv[3] == "main.java.mddoai.validation.acceleo.AcceleoValidatorCli"
    # Unlike run_ecore_validator, there's no mode arg — just the file path.
    assert argv[4].endswith("generate.mtl")
    assert len(argv) == 5


def test_acceleo_writes_content_to_temp_file_and_cleans_up_afterward():
    valid_json = json.dumps({"valid": True, "issues": []})
    written_path_holder = {}

    def capture_and_respond(argv, **kwargs):
        written_path_holder["path"] = Path(argv[4])
        assert written_path_holder["path"].read_text(encoding="utf-8") == "[module generate('http://x')]"
        return fake_completed_process(stdout=valid_json)

    with patch("validator_runner.subprocess.run", side_effect=capture_and_respond):
        run_acceleo_validator("[module generate('http://x')]", "generate.mtl")

    assert not written_path_holder["path"].exists()


def test_acceleo_nonzero_exit_becomes_infra_error_with_stderr():
    with patch("validator_runner.subprocess.run",
               return_value=fake_completed_process(returncode=1, stderr="boom")):
        with pytest.raises(ValidatorInfraError, match="boom"):
            run_acceleo_validator("[module generate('http://x')]", "generate.mtl")


def test_acceleo_duration_ms_is_injected_into_successful_result():
    valid_json = json.dumps({"valid": True, "issues": []})
    with patch("validator_runner.subprocess.run", return_value=fake_completed_process(stdout=valid_json)):
        result = run_acceleo_validator("[module generate('http://x')]", "generate.mtl")

    assert "duration_ms" in result
    assert result["duration_ms"] >= 0
    assert result["valid"] is True


def test_acceleo_generated_source_path_is_none_when_nothing_produced():
    # A broken-enough module produces nothing in outputDir at all -
    # AcceleoValidator's own keepOutput check (hasAnyFile) is false.
    invalid_json = json.dumps({"valid": False, "issues": []})
    with patch("validator_runner.subprocess.run", return_value=fake_completed_process(stdout=invalid_json)):
        result = run_acceleo_validator("garbage", "generate.mtl")

    assert result["generated_source_path"] is None


def test_acceleo_generated_source_path_is_translated_from_camel_case_java_key():
    compiled_json = json.dumps({
        "valid": True, "issues": [],
        "generatedOutputPath": "/validator-output/acceleo-validate-abc123/out",
    })
    with patch("validator_runner.subprocess.run", return_value=fake_completed_process(stdout=compiled_json)):
        result = run_acceleo_validator("[module generate('http://x')]", "generate.mtl")

    assert result["generated_source_path"] == "/validator-output/acceleo-validate-abc123/out"
    assert "generatedOutputPath" not in result


def test_acceleo_scopes_java_output_directory_to_run_id():
    valid_json = json.dumps({"valid": True, "issues": []})
    with patch.dict("validator_runner.os.environ", {"VALIDATOR_OUTPUT_DIR": "/validator-output"}), \
         patch("validator_runner.subprocess.run", return_value=fake_completed_process(stdout=valid_json)) as mock_run:
        run_acceleo_validator("[module generate('http://x')]", "generate.mtl", "run-123")

    output_dir = Path(mock_run.call_args.kwargs["env"]["VALIDATOR_OUTPUT_DIR"])
    assert output_dir.as_posix() == "/validator-output/run-123"


def test_acceleo_scopes_java_output_directory_to_run_id_stage_and_attempt_when_all_given():
    # Real calling convention acceleo_stage uses: stage="acceleo" plus its
    # own reserved attempt name, nesting inside runs/<run_id>/acceleo/attempt_N/.
    valid_json = json.dumps({"valid": True, "issues": []})
    with patch.dict("validator_runner.os.environ", {"VALIDATOR_OUTPUT_DIR": "/validator-output"}), \
         patch("validator_runner.subprocess.run", return_value=fake_completed_process(stdout=valid_json)) as mock_run:
        run_acceleo_validator("[module generate('http://x')]", "generate.mtl", "run-123", "acceleo", "attempt_4")

    output_dir = Path(mock_run.call_args.kwargs["env"]["VALIDATOR_OUTPUT_DIR"])
    assert output_dir.as_posix() == "/validator-output/run-123/acceleo/attempt_4"
