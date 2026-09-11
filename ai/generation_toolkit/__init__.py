"""Shared, stage-agnostic building blocks for a "generate, validate, retry"
agent: never deployed standalone, no Dockerfile, no port, only ever imported
in-process by whichever service's own stage agent needs this shape.
Callers use module-qualified access (`from generation_toolkit import
generation_agent`, then `generation_agent.run_with_retry(...)`), matching
`clients/`'s own convention (the other shared, not-itself-a-service folder
under `ai/`).

psm_agent's Generation Agent (see psm_agent/generation.py) is the first real
caller. Extracted now, ahead of a second real consumer, on an explicit,
stated direction (not speculative): pim/atl/acceleo/generation's own stage
agents are still placeholders today, but whichever of them gets a real
implementation next should reuse this rather than rebuilding the same
prompt-assembly + regenerate-loop pattern from scratch. A stage with no real
validator yet just omits `validate_fn` and gets a single-shot call — see
generation_agent.py's own docstring.
"""
