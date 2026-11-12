import clients.testing_urls  # noqa: F401  (the clients' links, which a service takes from its environment)
import os
from pathlib import Path

import pytest

from clients.journal_testing import folders_in, journal_in_process

# Settings a service takes from its environment, which Docker Compose sets in a container.
os.environ.setdefault("LOG_LEVEL", "INFO")
# The built Java libraries, where `cd main && ./gradlew installDist` puts them.
os.environ.setdefault("VALIDATOR_LIB_DIR", str(Path(__file__).resolve().parents[3] / "main" / "build" / "install" / "com.mddoai" / "lib"))


@pytest.fixture(autouse=True)
def journal(tmp_path):
    """The real journal, in this process, over folders of this test alone: what a validator
    compiled is kept there, with the attempt it was compiled for."""
    with journal_in_process(folders_in(tmp_path)):
        yield
