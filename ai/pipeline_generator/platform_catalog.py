"""Every platform this service offers: the built-in ones, and the latest
version of each platform published from an integration run."""
from platform_registry import PlatformRegistry, PublishedVersion
from platforms import PLATFORMS, PUBLISHED, Intermediate, Platform


def published_platform(version: PublishedVersion) -> Platform:
    """A published version as a platform this service can generate for. Its
    intermediate models are the ones published_runner produces on the way."""
    return Platform(
        id=version.id,
        name=version.name,
        command="",
        output_filename=version.output_filename,
        intermediates=(
            Intermediate("pim", "Platform-independent model", "PipelinePIM.pimmm"),
            Intermediate("platform_model", f"{version.name} model", "PipelinePSM.xmi"),
        ),
        runner=PUBLISHED,
        version=version.version,
    )


def all_platforms(registry: PlatformRegistry) -> list[Platform]:
    return list(PLATFORMS.values()) + [published_platform(version) for version in registry.latest()]


def get_platform(platform_id: str, registry: PlatformRegistry) -> Platform | None:
    if platform_id in PLATFORMS:
        return PLATFORMS[platform_id]
    version = registry.get_latest(platform_id)
    return published_platform(version) if version is not None else None


def reserved_ids() -> set[str]:
    """Ids a published platform may not take: the built-in platforms' own."""
    return set(PLATFORMS)
