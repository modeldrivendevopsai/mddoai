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


def resolve_file_attachment(path: str, files_root: str | Path | list[str | Path]) -> str:
    """Reads and returns the real content of `path`, resolved against
    `files_root`. Refuses anything that doesn't stay under that root, even
    after resolving symlinks and any ".."-style segment.

    `files_root` accepts a list of roots, tried in order, for a caller that
    resolves "file" attachments against more than one real directory (e.g.
    psm_agent's own read-only META_MODELS_DIR plus a separate, writable
    uploads directory - two directories with two different real reasons to
    stay distinct, see generation_toolkit/README.md's own attachments
    section) - a single caller-supplied root is still just as valid, kept
    as the common case every existing caller already uses.

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

    roots = files_root if isinstance(files_root, list) else [files_root]
    for candidate_root in roots:
        root = Path(candidate_root).resolve()
        resolved = (root / path).resolve()
        if not resolved.is_relative_to(root):
            continue
        if resolved.is_file():
            return resolved.read_text(encoding="utf-8")
    raise AttachmentFileError(f"attachment path {path!r} does not exist under any allowed root")


def list_reference_and_uploads(reference_example_path: str | Path, uploads_dir: str | Path) -> list[str]:
    """Real, already-existing files a "file" attachment can reference,
    found by scanning real directories directly rather than a hardcoded
    list: the one real master-example file (`reference_example_path`) plus
    every real uploaded file under `uploads_dir` - the shape shared by
    every generation-capable service whose picker only ever has one real
    reference file (atl_agent, acceleo_agent), unlike psm_agent's own
    broader multi-metamodel listing (its own available_files.py stays
    separate, it answers a genuinely different question - see its own
    docstring). Sorted for a deterministic, stable UI listing, not whatever
    order the filesystem happens to return."""
    files: list[str] = []

    reference = Path(reference_example_path)
    if reference.is_file():
        files.append(reference.name)

    uploads_root = Path(uploads_dir)
    if uploads_root.is_dir():
        files.extend(path.name for path in uploads_root.iterdir() if path.is_file())

    return sorted(files)
