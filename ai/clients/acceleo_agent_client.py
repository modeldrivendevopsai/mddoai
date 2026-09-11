"""HTTP client for acceleo_agent: the real acceleo stage's whole capability
(see acceleo_agent/generation.py). No fallback on failure, matching this
repo's other client modules. Generous timeout: a real call can run the
Acceleo Generation Agent's multi-round regenerate loop, each round doing
one real LLM call plus one real validator-agent call.
"""
import os

import httpx

ACCELEO_AGENT_URL = os.environ.get("ACCELEO_AGENT_URL", "http://localhost:8080")
ACCELEO_TIMEOUT = float(os.environ.get("ACCELEO_TIMEOUT", "960.0"))
# Every prompt-config endpoint below is a fast, local filesystem/JSON
# operation, never a real LLM call (even /preview only renders text, it
# doesn't call the model) - a much shorter budget than ACCELEO_TIMEOUT's
# own real multi-round-regenerate-loop margin above is deliberate, a hung
# config read/write shouldn't leave a caller waiting 16 minutes to find out.
ACCELEO_CONFIG_TIMEOUT = float(os.environ.get("ACCELEO_CONFIG_TIMEOUT", "10.0"))


def run_acceleo(
    psm_artifact: str,
    platform_docs: str,
    constraints: list[str] | None = None,
    model: str | None = None,
    run_id: str | None = None,
    stage: str | None = None,
    attempt: str | None = None,
    mock: bool = False,
) -> dict:
    """POST acceleo_agent's real /generate: returns {"artifact", "prompt",
    "validation", "rounds", "prompt_version"}. stage/attempt name the
    calling stage ("acceleo") and its own reserved attempt directory -
    forwarded all the way through to generation.py's own real
    validator-agent call, so a call's real compiled .emtl module nests
    inside that same attempt directory instead of landing as an unlinked
    sibling of it (see stages/acceleo/agent.py's own reserve_attempt_dir()
    call). mock mirrors docs_stage's own per-run "Mock" override - see
    acceleo_agent's generation.py generate() docstring for exactly what it
    skips."""
    response = httpx.post(
        f"{ACCELEO_AGENT_URL}/generate",
        json={
            "psm_artifact": psm_artifact,
            "platform_docs": platform_docs,
            "constraints": constraints,
            "model": model,
            "run_id": run_id,
            "stage": stage,
            "attempt": attempt,
            "mock": mock,
        },
        timeout=ACCELEO_TIMEOUT,
    )
    response.raise_for_status()
    return response.json()


def _config_request(method: str, path: str, **kwargs) -> dict:
    response = httpx.request(method, f"{ACCELEO_AGENT_URL}{path}", timeout=ACCELEO_CONFIG_TIMEOUT, **kwargs)
    response.raise_for_status()
    return response.json()


def get_prompt_config(name: str) -> dict:
    return _config_request("GET", f"/prompt-config/{name}")


def save_prompt_config(name: str, config: dict) -> dict:
    return _config_request("PUT", f"/prompt-config/{name}", json=config)


def get_prompt_config_history(name: str) -> list[str]:
    return _config_request("GET", f"/prompt-config/{name}/history")["versions"]


def diff_prompt_config_versions(name: str, version_a: str, version_b: str) -> dict:
    return _config_request("GET", f"/prompt-config/{name}/diff", params={"a": version_a, "b": version_b})


def restore_prompt_config_version(name: str, version: str) -> dict:
    return _config_request("POST", f"/prompt-config/{name}/restore/{version}")


def revert_prompt_config(name: str) -> dict:
    return _config_request("POST", f"/prompt-config/{name}/revert")


def promote_prompt_config_to_default(name: str) -> dict:
    return _config_request("POST", f"/prompt-config/{name}/promote-to-default")


def check_prompt_config_references(name: str) -> list[dict]:
    return _config_request("GET", f"/prompt-config/{name}/check-references")["broken"]


def preview_prompt_config(name: str) -> dict:
    return _config_request("POST", f"/prompt-config/{name}/preview")


def add_learned_constraints(name: str, constraints: list[str]) -> dict:
    return _config_request("POST", f"/prompt-config/{name}/learned-constraints", json={"constraints": constraints})


def remove_learned_constraint(name: str, constraint: str) -> dict:
    return _config_request("DELETE", f"/prompt-config/{name}/learned-constraints", json={"constraint": constraint})


def list_available_files() -> list[str]:
    return _config_request("GET", "/available-files")["files"]


def upload_attachment_file(filename: str, content: bytes) -> str:
    """POSTs a real multipart file upload to acceleo_agent's own real
    /attachment-uploads (see routes/uploads.py) - returns the real, safe
    stored path to use as a new "file" attachment's own `path`."""
    response = httpx.post(
        f"{ACCELEO_AGENT_URL}/attachment-uploads", files={"file": (filename, content)}, timeout=ACCELEO_CONFIG_TIMEOUT
    )
    response.raise_for_status()
    return response.json()["path"]
