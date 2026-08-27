"""The Acceleo code-generation template stage. Still a placeholder — no
real Acceleo engine invocation exists yet — but no longer LLM prose either:
this always returns fixed mock Acceleo template source and validates it
for real against validator-agent's /validate/acceleo, the same way a real
Acceleo template's output eventually will. Unconditional for the same
reason stages/pim/agent.py's mock is: no real Acceleo generation to fall
back to yet.
"""
from clients import validator_agent_client
from integration_runner.stages._validation import persist_attempt, raise_if_invalid

_MODULE_NAME = "mockAcceleo"
# Acceleo requires a module's file to be literally named after its own
# module identifier (confirmed the hard way against a real running
# validator-agent: "Module 'mockAcceleo' must be defined in its own
# file" for anything else) — not a filename this stage gets to invent
# independently of _MODULE_NAME above.
_FILENAME = f"{_MODULE_NAME}.mtl"
# Same template shape as validator_agent/tests/fixtures/valid.mtl (already
# proven to compile via the real Acceleo parser, see that fixture's own use
# in validator_agent's test suite), renamed to its own mock module, not the
# full 244-line real generate.mtl. Targets the real, registered gitlab
# metamodel URI (same one valid.mtl itself targets) rather than an invented
# one — also confirmed the hard way: a made-up metamodel URI fails real
# validation with "The metamodel couldn't be resolved", since there's
# nothing in validator-agent's own classpath registered under it.
_MOCK_CONTENT = f"""[comment encoding = UTF-8 /]
[module {_MODULE_NAME}('http://www.mddoai.com/mddoai/metamodel/gitlab')]

[template public generateMockStages(stages : OrderedSet(String))]
mock-stages:
[for (stage: String | stages)]
  - [stage/]
[/for]
[/template]
"""


def acceleo_stage(context: dict) -> str:
    result = validator_agent_client.validate_acceleo(_MOCK_CONTENT, _FILENAME)
    persist_attempt(context.get("run_id", "unknown"), "acceleo", _FILENAME, _MOCK_CONTENT, result)
    raise_if_invalid("acceleo", result)
    return _MOCK_CONTENT
