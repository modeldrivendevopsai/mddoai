# Build context is ai/ (not ./pipeline_generator) in docker-compose.yml, so
# this can reach its one real sibling dependency: clients/ (the HTTP clients
# for validator-agent, swarch-agent and execution-agent). Other services that
# call a sibling use the same multi-folder pattern.
#
# Plain Python: the transformations run in execution-agent's JVM, so this
# image needs no Java of its own.
FROM python:3.12-slim
WORKDIR /app
COPY pipeline_generator/requirements.txt ./pipeline_generator/requirements.txt
RUN pip install --no-cache-dir -r pipeline_generator/requirements.txt
COPY pipeline_generator/ ./pipeline_generator/
COPY clients/ ./clients/
WORKDIR /app/pipeline_generator
ENV PYTHONPATH=/app/pipeline_generator:/app
EXPOSE 8100
HEALTHCHECK --interval=30s --timeout=10s --start-period=10s --retries=3 \
    CMD python -c "import urllib.request; urllib.request.urlopen('http://localhost:8100/health', timeout=5)" || exit 1
CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8100"]
