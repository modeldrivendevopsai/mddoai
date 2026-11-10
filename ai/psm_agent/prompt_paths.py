"""Where this service's own real, UI-editable prompt configs (and their
"file" attachments' uploaded copies) live on disk, shared by generation.py
and comparison.py, both real callers of generation_toolkit.prompt_config
against these directories. Its own module rather than a constant defined
in either of those two: generation.py already imports from comparison.py
(META_MODELS_DIR), so comparison.py importing this constant back from
generation.py would be a real import cycle, a shared, tiny, dependency-free
module avoids that without either module duplicating the same path logic.
"""
from pathlib import Path

from generation_toolkit.required_env import required_env

PROMPT_CONFIG_DIR = Path(required_env("PSM_PROMPT_CONFIG_DIR"))

# Separate from PROMPT_CONFIG_DIR (config JSON)
# and from META_MODELS_DIR (real, pre-existing repo .ecore files, mounted
# read-only): a real human's file upload is neither - a writable directory
# of its own, so an upload never needs write access to either of the other
# two. See generation_toolkit.attachments.resolve_file_attachment's own
# multi-root support, which resolves a "file" attachment against this
# directory and META_MODELS_DIR together.
ATTACHMENT_UPLOADS_DIR = Path(required_env("PSM_ATTACHMENT_UPLOADS_DIR"))

# An engineering guess, not a measured limit: real .ecore/docs attachments
# in this service's own shipped defaults are a few KB; 5MB comfortably
# covers a large real metamodel or a documentation dump without letting an
# upload endpoint accept an unbounded amount of data.
MAX_UPLOAD_BYTES = 5 * 1024 * 1024
