# Plain slim base, not Microsoft's Playwright image: that image preinstalls all
# three browser engines (Chromium, Firefox, WebKit, ~2GB) and we only use Chromium.
# crawl4ai-setup installs its own Chromium (via patchright) plus the OS-level
# shared libraries it needs, --with-deps handles the apt-get itself.
FROM python:3.12-slim
WORKDIR /app
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

# Create the runtime user and point HOME at its directory before crawl4ai-setup
# runs, so the browser cache (~/.cache/ms-playwright) and crawl4ai's own state
# (~/.crawl4ai) land somewhere the non-root user can actually read, rather than
# under /root (mode 700). crawl4ai-setup itself still runs as root here, it
# needs apt-get for the OS-level Chromium dependencies.
RUN groupadd -r retrieval && useradd -r -m -g retrieval -d /home/retrieval retrieval
ENV HOME=/home/retrieval
RUN crawl4ai-setup
RUN chown -R retrieval:retrieval /home/retrieval

COPY . .
RUN chown -R retrieval:retrieval /app
USER retrieval

EXPOSE 8010
HEALTHCHECK --interval=30s --timeout=10s --start-period=10s --retries=3 \
    CMD python -c "import urllib.request; urllib.request.urlopen('http://localhost:8010/health', timeout=5)" || exit 1
CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8010"]
