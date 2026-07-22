import logging
import os

from fastapi import FastAPI, HTTPException
from pydantic import BaseModel, Field, HttpUrl

from retrieval import (
    DEFAULT_MAX_DEPTH,
    DEFAULT_MAX_PAGES,
    MAX_DEPTH_LIMIT,
    MAX_PAGES_LIMIT,
    FetchResult,
    Page,
    fetch_documentation,
    fetch_single_page,
)

# retrieval.py only calls logger.info/.warning; basicConfig here is what
# actually makes those calls visible. LOG_LEVEL is operator-tunable.
logging.basicConfig(
    level=os.environ.get("LOG_LEVEL", "INFO").upper(),
    format="%(asctime)s %(levelname)s %(name)s: %(message)s",
)
logger = logging.getLogger(__name__)

app = FastAPI(title="MDDOAI Retrieval")

MAX_HINT_LENGTH = int(os.environ.get("MAX_HINT_LENGTH", "500"))


class FetchRequest(BaseModel):
    url: HttpUrl
    max_pages: int = Field(default=DEFAULT_MAX_PAGES, ge=1, le=MAX_PAGES_LIMIT)
    max_depth: int = Field(default=DEFAULT_MAX_DEPTH, ge=1, le=MAX_DEPTH_LIMIT)
    # Crawl4AI's page cache has no TTL, so caching is on by default for speed;
    # this is the escape hatch for a caller that knows a page changed.
    force_refresh: bool = False
    # Retry lever: free text folded into both the statistical pass and the LLM
    # ranking call, for a caller that noticed a gap and wants the next attempt
    # steered toward it.
    hint: str | None = Field(default=None, max_length=MAX_HINT_LENGTH)
    # The other retry lever: rule out a specific URL already known to be wrong.
    exclude_urls: list[str] | None = None


class FetchPageRequest(BaseModel):
    url: HttpUrl
    force_refresh: bool = False


def _fail_with_500(context: str, url, e: Exception) -> HTTPException:
    """Logs the real exception server-side (exc_info captures the full traceback)
    but never echoes internal exception text back to the HTTP caller."""
    logger.error("%s failed url=%s: %s", context, url, e, exc_info=True)
    return HTTPException(status_code=500, detail=f"{context} failed, see server logs for details")


@app.get("/health")
def health():
    return {"status": "ok"}


@app.post("/fetch", response_model=FetchResult)
async def fetch_endpoint(request: FetchRequest) -> FetchResult:
    logger.info(
        "POST /fetch url=%s max_pages=%d max_depth=%d force_refresh=%s hint=%r exclude_urls=%s",
        request.url, request.max_pages, request.max_depth, request.force_refresh, request.hint,
        request.exclude_urls,
    )
    try:
        result = await fetch_documentation(
            str(request.url),
            max_pages=request.max_pages,
            force_refresh=request.force_refresh,
            hint=request.hint,
            max_depth=request.max_depth,
            exclude_urls=request.exclude_urls,
        )
    except Exception as e:
        raise _fail_with_500("POST /fetch", request.url, e)
    logger.info(
        "POST /fetch done url=%s pages=%d confidence=%.2f pending_links=%d",
        request.url, len(result["pages"]), result["meta"]["confidence"], len(result["meta"]["pending_links"]),
    )
    return result


@app.post("/fetch/page", response_model=Page)
async def fetch_page_endpoint(request: FetchPageRequest) -> Page:
    """Fetches and cleans exactly one URL, no crawling. The targeted half of the
    retry story: a caller who already knows a specific page is missing can pull
    it in directly rather than re-running a full crawl."""
    logger.info("POST /fetch/page url=%s force_refresh=%s", request.url, request.force_refresh)
    try:
        page = await fetch_single_page(str(request.url), force_refresh=request.force_refresh)
    except Exception as e:
        raise _fail_with_500("POST /fetch/page", request.url, e)
    logger.info("POST /fetch/page done url=%s success=%s", request.url, page["success"])
    return page
