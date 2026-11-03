# pipeline_generator

The backend of the "Generate a CI/CD pipeline" mode. A person gives it a Software Architecture
model (a `.swarch` file, uploaded or picked from the workspace folder), and this service checks
it, turns it into a CI/CD pipeline for each chosen platform, keeps them so they can be reopened,
and checks the result.

It is UI-facing, the way `orchestrator` is for the platform-integration mode, but it is a
separate service. This mode has no pipeline run, no chat and no stages, and it calls no LLM.

## What it does, and how

1. **Inspect** (`POST /inspect`). Reads the uploaded model's attributes for display, and asks
   `validator_agent` whether the real EMF validator accepts it.
2. **Generate** (`POST /generate`). Checks the model again and answers at once with a job id.
   Then, for each chosen platform, it runs that platform's files through
   `execution_agent` and keeps the generated pipeline plus the intermediate models it passed
   through, for
   `GET /generate/{job_id}` to return. It records the generation so it can be reopened. A
   generation can take longer than a proxy in front of the service allows one request to last,
   which is why it does not answer with the results.
3. **Validate** (`POST /validate`). Checks the generated pipeline file and returns a short
   checklist in plain language.

Around these, more concerns have their own modules: the **workspace** (`workspace.py`), the
**platforms** (`platform_registry.py` and `platform_runner.py`) and the **pipeline history**
(`pipeline_history.py`). They are described below.

## The workspace

