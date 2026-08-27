"""The PIM (Platform-Independent Model) stage. Still a placeholder — no
real PIM extraction pipeline exists yet — but no longer LLM prose either:
this always returns fixed mock Ecore content (see stages/__init__.py's own
docstring for how this gets replaced with a real agent later, matching
stages/docs/agent.py's own history) and validates it for real against
validator-agent's /validate/ecore, the same way a real PIM extraction's
output eventually will. Unconditional, unlike docs_stage's own opt-in mock
(a real crawl works today there, mock is an escape hatch): there is no real
PIM extraction to fall back to yet, so this always returns mock content, no
toggle. Ignores its input context for the same reason — corrections/
constraints have nothing to act on against fixed content — until a real
extraction pipeline replaces this.
"""
from clients import validator_agent_client
from integration_runner.stages._validation import persist_attempt, raise_if_invalid

_FILENAME = "pim_mock.ecore"
# Minimal, already-proven-valid shape (same structure as
# validator_agent/tests/fixtures/valid.ecore, which the validator's own
# real test suite already asserts passes reflective validation), not the
# full 275-line real pimMM.ecore — named distinctly so it's never mistaken
# for that real metamodel.
_MOCK_CONTENT = """<?xml version="1.0" encoding="UTF-8"?>
<ecore:EPackage xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:ecore="http://www.eclipse.org/emf/2002/Ecore" name="mockPim" nsURI="http://mddoai.com/mock/pim" nsPrefix="mockPim">
  <eClassifiers xsi:type="ecore:EClass" name="MockPipelineBlock">
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="name" eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"/>
  </eClassifiers>
</ecore:EPackage>
"""


def pim_stage(context: dict) -> str:
    result = validator_agent_client.validate_ecore(_MOCK_CONTENT, _FILENAME)
    persist_attempt(context.get("run_id", "unknown"), "pim", _FILENAME, _MOCK_CONTENT, result)
    raise_if_invalid("pim", result)
    return _MOCK_CONTENT
