# Build context is set to ai/ (not ./psm_agent) in docker-compose.yml, so this
# can reach the sibling clients/ package (real dependency: comparison.py calls
# clients/ai_layer_client.py, not a service-boundary import from orchestrator).
# Plain Python for now, same reasoning as pim_agent/Dockerfile (see its own
# comment and __init__.py): today's real capability doesn't touch Java yet,
# that migration is separate future work.
FROM python:3.12-slim
WORKDIR /app
COPY psm_agent/requirements.txt ./psm_agent/requirements.txt
RUN pip install --no-cache-dir -r psm_agent/requirements.txt
COPY psm_agent/ ./psm_agent/
COPY clients/ ./clients/
WORKDIR /app/psm_agent
ENV PYTHONPATH=/app/psm_agent:/app
EXPOSE 8040
HEALTHCHECK --interval=30s --timeout=10s --start-period=10s --retries=3 \
    CMD python -c "import urllib.request; urllib.request.urlopen('http://localhost:8040/health', timeout=5)" || exit 1
CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8040"]
