# CLAUDE.md — ai/

All AI-related work for MDDOAI (Model-Driven DevOps AI) lives under this folder, separate from the Java/Eclipse engine at the repo root.

## Folder boundaries

- Every real, deployed service under `ai/` only touches files inside its own folder. A service
  that needs something from another one calls it over real HTTP, never by importing the other
  service's Python internals directly — see [ai/README.md](./README.md)'s services list and
  request-path description for which services exist today and how they actually call each other.
- `clients/`, `generation_toolkit/`, `design-system/`, and `orchestrator-types/` are the four
  folders under `ai/` that aren't themselves deployed services: no port, no Dockerfile, no entry in
  `docker-compose.yml`. `clients/` is a shared package of thin HTTP wrapper functions (one module
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
- Shared infrastructure that spans services (the combined `docker-compose.yml`) lives directly in `ai/`, not nested inside any service.
- **Second exception, also deliberate**: `integration_runner` and `validator_agent` share one Docker
  volume (`pipeline-runs` in `ai/docker-compose.yml`) for real, on-disk pipeline artifacts. This
  covers every stage's own `persist_attempt()` output (the artifact plus its validation result, and,
  for a stage with a real, UI-editable prompt config, a third file, `prompt.json`, recording the
  exact prompt and config version that produced this attempt — see
  `integration_runner/stages/_validation.py`) and every real compiled artifact `validator_agent`'s
  own deep checks produce (`EcoreValidator`'s codegen classes, `AtlValidator`'s `.asm`,
  `AcceleoValidator`'s `.emtl`, none of which validator_agent itself ever deletes anymore). These
  are meant to be genuinely reusable pipeline artifacts, not disposable debugging output. The
  concrete, stated direction is assembling them into a real conversion pipeline once the full
  integration chain is done, not just keeping them around for a human to inspect a failed check,
  so they land in one shared `runs/<run_id>/` tree instead of two disconnected per-service
  locations. For `atl`/`acceleo`/`psm`, that shared tree is a real nested layout, not just a shared
  top-level directory: each stage reserves its own `attempt_N/` directory before it (or, for `psm`,
  before `psm_agent`'s own generation code, called on its behalf) ever calls validator-agent
  (`stages/_validation.py`'s own `reserve_attempt_dir()`), then forwards its own stage name and
  that attempt's name as the request's `stage` and `attempt` fields alongside `run_id`, and
  validator-agent joins all three onto its own output root (`validator_runner.py`'s own
  `_scoped_output_env()`) before ever invoking the Java CLI. `AtlValidator`'s `.asm`,
  `AcceleoValidator`'s `.emtl`, and `EcoreValidator`'s codegen classes (on `psm`'s own real
  generation path) land inside that same `attempt_N/` directory
  (`runs/<run_id>/<stage>/attempt_N/<type>-validate-<uuid>/`), not merely as a sibling of it under
  the same `run_id`, and not missing the `stage` segment either (which would risk two stages' own
  same-numbered attempts colliding). `psm` is the one case where the calling service and the
  service that reserves the attempt directory are different processes: `integration_runner`'s own
  `stages/psm/agent.py` reserves the attempt directory and forwards `stage`/`attempt` over HTTP to
  `psm_agent`, which has no attempt-numbering concept of its own and just carries those two values
  through its own real generate-validate-retry loop (`psm_agent/generation.py`) unchanged, once per
  round, so every regeneration round's own real compiled Ecore classes land inside that same one
  attempt directory, each in its own uniquely-named subfolder, rather than scattered as orphaned
  siblings under the run root. `pim` only ever validates reflectively today, which produces nothing
  worth scoping, so it never sends `run_id`, `stage`, or `attempt` at all.
  This is the one place in `ai/` where two independently deployed services share a filesystem
  rather than only talking over HTTP: `integration_runner` writes its own side of that tree
  directly, `validator_agent` returns its side's real path as `generated_source_path` for the
  calling stage's own `persist_attempt()` to record, so the two sides of the same run still get
  reconciled through a real API response, not a hidden read of the other service's own files.
  Whoever changes either container's mount path must re-derive it from that service's own code
  (`integration_runner`'s `RUNS_DIR`, `validator_agent`'s `VALIDATOR_OUTPUT_DIR` usage), not just
  copy the other container's mount string, since the two Dockerfiles have different `WORKDIR`s, so
  the same volume is deliberately mounted at different absolute paths in each (see both mounts' own
  comments in `ai/docker-compose.yml`).
- **Third exception, also deliberate and narrow**: a stage-agent service that needs to read real,
  pre-existing MDE-engine data (a metamodel, a master-example transformation or code-generation
  template) it doesn't own gets a read-only Docker bind mount of that specific data, not a copy
  into its own image, so a metamodel or reference-example change doesn't need a service rebuild.
  `psm_agent` mounts the whole `meta_models/` tree this way (`META_MODELS_DIR`). `atl_agent` and
  `acceleo_agent` each mount a single real file instead of a whole directory, narrower still: one
  real, working reference transformation/template this project already has, attached as their
  default prompt's syntax example (`REFERENCE_EXAMPLE_PATH` in each service's own
  `prompt_paths.py`; see `ai/docker-compose.yml` for the real mounts). This is a read of real MDE
  *data*, never Java/Eclipse *code* — that boundary is the second exception above, `validator_agent`'s
  own, and stays separate from this one. This exception does not extend to any `ui-*` package,
  `design-system`, or `ai-layer`, and does not license any other future `ai/` service to reach into
  `main/`, `meta_models/`, or `code_generation/` without the same explicit justification.

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
