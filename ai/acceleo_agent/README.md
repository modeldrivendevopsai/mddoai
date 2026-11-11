# acceleo_agent

The real `acceleo` pipeline stage's whole capability, `POST /generate` (`generation.py`'s
`generate()`): given the run's own real PSM metamodel and target platform documentation,
generates a new Acceleo model-to-text template that generates that platform's real CI/CD YAML
configuration from a PSM model instance, refined against real `validator_agent` feedback
(`validator_agent_client.validate_acceleo`, given the run's own real PSM ecore as
`metamodel_ecore` so the target platform's own metamodel resolves during compilation even when
it's a brand-new platform with no genmodel or compiled Java package of its own — mock mode gives
the validator the mock chain's own metamodel and transformation instead) rather than accepted on the first
attempt, via the
shared `generation_toolkit` package's `run_with_retry()` (a stage-agnostic "build a prompt, call
the LLM, validate, retry" toolkit, not specific to Acceleo — see `generation_toolkit/README.md`).
On failure, every one of the validator's ERROR-severity issues becomes a new constraint (skipping
any already recorded from an earlier round) and the prompt is rebuilt for another round, bounded,
so a platform whose real docs genuinely can't produce a compiling template fails closed instead of
looping forever.

`psm_artifact` (this service's own `POST /generate` request body) reaches validator-agent's real
Java EMF parsing unmodified, as `metamodel_ecore` above — validator-agent's own `EMFUtils` hardens
that parse against untrusted content (see `ai/validator_agent/README.md`'s own note on
`metamodel_ecore`), not this service.

Simpler than `psm_agent`: one real mode only, no generation-vs-knowledge-mode routing (there is no
"platform already has an Acceleo template" concept — every real run generates fresh from that
run's own real PSM artifact and docs) and no PIM-concept grounding call (the real, validated
research prompts this is ported from never used one for this step, and Acceleo generation needs no
PIM at all).

Own container, own port (8080). Same reasoning as `psm_agent`/`atl_agent`: each real pipeline
capability gets independent deployability, not bundled into whichever process happens to call it.
Reached by `integration_runner`'s acceleo stage (`integration_runner/stages/acceleo/agent.py`) via
`clients/acceleo_agent_client.py`.

The one real, pre-existing repo file this service reads is this project's own real, working
GitLab Acceleo code-generation template
(`code_generation/com.mddoai.codegeneration.gitlab.acceleo/src/.../main/generate.mtl`) — the
master example the default prompt attaches, the same role `psm_agent`'s own master-example
`.ecore` file plays for it. Read-only, a single file (not a whole directory), see
`prompt_settings.py`'s own `REFERENCE_EXAMPLE_PATH`.

## API

### `POST /generate`

```json
// request
{
  "psm_artifact": "<?xml version=\"1.0\"?><ecore:EPackage ...>",
  "platform_docs": "# TeamCity CI/CD Configuration\n...",
  "constraints": [],
  "model": null,
  "run_id": "run-123",
  "stage": "acceleo",
  "attempt": "attempt_1",
  "mock": false
}

// response (200)
{
  "artifact": "[comment encoding = UTF-8 /]\n[module generate('...')]...",
  "prompt": {"psm_ecore": "...", "platform_docs": "...", "acceleo_example": "...", "constraints": ""},
  "validation": {
    "valid": true, "issues": [], "duration_ms": 120,
    "compiled_output_kept": true
  },
  "rounds": 2,
  "prompt_version": null
}
```

`constraints`/`model` are optional. `run_id`/`stage`/`attempt` are optional too and only matter for
scoping the real compiled `.emtl` module a real validator-agent call produces: plain passthrough
fields, forwarded unchanged into `validator_agent_client.validate_acceleo`.

`mock: true` (the same per-run "Mock" override every other real stage shares) still resolves the
real prompt config and runs the real `validator_agent` call, against the mock chain's fixed template
(see `generation_toolkit`'s `mock_chain`) and the mock chain's own metamodel, not the run's. It skips
the real LLM call, so no constraint changes the result, and a mocked call learns no fix.

### `GET /health`

Used by the Dockerfile's `HEALTHCHECK`.

## Prompt configuration

`generate()` has no hardcoded system prompt: it resolves a real, UI-editable config
through `generation_toolkit.prompt_config` (see `generation_toolkit/README.md` for the mechanism
itself, generic across any service that supplies it an agent name). This service's own
`routes/prompt_config.py` is a thin HTTP surface over it, `routes/files.py` a related but separate
concern (see below); both live under `routes/` and are wired into `main.py` via
`app.include_router(...)`.

Configs live under `generation/` in the journal (this service's name `AGENT`, in `prompt_settings.py`):

- `default.default.json`: the immutable, git-committed shipped default, seeded from real
  constraints accumulated across GitLab and Bamboo before being carried into every later
  platform. See `generation_toolkit/README.md`'s own `learned_constraints` section. One shared
  config, not one per platform: the real, validated experiments always generated a fresh template
  from the current run's own real artifacts regardless of target platform.
- `default.json` — the live, currently-in-effect config, only created once someone actually saves
  an edit through `PUT /prompt-config/generation` (restoring a past version, shipped default
  included, is also a save). `GET /prompt-config/generation` falls back to the shipped default when
  this doesn't exist yet.
- `history/default.{version}.json` — an immutable snapshot of every version that's ever been live.
- `constraints.json`: the real, always-current `learned_constraints` list, kept entirely outside
  the three files above: restoring any version of the prompt's own text/attachments, the shipped
  default included, never touches it, so a promoted constraint stays permanent through every one of
  them (see `generation_toolkit/README.md`'s own `learned_constraints.py` section).

`ai/docker-compose.yml`'s `acceleo-agent` service bind-mounts `prompts/` read-write, so a save
through the running dev container lands on the real host git checkout, and `history/` is
git-visible too.

**Promoting a run's own live corrections into the permanent config**: `POST
/prompt-config/generation/learned-constraints` (and its `DELETE` counterpart) persist a change to
`learned_constraints`, applied to every future run from then on. Always a single, explicit,
human-confirmed action (`integration_runner`'s own `POST /acceleo/promote-constraints`, gated on a
real validated result), never automatic capture of a typed correction. `PUT
/prompt-config/generation/learned-constraints/reorder` persists a human reordering that same list -
it only accepts a permutation of the current set, 400ing otherwise.

Every other prompt-config endpoint, `history`, `diff`, `restore/{version}` (also how "revert to
default" works, called with `version=shipped`, not a separate endpoint), `check-references`,
`preview`, is a thin, one-line call into the matching `generation_toolkit.prompt_config` function.

## Available files

`GET /available-files` (`routes/files.py`, backed by `available_files.py`) lists the one real
master-example file plus every file uploaded to this service (kept by the journal), the picker a
"file" attachment offers, and independently the same roots
`generation_toolkit.attachments.files.resolve_file_attachment` validates a saved attachment's
`path` against, since a client can't be trusted to only send what the picker offered.

## Setup

```bash
pip install -r requirements.txt
```

The reference example is a file shipped inside this service, so it needs no setting.
`VALIDATOR_AGENT_URL` (`ai/clients/validator_agent_client.py`) points at that sibling service.
`VALIDATOR_AGENT_URL` and `JOURNAL_URL` are required, with no default: the service stops at start if one is
unset or empty. Docker Compose sets them. The prompt config and uploaded files are in the journal, so this
service mounts no folder for them. For a local run, export the links of validator-agent and the journal.

## Run

```bash
uvicorn main:app --reload --port 8080
```

Because `main.py` imports the sibling `clients/`/`generation_toolkit/` packages, which live
outside this folder, `PYTHONPATH` needs to include the parent `ai/` directory too:

```bash
# from ai/acceleo_agent/
PYTHONPATH=.. uvicorn main:app --reload --port 8080
```

Nothing reads a `.env` file for you, so export the links first.

## Test

```bash
cd acceleo_agent
pytest
```
