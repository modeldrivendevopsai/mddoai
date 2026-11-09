"""Publishing a platform through the real app and generating for it. The
registry is real (on a temporary copy of the platforms folder); the model check
and the steps run in other services are mocked."""
from unittest.mock import patch

import pytest
from fastapi.testclient import TestClient

import main
from helpers import generate

client = TestClient(main.app)

PUBLISH = {
    "name": "Woodpecker CI",
    "output_filename": ".woodpecker.yaml",
    "output_model_name": "WoodMM",
    "metamodel": "<ecore/>",
    "transformation": "<atl/>",
    "template": "<mtl/>",
    "source_run_id": "run1",
}
MODEL = {"filename": "app.swarch", "content": "<swarch:SoftwareArchitecture xmlns:swarch='urn:x' name='a'/>"}
OK = {"valid": True, "issues": [], "duration_ms": 5}


def test_publishing_returns_the_new_version_and_the_platform_is_then_listed():
    response = client.post("/platforms", json=PUBLISH)

    assert response.status_code == 201
    assert response.json() == {"id": "woodpecker-ci", "name": "Woodpecker CI", "version": 1, "output_filename": ".woodpecker.yaml"}
    platforms = client.get("/platforms").json()["platforms"]
    assert {"id": "woodpecker-ci", "name": "Woodpecker CI", "output_filename": ".woodpecker.yaml"} in platforms


def test_publishing_again_lists_only_the_latest_version():
    client.post("/platforms", json=PUBLISH)
    client.post("/platforms", json=PUBLISH)

    assert client.post("/platforms", json=PUBLISH).json()["version"] == 3
    assert [p["id"] for p in client.get("/platforms").json()["platforms"]].count("woodpecker-ci") == 1


@pytest.mark.parametrize("overrides", [{"output_filename": "../x.yml"}, {"output_filename": "a/b.yml"}, {"name": "!!!"}])
def test_what_cannot_be_published_is_refused_with_a_reason(overrides):
    response = client.post("/platforms", json={**PUBLISH, **overrides})

    assert response.status_code == 422
    assert response.json()["detail"]


def test_an_artifact_over_the_size_limit_is_refused(monkeypatch):
    monkeypatch.setattr("routes.platforms.MAX_ARTIFACT_BYTES", 10)

    assert client.post("/platforms", json={**PUBLISH, "template": "x" * 11}).status_code == 413


def test_a_published_platform_is_generated_with_its_own_latest_version():
    client.post("/platforms", json=PUBLISH)
    client.post("/platforms", json=PUBLISH)
    result = {"pipeline": "steps: []\n", "intermediates": [], "duration_ms": 1}

    with patch("main.validator_agent_client.validate_swarch", return_value=OK), \
            patch("generation.run_platform", return_value=result) as run:
        [generated] = generate(client, {**MODEL, "platforms": ["woodpecker-ci"]}).json()["results"]

    assert generated["error"] is None
    assert run.call_args.args[1].version == 2
