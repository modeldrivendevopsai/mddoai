"""integration_runner: the stage-based pipeline state machine (advance
stages, track review/approval). Named "integration", not "pipeline",
matching ai/ui-host/CLAUDE.md's own naming rule — "pipeline" is already
reserved for MDDOAI's separate, not-yet-built "Generate a CI/CD pipeline"
mode. Runs inside orchestrator's container/process; has no knowledge of
HTTP, chat, or LLM tool-calling, that lives in orchestrator/.
"""
