import asyncio
import hashlib
import ipaddress
import json
import logging
import os
import re
import socket
from collections.abc import Sequence
from pathlib import Path
from typing import TypedDict
from urllib.parse import urlparse

import httpx
import yaml
from crawl4ai import AsyncWebCrawler, CacheMode, CrawlerRunConfig
from crawl4ai.adaptive_crawler import AdaptiveConfig, AdaptiveCrawler, CrawlState, LinkPreviewConfig, StatisticalStrategy
from crawl4ai.content_filter_strategy import PruningContentFilter
from crawl4ai.markdown_generation_strategy import DefaultMarkdownGenerator
from crawl4ai.models import CrawlResult, Link
from crawl4ai.utils import normalize_url

# retrieval runs in its own container (needs a real browser via Playwright), so
# LLM calls go through ai-layer's shared /chat over HTTP, not an in-process import.
AI_LAYER_URL = os.environ.get("AI_LAYER_URL", "http://ai-layer:8000")

logger = logging.getLogger(__name__)


# --- Configuration -----------------------------------------------------------
# Tuning defaults live in config.yaml, checked in next to this module, so
# changing them doesn't require editing Python source. Every numeric/threshold
# key is still overridable per-deploy via an environment variable of the same
# name; the config.yaml value is only the fallback default. These are
# operational tuning knobs, not per-request params, unlike hint/max_depth/
# exclude_urls (see fetch_documentation) a caller has no principled basis to
# pick a different value per call. QUERY and the two *_PROMPT keys have no
# environment variable equivalent, edit config.yaml directly for those.

with open(Path(__file__).with_name("config.yaml"), encoding="utf-8") as _f:
    _config = yaml.safe_load(_f)

_MAX_LINKS = int(os.environ.get("MAX_LINKS", _config["MAX_LINKS"]))
_LINK_PREVIEW_CONCURRENCY = int(os.environ.get("LINK_PREVIEW_CONCURRENCY", _config["LINK_PREVIEW_CONCURRENCY"]))
_SHORTLIST_SIZE = int(os.environ.get("SHORTLIST_SIZE", _config["SHORTLIST_SIZE"]))

_WAIT_FOR_MIN_CHARS = int(os.environ.get("WAIT_FOR_MIN_CHARS", _config["WAIT_FOR_MIN_CHARS"]))
_DELAY_BEFORE_RETURN_HTML = float(os.environ.get("DELAY_BEFORE_RETURN_HTML", _config["DELAY_BEFORE_RETURN_HTML"]))

_CONTENT_FILTER_THRESHOLD = float(os.environ.get("CONTENT_FILTER_THRESHOLD", _config["CONTENT_FILTER_THRESHOLD"]))
_MIN_WORD_THRESHOLD = int(os.environ.get("MIN_WORD_THRESHOLD", _config["MIN_WORD_THRESHOLD"]))

_MIN_USABLE_CONTENT_CHARS = int(os.environ.get("MIN_USABLE_CONTENT_CHARS", _config["MIN_USABLE_CONTENT_CHARS"]))

_CLEAN_CHUNK_SIZE = int(os.environ.get("CLEAN_CHUNK_SIZE", _config["CLEAN_CHUNK_SIZE"]))
_CLEAN_CONCURRENCY = int(os.environ.get("CLEAN_CONCURRENCY", _config["CLEAN_CONCURRENCY"]))
_CLEAN_CACHE_MAX_SIZE = int(os.environ.get("CLEAN_CACHE_MAX_SIZE", _config["CLEAN_CACHE_MAX_SIZE"]))
_CLEAN_MAX_GROWTH_RATIO = float(os.environ.get("CLEAN_MAX_GROWTH_RATIO", _config["CLEAN_MAX_GROWTH_RATIO"]))
_CLEAN_MIN_OUTPUT_CHARS = int(os.environ.get("CLEAN_MIN_OUTPUT_CHARS", _config["CLEAN_MIN_OUTPUT_CHARS"]))
_CLEAN_MIN_INPUT_CHARS_FOR_GUARD = int(
    os.environ.get("CLEAN_MIN_INPUT_CHARS_FOR_GUARD", _config["CLEAN_MIN_INPUT_CHARS_FOR_GUARD"])
)

