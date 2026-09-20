"""Renders a resolved prompt (prompt_builder.build_prompt()'s own output)
into the actual text an LLM call's user message carries, labeling each
section with that attachment's own human-readable "name" from the config
it came from, rather than its raw id, keeping the LLM-facing text readable
the same way a fixed, hand-written prompt would, without the caller
needing to know in advance which attachments a given config actually has.

Shared by every real caller of a config-driven prompt (psm_agent's
generation.py and comparison.py, both real callers today, not a
speculative extraction ahead of one), and reused directly by a "preview"
capability that shows a human this exact text before spending a real LLM
call.
"""


def render_user_content(config: dict, prompt: dict[str, str]) -> str:
    """`config` is the loaded prompt config (for its own "attachments" list,
    read only for their id -> name mapping here). `prompt` is
    prompt_builder.build_prompt()'s own output, the same ordered dict
    resolve_attachments() plus build_prompt() produce."""
    names_by_id = {attachment["id"]: attachment["name"] for attachment in config["attachments"]}
    sections = [f"{names_by_id.get(key, key)}:\n{value}" for key, value in prompt.items() if key != "constraints"]
    content = "\n\n".join(sections)
    if prompt.get("constraints"):
        content += f"\n\nApply these corrections from prior rounds:\n{prompt['constraints']}"
    return content
