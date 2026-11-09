"""The journal's HTTP surface, over the real app and temporary folders."""
import json

import pytest

from journal import settings
from journal.platforms import PlatformNotFound, PlatformStore

PLATFORM = {
    "name": "Woodpecker CI",
    "output_filename": ".woodpecker.yaml",
    "output_model_name": "WoodpeckerMM",
    "metamodel": "<ecore/>",
    "transformation": "module T;",
    "template": "[template]",
    "source_run_id": "run1",
}


def test_the_journal_says_it_is_healthy(client):
    assert client.get("/health").json() == {"status": "ok"}


# --- histories ------------------------------------------------------------------------


@pytest.mark.parametrize("kind", ["runs", "chats"])
def test_a_history_is_written_added_to_and_read_back(client, kind):
    assert client.put(f"/histories/{kind}/r1", json={"state": {"stage": 0}, "events": [{"n": 1}]}).json() == {"count": 1}

    appended = client.post(f"/histories/{kind}/r1/append", json={"start": 1, "state": {"stage": 1}, "events": [{"n": 2}]})

    assert appended.json() == {"count": 2}
    assert client.get(f"/histories/{kind}/r1").json() == {"state": {"stage": 1}, "events": [{"n": 1}, {"n": 2}]}
    assert client.get(f"/histories/{kind}").json() == {"keys": ["r1"]}


def test_runs_and_chats_are_kept_apart(client):
    client.put("/histories/runs/r1", json={"state": {}, "events": [{"n": 1}]})
    client.put("/histories/chats/r1", json={"state": {"raw_seen": 1}, "events": [{"m": 1}]})

    assert client.get("/histories/runs/r1").json()["events"] == [{"n": 1}]
    assert client.get("/histories/chats/r1").json()["events"] == [{"m": 1}]


def test_a_kind_of_history_that_does_not_exist_is_not_found(client):
    assert client.get("/histories/mystery").status_code == 404
    assert client.put("/histories/mystery/r1", json={"state": {}, "events": []}).status_code == 404
    assert client.post("/histories/mystery/r1/append", json={"start": 0, "events": []}).status_code == 404


def test_a_history_that_is_not_kept_is_not_found(client):
    assert client.get("/histories/runs/nothing").status_code == 404


def test_an_append_past_the_end_is_a_409_that_says_how_many_events_are_kept(client):
    client.put("/histories/runs/r1", json={"state": {}, "events": [{"n": 1}]})

    response = client.post("/histories/runs/r1/append", json={"start": 5, "events": [{"n": 6}]})

    assert response.status_code == 409
    assert response.json()["count"] == 1
    assert "1 events" in response.json()["detail"]


def test_an_append_that_sends_no_state_changes_is_allowed(client):
    client.put("/histories/runs/r1", json={"state": {"a": 1}, "events": []})

    assert client.post("/histories/runs/r1/append", json={"start": 0, "events": [{"n": 1}]}).json() == {"count": 1}
    assert client.get("/histories/runs/r1").json()["state"] == {"a": 1}


@pytest.mark.parametrize("key", ["..%2Fevil", "a%2Fb", "x" * 65])
def test_a_key_that_cannot_name_a_file_is_not_found_everywhere(client, key):
    assert client.get(f"/histories/runs/{key}").status_code == 404
    assert client.put(f"/histories/runs/{key}", json={"state": {}, "events": []}).status_code == 404
    assert client.post(f"/histories/runs/{key}/append", json={"start": 0, "events": []}).status_code == 404


def test_a_negative_start_is_refused(client):
    assert client.post("/histories/runs/r1/append", json={"start": -1, "events": []}).status_code == 422


def test_a_journal_that_is_not_a_history_is_a_422_that_names_it(client, folders):
    runs, _, _ = folders
    (runs / "bad").mkdir(parents=True)
    (runs / "bad" / "run.jsonl").write_text("{not json\n", encoding="utf-8")

    read = client.get("/histories/runs/bad")
    appended = client.post("/histories/runs/bad/append", json={"start": 0, "events": []})

    assert read.status_code == 422 and "'bad'" in read.json()["detail"]
    assert appended.status_code == 422
    assert client.get("/histories/runs").json() == {"keys": ["bad"]}


