# journal

The one service that reads and writes the run, chat, platform, prompt config, attachment and pipeline folders. Every other service keeps and reads what is in them through its API, using `clients/journal_client.py`, never through the folders. It knows how to keep things, and nothing about what a run, a chat or a stage means: that stays with the service that owns the idea.

Internal-only (no host port), like the agents. Services that call it start after it is healthy.

## What it keeps

| Folder | What | Written by callers as |
|---|---|---|
| `JOURNAL_RUNS_DIR` | `<run_id>/run.jsonl`, the run's history, and `<run_id>/<stage>/attempt_N/`, what each try of a stage produced | histories of kind `runs`, and attempts |
| `JOURNAL_CHATS_DIR` | `<run_id>.jsonl`, the run's chat | histories of kind `chats` |
| `JOURNAL_PLATFORMS_DIR` | `<platform-id>/<version>/`, each platform version's files, and the SWArch to PIM transformation beside them | platforms |
| `JOURNAL_PROMPTS_DIR` | `<agent>/<name>/`, a prompt config's live file, its shipped starting file, its saved versions and its learned constraints | prompt configs |
| `JOURNAL_ATTACHMENTS_DIR` | `<agent>/<file>`, the files uploaded to an agent as attachments | uploads |
| `JOURNAL_PIPELINES_DIR` | `<pipeline-id>/`, a generation as real files: the model, each pipeline, the models it passed through | pipelines |

Each folder is its own required setting with no default. In Docker they are the mounts of this service alone, and the host folders come from `config.env`.

## API

**Histories.** A history is a list of events and some state fields, such as a run or a chat. `GET /histories/{kind}` lists the keys. `GET /histories/{kind}/{key}` returns `{state, events}`. `PUT` replaces a history whole. `POST /histories/{kind}/{key}/append` takes `{start, state, events}`: where in the history the events begin, the state fields that changed, and the events. An append that is sent again after a lost answer changes nothing, because events kept from `start` on are written over by the same ones. An append that starts past the end is a `409` with `count`, how many events are kept, so the caller can send the history whole. A key is letters, digits, `_` and `-`, up to 64 characters. `DELETE /histories/{kind}/{key}` removes one, and for a run it removes the run's attempt folders with it. It is a `404` for an unknown key, and a `409` for a run a platform was published from, which is kept until that platform is removed.

**Attempts.** `POST /attempts/{run_id}` reserves the next attempt of a stage, which claims its number. `PUT /attempts/{run_id}/{stage}/{attempt}` records it: the artifact, the validator's result, and optionally the prompt and other files, which can sit in subfolders. `PUT /attempts/{run_id}/{stage}/{attempt}/compiled` keeps what the validators compiled during an attempt, as one base64 zip, until the attempt is recorded, and `GET` reads it. `DELETE` releases a reserved attempt that was never recorded, and refuses one that was. `GET /attempts/{run_id}/manifest` lists every recorded attempt of a run. `GET /attempts/{run_id}/{stage}/{attempt}` reads one.

**Platforms.** `GET /platforms` lists each platform's latest version, with every version it has (`versions`), which of those were published (`published_versions`, the only ones that can be purged), and whether the latest was (`published`). `GET /platforms/{id}?version=` reads a version, its latest when none is named, with its three files. `POST /platforms` publishes a version, the patch after the highest unless `version` is given. `DELETE /platforms/{id}/versions/{version}` and `DELETE /platforms/{id}` purge published versions. `GET /platforms/pim-transformation` serves the SWArch to PIM transformation.

**Prompt configs.** An agent that writes prompts keeps its configs here under its own name, such as `psm`, and the config's name, such as `generation`. A config is a dict with an `attachments` list: what an attachment means, and whether a config is valid, is the agent's to say. `GET /prompt-configs/{agent}/{name}` reads the live config, or the shipped starting one when none was saved. `PUT` keeps a config as the live one and as a new version, stamped with its version id. A config whose attachments are the live one's is no change: the live config comes back and no version is added, so a save that is sent twice does not pile up copies. Only the newest `MAX_PROMPT_VERSIONS` versions are kept, and the shipped config is never pruned. `GET .../shipped` reads the shipped config. `GET .../versions` lists the saved versions, newest first, and says whether a shipped config exists. `GET .../versions/{version}` reads one. `GET` and `PUT .../constraints` read and replace the learned constraints, which are never versioned, so restoring an older config does not lose them. They are a `404` until some were kept.

