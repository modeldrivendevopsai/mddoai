# validator_agent

Wraps `main/`'s headless model/transformation validators as an HTTP service, so other `ai/` services can validate AI-generated files without needing a JVM of their own: `.ecore` metamodels (issue #313), `.atl` transformations (issue #314), and `.mtl` Acceleo templates (issue #315). The real callers today are `integration_runner`'s own `pim` (`/validate/ecore`), `atl` (`/validate/atl`), and `acceleo` (`/validate/acceleo`) stage agents (`clients/validator_agent_client.py`), validating each stage's own currently-mock DSL output — see [integration_runner/README.md](../integration_runner/README.md#persisted-validation-attempts) — and `psm_agent`, which calls `/validate/ecore` internally on its own generation path to check a freshly generated (real, not mock) `.ecore` before returning it — see [psm_agent/README.md](../psm_agent/README.md).

Named `validator_agent`, not `ecore_validator`, because it hosts one `/validate/<type>` route per file type behind a single Java-process-spawning FastAPI wrapper, not a new microservice per file type. The `docker-compose.yml` service key stays `validator-agent` (hyphenated), matching every other service's Compose naming (`pim-agent`, `psm-agent`, `integration-runner`); only this package's own directory/import name uses an underscore.

## Why a subprocess, not an embedded JVM

Each call spawns a fresh `java` process rather than keeping one JVM warm across requests (via JPype or Py4J). EMF's validator relies on mutable global registry state (`EPackage.Registry`, `EcorePlugin`'s platform resource map) that was never designed for safe reuse across many calls in one shared JVM. A fresh JVM per call sidesteps that risk entirely — every validation starts from a clean slate — at the cost of real, but bounded and logged, per-call startup latency.

## How a request flows

1. A caller `POST`s file content (not a file path — this service shares no filesystem with its callers) to `/validate/ecore`, `/validate/atl`, or `/validate/acceleo`.
2. `validator_runner.py` writes that content to a temp file, then runs `java -cp <lib>/* <FQN of the matching *ValidatorCli> ...` as a subprocess (`EcoreValidatorCli <mode> <path>`, `AtlValidatorCli <path>`, or `AcceleoValidatorCli <path>`).
3. The Java side prints one line of JSON to stdout and exits 0, whether the input is valid or not — validity lives inside the JSON, not the exit code. A nonzero exit, a timeout, or unparseable stdout is treated as an infrastructure failure, distinct from an input that's simply invalid.
4. The JSON is parsed, `duration_ms` is added, and returned as the HTTP response.

| Outcome | HTTP status |
|---|---|
| Input validated (valid or not) | `200`, `valid` field tells you which |
| Subprocess itself failed (missing `java`, timeout, crash, garbage stdout) | `500` |
| Request body over `MAX_CONTENT_BYTES` | `413` |
| Bad `mode` (ecore only) / missing `content` | `422` |

## API

### `POST /validate/ecore`

```json
// request
{"filename": "pimMM.ecore", "content": "<?xml ...>", "mode": "reflective"}

// response (200)
{"valid": false, "mode": "reflective", "issues": [{"severity": "ERROR", "message": "...", "source": "..."}], "duration_ms": 842, "generated_source_path": null}
```

`mode` is `"reflective"` (structural check only — is the metamodel well-formed) or `"codegen"` (also generates real Java from it and compiles that with a real `javac` — the only way to catch problems like an `instanceClassName` pointing at a Java class that doesn't actually exist). `codegen` always runs the reflective check first and returns immediately if that fails, so it never spends time generating code for an already-broken metamodel.

`generated_source_path` is only ever non-null for `codegen` mode, and only once generation got far enough to actually produce `src-gen/` (a genmodel-level failure before that point has nothing worth keeping). It's `main/`'s real `EcoreValidator.OUTPUT_ROOT` path (`VALIDATOR_OUTPUT_DIR` below) where the generated `.genmodel`/`src-gen`/`classes-out` survive on disk — kept even when the generated source fails to actually compile, since that's exactly the output a human debugging the failure needs to see.

### `POST /validate/atl`

```json
// request
{"filename": "swarch2pim.atl", "content": "module ...;"}

// response (200)
{"valid": false, "issues": [{"severity": "ERROR", "message": "mismatched input '<EOF>' expecting RPAREN", "source": "swarch2pim.atl#6:3"}], "duration_ms": 310, "generated_source_path": null}
```

Compiles the `.atl` source with ATL's own standalone compiler (`AtlCompiler.getCompiler("atl2006")`) and reports the real parser/compiler diagnostics. This catches syntax errors, reserved-word misuse, and malformed rule structure, all with real `line:col` locations. It does **not** catch a reference to a type or attribute that doesn't actually exist in the real `.ecore` metamodel — ATL's compiler does no static type checking against real metamodels (confirmed against ATL's own documented architecture); that class of error only surfaces when the transformation actually runs against real model instances.

Unlike `/validate/ecore`, there's no separate cheap mode here, compiling *is* the only validation ATL has, so `generated_source_path` reports the real compiled `.asm` bytecode's path whenever compilation actually produces one (win or lose, a source with real errors can still emit a partial `.asm`). A request can also carry the same `run_id` field `/validate/ecore` does, plus the calling stage's own name and its already-reserved attempt directory as `stage`/`attempt`, nesting the compiled output two levels deeper still, see [Setup](#setup) below.

### `POST /validate/acceleo`

```json
// request
{"filename": "generate.mtl", "content": "[module generate('http://...')]", "metamodel_ecore": "<ecore:EPackage .../>"}

// response (200)
{"valid": false, "issues": [{"severity": "ERROR", "message": "'for' block body isn't terminated", "source": "generate.mtl#13"}], "duration_ms": 512, "generated_source_path": null}
```

