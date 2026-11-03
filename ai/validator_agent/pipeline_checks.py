"""Validation of a generated pipeline file: a short, plain-language checklist
rather than a raw parser dump, since the reader is not a DevOps engineer.

Pure Python, no JVM, and no knowledge of any platform. The check made is that
the file parses as YAML. A file that is not YAML (a Jenkinsfile, say) cannot be
checked, which is reported as skipped, never as passed.
"""
import yaml

from check_results import PipelineValidation, check, skipped

# The file endings a pipeline can be checked as YAML for.
_YAML_SUFFIXES = (".yml", ".yaml")


class _Loader(yaml.SafeLoader):
    """A safe YAML reader that leaves merge keys (`<<`) unexpanded. The parsed
    value is thrown away, and expanding a merge copies the merged mappings
    once per use, which a few hundred bytes of nested merges can make
    astronomically large."""

    def flatten_mapping(self, node):
        pass


# Unexpanded, a merge key is an ordinary text key.
_Loader.add_constructor("tag:yaml.org,2002:merge", _Loader.construct_yaml_str)


def _yaml_problems(content: str) -> list[str]:
    """What stops `content` from parsing as YAML, in words a person can act on;
    empty when it parses. The content is untrusted: whatever the YAML reader
    raises for it is a problem with the file, never an error here."""
    try:
        yaml.load(content, Loader=_Loader)
    except yaml.YAMLError as e:
        mark = getattr(e, "problem_mark", None)
        where = f" (line {mark.line + 1})" if mark is not None else ""
        said = ", ".join(part for part in (getattr(e, "context", None), getattr(e, "problem", None)) if part)
        return [f"{said or 'The file is not valid YAML'}{where}"]
    except RecursionError:
        return ["The file is nested too deeply"]
    except Exception:
        # The reader fails on some values with an error of its own, not a
        # YAMLError: a timestamp or a boolean it cannot read, an integer with
        # more digits than Python will convert.
        return ["The file contains a value that cannot be read"]
    return []


def check_pipeline(content: str, filename: str) -> PipelineValidation:
    """The checklist for a generated file. `filename` says whether it is YAML."""
    if not filename.lower().endswith(_YAML_SUFFIXES):
        return {"valid": True, "checks": [skipped("format", "The file can be checked", ["Only YAML pipeline files can be checked."])]}
    checks = [check("yaml", "The file is valid YAML", _yaml_problems(content))]
    return {"valid": all(entry["status"] != "fail" for entry in checks), "checks": checks}
