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

Persists this stage's own real output (win or lose) via
stages/_validation.py's persist_attempt(), matching atl_stage/acceleo_stage's
own convention - unlike those two, this stage has no retry loop of its own,
so there is exactly one real attempt per real run of this stage, but the
result (the actual generated CI/CD config, and the intermediate PSM model
instance ATL execution produced along the way) is exactly the kind of real
artifact a human debugging a run needs to find on disk afterward, not just
in this run's own transient in-memory event log. Still raises on a real
execution failure (a validated-but-not-actually-runnable ATL/Acceleo pair -
exactly the gap this stage's own real execution exists to expose, see
execution_agent_client's own AgentServiceError): the failure is persisted
first (so it's on record), then re-raised, reported as a call_failed event
by pipeline.py's own run_stage(), the same honest way stages/docs/agent.py's
own real failures already do.

Returns (output, extra) on success, matching psm/atl/acceleo's own
documented exception to the plain-string stage-agent contract (see
pipeline.py's run_stage() docstring): extra carries "psm_instance" (the
real intermediate model ATL execution produced) and "generated_files" (the
real {path: content} map AcceleoExecutor produced, before it was joined
into output) - the chat-ui's own "what generation actually produced" needs
both, not just the joined text.
"""
import os
import re
from pathlib import Path

from clients import execution_agent_client
from integration_runner.stages._validation import persist_attempt, reserved_attempt

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


# The primary artifact persist_attempt() writes for this stage, matching
# atl_stage's "atl.atl"/acceleo_stage's "generate.mtl" own convention - the
# exact text this stage returns to its caller (one real file's content, or
# the joined multi-file text below), not necessarily itself one valid YAML
# document on a multi-file generation. The intermediate PSM instance and
# every real individual generated file are written alongside it (see
# gen_stage() below), so nothing this stage actually produced is only ever
# a name inside this one joined file.
_FILENAME = "output.yaml"


def _persist_failure(run_id: str | None, attempt_dir: Path | None, message: str) -> None:
    if attempt_dir is not None:
        persist_attempt(
            run_id or "unknown",
            "generation",
            "error.txt",
            message,
            {"valid": False, "issues": [{"severity": "ERROR", "message": message}]},
            attempt_dir=attempt_dir,
        )


def gen_stage(context: dict) -> str:
    atl_source = context.get("atl_output", "")
    mtl_source = context.get("acceleo_output", "")
    target_ecore = context.get("psm_output", "")
    pim_instance = PIM_SAMPLE_INSTANCE_PATH.read_text(encoding="utf-8")
    run_id = context.get("run_id")

    # Reserved before either real execution call runs, the same reasoning
    # as atl_stage/acceleo_stage's own reserved_attempt() use: if either
    # call raises, the except block below persists a real failure record
    # into this same attempt directory before re-raising, so
    # reserved_attempt()'s own cleanup (which only removes a directory that
    # never got a result.json) leaves it in place.
    #
    # ATL and Acceleo execution are two separate try/except blocks, not one
    # covering both: pipeline.py's own run_stage() turns any raised
    # exception into a call_failed event carrying only {"error": str(e)},
    # with no other field for which phase actually failed - a real human
    # deciding whether to fork back to atl or back to acceleo (see this
    # stage's own panel) needs that distinction, and the only place left to
    # put it is the error message's own text. A failure in Acceleo also
    # keeps the real psm_instance ATL already produced (real, valid,
    # genuinely not the problem) on disk, instead of discarding it - a
    # human debugging why Acceleo failed needs to see the real model it was
    # actually given, not just the error.
    # Parsed before reserved_attempt() ever runs: a malformed atl_output
    # (missing its own "create OUT : <Name> from IN : PIM;" declaration) is
    # a pre-flight problem with the ATL source's own text, not a real
    # execution failure - it never reaches execution-agent at all, so it
    # keeps its own real ValueError type and message, not wrapped as an
    # "ATL execution failed" RuntimeError, and there's no real attempt to
    # persist for a call that never happened.
    output_model_name = _output_model_name(atl_source)

    with reserved_attempt(run_id, "generation") as attempt_dir:
        try:
            psm_instance = execution_agent_client.execute_atl(
                atl_source, pim_instance, target_ecore, output_model_name
            )
        except Exception as e:
            message = f"ATL execution failed: {e}"
            _persist_failure(run_id, attempt_dir, message)
            raise RuntimeError(message) from e

        try:
            generated_files = execution_agent_client.execute_acceleo(mtl_source, psm_instance, target_ecore)
        except Exception as e:
            if attempt_dir is not None:
                (attempt_dir / "psm_instance.xmi").write_text(psm_instance, encoding="utf-8")
            message = f"Acceleo execution failed (ATL succeeded): {e}"
            _persist_failure(run_id, attempt_dir, message)
            # psm_instance also rides along on the raised exception itself
            # (pipeline.py's own run_stage_async worker threads a raised
            # exception's "extra" dict into the real call_failed event's
            # own data, alongside "error") - not just persisted to disk,
            # since a human looking at the failed run in the chat-ui, not
            # digging through the real attempt directory on disk, still
            # needs to see the real, valid model ATL already produced
            # before deciding whether the real bug is in Acceleo's own
            # template or further back.
            failure = RuntimeError(message)
            failure.extra = {"psm_instance": psm_instance}
            raise failure from e

        if len(generated_files) == 1:
            output = next(iter(generated_files.values()))
        else:
            # A real template generating more than one file is rare (every
            # platform this project has generated for so far produces one
            # real CI/CD YAML file) but not impossible - each file's own
            # name keeps multiple outputs distinguishable rather than
            # silently concatenated.
            output = "\n\n".join(f"# {name}\n{content}" for name, content in generated_files.items())

        if attempt_dir is not None:
            # The intermediate artifact and every real individual generated
            # file, alongside (not instead of) the primary artifact
            # persist_attempt() writes below - a human debugging a run
            # needs the real PSM instance ATL execution actually produced,
            # not just the final text.
            (attempt_dir / "psm_instance.xmi").write_text(psm_instance, encoding="utf-8")
            for name, content in generated_files.items():
                # A real generated file name can carry its own subdirectory
                # (e.g. Woodpecker's own real ".woodpecker/pipeline.yaml"
                # convention, confirmed for real: writing straight to
                # attempt_dir/name failed with "No such file or directory"
                # since nothing had created attempt_dir/.woodpecker/ yet) -
                # create whatever parent directory it needs first. resolve()
                # + a real containment check guards against a name that
                # tries to escape attempt_dir entirely (e.g. "../../etc"),
                # matching AcceleoExecutorCli's own identical guard on the
                # same real, model-controlled file names.
                target = (attempt_dir / name).resolve()
                if not target.is_relative_to(attempt_dir.resolve()):
                    raise ValueError(f"generated file path escapes the attempt directory: {name!r}")
                target.parent.mkdir(parents=True, exist_ok=True)
                target.write_text(content, encoding="utf-8")

        persist_attempt(
            run_id or "unknown",
            "generation",
            _FILENAME,
            output,
            {"valid": True, "issues": []},
            attempt_dir=attempt_dir,
        )
        # psm_instance/generated_files alongside the joined output text,
        # the same (output, extra) exception psm/atl/acceleo's own stage
        # agents already use (see pipeline.py's run_stage() docstring) -
        # the chat-ui's own "what generation actually produced" needs the
        # real intermediate model and each real file's own name, not just
        # the joined text every other stage's plain-string contract covers.
        return output, {"psm_instance": psm_instance, "generated_files": generated_files}
