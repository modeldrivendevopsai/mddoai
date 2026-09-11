# generation_toolkit

Shared, stage-agnostic building blocks for a "build a prompt, call the LLM, validate, retry"
agent. Not a deployed service — no Dockerfile, no port, imported directly as a Python package by
whichever service's own stage agent needs this shape, the same way `clients/` (the other
not-itself-a-service folder under `ai/`) is imported for outbound HTTP calls.

`psm_agent`'s Generation Agent (`psm_agent/generation.py`) is the first real caller. Extracted
ahead of a second real consumer, on a stated, concrete direction, not a speculative one:
`integration_runner`'s remaining placeholder stages (`pim`, `atl`, `acceleo`, `generation`) are
still plain single-shot LLM prompts today, but whichever gets a real implementation next should
reuse this rather than rebuilding the same prompt-assembly and regenerate-loop pattern from
scratch.

## `prompt_builder.build_prompt(parts, constraints=None)`

Pure, deterministic assembly: `parts` (an ordered `dict[str, str]`, e.g.
`{"pim_ecore": ..., "psm_docs": ..., "psm_example": ...}`) pass through unchanged, plus a
`"constraints"` key rendered as a bullet list. No LLM call, no I/O — callers gather their own part
content (reading a file, formatting docs, folding in grounding text, etc.) before calling this.
The returned dict's shape (one key per part, in order, plus `"constraints"`) is also what a
generic prompt-tab UI can render directly, one tab per key (see
`chat-ui/.../PsmStagePanel.tsx`'s own `Tabs` usage).

## `generation_agent.run_with_retry(system_prompt, parts, ...)`

```python
result = run_with_retry(
    system_prompt,
    parts,                          # dict[str, str], passed straight to build_prompt()
    constraints=None,               # list[str] | None — corrections carried in from a prior run
    validate_fn=None,                # Callable[[str], dict] | None — omit for a single-shot call
    root_cause_fn=_default_root_cause,       # Callable[[dict], list[str]] — validation result -> new constraints
    render_user_content=_default_render,     # Callable[[dict[str,str]], str] — prompt dict -> the LLM's user message
    max_regenerate_rounds=DEFAULT_MAX_REGENERATE_ROUNDS,  # 6
    model=None,
)
# -> {"output": str, "prompt": dict, "validation": dict | None, "rounds": int}
```

Loop: build the prompt, call `ai-layer` via `clients/ai_layer_client.py`, strip a markdown code
fence if the model added one anyway (real models routinely do despite being told not to). If
`validate_fn` was given, call it on the output; if invalid and rounds remain, turn the result into
new constraints, one per validator issue (`root_cause_fn`, default: every issue, each prefixed
`"Fix: "`), skipping any already recorded from an earlier round, and rebuild the prompt for
another round. Bounded, so a persistently-invalid generation fails closed (returns its last,
still-invalid attempt) instead of looping forever.

`validation` is `None` when `validate_fn` was never given — a stage with no real validator yet
just omits it and gets a plain single-shot call, no code path change needed once that stage grows
a real validator to check against later. `prompt` in the result always reflects the round that
actually produced `output`, so a caller showing "what was fed to the model" is always showing the
truth, not a stale first attempt.

## `attachments/` — turning a UI-editable list into prompt parts

An **attachment** is a plain dict a UI builds and a human edits: `{"id", "name", "type", ...}`,
`type` one of:

- `"text"` — a `"content"` string, literal, edited directly in the UI.
- `"file"` — a `"path"` string, resolved against a caller-supplied root directory.
- `"context"` — a `"key"` string, looked up in the caller's own `context_values` dict (one of the
  real pipeline values already flowing into whichever stage is calling this, e.g. `"pim_ecore"`,
  never an arbitrary string a config author invents).

`attachments.resolve.resolve_attachments(attachments, context_values, files_root) ->
dict[str, str]` turns that list into the ordered parts dict `prompt_builder.build_prompt()`
expects, in the list's own order (also the order a UI's card list and the generated prompt's own
tabs should show). Raises `UnknownContextKeyError` naming the offending attachment on an unknown
context key, rather than silently dropping it — a config that can't fully resolve shouldn't
quietly produce a partial prompt.

`attachments.files.resolve_file_attachment(path, files_root) -> str` reads a `"file"` attachment's
real content, rejecting an absolute path, a disallowed character, or a resolved path outside
`files_root` (the real path-traversal exposure a user-editable "attach a file" feature creates).
`attachments.files.validate_path_segment(segment) -> str` is the same character-class check for a
single path segment with no `/` allowed at all — reused for a `run_id`/`stage`/`attempt` route
parameter, not just a file attachment's path.

