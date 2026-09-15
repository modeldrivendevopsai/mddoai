"""HTTP client for execution-agent: real ATL/Acceleo execution, backed by
the same Java CLIs main/'s own build produces (see
execution_agent/README.md). A real, describable execution failure (bad
input, a genuine ATL/Acceleo compile or runtime error) raises
AgentServiceError (status_code=422), not a raw httpx error - matching
psm_agent_client.py's/atl_agent_client.py's/acceleo_agent_client.py's own
_config_request pattern, since execution-agent reports this exact kind of
structured business error too (unlike validator_agent, where validity is
always a normal 200 response, never something to branch on via an
exception). A genuine infra failure (execution-agent unreachable, its own
subprocess crashed) still surfaces as a raw httpx error.
"""
import os

import httpx

from clients.agent_service_errors import raise_for_business_error

EXECUTION_AGENT_URL = os.environ.get("EXECUTION_AGENT_URL", "http://localhost:8090")
# Comfortably above execution-agent's own default execution timeout so a
# real subprocess run never gets cut off from this side first.
EXECUTE_TIMEOUT = float(os.environ.get("EXECUTE_TIMEOUT", "90.0"))


def _execute_request(path: str, payload: dict) -> dict:
    response = httpx.post(f"{EXECUTION_AGENT_URL}{path}", json=payload, timeout=EXECUTE_TIMEOUT)
    raise_for_business_error(response)
    return response.json()


def execute_atl(atl_source: str, pim_model_xmi: str, target_ecore: str, output_model_name: str = "OUT") -> str:
    """POSTs to execution-agent's real /execute/atl: runs atl_source (real
    .atl transformation text) against pim_model_xmi (a real PIM model
    instance, XMI text) to produce a real instance of target_ecore (the
    target platform's own PSM metamodel, Ecore text). Returns the real
    output model, serialized as XMI text. Raises AgentServiceError for a
    real, describable execution failure (bad input, a genuine ATL compile
    or runtime error)."""
    result = _execute_request("/execute/atl", {
        "atl_source": atl_source,
        "pim_model_xmi": pim_model_xmi,
        "target_ecore": target_ecore,
        "output_model_name": output_model_name,
    })
    return result["output_xmi"]


def execute_acceleo(mtl_source: str, psm_model_xmi: str, target_ecore: str) -> dict[str, str]:
    """POSTs to execution-agent's real /execute/acceleo: runs mtl_source
    (real .mtl Acceleo template text) against psm_model_xmi (a real PSM
    model instance, XMI text, conforming to target_ecore) to produce the
    real generated file(s). Returns {relative_path: real generated text
    content} - a real Acceleo template can legitimately generate more than
    one file. Raises AgentServiceError for a real, describable execution
    failure (bad input, a genuine Acceleo compile or runtime error)."""
    result = _execute_request("/execute/acceleo", {
        "mtl_source": mtl_source,
        "psm_model_xmi": psm_model_xmi,
        "target_ecore": target_ecore,
    })
    return result["generated_files"]
