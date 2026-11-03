"""pipeline_checks.py: the checklist for a generated file. The GitLab cases use
the committed GitLab platform's real schema and rules (see gitlab_platform.py);
the rest hand the checker small schemas of their own."""
import pytest

from check_results import MAX_DETAILS
from pipeline_checks import PlatformChecksError, check_pipeline

SCHEMA = {
    "type": "object",
    "required": ["name"],
    "properties": {"name": {"type": "string"}},
    "additionalProperties": False,
}


def _check(content="name: a\n", filename=".x.yml", schema=None, rules=()):
    return check_pipeline(content, filename, "X CI", schema, list(rules))


def _statuses(result):
    return {entry["id"]: entry["status"] for entry in result["checks"]}


# --- what a platform's own files decide ---------------------------------------


def test_a_platform_without_a_schema_has_its_schema_check_skipped_not_passed():
    result = _check()

    assert _statuses(result) == {"yaml": "pass", "schema": "skipped"}
    assert result["valid"] is True
    assert result["checks"][1]["details"] == ["X CI has no schema to check against."]


def test_a_pipeline_that_follows_the_schema_passes_it_and_the_title_names_the_platform():
    result = _check(schema=SCHEMA)

    assert _statuses(result) == {"yaml": "pass", "schema": "pass"}
    assert result["checks"][1]["title"] == "The settings follow X CI's format"


def test_a_pipeline_that_breaks_the_schema_fails_and_says_where():
    result = _check("name: 5\nextra: 1\n", schema=SCHEMA)

    assert result["valid"] is False
    assert result["checks"][1]["status"] == "fail"
    assert any("name" in detail for detail in result["checks"][1]["details"])


def test_yaml_that_does_not_parse_fails_and_everything_after_it_is_skipped():
    result = _check("a: [b\n", schema=SCHEMA, rules=["gitlab-references"])

    assert result["valid"] is False
    assert "line" in result["checks"][0]["details"][0]
    assert _statuses(result) == {
        "yaml": "fail",
        "schema": "skipped",
        "jobs": "skipped",
        "stages": "skipped",
        "needs": "skipped",
    }


def test_a_top_level_list_is_not_a_pipeline():
    result = _check("- build\n- test\n")

    assert result["valid"] is False
    assert _statuses(result)["yaml"] == "fail"


def test_a_file_that_is_not_yaml_is_skipped_as_a_whole_not_passed():
    result = _check("pipeline { }", filename="Jenkinsfile")

    assert result["valid"] is True
    assert _statuses(result) == {"format": "skipped"}


def test_a_rule_set_that_is_asked_for_is_applied():
    result = _check("job:\n  stage: nowhere\n  script: [a]\n", rules=["gitlab-references"])

    assert _statuses(result)["stages"] == "fail"
    assert result["valid"] is False


# --- what cannot be used is an error, found first, whatever the file holds ----


@pytest.mark.parametrize("content, filename", [("name: a\n", ".x.yml"), ("a: [b\n", ".x.yml"), ("anything", "Jenkinsfile")])
def test_a_rule_set_that_does_not_exist_is_an_error_whatever_the_file(content, filename):
    with pytest.raises(PlatformChecksError, match="no-such-rules"):
        _check(content, filename, rules=["no-such-rules"])


@pytest.mark.parametrize("content, filename", [("name: a\n", ".x.yml"), ("a: [b\n", ".x.yml"), ("anything", "Jenkinsfile")])
def test_a_schema_that_is_not_a_json_schema_is_an_error_whatever_the_file(content, filename):
    with pytest.raises(PlatformChecksError, match="not a valid JSON schema"):
        _check(content, filename, schema={"type": 5})


def test_a_schema_with_a_reference_to_nothing_is_an_error_not_a_crash():
    schema = {"properties": {"name": {"$ref": "#/definitions/nope"}}}

    with pytest.raises(PlatformChecksError, match="does not contain"):
        _check("name: a\n", schema=schema)


def test_a_reference_outside_the_schema_is_never_fetched():
    # Nothing listens here, and nothing must be asked: it is unresolvable at once.
    schema = {"properties": {"name": {"$ref": "http://127.0.0.1:9/never.json"}}}

    with pytest.raises(PlatformChecksError, match="does not contain"):
        _check("name: a\n", schema=schema)


def test_a_reference_inside_the_schema_works():
    schema = {"definitions": {"text": {"type": "string"}}, "properties": {"name": {"$ref": "#/definitions/text"}}}

    assert _check("name: a\n", schema=schema)["valid"] is True
    assert _check("name: 5\n", schema=schema)["valid"] is False


# --- hostile or unusual content is a failed check, never an error --------------


@pytest.mark.parametrize(
    "content",
    ["a: " + "9" * 5000 + "\n", "2020-01-01: x\n", "? !!binary dGVzdA==\n: x\n"],
    ids=["a number too long to read", "a date as a setting name", "a binary setting name"],
)
def test_content_the_yaml_reader_cannot_handle_is_a_failed_check_not_an_error(content):
    result = _check(content, schema=SCHEMA)

    assert result["valid"] is False
    assert _statuses(result)["yaml"] == "fail"
    assert _statuses(result)["schema"] == "skipped"


# --- GitLab, with its real schema and rules -----------------------------------


def test_the_expected_gitlab_output_passes_every_check():
    from pathlib import Path

    from gitlab_platform import check_gitlab_pipeline

    content = (Path(__file__).parent / "fixtures" / "expected-gitlab-ci.yml").read_text(encoding="utf-8")

    result = check_gitlab_pipeline(content)

    assert result["valid"] is True, result
    assert [entry["status"] for entry in result["checks"]] == ["pass"] * 5
    assert result["checks"][1]["title"] == "The settings follow GitLab CI's format"


def test_a_misspelled_gitlab_keyword_fails_the_schema_check_by_name():
    from gitlab_platform import check_gitlab_pipeline

    result = check_gitlab_pipeline("stages: [build]\nbuild:\n  stage: build\n  scrpt:\n    - echo hi\n")

    schema = next(entry for entry in result["checks"] if entry["id"] == "schema")
    assert schema["status"] == "fail"
    assert any("scrpt" in detail for detail in schema["details"]), schema["details"]


def test_details_are_capped_so_a_systemic_mistake_does_not_flood_the_reader():
    from gitlab_platform import check_gitlab_pipeline

    jobs = "".join(f"j{i}:\n  stage: build\n  scrpt{i}: [echo hi]\n" for i in range(MAX_DETAILS + 15))

    result = check_gitlab_pipeline("stages: [build]\n" + jobs)

    schema = next(entry for entry in result["checks"] if entry["id"] == "schema")
    assert len(schema["details"]) == MAX_DETAILS
