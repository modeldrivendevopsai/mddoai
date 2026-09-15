"""The final generation stage: actually runs this run's own real,
generated ATL transformation and Acceleo template against a real PIM
model instance, via execution_agent, to produce the real generated CI/CD
configuration - no longer a placeholder LLM summary of the prior stages.

context["atl_output"]/context["acceleo_output"] are what a live run
actually has once "atl"/"acceleo" precede "generation" - the real ATL/
Acceleo source those stages generated and validated (compiled) against
validator-agent, but never actually ran until now. context["psm_output"]
is the target platform's own real PSM metamodel (Ecore text), needed both
as ATL's own output metamodel and Acceleo's own input metamodel. No
fallback for any of the three: there's no reasonable stand-in for a
caller that skips straight to generation, matching every other real
stage's own "context has what a real preceding run produced" assumption.

Raises on a real execution failure (a validated-but-not-actually-runnable
ATL/Acceleo pair - exactly the gap this stage's own real execution exists
to expose, see execution_agent_client's own AgentServiceError) rather than
swallowing it: unlike atl_stage/acceleo_stage, this stage has no retry loop
or attempt-persistence of its own to protect, so a real failure surfaces
the same honest way stages/docs/agent.py's own real failures already do
(a raised exception, reported as a call_failed event by pipeline.py's own
run_stage()).
"""
import os
import re
from pathlib import Path

from clients import execution_agent_client

# A real, rich PIM model instance (11 jobs, exercising all of MDDOAI's own
# PIM concepts), this stage's own real input to the real ATL execution it
# runs - main/'s own real Java test fixture, a temporary stand-in until a
# real SWArch-driven PIM extraction replaces it here. Env-overridable so a
# container deployment can bind-mount a real, git-committed file here
# instead, matching stages/pim/agent.py's own PIM_METAMODEL_PATH
# convention - this file's own path is
# ai/integration_runner/stages/generation/agent.py, the same four
# directories under ai/'s own parent that pim/agent.py's default resolves
# from.
PIM_SAMPLE_INSTANCE_PATH = Path(
    os.environ.get(
        "PIM_SAMPLE_INSTANCE_PATH",
        str(
            Path(__file__).resolve().parents[4]
            / "main" / "src" / "test" / "resources" / "testCases" / "execution" / "gitlab" / "input.pimmm"
        ),
    )
)

# atl_agent's own generation prompt forces every generated ATL's output
# model name into this exact shape: "create OUT : <ModelName> from IN :
# PIM;", with a matching "-- @nsURI <ModelName>=<platform>MM=..." header
# line just above it. Parsed directly out of the real generated source
# rather than reconstructed from a platform name string this stage doesn't
# otherwise have: the ATL source itself is the one place that name is
# declared, and it must match exactly for AtlExecutor's own output model
# to resolve.
_OUTPUT_MODEL_NAME_PATTERN = re.compile(r"create\s+OUT\s*:\s*(\w+)\s+from")

# ATL's own line-comment marker (matching this project's real, existing
# .atl files' own "-- @nsURI ..."/"-- Main pipeline transformation"-style
# lines). Stripped before searching for the real output model name so a
# comment merely mentioning the "create OUT : ... from" convention (in an
# LLM-generated header, say) can never be mistaken for the actual
# declaration.
_ATL_LINE_COMMENT_PATTERN = re.compile(r"--.*$", re.MULTILINE)


def _output_model_name(atl_source: str) -> str:
    code_only = _ATL_LINE_COMMENT_PATTERN.sub("", atl_source)
    match = _OUTPUT_MODEL_NAME_PATTERN.search(code_only)
    if not match:
        raise ValueError(
            "Could not find a real output model name in the generated ATL source "
            '(expected "create OUT : <Name> from IN : PIM;")'
        )
    return match.group(1)


def gen_stage(context: dict) -> str:
    atl_source = context.get("atl_output", "")
    mtl_source = context.get("acceleo_output", "")
    target_ecore = context.get("psm_output", "")
    pim_instance = PIM_SAMPLE_INSTANCE_PATH.read_text(encoding="utf-8")

    psm_instance = execution_agent_client.execute_atl(
        atl_source, pim_instance, target_ecore, _output_model_name(atl_source)
    )
    generated_files = execution_agent_client.execute_acceleo(mtl_source, psm_instance, target_ecore)

    if len(generated_files) == 1:
        return next(iter(generated_files.values()))
    # A real template generating more than one file is rare (every
    # platform this project has generated for so far produces one real
    # CI/CD YAML file) but not impossible - each file's own name keeps
    # multiple outputs distinguishable rather than silently concatenated.
    return "\n\n".join(f"# {name}\n{content}" for name, content in generated_files.items())
