# orchestrator

The chat and REST gateway for MDDOAI: the one thing `chat-ui` talks to, and the only place LLM
tool-calling and narration live. It has no knowledge of the pipeline's own state machine at
all — starting a stage, reviewing it, adding a constraint, or adding a page to the docs stage's
output are all real HTTP calls to [`ai/integration_runner/`](../integration_runner) (see
[`clients/integration_runner_client.py`](../clients/integration_runner_client.py)), never a
Python import. A human clicking a button and an LLM calling a tool end up making the exact same
HTTP call to `integration_runner`; the only thing this service adds on top is turning
`integration_runner`'s raw, structured facts into a running chat conversation.

Every real fact `integration_runner` records (a stage starting/completing/failing, a review
decision, a constraint, a page added to the docs stage's output) gets a short narrated comment from "the Orchestrator"
automatically, the next time something polls `GET /events`. That same reply mechanism,
`react_to_event()`, is also what powers `POST /message`: a free-form human message is handled by
the exact same function, just with a set of real tools attached, so it can decide to act instead
of only commenting. See [The reply mechanism](#the-reply-mechanism-react_to_event--send_message)
below.

For MVP, every stage transition is human-driven: a client calls `POST /start`, then reviews
each stage's output via `POST /review/{stage_id}` (approve or reject with a correction), or asks
for a stage to be redone via `POST /rerun/{stage_id}`. `POST /message`'s tool-calling path exists
and is fully tested, but isn't part of that primary flow yet, it's the intended upgrade path for
the Orchestrator to eventually drive the pipeline autonomously.

## Module layout

- **`main.py`** — the FastAPI routes (see [API endpoints](#api-endpoints-mainpy) below). Every
  endpoint is a thin forwarding call to `clients/integration_runner_client.py` or
  `clients/ai_layer_client.py`, plus one registered exception handler
  (`IntegrationRunnerError`) that reconstructs `integration_runner`'s own real status code and
  message — there's no validation logic duplicated here, `integration_runner` is the one place
  that enforces busy guards and stage staleness checks, since a check made in this process
  before a mutating call to a different one would be a real race, not just a relocation.
- **`assistant.py`** — the one reply mechanism: `react_to_event()` (narration) and
  `send_message()` (a human's free-form message, the tools-enabled path through the same
  function).
- **`chat_log.py`** — this run's chat transcript: mirrors `integration_runner`'s raw events in
  immediately, narrates them in the background, and holds `send_message()`'s own turns. See
  [The chat log](#the-chat-log-chat_logpy) below.
- **`event_summarization.py`** — `summarize_for_reaction()`/`summarize_history()`: truncating
  long event fields before they're fed into an LLM prompt. Its own module, not part of
  `chat_log.py`, because both `chat_log.py`'s own narration and `assistant.py`'s `send_message()`
  need the identical truncation, and neither one owns "how we build a prompt from event history"
  more than the other.
- **`pipeline_tools.py`** — the Orchestrator's system prompt template.
- **`tools/`** — the declared LLM tools, grouped by what they operate on:
  `pipeline_control.py` (generic, stage-agnostic tools) and `docs.py` (docs-stage-specific
  tools), aggregated by `tools/__init__.py`'s `get_tools()`. Split into a package, not one flat
  file: these are already two distinct, real groups today, mirrored on `integration_runner`'s
  own side by `stages/<stage>/actions.py` and `routes/<stage>.py` (see
  [ai/CLAUDE.md](../CLAUDE.md) for the full step-by-step recipe for adding a new one). A future
  agent with its own real tools (e.g. a validation agent, once a validation service's
  already-real endpoints get wired in) adds one new sibling module here, not a growing edit to
  an existing one.
- **`tool_calling.py`** — a small, generic, reusable tool-calling engine with zero knowledge of
  MDDOAI, pipelines, or stages, it would work unchanged in a different project. Stays nested
  here rather than becoming its own top-level package: its only real callers (`assistant.py`,
  `tools/pipeline_control.py`) already live inside this same service.

```
main.py ──imports──> assistant.py, chat_log.py, clients.ai_layer_client, clients.integration_runner_client
assistant.py ──imports──> chat_log.py, event_summarization.py, pipeline_tools.py, tools, tool_calling.py, clients.ai_layer_client, clients.integration_runner_client
chat_log.py ──imports──> event_summarization.py, clients.integration_runner_client
event_summarization.py                                    (imports none of the above)
pipeline_tools.py ──imports──> tools                      (stage_metadata(), lazily cached)
tools/__init__.py ──imports──> tools.pipeline_control, tools.docs, clients.integration_runner_client
tools/pipeline_control.py, tools/docs.py ──imports──> tool_calling.py, clients.integration_runner_client
tool_calling.py                                            (imports none of the above)
```

No import at all, in this production module graph, on `ai/integration_runner`'s Python internals,
`ai/clients/retrieval_client.py`, or `ai/serialization_agent` — those all moved into
`integration_runner`'s own container when it became a separate service (see
`integration_runner/README.md`'s own module layout). The one exception is
`tests/test_main.py`'s own test-isolation fixture, which does import `integration_runner`
directly to reset its run registry between tests — see [Test](#test) below for why that's a
test-only convenience, not a production dependency.

## The reply mechanism (`react_to_event()` / `send_message()`)

There's one reply function, not a separate "narrator" and "judge": `assistant.react_to_event(event,
history, use_tools=False)`. With no tools it can only comment on what happened (what
`chat_log.py` calls automatically for every new raw event it notices from `integration_runner`);
given tools, it can also decide to act (what `send_message()` calls for a human's free-form
message). Giving it tools is the *only* difference between narrating and acting, same function,
same system prompt, same dispatch code.

The system prompt (`pipeline_tools.py`) and the tools (`tools/`) aren't hardcoded inside
`assistant.py`/`tool_calling.py`: adding a new ability means adding one `tool_calling.Tool(...)`
entry, referencing a real function (usually an `integration_runner_client` function directly),
nothing else changes. A `Tool` bundles its schema and its real implementation as a single
object, so there's no separate config file and implementation dict that could drift apart.

A tool's optional `stages` key restricts when it's offered, only while the pipeline's current
stage is in that list. The pipeline-control tools have no `stages` key, available regardless of
stage. The docs-specific one is scoped to `stages: [docs]`, wrapping `integration_runner`'s own
`/docs/extend` endpoint, which in turn wraps `retrieval`'s real single-page fetch capability:

- **`run_stage(context)`** — start the current stage running (in the background) with new input.
- **`rerun_stage(hint=None, exclude_urls=None, max_pages=None, max_depth=None, force_refresh=None, mock=None)`**
  — redo the current stage, reusing its last context plus any constraints. Its schema takes no
  `stage_id` at all, but `integration_runner`'s real `POST /rerun/{stage_id}` needs one in its
  path, so this is one of the two tools with a real local wrapper (`tools/pipeline_control.py`'s
  `_rerun_stage_tool`, `start_pipeline`'s own `_start_pipeline_tool` below is the other): it
  reads the current stage fresh via `GET /status` first, the same real check a direct REST
  caller's `stage_id` gets validated against, then forwards only the override fields the LLM
  actually supplied. The optional parameters are real, structured docs-stage overrides the REST
  endpoint has always accepted (this schema just exposes them to the LLM too, so a
  chat-triggered rerun has the same real steering ability a direct REST/manual caller already
  had) — `integration_runner` rejects them with a real error on any stage but
  `docs`, surfaced as this call's own `"result": {"error": ...}`. This REPLACES the docs stage's
  whole current output with a fresh crawl; it does not add to what's already there.
- **`stage_result(stage_id, approved, correction=None)`** — record a review decision; approving
  advances the pipeline and starts the next stage immediately, same as `POST /review` does.
- **`add_constraint(stage, constraint)`** — record a correction without rerunning yet.
- **`start_pipeline(platform_description, seed_url, model=None, hint=None, exclude_urls=None, max_pages=None, max_depth=None, force_refresh=None, mock=None)`**
  — reset the pipeline and start the `docs` stage fresh for a *different* platform. The optional
  parameters steer the very first real crawl the same way `rerun_stage`'s own do for a later
  one (including `mock`, for a fast canned run with no real crawl) — real REST fields
  `POST /start` has always accepted, assembled from the tool's own flat arguments by
  `tools/pipeline_control.py`'s `_start_pipeline_tool` wrapper, the same pattern
  `_rerun_stage_tool` uses.
- **`add_page_to_docs(url, force_refresh=False)`** (`docs` only) — fetch one specific page the
  human already knows the URL of, and APPEND its real content onto the docs stage's current
  pending output, for when the crawl mostly succeeded but missed one known page. Does not
  replace or redo the crawl (use `rerun_stage` for that); only valid while docs is still
  pending, not yet approved.

Because every tool that starts a stage running goes through `integration_runner`'s own REST
endpoints, the same background-thread path a direct REST call uses, `send_message()` itself only
ever makes one `chat()` call, the routing decision. It never blocks for a stage's duration.

`send_message(user_message)` records the human's message as a `user_message` turn, calls
`react_to_event(..., use_tools=True)`, records each dispatched tool call as its own
`tool_called` turn (see [The chat log](#the-chat-log-chat_logpy) below for why), records the
reply as its own `"message"` turn, and returns:

- **One or more tool calls** → `{"tool_called": <last tool's name>, "result": <last tool's
  result>, "steps": [{"tool", "arguments", "result"}, ...]}`. For a tool that starts a stage
  running, `result` is `{"status": "started", "stage": ...}`; the stage's actual output shows up
  in `GET /events` once `integration_runner`'s background thread finishes, not in this response.
- **No tool call** → `{"tool_called": None, "result": None, "message": <the model's own
  clarifying question, or a generic fallback>}`. No state is mutated.

A hallucinated/unknown tool name doesn't crash `send_message()`, that step's `"result"` becomes
`{"error": "Unknown tool: <name>"}` instead. A tool call that WOULD mutate state while
`integration_runner` is busy gets the real `409` surfaced the same way, in that step's own
`"result"` — `/message` itself has no busy guard (unlike every other mutating endpoint below): a
message that doesn't need a tool should still get a reply even while a stage is running.

## The chat log (`chat_log.py`)

`integration_runner` produces raw pipeline facts and knows nothing about chat or narration —
deliberately: a human clicking a button and an LLM calling a tool are indistinguishable to it.
This module is the one place that turns those facts into a running conversation, per run:

1. **`GET /events`** (via `chat_log.get_events()`) fetches `integration_runner`'s raw events for
   a run, and immediately, synchronously, mirrors any new ones into this run's own `ChatLog`
   (an ordered, append-only list) — visible on this very call, even before any comment exists.
2. Narrating those new events (a real LLM call) happens **in the background**, one at a time, in
   order, and never blocks the request: a comment on the very latest event may only show up on
   the next poll.
3. `send_message()`'s own turns (the human's message, each dispatched tool call, the reply) are
   appended directly to the same `ChatLog`, under the same lock — genuinely one merged, ordered
   transcript per run, not separate streams stitched together after the fact.

An earlier design considered merging `integration_runner`'s raw events with locally generated
narration by sorting on `timestamp`. Rejected: a narration's timestamp comes from this process's
clock, the raw event's from `integration_runner`'s — real clock skew between the two containers
could sort a comment before the thing it's commenting on. Keeping one single, local, append-only
list per run side-steps that entirely.

This chat log is **orchestrator-local and lost on a restart**, even though `integration_runner`
itself keeps a run's raw history across one — a real, accepted failure mode this split
introduces (worst case: a burst of re-narration the next time this run is polled, not a crash),
the same MVP, in-memory-only tradeoff `integration_runner`'s own run registry already accepts.

## API endpoints (`main.py`)

The service starts at [http://localhost:8001](http://localhost:8001) (port 8000 is `ai-layer`'s,
8050 is `integration_runner`'s).

Every endpoint below that runs a stage returns `202` and starts the work running in the
background on `integration_runner`'s side, it does not wait for the stage to finish. Poll
`GET /events` to see progress. A downstream failure surfaces as a `call_failed` event via
`GET /events`, not an HTTP `500` — `500` is reserved for a genuine unhandled server bug (or, for
`/message`, a failure in its own synchronous routing call).

### `POST /start`

Resets the pipeline and starts the first stage (`docs`) running against a platform description
and its real documentation URL. `409` if a stage is currently running (`integration_runner`'s
own busy guard, surfaced here via the shared `IntegrationRunnerError` handler).

`hint`, `exclude_urls`, `max_pages`, `max_depth`, and `force_refresh` are optional, the same real
retrieval parameters `/rerun/docs`'s `overrides` accepts. `mock` is also optional: when set, the
docs stage skips the real crawl entirely and returns canned placeholder output instantly.

Request (minimal):
```json
{ "platform_description": "TeamCity: A CI/CD platform using Kotlin DSL", "seed_url": "https://www.jetbrains.com/help/teamcity/" }
```

Response (`202`), immediately, before the fetch has necessarily finished:
```json
{ "status": "started", "stage": "docs" }
```

### `POST /reset`

Replaces the current run with a fresh, blank one: the empty-state counterpart to `/start`. The
old run isn't deleted, `GET /runs` still lists it and `POST /resume/{run_id}` can bring it back.
`409` if a stage is currently running.

### `POST /resume/{run_id}`

Makes a past run current again, picking up exactly where it left off. `404` for an unknown
`run_id`. `409` if a stage is currently running.

### `GET /events`

Returns this run's merged chat log (mirrored raw events, their narration, and `send_message()`
turns). `?since_index=N` returns only entries from index `N` onward, for incremental polling.
`?run_id=` reads a specific (possibly past) run instead of the current one.

Response:
```json
{
  "run_id": "a1b2c3d4e5f6...",
  "events": [
    { "type": "call_started", "stage": "docs", "data": {"seed_url": "..."}, "timestamp": 1234.5 },
    { "type": "message", "stage": "docs", "text": "Fetching TeamCity's documentation now.", "model": "gemini-flash", "timestamp": 1234.6 },
    { "type": "call_completed", "stage": "docs", "data": {"stage": "docs", "output": "...", "valid": true}, "timestamp": 1240.1 },
    { "type": "message", "stage": "docs", "text": "Retrieval completed: found 6 pages, confidence 0.82.", "model": "gemini-flash", "timestamp": 1240.2 }
  ],
  "current_stage": "docs",
  "busy": false,
  "model": null,
  "is_current": true
}
```

### `GET /runs`

Every run this process has seen, newest first, proxying `integration_runner`'s own `GET /runs`.

### `POST /review/{stage_id}`

Records a human's decision on the named stage's most recent output. `400` if `stage_id` doesn't
match the current pending stage, or if `correction` is missing on a rejection; `409` if busy.

Request (approve): `{ "approved": true }` → Response (`202`): `{ "status": "started", "stage": "psm" }`
(or `200`, `{ "status": "complete" }`, on the last stage).

Request (reject): `{ "approved": false, "correction": "Include a lint stage before build" }` →
Response (`200`, nothing started): `{ "status": "rerun", "stage": "psm" }`.

### `POST /rerun/{stage_id}`

Starts the current pending stage running again, reusing its last context and picking up any
constraints recorded since then, unless overrides are given. `400` on stage mismatch, or
overrides on any stage but `docs`; `409` if busy.

### `POST /message`

Lets an LLM decide which pipeline action a free-form human message maps to (see
[The reply mechanism](#the-reply-mechanism-react_to_event--send_message) above), executes it,
and returns whatever `send_message()` returns directly. No busy guard (see above for why).

Request:
```json
{ "message": "the ATL stage output is wrong, please redo it with a lint step added" }
```

Response — here the model called `add_constraint` then `rerun_stage`:
```json
{
  "tool_called": "rerun_stage",
  "result": { "status": "started", "stage": "atl" },
  "steps": [
    { "tool": "add_constraint", "arguments": { "stage": "atl", "constraint": "Add a lint step to validate the ATL transformation rules" }, "result": null },
    { "tool": "rerun_stage", "arguments": {}, "result": { "status": "started", "stage": "atl" } }
  ]
}
```

### `GET /providers`

Proxies `ai-layer`'s real `GET /providers`, so the model picker shows real, current provider/tier
options.

### `POST /model`

Changes the model for the rest of the run, not just what `/start` chose.

## Setup

```bash
pip install -r requirements.txt
cp .env.example .env
# AI_LAYER_URL defaults to http://localhost:8000, INTEGRATION_RUNNER_URL to
# http://localhost:8050, if unset — override only if either runs somewhere
# else (e.g. a different port, or a Docker Compose service name).
```

This service's production code has no import on `ai-layer`'s or `integration_runner`'s source,
only real HTTP calls to each (see [Test](#test) below for the one test-only exception). It does
need both actually *running* and reachable: narration, `send_message()`'s
routing decision, and `/providers` all call `ai-layer`'s `/chat`/`/providers` directly; every
other real capability (running a stage, reviewing it, adding a page to the docs stage's output)
is a real HTTP call to `integration_runner`.

## Run

```bash
uvicorn main:app --reload --port 8001
```

`main.py` has no import on any sibling folder outside this one anymore (see
[Module layout](#module-layout) above) — unlike before `integration_runner` became its own
service, a plain local run doesn't need `PYTHONPATH` extended to the parent `ai/` directory.

## Test

```bash
cd orchestrator
pytest
```

No real network calls: `clients.ai_layer_client`'s and `clients.integration_runner_client`'s
httpx calls are mocked. `tests/test_main.py` is the one file that goes further — it routes
`integration_runner_client`'s calls to a REAL, in-process `integration_runner.main.app` via
`httpx.ASGITransport`, exercising `integration_runner`'s actual validation/busy-guard code, not
a hand-mocked guess at what it would say. Its `real_integration_runner` fixture also imports
`integration_runner.runs` directly, to reset its run registry between tests — the same
test-isolation pattern `integration_runner`'s own test suite uses on itself. This is the one
place this service's test code imports `integration_runner`'s Python internals: it exists only
to give each test a clean run registry, every actual assertion still goes through the real
ASGI-routed HTTP call above, never a direct internals call.

- **`tests/test_assistant.py`** — `react_to_event()`/`send_message()`, tool dispatch for every
  declared tool, and the multi-step `add_constraint` → `rerun_stage` sequence.
- **`tests/test_chat_log.py`** — the narration mechanism: synchronous mirroring, background
  narration order and history, failure fallback, per-run isolation, `since_index` slicing.
- **`tests/test_event_summarization.py`** — `summarize_for_reaction()`/`summarize_history()`'s
  own truncation contract, independent of `chat_log.py`/`assistant.py`.
- **`tests/test_pipeline_tools.py`** — the system prompt template.
- **`tests/test_tools.py`** — `tools/`'s aggregation, stage-scoping, and caching; the
  `rerun_stage` wrapper's real behavior.
- **`tests/test_main.py`** — every endpoint above, against a real in-process
  `integration_runner` (see above) plus mocked `ai_layer_client`.

`ai-layer`'s and `integration_runner`'s own test suites are separate and cover only their own
endpoints, run each from its own directory, not together (each project has its own `main.py`,
running them in one pytest session causes a module-name collision).
