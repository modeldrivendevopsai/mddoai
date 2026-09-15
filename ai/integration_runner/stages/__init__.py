"""MDDOAI's seven stage agents, one folder per stage in this package, plus
the two lookups IntegrationRun/orchestrator read from: STAGE_DESCRIPTIONS
(the system prompt's stage list) and stage_agents (what run_stage() calls).

Each stage's folder holds agent.py (the pure, run-agnostic function
dispatched from stage_agents below) and, only once a stage actually has
one, actions.py (extra real, run-aware chat tools scoped to that stage,
docs/actions.py is the one that exists today). Adding or replacing a
stage's agent means writing agent.py in its own folder (matching
stages/docs/agent.py's own history, it replaced a placeholder the same
way stages/pim/agent.py etc. will, each on its own schedule) and adding one
entry to each mapping below. A stage growing its own extra chat tool later
means adding actions.py as a new sibling file in that same folder, nothing
elsewhere in integration_runner changes: pipeline.py only ever reads
stages.stage_agents[stage] and stages.STAGE_DESCRIPTIONS, never a specific
stage's own module, and routes/ only ever imports the one stage folder it
needs.

pim returns the project's own real, fixed PIM metamodel, validated for real
against validator-agent (see stages/_validation.py and its own agent.py),
still a placeholder in the sense that no real PIM *instance* extraction
pipeline exists yet to read a run's own SWArch input. psm, atl, and acceleo
are each a thin proxy to their own real, separate service (psm_agent,
atl_agent, acceleo_agent), generating real output refined against real
validator-agent feedback (see each of their own agent.py's own docstring).
generation actually runs that real ATL/Acceleo output for real (via
execution_agent) against a real PIM model instance, producing the real
generated CI/CD configuration, no LLM call of its own, and (being the
last real caller) its own move off of stages/_shared.py's constraints_note()
is what made that module fully dead code, since deleted; a stage with a
real, config-driven prompt (psm, atl, acceleo) instead forwards a
correction as a plain constraints list straight to its own real service's
/generate call, which folds it into that service's own real prompt
alongside any already-learned constraints (see each service's own
generation.py).
"""
from dataclasses import dataclass

from integration_runner.stages import acceleo, atl, docs, generation, pim, psm, serialization


@dataclass(frozen=True)
class StageInfo:
    """One stage's real, plain-language shape: what it reads, what it
    produces, and whether that output is genuinely derived from its input
    today or is still fixed placeholder content regardless of what it's
    given. This is metadata, not behavior. A stage's own agent.py is still
    the only place its real logic lives, this dict just describes it
    honestly for anything that needs to explain a stage to a human or an
    LLM, so that explanation comes from one real place instead of being
    hand-written again per consumer (a UI panel, a system prompt) and
    drifting from what the code actually does."""

    description: str
    input: str
    output: str
    # True once this stage's output is genuinely derived from the real
    # input it's given. False for a stage that still ignores its input and
    # always returns the same fixed content, see each entry's own
    # description for exactly what "real" still means for that stage
    # (e.g. generation makes a real call over real prior-stage output, but
    # with a placeholder prompt and no real CI/CD config yet).
    real: bool


# One entry per stage. Exposed over HTTP via this service's own GET /stages
# (routes/core.py) for orchestrator to build its narration/tool-routing
# system prompt from, and for the UI to explain each stage to a human,
# so neither needs a manual edit when a stage's real shape changes, only
# this dict does, alongside pipeline.STAGES and stage_agents below.
STAGE_DETAILS: dict[str, StageInfo] = {
    "docs": StageInfo(
        description="fetches the platform's real documentation (a real call to retrieval).",
        input="a seed URL for the platform's own documentation",
        output="the crawled documentation, as raw markdown",
        real=True,
    ),
    "serialization": StageInfo(
        description="restructures the fetched documentation into a labeled, PIM-concept-tagged markdown artifact.",
        input="the docs stage's raw documentation markdown",
        output="the same documentation, restructured into sections labeled against MDDOAI's real PIM concepts, plus an Unrecognized section for anything that didn't match one",
        real=True,
    ),
    "pim": StageInfo(
        description="MDDOAI's own real, fixed PIM (Platform-Independent Model) Ecore metamodel, validated for real.",
        input="ignored today, no real PIM instance extraction exists yet to read the serialization stage's output",
        output="the project's own real, git-committed PIM Ecore metamodel, the same one every downstream stage treats as fixed - not derived from any real input, since no real per-run PIM extraction exists yet",
        real=False,
    ),
    "psm": StageInfo(
        description="a PSM (Platform-Specific Model) Ecore metamodel for the platform, generates a new one, or checks an existing one for drift, depending on the platform.",
        input="the serialization stage's labeled documentation",
        output="a new PSM Ecore metamodel for a platform with none yet, or a drift report against an existing one",
        real=True,
    ),
    "atl": StageInfo(
        description="the ATL transformation rules needed to build that PSM, generated fresh from this run's own real PIM and PSM artifacts.",
        input="the pim stage's own PIM artifact and the psm stage's own target platform PSM metamodel",
        output="a new ATL model-to-model transformation from PIM to the target platform's PSM, refined against real validator-agent feedback",
        real=True,
    ),
    "acceleo": StageInfo(
        description="the Acceleo code-generation template for that PSM, generated fresh from this run's own real PSM artifact and documentation.",
        input="the psm stage's own target platform PSM metamodel and the serialization stage's labeled documentation",
        output="a new Acceleo template that generates the target platform's own real CI/CD YAML from a PSM model instance, refined against real validator-agent feedback",
        real=True,
    ),
    "generation": StageInfo(
        description="actually runs this run's own real ATL transformation and Acceleo template to produce the real generated CI/CD configuration.",
        input="the atl and acceleo stages' own real, validated (compiled) source, the psm stage's own target platform PSM metamodel, and a real sample PIM model instance",
        output="the real generated CI/CD configuration file(s) this run's own ATL/Acceleo actually produce when run for real, not a summary of them",
        real=True,
    ),
}

# Derived from STAGE_DETAILS, not hand-duplicated: every existing consumer
# (orchestrator's own system prompt, tests) only ever needs the plain
# narration string, so this stays the same plain dict[str, str] shape it
# always was.
STAGE_DESCRIPTIONS: dict[str, str] = {stage: info.description for stage, info in STAGE_DETAILS.items()}

stage_agents = {
    "docs": docs.agent.docs_stage,
    "serialization": serialization.agent.serialization_stage,
    "pim": pim.agent.pim_stage,
    "psm": psm.agent.psm_stage,
    "atl": atl.agent.atl_stage,
    "acceleo": acceleo.agent.acceleo_stage,
    "generation": generation.agent.gen_stage,
}
