"""Real, already-existing files a "file" attachment can reference, found by
scanning real directories directly rather than a hardcoded list, so a newly
uploaded file is picked up with no code change here. Two real roots: the
one real master-example Acceleo template (REFERENCE_EXAMPLE_PATH, see
prompt_paths.py) and ATTACHMENT_UPLOADS_DIR (a human's own uploaded files,
see routes/uploads.py) - the same two roots
generation_toolkit.attachments.files.resolve_file_attachment's own
multi-root support tries in order when actually resolving a "file"
attachment's path, so a path this function lists is always one that root
can genuinely resolve.
"""
from pathlib import Path

import prompt_paths


def list_available_files() -> list[str]:
    """The one real reference file's own name, plus every real uploaded
    file under ATTACHMENT_UPLOADS_DIR. Sorted for a deterministic, stable
    UI listing, not whatever order the filesystem happens to return."""
    files: list[str] = []

    reference = Path(prompt_paths.REFERENCE_EXAMPLE_PATH)
    if reference.is_file():
        files.append(reference.name)

    uploads_root = Path(prompt_paths.ATTACHMENT_UPLOADS_DIR)
    if uploads_root.is_dir():
        files.extend(path.name for path in uploads_root.iterdir() if path.is_file())

    return sorted(files)
