# Build context is set to ai/ (not ./acceleo_agent) in docker-compose.yml, so this
# can reach the sibling clients/ and generation_toolkit/ packages (real
# dependencies: generation.py calls clients/validator_agent_client.py and
# generation_toolkit/generation_agent.py, neither is a service-boundary
# import from orchestrator). Plain Python for now, same reasoning as
# pim_agent/Dockerfile: today's real capability doesn't touch Java directly,
# it only reads one real .mtl file through a read-only bind mount (see
# ai/docker-compose.yml and prompt_paths.py's own REFERENCE_EXAMPLE_PATH).
FROM python:3.12-slim
WORKDIR /app
COPY acceleo_agent/requirements.txt ./acceleo_agent/requirements.txt
RUN pip install --no-cache-dir -r acceleo_agent/requirements.txt
COPY acceleo_agent/ ./acceleo_agent/
COPY clients/ ./clients/
COPY generation_toolkit/ ./generation_toolkit/
WORKDIR /app/acceleo_agent
ENV PYTHONPATH=/app/acceleo_agent:/app
EXPOSE 8080
HEALTHCHECK --interval=30s --timeout=10s --start-period=10s --retries=3 \
    CMD python -c "import urllib.request; urllib.request.urlopen('http://localhost:8080/health', timeout=5)" || exit 1
CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8080"]
