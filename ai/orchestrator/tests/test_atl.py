"""main.py's own atl prompt-config endpoints: every one is a one-line
proxy to clients/integration_runner_client.py's own atl wrappers, mocked
directly at that boundary - mirrors test_prompt_config.py's own
psm-specific shape, minus resolve-mode (atl has no dual mode). This
service's real endpoints all live directly in main.py (no routes/ package -
that split belongs to integration_runner, which owns it); this test file
is still its own, one concern per test file.
"""
from unittest.mock import patch

from fastapi.testclient import TestClient

import main
from clients import integration_runner_client

client = TestClient(main.app)


def test_list_presets_endpoint_proxies_the_real_client():
    with patch.object(integration_runner_client, "list_atl_presets", return_value=[{"id": "default"}]) as mock_list:
        response = client.get("/atl/prompt-config/generation/presets")

    mock_list.assert_called_once_with("generation")
    assert response.json() == {"presets": [{"id": "default"}]}


def test_get_prompt_config_endpoint_proxies_the_real_client():
    config = {"attachments": []}
    with patch.object(integration_runner_client, "get_atl_prompt_config", return_value=config) as mock_get:
        response = client.get("/atl/prompt-config/generation/default")

    mock_get.assert_called_once_with("generation", "default")
    assert response.json() == config


def test_save_prompt_config_endpoint_forwards_the_real_body():
    body = {"attachments": [], "learned_constraints": [], "label": None, "platform_hints": []}
    with patch.object(integration_runner_client, "save_atl_prompt_config", return_value={**body, "_version": "v1"}) as mock_save:
        response = client.put("/atl/prompt-config/generation/default", json=body)

    mock_save.assert_called_once_with("generation", "default", body)
    assert response.json()["_version"] == "v1"


def test_prompt_config_history_endpoint():
    with patch.object(integration_runner_client, "get_atl_prompt_config_history", return_value=["v1", "v2"]):
        response = client.get("/atl/prompt-config/generation/default/history")

    assert response.json() == {"versions": ["v1", "v2"]}


def test_prompt_config_diff_endpoint_forwards_query_params():
    with patch.object(integration_runner_client, "diff_atl_prompt_config_versions", return_value={"attachments_changed": []}) as mock_diff:
        response = client.get("/atl/prompt-config/generation/default/diff", params={"a": "v1", "b": "v2"})

    mock_diff.assert_called_once_with("generation", "default", "v1", "v2")
    assert response.json() == {"attachments_changed": []}


def test_restore_prompt_config_endpoint():
    with patch.object(integration_runner_client, "restore_atl_prompt_config_version", return_value={"attachments": []}) as mock_restore:
        response = client.post("/atl/prompt-config/generation/default/restore/v1")

    mock_restore.assert_called_once_with("generation", "default", "v1")
    assert response.json() == {"attachments": []}


def test_revert_prompt_config_endpoint():
    with patch.object(integration_runner_client, "revert_atl_prompt_config", return_value={"attachments": []}) as mock_revert:
        response = client.post("/atl/prompt-config/generation/default/revert")

    mock_revert.assert_called_once_with("generation", "default")
    assert response.json() == {"attachments": []}


def test_promote_prompt_config_to_default_endpoint():
    with patch.object(integration_runner_client, "promote_atl_prompt_config_to_default", return_value={"attachments": []}) as mock_promote:
        response = client.post("/atl/prompt-config/generation/default/promote-to-default")

    mock_promote.assert_called_once_with("generation", "default")
    assert response.json() == {"attachments": []}


def test_check_prompt_config_references_endpoint():
    with patch.object(integration_runner_client, "check_atl_prompt_config_references", return_value=[]):
        response = client.get("/atl/prompt-config/generation/default/check-references")

    assert response.json() == {"broken": []}


def test_preview_prompt_config_endpoint():
    preview = {"system_prompt": "x", "user_content": "y", "attachments": {}}
    with patch.object(integration_runner_client, "preview_atl_prompt_config", return_value=preview):
        response = client.post("/atl/prompt-config/generation/default/preview")

    assert response.json() == preview


def test_add_learned_constraints_endpoint_forwards_the_real_body():
    with patch.object(integration_runner_client, "add_atl_learned_constraints", return_value={"learned_constraints": ["x"]}) as mock_add:
        response = client.post("/atl/prompt-config/generation/default/learned-constraints", json={"constraints": ["x"]})

    mock_add.assert_called_once_with("generation", "default", ["x"])
    assert response.json() == {"learned_constraints": ["x"]}


def test_remove_learned_constraint_endpoint_forwards_the_real_body():
    with patch.object(integration_runner_client, "remove_atl_learned_constraint", return_value={"learned_constraints": []}) as mock_remove:
        response = client.request("DELETE", "/atl/prompt-config/generation/default/learned-constraints", json={"constraint": "x"})

    mock_remove.assert_called_once_with("generation", "default", "x")
    assert response.json() == {"learned_constraints": []}


def test_available_files_endpoint():
    with patch.object(integration_runner_client, "list_atl_available_files", return_value=["pim2gitlabmodel.atl"]):
        response = client.get("/atl/available-files")

    assert response.json() == {"files": ["pim2gitlabmodel.atl"]}


def test_promote_constraints_endpoint_forwards_the_real_body():
    with patch.object(integration_runner_client, "promote_atl_constraints", return_value={"learned_constraints": ["x"]}) as mock_promote:
        response = client.post("/atl/promote-constraints", json={"constraints": ["x"]})

    mock_promote.assert_called_once_with(["x"])
    assert response.json() == {"learned_constraints": ["x"]}


def test_upload_attachment_endpoint_forwards_a_real_multipart_upload():
    with patch.object(integration_runner_client, "upload_atl_attachment_file", return_value="abc123-model.atl") as mock_upload:
        response = client.post("/atl/attachment-uploads", files={"file": ("model.atl", b"module m;", "text/plain")})

    mock_upload.assert_called_once_with("model.atl", b"module m;")
    assert response.json() == {"path": "abc123-model.atl"}
