"""acceleo_agent: the real acceleo pipeline stage's whole capability, POST
/generate (generation.py's generate()) - a new Acceleo model-to-text
template from this run's own real PSM artifact and platform documentation,
refined against real validator_agent feedback.

Own top-level service (own container, own Dockerfile), same reasoning as
psm_agent's own promotion to a service: each real pipeline capability gets
independent deployability, not bundled into whichever process happens to
call it.
"""

from .generation import generate

__all__ = ["generate"]
