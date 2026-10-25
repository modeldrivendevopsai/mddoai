"""swarch_agent_client.to_pim: POSTs the model to swarch-agent's /pim and
returns the PIM. Mocks httpx.post directly, the real network boundary."""
from unittest.mock import MagicMock, patch

import pytest

import swarch_agent_client
from clients.agent_service_errors import AgentServiceError
from helpers import _fake_httpx_response_raw


def _fake_response(status_code, payload):
    resp = MagicMock()
    resp.status_code = status_code
    resp.json.return_value = payload
    resp.text = str(payload)
    return resp


def test_to_pim_posts_the_model_and_returns_the_pim():
    response = _fake_httpx_response_raw({"pim": "<pim/>"})
    response.status_code = 200
    with patch("swarch_agent_client.httpx.post", return_value=response) as mock_post:
        assert swarch_agent_client.to_pim("<swarch/>") == "<pim/>"

    mock_post.assert_called_once_with(
        f"{swarch_agent_client.SWARCH_AGENT_URL}/pim",
        json={"content": "<swarch/>"},
        timeout=swarch_agent_client.PIM_TIMEOUT,
    )


def test_an_invalid_model_raises_agent_service_error_carrying_the_issues():
    detail = {"message": "The model is not a valid Software Architecture model.",
              "issues": [{"severity": "ERROR", "message": "missing name"}]}
    with patch("swarch_agent_client.httpx.post", return_value=_fake_response(422, {"detail": detail})):
        with pytest.raises(AgentServiceError) as caught:
            swarch_agent_client.to_pim("<swarch/>")

    assert caught.value.status_code == 422
    assert caught.value.detail == detail


def test_an_unavailable_service_is_a_raw_httpx_error():
    import httpx

    response = _fake_response(502, {"detail": "unavailable"})
    response.raise_for_status.side_effect = httpx.HTTPStatusError("bad gateway", request=MagicMock(), response=response)
    with patch("swarch_agent_client.httpx.post", return_value=response):
        with pytest.raises(httpx.HTTPStatusError):
            swarch_agent_client.to_pim("<swarch/>")
