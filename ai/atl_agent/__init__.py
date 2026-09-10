"""atl_agent: the real atl pipeline stage's whole capability, POST /generate
(generation.py's generate()) - a new ATL model-to-model transformation from
this run's own real PIM artifact to a target platform's own PSM metamodel,
refined against real validator_agent feedback.

Own top-level service (own container, own Dockerfile), same reasoning as
psm_agent's own promotion to a service: each real pipeline capability gets
independent deployability, not bundled into whichever process happens to
call it.
"""

from .generation import generate

__all__ = ["generate"]
