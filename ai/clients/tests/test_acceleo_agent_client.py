"""acceleo_agent_client.py unit tests: run_acceleo() POSTs to acceleo_agent's
real /generate and returns its parsed JSON response as a plain dict,
unmodified. Mocks httpx.post/httpx.request directly (the real network
boundary), matching test_psm_agent_client.py's own pattern. Not a full
behavioral suite for every prompt-config wrapper (each is an identical
one-line httpx.request call, already exhaustively proven correct by
psm_agent_client's own tests) - just real coverage for run_acceleo()'s own
payload shape and one representative config wrapper plus the real upload
call.
"""
from unittest.mock import patch

import acceleo_agent_client
from helpers import _fake_httpx_response_raw


def _fake_result(artifact="[module m('x')]"):
    return {"artifact": artifact, "prompt": {}, "validation": {"valid": True}, "rounds": 1}


def test_run_acceleo_posts_the_real_payload_shape():
    with patch("acceleo_agent_client.httpx.post", return_value=_fake_httpx_response_raw(_fake_result())) as mock_post:
        response = acceleo_agent_client.run_acceleo("<psm/>", "docs")

    mock_post.assert_called_once_with(
        f"{acceleo_agent_client.ACCELEO_AGENT_URL}/generate",
        json={
            "psm_artifact": "<psm/>", "platform_docs": "docs",
            "constraints": None, "model": None, "run_id": None, "stage": None, "attempt": None,
            "mock": False,
        },
        timeout=acceleo_agent_client.ACCELEO_TIMEOUT,
    )
    assert response == _fake_result()


def test_run_acceleo_forwards_stage_and_attempt_for_compiled_output_nesting():
    with patch("acceleo_agent_client.httpx.post", return_value=_fake_httpx_response_raw(_fake_result())) as mock_post:
        acceleo_agent_client.run_acceleo("<psm/>", "docs", run_id="run-123", stage="acceleo", attempt="attempt_1")

    assert mock_post.call_args.kwargs["json"]["stage"] == "acceleo"
    assert mock_post.call_args.kwargs["json"]["attempt"] == "attempt_1"


def test_run_acceleo_forwards_mock():
    with patch("acceleo_agent_client.httpx.post", return_value=_fake_httpx_response_raw(_fake_result())) as mock_post:
        acceleo_agent_client.run_acceleo("<psm/>", "docs", mock=True)

    assert mock_post.call_args.kwargs["json"]["mock"] is True


def test_get_prompt_config_hits_the_real_endpoint():
    config = {"attachments": []}
    with patch("acceleo_agent_client.httpx.request", return_value=_fake_httpx_response_raw(config)) as mock_request:
        result = acceleo_agent_client.get_prompt_config("generation")

    mock_request.assert_called_once_with(
        "GET", f"{acceleo_agent_client.ACCELEO_AGENT_URL}/prompt-config/generation",
        timeout=acceleo_agent_client.ACCELEO_CONFIG_TIMEOUT,
    )
    assert result == config


def test_upload_attachment_file_posts_a_real_multipart_request():
    with patch("acceleo_agent_client.httpx.post", return_value=_fake_httpx_response_raw({"path": "abc-model.mtl"})) as mock_post:
        result = acceleo_agent_client.upload_attachment_file("model.mtl", b"[module m('x')]")

    mock_post.assert_called_once_with(
        f"{acceleo_agent_client.ACCELEO_AGENT_URL}/attachment-uploads",
        files={"file": ("model.mtl", b"[module m('x')]")},
        timeout=acceleo_agent_client.ACCELEO_CONFIG_TIMEOUT,
    )
    assert result == "abc-model.mtl"
