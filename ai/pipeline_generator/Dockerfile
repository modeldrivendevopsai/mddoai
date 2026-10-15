# Build context is ai/ (not ./pipeline_generator) in docker-compose.yml, so
# this can reach its one real sibling dependency: clients/ (the HTTP client
# for validator-agent). Other services that call a sibling use the same
# multi-folder pattern.
#
# A JRE as the base (the engine CLI is Java), with Python added on top.
# -noble (glibc), not -alpine: Alpine's python3 has a broken pyexpat that
# breaks pip. JRE not JDK: nothing here compiles.
FROM eclipse-temurin:24-jre-noble
WORKDIR /app
RUN apt-get update && apt-get install -y --no-install-recommends python3 python3-pip \
    && rm -rf /var/lib/apt/lists/*
COPY pipeline_generator/requirements.txt ./pipeline_generator/requirements.txt
RUN pip install --no-cache-dir --break-system-packages -r pipeline_generator/requirements.txt
COPY pipeline_generator/ ./pipeline_generator/
COPY clients/ ./clients/
WORKDIR /app/pipeline_generator
ENV PYTHONPATH=/app/pipeline_generator:/app
EXPOSE 8100
HEALTHCHECK --interval=30s --timeout=10s --start-period=10s --retries=3 \
    CMD python3 -c "import urllib.request; urllib.request.urlopen('http://localhost:8100/health', timeout=5)" || exit 1
CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8100"]
