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

`attachments.files.list_reference_and_uploads(reference_example_path, uploads_dir) -> list[str]`
lists a service's real "file" attachment candidates for the common one-reference-file shape
(`atl_agent`/`acceleo_agent`, each with exactly one master example): that one file's own name, plus
every real uploaded file under `uploads_dir`, sorted. A service with a broader real listing (e.g.
`psm_agent`, scanning every known platform's own metamodel) keeps that logic itself rather than
forcing it through this shape.

## `prompt_config/` — generic, path-parameterized persistence

Every function takes a `config_dir: str | Path` the calling service supplies — this package owns
no service-specific path of its own, so the same functions work against a different directory per
caller (`psm_agent`'s own `PROMPT_CONFIG_DIR`, and `atl_agent`/`acceleo_agent`'s own). There is
exactly one config per name, not a family of named presets: a target platform's identity is
supplied as plain runtime data (a `"context"`-type attachment, resolved at call time), never as a
separately saved document, so onboarding a new platform never requires creating any new file here.
Module-qualified access: `from generation_toolkit.prompt_config import storage, history,
references, rendering, resolution, learned_constraints`.

A config on disk (`config_dir/{name}/default.json`, e.g. `generation/default.json`) has this
shape: `{"attachments": [...], "_version": str}`. There is no separate stored `system_prompt`
field: the config's own first `"text"` attachment IS the system message, derived at resolve time
(see `resolution.py` below), not persisted as its own field. A human builds and reorders it the
same way as any other attachment. `learned_constraints` is not part of this file at all: it lives
in its own separate `config_dir/{name}/constraints.json`, see `learned_constraints.py` below.

- **`storage.py`**: `load_config(config_dir, name) -> dict`: reads the live config, falling back
  to `default.default.json` if it doesn't exist yet. `save_config(config_dir, name, config,
  context_values, files_root) -> dict`: dry-run validates by resolving every attachment against a
  caller-supplied sample `context_values` first (raising `PromptConfigValidationError` naming the
  broken attachment, rather than persisting something broken), stamps a new sortable
  `config["_version"]`, atomically writes the live file, and writes an immutable copy to
  `config_dir/{name}/history/default.{version}.json`.
- **`history.py`**: `list_history` returns every real saved version id, newest first, with
  `SHIPPED_DEFAULT_VERSION` (the sentinel `"shipped"`) always appended as the oldest entry. The
  git-committed `default.default.json` is folded into this same timeline as one more version rather
  than a separate concept: `restore_version(config_dir, name, version, ...)` (copies a snapshot back
  over the live file via `save_config` again, a restore is just another save, never destructive)
  handles `version == SHIPPED_DEFAULT_VERSION` by reading the shipped default instead of a real
  history file, so "revert to default" is just `restore_version(..., SHIPPED_DEFAULT_VERSION)`, not
  a separate function or route. There is deliberately no "promote the current live version into the
  new shipped default" action any more: the shipped file is git-committed source, so updating what
  it means to be "the default" is a deliberate commit to that file, not an in-app button whose
  one-way effect was hard to convey and easy to click by accident. `diff_versions` accepts
  `SHIPPED_DEFAULT_VERSION` on either side too (a real structural diff: per attachment,
  added/removed/changed; a change to the system-message-role attachment already shows up as its own
  id in `attachments_changed`, no separate flag needed). None of this ever reads or writes
  `learned_constraints`: that list lives entirely outside the versioned file these functions operate
  on (see `learned_constraints.py` below), so a restore of the prompt's own text/attachments,
  shipped default included, can never discard it.
- **`references.py`**: `check_references(config_dir, name, context_values, files_root)`: the same
  resolution `save_config`'s dry-run uses, callable on demand against the *currently loaded*
  config, so a caller can detect drift since the last save (a metamodel file got renamed, a
  context key stopped being produced). `load_config` itself never fails this way — this is opt-in.
- **`rendering.py`** — `render_user_content(config, prompt) -> str`: the stage-owned hook for
  turning a resolved prompt dict into the LLM's actual user message, a customization point
  `run_with_retry`'s own `render_user_content` parameter already supports.
- **`resolution.py`**: `resolve_config(config, context_values, files_root) -> (config, parts)` is the
  real resolution step - deriving the system message from a config's own first `"text"` attachment,
  then resolving every remaining attachment - applied to a config the caller already has in hand.
  `resolve_for_call(config_dir, name, context_values, files_root)` is the same thing for the common
  case of a caller with no config of its own yet: it loads `name`'s saved config, then calls
  `resolve_config`. A live LLM call always goes through `resolve_for_call` (parts fed to
  `run_with_retry`); a preview capability resolving a UI's own current, unsaved draft calls
  `resolve_config` directly instead, so what a human sees previewed is the exact draft on screen, not
  whatever the last save happened to leave on disk. `render_prompt(...) -> dict` is a static,
  no-retry render (`resolve_for_call` plus `prompt_builder.build_prompt`) for a caller that wants
  "what would be sent" from the saved config with no retry loop at all (e.g. a knowledge-mode drift
  check).
- **`learned_constraints.py`**: `add_learned_constraints`/`remove_learned_constraint`/
  `load_constraints`: persist and read a config's own `learned_constraints` list from its own
  separate `config_dir/{name}/constraints.json`, entirely outside `storage.py`/`history.py`'s
  versioned file. Restoring any version of the prompt's own text/attachments, the shipped default
  included, never touches this file, so a permanent constraint stays permanent through every one of
  them.
  `load_constraints` migrates once from an older config that still carries `learned_constraints`
  embedded in the versioned file itself (this module's shape before it got its own store), so
  adopting this never silently drops a team's already-accumulated constraints. Distinct from a
  pipeline run's own ephemeral, per-run constraints list: a learned constraint is permanent,
  applies to every future run of that name once promoted, and promotion is always a single,
  explicit, human-confirmed action, never automatic capture of every typed correction.
  `resolution.resolve_for_call` merges the real, current list from this store into the config it
  hands back, so every real caller sees it without needing to know this file exists.

## `routes/` — the shared prompt-config/files/uploads HTTP surface

`psm_agent`, `atl_agent`, and `acceleo_agent` each expose the exact same real endpoint shapes over
`prompt_config/`/`attachments/` above (the full prompt-config CRUD surface, one available-files
listing, one attachment upload). This package builds each shape once, and every service's own
`routes/*.py` becomes the thin, service-specific adapter binding it to that service's own real
`config_dir`, `files_root`, and sample context values, rather than each service hand-writing the
same routing/error-translation logic three times over.

- **`prompt_config.py`** — `PromptConfigRouter(config_dir, files_root, context_for)`: builds the
  full prompt-config `APIRouter` (get/put, `history`, `diff`, `restore/{version}`, which also
  handles "revert to default" when called with `history.SHIPPED_DEFAULT_VERSION` as `version`,
  `check-references`, `learned-constraints` GET/POST/DELETE, `preview`) as bound methods a caller
  re-exports under the same names its own tests already import
  directly (e.g. `get_config_endpoint = router.get_config_endpoint`). `context_for(name) -> dict[str, str]`
  is the one real per-service variation: which `name`s are known and what sample context each one
  resolves against. `atl_agent`/`acceleo_agent` each have exactly one name; `psm_agent` has two
  (`"generation"`/`"comparison"`), so this is asked of the caller rather than assumed. `config_dir`
  and `files_root` are both zero-arg getters, not plain values, called fresh on every request: each
  service's own test suite isolates prompt-config reads/writes by monkeypatching its own
  `prompt_paths` module attributes for one test at a time (see each service's own `conftest.py`),
  which only works if this class re-reads them through that same module reference on every call,
  not once at construction time. Also exports `PromptConfigBody`/`LearnedConstraintsBody`/
  `RemoveLearnedConstraintBody`, the three request-body shapes every service's config uses
  identically.
- **`files.py`** — `build_files_router(list_available_files) -> (router, available_files_endpoint)`:
  the shared `GET /available-files` endpoint. What actually counts as an available file stays real,
  per-service logic (`attachments.files.list_reference_and_uploads` for a service with exactly one
  reference file, or a service's own broader listing like `psm_agent`'s multi-metamodel scan). This
  only wires whatever that returns into the one real endpoint shape.
- **`uploads.py`** — `build_uploads_router(uploads_dir, max_upload_bytes) -> APIRouter`: the shared
  `POST /attachment-uploads` endpoint over `attachments.uploads.save_uploaded_file`. Both arguments
  are zero-arg getters, for the same reason `config_dir` above is: a test that monkeypatches a
  service's own `ATTACHMENT_UPLOADS_DIR`/`MAX_UPLOAD_BYTES` needs the router to re-read it per
  request, not once at startup.

## Test

```bash
cd generation_toolkit
pytest
```

Mocks `clients.ai_layer_client.chat`, the only real network call this package makes — matches
this repo's other agent tests' convention of mocking the network boundary, not internal logic.
`prompt_config`'s and `attachments`' own tests use a `tmp_path` as `config_dir`/`files_root` — no
service-specific assumptions, proving the mechanism is genuinely generic.
