"""MDDOAI's five placeholder-or-real stage agents (docs/pim/psm/atl/acceleo/
generation), their prompts, and the stage_agents dict Orchestrator.run_stage()
looks them up from.

Split out of orchestrator.py (2026-07-30, an independent architecture review's
recommendation) because this content is volatile in a way the pipeline state
machine isn't: docs_agent is already real (a retrieval crawl); the other five
are explicitly temporary LLM-prompt placeholders standing in for future real
per-stage implementations (an actual ATL engine call, actual PSM generation),
each likely to be replaced on its own schedule with something that looks
nothing like `chat(messages)["content"]`. orchestrator.py's Orchestrator
class, event log, and background-thread mechanics aren't volatile the same
way, they don't need to change when a stage goes real.

This file imports orchestrator (for chat()/fetch_documentation()/fetch_page(),
which stay there as shared HTTP-client infrastructure, not stage-specific
content). orchestrator.py does NOT import this file at module level, that
would be circular (this file needs orchestrator to already be loaded); see
Orchestrator.run_stage()'s own local import for how that's resolved.
"""
import os

import orchestrator
import serialization_agent

# Opt-in only (unset by default, so a real `docker compose up` still runs the
# real crawl): when set, docs_agent() skips retrieval entirely and returns
# canned output instantly. For iterating on the orchestrator UI/mechanics
# (approve/reject/retry/nudge) without needing a real, valid documentation
# URL or waiting on a real 90+ second crawl each time. Set in orchestrator's
# own .env (gitignored, not docker-compose.yml's committed defaults), so it
# never silently affects a real run.
_STUB_DOCS = os.environ.get("ORCHESTRATOR_STUB_DOCS", "").strip().lower() in ("1", "true", "yes")
# retrieval's own AdaptiveCrawler stops early on a poor crawl (min_gain_threshold),
# so a fetch that returns can still have found essentially nothing useful. This is
# the floor below which that's treated as a hard failure, not a low-quality result
# for a human to review, an engineering guess, not measured against a real corpus.
_DOCS_MIN_CONFIDENCE = 0.15

# One line per stage, used to build the Orchestrator's own narration/tool-
# routing system prompt (see pipeline_tools.py) so that prompt never needs a
# manual edit when a stage is added, only this dict does, alongside
# orchestrator.STAGES and stage_agents below.
STAGE_DESCRIPTIONS: dict[str, str] = {
    "docs": "fetches the platform's real documentation (a real call to retrieval).",
    "serialization": "restructures the fetched documentation into a labeled, PIM-concept-tagged markdown artifact.",
    "pim": "a PIM (Platform-Independent Model) description of the platform.",
    "psm": "a PSM (Platform-Specific Model) description of the platform.",
    "atl": "the ATL transformation rules needed to build that PSM.",
    "acceleo": "the Acceleo code-generation template for that ATL.",
    "generation": "a final summary tying all prior stages together.",
}

_STAGE_SYSTEM_PROMPTS = {
    "pim": (
        "You are the MDDOAI PIM (Platform-Independent Model) agent. Given a platform's real "
        "documentation, produce a clear PIM-level description: express the platform's CI/CD "
        "concepts (jobs, stages, triggers, artifacts, agents/runners) in MDDOAI's "
        "platform-independent metamodel terms, without committing to any one platform's "
        "specific syntax yet. Be precise and structured."
    ),
    "psm": (
        "You are the MDDOAI PSM (Platform-Specific Model) agent. Given a PIM-level description "
        "of a CI/CD platform, produce a clear PSM-level description: express the same concepts "
        "(jobs, stages, triggers, artifacts, agents/runners) in MDDOAI's platform-specific "
        "metamodel terms. Be precise and structured."
    ),
    "atl": (
        "You are the MDDOAI ATL transformation agent. Given a PSM-level description, describe "
        "the ATL (ATLAS Transformation Language) transformation rules needed to map the "
        "platform-independent model to this PSM: name the rules, and describe their source "
        "and target patterns and the mapping logic between them."
    ),
    "acceleo": (
        "You are the MDDOAI Acceleo template agent. Given a description of ATL transformation "
        "rules, describe the Acceleo code-generation template needed to turn the transformed "
        "model into real pipeline configuration files: the template's structure, its key "
        "generation blocks, and the output files it targets."
    ),
    "generation": (
        "You are the MDDOAI generation summary agent. Given the PSM description, the ATL "
        "transformation rules, and the Acceleo template plan produced in the prior stages, "
        "produce a final, concise summary of the full pipeline generation plan, from the "
        "original platform input through to the generated CI/CD configuration."
    ),
}


def _constraints_note(context: dict, stage: str) -> str:
    constraints = context.get("constraints", {}).get(stage, [])
    if not constraints:
        return ""
    bullet_list = "\n".join(f"- {c}" for c in constraints)
    return f"\n\nApply these corrections from prior review:\n{bullet_list}"


