# Plain Python for now: today's real capability (ground()/PIM_CONCEPTS) is
# pure Python, no Java involved. A future migration to a JDK-based shape
# matching integration_agent's (when pim_agent takes on the heavier
# Graph-RAG-fed generation job MDDOAI_Agentic_Architecture.drawio's Phase 1
# plan describes, see __init__.py) is separate future work, not built
# speculatively here.
FROM python:3.12-slim
WORKDIR /app
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt
COPY . .
EXPOSE 8030
HEALTHCHECK --interval=30s --timeout=10s --start-period=10s --retries=3 \
    CMD python -c "import urllib.request; urllib.request.urlopen('http://localhost:8030/health', timeout=5)" || exit 1
CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8030"]