_DEDUPE_MIN_PARAGRAPH_CHARS = int(os.environ.get("DEDUPE_MIN_PARAGRAPH_CHARS", _config["DEDUPE_MIN_PARAGRAPH_CHARS"]))

_AI_LAYER_TIMEOUT = float(os.environ.get("AI_LAYER_TIMEOUT", _config["AI_LAYER_TIMEOUT"]))

_CONFIDENCE_THRESHOLD = float(os.environ.get("CONFIDENCE_THRESHOLD", _config["CONFIDENCE_THRESHOLD"]))
_SATURATION_THRESHOLD = float(os.environ.get("SATURATION_THRESHOLD", _config["SATURATION_THRESHOLD"]))

# AdaptiveCrawler's own per-round tuning: how many links it fetches per round,
# the score below which it stops entirely, and the weights StatisticalStrategy
# scores candidates with. Previously left at the library's defaults while
# confidence/saturation were externalized; these are just as behaviorally
# significant, so they get the same treatment.
_TOP_K_LINKS = int(os.environ.get("TOP_K_LINKS", _config["TOP_K_LINKS"]))
_MIN_GAIN_THRESHOLD = float(os.environ.get("MIN_GAIN_THRESHOLD", _config["MIN_GAIN_THRESHOLD"]))
_RELEVANCE_WEIGHT = float(os.environ.get("RELEVANCE_WEIGHT", _config["RELEVANCE_WEIGHT"]))
_NOVELTY_WEIGHT = float(os.environ.get("NOVELTY_WEIGHT", _config["NOVELTY_WEIGHT"]))
_AUTHORITY_WEIGHT = float(os.environ.get("AUTHORITY_WEIGHT", _config["AUTHORITY_WEIGHT"]))
_LINK_PREVIEW_TIMEOUT = float(os.environ.get("LINK_PREVIEW_TIMEOUT", _config["LINK_PREVIEW_TIMEOUT"]))

DEFAULT_MAX_PAGES = int(os.environ.get("DEFAULT_MAX_PAGES", _config["DEFAULT_MAX_PAGES"]))
MAX_PAGES_LIMIT = int(os.environ.get("MAX_PAGES_LIMIT", _config["MAX_PAGES_LIMIT"]))
DEFAULT_MAX_DEPTH = int(os.environ.get("DEFAULT_MAX_DEPTH", _config["DEFAULT_MAX_DEPTH"]))
MAX_DEPTH_LIMIT = int(os.environ.get("MAX_DEPTH_LIMIT", _config["MAX_DEPTH_LIMIT"]))


def _require_positive(name: str, value: int | float) -> None:
    if value <= 0:
        raise ValueError(f"{name} must be positive, got {value}")


def _require_unit_interval(name: str, value: float) -> None:
    if not 0.0 <= value <= 1.0:
        raise ValueError(f"{name} must be between 0 and 1, got {value}")


# Catches misconfiguration at startup with a clear message instead of a silent
# runtime deadlock (CLEAN_CONCURRENCY=0 -> asyncio.Semaphore(0) never acquires)
# or an opaque AssertionError from deep inside crawl4ai's own config validation.
_require_positive("MAX_LINKS", _MAX_LINKS)
_require_positive("LINK_PREVIEW_CONCURRENCY", _LINK_PREVIEW_CONCURRENCY)
_require_positive("SHORTLIST_SIZE", _SHORTLIST_SIZE)
_require_positive("CLEAN_CHUNK_SIZE", _CLEAN_CHUNK_SIZE)
_require_positive("CLEAN_CONCURRENCY", _CLEAN_CONCURRENCY)
_require_positive("CLEAN_CACHE_MAX_SIZE", _CLEAN_CACHE_MAX_SIZE)
_require_positive("TOP_K_LINKS", _TOP_K_LINKS)
_require_unit_interval("CONTENT_FILTER_THRESHOLD", _CONTENT_FILTER_THRESHOLD)
_require_unit_interval("CONFIDENCE_THRESHOLD", _CONFIDENCE_THRESHOLD)
_require_unit_interval("SATURATION_THRESHOLD", _SATURATION_THRESHOLD)
_require_unit_interval("MIN_GAIN_THRESHOLD", _MIN_GAIN_THRESHOLD)

