"""FastAPI endpoint tests. Mocks only the real boundaries: the call to
validator-agent and the generation steps in other services. Request
validation, error mapping and response shaping all run for real."""
from unittest.mock import patch

import httpx
from fastapi.testclient import TestClient

import main
from helpers import generate
from platform_runner import PlatformRunError

client = TestClient(main.app)

MODEL = {"filename": "app.swarch", "content": "<swarch:SoftwareArchitecture xmlns:swarch='urn:x' name='a' language='python'/>"}


def _valid():
    return {"valid": True, "issues": [], "duration_ms": 5}


def _invalid():
    return {
        "valid": False,
        "issues": [{"severity": "ERROR", "message": "The required feature 'language' of 'SoftwareArchitecture' must be set",
                    "source": "/tmp/tmpabc123/app.swarch"}],
        "duration_ms": 5,
    }


def _run_result():
    return {
        "pipeline": "stages: [build]\n",
        "intermediates": [{"id": "pim", "title": "Platform-independent model", "filename": "PipelinePIM.pimmm", "content": "<pim/>"}],
        "duration_ms": 1200,
    }


def test_health():
    assert client.get("/health").json() == {"status": "ok"}


def test_platforms_lists_what_the_registry_holds():
    body = client.get("/platforms").json()

    assert {"id": "gitlab", "name": "GitLab CI", "output_filename": ".gitlab-ci.yml"} in body["platforms"]


def test_inspect_returns_the_attributes_and_a_passing_model_check():
    with patch("main.validator_agent_client.validate_swarch", return_value=_valid()):
        response = client.post("/inspect", json=MODEL)

    assert response.status_code == 200
    body = response.json()
    assert body["element"] == "SoftwareArchitecture"
    assert {"name": "language", "value": "python"} in body["attributes"]
    assert body["model_validation"] == {"valid": True, "issues": []}


def test_inspect_reports_an_invalid_model_as_a_normal_answer_without_the_server_path():
    with patch("main.validator_agent_client.validate_swarch", return_value=_invalid()):
        response = client.post("/inspect", json=MODEL)

    assert response.status_code == 200
    validation = response.json()["model_validation"]
    assert validation["valid"] is False
    assert validation["issues"] == [
        {"severity": "ERROR", "message": "The required feature 'language' of 'SoftwareArchitecture' must be set"}
    ]
    assert "tmpabc123" not in response.text


def test_inspect_of_unreadable_xml_shows_nothing_but_still_reports_the_validators_verdict():
    with patch("main.validator_agent_client.validate_swarch", return_value=_invalid()):
        response = client.post("/inspect", json={"filename": "x.swarch", "content": "not xml"})

    body = response.json()
    assert body["element"] is None
    assert body["attributes"] == []
    assert body["model_validation"]["valid"] is False


def test_inspect_forwards_the_content_to_the_validator():
    with patch("main.validator_agent_client.validate_swarch", return_value=_valid()) as validate:
        client.post("/inspect", json=MODEL)

    validate.assert_called_once_with(MODEL["content"], "model.swarch")


def test_inspect_returns_502_when_the_validator_is_unreachable():
    with patch("main.validator_agent_client.validate_swarch", side_effect=httpx.ConnectError("down")):
        response = client.post("/inspect", json=MODEL)

    assert response.status_code == 502


def test_inspect_rejects_oversized_content():
    oversized = {"filename": "x.swarch", "content": "x" * (main.MAX_MODEL_BYTES + 1)}

    assert client.post("/inspect", json=oversized).status_code == 413


def test_inspect_rejects_missing_content():
    assert client.post("/inspect", json={"filename": "x.swarch"}).status_code == 422


def test_inspect_rejects_an_overlong_filename():
    assert client.post("/inspect", json={"filename": "x" * 300, "content": "<a/>"}).status_code == 422


def test_generate_returns_one_result_per_platform_with_the_pipeline_and_intermediates():
    with patch("main.validator_agent_client.validate_swarch", return_value=_valid()), \
            patch("generation.run_platform", return_value=_run_result()) as run:
        response = generate(client, {**MODEL, "platforms": ["gitlab"]})

    assert response.status_code == 200
    body = response.json()
    [result] = body["results"]
    assert result["platform"] == {"id": "gitlab", "name": "GitLab CI"}
    assert result["output_filename"] == ".gitlab-ci.yml"
    assert result["pipeline"] == "stages: [build]\n"
    assert result["intermediates"][0]["id"] == "pim"
    assert result["error"] is None
    assert run.call_args.args[0] == MODEL["content"]
    assert run.call_args.args[1].id == "gitlab"


