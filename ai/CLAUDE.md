# CLAUDE.md — ai/

All AI-related work for MDDOAI (Model-Driven DevOps AI) lives under this folder, separate from the Java/Eclipse engine at the repo root.

## Folder boundaries

- A deployed service under `ai/` touches only its own folder. It calls another service over HTTP and never imports that service's Python code. [ai/README.md](./README.md) lists the services and how they call each other.
- Some folders are not services: no port, no Dockerfile, no compose entry.
  - `clients/` holds thin HTTP wrappers, one module per service it can reach. Services import it to call each other. `clients/journal_client.py` also holds the small tracker that sends the journal only what it does not have yet, and `clients/journal_testing.py` is a helper for tests only: it runs the journal's own code in the test's process, which no service does.
  - `generation_toolkit/` holds the shared "build a prompt, call the LLM, validate, retry" code. A stage with a config-driven generation step uses it instead of rebuilding that loop. A stage with no validator omits `validate_fn` and gets one call.
  - `design-system/` and `orchestrator-types/` are shared frontend packages. They are local npm dependencies bundled into each consumer's build, not Module Federation remotes: every frontend piece needs them to render or compile, so a container would make them a single point of failure. `orchestrator-types` holds the type contract and nothing else, so a helper that several remotes use stays local to each.
  - `data/` holds the starting platforms and prompt configs and a layout for the host folders. `tests/` checks the compose file.
- Frontend folders start with `ui-`. `ui-host/` is the shell: routing, layout, state and backend calls. Each `ui-remote-*/` is one Module Federation remote in its own container, loaded by `ui-host` at runtime through a federated import and never a source import. That is the frontend's version of "call over HTTP, never import internals".
- Services do not share what they generate. Each keeps it in folders of its own (see "Where files live" in [ai/README.md](./README.md)), and other services reach it through that service's API. The `journal` is the one service that writes the run, chat, platform, prompt config and attachment folders: the services that produce them keep them there through its API and mount none of those folders.
- `ui-host/`, every `ui-remote-*/`, `design-system/` and `ai-layer/` never touch the Java/Eclipse code at the repository root.
- A service reaches into `main/`, `meta_models/` or `code_generation/` in these ways. Anything else needs explicit justification.
  - **Java behind a command line.** `validator_agent` owns the `*ValidatorCli.java` classes under `main/src/main/java/mddoai/validation/`. `execution_agent` owns the `*Executor.java` and `*ExecutorCli.java` classes under `main/src/main/java/mddoai/execution/`. Both go by name and path at any depth, so a new validator or executor needs no edit here. The service runs the `*Cli` class as a subprocess and reads JSON from stdout. It never links against Java code. The Java/Eclipse work owns everything else in those packages.
  - **Data behind a read-only mount.** A service that needs MDE data it does not own, such as a metamodel or a sample model instance, gets a read-only bind mount of just that data. It is never a copy in the image, so a change needs no rebuild, and it is never Java/Eclipse code. The mounts are in [docker-compose.yml](./docker-compose.yml).
See [ai/README.md](./README.md) for how the services fit together and how to run the full stack. See each service's own `CLAUDE.md`/`README.md` for service-specific conventions (`ui-host/CLAUDE.md` has the frontend's design system and behavior spec; `ai-layer/README.md` has the backend's API and provider setup).

## Adding to `integration_runner`'s pipeline: stages and their tools

`integration_runner/stages/<stage>/` holds one folder per pipeline stage: `agent.py` (the stage's own real capability, a plain `(context: dict) -> str` function with no knowledge of a run, dispatched by name from `stages/__init__.py`'s `stage_agents` dict) and, only once a stage actually has one, `actions.py` (extra real, run-aware capabilities beyond running the stage itself — see `integration_runner/stages/docs/actions.py` for a full worked example). Don't add a stage's `actions.py` before that stage actually needs one.

**Replacing a placeholder stage agent with a real implementation:**
1. Rewrite `stages/<stage>/agent.py`'s real logic, keeping the same function name and `(context: dict) -> str` signature the placeholder had.
2. Nothing else changes: `stages/__init__.py` already points `stage_agents[stage]` at that function by name, and `pipeline.py` only ever reads `stages.stage_agents[stage]`, never a specific stage's own module.

**When a stage's output is more than one string:** a stage whose output is structured data a string cannot carry (the prompt used, a validation result) may return `(context: dict) -> tuple[str, dict]`. `pipeline.py`'s `run_stage()` merges the dict into the `call_completed` event's data. Use it only then: every other stage returns a plain `-> str`.

**When a stage gets a real, UI-editable prompt config** (via `generation_toolkit.prompt_config`, see `ai/generation_toolkit/README.md` for the mechanism and `ai/psm_agent/README.md` for a real, worked example): add the stage to `pipeline.py`'s `_REQUIRES_MANUAL_START` so a human gets to review, and possibly edit, the config before that stage's very first real attempt fires, rather than only after a first result. If that stage's own agent needs a real rerun override (`mock`, to skip a slow/billed real call for fast local iteration, or a genuinely new one), add its recognized keys to `pipeline.py`'s `_STAGE_OVERRIDE_KEYS` — a key valid for one stage but sent to a different one is rejected, not silently ignored, so every stage's own real override shape needs its own entry.

**Adding a new real, chat-callable capability for a stage** (something beyond running/rerunning the stage itself, e.g. an action targeting one specific piece of a stage's existing output):
1. Write the real implementation in `stages/<stage>/actions.py` (create it if this stage doesn't have one yet) as a function taking the run instance as its first argument and mutating it directly. It must have real effect, actually changing what the run holds, not just log a summary of what happened: any chat-callable action must invoke the same real state-changing path a manual or direct REST caller would use for the same intent, never a weaker echo of it.
2. Expose it over HTTP in `routes/<stage>.py` (create it if this stage doesn't have its own routes file yet, matching `routes/docs.py`'s shape), calling the action directly against `runs.current()`, with the same busy-guard pattern every other mutating endpoint already uses. Register a new router file in `main.py`.
3. Add a thin wrapper for it in `clients/integration_runner_client.py`: the HTTP call only, no business logic there.
4. Declare the tool in `orchestrator/tools/<stage>.py` (create it if this stage doesn't have its own tools file yet, matching `tools/docs.py`'s shape): a name and description that describe the real effect in plain language for an LLM's own decision-making, not the internal implementation, restricted to the stages it's actually valid for via the tool's own `stages=` list, with `impl` pointing at the new client function.
5. If that tools file is new, register it in `orchestrator/tools/__init__.py`'s aggregation.
6. Add real tests: the action's own behavior under `integration_runner/tests/stages/`, and the tool's wiring in `orchestrator/tests/test_tools.py`.

**Extending an existing generic tool instead of adding a new one:** if the new capability is really a variant of an existing stage-agnostic action (e.g. a new kind of override for rerunning the current stage), extend that tool's own schema and its underlying endpoint's request model instead of creating a parallel tool. A REST endpoint already accepting a parameter and the chat tool's own schema exposing that same parameter to the LLM are two different things, both need updating — see `rerun_stage`'s own docstring in `orchestrator/tools/pipeline_control.py` for a real example of that gap and its fix.

## Design system

For user interfaces, components or visual prototypes, use the [MDDOAI Design System skill](/mddoai-design-system/project/SKILL.md) with `/mddoai-design`. [mddoai-design-system/project/README.md](/mddoai-design-system/project/README.md) has the brand rules, components and assets.
