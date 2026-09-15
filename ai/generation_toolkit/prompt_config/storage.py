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

save_config also strips any "learned_constraints" key it's handed before
writing - constraints live entirely in their own separate store now (see
learned_constraints.py), and this is the one real write choke point every
path that can produce a live/history file (a plain save, a revert, a
restore) funnels through. Without this, an already-shipped config that
still carries the older embedded shape (from before constraints got their
own store) would keep re-writing that same stale array into the live file
and every new history snapshot forever, even though nothing ever reads it
back off this file any more.

Stripping alone would create a real race with learned_constraints.py's own
one-time migration, though: that migration only ever runs the first time
something calls load_constraints, reading whatever load_config currently
returns - if a save, revert, or restore reaches this function BEFORE that
first read ever happens, its own strip above would become the very thing
that erases the last remaining copy of an older, real, already-accumulated
constraints list nobody had migrated yet. save_config guards against
exactly that ordering by migrating straight from whatever `config` it was
actually given (see learned_constraints.migrate_if_missing), not by
re-reading disk, before ever stripping or writing anything.
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

# How many of a name's own real saved versions to keep (the shipped
# default doesn't count towards this, it's a separate, always-available
# file, not a history entry) before the oldest ones start getting pruned.
# An engineering guess, not a measured limit: this module's own no-op
# dedup above already keeps a steady editing session from ever minting a
# version for unchanged content, so what's left is real, distinct edits
# only - real usage produces at most a few dozen of those per config even
# across a long, active day of work. 200 is a genuine safety net against
# truly unbounded growth (a config nobody has touched in months
# accumulating years of real edits), not a limit real day-to-day
# prompt-editing work should ever actually reach.
MAX_HISTORY_VERSIONS = 200


def _prune_old_versions(config_dir: str | Path, name: str) -> None:
    """Deletes the oldest saved versions beyond MAX_HISTORY_VERSIONS.
    Called once per real save below, never on the no-op dedup path above
    (nothing new was created there to ever need pruning for). A local
    import: history.py already imports this module at module level (to
    load a snapshot to diff/restore), so importing it back here at module
    level would be circular - list_history has no reason to depend on this
    module, the reverse just isn't true."""
    from . import history

    versions = [v for v in history.list_history(config_dir, name) if v != history.SHIPPED_DEFAULT_VERSION]
    for stale_version in versions[MAX_HISTORY_VERSIONS:]:
        _paths.history_path(config_dir, name, stale_version).unlink(missing_ok=True)


class PromptConfigValidationError(ValueError):
    """A config failed its own dry-run resolution, an attachment's file
    doesn't exist, or a context key isn't real. Raised by save_config
    before anything is written, and reusable by references.check_references
    for the same check against an already-saved config."""


def load_config(config_dir: str | Path, name: str) -> dict:
    """Reads the live config for name, falling back to its shipped
    default, so an unsaved name still returns a real, working config
    rather than raising. Raises FileNotFoundError only if neither exists,
    which should never happen once default.default.json is committed."""
    for path in (_paths.config_path(config_dir, name), _paths.default_path(config_dir, name)):
        if path.is_file():
            return json.loads(path.read_text(encoding="utf-8"))
    raise FileNotFoundError(
        f"no config found for {name!r}, and no default.default.json exists "
        f"under {_paths.mode_dir(config_dir, name)}"
    )


def save_config(
    config_dir: str | Path,
    name: str,
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
    check.

    A no-op when `config["attachments"]` is byte-identical to the current
    live file's own "attachments" - returns the existing live config
    unchanged instead of minting a new version for content nobody actually
    changed. This is the real backstop against version-history bloat: an
    autosaving UI can (a retry after a client-side timeout that actually
    landed, a debounce firing again for a draft that settled back to its
    last-saved shape, two browser tabs open on the same config) end up
    calling this with the exact same attachments more than once, and
    "nothing to commit" should behave the same way here as it does for any
    other real version-control system, not silently pile up identical
    entries. Compares "attachments" only, never "learned_constraints" -
    stripped below regardless, and versioned entirely separately (see this
    module's own docstring).

    A real save also prunes this name's own oldest history entries beyond
    MAX_HISTORY_VERSIONS, so history stays real (no duplicates, thanks to
    the dedup above) and bounded (no unlimited growth either), rather than
    trading one problem for the other."""
    try:
        resolve_attachments(config.get("attachments", []), context_values, files_root)
    except Exception as e:
        raise PromptConfigValidationError(str(e)) from e

    # A local import: learned_constraints.py already imports this module at
    # module level, so importing it back at module level here would be
    # circular.
    from . import learned_constraints

    if "learned_constraints" in config:
        learned_constraints.migrate_if_missing(config_dir, name, config["learned_constraints"])

    # Drops a legacy embedded "learned_constraints" key rather than ever
    # writing it back out - see this module's own docstring for why.
    config = {key: value for key, value in config.items() if key != "learned_constraints"}

    with _write_lock:
        live_path = _paths.config_path(config_dir, name)
        if live_path.is_file():
            live = json.loads(live_path.read_text(encoding="utf-8"))
            if live.get("attachments") == config.get("attachments"):
                return live

        # A plain microsecond timestamp alone can collide: two saves issued
        # back to back (a script, a retry, two test calls with no sleep
        # between them) can land in the same microsecond on a fast machine
        # or a coarse system clock, which would silently overwrite one
        # save's history file with the other's. The random suffix
        # guarantees a unique id regardless of timing, while the timestamp
        # prefix keeps plain lexicographic sort equal to chronological
        # order (see history.list_history).
        version = f"{datetime.now(timezone.utc).strftime('%Y%m%dT%H%M%S.%fZ')}-{secrets.token_hex(3)}"
        stamped = {**config, "_version": version}
        _paths.atomic_write_json(live_path, stamped)
        _paths.atomic_write_json(_paths.history_path(config_dir, name, version), stamped)
        _prune_old_versions(config_dir, name)
    return stamped
