"""MDDOAI's seven stage agents, one folder per stage in this package, plus
the two lookups IntegrationRun/orchestrator read from: STAGE_DESCRIPTIONS
(the system prompt's stage list) and stage_agents (what run_stage() calls).

Each stage's folder holds agent.py (the pure, run-agnostic function
dispatched from stage_agents below) and, only once a stage actually has
one, actions.py (extra real, run-aware chat tools scoped to that stage —
docs/actions.py is the one that exists today). Adding or replacing a
stage's agent means writing agent.py in its own folder (matching
stages/docs/agent.py's own history — it replaced a placeholder the same
way stages/pim/agent.py etc. will, each on its own schedule) and adding one
line to each dict below. A stage growing its own extra chat tool later
means adding actions.py as a new sibling file in that same folder, nothing
elsewhere in integration_runner changes: pipeline.py only ever reads
stages.stage_agents[stage] and stages.STAGE_DESCRIPTIONS, never a specific
stage's own module, and routes/ only ever imports the one stage folder it
needs.

_shared.py holds constraints_note(), which stages/generation/agent.py still
uses — the last remaining LLM-prompt placeholder agent; pim/psm/atl/acceleo
switched to fixed mock content validated for real against validator-agent
instead (see stages/_validation.py and each of their own agent.py).
"""
from integration_runner.stages import acceleo, atl, docs, generation, pim, psm, serialization

# One line per stage. Exposed over HTTP via this service's own GET /stages
# (routes/core.py) for orchestrator to build its narration/tool-routing
# system prompt from, so that prompt never needs a manual edit when a stage
# is added, only this dict does, alongside pipeline.STAGES and stage_agents
# below.
STAGE_DESCRIPTIONS: dict[str, str] = {
    "docs": "fetches the platform's real documentation (a real call to retrieval).",
    "serialization": "restructures the fetched documentation into a labeled, PIM-concept-tagged markdown artifact.",
    "pim": "a PIM (Platform-Independent Model) Ecore description of the platform (mock content, validated for real).",
    "psm": "a PSM (Platform-Specific Model) Ecore description of the platform (mock content, validated for real).",
    "atl": "the ATL transformation rules needed to build that PSM (mock content, validated for real).",
    "acceleo": "the Acceleo code-generation template for that ATL (mock content, validated for real).",
    "generation": "a final summary tying all prior stages together.",
}

stage_agents = {
    "docs": docs.agent.docs_stage,
    "serialization": serialization.agent.serialization_stage,
    "pim": pim.agent.pim_stage,
    "psm": psm.agent.psm_stage,
    "atl": atl.agent.atl_stage,
    "acceleo": acceleo.agent.acceleo_stage,
    "generation": generation.agent.gen_stage,
}
