"""The pipelines a person has generated, kept so the "Your pipelines" screen
can bring them back to their work.

One folder per pipeline, named by a random id, holding the generation as real
files a person can open:

    meta.json                          what the list shows
    record.json                        the rest of what reopening needs, without file contents
    model.swarch                       the model it was generated from
    <platform-id>/pipeline/<output file>  each generated pipeline (a relative path, so
                                          it can sit in a subfolder)
    <platform-id>/intermediates/<file>    the models the engine passed through on the way

The pipeline and the intermediates sit in separate folders, so no output path
can collide with an intermediate file.

The list reads only the small files. A pipeline is identified by its model
input (the workspace path it was loaded from, else the file name), and the
newest generation for an input replaces the earlier one, so the list has one
row per input.
"""
import json
import logging
import os
import re
import shutil
import threading
import uuid
from datetime import datetime, timezone
from pathlib import Path

from platforms import check_output_filename

logger = logging.getLogger(__name__)

_ID_PATTERN = re.compile(r"[0-9a-f]{32}")

# The text fields every list row is built from, besides its id and its lists.
_TEXT_FIELDS = ("key", "swarch_name", "generated_at")

_MODEL_FILENAME = "model.swarch"
_PIPELINE_DIR = "pipeline"
_INTERMEDIATES_DIR = "intermediates"

# Folders that are not (yet, or any more) a pipeline. None of these prefixes
# can start an id, so none of these folders is ever listed.
# A generation is written into a ".writing-" folder, then renamed into place.
_WRITING_PREFIX = ".writing-"
# The generation it replaces is moved aside first, as ".replaced-<id>-...",
# so a crash before the new one is in place can still put it back.
_REPLACED_PREFIX = ".replaced-"
# A pipeline being forgotten is moved aside as ".removing-..." before it is
# deleted, so a deletion that stops part way never leaves a listed, broken row.
_REMOVING_PREFIX = ".removing-"


def _is_usable(meta: object, folder_name: str) -> bool:
    """Whether `meta` is a complete list row for the folder it was found in.
    An entry that parses but lacks what the list shows (or names some other
    folder than its own) is as unusable as one that does not parse."""
    return (
        isinstance(meta, dict)
        and _ID_PATTERN.fullmatch(folder_name) is not None
        and meta.get("id") == folder_name
        and all(isinstance(meta.get(name), str) for name in _TEXT_FIELDS)
        and isinstance(meta.get("source_path"), (str, type(None)))
        and isinstance(meta.get("platforms"), list)
        and isinstance(meta.get("outputs"), list)
    )


def _plain_name(name: object) -> str:
    """`name` as one path segment. Platform ids and file names come from the
    platform definitions, never from a request, but record.json is read back
    from disk, so a name that would step outside its folder is refused."""
    if not isinstance(name, str) or name in ("", ".", "..") or Path(name).name != name:
        raise ValueError(f"not a plain file name: {name!r}")
    return name


def _write_json(path: Path, value: dict) -> None:
    """Written beside the target and renamed into place, so a crash part way
    leaves the previous complete version, never a half-written one. Created
    with the ordinary file mode (the umask applies), not tempfile's private
    one, so the history can be read from the host like the rest of the data
    folder."""
    temporary = path.parent / f".writing-{uuid.uuid4().hex}"
    descriptor = os.open(temporary, os.O_WRONLY | os.O_CREAT | os.O_EXCL, 0o666)
    try:
        with os.fdopen(descriptor, "w", encoding="utf-8") as handle:
            json.dump(value, handle)
        os.replace(temporary, path)
    except BaseException:
        temporary.unlink(missing_ok=True)
        raise


# newline="" on both sides keeps a file's line endings exactly as generated.
def _write_text(path: Path, content: str) -> None:
    path.parent.mkdir(parents=True, exist_ok=True)
    with open(path, "w", encoding="utf-8", newline="") as handle:
        handle.write(content)


def _pipeline_path(platform_dir: Path, output_filename: str) -> Path:
    """Where a recorded pipeline file is. Pipelines recorded before the
    pipeline/ folder existed sit directly in the platform's folder, and are
    read from there."""
    path = platform_dir / _PIPELINE_DIR / output_filename
    earlier = platform_dir / output_filename
    return earlier if not path.is_file() and earlier.is_file() else path


