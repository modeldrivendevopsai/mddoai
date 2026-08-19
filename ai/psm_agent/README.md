# psm_agent

Compares serialized platform documentation against one of MDDOAI's real PSM (Platform-Specific
Model) metamodels and reports what's missing or outdated, via a real LLM call (`comparison.py`'s
`compare()`, through `clients/ai_layer_client.py`, the same `ai-layer` `/chat` convention every
other agent in this repo uses). Unlike `pim_agent`'s `ground()`, this isn't a static knowledge
lookup: the comparison itself, not just grounding context, is the point.

**Not called by anything live yet.** `integration_runner`'s own `psm` stage (a different,
unrelated placeholder) is still a plain LLM prompt, so there's no `psm_agent_client.py` in
`ai/clients/` and nothing in the running pipeline reaches this service today. It exists so the
real capability is callable and testable now, ahead of that wiring, the same "build the real
thing, wire it in later" order `serialization_agent`/`pim_agent` already went through.

Own container, own port (8040). Promoted out of `orchestrator`'s own container for the same
documented reason as `pim_agent` (see its own README): `MDDOAI_Agentic_Architecture.drawio`'s
Phase 1 plan has the same Graph-RAG "RAG Agent" splitting the real model and feeding it to the
PSM Agent directly, beyond today's plain text comparison. Today's code is still plain Python,
a real Java/EMF/Gradle migration (the same subprocess-wrapped-as-HTTP pattern already used
elsewhere in this repo for JVM-backed validation) is separate, real, future work, not built
here — see `pim_agent/README.md` for why that specific runtime is a reasonable inference from
the documented plan, not something the plan states outright.

There is no single `psmMM.ecore` in this repo: MDDOAI's PSM is realized per target platform
(`gitlabMM.ecore`, `githubMM.ecore`, and `bitbucketMM.ecore` once added, all under the repo
root's `meta_models/`), so `compare()`'s `psm_metamodel_path` is a parameter, not a fixed path.
It defaults to `gitlabMM.ecore` (`DEFAULT_PSM_METAMODEL_PATH`), since MDDOAI targets GitLab
specifically (see `pim_agent`'s own reusability notes).

## API

### `POST /compare`

```json
// request
{
  "serialized_docs": "## Pipeline\n\n- **CI Pipeline** (pipeline)\n\n  ```\n  A pipeline runs a series of jobs...\n  ```\n",
  "psm_metamodel_path": null
}

// response (200)
{
  "suggestions": [
    {
      "kind": "missing",
      "target": "RetryPolicy",
      "description": "The documentation describes per-job retry counts, but gitlabMM.ecore has no RetryPolicy class or equivalent property.",
      "source_excerpt": "Jobs can be configured to retry up to 2 times on failure."
    }
  ]
}
```

`kind` is `"missing"` (the docs describe something the metamodel has no representation for) or
`"outdated"` (the metamodel represents it, but in a way that no longer matches what the docs
describe, e.g. a renamed field or changed relationship). `psm_metamodel_path` is optional,
defaulting to `gitlabMM.ecore`; pass `githubMM.ecore`'s real path explicitly to compare against
that platform's PSM instead. `400` if the given (or default) metamodel path doesn't exist on
disk.

### `GET /health`

Used by the Dockerfile's `HEALTHCHECK`.

## Setup

```bash
pip install -r requirements.txt
```

`META_MODELS_DIR` is optional, defaulting to the real relative repo path to the root
`meta_models/` tree (`Path(__file__).resolve().parents[2] / "meta_models"`) for local/non-Docker
dev. Override it only if `meta_models/` is reachable somewhere else, e.g. the Docker Compose
read-only bind mount `ai/docker-compose.yml`'s `psm-agent` service entry sets it to.

## Run

```bash
uvicorn main:app --reload --port 8040
```

## Test

```bash
cd psm_agent
pytest
```

`tests/test_comparison.py` mocks `clients.ai_layer_client.chat` (the only real network call
`compare()` makes) and reads a real `.ecore` file from the repo's actual `meta_models/` tree, so
`_META_MODELS_DIR`/`DEFAULT_PSM_METAMODEL_PATH` resolving to a real, existing path is itself
part of what's under test, not assumed.
