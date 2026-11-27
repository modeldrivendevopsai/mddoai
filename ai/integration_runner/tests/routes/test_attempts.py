"""routes/attempts.py unit tests: manifest_endpoint()/evolution_endpoint()/attempt_endpoint()
called directly as plain functions, matching test_docs.py's own
convention. The journal they read from is the real one, isolated by
conftest.py's own autouse fixture. An unknown run or attempt is the journal's
own 404, which the app's AgentServiceError handler turns into the response.
"""
from unittest.mock import patch

import httpx
import pytest
from fastapi import HTTPException

from clients.agent_service_errors import AgentServiceError
from integration_runner import pipeline, runs
from integration_runner.routes.attempts import attempt_endpoint, evolution_endpoint, manifest_endpoint
from integration_runner.stages import _validation


def _ok_result():
    return {"valid": True, "issues": [], "duration_ms": 5}


def test_manifest_endpoint_returns_real_persisted_attempts():
    _validation.persist_attempt("run-1", "psm", "psm.ecore", "<ecore/>", _ok_result())

    result = manifest_endpoint("run-1")

    assert len(result["attempts"]) == 1
    assert result["attempts"][0]["stage"] == "psm"


def test_manifest_endpoint_adds_what_the_runs_own_call_spent_on_each_attempt():
    run = pipeline.IntegrationRun()
    runs._runs[run.run_id] = run
    try:
        attempt = _validation.persist_attempt(run.run_id, "atl", "atl.atl", "x", _ok_result())
        run.record_event("call_started", "atl", {})
        run.record_event("constraint_added", "atl", {"constraint": "Fix: a", "source": "validation"})
        run.record_event("call_completed", "atl", {"rounds": 4, "attempt": attempt.name})

        [entry] = manifest_endpoint(run.run_id)["attempts"]
    finally:
        runs._runs.pop(run.run_id, None)

    assert (entry["attempt_n"], entry["rounds"], entry["ran_with"]) == (1, 4, 0)
    assert [c["text"] for c in entry["learned"]] == ["Fix: a"]


def test_manifest_endpoint_404s_for_an_unknown_run():
    with pytest.raises(AgentServiceError) as exc_info:
        manifest_endpoint("no-such-run")

    assert exc_info.value.status_code == 404


def test_manifest_endpoint_is_a_502_when_the_journal_cannot_be_reached():
    with patch("integration_runner.routes.attempts.journal_client.get_manifest", side_effect=httpx.ConnectError("journal down")):
        with pytest.raises(HTTPException) as exc_info:
            manifest_endpoint("any-run")

    assert exc_info.value.status_code == 502
    assert "journal" in exc_info.value.detail


# --- /runs/{id}/evolution: what a run took, without its generated files ---------------


def test_evolution_endpoint_lists_the_calls_of_a_past_run_with_their_rounds_and_corrections():
    run = pipeline.IntegrationRun()
    run.record_event("call_started", "psm", {})
    run.record_event("constraint_added", "psm", {"constraint": "use the real names", "source": "validation"})
    run.record_event("call_completed", "psm", {"rounds": 2, "output": "a large generated file"})
    runs._runs[run.run_id] = run
    try:
        body = evolution_endpoint(run.run_id)
    finally:
        runs._runs.pop(run.run_id, None)

    assert body["run_id"] == run.run_id
    [psm] = body["stages"]
    assert psm["calls"][0]["learned"][0]["text"] == "use the real names"
    # The summary is small: no stage entry carries a generated file.
    assert "a large generated file" not in str(body)


def test_evolution_endpoint_is_a_502_when_the_journal_cannot_be_reached():
    with patch.object(runs, "get_run_evolution", side_effect=httpx.ConnectError("journal down")):
        with pytest.raises(HTTPException) as exc_info:
            evolution_endpoint("any-run")

    assert exc_info.value.status_code == 502
    assert "journal" in exc_info.value.detail


def test_evolution_endpoint_is_a_404_for_an_unknown_run():
    with pytest.raises(HTTPException) as exc_info:
        evolution_endpoint("no-such-run")
    assert exc_info.value.status_code == 404


def test_attempt_endpoint_returns_the_real_attempt():
    _validation.persist_attempt("run-1", "psm", "psm.ecore", "<ecore:EPackage/>", _ok_result())

    result = attempt_endpoint("run-1", "psm", "attempt_1")

    assert result["artifact"]["content"] == "<ecore:EPackage/>"


def test_attempt_endpoint_404s_for_a_nonexistent_attempt():
    with pytest.raises(AgentServiceError) as exc_info:
        attempt_endpoint("run-1", "psm", "attempt_99")

    assert exc_info.value.status_code == 404
