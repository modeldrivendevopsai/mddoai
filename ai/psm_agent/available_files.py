"""Real, already-existing files a "file" attachment can reference, found by
scanning real directories directly rather than a hardcoded list, so a
newly added metamodel or upload is picked up with no code change here.

Two real, separately-scanned roots, both valid: META_MODELS_DIR (real,
pre-existing repo .ecore files) and ATTACHMENT_UPLOADS_DIR (a human's own
uploaded files, see routes/uploads.py) - the same two roots
generation_toolkit.attachments.files.resolve_file_attachment's own
multi-root support tries in order when actually resolving a "file"
attachment's path, so a path this function lists is always one that root
can genuinely resolve.

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
import prompt_paths


def list_available_files() -> list[str]:
    """Every real .ecore file under META_MODELS_DIR, plus every real
    uploaded file under ATTACHMENT_UPLOADS_DIR, as forward-slash paths
    relative to their own root. Sorted for a deterministic, stable UI
    listing, not whatever order the filesystem happens to return. The two
    roots never produce colliding path strings in practice (an upload's
    stored name always carries a random prefix, see
    generation_toolkit.attachments.uploads.save_uploaded_file, while a real
    metamodel path always carries its own platform/model/ directory
    structure), so both lists are simply combined."""
    files: list[str] = []

    metamodels_root = Path(META_MODELS_DIR)
    if metamodels_root.is_dir():
        files.extend(str(path.relative_to(metamodels_root)).replace("\\", "/") for path in metamodels_root.glob("*/model/*.ecore"))

    uploads_root = Path(prompt_paths.ATTACHMENT_UPLOADS_DIR)
    if uploads_root.is_dir():
        files.extend(path.name for path in uploads_root.iterdir() if path.is_file())

    return sorted(files)
