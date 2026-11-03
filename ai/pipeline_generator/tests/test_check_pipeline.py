from pipeline_checks import check_pipeline
from platforms import Platform

GITLAB = Platform("gitlab", "GitLab CI", 1, ".gitlab-ci.yml", "GitLabMM")


def _other(output_filename):
    return Platform("other", "Other CI", 1, output_filename, "OtherMM")


def test_a_platform_with_a_checker_of_its_own_is_checked_by_it():
    result = check_pipeline(GITLAB, "stages: [build]\njob:\n  stage: deploy\n  script: [a]\n")

    assert {check["id"] for check in result["checks"]} >= {"yaml", "schema", "stages"}
    assert result["valid"] is False


def test_a_platform_without_a_checker_gets_the_one_check_that_a_yaml_file_parses():
    result = check_pipeline(_other("other.yml"), "a: b\n")

    assert result == {"valid": True, "checks": [{"id": "yaml", "title": "The file is valid YAML", "status": "pass", "details": []}]}


def test_that_check_fails_on_yaml_that_does_not_parse():
    result = check_pipeline(_other("other.yaml"), "a: [b\n")

    assert result["valid"] is False
    assert result["checks"][0]["status"] == "fail"


def test_a_file_that_is_not_yaml_has_nothing_to_check():
    result = check_pipeline(_other("Jenkinsfile"), "pipeline { }")

    assert result["valid"] is True
    assert result["checks"][0]["status"] == "skipped"
