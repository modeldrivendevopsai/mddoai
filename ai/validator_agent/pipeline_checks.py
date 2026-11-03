"""Validation of a generated pipeline file: a short, plain-language checklist
rather than a raw schema dump, since the reader is not a DevOps engineer.

Pure Python, no JVM, and no knowledge of any platform: the caller says what to
check against, which is the platform's JSON schema if it has one. The checks
are: the file parses as YAML, and it follows the schema. A check that cannot be
made is reported as skipped, never as passed.

The content is untrusted, so the document is size-checked before anything
walks it: YAML aliases make a tiny file expand into an astronomically large
tree, and a schema validator walks the expanded tree.
"""
import json

import yaml
from jsonschema.exceptions import SchemaError, best_match
from jsonschema.validators import validator_for
from referencing import Registry
from referencing.exceptions import Unresolvable

from check_results import Check, PipelineValidation, check, skipped

# The file endings a pipeline can be checked as YAML for.
_YAML_SUFFIXES = (".yml", ".yaml")

_MAX_DETAIL_CHARS = 300

# What applying a schema can fail with when the schema, not the pipeline, is at
# fault: a reference loop, a malformed keyword. The document it is applied to
# is size-checked first, so it cannot be what makes these happen.
_SCHEMA_FAULTS = (RecursionError, TypeError, AttributeError, ValueError)


class PlatformChecksError(ValueError):
    """What the caller asked to check against cannot be used: a schema that is
    not a valid JSON schema, or one that refers to something it does not
    contain. That is a fault in the platform's files, not in the pipeline, so it
    is an error and never a verdict on the file."""


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
    except ValueError as e:
        # For example an integer with more digits than Python will convert.
        return None, ["The file contains a value too long to read"]
    if not isinstance(parsed, dict):
        return None, ["The top level of the file must be a set of settings and jobs"]
    if _is_too_large(parsed):
        return None, ["The file is too large or too repetitive to check (is something repeated through aliases?)"]
    try:
        return json.loads(json.dumps(parsed, default=str)), []
    except (TypeError, ValueError):
        # A key that is not text and has no text form, such as a date.
        return None, ["The file has a setting name that is not text, which this checker cannot read"]


def _validator(schema: dict):
    """A validator for `schema`. It is built with an empty registry, so a
    reference to anything outside the schema cannot be fetched from anywhere:
    a schema must be self-contained."""
    try:
        validator_class = validator_for(schema)
        validator_class.check_schema(schema)
        return validator_class(schema, registry=Registry())
    except SchemaError as e:
        raise PlatformChecksError(f"the schema is not a valid JSON schema: {_clip(e.message)}") from e
    except _SCHEMA_FAULTS as e:
        raise PlatformChecksError(f"the schema cannot be used ({type(e).__name__})") from e


def _schema_problems(validator, document: dict) -> list[str]:
    problems: list[str] = []
    try:
        for error in validator.iter_errors(document):
            relevant = best_match([error])
            location = " > ".join(str(part) for part in relevant.absolute_path) or "the file"
            problem = _clip(f"{location}: {relevant.message}")
            if problem not in problems:
                problems.append(problem)
    except Unresolvable as e:
        raise PlatformChecksError(f"the schema refers to something it does not contain: {_clip(str(e))}") from e
    except _SCHEMA_FAULTS as e:
        raise PlatformChecksError(f"the schema cannot be used ({type(e).__name__})") from e
    return problems


def _schema_check(platform_name: str, validator, document: dict | None) -> Check:
    title = f"The settings follow {platform_name}'s format"
    if document is None:
        return skipped("schema", title)
    if validator is None:
        return skipped("schema", title, [f"{platform_name} has no schema to check against."])
    return check("schema", title, _schema_problems(validator, document))


def check_pipeline(content: str, filename: str, platform_name: str, schema: dict | None) -> PipelineValidation:
    """The checklist for a generated file. `filename` says whether it is YAML,
    `platform_name` is how the checks name the platform, and `schema` is its
    JSON schema if it has one. Raises PlatformChecksError if the schema cannot
    be used. A schema that is not a valid JSON schema is found first, whatever
    the file holds. A schema that refers to something it does not contain, or
    cannot be applied, is found when the pipeline reaches the part that does."""
    validator = _validator(schema) if schema is not None else None
    if not filename.lower().endswith(_YAML_SUFFIXES):
        return {"valid": True, "checks": [skipped("format", "The file can be checked", ["Only YAML pipeline files can be checked."])]}
    document, load_problems = _load_document(content)
    checks = [check("yaml", "The file is valid YAML", load_problems), _schema_check(platform_name, validator, document)]
    return {"valid": all(entry["status"] != "fail" for entry in checks), "checks": checks}
