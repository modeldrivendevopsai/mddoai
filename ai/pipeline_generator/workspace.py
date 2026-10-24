"""The user's workspace folder: where a model file can be picked from and
where generated pipeline files are saved.

A web page can neither read nor write a path on the user's machine, so the
service works inside one folder that is mounted into it. Every path a caller
supplies is read as relative to that folder ("/repos/app/a.swarch",
"./repos/app/a.swarch" and "repos/app/a.swarch" are the same file), and
whatever it resolves to, links included, must still be inside it. Nothing
outside the folder is listed, read or written, provided nothing else is
changing the folder's links while a request runs: a path is checked and then
used, not held open in between.
"""
import logging
import os
import uuid
from dataclasses import dataclass
from pathlib import Path

logger = logging.getLogger(__name__)

# A folder listing is for a person to click through, not to enumerate.
# Engineering guess, well above what a project folder holds.
MAX_LISTED_ENTRIES = 500

# The only kind of file this service reads or lists. The folder is mounted
# from the person's own disk and may hold anything, so a request can only get
# an architecture model out of it, never an arbitrary file.
MODEL_EXTENSION = ".swarch"

# What the filesystem raises for a path it cannot follow or look at. Python
# 3.12 reports a link loop as RuntimeError, later versions as OSError, and
# only some OS errors (a missing path, a loop) are swallowed by pathlib's own
# is_dir/is_file, so a permission error would otherwise escape.
_UNFOLLOWABLE = (OSError, RuntimeError)


def _is_model_file(path: Path) -> bool:
    return path.suffix.lower() == MODEL_EXTENSION


def _is_dir(path: Path) -> bool:
    try:
        return path.is_dir()
    except _UNFOLLOWABLE:
        return False


def _is_file(path: Path) -> bool:
    try:
        return path.is_file()
    except _UNFOLLOWABLE:
        return False


def _is_usable_name(name: str) -> bool:
    """Whether a name can be shown and later typed back: it must survive JSON
    (a name that is not valid UTF-8 cannot) and must not contain the one
    separator `_resolve` reads as a slash, which would make it unopenable."""
    try:
        name.encode("utf-8")
    except UnicodeEncodeError:
        return False
    return "\\" not in name


class WorkspaceError(Exception):
    """A path problem the caller can act on. `status` is the HTTP status to
    report it with; the message never names anything outside the workspace."""

    def __init__(self, message: str, status: int = 422):
        super().__init__(message)
        self.message = message
        self.status = status


@dataclass(frozen=True)
class SavedFile:
    # Where the file went, from the workspace folder with a leading slash.
    path: str
    # True when a file of that name was already there and was replaced.
    replaced: bool


