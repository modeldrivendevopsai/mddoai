"""The Acceleo code-generation template stage. Still a placeholder: a plain
LLM prompt call, not yet a real Acceleo engine invocation. See
stages/__init__.py's own docstring for why every stage gets its own folder.
"""
from clients import ai_layer_client
from integration_runner.stages._shared import constraints_note

_SYSTEM_PROMPT = (
    "You are the MDDOAI Acceleo template agent. Given a description of ATL transformation "
    "rules, describe the Acceleo code-generation template needed to turn the transformed "
    "model into real pipeline configuration files: the template's structure, its key "
    "generation blocks, and the output files it targets."
)


def acceleo_stage(context: dict) -> str:
    atl_output = context.get("atl_output", "")
    user_content = atl_output + constraints_note(context, "acceleo")
    messages = [
        {"role": "system", "content": _SYSTEM_PROMPT},
        {"role": "user", "content": user_content},
    ]
    return ai_layer_client.chat(messages, model=context.get("model"))["content"]
