"""pipeline_checks.py: the checklist for a generated file. Real YAML in, nothing
mocked."""
import time

import pytest

from check_results import MAX_DETAILS, check
from pipeline_checks import check_pipeline


def _statuses(result):
    return {entry["id"]: entry["status"] for entry in result["checks"]}


def test_a_file_that_parses_as_yaml_passes():
    result = check_pipeline("stages: [build]\nbuild-job:\n  stage: build\n  script: [make]\n", ".gitlab-ci.yml")

    assert result == {
        "valid": True,
        "checks": [{"id": "yaml", "title": "The file is valid YAML", "status": "pass", "details": []}],
    }


def test_yaml_that_does_not_parse_fails_and_says_which_line():
    result = check_pipeline("stages: [build\n", ".gitlab-ci.yml")

    assert result["valid"] is False
    assert _statuses(result) == {"yaml": "fail"}
    assert "line" in result["checks"][0]["details"][0]


def test_a_second_document_in_the_file_is_named_as_the_problem():
    result = check_pipeline("a: 1\n---\nb: 2\n", ".yml")

    assert result["checks"][0]["details"] == ["expected a single document in the stream, but found another document (line 2)"]


def test_the_check_is_by_the_files_ending_not_its_name():
    assert check_pipeline("a: b\n", "workflow.YAML")["checks"][0]["status"] == "pass"


def test_a_file_that_is_not_yaml_is_skipped_as_a_whole_not_passed():
    result = check_pipeline("pipeline { }", "Jenkinsfile")

    assert result["valid"] is True
    assert _statuses(result) == {"format": "skipped"}


@pytest.mark.parametrize("content", ["", "   \n", "# only a comment\n"], ids=["empty", "blank", "comment"])
def test_a_file_with_nothing_in_it_is_still_valid_yaml(content):
    assert check_pipeline(content, ".yml")["valid"] is True


def test_anchors_and_merge_keys_are_valid_yaml():
    content = ".defaults: &defaults\n  image: node\nbuild:\n  <<: *defaults\n  script: [make]\n"

    assert check_pipeline(content, ".yml")["valid"] is True


@pytest.mark.parametrize(
    "content",
    [
        "a: " + "9" * 5000 + "\n",
        "? [a, b]\n: x\n",
        "[" * 5000 + "]" * 5000,
        "a: !!python/object:os.system x\n",
        "a: !!timestamp abc\n",
        "a: !!bool maybe\n",
    ],
    ids=[
        "a number too long to read",
        "a list as a setting name",
        "nested too deeply",
        "a python tag",
        "a timestamp that is not one",
        "a boolean that is not one",
    ],
)
def test_content_the_yaml_reader_cannot_handle_is_a_failed_check_not_an_error(content):
    result = check_pipeline(content, ".yml")

    assert result["valid"] is False
    assert _statuses(result) == {"yaml": "fail"}


def test_nested_merges_that_would_expand_enormously_are_checked_in_no_time():
    # Each level merges the one before it ten times, so expanding the merges
    # would take ten times as long per level.
    lines = ["a0: &a0 {x: 1}"]
    for level in range(1, 21):
        lines.append(f"a{level}: &a{level} {{<<: [{', '.join([f'*a{level - 1}'] * 10)}]}}")
    started = time.monotonic()

    result = check_pipeline("\n".join(lines) + "\n", ".yml")

    assert result["valid"] is True
    assert time.monotonic() - started < 2


def test_a_check_with_many_problems_lists_only_the_first_few():
    entry = check("many", "A check", [f"problem {n}" for n in range(MAX_DETAILS + 5)])

    assert entry["status"] == "fail"
    assert entry["details"] == [f"problem {n}" for n in range(MAX_DETAILS)]
