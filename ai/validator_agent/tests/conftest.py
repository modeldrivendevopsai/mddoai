import atexit
import os
import shutil
import tempfile
from pathlib import Path

# The required output setting, set before the service modules read it.
_session_dir = Path(tempfile.mkdtemp(prefix="validator-agent-tests-"))
atexit.register(shutil.rmtree, _session_dir, ignore_errors=True)
os.environ["VALIDATOR_OUTPUT_DIR"] = str(_session_dir / "output")
