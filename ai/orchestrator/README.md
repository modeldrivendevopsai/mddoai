# orchestrator

Stage-based pipeline generation for MDDOAI: walks a platform description through a fixed
`docs → psm → atl → acceleo → generation` pipeline, with a human reviewing and approving each
stage's output before the next one runs. It's a standalone FastAPI service, independent of
`ai-layer` and `retrieval` at the code level, that gets its LLM completions by calling
`ai-layer`'s `POST /chat` endpoint over HTTP, and the `docs` stage's real documentation by
calling `retrieval`'s `POST /fetch`, the same way any other client of those services would.

Every real action (a stage running, a human's review decision) is recorded as a structured
event, and recording an event automatically triggers a short reply from "the Orchestrator"
commenting on what happened. That reply mechanism, `react_to_event()`, is also what powers
`POST /nudge`: a free-form human message is handled by the exact same function, just with a
set of real tools attached, so it can decide to act instead of only commenting. See
[The reply mechanism](#the-reply-mechanism-react_to_event--nudge) below.

For MVP, every stage transition is human-driven: a client calls `POST /start`, then reviews
each stage's output via `POST /review/{stage_id}` (approve or reject with a correction), or
asks for a stage to be redone with adjusted parameters via `POST /rerun/{stage_id}`.
`POST /nudge`'s tool-calling path exists and is fully tested, but isn't part of that primary
flow yet, it's the intended upgrade path for the Orchestrator to eventually drive the pipeline
autonomously.

## Module layout

Six Python modules, each with one job, arranged so a REST call (a human clicking a button)
and an LLM tool call (`/nudge`) end up running the exact same code, not two parallel
implementations of "approve a stage" or "rerun a stage":

- **`orchestrator.py`** — the pipeline state machine: `STAGES`, the event log, shared
  HTTP-client infra (`chat()`, `fetch_documentation()`, `fetch_page()`), and the plain
  operations (`start_pipeline`, `review`, `rerun_stage`, ...) that both `main.py`'s endpoints
  and `pipeline_tools.py`'s tools call directly. Has no concept of "a tool" or LLM tool-calling
  at all; the only thing it exposes to the rest of the reply mechanism is `set_reactor(fn)`, a
  blank hook some other module fills in. Does not import `stage_agents.py` at module level
  (that would be circular, `stage_agents.py` needs this module already loaded), `run_stage()`
  does a local, deferred import instead.
- **`stage_agents.py`** — the six stage agents (`docs_agent`, `pim_agent`, `psm_agent`,
  `atl_agent`, `acceleo_agent`, `gen_agent`), their prompts, and the `stage_agents` lookup dict.
  Split out from `orchestrator.py` because this content is volatile in a way the state machine
  isn't: every agent but `docs_agent` is an explicit placeholder standing in for a future real
  per-stage implementation, each likely to be replaced on its own schedule.
- **`tool_calling.py`** — a small, generic, reusable LLM tool-calling reply engine (a `Tool`
  dataclass bundling a schema with its real implementation, plus the code that turns a system
  prompt + a list of `Tool`s into a reply). Has zero knowledge of MDDOAI, pipelines, or stages,
  it would work unchanged in a different project.
- **`pipeline_tools.py`** — MDDOAI's actual abilities: the system prompt and the 7 declared
  `Tool`s, each one wrapping a real function from `orchestrator.py` or `stage_agents.py`. This
  is the file to open to see what the Orchestrator can do.
- **`assistant.py`** — the two entry points that actually produce a reply: `react_to_event()`
  (narration) and `nudge()` (a human's free-form message). Composes the modules above; none of
  them import this file or each other except as described.
- **`main.py`** — the FastAPI routes. Imports plain operations from `orchestrator.py` for the
  button-driven endpoints, and `assistant.nudge` for the one `/nudge` endpoint. Explicitly
  wires `orchestrator.set_reactor(assistant.react_to_event)` at startup (not as a side effect
  of an import elsewhere), so narration only ever starts working because of a line you can see.

```
main.py ──imports──> orchestrator.py   (plain operations: start_pipeline, review, rerun_stage, ...)
main.py ──imports──> assistant.py      (nudge)
assistant.py ──imports──> orchestrator.py, pipeline_tools.py, tool_calling.py
pipeline_tools.py ──imports──> orchestrator.py, stage_agents.py, tool_calling.py
stage_agents.py ──imports──> orchestrator.py
orchestrator.py ──imports──> stage_agents.py locally, inside run_stage() only (breaks the cycle)
tool_calling.py                        (imports none of the above)
```

## Stage-based pipeline generation

The pipeline turns a platform description into generated CI/CD tooling through six fixed
stages, in order:

```python
STAGES = ["docs", "pim", "psm", "atl", "acceleo", "generation"]
```

Each stage has exactly one agent, looked up directly by stage name, no classification or
selection step:

```python
stage_agents = {
    "docs": docs_agent,
    "pim": pim_agent,
    "psm": psm_agent,
    "atl": atl_agent,
    "acceleo": acceleo_agent,
    "generation": gen_agent,
}
```

### The agents

All six agents, their prompts, and the `stage_agents` dict above live in `stage_agents.py`, not
`orchestrator.py`. `docs_agent` is a real agent: it calls `retrieval`'s actual `POST /fetch` and
returns the crawled documentation. `pim_agent`, `psm_agent`, `atl_agent`, `acceleo_agent`, and
`gen_agent` are placeholders standing in for future real per-stage agents, each is a plain LLM
prompt call `(context: dict) -> str`, not yet the real MDE toolchain.

- **`docs_agent(context)`** — reads `context["seed_url"]` (the platform's real documentation
  URL). Calls `orchestrator.fetch_documentation()` (retrieval's `POST /fetch`), and raises if
  the crawl found essentially nothing useful (confidence below `_DOCS_MIN_CONFIDENCE`, or zero
  pages fetched successfully). Corrections fold into retrieval's own `hint` parameter directly,
  no translation needed, retrieval's `/fetch` already accepts free text there as a retry lever.
- **`pim_agent(context)`** — prefers `context["docs_output"]` (the real fetched documentation)
  when present, falls back to `context["platform_description"]` for a direct call without it
  (e.g. a unit test). Produces a PIM (Platform-Independent Model) description: the platform's
  CI/CD concepts (jobs, stages, triggers, artifacts, agents/runners) expressed in MDDOAI's
  platform-independent metamodel terms, without committing to any one platform's syntax yet.
- **`psm_agent(context)`** — prefers `context["pim_output"]`, falls back to
  `context["docs_output"]`, then `context["platform_description"]` for a direct call without
  either. Produces a PSM (Platform-Specific Model) description: the same concepts expressed in
  MDDOAI's platform-specific metamodel terms.
- **`atl_agent(context)`** — reads `context["psm_output"]`. Produces a description of the ATL
  (ATLAS Transformation Language) transformation rules needed to map the platform-independent
  model to that PSM: rule names, source/target patterns, and mapping logic.
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
a bare `"error"` substring match, generated content that legitimately *discusses* error
handling (e.g. an Acceleo template's error-handling block) isn't flagged as a bad response.
`validate()` is applied to every stage's output before a human ever sees it.

### The event log and background execution

Every `Orchestrator` instance keeps `self.events: list[dict]`, an ordered log of everything
that's happened. `record_event(event_type, stage, data)` appends a structured event
(`call_started`, `call_completed`, `call_failed`, `review_approved`, `review_rejected`), then
reacts to it (via whatever `set_reactor()` wired in) and appends that reply too, as its own
`"message"` event, a side effect of recording, not a separate step callers trigger themselves.
`GET /events` exposes the full log (or a slice via `?since_index=`).

`run_stage_async(context)` is the one way a stage ever starts running: it spawns a real
background thread, records `call_started`, runs the stage, then records
`call_completed`/`call_failed`, and returns immediately, it does not wait for the thread.
`Orchestrator.busy` is set to `True` synchronously, before the thread even starts, so nothing
that calls `run_stage_async()` can ever observe a run that's already been triggered as "not
busy" — `/review`, `/rerun`, and `/nudge` all return `409` if called while busy, a guard
against a single user double-clicking, not a task queue. Both `main.py`'s endpoints and
`pipeline_tools.py`'s tools call `run_stage_async()` (directly, or via `review()`/
`rerun_stage()`/`start_pipeline()`, which call it internally) — there's exactly one code path
that starts a stage running, not one per caller, which is also why a tool dispatched from
`/nudge` returns as fast as a REST call does, instead of blocking for the stage's full
duration.

### The human review loop

Progress through `STAGES` is tracked by an `Orchestrator` instance (`current_stage_index`,
`constraints`, `last_context`, `last_output`, `events`, `busy`); a module-level default
instance backs the plain function API below.

1. **`run_stage(context)`** — looks up the current stage's agent via `stage_agents`, calls it,
   validates the output, and returns `{"stage": ..., "output": ..., "valid": bool}`. It also
   remembers `context` as `last_context` and the output as `last_output`. This does *not*
   advance the pipeline; the current stage stays pending until a human reviews it. This is the
   only stage-running function that's still fully synchronous, `run_stage_async()` (above)
   wraps it for every real caller.
2. A human reviews the stage's `output` (via `POST /review/{stage_id}`, below).
3. **Approve** → `record_review()` advances `current_stage_index` and, if there's a next
   stage, returns `{"status": "advanced", "stage": ..., "context": ...}` with that stage's
   context built from `last_context` plus `{stage_id}_output: last_output` (so context
   accumulates every prior stage's output as the pipeline progresses), without running it.
   `review()` wraps `record_review()` and, on `"advanced"`, immediately calls
   `run_stage_async()` on that context, returning `{"status": "started", "stage": ...}`
   instead. If `generation` was the stage just approved, there's no next stage and both
   functions return `{"status": "complete"}`.
4. **Reject** → `add_constraint(stage, correction)` records the human's correction in
   `constraints[stage]`; the *same* stage stays current and must be rerun via
   `POST /rerun/{stage_id}`, which calls `run_stage_async(last_context)` again, since
   `run_stage()` always reads the live `constraints` dict (not a snapshot taken at call time),
   the just-added correction is folded into the agent's input on that rerun automatically.

`record_review(stage_id, approved, correction=None)` validates first: `stage_id` must match
the actual current pending stage, and `correction` is required when `approved` is `False`, a
`ValueError` otherwise. This validation is enforced in the `Orchestrator` method itself, not
just at the REST layer, so it can't be silently bypassed by a caller that doesn't go through
`main.py`'s endpoint.

`reset_pipeline()` replaces the default `Orchestrator` with a fresh, blank one, used to start a
new run. The old instance isn't discarded, it stays in `_runs` (see `list_runs()` above) so it
can still be viewed, or made current again via `resume_run(run_id)`, the counterpart that swaps
`_default` back to an existing instance instead of a new one, picking up its progress,
constraints, and events exactly as they were.

## The reply mechanism (`react_to_event()` / `nudge()`)

There's one reply function, not a separate "narrator" and "judge": `assistant.react_to_event(event,
history, use_tools=False)`. With no tools it can only comment on what happened (what
`record_event()` calls automatically for every real event, via the `set_reactor()` hook);
given tools, it can also decide to act (what `nudge()` calls for a human's free-form message).
Giving it tools is the *only* difference between narrating and acting, same function, same
system prompt, same dispatch code.

Both the system prompt and the 7 tools live in `pipeline_tools.py`, not hardcoded inside
`assistant.py`/`tool_calling.py`: adding a new ability means adding one `tool_calling.Tool(...)`
entry there, referencing a real function (usually already living in `orchestrator.py`), nothing
else changes. A `Tool` bundles its schema and its real implementation as a single object, so
there's no separate config file and implementation dict that could drift apart, unlike an
earlier version of this design that kept them in two places and needed a startup check just to
catch when they disagreed.

A tool's optional `stages` key restricts when it's offered, only while the pipeline's current
stage is in that list. The five pipeline-control tools below have no `stages` key, they're
available regardless of stage, since they operate on "whichever stage is current." Two more
tools are scoped to `stages: [docs]`, wrapping `retrieval`'s own real capabilities directly:

- **`run_stage(context)`** — start the current stage running (in the background) with new
  input.
- **`rerun_stage()`** — redo the current stage running (in the background), reusing its last
  context plus any constraints.
- **`stage_result(stage_id, approved, correction=None)`** — record a review decision;
  approving advances the pipeline and starts the next stage running (in the background)
  immediately, same as `POST /review` approving does.
- **`add_constraint(stage, constraint)`** — record a correction without rerunning yet;
  typically followed immediately by `rerun_stage()` when the user wants a fix applied now.
- **`start_pipeline(platform_description, seed_url, model=None, docs_options=None)`** — reset the
  pipeline and start the `docs` stage running (in the background) fresh for a *different*
  platform, discarding all current progress and constraints. `docs_options` is the same shape
  `rerun()`'s overrides accepts for the docs stage (`hint`, `exclude_urls`, `max_pages`,
  `max_depth`, `force_refresh`), settable at start time too, not only reachable via a retry.
- **`fetch_documentation(url, hint=None, exclude_urls=None, max_pages=None, max_depth=None, force_refresh=None)`**
  (`docs` only) — wraps `retrieval`'s real `POST /fetch` directly, for steering or narrowing a
  fresh crawl without advancing the pipeline. Returns a summary (page count, confidence,
  pending links), not the full crawled content.
- **`fetch_page(url, force_refresh=False)`** (`docs` only) — wraps `retrieval`'s real
  `POST /fetch/page`, fetching one specific known page directly rather than re-crawling. Use
  this when the human names a specific missing page, `fetch_documentation` for a broader
  request.

Because every tool that starts a stage running does so via `orchestrator.run_stage_async()`,
the same background thread a REST call uses, `nudge()` itself only ever makes one `chat()`
call, the routing decision. It never blocks for a stage's duration the way an earlier version
of this design did.

`nudge(user_message)` records the human's message as a `user_message` event, calls
`react_to_event(..., use_tools=True)`, records the reply as its own `"message"` event, and
returns:

- **One or more tool calls** → `{"tool_called": <last tool's name>, "result": <last tool's
  result>, "steps": [{"tool", "arguments", "result"}, ...]}`, `steps` holds every call made
  (so a two-call `add_constraint` → `rerun_stage` sequence is fully visible). For a tool that
  starts a stage running, `result` is `{"status": "started", "stage": ...}`, the same
  immediate-return shape a REST call gets; the stage's actual output shows up in `GET /events`
  once the background thread finishes, not in this response.
- **No tool call** (the model couldn't map the message to a pipeline action) →
  `{"tool_called": None, "result": None, "message": <the model's own clarifying question, or a
  generic fallback>}`. No state is mutated in this case.

A hallucinated/unknown tool name doesn't crash `nudge()`, that step's `"result"` becomes
`{"error": "Unknown tool: <name>"}` instead.

## API endpoints (`main.py`)

The service starts at [http://localhost:8001](http://localhost:8001) (port 8000 is `ai-layer`'s,
8010 is `retrieval`'s).

Every endpoint below that runs a stage returns `202` and starts the work running in the
background (a real thread, see [above](#the-event-log-and-background-execution)), it does not
wait for the stage to finish. Poll `GET /events` to see progress. A downstream failure (a bad
fetch, `ai-layer` unreachable) surfaces as a `call_failed` event via `GET /events`, not an HTTP
`500`, since it happens on that background thread, `500` is reserved for a genuine unhandled
server bug (or, for `/nudge`, a failure in its own synchronous routing call, see below).

### `POST /start`

Resets the pipeline and starts the first stage (`docs`) running against a platform description
and its real documentation URL. `409` if a stage is currently running in the background (same
guard `/review`, `/rerun`, and `/nudge` have, added here for the same reason: `start_pipeline()`
swaps in a brand-new `Orchestrator`, so without this guard, starting over mid-run wouldn't error,
it would silently orphan the old run's background thread instead).

`hint`, `exclude_urls`, `max_pages`, `max_depth`, and `force_refresh` are optional, the same
real retrieval parameters `/rerun/docs`'s `overrides` accepts, settable here too so a caller can
steer the very first crawl, not only a retry.

Request (minimal):
```json
{ "platform_description": "TeamCity: A CI/CD platform using Kotlin DSL", "seed_url": "https://www.jetbrains.com/help/teamcity/" }
```

Request (with the docs stage's advanced options):
```json
{
  "platform_description": "TeamCity: A CI/CD platform using Kotlin DSL",
  "seed_url": "https://www.jetbrains.com/help/teamcity/",
  "hint": "prioritize pages about triggers and secrets",
  "exclude_urls": ["https://www.jetbrains.com/help/teamcity/old-page.html"],
  "max_pages": 20,
  "max_depth": 4,
  "force_refresh": false
}
```

Response (`202`), immediately, before the fetch has necessarily finished:
```json
{ "status": "started", "stage": "docs" }
```

### `POST /reset`

Replaces the current run with a fresh, blank one: the empty-state counterpart to `/start`. The
old run isn't deleted, it just stops being current, `GET /runs` still lists it and
`POST /resume/{run_id}` below can bring it back. `409` if a stage is currently running, same
guard and same reasoning as `/start` above.

Response (`200`):
```json
{ "status": "reset" }
```

### `POST /resume/{run_id}`

Makes a past run current again, so it can be approved/retried/nudged like any other live run,
picking up exactly where it left off: nothing about that run's progress, constraints, or events
is replayed or reset. The counterpart to `/reset`, which replaces the current run with a blank
one instead of an existing one. `404` for an unknown `run_id`. `409` if a stage is currently
running, same guard and same reasoning as `/reset` above (swapping `_default` out from under a
genuinely in-flight background thread would orphan it).

Response (`200`):
```json
{ "run_id": "a1b2c3d4e5f6...", "current_stage": "psm" }
```

### `GET /events`

Returns the event log. `?since_index=N` returns only events from index `N` onward, for
incremental polling.

Response:
```json
{
  "events": [
    { "type": "call_started", "stage": "docs", "data": {"seed_url": "..."}, "timestamp": 1234.5 },
    { "type": "message", "stage": "docs", "text": "Fetching TeamCity's documentation now.", "timestamp": 1234.6 },
    { "type": "call_completed", "stage": "docs", "data": {"stage": "docs", "output": "...", "valid": true}, "timestamp": 1240.1 },
    { "type": "message", "stage": "docs", "text": "Retrieval completed: found 6 pages, confidence 0.82.", "timestamp": 1240.2 }
  ],
  "current_stage": "docs",
  "busy": false
}
```

### `POST /review/{stage_id}`

Records a human's decision on the named stage's most recent output. `stage_id` must match the
pipeline's current pending stage (`400` otherwise); `correction` is required when `approved`
is `false` (`400` otherwise); `409` if a stage is currently running in the background.

Request (approve, mid-pipeline):
```json
{ "approved": true }
```

Response (`202`) — the *next* stage has started running, not finished yet:
```json
{ "status": "started", "stage": "psm" }
```

Request (approve, on the last stage, `generation`):
```json
{ "approved": true }
```

Response (`200`, nothing left to run):
```json
{ "status": "complete" }
```

Request (reject):
```json
{ "approved": false, "correction": "Include a lint stage before build" }
```

Response (`200`, nothing started, the same stage stays current):
```json
{ "status": "rerun", "stage": "psm" }
```

### `POST /rerun/{stage_id}`

Starts the current pending stage's agent running again, reusing the same context passed to its
last `run_stage()` call and picking up any constraints recorded against it since then, unless
overrides are given. `stage_id` must match the pipeline's current pending stage (`400`
otherwise); `409` if already busy. Does *not* reset `current_stage_index` or `constraints`.

Request (plain retry, no body needed):
```
POST /rerun/psm
```

Request (docs stage only, structured overrides instead of a free-text constraint):
```json
{ "overrides": { "seed_url": "https://correct-docs-url.example.com", "hint": "focus on the syntax reference" } }
```

A non-empty `overrides` body on any stage other than `docs` returns `400`, the placeholder
stages have no structured parameters to override, only free-text constraints via `/review`'s
rejection path.

Response (`202`, either way):
```json
{ "status": "started", "stage": "psm" }
```

### `POST /nudge`

Lets an LLM decide which pipeline action a free-form human message maps to (see
[The reply mechanism](#the-reply-mechanism-react_to_event--nudge) above), executes it, and
returns whatever `nudge()` returns directly (`200`, this endpoint isn't itself
background-scheduled, only the stage runs a dispatched tool might trigger are). `409` if a
stage is currently running.

Request:
```json
{ "message": "the ATL stage output is wrong, please redo it with a lint step added" }
```

Response — here the model called `add_constraint` then `rerun_stage`; `atl` has started
regenerating with the correction folded in, but hasn't finished yet (poll `GET /events` for
the real output):
```json
{
  "tool_called": "rerun_stage",
  "result": { "status": "started", "stage": "atl" },
  "steps": [
    {
      "tool": "add_constraint",
      "arguments": { "stage": "atl", "constraint": "Add a lint step to validate the ATL transformation rules" },
      "result": null
    },
    {
      "tool": "rerun_stage",
      "arguments": {},
      "result": { "status": "started", "stage": "atl" }
    }
  ]
}
```

Request (ambiguous, no stated correction):
```json
{ "message": "reject this, it's not good enough" }
```

Response — the model asked for clarification instead of guessing, and nothing was mutated:
```json
{
  "tool_called": null,
  "result": null,
  "message": "Could you clarify which stage and what you'd like done?"
}
```

## Setup

```bash
pip install -r requirements.txt
cp .env.example .env
# AI_LAYER_URL defaults to http://localhost:8000, RETRIEVAL_URL defaults to
# http://localhost:8010, if unset — override only if either service runs
# somewhere else (e.g. a different port, or a Docker Compose service name).
```

This service is independent of `ai-layer`/`retrieval` at the code level, it has no import on
either's source. It does need both actually *running* and reachable at `AI_LAYER_URL`/
`RETRIEVAL_URL`, since every placeholder stage agent and the reply mechanism get their LLM
completions from `ai-layer`'s `/chat`, and the real `docs` stage gets its documentation from
`retrieval`'s `/fetch` (see `ai-layer/README.md` and `retrieval/README.md`).

## Run

```bash
uvicorn main:app --reload --port 8001
```

## Use

```python
import assistant
import orchestrator
from orchestrator import current_stage, events, record_review, run_stage, wait_for_idle

# Narration only happens once a reactor is wired in — main.py does this at
# startup; here, outside of main.py, do it explicitly the same way.
orchestrator.set_reactor(assistant.react_to_event)

# Kick off the pipeline: runs the docs stage for real, synchronously (run_stage()
# is the one stage-runner that doesn't background itself, see run_stage_async())
result = run_stage({"platform_description": "TeamCity: A CI/CD platform using Kotlin DSL", "seed_url": "https://www.jetbrains.com/help/teamcity/"})
print(result)  # {"stage": "docs", "output": "Fetched N page(s)...", "valid": True}

# Approved — record_review() returns the next stage's context, it doesn't run it
review = record_review("docs", approved=True)
print(review)  # {"status": "advanced", "stage": "psm", "context": {...}}
result = run_stage(review["context"])
print(result)  # {"stage": "psm", "output": "...", "valid": True}

# Rejected — record a correction, then rerun the same stage with it folded in.
# Unlike run_stage() above, review()/rerun_stage() start a background thread
# and return immediately; a real client polls GET /events, here we just wait
# for it and read the resulting event.
from orchestrator import review as review_and_run, rerun_stage

review_and_run("psm", approved=False, correction="Include a lint stage before build")
rerun_stage()
wait_for_idle()
print(next(e for e in events() if e["type"] == "call_completed")["data"])
# {"stage": "psm", "output": "... (lint stage added)", "valid": True}

# ...review "atl", "acceleo", and so on, until approving the last stage
# ("generation") completes the pipeline:
result = review_and_run("generation", approved=True)
print(result)  # {"status": "complete"}
```

Or let the LLM decide which action a free-form message maps to, instead of calling the
primitives above directly:

```python
from assistant import nudge

result = nudge("the ATL stage output is wrong, please redo it with a lint step added")
print(result["tool_called"])  # "rerun_stage"
print(result["steps"])        # [{"tool": "add_constraint", ...}, {"tool": "rerun_stage", ...}]

result = nudge("let's do this for GitLab instead")
print(result["tool_called"])  # "start_pipeline" — resets the pipeline for the new platform
```

`main.py`'s endpoints call these same functions directly, see
[Module layout](#module-layout) above.

## Test

```bash
cd orchestrator
pytest
```

No real network calls are made, `httpx.post` (the only thing that talks to `ai-layer` and
`retrieval`) is mocked in every test.

`tests/test_orchestrator.py` covers `orchestrator.py`, `tool_calling.py`, and `pipeline_tools.py`:
the agents (including the real `docs_agent`/`fetch_documentation`/`fetch_page`), `validate()`,
the event log (`record_event`, `run_stage_async`, the narration-failure fallback), the
review/validation path (`review`, `record_review`), and `assistant.nudge()`/`react_to_event()`'s
tool dispatch. `tests/test_main.py` covers the endpoints above, mocking only `httpx.post` and
letting the real `orchestrator.py` logic run underneath `main.py`'s routes, so the tests
exercise the actual wiring between the two rather than mocking across that seam.

`run_stage_async()` spawns a real background thread (not FastAPI's `BackgroundTasks`), so a
test that needs a run's outcome calls `orchestrator.wait_for_idle()` (joining that thread)
*before* the `with patch("orchestrator.httpx.post", ...)` block that installed the mock exits,
never after, otherwise the thread's real work would race against the mock being torn down.
Both test files' background-execution tests document and check this explicitly.

`ai-layer`'s and `retrieval`'s own test suites are separate and cover only their own
endpoints, run each from its own directory, not together (each project has its own `main.py`,
running them in one pytest session causes a module-name collision).
