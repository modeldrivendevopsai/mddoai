# atl_agent

The real `atl` pipeline stage's whole capability, `POST /generate` (`generation.py`'s
`generate()`): given the run's own real PIM artifact and target platform PSM metamodel, generates
a new ATL model-to-model transformation from PIM to that platform's PSM, refined against real
`validator_agent` feedback (`validator_agent_client.validate_atl`) rather than accepted on the
first attempt, via the shared `generation_toolkit` package's `run_with_retry()` (a stage-agnostic
"build a prompt, call the LLM, validate, retry" toolkit, not specific to ATL — see
`generation_toolkit/README.md`). On failure, the validator's first issue becomes one new
constraint and the prompt is rebuilt for another round, bounded, so a platform whose real
metamodels genuinely can't produce a compiling ATL transformation fails closed instead of looping
forever.

Simpler than `psm_agent`: one real mode only, no generation-vs-knowledge-mode routing (there is no
"platform already has an ATL transformation" concept — every real run generates fresh from that
run's own real PIM/PSM artifacts) and no PIM-concept grounding call (the real, validated research
prompts this is ported from never used one for this step).

Own container, own port (8070). Same reasoning as `psm_agent`/`pim_agent`: each real pipeline
capability gets independent deployability, not bundled into whichever process happens to call it.
Reached by `integration_runner`'s atl stage (`integration_runner/stages/atl/agent.py`) via
`clients/atl_agent_client.py`.

The one real, pre-existing repo file this service reads is `main/`'s own real, working
`pim2gitlabmodel.atl` (a real PIM→GitLab transformation, `main/src/main/resources/transformations/pim2psm/`) —
the master example the default prompt attaches, the same role `psm_agent`'s own master-example
`.ecore` file plays for it. Read-only, a single file (not a whole directory), see
`prompt_paths.py`'s own `REFERENCE_EXAMPLE_PATH` and `ai/CLAUDE.md`'s folder-boundaries section for
why this narrow read across the MDE-engine/AI-layer boundary is a deliberate, documented exception.

## API

### `POST /generate`

```json
// request
{
  "pim_artifact": "<?xml version=\"1.0\"?><ecore:EPackage ...>",
  "psm_artifact": "<?xml version=\"1.0\"?><ecore:EPackage ...>",
  "platform_description": "TeamCity",
  "constraints": [],
  "model": null,
  "run_id": "run-123",
  "stage": "atl",
  "attempt": "attempt_1",
  "mock": false
}

// response (200)
{
  "artifact": "-- @nsURI PIM=pimMM=...\nmodule pim2teamcity;\n...",
  "prompt": {"pim_ecore": "...", "psm_ecore": "...", "atl_example": "...", "constraints": ""},
  "validation": {
    "valid": true, "mode": "compile", "issues": [], "duration_ms": 120,
    "generated_source_path": "/runs/run-123/atl/attempt_1/atl-validate-abc123"
  },
  "rounds": 2,
  "preset": "default",
  "prompt_version": null
}
```

`constraints`/`model` are optional. `run_id`/`stage`/`attempt` are optional too and only matter for
scoping the real compiled `.asm` bytecode a real validator-agent call produces: plain passthrough
fields, forwarded unchanged into `validator_agent_client.validate_atl`.

`mock: true` (the same per-run "Mock" override every other real stage shares) still resolves the
real prompt config and runs the real `validator_agent` call against a small, fixed, already-valid
artifact — it only skips the real, slow, billed LLM call, for fast local iteration on a config.

### `GET /health`

Used by the Dockerfile's `HEALTHCHECK`.

## Prompt configuration

`generate()` has no hardcoded system prompt: it resolves a real, git-committed, UI-editable config
through `generation_toolkit.prompt_config` (see `generation_toolkit/README.md` for the mechanism
itself, generic across any service that supplies it a `config_dir`). This service's own
`routes/prompt_config.py` is a thin HTTP surface over it, `routes/files.py` a related but separate
concern (see below); both live under `routes/` and are wired into `main.py` via
`app.include_router(...)`.

Configs live under `prompts/generation/` (this service has only one real config name,
`"generation"`), `PROMPT_CONFIG_DIR` (`prompt_paths.py`, overridable via
`ATL_AGENT_PROMPT_CONFIG_DIR`, defaulting to `atl_agent/prompts/` next to this service's own
source):

- `default.default.json` — the immutable, git-committed shipped default, seeded from the real
  experiment's own final, most-refined accumulated `learned_constraints` (see
  `generation_toolkit/README.md`'s own `learned_constraints` section).
- `default.json` — the live, currently-in-effect config, only created once someone actually saves
  an edit through `PUT /prompt-config/generation/default` (a `revert`/`restore` is also a save).
  `GET /prompt-config/generation/default` falls back to the shipped default when this doesn't
  exist yet.
- `history/default.{version}.json` — an immutable snapshot of every version that's ever been live.

`ai/docker-compose.yml`'s `atl-agent` service bind-mounts `prompts/` read-write, so a save through
the running dev container lands on the real host git checkout, and `history/` is git-visible too.

Presets are supported for parity with `psm_agent` (resolved from the real, free-text
`platform_description` via `presets.resolve_preset`, matching each preset's own `platform_hints`),
even though only `"default"` has real content today — a future platform needing its own
ATL-generation guidance can get one with no code change.

**Promoting a run's own live corrections into the permanent config**: `POST
/prompt-config/generation/{preset}/learned-constraints` (and its `DELETE` counterpart) persist a
change to `learned_constraints`, applied to every future run of that preset from then on. Always a
single, explicit, human-confirmed action (`integration_runner`'s own `POST
/atl/promote-constraints`, gated on a real validated result), never automatic capture of a typed
correction.

Every other prompt-config endpoint (`presets`, `history`, `diff`, `restore/{version}`, `revert`,
`promote-to-default`, `check-references`, `preview`) is a thin, one-line call into the matching
`generation_toolkit.prompt_config` function.

## Available files

`GET /available-files` (`routes/files.py`, backed by `available_files.py`) lists the one real
master-example file plus every real uploaded file under `ATTACHMENT_UPLOADS_DIR` — the picker a
"file" attachment offers, and independently the same roots
`generation_toolkit.attachments.files.resolve_file_attachment` validates a saved attachment's
`path` against, since a client can't be trusted to only send what the picker offered.

## Setup

```bash
pip install -r requirements.txt
```

`ATL_AGENT_REFERENCE_EXAMPLE_PATH` is optional, defaulting to the real relative repo path to
`main/src/main/resources/transformations/pim2psm/pim2gitlabmodel.atl` for local/non-Docker dev.
Override it only if that file is reachable somewhere else, e.g. the Docker Compose read-only bind
mount `ai/docker-compose.yml`'s `atl-agent` service entry sets it to. `VALIDATOR_AGENT_URL`
(`ai/clients/validator_agent_client.py`) points at that sibling service, defaulting to its own
local-dev port. `ATL_AGENT_PROMPT_CONFIG_DIR`/`ATL_AGENT_ATTACHMENT_UPLOADS_DIR` are optional too,
each defaulting to a directory next to this service's own source (see "Prompt configuration"
above) — override only if bind-mounted somewhere else.

## Run

```bash
uvicorn main:app --reload --port 8070
```

Because `main.py` imports the sibling `clients/`/`generation_toolkit/` packages, which live
outside this folder, `PYTHONPATH` needs to include the parent `ai/` directory too:

```bash
# from ai/atl_agent/
PYTHONPATH=.. uvicorn main:app --reload --port 8070
```

## Test

```bash
cd atl_agent
pytest
```
