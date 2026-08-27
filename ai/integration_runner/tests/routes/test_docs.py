"""routes/docs.py unit tests: extend_endpoint(), called directly as a plain
function (no TestClient needed for this). No real API calls —
clients/retrieval_client.httpx is mocked.

Tests verify:
  1. 409 if a stage is already busy, before even attempting the fetch.
  2. busy is held (True) for the real fetch + append itself, not just the
     initial check, and always cleared afterward even on failure — the
     real race this endpoint's own docstring explains it guards against
     (a concurrent stage run mutating last_output at the same time).
  3. ValueError/RuntimeError from extend_with_page() map to 400 with the
     real message preserved.
"""
from unittest.mock import patch

import pytest
from fastapi import HTTPException

from clients import retrieval_client
from integration_runner import pipeline, runs
from integration_runner.routes.docs import ExtendRequest, extend_endpoint
from helpers import _fake_page_response, _fast_forward_to_generation


@pytest.fixture(autouse=True)
def _reset_default_run():
    original = runs._default
    yield
    runs._default = original
    runs._runs.clear()
    runs._runs[original.run_id] = original


def test_extend_endpoint_rejects_when_already_busy():
    runs._default = pipeline.IntegrationRun()
    runs.current().busy = True

    with pytest.raises(HTTPException) as exc_info:
        extend_endpoint(ExtendRequest(url="https://example.com/docs"))

    assert exc_info.value.status_code == 409


def test_extend_endpoint_holds_busy_during_the_real_fetch_and_clears_it_after():
    runs._default = pipeline.IntegrationRun()
    run = runs.current()
    busy_during_fetch = None

    def _capture_busy_then_fetch(*args, **kwargs):
        nonlocal busy_during_fetch
        busy_during_fetch = run.busy
        return _fake_page_response()

    with patch.object(retrieval_client, "httpx") as mock_httpx:
        mock_httpx.post.side_effect = _capture_busy_then_fetch
        extend_endpoint(ExtendRequest(url="https://example.com/docs"))

    assert busy_during_fetch is True
    assert run.busy is False


def test_extend_endpoint_clears_busy_even_when_the_fetch_fails():
    runs._default = pipeline.IntegrationRun()
    run = runs.current()

    with patch.object(retrieval_client, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_page_response(success=False)
        with pytest.raises(HTTPException) as exc_info:
            extend_endpoint(ExtendRequest(url="https://example.com/docs"))

    assert exc_info.value.status_code == 400
    assert "failed" in exc_info.value.detail
    assert run.busy is False


def test_extend_endpoint_maps_wrong_stage_to_400():
    runs._default = pipeline.IntegrationRun()
    _fast_forward_to_generation(runs.current())

    with pytest.raises(HTTPException) as exc_info:
        extend_endpoint(ExtendRequest(url="https://example.com/docs"))

    assert exc_info.value.status_code == 400
    assert "docs" in exc_info.value.detail
