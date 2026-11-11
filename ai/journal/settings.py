"""The journal's settings. The five folders are required, with no default: each
is its own host folder, set in config.env."""
from pathlib import Path

from generation_toolkit.required_env import required_env

RUNS_DIR = Path(required_env("JOURNAL_RUNS_DIR"))
CHATS_DIR = Path(required_env("JOURNAL_CHATS_DIR"))
PLATFORMS_DIR = Path(required_env("JOURNAL_PLATFORMS_DIR"))
PROMPTS_DIR = Path(required_env("JOURNAL_PROMPTS_DIR"))
ATTACHMENTS_DIR = Path(required_env("JOURNAL_ATTACHMENTS_DIR"))

# The largest metamodel, transformation or template a platform is published
# with, each. Engineering guess: far above any real one, and below what
# execution-agent itself accepts per field.
MAX_ARTIFACT_BYTES = 1024 * 1024

# The largest file a person uploads to attach to a prompt. Engineering guess: a large
# metamodel or a documentation dump is a few hundred KB. It must be at least what an
# agent accepts, and a test pins that.
MAX_UPLOAD_BYTES = 5 * 1024 * 1024

# How many saved versions of one prompt config are kept, the oldest pruned first.
# Engineering guess: saves of unchanged content are not versions, so what is left is
# real edits, and a config edited every day for months stays well under it.
MAX_PROMPT_VERSIONS = 200
