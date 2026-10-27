"""Publishing a completed run's platform to the pipeline generator, so
pipelines can be generated for it next to the built-in platforms.

What is published is what produced the approved pipeline: the PSM metamodel,
the ATL transformation and the Acceleo template that the generation stage's
last successful attempt ran with, read from that attempt's own recorded
events. Not the run's latest context, which a later failed attempt or a
context override can have changed since.
"""
from clients import pipeline_generator_client
from integration_runner import runs
from integration_runner.atl_source import output_model_name
from integration_runner.pipeline import IntegrationRun

# The stage outputs a platform is made of, as the generation stage's context
# carries them.
_ARTIFACT_KEYS = {"metamodel": "psm_output", "transformation": "atl_output", "template": "acceleo_output"}


class NotPublishableError(ValueError):
    """This run cannot be published as it stands; the message says why."""


def _last_generation_event(events: list[dict], event_type: str, before: int) -> dict | None:
    """The last generation-stage event of `event_type` before index `before`,
    with its index, or None."""
    for index in range(before - 1, -1, -1):
        if events[index].get("stage") == "generation" and events[index].get("type") == event_type:
            return {"index": index, "data": events[index].get("data") or {}}
    return None


def _approved_generation(run: IntegrationRun) -> tuple[dict, dict]:
    """The generation stage's last successful attempt: the context it ran with
    and what it produced. Approval needs a successful attempt, so on a
    completed run this is the attempt behind the approved pipeline. Empty
    dicts when generation never completed."""
    events = run.events
    completed = _last_generation_event(events, "call_completed", len(events))
    if completed is None:
        return {}, {}
    started = _last_generation_event(events, "call_started", completed["index"])
    return (started["data"] if started else {}), completed["data"]


def defaults(run: IntegrationRun) -> dict:
    """The name and output file a publish uses when the caller gives none:
    the run's own platform description, and the one file its generation
    stage produced (none when it produced several, so the caller must say)."""
    files = sorted(_approved_generation(run)[1].get("generated_files") or {})
    return {"name": runs.platform_name(run), "output_filename": files[0] if len(files) == 1 else None}


def publish(run: IntegrationRun, name: str | None = None, output_filename: str | None = None) -> dict:
    """Publishes `run`'s approved platform and records that it was. Returns the
    pipeline generator's answer: the platform's id, name, version and output
    file. Raises NotPublishableError when the run is not complete or what it
    produced cannot be published."""
    if run.current_stage is not None:
        raise NotPublishableError(
            f"This run is not complete yet: '{run.current_stage}' still needs approval, so there is no approved "
            "platform to publish."
        )
    context, _ = _approved_generation(run)
    artifacts = {field: context.get(key) for field, key in _ARTIFACT_KEYS.items()}
    missing = [_ARTIFACT_KEYS[field] for field, value in artifacts.items() if not value]
    if missing:
        raise NotPublishableError(f"This run has no approved {', '.join(missing)} to publish.")
    try:
        model_name = output_model_name(artifacts["transformation"])
    except ValueError as e:
        raise NotPublishableError(str(e)) from e

    fallback = defaults(run)
    name = (name or fallback["name"] or "").strip()
    output_filename = output_filename or fallback["output_filename"]
    if not name:
        raise NotPublishableError("Give the platform a name to publish it under.")
    if not output_filename:
        raise NotPublishableError("Say which generated file is the platform's pipeline file.")

    result = pipeline_generator_client.publish_platform(
        name=name,
        output_filename=output_filename,
        output_model_name=model_name,
        source_run_id=run.run_id,
        **artifacts,
    )
    run.record_event(
        "platform_published", None, {"platform_id": result["id"], "name": result["name"], "version": result["version"]}
    )
    return result
