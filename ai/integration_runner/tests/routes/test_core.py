"""routes/core.py unit tests for the concurrency backstop: every mutating
endpoint keeps its own pre-flight `if run.busy` check, but that check has a
window before busy is actually claimed. The real atomic claim
(`IntegrationRun.claim_busy()`, called by `run_stage_async()`/`review()`, or
`_reject_if_busy()` in `runs.py` for `reset_pipeline()`/`resume_run()`)
raises `BusyError` in that window; these tests confirm each handler turns
that `BusyError` into the same 409 the pre-flight check gives, rather than
letting it escape as an unhandled 500. Endpoints are called directly as
plain functions, no TestClient. No real API calls.
"""
from unittest.mock import patch

import pytest
from fastapi import HTTPException

from integration_runner import pipeline, runs
from integration_runner.routes.core import (
    RerunRequest,
    ReviewRequest,
    StageRunRequest,
    StartRequest,
    rerun_endpoint,
    resume_endpoint,
    reset_endpoint,
    review_endpoint,
    stage_run_endpoint,
    start_endpoint,
)


@pytest.fixture(autouse=True)
def _reset_default_run():
    original = runs._default
    yield
    runs._default = original
    runs._runs.clear()
    runs._runs[original.run_id] = original


def test_stage_run_endpoint_maps_a_racing_busyerror_to_409():
    run = pipeline.IntegrationRun()  # not busy, so the pre-flight check passes
    runs._default = run
    with patch.object(run, "start_stage_run", side_effect=pipeline.BusyError("busy")):
        with pytest.raises(HTTPException) as exc_info:
            stage_run_endpoint(StageRunRequest(context={}))
    assert exc_info.value.status_code == 409


def test_start_endpoint_maps_a_racing_busyerror_to_409():
    runs._default = pipeline.IntegrationRun()
    with patch.object(runs, "start_pipeline", side_effect=pipeline.BusyError("busy")):
        with pytest.raises(HTTPException) as exc_info:
            start_endpoint(StartRequest(platform_description="TeamCity", seed_url="https://example.com/docs"))
    assert exc_info.value.status_code == 409


def test_review_endpoint_maps_a_racing_busyerror_to_409():
    run = pipeline.IntegrationRun()
    runs._default = run
    with patch.object(run, "review", side_effect=pipeline.BusyError("busy")):
        with pytest.raises(HTTPException) as exc_info:
            review_endpoint("docs", ReviewRequest(approved=True))
    assert exc_info.value.status_code == 409


def test_rerun_endpoint_maps_a_racing_busyerror_to_409():
    run = pipeline.IntegrationRun()
    runs._default = run
    # rerun_endpoint checks stage_id == run.current_stage first; docs is the
    # current stage on a fresh run.
    with patch.object(run, "rerun", side_effect=pipeline.BusyError("busy")):
        with pytest.raises(HTTPException) as exc_info:
            rerun_endpoint("docs", None)
    assert exc_info.value.status_code == 409


def test_reset_endpoint_maps_a_racing_busyerror_to_409():
    runs._default = pipeline.IntegrationRun()  # not busy, so the pre-flight check passes
    with patch.object(runs, "reset_pipeline", side_effect=pipeline.BusyError("busy")):
        with pytest.raises(HTTPException) as exc_info:
            reset_endpoint()
    assert exc_info.value.status_code == 409


def test_resume_endpoint_maps_a_racing_busyerror_to_409():
    runs._default = pipeline.IntegrationRun()  # not busy, so the pre-flight check passes
    with patch.object(runs, "resume_run", side_effect=pipeline.BusyError("busy")):
        with pytest.raises(HTTPException) as exc_info:
            resume_endpoint("some-run-id")
    assert exc_info.value.status_code == 409
