"""Validates a user-editable "attach a real file" path before ever reading
it, the same two-part pattern validator_agent/main.py already established
for the identical problem, a user-suppliable string that ends up part of a
filesystem path: a restrictive character class, then an explicit check for
a path segment made up entirely of dots (an all-dots segment can collapse
or escape upward on some filesystems even when every individual character
in it is otherwise allowed). Reused here rather than a freshly invented
regex, since this is the same real risk validator_agent's own run_id/
stage/attempt fields already had, not a new one.
"""
import re
from pathlib import Path

_ALLOWED_CHARACTERS = re.compile(r"^[A-Za-z0-9._/-]+$")
_SEGMENT_ALLOWED_CHARACTERS = re.compile(r"^[A-Za-z0-9._-]+$")


class AttachmentFileError(ValueError):
    """A "file" attachment's path failed validation, or doesn't resolve to
    a real, readable file under the allowed root. A real, user-facing
    config error (a stale reference, a typo, an attempted escape), not a
    bug in this module."""


class PathSegmentError(ValueError):
    """A single path segment (a run id, a stage name, an attempt name, ...)
    failed validation. The same real risk as a "file" attachment's path,
    applied to a value that must never contain a path separator at all."""


def validate_path_segment(segment: str) -> str:
    """Validates a single, one-part path segment (no "/" allowed at all,
    unlike resolve_file_attachment's own multi-segment relative path)
    against the same character-class-plus-bare-dot-segment pattern.
    Returns the segment unchanged when valid, so a caller can validate and
    use it inline. For values that get joined into a real filesystem path
    (a run id, a stage name, an attempt directory name) but are never
    themselves a multi-part relative path."""
    if not _SEGMENT_ALLOWED_CHARACTERS.match(segment):
        raise PathSegmentError(f"path segment {segment!r} contains characters outside the allowed set")
    if set(segment) == {"."}:
        raise PathSegmentError(f'path segment {segment!r} must not consist only of "." characters')
    return segment


def resolve_file_attachment(path: str, files_root: str | Path) -> str:
    """Reads and returns the real content of `path`, resolved against
    `files_root`. Refuses anything that doesn't stay under that root, even
    after resolving symlinks and any ".."-style segment.

    The leading-slash check exists even though Path's own `/` operator
    already makes an absolute right-hand side discard `files_root`
    entirely (confirmed against Python's own pathlib docs), which the
    is_relative_to() check below would then also catch. Rejecting it here
    explicitly, before that join ever happens, means this stays correct on
    its own even if a future change swaps that join for something that
    does not have the same discard behavior, and gives a clearer error
    than a downstream "resolves outside the allowed root" would.
    """
    if path.startswith("/"):
        raise AttachmentFileError(f"attachment path {path!r} must be relative, not absolute")
    if not _ALLOWED_CHARACTERS.match(path):
        raise AttachmentFileError(f"attachment path {path!r} contains characters outside the allowed set")
    if any(set(segment) == {"."} for segment in path.split("/")):
        raise AttachmentFileError(f"attachment path {path!r} contains a bare-dot path segment")

    root = Path(files_root).resolve()
    resolved = (root / path).resolve()
    if not resolved.is_relative_to(root):
        raise AttachmentFileError(f"attachment path {path!r} resolves outside the allowed root {root}")
    if not resolved.is_file():
        raise AttachmentFileError(f"attachment path {path!r} does not exist")
    return resolved.read_text(encoding="utf-8")
