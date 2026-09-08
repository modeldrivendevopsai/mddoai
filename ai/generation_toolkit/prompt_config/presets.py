"""Which named preset (an id, or "default") a config lookup should use.

A preset's id (its filename) is just a stable storage key, not itself the
thing that decides whether a platform description matches it: a preset's
own file carries a "platform_hints" list (e.g. ["gitlab", "gitlab-ci"]) as
real, UI-editable data, and matching goes against that metadata, not
against the id string. This keeps a preset genuinely renameable and
independent of any single platform's exact spelling, matching is data a
human can add to or correct through the same prompt-config file the rest
of a preset already lives in, not logic baked into a filename.

list_presets/preset_metadata do the actual directory/file reads, sharing
storage.py's own file layout via _paths; resolve_preset itself is pure, no
I/O, testable against a plain list of metadata dicts.
"""
from pathlib import Path

from . import _paths, storage


def list_presets(config_dir: str | Path, name: str) -> list[str]:
    """Every preset id with a real file on disk for this name (its live
    config, or just its own shipped default), "default" always included
    even if nothing has been saved under that exact id yet, since
    default.default.json always backs it, see storage.load_config."""
    presets = {"default"}
    directory = _paths.mode_dir(config_dir, name)
    if directory.is_dir():
        for entry in directory.iterdir():
            if not entry.is_file():
                continue
            if entry.name.endswith(".default.json"):
                presets.add(entry.name.removesuffix(".default.json"))
            elif entry.name.endswith(".json"):
                presets.add(entry.name.removesuffix(".json"))
    return sorted(presets)


def preset_metadata(config_dir: str | Path, name: str, preset: str) -> dict:
    """{"id": preset, "label": str, "platform_hints": list[str]}, read from
    that preset's own live-or-default file (storage.load_config's own
    fallback chain). "label" defaults to the id itself, "platform_hints"
    defaults to an empty list, so an older config saved before either
    field existed still loads cleanly."""
    config = storage.load_config(config_dir, name, preset)
    # `or` rather than dict.get(key, default): a config saved through the
    # real HTTP API (routes/prompt_config.py's PromptConfigBody) always
    # includes both keys explicitly, "label" as an explicit None when
    # unset, not simply absent, so a plain .get(..., default) would never
    # actually fall back.
    return {
        "id": preset,
        "label": config.get("label") or preset,
        "platform_hints": config.get("platform_hints") or [],
    }


def list_preset_metadata(config_dir: str | Path, name: str) -> list[dict]:
    """preset_metadata() for every real preset, the shape a UI's preset
    picker renders directly (label, not a raw id) and resolve_preset()
    below matches against."""
    return [preset_metadata(config_dir, name, preset) for preset in list_presets(config_dir, name)]


def resolve_preset(platform_description: str, presets: list[dict]) -> str:
    """Which preset's metadata best matches this free-text platform
    description: substring match, case-insensitive and whitespace-stripped
    (a real platform name like "Azure DevOps" routinely has a space a hint
    string never needs to), against each preset's own "platform_hints"
    plus its id (an id doubles as an implicit hint, so a preset just named
    "gitlab" still matches out of the box with zero configured hints), not
    a fixed one-string-per-preset rule. Falls back to "default"."""
    description_normalized = "".join(platform_description.lower().split())
    for preset in presets:
        if preset["id"] == "default":
            continue
        candidates = {*preset.get("platform_hints", []), preset["id"]}
        if any(candidate.lower() in description_normalized for candidate in candidates):
            return preset["id"]
    return "default"
