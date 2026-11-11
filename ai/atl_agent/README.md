# atl_agent

The real `atl` pipeline stage's whole capability, `POST /generate` (`generation.py`'s
`generate()`): given the run's own real PIM artifact and target platform PSM metamodel, generates
a new ATL model-to-model transformation from PIM to that platform's PSM, refined against real
`validator_agent` feedback (`validator_agent_client.validate_atl`) rather than accepted on the
first attempt, via the shared `generation_toolkit` package's `run_with_retry()` (a stage-agnostic
"build a prompt, call the LLM, validate, retry" toolkit, not specific to ATL — see
`generation_toolkit/README.md`). On failure, every one of the validator's ERROR-severity issues
becomes a new constraint (skipping any already recorded from an earlier round) and the prompt is
rebuilt for another round, bounded, so a platform whose real metamodels genuinely can't produce a
compiling ATL transformation fails closed instead of looping
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
`prompt_paths.py`'s own `REFERENCE_EXAMPLE_PATH`.

## API

### `POST /generate`

```json
// request
{
  "pim_artifact": "<?xml version=\"1.0\"?><ecore:EPackage ...>",
  "psm_artifact": "<?xml version=\"1.0\"?><ecore:EPackage ...>",
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
    "generated_source_path": "<validator output folder>/run-123/atl/attempt_1/atl-validate-abc123"
  },
  "rounds": 2,
  "prompt_version": null
}
```

`constraints`/`model` are optional. `run_id`/`stage`/`attempt` are optional too and only matter for
scoping the real compiled `.asm` bytecode a real validator-agent call produces: plain passthrough
fields, forwarded unchanged into `validator_agent_client.validate_atl`.

`mock: true` (the same per-run "Mock" override every other real stage shares) still resolves the
real prompt config and runs the real `validator_agent` call, against the mock chain's fixed transformation
(see `generation_toolkit`'s `mock_chain`) and the mock chain's own metamodel, not the run's. It skips
the real LLM call, so no constraint changes the result, and a mocked call learns no fix.

### `GET /health`

Used by the Dockerfile's `HEALTHCHECK`.

## Prompt configuration

`generate()` has no hardcoded system prompt: it resolves a real, UI-editable config
through `generation_toolkit.prompt_config` (see `generation_toolkit/README.md` for the mechanism
itself, generic across any service that supplies it a `config_dir`). This service's own
`routes/prompt_config.py` is a thin HTTP surface over it, `routes/files.py` a related but separate
concern (see below); both live under `routes/` and are wired into `main.py` via
`app.include_router(...)`.

Configs live under `generation/` in the prompt folder (this service has only one real config name,
`"generation"`), `PROMPT_CONFIG_DIR` (`prompt_paths.py`, the required `ATL_AGENT_PROMPT_CONFIG_DIR`):

- `default.default.json` — the immutable, git-committed shipped default, seeded from the real
  experiment's own final, most-refined accumulated `learned_constraints` (see
  `generation_toolkit/README.md`'s own `learned_constraints` section). One shared config, not one
  per platform: the real, validated experiments always generated a fresh ATL transformation from
  the current run's own PIM/PSM artifacts regardless of target platform, so there's no per-platform
  guidance to separate out.
- `default.json` — the live, currently-in-effect config, only created once someone actually saves
  an edit through `PUT /prompt-config/generation` (restoring a past version, shipped default
  included, is also a save). `GET /prompt-config/generation` falls back to the shipped default when
  this doesn't exist yet.
- `history/default.{version}.json` — an immutable snapshot of every version that's ever been live.
- `constraints.json`: the real, always-current `learned_constraints` list, kept entirely outside
  the three files above: restoring any version of the prompt's own text/attachments, the shipped
  default included, never touches it, so a promoted constraint stays permanent through every one of
  them (see `generation_toolkit/README.md`'s own `learned_constraints.py` section).

`ai/docker-compose.yml`'s `atl-agent` service bind-mounts `prompts/` read-write, so a save through
the running dev container lands on the real host git checkout, and `history/` is git-visible too.

**Promoting a run's own live corrections into the permanent config**: `POST
/prompt-config/generation/learned-constraints` (and its `DELETE` counterpart) persist a change to
`learned_constraints`, applied to every future run from then on. Always a single, explicit,
human-confirmed action (`integration_runner`'s own `POST /atl/promote-constraints`, gated on a
real validated result), never automatic capture of a typed correction. `PUT
/prompt-config/generation/learned-constraints/reorder` persists a human reordering that same list -
it only accepts a permutation of the current set, 400ing otherwise.

Every other prompt-config endpoint, `history`, `diff`, `restore/{version}` (also how "revert to
default" works, called with `version=shipped`, not a separate endpoint), `check-references`,
`preview`, is a thin, one-line call into the matching `generation_toolkit.prompt_config` function.

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

The reference example is a file shipped inside this service, so it needs no setting.
`VALIDATOR_AGENT_URL` (`ai/clients/validator_agent_client.py`) points at that sibling service.
`VALIDATOR_AGENT_URL`, `ATL_AGENT_PROMPT_CONFIG_DIR` and `ATL_AGENT_ATTACHMENT_UPLOADS_DIR` are
required, with no default: the service stops at start if one is unset or empty. Docker Compose sets
them. For a local run, export them: the link of validator-agent, `ai/data/prompts/atl`, and a
writable folder of your own.

## Run

```bash
uvicorn main:app --reload --port 8070
```

Because `main.py` imports the sibling `clients/`/`generation_toolkit/` packages, which live
outside this folder, `PYTHONPATH` needs to include the parent `ai/` directory too:

```bash
# from ai/atl_agent/
export ATL_AGENT_PROMPT_CONFIG_DIR=$PWD/../data/prompts/atl
export ATL_AGENT_ATTACHMENT_UPLOADS_DIR=/path/to/a/writable/folder
PYTHONPATH=.. uvicorn main:app --reload --port 8070
```

The two `export` lines are the required folder settings (nothing reads a `.env` file for you).

## Test

```bash
cd atl_agent
pytest
```