A web page cannot read a path on the user's machine, so the service works inside one folder that
is mounted into it. In Docker Compose that is the data folder's `workspace/` on the host
(`MDDOAI_DATA_DIR` in the environment's `config.env`, see [ai/README.md](../README.md)'s "Where
files live"), seen read-only as `/workspace` inside the container. Put the models you want to pick
in it, or use Upload. The Browse dialog names the folder it is showing, from
`GENERATOR_WORKSPACE_LABEL`, which compose sets to the host folder (`GET /workspace/browse`
returns it as `root_label`).

Every path a caller supplies is read as relative to that folder: `/repos/app/a.swarch`,
`./repos/app/a.swarch` and `repos/app/a.swarch` are the same file. Whatever a path resolves to,
links included, must still be inside the folder. A path that leads outside is refused, nothing
outside is listed, and error messages never name a location outside the folder. This holds as
long as nothing else is changing the folder's links while a request runs: a path is checked and
then used, not held open in between.

- **Reading.** `GET /workspace/browse` lists a folder (for the Browse dialog). `GET /workspace/file`
  returns a model file's text, so it loads exactly like an upload. Every folder and regular file
  is listed by name, so a person can see what is in the workspace. Only `.swarch` files are read,
  and only up to `GENERATOR_MAX_MODEL_BYTES`. Other files are never opened. Links that leave the
  workspace are not listed.
- **No writing.** Nothing is ever written into the workspace. A generated pipeline is shown and
  downloaded in the browser, and kept in the pipeline history.

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
  <platform-id>/intermediates/<file> the models the generation passed through on the way
```

A pipeline is identified by its model input, which is the workspace path it was loaded from or,
for an upload, its file name. Generating again for the same input replaces the earlier folder, so
the list has one row per input. The new generation is written into a temporary folder and only
then put in place, so the list never shows a pipeline whose files are not all there.
`GET /pipelines/{id}` reads the files back into one response.

## The platforms

The platforms are in the data folder's `platforms/` (in Docker, mounted read-only at
`/data/platforms`). Unlike the rest of the data folder, these files are committed to the
repository:

```
platforms/
  swarch2pim.atl                the transformation every platform starts with
  <platform-id>/<version>/
    metamodel.ecore             the platform's metamodel
    transformation.atl          the transformation from the PIM to the platform's model
    template.mtl                the Acceleo template from that model to the pipeline file
    platform.json               id, name, version, the pipeline's file name, the name the
                                transformation gives the model it creates (the `GitLabMM` in
                                `create OUT : GitLabMM from IN : PIM;`)
```

Generation uses each platform's latest version. A version without a usable `platform.json`, or
missing one of its files, is skipped with a log line, and the version before it is used. The files
are read on every request, so a changed file applies to the next generation. Nothing else is read
to run a platform.

These files belong to this service and are changed here. They do not follow `main/`, which is a
separate codebase. Docker Compose mounts the folder read-only, and the image carries no copy of it.
The data folder is the repository's `ai/data` unless `MDDOAI_DATA_DIR` points elsewhere, and then
it needs its own `platforms/`. A missing folder, or one with no usable platform, is logged as an
error on every request that needs it.

## How a pipeline is generated

For each platform, `platform_runner.py` makes three calls, each to the service that owns the step:

1. `execution_agent` runs `swarch2pim.atl` on the model, giving the PIM every platform starts from.
2. `execution_agent` runs the platform's `transformation.atl` on the PIM, giving the platform's
   own model.
3. `execution_agent` runs the platform's `template.mtl` on that model, giving the pipeline file.

The same three steps run for every platform, and nothing in them is specific to one. The service
has no JVM of its own and writes no scratch files. The PIM and the platform's model are kept as
the intermediate models. A failing step is reported in that platform's own result. The other
service's error text goes to the log, never to the caller.

## API

| Endpoint | Purpose |
|---|---|
| `GET /health` | Used by the Dockerfile's `HEALTHCHECK`. |
| `GET /platforms` | The platforms in the registry, at their latest versions: `id`, `name`, `output_filename`. |
| `POST /inspect` | Body: `filename`, `content`. Returns `element`, `attributes` (name and value, in file order) and `model_validation` (`valid` plus `issues`). An invalid model is a normal `200` answer. |
| `POST /generate` | Body: `filename`, `content`, `platforms` (one or more ids), optional `source_path`. Checks the request, answers `202` with a `job_id`, and generates afterwards. |
| `GET /generate/{job_id}` | The generation's `state`: `running`, `failed` (with a generic `error`; the cause is in the service log) or `done`. A done job has `pipeline_id` (null if nothing was recorded) and `results`, one per platform, each with `platform`, `output_filename`, `pipeline`, `intermediates`, `duration_ms` and `error`. One platform failing is reported in its own entry and does not discard the others. |
| `GET /pipelines` | The generated pipelines, newest first: `id`, `swarch_name`, `source_path`, `platforms`, `outputs`, `generated_at`. |
| `GET /pipelines/{id}` | One pipeline in full, including the model `content` and `results`. `404` for an unknown id. |
| `GET /workspace/browse` | Query: `path`. Returns `path`, `parent`, `entries` (name and `dir` or `file`) and `truncated`. |
| `GET /workspace/file` | Query: `path`. Returns `filename`, `path` and `content`. |
| `POST /validate` | Body: `platform`, `content` (the pipeline text). Returns `valid` and `checks`, each with `status` (`pass`, `fail` or `skipped`) and `details`. |

| Outcome | HTTP status |
|---|---|
| Success, including an invalid model on `/inspect` and a failing check on `/validate` | `200` |
| `/generate` accepted the request | `202` |
| `/generate` given a model that fails validation (the response lists the issues) | `422` |
| A generation job that is unknown, for example lost in a restart (jobs are kept in memory only; the pipeline history is not) | `404` |
| Unsupported platform, a path outside the workspace, or a malformed body | `422` |
| A workspace path that names no file or folder | `404` |
| Content or a workspace file over the size limit | `413` |
| `validator_agent` unreachable | `502` |
| The workspace folder is not mounted | `503` |
| A workspace file or folder could not be read or listed | `500`, with a generic message (the cause is in the service log) |
| A generation step failed or produced no pipeline for a platform | `200`, with that platform's `error` set to a generic message |

The other services' own error text can contain stack traces and server paths, so it is written to
the service log and never returned to the caller. The same applies to `validator_agent` issues: only
severity and message are returned, never the server-side file path.

## Pipeline checks

The checks themselves are `validator_agent`'s (`POST /validate/pipeline`, described in its README).
`POST /validate` here sends `validator_agent` the pipeline and the file name the platform gives it,
and returns the checklist as it comes back. The check made is that a YAML file parses as YAML.

A check that cannot be made is reported as skipped, never as passed. The UI says "passed" only when
every check was made and passed, and otherwise says how many were skipped, or that nothing could be
checked.

## Configuration

Every setting has a default. For a local run the defaults name a `./workspace` and a `./data`
folder next to the service: create `workspace` first, or the workspace routes answer `503`.

| Variable | Meaning |
|---|---|
| `VALIDATOR_AGENT_URL`, `EXECUTION_AGENT_URL` | Where `validator_agent` and `execution_agent` are reached. |
| `GENERATOR_MAX_RUNNING_JOBS` | How many generations run at once. The rest wait their turn. |
| `GENERATOR_MAX_FINISHED_JOBS` | How many finished generations are kept for their callers to fetch. |
| `LOG_LEVEL` | Logging verbosity. |
| `GENERATOR_WORKSPACE_DIR` | The workspace folder (see above). In Docker it is `/workspace`. |
| `GENERATOR_WORKSPACE_LABEL` | How the Browse dialog names the workspace folder. Compose sets it to the host folder. Unset, the folder's own path is shown. |
| `GENERATOR_DATA_DIR` | Where the pipeline history is kept, in a `pipelines/` folder inside it, and the platforms, in a `platforms/` folder. In Docker it is `/data`, with the data folder's `pipelines/` and `platforms/` mounted inside it. |
| `GENERATOR_MAX_PIPELINES` | How many generated pipelines are kept. The oldest are forgotten beyond it. |
| `GENERATOR_MAX_MODEL_BYTES` | The largest uploaded or workspace model accepted. |
| `GENERATOR_MAX_PIPELINE_BYTES` | The largest pipeline a template may produce, and the largest `/validate` accepts. `validator_agent`'s own `MAX_PIPELINE_BYTES` must be at least as large, or it refuses a pipeline this service accepted (the answer is then `413`). |

In Docker, `ai/docker-compose.yml` sets the services' addresses and mounts the workspace folder
(read-only), the pipeline history and the platforms folder (read-only).

## Run and test

```
cd ai/pipeline_generator
pip install -r requirements.txt
PYTHONPATH=.:.. uvicorn main:app --port 8100
pytest
```

`pytest` alone runs the fast suite on any machine. The real-service tests run the committed GitLab
platform through a real `execution_agent`, and skip themselves unless it answers. Inside this
service's container in the running stack they do.

## Adding a platform

Add `<platform-id>/<version>/` to the data folder's `platforms/`, holding each file listed in the
layout above, and commit it.

## Boundaries

The workspace folder is the part of the host's disk this service reads from a caller's path, and
it is mounted read-only. The API has no authentication, like the other services, so what protects the workspace
is who can reach the UI: `docker-compose.yml` publishes the UI's port on this machine only. Anyone
who can reach it can list the workspace's folders and files and read its `.swarch` files, so mount
a folder you are comfortable exposing that way.

The data folder's `platforms/` is trusted input. Its transformations and templates are compiled and run by
`execution_agent`, and an Acceleo template can call Java code (`invoke`), so a change to it is
reviewed like any other code change. It is committed to the repository, Docker Compose mounts it
read-only, and no route of this service writes to it.

This service runs no Java code and reads nothing from `main/`. It reaches `validator_agent` and
`execution_agent` through the shared `clients/` package, like every other service.