# --- attempts -------------------------------------------------------------------------


def test_an_attempt_is_reserved_recorded_and_read_back(client):
    reserved = client.post("/attempts/run1", json={"stage": "psm"})
    assert (reserved.status_code, reserved.json()) == (201, {"attempt": "attempt_1"})

    body = {"filename": "psm.ecore", "content": "<ecore/>", "result": {"valid": True, "issues": []}, "prompt": {"p": 1}, "prompt_version": "v2"}
    assert client.put("/attempts/run1/psm/attempt_1", json=body).json() == {"status": "recorded"}

    assert client.get("/attempts/run1/psm/attempt_1").json() == {
        "artifact": {"filename": "psm.ecore", "content": "<ecore/>"},
        "result": {"valid": True, "issues": []},
        "prompt": {"p": 1},
        "prompt_version": "v2",
    }
    manifest = client.get("/attempts/run1/manifest").json()["attempts"]
    assert [(e["stage"], e["attempt_n"], e["valid"]) for e in manifest] == [("psm", 1, True)]


def test_a_reserved_attempt_can_be_discarded_and_a_recorded_one_cannot(client):
    client.post("/attempts/run1", json={"stage": "psm"})
    client.post("/attempts/run1", json={"stage": "psm"})
    body = {"filename": "psm.ecore", "content": "x", "result": {"valid": False}}
    client.put("/attempts/run1/psm/attempt_2", json=body)

    assert client.delete("/attempts/run1/psm/attempt_1").json() == {"status": "discarded"}
    assert client.delete("/attempts/run1/psm/attempt_2").status_code == 409
    assert client.put("/attempts/run1/psm/attempt_2", json=body).status_code == 409


def test_what_is_not_there_or_cannot_be_a_name_is_not_found(client):
    body = {"filename": "psm.ecore", "content": "x", "result": {"valid": True}}

    assert client.get("/attempts/run1/manifest").status_code == 404
    assert client.get("/attempts/run1/psm/attempt_1").status_code == 404
    assert client.put("/attempts/run1/psm/attempt_1", json=body).status_code == 404
    assert client.delete("/attempts/run1/psm/attempt_1").status_code == 404
    assert client.post("/attempts/run1", json={"stage": "a b"}).status_code == 404
    assert client.get("/attempts/run1/psm/attempt_x").status_code == 404


def test_an_artifact_or_result_that_cannot_be_kept_is_a_422(client):
    client.post("/attempts/run1", json={"stage": "psm"})

    clashing = {"filename": "result.json", "content": "x", "result": {"valid": True}}
    no_verdict = {"filename": "a.ecore", "content": "x", "result": {}}

    assert client.put("/attempts/run1/psm/attempt_1", json=clashing).status_code == 422
    assert client.put("/attempts/run1/psm/attempt_1", json=no_verdict).status_code == 422


# --- platforms ------------------------------------------------------------------------


def test_a_platform_is_published_listed_and_read_with_its_files(client):
    published = client.post("/platforms", json=PLATFORM)

    assert published.status_code == 201
    assert published.json()["version"] == "1.0.0" and published.json()["published"] is True
    listed = client.get("/platforms").json()["platforms"]
    assert [(p["id"], p["version"], p["versions"], p["published"]) for p in listed] == [("woodpecker-ci", "1.0.0", ["1.0.0"], True)]
    read = client.get("/platforms/woodpecker-ci").json()
    assert read["files"] == {"metamodel": "<ecore/>", "transformation": "module T;", "template": "[template]"}
    assert read["output_filename"] == ".woodpecker.yaml"


