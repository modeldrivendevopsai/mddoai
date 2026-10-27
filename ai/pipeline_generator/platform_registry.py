"""Platforms published from integration runs, kept so this service can
generate pipelines for them next to its built-in platforms.

One folder per platform, one subfolder per published version:

    <platform-id>/<version>/
        metamodel.ecore      the platform's metamodel (PSM)
        transformation.atl   PIM to platform model
        template.mtl         platform model to pipeline file
        platform.json        name, version, output file name, output model name, source run, date

Publishing the same platform again adds the next version; earlier versions are
kept. Generation uses each platform's latest version. A version folder is
claimed with an atomic mkdir and its platform.json is written last, so a
version whose files are not all written yet is never listed.
"""
import json
import logging
import os
import re
import threading
import uuid
from dataclasses import dataclass
from datetime import datetime, timezone
from pathlib import Path

from platforms import check_output_filename

logger = logging.getLogger(__name__)

_ID_PATTERN = re.compile(r"[a-z0-9][a-z0-9-]{0,39}")
_VERSION_PATTERN = re.compile(r"[1-9][0-9]{0,5}")

# The files a version holds, by what they are.
_METAMODEL = "metamodel.ecore"
_TRANSFORMATION = "transformation.atl"
_TEMPLATE = "template.mtl"
_META = "platform.json"

# The longest display name and output file name accepted. Engineering guess:
# far longer than any real platform name or CI file name.
MAX_NAME_CHARS = 100
MAX_OUTPUT_FILENAME_CHARS = 255


class PublishError(ValueError):
    """What was sent cannot be published: the message says why, in words a
    person can act on."""


@dataclass(frozen=True)
class PublishedVersion:
    id: str
    name: str
    version: int
    output_filename: str
    output_model_name: str
    source_run_id: str | None
    published_at: str


@dataclass(frozen=True)
class Artifacts:
    metamodel: str
    transformation: str
    template: str


def platform_id_for(name: str) -> str:
    """The id a display name is published under: lowercase letters, digits and
    dashes, so it can be a folder name and a URL segment."""
    slug = re.sub(r"[^a-z0-9]+", "-", name.lower()).strip("-")[:40].strip("-")
    if not slug:
        raise PublishError("The platform name needs at least one letter or digit.")
    return slug


def _check_publish_input(name: str, output_filename: str, output_model_name: str, reserved_ids: set[str]) -> str:
    """The id `name` is published under, once everything sent is acceptable."""
    if not name or len(name) > MAX_NAME_CHARS:
        raise PublishError(f"The platform name must be 1 to {MAX_NAME_CHARS} characters.")
    platform_id = platform_id_for(name)
    if platform_id in reserved_ids:
        raise PublishError(f"'{platform_id}' is a built-in platform; publish under another name.")
    try:
        if len(output_filename) > MAX_OUTPUT_FILENAME_CHARS:
            raise ValueError("too long")
        check_output_filename(output_filename)
    except ValueError:
        raise PublishError("The output file name must be a relative path inside the folder, such as .ci.yml.")
    if not re.fullmatch(r"\w+", output_model_name):
        raise PublishError("The output model name must be a single word, as declared in the transformation.")
    return platform_id


def _write_text(path: Path, content: str) -> None:
    with open(path, "w", encoding="utf-8", newline="") as handle:
        handle.write(content)


def _read_text(path: Path) -> str:
    with open(path, encoding="utf-8", newline="") as handle:
        return handle.read()


