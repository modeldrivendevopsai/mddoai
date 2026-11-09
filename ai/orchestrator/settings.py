"""Configuration read from the environment."""
import os
from pathlib import Path


def _required(name: str) -> str:
    """A required setting. Unset or empty stops the service."""
    value = os.environ[name].strip()
    if not value:
        raise KeyError(f"{name} is set but empty")
    return value


# Where each run's chat is kept.
CHATS_DIR = Path(_required("ORCHESTRATOR_CHATS_DIR"))
