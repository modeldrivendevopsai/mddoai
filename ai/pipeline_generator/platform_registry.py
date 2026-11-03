"""Where the platforms this service generates for are kept, and how they are
read.

One folder per platform, one subfolder per version:

    <platform-id>/<version>/
        metamodel.ecore      the platform's metamodel
        transformation.atl   PIM to platform model
        template.mtl         platform model to pipeline file
        platform.json        name, output file name, name of the model the transformation creates

Generation uses each platform's latest version. A version is only listed when
platform.json is usable and the three files are all there, so a version that
is unfinished or damaged is skipped (and logged) and the one before it is used.

The platforms this service ships with are copied in at startup (`seed`), so a
fresh data folder works at once; after that these files are the platform, and
the service reads nothing else to run it.
"""
import json
import logging
import os
import re
import shutil
from dataclasses import dataclass
from pathlib import Path

from platforms import Platform

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


@dataclass(frozen=True)
class Artifacts:
    metamodel: str
    transformation: str
    template: str


def _read_text(path: Path) -> str:
    with open(path, encoding="utf-8", newline="") as handle:
        return handle.read()


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
            return []
        found = []
        for entry in self._directory.iterdir():
            if _ID_PATTERN.fullmatch(entry.name):
                versions = self._versions(entry.name)
                if versions:
                    found.append(versions[-1])
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

    def seed(self, seed_directory: Path) -> None:
        """Copies each platform version in `seed_directory` that is not stored
        yet. A version that is stored is never overwritten, so a change made to
        a stored platform survives a restart. A version is copied beside its
        final name and renamed into place, so it appears whole or not at all."""
        for platform_folder in sorted(p for p in seed_directory.iterdir() if p.is_dir()):
            for version_folder in sorted(v for v in platform_folder.iterdir() if v.is_dir()):
                target = self._directory / platform_folder.name / version_folder.name
                if target.exists():
                    continue
                target.parent.mkdir(parents=True, exist_ok=True)
                # A fixed staging name, so one left by a crash is replaced by
                # the next start instead of piling up.
                staging = target.parent / f".seeding-{version_folder.name}"
                shutil.rmtree(staging, ignore_errors=True)
                shutil.copytree(version_folder, staging)
                os.rename(staging, target)
                logger.info("stored platform %s version %s", platform_folder.name, version_folder.name)
