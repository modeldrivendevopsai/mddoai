# retrieval

Fetches CI/CD platform documentation from a URL and returns clean markdown, for use by
downstream generation agents (PSM/ATL/Acceleo). Implements issue #220.

## What it fetches

Not general documentation, specifically a platform's configuration syntax reference: the
keyword-by-keyword schema for its pipeline definition file. GitLab CI's
[yaml reference](https://docs.gitlab.com/ci/yaml/) is the model case, every field (`stages`,
`rules`, `needs`, `artifacts`, ...) with its type and meaning. Some platforms keep this on
one page, others scatter it across several. When a seed page doesn't contain everything on
its own, the service follows a small number of additional same-site links, selected by an
LLM call rather than a fixed keyword/URL-pattern heuristic.

Tested against five real platforms (GitLab CI, TeamCity, GitHub Actions, Azure DevOps,
Bamboo). None besides GitLab reach full concept coverage at a small `max_pages`, Azure
DevOps's schema is split across dozens of separate per-keyword pages rather than
consolidated, so a single seed-page link-selection pass doesn't reach all of it. Following
links found on the *followed* pages too, not just the seed, would likely close most of this
gap; not implemented yet.

## Architecture

`retrieval` runs as its own service, separate from the shared `ai-layer` service that other
agents use. It needs a real headless browser (Playwright/Chromium) to render JS-heavy
documentation sites correctly; `ai-layer` and the other agents don't, so that dependency
isn't bundled into their container.

For link-selection, it calls `ai-layer`'s `/chat` endpoint over HTTP rather than importing
the router in-process, since the two now run in separate containers with no shared
filesystem. `AI_LAYER_URL` (default `http://ai-layer:8000`, matching the Docker Compose
service name) controls where that call goes.

A single `AsyncWebCrawler` is opened once per `fetch_documentation()` call and reused across
the seed page and every follow-up page, rather than launching a fresh headless Chromium
process per page.

## API

`POST /fetch`

```json
{ "url": "https://docs.gitlab.com/ci/", "max_pages": 5 }
```

`url` must be a valid URL (rejected with 422 otherwise). `max_pages` is bounded to 1-20,
each additional page is a full sequential browser fetch plus, once every `max_pages - 1`
links, another LLM call.

Returns:

```json
{
  "seed_url": "https://docs.gitlab.com/ci/",
  "pages": [
    { "url": "...", "success": true, "status_code": 200, "markdown": "...", "links": ["..."] }
  ]
}
```

The seed URL is always fetched first. If it succeeds and `max_pages > 1`, its content and
discovered links are sent to `ai-layer`'s `/chat`, which returns which of those links (if
any) are worth fetching next; those pages are fetched and appended to `pages`.

`GET /health` returns `{"status": "ok"}`.

## Content filtering

Cleanup is layered, each stage catches what the one before it structurally can't. Matches
the community-standard pattern for Crawl4AI (prune structurally first, refine what's left
second, rather than re-crawling), not something invented here.

1. **`PruningContentFilter`** (structural, per-page, inside `fetch_page`). Crawl4AI's raw
   HTML-to-markdown conversion (`raw_markdown`) keeps the entire page, including navigation,
   confirmed on a real Azure DevOps schema page: 800+ lines of cookie banners and mega-menus
   before any real content starts. `fit_markdown` strips that, but its defaults also strip
   real content, in two different ways depending on the site, both confirmed against real
   pages from three platforms: code-block-heavy pages (GitLab, GitHub Actions) lose syntax to
   the density/tag-weight score unless `pre`/`code`/`table` are explicitly preserved
   (`preserve_tags`); prose-style pages with short definitions (Azure DevOps, ~9 words per
   keyword entry) lose content to the word-count floor unless it's lowered
   (`min_word_threshold`, default 20 in the library, way too high for this content; 5
   recovers it). `fetch_page()` reads `fit_markdown` and falls back to `raw_markdown` only if
   the filter stripped a page down to nothing.
2. **`clean_page_content()`** (semantic, per-page, via `ai-layer`'s shared `/chat`, chunked at
   paragraph boundaries for pages too large for one call). `PruningContentFilter` is purely
   structural, text/link density and tag weight, with no understanding of meaning, confirmed
   to let through exact, well-formed cookie-consent paragraphs on a Confluence-hosted site
   since well-written boilerplate prose scores just like real content structurally. An LLM
   pass, instructed to remove chrome and return everything else byte-for-byte unchanged, not
   summarized or paraphrased, catches what the heuristic can't. `LLMContentFilter` (Crawl4AI's
   own built-in option for this) was deliberately not used, it needs its own separate
   `provider`/`api_token`, bypassing `ai-layer`'s shared multi-provider router entirely.
3. **`dedupe_pages()`** (deterministic, across all fetched pages, after cleanup). A cheap,
   free safety net for the specific failure mode observed, the same long paragraph (a
   cookie notice) repeated verbatim across every page of the same site. Drops a paragraph
   (over 40 chars) if it already appeared on an earlier page; short repeats (headings, single
   lines) are left alone since those are more likely legitimate than chrome.

Verified end-to-end after all three layers: Bamboo went from 0 cookie-notice-free pages
(the pre-fix baseline had "cookie" appearing repeatedly) to zero mentions at all, while real
YAML code blocks (`plan:`, `stages:`, `tasks:`, `artifacts:`) survived byte-for-byte. Across
all five test platforms after the full pipeline, four had zero navigation-boilerplate markers
left at all, the fifth (TeamCity) had one, a legitimate technical mention (their API's actual
`TCSESSIONID` cookie), not leftover chrome.

## Local development

```
python -m venv venv
venv\Scripts\activate
pip install -r requirements.txt
crawl4ai-setup
uvicorn main:app --reload --port 8010
```

