# Build context is set to ai/ (not ./orchestrator) in docker-compose.yml,
# so this can reach its one real sibling dependency: clients/ (HTTP clients
# for ai-layer/retrieval/integration_runner). Same multi-folder pattern
# psm_agent/Dockerfile already uses. Before this split, this image also
# assembled integration_runner/ and serialization_agent/ into the same
# container — both are their own, separately deployed containers now
# (integration_runner/Dockerfile, integration_runner's own container reached
# only over HTTP via clients/integration_runner_client.py), so this shrinking
# to one sibling copy is a real, concrete signal the split reduced coupling,
# not just relocated it.
FROM python:3.12-slim
WORKDIR /app
COPY orchestrator/requirements.txt ./orchestrator/requirements.txt
RUN pip install --no-cache-dir -r orchestrator/requirements.txt
COPY orchestrator/ ./orchestrator/
COPY clients/ ./clients/
WORKDIR /app/orchestrator
ENV PYTHONPATH=/app/orchestrator:/app
EXPOSE 8001
CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8001"]
