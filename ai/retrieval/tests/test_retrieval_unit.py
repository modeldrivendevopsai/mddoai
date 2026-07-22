"""
Unit tests for retrieval.py. No real network calls, no real browser, no real LLM;
AsyncWebCrawler, _ConfiguredAdaptiveCrawler, and the ai-layer HTTP call are all mocked.

Tests verify:
  1. _result_to_page() shapes a CrawlResult into {url, success, status_code, markdown,
     links}, keeps only internal links, and falls back to raw_markdown when
     fit_markdown is empty.
  2. _ConfiguredAdaptiveCrawler._crawl_with_preview() builds a CrawlerRunConfig with our
     tuned content filter, wait_for, cache_mode, and check_robots_txt (AdaptiveCrawler's
     own internal config has none of these, confirmed by reading crawl4ai 0.9.2's
     source, this override exists specifically to inject them), preserves the
     link_preview_config/score_links AdaptiveCrawler's ranking depends on, filters
     internal links down to ones with head_data, and returns None instead of raising
     on a fetch error.
  3. fetch_documentation() converts AdaptiveCrawler's knowledge_base into pages, passes
     max_pages into AdaptiveConfig and force_refresh into a cache_mode
     (CacheMode.ENABLED by default, CacheMode.BYPASS when force_refresh=True).
  4. fetch_documentation() calls dedupe_pages() twice, once before clean_page_content
     (so a duplicate paragraph never reaches the LLM cleanup call at all) and once
     after (a safety net for whatever cleanup leaves inconsistent).
  5. clean_page_content() sends chunked content to ai-layer concurrently (bounded) and
     reassembles the cleaned chunks in original order, not completion order; a chunk
     whose cleanup call fails is kept as-is rather than lost.
  6. clean_page_content() strips a code-fence wrapper if the model adds one despite
     being told not to, and discards a near-empty result as more likely a mistake
     than real content, keeping the original chunk instead.
  7. dedupe_pages() drops a long paragraph that appears identically on a later page,
     keeping the first occurrence, but leaves short (<=40 char) repeats alone since
     those are more likely legitimate (e.g. a repeated heading) than site chrome.
"""
import asyncio
from unittest.mock import AsyncMock, MagicMock, patch

import pytest
from crawl4ai import CacheMode
from crawl4ai.adaptive_crawler import AdaptiveConfig

import retrieval


@pytest.fixture(autouse=True)
def _clear_clean_cache():
    """clean_page_content's cache is a module-level dict, shared across every test
    in the process, clear it before each test so cache hits/misses are never
    order-dependent on which test happened to run first."""
    retrieval._clean_cache.clear()
    yield


@pytest.fixture(autouse=True)
def _mock_dns_to_a_public_address():
    """_validate_fetchable_url() (called by both _crawl_with_preview and
    fetch_single_page) does a real socket.getaddrinfo() lookup. Without this,
    every existing test using a fake https://docs.example.com/... URL would
    depend on real, non-deterministic DNS (docs.example.com specifically has no
    guaranteed A record) instead of the mocked crawler they actually intend to
    exercise. Defaults to a real public IP so validation passes by default;
    tests specifically covering _validate_fetchable_url's rejection behavior
    wrap this with their own patch_dns(...) to override it for that scope."""
    with patch.object(retrieval.socket, "getaddrinfo", return_value=[(2, 1, 6, "", ("93.184.216.34", 0))]):
        yield


_LONG_ENOUGH_CONTENT = "real page content, well over the _MIN_USABLE_CONTENT_CHARS floor. " * 5


def make_crawl_result(url="https://docs.example.com/", success=True, status_code=200, markdown=_LONG_ENOUGH_CONTENT, internal_links=None):
    result = MagicMock()
    result.url = url
    result.success = success
    result.status_code = status_code
    result.markdown = MagicMock(fit_markdown=markdown, raw_markdown=markdown) if markdown is not None else None
    result.links = {"internal": [{"href": h} for h in (internal_links or [])], "external": [{"href": "https://other-site.example/"}]}
    # a bare MagicMock auto-creates ANY attribute accessed, including _results,
    # as a truthy Mock, which would wrongly trigger _crawl_with_preview's
    # "unwrap a multi-result container" branch; pin it to a real, falsy value.
    result._results = None
    return result


def make_fake_state(results, confidence=0.8, depth_reached=1, pending_links=None):
    state = MagicMock()
    state.knowledge_base = results
    state.metrics = {"confidence": confidence, "depth_reached": depth_reached, "pages_crawled": len(results)}
    # a bare MagicMock's .pending_links would be an auto-mock, not a real list,
    # and len() on that raises; fetch_documentation()'s meta block needs a real one
    state.pending_links = pending_links if pending_links is not None else []
    return state


def patch_adaptive_crawler(state):
    """Patches _ConfiguredAdaptiveCrawler itself (not its internals), for tests that go
    through fetch_documentation()'s `adaptive = _ConfiguredAdaptiveCrawler(...)`. Returns
    the mock class so tests can assert on constructor call args (max_pages, cache_mode)."""
    instance = MagicMock()
    instance.digest = AsyncMock(return_value=state)
    crawler_cls = MagicMock(return_value=instance)
    return patch.object(retrieval, "_ConfiguredAdaptiveCrawler", crawler_cls), crawler_cls


def patch_crawler():
    """Patches AsyncWebCrawler itself, for tests that go through fetch_documentation()'s
    `async with AsyncWebCrawler() as crawler` block; the crawler instance is unused by
    the mocked _ConfiguredAdaptiveCrawler, this just needs to not launch a real browser."""
    crawler_cm = MagicMock()
    crawler_cm.__aenter__ = AsyncMock(return_value=MagicMock())
    crawler_cm.__aexit__ = AsyncMock(return_value=False)
    return patch.object(retrieval, "AsyncWebCrawler", return_value=crawler_cm)


def patch_clean_passthrough():
    """clean_page_content is a real LLM call over the network, tests that aren't
    exercising cleanup behavior specifically should mock it as an identity function."""
    return patch.object(retrieval, "clean_page_content", AsyncMock(side_effect=lambda md, force_refresh=False: md))


# --- _result_to_page ----------------------------------------------------------


def test_result_to_page_shapes_result_and_keeps_only_internal_links():
    result = make_crawl_result(internal_links=["https://docs.example.com/a", "https://docs.example.com/b"])
    page = retrieval._result_to_page(result)
    assert page == {
        "url": "https://docs.example.com/",
        "success": True,
        "status_code": 200,
        "markdown": _LONG_ENOUGH_CONTENT,
        "links": ["https://docs.example.com/a", "https://docs.example.com/b"],
    }


