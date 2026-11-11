"""Unit tests for validator_runner.py.

Mocks only the real boundary — subprocess.run, standing in for the Java
process — matching orchestrator's own test convention (mock the network/
process boundary, let everything else execute for real).
"""
import json
import io
import zipfile
from pathlib import Path
from unittest.mock import MagicMock, patch

import httpx
import pytest

from clients import journal_client
from validator_runner import (
    ValidatorInfraError,
    run_acceleo_validator,
    run_atl_validator,
    run_ecore_validator,
    run_swarch_validator,
)


def fake_completed_process(returncode=0, stdout="", stderr=""):
    proc = MagicMock()
    proc.returncode = returncode
    proc.stdout = stdout
    proc.stderr = stderr
    return proc


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


def test_atl_appends_the_target_metamodel_path_when_given():
    # Without this, AtlValidator's own execution smoke test never runs at
    # all (see its own pimSampleInstancePath comment) - a real runtime-only
    # ATL failure would then only ever surface later, at the pipeline's own
    # final execution stage, with no retries left.
    valid_json = json.dumps({"valid": True, "issues": []})
    captured = {}

    def capture_and_respond(argv, **kwargs):
        captured["argv"] = argv
        captured["ecore_content"] = Path(argv[5]).read_text(encoding="utf-8")
        return fake_completed_process(stdout=valid_json)

    with patch("validator_runner.subprocess.run", side_effect=capture_and_respond):
        run_atl_validator("module M;", "sample.atl", metamodel_ecore="<ecore:EPackage/>")

    assert len(captured["argv"]) == 6
    assert captured["argv"][5].endswith(".ecore")
    assert captured["ecore_content"] == "<ecore:EPackage/>"


def test_atl_omits_the_metamodel_arg_when_not_given():
    valid_json = json.dumps({"valid": True, "issues": []})
    with patch("validator_runner.subprocess.run", return_value=fake_completed_process(stdout=valid_json)) as mock_run:
        run_atl_validator("module M;", "sample.atl")

    assert len(mock_run.call_args.args[0]) == 5


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


def test_acceleo_appends_the_target_metamodel_path_when_given():
    # Without this, AcceleoValidator can only ever resolve the metamodels
    # main/'s own EMFUtils.init() hardcodes (PIM, SWArch, GitLab) - every
    # other platform's real generated template always fails with "the
    # metamodel couldn't be resolved", regardless of how correct it is.
    valid_json = json.dumps({"valid": True, "issues": []})
    captured = {}

    def capture_and_respond(argv, **kwargs):
        captured["argv"] = argv
        captured["ecore_content"] = Path(argv[5]).read_text(encoding="utf-8")
        return fake_completed_process(stdout=valid_json)

    with patch("validator_runner.subprocess.run", side_effect=capture_and_respond):
        run_acceleo_validator(
            "[module generate('http://example.com/mm')]", "generate.mtl", metamodel_ecore="<ecore:EPackage/>"
        )

    assert len(captured["argv"]) == 6
    assert captured["argv"][5].endswith(".ecore")
    assert captured["ecore_content"] == "<ecore:EPackage/>"


def test_acceleo_omits_the_metamodel_arg_when_not_given():
    valid_json = json.dumps({"valid": True, "issues": []})
    with patch("validator_runner.subprocess.run", return_value=fake_completed_process(stdout=valid_json)) as mock_run:
        run_acceleo_validator("[module generate('http://example.com/mm')]", "generate.mtl")

    assert len(mock_run.call_args.args[0]) == 5


def test_acceleo_appends_the_atl_source_path_after_the_metamodel_when_both_given():
    # Without this, AcceleoValidator's own execution smoke test never has a
    # real PSM model instance to actually generate from (it only exists by
    # actually running this same atl_source first - see
    # AcceleoValidator.java's own three-arg validate() comment).
    valid_json = json.dumps({"valid": True, "issues": []})
    captured = {}

    def capture_and_respond(argv, **kwargs):
        captured["argv"] = argv
        captured["atl_content"] = Path(argv[6]).read_text(encoding="utf-8")
        return fake_completed_process(stdout=valid_json)

    with patch("validator_runner.subprocess.run", side_effect=capture_and_respond):
        run_acceleo_validator(
            "[module generate('http://example.com/mm')]", "generate.mtl",
            metamodel_ecore="<ecore:EPackage/>", atl_source="module M; ...",
        )

    assert len(captured["argv"]) == 7
    assert captured["argv"][6].endswith(".atl")
    assert captured["atl_content"] == "module M; ..."


