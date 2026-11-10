"""This service's settings in one place: what its environment must provide, and the limits it holds itself to."""
from pathlib import Path

from clients.required_env import required_env

# A real .swarch model is a handful of attributes. The ceiling is far above
# any real file and exists only to stop an absurdly large upload being parsed
# or fed to a JVM.
MAX_MODEL_BYTES = 256 * 1024

# The largest pipeline a template may produce, and the largest the validate
# endpoint accepts. validator-agent has a limit of its own for what it checks
# (MAX_PIPELINE_BYTES there), which must be at least this large. The models a
# generation passes through are held to the same limit.
# A real pipeline or model is a few KB; this only guards against keeping
# something unexpectedly huge in memory and on disk.
MAX_PIPELINE_BYTES = 512 * 1024

# The longest path or file name a request may carry. Engineering guess: far
# longer than anything typed into the screen or named a model file.
MAX_PATH_CHARS = 1024
MAX_FILENAME_CHARS = 255



# The folder models are picked from.
WORKSPACE_DIR = Path(required_env("GENERATOR_WORKSPACE_DIR"))

# The workspace's name in Browse: inside a container WORKSPACE_DIR is only a mount point.
WORKSPACE_LABEL = required_env("GENERATOR_WORKSPACE_LABEL")

# Where generated pipelines are kept so they can be reopened later.
PIPELINES_DIR = Path(required_env("GENERATOR_PIPELINES_DIR"))

# How many generated pipelines are kept; the oldest are forgotten beyond this.
# Engineering guess: far more than anyone reopens, small enough that the list
# and the folder stay small.
MAX_PIPELINES = 200

# How many finished generation jobs are kept for their callers to fetch. The
# browser that started a job fetches it within seconds of it finishing, so
# this only needs to cover a handful of open tabs (engineering guess).
MAX_FINISHED_JOBS = 100

# How many generations run at once; the rest wait their turn. Each one runs
# three steps in other services, so a few at once is plenty for the people
# sharing one stack (engineering guess).
MAX_RUNNING_JOBS = 2