def test_result_to_page_marks_unsuccessful_when_content_is_suspiciously_short():
    # confirmed live: a query-string variant of an already-fetched Azure DevOps
    # page reported success=True and a real status_code with only 3-37 chars of
    # content, neither field alone catches that, this does
    result = make_crawl_result(markdown="short")
    page = retrieval._result_to_page(result)
    assert page["success"] is False
    assert page["markdown"] == "short"  # content itself is kept, just flagged


def test_result_to_page_falls_back_to_raw_markdown_when_fit_markdown_is_empty():
    result = MagicMock(url="https://docs.example.com/", success=True, status_code=200)
    result.markdown = MagicMock(fit_markdown="", raw_markdown="unfiltered content")
    result.links = {"internal": [], "external": []}
    page = retrieval._result_to_page(result)
    assert page["markdown"] == "unfiltered content"


# --- _llm_rank_links / _LLMRankedStrategy ----------------------------------------


def make_link(href, text="", title=""):
    from crawl4ai.models import Link
    return Link(href=href, text=text, title=title)


@pytest.mark.asyncio
async def test_llm_rank_links_returns_llm_selected_subset_in_order():
    links = [make_link("https://docs.example.com/boards"), make_link("https://docs.example.com/yaml-schema/pipeline")]
    with patch_chat_response('["https://docs.example.com/yaml-schema/pipeline"]'):
        selected = await retrieval._llm_rank_links(links)
    assert selected == ["https://docs.example.com/yaml-schema/pipeline"]


@pytest.mark.asyncio
async def test_llm_rank_links_drops_invented_hrefs():
    links = [make_link("https://docs.example.com/yaml-schema/pipeline")]
    with patch_chat_response('["https://docs.example.com/yaml-schema/pipeline", "https://docs.example.com/made-up"]'):
        selected = await retrieval._llm_rank_links(links)
    assert selected == ["https://docs.example.com/yaml-schema/pipeline"]


@pytest.mark.asyncio
async def test_llm_rank_links_falls_back_to_original_order_on_call_failure():
    links = [make_link("https://docs.example.com/a"), make_link("https://docs.example.com/b")]
    client = AsyncMock()
    client.post.side_effect = ConnectionError("ai-layer unreachable")
    client_cm = MagicMock()
    client_cm.__aenter__.return_value = client
    client_cm.__aexit__.return_value = False
    with patch.object(retrieval.httpx, "AsyncClient", return_value=client_cm):
        selected = await retrieval._llm_rank_links(links)
    assert selected == ["https://docs.example.com/a", "https://docs.example.com/b"]


@pytest.mark.asyncio
async def test_llm_rank_links_folds_hint_into_the_prompt():
    links = [make_link("https://docs.example.com/a")]
    seen_content = {}

    async def capture_post(url, json):
        seen_content["user_message"] = json["messages"][1]["content"]
        response = MagicMock()
        response.raise_for_status.return_value = None
        response.json.return_value = {"content": '["https://docs.example.com/a"]'}
        return response

    client = AsyncMock()
    client.post.side_effect = capture_post
    client_cm = MagicMock()
    client_cm.__aenter__.return_value = client
    client_cm.__aexit__.return_value = False
    with patch.object(retrieval.httpx, "AsyncClient", return_value=client_cm):
        await retrieval._llm_rank_links(links, hint="prioritize pages about environment variables")

    assert "prioritize pages about environment variables" in seen_content["user_message"]


@pytest.mark.asyncio
async def test_llm_ranked_strategy_reorders_shortlist_and_keeps_unlisted_at_tail():
    from crawl4ai.adaptive_crawler import CrawlState

    link_a = make_link("https://docs.example.com/boards", text="Boards")
    link_b = make_link("https://docs.example.com/yaml-schema/pipeline", text="Pipeline YAML schema")
    state = CrawlState(pending_links=[link_a, link_b], query="pipeline yaml schema")
    strategy = retrieval._LLMRankedStrategy()

    # LLM promotes link_b over link_a, the opposite of whatever statistical
    # scoring alone would have produced from raw term overlap
    with patch.object(retrieval, "_llm_rank_links", AsyncMock(return_value=[link_b.href])):
        ranked = await strategy.rank_links(state, AdaptiveConfig(strategy="statistical"))

    assert [link.href for link, _ in ranked][0] == link_b.href
    assert {link.href for link, _ in ranked} == {link_a.href, link_b.href}  # link_a kept, not dropped


@pytest.mark.asyncio
async def test_llm_ranked_strategy_passes_its_hint_through_to_llm_rank_links():
    from crawl4ai.adaptive_crawler import CrawlState

    link_a = make_link("https://docs.example.com/a")
    state = CrawlState(pending_links=[link_a], query="q")
    strategy = retrieval._LLMRankedStrategy(hint="find the secrets page")

    with patch.object(retrieval, "_llm_rank_links", AsyncMock(return_value=[link_a.href])) as mock_rank:
        await strategy.rank_links(state, AdaptiveConfig(strategy="statistical"))

    assert mock_rank.call_args.kwargs["hint"] == "find the secrets page"


@pytest.mark.asyncio
async def test_llm_ranked_strategy_gives_llm_selected_links_the_shortlists_max_score():
    from crawl4ai.adaptive_crawler import CrawlState

    # link_a scores high statistically (title packed with query terms) but is
    # actually the wrong page; link_b scores low (generic text) but is the real
    # target, mirroring the confirmed Azure DevOps case where statistical
    # scoring ranked /boards/ above the real yaml-schema page. digest()'s
    # min_gain_threshold stopping check reads ranked_links[0][1], so the
    # promoted link's returned score must not stay at its own low value.
    link_a = make_link("https://docs.example.com/boards", text="pipeline configuration syntax reference stages jobs")
    link_b = make_link("https://docs.example.com/yaml-schema/pipeline", text="click here")
    state = CrawlState(pending_links=[link_a, link_b], query=retrieval._QUERY)
    config = AdaptiveConfig(strategy="statistical")

    baseline = await retrieval.StatisticalStrategy().rank_links(state, config)
    baseline_scores = {link.href: score for link, score in baseline}
    assert baseline_scores[link_a.href] > baseline_scores[link_b.href]  # sanity check the setup

    strategy = retrieval._LLMRankedStrategy()
    with patch.object(retrieval, "_llm_rank_links", AsyncMock(return_value=[link_b.href])):
        ranked = await strategy.rank_links(state, config)

    top_link, top_score = ranked[0]
    assert top_link.href == link_b.href
    assert top_score == baseline_scores[link_a.href]  # shortlist's max, not link_b's own low score


