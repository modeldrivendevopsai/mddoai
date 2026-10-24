"""The pipelines a person has generated, kept so the "Your pipelines" screen
can bring them back to their work.

One folder per pipeline, named by a random id: a small `meta.json` (what the
list shows) beside a `record.json` (everything needed to reopen it). The list
reads only the small files. A pipeline is identified by its model input (the
workspace path it was loaded from, else the file name), and the newest
generation for an input replaces the earlier one, so the list has one row per
input.
"""
import json
import logging
import os
import re
import shutil
import tempfile
import threading
import uuid
from datetime import datetime, timezone
from pathlib import Path

logger = logging.getLogger(__name__)

_ID_PATTERN = re.compile(r"[0-9a-f]{32}")

# The text fields every list row is built from, besides its id and its lists.
_TEXT_FIELDS = ("key", "swarch_name", "generated_at")


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


def _write_json(path: Path, value: dict) -> None:
    """Written beside the target and renamed into place, so a crash part way
    leaves the previous complete version, never a half-written one."""
    descriptor, temporary = tempfile.mkstemp(dir=path.parent, prefix=".writing-")
    try:
        with os.fdopen(descriptor, "w", encoding="utf-8") as handle:
            json.dump(value, handle)
        os.replace(temporary, path)
    except BaseException:
        Path(temporary).unlink(missing_ok=True)
        raise


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
            folder = self._directory / pipeline_id
            folder.mkdir(parents=True, exist_ok=True)
            meta = {"id": pipeline_id, **meta_base}
            # Record first: a list row must never point at a record that is missing.
            _write_json(folder / "record.json", {**meta, "content": content, "save_to": save_to, "results": results})
            _write_json(folder / "meta.json", meta)
            self._forget_the_oldest(keep=pipeline_id)
        return pipeline_id

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
        except OSError as e:
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
            shutil.rmtree(self._directory / meta["id"], ignore_errors=True)

    def summaries(self) -> list[dict]:
        """Every pipeline, newest first, without the bulky parts."""
        with self._lock:
            metas = self._metas()
        return sorted(metas, key=lambda meta: meta["generated_at"], reverse=True)

    def get(self, pipeline_id: str) -> dict | None:
        folder = self._folder(pipeline_id)
        if folder is None:
            return None
        with self._lock:
            try:
                record = json.loads((folder / "record.json").read_text(encoding="utf-8"))
            except (OSError, ValueError):
                return None
        return record if isinstance(record, dict) else None
