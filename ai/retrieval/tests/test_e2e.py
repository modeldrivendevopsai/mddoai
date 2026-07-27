"""
Real end-to-end tests: real headless Chromium, real network, real ai-layer LLM
calls (ranking + cleanup). No mocking anywhere. Marked integration, excluded
from the default run (see pytest.ini), run explicitly with `pytest -m
integration`. Requires AI_LAYER_URL to be reachable and outbound internet
access, both already true when run inside the retrieval container via
docker-compose.

The unit tests in test_retrieval_unit.py mock every real boundary (browser, network,
LLM) precisely so they stay fast and deterministic; that also means none of
them can catch a real crawl4ai/Playwright/ai-layer integration break. This
file exists for that: fewer, slower, but real.

GitLab CI's yaml reference keeps its entire schema on one page (confirmed live
this session), so this stays fast even as a real crawl.
"""
import pytest

import retrieval

GITLAB_YAML_REFERENCE = "https://docs.gitlab.com/ci/yaml/"
_CHROME_TERMS = ("accept cookies", "sign in to your account", "skip to content", "subscribe to our newsletter")


@pytest.mark.integration
@pytest.mark.asyncio
async def test_fetch_documentation_against_real_docs():
    result = await retrieval.fetch_documentation(GITLAB_YAML_REFERENCE, max_pages=2, force_refresh=True)

    assert result["seed_url"] == GITLAB_YAML_REFERENCE
    assert result["pages"], "expected at least one page back from a real crawl"
    seed_page = result["pages"][0]
    assert seed_page["url"] == GITLAB_YAML_REFERENCE
    assert seed_page["success"] is True
    assert len(seed_page["markdown"]) > 1000, "a real CI/CD yaml reference page should be substantial"

    markdown_lower = seed_page["markdown"].lower()
    # real syntax content actually present, not just chrome
    for keyword in ("stages", "rules", "artifacts"):
        assert keyword in markdown_lower, f"expected real yaml reference content to mention {keyword!r}"
    # chrome actually removed, not just present in smaller amounts
    for term in _CHROME_TERMS:
        assert term not in markdown_lower, f"leftover site chrome found: {term!r}"

    assert 0.0 <= result["meta"]["confidence"] <= 1.0
    assert isinstance(result["meta"]["pending_links"], list)


@pytest.mark.integration
@pytest.mark.asyncio
async def test_fetch_single_page_against_real_docs():
    page = await retrieval.fetch_single_page(GITLAB_YAML_REFERENCE, force_refresh=True)

    assert page["url"] == GITLAB_YAML_REFERENCE
    assert page["success"] is True
    assert page["status_code"] == 200
    assert len(page["markdown"]) > 1000
    assert "stages" in page["markdown"].lower()
