# orchestrator

Stage-based pipeline generation for MDDOAI: walks a platform description through a fixed
PSM → ATL → Acceleo → generation pipeline, calling the `ai-layer` router's `chat()` for each
stage's agent, with a human reviewing and approving each stage's output before the next one
runs. Used by `ai-layer/main.py`'s `POST /orchestrate/start`,
`POST /orchestrate/rerun/{stage_id}`, and `POST /review/{stage_id}`.

## Stage-based pipeline generation

The pipeline turns a platform description into generated CI/CD tooling through four fixed
stages, in order:

```python
STAGES = ["psm", "atl", "acceleo", "generation"]
```

Each stage has exactly one agent, looked up directly by stage name — no classification or
selection step:

```python
stage_agents = {
    "psm": psm_agent,
    "atl": atl_agent,
    "acceleo": acceleo_agent,
    "generation": gen_agent,
}
```

### The agents

Each agent is a plain function `(context: dict) -> str` that builds a stage-specific system
prompt, sends it plus the relevant input to `chat()`, and returns the response content. Every
stage's output feeds the next stage's input:

- **`psm_agent(context)`** — reads `context["platform_description"]`. Produces a PSM
  (Platform-Specific Model) description: the platform's concepts (jobs, stages, triggers,
  artifacts, agents/runners) expressed in MDDOAI's platform-specific metamodel terms.