Compiles the `.mtl` source with Acceleo's own classic standalone compiler (`AcceleoCompilerHelper`) and reports the real compiler diagnostics, with real source-line locations. The submitted `filename` must end in `.mtl` — the compiler resolves the file to compile by scanning its source folder for that extension, not by parsing whatever single file it's handed, so a request whose `filename` doesn't end in `.mtl` is rejected as invalid up front rather than silently reporting a trivial pass.

`metamodel_ecore` is optional: the real target platform's own `.ecore` content, dynamically registered into EMF's package registry before compiling (see `AcceleoValidator.validate(String, String)`'s own comment for why this needs no genmodel or compile step). Without it, Acceleo's compiler can only ever resolve the metamodels `EMFUtils.init()` hardcodes at the Java build level (today: PIM, SWArch, GitLab) — any other platform's own template, however correct, fails with `"the metamodel couldn't be resolved"`. Every real caller with a target metamodel on hand (`acceleo_agent`'s own real generation path) should always pass it; the size cap on `content` (`MAX_CONTENT_BYTES`) applies to this field too.

Since this field's content ultimately traces back to a stage-agent's own request body (not something this service generates itself), `EMFUtils.loadEPackage()` (the shared loader both this field and `/validate/ecore`'s own codegen path use) parses it with DOCTYPE declarations rejected outright and outbound network resolution disabled for any cross-document reference — see that method's own comment for the real class of attack (XXE, SSRF via EMF's own proxy resolution) this closes.

Same real-compiled-output behavior as `/validate/atl`: `generated_source_path` reports where the compiled `.emtl` module actually landed, and the same optional `run_id`/`stage`/`attempt` fields scope it.

### `GET /health`

Used by the Dockerfile's `HEALTHCHECK`.

## Where the Java side comes from

This service never bundles a Gradle/JDK toolchain in its own image. `ai/docker-compose.yml`'s `gradle-builder` service builds `main/`'s real distribution (`./gradlew --no-daemon build -x test installDist`, piped through `tr`+`sh` rather than run directly, since a Windows checkout's `gradlew` has CRLF endings that break it otherwise — no `clean`, `build/` is a volume mount point Gradle can't rmdir, and doesn't need to, nothing else writes to that volume) into a shared `main-build-output` volume; `validator-agent` mounts that volume read-only and points `VALIDATOR_LIB_DIR` at its `install/com.mddoai/lib` subdirectory. `validator-agent` won't start until `gradle-builder` finishes (`depends_on: condition: service_completed_successfully`), so `docker compose up --build` from `ai/` works from a clean checkout with no manual Gradle step.

## Setup

```
cp .env.example .env   # optional — every setting has a working default
```

`VALIDATOR_OUTPUT_DIR` (Java-side env var, read by `EcoreValidator`/`AtlValidator`/
`AcceleoValidator` directly, not by this Python service) is the base directory every real
compiled artifact these three validators produce gets persisted under, instead of being deleted
after the check. In Docker this is the same `pipeline-runs` volume `integration_runner` itself
mounts, at `/app/integration_runner/runs` there and at `/runs` here: one shared volume, two
different mount paths, each matching what that service's own code actually reads. See
`ai/docker-compose.yml`'s own comments on both mounts before changing either. A request may
include a validated `run_id`, in which case the compiled output is scoped under a subfolder
named for it; every codegen-producing request (`/validate/atl`, `/validate/acceleo`, and
`/validate/ecore` in `codegen` mode) may also include `stage` and `attempt` (the calling stage's
own name and its already-reserved `attempt_N` directory name, forwarded on `psm_agent`'s own
behalf for its `/validate/ecore` calls, since it has no attempt-numbering concept of its own -
see [integration_runner's own README](../integration_runner/README.md#persisted-validation-attempts)),
in which case the output nests two levels deeper still, landing *inside* that specific attempt's
own directory (`runs/<run_id>/<stage>/attempt_N/<type>-validate-<uuid>/`) rather than merely
somewhere else under the same `run_id`. The real path is still returned as `generated_source_path`
in the response either way, and `persist_attempt()` records it in the calling stage's own
`result.json`. This is deliberate: these are real, reusable pipeline artifacts (the compiled
model classes, ATL bytecode, and Acceleo module a real generate-and-validate cycle produces), not
disposable debugging output, so they belong nested inside the one place a run's other real
artifacts already live, not off in a validator-only corner.

## Run

Via the full stack (recommended — builds the Java side automatically):
```
cd ai && docker compose up --build
```

Standalone (needs `main/`'s distribution already built):
```
cd main && ./gradlew installDist
cd ai/validator_agent && VALIDATOR_LIB_DIR=../../main/build/install/com.mddoai/lib \
    uvicorn main:app --port 8020
```

## Test

```
python -m venv .venv && .venv/Scripts/pip install -r requirements.txt   # Windows
pytest
```

The fast suite (`test_main.py`, `test_validator_runner.py`) mocks the subprocess boundary and needs no JDK. `test_integration_real_jvm.py` spawns a real `java` process against real fixtures — it auto-skips unless a JDK is on `PATH` and `VALIDATOR_LIB_DIR` (or its default, `../../main/build/install/com.mddoai/lib`) actually exists, so plain `pytest` always runs standalone. To exercise the real path:
```
cd main && ./gradlew installDist
cd ai/validator_agent && VALIDATOR_LIB_DIR=../../main/build/install/com.mddoai/lib pytest
```
