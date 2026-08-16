"""A small helper shared by every placeholder stage's own agent.py (pim,
psm, atl, acceleo, generation). Not shared with the docs stage: docs_stage
folds corrections into retrieval's own `hint` parameter instead, a
different real mechanism (see stages/docs/agent.py's own docstring).
"""


def constraints_note(context: dict, stage: str) -> str:
    """A correction, previously recorded via add_constraint(stage, ...), as
    a note appended to this stage's prompt. Empty string if none."""
    constraints = context.get("constraints", {}).get(stage, [])
    if not constraints:
        return ""
    bullet_list = "\n".join(f"- {c}" for c in constraints)
    return f"\n\nApply these corrections from prior review:\n{bullet_list}"
