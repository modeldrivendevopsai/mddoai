# -noble (glibc), not -alpine: Alpine's python3 has a broken pyexpat that breaks pip.
# A JRE, not a JDK: this service only runs the engine's executors with `java`,
# and never compiles Java (validator-agent, which runs javac for its
# Ecore code-generation check, keeps the JDK).
FROM eclipse-temurin:24-jre-noble
WORKDIR /app
RUN apt-get update && apt-get install -y --no-install-recommends python3 python3-pip \
    && rm -rf /var/lib/apt/lists/*
COPY requirements.txt /app/requirements.txt
RUN pip install --no-cache-dir --break-system-packages -r /app/requirements.txt
COPY . /app/
EXPOSE 8090
HEALTHCHECK --interval=30s --timeout=10s --start-period=10s --retries=3 \
    CMD python3 -c "import urllib.request; urllib.request.urlopen('http://localhost:8090/health', timeout=5)" || exit 1
CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8090"]
