"""The final generation-summary stage. Still a placeholder: a plain LLM
prompt call tying together the prior stages' outputs, not yet real
generated CI/CD config. See stages/__init__.py's own docstring for why
every stage gets its own folder.
"""
from clients import ai_layer_client
from integration_runner.stages._shared import constraints_note

_SYSTEM_PROMPT = (
    "You are the MDDOAI generation summary agent. Given the PSM description, the ATL "
    "transformation rules, and the Acceleo template plan produced in the prior stages, "
    "produce a final, concise summary of the full pipeline generation plan, from the "
    "original platform input through to the generated CI/CD configuration."
)


def gen_stage(context: dict) -> str:
    user_content = (
        f"PSM description:\n{context.get('psm_output', '')}\n\n"
        f"ATL transformation rules:\n{context.get('atl_output', '')}\n\n"
        f"Acceleo template plan:\n{context.get('acceleo_output', '')}"
    ) + constraints_note(context, "generation")
    messages = [
        {"role": "system", "content": _SYSTEM_PROMPT},
        {"role": "user", "content": user_content},
    ]
    return ai_layer_client.chat(messages, model=context.get("model"))["content"]
