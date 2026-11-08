import atexit
import os
import shutil
import tempfile
from pathlib import Path

import pytest

# The required runs setting, set before the service modules read it.
_session_dir = Path(tempfile.mkdtemp(prefix="integration-runner-tests-"))
atexit.register(shutil.rmtree, _session_dir, ignore_errors=True)
os.environ["INTEGRATION_RUNNER_RUNS_DIR"] = str(_session_dir / "runs")


def pytest_configure(config):
    # Tests must not depend on a developer's local .env, notably
    # ORCHESTRATOR_STUB_DOCS (see stages/docs/agent.py), meant only for
    # interactive UI testing against a running Docker container, never for
    # the automated suite. Force it off for the whole test session;
    # test_docs_stage_returns_stub_output_without_calling_retrieval_when_flag_set
    # still exercises the stub path itself by patching docs_stage._STUB_DOCS
    # directly.
    from integration_runner.stages.docs import agent as docs_stage
    docs_stage._STUB_DOCS = False


@pytest.fixture(autouse=True)
def _isolated_validation_runs_dir(tmp_path, monkeypatch):
    # Any test that runs pim_stage/psm_stage/atl_stage/acceleo_stage for
    # real (not every test does, but nothing here can tell which ones
    # without checking, so this applies to all of them) would otherwise
    # write real attempt files into the session's shared folder. It is
    # autouse, so no individual test file has to remember to isolate this
    # itself.
    from integration_runner.stages import _validation
    monkeypatch.setattr(_validation, "RUNS_DIR", tmp_path / "runs")
