# Plain slim base, not Microsoft's Playwright image: that image preinstalls all
# three browser engines (Chromium, Firefox, WebKit, ~2GB) and we only use Chromium.
# crawl4ai-setup installs its own Chromium (via patchright) plus the OS-level
# shared libraries it needs, --with-deps handles the apt-get itself.
#
# Build context is set to ai/ (not ./retrieval) in docker-compose.yml, so this
# can reach the sibling clients/ package (real dependency: retrieval.py's LLM
# calls go through clients/ai_layer_client.py's async achat() now, not raw
# httpx) — same multi-folder pattern integration_runner/psm_agent/
# serialization_agent already use.
FROM python:3.12-slim
WORKDIR /app
COPY retrieval/requirements.txt ./retrieval/requirements.txt
RUN pip install --no-cache-dir -r retrieval/requirements.txt

# Create the runtime user and point HOME at its directory before crawl4ai-setup
# runs, so the browser cache (~/.cache/ms-playwright) and crawl4ai's own state
# (~/.crawl4ai) land somewhere the non-root user can actually read, rather than
# under /root (mode 700). crawl4ai-setup itself still runs as root here, it
# needs apt-get for the OS-level Chromium dependencies.
RUN groupadd -r retrieval && useradd -r -m -g retrieval -d /home/retrieval retrieval
ENV HOME=/home/retrieval
# Ownership is changed in the same step that downloads the browser: a separate
# RUN chown copies every file it touches into a new layer, which kept a second
# copy of the browser (about 0.7 GB) in the image.
RUN crawl4ai-setup && chown -R retrieval:retrieval /home/retrieval

COPY --chown=retrieval:retrieval retrieval/ ./retrieval/
COPY --chown=retrieval:retrieval clients/ ./clients/
# The two folders were created by root above; their files are already the
# user's, so only the folders themselves change owner (no copy of anything).
RUN chown retrieval:retrieval /app /app/retrieval
USER retrieval

WORKDIR /app/retrieval
ENV PYTHONPATH=/app/retrieval:/app
EXPOSE 8010
HEALTHCHECK --interval=30s --timeout=10s --start-period=10s --retries=3 \
    CMD python -c "import urllib.request; urllib.request.urlopen('http://localhost:8010/health', timeout=5)" || exit 1
CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8010"]
