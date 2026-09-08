"""HTTP client for psm_agent: the real psm stage's whole capability (routes
between generation and existing-platform comparison internally, see
psm_agent/psm_flow.py). No fallback on failure, matching this repo's other
client modules. Generous timeout: a real call can run the Generation Agent's
multi-round regenerate loop, each round doing one real LLM call plus one real
validator-agent call."""
import os

import httpx

PSM_AGENT_URL = os.environ.get("PSM_AGENT_URL", "http://localhost:8040")
PSM_TIMEOUT = float(os.environ.get("PSM_TIMEOUT", "960.0"))
# Every prompt-config endpoint below is a fast, local filesystem/JSON
# operation, never a real LLM call (even /preview only renders text, it
# doesn't call the model) - a much shorter budget than PSM_TIMEOUT's own
# real multi-round-regenerate-loop margin above is deliberate, a hung
# config read/write shouldn't leave a caller waiting 16 minutes to find out.
PSM_CONFIG_TIMEOUT = float(os.environ.get("PSM_CONFIG_TIMEOUT", "10.0"))


def run_psm(
    platform_description: str,
    pim_artifact: str,
    platform_docs: str,
    constraints: list[str] | None = None,
    model: str | None = None,
    run_id: str | None = None,
    stage: str | None = None,
    attempt: str | None = None,
    mock: bool = False,
) -> dict:
    """POST psm_agent's real /psm: returns either a generation-mode result
    ({"mode": "generation", "artifact", "prompt", "validation", "rounds"}) or
    a knowledge-mode result ({"mode": "knowledge", "artifact", "gaps", "prompt"}).
    stage/attempt name the calling stage ("psm") and its own reserved attempt
    directory - forwarded all the way through to generation.py's own real
    validator-agent call, so a generation-mode call's real compiled Ecore
    classes nest inside that same attempt directory instead of landing as an
    unlinked sibling of it (see stages/psm/agent.py's own reserve_attempt_dir()
    call, the same pattern stages/atl/agent.py and stages/acceleo/agent.py use).
    mock mirrors docs_stage's own per-run "Mock" override - see
    psm_agent's generation.py generate() docstring for exactly what it skips."""
    response = httpx.post(
        f"{PSM_AGENT_URL}/psm",
        json={
            "platform_description": platform_description,
            "pim_artifact": pim_artifact,
            "platform_docs": platform_docs,
            "constraints": constraints,
            "model": model,
            "run_id": run_id,
            "stage": stage,
            "attempt": attempt,
            "mock": mock,
        },
        timeout=PSM_TIMEOUT,
    )
    response.raise_for_status()
    return response.json()


def _config_request(method: str, path: str, **kwargs) -> dict:
    response = httpx.request(method, f"{PSM_AGENT_URL}{path}", timeout=PSM_CONFIG_TIMEOUT, **kwargs)
    response.raise_for_status()
    return response.json()


def list_presets(name: str) -> list[dict]:
    """Every real preset psm_agent knows about for this mode ("generation"
    or "comparison"), as {"id", "label", "platform_hints"} metadata - see
    generation_toolkit.prompt_config.presets.list_preset_metadata."""
    return _config_request("GET", f"/prompt-config/{name}/presets")["presets"]


def get_prompt_config(name: str, preset: str) -> dict:
    return _config_request("GET", f"/prompt-config/{name}/{preset}")


def save_prompt_config(name: str, preset: str, config: dict) -> dict:
    return _config_request("PUT", f"/prompt-config/{name}/{preset}", json=config)


def get_prompt_config_history(name: str, preset: str) -> list[str]:
    return _config_request("GET", f"/prompt-config/{name}/{preset}/history")["versions"]


def diff_prompt_config_versions(name: str, preset: str, version_a: str, version_b: str) -> dict:
    return _config_request(
        "GET", f"/prompt-config/{name}/{preset}/diff", params={"a": version_a, "b": version_b}
    )


def restore_prompt_config_version(name: str, preset: str, version: str) -> dict:
    return _config_request("POST", f"/prompt-config/{name}/{preset}/restore/{version}")


def revert_prompt_config(name: str, preset: str) -> dict:
    return _config_request("POST", f"/prompt-config/{name}/{preset}/revert")


def promote_prompt_config_to_default(name: str, preset: str) -> dict:
    return _config_request("POST", f"/prompt-config/{name}/{preset}/promote-to-default")


def check_prompt_config_references(name: str, preset: str) -> list[dict]:
    return _config_request("GET", f"/prompt-config/{name}/{preset}/check-references")["broken"]


def preview_prompt_config(name: str, preset: str) -> dict:
    return _config_request("POST", f"/prompt-config/{name}/{preset}/preview")


def add_learned_constraints(name: str, preset: str, constraints: list[str]) -> dict:
    return _config_request(
        "POST", f"/prompt-config/{name}/{preset}/learned-constraints", json={"constraints": constraints}
    )


def remove_learned_constraint(name: str, preset: str, constraint: str) -> dict:
    return _config_request(
        "DELETE", f"/prompt-config/{name}/{preset}/learned-constraints", json={"constraint": constraint}
    )


def list_available_files() -> list[str]:
    return _config_request("GET", "/available-files")["files"]
