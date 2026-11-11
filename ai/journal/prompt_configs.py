"""The prompt configs of the agents that write prompts: each is a versioned document
the agent edits, and the learned constraints kept beside it.

prompts/<agent>/<name>/ holds default.json (the live config), default.default.json
(the shipped starting config, which git carries), history/default.<version>.json (one
snapshot per save) and constraints.json (the permanent learned constraints, which are
never versioned, so restoring an older config does not lose them).

A config is a dict with an "attachments" list. What an attachment means, and whether
a config is valid, is the agent's to say: this store keeps what it is given.
"""
import json
import os
import re
import secrets
import threading
from datetime import datetime, timezone
from pathlib import Path

# An agent's or a config's name, which becomes a folder.
_NAME = re.compile(r"[A-Za-z0-9_-]{1,64}")
# A saved version's id: a timestamp and a random suffix.
_VERSION = re.compile(r"[A-Za-z0-9._-]{1,64}")
_LIVE = "default.json"
_SHIPPED = "default.default.json"
_HISTORY = "history"
_HISTORY_PREFIX = "default."
_CONSTRAINTS = "constraints.json"


class PromptConfigNotFound(KeyError):
    """No such config, version or constraints, or a name that cannot be one."""


def _name(value: str, what: str) -> str:
    if not isinstance(value, str) or not _NAME.fullmatch(value):
        raise PromptConfigNotFound(f"not a {what} that can name a folder: {value!r}")
    return value


def _version(value: str) -> str:
    if not isinstance(value, str) or not _VERSION.fullmatch(value) or set(value) == {"."}:
        raise PromptConfigNotFound(f"not a version: {value!r}")
    return value


def _read(path: Path) -> dict:
    return json.loads(path.read_text(encoding="utf-8"))


def _write(path: Path, data: dict) -> None:
    """Writes data as JSON through a temp file that is renamed into place, so the
    file is never half written."""
    path.parent.mkdir(parents=True, exist_ok=True)
    temporary = path.with_name(f".{path.name}.{secrets.token_hex(6)}")
    try:
        temporary.write_text(json.dumps(data, indent=2), encoding="utf-8")
        os.replace(temporary, path)
    except BaseException:
        temporary.unlink(missing_ok=True)
        raise


class PromptConfigStore:
    def __init__(self, prompts_dir: Path, max_versions: int):
        self._root = prompts_dir
        # How many saved versions of one config are kept, the oldest pruned first.
        # The shipped config is not a version.
        self._max_versions = max_versions
        # One writer at a time: this service runs as one process.
        self._lock = threading.Lock()

    def _folder(self, agent: str, name: str) -> Path:
        return self._root / _name(agent, "agent") / _name(name, "config")

    def live(self, agent: str, name: str) -> dict:
        """The live config, or the shipped one when none was saved."""
        folder = self._folder(agent, name)
        for path in (folder / _LIVE, folder / _SHIPPED):
            if path.is_file():
                return _read(path)
        raise PromptConfigNotFound(f"no config {name!r} for {agent!r}, and no shipped one")

    def shipped(self, agent: str, name: str) -> dict:
        path = self._folder(agent, name) / _SHIPPED
        if not path.is_file():
            raise PromptConfigNotFound(f"no shipped config {name!r} for {agent!r}")
        return _read(path)

    def save(self, agent: str, name: str, config: dict) -> dict:
        """Keeps config as the live one and as a new version, stamped with the
        version's id. A config whose attachments are those of the live one is not
        a change: the live config is returned and nothing is written, so a save
        that is sent twice does not pile up identical versions."""
        folder = self._folder(agent, name)
        with self._lock:
            live_path = folder / _LIVE
            if live_path.is_file():
                live = _read(live_path)
                if live.get("attachments") == config.get("attachments"):
                    return live
            # The timestamp keeps the ids in the order they were made. Two saves in the
            # same microsecond still differ by the random suffix.
            version = f"{datetime.now(timezone.utc).strftime('%Y%m%dT%H%M%S.%fZ')}-{secrets.token_hex(3)}"
            stamped = {**config, "_version": version}
            _write(live_path, stamped)
            _write(folder / _HISTORY / f"{_HISTORY_PREFIX}{version}.json", stamped)
            for stale in self._saved_versions(folder)[self._max_versions :]:
                (folder / _HISTORY / f"{_HISTORY_PREFIX}{stale}.json").unlink(missing_ok=True)
        return stamped

    def _saved_versions(self, folder: Path) -> list[str]:
        history = folder / _HISTORY
        if not history.is_dir():
            return []
        ids = [
            entry.name.removeprefix(_HISTORY_PREFIX).removesuffix(".json")
            for entry in history.iterdir()
            if entry.name.startswith(_HISTORY_PREFIX) and entry.name.endswith(".json")
        ]
        return sorted(ids, reverse=True)

    def versions(self, agent: str, name: str) -> list[str]:
        """The saved versions' ids, newest first."""
        return self._saved_versions(self._folder(agent, name))

    def has_shipped(self, agent: str, name: str) -> bool:
        return (self._folder(agent, name) / _SHIPPED).is_file()

    def version(self, agent: str, name: str, version: str) -> dict:
        path = self._folder(agent, name) / _HISTORY / f"{_HISTORY_PREFIX}{_version(version)}.json"
        if not path.is_file():
            raise PromptConfigNotFound(f"no version {version!r} of {name!r} for {agent!r}")
        return _read(path)

    def constraints(self, agent: str, name: str) -> list[str]:
        """The learned constraints. Raises PromptConfigNotFound when none were ever kept."""
        path = self._folder(agent, name) / _CONSTRAINTS
        if not path.is_file():
            raise PromptConfigNotFound(f"no learned constraints for {name!r} of {agent!r}")
        return _read(path).get("constraints", [])

    def set_constraints(self, agent: str, name: str, constraints: list[str]) -> None:
        with self._lock:
            _write(self._folder(agent, name) / _CONSTRAINTS, {"constraints": constraints})
