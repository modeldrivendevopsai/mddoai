# Build context is set to ai/ (not ./serialization_agent) in
# docker-compose.yml, so this can reach the sibling clients/ package (real
# dependency: serialization.py calls clients/ai_layer_client.py and
# clients/pim_agent_client.py). Plain Python for now, same reasoning as
# pim_agent/Dockerfile and psm_agent/Dockerfile.
FROM python:3.12-slim
WORKDIR /app
COPY serialization_agent/requirements.txt ./serialization_agent/requirements.txt
RUN pip install --no-cache-dir -r serialization_agent/requirements.txt
COPY serialization_agent/ ./serialization_agent/
COPY clients/ ./clients/
WORKDIR /app/serialization_agent
ENV PYTHONPATH=/app/serialization_agent:/app
EXPOSE 8060
HEALTHCHECK --interval=30s --timeout=10s --start-period=10s --retries=3 \
    CMD python -c "import urllib.request; urllib.request.urlopen('http://localhost:8060/health', timeout=5)" || exit 1
CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8060"]
