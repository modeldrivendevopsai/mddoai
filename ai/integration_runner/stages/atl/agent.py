"""The ATL transformation stage. Still a placeholder — no real ATL engine
invocation exists yet — but no longer LLM prose either: this always
returns fixed mock ATL source and validates it for real against
validator-agent's /validate/atl, the same way a real ATL transformation's
output eventually will. Unconditional for the same reason
stages/pim/agent.py's mock is: no real ATL generation to fall back to yet.
"""
from clients import validator_agent_client
from integration_runner.stages._validation import persist_attempt, raise_if_invalid

_FILENAME = "atl_mock.atl"
# Same rule shape as validator_agent/tests/fixtures/valid.atl (already
# proven to compile via the real ATL standalone compiler, see that
# fixture's own use in validator_agent's test suite), renamed to reflect a
# PIM -> PSM mapping instead of that fixture's SWArch -> PIM one, not the
# full 522-line real pim2gitlabmodel.atl.
_MOCK_CONTENT = """module MockPim2Psm;
create OUT : PSM from IN : PIM;

rule MockPipelineBlock2MockPipeline {
\tfrom
\t\ts : PIM!MockPipelineBlock
\tto
\t\tt : PSM!MockPipeline (
\t\t\tstages <- s.name
\t\t)
}
"""


def atl_stage(context: dict) -> str:
    result = validator_agent_client.validate_atl(_MOCK_CONTENT, _FILENAME)
    persist_attempt(context.get("run_id", "unknown"), "atl", _FILENAME, _MOCK_CONTENT, result)
    raise_if_invalid("atl", result)
    return _MOCK_CONTENT
