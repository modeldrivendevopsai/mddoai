"""routes/attempts.py unit tests: manifest_endpoint()/attempt_endpoint()
called directly as plain functions, matching test_docs.py's own
convention. RUNS_DIR isolation comes from conftest.py's own autouse
fixture, not repeated here.
"""
import pytest
from fastapi import HTTPException

from integration_runner.routes.attempts import attempt_endpoint, manifest_endpoint
from integration_runner.stages import _validation


def _ok_result():
    return {"valid": True, "issues": [], "duration_ms": 5}


def test_manifest_endpoint_returns_real_persisted_attempts():
    _validation.persist_attempt("run-1", "psm", "psm.ecore", "<ecore/>", _ok_result())

    result = manifest_endpoint("run-1")

    assert len(result["attempts"]) == 1
    assert result["attempts"][0]["stage"] == "psm"


def test_manifest_endpoint_404s_for_an_unknown_run():
    with pytest.raises(HTTPException) as exc_info:
        manifest_endpoint("no-such-run")

    assert exc_info.value.status_code == 404


def test_attempt_endpoint_returns_the_real_attempt():
    _validation.persist_attempt("run-1", "psm", "psm.ecore", "<ecore:EPackage/>", _ok_result())

    result = attempt_endpoint("run-1", "psm", "attempt_1")

    assert result["artifact"]["content"] == "<ecore:EPackage/>"


def test_attempt_endpoint_404s_for_a_nonexistent_attempt():
    with pytest.raises(HTTPException) as exc_info:
        attempt_endpoint("run-1", "psm", "attempt_99")

    assert exc_info.value.status_code == 404
