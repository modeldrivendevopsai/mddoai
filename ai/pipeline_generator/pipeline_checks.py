"""Validation of a generated pipeline file: a short, plain-language checklist
rather than a raw schema dump, since the reader is not a DevOps engineer.

Pure Python, no JVM. GitLab's own published CI schema (vendored under
schemas/, see its license notice there) catches unknown keywords and wrongly
shaped values, but it cannot see cross-references (a job naming a stage that
was never declared, a `needs:` pointing at a job that doesn't exist), so
those are checked separately here.

The content is untrusted, so the document is size-checked before anything
walks it: YAML aliases make a tiny file expand into an astronomically large
tree, and a schema validator walks the expanded tree.
"""
import json
from pathlib import Path
from typing import Callable, Literal, TypedDict

import yaml
from jsonschema import Draft7Validator
from jsonschema.exceptions import best_match

from platforms import PUBLISHED

_SCHEMA_PATH = Path(__file__).parent / "schemas" / "gitlab-ci.schema.json"
_GITLAB_VALIDATOR = Draft7Validator(json.loads(_SCHEMA_PATH.read_text(encoding="utf-8")))

# A pipeline with a systemic mistake can trip the schema hundreds of times;
# the first few are enough to act on, the rest is noise.
MAX_DETAILS = 10
_MAX_DETAIL_CHARS = 300

# Ceilings on a document, measured with aliases expanded. Engineering guesses,
# far above any real pipeline (a few thousand values at most, none anywhere
# near this long) and low enough that checking one cannot take noticeable time.
_MAX_EXPANDED_VALUES = 20_000
_MAX_VALUE_CHARS = 10_000
_MAX_DEPTH = 100

# Top-level GitLab keywords that are settings, not jobs, even when their
# value is a mapping.
_NON_JOB_KEYS = {
    "default", "include", "stages", "variables", "workflow", "spec",
    "image", "services", "cache", "before_script", "after_script", "types",
}
# The stages GitLab provides when a pipeline declares none, and the two it
# always provides in addition to any it does.
_DEFAULT_STAGES = {".pre", "build", "test", "deploy", ".post"}
_IMPLICIT_STAGES = {".pre", ".post"}
# The stage a job falls into when it names none.
_DEFAULT_STAGE = "test"


class Check(TypedDict):
    id: str
    title: str
    status: Literal["pass", "fail", "skipped"]
    details: list[str]


class PipelineValidation(TypedDict):
    valid: bool
    checks: list[Check]


def _is_too_large(document: object) -> bool:
    """Whether the document, with every alias expanded, goes past the ceilings
    above. Iterative, and gives up the moment a ceiling is crossed, so the
    cost of the answer is bounded however hostile the input is."""
    stack: list[tuple[object, int]] = [(document, 0)]
    values = 0
    while stack:
        node, depth = stack.pop()
        values += 1
        if values > _MAX_EXPANDED_VALUES or depth > _MAX_DEPTH:
            return True
        if isinstance(node, str):
            if len(node) > _MAX_VALUE_CHARS:
                return True
        elif isinstance(node, dict):
            stack.extend((child, depth + 1) for pair in node.items() for child in pair)
        elif isinstance(node, list):
            stack.extend((child, depth + 1) for child in node)
    return False


def _jobs(document: dict) -> dict[str, dict]:
    return {
        name: value for name, value in document.items()
        if not name.startswith(".") and name not in _NON_JOB_KEYS and isinstance(value, dict)
    }


def _clip(text: str) -> str:
    return text if len(text) <= _MAX_DETAIL_CHARS else text[: _MAX_DETAIL_CHARS - 1] + "…"


def _check(check_id: str, title: str, details: list[str]) -> Check:
    return {"id": check_id, "title": title, "status": "fail" if details else "pass", "details": details[:MAX_DETAILS]}


def _skipped(check_id: str, title: str) -> Check:
    return {"id": check_id, "title": title, "status": "skipped", "details": []}


def _load_document(content: str) -> tuple[dict | None, list[str]]:
    """The pipeline as a JSON-shaped mapping, or the problems that prevented
    it. Going through JSON mirrors GitLab (it converts the YAML to JSON before
    validating), which is also what turns a non-text key such as `1:` into the
    text key "1" instead of something a schema cannot compare."""
    try:
        parsed = yaml.safe_load(content)
    except yaml.YAMLError as e:
        mark = getattr(e, "problem_mark", None)
        where = f" (line {mark.line + 1})" if mark is not None else ""
        return None, [f"{getattr(e, 'problem', None) or 'The file is not valid YAML'}{where}"]
    except RecursionError:
        return None, ["The file is nested too deeply"]
    if not isinstance(parsed, dict):
        return None, ["The top level of the file must be a set of settings and jobs"]
    if _is_too_large(parsed):
        return None, ["The file is too large or too repetitive to check (is something repeated through aliases?)"]
    return json.loads(json.dumps(parsed, default=str)), []