_WAIT_FOR = f"js:() => document.body && document.body.innerText.length > {_WAIT_FOR_MIN_CHARS}"

_CONTENT_FILTER_KWARGS = dict(
    threshold=_CONTENT_FILTER_THRESHOLD,
    threshold_type="fixed",
    min_word_threshold=_MIN_WORD_THRESHOLD,
    preserve_tags=["pre", "code", "table"],
)

# What AdaptiveCrawler's statistical strategy scores link/page relevance against:
# terms to overweight, not a chat prompt. Defined in config.yaml.
_QUERY = _config["QUERY"]


def _base_run_config_kwargs(cache_mode: CacheMode) -> dict:
    """Shared CrawlerRunConfig kwargs for every real page fetch (multi-hop and
    single-page), so the content filter/wait/robots policy can't drift between
    the two call sites."""
    return dict(
        markdown_generator=DefaultMarkdownGenerator(content_filter=PruningContentFilter(**_CONTENT_FILTER_KWARGS)),
        wait_for=_WAIT_FOR,
        delay_before_return_html=_DELAY_BEFORE_RETURN_HTML,
        cache_mode=cache_mode,
        check_robots_txt=True,
    )


# --- Types ---------------------------------------------------------------


class PendingLink(TypedDict):
    href: str
    text: str
    title: str


class Page(TypedDict):
    url: str
    success: bool
    status_code: int | None
    markdown: str
    links: list[str]


class FetchMeta(TypedDict):
    confidence: float
    pages_crawled: int
    depth_reached: int | None
    pending_links: list[PendingLink]


class FetchResult(TypedDict):
    seed_url: str
    pages: list[Page]
    meta: FetchMeta


def _validate_fetchable_url(url: str) -> None:
    """Raises ValueError unless url is a public http(s) address, blocking SSRF
    against internal services, localhost, and cloud metadata endpoints."""
    parsed = urlparse(url)
    if parsed.scheme not in ("http", "https"):
        raise ValueError(f"refusing to fetch non-http(s) URL: {url!r}")
    hostname = parsed.hostname
    if not hostname:
        raise ValueError(f"refusing to fetch URL with no hostname: {url!r}")
    try:
        addrinfo = socket.getaddrinfo(hostname, None)
    except socket.gaierror as e:
        raise ValueError(f"could not resolve hostname {hostname!r}: {e}") from e
    for _, _, _, _, sockaddr in addrinfo:
        ip = ipaddress.ip_address(sockaddr[0])
        if ip.is_private or ip.is_loopback or ip.is_link_local or ip.is_reserved or ip.is_multicast or ip.is_unspecified:
            raise ValueError(f"refusing to fetch {url!r}: {hostname} resolves to non-public address {ip}")


_SCOPE_PREFIX_MAX_SEGMENTS = 3


def _scope_prefixes(seed_url: str) -> list[str]:
    """Progressively broader domain+path prefixes for seed_url, narrowest first,
    so a caller can find the narrowest prefix that actually has candidate links."""
    parsed = urlparse(seed_url)
    segments = [s for s in parsed.path.split("/") if s]
    if segments and "." in segments[-1]:
        segments = segments[:-1]
    base = f"{parsed.scheme}://{parsed.netloc}"
    prefixes = [f"{base}/" + "/".join(segments[:n]) for n in range(min(_SCOPE_PREFIX_MAX_SEGMENTS, len(segments)), 0, -1)]
    prefixes.append(base)
    seen: set[str] = set()
    result = []
    for p in prefixes:
        if p not in seen:
            seen.add(p)
            result.append(p)
    return result


# System prompt for _llm_rank_links. Defined in config.yaml, its final two
# sentences are load-bearing for _llm_rank_links's response parsing, see the
# comment above LINK_RANKING_PROMPT in that file before editing it.
_LINK_RANKING_PROMPT = _config["LINK_RANKING_PROMPT"]


