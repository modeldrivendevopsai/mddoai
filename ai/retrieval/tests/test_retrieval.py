"""
Unit tests for retrieval.py. No real network calls, no real browser,
no real LLM, both AsyncWebCrawler and the ai-layer HTTP call are mocked.

Tests verify:
  1. fetch_page() shapes a CrawlResult into {url, success, status_code, markdown, links}
     and only keeps internal links.
  2. select_relevant_links() returns [] without making an HTTP call when there are no
     candidate links.
  3. select_relevant_links() parses a JSON array out of the LLM's response content.
  4. select_relevant_links() drops any URL the LLM returned that wasn't actually a
     candidate, an invented-but-plausible URL isn't something we can fetch.
  5. select_relevant_links() returns [] on a malformed/non-JSON response instead of
     raising.
  6. fetch_documentation() fetches only the seed page when max_pages=1, skipping the
     link-selection call entirely.
  7. fetch_documentation() fetches the seed plus whichever links were selected.
  8. clean_page_content() sends chunked content to ai-layer and reassembles the
     cleaned chunks; a chunk whose cleanup call fails is kept as-is rather than lost.
  9. clean_page_content() strips a code-fence wrapper if the model adds one despite
     being told not to, and discards a near-empty result as more likely a mistake
     than real content, keeping the original chunk instead.
  10. dedupe_pages() drops a long paragraph that appears identically on a later page,
      keeping the first occurrence, but leaves short (<=40 char) repeats alone since
      those are more likely legitimate (e.g. a repeated heading) than site chrome.
"""
from unittest.mock import AsyncMock, MagicMock, patch

import pytest

import retrieval


def make_crawl_result(success=True, status_code=200, markdown="content", internal_links=None):
    result = MagicMock()
    result.success = success
    result.status_code = status_code
    # fetch_page reads fit_markdown first, falling back to raw_markdown; both are
    # set to the same value here since these tests aren't exercising that fallback.
    result.markdown = MagicMock(fit_markdown=markdown, raw_markdown=markdown) if markdown is not None else None
    result.links = {"internal": [{"href": h} for h in (internal_links or [])], "external": [{"href": "https://other-site.example/"}]}
    return result


def make_mock_crawler(result):
    """A mock crawler instance whose .arun() returns the given result, for
    passing directly into fetch_page(url, crawler)."""
    crawler = AsyncMock()
    crawler.arun.return_value = result
    return crawler


def patch_crawler(result):
    """Patches the AsyncWebCrawler class itself, for tests that go through
    fetch_documentation()'s `async with AsyncWebCrawler() as crawler` block."""
    crawler_instance = AsyncMock()
    crawler_instance.arun.return_value = result
    crawler_cm = MagicMock()
    crawler_cm.__aenter__.return_value = crawler_instance
    crawler_cm.__aexit__.return_value = False
    return patch.object(retrieval, "AsyncWebCrawler", return_value=crawler_cm)


@pytest.mark.asyncio
async def test_fetch_page_shapes_result_and_keeps_only_internal_links():
    result = make_crawl_result(internal_links=["https://docs.example.com/a", "https://docs.example.com/b"])
    crawler = make_mock_crawler(result)
    page = await retrieval.fetch_page("https://docs.example.com/", crawler)
    assert page == {
        "url": "https://docs.example.com/",
        "success": True,
        "status_code": 200,
        "markdown": "content",
        "links": ["https://docs.example.com/a", "https://docs.example.com/b"],
    }


@pytest.mark.asyncio
async def test_fetch_page_falls_back_to_raw_markdown_when_fit_markdown_is_empty():
    result = MagicMock()
    result.success = True
    result.status_code = 200
    result.markdown = MagicMock(fit_markdown="", raw_markdown="unfiltered content")
    result.links = {"internal": [], "external": []}
    crawler = make_mock_crawler(result)
    page = await retrieval.fetch_page("https://docs.example.com/", crawler)
    assert page["markdown"] == "unfiltered content"


@pytest.mark.asyncio
async def test_select_relevant_links_no_candidates_skips_http_call():
    with patch.object(retrieval.httpx, "AsyncClient") as mock_client_cls:
        selected = await retrieval.select_relevant_links("seed content", [], max_links=5)
    assert selected == []
    mock_client_cls.assert_not_called()


def patch_chat_response(content: str):
    response = MagicMock()
    response.raise_for_status.return_value = None
    response.json.return_value = {"content": content}
    client = AsyncMock()
    client.post.return_value = response
    client_cm = MagicMock()
    client_cm.__aenter__.return_value = client
    client_cm.__aexit__.return_value = False
    return patch.object(retrieval.httpx, "AsyncClient", return_value=client_cm)


@pytest.mark.asyncio
async def test_select_relevant_links_parses_json_array_from_response():
    candidates = ["https://docs.example.com/yaml", "https://docs.example.com/about"]
    with patch_chat_response('["https://docs.example.com/yaml"]'):
        selected = await retrieval.select_relevant_links("seed", candidates, max_links=5)
    assert selected == ["https://docs.example.com/yaml"]


