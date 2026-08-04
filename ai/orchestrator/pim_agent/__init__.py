"""pim_agent: knowledge agents that ground the future PIM Agent's work.

Currently contains the ACICDTrip Knowledge Agent (acicdtrip_knowledge.py). Future
sibling agents (a PIM comparison agent, a PIM extension agent) call into this
package through the public interface re-exported below, without depending on
any agent's internals.
"""

from .acicdtrip_knowledge import GroundingExample, ground

__all__ = ["GroundingExample", "ground"]
