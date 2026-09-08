"""stages/_attempts_read.py unit tests: read_manifest()/read_attempt()
read the real on-disk tree stages/_validation.py's own persist_attempt()
writes. RUNS_DIR is redirected to a throwaway tmp_path for every test in
this whole suite by conftest.py's own autouse _isolated_validation_runs_dir
fixture, not repeated here.
"""
import pytest

from integration_runner.stages import _validation
from integration_runner.stages._attempts_read import AttemptNotFoundError, read_attempt, read_manifest


def _ok_result():
    return {"valid": True, "issues": [], "duration_ms": 5}


def test_read_manifest_lists_every_persisted_attempt():
    _validation.persist_attempt("run-1", "pim", "pim_mock.ecore", "content", _ok_result())
    _validation.persist_attempt("run-1", "psm", "psm.ecore", "<ecore/>", _ok_result())

    manifest = read_manifest("run-1")

    assert len(manifest) == 2
    assert {entry["stage"] for entry in manifest} == {"pim", "psm"}


def test_read_manifest_raises_for_an_unknown_run():
    with pytest.raises(AttemptNotFoundError):
        read_manifest("no-such-run")


def test_read_manifest_rejects_a_path_traversal_run_id():
    with pytest.raises(AttemptNotFoundError):
        read_manifest("../../etc")


def test_read_attempt_returns_the_real_artifact_and_result():
    _validation.persist_attempt("run-1", "psm", "psm.ecore", "<ecore:EPackage/>", _ok_result())

    attempt = read_attempt("run-1", "psm", "attempt_1")

    assert attempt["artifact"] == {"filename": "psm.ecore", "content": "<ecore:EPackage/>"}
    assert attempt["result"] == _ok_result()
    assert attempt["prompt"] is None
    assert attempt["prompt_version"] is None


def test_read_attempt_includes_the_real_prompt_when_persisted():
    prompt = {"pim_ecore": "<pim/>", "constraints": ""}
    _validation.persist_attempt(
        "run-1", "psm", "psm.ecore", "<ecore/>", _ok_result(), prompt=prompt, prompt_version="v1"
    )

    attempt = read_attempt("run-1", "psm", "attempt_1")

    assert attempt["prompt"] == prompt
    assert attempt["prompt_version"] == "v1"


def test_read_attempt_raises_for_a_nonexistent_attempt():
    with pytest.raises(AttemptNotFoundError):
        read_attempt("run-1", "psm", "attempt_99")


def test_read_attempt_rejects_a_path_traversal_stage():
    with pytest.raises(AttemptNotFoundError):
        read_attempt("run-1", "../../etc", "attempt_1")


def test_read_attempt_rejects_a_path_traversal_attempt():
    with pytest.raises(AttemptNotFoundError):
        read_attempt("run-1", "psm", "..")
