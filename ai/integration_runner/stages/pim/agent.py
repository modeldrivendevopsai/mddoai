"""The PIM (Platform-Independent Model) stage. Still a placeholder in one
real sense, no real PIM *instance* extraction pipeline exists yet, so this
ignores a run's own real SWArch/serialization input, the same way it always
has, but its output is no longer a one-class mock standing in for a real
metamodel. It now returns MDDOAI's own real, fixed PIM metamodel (see
meta_models/com.mddoai.metamodel.pim's own model), the same real metamodel
psm_agent's own comparison mode already treats as a known reference, and
validates it for real against validator-agent's /validate/ecore, exactly as
before. See stages/__init__.py's own docstring for how a stage like this
gets replaced with a real agent later, matching stages/docs/agent.py's own
history. Ignores its input context for the same reason it always did:
corrections/constraints have nothing to act on against fixed content, until
a real SWArch-driven PIM extraction replaces this.

Returns (content, extra), the documented exception to the plain-string stage-agent
contract psm/atl/acceleo already use: extra carries "attempt", the name of the attempt
the call was recorded as, which a failure carries on its own extra too, so the run's
events say which attempt each call was.
"""
from pathlib import Path

from clients import validator_agent_client
from clients.required_env import required_env
from integration_runner.stages._validation import attach_attempt, persist_attempt, raise_if_invalid

_FILENAME = "pimMM.ecore"

# The project's own real, git-committed PIM metamodel - read-only, a real
# MDE-engine *data* file this service doesn't own, not Java/Eclipse *code*
# (see ai/CLAUDE.md's folder-boundaries section for why that distinction is
# what actually licenses this read). Required, no default: Docker Compose
# bind-mounts the file and sets this to where it is.
PIM_METAMODEL_PATH = Path(required_env("PIM_METAMODEL_PATH"))


def pim_stage(context: dict) -> tuple[str, dict]:
    content = PIM_METAMODEL_PATH.read_text(encoding="utf-8")
    result = validator_agent_client.validate_ecore(content, _FILENAME)
    attempt = persist_attempt(context.get("run_id", "unknown"), "pim", _FILENAME, content, result)
    try:
        raise_if_invalid("pim", result)
    except RuntimeError as failure:
        attach_attempt(failure, attempt)
        raise
    return content, {"attempt": attempt.name}
