"""The workspace and pipelines routes, and /generate's saving and recording,
through the real app. Only the validator call and the engine subprocess are
mocked, as in test_main.py."""
from unittest.mock import patch

from fastapi.testclient import TestClient

import main
import platforms
from engine_runner import EngineError
from platforms import Platform

client = TestClient(main.app)

MODEL = {"filename": "app.swarch", "content": "<swarch:SoftwareArchitecture xmlns:swarch='urn:x' name='a'/>"}
OK = {"valid": True, "issues": [], "duration_ms": 5}


def _engine_result(text="stages: [build]\n"):
    return {"pipeline": text, "intermediates": [], "duration_ms": 100}


def _generate(**overrides):
    with patch("main.validator_agent_client.validate_swarch", return_value=OK), \
            patch("generation.run_engine", return_value=_engine_result()):
        return client.post("/generate", json={**MODEL, "platforms": ["gitlab"], **overrides})


# --- /workspace -------------------------------------------------------------


def test_browse_lists_the_workspace(workspace_dir):
    (workspace_dir / "repos").mkdir()
    (workspace_dir / "a.swarch").write_text("<a/>", encoding="utf-8")

    body = client.get("/workspace/browse").json()

    assert body["path"] == "/"
    assert body["entries"] == [{"name": "repos", "kind": "dir"}, {"name": "a.swarch", "kind": "file"}]


def test_browse_can_be_limited_to_folders(workspace_dir):
    (workspace_dir / "repos").mkdir()
    (workspace_dir / "a.swarch").write_text("<a/>", encoding="utf-8")

    body = client.get("/workspace/browse", params={"path": "/", "dirs_only": "true"}).json()

    assert [entry["name"] for entry in body["entries"]] == ["repos"]


def test_browse_refuses_a_path_outside_the_workspace():
    response = client.get("/workspace/browse", params={"path": "../.."})

    assert response.status_code == 422
    assert "outside the workspace" in response.json()["detail"]


def test_file_returns_a_model_the_same_way_an_upload_would_arrive(workspace_dir):
    (workspace_dir / "repos").mkdir()
    (workspace_dir / "repos" / "a.swarch").write_text("<a/>", encoding="utf-8")

    response = client.get("/workspace/file", params={"path": "./repos/a.swarch"})

    assert response.json() == {"filename": "a.swarch", "path": "/repos/a.swarch", "content": "<a/>"}


def test_file_reports_a_missing_file_as_not_found():
    assert client.get("/workspace/file", params={"path": "/nope.swarch"}).status_code == 404


def test_file_refuses_a_file_over_the_model_size_limit(workspace_dir):
    (workspace_dir / "big.swarch").write_text("x" * (main.MAX_MODEL_BYTES + 1), encoding="utf-8")

    assert client.get("/workspace/file", params={"path": "/big.swarch"}).status_code == 413


def test_file_requires_a_path():
    assert client.get("/workspace/file").status_code == 422


def test_a_workspace_that_is_not_mounted_is_reported_as_unavailable(tmp_path):
    from routes.dependencies import get_workspace
    from workspace import Workspace

    main.app.dependency_overrides[get_workspace] = lambda: Workspace(tmp_path / "missing")

    assert client.get("/workspace/browse").status_code == 503


# --- /generate: saving ------------------------------------------------------


def test_generate_saves_each_pipeline_into_the_requested_folder(workspace_dir):
    body = _generate(save_to="./ci-output/").json()

    [result] = body["results"]
    assert result["saved_to"] == "/ci-output/.gitlab-ci.yml"
    assert result["save_error"] is None
    assert (workspace_dir / "ci-output" / ".gitlab-ci.yml").read_text(encoding="utf-8") == "stages: [build]\n"


def test_generate_saves_nothing_when_no_folder_is_given(workspace_dir):
    [result] = _generate().json()["results"]

    assert result["saved_to"] is None
    assert list(workspace_dir.iterdir()) == []


def test_a_folder_outside_the_workspace_is_reported_but_the_pipeline_is_still_returned(tmp_path):
    [result] = _generate(save_to="../elsewhere").json()["results"]

    assert result["pipeline"] == "stages: [build]\n"
    assert "outside the workspace" in result["save_error"]
    assert not (tmp_path / "elsewhere").exists()


def test_generate_with_several_platforms_returns_one_result_each_and_isolates_a_failure():
    other = Platform("other", "Other CI", "swarch2other", "other.yml", ())

    def engine(content, platform):
        if platform.id == "other":
            raise EngineError("boom at /secret")
        return _engine_result()

    with patch.dict(platforms.PLATFORMS, {"other": other}), \
            patch("main.validator_agent_client.validate_swarch", return_value=OK), \
            patch("generation.run_engine", side_effect=engine):
        response = client.post("/generate", json={**MODEL, "platforms": ["gitlab", "other"]})

    results = response.json()["results"]
    assert [r["platform"]["id"] for r in results] == ["gitlab", "other"]
    assert results[0]["error"] is None and results[0]["pipeline"]
    assert results[1]["pipeline"] is None and "failed" in results[1]["error"]
    assert "secret" not in response.text


def test_naming_a_platform_twice_generates_it_once():
    with patch("main.validator_agent_client.validate_swarch", return_value=OK), \
            patch("generation.run_engine", return_value=_engine_result()) as engine:
        results = client.post("/generate", json={**MODEL, "platforms": ["gitlab", "gitlab"]}).json()["results"]

    assert len(results) == 1
    assert engine.call_count == 1