def docs_agent(context: dict) -> str:
    """The docs stage's normal (non-tool-call) path: calls
    orchestrator.fetch_documentation with whatever context supplies, formats
    the result as the stage's output string, and fails hard if the crawl
    found essentially nothing. An explicit context["hint"] (from a /rerun
    override) takes priority over the constraints-derived one. Short-circuits
    to canned output, skipping retrieval entirely, when either the process-wide
    ORCHESTRATOR_STUB_DOCS env var is set (see _STUB_DOCS) or the caller passed
    context["mock"] (the per-run "Mock" checkbox on the Start/Retry form, see
    main.py's StartRequest/RerunOverrides — real crawls are slow enough during
    local dev that a permanent env var is too blunt, this is opt-in per run
    instead). The real fetch_documentation()/fetch_page() (the tool-call paths
    nudge() can reach directly, via pipeline_tools.py) are untouched either way.
    """
    seed_url = context.get("seed_url", "")
    if _STUB_DOCS or context.get("mock"):
        return (
            f"[MOCKED] Skipped the real crawl of {seed_url}. "
            f"This is placeholder output for testing the pipeline's mechanics, not real documentation."
        )
    constraints = context.get("constraints", {}).get("docs", [])
    hint = context.get("hint") or (" ".join(constraints) if constraints else None)

    result = orchestrator.fetch_documentation(
        seed_url,
        hint=hint,
        exclude_urls=context.get("exclude_urls"),
        max_pages=context.get("max_pages"),
        max_depth=context.get("max_depth"),
        force_refresh=context.get("force_refresh"),
    )
    pages = [p for p in result["pages"] if p["success"]]
    confidence = result["meta"]["confidence"]
    if not pages or confidence < _DOCS_MIN_CONFIDENCE:
        raise RuntimeError(
            f"Fetch for {seed_url} found essentially nothing useful "
            f"(confidence {confidence:.2f}, {len(pages)} usable page(s))."
        )
    content = "\n\n".join(f"# {p['url']}\n{p['markdown']}" for p in pages)
    return f"Fetched {len(pages)} page(s) from {seed_url}, confidence {confidence:.2f}.\n\n{content}"


def pim_agent(context: dict) -> str:
    # serialization_output (the labeled, structured markdown) is what a live
    # run actually has once "serialization" precedes "pim" — strictly more
    # useful than the raw docs_output it's built from. docs_output is the
    # fallback for a caller (or a unit test) that invokes pim_agent directly
    # without a serialization stage having run; platform_description is the
    # last-resort fallback below that.
    platform_description = context.get("platform_description", "")
    primary_input = context.get("serialization_output") or context.get("docs_output") or platform_description
    user_content = primary_input + _constraints_note(context, "pim")
    messages = [
        {"role": "system", "content": _STAGE_SYSTEM_PROMPTS["pim"]},
        {"role": "user", "content": user_content},
    ]
    return orchestrator.chat(messages, model=context.get("model"))["content"]


def psm_agent(context: dict) -> str:
    # pim_output is what a live run actually has once "pim" precedes "psm";
    # docs_output/platform_description are fallbacks for a caller (or a unit
    # test) that invokes psm_agent directly without a pim stage having run.
    platform_description = context.get("platform_description", "")
    primary_input = context.get("pim_output") or context.get("docs_output") or platform_description
    user_content = primary_input + _constraints_note(context, "psm")
    messages = [
        {"role": "system", "content": _STAGE_SYSTEM_PROMPTS["psm"]},
        {"role": "user", "content": user_content},
    ]
    return orchestrator.chat(messages, model=context.get("model"))["content"]


def atl_agent(context: dict) -> str:
    psm_output = context.get("psm_output", "")
    user_content = psm_output + _constraints_note(context, "atl")
    messages = [
        {"role": "system", "content": _STAGE_SYSTEM_PROMPTS["atl"]},
        {"role": "user", "content": user_content},
    ]
    return orchestrator.chat(messages, model=context.get("model"))["content"]


def acceleo_agent(context: dict) -> str:
    atl_output = context.get("atl_output", "")
    user_content = atl_output + _constraints_note(context, "acceleo")
    messages = [
        {"role": "system", "content": _STAGE_SYSTEM_PROMPTS["acceleo"]},
        {"role": "user", "content": user_content},
    ]
    return orchestrator.chat(messages, model=context.get("model"))["content"]


def gen_agent(context: dict) -> str:
    user_content = (
        f"PSM description:\n{context.get('psm_output', '')}\n\n"
        f"ATL transformation rules:\n{context.get('atl_output', '')}\n\n"
        f"Acceleo template plan:\n{context.get('acceleo_output', '')}"
    ) + _constraints_note(context, "generation")
    messages = [
        {"role": "system", "content": _STAGE_SYSTEM_PROMPTS["generation"]},
        {"role": "user", "content": user_content},
    ]
    return orchestrator.chat(messages, model=context.get("model"))["content"]


stage_agents = {
    "docs": docs_agent,
    "serialization": serialization_agent.serialization_agent,
    "pim": pim_agent,
    "psm": psm_agent,
    "atl": atl_agent,
    "acceleo": acceleo_agent,
    "generation": gen_agent,
}
