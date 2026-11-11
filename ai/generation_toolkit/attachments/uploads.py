"""Saving a real, human-uploaded file in the journal, under the agent's own
name, so it can become a real `type: "file"` attachment (see resolve.py)
instead of a client-side-only copy pasted into a `type: "text"` one.

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

from clients import journal_client

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


class UploadedFiles:
    """The files uploaded to one agent, kept by the journal: a place a "file"
    attachment's path can resolve in, next to the real files a service ships.
    A path that names no uploaded file is None, so the next place is tried."""

    def __init__(self, agent: str):
        self.agent = agent

    def read(self, path: str) -> str | None:
        # Uploads are kept by a flat name, so a path with folders in it is never one: no request.
        if "/" in path:
            return None
        return journal_client.get_upload(self.agent, path)

    def names(self) -> list[str]:
        return journal_client.list_uploads(self.agent)


def save_uploaded_file(
    agent: str,
    filename: str,
    content: bytes,
    max_bytes: int,
) -> str:
    """Has the journal keep `content` for `agent` under a real, safe, collision-free
    name derived from `filename` (never the raw client-supplied name), and returns
    that name - the value to store as a new `type: "file"` attachment's own `path`.
    Raises UploadError over `max_bytes`, on an empty/unusable filename, or on bytes
    that are not UTF-8 text (an attachment is text, put into a prompt)."""
    if len(content) > max_bytes:
        raise UploadError(f"uploaded file is {len(content)} bytes, over the {max_bytes}-byte limit")
    try:
        text = content.decode("utf-8")
    except UnicodeDecodeError as e:
        raise UploadError("uploaded file is not UTF-8 text") from e

    stem, suffix = _safe_stem_and_suffix(filename)
    # A random prefix, not the bare sanitized name: two different uploads
    # (from two different browser sessions, or the same human uploading
    # "model.ecore" twice with different content) must never silently
    # overwrite each other's real saved file.
    stored_name = f"{uuid.uuid4().hex}-{stem}{suffix}"
    if not _ALLOWED_NAME_CHARACTERS.match(stored_name):
        raise UploadError(f"computed storage name {stored_name!r} is not safe")

    journal_client.save_upload(agent, stored_name, text)
    return stored_name
