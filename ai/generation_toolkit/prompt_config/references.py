"""Detects drift in an already-saved prompt config: the same resolution
storage.save_config's own dry-run uses, callable on demand against the
*currently loaded* config, so a caller can notice a metamodel file that
got renamed, or a context key that stopped being produced, since the last
save. load_config itself never fails this way, this is opt-in, called
separately by whatever wants the warning, e.g. a UI on load.
"""
from pathlib import Path

from generation_toolkit.attachments.resolve import resolve_attachments

from . import storage


def check_references(
    config_dir: str | Path,
    name: str,
    preset: str,
    context_values: dict[str, str],
    files_root: str | Path | list[str | Path],
) -> list[dict]:
    """Returns one entry per broken attachment: {"id": str, "error": str}.
    An empty list means every attachment in the currently loaded config
    still resolves cleanly."""
    config = storage.load_config(config_dir, name, preset)
    broken = []
    for attachment in config.get("attachments", []):
        try:
            resolve_attachments([attachment], context_values, files_root)
        except Exception as e:
            broken.append({"id": attachment.get("id"), "error": str(e)})
    return broken
