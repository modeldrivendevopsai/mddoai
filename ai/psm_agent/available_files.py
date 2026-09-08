"""Real, already-existing files a "file" attachment can reference, found by
scanning META_MODELS_DIR directly rather than a hardcoded list, so a newly
added metamodel is picked up with no code change here.

Deliberately broader than comparison.py's own known_psm_platforms(), which
only lists platforms with a real <platform>MM.ecore file (excluding pim and
swarch), since that function answers a different question, whether an
existing PSM exists to check for maintenance drift against. A human
building a prompt might legitimately want to attach the PIM metamodel
itself as extra context, not only a PSM master example (the real MDDOAI
research prompts have done exactly this on a later refinement round), so
every real .ecore file is a candidate here, not only ones matching the PSM
naming convention.
"""
from pathlib import Path

from comparison import META_MODELS_DIR


def list_available_files() -> list[str]:
    """Every real .ecore file under META_MODELS_DIR, as forward-slash paths
    relative to it, the same root
    generation_toolkit.attachments.files.resolve_file_attachment validates
    a "file" attachment's path against. Sorted for a deterministic, stable
    UI listing, not whatever order the filesystem happens to return."""
    base = Path(META_MODELS_DIR)
    if not base.is_dir():
        return []
    return sorted(str(path.relative_to(base)).replace("\\", "/") for path in base.glob("*/model/*.ecore"))
