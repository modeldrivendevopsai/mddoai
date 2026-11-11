"""The files a person uploads to an agent, to attach to its prompt: uploads/<agent>/<name>.

A name is chosen by the agent and is unique, so an upload never replaces another. The
files are text: that is what an attachment is when it is put into a prompt.
"""
import os
import re
from pathlib import Path

_AGENT = re.compile(r"[A-Za-z0-9_-]{1,64}")
_NAME = re.compile(r"[A-Za-z0-9._-]{1,255}")


class UploadNotFound(KeyError):
    """No such file, or a name that cannot be one."""


class UploadExists(Exception):
    """A file is already kept under that name."""


class UploadTooLarge(ValueError):
    """The file is over the size an upload may be."""


def _agent(value: str) -> str:
    if not isinstance(value, str) or not _AGENT.fullmatch(value):
        raise UploadNotFound(f"not an agent that can name a folder: {value!r}")
    return value


def _name(value: str) -> str:
    if not isinstance(value, str) or not _NAME.fullmatch(value) or set(value) == {"."}:
        raise UploadNotFound(f"not a file name: {value!r}")
    return value


class UploadStore:
    def __init__(self, attachments_dir: Path, max_bytes: int):
        self._root = attachments_dir
        self._max_bytes = max_bytes

    def save(self, agent: str, name: str, content: str) -> None:
        """Keeps the file. Raises UploadExists when the name is taken and UploadTooLarge
        when the file is over the limit."""
        path = self._root / _agent(agent) / _name(name)
        data = content.encode("utf-8")
        if len(data) > self._max_bytes:
            raise UploadTooLarge(f"the file is {len(data)} bytes, over the {self._max_bytes}-byte limit")
        path.parent.mkdir(parents=True, exist_ok=True)
        try:
            # Exclusive: two uploads that chose the same name cannot overwrite each other.
            descriptor = os.open(path, os.O_WRONLY | os.O_CREAT | os.O_EXCL, 0o644)
        except FileExistsError as e:
            raise UploadExists(f"a file named {name!r} is already kept") from e
        with os.fdopen(descriptor, "wb") as handle:
            handle.write(data)

    def names(self, agent: str) -> list[str]:
        folder = self._root / _agent(agent)
        if not folder.is_dir():
            return []
        return sorted(entry.name for entry in folder.iterdir() if entry.is_file())

    def read(self, agent: str, name: str) -> str:
        path = self._root / _agent(agent) / _name(name)
        if not path.is_file():
            raise UploadNotFound(f"no file named {name!r}")
        return path.read_text(encoding="utf-8")
