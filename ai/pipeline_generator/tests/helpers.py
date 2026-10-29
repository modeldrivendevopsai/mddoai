"""Shared by the tests that generate through the real app."""


def generate(client, body: dict):
    """POST /generate, then GET the job it started. The test client runs the
    generation (a background task) before the POST returns, so the job is
    already finished. A refused request returns the POST's own response."""
    started = client.post("/generate", json=body)
    if started.status_code != 202:
        return started
    return client.get(f"/generate/{started.json()['job_id']}")
