"""A run's attempts: what each try of a stage produced, kept apart so a failed
try is as much on record as a good one.

runs/<run_id>/<stage>/attempt_N/ holds the artifact (any file name), result.json
and, when the try had a prompt, prompt.json. A try can also leave other files
beside them, such as an intermediate model or each file a template generated,
which can sit in subfolders. runs/<run_id>/manifest.json lists every recorded
attempt of the run, in order.

An attempt is reserved first, which claims its number with an atomic mkdir, and
recorded after. result.json is written last and in one piece: its presence is
what tells a recorded attempt from a reserved one that a failed call left behind.
"""
import json
import os
import re
import shutil
import threading
from datetime import datetime, timezone
from pathlib import Path

_SEGMENT = re.compile(r"[A-Za-z0-9_-]{1,64}")
_ATTEMPT = re.compile(r"attempt_[1-9][0-9]{0,5}")
# One file name: no folder separator, no drive colon, no control character.
_FILE_NAME = re.compile(r"[^\x00-\x1f/\\:]{1,255}")
_RESULT = "result.json"
_PROMPT = "prompt.json"
_MANIFEST = "manifest.json"
# What the validators compiled during the attempt, as one zip. It is kept while the attempt is
# being worked on, before it is recorded.
_COMPILED = "compiled.zip"
# Names which of the attempt's files is its artifact, since the others, such as an
# intermediate model, sit beside it.
_ARTIFACT_NAME = ".artifact"
# The longest path of an extra file, in characters. Engineering guess: far longer
# than any generated file's name.
_MAX_EXTRA_PATH_CHARS = 1024
# The longest run id, stage or other name that becomes a folder, in characters.
_MAX_NAME_CHARS = 64


class AttemptNotFound(KeyError):
    """No such run, attempt or manifest, or a name that cannot be one."""


class AttemptConflict(Exception):
    """The attempt is already recorded, so it cannot be recorded or discarded."""


def _segment(value: str, what: str) -> str:
    if not isinstance(value, str) or not _SEGMENT.fullmatch(value):
        raise AttemptNotFound(f"not a {what} that can name a folder: {value!r}")
    return value


def _attempt_name(value: str) -> str:
    if not isinstance(value, str) or not _ATTEMPT.fullmatch(value):
        raise AttemptNotFound(f"not an attempt: {value!r}")
    return value


