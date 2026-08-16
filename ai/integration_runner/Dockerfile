# Build context is set to ai/ (not ./integration_runner) in
# docker-compose.yml, so this can reach its sibling clients/ package (HTTP
# clients for ai-layer/retrieval/pim_agent/serialization_agent, every real
# capability integration_runner's own stages call out for). Together with
# integration_runner/ itself (the pipeline state machine, run history,
# stage agents, own REST API) they're one deployed container/process, two
# Python packages, not two services.
FROM python:3.12-slim
WORKDIR /app
COPY integration_runner/requirements.txt ./integration_runner/requirements.txt
RUN pip install --no-cache-dir -r integration_runner/requirements.txt
COPY integration_runner/ ./integration_runner/
COPY clients/ ./clients/
WORKDIR /app/integration_runner
ENV PYTHONPATH=/app/integration_runner:/app
EXPOSE 8050
CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8050"]
