# integration-agent

Wraps `main/`'s headless `.ecore` validator (issue #313) as an HTTP service, so other `ai/` services (like `orchestrator`) can validate an AI-generated `.ecore` metamodel without needing a JVM of their own.

Named `integration-agent`, not `ecore-validator`, because sibling issues #314 (`.atl`) and #315 (`.mtl`) are expected to add more `/validate/<type>` routes to this same service later — one Java-process-spawning FastAPI wrapper, not a new microservice per file type.

## Why a subprocess, not an embedded JVM

Each call spawns a fresh `java` process rather than keeping one JVM warm across requests (via JPype or Py4J). EMF's validator relies on mutable global registry state (`EPackage.Registry`, `EcorePlugin`'s platform resource map) that was never designed for safe reuse across many calls in one shared JVM. A fresh JVM per call sidesteps that risk entirely — every validation starts from a clean slate — at the cost of real, but bounded and logged, per-call startup latency.

## How a request flows

1. A caller `POST`s `.ecore` content (not a file path — this service shares no filesystem with its callers) to `/validate/ecore`.
2. `validator_runner.py` writes that content to a temp file, then runs `java -cp <lib>/* main.java.mddoai.validation.EcoreValidatorCli <mode> <path>` as a subprocess.
3. The Java side prints one line of JSON to stdout and exits 0, whether the model is valid or not — validity lives inside the JSON, not the exit code. A nonzero exit, a timeout, or unparseable stdout is treated as an infrastructure failure, distinct from a model that's simply invalid.
4. The JSON is parsed, `duration_ms` is added, and returned as the HTTP response.

| Outcome | HTTP status |
|---|---|
| Model validated (valid or not) | `200`, `valid` field tells you which |
| Subprocess itself failed (missing `java`, timeout, crash, garbage stdout) | `500` |
| Request body over `MAX_CONTENT_BYTES` | `413` |
| Bad `mode` / missing `content` | `422` |

## API

### `POST /validate/ecore`

```json
// request
{"filename": "pimMM.ecore", "content": "<?xml ...>", "mode": "reflective"}

// response (200)
{"valid": false, "mode": "reflective", "issues": [{"severity": "ERROR", "message": "...", "source": "..."}], "duration_ms": 842}
```

`mode` is `"reflective"` (structural check only — is the metamodel well-formed) or `"codegen"` (also generates real Java from it and compiles that with a real `javac` — the only way to catch problems like an `instanceClassName` pointing at a Java class that doesn't actually exist). `codegen` always runs the reflective check first and returns immediately if that fails, so it never spends time generating code for an already-broken metamodel.

### `GET /health`

Used by the Dockerfile's `HEALTHCHECK`.

## Where the Java side comes from

This service never bundles a Gradle/JDK toolchain in its own image. `ai/docker-compose.yml`'s `gradle-builder` service builds `main/`'s real distribution (`./gradlew clean build -x test installDist` — the same command CI runs) into a shared `main-build-output` volume; `integration-agent` mounts that volume read-only and points `VALIDATOR_LIB_DIR` at its `install/com.mddoai/lib` subdirectory. `integration-agent` won't start until `gradle-builder` finishes (`depends_on: condition: service_completed_successfully`), so `docker compose up --build` from `ai/` works from a clean checkout with no manual Gradle step.

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
cd ai/integration-agent && VALIDATOR_LIB_DIR=../../main/build/install/com.mddoai/lib \
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
cd ai/integration-agent && VALIDATOR_LIB_DIR=../../main/build/install/com.mddoai/lib pytest
```
