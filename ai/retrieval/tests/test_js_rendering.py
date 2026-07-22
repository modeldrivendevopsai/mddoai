"""
Integration test: runs the REAL AsyncWebCrawler with the module's real
_base_run_config_kwargs (wait_for + content filter) against a local static
HTML fixture that injects content via a delayed script, mimicking a
JS-rendered SPA doc page. Launches a real browser, excluded from the default
test run (see pytest.ini's addopts), run explicitly with `pytest -m
integration`.

This exists because the wait_for config is the entire reason Crawl4AI was
chosen over a plain-HTTP tool in the first place (confirmed by hand against
TeamCity's real docs: 72 chars vs. 7600+), and without this test nothing in
the automated suite verifies that behavior at all. A Crawl4AI/Playwright
upgrade or a config typo could silently break it.
"""
import http.server
import threading

import pytest

import retrieval

DELAYED_TEXT = "this text only exists after the delayed script runs"

# _WAIT_FOR's real condition is innerText.length > WAIT_FOR_MIN_CHARS (1000 by
# default), tuned against real documentation pages, not a token amount. Static
# content alone is kept well under that (so the condition can't resolve before
# the delayed script even runs, which would defeat the point of this test);
# static + delayed together comfortably clears it, so the condition only
# becomes true once the delayed content is actually present, and the total
# also clears retrieval._MIN_USABLE_CONTENT_CHARS so a real page-sized fixture
# doesn't get marked unsuccessful purely for being short.
_STATIC_FILLER = "This is static content that is present immediately on page load, before any script runs. " * 6
_DELAYED_FILLER = f"{DELAYED_TEXT}. " * 10

FIXTURE_HTML = f"""<!DOCTYPE html>
<html>
<head><title>JS-rendered fixture</title></head>
<body>
<p>{_STATIC_FILLER}</p>
<div id="app"></div>
<script>
setTimeout(function() {{
  document.getElementById("app").innerText = {_DELAYED_FILLER!r};
}}, 800);
</script>
</body>
</html>"""


class _FixtureHandler(http.server.BaseHTTPRequestHandler):
    def do_GET(self):
        body = FIXTURE_HTML.encode("utf-8")
        self.send_response(200)
        self.send_header("Content-Type", "text/html")
        self.send_header("Content-Length", str(len(body)))
        self.end_headers()
        self.wfile.write(body)

    def log_message(self, *args):
        pass  # keep test output quiet


@pytest.fixture
def local_server():
    server = http.server.HTTPServer(("127.0.0.1", 0), _FixtureHandler)
    port = server.server_address[1]
    thread = threading.Thread(target=server.serve_forever, daemon=True)
    thread.start()
    yield f"http://127.0.0.1:{port}/"
    server.shutdown()
    thread.join(timeout=5)


@pytest.mark.integration
@pytest.mark.asyncio
async def test_wait_config_captures_delayed_js_content(local_server):
    from crawl4ai import AsyncWebCrawler, CacheMode, CrawlerRunConfig

    # Goes through _base_run_config_kwargs (the real wait_for/content-filter config
    # every fetch in this service uses) and a real crawler.arun(), not through
    # fetch_single_page's public entrypoint, that would also run SSRF validation,
    # which correctly rejects this fixture's 127.0.0.1 address, unrelated to what
    # this test actually verifies.
    config = CrawlerRunConfig(**retrieval._base_run_config_kwargs(CacheMode.BYPASS))
    async with AsyncWebCrawler() as crawler:
        result = await crawler.arun(url=local_server, config=config)
    page = retrieval._result_to_page(result)

    assert page["success"] is True
    assert "static content that is present immediately" in page["markdown"]
    assert DELAYED_TEXT in page["markdown"], (
        "delayed script content is missing, the wait_for + delay_before_return_html "
        "config in retrieval._base_run_config_kwargs isn't waiting long enough for "
        "JS-injected content anymore"
    )
