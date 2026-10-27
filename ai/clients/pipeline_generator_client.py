"""HTTP client for pipeline-generator, for the one thing other services ask
of it: publishing a platform approved in an integration run. A publish it
refuses (a name taken by a built-in platform, an output file outside its
folder, files over its size limit) raises AgentServiceError with its reason;
pipeline-generator being unreachable surfaces as a raw httpx error.
"""
import os

import httpx

from clients.agent_service_errors import AgentServiceError, raise_for_business_error

PIPELINE_GENERATOR_URL = os.environ.get("PIPELINE_GENERATOR_URL", "http://localhost:8100")
# A publish only writes four small files.
PUBLISH_TIMEOUT = float(os.environ.get("PIPELINE_GENERATOR_PUBLISH_TIMEOUT", "30.0"))

# pipeline-generator's answer to an artifact over its size limit. Sending it
# again cannot succeed, so it is a refusal like the business errors, not an
# outage.
_TOO_LARGE = 413


def publish_platform(
    *,
    name: str,
    output_filename: str,
    output_model_name: str,
    metamodel: str,
    transformation: str,
    template: str,
    source_run_id: str | None,
) -> dict:
    """POSTs to pipeline-generator's /platforms. Returns the published
    version: {"id", "name", "version", "output_filename"}."""
    response = httpx.post(
        f"{PIPELINE_GENERATOR_URL}/platforms",
        json={
            "name": name,
            "output_filename": output_filename,
            "output_model_name": output_model_name,
            "metamodel": metamodel,
            "transformation": transformation,
            "template": template,
            "source_run_id": source_run_id,
        },
        timeout=PUBLISH_TIMEOUT,
    )
    if response.status_code == _TOO_LARGE:
        raise AgentServiceError(_TOO_LARGE, "The platform's files are larger than the pipeline generator accepts.")
    raise_for_business_error(response)
    return response.json()