## `prompt_config/` — generic, path-parameterized persistence

Every function takes a `config_dir: str | Path` the calling service supplies — this package owns
no service-specific path of its own, so the same functions work against a different directory per
caller (`psm_agent`'s own `PROMPT_CONFIG_DIR`, and later ATL/Acceleo's own, once each is real).
Module-qualified access: `from generation_toolkit.prompt_config import storage, presets, history,
references, rendering, resolution, learned_constraints`.

A config on disk (`config_dir/{name}/{preset}.json`, e.g. `generation/default.json`) has this
shape: `{"attachments": [...], "learned_constraints": [str, ...], "label": str | None,
"platform_hints": [str, ...], "_version": str}`. There is no separate stored `system_prompt`
field: the config's own first `"text"` attachment IS the system message, derived at resolve time
(see `resolution.py` below), not persisted as its own field. A human builds and reorders it the
same way as any other attachment.

- **`storage.py`** — `load_config(config_dir, name, preset="default") -> dict`: reads the live
  config, falling back to `{preset}.default.json`, then `default.default.json` if neither the live
  nor the platform-specific default exists yet. `save_config(config_dir, name, preset, config,
  context_values, files_root) -> dict`: dry-run validates by resolving every attachment against a
  caller-supplied sample `context_values` first (raising `PromptConfigValidationError` naming the
  broken attachment, rather than persisting something broken), stamps a new sortable
  `config["_version"]`, atomically writes the live file, and writes an immutable copy to
  `config_dir/{name}/history/{preset}.{version}.json`.
- **`presets.py`** — `list_presets`/`list_preset_metadata` enumerate every preset with a real file
  on disk (`"default"` always included); `preset_metadata` reads one preset's `label` and
  `platform_hints`. `resolve_preset(platform_description, presets) -> str` matches a free-text
  platform description against each preset's own `platform_hints` metadata (never its storage id,
  so a preset's identity stays platform-agnostic), falling back to `"default"`.
- **`history.py`** — `list_history`, `diff_versions` (a real structural diff: per attachment,
  added/removed/changed; a change to the system-message-role attachment already shows up as its
  own id in `attachments_changed`, no separate flag needed), `restore_version` (copies a
  snapshot back over the live file via `save_config` again — a restore is just another save, never
  destructive), `revert_to_default` (same, against the shipped default). `promote_live_to_default`
  copies the current live config over the shipped `{preset}.default.json` — a deliberate, explicit
  action distinct from an ordinary save, since the default file is git-committed and "updating the
  default" means preparing it to be committed as the new baseline.
- **`references.py`** — `check_references(config_dir, name, preset, context_values, files_root)`:
  the same resolution `save_config`'s dry-run uses, callable on demand against the *currently
  loaded* config, so a caller can detect drift since the last save (a metamodel file got renamed, a
  context key stopped being produced). `load_config` itself never fails this way — this is opt-in.
- **`rendering.py`** — `render_user_content(config, prompt) -> str`: the stage-owned hook for
  turning a resolved prompt dict into the LLM's actual user message, a customization point
  `run_with_retry`'s own `render_user_content` parameter already supports.
- **`resolution.py`** — `resolve_for_call(config_dir, name, preset, context_values, files_root) ->
  (config, parts)` and `render_prompt(...) -> dict`: the "load a config, resolve its attachments"
  sequence every real caller needs, whether for an actual LLM call (`resolve_for_call`, parts fed
  to `run_with_retry`) or a static preview/knowledge-mode render with no retry loop
  (`render_prompt`, calls `prompt_builder.build_prompt` directly).
- **`learned_constraints.py`** — `add_learned_constraints`/`remove_learned_constraint`: persist a
  change to a config's own `learned_constraints` list through `storage.save_config` (versioned,
  revertible, same as any other edit). Distinct from a pipeline run's own ephemeral, per-run
  constraints list: a learned constraint is permanent, applies to every future run of that
  (mode, preset) once promoted, and promotion is always a single, explicit, human-confirmed action,
  never automatic capture of every typed correction.

## Test

```bash
cd generation_toolkit
pytest
```

Mocks `clients.ai_layer_client.chat`, the only real network call this package makes — matches
this repo's other agent tests' convention of mocking the network boundary, not internal logic.
`prompt_config`'s and `attachments`' own tests use a `tmp_path` as `config_dir`/`files_root` — no
service-specific assumptions, proving the mechanism is genuinely generic.
