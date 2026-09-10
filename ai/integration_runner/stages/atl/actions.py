"""atl-stage-specific ad-hoc action: promoting a run's own live, verified
corrections into atl_agent's own real, permanent prompt config - see
generation_toolkit.prompt_config.learned_constraints for the persistence
side this calls into over HTTP (via clients/atl_agent_client.py). Mirrors
stages/psm/actions.py, simpler gate: atl has only one real mode (there's
no generation-vs-knowledge-mode split to check), so promoting only ever
needs a real, successfully-validated result.

Not a stage agent (never in stage_agents, never dispatched by
run_stage()), matching stages/docs/actions.py's own shape: takes `run`
directly, but doesn't mutate any of its own state fields the way that
action does - promoting a constraint changes atl_agent's own saved config,
a resource outside this run entirely, this only records that it happened.

Deliberately gated on a real, already-validated success, never every
human-typed correction automatically: only a constraint verified to
actually work should ever become permanent, the same "curate after it
holds" discipline the real ai-research experiments themselves used, not
blind copy-on-type. This also keeps IntegrationRun.constraints (the
existing, generic, per-run, ephemeral "retry with a correction" list every
stage already shares via add_constraint()) completely untouched - promoting
a constraint into atl_agent's own permanent config is a separate, explicit,
human-confirmed action layered on top, not a change to that shared
mechanism's own behavior.
"""
from clients import atl_agent_client


def _latest_completed_atl_result(run) -> dict | None:
    for event in reversed(run.events):
        if event["type"] == "call_completed" and event["stage"] == "atl":
            return event["data"]
    return None


def promote_constraints(run, constraints: list[str]) -> dict:
    """Promotes `constraints` into atl_agent's own generation config for
    whichever preset the run's own latest, real, successfully-validated
    atl result actually used - permanently, for every future run of that
    preset. Raises ValueError when there's no such verified result to
    promote from (atl isn't the current stage, or its latest completion
    wasn't a real, validated success)."""
    if run.current_stage != "atl":
        raise ValueError(
            f"Can only promote constraints while atl is the current pending stage (current: {run.current_stage!r})."
        )
    latest = _latest_completed_atl_result(run)
    if latest is None or not latest.get("validation", {}).get("valid"):
        raise ValueError("Can only promote constraints from a real, successfully validated atl result.")
    preset = latest.get("preset", "default")
    updated = atl_agent_client.add_learned_constraints("generation", preset, constraints)
    run.record_event("constraints_promoted", "atl", {"preset": preset, "constraints": constraints})
    return updated
