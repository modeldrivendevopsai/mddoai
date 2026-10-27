# Build context is ai/ (not ./swarch_agent) in docker-compose.yml, so this can
# reach its sibling dependency: clients/ (the HTTP clients for validator-agent
# and execution-agent). Other services that call a sibling use the same
# multi-folder pattern.
#
# Plain Python: the transformation itself runs in execution-agent's JVM, so
# this image needs no Java of its own.
FROM python:3.12-slim
WORKDIR /app
COPY swarch_agent/requirements.txt ./swarch_agent/requirements.txt
RUN pip install --no-cache-dir -r swarch_agent/requirements.txt
COPY swarch_agent/ ./swarch_agent/
COPY clients/ ./clients/
WORKDIR /app/swarch_agent
ENV PYTHONPATH=/app/swarch_agent:/app
EXPOSE 8110
HEALTHCHECK --interval=30s --timeout=10s --start-period=10s --retries=3 \
    CMD python -c "import urllib.request; urllib.request.urlopen('http://localhost:8110/health', timeout=5)" || exit 1
CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8110"]