@pytest.mark.asyncio
async def test_select_relevant_links_drops_invented_urls():
    candidates = ["https://docs.example.com/yaml"]
    # LLM returns a URL that was never in the candidate list
    with patch_chat_response('["https://docs.example.com/yaml", "https://docs.example.com/made-up"]'):
        selected = await retrieval.select_relevant_links("seed", candidates, max_links=5)
    assert selected == ["https://docs.example.com/yaml"]


@pytest.mark.asyncio
async def test_select_relevant_links_malformed_response_returns_empty():
    candidates = ["https://docs.example.com/yaml"]
    with patch_chat_response("sorry, I can't help with that"):
        selected = await retrieval.select_relevant_links("seed", candidates, max_links=5)
    assert selected == []


def patch_clean_passthrough():
    """clean_page_content is a real LLM call over the network, tests that aren't
    exercising cleanup behavior specifically should mock it as an identity
    function so they stay fast and fully isolated (this was previously
    unmocked and silently made real, failing network calls: ~10s per test run
    instead of ~1s, falling back to the original content on connection error)."""
    return patch.object(retrieval, "clean_page_content", AsyncMock(side_effect=lambda md: md))


@pytest.mark.asyncio
async def test_fetch_documentation_seed_only_when_max_pages_is_one():
    seed_result = make_crawl_result(internal_links=["https://docs.example.com/yaml"])
    with patch_crawler(seed_result), \
         patch.object(retrieval, "select_relevant_links") as mock_select, \
         patch_clean_passthrough():
        result = await retrieval.fetch_documentation("https://docs.example.com/", max_pages=1)
    mock_select.assert_not_called()
    assert len(result["pages"]) == 1
    assert result["seed_url"] == "https://docs.example.com/"


@pytest.mark.asyncio
async def test_fetch_documentation_fetches_seed_plus_selected_links():
    async def fake_fetch_page(url, crawler):
        if url == "https://docs.example.com/":
            return {
                "url": url,
                "success": True,
                "status_code": 200,
                "markdown": "seed content",
                "links": ["https://docs.example.com/yaml", "https://docs.example.com/about"],
            }
        return {"url": url, "success": True, "status_code": 200, "markdown": "yaml reference content", "links": []}

    # fetch_page itself is mocked out, but fetch_documentation still opens a
    # real `async with AsyncWebCrawler()`, that needs mocking too so it doesn't
    # try to launch an actual browser.
    with patch_crawler(make_crawl_result()), \
         patch.object(retrieval, "fetch_page", side_effect=fake_fetch_page), \
         patch.object(retrieval, "select_relevant_links", return_value=["https://docs.example.com/yaml"]), \
         patch_clean_passthrough():
        result = await retrieval.fetch_documentation("https://docs.example.com/", max_pages=3)

    assert [p["url"] for p in result["pages"]] == [
        "https://docs.example.com/",
        "https://docs.example.com/yaml",
    ]


@pytest.mark.asyncio
async def test_clean_page_content_chunks_and_reassembles():
    # two paragraphs well under the chunk size land in the same chunk, one call
    long_markdown = "first paragraph of real content" + "\n\n" + "second paragraph of real content"
    with patch_chat_response("cleaned content") as _:
        cleaned = await retrieval.clean_page_content(long_markdown)
    assert cleaned == "cleaned content"


@pytest.mark.asyncio
async def test_clean_page_content_keeps_chunk_when_cleanup_call_fails():
    client = AsyncMock()
    client.post.side_effect = ConnectionError("ai-layer unreachable")
    client_cm = MagicMock()
    client_cm.__aenter__.return_value = client
    client_cm.__aexit__.return_value = False
    with patch.object(retrieval.httpx, "AsyncClient", return_value=client_cm):
        cleaned = await retrieval.clean_page_content("original content that should survive")
    assert cleaned == "original content that should survive"


@pytest.mark.asyncio
async def test_clean_page_content_strips_stray_code_fence():
    with patch_chat_response("```markdown\ncleaned content\n```"):
        cleaned = await retrieval.clean_page_content("some real content here")
    assert cleaned == "cleaned content"


@pytest.mark.asyncio
async def test_clean_page_content_discards_near_empty_result():
    # must be over the _clean_chunk near-empty guard's 100-char floor to trigger it
    original = "a real paragraph of genuine documentation content that is long enough to trip the near-empty guard, well over 100 characters"
    with patch_chat_response("ok"):
        cleaned = await retrieval.clean_page_content(original)
    assert cleaned == original


def test_dedupe_pages_drops_repeated_long_paragraph_keeps_first():
    boilerplate = "this cookie notice paragraph is long enough to count as real content by length alone"
    pages = [
        {"url": "a", "success": True, "markdown": f"unique content on page a\n\n{boilerplate}"},
        {"url": "b", "success": True, "markdown": f"{boilerplate}\n\nunique content on page b"},
    ]
    result = retrieval.dedupe_pages(pages)
    assert result[0]["markdown"] == f"unique content on page a\n\n{boilerplate}"
    assert result[1]["markdown"] == "unique content on page b"


def test_dedupe_pages_leaves_short_repeats_alone():
    pages = [
        {"url": "a", "success": True, "markdown": "## Overview"},
        {"url": "b", "success": True, "markdown": "## Overview"},
    ]
    result = retrieval.dedupe_pages(pages)
    assert result[0]["markdown"] == "## Overview"
    assert result[1]["markdown"] == "## Overview"
