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
import logging
import shutil
import threading
from datetime import datetime, timezone
from pathlib import Path

from . import versions
from .platform_meta import (
    ID_PATTERN,
    METAMODEL,
    NotPurgeable,
    PlatformNotFound,
    PlatformVersion,
    PublishError,
    TEMPLATE,
    TRANSFORMATION,
    VersionTaken,
    check_publish_input,
    may_be_purged,
    read_meta,
    read_text,
    write_meta,
    write_text,
)

logger = logging.getLogger(__name__)

# The step every platform starts with, beside the platform folders.
_PIM_TRANSFORMATION = "swarch2pim.atl"


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
            platform = read_meta(entry)
            # A folder is only what it says it is: its own id and version.
            if platform is not None and platform.id == platform_id and platform.version == entry.name:
                usable.append(platform)
        return sorted(usable, key=lambda platform: versions.parse(platform.version))

    def published_from(self, run_id: str) -> list[PlatformVersion]:
        """Every platform version that was published from this run."""
        return [
            version
            for platform in self.latest()
            for version in self.versions(platform.id)
            if version.source_run_id == run_id
        ]

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
            "metamodel": read_text(folder / METAMODEL),
            "transformation": read_text(folder / TRANSFORMATION),
            "template": read_text(folder / TEMPLATE),
        }

    def pim_transformation(self) -> str:
        """The SWArch-to-PIM transformation, as ATL source text."""
        return read_text(self._directory / _PIM_TRANSFORMATION)

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
        platform_id = check_publish_input(name, output_filename, output_model_name)
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
                write_text(version_folder / METAMODEL, metamodel)
                write_text(version_folder / TRANSFORMATION, transformation)
                write_text(version_folder / TEMPLATE, template)
                write_meta(version_folder, published)
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
                if not may_be_purged(folder / name):
                    raise NotPurgeable(
                        f"'{platform_id}' version {name} has no record of being published, so it is kept. "
                        "A version that came with the stack is never removed."
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
