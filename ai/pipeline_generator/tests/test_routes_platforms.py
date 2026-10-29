"""Publishing a platform and generating for it, through the real app. The
registry is real (on a temporary folder); the model check and the published
run's own steps are mocked."""
from unittest.mock import patch

import pytest
from fastapi.testclient import TestClient

import main
from helpers import generate

client = TestClient(main.app)

PUBLISH = {
    "name": "Woodpecker CI",
    "output_filename": ".woodpecker/pipeline.yaml",
    "output_model_name": "WoodMM",
    "metamodel": "<ecore/>",
    "transformation": "<atl/>",
    "template": "<mtl/>",
    "source_run_id": "run1",
}
MODEL = {"filename": "app.swarch", "content": "<swarch:SoftwareArchitecture xmlns:swarch='urn:x' name='a'/>"}
OK = {"valid": True, "issues": [], "duration_ms": 5}


def _published_result(text="steps: []\n"):
    return {
        "pipeline": text,
        "intermediates": [{"id": "pim", "title": "Platform-independent model", "filename": "PipelinePIM.pimmm", "content": "<pim/>"}],
        "duration_ms": 50,
    }


def test_publishing_returns_the_new_version_and_the_platform_is_then_listed():
    response = client.post("/platforms", json=PUBLISH)

    assert response.status_code == 201
    assert response.json() == {"id": "woodpecker-ci", "name": "Woodpecker CI", "version": 1,
                               "output_filename": ".woodpecker/pipeline.yaml"}
    platforms = client.get("/platforms").json()["platforms"]
    assert [p["id"] for p in platforms] == ["gitlab", "woodpecker-ci"]
    assert platforms[1] == {"id": "woodpecker-ci", "name": "Woodpecker CI", "output_filename": ".woodpecker/pipeline.yaml",
                            "source": "published", "version": 1}


def test_publishing_again_lists_only_the_latest_version():
    client.post("/platforms", json=PUBLISH)
    client.post("/platforms", json=PUBLISH)

    [published] = [p for p in client.get("/platforms").json()["platforms"] if p["source"] == "published"]
    assert published["version"] == 2


@pytest.mark.parametrize("overrides", [{"name": "GitLab"}, {"output_filename": "../x.yml"}, {"name": "!!!"}])
def test_what_cannot_be_published_is_refused_with_a_reason(overrides):
    response = client.post("/platforms", json={**PUBLISH, **overrides})

    assert response.status_code == 422
    assert response.json()["detail"]


def test_an_artifact_over_the_size_limit_is_refused(monkeypatch):
    monkeypatch.setattr("routes.platforms.MAX_ARTIFACT_BYTES", 10)

    assert client.post("/platforms", json={**PUBLISH, "template": "x" * 11}).status_code == 413


def test_a_published_platform_can_be_generated_saved_into_its_subfolder_and_reopened(workspace_dir):
    client.post("/platforms", json=PUBLISH)

    with patch("main.validator_agent_client.validate_swarch", return_value=OK), \
            patch("generation.run_published", return_value=_published_result()) as run:
        response = generate(client, {**MODEL, "platforms": ["woodpecker-ci"], "save_to": "/ci"})

    body = response.json()
    [result] = body["results"]
    assert response.status_code == 200 and result["error"] is None
    assert run.call_args.args[1].version == 1
    assert result["saved_to"] == "/ci/.woodpecker/pipeline.yaml"
    assert (workspace_dir / "ci" / ".woodpecker" / "pipeline.yaml").read_text(encoding="utf-8") == "steps: []\n"
    reopened = client.get(f"/pipelines/{body['pipeline_id']}").json()
    assert reopened["results"][0]["pipeline"] == "steps: []\n"


def test_gitlab_and_a_published_platform_generate_side_by_side():
    client.post("/platforms", json=PUBLISH)

    with patch("main.validator_agent_client.validate_swarch", return_value=OK), \
            patch("generation.run_engine", return_value={"pipeline": "stages: [build]\n", "intermediates": [], "duration_ms": 1}), \
            patch("generation.run_published", return_value=_published_result()):
        results = generate(client, {**MODEL, "platforms": ["gitlab", "woodpecker-ci"]}).json()["results"]

    assert [(r["platform"]["id"], r["error"]) for r in results] == [("gitlab", None), ("woodpecker-ci", None)]


def test_a_published_platform_that_fails_is_reported_in_its_own_entry_without_detail():
    from published_runner import PublishedRunError

    client.post("/platforms", json=PUBLISH)
    with patch("main.validator_agent_client.validate_swarch", return_value=OK), \
            patch("generation.run_published", side_effect=PublishedRunError("execution-agent said /tmp/secret")):
        [result] = generate(client, {**MODEL, "platforms": ["woodpecker-ci"]}).json()["results"]

    assert result["pipeline"] is None
    assert "/tmp/" not in result["error"]


def test_validating_a_published_platforms_yaml_uses_the_generic_check():
    client.post("/platforms", json=PUBLISH)

    good = client.post("/validate", json={"platform": "woodpecker-ci", "content": "steps:\n  - name: build\n"}).json()
    bad = client.post("/validate", json={"platform": "woodpecker-ci", "content": "steps: [\n"}).json()

    assert good == {"valid": True, "checks": [{"id": "yaml", "title": "The file is valid YAML", "status": "pass", "details": []}]}
    assert bad["valid"] is False and bad["checks"][0]["status"] == "fail"


def test_an_unknown_platform_is_still_refused_and_the_supported_list_names_published_ones():
    client.post("/platforms", json=PUBLISH)

    response = client.post("/validate", json={"platform": "nope", "content": "x"})

    assert response.status_code == 422
    assert "woodpecker-ci" in response.json()["detail"]
