"""pim_agent: knowledge agents that ground the future PIM Agent's work.

Currently contains the Reference Knowledge Agent (reference_knowledge.py). Future
sibling agents (a PIM comparison agent, a PIM extension agent) call into this
package through the public interface re-exported below, without depending on
any agent's internals.
"""

from .reference_knowledge import GroundingExample, ground, PIM_CONCEPTS, concept_for_entry_title

__all__ = ["GroundingExample", "ground", "PIM_CONCEPTS", "concept_for_entry_title"]
