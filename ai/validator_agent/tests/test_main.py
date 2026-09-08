"""FastAPI endpoint tests. Mocks only the real boundary — main.run_ecore_validator
(main.py's own bound reference, imported from validator_runner) — matching
orchestrator's convention of mocking the process/network boundary, not internal
logic.
"""
from unittest.mock import patch

from fastapi.testclient import TestClient

import main
from validator_runner import ValidatorInfraError

client = TestClient(main.app)


def fake_result(valid=True, mode="reflective", issues=None, duration_ms=42, generated_source_path=None):
    return {
        "valid": valid, "mode": mode, "issues": issues or [], "duration_ms": duration_ms,
        "generated_source_path": generated_source_path,
    }


def test_health_endpoint():
    response = client.get("/health")

    assert response.status_code == 200
    assert response.json() == {"status": "ok"}


def test_validate_ecore_returns_200_with_valid_true():
    with patch("main.run_ecore_validator", return_value=fake_result(valid=True)):
        response = client.post("/validate/ecore", json={
            "filename": "model.ecore", "content": "<ecore/>", "mode": "reflective",
        })

    assert response.status_code == 200
    assert response.json()["valid"] is True


def test_validate_ecore_returns_200_with_valid_false_and_issues():
    issues = [{"severity": "ERROR", "message": "broken thing", "source": "model.ecore"}]
    with patch("main.run_ecore_validator", return_value=fake_result(valid=False, issues=issues)):
        response = client.post("/validate/ecore", json={
            "filename": "model.ecore", "content": "<ecore/>", "mode": "reflective",
        })

    assert response.status_code == 200
    body = response.json()
    assert body["valid"] is False
    assert body["issues"] == issues


def test_validate_ecore_passes_through_generated_source_path():
    with patch("main.run_ecore_validator",
               return_value=fake_result(mode="codegen", generated_source_path="/validator-output/abc123")):
        response = client.post("/validate/ecore", json={
            "filename": "model.ecore", "content": "<ecore/>", "mode": "codegen",
        })

    assert response.status_code == 200
    assert response.json()["generated_source_path"] == "/validator-output/abc123"


def test_validate_ecore_returns_500_on_infra_error():
    with patch("main.run_ecore_validator", side_effect=ValidatorInfraError("java not found")):
        response = client.post("/validate/ecore", json={
            "filename": "model.ecore", "content": "<ecore/>", "mode": "reflective",
        })

    assert response.status_code == 500
    assert "java not found" in response.json()["detail"]


def test_validate_ecore_rejects_oversized_content():
    oversized = "x" * (main.MAX_CONTENT_BYTES + 1)
    response = client.post("/validate/ecore", json={
        "filename": "model.ecore", "content": oversized, "mode": "reflective",
    })

    assert response.status_code == 413


def test_validate_ecore_rejects_invalid_mode():
    response = client.post("/validate/ecore", json={
        "filename": "model.ecore", "content": "<ecore/>", "mode": "bogus",
    })

    assert response.status_code == 422


def test_validate_ecore_rejects_missing_content():
    response = client.post("/validate/ecore", json={"filename": "model.ecore", "mode": "reflective"})

    assert response.status_code == 422


def test_validate_ecore_rejects_dot_dot_run_id():
    # "." and ".." are both made only of characters the pattern itself
    # allows, so this must be a real, reachable HTTP-level rejection, not
    # just something a unit test of the validator function would catch —
    # confirms the actual path-traversal fix, not just its intent.
    response = client.post("/validate/ecore", json={
        "filename": "model.ecore", "content": "<ecore/>", "mode": "codegen", "run_id": "..",
    })

    assert response.status_code == 422


def test_validate_ecore_rejects_dot_run_id():
    response = client.post("/validate/ecore", json={
        "filename": "model.ecore", "content": "<ecore/>", "mode": "codegen", "run_id": ".",
    })

    assert response.status_code == 422


def test_validate_ecore_rejects_dot_dot_newline_run_id():
    # Python's own re.match would let "..\n" satisfy a $-anchored pattern
    # (its $ matches immediately before a single trailing "\n" even without
    # re.MULTILINE) - but Pydantic v2 enforces `pattern=` via pydantic_core's
    # Rust regex engine, not Python's re module, and that engine's $ requires
    # true end-of-string (confirmed directly). This is the real, reachable
    # HTTP-level proof that this codebase's actual runtime behavior is safe,
    # not a fix for a gap that doesn't reach it.
    response = client.post("/validate/ecore", json={
        "filename": "model.ecore", "content": "<ecore/>", "mode": "codegen", "run_id": "..\n",
    })

    assert response.status_code == 422


