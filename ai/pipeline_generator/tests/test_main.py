"""FastAPI endpoint tests. Mocks only the real boundaries: the call to
validator-agent and the engine subprocess. Request validation, error mapping
and response shaping all run for real."""
from unittest.mock import patch

import httpx
from fastapi.testclient import TestClient

import engine_runner
import main
from engine_runner import EngineError
from helpers import generate

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


def _engine_result():
    return {
        "pipeline": "stages: [build]\n",
        "intermediates": [{"id": "pim", "title": "Platform-independent model", "filename": "PipelinePIM.pimmm", "content": "<pim/>"}],
        "duration_ms": 1200,
    }


def test_health():
    assert client.get("/health").json() == {"status": "ok"}


def test_platforms_lists_what_the_engine_can_generate():
    body = client.get("/platforms").json()

    assert body == {"platforms": [
        {"id": "gitlab", "name": "GitLab CI", "output_filename": ".gitlab-ci.yml", "source": "engine", "version": None}
    ]}


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
            patch("generation.run_engine", return_value=_engine_result()) as engine:
        response = generate(client, {**MODEL, "platforms": ["gitlab"]})

    assert response.status_code == 200
    body = response.json()
    [result] = body["results"]
    assert result["platform"] == {"id": "gitlab", "name": "GitLab CI"}
    assert result["output_filename"] == ".gitlab-ci.yml"
    assert result["pipeline"] == "stages: [build]\n"
    assert result["intermediates"][0]["id"] == "pim"
    assert result["error"] is None
    assert engine.call_args.args[0] == MODEL["content"]
    assert engine.call_args.args[1].id == "gitlab"


def test_generate_answers_at_once_with_a_job_to_fetch():
    with patch("main.validator_agent_client.validate_swarch", return_value=_valid()), \
            patch("generation.run_engine", return_value=_engine_result()):
        started = client.post("/generate", json={**MODEL, "platforms": ["gitlab"]})
        job = client.get(f"/generate/{started.json()['job_id']}")

    assert started.status_code == 202
    assert set(started.json()) == {"job_id"}
    assert job.status_code == 200
    assert job.json()["state"] == "done"
    assert job.json()["pipeline_id"]


def test_generate_answers_before_generating(workspace_dir, history, registry):
    """With a job store that holds work back, the endpoint has answered and
    the job is running before the engine is ever called."""
    from generation_jobs import GenerationJobs
    from workspace import Workspace

    pending = []
    jobs = GenerationJobs(max_finished=10, run=pending.append)
    request = main.GenerateRequest(**MODEL, platforms=["gitlab"])
    with patch("main.validator_agent_client.validate_swarch", return_value=_valid()), \
            patch("generation.run_engine", return_value=_engine_result()) as engine:
        started = main.generate_endpoint(request, Workspace(workspace_dir), history, registry, jobs)

        assert jobs.get(started["job_id"]) == {"state": "running"}
        engine.assert_not_called()

        pending[0]()

    assert jobs.get(started["job_id"])["state"] == "done"


def test_an_unknown_generation_job_is_404():
    response = client.get("/generate/no-such-job")

    assert response.status_code == 404
    assert "restart" in response.json()["detail"]


def test_a_crash_during_generation_fails_the_job_without_its_error_text():
    with patch("main.validator_agent_client.validate_swarch", return_value=_valid()), \
            patch("generation.run_engine", side_effect=RuntimeError("boom at /secret/path")):
        response = generate(client, {**MODEL, "platforms": ["gitlab"]})

    assert response.status_code == 200
    assert response.json()["state"] == "failed"
    assert "failed" in response.json()["error"]
    assert "secret" not in response.text


def test_generate_refuses_an_invalid_model_and_never_runs_the_engine():
    with patch("main.validator_agent_client.validate_swarch", return_value=_invalid()), \
            patch("generation.run_engine") as engine:
        response = generate(client, {**MODEL, "platforms": ["gitlab"]})

    assert response.status_code == 422
    detail = response.json()["detail"]
    assert "language" in detail["issues"][0]["message"]
    assert "tmpabc123" not in response.text
    engine.assert_not_called()


def test_generate_rejects_an_unsupported_platform_naming_the_supported_ones():
    with patch("main.validator_agent_client.validate_swarch", return_value=_valid()):
        response = generate(client, {**MODEL, "platforms": ["jenkins"]})

    assert response.status_code == 422
    assert "gitlab" in response.json()["detail"]


def test_generate_hides_the_engines_own_error_text():
    with patch("main.validator_agent_client.validate_swarch", return_value=_valid()), \
            patch("generation.run_engine", side_effect=EngineError("exited 1: java.lang.Boom at /secret/path")):
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


def test_validate_checks_the_pipeline_for_the_platform():
    response = client.post("/validate", json={"platform": "gitlab", "content": "stages: [build]\nbuild:\n  stage: build\n  script: [echo]\n"})

    assert response.status_code == 200
    body = response.json()
    assert body["valid"] is True
    assert [check["id"] for check in body["checks"]] == ["yaml", "schema", "jobs", "stages", "needs"]


def test_validate_reports_a_broken_pipeline_as_a_normal_answer():
    response = client.post("/validate", json={"platform": "gitlab", "content": "stages: [build\n"})

    assert response.status_code == 200
    assert response.json()["valid"] is False


def test_validate_rejects_an_unsupported_platform():
    assert client.post("/validate", json={"platform": "jenkins", "content": "a: b"}).status_code == 422


def test_validate_rejects_oversized_content():
    oversized = {"platform": "gitlab", "content": "x" * (main.MAX_PIPELINE_BYTES + 1)}

    assert client.post("/validate", json=oversized).status_code == 413


def test_a_pipeline_the_engine_can_produce_can_always_be_validated():
    # The generated-pipeline size ceiling and the validate ceiling are one
    # setting, so a pipeline can never be generated and then refused.
    assert main.MAX_PIPELINE_BYTES == engine_runner.MAX_PIPELINE_BYTES


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