def test_acceleo_omits_the_atl_source_arg_without_a_metamodel_even_if_given():
    # There's no target metamodel to run either executor against without
    # metamodel_ecore too, so atl_source alone is meaningless and ignored -
    # matches run_acceleo_validator's own documented behavior.
    valid_json = json.dumps({"valid": True, "issues": []})
    with patch("validator_runner.subprocess.run", return_value=fake_completed_process(stdout=valid_json)) as mock_run:
        run_acceleo_validator(
            "[module generate('http://example.com/mm')]", "generate.mtl", atl_source="module M; ..."
        )

    assert len(mock_run.call_args.args[0]) == 5


def test_swarch_builds_expected_argv_and_invokes_correct_class():
    valid_json = json.dumps({"valid": True, "issues": []})
    with patch("validator_runner.subprocess.run", return_value=fake_completed_process(stdout=valid_json)) as mock_run:
        run_swarch_validator("<swarch/>", "model.swarch")

    argv = mock_run.call_args.args[0]
    assert argv[0] == "java"
    assert argv[1] == "-cp"
    assert argv[2].endswith("/*")
    assert argv[3] == "main.java.mddoai.validation.swarch.SwarchValidatorCli"
    assert argv[4].endswith("model.swarch")
    assert len(argv) == 5


def test_swarch_writes_content_to_temp_file_and_cleans_up_afterward():
    valid_json = json.dumps({"valid": True, "issues": []})
    written_path_holder = {}

    def capture_and_respond(argv, **kwargs):
        written_path_holder["path"] = Path(argv[4])
        assert written_path_holder["path"].read_text(encoding="utf-8") == "<swarch/>"
        return fake_completed_process(stdout=valid_json)

    with patch("validator_runner.subprocess.run", side_effect=capture_and_respond):
        run_swarch_validator("<swarch/>", "model.swarch")

    assert not written_path_holder["path"].exists()


def test_swarch_never_uses_the_callers_filename_as_the_temp_file_name():
    # filename is caller-supplied: whatever it is (a traversal, a bare "..",
    # an unusual extension) must not decide the temp file's name.
    valid_json = json.dumps({"valid": True, "issues": []})
    for hostile in ("../../etc/evil.swarch", "..", "notes.txt", "A.SWARCH", ""):
        with patch("validator_runner.subprocess.run", return_value=fake_completed_process(stdout=valid_json)) as mock_run:
            run_swarch_validator("<swarch/>", hostile)

        assert Path(mock_run.call_args.args[0][4]).name == "model.swarch", hostile


def test_swarch_nonzero_exit_becomes_infra_error_with_stderr():
    with patch("validator_runner.subprocess.run",
               return_value=fake_completed_process(returncode=1, stderr="boom")):
        with pytest.raises(ValidatorInfraError, match="boom"):
            run_swarch_validator("<swarch/>", "model.swarch")


def test_swarch_duration_ms_is_injected_into_successful_result():
    valid_json = json.dumps({"valid": True, "issues": []})
    with patch("validator_runner.subprocess.run", return_value=fake_completed_process(stdout=valid_json)):
        result = run_swarch_validator("<swarch/>", "model.swarch")

    assert result["duration_ms"] >= 0
    assert result["valid"] is True


# --- what a validator compiled is kept with its attempt ---------------------------------------

ATL_OK = json.dumps({"valid": True, "issues": []})


def _compiling(stdout, files):
    """A stand-in for the Java process: it compiles `files` into the folder it is told to."""
    seen = {}

    def run(argv, **kwargs):
        output_dir = Path(kwargs["env"]["VALIDATOR_OUTPUT_DIR"])
        seen["dir"] = output_dir
        seen["existed"] = output_dir.is_dir()
        for name, content in files.items():
            (output_dir / name).parent.mkdir(parents=True, exist_ok=True)
            (output_dir / name).write_text(content, encoding="utf-8")
        return fake_completed_process(stdout=stdout)

    return run, seen


def _zip_of(run_id, stage, attempt):
    kept = journal_client.get_compiled_output(run_id, stage, attempt)
    assert kept is not None, "nothing was kept"
    with zipfile.ZipFile(io.BytesIO(kept)) as archive:
        return {name: archive.read(name).decode("utf-8") for name in archive.namelist()}


@pytest.mark.parametrize(
    "call",
    [
        lambda: run_ecore_validator("<ecore/>", "model.ecore", "codegen"),
        lambda: run_ecore_validator("<ecore/>", "model.ecore", "reflective"),
        lambda: run_atl_validator("module M;", "sample.atl"),
        lambda: run_acceleo_validator("[module generate('http://x')]", "generate.mtl"),
        lambda: run_swarch_validator("<swarch/>", "model.swarch"),
    ],
    ids=["ecore-codegen", "ecore-reflective", "atl", "acceleo", "swarch"],
)
def test_every_validator_compiles_into_a_scratch_folder_that_is_gone_afterwards(call):
    run, seen = _compiling(ATL_OK, {})
    with patch("validator_runner.subprocess.run", side_effect=run):
        call()

    assert seen["existed"] is True
    assert not seen["dir"].exists()
    assert not seen["dir"].parent.exists()


