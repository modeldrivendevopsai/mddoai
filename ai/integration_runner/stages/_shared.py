"""A small helper for a placeholder stage's own agent.py that still calls
an LLM with a corrections-aware prompt — only stages/generation/agent.py
today; pim/atl/acceleo return fixed mock content instead now (see each of
their own agent.py docstrings for why a correction has nothing to act on
there). psm is real and does take a correction, but folds it into its own
request to psm_agent instead of using this helper (see stages/psm/agent.py's
own docstring). Not shared with the docs stage either: docs_stage folds
corrections into retrieval's own `hint` parameter instead, a different real
mechanism (see stages/docs/agent.py's own docstring).
"""


def constraints_note(context: dict, stage: str) -> str:
    """A correction, previously recorded via add_constraint(stage, ...), as
    a note appended to this stage's prompt. Empty string if none."""
    constraints = context.get("constraints", {}).get(stage, [])
    if not constraints:
        return ""
    bullet_list = "\n".join(f"- {c}" for c in constraints)
    return f"\n\nApply these corrections from prior review:\n{bullet_list}"
