FROM python:3.12-slim
WORKDIR /app
COPY ai-layer/requirements.txt ai-layer/requirements.txt
COPY orchestrator/requirements.txt orchestrator/requirements.txt
RUN pip install --no-cache-dir -r ai-layer/requirements.txt -r orchestrator/requirements.txt
COPY ai-layer/ ai-layer/
COPY orchestrator/ orchestrator/
WORKDIR /app/ai-layer
EXPOSE 8000
CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8000"]
