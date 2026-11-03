"""Validation of a generated pipeline file: a short, plain-language checklist
rather than a raw schema dump, since the reader is not a DevOps engineer.

Pure Python, no JVM. What is checked comes from the platform's own files, not
from this module: the file parses as YAML, it follows the platform's JSON
schema (`schema.json` in the platform's folder), and it passes the rule sets
the platform lists in its platform.json, for what a schema cannot see (a
cross-reference). A check that cannot be made is reported as skipped, never as
passed.

The content is untrusted, so the document is size-checked before anything
walks it: YAML aliases make a tiny file expand into an astronomically large
tree, and a schema validator walks the expanded tree.
"""
import json
from collections.abc import Callable

import yaml
from jsonschema.exceptions import SchemaError, best_match
from jsonschema.validators import validator_for

import gitlab_references
from check_results import Check, PipelineValidation, check, skipped
from platforms import Platform

# Named rule sets a platform can list in its platform.json `rules`.
RULE_SETS: dict[str, Callable[[dict | None], list[Check]]] = {
    "gitlab-references": gitlab_references.check_references,
}

# The file endings a pipeline can be checked as YAML for.
_YAML_SUFFIXES = (".yml", ".yaml")

_MAX_DETAIL_CHARS = 300

# Ceilings on a document, measured with aliases expanded. Engineering guesses,
# far above any real pipeline (a few thousand values at most, none anywhere
# near this long) and low enough that checking one cannot take noticeable time.
_MAX_EXPANDED_VALUES = 20_000
_MAX_VALUE_CHARS = 10_000
_MAX_DEPTH = 100


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


def _clip(text: str) -> str:
    return text if len(text) <= _MAX_DETAIL_CHARS else text[: _MAX_DETAIL_CHARS - 1] + "…"


def _load_document(content: str) -> tuple[dict | None, list[str]]:
    """The pipeline as a JSON-shaped mapping, or the problems that prevented
    it. Going through JSON is how a JSON schema sees YAML (GitLab does the
    same before validating), and it turns a non-text key such as `1:` into the
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


def _schema_problems(schema: dict, document: dict) -> list[str]:
    """What the document gets wrong against `schema`. A schema that is not a
    valid JSON schema raises ValueError: that is the platform's files being
    wrong, not the pipeline."""
    validator_class = validator_for(schema)
    try:
        validator_class.check_schema(schema)
    except SchemaError as e:
        raise ValueError(f"the platform's schema.json is not a valid JSON schema: {e.message}") from e
    problems: list[str] = []
    for error in validator_class(schema).iter_errors(document):
        relevant = best_match([error])
        location = " > ".join(str(part) for part in relevant.absolute_path) or "the file"
        problem = _clip(f"{location}: {relevant.message}")
        if problem not in problems:
            problems.append(problem)
    return problems


def _schema_check(platform: Platform, schema: dict | None, document: dict | None) -> Check:
    title = f"The settings follow {platform.name}'s format"
    if document is None:
        return skipped("schema", title)
    if schema is None:
        return skipped("schema", title, [f"{platform.name} has no schema to check against."])
    return check("schema", title, _schema_problems(schema, document))


def _rule_checks(rule: str, document: dict | None) -> list[Check]:
    rule_set = RULE_SETS.get(rule)
    if rule_set is None:
        return [check(rule, f"The rule set '{rule}' exists", [f"No rule set is named '{rule}'."])]
    return rule_set(document)


def check_pipeline(platform: Platform, content: str, schema: dict | None) -> PipelineValidation:
    """The checklist for a platform's generated file. `schema` is the
    platform's JSON schema, if it has one. Raises ValueError if the schema is
    not a valid JSON schema."""
    if not platform.output_filename.lower().endswith(_YAML_SUFFIXES):
        title = "The file can be checked"
        return {"valid": True, "checks": [skipped("format", title, ["Only YAML pipeline files can be checked."])]}
    document, load_problems = _load_document(content)
    checks = [check("yaml", "The file is valid YAML", load_problems), _schema_check(platform, schema, document)]
    for rule in platform.rules:
        checks += _rule_checks(rule, document)
    return {"valid": all(entry["status"] != "fail" for entry in checks), "checks": checks}
