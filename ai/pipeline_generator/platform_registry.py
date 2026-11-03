"""The platforms this service generates for, and how they are read.

Committed files, one folder per platform and one subfolder per version:

    swarch2pim.atl                the transformation every platform starts with
    <platform-id>/<version>/
        metamodel.ecore           the platform's metamodel
        transformation.atl        PIM to platform model
        template.mtl              platform model to pipeline file
        platform.json             name, output file name, name of the model the
                                  transformation creates
        schema.json               optional: the JSON schema its pipeline must follow

The files are read on every request, so a changed file applies to the next
generation. Generation uses each platform's latest version. A version is only
listed when platform.json is usable and the three files are all there, so a
version that is unfinished or damaged is skipped (and logged) and the one
before it is used.
"""
import json
import logging
import re
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
_SCHEMA = "schema.json"
_META = "platform.json"
# The step every platform starts with, beside the platform folders.
_PIM_TRANSFORMATION = "swarch2pim.atl"


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

    def schema(self, platform: Platform) -> dict | None:
        """The JSON schema the platform's pipeline must follow, or None if it
        has none. A schema that is there but cannot be read raises OSError or
        ValueError."""
        path = self._directory / platform.id / str(platform.version) / _SCHEMA
        if not path.is_file():
            return None
        schema = json.loads(_read_text(path))
        if not isinstance(schema, dict):
            raise ValueError("schema.json is not an object")
        return schema

    def pim_transformation(self) -> str:
        """The SWArch-to-PIM transformation, as ATL source text."""
        return _read_text(self._directory / _PIM_TRANSFORMATION)
