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
            "mock": False,
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


def test_run_psm_forwards_mock():
    with patch("psm_agent_client.httpx.post", return_value=_fake_httpx_response_raw(_fake_result())) as mock_post:
        psm_agent_client.run_psm("GitLab CI", "<pim/>", "docs", mock=True)

    assert mock_post.call_args.kwargs["json"]["mock"] is True


# --- prompt-config wrappers: every one is a thin httpx.request() call, ------
# mocked directly at that one real network boundary, matching run_psm()'s
# own convention above.


def test_list_presets_hits_the_real_endpoint():
    with patch("psm_agent_client.httpx.request", return_value=_fake_httpx_response_raw({"presets": [{"id": "default"}]})) as mock_request:
        result = psm_agent_client.list_presets("generation")

    mock_request.assert_called_once_with(
        "GET", f"{psm_agent_client.PSM_AGENT_URL}/prompt-config/generation/presets",
        timeout=psm_agent_client.PSM_CONFIG_TIMEOUT,
    )
    assert result == [{"id": "default"}]


def test_get_prompt_config_hits_the_real_endpoint():
    config = {"system_prompt": "x", "attachments": []}
    with patch("psm_agent_client.httpx.request", return_value=_fake_httpx_response_raw(config)) as mock_request:
        result = psm_agent_client.get_prompt_config("generation", "default")

    mock_request.assert_called_once_with(
        "GET", f"{psm_agent_client.PSM_AGENT_URL}/prompt-config/generation/default",
        timeout=psm_agent_client.PSM_CONFIG_TIMEOUT,
    )
    assert result == config


def test_save_prompt_config_puts_the_real_body():
    config = {"system_prompt": "x", "attachments": []}
    with patch("psm_agent_client.httpx.request", return_value=_fake_httpx_response_raw({**config, "_version": "v1"})) as mock_request:
        result = psm_agent_client.save_prompt_config("generation", "default", config)

    mock_request.assert_called_once_with(
        "PUT", f"{psm_agent_client.PSM_AGENT_URL}/prompt-config/generation/default",
        timeout=psm_agent_client.PSM_CONFIG_TIMEOUT, json=config,
    )
    assert result["_version"] == "v1"


def test_get_prompt_config_history():
    with patch("psm_agent_client.httpx.request", return_value=_fake_httpx_response_raw({"versions": ["v1", "v2"]})):
        result = psm_agent_client.get_prompt_config_history("generation", "default")

    assert result == ["v1", "v2"]


def test_diff_prompt_config_versions_sends_both_versions_as_query_params():
    with patch("psm_agent_client.httpx.request", return_value=_fake_httpx_response_raw({"system_prompt_changed": False})) as mock_request:
        psm_agent_client.diff_prompt_config_versions("generation", "default", "v1", "v2")

    assert mock_request.call_args.kwargs["params"] == {"a": "v1", "b": "v2"}


def test_restore_prompt_config_version():
    with patch("psm_agent_client.httpx.request", return_value=_fake_httpx_response_raw({"system_prompt": "restored"})) as mock_request:
        result = psm_agent_client.restore_prompt_config_version("generation", "default", "v1")

    mock_request.assert_called_once_with(
        "POST", f"{psm_agent_client.PSM_AGENT_URL}/prompt-config/generation/default/restore/v1",
        timeout=psm_agent_client.PSM_CONFIG_TIMEOUT,
    )
    assert result["system_prompt"] == "restored"


def test_revert_prompt_config():
    with patch("psm_agent_client.httpx.request", return_value=_fake_httpx_response_raw({"system_prompt": "shipped"})) as mock_request:
        psm_agent_client.revert_prompt_config("generation", "default")

    mock_request.assert_called_once_with(
        "POST", f"{psm_agent_client.PSM_AGENT_URL}/prompt-config/generation/default/revert",
        timeout=psm_agent_client.PSM_CONFIG_TIMEOUT,
    )


def test_promote_prompt_config_to_default():
    with patch("psm_agent_client.httpx.request", return_value=_fake_httpx_response_raw({"system_prompt": "x"})) as mock_request:
        psm_agent_client.promote_prompt_config_to_default("generation", "default")

    mock_request.assert_called_once_with(
        "POST", f"{psm_agent_client.PSM_AGENT_URL}/prompt-config/generation/default/promote-to-default",
        timeout=psm_agent_client.PSM_CONFIG_TIMEOUT,
    )


def test_check_prompt_config_references():
    with patch("psm_agent_client.httpx.request", return_value=_fake_httpx_response_raw({"broken": [{"id": "a"}]})):
        result = psm_agent_client.check_prompt_config_references("generation", "default")

    assert result == [{"id": "a"}]


def test_preview_prompt_config():
    with patch("psm_agent_client.httpx.request", return_value=_fake_httpx_response_raw({"system_prompt": "x", "user_content": "y"})) as mock_request:
        result = psm_agent_client.preview_prompt_config("generation", "default")

    mock_request.assert_called_once_with(
        "POST", f"{psm_agent_client.PSM_AGENT_URL}/prompt-config/generation/default/preview",
        timeout=psm_agent_client.PSM_CONFIG_TIMEOUT,
    )
    assert result == {"system_prompt": "x", "user_content": "y"}


def test_add_learned_constraints_posts_the_real_body():
    with patch("psm_agent_client.httpx.request", return_value=_fake_httpx_response_raw({"learned_constraints": ["x"]})) as mock_request:
        result = psm_agent_client.add_learned_constraints("generation", "default", ["x"])

    mock_request.assert_called_once_with(
        "POST", f"{psm_agent_client.PSM_AGENT_URL}/prompt-config/generation/default/learned-constraints",
        timeout=psm_agent_client.PSM_CONFIG_TIMEOUT, json={"constraints": ["x"]},
    )
    assert result == {"learned_constraints": ["x"]}


def test_remove_learned_constraint_deletes_with_the_real_body():
    with patch("psm_agent_client.httpx.request", return_value=_fake_httpx_response_raw({"learned_constraints": []})) as mock_request:
        result = psm_agent_client.remove_learned_constraint("generation", "default", "x")

    mock_request.assert_called_once_with(
        "DELETE", f"{psm_agent_client.PSM_AGENT_URL}/prompt-config/generation/default/learned-constraints",
        timeout=psm_agent_client.PSM_CONFIG_TIMEOUT, json={"constraint": "x"},
    )
    assert result == {"learned_constraints": []}


def test_list_available_files():
    with patch("psm_agent_client.httpx.request", return_value=_fake_httpx_response_raw({"files": ["a.ecore"]})) as mock_request:
        result = psm_agent_client.list_available_files()

    mock_request.assert_called_once_with(
        "GET", f"{psm_agent_client.PSM_AGENT_URL}/available-files",
        timeout=psm_agent_client.PSM_CONFIG_TIMEOUT,
    )
    assert result == ["a.ecore"]
