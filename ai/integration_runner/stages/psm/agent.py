"""The PSM (Platform-Specific Model) stage. Still a placeholder: a plain
LLM prompt call, not yet the real MDE toolchain. See stages/__init__.py's
own docstring for why every stage gets its own folder.
"""
from clients import ai_layer_client
from integration_runner.stages._shared import constraints_note

_SYSTEM_PROMPT = (
    "You are the MDDOAI PSM (Platform-Specific Model) agent. Given a PIM-level description "
    "of a CI/CD platform, produce a clear PSM-level description: express the same concepts "
    "(jobs, stages, triggers, artifacts, agents/runners) in MDDOAI's platform-specific "
    "metamodel terms. Be precise and structured."
)


def psm_stage(context: dict) -> str:
    # pim_output is what a live run actually has once "pim" precedes "psm";
    # docs_output/platform_description are fallbacks for a caller (or a unit
    # test) that invokes psm_stage directly without a pim stage having run.
    platform_description = context.get("platform_description", "")
    primary_input = context.get("pim_output") or context.get("docs_output") or platform_description
    user_content = primary_input + constraints_note(context, "psm")
    messages = [
        {"role": "system", "content": _SYSTEM_PROMPT},
        {"role": "user", "content": user_content},
    ]
    return ai_layer_client.chat(messages, model=context.get("model"))["content"]
