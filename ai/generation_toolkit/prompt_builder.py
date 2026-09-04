"""Generic, stage-agnostic prompt assembly: given labeled content parts and a
running list of constraints, produces a structured prompt dict (the parts,
unchanged, plus "constraints" rendered as a bullet list) - the same shape a
generation agent's regenerate loop rebuilds each round, and the shape a
generic prompt-tab UI can render directly (one tab per key, see
chat-ui/.../PsmStagePanel.tsx's own Tabs usage). No LLM call, no I/O of its
own - callers gather their own part content (reading a file, formatting
docs, folding in grounding text, etc.) before calling this.
"""


def build_prompt(parts: dict[str, str], constraints: list[str] | None = None) -> dict[str, str]:
    """`parts` order is preserved (dicts are ordered) — callers control tab
    order this way. `constraints` defaults to an empty list, rendering as ""
    rather than a key that's simply absent, so a caller/UI can always safely
    read prompt["constraints"]."""
    prompt = dict(parts)
    prompt["constraints"] = "\n".join(f"- {c}" for c in (constraints or []))
    return prompt
