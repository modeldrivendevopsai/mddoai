# serialization_agent

The serialization stage: turns the docs stage's raw, unstructured documentation markdown into a
structured artifact, labeled against MDDOAI's real PIM concepts, for the pim stage (and any
future stage) to build on. It keeps all of the documentation and calls no model: the text is cut
into its markdown sections (at headings outside code fences), each section is labeled
deterministically against `pim_agent`'s real PIM concepts via `clients/pim_agent_client.py`'s
`ground()`, and the sections are written out verbatim under their concept. A section that doesn't
match any of the 9 concepts is kept under an "Unrecognized" section. A page with no headings (a
platform's JSON schema, say) is one section. An earlier version asked a model to extract the
relevant fragments, and it kept a few hundred characters of a 60,000 character page. See
`serialization.py`'s own docstring for the full detail.

Own container, own port (8060), same reasoning as `pim_agent`/`psm_agent`: each real pipeline
capability gets independent deployability, not bundled into whichever process happens to call it.
Reached by `integration_runner`'s serialization stage
(`integration_runner/stages/serialization/agent.py`) via `clients/serialization_agent_client.py`.
This service in turn calls `pim_agent` (concept grounding) directly.

## API

### `POST /serialize`

```json
// request
{
  "docs_output": "Fetched 2 page(s) from https://example.com/docs, confidence 0.80.\n\n# Docs\nRuns on a cron schedule."
}

// response (200)
{
  "markdown": "# Serialization: labeled documentation structure\n\n...\n"
}
```

### `GET /health`

Used by the Dockerfile's `HEALTHCHECK`.

## Setup

```bash
pip install -r requirements.txt
```

`PIM_AGENT_URL` is a required setting with no default: Docker Compose sets it for the container.
Running outside Docker, export it first (uvicorn does not read `.env.example`). Without it the
service stops at start and names it.

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

No real network calls — `clients/pim_agent_client.ground` is mocked, with its mock delegated to
`pim_agent`'s own real, unmocked
`ground()` (already exercised for real in `ai/pim_agent/tests/test_reference_knowledge.py`), so
labeling tests here still exercise real matching behavior over the mocked HTTP boundary. See
`tests/test_serialization_agent.py`'s own module docstring for the full test breakdown, including
the Issue #221 real-Bamboo-documentation regression test.
