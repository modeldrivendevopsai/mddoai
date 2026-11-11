"""The mock chain (generation_toolkit's mock_chain) through the real engines: each artifact
compiles in the real validator, and the whole chain runs on the sample PIM that the generation
stage uses and writes its file. Nothing is mocked here.
Auto-skips when validator-agent or execution-agent isn't reachable, like the other real-engine
tests, so the fast suite runs standalone. A local run needs both started (`docker compose up
validator-agent execution-agent`)."""
import os
import re
from pathlib import Path

import httpx
import pytest

from clients import execution_agent_client, validator_agent_client
from generation_toolkit.mock_chain import (
    MOCK_ATL,
    MOCK_MTL,
    MOCK_OUTPUT_FILENAME,
    MOCK_PSM_ECORE,
    MOCK_TEMPLATE_FILENAME,
)
from integration_runner.atl_source import output_model_name


def _reachable(url: str) -> bool:
    try:
        httpx.get(f"{url}/health", timeout=2.0).raise_for_status()
        return True
    except Exception:
        return False


pytestmark = pytest.mark.skipif(
    not (_reachable(validator_agent_client.VALIDATOR_AGENT_URL) and _reachable(execution_agent_client.EXECUTION_AGENT_URL)),
    reason="real validator-agent and execution-agent not both reachable",
)


def _sample_pim() -> str:
    return Path(os.environ["PIM_SAMPLE_INSTANCE_PATH"]).read_text(encoding="utf-8")


def test_each_mock_artifact_compiles_in_the_real_validator():
    metamodel = validator_agent_client.validate_ecore(MOCK_PSM_ECORE, mode="codegen")
    transformation = validator_agent_client.validate_atl(MOCK_ATL, "generated.atl", metamodel_ecore=MOCK_PSM_ECORE)
    template = validator_agent_client.validate_acceleo(
        MOCK_MTL, MOCK_TEMPLATE_FILENAME, metamodel_ecore=MOCK_PSM_ECORE, atl_source=MOCK_ATL
    )

    assert [r["valid"] for r in (metamodel, transformation, template)] == [True, True, True], (metamodel, transformation, template)


def test_the_mock_chain_runs_on_the_sample_pim_and_writes_one_file_with_every_job():
    pim = _sample_pim()
    psm_instance = execution_agent_client.execute_atl_from_pim(
        MOCK_ATL, pim, MOCK_PSM_ECORE, output_model_name(MOCK_ATL)
    )
    files = execution_agent_client.execute_acceleo(MOCK_MTL, psm_instance, MOCK_PSM_ECORE)

    assert list(files) == [MOCK_OUTPUT_FILENAME]
    # Every job of the PIM is a line of the file.
    job_names = re.findall(r'<pimMM:ScriptJob [^>]*\bname="([^"]+)"', pim)
    assert job_names
    assert [line.strip()[2:] for line in files[MOCK_OUTPUT_FILENAME].splitlines() if line.strip().startswith("- ")] == job_names
