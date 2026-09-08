"""Where this service's own real, UI-editable prompt configs live on disk,
shared by generation.py and comparison.py, both real callers of
generation_toolkit.prompt_config against this one directory. Its own
module rather than a constant defined in either of those two: generation.py
already imports from comparison.py (META_MODELS_DIR), so comparison.py
importing this constant back from generation.py would be a real import
cycle, a shared, tiny, dependency-free module avoids that without either
module duplicating the same path logic.

Sibling to this service's own source, matching integration_runner's
RUNS_DIR / this same service's own META_MODELS_DIR pattern for "a real,
no-external-mount-required default, overridable for Docker" - see
ai/docker-compose.yml for the real bind mount that makes a save through
the running service land on the host's actual git checkout.
"""
import os
from pathlib import Path

PROMPT_CONFIG_DIR = Path(os.environ.get("PSM_PROMPT_CONFIG_DIR", str(Path(__file__).resolve().parent / "prompts")))
