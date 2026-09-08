"""integration_runner_client.py unit tests: focused on the newly added
attempt-introspection and psm prompt-config pass-throughs (this module had
no test file before). Every real business-error/timeout mechanism these
share (_request()) is exercised already via the shape shown here; this
doesn't re-test IntegrationRunnerError itself, only that each new function
hits the real endpoint path with the real payload/params.

Mocks httpx.request directly (the real network boundary), matching
test_psm_agent_client.py's own pattern.
"""
from unittest.mock import patch

import integration_runner_client
from helpers import _fake_httpx_response_raw


def test_get_run_manifest_hits_the_real_endpoint():
    with patch("integration_runner_client.httpx.request", return_value=_fake_httpx_response_raw({"attempts": [{"stage": "psm"}]})) as mock_request:
        result = integration_runner_client.get_run_manifest("run-1")

    mock_request.assert_called_once_with(
        "GET", f"{integration_runner_client.INTEGRATION_RUNNER_URL}/runs/run-1/manifest", timeout=10.0,
    )
    assert result == [{"stage": "psm"}]


def test_get_attempt_hits_the_real_endpoint():
    attempt = {"artifact": {"filename": "psm.ecore", "content": "<ecore/>"}, "result": {"valid": True}}
    with patch("integration_runner_client.httpx.request", return_value=_fake_httpx_response_raw(attempt)) as mock_request:
        result = integration_runner_client.get_attempt("run-1", "psm", "attempt_1")

    mock_request.assert_called_once_with(
        "GET", f"{integration_runner_client.INTEGRATION_RUNNER_URL}/runs/run-1/psm/attempt_1", timeout=10.0,
    )
    assert result == attempt


def test_list_psm_presets():
    with patch("integration_runner_client.httpx.request", return_value=_fake_httpx_response_raw({"presets": [{"id": "default"}]})):
        result = integration_runner_client.list_psm_presets("generation")

    assert result == [{"id": "default"}]


def test_get_psm_prompt_config_hits_the_real_endpoint():
    config = {"system_prompt": "x", "attachments": []}
    with patch("integration_runner_client.httpx.request", return_value=_fake_httpx_response_raw(config)) as mock_request:
        result = integration_runner_client.get_psm_prompt_config("generation", "default")

    mock_request.assert_called_once_with(
        "GET", f"{integration_runner_client.INTEGRATION_RUNNER_URL}/psm/prompt-config/generation/default",
        timeout=10.0,
    )
    assert result == config


def test_save_psm_prompt_config_puts_the_real_body():
    config = {"system_prompt": "x", "attachments": []}
    with patch("integration_runner_client.httpx.request", return_value=_fake_httpx_response_raw({**config, "_version": "v1"})) as mock_request:
        result = integration_runner_client.save_psm_prompt_config("generation", "default", config)

    mock_request.assert_called_once_with(
        "PUT", f"{integration_runner_client.INTEGRATION_RUNNER_URL}/psm/prompt-config/generation/default",
        timeout=10.0, json=config,
    )
    assert result["_version"] == "v1"


def test_get_psm_prompt_config_history():
    with patch("integration_runner_client.httpx.request", return_value=_fake_httpx_response_raw({"versions": ["v1", "v2"]})):
        result = integration_runner_client.get_psm_prompt_config_history("generation", "default")

    assert result == ["v1", "v2"]


def test_diff_psm_prompt_config_versions_sends_query_params():
    with patch("integration_runner_client.httpx.request", return_value=_fake_httpx_response_raw({"system_prompt_changed": False})) as mock_request:
        integration_runner_client.diff_psm_prompt_config_versions("generation", "default", "v1", "v2")

    assert mock_request.call_args.kwargs["params"] == {"a": "v1", "b": "v2"}


def test_restore_psm_prompt_config_version():
    with patch("integration_runner_client.httpx.request", return_value=_fake_httpx_response_raw({"system_prompt": "restored"})) as mock_request:
        result = integration_runner_client.restore_psm_prompt_config_version("generation", "default", "v1")

    mock_request.assert_called_once_with(
        "POST", f"{integration_runner_client.INTEGRATION_RUNNER_URL}/psm/prompt-config/generation/default/restore/v1",
        timeout=10.0,
    )
    assert result["system_prompt"] == "restored"


