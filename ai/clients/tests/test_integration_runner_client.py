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


def test_get_psm_prompt_config_hits_the_real_endpoint():
    config = {"system_prompt": "x", "attachments": []}
    with patch("integration_runner_client.httpx.request", return_value=_fake_httpx_response_raw(config)) as mock_request:
        result = integration_runner_client.get_psm_prompt_config("generation")

    mock_request.assert_called_once_with(
        "GET", f"{integration_runner_client.INTEGRATION_RUNNER_URL}/psm/prompt-config/generation",
        timeout=10.0,
    )
    assert result == config


def test_save_psm_prompt_config_puts_the_real_body():
    config = {"system_prompt": "x", "attachments": []}
    with patch("integration_runner_client.httpx.request", return_value=_fake_httpx_response_raw({**config, "_version": "v1"})) as mock_request:
        result = integration_runner_client.save_psm_prompt_config("generation", config)

    mock_request.assert_called_once_with(
        "PUT", f"{integration_runner_client.INTEGRATION_RUNNER_URL}/psm/prompt-config/generation",
        timeout=10.0, json=config,
    )
    assert result["_version"] == "v1"


def test_get_psm_prompt_config_history():
    with patch("integration_runner_client.httpx.request", return_value=_fake_httpx_response_raw({"versions": ["v1", "v2"]})):
        result = integration_runner_client.get_psm_prompt_config_history("generation")

    assert result == ["v1", "v2"]


def test_diff_psm_prompt_config_versions_sends_query_params():
    with patch("integration_runner_client.httpx.request", return_value=_fake_httpx_response_raw({"system_prompt_changed": False})) as mock_request:
        integration_runner_client.diff_psm_prompt_config_versions("generation", "v1", "v2")

    assert mock_request.call_args.kwargs["params"] == {"a": "v1", "b": "v2"}


def test_restore_psm_prompt_config_version():
    with patch("integration_runner_client.httpx.request", return_value=_fake_httpx_response_raw({"system_prompt": "restored"})) as mock_request:
        result = integration_runner_client.restore_psm_prompt_config_version("generation", "v1")

    mock_request.assert_called_once_with(
        "POST", f"{integration_runner_client.INTEGRATION_RUNNER_URL}/psm/prompt-config/generation/restore/v1",
        timeout=10.0,
    )
    assert result["system_prompt"] == "restored"


def test_revert_psm_prompt_config():
    with patch("integration_runner_client.httpx.request", return_value=_fake_httpx_response_raw({"system_prompt": "shipped"})) as mock_request:
        integration_runner_client.revert_psm_prompt_config("generation")

    mock_request.assert_called_once_with(
        "POST", f"{integration_runner_client.INTEGRATION_RUNNER_URL}/psm/prompt-config/generation/revert",
        timeout=10.0,
    )


def test_promote_psm_prompt_config_to_default():
    with patch("integration_runner_client.httpx.request", return_value=_fake_httpx_response_raw({"system_prompt": "x"})) as mock_request:
        integration_runner_client.promote_psm_prompt_config_to_default("generation")

    mock_request.assert_called_once_with(
        "POST", f"{integration_runner_client.INTEGRATION_RUNNER_URL}/psm/prompt-config/generation/promote-to-default",
        timeout=10.0,
    )


def test_check_psm_prompt_config_references():
    with patch("integration_runner_client.httpx.request", return_value=_fake_httpx_response_raw({"broken": [{"id": "a"}]})):
        result = integration_runner_client.check_psm_prompt_config_references("generation")

    assert result == [{"id": "a"}]


def test_preview_psm_prompt_config():
    with patch("integration_runner_client.httpx.request", return_value=_fake_httpx_response_raw({"system_prompt": "x", "user_content": "y"})) as mock_request:
        result = integration_runner_client.preview_psm_prompt_config("generation")

    mock_request.assert_called_once_with(
        "POST", f"{integration_runner_client.INTEGRATION_RUNNER_URL}/psm/prompt-config/generation/preview",
        timeout=10.0,
    )
    assert result == {"system_prompt": "x", "user_content": "y"}


def test_add_psm_learned_constraints_posts_the_real_body():
    with patch("integration_runner_client.httpx.request", return_value=_fake_httpx_response_raw({"learned_constraints": ["x"]})) as mock_request:
        result = integration_runner_client.add_psm_learned_constraints("generation", ["x"])

    mock_request.assert_called_once_with(
        "POST", f"{integration_runner_client.INTEGRATION_RUNNER_URL}/psm/prompt-config/generation/learned-constraints",
        timeout=10.0, json={"constraints": ["x"]},
    )
    assert result == {"learned_constraints": ["x"]}


