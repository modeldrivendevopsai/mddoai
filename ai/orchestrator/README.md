# orchestrator

Stage-based pipeline generation for MDDOAI: walks a platform description through a fixed
PSM → ATL → Acceleo → generation pipeline, with a human reviewing and approving each stage's
output before the next one runs. It's a standalone FastAPI service — independent of
`ai-layer` at the code level — that gets its LLM completions by calling `ai-layer`'s
`POST /chat` endpoint over HTTP, the same way any other client of that service would. Used by
this service's own `POST /start` and `POST /rerun/{stage_id}` (see [API endpoints](#api-endpoints-mainpy) below).

On top of that, `judge()` is an outer layer that lets an LLM decide *which* of the pipeline
functions to call for a free-form human message ("the ATL stage output is wrong, please fix
it with X"), using real tool/function calling — via `ai-layer`'s `/chat` `tools`/`tool_choice`
support — rather than keyword matching. Used by `POST /judge`.

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

## The judgment layer (`judge()`)

`run_stage()`, `rerun_stage()`, `stage_result()`, `add_constraint()`, and `start_pipeline()`
(below) are the primitives above; something still has to decide *which one* a free-form human
message maps to. `judge(user_message)` does that with real LLM tool/function calling — not
keyword matching — so "the ATL stage output is wrong, please fix it with X" gets correctly
mapped to two tool calls in sequence, not just a single guess.

`judge()` sends the message to `chat()` along with `TOOLS` (an OpenAI-style tool schema list)
and `tool_choice="auto"`, plus a system prompt that describes the four-stage pipeline and the
current pending stage (via `current_stage()`) so the LLM has situational context. The five
tools it can choose from:

- **`run_stage(context)`** — start/continue the current stage with new input.
- **`rerun_stage()`** — redo the current stage, reusing its last context plus any constraints.
- **`stage_result(stage_id, approved, correction=None)`** — record a review decision;
  approving advances the pipeline and runs the next stage immediately.
- **`add_constraint(stage, constraint)`** — record a correction without rerunning yet;
  typically followed immediately by `rerun_stage()` when the user wants a fix applied now.
- **`start_pipeline(platform_description)`** — reset the pipeline and start fresh at `psm`
  for a *different* platform, discarding all current progress and constraints. The system
  prompt steers phrases like "let's do this for GitLab instead" or "start over with a new
  platform" here, and explicitly *away* from `run_stage`/`rerun_stage`, which act on the
  current in-progress pipeline rather than starting a new one.

`judge()` executes whichever tool call(s) the LLM returns, in order, against these real
functions (via `_dispatch_tool()`), and reports:

- **One or more tool calls** → `{"tool_called": <last tool's name>, "result": <last tool's
  result>, "steps": [{"tool", "arguments", "result"}, ...]}` — `steps` holds every call made
  (so a two-call `add_constraint` → `rerun_stage` sequence is fully visible), while
  `tool_called`/`result` always reflect the final, most-actionable step.
- **No tool call** (the model couldn't map the message to a pipeline action, or the request
  was genuinely ambiguous — e.g. "reject this" with no stated reason) → `{"tool_called": None,
  "result": None, "message": <the model's own clarifying question, or a generic fallback>}`.
  No state is mutated in this case.

A hallucinated/unknown tool name doesn't crash `judge()` — that step's `"result"` becomes
`{"error": "Unknown tool: <name>"}` instead.

## API endpoints (`main.py`)

The service starts at [http://localhost:8001](http://localhost:8001) (port 8000 is `ai-layer`'s).

### `POST /start`

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

### `POST /rerun/{stage_id}`

Re-runs the current pending stage's agent, reusing the same context passed to its last
`run_stage()` call and picking up any constraints recorded against it since then via
`add_constraint()`. `stage_id` must match the pipeline's current pending stage (`400`
otherwise) — unlike `/start`, this does *not* reset `current_stage_index` or
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

A typical run is: `POST /start` → review the `psm` output → `POST /review/psm`.
If approved, the response *is* the freshly generated `atl` output — review that and
`POST /review/atl`, and so on through `acceleo` and `generation`, until approving `generation`
returns `{"status": "complete"}`. If any review instead rejects a stage, call
`POST /rerun/{stage_id}` (the recorded correction is folded into the agent's input)
and review its output again before moving on — `/start` is *not* called again,
since that would reset the whole pipeline.

### `POST /judge`

Lets an LLM decide which pipeline action a free-form human message maps to (see
[The judgment layer](#the-judgment-layer-judge) above), executes it, and returns whatever
`judge()` returns directly — no response transformation.

Request:
```json
{ "message": "the ATL stage output is wrong, please redo it with a lint step added" }
```

Response — here the model called `add_constraint` then `rerun_stage`, so `atl` was
regenerated with the correction folded in:
```json
{
  "tool_called": "rerun_stage",
  "result": {
    "stage": "atl",
    "output": "Rule PIMJobToBuildType: matches PIM!Job -> TeamCity!BuildType, ... (lint step added)",
    "valid": true
  },
  "steps": [
    {
      "tool": "add_constraint",
      "arguments": { "stage": "atl", "constraint": "Add a lint step to validate the ATL transformation rules" },
      "result": null
    },
    {
      "tool": "rerun_stage",
      "arguments": {},
      "result": { "stage": "atl", "output": "... (lint step added)", "valid": true }
    }
  ]
}
```

Request (ambiguous — no stated correction):
```json
{ "message": "reject this, it's not good enough" }
```

Response — the model asked for clarification instead of guessing, and nothing was mutated:
```json
{
  "tool_called": null,
  "result": null,
  "message": "I need a bit more information to record your rejection properly: what's the correction or reason?"
}
```

## Setup

```bash
pip install -r requirements.txt
cp .env.example .env
# AI_LAYER_URL defaults to http://localhost:8000 if unset — override only if ai-layer
# runs somewhere else (e.g. a different port, or a Docker Compose service name).
```

This service is independent of `ai-layer` at the code level — it has no import on
`router.*` and doesn't need `ai-layer`'s source tree or `.env` present. It does need an
`ai-layer` instance actually *running* and reachable at `AI_LAYER_URL`, since every stage
agent and `judge()` get their LLM completions by POSTing to its `/chat` endpoint (see
`ai-layer/README.md` for running that service).

## Run

```bash
uvicorn main:app --reload --port 8001
```

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

Or let the LLM decide which action a free-form message maps to, instead of calling the
primitives above directly:

```python
from orchestrator import judge

result = judge("the ATL stage output is wrong, please redo it with a lint step added")
print(result["tool_called"])  # "rerun_stage"
print(result["steps"])        # [{"tool": "add_constraint", ...}, {"tool": "rerun_stage", ...}]

result = judge("let's do this for GitLab instead")
print(result["tool_called"])  # "start_pipeline" — resets the pipeline for the new platform
```

This service's own `POST /start`, `POST /rerun/{stage_id}`, `POST /review/{stage_id}`, and
`POST /judge` call `run_stage()`/`rerun_stage()`/`stage_result()`/`judge()` directly (see
`main.py`).

## Test

```bash
cd orchestrator
pytest
```

No real network calls are made — `httpx.post` (the only thing that talks to `ai-layer`) is
mocked in every test, standing in for `ai-layer`'s `/chat` responses.

`tests/test_orchestrator.py` covers `orchestrator.py` itself: the agents, `validate()`,
`judge()`, the `Orchestrator` state machine, and the HTTP `chat()` client (payload shape,
`tools`/`tool_choice` passthrough, and unwrapping `content`/`tool_calls` from the JSON
response). `tests/test_main.py` covers the four endpoints above — these mock only
`httpx.post`, letting the real `orchestrator.py` logic run underneath `main.py`'s routes, so
the tests exercise the actual wiring between the two rather than mocking across that seam.

`ai-layer`'s own test suite is separate (`cd ai-layer && pytest`) and covers only its own
`/health`, `/providers`, and `/chat` endpoints — the two suites are independent and should be
run from their own directories, not together (both projects have their own `main.py`, and
running them in one pytest session causes a module-name collision).
