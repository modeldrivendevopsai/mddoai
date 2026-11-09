"""routes/core.py unit tests for the concurrency backstop: every mutating
endpoint keeps its own pre-flight `if run.busy` check, but that check has a
window before busy is actually claimed. The real atomic claim
(`IntegrationRun.claim_busy()`, called by `run_stage_async()`/`review()`, or
`_reject_if_busy()` in `runs.py` for `reset_pipeline()`/`resume_run()`/
`fork_run()`) raises `BusyError` in that window; these tests confirm each
handler turns that `BusyError` into the same 409 the pre-flight check gives,
rather than letting it escape as an unhandled 500. `/fork` also gets its own
404 (unknown source run)/400 (bad stage) mapping tests, the one endpoint
here with more than one real error status to map. Endpoints are called
directly as plain functions, no TestClient. No real API calls.
"""
from unittest.mock import patch

import pytest
from fastapi import HTTPException

from integration_runner import pipeline, runs
from integration_runner.routes.core import (
    ForkRequest,
    RerunRequest,
    ReviewRequest,
    StageRunRequest,
    StartRequest,
    events_endpoint,
    fork_endpoint,
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


def test_fork_endpoint_maps_a_racing_busyerror_to_409():
    runs._default = pipeline.IntegrationRun()  # not busy, so the pre-flight check passes
    source_run = pipeline.IntegrationRun()
    runs._runs[source_run.run_id] = source_run  # must exist for the endpoint's own pre-flight lookup
    with patch.object(runs, "fork_run", side_effect=pipeline.BusyError("busy")):
        with pytest.raises(HTTPException) as exc_info:
            fork_endpoint(ForkRequest(source_run_id=source_run.run_id, from_stage="atl"))
    assert exc_info.value.status_code == 409


def test_fork_endpoint_maps_an_unknown_source_run_to_404():
    runs._default = pipeline.IntegrationRun()
    with pytest.raises(HTTPException) as exc_info:
        fork_endpoint(ForkRequest(source_run_id="no-such-run-id", from_stage="atl"))
    assert exc_info.value.status_code == 404


def test_fork_endpoint_maps_a_real_valueerror_to_400():
    runs._default = pipeline.IntegrationRun()
    source_run = pipeline.IntegrationRun()
    runs._runs[source_run.run_id] = source_run
    with patch.object(runs, "fork_run", side_effect=ValueError("not a real stage")):
        with pytest.raises(HTTPException) as exc_info:
            fork_endpoint(ForkRequest(source_run_id=source_run.run_id, from_stage="not-a-real-stage"))
    assert exc_info.value.status_code == 400


# --- /events: only what a poller has not seen ------------------------------------


def _run_with_events(count):
    run = pipeline.IntegrationRun()
    for number in range(count):
        run.record_event("constraint_added", "docs", {"number": number})
    runs._default = run
    runs._runs[run.run_id] = run
    return run


def test_events_endpoint_sends_every_event_when_no_index_is_given():
    run = _run_with_events(3)

    body = events_endpoint()

    assert [e["data"]["number"] for e in body["events"]] == [0, 1, 2]
    assert (body["run_id"], body["offset"], body["total"]) == (run.run_id, 0, 3)


def test_events_endpoint_sends_only_the_events_from_since_index_on_and_says_where_they_start():
    _run_with_events(5)

    body = events_endpoint(since_index=3)

    assert [e["data"]["number"] for e in body["events"]] == [3, 4]
    assert (body["offset"], body["total"]) == (3, 5)


def test_events_endpoint_sends_nothing_when_the_poller_has_seen_everything():
    _run_with_events(2)

    body = events_endpoint(since_index=2)

    assert body["events"] == []
    assert body["total"] == 2


def test_events_endpoint_does_the_same_for_a_past_run():
    past = _run_with_events(4)
    runs._default = pipeline.IntegrationRun()  # past is no longer the current run

    body = events_endpoint(since_index=1, run_id=past.run_id)

    assert [e["data"]["number"] for e in body["events"]] == [1, 2, 3]
    assert (body["offset"], body["total"], body["is_current"]) == (1, 4, False)


def test_events_endpoint_maps_an_unknown_run_to_404():
    with pytest.raises(HTTPException) as exc_info:
        events_endpoint(run_id="no-such-run-id")
    assert exc_info.value.status_code == 404

