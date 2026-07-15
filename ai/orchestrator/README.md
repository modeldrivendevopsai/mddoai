# orchestrator

Two orchestration flows for MDDOAI, both calling the `ai-layer` router's `chat()`:

- **Intent-based chat** (`orchestrate()`) — classifies a chat message's intent and answers it
  with an intent-specific system prompt. Used by `ai-layer/main.py`'s `POST /orchestrate`.
- **Stage-based pipeline generation** (`Orchestrator`, `run_stage()`, `rerun_stage()`,
  `stage_result()`) — walks a platform description through a fixed PSM → ATL → Acceleo →
  generation pipeline, with a human reviewing and approving each stage's output before the
  next one runs. Used by `ai-layer/main.py`'s `POST /orchestrate/start`,
  `POST /orchestrate/rerun/{stage_id}`, and `POST /review/{stage_id}`.

## Intent-based chat (`orchestrate()`)

`orchestrate(messages)`:

1. Reads the latest `role: "user"` message from the conversation.
2. Classifies its intent into one of:
   - `pipeline_generation` — mentions pipeline, CI/CD, deploy, build, test stages, or generate.
   - `platform_question` — mentions platform, TeamCity, GitLab, GitHub Actions, Bamboo, or Azure DevOps.
   - `general_question` — anything else.
3. Prepends an intent-specific system prompt to the conversation.
4. Calls `router.router.chat()` (from `ai-layer`) with the enriched messages.
5. Returns the response content as a string.

### Retry loop

A low-quality response (a refusal, an "I don't know", or an empty reply) usually means the
system prompt needs tightening rather than the provider being down, so `orchestrate()` retries
the call before giving up:

- Up to `MAX_ATTEMPTS` (3) calls to `chat()`.
- After each attempt, `is_good_enough(response)` checks that the response is non-empty and
  doesn't contain obvious error markers (`"I cannot"`, `"I don't know"`, `"error"`, ...).
- A response that passes is returned immediately — no unnecessary extra calls.
- Every failed attempt is logged (`logging.warning`) with the attempt number and the system
  prompt used, so a human can see which intent's prompt needs curation.
- If all `MAX_ATTEMPTS` attempts fail the quality check, the last response is returned as-is
  with an appended note that human curation of the prompt is needed — callers always get
  *something* back, never an exception.

## Stage-based pipeline generation

The pipeline turns a platform description into generated CI/CD tooling through four fixed
stages, in order:

```python
STAGES = ["psm", "atl", "acceleo", "generation"]
```

Each stage has exactly one agent, looked up directly by stage name — no classification or
selection step, unlike `orchestrate()`'s intent routing:

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

`validate(output)` reuses `is_good_enough()` from the intent-based flow — non-empty, no
refusal/error markers. It's applied to every stage's output before a human ever sees it.

### The human review loop

Progress through `STAGES` is tracked by an `Orchestrator` instance (`current_stage_index`,
`constraints`, `last_context`); a module-level default instance backs the plain function API
below.

1. **`run_stage(context)`** — looks up the current stage's agent via `stage_agents`, calls it,
   validates the output, and returns `{"stage": ..., "output": ..., "valid": bool}`. It also
   remembers `context` as `last_context`. This does *not* advance the pipeline; the current
   stage stays pending until a human reviews it.
2. A human reviews the stage's `output` (typically via `POST /review/{stage_id}`, below).
3. **Approve** → `advance_stage()` moves `current_stage_index` forward and returns the new
   stage name, or `None` if `generation` was the last stage completed.
4. **Reject** → `add_constraint(stage, correction)` records the human's correction in
   `constraints[stage]`; the *same* stage stays current and must be rerun via
   **`rerun_stage()`**, which calls `run_stage(last_context)` again — since `run_stage()`
   always reads the live `constraints` dict (not a snapshot taken at call time), the just-added
   correction is folded into the agent's input on that rerun automatically.

`stage_result(stage_id, approved, correction=None)` wraps steps 3–4 into a single call:

- `approved=True` → advances and returns `{"status": "advanced", "next_stage": ...}`, or
  `{"status": "complete"}` if there was no next stage.
- `approved=False` → records `correction` against `stage_id` and returns
  `{"status": "rerun", "stage": stage_id}`.

`reset_pipeline()` replaces the default `Orchestrator`, discarding progress and constraints —
used to start a fresh run.

## API endpoints (`ai-layer/main.py`)

### `POST /orchestrate`

Intent-based chat, unchanged — see [Intent-based chat](#intent-based-chat-orchestrate) above.

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
pipeline's current pending stage (`400` otherwise); `correction` is required when
`approved` is `false` (`400` otherwise).

Request (approve):
```json
{ "approved": true }
```

Response:
```json
{ "status": "advanced", "next_stage": "atl" }
```

— or, if the approved stage was the last one:
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

A typical run is: `POST /orchestrate/start` → `POST /review/psm`. If that review rejects the
stage, the caller calls `POST /orchestrate/rerun/psm` (the recorded correction is now folded
into the agent's input) and reviews it again — `/orchestrate/start` is *not* called again,
since that would reset the whole pipeline. Once a review approves the stage, move on to
`POST /review/atl`, and so on through `acceleo` and `generation`.

## Setup

```bash
pip install -r requirements.txt
```

This module imports `router.router.chat` from the sibling `ai-layer/` service, so it must be
run alongside a checkout that has `ai-layer/` configured (`.env` with at least one provider
key — see `ai-layer/README.md`).

## Use

```python
from orchestrator import add_constraint, orchestrate, rerun_stage, run_stage, stage_result

# Intent-based chat
response = orchestrate([{"role": "user", "content": "Generate a CI/CD pipeline for a Node app"}])

# Stage-based pipeline
result = run_stage({"platform_description": "TeamCity: A CI/CD platform using Kotlin DSL"})
print(result)  # {"stage": "psm", "output": "...", "valid": True}

# Rejected — record a correction and rerun the same stage with it folded in
review = stage_result("psm", approved=False, correction="Include a lint stage before build")
print(review)  # {"status": "rerun", "stage": "psm"}
result = rerun_stage()
print(result)  # {"stage": "psm", "output": "... (lint stage added)", "valid": True}

# Approved — advance to the next stage
review = stage_result("psm", approved=True)
print(review)  # {"status": "advanced", "next_stage": "atl"}
```

`ai-layer/main.py`'s `POST /orchestrate` endpoint calls `orchestrate()` directly;
`POST /orchestrate/start`, `POST /orchestrate/rerun/{stage_id}`, and `POST /review/{stage_id}`
call `run_stage()`/`rerun_stage()`/`stage_result()`.

## Test

```bash
cd orchestrator
pytest
```

No real API calls are made — `chat()` is mocked in tests, for both the intent-based flow and
the stage agents.
