"""Generic, path-parameterized persistence for a UI-editable prompt
config: load/save (storage.py), version history/diff/restore
(history.py), and on-demand drift detection against the currently loaded
config (references.py). Every function takes a config_dir the calling
service supplies, so this package owns no service-specific path of its
own, the same functions work against a different directory per caller.

There is exactly one config per name (no per-platform preset variant):
a target platform's identity is supplied as plain runtime data (a
"context"-type attachment, resolved at call time), never as a separately
saved document, so onboarding a new platform never requires creating any
new file here.

Module-qualified access, matching this repo's own convention (see
generation_agent.py's own docstring): `from generation_toolkit.prompt_config
import storage, history, references`.
"""
