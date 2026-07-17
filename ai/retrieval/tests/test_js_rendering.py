"""
Integration test: runs the REAL AsyncWebCrawler with the module's real
_PAGE_CONFIG against a local static HTML fixture that injects content via a
delayed script, mimicking a JS-rendered SPA doc page. Launches a real browser,
excluded from the default test run (see pytest.ini's addopts), run explicitly
with `pytest -m integration`.

This exists because the wait_until="networkidle" + settle-delay config is the
entire reason Crawl4AI was chosen over a plain-HTTP tool in the first place
(confirmed by hand against TeamCity's real docs: 72 chars vs. 7600+), and until
this test existed, nothing in the automated suite verified that behavior at
all. A Crawl4AI/Playwright upgrade or a config typo could silently break it.
"""
import http.server
import threading

import pytest

import retrieval

DELAYED_TEXT = "this text only exists after the delayed script runs"

FIXTURE_HTML = f"""<!DOCTYPE html>
<html>
<head><title>JS-rendered fixture</title></head>
<body>
<p>this is static content that is present immediately on page load, before any script runs</p>
<div id="app"></div>
<script>
setTimeout(function() {{
  document.getElementById("app").innerText = "{DELAYED_TEXT}";
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
    from crawl4ai import AsyncWebCrawler

    async with AsyncWebCrawler() as crawler:
        page = await retrieval.fetch_page(local_server, crawler)

    assert page["success"] is True
    assert "static content that is present immediately" in page["markdown"]
    assert DELAYED_TEXT in page["markdown"], (
        "delayed script content is missing, the wait_until='networkidle' + "
        "delay_before_return_html config in retrieval._PAGE_CONFIG isn't "
        "waiting long enough for JS-injected content anymore"
    )
