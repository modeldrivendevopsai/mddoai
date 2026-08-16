"""retrieval_client.py unit tests: fetch_documentation()/fetch_page() POST
to retrieval's real endpoints with the right payload shape. Mocks
httpx.post directly (the actual network boundary), not the function itself
— every consumer (integration_runner's docs_stage and its own extra
docs-extending action) has its own tests that mock these functions instead,
at their own boundary.
"""
from unittest.mock import patch

import retrieval_client
from helpers import _fake_fetch_response, _fake_page_response


def test_fetch_documentation_posts_url_and_omits_unset_optional_params():
    with patch.object(retrieval_client, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_fetch_response()
        retrieval_client.fetch_documentation("https://example.com/docs")

    mock_httpx.post.assert_called_once_with(
        f"{retrieval_client.RETRIEVAL_URL}/fetch", json={"url": "https://example.com/docs"}, timeout=180.0,
    )


def test_fetch_documentation_forwards_all_optional_params_when_given():
    with patch.object(retrieval_client, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_fetch_response()
        retrieval_client.fetch_documentation(
            "https://example.com/docs", hint="focus on syntax", exclude_urls=["https://example.com/blog"],
            max_pages=5, max_depth=2, force_refresh=True,
        )

    sent = mock_httpx.post.call_args.kwargs["json"]
    assert sent == {
        "url": "https://example.com/docs", "hint": "focus on syntax",
        "exclude_urls": ["https://example.com/blog"], "max_pages": 5, "max_depth": 2, "force_refresh": True,
    }


def test_fetch_page_posts_url_and_force_refresh():
    with patch.object(retrieval_client, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_page_response()
        retrieval_client.fetch_page("https://example.com/docs/specific-page", force_refresh=True)

    mock_httpx.post.assert_called_once_with(
        f"{retrieval_client.RETRIEVAL_URL}/fetch/page",
        json={"url": "https://example.com/docs/specific-page", "force_refresh": True},
        timeout=60.0,
    )
