"""Real end-to-end test: no mocking, spawns a real java subprocess against the
real EcoreValidatorCli, parses real JSON on stdout. Auto-skips when a JDK and
the built main/ distribution aren't available, so the fast suite (plain
`pytest`) always runs standalone on any machine. CI (or a local run after
`cd main && ./gradlew installDist`) gets the real run by pointing
VALIDATOR_LIB_DIR at the real built lib/ directory.
"""
import shutil
from pathlib import Path

import pytest
from fastapi.testclient import TestClient

import main
import validator_runner

FIXTURES_DIR = Path(__file__).parent / "fixtures"

# Checks the exact same LIB_DIR the real code path resolves (see
# validator_runner.py), not a separately-hardcoded copy of its default —
# two independent defaults silently drifting apart is exactly what let this
# skip check pass locally while the real subprocess call still failed
# against a nonexistent classpath (confirmed the hard way, not a hypothetical).
pytestmark = pytest.mark.skipif(
    not (shutil.which("java") and Path(validator_runner.LIB_DIR).exists()),
    reason="real JDK + built main/ distribution not available",
)

client = TestClient(main.app)


def test_real_validation_of_valid_fixture():
    content = (FIXTURES_DIR / "valid.ecore").read_text(encoding="utf-8")

    response = client.post("/validate/ecore", json={
        "filename": "valid.ecore", "content": content, "mode": "reflective",
    })

    assert response.status_code == 200
    body = response.json()
    assert body["valid"] is True
    assert body["issues"] == []
    assert body["duration_ms"] >= 0


def test_real_validation_of_invalid_fixture():
    content = (FIXTURES_DIR / "invalid.ecore").read_text(encoding="utf-8")

    response = client.post("/validate/ecore", json={
        "filename": "invalid.ecore", "content": content, "mode": "reflective",
    })

    assert response.status_code == 200
    body = response.json()
    assert body["valid"] is False
    assert any("NoSuchType" in issue["message"] for issue in body["issues"])
