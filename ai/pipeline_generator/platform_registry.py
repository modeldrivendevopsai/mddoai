"""The platforms this service generates for, and how they are read.

Committed files, one folder per platform and one subfolder per version:

    swarch2pim.atl                the transformation every platform starts with
    <platform-id>/<version>/
        metamodel.ecore           the platform's metamodel
        transformation.atl        PIM to platform model
        template.mtl              platform model to pipeline file
        platform.json             name, output file name, name of the model the
                                  transformation creates

The files are read on every request, so a changed file applies to the next
generation. Generation uses each platform's latest version. A version is only
listed when platform.json is usable and the three files are all there, so a
version that is unfinished or damaged is skipped (and logged) and the one
before it is used.

Publishing a platform adds its next version and never changes an earlier one.
The version folder is claimed with an atomic mkdir and its platform.json is
written last, so a version whose files are not all written yet is never listed.
"""
import json
import logging
import os
import re
import uuid
from dataclasses import dataclass
from datetime import datetime, timezone
from pathlib import Path

from platforms import Platform, plain_name

logger = logging.getLogger(__name__)

# A platform id is a folder name and a URL segment: lowercase letters, digits
# and dashes, up to 40 characters (an engineering guess, far longer than any
# real platform's name). A version is a whole number from 1, up to 6 digits.
_ID_PATTERN = re.compile(r"[a-z0-9][a-z0-9-]{0,39}")
_VERSION_PATTERN = re.compile(r"[1-9][0-9]{0,5}")

# The files a version holds, by what they are.
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
# A name Linux accepts but a path or a null byte would break later.
_FILE_NAME_PATTERN = re.compile(r"[^\x00-\x1f/\\:]+")


class PublishError(ValueError):
    """What was sent cannot be published. The message says why, in words a
    person can act on."""


@dataclass(frozen=True)
class Artifacts:
    metamodel: str
    transformation: str
    template: str


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
    try:
        if len(output_filename) > MAX_OUTPUT_FILENAME_CHARS:
            raise ValueError("too long")
        plain_name(output_filename)
        if not _FILE_NAME_PATTERN.fullmatch(output_filename):
            raise ValueError("not one file name")
    except ValueError:
        raise PublishError("The output file name must be one file name, such as .ci.yml, not a path.") from None
    if not re.fullmatch(r"\w+", output_model_name):
        raise PublishError("The output model name must be a single word, as declared in the transformation.")
    return platform_id


class PlatformRegistry:
    def __init__(self, directory: Path):
        self._directory = directory

    @staticmethod
    def _read_meta(folder: Path) -> Platform | None:
        """The platform a version folder describes, or None (with the reason in
        the log) if it has no usable platform.json or lacks one of its files."""
        try:
            raw = json.loads((folder / _META).read_text(encoding="utf-8"))
            if not isinstance(raw, dict):
                raise ValueError("platform.json is not an object")
            for field in ("id", "name", "output_filename", "output_model_name"):
                if not isinstance(raw.get(field), str) or not raw[field]:
                    raise ValueError(f"{field} must be a non-empty string")
            if type(raw.get("version")) is not int:
                raise ValueError("version must be a whole number")
            for filename in (_METAMODEL, _TRANSFORMATION, _TEMPLATE):
                if not (folder / filename).is_file():
                    raise ValueError(f"{filename} is missing")
            return Platform(
                id=raw["id"],
                name=raw["name"],
                version=raw["version"],
                output_filename=raw["output_filename"],
                output_model_name=raw["output_model_name"],
            )
        except (OSError, ValueError) as e:
            logger.warning("ignoring platform version %s: %s", folder, e)
            return None

    def _versions(self, platform_id: str) -> list[Platform]:
        folder = self._directory / platform_id
        if not folder.is_dir():
            return []
        versions = []
        for entry in folder.iterdir():
            if not _VERSION_PATTERN.fullmatch(entry.name):
                continue
            platform = self._read_meta(entry)
            # A folder is only what it says it is: its own id and version.
            if platform is not None and platform.id == platform_id and platform.version == int(entry.name):
                versions.append(platform)
        return sorted(versions, key=lambda platform: platform.version)

    def latest(self) -> list[Platform]:
        """Every platform's latest usable version, by name."""
        if not self._directory.is_dir():
            logger.error("the platforms folder %s does not exist", self._directory)
            return []
        found = []
        for entry in self._directory.iterdir():
            if _ID_PATTERN.fullmatch(entry.name):
                versions = self._versions(entry.name)
                if versions:
                    found.append(versions[-1])
        if not found:
            logger.error("the platforms folder %s holds no usable platform", self._directory)
        return sorted(found, key=lambda platform: platform.name.lower())

    def get(self, platform_id: str) -> Platform | None:
        """A platform's latest usable version."""
        if not _ID_PATTERN.fullmatch(platform_id):
            return None
        versions = self._versions(platform_id)
        return versions[-1] if versions else None

    def artifacts(self, platform: Platform) -> Artifacts:
        folder = self._directory / platform.id / str(platform.version)
        return Artifacts(
            metamodel=_read_text(folder / _METAMODEL),
            transformation=_read_text(folder / _TRANSFORMATION),
            template=_read_text(folder / _TEMPLATE),
        )

    def pim_transformation(self) -> str:
        """The SWArch-to-PIM transformation, as ATL source text."""
        return _read_text(self._directory / _PIM_TRANSFORMATION)

    def publish(
        self,
        *,
        name: str,
        output_filename: str,
        output_model_name: str,
        artifacts: Artifacts,
        source_run_id: str | None,
    ) -> Platform:
        """Stores a new version of the platform `name` and returns it."""
        name = name.strip()
        platform_id = _check_publish_input(name, output_filename, output_model_name)
        existing = self._versions(platform_id)
        if existing and existing[-1].name != name:
            raise PublishError(
                f"'{platform_id}' is already the platform '{existing[-1].name}'. "
                "Publish under another name, or under that name to add a version."
            )
        folder = self._directory / platform_id
        folder.mkdir(exist_ok=True)
        version_folder, version = self._claim_next_version(folder)
        published = Platform(
            id=platform_id,
            name=name,
            version=version,
            output_filename=output_filename,
            output_model_name=output_model_name,
        )
        try:
            _write_text(version_folder / _METAMODEL, artifacts.metamodel)
            _write_text(version_folder / _TRANSFORMATION, artifacts.transformation)
            _write_text(version_folder / _TEMPLATE, artifacts.template)
            self._write_meta(version_folder, published, source_run_id)
        except BaseException:
            # The version folder stays claimed but has no platform.json, so it
            # is never listed and its number is never reused.
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
    def _write_meta(folder: Path, published: Platform, source_run_id: str | None) -> None:
        # Written beside its final name and renamed into place, with the
        # ordinary file mode, so it appears whole or not at all.
        meta = {
            **published.__dict__,
            "source_run_id": source_run_id,
            "published_at": datetime.now(timezone.utc).isoformat(timespec="seconds"),
        }
        temporary = folder / f".writing-{uuid.uuid4().hex}"
        descriptor = os.open(temporary, os.O_WRONLY | os.O_CREAT | os.O_EXCL, 0o666)
        try:
            with os.fdopen(descriptor, "w", encoding="utf-8") as handle:
                json.dump(meta, handle, indent=2)
            os.replace(temporary, folder / _META)
        except BaseException:
            temporary.unlink(missing_ok=True)
            raise
