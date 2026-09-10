"""main.py's own psm prompt-config endpoints: every one is a one-line proxy
to clients/integration_runner_client.py's own psm prompt-config wrappers,
mocked directly at that boundary - this behavior has no dependency on any
real pipeline/run state, unlike test_main.py's own real cross-service
tests for the actual pipeline endpoints.
"""
from unittest.mock import patch

from fastapi.testclient import TestClient

import main
from clients import integration_runner_client

client = TestClient(main.app)


def test_list_presets_endpoint_proxies_the_real_client():
    with patch.object(integration_runner_client, "list_psm_presets", return_value=[{"id": "default"}]) as mock_list:
        response = client.get("/psm/prompt-config/generation/presets")

    mock_list.assert_called_once_with("generation")
    assert response.json() == {"presets": [{"id": "default"}]}


def test_get_prompt_config_endpoint_proxies_the_real_client():
    config = {"system_prompt": "x", "attachments": []}
    with patch.object(integration_runner_client, "get_psm_prompt_config", return_value=config) as mock_get:
        response = client.get("/psm/prompt-config/generation/default")

    mock_get.assert_called_once_with("generation", "default")
    assert response.json() == config


def test_save_prompt_config_endpoint_forwards_the_real_body():
    body = {"attachments": [], "learned_constraints": [], "label": None, "platform_hints": []}
    with patch.object(integration_runner_client, "save_psm_prompt_config", return_value={**body, "_version": "v1"}) as mock_save:
        response = client.put("/psm/prompt-config/generation/default", json=body)

    mock_save.assert_called_once_with("generation", "default", body)
    assert response.json()["_version"] == "v1"


def test_prompt_config_history_endpoint():
    with patch.object(integration_runner_client, "get_psm_prompt_config_history", return_value=["v1", "v2"]):
        response = client.get("/psm/prompt-config/generation/default/history")

    assert response.json() == {"versions": ["v1", "v2"]}


def test_prompt_config_diff_endpoint_forwards_query_params():
    with patch.object(integration_runner_client, "diff_psm_prompt_config_versions", return_value={"system_prompt_changed": False}) as mock_diff:
        response = client.get("/psm/prompt-config/generation/default/diff", params={"a": "v1", "b": "v2"})

    mock_diff.assert_called_once_with("generation", "default", "v1", "v2")
    assert response.json() == {"system_prompt_changed": False}


def test_restore_prompt_config_endpoint():
    with patch.object(integration_runner_client, "restore_psm_prompt_config_version", return_value={"system_prompt": "restored"}) as mock_restore:
        response = client.post("/psm/prompt-config/generation/default/restore/v1")

    mock_restore.assert_called_once_with("generation", "default", "v1")
    assert response.json()["system_prompt"] == "restored"


def test_revert_prompt_config_endpoint():
    with patch.object(integration_runner_client, "revert_psm_prompt_config", return_value={"system_prompt": "shipped"}) as mock_revert:
        client.post("/psm/prompt-config/generation/default/revert")

    mock_revert.assert_called_once_with("generation", "default")


def test_promote_prompt_config_to_default_endpoint():
    with patch.object(integration_runner_client, "promote_psm_prompt_config_to_default", return_value={"system_prompt": "x"}) as mock_promote:
        client.post("/psm/prompt-config/generation/default/promote-to-default")

    mock_promote.assert_called_once_with("generation", "default")


def test_check_references_endpoint():
    with patch.object(integration_runner_client, "check_psm_prompt_config_references", return_value=[{"id": "a"}]):
        response = client.get("/psm/prompt-config/generation/default/check-references")

    assert response.json() == {"broken": [{"id": "a"}]}


def test_preview_endpoint():
    with patch.object(integration_runner_client, "preview_psm_prompt_config", return_value={"system_prompt": "x", "user_content": "y"}) as mock_preview:
        response = client.post("/psm/prompt-config/generation/default/preview")

    mock_preview.assert_called_once_with("generation", "default")
    assert response.json() == {"system_prompt": "x", "user_content": "y"}


def test_add_learned_constraints_endpoint_forwards_the_real_body():
    with patch.object(integration_runner_client, "add_psm_learned_constraints", return_value={"learned_constraints": ["x"]}) as mock_add:
        response = client.post("/psm/prompt-config/generation/default/learned-constraints", json={"constraints": ["x"]})

    mock_add.assert_called_once_with("generation", "default", ["x"])
    assert response.json() == {"learned_constraints": ["x"]}


def test_remove_learned_constraint_endpoint_forwards_the_real_body():
    with patch.object(integration_runner_client, "remove_psm_learned_constraint", return_value={"learned_constraints": []}) as mock_remove:
        response = client.request(
            "DELETE", "/psm/prompt-config/generation/default/learned-constraints", json={"constraint": "x"}
        )

    mock_remove.assert_called_once_with("generation", "default", "x")
    assert response.json() == {"learned_constraints": []}


def test_available_files_endpoint():
    with patch.object(integration_runner_client, "list_psm_available_files", return_value=["a.ecore"]):
        response = client.get("/psm/available-files")

    assert response.json() == {"files": ["a.ecore"]}


def test_promote_constraints_endpoint_forwards_the_real_body():
    with patch.object(integration_runner_client, "promote_psm_constraints", return_value={"learned_constraints": ["x"]}) as mock_promote:
        response = client.post("/psm/promote-constraints", json={"constraints": ["x"]})

    mock_promote.assert_called_once_with(["x"])
    assert response.json() == {"learned_constraints": ["x"]}


def test_resolve_mode_endpoint_proxies_the_real_client():
    with patch.object(integration_runner_client, "resolve_psm_mode", return_value={"mode": "knowledge", "metamodel_path": "gitlabMM.ecore"}) as mock_resolve:
        response = client.get("/psm/resolve-mode", params={"platform_description": "GitLab CI"})

    mock_resolve.assert_called_once_with("GitLab CI")
    assert response.json() == {"mode": "knowledge", "metamodel_path": "gitlabMM.ecore"}


def test_upload_attachment_endpoint_forwards_a_real_multipart_upload():
    with patch.object(integration_runner_client, "upload_psm_attachment_file", return_value="abc123-model.ecore") as mock_upload:
        response = client.post("/psm/attachment-uploads", files={"file": ("model.ecore", b"<ecore/>", "application/xml")})

    mock_upload.assert_called_once_with("model.ecore", b"<ecore/>")
    assert response.json() == {"path": "abc123-model.ecore"}
