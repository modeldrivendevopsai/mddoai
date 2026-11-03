"""Shared by the tests that generate through the real app."""
import functools

from pipeline_checks import check_pipeline
from platform_registry import PlatformRegistry
from settings import PLATFORMS_DIR


def generate(client, body: dict):
    """POST /generate, then GET the job it started. The tests' job store
    runs a generation at once, so the job is already finished. A refused
    request returns the POST's own response."""
    started = client.post("/generate", json=body)
    if started.status_code != 202:
        return started
    return client.get(f"/generate/{started.json()['job_id']}")


@functools.cache
def _gitlab():
    registry = PlatformRegistry(PLATFORMS_DIR)
    platform = registry.get("gitlab")
    return platform, registry.schema(platform)


def check_gitlab_pipeline(content: str):
    """The checklist for a GitLab pipeline, from the committed GitLab
    platform's own schema and rules."""
    platform, schema = _gitlab()
    return check_pipeline(platform, content, schema)