# --- _validate_fetchable_url -----------------------------------------------------


def patch_dns(ip: str):
    """Deterministic DNS resolution for _validate_fetchable_url tests, real
    socket.getaddrinfo() would be network-dependent and slow."""
    return patch.object(
        retrieval.socket, "getaddrinfo", return_value=[(2, 1, 6, "", (ip, 0))]
    )


def test_validate_fetchable_url_rejects_non_http_scheme():
    with pytest.raises(ValueError, match="non-http"):
        retrieval._validate_fetchable_url("ftp://docs.example.com/")


def test_validate_fetchable_url_rejects_loopback():
    # confirmed via independent review: no restriction meant a caller could point
    # /fetch at an internal service on the same Docker network or localhost
    with patch_dns("127.0.0.1"):
        with pytest.raises(ValueError, match="non-public"):
            retrieval._validate_fetchable_url("http://localhost/")


def test_validate_fetchable_url_rejects_private_range():
    # the Docker-internal-service case flagged on review: ai-layer, retrieval
    # itself, etc. all live on a private (172.x / 10.x / 192.168.x) range
    with patch_dns("172.19.0.5"):
        with pytest.raises(ValueError, match="non-public"):
            retrieval._validate_fetchable_url("http://ai-layer/")


def test_validate_fetchable_url_rejects_link_local_cloud_metadata():
    # 169.254.169.254 is the AWS/GCP/Azure instance-metadata address, a classic
    # SSRF target for credential theft
    with patch_dns("169.254.169.254"):
        with pytest.raises(ValueError, match="non-public"):
            retrieval._validate_fetchable_url("http://169.254.169.254/latest/meta-data/")


def test_validate_fetchable_url_accepts_public_address():
    with patch_dns("93.184.216.34"):
        retrieval._validate_fetchable_url("https://docs.example.com/")  # does not raise


def test_validate_fetchable_url_rejects_unresolvable_hostname():
    with patch.object(retrieval.socket, "getaddrinfo", side_effect=retrieval.socket.gaierror("nope")):
        with pytest.raises(ValueError, match="could not resolve"):
            retrieval._validate_fetchable_url("https://this-does-not-resolve.invalid/")


# --- _scope_prefixes ------------------------------------------------------------


def test_scope_prefixes_narrowest_first_covers_all_platform_url_shapes_tested_live():
    # Verified live this session, not guessed: unfiltered statistical scoring on
    # the Azure case picked https://learn.microsoft.com/en-us/agents/ (an
    # unrelated Microsoft AI-agents product page) as a top candidate purely
    # because the query contains "agents" (meant for CI/CD runner agents).
    # learn.microsoft.com hosts many unrelated products under one domain, so
    # "same domain" alone isn't a safe scope there.
    assert retrieval._scope_prefixes(
        "https://learn.microsoft.com/en-us/azure/devops/pipelines/yaml-schema/?view=azure-pipelines"
    )[0] == "https://learn.microsoft.com/en-us/azure/devops"
    assert retrieval._scope_prefixes(
        "https://www.jetbrains.com/help/teamcity/kotlin-dsl.html"
    )[0] == "https://www.jetbrains.com/help/teamcity"
    assert retrieval._scope_prefixes("https://docs.gitlab.com/ci/yaml/")[0] == "https://docs.gitlab.com/ci/yaml"
    assert retrieval._scope_prefixes(
        "https://confluence.atlassian.com/spaces/BAMBOO/pages/938844479/Bamboo+YAML+Specs"
    )[0] == "https://confluence.atlassian.com/spaces/BAMBOO/pages"


def test_scope_prefixes_widens_when_seed_path_is_short():
    # the exact regression this exists to prevent: Travis CI's seed URL has
    # only 2 path segments, so "up to 3 segments" used the entire seed path,
    # matching zero other pages on the site (confirmed live: 0 pending_links
    # after the seed). Broader fallbacks must be present in the list.
    prefixes = retrieval._scope_prefixes("https://docs.travis-ci.com/user/build-config-yaml")
    assert prefixes == [
        "https://docs.travis-ci.com/user/build-config-yaml",
        "https://docs.travis-ci.com/user",
        "https://docs.travis-ci.com",
    ]


# --- _ConfiguredAdaptiveCrawler._crawl_with_preview ---------------------------


@pytest.mark.asyncio
async def test_crawl_with_preview_builds_config_with_our_tuned_settings_and_filters_head_data():
    raw_result = make_crawl_result(internal_links=[])
    raw_result.links["internal"] = [
        {"href": "https://docs.example.com/ci/yaml/has-head", "head_data": {"title": "t"}},
        {"href": "https://docs.example.com/ci/yaml/no-head"},
    ]
    mock_crawler = AsyncMock()
    mock_crawler.arun.return_value = raw_result
    adaptive = retrieval._ConfiguredAdaptiveCrawler(
        crawler=mock_crawler, config=AdaptiveConfig(strategy="statistical"), cache_mode=CacheMode.ENABLED,
        scope_prefixes=["https://docs.example.com/ci"],
    )

    result = await adaptive._crawl_with_preview("https://docs.example.com/ci/yaml/", "pipeline syntax")

    call_kwargs = mock_crawler.arun.call_args.kwargs
    config = call_kwargs["config"]
    assert config.score_links is True
    assert config.link_preview_config.query == "pipeline syntax"
    assert config.cache_mode == CacheMode.ENABLED
    assert config.check_robots_txt is True
    assert config.wait_for == retrieval._WAIT_FOR
    assert config.markdown_generator is not None
    # only the link with head_data AND the right scope prefix survives, matching
    # both AdaptiveCrawler's own ranking requirement and our scope filter
    assert [link["href"] for link in result.links["internal"]] == ["https://docs.example.com/ci/yaml/has-head"]


