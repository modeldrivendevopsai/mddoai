"""The CI/CD platforms this service generates for, and the rules every
platform's definition follows.

The built-in platforms are kept as data, separate from the subprocess code
that consumes them: one is supported exactly when `main/`'s CLI has a
`swarch2<platform>` command wired in, and adding one also needs a pipeline
checker for it. Platforms published from integration runs are added beside
them by platform_catalog.
"""
from dataclasses import dataclass
from pathlib import PurePosixPath

from workspace import is_excluded_name

# How a platform's pipeline is generated.
ENGINE = "engine"        # the engine's own swarch2<platform> command
PUBLISHED = "published"  # a platform published from an integration run


def check_output_filename(output_filename: str) -> str:
    """`output_filename` as a relative path that stays inside the folder it is
    saved into, or ValueError. A path is allowed, not just a file name,
    because some CI systems read their file from a subfolder (a name like
    ".woodpecker/pipeline.yaml"). No part of it may be a name the workspace
    never writes to, such as git's own folder, in any spelling."""
    parts = output_filename.split("/")
    if (
        not output_filename
        or "\\" in output_filename
        or PurePosixPath(output_filename).is_absolute()
        or any(part in ("", ".", "..") or is_excluded_name(part) for part in parts)
    ):
        raise ValueError(f"not a relative path inside the folder: {output_filename!r}")
    return output_filename


@dataclass(frozen=True)
class Intermediate:
    """A model file produced between transformation steps. For a built-in
    platform, the engine's own handler writes it under its fixed relative
    ./test/generatedModels directory."""

    id: str
    title: str
    filename: str


@dataclass(frozen=True)
class Platform:
    id: str
    name: str
    # For a built-in platform, the CLI's own <Type> argument for the full
    # SWArch -> platform chain; empty for a published one.
    command: str
    # The file a generation produces, saved under exactly this relative path:
    # checked when the platform is defined, not on every save.
    output_filename: str
    intermediates: tuple[Intermediate, ...]
    runner: str = ENGINE
    # A published platform's version; None for a built-in one.
    version: int | None = None

    def __post_init__(self):
        try:
            check_output_filename(self.output_filename)
        except ValueError as e:
            raise ValueError(f"Platform {self.id!r}: output_filename must be a relative path inside the folder") from e


PLATFORMS: dict[str, Platform] = {
    "gitlab": Platform(
        id="gitlab",
        name="GitLab CI",
        command="swarch2gitlab",
        output_filename=".gitlab-ci.yml",
        intermediates=(
            Intermediate("pim", "Platform-independent model", "PipelinePIM.pimmm"),
            Intermediate("platform_model", "GitLab model", "PipelineGit.gitlabmm"),
        ),
    ),
}
