"""
FastAPI endpoint tests. No real crawling, no real LLM call, retrieval.fetch_documentation
and retrieval.fetch_single_page are mocked.

Tests verify:
  1. /health reports ok.
  2. /fetch calls fetch_documentation with the given url, max_pages, max_depth, force_refresh,
     and hint, returns its result.
  3. /fetch uses the defaults (DEFAULT_MAX_PAGES, DEFAULT_MAX_DEPTH, force_refresh=False,
     hint=None) when not specified.
  4. /fetch passes force_refresh=True and hint through when specified.
  5. /fetch converts a downstream exception into a 500.
  6. /fetch rejects a non-URL string with 422 before ever calling fetch_documentation.
  7. /fetch rejects max_pages/max_depth outside their bounds with 422 before ever calling
     fetch_documentation.
  8. /fetch/page calls fetch_single_page with the given url and force_refresh, returns its
     result; defaults force_refresh to False; converts a downstream exception to 500; rejects
     a non-URL string with 422.
"""
from unittest.mock import AsyncMock, patch

from fastapi.testclient import TestClient

import main

client = TestClient(main.app)


def test_health():
    response = client.get("/health")
    assert response.status_code == 200
    assert response.json() == {"status": "ok"}


def test_fetch_returns_fetch_documentation_result():
    fake_result = {
        "seed_url": "https://docs.example.com/",
        "pages": [{"url": "https://docs.example.com/", "success": True, "status_code": 200, "markdown": "content", "links": []}],
        "meta": {"confidence": 0.5, "pages_crawled": 1, "depth_reached": 0, "pending_links": [{"href": "https://docs.example.com/x", "text": "", "title": ""}]},
    }
    with patch.object(main, "fetch_documentation", AsyncMock(return_value=fake_result)) as mock_fetch:
        response = client.post("/fetch", json={"url": "https://docs.example.com/", "max_pages": 3})
    assert response.status_code == 200
    assert response.json() == fake_result
    mock_fetch.assert_called_once_with(
        "https://docs.example.com/", max_pages=3, force_refresh=False, hint=None,
        max_depth=main.DEFAULT_MAX_DEPTH, exclude_urls=None,
    )


def test_fetch_defaults_max_pages_max_depth_and_force_refresh():
    fake_result = {"seed_url": "u", "pages": [], "meta": {"confidence": 0.0, "pages_crawled": 0, "depth_reached": None, "pending_links": []}}
    with patch.object(main, "fetch_documentation", AsyncMock(return_value=fake_result)) as mock_fetch:
        client.post("/fetch", json={"url": "https://docs.example.com/"})
    mock_fetch.assert_called_once_with(
        "https://docs.example.com/",
        max_pages=main.DEFAULT_MAX_PAGES,
        force_refresh=False,
        hint=None,
        max_depth=main.DEFAULT_MAX_DEPTH,
        exclude_urls=None,
    )


def test_fetch_passes_force_refresh_hint_and_exclude_urls_through():
    fake_result = {"seed_url": "u", "pages": [], "meta": {"confidence": 0.0, "pages_crawled": 0, "depth_reached": None, "pending_links": []}}
    with patch.object(main, "fetch_documentation", AsyncMock(return_value=fake_result)) as mock_fetch:
        client.post("/fetch", json={
            "url": "https://docs.example.com/",
            "force_refresh": True,
            "hint": "prioritize pages about environment variables",
            "max_depth": 8,
            "exclude_urls": ["https://docs.example.com/wrong-page"],
        })
    mock_fetch.assert_called_once_with(
        "https://docs.example.com/",
        max_pages=main.DEFAULT_MAX_PAGES,
        force_refresh=True,
        hint="prioritize pages about environment variables",
        max_depth=8,
        exclude_urls=["https://docs.example.com/wrong-page"],
    )


def test_fetch_converts_exception_to_500():
    with patch.object(main, "fetch_documentation", AsyncMock(side_effect=RuntimeError("crawler exploded"))):
        response = client.post("/fetch", json={"url": "https://docs.example.com/"})
    assert response.status_code == 500
    assert "crawler exploded" not in response.json()["detail"]  # internal exception text not leaked to caller
    assert "POST /fetch" in response.json()["detail"]


def test_fetch_rejects_invalid_url():
    with patch.object(main, "fetch_documentation", AsyncMock()) as mock_fetch:
        response = client.post("/fetch", json={"url": "not a url"})
    assert response.status_code == 422
    mock_fetch.assert_not_called()


def test_fetch_rejects_max_pages_out_of_bounds():
    with patch.object(main, "fetch_documentation", AsyncMock()) as mock_fetch:
        too_high = client.post("/fetch", json={"url": "https://docs.example.com/", "max_pages": 21})
        too_low = client.post("/fetch", json={"url": "https://docs.example.com/", "max_pages": 0})
    assert too_high.status_code == 422
    assert too_low.status_code == 422
    mock_fetch.assert_not_called()


def test_fetch_rejects_max_depth_out_of_bounds():
    with patch.object(main, "fetch_documentation", AsyncMock()) as mock_fetch:
        too_high = client.post("/fetch", json={"url": "https://docs.example.com/", "max_depth": 11})
        too_low = client.post("/fetch", json={"url": "https://docs.example.com/", "max_depth": 0})
    assert too_high.status_code == 422
    assert too_low.status_code == 422
    mock_fetch.assert_not_called()


# --- /fetch/page ----------------------------------------------------------------


def test_fetch_page_returns_fetch_single_page_result():
    fake_page = {"url": "https://docs.example.com/a", "success": True, "status_code": 200, "markdown": "content", "links": []}
    with patch.object(main, "fetch_single_page", AsyncMock(return_value=fake_page)) as mock_fetch:
        response = client.post("/fetch/page", json={"url": "https://docs.example.com/a"})
    assert response.status_code == 200
    assert response.json() == fake_page
    mock_fetch.assert_called_once_with("https://docs.example.com/a", force_refresh=False)


def test_fetch_page_passes_force_refresh_through():
    fake_page = {"url": "https://docs.example.com/a", "success": True, "status_code": 200, "markdown": "c", "links": []}
    with patch.object(main, "fetch_single_page", AsyncMock(return_value=fake_page)) as mock_fetch:
        client.post("/fetch/page", json={"url": "https://docs.example.com/a", "force_refresh": True})
    mock_fetch.assert_called_once_with("https://docs.example.com/a", force_refresh=True)


def test_fetch_page_converts_exception_to_500():
    with patch.object(main, "fetch_single_page", AsyncMock(side_effect=RuntimeError("fetch exploded"))):
        response = client.post("/fetch/page", json={"url": "https://docs.example.com/a"})
    assert response.status_code == 500
    assert "fetch exploded" not in response.json()["detail"]  # internal exception text not leaked to caller
    assert "POST /fetch/page" in response.json()["detail"]


def test_fetch_page_rejects_invalid_url():
    with patch.object(main, "fetch_single_page", AsyncMock()) as mock_fetch:
        response = client.post("/fetch/page", json={"url": "not a url"})
    assert response.status_code == 422
    mock_fetch.assert_not_called()
