"""psm_agent: compares serialized platform documentation against MDDOAI's PSM
(Platform-Specific Model) metamodels and reports missing/outdated parts.

Not wired into orchestrator.py's live pipeline yet, kept cleanly importable for
future integration, same as pim_agent.
"""

from .comparison import DEFAULT_PSM_METAMODEL_PATH, Suggestion, compare

__all__ = ["DEFAULT_PSM_METAMODEL_PATH", "Suggestion", "compare"]
