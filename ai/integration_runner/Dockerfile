# Build context is set to ai/ (not ./integration_runner) in
# docker-compose.yml, so this can reach the shared clients/ package: the HTTP
# clients for ai-layer, retrieval, pim_agent, serialization_agent, psm_agent and
# the journal, every capability integration_runner's own stages call out for.
# Together with integration_runner/ itself (the pipeline state machine, run
# history, stage agents, own REST API) they're one deployed container/process.
FROM python:3.12-slim
WORKDIR /app
COPY integration_runner/requirements.txt ./integration_runner/requirements.txt
RUN pip install --no-cache-dir -r integration_runner/requirements.txt
COPY integration_runner/ ./integration_runner/
COPY clients/ ./clients/
WORKDIR /app/integration_runner
ENV PYTHONPATH=/app/integration_runner:/app
EXPOSE 8050
HEALTHCHECK --interval=30s --timeout=10s --start-period=10s --retries=3 \
    CMD python -c "import urllib.request; urllib.request.urlopen('http://localhost:8050/health', timeout=5)" || exit 1
CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8050"]
