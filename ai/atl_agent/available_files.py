"""Real, already-existing files a "file" attachment can reference. See
generation_toolkit.attachments.files.list_reference_and_uploads for what
counts (the one real master-example ATL file plus every real uploaded
file) and why - the shape shared with acceleo_agent's own copy, since both
services have exactly one real reference file, unlike psm_agent's own
broader multi-metamodel listing (its own available_files.py stays
separate).
"""
from generation_toolkit.attachments.files import list_reference_and_uploads

import prompt_paths


def list_available_files() -> list[str]:
    return list_reference_and_uploads(prompt_paths.REFERENCE_EXAMPLE_PATH, prompt_paths.ATTACHMENT_UPLOADS_DIR)
