"""Real end-to-end test: no mocking, spawns a real java subprocess against the
real EcoreValidatorCli, parses real JSON on stdout. Auto-skips when a JDK and
the built main/ distribution aren't available, so the fast suite (plain
`pytest`) always runs standalone on any machine. CI (or a local run after
`cd main && ./gradlew installDist`) gets the real run by pointing
VALIDATOR_LIB_DIR at the real built lib/ directory.
"""
import os
import shutil
from pathlib import Path

import pytest
from fastapi.testclient import TestClient

import main

FIXTURES_DIR = Path(__file__).parent / "fixtures"
LIB_DIR = Path(os.environ.get("VALIDATOR_LIB_DIR", "../../main/build/install/com.mddoai/lib"))

pytestmark = pytest.mark.skipif(
    not (shutil.which("java") and LIB_DIR.exists()),
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
