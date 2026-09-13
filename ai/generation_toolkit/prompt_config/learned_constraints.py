"""Promoting a per-run correction into a config's own permanent baseline.

Two different, related things are both called "constraints" in this
codebase, and this module is the bridge between them:
  - A per-run constraint (integration_runner's own IntegrationRun.constraints)
    is a live correction a human adds during one run's retries, reset the
    next time that run resets. It never touches a saved config on its own.
  - A "learned" constraint (this module) is applied to EVERY future run
    that loads this name, not just the run that discovered it.

This mirrors the real, validated MDDOAI research methodology directly: a
constraint discovered while generating one platform's metamodel was
carried forward as a starting constraint for the next platform's own
prompt, once it had actually proven itself, and that carried-forward
constraint library measurably cut round counts across platforms. A
constraint that proved itself during one real run (a human decides which
ones, this module doesn't guess) gets promoted here so the next run, or a
brand new platform, starts with that lesson already applied instead of
rediscovering it from a fresh retry loop.

Whichever stage-owned function assembles its final constraints list for a
real generation call (e.g. psm_agent/generation.py's own generate()) is
responsible for combining resolution.resolve_for_call's own resolved
"learned_constraints" with that run's own live corrections before calling
generation_toolkit.generation_agent.run_with_retry, this module only
manages the saved, permanent side.

Kept in its own file (constraints.json, see _paths.constraints_path), not
inside the same versioned config storage.py/history.py manage: a learned
constraint is meant to survive every experiment with the prompt's own
text/attachments, including a Revert to default or a Restore of an older
version - "permanent" was never meant to mean "permanent until the next
revert." Storing it in the same versioned file was the earlier design and
was the actual root cause of a revert silently discarding accumulated
constraints; this file has no version history of its own on purpose, it
is simply always current, exactly like a config's own default.default.json
baseline is always current for a fresh install, just never versioned.
"""
import json
import threading
from pathlib import Path

from . import _paths, storage

# Serializes this module's own read-modify-write of constraints.json,
# process-wide - the same reasoning storage.py's own _write_lock documents
# for the live config file: sufficient because each service that calls
# this module runs single-process (no --workers flag), not a cross-process
# problem this module actually has.
_write_lock = threading.Lock()


def _read_constraints_file(config_dir: str | Path, name: str) -> list[str] | None:
    path = _paths.constraints_path(config_dir, name)
    if not path.is_file():
        return None
    return json.loads(path.read_text(encoding="utf-8")).get("constraints", [])


def _load_constraints_locked(config_dir: str | Path, name: str) -> list[str]:
    """The real read(-and-maybe-migrate) logic load_constraints below
    exposes, factored out so a caller that already holds _write_lock (see
    add_learned_constraints/remove_learned_constraint) can reuse it
    directly instead of calling the public, self-locking load_constraints
    and deadlocking on a lock this module's own threading.Lock doesn't let
    the same thread re-enter."""
    existing = _read_constraints_file(config_dir, name)
    if existing is not None:
        return existing
    legacy = storage.load_config(config_dir, name).get("learned_constraints", [])
    _paths.atomic_write_json(_paths.constraints_path(config_dir, name), {"constraints": legacy})
    return legacy


def load_constraints(config_dir: str | Path, name: str) -> list[str]:
    """The real, always-current constraints list for name - never touched
    by a save/revert/restore of the prompt's own text/attachments. Migrates
    once from an older config that still carries its constraints embedded
    in the versioned file itself (the shape this module used before it got
    its own separate store), so shipping this change never silently drops
    a team's already-accumulated constraints; every read after that first
    one comes straight from constraints.json.

    Raises FileNotFoundError for a name with no config at all (no
    default.default.json ever shipped), the same as storage.load_config
    itself - a caller (see routes/prompt_config.py's own 404 handling on
    every other endpoint) should treat "no base prompt to attach
    constraints to" the same way here, not silently succeed against a name
    that was never real.

    Locked the same as add_learned_constraints/remove_learned_constraint
    below, not just those two: this function's own migrate-on-first-read
    is itself a read-then-conditionally-write of constraints.json, and an
    unlocked one running concurrently with a locked add/remove could still
    read "not migrated yet", then write its own stale legacy value back
    AFTER add/remove's own locked write already landed the real, current
    list - silently clobbering it back to the older value. Serializing
    every reader through the same lock as every writer closes that
    window."""
    with _write_lock:
        return _load_constraints_locked(config_dir, name)


def add_learned_constraints(config_dir: str | Path, name: str, constraints: list[str]) -> list[str]:
    """Appends `constraints` to name's own constraints.json, deduplicated (a
    constraint promoted twice stays a single entry) but otherwise
    order-preserving. Returns the full, updated list."""
    with _write_lock:
        existing = _load_constraints_locked(config_dir, name)
        merged = list(existing)
        for constraint in constraints:
            if constraint not in merged:
                merged.append(constraint)
        _paths.atomic_write_json(_paths.constraints_path(config_dir, name), {"constraints": merged})
        return merged


def migrate_if_missing(config_dir: str | Path, name: str, legacy_constraints: list[str]) -> None:
    """Seeds constraints.json from `legacy_constraints` - an older config's
    own embedded "learned_constraints" value, one storage.save_config is
    about to strip and overwrite - but only if nothing has migrated this
    name yet (a real, already-accumulated constraints.json is never
    clobbered by a caller round-tripping a stale or merely-echoed value).

    Called from inside storage.save_config itself, not only from
    load_constraints's own on-read migration above: a save, revert, or
    restore that's about to strip and overwrite the one on-disk copy of a
    legacy embedded value must migrate straight from what it was actually
    given, not from re-reading disk - for an ordinary first save, disk
    doesn't have this data at all yet; for a revert/restore, disk is
    exactly the copy this same call is about to replace. Re-reading disk
    here instead of using the config already in hand would race the very
    write this function exists to run ahead of.

    Locked for the same reason load_constraints is: this is itself a
    read-then-conditionally-write of constraints.json, running from inside
    storage.save_config's own write path, concurrently with whatever
    add_learned_constraints/remove_learned_constraint might be doing to
    the same file."""
    with _write_lock:
        if _read_constraints_file(config_dir, name) is not None:
            return
        _paths.atomic_write_json(_paths.constraints_path(config_dir, name), {"constraints": legacy_constraints})


def remove_learned_constraint(config_dir: str | Path, name: str, constraint: str) -> list[str]:
    """The reverse of add_learned_constraints: a promoted constraint that
    turns out to be wrong, or no longer relevant, is removed the same way,
    not left to accumulate forever. Returns the full, updated list."""
    with _write_lock:
        remaining = [c for c in _load_constraints_locked(config_dir, name) if c != constraint]
        _paths.atomic_write_json(_paths.constraints_path(config_dir, name), {"constraints": remaining})
        return remaining


def with_current_constraints(config_dir: str | Path, name: str, config: dict) -> dict:
    """The one real "merge the current, real constraints into a config a
    human is about to see" step, shared by every caller that needs it
    (resolution.resolve_for_call, for a real call's own resolved config;
    routes/prompt_config.py's own _with_constraints, for a get/save/restore/
    revert response) rather than each re-implementing the same one-line
    merge independently - a config's own "learned_constraints" key, if it
    still has one at all (see storage.save_config's own migrate-then-strip),
    is never what should actually be shown; this always is."""
    return {**config, "learned_constraints": load_constraints(config_dir, name)}
