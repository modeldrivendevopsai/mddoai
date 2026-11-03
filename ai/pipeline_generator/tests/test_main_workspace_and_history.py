"""The workspace and pipelines routes, and /generate's recording,
through the real app. Only the validator call and the generation steps in
other services are mocked, as in test_main.py."""
import json
from unittest.mock import patch

from fastapi.testclient import TestClient

import main
from helpers import generate
from platform_runner import PlatformRunError

client = TestClient(main.app)

MODEL = {"filename": "app.swarch", "content": "<swarch:SoftwareArchitecture xmlns:swarch='urn:x' name='a'/>"}
OK = {"valid": True, "issues": [], "duration_ms": 5}


def _run_result(text="stages: [build]\n"):
    return {"pipeline": text, "intermediates": [], "duration_ms": 100}


def _generate(**overrides):
    with patch("main.validator_agent_client.validate_swarch", return_value=OK), \
            patch("generation.run_platform", return_value=_run_result()):
        return generate(client, {**MODEL, "platforms": ["gitlab"], **overrides})


# --- /workspace -------------------------------------------------------------


def test_browse_lists_the_workspace(workspace_dir):
    (workspace_dir / "repos").mkdir()
    (workspace_dir / "a.swarch").write_text("<a/>", encoding="utf-8")

    body = client.get("/workspace/browse").json()

    assert body["path"] == "/"
    assert body["entries"] == [{"name": "repos", "kind": "dir"}, {"name": "a.swarch", "kind": "file"}]


def test_browse_names_the_folder_it_is_looking_in(monkeypatch):
    monkeypatch.setattr("routes.workspace.WORKSPACE_LABEL", "the MDDOAI repository")

    body = client.get("/workspace/browse").json()

    assert body["root_label"] == "the MDDOAI repository"


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


# --- /generate --------------------------------------------------------------


def test_generate_never_writes_into_the_workspace(workspace_dir):
    _generate()

    assert list(workspace_dir.iterdir()) == []


def test_a_request_still_naming_a_save_folder_is_accepted_and_saves_nothing(workspace_dir):
    response = _generate(save_to="./ci-output/")

    assert response.status_code == 200
    assert "saved_to" not in response.json()["results"][0]
    assert list(workspace_dir.iterdir()) == []


def _store_platform(tmp_path, platform_id, name, output_filename):
    """A second platform in the registry's folder. Its files are placeholders:
    what runs them is mocked in these tests."""
    folder = tmp_path / "platforms" / platform_id / "1"
    folder.mkdir(parents=True)
    meta = {"id": platform_id, "name": name, "version": 1, "output_filename": output_filename, "output_model_name": "XMM"}
    (folder / "platform.json").write_text(json.dumps(meta), encoding="utf-8")
    for filename in ("metamodel.ecore", "transformation.atl", "template.mtl"):
        (folder / filename).write_text("placeholder", encoding="utf-8")


def test_validate_for_a_platform_with_no_schema_sends_none(tmp_path):
    _store_platform(tmp_path, "other", "Other CI", "other.yml")
    answer = {"valid": True, "checks": []}

    with patch("main.validator_agent_client.validate_pipeline", return_value=answer) as validate:
        body = client.post("/validate", json={"platform": "other", "content": "a: b\n"}).json()

    assert body == answer
    assert validate.call_args.args == ("a: b\n", "other.yml", "Other CI", None)


def test_validate_sends_the_schema_the_platform_has(tmp_path):
    _store_platform(tmp_path, "other", "Other CI", "other.yml")
    (tmp_path / "platforms" / "other" / "1" / "schema.json").write_text('{"type": "object"}', encoding="utf-8")

    with patch("main.validator_agent_client.validate_pipeline", return_value={"valid": True, "checks": []}) as validate:
        client.post("/validate", json={"platform": "other", "content": "a: b\n"})

    assert validate.call_args.args[3] == {"type": "object"}


def test_validate_with_a_schema_file_that_cannot_be_read_is_a_generic_error_and_asks_nobody(tmp_path):
    _store_platform(tmp_path, "other", "Other CI", "other.yml")
    (tmp_path / "platforms" / "other" / "1" / "schema.json").write_text("{not json", encoding="utf-8")

    with patch("main.validator_agent_client.validate_pipeline") as validate:
        response = client.post("/validate", json={"platform": "other", "content": "a: b\n"})

    assert response.status_code == 500
    assert response.json()["detail"] == "The platform's checks could not be used."
    assert "not json" not in response.text
    validate.assert_not_called()


def test_a_bad_schema_file_affects_only_validation_not_listing_or_generating(tmp_path):
    _store_platform(tmp_path, "other", "Other CI", "other.yml")
    (tmp_path / "platforms" / "other" / "1" / "schema.json").write_text("{not json", encoding="utf-8")

    listed = [p["id"] for p in client.get("/platforms").json()["platforms"]]
    with patch("main.validator_agent_client.validate_swarch", return_value=OK), \
            patch("generation.run_platform", return_value=_run_result()):
        result = generate(client, {**MODEL, "platforms": ["other"]}).json()["results"][0]

    assert "other" in listed
    assert result["error"] is None and result["pipeline"]


