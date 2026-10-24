# pipeline_generator

The backend of the "Generate a CI/CD pipeline" mode. A person gives it a Software Architecture
model (a `.swarch` file, uploaded or picked from the workspace folder), and this service checks
it, turns it into a CI/CD pipeline for each chosen platform, saves the pipelines, and checks the
result. It replaces running the engine's command line or Eclipse by hand.

It is UI-facing, the way `orchestrator` is for the platform-integration mode, but it is a
separate service. This mode has no pipeline run, no chat and no stages, and it calls no LLM.

## What it does, and how

1. **Inspect** (`POST /inspect`). Reads the uploaded model's attributes for display, and asks
   `validator_agent` whether the real EMF validator accepts it.
2. **Generate** (`POST /generate`). Checks the model again, then, for each chosen platform, runs
   the engine's own `swarch2<platform>` command and returns the generated pipeline plus the
   intermediate models the engine passed through. It saves each pipeline file into the requested
   workspace folder and records the generation so it can be reopened.
3. **Validate** (`POST /validate`). Checks the generated pipeline file and returns a short
   checklist in plain language.

Around these, two more concerns have their own modules: the **workspace** (`workspace.py`), and the
**pipeline history** (`pipeline_history.py`). Both are described below.

## The workspace

A web page can neither read nor write a path on the user's machine, so the service works inside
one folder that is mounted into it. In Docker Compose that is `GENERATOR_WORKSPACE_HOST_DIR` on
the host (default: this repository, so its example models can be picked), seen as `/workspace`
inside the container. Point it at a folder of your own, such as the one holding your repositories.
The Browse dialog names the folder it is showing, from `GENERATOR_WORKSPACE_LABEL`, which compose
sets to the host folder (`GET /workspace/browse` returns it as `root_label`).

Every path a caller supplies is read as relative to that folder: `/repos/app/a.swarch`,
`./repos/app/a.swarch` and `repos/app/a.swarch` are the same file. Whatever a path resolves to,
links included, must still be inside the folder. A path that leads outside is refused, nothing
outside is listed, and error messages never name a location outside the folder. This holds as
long as nothing else is changing the folder's links while a request runs: a path is checked and
then used, not held open in between.

- **Reading.** `GET /workspace/browse` lists a folder (for the Browse dialogs). `GET /workspace/file`
  returns a model file's text, so it loads exactly like an upload. Only `.swarch` files are read
  or listed (folders are listed so they can be walked), and only regular text files up to
  `GENERATOR_MAX_MODEL_BYTES` are returned. Whatever else the folder holds is neither read nor named.
- **Saving.** `POST /generate` with `save_to` writes each pipeline into that workspace folder
  (created if needed) under the platform's own file name, replacing a file of that name. The write
  goes to a temporary file that is renamed into place, so a failure part way does not leave half a
  pipeline at the target (a hard kill of the process can leave the temporary `.saving-` file).
  A failed save is reported next to the pipeline, which is still returned. When a file of that name
  was already there, the result says it was replaced. A link of that name is replaced by the new
  file, never written through. Saving is not a preview: the content written is the generated
  pipeline, which carries the model's own values (a command named in the model ends up in the
  pipeline), so a model should be trusted as much as the pipeline it produces.

## The pipeline history

Each successful generation is recorded under `GENERATOR_DATA_DIR/pipelines` (in Docker, the data
folder's `pipelines/`, see [ai/README.md](../README.md)'s "Where files live"), one folder per
pipeline named by a random id, holding the generation as real files:

```
<pipeline-id>/
  meta.json                          what the list shows
  record.json                        the rest of what reopening needs, without file contents
  model.swarch                       the model it was generated from
  <platform-id>/<output file>        each generated pipeline
  <platform-id>/intermediates/<file> the models the engine passed through on the way
```

A pipeline is identified by its model input, which is the workspace path it was loaded from or,
for an upload, its file name. Generating again for the same input replaces the earlier folder, so
the list has one row per input. The new generation is written into a temporary folder and only
then put in place, so the list never shows a pipeline whose files are not all there.
`GET /pipelines/{id}` reads the files back into one response.

## Why it runs the real engine command

The engine's code generator for a platform is a multi-module Acceleo plugin compiled into the
engine's jar. `execution_agent` runs a single `.atl` or `.mtl` source file, so it cannot run that
generator. The only faithful way to reuse the real chain is to run the engine's own command, so
the output is exactly what the Eclipse or command-line flow produces.

## What the engine command needs

The command is not a clean function of its arguments, and `engine_runner.py` is built around two
facts:

- It loads its compiled ATL transformations from a path relative to the working directory, not
  from the classpath.
- It writes intermediate files to fixed relative paths in the working directory. Two runs that
  share a directory delete each other's files, and the run fails.