@pytest.mark.asyncio
async def test_crawl_with_preview_drops_links_outside_scope_prefix_even_with_head_data():
    # the exact regression this exists to prevent: a same-domain but unrelated
    # link, with head_data, must still be dropped if it's outside scope
    raw_result = make_crawl_result(internal_links=[])
    raw_result.links["internal"] = [
        {"href": "https://docs.example.com/ci/yaml/in-scope", "head_data": {"title": "t"}},
        {"href": "https://docs.example.com/unrelated-product/out-of-scope", "head_data": {"title": "t"}},
    ]
    mock_crawler = AsyncMock()
    mock_crawler.arun.return_value = raw_result
    adaptive = retrieval._ConfiguredAdaptiveCrawler(
        crawler=mock_crawler, config=AdaptiveConfig(strategy="statistical"), cache_mode=CacheMode.ENABLED,
        scope_prefixes=["https://docs.example.com/ci"],
    )

    result = await adaptive._crawl_with_preview("https://docs.example.com/ci/yaml/", "pipeline syntax")

    assert [link["href"] for link in result.links["internal"]] == ["https://docs.example.com/ci/yaml/in-scope"]


@pytest.mark.asyncio
async def test_crawl_with_preview_drops_excluded_urls_even_if_in_scope_with_head_data():
    # the retry lever: a caller who already knows a specific page was wrong
    # can rule it out explicitly, even though it's otherwise a perfectly
    # valid, in-scope, head_data-bearing candidate
    raw_result = make_crawl_result(internal_links=[])
    raw_result.links["internal"] = [
        {"href": "https://docs.example.com/ci/yaml/good", "head_data": {"title": "t"}},
        {"href": "https://docs.example.com/ci/yaml/known-bad", "head_data": {"title": "t"}},
    ]
    mock_crawler = AsyncMock()
    mock_crawler.arun.return_value = raw_result
    adaptive = retrieval._ConfiguredAdaptiveCrawler(
        crawler=mock_crawler, config=AdaptiveConfig(strategy="statistical"), cache_mode=CacheMode.ENABLED,
        scope_prefixes=["https://docs.example.com/ci"],
        exclude_urls=frozenset({"https://docs.example.com/ci/yaml/known-bad"}),
    )

    result = await adaptive._crawl_with_preview("https://docs.example.com/ci/yaml/", "pipeline syntax")

    assert [link["href"] for link in result.links["internal"]] == ["https://docs.example.com/ci/yaml/good"]


@pytest.mark.asyncio
async def test_crawl_with_preview_exclude_urls_does_not_corrupt_scope_resolution():
    # the exact regression this exists to prevent, flagged on independent review:
    # if the ONLY in-scope candidate happened to be the excluded one, and
    # exclusion was applied before scope resolution ran, resolution would see
    # zero candidates at the narrow (correct) prefix and wrongly widen scope
    # for the entire rest of the crawl, even though the true reason was just
    # that the one candidate got excluded, not that the prefix was empty
    raw_result = make_crawl_result(internal_links=[])
    raw_result.links["internal"] = [
        # only candidate at the narrow prefix, and it's excluded
        {"href": "https://docs.example.com/ci/yaml/known-bad", "head_data": {"title": "t"}},
        # only reachable if scope wrongly widened to the broader prefix
        {"href": "https://docs.example.com/unrelated-product/page", "head_data": {"title": "t"}},
    ]
    mock_crawler = AsyncMock()
    mock_crawler.arun.return_value = raw_result
    adaptive = retrieval._ConfiguredAdaptiveCrawler(
        crawler=mock_crawler, config=AdaptiveConfig(strategy="statistical"), cache_mode=CacheMode.ENABLED,
        scope_prefixes=["https://docs.example.com/ci", "https://docs.example.com"],
        exclude_urls=frozenset({"https://docs.example.com/ci/yaml/known-bad"}),
    )

    result = await adaptive._crawl_with_preview("https://docs.example.com/ci/yaml/", "pipeline syntax")

    # scope must resolve to the narrow prefix (the excluded link still counts as
    # real signal that this prefix has content), and the final list is empty,
    # not widened to include the unrelated-product page
    assert adaptive._scope_prefix == "https://docs.example.com/ci"
    assert result.links["internal"] == []


@pytest.mark.asyncio
async def test_crawl_with_preview_widens_scope_when_narrowest_prefix_has_no_candidates():
    raw_result = make_crawl_result(internal_links=[])
    raw_result.links["internal"] = [
        {"href": "https://docs.example.com/user/sibling-page", "head_data": {"title": "t"}},
    ]
    mock_crawler = AsyncMock()
    mock_crawler.arun.return_value = raw_result
    adaptive = retrieval._ConfiguredAdaptiveCrawler(
        crawler=mock_crawler, config=AdaptiveConfig(strategy="statistical"), cache_mode=CacheMode.ENABLED,
        # narrowest prefix matches nothing (the sibling page isn't under it),
        # broader ones do
        scope_prefixes=["https://docs.example.com/user/build-config-yaml", "https://docs.example.com/user"],
    )

    result = await adaptive._crawl_with_preview("https://docs.example.com/user/build-config-yaml", "pipeline syntax")

    assert adaptive._scope_prefix == "https://docs.example.com/user"
    assert [link["href"] for link in result.links["internal"]] == ["https://docs.example.com/user/sibling-page"]


@pytest.mark.asyncio
async def test_crawl_with_preview_ignores_self_link_when_resolving_scope():
    # the exact regression this exists to prevent, confirmed live on Travis CI:
    # a page linking to itself (breadcrumb, canonical tag) trivially "matches"
    # the narrowest prefix without representing any real additional coverage,
    # which stopped the widening logic before it ever tried the genuinely
    # useful broader prefix
    seed_url = "https://docs.example.com/user/build-config-yaml"
    raw_result = make_crawl_result(internal_links=[])
    raw_result.links["internal"] = [
        {"href": seed_url, "head_data": {"title": "self"}},
        {"href": "https://docs.example.com/user/sibling-page", "head_data": {"title": "t"}},
    ]
    mock_crawler = AsyncMock()
    mock_crawler.arun.return_value = raw_result
    adaptive = retrieval._ConfiguredAdaptiveCrawler(
        crawler=mock_crawler, config=AdaptiveConfig(strategy="statistical"), cache_mode=CacheMode.ENABLED,
        scope_prefixes=[seed_url, "https://docs.example.com/user"],
    )

    result = await adaptive._crawl_with_preview(seed_url, "pipeline syntax")

    # must widen past the narrowest prefix (which only "matched" via the
    # self-link) to the one with a real sibling page
    assert adaptive._scope_prefix == "https://docs.example.com/user"
    assert [link["href"] for link in result.links["internal"]] == [seed_url, "https://docs.example.com/user/sibling-page"]


