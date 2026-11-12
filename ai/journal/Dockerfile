# Build context is set to ai/ (not ./journal) in docker-compose.yml, so this
# can reach the shared generation_toolkit/ package: only its required_env helper
# is used, to read the folder settings.
FROM python:3.12-slim
WORKDIR /app
COPY journal/requirements.txt ./journal/requirements.txt
RUN pip install --no-cache-dir -r journal/requirements.txt
COPY journal/ ./journal/
COPY generation_toolkit/ ./generation_toolkit/
WORKDIR /app/journal
ENV PYTHONPATH=/app/journal:/app
EXPOSE 8120
HEALTHCHECK --interval=30s --timeout=10s --start-period=10s --retries=3 \
    CMD python -c "import urllib.request; urllib.request.urlopen('http://localhost:8120/health', timeout=5)" || exit 1
CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8120"]
