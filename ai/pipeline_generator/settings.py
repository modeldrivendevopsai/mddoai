"""Configuration read from the environment, in one place."""
import os
from pathlib import Path

# A real .swarch model is a handful of attributes. The ceiling is far above
# any real file and exists only to stop an absurdly large upload being parsed
# or fed to a JVM. (The pipeline ceiling, MAX_PIPELINE_BYTES, comes from the
# engine runner: anything the engine can write can also be validated.)
MAX_MODEL_BYTES = int(os.environ.get("GENERATOR_MAX_MODEL_BYTES", str(256 * 1024)))

# The longest path or file name a request may carry. Engineering guess: far
# longer than anything typed into the screen or named a model file.
MAX_PATH_CHARS = 1024
MAX_FILENAME_CHARS = 255

# The folder models are picked from and pipeline files are saved to.
WORKSPACE_DIR = Path(os.environ.get("GENERATOR_WORKSPACE_DIR", "./workspace"))

# Where generated pipelines are kept so they can be reopened later.
DATA_DIR = Path(os.environ.get("GENERATOR_DATA_DIR", "./data"))

# How many generated pipelines are kept; the oldest are forgotten beyond this.
# Engineering guess: far more than anyone reopens, small enough that the list
# and the folder stay small.
MAX_PIPELINES = int(os.environ.get("GENERATOR_MAX_PIPELINES", "200"))
