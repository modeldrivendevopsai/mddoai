"""The journal's settings. The three folders are required, with no default: each
is its own host folder, set in config.env."""
import os
from pathlib import Path

from generation_toolkit.required_env import required_env

RUNS_DIR = Path(required_env("JOURNAL_RUNS_DIR"))
CHATS_DIR = Path(required_env("JOURNAL_CHATS_DIR"))
PLATFORMS_DIR = Path(required_env("JOURNAL_PLATFORMS_DIR"))

# The largest metamodel, transformation or template a platform is published
# with, each. Engineering guess: far above any real one, and below what
# execution-agent itself accepts per field.
MAX_ARTIFACT_BYTES = int(os.environ.get("JOURNAL_MAX_ARTIFACT_BYTES", str(1024 * 1024)))
