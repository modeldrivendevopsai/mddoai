import json
import logging
import os
import re

import httpx
from crawl4ai import AsyncWebCrawler, CrawlerRunConfig
from crawl4ai.content_filter_strategy import PruningContentFilter
from crawl4ai.markdown_generation_strategy import DefaultMarkdownGenerator

# retrieval runs in its own container (it needs a real Chromium browser via
# Playwright, ai-layer and orchestrator don't), so unlike orchestrator this
# calls ai-layer's shared chat() over HTTP rather than importing it in-process.
AI_LAYER_URL = os.environ.get("AI_LAYER_URL", "http://ai-layer:8000")

# Plain HTTP fetches silently return an empty/near-empty shell on JS-rendered
# SPA doc sites (confirmed against TeamCity's docs: 72 chars vs. 7600+ once the
# page is actually given time to hydrate). wait_until="networkidle" plus a
# settle delay fixes it; a short page_timeout is not enough on its own.
#
# raw_markdown keeps the entire page including navigation chrome, confirmed on
# a real Azure DevOps schema page: 800+ lines of cookie banners and mega-menus
# before any real content. fit_markdown (via PruningContentFilter) strips that,
# but its defaults also strip real content in two different ways depending on
# the site: code-block-heavy pages (GitLab, GitHub Actions) lose syntax to the
# density/tag-weight score unless pre/code/table are explicitly preserved;
# prose-style pages with short definitions (Azure DevOps) lose content to the
# word-count floor unless it's lowered. Both confirmed by testing against real
# pages from all three sites, this config is not guessed.
_PAGE_CONFIG = CrawlerRunConfig(
    wait_until="networkidle",
    delay_before_return_html=2.0,
    page_timeout=60000,
    markdown_generator=DefaultMarkdownGenerator(
        content_filter=PruningContentFilter(
            threshold=0.48,
            threshold_type="fixed",
            min_word_threshold=5,
            preserve_tags=["pre", "code", "table"],
        )
    ),
)

_CLEAN_CONTENT_PROMPT = (
    "You are cleaning already-mostly-filtered documentation markdown. Remove any remaining "
    "site chrome: cookie/consent notices, \"sign in\"/\"skip to content\" prompts, footer "
    "legal text, and repeated navigation menus. Do NOT summarize, paraphrase, or rewrite "
    "anything else, every sentence, code block, YAML example, and table that isn't chrome "
    "must be returned byte-for-byte as given. If a page has no chrome left to remove, return "
    "it completely unchanged. Respond with ONLY the cleaned markdown, no preamble, no "
    "explanation, no code fence wrapping the whole response."
)
# Pages the size of a full syntax reference can be very large (GitLab's ran ~170K chars),
# well past what's safe to hand an LLM in one call, so this cleans it in paragraph-boundary
# chunks rather than truncating or sending the whole thing at once.
_CLEAN_CHUNK_SIZE = 12000


async def _clean_chunk(client: httpx.AsyncClient, chunk: str) -> str:
    messages = [
        {"role": "system", "content": _CLEAN_CONTENT_PROMPT},
        {"role": "user", "content": chunk},
    ]
    try:
        response = await client.post(f"{AI_LAYER_URL}/chat", json={"messages": messages})
        response.raise_for_status()
        cleaned = response.json()["content"].strip()
    except Exception:
        logger.warning("clean_page_content: chunk cleanup call failed, keeping chunk as-is", exc_info=True)
        return chunk
    # a model that ignores "no code fence wrapping" and wraps the whole answer in one
    # is still usable, just strip the fence rather than losing the chunk to a formatting slip
    if cleaned.startswith("```"):
        cleaned = re.sub(r"^```[a-zA-Z]*\n|\n```$", "", cleaned)
    # an empty/near-empty result is more likely a refusal or a mistake than a page that
    # was 100% chrome, real content is not something a filter pass should discard entirely
    if len(cleaned) < 20 and len(chunk) > 100:
        logger.warning("clean_page_content: chunk cleanup returned near-empty result, keeping chunk as-is")
        return chunk
    return cleaned


async def clean_page_content(markdown: str) -> str:
    """Second-pass cleanup over already fit_markdown-filtered content, via ai-layer's
    shared /chat. PruningContentFilter is structural (text/link density, tag weight) with
    no semantic understanding, confirmed to let through exact-duplicate cross-page
    boilerplate (e.g. the same cookie-consent paragraph appearing on every page of a site)
    since well-written boilerplate prose scores just like real content structurally. An LLM
    pass catches what a density heuristic fundamentally can't."""
    if not markdown.strip():
        return markdown
    paragraphs = markdown.split("\n\n")
    chunks: list[str] = []
    current = ""
    for para in paragraphs:
        if current and len(current) + len(para) + 2 > _CLEAN_CHUNK_SIZE:
            chunks.append(current)
            current = para
        else:
            current = f"{current}\n\n{para}" if current else para
    if current:
        chunks.append(current)

    async with httpx.AsyncClient(timeout=60.0) as client:
        cleaned_chunks = [await _clean_chunk(client, chunk) for chunk in chunks]
    return "\n\n".join(cleaned_chunks)