class AttemptStore:
    def __init__(self, runs_dir: Path):
        self._runs_dir = runs_dir
        # Serializes the manifest's read-modify-write. This service runs as one process.
        self._manifest_lock = threading.Lock()

    def reserve(self, run_id: str, stage: str) -> str:
        """The next attempt of the stage in the run, claimed by an atomic mkdir
        and not by counting the folder first, so two callers never get one number."""
        stage_dir = self._runs_dir / _segment(run_id, "run id") / _segment(stage, "stage")
        stage_dir.mkdir(parents=True, exist_ok=True)
        number = 1
        while True:
            try:
                (stage_dir / f"attempt_{number}").mkdir()
                return f"attempt_{number}"
            except FileExistsError:
                number += 1

    def discard(self, run_id: str, stage: str, attempt: str) -> None:
        """Removes a reserved attempt that was never recorded, so a failed call
        leaves no stray folder and no skipped number. Raises AttemptConflict
        for one that is recorded."""
        folder = self._attempt_dir(run_id, stage, attempt)
        if (folder / _RESULT).exists():
            raise AttemptConflict(f"{attempt} is recorded, so it is kept")
        shutil.rmtree(folder, ignore_errors=True)

    def record(
        self,
        run_id: str,
        stage: str,
        attempt: str,
        *,
        filename: str,
        content: str,
        result: dict,
        prompt: dict | None,
        prompt_version: str | None,
        extra_files: dict[str, str] | None = None,
    ) -> None:
        """Writes the attempt's artifact, its result, its prompt if it had one
        and any extra files, and adds it to the run's manifest. The attempt must
        be reserved."""
        if filename in (_RESULT, _PROMPT) or not _FILE_NAME.fullmatch(filename) or filename in (".", ".."):
            raise ValueError(f"not a file name for an artifact: {filename!r}")
        if not isinstance(result.get("valid"), bool):
            raise ValueError("the result needs a valid field that is true or false")
        folder = self._attempt_dir(run_id, stage, attempt)
        if (folder / _RESULT).exists():
            raise AttemptConflict(f"{attempt} is already recorded")
        extras = {self._extra_target(folder, name, filename): text for name, text in (extra_files or {}).items()}
        (folder / filename).write_text(content, encoding="utf-8")
        (folder / _ARTIFACT_NAME).write_text(filename, encoding="utf-8")
        for target, text in extras.items():
            try:
                target.parent.mkdir(parents=True, exist_ok=True)
                target.write_text(text, encoding="utf-8")
            except (FileExistsError, NotADirectoryError, IsADirectoryError) as e:
                raise ValueError(f"the extra files cannot be stored together, at {target.name!r}") from e
        if prompt is not None:
            (folder / _PROMPT).write_text(
                json.dumps({"prompt": prompt, "prompt_version": prompt_version}, indent=2), encoding="utf-8"
            )
        self._write_json(folder / _RESULT, result)
        self._add_to_manifest(run_id, stage, int(attempt.removeprefix("attempt_")), result["valid"])

    def keep_compiled(self, run_id: str, stage: str, attempt: str, data: bytes) -> None:
        """Keeps what the validators compiled during the attempt, as one zip. It is kept while the
        attempt is being worked on: a recorded attempt is final, so a recorded one is refused. A
        second call replaces the first."""
        folder = self._attempt_dir(run_id, stage, attempt)
        if (folder / _RESULT).exists():
            raise AttemptConflict(f"{attempt} is already recorded")
        temporary = folder / f".{_COMPILED}.{os.getpid()}.{threading.get_ident()}"
        try:
            temporary.write_bytes(data)
            os.replace(temporary, folder / _COMPILED)
        except BaseException:
            temporary.unlink(missing_ok=True)
            raise

    def compiled(self, run_id: str, stage: str, attempt: str) -> bytes:
        """What the validators compiled during the attempt, as the zip it was kept as."""
        path = self._attempt_dir(run_id, stage, attempt) / _COMPILED
        if not path.is_file():
            raise AttemptNotFound(f"nothing was compiled during {attempt} of {stage!r} in run {run_id!r}")
        return path.read_bytes()

    def manifest(self, run_id: str) -> list[dict]:
        path = self._runs_dir / _segment(run_id, "run id") / _MANIFEST
        if not path.is_file():
            raise AttemptNotFound(f"no manifest for run {run_id!r}")
        return json.loads(path.read_text(encoding="utf-8"))

    def read(self, run_id: str, stage: str, attempt: str) -> dict:
        """One recorded attempt: {"artifact": {"filename", "content"} | None,
        "result": dict, "prompt": dict | None, "prompt_version": str | None}."""
        folder = self._attempt_dir(run_id, stage, attempt)
        result_path = folder / _RESULT
        if not result_path.is_file():
            raise AttemptNotFound(f"{attempt} of {stage!r} in run {run_id!r} is not recorded")
        artifact = self._read_artifact(folder)
        prompt = prompt_version = None
        if (folder / _PROMPT).is_file():
            record = json.loads((folder / _PROMPT).read_text(encoding="utf-8"))
            prompt, prompt_version = record.get("prompt"), record.get("prompt_version")
        return {
            "artifact": artifact,
            "result": json.loads(result_path.read_text(encoding="utf-8")),
            "prompt": prompt,
            "prompt_version": prompt_version,
        }

    @staticmethod
    def _read_artifact(folder: Path) -> dict | None:
        """The attempt's artifact. An attempt recorded before the artifact was
        named has the first of its files, by name, standing in for it."""
        marker = folder / _ARTIFACT_NAME
        if marker.is_file():
            path = folder / marker.read_text(encoding="utf-8")
            return {"filename": path.name, "content": path.read_text(encoding="utf-8")} if path.is_file() else None
        files = [
            path
            for path in sorted(folder.iterdir())
            if path.is_file() and path.name not in (_RESULT, _PROMPT, _COMPILED) and path.name != _COMPILED and not path.name.startswith(".")
        ]
        return {"filename": files[0].name, "content": files[0].read_text(encoding="utf-8")} if files else None

    @staticmethod
    def _extra_target(folder: Path, name: str, artifact: str) -> Path:
        """Where an extra file goes, which is inside the attempt's folder. The
        name is a relative path: no empty or dot part, no drive colon, backslash
        or control character, and it clashes with no file the attempt keeps."""
        parts = name.split("/")
        if (
            not name
            or len(name) > _MAX_EXTRA_PATH_CHARS
            or any(part in ("", ".", "..") or not _FILE_NAME.fullmatch(part) for part in parts)
            or parts[0] in (_RESULT, _PROMPT, _COMPILED, _ARTIFACT_NAME, artifact)
        ):
            raise ValueError(f"not a path for an extra file: {name!r}")
        target = folder.joinpath(*parts)
        if not target.resolve().is_relative_to(folder.resolve()):
            raise ValueError(f"not a path for an extra file: {name!r}")
        return target

    def _attempt_dir(self, run_id: str, stage: str, attempt: str) -> Path:
        folder = self._runs_dir / _segment(run_id, "run id") / _segment(stage, "stage") / _attempt_name(attempt)
        if not folder.is_dir():
            raise AttemptNotFound(f"no attempt at {run_id!r}/{stage!r}/{attempt!r}")
        return folder

    def _add_to_manifest(self, run_id: str, stage: str, attempt_number: int, valid: bool) -> None:
        path = self._runs_dir / run_id / _MANIFEST
        entry = {
            "run_id": run_id,
            "stage": stage,
            "attempt_n": attempt_number,
            "valid": valid,
            "timestamp": datetime.now(timezone.utc).isoformat(),
        }
        with self._manifest_lock:
            manifest = json.loads(path.read_text(encoding="utf-8")) if path.exists() else []
            manifest.append(entry)
            self._write_json(path, manifest)

    @staticmethod
    def _write_json(path: Path, data) -> None:
        """Writes beside the final name and renames into place, so a reader
        sees the old file or the new one, never half of one."""
        temporary = path.with_name(f".{path.name}.{os.getpid()}.{threading.get_ident()}")
        temporary.write_text(json.dumps(data, indent=2), encoding="utf-8")
        os.replace(temporary, path)
