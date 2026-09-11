"""psm_agent: the real psm pipeline stage's whole capability, POST /psm
(psm_flow.py's run()) — routes between generating a brand-new PSM
(Platform-Specific Model) metamodel for a platform that doesn't have one yet
(generation.py's Generation Agent) and comparing platform documentation
against an existing metamodel for missing/outdated parts (comparison.py's
Knowledge Agent, compare(), also still callable standalone via POST
/compare).

Own top-level service (own container, own Dockerfile) — same reasoning as
pim_agent's own promotion to a service (see ai/pim_agent/__init__.py):
MDDOAI_Agentic_Architecture.drawio (origin/draw.io-diagrams branch)'s Phase
1 plan has the same Graph-RAG "RAG Agent" splitting the real model and
feeding PSM Agent directly, a materially heavier job than today's
single-file .ecore text comparison.
"""

from .comparison import DEFAULT_PSM_METAMODEL_PATH, Suggestion, compare

__all__ = ["DEFAULT_PSM_METAMODEL_PATH", "Suggestion", "compare"]
