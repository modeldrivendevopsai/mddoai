"""The PIM (Platform-Independent Model) stage. Still a placeholder: a plain
LLM prompt call, not yet the real MDE toolchain (an actual PIM extraction
pipeline). See stages/__init__.py's own docstring for why every stage gets
its own folder, and for how this gets replaced with a real agent later
(matching stages/docs/agent.py's own history).
"""
from clients import ai_layer_client
from integration_runner.stages._shared import constraints_note

_SYSTEM_PROMPT = (
    "You are the MDDOAI PIM (Platform-Independent Model) agent. Given a platform's real "
    "documentation, produce a clear PIM-level description: express the platform's CI/CD "
    "concepts (jobs, stages, triggers, artifacts, agents/runners) in MDDOAI's "
    "platform-independent metamodel terms, without committing to any one platform's "
    "specific syntax yet. Be precise and structured."
)


def pim_stage(context: dict) -> str:
    # serialization_output (the labeled, structured markdown) is what a live
    # run actually has once "serialization" precedes "pim" — strictly more
    # useful than the raw docs_output it's built from. docs_output is the
    # fallback for a caller (or a unit test) that invokes pim_stage directly
    # without a serialization stage having run; platform_description is the
    # last-resort fallback below that.
    platform_description = context.get("platform_description", "")
    primary_input = context.get("serialization_output") or context.get("docs_output") or platform_description
    user_content = primary_input + constraints_note(context, "pim")
    messages = [
        {"role": "system", "content": _SYSTEM_PROMPT},
        {"role": "user", "content": user_content},
    ]
    return ai_layer_client.chat(messages, model=context.get("model"))["content"]
