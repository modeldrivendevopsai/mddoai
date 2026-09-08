"""Generic, path-parameterized persistence for a UI-editable prompt
config: load/save (storage.py), which named preset applies to a given
platform (presets.py), version history/diff/restore (history.py), and
on-demand drift detection against the currently loaded config
(references.py). Every function takes a config_dir the calling service
supplies, so this package owns no service-specific path of its own, the
same functions work against a different directory per caller.

Module-qualified access, matching this repo's own convention (see
generation_agent.py's own docstring): `from generation_toolkit.prompt_config
import storage, presets, history, references`.
"""