class PlatformRegistry:
    def __init__(self, directory: Path):
        self._directory = directory
        # The service runs as one process; the lock keeps two publishes of the
        # same platform from racing for a version number.
        self._lock = threading.Lock()

    def _versions(self, platform_id: str) -> list[PublishedVersion]:
        folder = self._directory / platform_id
        if not folder.is_dir():
            return []
        versions = []
        for entry in folder.iterdir():
            if not _VERSION_PATTERN.fullmatch(entry.name):
                continue
            meta = self._read_meta(entry)
            if meta is not None and meta.id == platform_id and meta.version == int(entry.name):
                versions.append(meta)
        return sorted(versions, key=lambda version: version.version)

    @staticmethod
    def _read_meta(folder: Path) -> PublishedVersion | None:
        try:
            raw = json.loads((folder / _META).read_text(encoding="utf-8"))
            return PublishedVersion(
                id=str(raw["id"]),
                name=str(raw["name"]),
                version=int(raw["version"]),
                output_filename=check_output_filename(str(raw["output_filename"])),
                output_model_name=str(raw["output_model_name"]),
                source_run_id=raw.get("source_run_id"),
                published_at=str(raw["published_at"]),
            )
        except (OSError, ValueError, KeyError, TypeError):
            return None

    def latest(self) -> list[PublishedVersion]:
        """Every published platform's latest complete version, by name."""
        if not self._directory.is_dir():
            return []
        with self._lock:
            found = []
            for entry in self._directory.iterdir():
                if _ID_PATTERN.fullmatch(entry.name):
                    versions = self._versions(entry.name)
                    if versions:
                        found.append(versions[-1])
        return sorted(found, key=lambda version: version.name.lower())

    def get_latest(self, platform_id: str) -> PublishedVersion | None:
        if not _ID_PATTERN.fullmatch(platform_id):
            return None
        with self._lock:
            versions = self._versions(platform_id)
        return versions[-1] if versions else None

    def get(self, platform_id: str, version: int) -> PublishedVersion | None:
        """One complete version. Versions are never changed or removed once
        published, so a version found earlier can still be loaded after a
        newer one appears."""
        if not _ID_PATTERN.fullmatch(platform_id) or version < 1:
            return None
        meta = self._read_meta(self._directory / platform_id / str(version))
        return meta if meta is not None and meta.id == platform_id and meta.version == version else None

    def artifacts(self, version: PublishedVersion) -> Artifacts:
        folder = self._directory / version.id / str(version.version)
        return Artifacts(
            metamodel=_read_text(folder / _METAMODEL),
            transformation=_read_text(folder / _TRANSFORMATION),
            template=_read_text(folder / _TEMPLATE),
        )

    def publish(
        self,
        *,
        name: str,
        output_filename: str,
        output_model_name: str,
        artifacts: Artifacts,
        source_run_id: str | None,
        reserved_ids: set[str],
    ) -> PublishedVersion:
        """Stores a new version and returns it. `reserved_ids` are the built-in
        platforms' ids, which a published platform may not take."""
        name = name.strip()
        platform_id = _check_publish_input(name, output_filename, output_model_name, reserved_ids)

        with self._lock:
            folder = self._directory / platform_id
            folder.mkdir(parents=True, exist_ok=True)
            version_folder, version = self._claim_next_version(folder)
            published = PublishedVersion(
                id=platform_id,
                name=name,
                version=version,
                output_filename=output_filename,
                output_model_name=output_model_name,
                source_run_id=source_run_id,
                published_at=datetime.now(timezone.utc).isoformat(timespec="seconds"),
            )
            try:
                _write_text(version_folder / _METAMODEL, artifacts.metamodel)
                _write_text(version_folder / _TRANSFORMATION, artifacts.transformation)
                _write_text(version_folder / _TEMPLATE, artifacts.template)
                self._write_meta(version_folder, published)
            except BaseException:
                # The version folder stays claimed but has no platform.json, so
                # it is never listed and its number is never reused.
                logger.error("publishing %s version %d did not complete", platform_id, version)
                raise
        return published

    @staticmethod
    def _claim_next_version(folder: Path) -> tuple[Path, int]:
        existing = [int(entry.name) for entry in folder.iterdir() if _VERSION_PATTERN.fullmatch(entry.name)]
        version = max(existing, default=0) + 1
        while True:
            candidate = folder / str(version)
            try:
                candidate.mkdir()
                return candidate, version
            except FileExistsError:
                version += 1

    @staticmethod
    def _write_meta(folder: Path, published: PublishedVersion) -> None:
        # Written beside its final name and renamed into place, with the
        # ordinary file mode, so it appears whole or not at all.
        temporary = folder / f".writing-{uuid.uuid4().hex}"
        descriptor = os.open(temporary, os.O_WRONLY | os.O_CREAT | os.O_EXCL, 0o666)
        try:
            with os.fdopen(descriptor, "w", encoding="utf-8") as handle:
                json.dump(published.__dict__, handle, indent=2)
            os.replace(temporary, folder / _META)
        except BaseException:
            temporary.unlink(missing_ok=True)
            raise
