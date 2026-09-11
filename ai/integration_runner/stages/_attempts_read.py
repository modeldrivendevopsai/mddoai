"""Read-only introspection of the real, on-disk attempt tree
stages/_validation.py's own persist_attempt() writes to - a different
concern from that module's write side (querying vs. persisting), so it
lives in its own file rather than growing that one.

run_id/stage/attempt are all user-suppliable route parameters (see
routes/attempts.py), so every read here validates each one the same way a
"file" attachment's own user-suppliable path is validated
(generation_toolkit.attachments.files): a restrictive character class plus
a bare-dot-segment rejection, the same real path-traversal risk applied to
a single path segment instead of a multi-part relative path.
"""
import json

from generation_toolkit.attachments.files import PathSegmentError, validate_path_segment

from . import _validation


class AttemptNotFoundError(Exception):
    """No manifest, or no attempt directory, exists for the given
    run_id/stage/attempt - a real 404, not a bug."""


def read_manifest(run_id: str) -> list[dict]:
    """The real runs/<run_id>/manifest.json content: every attempt
    recorded for this run, across every stage, in the order
    persist_attempt() wrote them."""
    try:
        validate_path_segment(run_id)
    except PathSegmentError as e:
        raise AttemptNotFoundError(str(e)) from e

    manifest_path = _validation.RUNS_DIR / run_id / "manifest.json"
    if not manifest_path.is_file():
        raise AttemptNotFoundError(f"no manifest for run_id {run_id!r}")
    return json.loads(manifest_path.read_text(encoding="utf-8"))


def read_attempt(run_id: str, stage: str, attempt: str) -> dict:
    """One attempt's real persisted files: {"artifact": {"filename": str,
    "content": str} | None, "result": dict, "prompt": dict | None,
    "prompt_version": str | None}. artifact's filename is read from
    whatever this stage actually wrote (psm.ecore, atl_mock.atl, ...), not
    a hardcoded name - stages/_validation.py's own persist_attempt()
    doesn't fix a single filename across every stage. prompt/prompt_version
    are None when this attempt has no prompt.json (every stage besides
    psm's own generation-mode calls today, see persist_attempt()'s own
    docstring)."""
    for segment in (run_id, stage, attempt):
        try:
            validate_path_segment(segment)
        except PathSegmentError as e:
            raise AttemptNotFoundError(str(e)) from e

    attempt_dir = _validation.RUNS_DIR / run_id / stage / attempt
    if not attempt_dir.is_dir():
        raise AttemptNotFoundError(f"no attempt at {run_id!r}/{stage!r}/{attempt!r}")

    result = json.loads((attempt_dir / "result.json").read_text(encoding="utf-8"))

    artifact = None
    artifact_files = [
        path for path in attempt_dir.iterdir() if path.is_file() and path.name not in ("result.json", "prompt.json")
    ]
    if artifact_files:
        artifact_path = artifact_files[0]
        artifact = {"filename": artifact_path.name, "content": artifact_path.read_text(encoding="utf-8")}

    prompt_path = attempt_dir / "prompt.json"
    prompt = None
    prompt_version = None
    if prompt_path.is_file():
        prompt_record = json.loads(prompt_path.read_text(encoding="utf-8"))
        prompt = prompt_record.get("prompt")
        prompt_version = prompt_record.get("prompt_version")

    return {"artifact": artifact, "result": result, "prompt": prompt, "prompt_version": prompt_version}
