import pytest

from helpers import check_gitlab_pipeline
from pipeline_checks import check_pipeline
from platforms import Platform

SCHEMA = {
    "type": "object",
    "required": ["name"],
    "properties": {"name": {"type": "string"}},
    "additionalProperties": False,
}


def _platform(output_filename=".x.yml", rules=()):
    return Platform("x", "X CI", 1, output_filename, "XMM", tuple(rules))


def _statuses(result):
    return {check["id"]: check["status"] for check in result["checks"]}


def test_a_platform_without_a_schema_has_its_schema_check_skipped_not_passed():
    result = check_pipeline(_platform(), "name: a\n", None)

    assert _statuses(result) == {"yaml": "pass", "schema": "skipped"}
    assert result["valid"] is True
    assert result["checks"][1]["details"] == ["X CI has no schema to check against."]


def test_a_pipeline_that_follows_the_platforms_schema_passes_it():
    result = check_pipeline(_platform(), "name: a\n", SCHEMA)

    assert _statuses(result) == {"yaml": "pass", "schema": "pass"}
    assert result["checks"][1]["title"] == "The settings follow X CI's format"


def test_a_pipeline_that_breaks_the_platforms_schema_fails_and_says_where():
    result = check_pipeline(_platform(), "name: 5\nextra: 1\n", SCHEMA)

    assert result["valid"] is False
    schema_check = result["checks"][1]
    assert schema_check["status"] == "fail"
    assert any("name" in detail for detail in schema_check["details"])


def test_yaml_that_does_not_parse_fails_and_everything_after_it_is_skipped():
    result = check_pipeline(_platform(rules=["gitlab-references"]), "a: [b\n", SCHEMA)

    assert result["valid"] is False
    assert _statuses(result) == {
        "yaml": "fail",
        "schema": "skipped",
        "jobs": "skipped",
        "stages": "skipped",
        "needs": "skipped",
    }


def test_a_file_that_is_not_yaml_is_skipped_as_a_whole_not_passed():
    result = check_pipeline(_platform("Jenkinsfile"), "pipeline { }", None)

    assert result["valid"] is True
    assert _statuses(result) == {"format": "skipped"}


def test_a_rule_set_the_platform_names_is_applied():
    content = "name: a\njob:\n  stage: nowhere\n  script: [a]\n"

    result = check_pipeline(_platform(rules=["gitlab-references"]), content, None)

    assert _statuses(result)["stages"] == "fail"
    assert result["valid"] is False


def test_a_rule_set_that_does_not_exist_fails_and_names_it():
    result = check_pipeline(_platform(rules=["no-such-rules"]), "name: a\n", None)

    assert result["valid"] is False
    assert result["checks"][-1]["details"] == ["No rule set is named 'no-such-rules'."]


def test_a_schema_that_is_not_a_json_schema_is_an_error_not_a_verdict_on_the_pipeline():
    with pytest.raises(ValueError, match="not a valid JSON schema"):
        check_pipeline(_platform(), "name: a\n", {"type": 5})


def test_the_gitlab_platform_is_checked_by_its_own_schema_and_rules():
    result = check_gitlab_pipeline("stages: [build]\njob:\n  stage: deploy\n  script: [a]\n")

    assert list(_statuses(result)) == ["yaml", "schema", "jobs", "stages", "needs"]
    assert _statuses(result)["stages"] == "fail"
    assert result["checks"][1]["title"] == "The settings follow GitLab CI's format"
