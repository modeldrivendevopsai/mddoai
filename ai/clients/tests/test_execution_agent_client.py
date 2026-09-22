"""execution_agent_client.py unit tests: execute_atl()/execute_acceleo()
POST to execution-agent's real /execute/atl and /execute/acceleo and return
the real output directly. Mocks httpx.post directly (the real network
boundary), matching test_psm_agent_client.py's own pattern. A 422 (a real,
describable execution failure) raises AgentServiceError, not a raw httpx
error - matching test_atl_agent_client.py's/test_acceleo_agent_client.py's
own _config_request coverage, since execution_agent_client uses the exact
same shared boundary (agent_service_errors.raise_for_business_error).
"""
from unittest.mock import MagicMock, patch

import pytest

import execution_agent_client
from clients.agent_service_errors import AgentServiceError
from helpers import _fake_httpx_response_raw


def _fake_error_response(status_code, detail):
    resp = MagicMock()
    resp.status_code = status_code
    resp.json.return_value = {"detail": detail}
    resp.text = detail
    return resp


def test_execute_atl_posts_the_real_payload_shape_and_returns_the_output():
    with patch(
        "execution_agent_client.httpx.post",
        return_value=_fake_httpx_response_raw({"output_xmi": "<real-output/>"}),
    ) as mock_post:
        result = execution_agent_client.execute_atl("atl source", "<pim/>", "<ecore/>", "GitLabMM")

    mock_post.assert_called_once_with(
        f"{execution_agent_client.EXECUTION_AGENT_URL}/execute/atl",
        json={
            "atl_source": "atl source", "pim_model_xmi": "<pim/>",
            "target_ecore": "<ecore/>", "output_model_name": "GitLabMM",
        },
        timeout=execution_agent_client.EXECUTE_TIMEOUT,
    )
    assert result == "<real-output/>"


def test_execute_atl_defaults_output_model_name():
    with patch(
        "execution_agent_client.httpx.post",
        return_value=_fake_httpx_response_raw({"output_xmi": "<real-output/>"}),
    ) as mock_post:
        execution_agent_client.execute_atl("atl source", "<pim/>", "<ecore/>")

    assert mock_post.call_args.kwargs["json"]["output_model_name"] == "OUT"


def test_execute_atl_raises_agent_service_error_for_a_real_execution_failure():
    with patch(
        "execution_agent_client.httpx.post",
        return_value=_fake_error_response(422, "not a real Pipeline instance"),
    ):
        with pytest.raises(AgentServiceError) as exc_info:
            execution_agent_client.execute_atl("atl source", "<pim/>", "<ecore/>")

    assert exc_info.value.status_code == 422
    assert exc_info.value.detail == "not a real Pipeline instance"


def test_execute_acceleo_posts_the_real_payload_shape_and_returns_the_generated_files():
    with patch(
        "execution_agent_client.httpx.post",
        return_value=_fake_httpx_response_raw({"generated_files": {".gitlab-ci.yml": "stages: []\n"}}),
    ) as mock_post:
        result = execution_agent_client.execute_acceleo("mtl source", "<psm/>", "<ecore/>")

    mock_post.assert_called_once_with(
        f"{execution_agent_client.EXECUTION_AGENT_URL}/execute/acceleo",
        json={"mtl_source": "mtl source", "psm_model_xmi": "<psm/>", "target_ecore": "<ecore/>"},
        timeout=execution_agent_client.EXECUTE_TIMEOUT,
    )
    assert result == {".gitlab-ci.yml": "stages: []\n"}


def test_execute_acceleo_raises_agent_service_error_for_a_real_execution_failure():
    with patch(
        "execution_agent_client.httpx.post",
        return_value=_fake_error_response(422, "target metamodel could not be loaded"),
    ):
        with pytest.raises(AgentServiceError) as exc_info:
            execution_agent_client.execute_acceleo("mtl source", "<psm/>", "<ecore/>")

    assert exc_info.value.status_code == 422
    assert exc_info.value.detail == "target metamodel could not be loaded"
