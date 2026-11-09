"""The platforms the stack generates for, one folder per platform and one
subfolder per version:

    swarch2pim.atl                the transformation every platform starts with
    <platform-id>/<version>/
        metamodel.ecore           the platform's metamodel
        transformation.atl        PIM to platform model
        template.mtl              platform model to pipeline file
        platform.json             name, output file name, name of the model the
                                  transformation creates, and for a published
                                  version where and when it was published

A version is MAJOR.MINOR.PATCH (see versions.py), and a platform's latest is its
highest one. A version is only listed when platform.json is usable and the three
files are all there, so one that is unfinished or damaged is skipped (and logged)
and the one before it is used.

Publishing adds a version and never changes an earlier one. The version folder
is claimed with an atomic mkdir and its platform.json is written last, so a
version whose files are not all written yet is never listed. Purging removes a
published version, or a platform's published versions. A platform that came with
the stack, which has no publish record, is never purged.
"""
import json
import logging
import os
import re
import shutil
import threading
import uuid
from dataclasses import asdict, dataclass
from datetime import datetime, timezone
from pathlib import Path

from . import versions

logger = logging.getLogger(__name__)

# A platform id is a folder name and a URL segment: lowercase letters, digits
# and dashes, up to 40 characters (an engineering guess, far longer than any
# real platform's name).
ID_PATTERN = re.compile(r"[a-z0-9][a-z0-9-]{0,39}")

_METAMODEL = "metamodel.ecore"
_TRANSFORMATION = "transformation.atl"
_TEMPLATE = "template.mtl"
_META = "platform.json"
# The step every platform starts with, beside the platform folders.
_PIM_TRANSFORMATION = "swarch2pim.atl"

# The longest display name and output file name accepted. Engineering guess:
# far longer than any real platform name or CI file name.
MAX_NAME_CHARS = 100
MAX_OUTPUT_FILENAME_CHARS = 255

# One file name: no folder separator, no drive colon, no control character.
_FILE_NAME = re.compile(r"[^\x00-\x1f/\\:]+")


class PlatformNotFound(KeyError):
    """No such platform or version."""


class PublishError(ValueError):
    """What was sent cannot be published. The message says why, in words a
    person can act on."""


class VersionTaken(Exception):
    """The version asked for already exists."""


class NotPurgeable(Exception):
    """A version that came with the stack cannot be purged."""


@dataclass(frozen=True)
class PlatformVersion:
    id: str
    name: str
    version: str
    # The file the platform's template generates: one plain file name.
    output_filename: str
    # The name the transformation gives the model it creates, as declared in
    # it: "create OUT : <name> from IN : PIM;".
    output_model_name: str
    source_run_id: str | None = None
    published_at: str | None = None

    @property
    def published(self) -> bool:
        return self.published_at is not None

    def as_dict(self) -> dict:
        return {**asdict(self), "published": self.published}


def _read_text(path: Path) -> str:
    with open(path, encoding="utf-8", newline="") as handle:
        return handle.read()


def _write_text(path: Path, content: str) -> None:
    with open(path, "w", encoding="utf-8", newline="") as handle:
        handle.write(content)


def platform_id_for(name: str) -> str:
    """The id a display name is published under: lowercase letters, digits and
    dashes, so it can be a folder name and a URL segment."""
    slug = re.sub(r"[^a-z0-9]+", "-", name.lower()).strip("-")[:40].strip("-")
    if not slug:
        raise PublishError("The platform name needs at least one letter or digit.")
    return slug


def _check_publish_input(name: str, output_filename: str, output_model_name: str) -> str:
    """The id `name` is published under, once everything sent is acceptable."""
    if not name or len(name) > MAX_NAME_CHARS:
        raise PublishError(f"The platform name must be 1 to {MAX_NAME_CHARS} characters.")
    platform_id = platform_id_for(name)
    if (
        len(output_filename) > MAX_OUTPUT_FILENAME_CHARS
        or output_filename in (".", "..")
        or not _FILE_NAME.fullmatch(output_filename)
    ):
        raise PublishError("The output file name must be one file name, such as .ci.yml, not a path.")
    if not re.fullmatch(r"\w+", output_model_name):
        raise PublishError("The output model name must be a single word, as declared in the transformation.")
    return platform_id


