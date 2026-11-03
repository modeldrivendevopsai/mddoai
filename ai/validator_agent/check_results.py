"""The shape of a pipeline check's result, and how one is built."""
from typing import Literal, TypedDict

# A pipeline with a systemic mistake can trip a schema hundreds of times; the
# first few are enough to act on, the rest is noise.
MAX_DETAILS = 10


class Check(TypedDict):
    id: str
    title: str
    status: Literal["pass", "fail", "skipped"]
    details: list[str]


class PipelineValidation(TypedDict):
    valid: bool
    checks: list[Check]


def check(check_id: str, title: str, details: list[str]) -> Check:
    """Passes when there is nothing to report, fails otherwise."""
    return {"id": check_id, "title": title, "status": "fail" if details else "pass", "details": details[:MAX_DETAILS]}


def skipped(check_id: str, title: str, details: list[str] | None = None) -> Check:
    """A check that could not be made. It is never counted as passed."""
    return {"id": check_id, "title": title, "status": "skipped", "details": details or []}
