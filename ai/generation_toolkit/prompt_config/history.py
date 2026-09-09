"""Version history for a saved prompt config: every save
(storage.save_config) keeps an immutable snapshot, this module lists,
diffs, and restores from those snapshots.
"""
import json
from pathlib import Path

from . import _paths, storage


def list_history(config_dir: str | Path, name: str, preset: str) -> list[str]:
    """Every saved version id for (name, preset), newest first - the
    version id is an ISO-sortable UTC timestamp (see storage.save_config),
    so a plain reverse lexicographic sort is chronological order."""
    directory = _paths.history_dir(config_dir, name)
    prefix = f"{preset}."
    versions = []
    if directory.is_dir():
        for entry in directory.iterdir():
            if entry.name.startswith(prefix) and entry.name.endswith(".json"):
                versions.append(entry.name.removeprefix(prefix).removesuffix(".json"))
    return sorted(versions, reverse=True)


def _load_version(config_dir: str | Path, name: str, preset: str, version: str) -> dict:
    path = _paths.history_path(config_dir, name, preset, version)
    if not path.is_file():
        raise FileNotFoundError(f"no saved version {version!r} for {name!r}/{preset!r}")
    return json.loads(path.read_text(encoding="utf-8"))


def diff_versions(config_dir: str | Path, name: str, preset: str, version_a: str, version_b: str) -> dict:
    """A real structural diff between two saved versions: per attachment,
    added / removed / changed, comparing the two JSON documents field by
    field, not a raw text diff, so a reordered-but-unchanged attachment
    list doesn't read as "everything changed". No separate system-prompt
    flag: the system message is a config's own first "text" attachment
    (see resolution.py), so a change to it already shows up as that
    attachment's own id in attachments_changed, the same as any other
    edited attachment - it needs no special case here."""
    a = _load_version(config_dir, name, preset, version_a)
    b = _load_version(config_dir, name, preset, version_b)

    attachments_a = {att["id"]: att for att in a.get("attachments", [])}
    attachments_b = {att["id"]: att for att in b.get("attachments", [])}

    return {
        "attachments_added": [aid for aid in attachments_b if aid not in attachments_a],
        "attachments_removed": [aid for aid in attachments_a if aid not in attachments_b],
        "attachments_changed": [
            aid for aid in attachments_a if aid in attachments_b and attachments_a[aid] != attachments_b[aid]
        ],
    }


def restore_version(
    config_dir: str | Path,
    name: str,
    preset: str,
    version: str,
    context_values: dict[str, str],
    files_root: str | Path | list[str | Path],
) -> dict:
    """Copies a saved version back over the live file, itself going
    through storage.save_config again, so restoring creates its own new
    version too. A restore is never destructive, it's just another save."""
    snapshot = _load_version(config_dir, name, preset, version)
    return storage.save_config(config_dir, name, preset, snapshot, context_values, files_root)


def revert_to_default(
    config_dir: str | Path,
    name: str,
    preset: str,
    context_values: dict[str, str],
    files_root: str | Path | list[str | Path],
) -> dict:
    """Copies this preset's own shipped default (or the generic default,
    if this preset has none of its own) over the live file, same way as
    restore_version."""
    for path in (_paths.default_path(config_dir, name, preset), _paths.generic_default_path(config_dir, name)):
        if path.is_file():
            default_config = json.loads(path.read_text(encoding="utf-8"))
            return storage.save_config(config_dir, name, preset, default_config, context_values, files_root)
    raise FileNotFoundError(f"no default exists for {name!r}/{preset!r}, nothing to revert to")


def promote_live_to_default(config_dir: str | Path, name: str, preset: str) -> dict:
    """Copies the current live config over this preset's own shipped
    default, the reverse of revert_to_default. A deliberate, explicit
    action a human takes once they've decided the live config's current
    state (including whatever learned_constraints it has accumulated)
    deserves to become the new starting point for this preset, never
    something that happens automatically on every edit, revert_to_default
    would stop meaning anything stable if the default silently chased
    every live change on its own.

    Since prompts/ is git-committed, not gitignored, "updating the
    default" here means preparing that file to be committed as the new
    baseline, the same real workflow the ai-research prompts themselves
    used when a constraint proved itself and got folded into the next
    platform's own starting prompt.

    Not routed through storage.save_config: "_version" belongs to the
    live/history side of this package's own bookkeeping, a shipped default
    file carries no version of its own."""
    live = storage.load_config(config_dir, name, preset)
    default_config = {key: value for key, value in live.items() if key != "_version"}
    _paths.atomic_write_json(_paths.default_path(config_dir, name, preset), default_config)
    return default_config
