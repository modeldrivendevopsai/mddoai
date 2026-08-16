"""HTTP client for pim_agent: real grounding lookups against the reference
PIM knowledge base. No fallback on failure, matching ai_layer_client.py and
retrieval_client.py's pattern."""
import os

import httpx

PIM_AGENT_URL = os.environ.get("PIM_AGENT_URL", "http://localhost:8030")


def ground(query: str, top_k: int = 5) -> list[dict]:
    """POST pim_agent's real /ground: returns the most relevant reference
    grounding examples for query, as a list of {category, title, content}
    dicts (mirrors GroundingExample's real fields)."""
    response = httpx.post(f"{PIM_AGENT_URL}/ground", json={"query": query, "top_k": top_k}, timeout=10.0)
    response.raise_for_status()
    return response.json()


def concepts() -> dict[str, list[str]]:
    """GET pim_agent's real /concepts: the 9 PIM_CONCEPTS -> grouped
    metamodel-entry-title mapping. Cheap to call once and cache client-side
    (it's static data), but not cached here, callers decide their own
    caching if it becomes a real cost."""
    response = httpx.get(f"{PIM_AGENT_URL}/concepts", timeout=10.0)
    response.raise_for_status()
    return response.json()
