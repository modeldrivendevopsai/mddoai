# psm_agent

The real `psm` pipeline stage's whole capability, `POST /psm` (`psm_flow.py`'s `run()`): routes
between two distinct real capabilities depending on whether the target platform already has a
real PSM metamodel checked into `meta_models/`.

- **No existing metamodel (new platform)** → the **Generation Agent** (`generation.py`'s
  `generate()`): given the real, run-specific PIM artifact and the target platform's docs, assembles
  the master example metamodel + docs + PIM artifact into a prompt and runs it through the shared
  `generation_toolkit` package's `run_with_retry()` (a stage-agnostic "build a prompt, call the LLM,
  validate, retry" toolkit, not specific to PSM — see `generation_toolkit/README.md`), asking
  `ai-layer` to generate a new `.ecore` and checking it
  against `validator_agent`'s real `/validate/ecore` (reflective mode) as the toolkit's `validate_fn`.
  On failure, the validator's first issue becomes one new constraint and the prompt is rebuilt for
  another round — bounded, so a platform whose docs genuinely can't produce a loadable `.ecore` fails
  closed instead of looping forever. Grounding (pulling relevant PIM-concept context into the prompt)
  reuses `pim_agent`'s existing `ground()`/`concepts()` — there is no separate RAG agent yet (a
  documented Phase 1 plan, not built here).
- **An existing metamodel (known platform)** → the **Knowledge Agent** (`comparison.py`'s
  `compare()`, unchanged): a real LLM comparison of the docs against the existing `.ecore` to
  find drift (missing/outdated concepts). Informational only — a gap is surfaced alongside the
  existing metamodel's own unchanged content, never an automatic edit.

`psm_flow.run()` is the first real caller `compare()` has ever had in the live pipeline; before
this it was callable (`POST /compare`, still available standalone) but not wired into any real
run.

Own container, own port (8040). Promoted out of `orchestrator`'s own container for the same
documented reason as `pim_agent` (see its own README). Today's code is still plain Python, a real
Java/EMF/Gradle migration (the same subprocess-wrapped-as-HTTP pattern already used elsewhere in
this repo for JVM-backed validation) is separate, real, future work, not built here.

There is no single `psmMM.ecore` in this repo: MDDOAI's PSM is realized per target platform
(`gitlabMM.ecore`, `githubMM.ecore`, and `bitbucketMM.ecore` once added, all under the repo
root's `meta_models/`). `known_psm_platforms()`/`resolve_platform_metamodel()` discover which
platforms already have one directly from `meta_models/`'s own directory layout, rather than a
hardcoded list, so a newly added platform needs no code change here. `compare()`'s own
`psm_metamodel_path` defaults to `gitlabMM.ecore` (`DEFAULT_PSM_METAMODEL_PATH`), since MDDOAI
targets GitLab specifically; `generate()`'s master example defaults to `githubMM.ecore`
(`DEFAULT_PSM_MASTER_EXAMPLE_PATH`) — a different role (a fixed structural exemplar for a *new*
metamodel, not a drift-check target), so it's a separate constant, not a reuse of the same one.

## API

### `POST /psm`

```json
// request
{
  "platform_description": "TeamCity",
  "pim_artifact": "A pipeline consists of jobs organized into stages...",
  "platform_docs": "# TeamCity CI/CD Configuration\n...",
  "constraints": [],
  "model": null
}

// response (200, generation mode - no existing metamodel for this platform)
{
  "mode": "generation",
  "artifact": "<?xml version=\"1.0\"?><ecore:EPackage ...>",
  "prompt": {
    "pim_ecore": "A pipeline consists of jobs organized into stages...",
    "psm_docs": "# TeamCity CI/CD Configuration\n...",
    "psm_example": "<?xml version=\"1.0\"?>... (githubMM.ecore's real content)",
    "constraints": "- Fix: dangling reference to RetryPolicy"
  },
  "validation": {"valid": true, "mode": "reflective", "issues": [], "duration_ms": 120, "generated_source_path": null},
  "rounds": 2
}

// response (200, knowledge mode - platform already has a real metamodel)
{
  "mode": "knowledge",
  "artifact": "<?xml version=\"1.0\"?>... (the existing gitlabMM.ecore's own content, unchanged)",
  "gaps": [
    {
      "kind": "missing",
      "target": "RetryPolicy",
      "description": "The documentation describes per-job retry counts, but gitlabMM.ecore has no RetryPolicy class or equivalent property.",
      "source_excerpt": "Jobs can be configured to retry up to 2 times on failure."
    }
  ],
  "prompt": {"pim_ecore": "...", "psm_docs": "...", "psm_example": "...", "constraints": ""}
}
```

`constraints`/`model` are optional. `400` if a resolved metamodel path doesn't exist on disk.

### `POST /compare`

Still available standalone (`psm_flow.run()` calls it internally for the knowledge-mode path
above, doesn't replace it) — see `comparison.py`'s own docstring. Same request/response shape as
before: `{"serialized_docs", "psm_metamodel_path"}` → `{"suggestions": [...]}`.

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
`PIM_AGENT_URL`/`VALIDATOR_AGENT_URL` (both `ai/clients/` modules) point at those sibling
services, defaulting to their own local-dev ports.

## Run

```bash
uvicorn main:app --reload --port 8040
```

## Test

```bash
cd psm_agent
pytest
```

`tests/test_comparison.py` reads real `.ecore` files from the repo's actual `meta_models/` tree
rather than mocking them, so `META_MODELS_DIR` resolving to a real, existing path is itself part
of what's under test, not assumed. `tests/test_generation.py`/`tests/test_psm_flow.py` mock the
real network boundaries (`ai_layer_client`, `pim_agent_client`, `validator_agent_client`) instead,
the same convention `test_comparison.py` already uses for its own one real network call — this
service's own test suite only covers PSM-specific wiring, the generic retry-loop mechanics have
their own tests under `generation_toolkit/tests/`. `tests/test_generation_real_llm.py` is the one
real, no-mocking end-to-end test — it needs a real running `ai-layer`, `pim-agent`, and
`validator-agent` (`docker compose up ai-layer pim-agent validator-agent`), and auto-skips
otherwise.
