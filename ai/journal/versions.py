"""Platform versions: MAJOR.MINOR.PATCH, as in semantic versioning (semver.org).
Each part is a whole number without leading zeros, and versions are ordered by
comparing the parts as numbers, so 1.10.0 is after 1.9.0. Pre-release and build
suffixes are not used."""
import re

# Up to six digits a part: far more than a platform will ever reach (engineering
# guess), and short enough for a folder name.
_PART = r"(0|[1-9][0-9]{0,5})"
VERSION_PATTERN = re.compile(rf"{_PART}\.{_PART}\.{_PART}")
FIRST_VERSION = "1.0.0"


def parse(version: str) -> tuple[int, int, int]:
    """The version's three parts as numbers. Raises ValueError for anything that
    is not MAJOR.MINOR.PATCH."""
    if not isinstance(version, str) or not VERSION_PATTERN.fullmatch(version):
        raise ValueError(f"not a version like 1.2.0: {version!r}")
    major, minor, patch = version.split(".")
    return int(major), int(minor), int(patch)


def next_patch(version: str | None) -> str:
    """The version after `version`: its patch plus one. The first version when there is none."""
    if version is None:
        return FIRST_VERSION
    major, minor, patch = parse(version)
    return f"{major}.{minor}.{patch + 1}"