def test_remove_psm_learned_constraint_deletes_with_the_real_body():
    with patch("integration_runner_client.httpx.request", return_value=_fake_httpx_response_raw({"learned_constraints": []})) as mock_request:
        result = integration_runner_client.remove_psm_learned_constraint("generation", "x")

    mock_request.assert_called_once_with(
        "DELETE", f"{integration_runner_client.INTEGRATION_RUNNER_URL}/psm/prompt-config/generation/learned-constraints",
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


def test_resolve_psm_mode_sends_platform_description_as_a_query_param():
    with patch("integration_runner_client.httpx.request", return_value=_fake_httpx_response_raw({"mode": "generation", "metamodel_path": None})) as mock_request:
        result = integration_runner_client.resolve_psm_mode("A brand new platform")

    mock_request.assert_called_once_with(
        "GET", f"{integration_runner_client.INTEGRATION_RUNNER_URL}/psm/resolve-mode",
        timeout=10.0, params={"platform_description": "A brand new platform"},
    )
    assert result == {"mode": "generation", "metamodel_path": None}


def test_upload_psm_attachment_file_posts_the_real_multipart_body():
    with patch("integration_runner_client.httpx.request", return_value=_fake_httpx_response_raw({"path": "abc123-model.ecore"})) as mock_request:
        result = integration_runner_client.upload_psm_attachment_file("model.ecore", b"<ecore/>")

    mock_request.assert_called_once_with(
        "POST", f"{integration_runner_client.INTEGRATION_RUNNER_URL}/psm/attachment-uploads",
        timeout=10.0, files={"file": ("model.ecore", b"<ecore/>")},
    )
    assert result == "abc123-model.ecore"


# --- atl/acceleo prompt-config pass-throughs - same shape as psm's own -----
# above, each hitting its own /atl or /acceleo prefix instead.


def test_get_atl_prompt_config_hits_the_real_endpoint():
    config = {"attachments": []}
    with patch("integration_runner_client.httpx.request", return_value=_fake_httpx_response_raw(config)) as mock_request:
        result = integration_runner_client.get_atl_prompt_config("generation")

    mock_request.assert_called_once_with(
        "GET", f"{integration_runner_client.INTEGRATION_RUNNER_URL}/atl/prompt-config/generation",
        timeout=10.0,
    )
    assert result == config


def test_upload_atl_attachment_file_posts_the_real_multipart_body():
    with patch("integration_runner_client.httpx.request", return_value=_fake_httpx_response_raw({"path": "abc123-model.atl"})) as mock_request:
        result = integration_runner_client.upload_atl_attachment_file("model.atl", b"module m;")

    mock_request.assert_called_once_with(
        "POST", f"{integration_runner_client.INTEGRATION_RUNNER_URL}/atl/attachment-uploads",
        timeout=10.0, files={"file": ("model.atl", b"module m;")},
    )
    assert result == "abc123-model.atl"


def test_promote_atl_constraints_hits_the_real_endpoint():
    with patch("integration_runner_client.httpx.request", return_value=_fake_httpx_response_raw({"learned_constraints": ["x"]})) as mock_request:
        result = integration_runner_client.promote_atl_constraints(["x"])

    mock_request.assert_called_once_with(
        "POST", f"{integration_runner_client.INTEGRATION_RUNNER_URL}/atl/promote-constraints",
        timeout=10.0, json={"constraints": ["x"]},
    )
    assert result == {"learned_constraints": ["x"]}


def test_get_acceleo_prompt_config_hits_the_real_endpoint():
    config = {"attachments": []}
    with patch("integration_runner_client.httpx.request", return_value=_fake_httpx_response_raw(config)) as mock_request:
        result = integration_runner_client.get_acceleo_prompt_config("generation")

    mock_request.assert_called_once_with(
        "GET", f"{integration_runner_client.INTEGRATION_RUNNER_URL}/acceleo/prompt-config/generation",
        timeout=10.0,
    )
    assert result == config


def test_upload_acceleo_attachment_file_posts_the_real_multipart_body():
    with patch("integration_runner_client.httpx.request", return_value=_fake_httpx_response_raw({"path": "abc123-model.mtl"})) as mock_request:
        result = integration_runner_client.upload_acceleo_attachment_file("model.mtl", b"[module m('x')]")

    mock_request.assert_called_once_with(
        "POST", f"{integration_runner_client.INTEGRATION_RUNNER_URL}/acceleo/attachment-uploads",
        timeout=10.0, files={"file": ("model.mtl", b"[module m('x')]")},
    )
    assert result == "abc123-model.mtl"


def test_promote_acceleo_constraints_hits_the_real_endpoint():
    with patch("integration_runner_client.httpx.request", return_value=_fake_httpx_response_raw({"learned_constraints": ["x"]})) as mock_request:
        result = integration_runner_client.promote_acceleo_constraints(["x"])

    mock_request.assert_called_once_with(
        "POST", f"{integration_runner_client.INTEGRATION_RUNNER_URL}/acceleo/promote-constraints",
        timeout=10.0, json={"constraints": ["x"]},
    )
    assert result == {"learned_constraints": ["x"]}