class PlatformStore:
    def __init__(self, directory: Path):
        self._directory = directory
        # Publishing and purging change folders the other reads; one at a time.
        # This service runs as one process.
        self._lock = threading.Lock()

    def latest(self) -> list[PlatformVersion]:
        """Every platform's latest usable version, by name."""
        if not self._directory.is_dir():
            logger.error("the platforms folder %s does not exist", self._directory)
            return []
        found = []
        for entry in self._directory.iterdir():
            if ID_PATTERN.fullmatch(entry.name):
                usable = self.versions(entry.name)
                if usable:
                    found.append(usable[-1])
        if not found:
            logger.error("the platforms folder %s holds no usable platform", self._directory)
        return sorted(found, key=lambda platform: platform.name.lower())

    def versions(self, platform_id: str) -> list[PlatformVersion]:
        """Every usable version of a platform, oldest first."""
        folder = self._directory / platform_id
        if not ID_PATTERN.fullmatch(platform_id) or not folder.is_dir():
            return []
        usable = []
        for entry in folder.iterdir():
            if not versions.VERSION_PATTERN.fullmatch(entry.name):
                continue
            platform = self._read_meta(entry)
            # A folder is only what it says it is: its own id and version.
            if platform is not None and platform.id == platform_id and platform.version == entry.name:
                usable.append(platform)
        return sorted(usable, key=lambda platform: versions.parse(platform.version))

    def get(self, platform_id: str, version: str | None = None) -> PlatformVersion | None:
        """A platform's version, or its latest usable one when none is named."""
        usable = self.versions(platform_id)
        if version is None:
            return usable[-1] if usable else None
        return next((platform for platform in usable if platform.version == version), None)

    def files(self, platform_id: str, version: str) -> dict[str, str]:
        """The three files of a usable version: metamodel, transformation, template."""
        if self.get(platform_id, version) is None:
            raise PlatformNotFound(f"no platform '{platform_id}' version {version}")
        folder = self._directory / platform_id / version
        return {
            "metamodel": _read_text(folder / _METAMODEL),
            "transformation": _read_text(folder / _TRANSFORMATION),
            "template": _read_text(folder / _TEMPLATE),
        }

    def pim_transformation(self) -> str:
        """The SWArch-to-PIM transformation, as ATL source text."""
        return _read_text(self._directory / _PIM_TRANSFORMATION)

    def publish(
        self,
        *,
        name: str,
        output_filename: str,
        output_model_name: str,
        metamodel: str,
        transformation: str,
        template: str,
        source_run_id: str | None,
        version: str | None = None,
    ) -> PlatformVersion:
        """Stores a new version of the platform `name`, under `version` if given
        and as the patch after its highest one if not. Returns it."""
        name = name.strip()
        platform_id = _check_publish_input(name, output_filename, output_model_name)
        if version is not None:
            try:
                versions.parse(version)
            except ValueError as e:
                raise PublishError(str(e)) from None
        with self._lock:
            usable = self.versions(platform_id)
            if usable and usable[-1].name != name:
                raise PublishError(
                    f"'{platform_id}' is already the platform '{usable[-1].name}'. "
                    "Publish under another name, or under that name to add a version."
                )
            folder = self._directory / platform_id
            folder.mkdir(exist_ok=True)
            version_folder, claimed = self._claim(folder, version)
            published = PlatformVersion(
                id=platform_id,
                name=name,
                version=claimed,
                output_filename=output_filename,
                output_model_name=output_model_name,
                source_run_id=source_run_id,
                published_at=datetime.now(timezone.utc).isoformat(timespec="seconds"),
            )
            try:
                _write_text(version_folder / _METAMODEL, metamodel)
                _write_text(version_folder / _TRANSFORMATION, transformation)
                _write_text(version_folder / _TEMPLATE, template)
                self._write_meta(version_folder, published)
            except BaseException:
                # The version folder stays claimed but has no platform.json, so it
                # is never listed and its number is never reused. Purging clears it.
                logger.error("publishing %s version %s did not complete", platform_id, claimed)
                raise
            return published

    def purge(self, platform_id: str, version: str | None = None) -> list[str]:
        """Removes one version of a published platform, or all of its versions
        when none is named. Returns the versions removed. Folders a publish left
        unfinished go too. Raises PlatformNotFound for an unknown platform or
        version and NotPurgeable for a platform that came with the stack, in
        which case nothing is removed."""
        if not ID_PATTERN.fullmatch(platform_id):
            raise PlatformNotFound(f"no platform '{platform_id}'")
        folder = self._directory / platform_id
        with self._lock:
            if not folder.is_dir():
                raise PlatformNotFound(f"no platform '{platform_id}'")
            if version is not None:
                try:
                    versions.parse(version)
                except ValueError as e:
                    raise PlatformNotFound(str(e)) from None
                if not (folder / version).is_dir():
                    raise PlatformNotFound(f"no platform '{platform_id}' version {version}")
                doomed = [version]
            else:
                doomed = [entry.name for entry in folder.iterdir() if versions.VERSION_PATTERN.fullmatch(entry.name)]
            for name in doomed:
                kept = self._read_meta(folder / name)
                if kept is not None and not kept.published:
                    raise NotPurgeable(
                        f"'{platform_id}' version {name} came with the stack and was not published, so it is kept."
                    )
            for name in sorted(doomed, key=versions.parse):
                shutil.rmtree(folder / name)
            if not any(folder.iterdir()):
                folder.rmdir()
            return sorted(doomed, key=versions.parse)

    @staticmethod
    def _claim(folder: Path, version: str | None) -> tuple[Path, str]:
        """Creates the version's folder, so no one else gets the number. Raises
        VersionTaken if a version asked for exists."""
        if version is not None:
            try:
                (folder / version).mkdir()
            except FileExistsError:
                raise VersionTaken(f"version {version} already exists, and a version is never changed") from None
            return folder / version, version
        existing = [entry.name for entry in folder.iterdir() if versions.VERSION_PATTERN.fullmatch(entry.name)]
        candidate = versions.next_patch(max(existing, key=versions.parse, default=None))
        while True:
            try:
                (folder / candidate).mkdir()
                return folder / candidate, candidate
            except FileExistsError:
                candidate = versions.next_patch(candidate)

    @staticmethod
    def _read_meta(folder: Path) -> PlatformVersion | None:
        """The version a folder describes, or None (with the reason in the log)
        if it has no usable platform.json or lacks one of its files."""
        try:
            raw = json.loads((folder / _META).read_text(encoding="utf-8"))
            if not isinstance(raw, dict):
                raise ValueError("platform.json is not an object")
            for field in ("id", "name", "output_filename", "output_model_name"):
                if not isinstance(raw.get(field), str) or not raw[field]:
                    raise ValueError(f"{field} must be a non-empty string")
            versions.parse(raw.get("version"))
            for field in ("source_run_id", "published_at"):
                if raw.get(field) is not None and not isinstance(raw[field], str):
                    raise ValueError(f"{field} must be text")
            if raw["output_filename"] in (".", "..") or not _FILE_NAME.fullmatch(raw["output_filename"]):
                raise ValueError("output_filename must be a plain file name, not a path")
            for filename in (_METAMODEL, _TRANSFORMATION, _TEMPLATE):
                if not (folder / filename).is_file():
                    raise ValueError(f"{filename} is missing")
            return PlatformVersion(
                id=raw["id"],
                name=raw["name"],
                version=raw["version"],
                output_filename=raw["output_filename"],
                output_model_name=raw["output_model_name"],
                source_run_id=raw.get("source_run_id"),
                published_at=raw.get("published_at"),
            )
        except (OSError, ValueError) as e:
            logger.warning("ignoring platform version %s: %s", folder, e)
            return None

    @staticmethod
    def _write_meta(folder: Path, published: PlatformVersion) -> None:
        # Written beside its final name and renamed into place, with the
        # ordinary file mode, so it appears whole or not at all.
        temporary = folder / f".writing-{uuid.uuid4().hex}"
        descriptor = os.open(temporary, os.O_WRONLY | os.O_CREAT | os.O_EXCL, 0o666)
        try:
            with os.fdopen(descriptor, "w", encoding="utf-8") as handle:
                json.dump(asdict(published), handle, indent=2)
            os.replace(temporary, folder / _META)
        except BaseException:
            temporary.unlink(missing_ok=True)
            raise
