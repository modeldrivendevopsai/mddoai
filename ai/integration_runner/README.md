# integration_runner

The pipeline state machine for MDDOAI: walks a platform description through a fixed
`docs → serialization → pim → psm → atl → acceleo → generation` pipeline, tracks a human's
review decisions, and records every real thing that happens to a run as a structured event.
It's a standalone FastAPI service with no knowledge of chat, narration, or LLM tool-calling —
a human clicking a button and an LLM calling a tool are indistinguishable to it, both just make
REST calls. `ai/orchestrator/` is the only real caller (via
[`clients/integration_runner_client.py`](../clients/integration_runner_client.py), reached only
over HTTP, never imported as a Python package); it's what turns these raw facts into a chat
conversation and an LLM tool-calling surface.

Gets its own real capabilities from `ai-layer` (`POST /chat`, for the placeholder stage
agents' LLM completions), `retrieval` (`POST /fetch`/`POST /fetch/page`, for the docs stage's
real crawl and the docs stage's extra add-on action), and `serialization_agent` (`POST
/serialize`, for the serialization stage's concept labeling — its own separate service, not a
bundled import).

## Module layout

- **`pipeline.py`** — the state machine only: `STAGES`, `validate()`, and `class IntegrationRun`
  (one run's progress, stage execution, review handling, and background-thread mechanics). Has
  no concept of "which run is current," only "how does one run advance." Composes `EventLog`
  (`event_log.py`) rather than implementing storage itself. Deliberately does not hold
  stage-specific extra actions (like `stages/docs/actions.py`'s `extend_with_page`) — see the
  class's own docstring for exactly which methods belong here (the ones that mutate the state
  machine's own core fields) versus which don't.
- **`event_log.py`** — `class EventLog`: one run's raw event storage (`record()`, `events`).
  No reaction, no narration, no LLM call, just storage — a genuinely separate concern from stage
  advancement, called from every state-transition method for every real event type this run
  produces.
- **`runs.py`** — the process-wide run registry: which run is current (`current()`), the
  history of every run this process has seen (`list_runs()`, `get_run_events()`), and the two
  genuinely compound operations that need registry-level context (`reset_pipeline()`,
  `resume_run()`, `start_pipeline()`). Deliberately doesn't duplicate any of
  `IntegrationRun`'s own methods as its own proxy functions — callers fetch the real instance
  via `current()` and call its methods directly (`runs.current().review(...)`,
  `runs.current().add_constraint(...)`), so there's exactly one place each operation is
  implemented, not two.
- **`stages/`** — one folder per stage, plus the `STAGE_DESCRIPTIONS`/`stage_agents` lookups
  `IntegrationRun.run_stage()` and orchestrator's system prompt read from. See
  [Stage-based pipeline generation](#stage-based-pipeline-generation) below for the full layout
  and why each stage gets its own folder.
- **`routes/`** — the FastAPI routes (see [API endpoints](#api-endpoints) below), one module per
  the same generic/stage-specific axis `stages/` and `orchestrator/tools/` already split on:
  `core.py` (stage-agnostic pipeline-lifecycle endpoints, plus the busy-guard and `/rerun`
  stage-mismatch validation that used to live in `orchestrator/main.py` before that split — a
  check made in one process before a mutating call to a different process is a real race, not
  just a relocation) and `docs.py` (the docs stage's own extra endpoint).
- **`main.py`** — just app assembly: creates the real `FastAPI` instance and includes both
  routers. Doesn't grow as new stage-specific routes are added, since those land in their own
  `routes/<stage>.py` file instead.

```
main.py ──imports──> routes/
routes/core.py ──imports──> runs.py, pipeline.py, stages/
routes/docs.py ──imports──> runs.py, stages/docs/
runs.py ──imports──> pipeline.py                          (constructs/holds IntegrationRun instances)
pipeline.py ──imports──> event_log.py, stages/
stages/__init__.py ──imports──> stages/<stage>/
stages/pim/, psm/, atl/, acceleo/, generation/ (agent.py)
    ──imports──> clients.ai_layer_client, stages/_shared.py
stages/docs/ (agent.py, actions.py) ──imports──> clients.retrieval_client
stages/serialization/ (agent.py) ──imports──> clients.serialization_agent_client
```

See [ai/CLAUDE.md](../CLAUDE.md) for the step-by-step recipe for adding a new stage-specific
tool or replacing a placeholder stage agent with a real implementation.

## Stage-based pipeline generation

The pipeline turns a platform description into generated CI/CD tooling through seven fixed
stages, in order:

```python
STAGES = ["docs", "serialization", "pim", "psm", "atl", "acceleo", "generation"]
```

Each stage has exactly one agent, looked up directly by stage name, no classification or
selection step. `stages/__init__.py` assembles the lookup from each stage's own folder:

```python
stage_agents = {
    "docs": docs.agent.docs_stage,
    "serialization": serialization.agent.serialization_stage,
    "pim": pim.agent.pim_stage,
    "psm": psm.agent.psm_stage,
    "atl": atl.agent.atl_stage,
    "acceleo": acceleo.agent.acceleo_stage,
    "generation": generation.agent.gen_stage,
}
```

### One folder per stage (`stages/<stage>/`)

- **`stages/docs/agent.py`** and **`stages/serialization/agent.py`** are real agents: the former
  calls `retrieval`'s actual `POST /fetch` via `clients/retrieval_client.py`; the latter calls the
  real, separate [`ai/serialization_agent`](../serialization_agent) service's `POST /serialize`
  via `clients/serialization_agent_client.py` — a multi-step extraction/labeling pipeline, its own
  container since it makes its own outbound call to `pim_agent`.
- **`stages/pim/agent.py`**, **`psm/agent.py`**, **`atl/agent.py`**, **`acceleo/agent.py`**,
  **`generation/agent.py`** are still placeholders standing in for future real per-stage agents,
  each a plain LLM prompt call `(context: dict) -> str` via `clients/ai_layer_client.py`, not
  yet the real MDE toolchain. These placeholder names are deliberately *not* `pim_agent`/
  `psm_agent`: those names belong to the real, separate
  [`ai/pim_agent/`](../pim_agent)/[`ai/psm_agent/`](../psm_agent) services — the only current
  caller of either is `serialization_agent`, for its own concept-labeling, unrelated to the
  `pim`/`psm` stages here.
- **`stages/_shared.py`** — `constraints_note()`, the one real thing the five placeholders
  share, at the `stages/` root rather than duplicated per folder. `stages/docs/agent.py` doesn't
  use it (see its own module docstring for why).
- **`stages/<stage>/__init__.py`** — imports that folder's own `agent.py` (and, for `docs`,
  `actions.py`) submodule so `stages/__init__.py` can reach it by dotted attribute access.
  Imports the MODULE, not a same-named function from within it: a same-named
  `from stages.docs.agent import docs_stage` here would permanently shadow the submodule
  attribute with the function for every later importer (see `stages/__init__.py`'s own
  docstring for the full explanation of that real Python footgun).

**One folder per stage, not a shared module, so replacing a placeholder with a real agent, or
adding that stage's own extra chat tool, is a self-contained change**: `stages/docs/` already
went through the placeholder-to-real transition once (it used to be a function alongside the
placeholders, split into its own file when it went real, then into its own folder once it grew
`actions.py`). When another stage gets a real MDE implementation, the only changes are: rewrite
that one folder's `agent.py`, keep its function name and signature the same, done — nothing
elsewhere in `integration_runner` needs to change, since `pipeline.py` only ever reads
`stages.stage_agents[stage]`, never a specific stage's own module. This isn't speculative
infrastructure: every one of the seven stages is real today (even the five "placeholder" ones
run for real, they just call an LLM prompt instead of a real MDE toolchain), this is just where
the code for each one lives, shaped so the next stage that grows its own extra tool (matching
`docs`'s own `actions.py`) is a new sibling file, not a restructuring.

- **`docs_stage(context)`** — reads `context["seed_url"]`. Calls
  `clients.retrieval_client.fetch_documentation()`, and raises if the crawl found essentially
  nothing useful (confidence below `_DOCS_MIN_CONFIDENCE`, or zero pages fetched successfully).
  Corrections fold into retrieval's own `hint` parameter directly. Short-circuits to canned
  output, skipping retrieval entirely, when either the process-wide `ORCHESTRATOR_STUB_DOCS` env
  var is set or the caller passed `context["mock"]` (the per-run "Mock" checkbox on the
  Start/Retry form) — real crawls are slow enough during local dev that a permanent env var is
  too blunt, this is opt-in per run instead. Not the same thing as `stages/docs/actions.py`'s own
  `extend_with_page()` despite both wrapping retrieval calls — see
  [The docs stage's extra action](#the-docs-stages-extra-action-stagesdocsactionspy) below for
  exactly how and why they differ.
- **`serialization_stage(context)`** — reads `context["docs_output"]` and the run's chosen
  model, and forwards both to the real `serialization_agent` service's `POST /serialize` via
  `clients/serialization_agent_client.py`. No constraints support today: this stage has no
  correction-taking parameter of its own to fold one into.
- **`pim_stage(context)`** — prefers `context["serialization_output"]`, falls back to
  `context["docs_output"]`, then `context["platform_description"]`. Produces a PIM
  (Platform-Independent Model) description.
- **`psm_stage(context)`** — prefers `context["pim_output"]`, falls back to
  `context["docs_output"]`, then `context["platform_description"]`. Produces a PSM
  (Platform-Specific Model) description.
- **`atl_stage(context)`** — reads `context["psm_output"]`. Produces a description of the ATL
  (ATLAS Transformation Language) transformation rules needed to map the platform-independent
  model to that PSM.
- **`acceleo_stage(context)`** — reads `context["atl_output"]`. Produces a description of the
  Acceleo code-generation template needed to turn the transformed model into real pipeline
  configuration files.
- **`gen_stage(context)`** — reads `context["psm_output"]`, `context["atl_output"]`, and
  `context["acceleo_output"]`. Produces a final, concise summary of the whole pipeline plan.

If a stage was previously rejected with a correction, that correction is appended to the
agent's input automatically, so a rerun of the stage takes the human's feedback into account.

A brand-new crawl with different parameters (a different `hint`, excluding known-bad pages, a
narrower/wider crawl) also goes through the normal rerun mechanism, `POST /rerun/docs` (the
`rerun_stage` tool's real target — see `ai/orchestrator/tools/pipeline_control.py`), which
already accepts the docs stage's real structured overrides (`hint`, `exclude_urls`, `max_pages`,
`max_depth`, `force_refresh`). That replaces the docs stage's whole output with a fresh crawl.

### The docs stage's extra action (`stages/docs/actions.py`)

`stages/docs/actions.py`'s `extend_with_page()` is **not** a stage agent — it's not looked up
from the `stage_agents` dict or called by `run_stage()`. It's the real target behind
`POST /docs/extend` (`routes/docs.py`), which orchestrator exposes as an LLM-callable tool
(`add_page_to_docs`, offered only while `docs` is the pending stage — see
`ai/orchestrator/tools/docs.py`) so a chat message can add one specific known page to the docs
stage's current pending output, for when the crawl mostly succeeded but missed one page whose
URL is already known, without redoing the whole crawl.

It wraps the same underlying `clients/retrieval_client.fetch_page()` call, but with a genuinely
different contract from `docs_stage()`'s own crawl, not a naming accident:

| | `stages/docs/agent.py`'s `docs_stage()` | `stages/docs/actions.py`'s `extend_with_page()` |
|---|---|---|
| Called by | `run_stage()`, as part of actually running the docs stage | An LLM tool call or a direct `POST /docs/extend`, any time docs is pending |
| Effect | REPLACES `last_output` with a fresh crawl's full formatted content | APPENDS one page's real content onto whatever `last_output` already has |
| On a failed fetch | Raises — a human should never review a near-empty stage result | Raises too, but doesn't append anything — the existing output is untouched |
| Valid when | Docs is the current stage (dispatched by `run_stage()`, which already only runs the current stage) | Only while docs is still the current, not-yet-approved pending stage — raises `ValueError` otherwise, since there'd be no reviewable output to extend, or it would silently corrupt an already-approved run |

Because `docs_stage()` needs the crawl's raw content to build its own formatted output and
`extend_with_page()` needs the same for the one page it's adding, both do real, meaningful work
with the fetch result — the real difference is *replace* versus *append*, not one being a
lesser echo of the other. Retroactively amending an *already-approved* docs output once later
stages have run is real, bigger future work (it would need to cascade re-runs downstream), not
attempted here.

Nothing here is duplicated between `integration_runner` and `orchestrator`: the real logic (the
retrieval call, appending to `last_output`, recording the event) lives here, in exactly one
place. `orchestrator`'s side
([`clients/integration_runner_client.py`](../clients/integration_runner_client.py)) is a pure
HTTP POST wrapper with no business logic of its own; `orchestrator/tools/docs.py` only adds the
LLM-facing schema (name/description/parameters), a different kind of thing (chat-layer metadata,
not a second implementation).

### `validate()`

`validate(output)` wraps `is_good_enough(output)`: the output must be non-empty and free of
refusal markers (`"I cannot"`, `"I don't know"`, `"I do not know"`) or explicit error markers
(`"an error occurred"`, `"sorry, an error"`). The markers are deliberately narrow phrases, not a
bare `"error"` substring match, generated content that legitimately *discusses* error handling
isn't flagged as a bad response. Applied to every stage's output before a human ever sees it.

### The event log: every real action becomes a real, persisted event

Every `IntegrationRun` instance holds its own `EventLog` (`event_log.py`, exposed as the
`events: list[dict]` property), an ordered, append-only log of everything real that's happened
to this run: `call_started`/`call_completed`/`call_failed` (a stage running),
`review_approved`/`review_rejected` (a human's decision), `constraint_added` (a correction
recorded), and `documentation_extended` (the docs stage's extra add-a-page action). Neither
`EventLog` nor `IntegrationRun.record_event()` (a thin delegate to it) has any concept of
narration — recording just appends the raw fact and returns it. Turning that into a
human-readable comment is `ai/orchestrator/chat_log.py`'s job, done by polling `GET /events`,
never by this module calling out to anything.

`run_stage_async(context)` is the one way a stage ever starts running: spawns a real background
thread, records `call_started`, runs the stage, then records `call_completed`/`call_failed`, and
returns immediately. `busy` is set to `True` synchronously, before the thread even starts, so
`/review`, `/rerun`, `/start`, `/reset`, `/resume`, `/stage/run`, and `/docs/extend` can all
safely check it and return `409` if a stage is genuinely still running — a guard against a
double-click, not a task queue.

### The human review loop

Progress through `STAGES` is tracked by an `IntegrationRun` instance
(`current_stage_index`, `constraints`, `last_context`, `last_output`, `events`, `busy`); a
module-level default instance in `runs.py` (reached via `runs.current()`) backs the REST API.

1. **`run_stage(context)`** — looks up the current stage's agent, calls it, validates the
   output, returns `{"stage": ..., "output": ..., "valid": bool}`. Doesn't advance the pipeline;
   the current stage stays pending until a human reviews it.
2. A human reviews the stage's `output` (via `POST /review/{stage_id}`, below) — optionally
   after adding one more known page via `POST /docs/extend` first, on the docs stage.
3. **Approve** → `record_review()` advances `current_stage_index` and, if there's a next stage,
   returns `{"status": "advanced", "stage": ..., "context": ...}` with that stage's context
   built from `last_context` plus `{stage_id}_output: last_output`, without running it.
   `review()` wraps `record_review()` and, on `"advanced"`, immediately calls
   `run_stage_async()` on that context. If `generation` was just approved, both return
   `{"status": "complete"}`.
4. **Reject** → `add_constraint(stage, correction)` records the correction; the same stage
   stays current and must be rerun via `POST /rerun/{stage_id}`, which reads the live
   `constraints` dict fresh, so the just-added correction is folded in automatically.

## API endpoints

The service starts at `http://localhost:8050`.

### `routes/core.py` — generic, stage-agnostic

| Endpoint | Notes |
|---|---|
| `GET /events?since_index=&run_id=` | Raw events (no narration) for the current run or a specific past one; includes `run_id` explicitly, so orchestrator can key its own chat log by it. |
| `GET /status` | Cheap `{current_stage, busy, model, run_id, is_current}`, no events array. |
| `GET /stages` | Static `{stages: [...], descriptions: {...}}`, orchestrator's real source for its system prompt and tool schemas. |
| `GET /runs` | Every run this process has seen, newest first. |
| `POST /start` | Resets the pipeline and starts the docs stage. `409` if busy. |
| `POST /reset` | Replaces the current run with a fresh, blank one. `409` if busy. |
| `POST /resume/{run_id}` | Makes a past run current again. `404` unknown run, `409` if busy. |
| `POST /review/{stage_id}` | Records a review decision; approving starts the next stage. `400` on stage mismatch or missing correction, `409` if busy. |
| `POST /rerun/{stage_id}` | Reruns the current stage. `400` on stage mismatch, or overrides on a non-docs stage; `409` if busy. Accepts the docs stage's real structured overrides (`hint`, `exclude_urls`, `max_pages`, `max_depth`, `force_refresh`, `mock`). |
| `POST /constraint/{stage}` | Records a correction without rerunning — the `add_constraint` tool's real target. |
| `POST /stage/run` | Runs the current stage with new context — the `run_stage` tool's real target. `409` if busy. |
| `POST /model` | Changes the model for the rest of the run. |

### `routes/docs.py` — docs-stage-specific

| Endpoint | Notes |
|---|---|
| `POST /docs/extend` | Fetches one specific page for real and appends it to the docs stage's current pending output — the `add_page_to_docs` tool's real target. `400` if docs isn't the current pending stage, or if the fetch itself failed; `409` if busy. |

## Setup

```bash
pip install -r requirements.txt
cp .env.example .env
# AI_LAYER_URL defaults to http://localhost:8000, RETRIEVAL_URL to
# http://localhost:8010, SERIALIZATION_AGENT_URL to http://localhost:8060,
# if unset — override only if any of them run somewhere else.
```

## Run

```bash
uvicorn main:app --reload --port 8050
```

Because `main.py` imports its sibling `clients/` package, which lives outside this folder,
`PYTHONPATH` needs to include the parent `ai/` directory too — the same reason
`integration_runner/Dockerfile` sets `ENV PYTHONPATH=/app/integration_runner:/app` for the real
deployed container:

```bash
# from ai/integration_runner/
PYTHONPATH=.. uvicorn main:app --reload --port 8050
```

## Test

```bash
cd integration_runner
pytest
```

`pytest.ini` sets `pythonpath = . .. tests` (the last entry lets test files nested under
`tests/stages/` still resolve `tests/helpers.py`/`tests/conftest.py`, which pytest's own
per-file rootless import resolution wouldn't otherwise reach). No real network calls —
`ai_layer_client.chat` / `retrieval_client.httpx` are mocked. `record_event()` has no reactor
concept, so exactly one real `chat()` call happens per stage transition (the stage agent's own),
never one for narration too — narration lives entirely in `orchestrator/chat_log.py`.

- **`tests/test_pipeline.py`** — the state machine: `STAGES`, `validate()`, `IntegrationRun`'s
  `run_stage`/`rerun`/`advance_stage`/`add_constraint`/`review`/`record_review`/`record_event`/
  `run_stage_async`.
- **`tests/test_event_log.py`** — `EventLog`'s own storage contract, independent of
  `IntegrationRun`.
- **`tests/test_runs.py`** — the run registry: run identity/history, `start_pipeline()`,
  `current()`.
- **`tests/stages/test_docs_agent.py`** — the real `docs_stage` agent (`stages/docs/agent.py`).
- **`tests/stages/test_docs_actions.py`** — `extend_with_page()`'s real appending behavior and
  event recording, against a real `IntegrationRun` (`stages/docs/actions.py`).
- **`tests/stages/test_serialization_agent.py`** — the `serialization_stage` wrapper
  (`stages/serialization/agent.py`); the real extraction/labeling logic it calls out to has its
  own tests in `ai/serialization_agent/tests/test_serialization_agent.py`.
- **`tests/stages/test_placeholder_stages.py`** — the five placeholder agents
  (`stages/pim/agent.py` through `stages/generation/agent.py`).
- **`tests/stages/test_stages_registry.py`** — `stages/__init__.py`'s own `stage_agents`/
  `STAGE_DESCRIPTIONS` assembly.

`tests/routes/` doesn't exist yet — `main.py`'s own endpoints (`routes/core.py`, `routes/docs.py`)
have no dedicated test file in this service's own suite today, a real, pre-existing gap (they're
only indirectly exercised via `orchestrator/tests/test_main.py`'s real in-process ASGI calls).
When that coverage gets written, it belongs there, mirroring `routes/`'s own layout.