async def _llm_rank_links(links: list[Link], hint: str | None = None) -> list[str]:
    """Asks ai-layer's /chat which candidate links look like real syntax/schema
    pages. hint, if given, is folded into the prompt to steer judgment."""
    if not links:
        return []
    candidates_text = "\n".join(f"{link.href} | text: {link.text!r} | title: {link.title!r}" for link in links)
    user_content = f"Candidate links:\n{candidates_text}"
    if hint:
        user_content = f"Extra guidance from the caller, weigh this heavily: {hint}\n\n{user_content}"
    messages = [
        {"role": "system", "content": _LINK_RANKING_PROMPT},
        {"role": "user", "content": user_content},
    ]
    try:
        async with httpx.AsyncClient(timeout=_AI_LAYER_TIMEOUT) as client:
            response = await client.post(f"{AI_LAYER_URL}/chat", json={"messages": messages})
            response.raise_for_status()
            content = response.json()["content"]
    except Exception:
        logger.warning("_llm_rank_links: ranking call failed, falling back to statistical order", exc_info=True)
        return [link.href for link in links]
    match = re.search(r"\[.*\]", content, re.DOTALL)
    if not match:
        logger.warning("_llm_rank_links: no JSON array in LLM response: %r", content[:300])
        return [link.href for link in links]
    try:
        selected = json.loads(match.group(0))
    except json.JSONDecodeError:
        logger.warning("_llm_rank_links: unparseable JSON from LLM: %r", match.group(0)[:300])
        return [link.href for link in links]
    candidate_hrefs = {link.href for link in links}
    accepted = [href for href in selected if href in candidate_hrefs]
    if len(accepted) < len(selected):
        logger.warning(
            "_llm_rank_links: LLM returned %d href(s) not in the candidate list, dropped",
            len(selected) - len(accepted),
        )
    return accepted


class _LLMRankedStrategy(StatisticalStrategy):
    """Statistical term-frequency scoring narrows candidates to a shortlist,
    then ai-layer's /chat judges which of those actually look like real
    syntax/schema pages. Candidates the LLM doesn't mention are kept, not
    dropped, appended at the tail in their original statistical order."""

    def __init__(self, hint: str | None = None):
        super().__init__()
        self._hint = hint

    async def rank_links(self, state: CrawlState, config: AdaptiveConfig) -> list[tuple[Link, float]]:
        statistically_ranked = await super().rank_links(state, config)
        shortlist = statistically_ranked[:_SHORTLIST_SIZE]
        if not shortlist:
            return shortlist
        by_href = {link.href: (link, score) for link, score in shortlist}
        max_score = max(score for _, score in shortlist)
        selected_hrefs = await _llm_rank_links([link for link, _ in shortlist], hint=self._hint)
        # LLM-selected links get the shortlist's own max statistical score, not
        # their original one: digest()'s min_gain_threshold stopping check reads
        # the top-ranked link's score, so a page the LLM promoted despite weak
        # term overlap must not look low-value to that gate, or the crawl can
        # stop early exactly when the LLM override matters most.
        ranked = [(by_href[href][0], max_score) for href in selected_hrefs if href in by_href]
        leftover = [pair for href, pair in by_href.items() if href not in selected_hrefs]
        return ranked + leftover


class _ConfiguredAdaptiveCrawler(AdaptiveCrawler):
    """AdaptiveCrawler with a custom CrawlerRunConfig injected into its internal
    fetch (content filtering, wait_for, cache mode, there is no public
    constructor param for this), plus scope and exclude_urls filtering on the
    candidate links it discovers."""

    def __init__(
        self,
        *args,
        cache_mode: CacheMode = CacheMode.ENABLED,
        scope_prefixes: Sequence[str] = (),
        exclude_urls: frozenset[str] = frozenset(),
        **kwargs,
    ):
        super().__init__(*args, **kwargs)
        self._cache_mode = cache_mode
        self._scope_prefix_candidates = list(scope_prefixes)
        self._scope_prefix: str | None = None
        self._exclude_urls = exclude_urls

    async def _crawl_with_preview(self, url: str, query: str):
        config = CrawlerRunConfig(
            link_preview_config=LinkPreviewConfig(
                include_internal=True,
                include_external=False,
                query=query,
                concurrency=_LINK_PREVIEW_CONCURRENCY,
                timeout=self.config.link_preview_timeout,
                max_links=_MAX_LINKS,
                verbose=False,
            ),
            score_links=True,
            **_base_run_config_kwargs(self._cache_mode),
        )
        try:
            _validate_fetchable_url(url)
            result = await self.crawler.arun(url=url, config=config)
            if hasattr(result, "_results") and result._results:
                result = result._results[0]
            if hasattr(result, "links") and result.links:
                internal = [link for link in result.links["internal"] if link.get("head_data")]
                if self._scope_prefix is None:
                    # a page's own self-link (breadcrumb, canonical tag) doesn't
                    # count as real coverage when picking a scope prefix
                    others = [link for link in internal if link.get("href") != url]
                    for candidate in self._scope_prefix_candidates or [""]:
                        if any(link.get("href", "").startswith(candidate) for link in others):
                            self._scope_prefix = candidate
                            break
                    else:
                        self._scope_prefix = self._scope_prefix_candidates[-1] if self._scope_prefix_candidates else ""
                    logger.info("adaptive crawl: resolved scope_prefix=%r for seed %s", self._scope_prefix, url)
                # applied after scope resolution, so an excluded URL can't make a
                # prefix look empty and wrongly widen the whole crawl's scope
                result.links["internal"] = [
                    link for link in internal
                    if link.get("href", "").startswith(self._scope_prefix) and link.get("href") not in self._exclude_urls
                ]
            return result
        except Exception:
            logger.warning("adaptive crawl: fetch failed for %s", url, exc_info=True)
            return None