def test_a_publish_can_choose_its_version_and_a_version_is_read_by_number(client):
    client.post("/platforms", json={**PLATFORM, "version": "2.1.0", "metamodel": "two"})
    client.post("/platforms", json={**PLATFORM, "version": "1.0.0", "metamodel": "one"})

    assert client.get("/platforms/woodpecker-ci").json()["version"] == "2.1.0"
    assert client.get("/platforms/woodpecker-ci?version=1.0.0").json()["files"]["metamodel"] == "one"
    assert client.get("/platforms/woodpecker-ci?version=9.9.9").status_code == 404
    assert client.get("/platforms/nothing").status_code == 404


@pytest.mark.parametrize("error", [PlatformNotFound("gone"), FileNotFoundError("gone")])
def test_a_platform_purged_while_its_files_are_being_read_is_a_404_and_not_a_500(client, monkeypatch, error):
    client.post("/platforms", json=PLATFORM)

    def purged(self, platform_id, version):
        raise error

    monkeypatch.setattr(PlatformStore, "files", purged)

    assert client.get("/platforms/woodpecker-ci").status_code == 404


def test_a_version_that_exists_is_a_409_and_a_bad_one_a_422(client):
    client.post("/platforms", json={**PLATFORM, "version": "1.0.0"})

    assert client.post("/platforms", json={**PLATFORM, "version": "1.0.0"}).status_code == 409
    bad = client.post("/platforms", json={**PLATFORM, "version": "1.0"})
    assert bad.status_code == 422 and "1.2.0" in bad.json()["detail"]


def test_a_publish_that_cannot_be_used_is_a_422_with_the_reason(client):
    response = client.post("/platforms", json={**PLATFORM, "output_filename": "a/b.yml"})

    assert response.status_code == 422 and "one file name" in response.json()["detail"]


def test_a_file_over_the_size_limit_is_a_413_and_nothing_is_kept(client, monkeypatch):
    monkeypatch.setattr(settings, "MAX_ARTIFACT_BYTES", 10)

    response = client.post("/platforms", json={**PLATFORM, "metamodel": "x" * 11})

    assert response.status_code == 413
    assert client.get("/platforms").json() == {"platforms": []}


def test_a_version_and_a_whole_platform_can_be_purged(client):
    for _ in range(2):
        client.post("/platforms", json=PLATFORM)

    assert client.delete("/platforms/woodpecker-ci/versions/1.0.0").json() == {"purged": ["1.0.0"]}
    assert client.delete("/platforms/woodpecker-ci").json() == {"purged": ["1.0.1"]}
    assert client.get("/platforms").json() == {"platforms": []}


def test_purging_what_is_not_there_is_a_404_and_what_came_with_the_stack_is_a_409(client, folders):
    _, _, platforms = folders
    shipped = platforms / "gitlab" / "1.0.0"
    shipped.mkdir(parents=True)
    for filename in ("metamodel.ecore", "transformation.atl", "template.mtl"):
        (shipped / filename).write_text("x", encoding="utf-8")
    (shipped / "platform.json").write_text(
        json.dumps({"id": "gitlab", "name": "GitLab CI", "version": "1.0.0", "output_filename": "g.yml", "output_model_name": "G"}),
        encoding="utf-8",
    )

    assert client.delete("/platforms/nothing").status_code == 404
    assert client.delete("/platforms/gitlab/versions/9.9.9").status_code == 404
    refused = client.delete("/platforms/gitlab/versions/1.0.0")
    assert refused.status_code == 409 and "came with the stack" in refused.json()["detail"]
    assert client.delete("/platforms/gitlab").status_code == 409
    assert shipped.is_dir()


def test_the_swarch_to_pim_transformation_is_served_or_not_found(client, folders):
    _, _, platforms = folders
    assert client.get("/platforms/pim-transformation").status_code == 404

    (platforms / "swarch2pim.atl").write_text("module S;", encoding="utf-8")

    assert client.get("/platforms/pim-transformation").json() == {"transformation": "module S;"}
