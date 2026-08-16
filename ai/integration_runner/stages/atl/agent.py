"""The ATL transformation stage. Still a placeholder: a plain LLM prompt
call, not yet a real ATL engine invocation. See stages/__init__.py's own
docstring for why every stage gets its own folder.
"""
from clients import ai_layer_client
from integration_runner.stages._shared import constraints_note

_SYSTEM_PROMPT = (
    "You are the MDDOAI ATL transformation agent. Given a PSM-level description, describe "
    "the ATL (ATLAS Transformation Language) transformation rules needed to map the "
    "platform-independent model to this PSM: name the rules, and describe their source "
    "and target patterns and the mapping logic between them."
)


def atl_stage(context: dict) -> str:
    psm_output = context.get("psm_output", "")
    user_content = psm_output + constraints_note(context, "atl")
    messages = [
        {"role": "system", "content": _SYSTEM_PROMPT},
        {"role": "user", "content": user_content},
    ]
    return ai_layer_client.chat(messages, model=context.get("model"))["content"]
