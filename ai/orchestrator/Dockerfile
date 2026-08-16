# Build context is set to ai/ (not ./orchestrator) in docker-compose.yml,
# so this can reach its one real sibling dependency: clients/ (HTTP clients
# for ai-layer/integration_runner — this service has no import on
# clients/retrieval_client.py, that call lives in integration_runner's own
# docs stage now). Same multi-folder pattern psm_agent/Dockerfile already
# uses.
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
