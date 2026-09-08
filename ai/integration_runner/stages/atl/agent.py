"""The ATL transformation stage. Still a placeholder — no real ATL engine
invocation exists yet — but no longer LLM prose either: this always
returns fixed mock ATL source and validates it for real against
validator-agent's /validate/atl, the same way a real ATL transformation's
output eventually will. Unconditional for the same reason
stages/pim/agent.py's mock is: no real ATL generation to fall back to yet.
"""
from clients import validator_agent_client
from integration_runner.stages._validation import persist_attempt, raise_if_invalid, reserve_attempt_dir

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
    # Reserved before validate_atl() runs, not after: validate_atl() is what
    # triggers AtlValidator's own real compiled .asm write, and that write
    # needs the real stage+attempt path to land inside, not beside it. See
    # reserve_attempt_dir()'s own docstring. attempt_dir.name alone is only
    # "attempt_N" - the stage segment ("atl") has to be forwarded separately
    # too, or the compiled output would nest one level too shallow (missing
    # the stage folder entirely) and could even collide with another
    # stage's own same-numbered attempt under the same run_id. Without a
    # run_id there is no run tree to reserve an attempt under, so
    # persist_attempt() below still reserves its own in that case, exactly
    # as it always has.
    run_id = context.get("run_id")
    attempt_dir = reserve_attempt_dir(run_id, "atl") if run_id else None
    result = validator_agent_client.validate_atl(
        _MOCK_CONTENT, _FILENAME, run_id=run_id,
        stage="atl" if attempt_dir else None,
        attempt=attempt_dir.name if attempt_dir else None,
    )
    persist_attempt(run_id or "unknown", "atl", _FILENAME, _MOCK_CONTENT, result, attempt_dir=attempt_dir)
    raise_if_invalid("atl", result)
    return _MOCK_CONTENT