def test_generate_answers_at_once_with_a_job_to_fetch():
    with patch("main.validator_agent_client.validate_swarch", return_value=_valid()), \
            patch("generation.run_platform", return_value=_run_result()):
        started = client.post("/generate", json={**MODEL, "platforms": ["gitlab"]})
        job = client.get(f"/generate/{started.json()['job_id']}")

    assert started.status_code == 202
    assert set(started.json()) == {"job_id"}
    assert job.status_code == 200
    assert job.json()["state"] == "done"
    assert job.json()["pipeline_id"]


def test_generate_answers_before_generating(workspace_dir, history, registry):
    """With a job store that holds work back, the endpoint has answered and
    the job is running before any platform is ever run."""
    from generation_jobs import GenerationJobs
    from workspace import Workspace

    pending = []
    jobs = GenerationJobs(max_finished=10, run=pending.append)
    request = main.GenerateRequest(**MODEL, platforms=["gitlab"])
    with patch("main.validator_agent_client.validate_swarch", return_value=_valid()), \
            patch("generation.run_platform", return_value=_run_result()) as run:
        started = main.generate_endpoint(request, Workspace(workspace_dir), history, registry, jobs)

        assert jobs.get(started["job_id"]) == {"state": "running"}
        run.assert_not_called()

        pending[0]()

    assert jobs.get(started["job_id"])["state"] == "done"


def test_an_unknown_generation_job_is_404():
    response = client.get("/generate/no-such-job")

    assert response.status_code == 404
    assert "restart" in response.json()["detail"]


def test_a_crash_during_generation_fails_the_job_without_its_error_text():
    with patch("main.validator_agent_client.validate_swarch", return_value=_valid()), \
            patch("generation.run_platform", side_effect=RuntimeError("boom at /secret/path")):
        response = generate(client, {**MODEL, "platforms": ["gitlab"]})

    assert response.status_code == 200
    assert response.json()["state"] == "failed"
    assert "failed" in response.json()["error"]
    assert "secret" not in response.text


def test_generate_refuses_an_invalid_model_and_never_runs_the_engine():
    with patch("main.validator_agent_client.validate_swarch", return_value=_invalid()), \
            patch("generation.run_platform") as run:
        response = generate(client, {**MODEL, "platforms": ["gitlab"]})

    assert response.status_code == 422
    detail = response.json()["detail"]
    assert "language" in detail["issues"][0]["message"]
    assert "tmpabc123" not in response.text
    run.assert_not_called()


def test_generate_rejects_an_unsupported_platform_naming_the_supported_ones():
    with patch("main.validator_agent_client.validate_swarch", return_value=_valid()):
        response = generate(client, {**MODEL, "platforms": ["jenkins"]})

    assert response.status_code == 422
    assert "gitlab" in response.json()["detail"]


def test_generate_hides_the_other_services_own_error_text():
    with patch("main.validator_agent_client.validate_swarch", return_value=_valid()), \
            patch("generation.run_platform", side_effect=PlatformRunError("exited 1: java.lang.Boom at /secret/path")):
        response = generate(client, {**MODEL, "platforms": ["gitlab"]})

    assert response.status_code == 200
    [result] = response.json()["results"]
    assert result["pipeline"] is None
    assert "failed" in result["error"]
    assert "secret" not in response.text
    assert "Boom" not in response.text


def test_generate_returns_502_when_the_validator_is_unreachable():
    with patch("main.validator_agent_client.validate_swarch", side_effect=httpx.ConnectError("down")):
        response = generate(client, {**MODEL, "platforms": ["gitlab"]})

    assert response.status_code == 502


def test_generate_rejects_oversized_content():
    oversized = {"filename": "x.swarch", "content": "x" * (main.MAX_MODEL_BYTES + 1), "platforms": ["gitlab"]}

    assert generate(client, oversized).status_code == 413


CHECKLIST = {"valid": True, "checks": [{"id": "yaml", "title": "The file is valid YAML", "status": "pass", "details": []}]}


def _status_error(status, text):
    request = httpx.Request("POST", "http://validator-agent/validate/pipeline")
    return httpx.HTTPStatusError("failed", request=request, response=httpx.Response(status, text=text, request=request))