def test_generate_needs_at_least_one_platform():
    assert client.post("/generate", json={**MODEL, "platforms": []}).status_code == 422


def test_one_unsupported_platform_refuses_the_whole_request_before_anything_runs():
    with patch("main.validator_agent_client.validate_swarch", return_value=OK), \
            patch("generation.run_engine") as engine:
        response = client.post("/generate", json={**MODEL, "platforms": ["gitlab", "jenkins"]})

    assert response.status_code == 422
    engine.assert_not_called()


# --- /generate: recording, /pipelines ---------------------------------------


def test_a_generated_pipeline_appears_in_the_list_and_can_be_reopened():
    body = _generate(source_path="/repos/app.swarch", save_to="./ci-output/").json()

    listed = client.get("/pipelines").json()["pipelines"]
    assert [row["id"] for row in listed] == [body["pipeline_id"]]
    assert listed[0]["swarch_name"] == "app.swarch"
    assert listed[0]["source_path"] == "/repos/app.swarch"
    assert listed[0]["platforms"] == [{"id": "gitlab", "name": "GitLab CI"}]
    assert listed[0]["outputs"] == [".gitlab-ci.yml"]
    assert "key" not in listed[0]
    opened = client.get(f"/pipelines/{body['pipeline_id']}").json()
    assert opened["content"] == MODEL["content"]
    assert opened["save_to"] == "./ci-output/"
    assert opened["results"][0]["pipeline"] == "stages: [build]\n"
    assert "key" not in opened


def test_generating_again_for_the_same_file_keeps_one_row():
    first = _generate(source_path="/repos/app.swarch").json()["pipeline_id"]
    second = _generate(source_path="./repos/app.swarch").json()["pipeline_id"]

    assert first == second
    assert len(client.get("/pipelines").json()["pipelines"]) == 1


def test_an_upload_is_identified_by_its_file_name():
    first = _generate().json()["pipeline_id"]
    other = _generate(filename="other.swarch").json()["pipeline_id"]

    assert first != other


def test_a_source_path_outside_the_workspace_is_not_stored():
    _generate(source_path="../../etc/passwd")

    assert client.get("/pipelines").json()["pipelines"][0]["source_path"] is None


def test_a_failed_generation_is_not_recorded():
    with patch("main.validator_agent_client.validate_swarch", return_value=OK), \
            patch("generation.run_engine", side_effect=EngineError("boom")):
        body = client.post("/generate", json={**MODEL, "platforms": ["gitlab"]}).json()

    assert body["pipeline_id"] is None
    assert client.get("/pipelines").json()["pipelines"] == []


def test_a_refused_model_is_not_recorded():
    invalid = {"valid": False, "issues": [{"severity": "ERROR", "message": "bad", "source": "x"}], "duration_ms": 1}
    with patch("main.validator_agent_client.validate_swarch", return_value=invalid):
        client.post("/generate", json={**MODEL, "platforms": ["gitlab"]})

    assert client.get("/pipelines").json()["pipelines"] == []


def test_failing_to_record_does_not_lose_the_generated_pipeline(history):
    with patch.object(history, "record", side_effect=OSError("disk full")):
        body = _generate().json()

    assert body["pipeline_id"] is None
    assert body["results"][0]["pipeline"] == "stages: [build]\n"


def test_the_list_is_empty_before_anything_is_generated():
    assert client.get("/pipelines").json() == {"pipelines": []}


def test_an_unknown_pipeline_is_not_found():
    assert client.get("/pipelines/" + "0" * 32).status_code == 404



# --- replacing, empty folders, keys -----------------------------------------


def test_generate_says_when_it_replaced_a_file_that_was_already_there():
    first = _generate(save_to="./ci-output/").json()["results"][0]
    second = _generate(save_to="./ci-output/").json()["results"][0]

    assert first["replaced"] is False
    assert second["replaced"] is True


def test_an_empty_save_folder_is_refused_not_read_as_the_workspace_root(workspace_dir):
    response = _generate(save_to="")

    assert response.status_code == 422
    assert list(workspace_dir.iterdir()) == []


def test_an_upload_called_like_a_workspace_path_does_not_replace_that_paths_pipeline():
    _generate(source_path="/repos/a.swarch", filename="a.swarch")
    _generate(filename="/repos/a.swarch")

    assert len(client.get("/pipelines").json()["pipelines"]) == 2

# --- review follow-ups ---------------------------------------------------------


def test_a_body_not_sent_as_json_is_refused_and_nothing_is_saved(workspace_dir):
    # What a web page can send to a local service without a CORS preflight: a
    # "simple" request with no JSON content type. It must not act.
    import json

    body = json.dumps({**MODEL, "platforms": ["gitlab"], "save_to": "./ci-output/"})
    with patch("main.validator_agent_client.validate_swarch", return_value=OK), \
            patch("generation.run_engine", return_value=_engine_result()) as engine:
        plain = client.post("/generate", content=body, headers={"Content-Type": "text/plain"})
        bare = client.post("/generate", content=body)

    assert plain.status_code == 422
    assert bare.status_code == 422
    engine.assert_not_called()
    assert list(workspace_dir.iterdir()) == []


def test_an_id_that_is_not_an_id_is_simply_not_found():
    for bad in ("abc", "G" * 32, "0" * 31, "%2e%2e"):
        assert client.get(f"/pipelines/{bad}").status_code == 404