So every request runs in its own scratch directory, seeded with a copy of the compiled
transformations, and the directory is deleted afterwards. Nothing the caller supplies is used to
build a path there: the model is always written under a fixed name. (The workspace folder, below,
is the one place a caller's path reaches the disk, and it is confined.)

## API

| Endpoint | Purpose |
|---|---|
| `GET /health` | Used by the Dockerfile's `HEALTHCHECK`. |
| `GET /platforms` | The platforms the engine can generate for end to end. |
| `POST /inspect` | Body: `filename`, `content`. Returns `element`, `attributes` (name and value, in file order) and `model_validation` (`valid` plus `issues`). An invalid model is a normal `200` answer. |
| `POST /generate` | Body: `filename`, `content`, `platforms` (one or more ids), optional `source_path` and `save_to`. Returns `pipeline_id` (null if nothing was recorded) and `results`, one per platform, each with `platform`, `output_filename`, `pipeline`, `intermediates`, `duration_ms`, `saved_to`, `replaced`, `save_error` and `error`. An empty `save_to` is refused; omit it to save nothing. One platform failing is reported in its own entry and does not discard the others. |
| `GET /pipelines` | The generated pipelines, newest first: `id`, `swarch_name`, `source_path`, `platforms`, `outputs`, `generated_at`. |
| `GET /pipelines/{id}` | One pipeline in full, including the model `content`, `save_to` and `results`. `404` for an unknown id. |
| `GET /workspace/browse` | Query: `path`, `dirs_only`. Returns `path`, `parent`, `entries` (name and `dir` or `file`) and `truncated`. |
| `GET /workspace/file` | Query: `path`. Returns `filename`, `path` and `content`. |
| `POST /validate` | Body: `platform`, `content` (the pipeline text). Returns `valid` and `checks`, each with `status` (`pass`, `fail` or `skipped`) and `details`. |

| Outcome | HTTP status |
|---|---|
| Success, including an invalid model on `/inspect` and a failing check on `/validate` | `200` |
| `/generate` given a model that fails validation (the response lists the issues) | `422` |
| Unsupported platform, a path outside the workspace, or a malformed body | `422` |
| A workspace path that names no file or folder | `404` |
| Content or a workspace file over the size limit | `413` |
| `validator_agent` unreachable | `502` |
| The workspace folder is not mounted | `503` |
| A workspace file or folder could not be read, listed or written | `500`, with a generic message (the cause is in the service log) |
| The engine failed to run or produced no output for a platform | `200`, with that platform's `error` set to a generic message |

The engine's own error text can contain stack traces and server paths, so it is written to the
service log and never returned to the caller. The same applies to `validator_agent` issues: only
severity and message are returned, never the server-side file path.

## Pipeline checks

`pipeline_checks.py` is pure Python. For GitLab CI it runs these checks in order:

1. The file is valid YAML.
2. The settings follow GitLab's published CI schema (vendored under `schemas/`, with its license
   notice next to it).
3. The pipeline defines at least one job.
4. Every job belongs to a declared stage.
5. Every `needs:` entry points at a real job.

The schema cannot see cross-references, which is why checks 4 and 5 exist. If the YAML itself is
broken, the later checks are reported as skipped rather than failed.

## Configuration

Every setting has a default. For a local run the defaults name a `./workspace` and a `./data`
folder next to the service: create `workspace` first, or the workspace routes answer `503`.

| Variable | Meaning |
|---|---|
| `VALIDATOR_AGENT_URL` | Where `validator_agent` is reached. |
| `GENERATOR_LIB_DIR` | The engine's classpath directory (its built `lib/`). |
| `ENGINE_TRANSFORMATIONS_DIR` | The directory of compiled ATL transformations the engine reads. |
| `GENERATOR_TIMEOUT_SECONDS` | How long one engine run may take. |
| `LOG_LEVEL` | Logging verbosity. |
| `GENERATOR_WORKSPACE_DIR` | The workspace folder (see above). In Docker it is `/workspace`. |
| `GENERATOR_WORKSPACE_LABEL` | How the Browse dialog names the workspace folder. Compose sets it to the host folder (`GENERATOR_WORKSPACE_HOST_DIR`, or a description of the default); set it yourself to show something clearer than a relative path. Unset, the folder's own path is shown. |
| `GENERATOR_DATA_DIR` | Where the pipeline history is kept, in a `pipelines/` folder inside it. In Docker it is `/data`, with the data folder's `pipelines/` mounted at `/data/pipelines`. |
| `GENERATOR_MAX_PIPELINES` | How many generated pipelines are kept. The oldest are forgotten beyond it. |
| `GENERATOR_MAX_MODEL_BYTES` | The largest uploaded or workspace model accepted. |
| `GENERATOR_MAX_PIPELINE_BYTES` | The largest file read back from the engine, and the largest pipeline `/validate` accepts. One setting, so a pipeline the engine can write can always be validated. |

In Docker, `ai/docker-compose.yml` sets the engine paths explicitly, pointing at the shared build
output volume and at a read-only mount of the engine's transformations directory, and mounts the
workspace folder and the history volume.

## Run and test

```
cd main && ./gradlew installDist          # builds the engine this service runs
cd ../ai/pipeline_generator
pip install -r requirements.txt
PYTHONPATH=.:.. uvicorn main:app --port 8100
pytest
```

`pytest` alone runs the fast suite on any machine. The real-engine tests start a real JVM and
skip themselves unless a JDK and the built engine are present.

## Adding a platform

A platform is supported when the engine has an end-to-end command for it. To add one:

1. Wire the engine command (a `swarch2<platform>` case in the engine's `Main`).
2. Add a `Platform` entry in `platforms.py`.
3. Add that platform's checker to `CHECKERS` in `pipeline_checks.py`.

## Boundaries

The workspace folder is the part of the host's disk this service reads or writes. The API has no
authentication, like the other services, so what protects the workspace is who can reach the UI:
`docker-compose.yml` publishes the UI's port on this machine only. Anyone who can reach it can list
the workspace's folders and `.swarch` files, read those files, and have pipeline files written into
it, so mount a folder you are comfortable exposing that way.

This is the service that runs the engine's own command, a deliberate exception described in
`ai/CLAUDE.md` (the fifth exception). It never imports Java code: it starts the engine as a
subprocess and reads files back. It reaches `validator_agent` through the shared `clients/`
package, like every other service.
