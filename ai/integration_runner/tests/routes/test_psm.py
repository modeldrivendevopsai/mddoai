"""routes/psm.py unit tests: each endpoint called directly as a plain
function, matching test_docs.py's own convention.

The prompt-config pass-throughs are pure wrappers over
clients/psm_agent_client.py - mocked at that one real boundary, no real
HTTP call. promote_constraints_endpoint is run-aware, so it also resets
runs._default the same way test_docs.py does.
"""
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
    save_prompt_config_endpoint,
)


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
    body = SaveConfigRequest(system_prompt="x", attachments=[])
    with patch.object(psm_agent_client, "save_prompt_config", return_value={"_version": "v1"}) as mock_save:
        result = save_prompt_config_endpoint("generation", "default", body)

    mock_save.assert_called_once_with("generation", "default", body.model_dump())
    assert result == {"_version": "v1"}


def test_add_learned_constraints_endpoint_forwards_constraints():
    with patch.object(psm_agent_client, "add_learned_constraints", return_value={}) as mock_add:
        add_learned_constraints_endpoint("generation", "default", LearnedConstraintsRequest(constraints=["x"]))

    mock_add.assert_called_once_with("generation", "default", ["x"])


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
