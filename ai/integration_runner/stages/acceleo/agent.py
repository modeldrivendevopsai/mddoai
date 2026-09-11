"""The Acceleo code-generation template stage. Still a placeholder — no
real Acceleo engine invocation exists yet — but no longer LLM prose either:
this always returns fixed mock Acceleo template source and validates it
for real against validator-agent's /validate/acceleo, the same way a real
Acceleo template's output eventually will. Unconditional for the same
reason stages/pim/agent.py's mock is: no real Acceleo generation to fall
back to yet.
"""
from clients import validator_agent_client
from integration_runner.stages._validation import (
    attempt_scope_kwargs,
    persist_attempt,
    raise_if_invalid,
    reserved_attempt,
)

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
    # Reserved before validate_acceleo() runs, not after: validate_acceleo()
    # is what triggers AcceleoValidator's own real compiled .emtl write, and
    # that write needs the real stage+attempt path to land inside, not
    # beside it. See reserve_attempt_dir()'s own docstring. attempt_dir.name
    # alone is only "attempt_N" - the stage segment ("acceleo") has to be
    # forwarded separately too, or the compiled output would nest one level
    # too shallow (missing the stage folder entirely) and could even
    # collide with another stage's own same-numbered attempt under the same
    # run_id. Without a run_id there is no run tree to reserve an attempt
    # under, so persist_attempt() below still reserves its own in that
    # case, exactly as it always has. reserved_attempt() undoes the
    # reservation if validate_acceleo() raises before persist_attempt()
    # writes anything.
    run_id = context.get("run_id")
    with reserved_attempt(run_id, "acceleo") as attempt_dir:
        result = validator_agent_client.validate_acceleo(
            _MOCK_CONTENT, _FILENAME, run_id=run_id, **attempt_scope_kwargs("acceleo", attempt_dir),
        )
        persist_attempt(run_id or "unknown", "acceleo", _FILENAME, _MOCK_CONTENT, result, attempt_dir=attempt_dir)
        raise_if_invalid("acceleo", result)
    return _MOCK_CONTENT