def test_generate_with_several_platforms_returns_one_result_each_and_isolates_a_failure(tmp_path):
    _store_platform(tmp_path, "other", "Other CI", "other.yml")

    def run(content, platform, registry):
        if platform.id == "other":
            raise PlatformRunError("boom at /secret")
        return _run_result()

    with patch("main.validator_agent_client.validate_swarch", return_value=OK), \
            patch("generation.run_platform", side_effect=run):
        response = generate(client, {**MODEL, "platforms": ["gitlab", "other"]})

    results = response.json()["results"]
    assert [r["platform"]["id"] for r in results] == ["gitlab", "other"]
    assert results[0]["error"] is None and results[0]["pipeline"]
    assert results[1]["pipeline"] is None and "failed" in results[1]["error"]
    assert "secret" not in response.text


def test_naming_a_platform_twice_generates_it_once():
    with patch("main.validator_agent_client.validate_swarch", return_value=OK), \
            patch("generation.run_platform", return_value=_run_result()) as run:
        results = generate(client, {**MODEL, "platforms": ["gitlab", "gitlab"]}).json()["results"]

    assert len(results) == 1
    assert run.call_count == 1


def test_generate_needs_at_least_one_platform():
    assert generate(client, {**MODEL, "platforms": []}).status_code == 422


def test_one_unsupported_platform_refuses_the_whole_request_before_anything_runs():
    with patch("main.validator_agent_client.validate_swarch", return_value=OK), \
            patch("generation.run_platform") as run:
        response = generate(client, {**MODEL, "platforms": ["gitlab", "jenkins"]})

    assert response.status_code == 422
    run.assert_not_called()


# --- /generate: recording, /pipelines ---------------------------------------


def test_a_generated_pipeline_appears_in_the_list_and_can_be_reopened():
    body = _generate(source_path="/repos/app.swarch").json()

    listed = client.get("/pipelines").json()["pipelines"]
    assert [row["id"] for row in listed] == [body["pipeline_id"]]
    assert listed[0]["swarch_name"] == "app.swarch"
    assert listed[0]["source_path"] == "/repos/app.swarch"
    assert listed[0]["platforms"] == [{"id": "gitlab", "name": "GitLab CI"}]
    assert listed[0]["outputs"] == [".gitlab-ci.yml"]
    assert "key" not in listed[0]
    opened = client.get(f"/pipelines/{body['pipeline_id']}").json()
    assert opened["content"] == MODEL["content"]
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
            patch("generation.run_platform", side_effect=PlatformRunError("boom")):
        body = generate(client, {**MODEL, "platforms": ["gitlab"]}).json()

    assert body["pipeline_id"] is None
    assert client.get("/pipelines").json()["pipelines"] == []


def test_a_refused_model_is_not_recorded():
    invalid = {"valid": False, "issues": [{"severity": "ERROR", "message": "bad", "source": "x"}], "duration_ms": 1}
    with patch("main.validator_agent_client.validate_swarch", return_value=invalid):
        generate(client, {**MODEL, "platforms": ["gitlab"]})

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



# --- keys -------------------------------------------------------------------


def test_an_upload_called_like_a_workspace_path_does_not_replace_that_paths_pipeline():
    _generate(source_path="/repos/a.swarch", filename="a.swarch")
    _generate(filename="/repos/a.swarch")

    assert len(client.get("/pipelines").json()["pipelines"]) == 2

# --- review follow-ups ---------------------------------------------------------


def test_a_body_not_sent_as_json_is_refused_and_nothing_runs(workspace_dir):
    # What a web page can send to a local service without a CORS preflight: a
    # "simple" request with no JSON content type. It must not act.
    import json

    body = json.dumps({**MODEL, "platforms": ["gitlab"]})
    with patch("main.validator_agent_client.validate_swarch", return_value=OK), \
            patch("generation.run_platform", return_value=_run_result()) as run:
        plain = client.post("/generate", content=body, headers={"Content-Type": "text/plain"})
        bare = client.post("/generate", content=body)

    assert plain.status_code == 422
    assert bare.status_code == 422
    run.assert_not_called()
    assert list(workspace_dir.iterdir()) == []


def test_an_id_that_is_not_an_id_is_simply_not_found():
    for bad in ("abc", "G" * 32, "0" * 31, "%2e%2e"):
        assert client.get(f"/pipelines/{bad}").status_code == 404


def test_the_workspace_label_falls_back_to_the_folders_own_path(monkeypatch):
    import importlib

    import settings

    monkeypatch.delenv("GENERATOR_WORKSPACE_LABEL", raising=False)
    monkeypatch.setenv("GENERATOR_WORKSPACE_DIR", "/some/workspace")
    try:
        reloaded = importlib.reload(settings)
        assert reloaded.WORKSPACE_LABEL == str(reloaded.WORKSPACE_DIR)
        monkeypatch.setenv("GENERATOR_WORKSPACE_LABEL", "the MDDOAI repository")
        assert importlib.reload(settings).WORKSPACE_LABEL == "the MDDOAI repository"
    finally:
        monkeypatch.undo()
        importlib.reload(settings)
