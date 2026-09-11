# Build context is set to ai/ (not ./integration_runner) in
# docker-compose.yml, so this can reach its sibling clients/ and
# generation_toolkit/ packages (clients/: HTTP clients for ai-layer/
# retrieval/pim_agent/serialization_agent/psm_agent, every real capability
# integration_runner's own stages call out for. generation_toolkit/: only
# its attachments.files path-safety validation, reused by
# stages/_attempts_read.py for the same real path-traversal risk a
# user-suppliable run_id/stage/attempt already has - integration_runner
# does not build prompts, it only reuses this one small, shared check).
# Together with integration_runner/ itself (the pipeline state machine, run
# history, stage agents, own REST API) they're one deployed container/
# process, three Python packages, not three services.
FROM python:3.12-slim
WORKDIR /app
COPY integration_runner/requirements.txt ./integration_runner/requirements.txt
RUN pip install --no-cache-dir -r integration_runner/requirements.txt
COPY integration_runner/ ./integration_runner/
COPY clients/ ./clients/
COPY generation_toolkit/ ./generation_toolkit/
WORKDIR /app/integration_runner
ENV PYTHONPATH=/app/integration_runner:/app
EXPOSE 8050
HEALTHCHECK --interval=30s --timeout=10s --start-period=10s --retries=3 \
    CMD python -c "import urllib.request; urllib.request.urlopen('http://localhost:8050/health', timeout=5)" || exit 1
CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8050"]
