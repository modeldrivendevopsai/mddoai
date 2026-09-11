"""Internal file-layout helpers shared by this package's own modules, not
part of prompt_config's public, module-qualified surface (storage,
history, references). The same "leading underscore means internal to this
codebase, not a second public entry point" convention
integration_runner/stages/_validation.py already uses.

Layout, under a caller-supplied config_dir:
  config_dir/<name>/default.json              - the live config
  config_dir/<name>/default.default.json      - immutable, git-shipped
                                                 baseline
  config_dir/<name>/history/default.<version>.json - immutable snapshots,
                                                 one per save
`<name>` is the stage's own mode ("generation", "comparison", ...). There
is exactly one config per name: every real config this project has ever
shipped or saved has been this one, so the file names keep the "default"
segment rather than dropping it, matching what's already on disk.

`name`/`version` are frequently user-suppliable (a REST route parameter,
ultimately from a browser). Both are validated via
attachments.files.validate_path_segment before they reach a filename, even
where a caller's own suffix (".json", ".default.json") already happens to
make a bare ".." harmless today - validating here, at this package's one
real path-construction choke point, means that stays true regardless of
how any of these functions' own string formatting changes later, not
something every call site has to remember on its own.
"""
import json
import os
import threading
from pathlib import Path

from generation_toolkit.attachments.files import validate_path_segment


def mode_dir(config_dir: str | Path, name: str) -> Path:
    return Path(config_dir) / validate_path_segment(name)


def config_path(config_dir: str | Path, name: str) -> Path:
    return mode_dir(config_dir, name) / "default.json"


def default_path(config_dir: str | Path, name: str) -> Path:
    return mode_dir(config_dir, name) / "default.default.json"


def history_dir(config_dir: str | Path, name: str) -> Path:
    return mode_dir(config_dir, name) / "history"


def history_path(config_dir: str | Path, name: str, version: str) -> Path:
    return history_dir(config_dir, name) / f"default.{validate_path_segment(version)}.json"


def atomic_write_json(path: Path, data: dict) -> None:
    """Writes data to path as JSON without ever leaving a partially-written
    or corrupted file behind: writes to a sibling temp file first, then
    os.replace()'s it into place, an atomic rename on both POSIX and
    Windows. Duplicated from integration_runner/stages/_validation.py's own
    identical helper rather than imported: psm_agent and integration_runner
    are separate deployed services that never import each other's
    internals, and this is a small, stdlib-only helper, not shared
    business logic worth a cross-service dependency."""
    path.parent.mkdir(parents=True, exist_ok=True)
    tmp_path = path.with_name(f"{path.name}.tmp.{os.getpid()}.{threading.get_ident()}")
    tmp_path.write_text(json.dumps(data, indent=2), encoding="utf-8")
    os.replace(tmp_path, path)
