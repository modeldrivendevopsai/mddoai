"""Real end-to-end test: no mocking, makes a real ai_layer_client.chat()
call and a real validator_agent_client.validate_acceleo() call, exercising
generate() fully end to end. Auto-skips when either isn't reachable,
matching psm_agent's own test_generation_real_llm.py's real-dependency-gated
pattern. CI (or a local run after `docker compose up ai-layer validator-agent`)
gets the real run.
"""
import httpx
import pytest

from clients import ai_layer_client, validator_agent_client
from generation import generate


def _dependencies_reachable() -> bool:
    try:
        httpx.get(f"{ai_layer_client.AI_LAYER_URL}/health", timeout=2.0).raise_for_status()
        httpx.get(f"{validator_agent_client.VALIDATOR_AGENT_URL}/health", timeout=2.0).raise_for_status()
        return True
    except Exception:
        return False


pytestmark = pytest.mark.skipif(
    not _dependencies_reachable(),
    reason=(
        f"real ai-layer ({ai_layer_client.AI_LAYER_URL}) or validator-agent "
        f"({validator_agent_client.VALIDATOR_AGENT_URL}) not reachable"
    ),
)

_SYNTHETIC_TARGET_PSM_ARTIFACT = """<?xml version="1.0" encoding="UTF-8"?>
<ecore:EPackage xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:ecore="http://www.eclipse.org/emf/2002/Ecore" name="teamcityMM" nsURI="http://www.mddoai.com/mddoai/metamodel/teamcity" nsPrefix="teamcityMM">
  <eClassifiers xsi:type="ecore:EClass" name="Pipeline">
    <eStructuralFeatures xsi:type="ecore:EReference" name="jobs" upperBound="-1" eType="#//Job" containment="true"/>
  </eClassifiers>
  <eClassifiers xsi:type="ecore:EClass" name="Job">
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="name" eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"/>
  </eClassifiers>
</ecore:EPackage>
"""

_TEAMCITY_DOCS = """# TeamCity CI/CD Configuration
A build configuration has a name and a list of build steps. Each step runs
a script. Build configurations belong to a project and can be triggered on
a VCS commit or on a schedule.
"""


def test_real_generation_produces_a_real_acceleo_template():
    result = generate(_SYNTHETIC_TARGET_PSM_ARTIFACT, _TEAMCITY_DOCS, "TeamCity", model=None)

    assert result["artifact"].strip()
    assert "rounds" in result and result["rounds"] >= 1
    assert "validation" in result and "valid" in result["validation"]
    # Real, non-deterministic LLM output - only structural invariants are
    # checked, not exact wording: it must at least look like an Acceleo
    # module with a main template.
    artifact_lower = result["artifact"].lower()
    assert "module" in artifact_lower
    assert "template" in artifact_lower
