"""Persistence for a UI-editable prompt config: load/save (storage.py),
version history/diff/restore (history.py), the permanent learned constraints
(learned_constraints.py), and on-demand drift detection against the currently
loaded config (references.py). The journal keeps it all. Every function takes
the agent's own name (such as "psm") that the journal keeps its configs under,
so this package owns no service-specific path of its own, the same functions
work against a different agent per caller.

There is exactly one config per name (no per-platform preset variant):
a target platform's identity is supplied as plain runtime data (a
"context"-type attachment, resolved at call time), never as a separately
saved document, so onboarding a new platform never requires creating any
new file here.

Module-qualified access, matching this repo's own convention (see
generation_agent.py's own docstring): `from generation_toolkit.prompt_config
import storage, history, references`.
"""