@pytest.mark.asyncio
async def test_crawl_with_preview_locks_resolved_scope_prefix_across_calls():
    raw_result_1 = make_crawl_result(internal_links=[])
    raw_result_1.links["internal"] = [{"href": "https://docs.example.com/user/a", "head_data": {"title": "t"}}]
    raw_result_2 = make_crawl_result(internal_links=[])
    raw_result_2.links["internal"] = [{"href": "https://docs.example.com/other/b", "head_data": {"title": "t"}}]
    mock_crawler = AsyncMock()
    mock_crawler.arun.side_effect = [raw_result_1, raw_result_2]
    adaptive = retrieval._ConfiguredAdaptiveCrawler(
        crawler=mock_crawler, config=AdaptiveConfig(strategy="statistical"), cache_mode=CacheMode.ENABLED,
        scope_prefixes=["https://docs.example.com/user/build-config-yaml", "https://docs.example.com/user",
                         "https://docs.example.com"],
    )

    await adaptive._crawl_with_preview("https://docs.example.com/user/build-config-yaml", "q")
    assert adaptive._scope_prefix == "https://docs.example.com/user"

    # second call's own candidates (/other/b) would resolve differently on
    # their own, but scope is already locked from the first call and must not
    # re-resolve or drift
    result_2 = await adaptive._crawl_with_preview("https://docs.example.com/user/a", "q")
    assert adaptive._scope_prefix == "https://docs.example.com/user"
    assert result_2.links["internal"] == []  # /other/b doesn't match the locked-in prefix


@pytest.mark.asyncio
async def test_crawl_with_preview_returns_none_instead_of_raising_on_fetch_error():
    mock_crawler = AsyncMock()
    mock_crawler.arun.side_effect = ConnectionError("site unreachable")
    adaptive = retrieval._ConfiguredAdaptiveCrawler(
        crawler=mock_crawler, config=AdaptiveConfig(strategy="statistical"), cache_mode=CacheMode.ENABLED,
        scope_prefixes=["https://docs.example.com/ci"],
    )
    result = await adaptive._crawl_with_preview("https://docs.example.com/", "pipeline syntax")
    assert result is None


# --- fetch_documentation -------------------------------------------------------


@pytest.mark.asyncio
async def test_fetch_documentation_converts_knowledge_base_to_pages():
    state = make_fake_state([
        make_crawl_result(url="https://docs.example.com/", markdown="seed content"),
        make_crawl_result(url="https://docs.example.com/yaml", markdown="yaml reference content"),
    ])
    patcher, _ = patch_adaptive_crawler(state)
    with patch_crawler(), patcher, patch_clean_passthrough():
        result = await retrieval.fetch_documentation("https://docs.example.com/", max_pages=3)

    assert result["seed_url"] == "https://docs.example.com/"
    assert [p["url"] for p in result["pages"]] == ["https://docs.example.com/", "https://docs.example.com/yaml"]


@pytest.mark.asyncio
async def test_fetch_documentation_passes_max_pages_into_adaptive_config():
    state = make_fake_state([make_crawl_result()])
    patcher, crawler_cls = patch_adaptive_crawler(state)
    with patch_crawler(), patcher, patch_clean_passthrough():
        await retrieval.fetch_documentation("https://docs.example.com/", max_pages=7)

    config = crawler_cls.call_args.kwargs["config"]
    assert config.max_pages == 7
    assert config.strategy == "statistical"  # no separate LLM/embedding config needed


@pytest.mark.asyncio
async def test_fetch_documentation_default_uses_cache_enabled():
    state = make_fake_state([make_crawl_result()])
    patcher, crawler_cls = patch_adaptive_crawler(state)
    with patch_crawler(), patcher, patch_clean_passthrough():
        await retrieval.fetch_documentation("https://docs.example.com/")

    assert crawler_cls.call_args.kwargs["cache_mode"] == CacheMode.ENABLED


@pytest.mark.asyncio
async def test_fetch_documentation_force_refresh_uses_cache_bypass():
    state = make_fake_state([make_crawl_result()])
    patcher, crawler_cls = patch_adaptive_crawler(state)
    with patch_crawler(), patcher, patch_clean_passthrough():
        await retrieval.fetch_documentation("https://docs.example.com/", force_refresh=True)

    assert crawler_cls.call_args.kwargs["cache_mode"] == CacheMode.BYPASS


@pytest.mark.asyncio
async def test_fetch_documentation_passes_max_depth_into_adaptive_config():
    state = make_fake_state([make_crawl_result()])
    patcher, crawler_cls = patch_adaptive_crawler(state)
    with patch_crawler(), patcher, patch_clean_passthrough():
        await retrieval.fetch_documentation("https://docs.example.com/", max_depth=8)

    assert crawler_cls.call_args.kwargs["config"].max_depth == 8


@pytest.mark.asyncio
async def test_fetch_documentation_default_max_depth_is_five():
    state = make_fake_state([make_crawl_result()])
    patcher, crawler_cls = patch_adaptive_crawler(state)
    with patch_crawler(), patcher, patch_clean_passthrough():
        await retrieval.fetch_documentation("https://docs.example.com/")

    assert crawler_cls.call_args.kwargs["config"].max_depth == 5


@pytest.mark.asyncio
async def test_fetch_documentation_passes_exclude_urls_through_as_a_frozenset():
    state = make_fake_state([make_crawl_result()])
    patcher, crawler_cls = patch_adaptive_crawler(state)
    with patch_crawler(), patcher, patch_clean_passthrough():
        await retrieval.fetch_documentation(
            "https://docs.example.com/", exclude_urls=["https://docs.example.com/wrong-page"]
        )

    assert crawler_cls.call_args.kwargs["exclude_urls"] == frozenset({"https://docs.example.com/wrong-page"})


@pytest.mark.asyncio
async def test_fetch_documentation_default_exclude_urls_is_empty():
    state = make_fake_state([make_crawl_result()])
    patcher, crawler_cls = patch_adaptive_crawler(state)
    with patch_crawler(), patcher, patch_clean_passthrough():
        await retrieval.fetch_documentation("https://docs.example.com/")

    assert crawler_cls.call_args.kwargs["exclude_urls"] == frozenset()


