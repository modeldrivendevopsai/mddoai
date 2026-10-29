"""pipeline_checks.py against hostile or unusual YAML, and against places where
GitLab's own rules differ from a naive reading. Every case here either crashed
the checker, froze it, or gave GitLab-incorrect advice."""
import time

from pipeline_checks import check_gitlab_pipeline


def _status(result, check_id):
    return next(check for check in result["checks"] if check["id"] == check_id)


def test_a_job_stage_that_is_a_list_is_a_failed_check_not_a_crash():
    result = check_gitlab_pipeline("stages: [build]\nb:\n  stage: [a, b]\n  script: [x]\n")

    assert result["valid"] is False


def test_a_non_text_entry_under_stages_is_a_failed_check_not_a_crash():
    result = check_gitlab_pipeline("stages: [{a: 1}]\nb:\n  script: [x]\n")

    assert result["valid"] is False


def test_a_non_text_top_level_key_is_handled_the_way_gitlab_does():
    # GitLab converts YAML to JSON before validating, so the key 1 is "1".
    result = check_gitlab_pipeline("1: x\nstages: [build]\nb:\n  stage: build\n  script: [x]\n")

    assert result["valid"] is False
    assert _status(result, "schema")["status"] == "fail"


def test_a_null_key_is_handled_not_a_crash():
    assert check_gitlab_pipeline("~: 1\nb:\n  script: [x]\n")["valid"] is False


def _alias_bomb(levels):
    lines = ["a0: &a0 [x,x,x,x,x,x,x,x,x,x]"]
    for i in range(1, levels):
        lines.append(f"a{i}: &a{i} [{','.join([f'*a{i - 1}'] * 10)}]")
    return "stages: [build]\n" + "\n".join(lines) + "\n"


def test_an_alias_bomb_is_refused_quickly_instead_of_freezing_the_checker():
    started = time.monotonic()
    result = check_gitlab_pipeline(_alias_bomb(12))

    assert time.monotonic() - started < 2
    assert result["valid"] is False
    assert any("too large" in detail for detail in _status(result, "yaml")["details"])


def test_an_overlong_single_value_is_refused():
    result = check_gitlab_pipeline("stages: [build]\nb:\n  stage: build\n  script: [" + "x" * 20000 + "]\n")

    assert result["valid"] is False
    assert any("too large" in detail for detail in _status(result, "yaml")["details"])


def test_legitimate_aliases_are_still_accepted():
    # Anchors and aliases are ordinary GitLab YAML; only a runaway expansion is refused.
    content = (
        "stages: [build]\n.defaults: &defaults\n  image: alpine\n  stage: build\n"
        "b:\n  <<: *defaults\n  script: [x]\n"
    )

    assert _status(check_gitlab_pipeline(content), "yaml")["status"] == "pass"


def test_needs_naming_another_project_is_not_flagged_as_a_missing_job():
    result = check_gitlab_pipeline(
        "stages: [build]\nb:\n  stage: build\n  script: [x]\n  needs:\n"
        "    - project: g/p\n      job: other\n      ref: main\n"
    )

    assert _status(result, "needs")["status"] == "pass"


def test_a_file_that_only_includes_others_is_not_told_it_has_no_job():
    result = check_gitlab_pipeline("include:\n  - local: ci/build.yml\n")

    assert _status(result, "jobs")["status"] == "pass"


def test_needs_on_a_job_defined_in_an_included_file_is_not_flagged():
    result = check_gitlab_pipeline(
        "include:\n  - local: ci/build.yml\nstages: [build, test]\n"
        "t:\n  stage: test\n  script: [x]\n  needs: [from_include]\n"
    )

    assert _status(result, "needs")["status"] == "pass"


def test_a_job_that_needs_itself_fails_the_dependency_check():
    result = check_gitlab_pipeline("stages: [build]\nb:\n  stage: build\n  script: [x]\n  needs: [b]\n")

    assert "itself" in _status(result, "needs")["details"][0]
