# -noble (glibc), not -alpine: Alpine's python3 has a broken pyexpat that breaks pip.
FROM eclipse-temurin:24-jdk-noble
WORKDIR /app
RUN apt-get update && apt-get install -y --no-install-recommends python3 python3-pip \
    && rm -rf /var/lib/apt/lists/*
# The build context is ai/, so the shared clients/ package (the journal's client) can be copied in.
COPY validator_agent/requirements.txt /app/requirements.txt
RUN pip install --no-cache-dir --break-system-packages -r /app/requirements.txt
COPY validator_agent/ /app/
COPY clients/ /app/clients/
EXPOSE 8020
HEALTHCHECK --interval=30s --timeout=10s --start-period=10s --retries=3 \
    CMD python3 -c "import urllib.request; urllib.request.urlopen('http://localhost:8020/health', timeout=5)" || exit 1
CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8020"]