def test_validate_asks_validator_agent_with_what_the_platform_says_to_check_against():
    with patch("main.validator_agent_client.validate_pipeline", return_value=CHECKLIST) as validate:
        response = client.post("/validate", json={"platform": "gitlab", "content": "stages: [build]\n"})

    assert response.status_code == 200
    assert response.json() == CHECKLIST
    content, filename, platform_name, schema, rules = validate.call_args.args
    assert (content, filename, platform_name, rules) == ("stages: [build]\n", ".gitlab-ci.yml", "GitLab CI", ["gitlab-references"])
    assert schema["$schema"].startswith("http://json-schema.org/")


def test_validate_reports_a_pipeline_that_fails_its_checks_as_a_normal_answer():
    failing = {"valid": False, "checks": [{"id": "yaml", "title": "t", "status": "fail", "details": ["bad"]}]}
    with patch("main.validator_agent_client.validate_pipeline", return_value=failing):
        response = client.post("/validate", json={"platform": "gitlab", "content": "stages: [build\n"})

    assert response.status_code == 200
    assert response.json() == failing


def test_validate_says_the_checker_is_unavailable_when_validator_agent_cannot_be_reached():
    with patch("main.validator_agent_client.validate_pipeline", side_effect=httpx.ConnectError("down")):
        response = client.post("/validate", json={"platform": "gitlab", "content": "a: b\n"})

    assert response.status_code == 502
    assert response.json()["detail"] == "The pipeline checker is unavailable, try again shortly."


def test_validate_treats_a_server_error_from_validator_agent_as_the_checker_being_unavailable():
    with patch("main.validator_agent_client.validate_pipeline", side_effect=_status_error(500, "boom at /secret")):
        response = client.post("/validate", json={"platform": "gitlab", "content": "a: b\n"})

    assert response.status_code == 502
    assert "secret" not in response.text


def test_validate_says_so_when_validator_agent_finds_the_pipeline_too_large_to_check():
    with patch("main.validator_agent_client.validate_pipeline", side_effect=_status_error(413, "content exceeds 5 bytes")):
        response = client.post("/validate", json={"platform": "gitlab", "content": "a: b\n"})

    assert response.status_code == 413
    assert response.json()["detail"] == "The pipeline is too large to check."


def test_validate_hides_why_the_platforms_checks_could_not_be_used(caplog):
    refusal = _status_error(422, "no rule set is named 'typo'")
    with patch("main.validator_agent_client.validate_pipeline", side_effect=refusal):
        response = client.post("/validate", json={"platform": "gitlab", "content": "a: b\n"})

    assert response.status_code == 500
    assert response.json()["detail"] == "The platform's checks could not be used."
    assert "typo" not in response.text
    assert "no rule set is named 'typo'" in caplog.text


def test_validate_rejects_an_unsupported_platform():
    assert client.post("/validate", json={"platform": "jenkins", "content": "a: b"}).status_code == 422


def test_validate_rejects_oversized_content():
    oversized = {"platform": "gitlab", "content": "x" * (main.MAX_PIPELINE_BYTES + 1)}

    assert client.post("/validate", json=oversized).status_code == 413


def _raw_json(body: str):
    # The JSON escape \ud800 is valid on the wire and parses to a lone
    # surrogate, which UTF-8 cannot encode. The test client refuses to build
    # such a body from a Python str, so the bytes are sent as-is.
    return {"content": body.encode("ascii"), "headers": {"Content-Type": "application/json"}}


def test_validate_rejects_a_lone_surrogate_cleanly():
    response = client.post("/validate", **_raw_json('{"platform": "gitlab", "content": "a: \\ud800"}'))

    assert response.status_code == 422


def test_inspect_rejects_a_lone_surrogate_cleanly():
    with patch("main.validator_agent_client.validate_swarch", return_value=_valid()):
        response = client.post("/inspect", **_raw_json('{"filename": "x.swarch", "content": "<a name=\'\\ud800\'/>"}'))

    assert response.status_code == 422


def test_the_validator_is_always_sent_a_fixed_filename_never_the_callers():
    # The caller's filename must not decide how the model is stored or
    # recognised downstream.
    with patch("main.validator_agent_client.validate_swarch", return_value=_valid()) as validate:
        client.post("/inspect", json={"filename": "../../evil.TXT", "content": MODEL["content"]})
        generate(client, {"filename": "..", "content": MODEL["content"], "platforms": ["gitlab"]})

    assert {call.args[1] for call in validate.call_args_list} == {"model.swarch"}


def test_an_empty_model_is_rejected_with_a_readable_message():
    response = client.post("/inspect", json={"filename": "x.swarch", "content": ""})

    assert response.status_code == 422
    assert isinstance(response.json()["detail"], str)
