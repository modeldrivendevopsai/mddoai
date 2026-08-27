# validator_agent

Wraps `main/`'s headless model/transformation validators as an HTTP service, so other `ai/` services can validate AI-generated files without needing a JVM of their own: `.ecore` metamodels (issue #313), `.atl` transformations (issue #314), and `.mtl` Acceleo templates (issue #315). The real caller today is `integration_runner`'s own `pim`/`psm` (`/validate/ecore`), `atl` (`/validate/atl`), and `acceleo` (`/validate/acceleo`) stage agents (`clients/validator_agent_client.py`), validating each stage's own currently-mock DSL output — see [integration_runner/README.md](../integration_runner/README.md#persisted-validation-attempts).

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
{"valid": false, "mode": "reflective", "issues": [{"severity": "ERROR", "message": "...", "source": "..."}], "duration_ms": 842}
```

`mode` is `"reflective"` (structural check only — is the metamodel well-formed) or `"codegen"` (also generates real Java from it and compiles that with a real `javac` — the only way to catch problems like an `instanceClassName` pointing at a Java class that doesn't actually exist). `codegen` always runs the reflective check first and returns immediately if that fails, so it never spends time generating code for an already-broken metamodel.

### `POST /validate/atl`

```json
// request
{"filename": "swarch2pim.atl", "content": "module ...;"}

// response (200)
{"valid": false, "issues": [{"severity": "ERROR", "message": "mismatched input '<EOF>' expecting RPAREN", "source": "swarch2pim.atl#6:3"}], "duration_ms": 310}
```

Compiles the `.atl` source with ATL's own standalone compiler (`AtlCompiler.getCompiler("atl2006")`) and reports the real parser/compiler diagnostics. This catches syntax errors, reserved-word misuse, and malformed rule structure, all with real `line:col` locations. It does **not** catch a reference to a type or attribute that doesn't actually exist in the real `.ecore` metamodel — ATL's compiler does no static type checking against real metamodels (confirmed against ATL's own documented architecture); that class of error only surfaces when the transformation actually runs against real model instances.

### `POST /validate/acceleo`

```json
// request
{"filename": "generate.mtl", "content": "[module generate('http://...')]"}

// response (200)
{"valid": false, "issues": [{"severity": "ERROR", "message": "'for' block body isn't terminated", "source": "generate.mtl#13"}], "duration_ms": 512}
```

Compiles the `.mtl` source with Acceleo's own classic standalone compiler (`AcceleoCompilerHelper`) and reports the real compiler diagnostics, with real source-line locations. The submitted `filename` must end in `.mtl` — the compiler resolves the file to compile by scanning its source folder for that extension, not by parsing whatever single file it's handed, so a request whose `filename` doesn't end in `.mtl` is rejected as invalid up front rather than silently reporting a trivial pass.

### `GET /health`

Used by the Dockerfile's `HEALTHCHECK`.

## Where the Java side comes from

This service never bundles a Gradle/JDK toolchain in its own image. `ai/docker-compose.yml`'s `gradle-builder` service builds `main/`'s real distribution (`./gradlew --no-daemon build -x test installDist`, piped through `tr`+`sh` rather than run directly, since a Windows checkout's `gradlew` has CRLF endings that break it otherwise — no `clean`, `build/` is a volume mount point Gradle can't rmdir, and doesn't need to, nothing else writes to that volume) into a shared `main-build-output` volume; `validator-agent` mounts that volume read-only and points `VALIDATOR_LIB_DIR` at its `install/com.mddoai/lib` subdirectory. `validator-agent` won't start until `gradle-builder` finishes (`depends_on: condition: service_completed_successfully`), so `docker compose up --build` from `ai/` works from a clean checkout with no manual Gradle step.

## Setup

```
cp .env.example .env   # optional — every setting has a working default
```

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
