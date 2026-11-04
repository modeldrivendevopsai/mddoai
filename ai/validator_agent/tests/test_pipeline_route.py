"""POST /validate/pipeline through the real app: nothing is mocked, it is pure
Python."""
from fastapi.testclient import TestClient

import main

client = TestClient(main.app)


def _post(**overrides):
    return client.post("/validate/pipeline", json={"content": "name: a\n", "filename": ".x.yml", **overrides})


def test_a_pipeline_is_checked_as_yaml():
    response = _post()

    assert response.status_code == 200
    assert [(c["id"], c["status"]) for c in response.json()["checks"]] == [("yaml", "pass")]


def test_a_failing_pipeline_is_still_a_normal_200_answer():
    response = _post(content="name: [a\n")

    assert response.status_code == 200
    assert response.json()["valid"] is False


def test_a_file_that_is_not_yaml_is_skipped():
    body = _post(filename="Jenkinsfile").json()

    assert [(c["id"], c["status"]) for c in body["checks"]] == [("format", "skipped")]


def test_content_over_the_limit_is_refused(monkeypatch):
    monkeypatch.setattr(main, "MAX_PIPELINE_BYTES", 5)

    assert _post(content="name: a\n").status_code == 413


def test_missing_fields_are_refused():
    assert client.post("/validate/pipeline", json={"content": "a: b\n"}).status_code == 422
