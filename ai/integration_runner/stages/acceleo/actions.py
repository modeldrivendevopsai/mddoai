"""acceleo-stage-specific ad-hoc action: promoting a run's own live,
verified corrections into acceleo_agent's own real, permanent prompt
config - see generation_toolkit.prompt_config.learned_constraints for the
persistence side this calls into over HTTP (via
clients/acceleo_agent_client.py). Mirrors stages/atl/actions.py, same
simpler gate (one real mode only, no generation-vs-knowledge-mode split to
check).
"""
from clients import acceleo_agent_client


def _latest_completed_acceleo_result(run) -> dict | None:
    for event in reversed(run.events):
        if event["type"] == "call_completed" and event["stage"] == "acceleo":
            return event["data"]
    return None


def promote_constraints(run, constraints: list[str]) -> dict:
    """Promotes `constraints` into acceleo_agent's own generation config
    for whichever preset the run's own latest, real, successfully-validated
    acceleo result actually used - permanently, for every future run of
    that preset. Raises ValueError when there's no such verified result to
    promote from (acceleo isn't the current stage, or its latest completion
    wasn't a real, validated success)."""
    if run.current_stage != "acceleo":
        raise ValueError(
            f"Can only promote constraints while acceleo is the current pending stage (current: {run.current_stage!r})."
        )
    latest = _latest_completed_acceleo_result(run)
    if latest is None or not latest.get("validation", {}).get("valid"):
        raise ValueError("Can only promote constraints from a real, successfully validated acceleo result.")
    preset = latest.get("preset", "default")
    updated = acceleo_agent_client.add_learned_constraints("generation", preset, constraints)
    run.record_event("constraints_promoted", "acceleo", {"preset": preset, "constraints": constraints})
    return updated
