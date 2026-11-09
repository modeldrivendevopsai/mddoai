# journal

The one service that reads and writes the run, chat and platform folders. Every other service keeps and reads what is in them through its API, using `clients/journal_client.py`, never through the folders. It knows how to keep things, and nothing about what a run, a chat or a stage means: that stays with the service that owns the idea.

Internal-only (no host port), like the agents. Services that call it start after it is healthy.

## What it keeps

| Folder | What | Written by callers as |
|---|---|---|
| `JOURNAL_RUNS_DIR` | `<run_id>/run.jsonl`, the run's history, and `<run_id>/<stage>/attempt_N/`, what each try of a stage produced | histories of kind `runs`, and attempts |
| `JOURNAL_CHATS_DIR` | `<run_id>.jsonl`, the run's chat | histories of kind `chats` |
| `JOURNAL_PLATFORMS_DIR` | `<platform-id>/<version>/`, each platform version's files, and the SWArch to PIM transformation beside them | platforms |

Each folder is its own required setting with no default. In Docker they are the mounts of this service alone, and the host folders come from `config.env`.

## API

**Histories.** A history is a list of events and some state fields, such as a run or a chat. `GET /histories/{kind}` lists the keys. `GET /histories/{kind}/{key}` returns `{state, events}`. `PUT` replaces a history whole. `POST /histories/{kind}/{key}/append` takes `{start, state, events}`: where in the history the events begin, the state fields that changed, and the events. An append that is sent again after a lost answer changes nothing, because events kept from `start` on are written over by the same ones. An append that starts past the end is a `409` with `count`, how many events are kept, so the caller can send the history whole. A key is letters, digits, `_` and `-`, up to 64 characters.

**Attempts.** `POST /attempts/{run_id}` reserves the next attempt of a stage, which claims its number. `PUT /attempts/{run_id}/{stage}/{attempt}` records it: the artifact, the validator's result, and optionally the prompt and other files, which can sit in subfolders. `DELETE` releases a reserved attempt that was never recorded, and refuses one that was. `GET /attempts/{run_id}/manifest` lists every recorded attempt of a run. `GET /attempts/{run_id}/{stage}/{attempt}` reads one.

**Platforms.** `GET /platforms` lists each platform's latest version, with the versions it has and whether it was published. `GET /platforms/{id}?version=` reads a version, its latest when none is named, with its three files. `POST /platforms` publishes a version, the patch after the highest unless `version` is given. `DELETE /platforms/{id}/versions/{version}` and `DELETE /platforms/{id}` purge published versions. `GET /platforms/pim-transformation` serves the SWArch to PIM transformation.

A request it refuses is `404` for an unknown name, `409` for a version that exists or a platform that came with the stack, `413` for a file over `JOURNAL_MAX_ARTIFACT_BYTES`, and `422` for input that cannot be used, always with a `detail` a person can read.

## Versions

A platform version is `MAJOR.MINOR.PATCH`, as in [semantic versioning](https://semver.org): whole numbers without leading zeros, compared as numbers, so `1.10.0` is after `1.9.0`. Pre-release and build suffixes are not used. A platform's latest is its highest version, so publishing `1.5.0` after `2.0.0` does not change what pipelines are generated with. A version is never changed once published. A version that exists is refused, and a purged version can be published again.

## Purging

Only a published version can be purged. A version that came with the stack has no publish record, so it is kept, and a purge of a whole platform that holds one removes nothing. A publish that stopped part way leaves a folder that is never listed and that a purge clears.

## Layout

- `main.py` assembles the app, and `routes/` holds one file per resource.
- `histories.py`, `attempts.py` and `platforms.py` keep each resource on disk, and `file.py` is the append-only file of JSON records under the histories.
- `versions.py` is the version rules.
- `dependencies.py` builds the stores from the settings. `settings.py` reads them.

## Where this is headed

Run history may later be queried across runs, to show forks or find patterns, and the storage behind the API may become another kind of store. Callers use the API and never a path, so that change stays inside this service. Other kinds of data the stack keeps, such as the generated pipelines and the prompt configs, are not here yet.

## Run

```bash
export JOURNAL_RUNS_DIR=$PWD/runs JOURNAL_CHATS_DIR=$PWD/chats JOURNAL_PLATFORMS_DIR=$PWD/../data/platforms
PYTHONPATH=.:.. uvicorn main:app --port 8120
```

## Test

`pytest` in this folder. The tests use the real app over temporary folders. Other services' tests use `clients/journal_testing.py` to run the real journal in their own process.