def test_validate_ecore_forwards_stage_and_attempt():
    with patch("main.run_ecore_validator", return_value=fake_result()) as mock_run:
        client.post("/validate/ecore", json={
            "filename": "model.ecore", "content": "<ecore/>", "mode": "codegen",
            "run_id": "run-123", "stage": "pim", "attempt": "attempt_2",
        })

    mock_run.assert_called_once_with("<ecore/>", "model.ecore", "codegen", "run-123", "pim", "attempt_2")


def test_validate_ecore_rejects_dot_dot_stage():
    response = client.post("/validate/ecore", json={
        "filename": "model.ecore", "content": "<ecore/>", "mode": "codegen",
        "run_id": "run-123", "stage": "..",
    })

    assert response.status_code == 422


def test_validate_ecore_rejects_dot_dot_attempt():
    response = client.post("/validate/ecore", json={
        "filename": "model.ecore", "content": "<ecore/>", "mode": "codegen",
        "run_id": "run-123", "attempt": "..",
    })

    assert response.status_code == 422


def fake_atl_result(valid=True, issues=None, duration_ms=42, generated_source_path=None):
    return {
        "valid": valid, "issues": issues or [], "duration_ms": duration_ms,
        "generated_source_path": generated_source_path,
    }


def test_validate_atl_returns_200_with_valid_true():
    with patch("main.run_atl_validator", return_value=fake_atl_result(valid=True)):
        response = client.post("/validate/atl", json={"filename": "sample.atl", "content": "module M;"})

    assert response.status_code == 200
    assert response.json()["valid"] is True


def test_validate_atl_returns_200_with_valid_false_and_issues():
    issues = [{"severity": "ERROR", "message": "mismatched input", "source": "sample.atl#3:1"}]
    with patch("main.run_atl_validator", return_value=fake_atl_result(valid=False, issues=issues)):
        response = client.post("/validate/atl", json={"filename": "sample.atl", "content": "module M;"})

    assert response.status_code == 200
    body = response.json()
    assert body["valid"] is False
    assert body["issues"] == issues


def test_validate_atl_returns_500_on_infra_error():
    with patch("main.run_atl_validator", side_effect=ValidatorInfraError("java not found")):
        response = client.post("/validate/atl", json={"filename": "sample.atl", "content": "module M;"})

    assert response.status_code == 500
    assert "java not found" in response.json()["detail"]


def test_validate_atl_rejects_oversized_content():
    oversized = "x" * (main.MAX_CONTENT_BYTES + 1)
    response = client.post("/validate/atl", json={"filename": "sample.atl", "content": oversized})

    assert response.status_code == 413


def test_validate_atl_rejects_missing_content():
    response = client.post("/validate/atl", json={"filename": "sample.atl"})

    assert response.status_code == 422


def test_validate_atl_passes_through_generated_source_path():
    with patch("main.run_atl_validator",
               return_value=fake_atl_result(generated_source_path="/runs/run-1/atl-validate-abc/sample.asm")):
        response = client.post("/validate/atl", json={"filename": "sample.atl", "content": "module M;"})

    assert response.status_code == 200
    assert response.json()["generated_source_path"] == "/runs/run-1/atl-validate-abc/sample.asm"


def test_validate_atl_forwards_run_id():
    with patch("main.run_atl_validator", return_value=fake_atl_result()) as mock_run:
        client.post("/validate/atl", json={"filename": "sample.atl", "content": "module M;", "run_id": "run-123"})

    mock_run.assert_called_once_with("module M;", "sample.atl", "run-123", None, None)


def test_validate_atl_forwards_stage_and_attempt():
    with patch("main.run_atl_validator", return_value=fake_atl_result()) as mock_run:
        client.post("/validate/atl", json={
            "filename": "sample.atl", "content": "module M;",
            "run_id": "run-123", "stage": "atl", "attempt": "attempt_2",
        })

    mock_run.assert_called_once_with("module M;", "sample.atl", "run-123", "atl", "attempt_2")


def test_validate_atl_rejects_dot_dot_run_id():
    response = client.post("/validate/atl", json={
        "filename": "sample.atl", "content": "module M;", "run_id": "..",
    })

    assert response.status_code == 422


