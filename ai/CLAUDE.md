# CLAUDE.md — ai/

All AI-related work for MDDOAI (Model-Driven DevOps AI) lives under this folder, separate from the Java/Eclipse engine at the repo root.

## Folder boundaries

- Every real, deployed service under `ai/` only touches files inside its own folder. A service
  that needs something from another one calls it over real HTTP, never by importing the other
  service's Python internals directly — see [ai/README.md](./README.md)'s services list and
  request-path description for which services exist today and how they actually call each other.
- `clients/`, `generation_toolkit/`, `design-system/`, `orchestrator-types/`, `data/` and `tests/`
  are folders under `ai/` that aren't themselves deployed services: no port, no Dockerfile, no entry
  in `docker-compose.yml`. `tests/` checks the compose file. `data/` holds the starting platforms and prompt configs and a
  layout for the host folders.
  `clients/` is a shared package of thin HTTP wrapper functions (one module
  per sibling service it can reach), imported directly as a Python package by whichever service
  needs to make that outbound call — this is how real cross-service communication happens in `ai/`,
  not a second, competing mechanism alongside it. `generation_toolkit/` is a shared, stage-agnostic
  "build a prompt, call the LLM, validate, retry" toolkit, imported directly as a Python package by
  every service with a real, config-driven generation step (`psm_agent`, `atl_agent`, and
  `acceleo_agent` today). Whichever of `integration_runner`'s remaining placeholder stages (`pim`,
  `generation`) gets a real implementation next should reuse this too, rather than rebuilding the
  same prompt-assembly and regenerate-loop pattern from scratch. A stage with no real validator yet
  just omits the optional `validate_fn` and gets a plain single-shot call — see
  `generation_toolkit/generation_agent.py`'s own docstring.
  `design-system/` is the frontend's equivalent for shared UI: a component/token package (its own
  `src/index.ts` barrel export is the current source of truth for exactly what it exports).
  `orchestrator-types/` is the frontend's equivalent for a shared type contract: `ai/orchestrator`'s
  real REST/event contract (`StageId`, `OrchestratorEvent`, etc.), plus the small `StagePanelProps`
  UI prop contract, in one place instead of hand-copied per package. Both frontend packages are
  consumed the same way, via an ordinary local `"file:../design-system"`/`"file:../orchestrator-types"`
  npm dependency, bundled into each consumer's own build at build time. Both are deliberately *not*
  Module Federation remotes like the `ui-remote-*` packages below: neither is an independently-owned
  feature (what Module Federation is for), each is a dependency every other frontend piece needs
  just to render or compile at all, so making either a live container would turn a handful of small
  components (or, for `orchestrator-types`, mostly type declarations plus a couple of small constant
  arrays) into a single point of failure for the whole app. A stage-local helper stays local to its
  own `ui-remote-*` package even once a near-identical copy exists in another one (e.g. each stage
  panel's own small `stageEvents.ts`): these are independent Module Federation remotes that only
  ever consume each other through a federated import at runtime, never a source import at build
  time, so sharing this kind of helper would mean pulling it into `orchestrator-types` instead,
  whose own charter is the type contract plus those two
  constant arrays, not a general utility grab-bag.
- Every deployed frontend package's folder is prefixed `ui-`: `ui-host/` (the host/shell — routing,
  `AppShell`, `useIntegration.ts`'s state hub, and every real backend service call) and
  `ui-remote-*/` (one Module Federation remote per independently-liftable UI section — a pipeline
  stage panel, the chat column, the stepper — each its own container, each consumed by `ui-host`
  at runtime via a federated import, never a source import). This mirrors the backend's own
  principle one level up: a federated module fetch is the frontend's equivalent of "calls it over
  real HTTP, never imports internals directly."
- `ui-host/`, every `ui-remote-*/`, `design-system/`, and `ai-layer/` never touch the Java/Eclipse
  code at the repo root.
- **Exception, deliberate and narrow**: `validator_agent/` wraps headless model/transformation
  validators (`main/src/main/java/mddoai/validation/`, one subpackage per file type) as HTTP
  routes, since a Python process can't call a JVM library directly. Its Python code lives in
  `ai/validator_agent/`; it also owns every `main/src/main/java/mddoai/validation/**/*ValidatorCli.java`
  class (recursive, any depth under `validation/`), a thin entrypoint that `validator_agent`
  invokes as a subprocess (`java -cp .../lib/* ...*ValidatorCli <args>`), reading structured JSON
  off stdout — it never links against or imports Java code directly. Everything else under
  `mddoai.validation` is owned by the Java/Eclipse work, not by `validator_agent`. Both rules
  (subprocess boundary, ownership split) are stated by naming pattern and path, not by
  enumerating specific classes or file types, so adding a new validator never requires an edit
  here. This exception does not extend to any `ui-*` package, `design-system`, or `ai-layer`,
  and does not license any other future `ai/` service to reach into `main/` without the same
  explicit justification.
- Shared infrastructure that spans services (the combined `docker-compose.yml`) lives directly in `ai/`, not nested inside any service. Its checks live in `ai/tests/`.
- **Services do not share what they generate.** Every service keeps what it generates in folders of
  its own (see [ai/README.md](./README.md)'s "Where files live"), and other services reach it only
  through that service's API. For example, `integration_runner` keeps each run's attempts, and
  `validator_agent` keeps its compiled output in its own folder, under the `run_id`, `stage` and
  `attempt` a request names, and returns the path in its response as `generated_source_path`.
  `integration_runner` records that path in the attempt's `result.json`. It is a path in
  `validator_agent`'s own storage, which `integration_runner` never opens.
- **Second exception, also deliberate and narrow**: a stage-agent service that needs to read real,
  pre-existing MDE-engine data (a metamodel, a real model instance) it doesn't own gets a read-only
  Docker bind mount of that specific data, not a copy into its own image, so a metamodel change
  doesn't need a service rebuild. `psm_agent` mounts the whole `meta_models/` tree this way
  (`META_MODELS_DIR`). `atl_agent`'s and `acceleo_agent`'s own master-example transformation/template
  (`REFERENCE_EXAMPLE_PATH` in each service's own `prompt_paths.py`) is a real file each service owns
  directly under its own `reference_example/`, not MDE-engine data reached into from outside `ai/` -
  both were ported from this project's own validated `ai-research` branch experiments (the same
  master-example convention the paper's own results were produced against), so they need no bind
  mount at all, unlike this exception's other examples. `integration_runner` mounts
  two single real files the same narrow way: `PIM_METAMODEL_PATH` (the project's own real, fixed
  PIM metamodel, which `stages/pim/agent.py` returns as that stage's real, no longer mock, output)
  and a real sample PIM model instance, `main/`'s own real Java test fixture (`stages/generation/
  agent.py`'s own real input to the real ATL/Acceleo execution it runs, see the third exception
  below for the execution side of that same stage, a temporary stand-in until a real
  SWArch-driven PIM extraction replaces it). `validator_agent` mounts that same sample PIM instance
  too, for the same reason one level earlier in the pipeline: `AtlValidator`/`AcceleoValidator`'s own
  real execution smoke test (given a real target metamodel) actually runs the candidate ATL/Acceleo
  against it, catching a real runtime-only failure a compile-only check can never see, during
  atl_stage's/acceleo_stage's own retry loop instead of only later, with no retries left. This is a
  read of real MDE *data*, never Java/Eclipse *code*, a distinction that separates it from the first
  exception above (`validator_agent`'s own reach into `mddoai.validation`) and the third exception
  below (`execution_agent`'s own). This exception does not extend
  to any `ui-*` package, `design-system`, or `ai-layer`, and does not license any other future `ai/`
  service to reach into `main/`, `meta_models/`, or `code_generation/` without the same explicit
  justification.
- **Third exception, deliberate and narrow, mirroring the first**: `execution_agent/` wraps
  headless model/code executors (`main/src/main/java/mddoai/execution/`, one subpackage per DSL
  type) as HTTP routes, actually *running* a real ATL transformation or Acceleo template against a
  real model instance, not just compiling it to check for errors (`validator_agent`'s own, separate
  concern, see the first exception above). A genuinely separate service from `validator_agent`, not
  new routes there: `validator_agent`'s own documented scope is specifically about validator-type
  proliferation (`/validate/<type>`), and its licensed reach into `main/` is scoped, by name and
  path, to `*ValidatorCli.java` under `mddoai.validation`; neither extends to execution. Its Python
  code lives in `ai/execution_agent/`; it also owns every
  `main/src/main/java/mddoai/execution/**/*Executor.java` class and its
  `main/src/main/java/mddoai/execution/**/*ExecutorCli.java` entrypoint (recursive, any depth under
  `execution/`, one such pair per DSL type). The executor class holds the execution logic, which
  exists only to serve this service: the Java/Eclipse engine does not call it. The `*ExecutorCli`
  class is a thin entrypoint that `execution_agent` invokes as a subprocess
  (`java -cp .../lib/* ...*ExecutorCli <args>`), reading structured JSON off stdout; the Python code
  never links against or imports Java code directly. Supporting a new kind of input model means
  adding a new pair in its own subpackage, not editing an existing one. Everything else under
  `mddoai.execution` is owned by the Java/Eclipse work, not by `execution_agent`. Like the first
  exception, both rules (subprocess boundary, ownership split) are stated by naming pattern and
  path, not by enumerating specific classes, so adding a new executor never requires an edit here.
  This exception does not extend to any `ui-*` package, `design-system`, or `ai-layer`, and does
  not license any other future `ai/` service to reach into `main/` without the same explicit
  justification.

See [ai/README.md](./README.md) for how the services fit together and how to run the full stack. See each service's own `CLAUDE.md`/`README.md` for service-specific conventions (`ui-host/CLAUDE.md` has the frontend's design system and behavior spec; `ai-layer/README.md` has the backend's API and provider setup).

## Adding to `integration_runner`'s pipeline: stages and their tools

`integration_runner/stages/<stage>/` holds one folder per pipeline stage: `agent.py` (the stage's own real capability, a plain `(context: dict) -> str` function with no knowledge of a run, dispatched by name from `stages/__init__.py`'s `stage_agents` dict) and, only once a stage actually has one, `actions.py` (extra real, run-aware capabilities beyond running the stage itself — see `integration_runner/stages/docs/actions.py` for a full worked example). Don't add a stage's `actions.py` before that stage actually needs one.

**Replacing a placeholder stage agent with a real implementation:**
1. Rewrite `stages/<stage>/agent.py`'s real logic, keeping the same function name and `(context: dict) -> str` signature the placeholder had.
2. Nothing else changes: `stages/__init__.py` already points `stage_agents[stage]` at that function by name, and `pipeline.py` only ever reads `stages.stage_agents[stage]`, never a specific stage's own module.

**When a stage's real output is more than one string:** `pipeline.py`'s `run_stage()` also accepts `(context: dict) -> tuple[str, dict]` — the tuple's second element is merged into the `call_completed` event's own data alongside `output`. This is a narrow, deliberate exception to the plain `-> str` contract above, for a stage whose real capability produces genuinely structured data (e.g. the exact prompt used, or a validation/gap result) that a plain string has nowhere to carry — `stages/psm/agent.py` is the one stage that needs this today. Don't reach for it by default: every other stage stays plain `-> str`, and `run_stage()` treats a non-tuple return exactly as before.

**When a stage gets a real, UI-editable prompt config** (via `generation_toolkit.prompt_config`, see `ai/generation_toolkit/README.md` for the mechanism and `ai/psm_agent/README.md` for a real, worked example): add the stage to `pipeline.py`'s `_REQUIRES_MANUAL_START` so a human gets to review, and possibly edit, the config before that stage's very first real attempt fires, rather than only after a first result. If that stage's own agent needs a real rerun override (`mock`, to skip a slow/billed real call for fast local iteration, or a genuinely new one), add its recognized keys to `pipeline.py`'s `_STAGE_OVERRIDE_KEYS` — a key valid for one stage but sent to a different one is rejected, not silently ignored, so every stage's own real override shape needs its own entry.

**Adding a new real, chat-callable capability for a stage** (something beyond running/rerunning the stage itself, e.g. an action targeting one specific piece of a stage's existing output):
1. Write the real implementation in `stages/<stage>/actions.py` (create it if this stage doesn't have one yet) as a function taking the run instance as its first argument and mutating it directly. It must have real effect, actually changing what the run holds, not just log a summary of what happened: any chat-callable action must invoke the same real state-changing path a manual or direct REST caller would use for the same intent, never a weaker echo of it.
2. Expose it over HTTP in `routes/<stage>.py` (create it if this stage doesn't have its own routes file yet, matching `routes/docs.py`'s shape), calling the action directly against `runs.current()`, with the same busy-guard pattern every other mutating endpoint already uses. Register a new router file in `main.py`.
3. Add a thin wrapper for it in `clients/integration_runner_client.py`: the HTTP call only, no business logic there.
4. Declare the tool in `orchestrator/tools/<stage>.py` (create it if this stage doesn't have its own tools file yet, matching `tools/docs.py`'s shape): a name and description that describe the real effect in plain language for an LLM's own decision-making, not the internal implementation, restricted to the stages it's actually valid for via the tool's own `stages=` list, with `impl` pointing at the new client function.
5. If that tools file is new, register it in `orchestrator/tools/__init__.py`'s aggregation.
6. Add real tests: the action's own behavior under `integration_runner/tests/stages/`, and the tool's wiring in `orchestrator/tests/test_tools.py`.

**Extending an existing generic tool instead of adding a new one:** if the new capability is really a variant of an existing stage-agnostic action (e.g. a new kind of override for rerunning the current stage), extend that tool's own schema and its underlying endpoint's request model instead of creating a parallel tool. A REST endpoint already accepting a parameter and the chat tool's own schema exposing that same parameter to the LLM are two different things, both need updating — see `rerun_stage`'s own docstring in `orchestrator/tools/pipeline_control.py` for a real example of that gap and its fix.

## Design System Skill

When building or updating **user interfaces, components, or visual prototypes**, use the [MDDOAI Design System skill](/mddoai-design-system/project/SKILL.md).

**Invoke with**: `/mddoai-design`

**Use when**:
- Building React components that need MDDOAI branding
- Creating prototypes, mockups, or throwaway visual assets
- Implementing production UI that follows brand guidelines
- Designing pages, layouts, or interactive surfaces
- Working with MDDOAI colors, typography, spacing, or assets

**What it provides**:
- Official brand colors, typography, and spacing tokens
- Reusable React primitives (Button, Icon, Badge, Card, Input, Tabs, CodeBlock, etc.)
- Ready-to-use UI kits for docs and marketing surfaces
- Asset library (logos, fonts, icons)
- Styling guidance and component examples

**Key brand rules**:
- Brand hue: violet `#684aeb` → `#a45eed` (use gradient sparingly)
- Type: Space Grotesk (display), IBM Plex Sans (body), IBM Plex Mono (code)
- Voice: second-person imperative, sentence case, **MDDOAI** always capitals, no emoji
- Commands are first-class content — show in dark terminal CodeBlock surface
- Semantic colors map to CI states: green=passed, amber=running, red=failed, blue=info

## Working with the Skill

The skill is self-guided:
1. Invoke `/mddoai-design`
2. Describe what you want to build or design
3. The skill will generate HTML artifacts for prototypes or guide production code implementation
4. For production: copy assets and follow brand rules
5. For prototypes: use static HTML files for review

See [mddoai-design-system/project/README.md](/mddoai-design-system/project/README.md) for full component documentation, color specs, and usage examples.
