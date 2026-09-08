# psm_agent

The real `psm` pipeline stage's whole capability, `POST /psm` (`psm_flow.py`'s `run()`): routes
between two distinct real capabilities depending on whether the target platform already has a
real PSM metamodel checked into `meta_models/`.

- **No existing metamodel (new platform)** → the **Generation Agent** (`generation.py`'s
  `generate()`): resolves a real, UI-editable prompt config (see "Prompt configuration" below) for
  the target platform, folds in real PIM-concept grounding, and runs the result through the shared
  `generation_toolkit` package's `run_with_retry()` (a stage-agnostic "build a prompt, call the LLM,
  validate, retry" toolkit, not specific to PSM — see `generation_toolkit/README.md`), asking
  `ai-layer` to generate a new `.ecore` and checking it against `validator_agent`'s real
  `/validate/ecore` in **codegen** mode, not just reflective, so a genuinely new metamodel's own
  generated Java classes get checked too, as the toolkit's `validate_fn`. On failure, the
  validator's first issue becomes one new constraint and the prompt is rebuilt for another round,
  bounded, so a platform whose docs genuinely can't produce a loadable `.ecore` fails closed
  instead of looping forever. Grounding (pulling relevant PIM-concept context into the prompt)
  reuses `pim_agent`'s existing `ground()`/`concepts()`, there is no separate RAG agent yet (a
  documented Phase 1 plan, not built here). Every round's own real compiled Ecore classes are
  kept, not deleted after the check (see [validator_agent's own
  README](../validator_agent/README.md#setup)). This stage forwards `stage`/`attempt` (see
  `POST /psm` below) so all of them nest inside the one attempt directory
  `integration_runner`'s own `psm_stage` reserved for this call, instead of scattering as
  orphaned siblings under the run root.
- **An existing metamodel (known platform)** → the **Knowledge Agent** (`comparison.py`'s
  `compare()`, unchanged): a real LLM comparison of the docs against the existing `.ecore` to
  find drift (missing/outdated concepts). Informational only — a gap is surfaced alongside the
  existing metamodel's own unchanged content, never an automatic edit.

`psm_flow.run()` is the first real caller `compare()` has ever had in the live pipeline; before
this it was callable (`POST /compare`, still available standalone) but not wired into any real
run.

Own container, own port (8040). Promoted out of `orchestrator`'s own container for the same
documented reason as `pim_agent` (see its own README). Today's code is still plain Python, a real
Java/EMF/Gradle migration (the same subprocess-wrapped-as-HTTP pattern already used elsewhere in
this repo for JVM-backed validation) is separate, real, future work, not built here.

There is no single `psmMM.ecore` in this repo: MDDOAI's PSM is realized per target platform
(`gitlabMM.ecore`, `githubMM.ecore`, and `bitbucketMM.ecore` once added, all under the repo
root's `meta_models/`). `known_psm_platforms()`/`resolve_platform_metamodel()` discover which
platforms already have one directly from `meta_models/`'s own directory layout, rather than a
hardcoded list, so a newly added platform needs no code change here. `compare()`'s own
`psm_metamodel_path` defaults to `gitlabMM.ecore` (`DEFAULT_PSM_METAMODEL_PATH`), since MDDOAI
targets GitLab specifically; `generate()`'s master example defaults to `githubMM.ecore`
(`DEFAULT_PSM_MASTER_EXAMPLE_PATH`) — a different role (a fixed structural exemplar for a *new*
metamodel, not a drift-check target), so it's a separate constant, not a reuse of the same one.

## API

### `POST /psm`

```json
// request
{
  "platform_description": "TeamCity",
  "pim_artifact": "A pipeline consists of jobs organized into stages...",
  "platform_docs": "# TeamCity CI/CD Configuration\n...",
  "constraints": [],
  "model": null,
  "run_id": "run-123",
  "stage": "psm",
  "attempt": "attempt_1",
  "mock": false
}

// response (200, generation mode - no existing metamodel for this platform)
{
  "mode": "generation",
  "artifact": "<?xml version=\"1.0\"?><ecore:EPackage ...>",
  "prompt": {
    "pim_ecore": "A pipeline consists of jobs organized into stages...",
    "psm_docs": "# TeamCity CI/CD Configuration\n...",
    "psm_example": "<?xml version=\"1.0\"?>... (githubMM.ecore's real content)",
    "constraints": "- Fix: dangling reference to RetryPolicy"
  },
  "validation": {
    "valid": true, "mode": "codegen", "issues": [], "duration_ms": 120,
    "generated_source_path": "/runs/run-123/psm/attempt_1/ecore-validate-abc123"
  },
  "rounds": 2,
  "preset": "default",
  "prompt_version": null
}

// response (200, knowledge mode - platform already has a real metamodel)
{
  "mode": "knowledge",
  "artifact": "<?xml version=\"1.0\"?>... (the existing gitlabMM.ecore's own content, unchanged)",
  "gaps": [
    {
      "kind": "missing",
      "target": "RetryPolicy",
      "description": "The documentation describes per-job retry counts, but gitlabMM.ecore has no RetryPolicy class or equivalent property.",
      "source_excerpt": "Jobs can be configured to retry up to 2 times on failure."
    }
  ],
  "prompt": {"pim_ecore": "...", "psm_docs": "...", "psm_example": "...", "constraints": ""}
}
```

`constraints`/`model` are optional. `run_id`/`stage`/`attempt` are optional too, and only ever
matter on the generation path (the knowledge/comparison path never calls a validator at all, so
there's nothing to scope): they're plain passthrough fields, forwarded unchanged into
`generation.py`'s own `validator_agent_client.validate_ecore()` call, where the real path-safety
validation happens (see [validator_agent's own README](../validator_agent/README.md#setup)) -
this service never touches the filesystem with them directly. `400` if a resolved metamodel path
doesn't exist on disk.

`mock` (optional, default `false`) only ever matters on the generation path too: it skips the two
slow, billed steps (PIM-concept grounding and the real LLM call), returning a fixed, already-valid
placeholder artifact instead, while still resolving the real prompt config and still running the
real `validator_agent` call against that placeholder — a fast, free way to exercise the prompt
config mechanism and the real attempt-persistence path without spending either. `preset` and
`prompt_version` in a generation-mode response name exactly which saved config (see below)
produced this output, the real link an attempt's own persisted `prompt.json` and a later "restore
the config that produced this" UI action both need.

### `POST /compare`

Still available standalone (`psm_flow.run()` calls it internally for the knowledge-mode path
above, doesn't replace it) — see `comparison.py`'s own docstring. Same request/response shape as
before: `{"serialized_docs", "psm_metamodel_path"}` → `{"suggestions": [...]}`.

### `GET /health`

Used by the Dockerfile's `HEALTHCHECK`.

## Prompt configuration

Neither `generate()` nor `compare()` has a hardcoded system prompt: both resolve a real,
git-committed, UI-editable config through `generation_toolkit.prompt_config` (see
`generation_toolkit/README.md` for the mechanism itself, generic across any service that supplies
it a `config_dir`). This service's own `routes/prompt_config.py` is a thin HTTP surface over it,
`routes/files.py` a related but separate concern (see below); both routers live under
`routes/`, this service's own equivalent of `integration_runner/routes/`, and are wired into
`main.py` via `app.include_router(...)`.

Configs live under `prompts/{name}/`, `name` one of `"generation"`/`"comparison"` (matching this
service's two real LLM capabilities above), `PROMPT_CONFIG_DIR` (`prompt_paths.py`, overridable via
`PSM_PROMPT_CONFIG_DIR`, defaulting to `psm_agent/prompts/` next to this service's own source):

- `{preset}.default.json` — the immutable, git-committed shipped default for one preset. Every
  `name` ships at least a generic `default.default.json`, seeded from the real experiment with the
  most already-proven `learned_constraints` at the time it was ported (see
  `generation_toolkit/README.md`'s own `learned_constraints` section) rather than one default per
  platform, so the constraint list keeps growing in one place as more platforms are generated for
  real, instead of starting over per platform.
- `{preset}.json` — the live, currently-in-effect config, only created once someone actually saves
  an edit through `PUT /prompt-config/{name}/{preset}` (a `revert`/`restore` is also a save, so it
  exists after either of those too). `GET /prompt-config/{name}/{preset}` falls back to the shipped
  default when this doesn't exist yet.
- `history/{preset}.{version}.json` — an immutable snapshot of every version that's ever been
  live, one per save, `{version}` a sortable UTC timestamp plus a random suffix.

`ai/docker-compose.yml`'s `psm-agent` service bind-mounts this whole directory read-write
(`./psm_agent/prompts:/app/psm_agent/prompts`), so a save through the running dev container lands
on the real host git checkout, and `history/` is git-visible too.

**Which preset applies**: `generate()`/`compare()` resolve a preset from the real, free-text
`platform_description` they're called with via `presets.resolve_preset`, matching against each
preset's own `platform_hints` metadata (`PromptConfigBody.platform_hints`), never the preset's
storage id — a preset's identity is platform-agnostic, its hints are what name the platform(s) it
applies to. No hints matching falls back to `"default"`.

**Promoting a run's own live corrections into the permanent config**: `POST
/prompt-config/{name}/{preset}/learned-constraints` (and its `DELETE` counterpart) persist a
change to `learned_constraints`, applied to every future run of that `(name, preset)` from then on.
This is always a single, explicit, human-confirmed action (`integration_runner`'s own `POST
/psm/promote-constraints`, gated on a real validated success — see `integration_runner/README.md`),
never automatic capture of a typed correction.

Every other prompt-config endpoint (`presets`, `history`, `diff`, `restore/{version}`, `revert`,
`promote-to-default`, `check-references`, `preview`) is a thin, one-line call into the matching
`generation_toolkit.prompt_config` function — see that package's own README for what each one does.

## Available files

`GET /available-files` (`routes/files.py`, backed by `available_files.py`) lists every real
`.ecore` file under `META_MODELS_DIR`, as forward-slash paths relative to it — the picker a "file"
attachment (see `generation_toolkit/README.md`'s `attachments/` section) offers, and independently
the same root `generation_toolkit.attachments.files.resolve_file_attachment` validates a saved
attachment's `path` against, since a client can't be trusted to only send what the picker offered.

## Setup

```bash
pip install -r requirements.txt
```

`META_MODELS_DIR` is optional, defaulting to the real relative repo path to the root
`meta_models/` tree (`Path(__file__).resolve().parents[2] / "meta_models"`) for local/non-Docker
dev. Override it only if `meta_models/` is reachable somewhere else, e.g. the Docker Compose
read-only bind mount `ai/docker-compose.yml`'s `psm-agent` service entry sets it to.
`PIM_AGENT_URL`/`VALIDATOR_AGENT_URL` (both `ai/clients/` modules) point at those sibling
services, defaulting to their own local-dev ports. `PSM_PROMPT_CONFIG_DIR` is optional too,
defaulting to `psm_agent/prompts/` next to this service's own source (see "Prompt configuration"
above) — override only if that directory is bind-mounted somewhere else.

## Run

```bash
uvicorn main:app --reload --port 8040
```

## Test

```bash
cd psm_agent
pytest
```

`tests/test_comparison.py` reads real `.ecore` files from the repo's actual `meta_models/` tree
rather than mocking them, so `META_MODELS_DIR` resolving to a real, existing path is itself part
of what's under test, not assumed. `tests/test_generation.py`/`tests/test_psm_flow.py` mock the
real network boundaries (`ai_layer_client`, `pim_agent_client`, `validator_agent_client`) instead,
the same convention `test_comparison.py` already uses for its own one real network call — this
service's own test suite only covers PSM-specific wiring, the generic retry-loop mechanics have
their own tests under `generation_toolkit/tests/`. `tests/test_generation_real_llm.py` is the one
real, no-mocking end-to-end test — it needs a real running `ai-layer`, `pim-agent`, and
`validator-agent` (`docker compose up ai-layer pim-agent validator-agent`), and auto-skips
otherwise.
