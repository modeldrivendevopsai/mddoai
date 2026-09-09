"""Turns a persisted, UI-editable list of "attachment" dicts into the plain
parts dict prompt_builder.build_prompt() already expects.

An attachment is a plain dict with these keys:
  "id": str - stable identifier, also the key it lands under in the
    returned parts dict, and the tab/card label key a UI renders it under.
  "name": str - human-readable label for the UI, not used for resolution.
  "type": "text" | "file" | "context"
  "content": str - present when type is "text", the attachment's own
    literal text.
  "path": str - present when type is "file", resolved against a
    caller-supplied root directory, see files.resolve_file_attachment.
  "key": str - present when type is "context", a lookup key into the
    caller's own context_values dict (e.g. "pim_ecore", "psm_docs"), one
    of the real pipeline values already flowing into whichever stage is
    calling this, never an arbitrary string a config author invents.
"""
from pathlib import Path

from . import files


class UnknownContextKeyError(ValueError):
    """A "context" attachment named a key the caller's own context_values
    doesn't have. A real, actionable config error (the config references a
    pipeline value that doesn't exist for this stage/mode), not a bug."""


def resolve_attachments(
    attachments: list[dict],
    context_values: dict[str, str],
    files_root: str | Path | list[str | Path],
) -> dict[str, str]:
    """Returns an ordered parts dict, one entry per attachment, in the
    attachment list's own order, the same order a UI's card list and a
    generated prompt's own tabs should both show. Raises on the first
    attachment that fails to resolve, naming that attachment, rather than
    silently dropping it: a config that can't fully resolve shouldn't
    quietly produce a partial prompt.
    """
    parts: dict[str, str] = {}
    for attachment in attachments:
        attachment_id = attachment["id"]
        attachment_type = attachment["type"]
        if attachment_type == "text":
            parts[attachment_id] = attachment["content"]
        elif attachment_type == "file":
            parts[attachment_id] = files.resolve_file_attachment(attachment["path"], files_root)
        elif attachment_type == "context":
            key = attachment["key"]
            if key not in context_values:
                raise UnknownContextKeyError(
                    f"attachment {attachment_id!r} references unknown context key {key!r}, "
                    f"available keys: {sorted(context_values)}"
                )
            parts[attachment_id] = context_values[key]
        else:
            raise ValueError(f"attachment {attachment_id!r} has unknown type {attachment_type!r}")
    return parts
