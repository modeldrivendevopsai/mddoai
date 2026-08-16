"""A run's raw event storage: an ordered, append-only log of real facts
(call_started/call_completed/call_failed, review_approved/review_rejected,
constraint_added, documentation_extended). No reaction, no
narration, no LLM call, just storage, so this is a separate concern from
IntegrationRun's stage-advancement rules in pipeline.py, which composes an
EventLog rather than managing a list itself. Turning these raw facts into a
human-readable comment is orchestrator's own job, done entirely by polling
this service's own GET /events over HTTP, never by this module calling out.
"""
import time


class EventLog:
    """One run's raw event history."""

    def __init__(self):
        self.events: list[dict] = []

    def record(self, event_type: str, stage: str | None, data: dict | None = None) -> dict:
        """Appends a raw fact and returns it. Nothing else."""
        event = {"type": event_type, "stage": stage, "data": data, "timestamp": time.time()}
        self.events.append(event)
        return event
