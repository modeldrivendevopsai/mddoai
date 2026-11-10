import atexit
import os
import shutil
import tempfile
from pathlib import Path

# The required output setting, set before the service modules read it.
_session_dir = Path(tempfile.mkdtemp(prefix="validator-agent-tests-"))
atexit.register(shutil.rmtree, _session_dir, ignore_errors=True)
os.environ["VALIDATOR_OUTPUT_DIR"] = str(_session_dir / "output")

# Settings a service takes from its environment, which Docker Compose sets in a container.
os.environ.setdefault("LOG_LEVEL", "INFO")
# The built Java libraries, where `cd main && ./gradlew installDist` puts them.
os.environ.setdefault("VALIDATOR_LIB_DIR", str(Path(__file__).resolve().parents[3] / "main" / "build" / "install" / "com.mddoai" / "lib"))