class Workspace:
    def __init__(self, root: Path):
        self._root = root

    @property
    def available(self) -> bool:
        return self._root.is_dir()

    def _root_resolved(self) -> Path:
        if not self.available:
            raise WorkspaceError("The workspace folder is not available.", 503)
        return self._root.resolve()

    @staticmethod
    def _inside(resolved: Path, root: Path) -> bool:
        return resolved == root or resolved.is_relative_to(root)

    def _resolve(self, relative: str) -> Path:
        """The absolute, link-free path `relative` points at, or an error if
        it leads outside the workspace."""
        if "\x00" in relative:
            raise WorkspaceError("That is not a valid path.")
        root = self._root_resolved()
        # Windows-style separators are accepted too; a leading slash means
        # "from the workspace folder", not from the machine's root.
        cleaned = relative.replace("\\", "/").lstrip("/")
        try:
            candidate = (root / cleaned).resolve()
        except _UNFOLLOWABLE:
            raise WorkspaceError("That path could not be followed.")
        if not self._inside(candidate, root):
            raise WorkspaceError("That path is outside the workspace folder.")
        return candidate

    def display(self, path: Path) -> str:
        """`path` as the caller sees it: from the workspace folder, with a leading slash."""
        root = self._root_resolved()
        relative = path.relative_to(root).as_posix()
        return "/" if relative == "." else "/" + relative

    def normalize(self, relative: str) -> str | None:
        """The display form of `relative`, or None if it is not a usable path
        inside the workspace. Follows links, but the path need not exist."""
        try:
            return self.display(self._resolve(relative))
        except WorkspaceError:
            return None

    def _offered_kind(self, entry: os.DirEntry, root: Path, dirs_only: bool) -> str | None:
        """`dir`, `file`, or None if `entry` is not offered: a name that cannot
        be used, a file that is not a model, a link that leaves the workspace
        or leads to another kind of file, or anything that cannot be looked at.

        The cheap name tests come first, and a link is the only thing that
        needs resolving, so a folder with very many entries stays quick."""
        if not _is_usable_name(entry.name):
            return None
        try:
            is_dir = entry.is_dir()
            if not is_dir and (dirs_only or not _is_model_file(Path(entry.name)) or not entry.is_file()):
                return None
            if entry.is_symlink():
                resolved = Path(entry.path).resolve()
                if not self._inside(resolved, root):
                    return None
                # A link named like a model but pointing at another kind of
                # file would otherwise be a way to name and read that file.
                if not is_dir and not _is_model_file(resolved):
                    return None
        except _UNFOLLOWABLE:
            return None
        return "dir" if is_dir else "file"

    def browse(self, relative: str, dirs_only: bool) -> dict:
        directory = self._resolve(relative)
        if not _is_dir(directory):
            raise WorkspaceError("There is no such folder in the workspace.", 404)
        root = self._root_resolved()
        offered: list[tuple[str, str]] = []
        try:
            with os.scandir(directory) as scan:
                for entry in scan:
                    kind = self._offered_kind(entry, root, dirs_only)
                    if kind is not None:
                        offered.append((entry.name, kind))
        except OSError as e:
            logger.warning("could not list %s: %s", directory, e)
            raise WorkspaceError("Could not list this folder.", 500)
        offered.sort(key=lambda item: (item[1] != "dir", item[0].lower()))
        return {
            "path": self.display(directory),
            "parent": None if directory == root else self.display(directory.parent),
            "entries": [{"name": name, "kind": kind} for name, kind in offered[:MAX_LISTED_ENTRIES]],
            "truncated": len(offered) > MAX_LISTED_ENTRIES,
        }

    def read_text(self, relative: str, limit: int) -> dict:
        # The name is judged first, before the disk is consulted, so nothing
        # about what is or is not there (or leads outside) is revealed for
        # anything that is not a model name.
        not_a_model = WorkspaceError(f"Only {MODEL_EXTENSION} architecture files can be opened here.")
        if not _is_model_file(Path(relative.replace("\\", "/"))):
            raise not_a_model
        path = self._resolve(relative)
        # And again for what the name leads to: a link can be named like a model.
        if not _is_model_file(path):
            raise not_a_model
        if not _is_file(path):
            raise WorkspaceError("There is no file at that path in the workspace.", 404)
        try:
            with path.open("rb") as handle:
                raw = handle.read(limit + 1)
        except OSError as e:
            logger.warning("could not read %s: %s", path, e)
            raise WorkspaceError("Could not read that file.", 500)
        if len(raw) > limit:
            raise WorkspaceError(f"That file is larger than the {limit} byte limit.", 413)
        try:
            content = raw.decode("utf-8")
        except UnicodeDecodeError:
            raise WorkspaceError("That file is not text.")
        return {"filename": path.name, "path": self.display(path), "content": content}

    def write_text(self, relative_dir: str, filename: str, content: str) -> SavedFile:
        """Saves `content` as `filename` inside the folder `relative_dir`
        (created if needed). `filename` is one plain file name, never a path.
        An existing file of that name is replaced, and the result says so. A
        link of that name is replaced by the new file, never written through
        to whatever it points at."""
        if Path(filename).name != filename or filename in ("", ".", ".."):
            raise WorkspaceError("That is not a valid file name.")
        directory = self._resolve(relative_dir)
        target = directory / filename
        replaced = os.path.lexists(target)
        try:
            directory.mkdir(parents=True, exist_ok=True)
            # Written beside the target and renamed into place, so a failure
            # part way does not leave half a pipeline file at the target. Created
            # with the ordinary file mode (the umask applies), not a private one.
            temporary = directory / f".saving-{uuid.uuid4().hex}"
            descriptor = os.open(temporary, os.O_WRONLY | os.O_CREAT | os.O_EXCL, 0o666)
            try:
                with os.fdopen(descriptor, "w", encoding="utf-8", newline="") as handle:
                    handle.write(content)
                os.replace(temporary, target)
            except BaseException:
                temporary.unlink(missing_ok=True)
                raise
        except OSError as e:
            logger.warning("could not save %s: %s", target, e)
            raise WorkspaceError("Could not save to that folder.", 500)
        return SavedFile(path=self.display(target), replaced=replaced)