@pytest.mark.asyncio
async def test_fetch_documentation_folds_hint_into_query_and_strategy():
    state = make_fake_state([make_crawl_result()])
    patcher, crawler_cls = patch_adaptive_crawler(state)
    with patch_crawler(), patcher, patch_clean_passthrough():
        await retrieval.fetch_documentation(
            "https://docs.example.com/", hint="prioritize pages about environment variables"
        )

    instance = crawler_cls.return_value
    digest_query = instance.digest.call_args.kwargs["query"]
    assert "prioritize pages about environment variables" in digest_query
    assert retrieval._QUERY in digest_query  # hint is additive, not a replacement

    strategy = crawler_cls.call_args.kwargs["strategy"]
    assert strategy._hint == "prioritize pages about environment variables"


@pytest.mark.asyncio
async def test_fetch_documentation_no_hint_uses_bare_query():
    state = make_fake_state([make_crawl_result()])
    patcher, crawler_cls = patch_adaptive_crawler(state)
    with patch_crawler(), patcher, patch_clean_passthrough():
        await retrieval.fetch_documentation("https://docs.example.com/")

    instance = crawler_cls.return_value
    assert instance.digest.call_args.kwargs["query"] == retrieval._QUERY
    assert crawler_cls.call_args.kwargs["strategy"]._hint is None


@pytest.mark.asyncio
async def test_fetch_documentation_returns_meta_for_retry_decisions():
    unexplored = make_link("https://docs.example.com/c", text="Caching", title="Caching reference")
    state = make_fake_state([make_crawl_result(), make_crawl_result(url="https://docs.example.com/b")],
                             confidence=0.42, depth_reached=2, pending_links=[unexplored])
    patcher, _ = patch_adaptive_crawler(state)
    with patch_crawler(), patcher, patch_clean_passthrough():
        result = await retrieval.fetch_documentation("https://docs.example.com/")

    assert result["meta"] == {
        "confidence": 0.42,
        "pages_crawled": 2,
        "depth_reached": 2,
        "pending_links": [{"href": "https://docs.example.com/c", "text": "Caching", "title": "Caching reference"}],
    }


@pytest.mark.asyncio
async def test_fetch_documentation_dedupes_pending_links_by_href():
    # flagged on independent review, confirmed by reading crawl4ai 0.9.2's own
    # AdaptiveCrawler.digest() source: pending_links is only checked against
    # already-crawled URLs before a new link is appended, never against links
    # already pending, so the same href (e.g. one appearing in every page's
    # nav) can legitimately end up queued more than once
    dup_a = make_link("https://docs.example.com/needs", text="needs")
    dup_b = make_link("https://docs.example.com/needs", text="needs (seen again)")
    unique = make_link("https://docs.example.com/rules", text="rules")
    state = make_fake_state([make_crawl_result()], pending_links=[dup_a, dup_b, unique])
    patcher, _ = patch_adaptive_crawler(state)
    with patch_crawler(), patcher, patch_clean_passthrough():
        result = await retrieval.fetch_documentation("https://docs.example.com/")

    hrefs = [link["href"] for link in result["meta"]["pending_links"]]
    assert hrefs == ["https://docs.example.com/needs", "https://docs.example.com/rules"]


@pytest.mark.asyncio
async def test_fetch_documentation_excludes_already_crawled_urls_from_pending_links():
    # confirmed live against a real crawl: crawl4ai's own state.pending_links can
    # still list a URL after it was actually crawled into knowledge_base, its
    # bookkeeping only prevents re-adding an already-crawled URL to the pending
    # queue, it doesn't remove one that was already queued before being fetched
    already_crawled = make_link("https://docs.example.com/", text="seed, already fetched")
    still_pending = make_link("https://docs.example.com/rules", text="rules")
    state = make_fake_state([make_crawl_result(url="https://docs.example.com/")],
                             pending_links=[already_crawled, still_pending])
    patcher, _ = patch_adaptive_crawler(state)
    with patch_crawler(), patcher, patch_clean_passthrough():
        result = await retrieval.fetch_documentation("https://docs.example.com/")

    hrefs = [link["href"] for link in result["meta"]["pending_links"]]
    assert hrefs == ["https://docs.example.com/rules"]


@pytest.mark.asyncio
async def test_fetch_documentation_normalizes_seed_url_before_excluding_from_pending_links():
    # flagged on independent review: crawl4ai normalizes every discovered link
    # href (tracking params dropped, query sorted) before it reaches
    # pending_links, but a page's own result.url is never normalized, it's
    # whatever string was passed to arun(). For the seed page that's the
    # caller's raw seed_url, so a self-link back to it in already-normalized
    # form would slip past an exact-string-only already-crawled filter
    raw_seed = "https://docs.example.com/?utm_source=x&b=2&a=1"
    normalized_self_link = make_link("https://docs.example.com/?a=1&b=2", text="home")
    still_pending = make_link("https://docs.example.com/rules", text="rules")
    state = make_fake_state([make_crawl_result(url=raw_seed)],
                             pending_links=[normalized_self_link, still_pending])
    patcher, _ = patch_adaptive_crawler(state)
    with patch_crawler(), patcher, patch_clean_passthrough():
        result = await retrieval.fetch_documentation(raw_seed)

    hrefs = [link["href"] for link in result["meta"]["pending_links"]]
    assert hrefs == ["https://docs.example.com/rules"]


@pytest.mark.asyncio
async def test_fetch_documentation_dedupes_before_cleanup_so_llm_never_sees_the_repeat():
    boilerplate = "this cookie notice paragraph is long enough to count as real content by length alone"
    # padded well past _MIN_USABLE_CONTENT_CHARS so this test isn't entangled
    # with the separate short-content-marked-unsuccessful behavior
    unique_a = "unique content a. " * 10
    unique_b = "unique content b. " * 10
    state = make_fake_state([
        make_crawl_result(url="https://docs.example.com/a", markdown=f"{unique_a}\n\n{boilerplate}"),
        make_crawl_result(url="https://docs.example.com/b", markdown=f"{boilerplate}\n\n{unique_b}"),
    ])
    patcher, _ = patch_adaptive_crawler(state)
    seen_by_cleanup = []

    async def record_and_passthrough(markdown, force_refresh=False):
        seen_by_cleanup.append(markdown)
        return markdown

    with patch_crawler(), patcher, patch.object(retrieval, "clean_page_content", side_effect=record_and_passthrough):
        result = await retrieval.fetch_documentation("https://docs.example.com/")

    # page b's markdown, as sent to clean_page_content, must NOT contain the
    # boilerplate, the pre-cleanup dedupe_pages() pass already stripped it
    page_b_seen = seen_by_cleanup[1]
    assert boilerplate not in page_b_seen
    assert boilerplate in result["pages"][0]["markdown"]  # first occurrence survives
    assert boilerplate not in result["pages"][1]["markdown"]