def _result_to_page(result: CrawlResult) -> Page:
    """Shapes a Crawl4AI CrawlResult into this service's page dict, marking a
    page unsuccessful if its usable content falls under the quality floor."""
    markdown = result.markdown
    text = (markdown.fit_markdown or markdown.raw_markdown) if markdown else ""
    links = result.links or {}
    success = result.success and len(text.strip()) >= _MIN_USABLE_CONTENT_CHARS
    if result.success and not success:
        logger.warning(
            "_result_to_page: %s reported success with only %d char(s) of content, marking unsuccessful",
            result.url, len(text.strip()),
        )
    return {
        "url": result.url,
        "success": success,
        "status_code": result.status_code,
        "markdown": text,
        "links": [link["href"] for link in links.get("internal", []) if link.get("href")],
    }


# System prompt for _clean_chunk. Defined in config.yaml, its byte-for-byte
# fidelity requirement is load-bearing, see the comment above
# CLEAN_CONTENT_PROMPT in that file before editing it.
_CLEAN_CONTENT_PROMPT = _config["CLEAN_CONTENT_PROMPT"]

# Keyed by a hash of the input markdown, not the URL: the same content is the
# same cleanup result regardless of which page it came from.
_clean_cache: dict[str, str] = {}


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
    if cleaned.startswith("```"):
        cleaned = re.sub(r"^```[a-zA-Z]*\n|\n```$", "", cleaned)
    if len(cleaned) < _CLEAN_MIN_OUTPUT_CHARS and len(chunk) > _CLEAN_MIN_INPUT_CHARS_FOR_GUARD:
        logger.warning("clean_page_content: chunk cleanup returned near-empty result, keeping chunk as-is")
        return chunk
    if len(cleaned) > len(chunk) * _CLEAN_MAX_GROWTH_RATIO:
        logger.warning(
            "clean_page_content: chunk cleanup returned %d chars for a %d char input, "
            "longer than cleanup should ever produce, keeping chunk as-is",
            len(cleaned), len(chunk),
        )
        return chunk
    return cleaned


async def clean_page_content(markdown: str, force_refresh: bool = False) -> str:
    """LLM cleanup pass over already-filtered markdown, chunked and cached by
    content hash. force_refresh bypasses the cache for a truly fresh result."""
    if not markdown.strip():
        return markdown
    cache_key = hashlib.sha256(markdown.encode("utf-8")).hexdigest()
    if not force_refresh and cache_key in _clean_cache:
        logger.info("clean_page_content: cache hit (%d chars), skipping LLM cleanup call(s)", len(markdown))
        return _clean_cache[cache_key]
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

    semaphore = asyncio.Semaphore(_CLEAN_CONCURRENCY)

    async def _bounded_clean(client: httpx.AsyncClient, chunk: str) -> str:
        async with semaphore:
            return await _clean_chunk(client, chunk)

    async with httpx.AsyncClient(timeout=_AI_LAYER_TIMEOUT) as client:
        cleaned_chunks = await asyncio.gather(*[_bounded_clean(client, chunk) for chunk in chunks])
    result = "\n\n".join(cleaned_chunks)
    logger.info(
        "clean_page_content: cleaned %d chunk(s) (concurrency=%d), %d -> %d chars",
        len(chunks), _CLEAN_CONCURRENCY, len(markdown), len(result),
    )
    if len(_clean_cache) >= _CLEAN_CACHE_MAX_SIZE:
        _clean_cache.pop(next(iter(_clean_cache)))  # dicts preserve insertion order: evicts oldest
    _clean_cache[cache_key] = result
    return result


