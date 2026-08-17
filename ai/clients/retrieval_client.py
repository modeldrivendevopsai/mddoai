"""HTTP client for retrieval: fetches real platform documentation for
integration_runner's docs stage. No fallback on failure, the fetch result is
the deliverable, errors propagate as real httpx.HTTPStatusErrors, matching
ai_layer_client.py and retrieval's own top-level call pattern."""
import os

import httpx

RETRIEVAL_URL = os.environ.get("RETRIEVAL_URL", "http://localhost:8010")
# 15 minutes, matching ai_layer_client.LLM_CHAT_TIMEOUT: a real crawl or page
# fetch can involve retrieval's own real render/fetch time on top of its
# chunk-cleanup LLM calls, and real free-tier LLM latency is unpredictable
# enough that a tighter default just trades a slow-but-real answer for a
# false failure.
RETRIEVAL_TIMEOUT = float(os.environ.get("RETRIEVAL_TIMEOUT", "900.0"))


def fetch_documentation(
    url: str,
    hint: str | None = None,
    exclude_urls: list[str] | None = None,
    max_pages: int | None = None,
    max_depth: int | None = None,
    force_refresh: bool | None = None,
) -> dict:
    """Calls retrieval's real POST /fetch. hint is retrieval's own retry lever
    (see its fetch_documentation docstring: "a retry lever for a caller
    (human or orchestrator)"), passed straight through, no translation
    needed. Returns the raw FetchResult dict."""
    payload = {"url": url}
    if hint:
        payload["hint"] = hint
    for key, value in (
        ("exclude_urls", exclude_urls),
        ("max_pages", max_pages),
        ("max_depth", max_depth),
        ("force_refresh", force_refresh),
    ):
        if value is not None:
            payload[key] = value
    response = httpx.post(f"{RETRIEVAL_URL}/fetch", json=payload, timeout=RETRIEVAL_TIMEOUT)
    response.raise_for_status()
    return response.json()


def fetch_page(url: str, force_refresh: bool = False) -> dict:
    """Calls retrieval's real POST /fetch/page: the targeted retry, pulling in
    one specific known page directly rather than re-running a full crawl.
    Returns the raw Page dict."""
    response = httpx.post(
        f"{RETRIEVAL_URL}/fetch/page", json={"url": url, "force_refresh": force_refresh}, timeout=RETRIEVAL_TIMEOUT
    )
    response.raise_for_status()
    return response.json()
