"""The platform list and generating for a published platform, through the real
app. The journal is real (over a temporary copy of the platforms folder); the
model check and the steps run in other services are mocked."""
from unittest.mock import patch

import httpx
from fastapi.testclient import TestClient

import main
from clients import journal_client
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


def test_the_list_shows_the_shipped_platform_and_a_published_one_with_their_versions():
    journal_client.publish_platform(**PUBLISH)
    journal_client.publish_platform(**PUBLISH)

    platforms = {p["id"]: p for p in client.get("/platforms").json()["platforms"]}

    assert platforms["gitlab"] == {
        "id": "gitlab",
        "name": "GitLab CI",
        "output_filename": ".gitlab-ci.yml",
        "version": "1.0.0",
        "versions": ["1.0.0"],
        "published": False,
    }
    assert platforms["woodpecker-ci"]["version"] == "1.0.1"
    assert platforms["woodpecker-ci"]["versions"] == ["1.0.0", "1.0.1"]
    assert platforms["woodpecker-ci"]["published"] is True


def test_a_published_platform_is_listed_once_at_its_latest_version():
    for _ in range(3):
        journal_client.publish_platform(**PUBLISH)

    ids = [p["id"] for p in client.get("/platforms").json()["platforms"]]

    assert ids.count("woodpecker-ci") == 1


def test_a_purged_platform_is_no_longer_listed():
    journal_client.publish_platform(**PUBLISH)
    journal_client.purge_platform("woodpecker-ci")

    assert [p["id"] for p in client.get("/platforms").json()["platforms"]] == ["gitlab"]


def test_a_published_platform_is_generated_with_its_own_latest_version():
    journal_client.publish_platform(**PUBLISH)
    journal_client.publish_platform(**PUBLISH, version="2.1.0")
    journal_client.publish_platform(**PUBLISH, version="1.9.0")
    result = {"pipeline": "steps: []\n", "intermediates": [], "duration_ms": 1}

    with patch("main.validator_agent_client.validate_swarch", return_value=OK), \
            patch("generation.run_platform", return_value=result) as run:
        [generated] = generate(client, {**MODEL, "platforms": ["woodpecker-ci"]}).json()["results"]

    assert generated["error"] is None
    assert run.call_args.args[1].version == "2.1.0"


def test_a_platform_that_was_purged_is_refused_as_unsupported():
    journal_client.publish_platform(**PUBLISH)
    journal_client.purge_platform("woodpecker-ci")

    with patch("main.validator_agent_client.validate_swarch", return_value=OK):
        response = client.post("/generate", json={**MODEL, "platforms": ["woodpecker-ci"]})

    assert response.status_code == 422
    assert "Unsupported platform 'woodpecker-ci'" in response.json()["detail"]


def test_a_journal_that_cannot_be_reached_is_a_502_that_does_not_echo_its_error():
    def down(*args, **kwargs):
        raise httpx.ConnectError("connect to http://journal:8120 refused")

    with patch.object(journal_client.httpx, "request", side_effect=down):
        listed = client.get("/platforms")
        generated = client.post("/generate", json={**MODEL, "platforms": ["gitlab"]})

    assert listed.status_code == 502 and generated.status_code == 502
    assert "journal" not in listed.text and "journal" not in generated.text
