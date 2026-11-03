import yaml

from gitlab_references import check_references


def _checks(content: str) -> dict:
    return {entry["id"]: entry for entry in check_references(yaml.safe_load(content))}


def test_a_job_in_an_undeclared_stage_fails_the_stage_check():
    stages = _checks("stages: [build]\nship:\n  stage: deploy\n  script: [echo hi]\n")["stages"]

    assert stages["status"] == "fail"
    assert "ship" in stages["details"][0] and "deploy" in stages["details"][0]


def test_a_job_with_no_stage_falls_into_test_which_must_be_declared():
    assert _checks("stages: [build]\nlint:\n  script: [echo hi]\n")["stages"]["status"] == "fail"


def test_implicit_pre_and_post_stages_need_no_declaration():
    assert _checks("stages: [build]\nsetup:\n  stage: .pre\n  script: [echo hi]\n")["stages"]["status"] == "pass"


def test_without_a_stages_key_gitlabs_default_stages_apply():
    ok = _checks("build:\n  stage: build\n  script: [echo hi]\n")
    bad = _checks("build:\n  stage: whatever\n  script: [echo hi]\n")

    assert ok["stages"]["status"] == "pass"
    assert bad["stages"]["status"] == "fail"


def test_a_job_extending_a_template_is_not_stage_checked():
    # The stage may legitimately come from the template.
    checks = _checks("stages: [build]\n.tpl:\n  stage: build\nship:\n  extends: .tpl\n  script: [echo hi]\n")

    assert checks["stages"]["status"] == "pass"


def test_needs_pointing_at_a_missing_job_fails_the_dependency_check():
    checks = _checks(
        "stages: [build, test]\nbuild:\n  stage: build\n  script: [echo hi]\n"
        "check:\n  stage: test\n  script: [echo hi]\n  needs:\n    - job: bulid\n"
    )

    assert checks["needs"]["status"] == "fail"
    assert "bulid" in checks["needs"]["details"][0]


def test_a_job_that_needs_itself_fails_the_dependency_check():
    checks = _checks("stages: [build]\nbuild:\n  stage: build\n  script: [echo hi]\n  needs: [build]\n")

    assert checks["needs"]["status"] == "fail"
    assert "needs itself" in checks["needs"]["details"][0]


def test_optional_and_other_pipeline_needs_are_not_flagged():
    checks = _checks(
        "stages: [build]\nbuild:\n  stage: build\n  script: [echo hi]\n  needs:\n"
        "    - job: maybe\n      optional: true\n"
        "    - job: elsewhere\n      pipeline: other/project\n"
    )

    assert checks["needs"]["status"] == "pass"


def test_a_file_with_only_settings_defines_no_job():
    assert _checks("stages: [build]\nvariables:\n  A: b\n")["jobs"]["status"] == "fail"


def test_hidden_jobs_do_not_count_as_jobs():
    assert _checks("stages: [build]\n.hidden:\n  script: [echo hi]\n")["jobs"]["status"] == "fail"


def test_an_included_file_may_supply_the_jobs_stages_and_dependencies():
    checks = _checks("include: other.yml\n")

    assert {entry["status"] for entry in checks.values()} == {"pass"}


def test_with_no_document_every_check_is_skipped_not_passed():
    checks = check_references(None)

    assert [entry["id"] for entry in checks] == ["jobs", "stages", "needs"]
    assert {entry["status"] for entry in checks} == {"skipped"}