- **`atl_agent(context)`** — reads `context["psm_output"]` (the previous stage's output).
  Produces a description of the ATL (ATLAS Transformation Language) transformation rules
  needed to map the platform-independent model to that PSM: rule names, source/target
  patterns, and mapping logic.
- **`acceleo_agent(context)`** — reads `context["atl_output"]`. Produces a description of the
  Acceleo code-generation template needed to turn the transformed model into real pipeline
  configuration files: template structure, key generation blocks, and output file targets.
- **`gen_agent(context)`** — reads `context["psm_output"]`, `context["atl_output"]`, and
  `context["acceleo_output"]` (all three prior outputs). Produces a final, concise summary of
  the whole pipeline plan, from the original platform input through to the generated
  configuration.

If a stage was previously rejected with a correction (see below), that correction is appended
to the agent's input automatically, so a rerun of the stage takes the human's feedback into
account.

### `validate()`

`validate(output)` wraps `is_good_enough(output)`: the output must be non-empty and free of
refusal markers (`"I cannot"`, `"I don't know"`, `"I do not know"`) or explicit error markers
(`"an error occurred"`, `"sorry, an error"`). The markers are deliberately narrow phrases, not
a bare `"error"` substring match — generated content that legitimately *discusses* error
handling (e.g. an Acceleo template's error-handling block) isn't flagged as a bad response.
`validate()` is applied to every stage's output before a human ever sees it.

### The human review loop

Progress through `STAGES` is tracked by an `Orchestrator` instance (`current_stage_index`,
`constraints`, `last_context`, `last_output`); a module-level default instance backs the plain
function API below.

1. **`run_stage(context)`** — looks up the current stage's agent via `stage_agents`, calls it,
   validates the output, and returns `{"stage": ..., "output": ..., "valid": bool}`. It also
   remembers `context` as `last_context` and the output as `last_output`. This does *not*
   advance the pipeline; the current stage stays pending until a human reviews it.
2. A human reviews the stage's `output` (typically via `POST /review/{stage_id}`, below).
3. **Approve** → `stage_result()` advances `current_stage_index` and, if there's a next stage,
   immediately runs it: it builds that stage's context from `last_context` plus
   `{stage_id}_output: last_output` (so context accumulates every prior stage's output as the
   pipeline progresses) and calls `run_stage()` on it — returning *that* stage's
   `{"stage", "output", "valid"}` directly. If `generation` was the stage just approved, there's
   no next stage and it returns `{"status": "complete"}` instead.
4. **Reject** → `add_constraint(stage, correction)` records the human's correction in
   `constraints[stage]`; the *same* stage stays current and must be rerun via
   **`rerun_stage()`**, which calls `run_stage(last_context)` again — since `run_stage()`
   always reads the live `constraints` dict (not a snapshot taken at call time), the just-added
   correction is folded into the agent's input on that rerun automatically.

`stage_result(stage_id, approved, correction=None)` wraps steps 3–4 into a single call:

- `approved=True` → runs and returns the next stage's `{"stage", "output", "valid"}`, or
  `{"status": "complete"}` if there was no next stage.
- `approved=False` → records `correction` against `stage_id` and returns
  `{"status": "rerun", "stage": stage_id}`.

`reset_pipeline()` replaces the default `Orchestrator`, discarding progress and constraints —
used to start a fresh run.

## API endpoints (`ai-layer/main.py`)

### `POST /orchestrate/start`

Resets the pipeline and runs the first stage (`psm`) against a platform description.

Request:
```json
{ "platform_description": "TeamCity: A CI/CD platform using Kotlin DSL" }
```

Response:
```json
{
  "stage": "psm",
  "output": "PSM: TeamCity BuildType -> Job, VcsRoot -> Trigger.Source, ...",
  "valid": true
}
```

### `POST /review/{stage_id}`

Records a human's decision on the named stage's most recent output. `stage_id` must match the
pipeline's current pending stage (`400` otherwise); `correction` is required when `approved`
is `false` (`400` otherwise).

Request (approve, mid-pipeline):
```json
{ "approved": true }
```

Response — the *next* stage's output, generated immediately:
```json
{
  "stage": "atl",
  "output": "Rule PIMJobToBuildType: matches PIM!Job -> TeamCity!BuildType, ...",
  "valid": true
}
```

Request (approve, on the last stage, `generation`):
```json
{ "approved": true }
```

Response:
```json
{ "status": "complete" }
```

Request (reject):
```json
{ "approved": false, "correction": "Include a lint stage before build" }
```

Response:
```json
{ "status": "rerun", "stage": "psm" }
```

### `POST /orchestrate/rerun/{stage_id}`

Re-runs the current pending stage's agent, reusing the same context passed to its last
`run_stage()` call and picking up any constraints recorded against it since then via
`add_constraint()`. `stage_id` must match the pipeline's current pending stage (`400`
otherwise) — unlike `/orchestrate/start`, this does *not* reset `current_stage_index` or
`constraints`, so earlier approved stages and recorded corrections are preserved. No request
body is needed.

Response:
```json
{
  "stage": "psm",
  "output": "PSM: TeamCity BuildType -> Job, VcsRoot -> Trigger.Source, ... (lint stage added)",
  "valid": true
}
```

A typical run is: `POST /orchestrate/start` → review the `psm` output → `POST /review/psm`.
If approved, the response *is* the freshly generated `atl` output — review that and
`POST /review/atl`, and so on through `acceleo` and `generation`, until approving `generation`
returns `{"status": "complete"}`. If any review instead rejects a stage, call
`POST /orchestrate/rerun/{stage_id}` (the recorded correction is folded into the agent's input)
and review its output again before moving on — `/orchestrate/start` is *not* called again,
since that would reset the whole pipeline.

## Setup

```bash
pip install -r requirements.txt
```

This module imports `router.router.chat` from the sibling `ai-layer/` service, so it must be
run alongside a checkout that has `ai-layer/` configured (`.env` with at least one provider
key — see `ai-layer/README.md`).

## Use

```python
from orchestrator import rerun_stage, run_stage, stage_result

# Kick off the pipeline: runs the psm stage
result = run_stage({"platform_description": "TeamCity: A CI/CD platform using Kotlin DSL"})
print(result)  # {"stage": "psm", "output": "...", "valid": True}

# Rejected — record a correction, then rerun the same stage with it folded in
review = stage_result("psm", approved=False, correction="Include a lint stage before build")
print(review)  # {"status": "rerun", "stage": "psm"}
result = rerun_stage()
print(result)  # {"stage": "psm", "output": "... (lint stage added)", "valid": True}

# Approved — stage_result() runs the next stage (atl) itself and returns its output
result = stage_result("psm", approved=True)
print(result)  # {"stage": "atl", "output": "...", "valid": True}

# ...review "atl", approve it to get "acceleo"'s output, and so on, until approving
# the last stage ("generation") completes the pipeline:
result = stage_result("generation", approved=True)
print(result)  # {"status": "complete"}
```

`ai-layer/main.py`'s `POST /orchestrate/start`, `POST /orchestrate/rerun/{stage_id}`, and
`POST /review/{stage_id}` call `run_stage()`/`rerun_stage()`/`stage_result()` directly.

## Test

```bash
cd orchestrator
pytest
```

No real API calls are made — `chat()` is mocked in every test. `orchestrator/tests/` covers
`orchestrator.py` itself (the agents, `validate()`, and the `Orchestrator` state machine);
`ai-layer/tests/test_main.py` covers the three endpoints above, with `orchestrator`'s functions
mocked so no orchestrator logic actually runs there — to exercise both suites together:

```bash
cd ai-layer
python -m pytest ../orchestrator/tests/ tests/
```