@pytest.mark.asyncio
async def test_fetch_documentation_calls_dedupe_pages_twice():
    state = make_fake_state([make_crawl_result()])
    patcher, _ = patch_adaptive_crawler(state)
    with patch_crawler(), patcher, patch_clean_passthrough(), \
         patch.object(retrieval, "dedupe_pages", wraps=retrieval.dedupe_pages) as spy:
        await retrieval.fetch_documentation("https://docs.example.com/")
    assert spy.call_count == 2


@pytest.mark.asyncio
async def test_fetch_documentation_marks_unsuccessful_when_dedup_hollows_a_page_out():
    # confirmed live: a page can start well over the content floor and still end
    # up nearly empty after dedupe_pages strips out paragraphs that turn out to
    # be near-total repeats of an already-captured sibling page, this must be
    # re-checked after dedup, not just once on the original fetched content
    boilerplate = ("shared content repeated across both pages, long enough to dedupe. " * 4).strip()
    state = make_fake_state([
        make_crawl_result(url="https://docs.example.com/a", markdown=boilerplate),
        # starts over the floor (boilerplate + a short unique tail), but almost
        # entirely duplicate of page a's content, so dedup should hollow it out
        make_crawl_result(url="https://docs.example.com/b", markdown=f"{boilerplate}\n\ntail"),
    ])
    patcher, _ = patch_adaptive_crawler(state)
    with patch_crawler(), patcher, patch_clean_passthrough():
        result = await retrieval.fetch_documentation("https://docs.example.com/")

    page_b = next(p for p in result["pages"] if p["url"] == "https://docs.example.com/b")
    assert page_b["success"] is False


# --- clean_page_content --------------------------------------------------------


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
async def test_clean_page_content_chunks_and_reassembles():
    long_markdown = "first paragraph of real content" + "\n\n" + "second paragraph of real content"
    with patch_chat_response("cleaned content"):
        cleaned = await retrieval.clean_page_content(long_markdown)
    assert cleaned == "cleaned content"


@pytest.mark.asyncio
async def test_clean_page_content_second_call_with_same_content_is_a_cache_hit():
    markdown = "identical content fetched twice, e.g. by a retry that revisits the same page"
    with patch_chat_response("cleaned once") as _:
        first = await retrieval.clean_page_content(markdown)
    assert first == "cleaned once"

    # no chat mock active this time; a real network call would raise/fail here,
    # a cache hit must return the cached result without attempting one
    with patch.object(retrieval.httpx, "AsyncClient", side_effect=AssertionError("should not call the network")):
        second = await retrieval.clean_page_content(markdown)
    assert second == "cleaned once"


@pytest.mark.asyncio
async def test_clean_page_content_force_refresh_bypasses_cache():
    markdown = "content that will be force-refreshed on the second call"
    with patch_chat_response("first cleanup"):
        first = await retrieval.clean_page_content(markdown)
    assert first == "first cleanup"

    with patch_chat_response("second cleanup, fresh"):
        second = await retrieval.clean_page_content(markdown, force_refresh=True)
    assert second == "second cleanup, fresh"


@pytest.mark.asyncio
async def test_clean_page_content_reassembles_concurrent_chunks_in_original_order():
    # two paragraphs each just under _CLEAN_CHUNK_SIZE force a 2-chunk split
    para_a = "A" * 7000
    para_b = "B" * 7000
    markdown = f"{para_a}\n\n{para_b}"

    # tags must be over _clean_chunk's 20-char near-empty floor, a short mock
    # return value would otherwise trip that guard and get discarded, keeping
    # the original chunk instead, exactly the failure mode this test wants to
    # rule out (chunks NOT reassembled in the wrong order because one got
    # replaced by fallback content instead of the mocked "cleaned" tag)
    cleaned_a_tag = "CLEANED_CHUNK_A_MARKER_TEXT_OVER_20_CHARS"
    cleaned_b_tag = "CLEANED_CHUNK_B_MARKER_TEXT_OVER_20_CHARS"

    async def echo_by_content(url, json):
        chunk = json["messages"][1]["content"]
        response = MagicMock()
        response.raise_for_status.return_value = None
        tag = cleaned_a_tag if "A" * 10 in chunk else cleaned_b_tag
        response.json.return_value = {"content": tag}
        return response

    client = AsyncMock()
    client.post.side_effect = echo_by_content
    client_cm = MagicMock()
    client_cm.__aenter__.return_value = client
    client_cm.__aexit__.return_value = False

    with patch.object(retrieval.httpx, "AsyncClient", return_value=client_cm):
        cleaned = await retrieval.clean_page_content(markdown)

    # gather() preserves input order in its results regardless of completion order,
    # reassembly must reflect the original paragraph order (A before B), not
    # whichever chunk's mocked call happened to resolve first
    assert cleaned == f"{cleaned_a_tag}\n\n{cleaned_b_tag}"
    assert client.post.call_count == 2


@pytest.mark.asyncio
async def test_clean_page_content_bounds_concurrency():
    # 5 chunks, but _CLEAN_CONCURRENCY=4, so at most 4 should ever be in flight at once
    paragraphs = [f"paragraph {i} " + ("x" * 11000) for i in range(5)]
    markdown = "\n\n".join(paragraphs)
    in_flight = 0
    max_in_flight = 0

    async def track_concurrency(url, json):
        nonlocal in_flight, max_in_flight
        in_flight += 1
        max_in_flight = max(max_in_flight, in_flight)
        await asyncio.sleep(0.01)
        in_flight -= 1
        response = MagicMock()
        response.raise_for_status.return_value = None
        response.json.return_value = {"content": "cleaned"}
        return response

    client = AsyncMock()
    client.post.side_effect = track_concurrency
    client_cm = MagicMock()
    client_cm.__aenter__.return_value = client
    client_cm.__aexit__.return_value = False

    with patch.object(retrieval.httpx, "AsyncClient", return_value=client_cm):
        await retrieval.clean_page_content(markdown)

    assert max_in_flight <= retrieval._CLEAN_CONCURRENCY


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