def dedupe_pages(pages: list[Page]) -> list[Page]:
    """Drops paragraphs (over DEDUPE_MIN_PARAGRAPH_CHARS) that already appeared
    on an earlier page, keeping the first occurrence. Short paragraphs are left
    alone since repeats there (headings, single lines) are usually legitimate."""
    seen: set[str] = set()
    deduped = []
    dropped = 0
    for page in pages:
        kept_paragraphs = []
        for para in page["markdown"].split("\n\n"):
            key = para.strip()
            if len(key) > _DEDUPE_MIN_PARAGRAPH_CHARS:
                if key in seen:
                    dropped += 1
                    continue
                seen.add(key)
            kept_paragraphs.append(para)
        deduped.append({**page, "markdown": "\n\n".join(kept_paragraphs)})
    if dropped:
        logger.info("dedupe_pages: dropped %d duplicate paragraph(s) across %d page(s)", dropped, len(pages))
    return deduped


async def fetch_documentation(
    seed_url: str,
    max_pages: int = DEFAULT_MAX_PAGES,
    force_refresh: bool = False,
    hint: str | None = None,
    max_depth: int = DEFAULT_MAX_DEPTH,
    exclude_urls: list[str] | None = None,
) -> FetchResult:
    """Crawls seed_url via AdaptiveCrawler for CI/CD pipeline syntax reference
    content, following links across pages (not just the seed). Stops on
    max_pages, max_depth, no in-scope candidates left, or AdaptiveCrawler's own
    confidence/saturation/min_gain_threshold gates. hint steers link ranking
    toward something specific; exclude_urls rules out known-bad pages; both are
    retry levers for a caller (human or orchestrator) that inspected a prior
    response and knows what to correct. Returns pages plus a meta block
    (confidence, pending_links, ...): non-empty pending_links means real
    candidates are still available, but not necessarily because a budget ran
    out, min_gain_threshold can also stop the crawl with candidates left,
    where raising max_pages does nothing and hint/exclude_urls are the actual
    levers; compare pages_crawled/depth_reached against max_pages/max_depth to
    tell which case it is. Empty pending_links means it ran out of in-scope
    candidates entirely."""
    cache_mode = CacheMode.BYPASS if force_refresh else CacheMode.ENABLED
    effective_query = f"{_QUERY} {hint}" if hint else _QUERY
    async with AsyncWebCrawler() as crawler:
        adaptive = _ConfiguredAdaptiveCrawler(
            crawler=crawler,
            config=AdaptiveConfig(
                strategy="statistical",
                max_pages=max_pages,
                max_depth=max_depth,
                confidence_threshold=_CONFIDENCE_THRESHOLD,
                saturation_threshold=_SATURATION_THRESHOLD,
                top_k_links=_TOP_K_LINKS,
                min_gain_threshold=_MIN_GAIN_THRESHOLD,
                relevance_weight=_RELEVANCE_WEIGHT,
                novelty_weight=_NOVELTY_WEIGHT,
                authority_weight=_AUTHORITY_WEIGHT,
                link_preview_timeout=_LINK_PREVIEW_TIMEOUT,
            ),
            strategy=_LLMRankedStrategy(hint=hint),
            cache_mode=cache_mode,
            scope_prefixes=_scope_prefixes(seed_url),
            exclude_urls=frozenset(exclude_urls or ()),
        )
        logger.info(
            "fetch_documentation: starting adaptive crawl seed=%s max_pages=%d max_depth=%d "
            "force_refresh=%s hint=%r exclude_urls=%s scope_prefix_candidates=%s",
            seed_url, max_pages, max_depth, force_refresh, hint,
            sorted(adaptive._exclude_urls), adaptive._scope_prefix_candidates,
        )
        state = await adaptive.digest(seed_url, query=effective_query)

    pages = [_result_to_page(r) for r in state.knowledge_base if r is not None]
    # Confirmed live against a real crawl: crawl4ai's own state.pending_links can
    # still list a URL after it's been crawled into knowledge_base, its internal
    # bookkeeping only prevents re-adding an already-crawled URL, it doesn't prune
    # pending_links once a queued link actually gets fetched. Without this filter,
    # meta.pending_links would tell a caller pages are still unexplored when full
    # data for them already exists in the pages list returned in this same response.
    #
    # Every discovered link href is normalized by crawl4ai (host lowercased,
    # tracking params dropped, query sorted) before it ever reaches pending_links,
    # but a page's own result.url is not, it's whatever string was passed to
    # arun(). For every non-seed page that's the same already-normalized href, so
    # it's a no-op; for the seed page specifically it's the caller's raw seed_url,
    # so without normalizing here too, a self-link back to a seed URL given in a
    # non-canonical form (tracking params, unsorted query, mixed-case host) could
    # still slip past this filter.
    crawled_urls = {normalize_url(p["url"], p["url"]) for p in pages}
    seen_hrefs: set[str] = set()
    pending_links: list[PendingLink] = []
    for link in state.pending_links:
        if link.href in seen_hrefs or link.href in crawled_urls:
            continue
        seen_hrefs.add(link.href)
        pending_links.append({"href": link.href, "text": link.text, "title": link.title})
    meta: FetchMeta = {
        "confidence": state.metrics.get("confidence", 0.0),
        # len(pages), not state.metrics["pages_crawled"] (== len(state.crawled_urls)
        # internally): crawl4ai's own crawled_urls bookkeeping can undercount on a
        # partial-batch fetch failure (a zip() of results against requested links
        # misaligns if a middle one fails), pages is what this response actually
        # returns, always the correct count for it.
        "pages_crawled": len(pages),
        "depth_reached": state.metrics.get("depth_reached"),
        "pending_links": pending_links,
    }
    logger.info(
        "fetch_documentation: adaptive crawl done pages=%d meta_confidence=%.2f meta_pending_links=%d urls=%s",
        len(pages), meta["confidence"], len(pending_links), [p["url"] for p in pages],
    )

    pages = dedupe_pages(pages)
    for page in pages:
        if page["success"]:
            page["markdown"] = await clean_page_content(page["markdown"], force_refresh=force_refresh)
    pages = dedupe_pages(pages)

    for page in pages:
        if page["success"] and len(page["markdown"].strip()) < _MIN_USABLE_CONTENT_CHARS:
            logger.info(
                "fetch_documentation: %s left with only %d char(s) after dedup (likely a near-total "
                "duplicate of an already-captured page), marking unsuccessful",
                page["url"], len(page["markdown"].strip()),
            )
            page["success"] = False

    return {"seed_url": seed_url, "pages": pages, "meta": meta}


async def fetch_single_page(url: str, force_refresh: bool = False) -> Page:
    """Fetches and cleans exactly one URL, no crawling or ranking, same content
    pipeline as every page inside fetch_documentation. For a caller that already
    knows the one specific URL missing from a prior result."""
    _validate_fetchable_url(url)  # unguarded: an SSRF rejection here is a real error, not a graceful failure
    cache_mode = CacheMode.BYPASS if force_refresh else CacheMode.ENABLED
    config = CrawlerRunConfig(**_base_run_config_kwargs(cache_mode))
    logger.info("fetch_single_page: fetching url=%s force_refresh=%s", url, force_refresh)
    try:
        async with AsyncWebCrawler() as crawler:
            result = await crawler.arun(url=url, config=config)
    except Exception:
        logger.warning("fetch_single_page: fetch failed for %s", url, exc_info=True)
        return {"url": url, "success": False, "status_code": None, "markdown": "", "links": []}
    page = _result_to_page(result)
    if page["success"]:
        page["markdown"] = await clean_page_content(page["markdown"], force_refresh=force_refresh)
    logger.info("fetch_single_page: done url=%s success=%s len=%d", url, page["success"], len(page["markdown"]))
    return page
