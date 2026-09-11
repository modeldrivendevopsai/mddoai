"""routes/acceleo.py unit tests: each endpoint called directly as a plain
function, matching test_psm.py's own convention.

The prompt-config pass-throughs are pure wrappers over
clients/acceleo_agent_client.py - mocked at that one real boundary, no real
HTTP call. promote_constraints_endpoint is run-aware, so it also resets
runs._default the same way test_psm.py does.
"""
import asyncio
from unittest.mock import patch

import pytest
from fastapi import HTTPException

from clients import acceleo_agent_client
from integration_runner import pipeline, runs
from integration_runner.routes.acceleo import (
    LearnedConstraintsRequest,
    PromoteConstraintsRequest,
    RemoveLearnedConstraintRequest,
    SaveConfigRequest,
    add_learned_constraints_endpoint,
    available_files_endpoint,
    get_prompt_config_endpoint,
    list_presets_endpoint,
    promote_constraints_endpoint,
    remove_learned_constraint_endpoint,
    save_prompt_config_endpoint,
    upload_attachment_endpoint,
)


class _FakeUploadFile:
    """A minimal duck-typed stand-in for FastAPI's real UploadFile - just
    enough surface (filename, an async read()) for upload_attachment_endpoint
    to use, without needing a real TestClient/multipart request for what's
    really just a thin proxy over acceleo_agent_client, mocked below the
    same way every other endpoint in this file already is."""

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
    with patch.object(acceleo_agent_client, "list_presets", return_value=[{"id": "default"}]) as mock_list:
        result = list_presets_endpoint("generation")

    mock_list.assert_called_once_with("generation")
    assert result == {"presets": [{"id": "default"}]}


def test_get_prompt_config_endpoint_proxies_the_real_client():
    config = {"system_prompt": "x", "attachments": []}
    with patch.object(acceleo_agent_client, "get_prompt_config", return_value=config) as mock_get:
        result = get_prompt_config_endpoint("generation", "default")

    mock_get.assert_called_once_with("generation", "default")
    assert result == config


def test_save_prompt_config_endpoint_forwards_the_real_body():
    body = SaveConfigRequest(attachments=[])
    with patch.object(acceleo_agent_client, "save_prompt_config", return_value={"_version": "v1"}) as mock_save:
        result = save_prompt_config_endpoint("generation", "default", body)

    mock_save.assert_called_once_with("generation", "default", body.model_dump())
    assert result == {"_version": "v1"}


def test_add_learned_constraints_endpoint_forwards_constraints():
    with patch.object(acceleo_agent_client, "add_learned_constraints", return_value={}) as mock_add:
        add_learned_constraints_endpoint("generation", "default", LearnedConstraintsRequest(constraints=["x"]))

    mock_add.assert_called_once_with("generation", "default", ["x"])


def test_remove_learned_constraint_endpoint_forwards_the_constraint():
    with patch.object(acceleo_agent_client, "remove_learned_constraint", return_value={}) as mock_remove:
        remove_learned_constraint_endpoint("generation", "default", RemoveLearnedConstraintRequest(constraint="x"))

    mock_remove.assert_called_once_with("generation", "default", "x")


def test_available_files_endpoint_proxies_the_real_client():
    with patch.object(acceleo_agent_client, "list_available_files", return_value=["generate.mtl"]) as mock_list:
        result = available_files_endpoint()

    mock_list.assert_called_once()
    assert result == {"files": ["generate.mtl"]}


def test_upload_attachment_endpoint_forwards_filename_and_content():
    fake_file = _FakeUploadFile("custom.mtl", b"[module m('x')]")
    with patch.object(acceleo_agent_client, "upload_attachment_file", return_value="abc123-custom.mtl") as mock_upload:
        result = asyncio.run(upload_attachment_endpoint(fake_file))

    mock_upload.assert_called_once_with("custom.mtl", b"[module m('x')]")
    assert result == {"path": "abc123-custom.mtl"}


def test_promote_constraints_endpoint_rejects_when_busy():
    runs._default = pipeline.IntegrationRun()
    runs.current().busy = True

    with pytest.raises(HTTPException) as exc_info:
        promote_constraints_endpoint(PromoteConstraintsRequest(constraints=["x"]))

    assert exc_info.value.status_code == 409


def test_promote_constraints_endpoint_maps_no_verified_result_to_400():
    runs._default = pipeline.IntegrationRun()
    runs.current().current_stage_index = pipeline.STAGES.index("acceleo")

    with pytest.raises(HTTPException) as exc_info:
        promote_constraints_endpoint(PromoteConstraintsRequest(constraints=["x"]))

    assert exc_info.value.status_code == 400


def test_promote_constraints_endpoint_succeeds_for_a_real_verified_result():
    runs._default = pipeline.IntegrationRun()
    run = runs.current()
    run.current_stage_index = pipeline.STAGES.index("acceleo")
    run.event_log.events.append({
        "type": "call_completed", "stage": "acceleo",
        "data": {"preset": "default", "validation": {"valid": True}},
    })

    with patch.object(acceleo_agent_client, "add_learned_constraints", return_value={"learned_constraints": ["x"]}):
        result = promote_constraints_endpoint(PromoteConstraintsRequest(constraints=["x"]))

    assert result == {"learned_constraints": ["x"]}
