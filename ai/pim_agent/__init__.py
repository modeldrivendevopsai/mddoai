"""pim_agent: knowledge agents that ground the future PIM Agent's work.

Own top-level service (own container, own Dockerfile) — a concrete, stated
future need, not speculative: MDDOAI_Agentic_Architecture.drawio (origin/
draw.io-diagrams branch)'s Phase 1 plan already has a Graph-RAG-backed "RAG
Agent" splitting the real metamodel/model and feeding PIM Agent/PSM Agent
directly, a materially heavier job than today's plain keyword-matched
ground(). Given MDDOAI's real metamodels are Java/EMF (see the repo root's
main/, meta_models/), that likely means the same real Java/EMF/Gradle
toolchain ai/integration_agent already needs for the same reason, matching
its established shape, though the diagram itself specifies the capability,
not the runtime, so that specific inference is engineering judgment, not a
literal quote. Today's actual capability (ground()/PIM_CONCEPTS) is still
plain Python; that migration is separate future work, not built here
speculatively.

Currently contains the Reference Knowledge Agent (reference_knowledge.py).
Future sibling agents (a PIM comparison agent, a PIM extension agent) call
into this package through the public interface re-exported below, without
depending on any agent's internals.
"""

from .reference_knowledge import GroundingExample, ground, PIM_CONCEPTS, concept_for_entry_title

__all__ = ["GroundingExample", "ground", "PIM_CONCEPTS", "concept_for_entry_title"]
