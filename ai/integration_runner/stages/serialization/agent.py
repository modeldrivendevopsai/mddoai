"""The serialization stage: calls the real, separate serialization_agent
service (own container, reached via clients/serialization_agent_client.py)
to turn the docs stage's raw output into a PIM-concept-labeled markdown
artifact. Doesn't use stages/_shared.py's constraints_note(): serialization
has no correction-taking parameter of its own today, same as before this
stage had its own folder.
"""
from clients import serialization_agent_client


def serialization_stage(context: dict) -> str:
    return serialization_agent_client.serialize(context.get("docs_output", ""), context.get("model"))
