import atexit
import os
import shutil
import tempfile
from pathlib import Path

# Before the service's modules are imported, which read their settings once.
# The output folder is a required setting, so the tests give it a folder of
# their own, always, and never one a real stack was given.
_session_dir = Path(tempfile.mkdtemp(prefix="validator-agent-tests-"))
atexit.register(shutil.rmtree, _session_dir, ignore_errors=True)
os.environ["VALIDATOR_OUTPUT_DIR"] = str(_session_dir / "output")
