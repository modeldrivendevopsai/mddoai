"""
FastAPI endpoint tests. No real crawling, no real LLM call, retrieval.fetch_documentation
is mocked.

Tests verify:
  1. /health reports ok.
  2. /fetch calls fetch_documentation with the given url and max_pages, returns its result.
  3. /fetch uses the default max_pages=5 when the request doesn't specify one.
  4. /fetch converts a downstream exception into a 500.
  5. /fetch rejects a non-URL string with 422 before ever calling fetch_documentation.
  6. /fetch rejects max_pages outside [1, 20] with 422 before ever calling fetch_documentation.
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
    fake_result = {"seed_url": "https://docs.example.com/", "pages": [{"url": "https://docs.example.com/", "markdown": "content"}]}
    with patch.object(main, "fetch_documentation", AsyncMock(return_value=fake_result)) as mock_fetch:
        response = client.post("/fetch", json={"url": "https://docs.example.com/", "max_pages": 3})
    assert response.status_code == 200
    assert response.json() == fake_result
    mock_fetch.assert_called_once_with("https://docs.example.com/", max_pages=3)


def test_fetch_defaults_max_pages_to_five():
    with patch.object(main, "fetch_documentation", AsyncMock(return_value={"seed_url": "u", "pages": []})) as mock_fetch:
        client.post("/fetch", json={"url": "https://docs.example.com/"})
    mock_fetch.assert_called_once_with("https://docs.example.com/", max_pages=5)


def test_fetch_converts_exception_to_500():
    with patch.object(main, "fetch_documentation", AsyncMock(side_effect=RuntimeError("crawler exploded"))):
        response = client.post("/fetch", json={"url": "https://docs.example.com/"})
    assert response.status_code == 500
    assert "crawler exploded" in response.json()["detail"]


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