Two environment variables keep Playwright's browser binary and crawl4ai's own cache/db
inside this folder instead of the user profile. Note the base-directory variable name has an
extra underscore (`CRAWL4_AI`, not `CRAWL4AI`), confirmed against the library's actual source
since published docs/blog posts get this wrong; the wrong name is silently ignored, no error,
it just writes to the user's home directory as if unset:

```
PLAYWRIGHT_BROWSERS_PATH = <repo>\ai\retrieval\.playwright-browsers
CRAWL4_AI_BASE_DIRECTORY = <repo>\ai\retrieval
```

(crawl4ai appends `.crawl4ai` to that path itself, don't include it in the env var value.)

`venv/`, `.playwright-browsers/`, and `.crawl4ai/` are gitignored at the repo root.

## Docker

Built from `python:3.12-slim`, not Microsoft's Playwright base image, that image
preinstalls Chromium, Firefox, and WebKit (~2GB) when only Chromium is ever used here.
`crawl4ai-setup` installs Chromium (via `patchright`) and its OS-level dependencies itself.
This brought the image from 5.59GB down to 2.79GB.

Runs via `docker-compose.yml` in the parent `ai/` directory as the `retrieval` service,
internal-only (`expose`, not `ports`), reachable by other services on the Docker network
but not from the host browser.

Runs as a non-root user (the container user/home directory is created and `HOME` is pointed
at it before `crawl4ai-setup` runs, so the browser cache and crawl4ai's state land somewhere
that user can actually read), with `cap_drop: ALL` and `no-new-privileges` (safe since
crawl4ai already hardcodes `--no-sandbox` into every Chromium launch, confirmed in its
source, so no elevated capabilities are needed either way), `init: true` to reap zombie
Chromium/zygote processes, and a `HEALTHCHECK`. All of the above was build-verified, not just
configured, an actual container was built and run with each setting to confirm Chromium
still launches and `/fetch` still works before trusting it.

## Design decisions

**Crawl4AI** (Apache 2.0) over two alternatives:

- **trafilatura**, the original candidate for this issue, does a plain HTTP fetch plus
  static HTML parsing. It handles static doc sites fine but returns a near-empty page on
  JS-rendered SPA docs (JetBrains TeamCity's docs: 72 characters of markdown, just a page
  footer). Its `focused_crawler()` is also not AI-based, it's a heuristic URL filter that
  prioritizes navigation-looking pages, not semantic relevance.
- **Firecrawl** handles JS and page selection well, but its self-hostable core is AGPL-3.0
  (only the SDKs are MIT), the same license category already ruled out for other tools on
  this project.
- **GcrawlAI**, a newer entrant, was checked and ruled out: 6 months old, 24 stars, small
  team, and its architecture (Postgres, Redis, Celery, a separate frontend) means adopting it
  would mean standing up a distributed app to replace what's currently one library call.
- **Crawl4AI** drives a real headless browser (Playwright), so it renders JS-heavy docs
  correctly. On the same TeamCity page above: 72 characters with default settings, 7,600+
  once given `wait_until="networkidle"` plus a settle delay. Default settings alone are not
  sufficient for a JS-heavy site.

Link-selection uses a live LLM call (via `ai-layer`) rather than a keyword/URL-pattern
scorer, since the actual signal ("is this a syntax reference page") is semantic, not
lexical. Any URL the LLM returns that wasn't in the original candidate list is dropped and
logged, LLMs occasionally invent plausible-looking URLs that were never on the page.
`select_relevant_links()` also logs when the LLM's response doesn't parse as JSON, so that
failure mode isn't silently indistinguishable from "the LLM legitimately found nothing worth
following."

Crawl4AI ships its own deep-crawling primitives (`BestFirstCrawlingStrategy`,
`KeywordRelevanceScorer`, `FilterChain`) that would give real bounded multi-hop crawling with
dedup, which the current single-hop-from-the-seed implementation doesn't have. Worth
layering underneath the existing LLM selection (mechanical `FilterChain` narrows the
candidates, the LLM call judges what's left) rather than replacing it; not done yet.

## Testing

`pytest` runs 20 unit tests (`tests/test_retrieval.py`, `tests/test_main.py`), all mocked, no
network, browser, or LLM calls, verifying the module's own control flow: link filtering, JSON
parsing of the LLM's response, multi-page assembly, request validation, the content-cleanup
chunking/fallback behavior, cross-page deduplication, and the FastAPI endpoint's request
handling.

One integration test (`tests/test_js_rendering.py`, marked `integration`, excluded from the
default run, run explicitly with `pytest -m integration`) launches a real browser against a
local static-HTML fixture that injects content via a delayed script, mimicking a JS-rendered
SPA. This exists because the wait-config behavior is the entire reason Crawl4AI was chosen
over a plain-HTTP tool, and until this test existed nothing in the automated suite verified
it at all, it was checked by hand, once, against a live third-party site.

Verified separately, by hand, end-to-end against real sites, both on the host and through the
full `docker compose up` stack, across all five platforms listed above, including the
non-root/hardened container.

## Known limitations

- **PDF documentation is not yet handled.** The issue asks for both web pages and PDF
  documents; this module only implements the web-page path. `pdfplumber` is the candidate
  from earlier research for the PDF path, not yet built.
- **Not yet called by anything.** The Orchestrator's PSM stage currently accepts a plain
  text platform description, not a URL, so nothing in the pipeline invokes this service yet.
- **No structured output normalization.** The issue AC asks for "clean text output in a
  consistent structured format"; each page currently returns markdown as-is, with no
  normalization pass across pages.
- **Single-hop link following.** Only links found on the seed page are considered, links
  found on a followed page aren't. See the deep-crawling note above.
