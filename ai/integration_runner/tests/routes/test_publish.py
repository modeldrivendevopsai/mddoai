"""routes/publish.py's /publish endpoints: each outcome mapped to its status.
Endpoints are called directly as plain functions, like test_core.py does."""
from unittest.mock import patch

import httpx
import pytest
from fastapi import HTTPException

from clients.agent_service_errors import AgentServiceError
from integration_runner import pipeline, runs
from integration_runner.routes.publish import PublishRequest, publish_defaults_endpoint, publish_endpoint

CONTEXT = {"psm_output": "<ecore/>", "atl_output": "create OUT : W from IN : PIM;", "acceleo_output": "[m]"}
PUBLISHED = {"id": "woodpecker-ci", "name": "Woodpecker CI", "version": "1.0.0", "output_filename": "ci.yml"}
CLIENT = "integration_runner.publishing.journal_client.publish_platform"


@pytest.fixture(autouse=True)
def _reset_default_run():
    original = runs._default
    yield
    runs._default = original
    runs._runs.clear()
    runs._runs[original.run_id] = original


def _current_completed_run():
    run = pipeline.IntegrationRun()
    run.record_event("call_started", "docs", {"platform_description": "Woodpecker CI"})
    run.record_event("call_started", "generation", dict(CONTEXT))
    run.record_event("call_completed", "generation", {"output": "x", "generated_files": {"ci.yml": "x"}})
    run.current_stage_index = len(pipeline.STAGES)
    run.last_context = dict(CONTEXT)
    runs._default = run
    return run


def _status(call):
    with pytest.raises(HTTPException) as caught:
        call()
    return caught.value.status_code, caught.value.detail


def test_publish_returns_the_published_version_and_releases_the_run():
    run = _current_completed_run()

    with patch(CLIENT, return_value=PUBLISHED):
        assert publish_endpoint(PublishRequest()) == PUBLISHED
    assert run.busy is False


def test_the_run_is_held_busy_while_it_publishes_so_a_second_publish_is_refused():
    run = _current_completed_run()
    seen = {}

    def publish_platform(**_kwargs):
        seen["busy"] = run.busy
        seen["second"] = _status(lambda: publish_endpoint(PublishRequest()))[0]
        return PUBLISHED

    with patch(CLIENT, side_effect=publish_platform) as publish:
        publish_endpoint(PublishRequest())

    assert seen == {"busy": True, "second": 409}
    assert publish.call_count == 1
    assert run.busy is False


def test_a_failed_publish_releases_the_run_too():
    run = _current_completed_run()

    with patch(CLIENT, side_effect=httpx.ConnectError("refused")):
        _status(lambda: publish_endpoint(PublishRequest()))
    assert run.busy is False


def test_publish_is_409_while_a_stage_is_running():
    _current_completed_run().busy = True

    assert _status(lambda: publish_endpoint(PublishRequest()))[0] == 409


def test_publish_is_409_for_a_page_showing_another_run_and_nothing_is_published():
    _current_completed_run()

    with patch(CLIENT) as publish:
        status, detail = _status(lambda: publish_endpoint(PublishRequest(run_id="0" * 32)))
    assert status == 409 and "no longer the current run" in detail
    publish.assert_not_called()


def test_publish_with_the_current_runs_id_goes_ahead():
    run = _current_completed_run()

    with patch(CLIENT, return_value=PUBLISHED):
        assert publish_endpoint(PublishRequest(run_id=run.run_id)) == PUBLISHED


def test_publish_is_409_with_the_reason_when_the_run_is_not_complete():
    runs._default = pipeline.IntegrationRun()

    status, detail = _status(lambda: publish_endpoint(PublishRequest()))
    assert status == 409 and "not complete" in detail


def test_a_refusal_from_the_journal_is_400_with_its_reason():
    _current_completed_run()

    with patch(CLIENT, side_effect=AgentServiceError(422, "'gitlab' is already the platform 'GitLab CI'. Publish under another name.")):
        status, detail = _status(lambda: publish_endpoint(PublishRequest(name="GitLab")))
    assert status == 400 and "already the platform" in detail


def test_files_over_the_journals_limit_are_400_with_its_reason():
    _current_completed_run()

    with patch(CLIENT, side_effect=AgentServiceError(413, "What was sent is larger than the journal accepts.")):
        status, detail = _status(lambda: publish_endpoint(PublishRequest()))
    assert status == 400 and "larger" in detail


def test_an_unreachable_journal_is_502():
    _current_completed_run()

    with patch(CLIENT, side_effect=httpx.ConnectError("refused")):
        assert _status(lambda: publish_endpoint(PublishRequest()))[0] == 502


def test_the_defaults_endpoint_shows_what_publish_would_use():
    _current_completed_run()

    assert publish_defaults_endpoint() == {"name": "Woodpecker CI", "output_filename": "ci.yml"}


def test_a_version_asked_for_is_passed_on_to_the_journal():
    _current_completed_run()

    with patch(CLIENT, return_value=PUBLISHED) as publish:
        publish_endpoint(PublishRequest(version="2.1.0"))

    assert publish.call_args.kwargs["version"] == "2.1.0"


def test_a_version_that_exists_is_a_400_with_the_journals_reason():
    _current_completed_run()

    with patch(CLIENT, side_effect=AgentServiceError(409, "version 1.0.0 already exists, and a version is never changed")):
        status, detail = _status(lambda: publish_endpoint(PublishRequest(version="1.0.0")))

    assert status == 400 and "already exists" in detail
