"""psm_agent_client.py unit tests: run_psm() POSTs to psm_agent's real /psm
and returns its parsed JSON response as a plain dict, unmodified. Mocks
httpx.post directly (the real network boundary), matching
test_validator_agent_client.py's own pattern. Not a full behavioral suite
for run_psm() (this module had none before), just real coverage for the
stage/attempt fields it forwards for compiled-output nesting - see
integration_runner/stages/psm/agent.py's own reserve_attempt_dir() call.
"""
from unittest.mock import patch

import psm_agent_client
from helpers import _fake_httpx_response_raw


def _fake_result(mode="generation", artifact="<ecore/>"):
    return {"mode": mode, "artifact": artifact}


def test_run_psm_posts_the_real_payload_shape():
    with patch("psm_agent_client.httpx.post", return_value=_fake_httpx_response_raw(_fake_result())) as mock_post:
        response = psm_agent_client.run_psm("GitLab CI", "<pim/>", "docs")

    mock_post.assert_called_once_with(
        f"{psm_agent_client.PSM_AGENT_URL}/psm",
        json={
            "platform_description": "GitLab CI", "pim_artifact": "<pim/>", "platform_docs": "docs",
            "constraints": None, "model": None, "run_id": None, "stage": None, "attempt": None,
        },
        timeout=psm_agent_client.PSM_TIMEOUT,
    )
    assert response == _fake_result()


def test_run_psm_forwards_stage_and_attempt_for_compiled_output_nesting():
    with patch("psm_agent_client.httpx.post", return_value=_fake_httpx_response_raw(_fake_result())) as mock_post:
        psm_agent_client.run_psm(
            "GitLab CI", "<pim/>", "docs", run_id="run-123", stage="psm", attempt="attempt_1",
        )

    assert mock_post.call_args.kwargs["json"]["stage"] == "psm"
    assert mock_post.call_args.kwargs["json"]["attempt"] == "attempt_1"
