"""Stage-agnostic attachment resolution: turns a persisted, UI-editable
list of named "attachment" dicts (freeform text, a real on-disk file, or a
real pipeline-context value) into the plain parts dict
generation_toolkit.prompt_builder.build_prompt() already expects. Nothing
in this package knows about psm, atl, or acceleo specifically, only the
three attachment shapes any of them can use, so a second real caller
reuses it unchanged.

Module-qualified access, matching this package's own established
convention (see generation_agent.py's own docstring): `from
generation_toolkit.attachments import resolve, files`, then
`resolve.resolve_attachments(...)` / `files.resolve_file_attachment(...)`.
"""
