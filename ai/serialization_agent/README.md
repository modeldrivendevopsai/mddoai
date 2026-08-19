# serialization_agent

The serialization stage: turns the docs stage's raw, unstructured documentation markdown into a
structured artifact, labeled against MDDOAI's real PIM concepts, for the pim stage (and any
future stage) to build on. Three steps, one LLM call: an LLM call extracts pipeline/job/task-shaped
fragments from the raw prose (`_extract_fragments`), each fragment is labeled deterministically
against `pim_agent`'s real PIM concepts via `clients/pim_agent_client.py`'s `ground()` (no LLM
call), then the labeled fragments are assembled into markdown by plain string formatting (no LLM
call). A fragment that doesn't match any of the 9 concepts is never dropped, it's kept under an
"Unrecognized" section — the point of this stage is complete, honest restructuring, not lossy
filtering. See `serialization.py`'s own docstring for the full detail.

Own container, own port (8060), same reasoning as `pim_agent`/`psm_agent`: each real pipeline
capability gets independent deployability, not bundled into whichever process happens to call it.
Reached by `integration_runner`'s serialization stage
(`integration_runner/stages/serialization/agent.py`) via `clients/serialization_agent_client.py`.
This service in turn calls `ai-layer` (the extraction LLM call) and `pim_agent` (concept
grounding) directly.

## API

### `POST /serialize`

```json
// request
{
  "docs_output": "Fetched 2 page(s) from https://example.com/docs, confidence 0.80.\n\n# Docs\nRuns on a cron schedule.",
  "model": null
}

// response (200)
{
  "markdown": "# Serialization: labeled documentation structure\n\n...\n"
}
```

`model` is optional, forwarded to the extraction call the same way every other stage forwards the
run's chosen model; omit or pass `null` to use `ai-layer`'s own default.

### `GET /health`

Used by the Dockerfile's `HEALTHCHECK`.

## Setup

```bash
pip install -r requirements.txt
cp .env.example .env
# AI_LAYER_URL defaults to http://localhost:8000, PIM_AGENT_URL to
# http://localhost:8030, if unset — override only if either runs somewhere else.
```

## Run

```bash
uvicorn main:app --reload --port 8060
```

Because `main.py` imports the sibling `clients/` package, which lives outside this folder,
`PYTHONPATH` needs to include the parent `ai/` directory too:

```bash
# from ai/serialization_agent/
PYTHONPATH=.. uvicorn main:app --reload --port 8060
```

## Test

```bash
cd serialization_agent
pytest
```

No real network calls — `clients/ai_layer_client.chat` and `clients/pim_agent_client.ground` are
mocked, with `pim_agent_client.ground`'s mock delegated to `pim_agent`'s own real, unmocked
`ground()` (already exercised for real in `ai/pim_agent/tests/test_reference_knowledge.py`), so
labeling tests here still exercise real matching behavior over the mocked HTTP boundary. See
`tests/test_serialization_agent.py`'s own module docstring for the full test breakdown, including
the Issue #221 real-Bamboo-documentation regression test.
