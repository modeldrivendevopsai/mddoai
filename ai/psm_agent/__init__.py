"""psm_agent: compares serialized platform documentation against MDDOAI's PSM
(Platform-Specific Model) metamodels and reports missing/outdated parts.

Own top-level service (own container, own Dockerfile) — same reasoning as
pim_agent's own promotion to a service (see ai/pim_agent/__init__.py):
MDDOAI_Agentic_Architecture.drawio (origin/draw.io-diagrams branch)'s Phase
1 plan has the same Graph-RAG "RAG Agent" splitting the real model and
feeding PSM Agent directly, a materially heavier job than today's
single-file .ecore text comparison. Not wired into the live pipeline's psm
stage yet, kept cleanly callable via POST /compare for future integration.
"""

from .comparison import DEFAULT_PSM_METAMODEL_PATH, Suggestion, compare

__all__ = ["DEFAULT_PSM_METAMODEL_PATH", "Suggestion", "compare"]
