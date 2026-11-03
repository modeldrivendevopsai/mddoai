"""The GitLab CI rules a schema cannot see: a job naming a stage that was never
declared, a `needs:` pointing at a job that does not exist, a file with no job
at all. They are GitLab's own semantics, so they are code; a platform opts in
by listing "gitlab-references" in the `rules` of its platform.json.
"""
from check_results import Check, check, skipped

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

_TITLES = {
    "jobs": "The pipeline defines at least one job",
    "stages": "Every job belongs to a declared stage",
    "needs": "Every job dependency points at a real job",
}


def _jobs(document: dict) -> dict[str, dict]:
    return {
        name: value for name, value in document.items()
        if not name.startswith(".") and name not in _NON_JOB_KEYS and isinstance(value, dict)
    }


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


def check_references(document: dict | None) -> list[Check]:
    """The checks for a pipeline document, or all of them skipped when there is
    no document to check (the file did not parse)."""
    if document is None:
        return [skipped(check_id, title) for check_id, title in _TITLES.items()]
    jobs = _jobs(document)
    no_job = [] if jobs or "include" in document else ["No job was found in the file"]
    return [
        check("jobs", _TITLES["jobs"], no_job),
        check("stages", _TITLES["stages"], _stage_problems(document, jobs)),
        check("needs", _TITLES["needs"], _needs_problems(document, jobs)),
    ]