_LINK_SELECTION_PROMPT = (
    "You are selecting which links on a CI/CD platform's documentation site are worth "
    "fetching next. The goal is NOT general documentation, it's the platform's actual "
    "configuration syntax reference: the keyword-by-keyword schema for its pipeline "
    "definition file (e.g. GitLab CI's yaml reference at docs.gitlab.com/ci/yaml/, which "
    "lists every field like `stages`, `rules`, `needs`, `artifacts`, with its type and "
    "meaning). Concepts to prioritize: pipeline/job/stage definitions, triggers, "
    "runners/agents, artifacts, environment/variable config, and any page that reads like "
    "a schema or keyword reference rather than a tutorial or getting-started guide. Some "
    "platforms keep this on one page (like GitLab), others scatter it across many "
    "separate reference pages, follow those too if the seed page doesn't have it all. "
    "Given the seed page's content and a list of candidate links found on it, return a "
    "JSON array of the URLs (a subset of the candidates) most likely to be syntax/schema "
    "reference content, not navigation, marketing, legal, release notes, or tutorial "
    "pages. Return at most {max_links} URLs. Respond with ONLY a JSON array of strings, "
    "no other text."
)


logger = logging.getLogger(__name__)


async def fetch_page(url: str, crawler: AsyncWebCrawler) -> dict:
    """Fetch a single documentation page and return clean markdown plus discovered links.

    Takes an already-open AsyncWebCrawler rather than opening its own, launching
    a fresh headless Chromium process per page is real, avoidable cost when
    fetching a seed page plus several follow-ups (Crawl4AI's own docs recommend
    reusing one crawler across calls)."""
    result = await crawler.arun(url, config=_PAGE_CONFIG)
    links = result.links or {}
    markdown = result.markdown
    # fit_markdown can come back empty on a page the filter doesn't handle well;
    # raw_markdown (nav chrome and all) beats silently returning nothing.
    text = (markdown.fit_markdown or markdown.raw_markdown) if markdown else ""
    return {
        "url": url,
        "success": result.success,
        "status_code": result.status_code,
        "markdown": text,
        "links": [link["href"] for link in links.get("internal", []) if link.get("href")],
    }


async def select_relevant_links(seed_markdown: str, candidate_links: list[str], max_links: int = 5) -> list[str]:
    """Ask ai-layer's shared /chat endpoint which discovered links are worth fetching next."""
    if not candidate_links:
        return []
    messages = [
        {"role": "system", "content": _LINK_SELECTION_PROMPT.format(max_links=max_links)},
        {
            "role": "user",
            "content": (
                f"Seed page content (truncated):\n{seed_markdown[:3000]}\n\n"
                "Candidate links:\n" + "\n".join(candidate_links[:200])
            ),
        },
    ]
    async with httpx.AsyncClient(timeout=60.0) as client:
        response = await client.post(f"{AI_LAYER_URL}/chat", json={"messages": messages})
        response.raise_for_status()
        content = response.json()["content"]
    match = re.search(r"\[.*\]", content, re.DOTALL)
    if not match:
        logger.warning("select_relevant_links: no JSON array in LLM response: %r", content[:300])
        return []
    try:
        selected = json.loads(match.group(0))
    except json.JSONDecodeError:
        logger.warning("select_relevant_links: unparseable JSON from LLM: %r", match.group(0)[:300])
        return []
    # only accept URLs that were actually in the candidate list, an LLM
    # inventing a plausible-looking URL is not a page we can fetch
    accepted = [url for url in selected if url in candidate_links][:max_links]
    if len(accepted) < len(selected):
        logger.warning(
            "select_relevant_links: LLM returned %d URL(s) not in the candidate list, dropped",
            len(selected) - len(accepted),
        )
    return accepted


def dedupe_pages(pages: list[dict]) -> list[dict]:
    """Drop paragraphs that appear identically across 2+ pages (keeping the first
    occurrence). A cheap, deterministic safety net for what clean_page_content's LLM
    pass might miss or apply inconsistently: site-wide chrome (a cookie-consent
    notice, a footer) that's word-for-word identical across every page of the same
    site. Short paragraphs (headings, single lines) are allowed to repeat legitimately,
    only long ones (over 40 chars) are deduplicated."""
    seen: set[str] = set()
    deduped = []
    for page in pages:
        kept_paragraphs = []
        for para in page["markdown"].split("\n\n"):
            key = para.strip()
            if len(key) > 40:
                if key in seen:
                    continue
                seen.add(key)
            kept_paragraphs.append(para)
        deduped.append({**page, "markdown": "\n\n".join(kept_paragraphs)})
    return deduped


async def fetch_documentation(seed_url: str, max_pages: int = 5) -> dict:
    """
    Fetch documentation starting from seed_url. If the seed page yields more
    links than max_pages allows, the shared ai-layer LLM picks which of them
    are worth following, rather than crawling everything or picking by
    keyword/URL-pattern heuristics alone.

    Content cleanup is layered: PruningContentFilter (structural, per-page, inside
    fetch_page) -> clean_page_content (semantic, per-page, via ai-layer) -> dedupe_pages
    (deterministic, across all fetched pages). Each layer catches what the one before
    it can't: the heuristic filter is cheap but has no semantic understanding, the LLM
    pass is semantic but costs a call per page and isn't perfectly consistent, the
    dedup pass is free and exact but only catches literal repeats.
    """
    async with AsyncWebCrawler() as crawler:
        seed = await fetch_page(seed_url, crawler)
        pages = [seed]
        if seed["success"] and max_pages > 1:
            selected = await select_relevant_links(seed["markdown"], seed["links"], max_links=max_pages - 1)
            for link in selected:
                pages.append(await fetch_page(link, crawler))

    for page in pages:
        if page["success"]:
            page["markdown"] = await clean_page_content(page["markdown"])
    pages = dedupe_pages(pages)

    return {"seed_url": seed_url, "pages": pages}
