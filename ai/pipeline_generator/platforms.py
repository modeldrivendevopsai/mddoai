"""What a CI/CD platform is, to this service: one stored version of it. Where
platforms are kept and how they are run is the registry's and the runner's
business, not this module's."""
from dataclasses import dataclass
from pathlib import Path


def plain_name(name: object) -> str:
    """`name` as one path segment, or ValueError. Platform ids and file names
    are joined onto folders, so a name that is empty, or would step outside
    its folder, is refused wherever one is read from disk."""
    if not isinstance(name, str) or name in ("", ".", "..") or Path(name).name != name:
        raise ValueError(f"not a plain file name: {name!r}")
    return name


@dataclass(frozen=True)
class Platform:
    id: str
    name: str
    version: int
    # The file the platform's template generates: one plain file name. The
    # pipeline history joins it onto a folder, so a name with a folder in it is
    # refused when the platform is read, not on every generation.
    output_filename: str
    # The name the transformation gives the model it creates, as declared in
    # it: "create OUT : <name> from IN : PIM;".
    output_model_name: str

    def __post_init__(self):
        try:
            plain_name(self.output_filename)
        except ValueError:
            raise ValueError(f"Platform {self.id!r}: output_filename must be a plain file name, not a path") from None
