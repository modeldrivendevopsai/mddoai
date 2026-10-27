"""pipeline_checks.py tests: real YAML in, real GitLab-schema validation, no mocks."""
from pathlib import Path

from pipeline_checks import MAX_DETAILS, check_gitlab_pipeline

FIXTURES = Path(__file__).parent / "fixtures"


def _status(result, check_id):
    return next(check for check in result["checks"] if check["id"] == check_id)


def test_the_engines_own_output_passes_every_check():
    content = (FIXTURES / "expected-gitlab-ci.yml").read_text(encoding="utf-8")

    result = check_gitlab_pipeline(content)

    assert result["valid"] is True, result
    assert [check["status"] for check in result["checks"]] == ["pass"] * 5


def test_invalid_yaml_fails_the_first_check_and_skips_the_rest():
    result = check_gitlab_pipeline("stages: [build\n")

    assert result["valid"] is False
    assert _status(result, "yaml")["status"] == "fail"
    assert "line" in _status(result, "yaml")["details"][0]
    assert all(check["status"] == "skipped" for check in result["checks"][1:])


def test_a_top_level_list_is_not_a_pipeline():
    result = check_gitlab_pipeline("- build\n- test\n")

    assert result["valid"] is False
    assert _status(result, "yaml")["status"] == "fail"


def test_a_misspelled_job_keyword_fails_the_schema_check_by_name():
    result = check_gitlab_pipeline("stages: [build]\nbuild:\n  stage: build\n  scrpt:\n    - echo hi\n")

    schema = _status(result, "schema")
    assert schema["status"] == "fail"
    assert any("scrpt" in detail for detail in schema["details"]), schema["details"]


def test_a_job_in_an_undeclared_stage_fails_the_stage_check():
    result = check_gitlab_pipeline("stages: [build]\nship:\n  stage: deploy\n  script: [echo hi]\n")

    stages = _status(result, "stages")
    assert stages["status"] == "fail"
    assert "ship" in stages["details"][0] and "deploy" in stages["details"][0]


def test_a_job_with_no_stage_falls_into_test_which_must_be_declared():
    result = check_gitlab_pipeline("stages: [build]\nlint:\n  script: [echo hi]\n")

    assert _status(result, "stages")["status"] == "fail"


def test_implicit_pre_and_post_stages_need_no_declaration():
    result = check_gitlab_pipeline("stages: [build]\nsetup:\n  stage: .pre\n  script: [echo hi]\n")

    assert _status(result, "stages")["status"] == "pass"


def test_without_a_stages_key_gitlabs_default_stages_apply():
    ok = check_gitlab_pipeline("build:\n  stage: build\n  script: [echo hi]\n")
    bad = check_gitlab_pipeline("build:\n  stage: whatever\n  script: [echo hi]\n")

    assert _status(ok, "stages")["status"] == "pass"
    assert _status(bad, "stages")["status"] == "fail"


def test_a_job_extending_a_template_is_not_stage_checked():
    # The stage may legitimately come from the template.
    result = check_gitlab_pipeline(
        "stages: [build]\n.tpl:\n  stage: build\nship:\n  extends: .tpl\n  script: [echo hi]\n"
    )

    assert _status(result, "stages")["status"] == "pass"


def test_needs_pointing_at_a_missing_job_fails_the_dependency_check():
    result = check_gitlab_pipeline(
        "stages: [build, test]\nbuild:\n  stage: build\n  script: [echo hi]\n"
        "check:\n  stage: test\n  script: [echo hi]\n  needs:\n    - job: bulid\n"
    )

    needs = _status(result, "needs")
    assert needs["status"] == "fail"
    assert "bulid" in needs["details"][0]


def test_optional_and_other_pipeline_needs_are_not_flagged():
    result = check_gitlab_pipeline(
        "stages: [build]\nbuild:\n  stage: build\n  script: [echo hi]\n  needs:\n"
        "    - job: maybe\n      optional: true\n"
        "    - job: elsewhere\n      pipeline: other/project\n"
    )

    assert _status(result, "needs")["status"] == "pass"


def test_a_file_with_only_settings_defines_no_job():
    result = check_gitlab_pipeline("stages: [build]\nvariables:\n  A: b\n")

    assert _status(result, "jobs")["status"] == "fail"


def test_hidden_jobs_do_not_count_as_jobs():
    result = check_gitlab_pipeline("stages: [build]\n.hidden:\n  script: [echo hi]\n")

    assert _status(result, "jobs")["status"] == "fail"


def test_details_are_capped_so_a_systemic_mistake_does_not_flood_the_reader():
    jobs = "".join(f"j{i}:\n  stage: build\n  scrpt{i}: [echo hi]\n" for i in range(MAX_DETAILS + 15))

    result = check_gitlab_pipeline("stages: [build]\n" + jobs)

    assert len(_status(result, "schema")["details"]) == MAX_DETAILS


# --- the generic check, for platforms without a checker of their own ----------


def test_the_generic_check_passes_valid_yaml_and_fails_broken_yaml():
    from pipeline_checks import check_generic_pipeline

    assert check_generic_pipeline("steps:\n  - build\n", "pipeline.yaml")["valid"] is True
    broken = check_generic_pipeline("steps: [\n", ".ci.yml")
    assert broken["valid"] is False
    assert broken["checks"][0]["details"]


def test_the_generic_check_has_nothing_to_check_for_a_file_that_is_not_yaml():
    from pipeline_checks import check_generic_pipeline

    result = check_generic_pipeline("pipeline { }", "Jenkinsfile")

    assert result == {"valid": True, "checks": [{"id": "yaml", "title": "The file is valid YAML", "status": "skipped", "details": []}]}


def test_a_built_in_platform_keeps_its_own_checker():
    from pipeline_checks import check_pipeline
    from platforms import PLATFORMS

    result = check_pipeline(PLATFORMS["gitlab"], "stages: [build]\nj:\n  stage: build\n  script: [echo hi]\n")

    assert {check["id"] for check in result["checks"]} == {"yaml", "schema", "jobs", "stages", "needs"}


def test_every_built_in_platform_has_a_checker():
    from pipeline_checks import CHECKERS
    from platforms import PLATFORMS

    assert set(PLATFORMS) <= set(CHECKERS)
