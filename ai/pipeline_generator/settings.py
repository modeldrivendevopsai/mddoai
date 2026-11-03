"""Configuration read from the environment, in one place."""
import os
from pathlib import Path

# A real .swarch model is a handful of attributes. The ceiling is far above
# any real file and exists only to stop an absurdly large upload being parsed
# or fed to a JVM.
MAX_MODEL_BYTES = int(os.environ.get("GENERATOR_MAX_MODEL_BYTES", str(256 * 1024)))

# The largest pipeline a template may produce, and the largest the validate
# endpoint accepts: one setting, so anything that can be generated can also be
# checked. The models a generation passes through are held to the same limit.
# A real pipeline or model is a few KB; this only guards against keeping
# something unexpectedly huge in memory and on disk.
MAX_PIPELINE_BYTES = int(os.environ.get("GENERATOR_MAX_PIPELINE_BYTES", str(512 * 1024)))

# The longest path or file name a request may carry. Engineering guess: far
# longer than anything typed into the screen or named a model file.
MAX_PATH_CHARS = 1024
MAX_FILENAME_CHARS = 255

# The folder models are picked from.
WORKSPACE_DIR = Path(os.environ.get("GENERATOR_WORKSPACE_DIR", "./workspace"))

# What that folder is called where the person can see it, shown in the Browse
# dialog. Inside a container WORKSPACE_DIR is only a mount point, so compose
# passes the host folder's name; run directly, the folder's own path is it.
WORKSPACE_LABEL = os.environ.get("GENERATOR_WORKSPACE_LABEL") or str(WORKSPACE_DIR)

# Where generated pipelines are kept so they can be reopened later.
DATA_DIR = Path(os.environ.get("GENERATOR_DATA_DIR", "./data"))

# The platforms this service generates for, and the SWArch-to-PIM
# transformation every one of them starts with: committed files, read from
# here on every request. Unset, the committed folder next to this code.
PLATFORMS_DIR = Path(os.environ.get("GENERATOR_PLATFORMS_DIR", str(Path(__file__).resolve().parent / "platforms")))

# How many generated pipelines are kept; the oldest are forgotten beyond this.
# Engineering guess: far more than anyone reopens, small enough that the list
# and the folder stay small.
MAX_PIPELINES = int(os.environ.get("GENERATOR_MAX_PIPELINES", "200"))

# How many finished generation jobs are kept for their callers to fetch. The
# browser that started a job fetches it within seconds of it finishing, so
# this only needs to cover a handful of open tabs (engineering guess).
MAX_FINISHED_JOBS = int(os.environ.get("GENERATOR_MAX_FINISHED_JOBS", "100"))

# How many generations run at once; the rest wait their turn. Each one runs
# three steps in other services, so a few at once is plenty for the people
# sharing one stack (engineering guess).
MAX_RUNNING_JOBS = int(os.environ.get("GENERATOR_MAX_RUNNING_JOBS", "2"))
