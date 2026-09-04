"""validator_agent_client.py unit tests: validate_ecore()/validate_atl()/
validate_acceleo() each POST to validator-agent's real endpoint and return
its parsed JSON response as a plain dict, unmodified — win or lose, that's
real data, not an error. These mock httpx.post directly (the actual
network boundary), matching test_ai_layer_client.py's own pattern.
"""
from unittest.mock import MagicMock, patch

import httpx
import pytest

import validator_agent_client
from helpers import _fake_httpx_response_raw


def _fake_result(valid=True, issues=None, duration_ms=5, mode=None):
    body = {"valid": valid, "issues": issues or [], "duration_ms": duration_ms}
    if mode is not None:
        body["mode"] = mode
    return body


def test_validate_ecore_posts_filename_content_and_mode():
    result = _fake_result(mode="reflective")
    with patch("validator_agent_client.httpx.post", return_value=_fake_httpx_response_raw(result)) as mock_post:
        response = validator_agent_client.validate_ecore("<ecore/>", "sample.ecore")

    mock_post.assert_called_once_with(
        f"{validator_agent_client.VALIDATOR_AGENT_URL}/validate/ecore",
        json={"filename": "sample.ecore", "content": "<ecore/>", "mode": "reflective"},
        timeout=validator_agent_client.VALIDATE_TIMEOUT,
    )
    assert response == result


def test_validate_ecore_defaults_filename_for_a_caller_with_no_real_source_file():
    # psm_agent's own generate() validates in-memory generated content with
    # no real source file to name - this default is what lets it call
    # validate_ecore(artifact, mode="reflective") without inventing one.
    with patch("validator_agent_client.httpx.post", return_value=_fake_httpx_response_raw(_fake_result())) as mock_post:
        validator_agent_client.validate_ecore("<ecore/>", mode="reflective")

    assert mock_post.call_args.kwargs["json"]["filename"] == "model.ecore"


def test_validate_ecore_forwards_an_explicit_mode():
    with patch("validator_agent_client.httpx.post", return_value=_fake_httpx_response_raw(_fake_result())) as mock_post:
        validator_agent_client.validate_ecore("<ecore/>", "sample.ecore", mode="codegen")

    assert mock_post.call_args.kwargs["json"]["mode"] == "codegen"


def test_validate_atl_posts_filename_and_content_with_no_mode_field():
    result = _fake_result()
    with patch("validator_agent_client.httpx.post", return_value=_fake_httpx_response_raw(result)) as mock_post:
        response = validator_agent_client.validate_atl("module M; ...", "sample.atl")

    mock_post.assert_called_once_with(
        f"{validator_agent_client.VALIDATOR_AGENT_URL}/validate/atl",
        json={"filename": "sample.atl", "content": "module M; ..."},
        timeout=validator_agent_client.VALIDATE_TIMEOUT,
    )
    assert response == result


def test_validate_acceleo_posts_filename_and_content():
    result = _fake_result()
    with patch("validator_agent_client.httpx.post", return_value=_fake_httpx_response_raw(result)) as mock_post:
        response = validator_agent_client.validate_acceleo("[module m('x')]", "sample.mtl")

    mock_post.assert_called_once_with(
        f"{validator_agent_client.VALIDATOR_AGENT_URL}/validate/acceleo",
        json={"filename": "sample.mtl", "content": "[module m('x')]"},
        timeout=validator_agent_client.VALIDATE_TIMEOUT,
    )
    assert response == result


def test_validate_ecore_returns_a_failing_result_as_plain_data_not_an_error():
    # win or lose is real data here, not something to raise over — only an
    # infra failure (see the next test) raises.
    failing = _fake_result(valid=False, issues=[{"severity": "error", "message": "NoSuchType", "source": None}])
    with patch("validator_agent_client.httpx.post", return_value=_fake_httpx_response_raw(failing)):
        response = validator_agent_client.validate_ecore("<broken/>", "sample.ecore")

    assert response["valid"] is False
    assert response["issues"][0]["message"] == "NoSuchType"


def test_validate_atl_raises_on_an_infra_failure_status():
    resp = MagicMock()
    resp.raise_for_status.side_effect = httpx.HTTPStatusError("500", request=MagicMock(), response=MagicMock())
    with patch("validator_agent_client.httpx.post", return_value=resp):
        with pytest.raises(httpx.HTTPStatusError):
            validator_agent_client.validate_atl("module M; ...", "sample.atl")