def test_validate_atl_rejects_dot_dot_stage():
    response = client.post("/validate/atl", json={
        "filename": "sample.atl", "content": "module M;", "run_id": "run-123", "stage": "..",
    })

    assert response.status_code == 422


def test_validate_atl_rejects_dot_dot_attempt():
    response = client.post("/validate/atl", json={
        "filename": "sample.atl", "content": "module M;", "run_id": "run-123", "attempt": "..",
    })

    assert response.status_code == 422


def fake_acceleo_result(valid=True, issues=None, duration_ms=42, generated_source_path=None):
    return {
        "valid": valid, "issues": issues or [], "duration_ms": duration_ms,
        "generated_source_path": generated_source_path,
    }


def test_validate_acceleo_returns_200_with_valid_true():
    with patch("main.run_acceleo_validator", return_value=fake_acceleo_result(valid=True)):
        response = client.post("/validate/acceleo", json={
            "filename": "generate.mtl", "content": "[module generate('http://x')]",
        })

    assert response.status_code == 200
    assert response.json()["valid"] is True


def test_validate_acceleo_returns_200_with_valid_false_and_issues():
    issues = [{"severity": "ERROR", "message": "'for' block body isn't terminated", "source": "generate.mtl#13"}]
    with patch("main.run_acceleo_validator", return_value=fake_acceleo_result(valid=False, issues=issues)):
        response = client.post("/validate/acceleo", json={
            "filename": "generate.mtl", "content": "[module generate('http://x')]",
        })

    assert response.status_code == 200
    body = response.json()
    assert body["valid"] is False
    assert body["issues"] == issues


def test_validate_acceleo_returns_500_on_infra_error():
    with patch("main.run_acceleo_validator", side_effect=ValidatorInfraError("java not found")):
        response = client.post("/validate/acceleo", json={
            "filename": "generate.mtl", "content": "[module generate('http://x')]",
        })

    assert response.status_code == 500
    assert "java not found" in response.json()["detail"]


def test_validate_acceleo_rejects_oversized_content():
    oversized = "x" * (main.MAX_CONTENT_BYTES + 1)
    response = client.post("/validate/acceleo", json={"filename": "generate.mtl", "content": oversized})

    assert response.status_code == 413


def test_validate_acceleo_passes_through_generated_source_path():
    with patch("main.run_acceleo_validator",
               return_value=fake_acceleo_result(generated_source_path="/runs/run-1/acceleo-validate-abc/out")):
        response = client.post("/validate/acceleo", json={
            "filename": "generate.mtl", "content": "[module generate('http://x')]",
        })

    assert response.status_code == 200
    assert response.json()["generated_source_path"] == "/runs/run-1/acceleo-validate-abc/out"


def test_validate_acceleo_forwards_run_id():
    with patch("main.run_acceleo_validator", return_value=fake_acceleo_result()) as mock_run:
        client.post("/validate/acceleo", json={
            "filename": "generate.mtl", "content": "[module generate('http://x')]", "run_id": "run-123",
        })

    mock_run.assert_called_once_with("[module generate('http://x')]", "generate.mtl", "run-123", None, None)


def test_validate_acceleo_forwards_stage_and_attempt():
    with patch("main.run_acceleo_validator", return_value=fake_acceleo_result()) as mock_run:
        client.post("/validate/acceleo", json={
            "filename": "generate.mtl", "content": "[module generate('http://x')]",
            "run_id": "run-123", "stage": "acceleo", "attempt": "attempt_2",
        })

    mock_run.assert_called_once_with(
        "[module generate('http://x')]", "generate.mtl", "run-123", "acceleo", "attempt_2"
    )


def test_validate_acceleo_rejects_dot_dot_run_id():
    response = client.post("/validate/acceleo", json={
        "filename": "generate.mtl", "content": "[module generate('http://x')]", "run_id": "..",
    })

    assert response.status_code == 422


def test_validate_acceleo_rejects_dot_dot_stage():
    response = client.post("/validate/acceleo", json={
        "filename": "generate.mtl", "content": "[module generate('http://x')]",
        "run_id": "run-123", "stage": "..",
    })

    assert response.status_code == 422


def test_validate_acceleo_rejects_dot_dot_attempt():
    response = client.post("/validate/acceleo", json={
        "filename": "generate.mtl", "content": "[module generate('http://x')]",
        "run_id": "run-123", "attempt": "..",
    })

    assert response.status_code == 422


def test_validate_acceleo_rejects_missing_content():
    response = client.post("/validate/acceleo", json={"filename": "generate.mtl"})

    assert response.status_code == 422
