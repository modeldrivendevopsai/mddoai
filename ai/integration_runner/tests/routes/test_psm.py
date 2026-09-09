"""routes/psm.py unit tests: each endpoint called directly as a plain
function, matching test_docs.py's own convention.

The prompt-config pass-throughs are pure wrappers over
clients/psm_agent_client.py - mocked at that one real boundary, no real
HTTP call. promote_constraints_endpoint is run-aware, so it also resets
runs._default the same way test_docs.py does.
"""
import asyncio
from unittest.mock import patch

import pytest
from fastapi import HTTPException

from clients import psm_agent_client
from integration_runner import pipeline, runs
from integration_runner.routes.psm import (
    LearnedConstraintsRequest,
    PromoteConstraintsRequest,
    SaveConfigRequest,
    add_learned_constraints_endpoint,
    get_prompt_config_endpoint,
    list_presets_endpoint,
    promote_constraints_endpoint,
    resolve_mode_endpoint,
    save_prompt_config_endpoint,
    upload_attachment_endpoint,
)


class _FakeUploadFile:
    """A minimal duck-typed stand-in for FastAPI's real UploadFile - just
    enough surface (filename, an async read()) for upload_attachment_endpoint
    to use, without needing a real TestClient/multipart request for what's
    really just a thin proxy over psm_agent_client, mocked below the same
    way every other endpoint in this file already is."""

    def __init__(self, filename: str, content: bytes):
        self.filename = filename
        self._content = content

    async def read(self) -> bytes:
        return self._content


@pytest.fixture(autouse=True)
def _reset_default_run():
    original = runs._default
    yield
    runs._default = original
    runs._runs.clear()
    runs._runs[original.run_id] = original


def test_list_presets_endpoint_proxies_the_real_client():
    with patch.object(psm_agent_client, "list_presets", return_value=[{"id": "default"}]) as mock_list:
        result = list_presets_endpoint("generation")

    mock_list.assert_called_once_with("generation")
    assert result == {"presets": [{"id": "default"}]}


def test_get_prompt_config_endpoint_proxies_the_real_client():
    config = {"system_prompt": "x", "attachments": []}
    with patch.object(psm_agent_client, "get_prompt_config", return_value=config) as mock_get:
        result = get_prompt_config_endpoint("generation", "default")

    mock_get.assert_called_once_with("generation", "default")
    assert result == config


def test_save_prompt_config_endpoint_forwards_the_real_body():
    body = SaveConfigRequest(attachments=[])
    with patch.object(psm_agent_client, "save_prompt_config", return_value={"_version": "v1"}) as mock_save:
        result = save_prompt_config_endpoint("generation", "default", body)

    mock_save.assert_called_once_with("generation", "default", body.model_dump())
    assert result == {"_version": "v1"}


def test_add_learned_constraints_endpoint_forwards_constraints():
    with patch.object(psm_agent_client, "add_learned_constraints", return_value={}) as mock_add:
        add_learned_constraints_endpoint("generation", "default", LearnedConstraintsRequest(constraints=["x"]))

    mock_add.assert_called_once_with("generation", "default", ["x"])


def test_resolve_mode_endpoint_proxies_the_real_client():
    with patch.object(psm_agent_client, "resolve_psm_mode", return_value={"mode": "generation", "metamodel_path": None}) as mock_resolve:
        result = resolve_mode_endpoint("A brand new platform")

    mock_resolve.assert_called_once_with("A brand new platform")
    assert result == {"mode": "generation", "metamodel_path": None}


def test_upload_attachment_endpoint_forwards_filename_and_content():
    fake_file = _FakeUploadFile("model.ecore", b"<ecore/>")
    with patch.object(psm_agent_client, "upload_attachment_file", return_value="abc123-model.ecore") as mock_upload:
        result = asyncio.run(upload_attachment_endpoint(fake_file))

    mock_upload.assert_called_once_with("model.ecore", b"<ecore/>")
    assert result == {"path": "abc123-model.ecore"}


def test_promote_constraints_endpoint_rejects_when_busy():
    runs._default = pipeline.IntegrationRun()
    runs.current().busy = True

    with pytest.raises(HTTPException) as exc_info:
        promote_constraints_endpoint(PromoteConstraintsRequest(constraints=["x"]))

    assert exc_info.value.status_code == 409


def test_promote_constraints_endpoint_maps_no_verified_result_to_400():
    runs._default = pipeline.IntegrationRun()
    runs.current().current_stage_index = pipeline.STAGES.index("psm")

    with pytest.raises(HTTPException) as exc_info:
        promote_constraints_endpoint(PromoteConstraintsRequest(constraints=["x"]))

    assert exc_info.value.status_code == 400


def test_promote_constraints_endpoint_succeeds_for_a_real_verified_result():
    runs._default = pipeline.IntegrationRun()
    run = runs.current()
    run.current_stage_index = pipeline.STAGES.index("psm")
    run.event_log.events.append({
        "type": "call_completed", "stage": "psm",
        "data": {"mode": "generation", "preset": "default", "validation": {"valid": True}},
    })

    with patch.object(psm_agent_client, "add_learned_constraints", return_value={"learned_constraints": ["x"]}):
        result = promote_constraints_endpoint(PromoteConstraintsRequest(constraints=["x"]))

    assert result == {"learned_constraints": ["x"]}
