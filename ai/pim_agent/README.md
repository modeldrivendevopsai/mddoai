# pim_agent

A static, hand-curated PIM (Platform-Independent Model) knowledge base, exposed over HTTP so
other `ai/` services can ground their own output against it: `orchestrator`'s
`serialization_agent` calls this today, labeling extracted documentation fragments against the
nine real PIM concepts (Pipeline, Job, Agent, Services, Trigger, Matrix, Parameters, Steps,
Expressions/VariableDeclaration).

This is **not** a RAG/vector-DB agent. `reference_knowledge.py`'s `ground(query, top_k)` does
plain keyword-overlap scoring against a fixed list of real facts derived from a reference
CI/CD-migration project's meta-model, transformation process, and reusability assessment, not
embeddings or a similarity search. `PIM_CONCEPTS` groups a subset of that knowledge (the
`"metamodel"`-category entries) under the nine concept names above.

Own container, own port (8030), reached only over HTTP by its callers, never imported as a
Python package across a service boundary. Promoted out of `orchestrator`'s own container for a
concrete, already-documented reason, not a speculative one: `MDDOAI_Agentic_Architecture.drawio`
(on the `origin/draw.io-diagrams` branch)'s Phase 1 plan already has a Graph-RAG-backed "RAG
Agent" splitting the real metamodel and feeding it to the PIM Agent directly, a materially
heavier job than today's keyword-matched `ground()`. Given MDDOAI's real metamodels are
Java/EMF (`main/`, `meta_models/` at the repo root), that likely means the same Java/EMF/Gradle
toolchain `ai/integration_agent/` already needs for the same reason, matching its established
shape, though that specific runtime is this codebase's own engineering inference, not something
the diagram itself states. Today's code is still plain Python, that migration is separate, real,
future work, not built here.

## API

### `POST /ground`

```json
// request
{ "query": "how are pipeline jobs ordered", "top_k": 5 }

// response (200)
[
  {
    "category": "metamodel",
    "title": "Job types: ScriptJob and PipelineCallJob",
    "content": "A Job is either a ScriptJob, holding an ordered list of Steps, or a PipelineCallJob, which calls another pipeline. Jobs are ordered via explicit previous/next references rather than an implicit list position.\n\nSource: the reference CI/CD migration project meta-model; structural reference for MDDOAI's pimMM.ecore, not directly reusable."
  }
]
```

`category` is one of `"metamodel"`, `"process"`, `"reusability"`, or `"limitation"`. Only
`"metamodel"`-category entries are grouped under `PIM_CONCEPTS` (see `GET /concepts` below);
the others are still real, groundable knowledge, just not concept-labeling targets. An empty
array means no entry matched any token in `query`, not an error.

### `GET /concepts`

```json
// response (200)
{
  "Pipeline": ["Pipeline and PipelineBlock"],
  "Job": ["Job types: ScriptJob and PipelineCallJob"],
  "Agent": ["Agent types"],
  "Services": ["Job services", "DockerContainer"],
  "Trigger": ["Trigger types"],
  "Matrix": ["Matrix"],
  "Parameters": ["Input and output parameters"],
  "Steps": ["Step types"],
  "Expressions/VariableDeclaration": ["Expression tree", "VariableDeclaration"]
}
```

The nine PIM concepts, each mapped to the real knowledge-entry title(s) that define it. A
caller doing its own reverse lookup (title → concept, e.g. `orchestrator`'s
`serialization_agent`) fetches this once and searches it locally, rather than this service
exposing a reverse-lookup endpoint of its own — the forward mapping is the real, canonical
data; a reverse index is a client-side convenience over it, not a second source of truth.

### `GET /health`

Used by the Dockerfile's `HEALTHCHECK`.

## Setup

```bash
pip install -r requirements.txt
```

No environment variables: `reference_knowledge.py`'s knowledge base is a fixed, in-code list,
not something pointed at an external URL or file.

## Run

```bash
uvicorn main:app --reload --port 8030
```

## Test

```bash
cd pim_agent
pytest
```

`tests/test_reference_knowledge.py` covers `ground()`'s scoring (including its stemming and
stopword handling) and `PIM_CONCEPTS`'s `concept_for_entry_title()` reverse lookup directly
against `reference_knowledge.py`, no HTTP involved, no mocking needed, it's pure, deterministic
Python.
