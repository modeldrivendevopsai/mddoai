# Build context is set to ai/ (not ./orchestrator) in docker-compose.yml,
# so this can reach the shared clients/ package (HTTP clients for ai-layer,
# integration_runner and the journal). Same multi-folder pattern
# psm_agent/Dockerfile already uses.
FROM python:3.12-slim
WORKDIR /app
COPY orchestrator/requirements.txt ./orchestrator/requirements.txt
RUN pip install --no-cache-dir -r orchestrator/requirements.txt
COPY orchestrator/ ./orchestrator/
COPY clients/ ./clients/
WORKDIR /app/orchestrator
ENV PYTHONPATH=/app/orchestrator:/app
EXPOSE 8001
HEALTHCHECK --interval=30s --timeout=10s --start-period=10s --retries=3 \
    CMD python -c "import urllib.request; urllib.request.urlopen('http://localhost:8001/health', timeout=5)" || exit 1
CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8001"]