@pytest.mark.asyncio
async def test_clean_page_content_discards_suspiciously_longer_result():
    # cleanup's whole job is removing chrome, legitimate output should never come
    # back meaningfully longer than the input. Flagged on independent review as a
    # partial mitigation against prompt injection via page content: this chunk is
    # untrusted, attacker-reachable text sent to an LLM with instructions to
    # return it byte-for-byte unchanged except for chrome, nothing previously
    # caught a response that came back expanded rather than trimmed
    original = "genuine documentation content, long enough to be a real chunk, not caught by the near-empty guard"
    suspiciously_long = original + (" extra injected content that should not be here" * 5)
    with patch_chat_response(suspiciously_long):
        cleaned = await retrieval.clean_page_content(original)
    assert cleaned == original


# --- dedupe_pages ---------------------------------------------------------------


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


# --- fetch_single_page -----------------------------------------------------------


def patch_crawler_with_result(result):
    """Patches AsyncWebCrawler so fetch_single_page()'s bare `crawler.arun(url, config)`
    call (no _ConfiguredAdaptiveCrawler involved, this is a direct single-page fetch)
    returns the given CrawlResult."""
    crawler_instance = AsyncMock()
    crawler_instance.arun.return_value = result
    crawler_cm = MagicMock()
    crawler_cm.__aenter__ = AsyncMock(return_value=crawler_instance)
    crawler_cm.__aexit__ = AsyncMock(return_value=False)
    return patch.object(retrieval, "AsyncWebCrawler", return_value=crawler_cm), crawler_instance


@pytest.mark.asyncio
async def test_fetch_single_page_returns_shaped_and_cleaned_page():
    result = make_crawl_result(url="https://docs.example.com/env-vars", markdown=_LONG_ENOUGH_CONTENT)
    patcher, crawler_instance = patch_crawler_with_result(result)
    with patcher, patch_clean_passthrough():
        page = await retrieval.fetch_single_page("https://docs.example.com/env-vars")

    assert page["url"] == "https://docs.example.com/env-vars"
    assert page["success"] is True
    assert page["markdown"] == _LONG_ENOUGH_CONTENT
    call_kwargs = crawler_instance.arun.call_args.kwargs
    assert call_kwargs["url"] == "https://docs.example.com/env-vars"
    assert call_kwargs["config"].wait_for == retrieval._WAIT_FOR
    assert call_kwargs["config"].markdown_generator is not None


@pytest.mark.asyncio
async def test_fetch_single_page_default_uses_cache_enabled():
    result = make_crawl_result()
    patcher, crawler_instance = patch_crawler_with_result(result)
    with patcher, patch_clean_passthrough():
        await retrieval.fetch_single_page("https://docs.example.com/")

    assert crawler_instance.arun.call_args.kwargs["config"].cache_mode == CacheMode.ENABLED


@pytest.mark.asyncio
async def test_fetch_single_page_force_refresh_uses_cache_bypass():
    result = make_crawl_result()
    patcher, crawler_instance = patch_crawler_with_result(result)
    with patcher, patch_clean_passthrough():
        await retrieval.fetch_single_page("https://docs.example.com/", force_refresh=True)

    assert crawler_instance.arun.call_args.kwargs["config"].cache_mode == CacheMode.BYPASS


@pytest.mark.asyncio
async def test_fetch_single_page_does_not_clean_unsuccessful_page():
    result = make_crawl_result(markdown="short")  # under _MIN_USABLE_CONTENT_CHARS
    patcher, _ = patch_crawler_with_result(result)
    with patcher, patch.object(retrieval, "clean_page_content", AsyncMock()) as mock_clean:
        page = await retrieval.fetch_single_page("https://docs.example.com/")

    assert page["success"] is False
    mock_clean.assert_not_called()


@pytest.mark.asyncio
async def test_fetch_single_page_returns_graceful_failure_instead_of_raising():
    # flagged on independent review: _crawl_with_preview (the multi-hop path) was
    # specifically hardened against real fetch failures with a try/except, this
    # single-page path wasn't, so a bad URL raised an unhandled exception all the
    # way to a raw 500 instead of the same {success: false} shape every other
    # failure in this service gets
    crawler_instance = AsyncMock()
    crawler_instance.arun.side_effect = ConnectionError("site unreachable")
    crawler_cm = MagicMock()
    crawler_cm.__aenter__ = AsyncMock(return_value=crawler_instance)
    crawler_cm.__aexit__ = AsyncMock(return_value=False)
    with patch.object(retrieval, "AsyncWebCrawler", return_value=crawler_cm):
        page = await retrieval.fetch_single_page("https://docs.example.com/unreachable")

    assert page == {
        "url": "https://docs.example.com/unreachable",
        "success": False,
        "status_code": None,
        "markdown": "",
        "links": [],
    }


@pytest.mark.asyncio
async def test_fetch_single_page_rejects_unsafe_url_before_fetching():
    with patch_dns("127.0.0.1"), patch.object(retrieval, "AsyncWebCrawler") as mock_crawler_cls:
        with pytest.raises(ValueError, match="non-public"):
            await retrieval.fetch_single_page("http://localhost/")
    mock_crawler_cls.assert_not_called()


# --- config validation helpers ---------------------------------------------------
# These run for real at module import time against the actual env-derived
# constants; tested here directly against the helper functions so a bad value
# (e.g. CLEAN_CONCURRENCY=0, which would deadlock asyncio.Semaphore(0) forever)
# fails fast with a clear message instead of silently, or as an opaque
# AssertionError from deep inside crawl4ai's own AdaptiveConfig validation.


def test_require_positive_accepts_positive_values():
    retrieval._require_positive("X", 1)
    retrieval._require_positive("X", 0.5)


def test_require_positive_rejects_zero_and_negative():
    with pytest.raises(ValueError, match="must be positive"):
        retrieval._require_positive("X", 0)
    with pytest.raises(ValueError, match="must be positive"):
        retrieval._require_positive("X", -1)


def test_require_unit_interval_accepts_bounds_inclusive():
    retrieval._require_unit_interval("X", 0.0)
    retrieval._require_unit_interval("X", 1.0)
    retrieval._require_unit_interval("X", 0.5)


def test_require_unit_interval_rejects_out_of_range():
    with pytest.raises(ValueError, match="between 0 and 1"):
        retrieval._require_unit_interval("X", -0.01)
    with pytest.raises(ValueError, match="between 0 and 1"):
        retrieval._require_unit_interval("X", 1.01)
