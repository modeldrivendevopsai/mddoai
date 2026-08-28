"""The PSM (Platform-Specific Model) stage. Still a placeholder — no real
MDE toolchain exists yet — but no longer LLM prose either: this always
returns fixed mock Ecore content, in the shape of a per-platform
realization (like meta_models/com.mddoai.metamodel.gitlab/model/
gitlabMM.ecore), and validates it for real against validator-agent's
/validate/ecore — PIM and PSM share the same real target format (Ecore),
just a different metamodel, see stages/pim/agent.py's own docstring for why
psm shares that stage's wiring. Unconditional for the same reason
stages/pim/agent.py's mock is: no real PSM derivation to fall back to yet.
"""
from clients import validator_agent_client
from integration_runner.stages._validation import persist_attempt, raise_if_invalid

_FILENAME = "psm_mock.ecore"
# Minimal, already-proven-valid shape (see stages/pim/agent.py's own
# comment on validator_agent/tests/fixtures/valid.ecore), shaped like a
# gitlab-flavored PSM realization rather than pim's platform-independent
# one, not the full 242-line real gitlabMM.ecore.
_MOCK_CONTENT = """<?xml version="1.0" encoding="UTF-8"?>
<ecore:EPackage xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:ecore="http://www.eclipse.org/emf/2002/Ecore" name="mockPsm" nsURI="http://mddoai.com/mock/psm" nsPrefix="mockPsm">
  <eClassifiers xsi:type="ecore:EClass" name="MockPipeline">
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="stages" upperBound="-1" eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"/>
  </eClassifiers>
</ecore:EPackage>
"""


def psm_stage(context: dict) -> str:
    result = validator_agent_client.validate_ecore(_MOCK_CONTENT, _FILENAME)
    persist_attempt(context.get("run_id", "unknown"), "psm", _FILENAME, _MOCK_CONTENT, result)
    raise_if_invalid("psm", result)
    return _MOCK_CONTENT
