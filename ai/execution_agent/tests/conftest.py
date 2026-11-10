"""Pytest setup for this suite."""
import os
from pathlib import Path

# Settings a service takes from its environment, which Docker Compose sets in a container.
os.environ.setdefault("LOG_LEVEL", "INFO")
# The built Java libraries, where `cd main && ./gradlew installDist` puts them.
os.environ.setdefault("EXECUTION_LIB_DIR", str(Path(__file__).resolve().parents[3] / "main" / "build" / "install" / "com.mddoai" / "lib"))
