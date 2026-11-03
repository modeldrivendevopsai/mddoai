"""POST /validate/pipeline through the real app: nothing is mocked, it is pure
Python."""
from fastapi.testclient import TestClient

import main

client = TestClient(main.app)

SCHEMA = {"type": "object", "required": ["name"], "properties": {"name": {"type": "string"}}}


def _post(**overrides):
    body = {"content": "name: a\n", "filename": ".x.yml", "platform_name": "X CI", **overrides}
    return client.post("/validate/pipeline", json=body)


def test_a_pipeline_is_checked_against_the_schema_and_rules_it_comes_with():
    response = _post(json_schema=SCHEMA, rules=["gitlab-references"])

    assert response.status_code == 200
    body = response.json()
    assert [(c["id"], c["status"]) for c in body["checks"]][:2] == [("yaml", "pass"), ("schema", "pass")]
    assert body["checks"][2]["id"] == "jobs"


def test_a_failing_pipeline_is_still_a_normal_200_answer():
    response = _post(content="name: 5\n", json_schema=SCHEMA)

    assert response.status_code == 200
    assert response.json()["valid"] is False


def test_without_a_schema_the_schema_check_is_skipped_and_says_why():
    body = _post().json()

    assert [(c["id"], c["status"]) for c in body["checks"]] == [("yaml", "pass"), ("schema", "skipped")]
    assert body["checks"][1]["details"] == ["X CI has no schema to check against."]


def test_a_schema_that_is_not_a_json_schema_is_refused_with_the_reason():
    response = _post(json_schema={"type": 5})

    assert response.status_code == 422
    assert "not a valid JSON schema" in response.json()["detail"]


def test_a_rule_set_that_does_not_exist_is_refused_naming_it():
    response = _post(rules=["no-such-rules"])

    assert response.status_code == 422
    assert "no-such-rules" in response.json()["detail"]


def test_a_rule_set_named_twice_or_empty_is_refused():
    assert _post(rules=["gitlab-references", "gitlab-references"]).status_code == 422
    assert _post(rules=[""]).status_code == 422


def test_content_over_the_limit_is_refused(monkeypatch):
    monkeypatch.setattr(main, "MAX_PIPELINE_BYTES", 5)

    assert _post(content="name: a\n").status_code == 413


def test_a_schema_over_the_limit_is_refused(monkeypatch):
    monkeypatch.setattr(main, "MAX_SCHEMA_BYTES", 10)

    assert _post(json_schema=SCHEMA).status_code == 413


def test_missing_fields_are_refused():
    assert client.post("/validate/pipeline", json={"content": "a: b\n"}).status_code == 422