**Pipelines.** `POST /pipelines` keeps a generation and returns its id: the model it was generated from, each generated pipeline and the models it passed through, with a `key` that says what it was generated from. A newer generation for the same key replaces the older, so the list has one row per key, and only the newest `MAX_PIPELINES` are kept. `GET /pipelines` lists them newest first without the bulky parts, and `GET /pipelines/{id}` reads one in full. A generation is written whole into place and the one it replaces is put back if that fails, so the list never shows a pipeline whose files are not all there.

**Uploads.** `PUT /uploads/{agent}/{name}` keeps a text file the agent named, which must not exist yet and must be within `MAX_UPLOAD_BYTES`. `GET /uploads/{agent}` lists the names, and `GET /uploads/{agent}/{name}` reads one.

A request it refuses is `404` for an unknown name, `409` for a version that exists or a platform that came with the stack, `413` for a file over the largest a publish (`MAX_ARTIFACT_BYTES`) or an upload (`MAX_UPLOAD_BYTES`) accepts, both in `settings.py`, and `422` for input that cannot be used, always with a `detail` a person can read.

## Versions

A platform version is `MAJOR.MINOR.PATCH`, as in [semantic versioning](https://semver.org): whole numbers without leading zeros, compared as numbers, so `1.10.0` is after `1.9.0`. Pre-release and build suffixes are not used. A platform's latest is its highest version, so publishing `1.5.0` after `2.0.0` does not change what pipelines are generated with. A version is never changed once published. A version that exists is refused, and a purged version can be published again.

## Purging

Only a published version can be purged. A version that came with the stack has no publish record, so it is kept, and a purge of a whole platform that holds one removes nothing. A publish that stopped part way leaves a folder that is never listed and that a purge clears.

## Settings

The six folders above are required, with no default. The largest file a publish or an upload accepts, the largest compiled output of an attempt, how many versions of a prompt config and how many pipelines are kept, are named constants in `settings.py`. An agent's own upload limit must not be above the journal's, which `ai/tests` pins. A service that calls the journal must be given `JOURNAL_URL`, also with no default: Docker Compose sets it, and the service stops at start without it. How long a caller waits, and how often it asks again, are named constants in `clients/journal_client.py`.

A service that cannot reach the journal goes on in memory and logs it once. After a failed save it skips further saves for a few seconds, so a journal that hangs costs one wait and not one per save, and the next save that gets through sends everything since the last one that did. A run or a chat is therefore kept again at its next change after an outage. A service that starts while the journal is not answering asks for its saved histories again for a while and then stops, because it would otherwise show nothing and narrate every event again.

## Layout

- `main.py` assembles the app, and `routes/` holds one file per resource.
- `histories.py`, `attempts.py`, `platforms.py`, `prompt_configs.py`, `uploads.py` and `pipelines.py` keep each resource on disk, `platform_meta.py` is what one platform version is on disk and what a publish must satisfy, and `file.py` is the append-only file of JSON records under the histories.
- `versions.py` is the version rules.
- `dependencies.py` builds the stores from the settings. `settings.py` reads them.

## Where this is headed

Run history may later be queried across runs, to show forks or find patterns, and the storage behind the API may become another kind of store. Callers use the API and never a path, so that change stays inside this service.

## Run

```bash
export JOURNAL_RUNS_DIR=$PWD/runs JOURNAL_CHATS_DIR=$PWD/chats JOURNAL_PLATFORMS_DIR=$PWD/../data/platforms JOURNAL_PROMPTS_DIR=$PWD/../data/prompts JOURNAL_ATTACHMENTS_DIR=$PWD/attachments JOURNAL_PIPELINES_DIR=$PWD/pipelines
PYTHONPATH=.:.. uvicorn main:app --port 8120
```

## Test

`pytest` in this folder. The tests use the real app over temporary folders. Other services' tests use `clients/journal_testing.py` to run the real journal in their own process.
