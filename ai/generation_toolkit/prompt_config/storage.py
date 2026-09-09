"""Load and save a prompt config. Every function here takes a config_dir
the calling service supplies (its own directory, its own Docker bind
mount decision), so this module owns no service-specific path of its
own, the same functions, a different directory, per caller.

A config is a plain dict: {"attachments": [...], "_version": str}.
"_version" is stamped by save_config, never set by a caller directly.
There is no separate stored "system_prompt" field - a config's own first
"text" attachment IS the system message, resolved that way by
resolution.py's own resolve_for_call, not a special field this module
knows about; this module persists "attachments" like any other key,
with no opinion on what any one entry in it means to a caller.
"""
import json
import secrets
import threading
from datetime import datetime, timezone
from pathlib import Path

from generation_toolkit.attachments.resolve import resolve_attachments

from . import _paths

# Serializes this module's own read-modify-write of a single config file
# process-wide, the same reasoning integration_runner/stages/_validation.py
# already documents for its own manifest.json: sufficient because each
# service that calls this module runs single-process (no --workers flag),
# not a cross-process problem this module actually has.
_write_lock = threading.Lock()


class PromptConfigValidationError(ValueError):
    """A config failed its own dry-run resolution, an attachment's file
    doesn't exist, or a context key isn't real. Raised by save_config
    before anything is written, and reusable by references.check_references
    for the same check against an already-saved config."""


def load_config(config_dir: str | Path, name: str, preset: str = "default") -> dict:
    """Reads the live config for (name, preset), falling back to that
    preset's own shipped default, then to the generic default, so an
    unresolved preset still returns a real, working config rather than
    raising. Raises FileNotFoundError only if none of the three exist,
    which should never happen once default.default.json is committed."""
    for path in (
        _paths.config_path(config_dir, name, preset),
        _paths.default_path(config_dir, name, preset),
        _paths.generic_default_path(config_dir, name),
    ):
        if path.is_file():
            return json.loads(path.read_text(encoding="utf-8"))
    raise FileNotFoundError(
        f"no config found for {name!r}/{preset!r}, and no default.default.json exists "
        f"under {_paths.mode_dir(config_dir, name)}"
    )


def save_config(
    config_dir: str | Path,
    name: str,
    preset: str,
    config: dict,
    context_values: dict[str, str],
    files_root: str | Path | list[str | Path],
) -> dict:
    """Validates config by actually resolving it, the same resolution a
    real generation call would do, stamps a new version, writes it as the
    live file, and keeps an immutable copy in history. Raises
    PromptConfigValidationError, naming the real broken attachment,
    instead of persisting something that would only fail on the next real
    run. context_values/files_root are a realistic sample the caller
    already has on hand (its own latest real values), not synthetic
    placeholders, so this dry-run is a genuine check, not just a shape
    check."""
    try:
        resolve_attachments(config.get("attachments", []), context_values, files_root)
    except Exception as e:
        raise PromptConfigValidationError(str(e)) from e

    # A plain microsecond timestamp alone can collide: two saves issued
    # back to back (a script, a retry, two test calls with no sleep
    # between them) can land in the same microsecond on a fast machine or
    # a coarse system clock, which would silently overwrite one save's
    # history file with the other's. The random suffix guarantees a
    # unique id regardless of timing, while the timestamp prefix keeps
    # plain lexicographic sort equal to chronological sort (see
    # history.list_history).
    version = f"{datetime.now(timezone.utc).strftime('%Y%m%dT%H%M%S.%fZ')}-{secrets.token_hex(3)}"
    stamped = {**config, "_version": version}

    with _write_lock:
        _paths.atomic_write_json(_paths.config_path(config_dir, name, preset), stamped)
        _paths.atomic_write_json(_paths.history_path(config_dir, name, preset, version), stamped)
    return stamped
