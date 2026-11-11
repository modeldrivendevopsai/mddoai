"""The journal's settings. The six folders are required, with no default: each
is its own host folder, set in config.env."""
from pathlib import Path

from generation_toolkit.required_env import required_env

RUNS_DIR = Path(required_env("JOURNAL_RUNS_DIR"))
CHATS_DIR = Path(required_env("JOURNAL_CHATS_DIR"))
PLATFORMS_DIR = Path(required_env("JOURNAL_PLATFORMS_DIR"))
PROMPTS_DIR = Path(required_env("JOURNAL_PROMPTS_DIR"))
ATTACHMENTS_DIR = Path(required_env("JOURNAL_ATTACHMENTS_DIR"))
PIPELINES_DIR = Path(required_env("JOURNAL_PIPELINES_DIR"))

# The largest metamodel, transformation or template a platform is published
# with, each. Engineering guess: far above any real one, and below what
# execution-agent itself accepts per field.
MAX_ARTIFACT_BYTES = 1024 * 1024

# The largest file a person uploads to attach to a prompt. Engineering guess: a large
# metamodel or a documentation dump is a few hundred KB. It must be at least what an
# agent accepts, and a test pins that.
MAX_UPLOAD_BYTES = 5 * 1024 * 1024

# The largest compiled output of one attempt, as the zip it is kept as. Engineering guess: the
# generated sources and classes of a metamodel are well under a few MB zipped.
MAX_COMPILED_BYTES = 20 * 1024 * 1024

# How many saved versions of one prompt config are kept, the oldest pruned first.
# Engineering guess: saves of unchanged content are not versions, so what is left is
# real edits, and a config edited every day for months stays well under it.
MAX_PROMPT_VERSIONS = 200

# How many generated pipelines are kept, the oldest forgotten beyond this. Engineering
# guess: far more than anyone reopens, small enough that the list and the folder stay small.
MAX_PIPELINES = 200
