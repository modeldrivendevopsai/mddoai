"""The CI/CD platforms the real engine CLI can generate for end to end.

Kept as data, separate from the subprocess code that consumes it: a
platform is supported exactly when `main/`'s CLI has a `swarch2<platform>`
command wired in. Adding a platform also needs a pipeline checker for it.
"""
from dataclasses import dataclass
from pathlib import Path


@dataclass(frozen=True)
class Intermediate:
    """A model file the engine's own handler writes between transformation
    steps, under its fixed relative ./test/generatedModels directory."""

    id: str
    title: str
    filename: str


@dataclass(frozen=True)
class Platform:
    id: str
    name: str
    # The CLI's own <Type> argument for the full SWArch -> platform chain.
    command: str
    # The file the engine's code generator writes into the output folder: one
    # plain file name. The workspace writer saves it under exactly that name, so
    # a name with a folder in it is refused when the platform is defined, not
    # on every save.
    output_filename: str
    intermediates: tuple[Intermediate, ...]

    def __post_init__(self):
        if Path(self.output_filename).name != self.output_filename:
            raise ValueError(f"Platform {self.id!r}: output_filename must be a plain file name, not a path")


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


def get_platform(platform_id: str) -> Platform | None:
    return PLATFORMS.get(platform_id)