def test_revert_psm_prompt_config():
    with patch("integration_runner_client.httpx.request", return_value=_fake_httpx_response_raw({"system_prompt": "shipped"})) as mock_request:
        integration_runner_client.revert_psm_prompt_config("generation", "default")

    mock_request.assert_called_once_with(
        "POST", f"{integration_runner_client.INTEGRATION_RUNNER_URL}/psm/prompt-config/generation/default/revert",
        timeout=10.0,
    )


def test_promote_psm_prompt_config_to_default():
    with patch("integration_runner_client.httpx.request", return_value=_fake_httpx_response_raw({"system_prompt": "x"})) as mock_request:
        integration_runner_client.promote_psm_prompt_config_to_default("generation", "default")

    mock_request.assert_called_once_with(
        "POST", f"{integration_runner_client.INTEGRATION_RUNNER_URL}/psm/prompt-config/generation/default/promote-to-default",
        timeout=10.0,
    )


def test_check_psm_prompt_config_references():
    with patch("integration_runner_client.httpx.request", return_value=_fake_httpx_response_raw({"broken": [{"id": "a"}]})):
        result = integration_runner_client.check_psm_prompt_config_references("generation", "default")

    assert result == [{"id": "a"}]


def test_preview_psm_prompt_config():
    with patch("integration_runner_client.httpx.request", return_value=_fake_httpx_response_raw({"system_prompt": "x", "user_content": "y"})) as mock_request:
        result = integration_runner_client.preview_psm_prompt_config("generation", "default")

    mock_request.assert_called_once_with(
        "POST", f"{integration_runner_client.INTEGRATION_RUNNER_URL}/psm/prompt-config/generation/default/preview",
        timeout=10.0,
    )
    assert result == {"system_prompt": "x", "user_content": "y"}


def test_add_psm_learned_constraints_posts_the_real_body():
    with patch("integration_runner_client.httpx.request", return_value=_fake_httpx_response_raw({"learned_constraints": ["x"]})) as mock_request:
        result = integration_runner_client.add_psm_learned_constraints("generation", "default", ["x"])

    mock_request.assert_called_once_with(
        "POST", f"{integration_runner_client.INTEGRATION_RUNNER_URL}/psm/prompt-config/generation/default/learned-constraints",
        timeout=10.0, json={"constraints": ["x"]},
    )
    assert result == {"learned_constraints": ["x"]}


def test_remove_psm_learned_constraint_deletes_with_the_real_body():
    with patch("integration_runner_client.httpx.request", return_value=_fake_httpx_response_raw({"learned_constraints": []})) as mock_request:
        result = integration_runner_client.remove_psm_learned_constraint("generation", "default", "x")

    mock_request.assert_called_once_with(
        "DELETE", f"{integration_runner_client.INTEGRATION_RUNNER_URL}/psm/prompt-config/generation/default/learned-constraints",
        timeout=10.0, json={"constraint": "x"},
    )
    assert result == {"learned_constraints": []}


def test_list_psm_available_files():
    with patch("integration_runner_client.httpx.request", return_value=_fake_httpx_response_raw({"files": ["a.ecore"]})):
        result = integration_runner_client.list_psm_available_files()

    assert result == ["a.ecore"]


def test_promote_psm_constraints_posts_the_real_body():
    with patch("integration_runner_client.httpx.request", return_value=_fake_httpx_response_raw({"learned_constraints": ["x"]})) as mock_request:
        result = integration_runner_client.promote_psm_constraints(["x"])

    mock_request.assert_called_once_with(
        "POST", f"{integration_runner_client.INTEGRATION_RUNNER_URL}/psm/promote-constraints",
        timeout=10.0, json={"constraints": ["x"]},
    )
    assert result == {"learned_constraints": ["x"]}
