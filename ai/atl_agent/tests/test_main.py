"""main.py's own REST surface: /health and /generate, the latter a thin
pass-through to generation.generate() (already covered in detail by
test_generation.py) - just proves the request/response wiring itself.
"""
from unittest.mock import patch

from fastapi.testclient import TestClient

import generation
import main

client = TestClient(main.app)


def test_health():
    response = client.get("/health")

    assert response.status_code == 200
    assert response.json() == {"status": "ok"}


def test_generate_endpoint_forwards_request_fields_to_generate():
    fake_result = {
        "artifact": "module m;",
        "prompt": {"constraints": ""},
        "validation": {"valid": True, "issues": [], "duration_ms": 1, "generated_source_path": None},
        "rounds": 1,
        "preset": "default",
        "prompt_version": None,
    }
    with patch.object(generation, "generate", return_value=fake_result) as mock_generate:
        response = client.post(
            "/generate",
            json={
                "pim_artifact": "<pim/>",
                "psm_artifact": "<psm/>",
                "platform_description": "TeamCity",
                "run_id": "run-1",
                "stage": "atl",
                "attempt": "attempt_1",
                "mock": True,
            },
        )

    assert response.status_code == 200
    assert response.json() == fake_result
    mock_generate.assert_called_once_with(
        "<pim/>", "<psm/>",
        platform_description="TeamCity",
        constraints=None,
        model=None,
        run_id="run-1",
        stage="atl",
        attempt="attempt_1",
        mock=True,
    )