def _read_text(path: Path) -> str:
    with open(path, encoding="utf-8", newline="") as handle:
        return handle.read()


def _stored_result(result: dict) -> dict:
    """A result as record.json keeps it: everything but the file contents,
    which live in their own files."""
    kept = {name: value for name, value in result.items() if name not in ("pipeline", "intermediates")}
    kept["intermediates"] = [
        {name: value for name, value in intermediate.items() if name != "content"}
        for intermediate in result.get("intermediates", [])
    ]
    return kept


class PipelineHistory:
    def __init__(self, directory: Path, max_entries: int | None = None):
        self._directory = directory
        # When set, the oldest pipelines beyond this many are forgotten as new
        # ones are recorded, so the folder cannot grow without limit.
        self._max_entries = max_entries
        # The service runs as one process, so one lock is enough to keep a
        # find-then-write from interleaving with another one.
        self._lock = threading.Lock()

    def _folder(self, pipeline_id: str) -> Path | None:
        # The id becomes a path segment, so only the exact shape this class
        # itself generates is accepted.
        if not _ID_PATTERN.fullmatch(pipeline_id):
            return None
        return self._directory / pipeline_id

    def _metas(self) -> list[dict]:
        metas = []
        if not self._directory.is_dir():
            return metas
        for folder in self._directory.iterdir():
            try:
                meta = json.loads((folder / "meta.json").read_text(encoding="utf-8"))
            except (OSError, ValueError):
                continue
            if _is_usable(meta, folder.name):
                metas.append(meta)
        return metas

    def record(
        self,
        *,
        key: str,
        swarch_name: str,
        source_path: str | None,
        content: str,
        save_to: str | None,
        results: list[dict],
        generated_at: str | None = None,
    ) -> str:
        """Stores a generation and returns its id: the id of the earlier
        generation for the same input if there is one, else a new one."""
        meta_base = {
            "key": key,
            "swarch_name": swarch_name,
            "source_path": source_path,
            "platforms": [result["platform"] for result in results],
            "outputs": [result["output_filename"] for result in results],
            "generated_at": generated_at or datetime.now(timezone.utc).isoformat(timespec="seconds"),
        }
        with self._lock:
            existing = next((meta["id"] for meta in self._metas() if meta["key"] == key), None)
            pipeline_id = existing or uuid.uuid4().hex
            meta = {"id": pipeline_id, **meta_base}
            self._directory.mkdir(parents=True, exist_ok=True)
            self._remove_leftovers()
            # The whole folder is written first and only then put in place,
            # so the list never shows a pipeline whose files are not all there.
            # A plain mkdir, not tempfile.mkdtemp: that one is private to the
            # service's user, and the folder must stay readable from the host.
            staging = self._directory / f"{_WRITING_PREFIX}{uuid.uuid4().hex}"
            staging.mkdir()
            try:
                self._write_folder(staging, meta, content, save_to, results)
                self._put_in_place(staging, self._directory / pipeline_id)
            except BaseException:
                shutil.rmtree(staging, ignore_errors=True)
                raise
            self._forget_the_oldest(keep=pipeline_id)
        return pipeline_id

    def _write_folder(self, folder: Path, meta: dict, content: str, save_to: str | None, results: list[dict]) -> None:
        _write_text(folder / _MODEL_FILENAME, content)
        for result in results:
            # Only generations that produced a pipeline are recorded.
            if not isinstance(result.get("pipeline"), str):
                raise ValueError("a recorded result must carry its generated pipeline")
            platform_dir = folder / _plain_name(result["platform"]["id"])
            _write_text(platform_dir / _PIPELINE_DIR / check_output_filename(result["output_filename"]), result["pipeline"])
            for intermediate in result.get("intermediates", []):
                path = platform_dir / _INTERMEDIATES_DIR / _plain_name(intermediate["filename"])
                _write_text(path, intermediate["content"])
        stored_results = [_stored_result(result) for result in results]
        _write_json(folder / "record.json", {**meta, "save_to": save_to, "results": stored_results})
        _write_json(folder / "meta.json", meta)

    def _put_in_place(self, staging: Path, target: Path) -> None:
        if not target.exists():
            os.rename(staging, target)
            return
        # A folder cannot be renamed over a non-empty one, so the earlier
        # generation is moved aside first and removed once the new one is in.
        # If the new one cannot be put in place, the earlier one goes back.
        replaced = self._directory / f"{_REPLACED_PREFIX}{target.name}-{uuid.uuid4().hex}"
        os.rename(target, replaced)
        try:
            os.rename(staging, target)
        except BaseException:
            os.rename(replaced, target)
            raise
        shutil.rmtree(replaced, ignore_errors=True)

    def _remove_leftovers(self) -> None:
        # Only this class creates these folders, under the lock, and removes
        # them before returning, so any found here were left by a crash. A
        # generation moved aside whose pipeline never got its new folder is
        # the only complete copy left, so it is put back, not deleted.
        for entry in self._directory.iterdir():
            if not entry.is_dir():
                continue
            if entry.name.startswith(_REPLACED_PREFIX):
                pipeline_id = entry.name[len(_REPLACED_PREFIX):].split("-", 1)[0]
                target = self._folder(pipeline_id)
                if target is not None and not target.exists():
                    os.rename(entry, target)
                    continue
            if entry.name.startswith((_WRITING_PREFIX, _REPLACED_PREFIX, _REMOVING_PREFIX)):
                shutil.rmtree(entry, ignore_errors=True)

    def _remove(self, folder: Path) -> None:
        aside = self._directory / f"{_REMOVING_PREFIX}{uuid.uuid4().hex}"
        os.rename(folder, aside)
        shutil.rmtree(aside, ignore_errors=True)

    def record_generation(
        self,
        *,
        filename: str,
        content: str,
        source_path: str | None,
        save_to: str | None,
        generated: list[dict],
    ) -> str | None:
        """Records what was just generated, so it can be reopened. `source_path`
        is the workspace path the model was loaded from, or None for an upload.
        Returns the id, or None if there was nothing to record or recording
        failed: the pipelines were generated either way and still go back to
        the caller."""
        if not generated:
            return None
        # A model is identified by where it was loaded from, or else by its file
        # name. The prefix keeps an upload that happens to be called like a
        # workspace path from replacing that path's pipeline.
        key = f"path:{source_path}" if source_path else f"upload:{filename}"
        try:
            return self.record(
                key=key,
                swarch_name=filename,
                source_path=source_path,
                content=content,
                save_to=save_to,
                results=generated,
            )
        except (OSError, ValueError) as e:
            logger.error("could not record the generated pipeline: %s", e)
            return None

    def _forget_the_oldest(self, keep: str) -> None:
        if self._max_entries is None:
            return
        metas = self._metas()
        excess = len(metas) - self._max_entries
        if excess <= 0:
            return
        # The pipeline just recorded stays whatever its timestamp says.
        others = sorted((meta for meta in metas if meta["id"] != keep), key=lambda meta: meta["generated_at"])
        for meta in others[:excess]:
            try:
                self._remove(self._directory / meta["id"])
            except OSError as e:
                logger.warning("could not forget pipeline %s: %s", meta["id"], e)

    def summaries(self) -> list[dict]:
        """Every pipeline, newest first, without the bulky parts."""
        with self._lock:
            metas = self._metas()
        return sorted(metas, key=lambda meta: meta["generated_at"], reverse=True)

    def get(self, pipeline_id: str) -> dict | None:
        """One pipeline in full, its files read back into the record, or None
        if it is unknown or any part of it is missing or damaged."""
        folder = self._folder(pipeline_id)
        if folder is None:
            return None
        with self._lock:
            try:
                record = json.loads((folder / "record.json").read_text(encoding="utf-8"))
                if not isinstance(record, dict):
                    return None
                return self._with_files(folder, record)
            except (OSError, ValueError, KeyError, TypeError, AttributeError):
                return None

    def _with_files(self, folder: Path, record: dict) -> dict:
        results = []
        for stored in record["results"]:
            platform_dir = folder / _plain_name(stored["platform"]["id"])
            intermediates = [
                {
                    **intermediate,
                    "content": _read_text(platform_dir / _INTERMEDIATES_DIR / _plain_name(intermediate["filename"])),
                }
                for intermediate in stored["intermediates"]
            ]
            results.append({
                **stored,
                "pipeline": _read_text(_pipeline_path(platform_dir, check_output_filename(stored["output_filename"]))),
                "intermediates": intermediates,
            })
        return {**record, "content": _read_text(folder / _MODEL_FILENAME), "results": results}