def test_what_atl_compiled_is_kept_as_one_zip_with_the_attempt():
    attempt = journal_client.reserve_attempt("run-1", "atl")
    run, _ = _compiling(
        json.dumps({"valid": True, "issues": [], "generatedOutputPath": "/gone/atl-validate-abc"}),
        {"atl-validate-abc/sample.asm": "bytecode", "atl-validate-abc/nested/extra.txt": "more"},
    )
    with patch("validator_runner.subprocess.run", side_effect=run):
        result = run_atl_validator("module M;", "sample.atl", "run-1", "atl", attempt)

    assert result["compiled_output_kept"] is True
    assert "generatedOutputPath" not in result
    assert _zip_of("run-1", "atl", attempt) == {
        "atl-validate-abc/sample.asm": "bytecode",
        "atl-validate-abc/nested/extra.txt": "more",
    }


def test_what_ecore_codegen_and_acceleo_compiled_is_kept_too():
    ecore_attempt = journal_client.reserve_attempt("run-1", "psm")
    run, _ = _compiling(json.dumps({"valid": True, "mode": "codegen", "issues": []}), {"src-gen/A.java": "class A {}"})
    with patch("validator_runner.subprocess.run", side_effect=run):
        ecore = run_ecore_validator("<ecore/>", "model.ecore", "codegen", "run-1", "psm", ecore_attempt)
    acceleo_attempt = journal_client.reserve_attempt("run-1", "acceleo")
    run, _ = _compiling(ATL_OK, {"generate.emtl": "module"})
    with patch("validator_runner.subprocess.run", side_effect=run):
        acceleo = run_acceleo_validator("[module generate('http://x')]", "generate.mtl", "run-1", "acceleo", acceleo_attempt)

    assert ecore["compiled_output_kept"] is True and acceleo["compiled_output_kept"] is True
    assert _zip_of("run-1", "psm", ecore_attempt) == {"src-gen/A.java": "class A {}"}
    assert _zip_of("run-1", "acceleo", acceleo_attempt) == {"generate.emtl": "module"}


@pytest.mark.parametrize("scope", [(None, None, None), ("run-1", None, None), ("run-1", "atl", None), (None, "atl", "attempt_1")])
def test_nothing_is_kept_for_a_call_that_does_not_name_a_whole_attempt(scope):
    run, _ = _compiling(ATL_OK, {"sample.asm": "bytecode"})
    with patch("validator_runner.subprocess.run", side_effect=run):
        result = run_atl_validator("module M;", "sample.atl", *scope)

    assert result["compiled_output_kept"] is False


def test_nothing_is_kept_when_nothing_was_compiled():
    attempt = journal_client.reserve_attempt("run-1", "atl")
    run, _ = _compiling(ATL_OK, {})
    with patch("validator_runner.subprocess.run", side_effect=run):
        result = run_atl_validator("module M;", "sample.atl", "run-1", "atl", attempt)

    assert result["compiled_output_kept"] is False
    assert journal_client.get_compiled_output("run-1", "atl", attempt) is None


def test_a_journal_that_cannot_be_reached_does_not_fail_the_validation(caplog):
    attempt = journal_client.reserve_attempt("run-1", "atl")
    run, _ = _compiling(ATL_OK, {"sample.asm": "bytecode"})
    with patch("validator_runner.subprocess.run", side_effect=run), patch.object(
        journal_client, "keep_compiled_output", side_effect=httpx.ConnectError("the journal is down")
    ):
        result = run_atl_validator("module M;", "sample.atl", "run-1", "atl", attempt)

    assert result["valid"] is True and result["compiled_output_kept"] is False
    assert "could not keep the compiled output" in caplog.text


def test_an_attempt_the_journal_does_not_know_is_not_kept_and_does_not_fail_the_validation():
    run, _ = _compiling(ATL_OK, {"sample.asm": "bytecode"})
    with patch("validator_runner.subprocess.run", side_effect=run):
        result = run_atl_validator("module M;", "sample.atl", "no-such-run", "atl", "attempt_1")

    assert result["valid"] is True and result["compiled_output_kept"] is False


def test_an_attempt_that_is_already_recorded_is_not_changed():
    attempt = journal_client.reserve_attempt("run-1", "atl")
    journal_client.record_attempt("run-1", "atl", attempt, filename="a.atl", content="module M;", result={"valid": True})
    run, _ = _compiling(ATL_OK, {"sample.asm": "bytecode"})
    with patch("validator_runner.subprocess.run", side_effect=run):
        result = run_atl_validator("module M;", "sample.atl", "run-1", "atl", attempt)

    assert result["compiled_output_kept"] is False
    assert journal_client.get_compiled_output("run-1", "atl", attempt) is None
