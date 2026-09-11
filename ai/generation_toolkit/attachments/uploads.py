"""Saving a real, human-uploaded file to a writable "attachments" root, so
it can become a real `type: "file"` attachment (see resolve.py) instead of
a client-side-only copy pasted into a `type: "text"` one.

A real, different risk from resolve_file_attachment's own (files.py):
that function defends *reading* an already-known, already-real filename
against traversal. Saving introduces a new, untrusted filename an
attacker chooses, on write - a different, real surface (arbitrary
separators in the name, a collision that silently overwrites another
attachment's saved file, no cap on how much gets written). This module is
the write-side counterpart, kept separate from files.py's own read-side
concern for the same one-concern-per-file reason every other split in this
package follows.
"""
import re
import uuid
from pathlib import Path

_ALLOWED_NAME_CHARACTERS = re.compile(r"^[A-Za-z0-9._-]+$")


class UploadError(ValueError):
    """A real, user-facing upload rejection (too large, an empty/unsafe
    filename), not a bug in this module."""


def _safe_stem_and_suffix(filename: str) -> tuple[str, str]:
    """Keeps only the real basename (strips any directory component the
    client's own filename might carry - browsers already do this, but a
    non-browser client isn't trusted to), then splits it into a
    sanitized stem and an allowlisted suffix, so a name like
    "../../etc/passwd" or "model.ecore; rm -rf /" can never reach the
    filesystem, and so two uploads with the same human-facing name still
    get their own real distinct files (the caller adds a uniqueness
    prefix, see save_uploaded_file)."""
    basename = Path(filename).name.strip()
    if not basename:
        raise UploadError("uploaded file has no usable filename")
    stem, _, suffix = basename.rpartition(".")
    stem = stem or basename
    candidate = re.sub(r"[^A-Za-z0-9._-]", "_", stem)[:100] or "upload"
    suffix = re.sub(r"[^A-Za-z0-9]", "", suffix)[:10]
    return candidate, f".{suffix}" if suffix else ""


def save_uploaded_file(
    uploads_root: str | Path,
    filename: str,
    content: bytes,
    max_bytes: int,
) -> str:
    """Writes `content` under `uploads_root` at a real, safe, collision-free
    path derived from `filename` (never the raw client-supplied name), and
    returns that relative path - the value to store as a new `type: "file"`
    attachment's own `path`. Raises UploadError over `max_bytes`, on an
    empty/unusable filename, or if the computed path would somehow still
    resolve outside `uploads_root` (the same root-confinement check
    resolve_file_attachment's own read side already relies on, applied
    here on write instead)."""
    if len(content) > max_bytes:
        raise UploadError(f"uploaded file is {len(content)} bytes, over the {max_bytes}-byte limit")

    stem, suffix = _safe_stem_and_suffix(filename)
    # A random prefix, not the bare sanitized name: two different uploads
    # (from two different browser sessions, or the same human uploading
    # "model.ecore" twice with different content) must never silently
    # overwrite each other's real saved file.
    stored_name = f"{uuid.uuid4().hex}-{stem}{suffix}"
    if not _ALLOWED_NAME_CHARACTERS.match(stored_name):
        raise UploadError(f"computed storage name {stored_name!r} is not safe")

    root = Path(uploads_root).resolve()
    root.mkdir(parents=True, exist_ok=True)
    destination = (root / stored_name).resolve()
    if not destination.is_relative_to(root):
        raise UploadError("computed storage path resolves outside the uploads root")

    destination.write_bytes(content)
    return stored_name
