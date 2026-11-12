"""What one platform version is on disk, and what a publish must satisfy.

A version is a folder holding three files and a platform.json. platform.json is
written last and in one piece, so a folder without a usable one is a publish that
did not finish, and is never listed. A published version's platform.json also
says where and when it was published. A platform that came with the stack has no
such record, which is how the two are told apart.
"""
import json
import logging
import os
import re
import uuid
from dataclasses import asdict, dataclass
from pathlib import Path

from . import versions

logger = logging.getLogger(__name__)

# A platform id is a folder name and a URL segment: lowercase letters, digits
# and dashes, up to 40 characters (an engineering guess, far longer than any
# real platform's name).
ID_PATTERN = re.compile(r"[a-z0-9][a-z0-9-]{0,39}")
_MAX_ID_CHARS = 40
# Ids the service's own routes use, so a platform cannot be published under one.
RESERVED_IDS = {"pim-transformation"}

METAMODEL = "metamodel.ecore"
TRANSFORMATION = "transformation.atl"
TEMPLATE = "template.mtl"
META = "platform.json"

# The longest display name and output file name accepted. Engineering guess:
# far longer than any real platform name or CI file name.
MAX_NAME_CHARS = 100
MAX_OUTPUT_FILENAME_CHARS = 255

# One file name: no folder separator, no drive colon, no control character.
FILE_NAME = re.compile(r"[^\x00-\x1f/\\:]+")


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


def read_text(path: Path) -> str:
    with open(path, encoding="utf-8", newline="") as handle:
        return handle.read()


def write_text(path: Path, content: str) -> None:
    with open(path, "w", encoding="utf-8", newline="") as handle:
        handle.write(content)


def platform_id_for(name: str) -> str:
    """The id a display name is published under: lowercase letters, digits and
    dashes, so it can be a folder name and a URL segment."""
    slug = re.sub(r"[^a-z0-9]+", "-", name.lower()).strip("-")[:_MAX_ID_CHARS].strip("-")
    if not slug:
        raise PublishError("The platform name needs at least one letter or digit.")
    return slug


def check_publish_input(name: str, output_filename: str, output_model_name: str) -> str:
    """The id `name` is published under, once everything sent is acceptable."""
    if not name or len(name) > MAX_NAME_CHARS:
        raise PublishError(f"The platform name must be 1 to {MAX_NAME_CHARS} characters.")
    platform_id = platform_id_for(name)
    if platform_id in RESERVED_IDS:
        raise PublishError(f"'{platform_id}' is used by the service itself. Publish under another name.")
    if (
        len(output_filename) > MAX_OUTPUT_FILENAME_CHARS
        or output_filename in (".", "..")
        or not FILE_NAME.fullmatch(output_filename)
    ):
        raise PublishError("The output file name must be one file name, such as .ci.yml, not a path.")
    if not re.fullmatch(r"\w+", output_model_name):
        raise PublishError("The output model name must be a single word, as declared in the transformation.")
    return platform_id


def read_meta(folder: Path) -> PlatformVersion | None:
    """The version a folder describes, or None (with the reason in the log) if it
    has no usable platform.json or lacks one of its files."""
    try:
        raw = json.loads((folder / META).read_text(encoding="utf-8"))
        if not isinstance(raw, dict):
            raise ValueError("platform.json is not an object")
        for field in ("id", "name", "output_filename", "output_model_name"):
            if not isinstance(raw.get(field), str) or not raw[field]:
                raise ValueError(f"{field} must be a non-empty string")
        versions.parse(raw.get("version"))
        for field in ("source_run_id", "published_at"):
            if raw.get(field) is not None and not isinstance(raw[field], str):
                raise ValueError(f"{field} must be text")
        if raw["output_filename"] in (".", "..") or not FILE_NAME.fullmatch(raw["output_filename"]):
            raise ValueError("output_filename must be a plain file name, not a path")
        for filename in (METAMODEL, TRANSFORMATION, TEMPLATE):
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


def may_be_purged(folder: Path) -> bool:
    """Whether a version folder may be removed: it was published, which its
    platform.json says, or it never finished, so it has no platform.json. One
    whose platform.json cannot be read is kept, since there is no telling what
    it is."""
    meta = folder / META
    if not meta.exists():
        return True
    try:
        raw = json.loads(meta.read_text(encoding="utf-8"))
    except (OSError, ValueError):
        return False
    return isinstance(raw, dict) and isinstance(raw.get("published_at"), str)


def write_meta(folder: Path, published: PlatformVersion) -> None:
    """Writes platform.json beside its final name and renames it into place, with
    the ordinary file mode, so it appears whole or not at all."""
    temporary = folder / f".writing-{uuid.uuid4().hex}"
    descriptor = os.open(temporary, os.O_WRONLY | os.O_CREAT | os.O_EXCL, 0o666)
    try:
        with os.fdopen(descriptor, "w", encoding="utf-8") as handle:
            json.dump(asdict(published), handle, indent=2)
        os.replace(temporary, folder / META)
    except BaseException:
        temporary.unlink(missing_ok=True)
        raise
