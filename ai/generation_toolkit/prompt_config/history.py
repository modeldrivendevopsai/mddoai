"""Version history for a saved prompt config: every save
(storage.save_config) keeps an immutable snapshot, this module lists,
diffs, and restores from those snapshots.

The shipped, git-committed `default.default.json` is folded into this same
timeline as one more version, rather than a separate file/concept with its
own dedicated revert/promote-to-default actions: SHIPPED_DEFAULT_VERSION
(the sentinel id "shipped") always sorts as the oldest entry, restorable
through the exact same restore_version every real saved version already
uses. "Reverting to default" is then just restore_version(..., "shipped"):
no separate function, no separate route. There is deliberately no
"promote current live version to the new shipped default" action any more
either - the shipped file is git-committed source, so updating what it
means to be "the default" is a deliberate commit to that file, the same
real workflow the ai-research prompts themselves already used, not a
button whose one-way effect ("no history of its own to undo it") was hard
to convey in the UI and easy to click by accident.
"""
import json
from pathlib import Path

from . import _paths, storage

SHIPPED_DEFAULT_VERSION = "shipped"


def list_history(config_dir: str | Path, name: str) -> list[str]:
    """Every saved version id for name, newest first, with
    SHIPPED_DEFAULT_VERSION appended last whenever a real default.default.json
    actually exists for name - the version id is an ISO-sortable UTC
    timestamp (see storage.save_config), so a plain reverse lexicographic
    sort of the real saved ones is chronological order, and the shipped
    default (never itself resaved, so it never earns a real timestamp of
    its own) always belongs at the very end, regardless of what any real
    timestamp happens to look like.

    Skips the sentinel entirely when no shipped default is actually on
    disk (a name introduced without ever committing one, or a config_dir
    reseeded without it) - _load_snapshot below raises FileNotFoundError
    for exactly this case, so listing the sentinel anyway would advertise
    a version a caller could pick for Restore or Diff only to 404."""
    directory = _paths.history_dir(config_dir, name)
    prefix = "default."
    versions = []
    if directory.is_dir():
        for entry in directory.iterdir():
            if entry.name.startswith(prefix) and entry.name.endswith(".json"):
                versions.append(entry.name.removeprefix(prefix).removesuffix(".json"))
    sorted_versions = sorted(versions, reverse=True)
    if _paths.default_path(config_dir, name).is_file():
        sorted_versions.append(SHIPPED_DEFAULT_VERSION)
    return sorted_versions


def _load_snapshot(config_dir: str | Path, name: str, version: str) -> dict:
    """A real saved version's own snapshot, or the shipped default when
    `version` is SHIPPED_DEFAULT_VERSION - the one place diff_versions and
    restore_version below both go to resolve "version" into an actual
    config dict, so neither needs its own special-cased branch."""
    if version == SHIPPED_DEFAULT_VERSION:
        path = _paths.default_path(config_dir, name)
        if not path.is_file():
            raise FileNotFoundError(f"no shipped default exists for {name!r}")
    else:
        path = _paths.history_path(config_dir, name, version)
        if not path.is_file():
            raise FileNotFoundError(f"no saved version {version!r} for {name!r}")
    return json.loads(path.read_text(encoding="utf-8"))


def diff_versions(config_dir: str | Path, name: str, version_a: str, version_b: str) -> dict:
    """A real structural diff between two saved versions (either may be
    SHIPPED_DEFAULT_VERSION): per attachment, added / removed / changed,
    comparing the two JSON documents field by field, not a raw text diff,
    so a reordered-but-unchanged attachment list doesn't read as
    "everything changed". No separate system-prompt flag: the system
    message is a config's own first "text" attachment (see resolution.py),
    so a change to it already shows up as that attachment's own id in
    attachments_changed, the same as any other edited attachment - it
    needs no special case here."""
    a = _load_snapshot(config_dir, name, version_a)
    b = _load_snapshot(config_dir, name, version_b)

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
    version: str,
    context_values: dict[str, str],
    files_root: str | Path | list[str | Path],
) -> dict:
    """Copies a saved version (or, for SHIPPED_DEFAULT_VERSION, the shipped
    default) back over the live file, itself going through
    storage.save_config again, so restoring creates its own new version
    too. A restore is never destructive, it's just another save - "reverting
    to default" is exactly this, called with version=SHIPPED_DEFAULT_VERSION,
    not a separate function."""
    snapshot = _load_snapshot(config_dir, name, version)
    return storage.save_config(config_dir, name, snapshot, context_values, files_root)