def _schema_problems(document: dict) -> list[str]:
    problems: list[str] = []
    for error in _GITLAB_VALIDATOR.iter_errors(document):
        relevant = best_match([error])
        location = " > ".join(str(part) for part in relevant.absolute_path) or "the file"
        problem = _clip(f"{location}: {relevant.message}")
        if problem not in problems:
            problems.append(problem)
    return problems


def _stage_problems(document: dict, jobs: dict[str, dict]) -> list[str]:
    # An included file can supply stages, so there is nothing to check against.
    if "include" in document:
        return []
    stages = document.get("stages")
    declared = (
        {stage for stage in stages if isinstance(stage, str)} | _IMPLICIT_STAGES
        if isinstance(stages, list)
        else _DEFAULT_STAGES
    )
    problems = []
    for name, job in jobs.items():
        # A job inheriting from a template may get its stage from there.
        if "extends" in job:
            continue
        stage = job.get("stage", _DEFAULT_STAGE)
        if not isinstance(stage, str):
            problems.append(f"Job '{name}' has a stage that is not a single name")
        elif stage not in declared:
            problems.append(f"Job '{name}' uses the stage '{stage}', which is not one of the pipeline's stages")
    return problems


def _needs_problems(document: dict, jobs: dict[str, dict]) -> list[str]:
    # An included file can define the job a `needs:` points at.
    if "include" in document:
        return []
    problems = []
    for name, job in jobs.items():
        needs = job.get("needs")
        entries = needs if isinstance(needs, list) else [needs] if needs is not None else []
        for entry in entries:
            if isinstance(entry, str):
                needed, exempt = entry, False
            elif isinstance(entry, dict) and isinstance(entry.get("job"), str):
                needed = entry["job"]
                # A job in another project's pipeline, or one that may be absent.
                exempt = "pipeline" in entry or "project" in entry or entry.get("optional") is True
            else:
                continue
            if exempt:
                continue
            if needed == name:
                problems.append(f"Job '{name}' needs itself")
            elif needed not in jobs:
                problems.append(f"Job '{name}' needs '{needed}', but no job has that name")
    return problems


def check_gitlab_pipeline(content: str) -> PipelineValidation:
    titles = {
        "yaml": "The file is valid YAML",
        "schema": "The settings follow GitLab's CI format",
        "jobs": "The pipeline defines at least one job",
        "stages": "Every job belongs to a declared stage",
        "needs": "Every job dependency points at a real job",
    }
    document, load_problems = _load_document(content)
    checks: list[Check] = [_check("yaml", titles["yaml"], load_problems)]
    if document is None:
        checks += [_skipped(check_id, title) for check_id, title in titles.items() if check_id != "yaml"]
        return {"valid": False, "checks": checks}

    jobs = _jobs(document)
    no_job = [] if jobs or "include" in document else ["No job was found in the file"]
    checks += [
        _check("schema", titles["schema"], _schema_problems(document)),
        _check("jobs", titles["jobs"], no_job),
        _check("stages", titles["stages"], _stage_problems(document, jobs)),
        _check("needs", titles["needs"], _needs_problems(document, jobs)),
    ]
    return {"valid": all(check["status"] != "fail" for check in checks), "checks": checks}


# The file endings a platform's pipeline can be checked as YAML for, when the
# platform has no checker of its own.
_YAML_SUFFIXES = (".yml", ".yaml")


def check_generic_pipeline(content: str, output_filename: str) -> PipelineValidation:
    """For a platform with no checker of its own, such as one published from
    an integration run: no schema is known, so the one check is that a YAML
    file parses. Any other kind of file has nothing to check."""
    title = "The file is valid YAML"
    if not output_filename.lower().endswith(_YAML_SUFFIXES):
        return {"valid": True, "checks": [_skipped("yaml", title)]}
    _document, problems = _load_document(content)
    return {"valid": not problems, "checks": [_check("yaml", title, problems)]}


# Built-in platform id (see platforms.PLATFORMS) -> its pipeline checker.
CHECKERS: dict[str, Callable[[str], PipelineValidation]] = {"gitlab": check_gitlab_pipeline}


def check_pipeline(platform, content: str) -> PipelineValidation:
    """A built-in platform's own checker, or the generic check for a published
    platform. A built-in platform without a checker raises KeyError: adding
    one needs a checker too."""
    if platform.runner == PUBLISHED:
        return check_generic_pipeline(content, platform.output_filename)
    return CHECKERS[platform.id](content)
