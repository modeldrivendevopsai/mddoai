"""The docs-stage-specific tool: adds one specific page's real content onto
the docs stage's current pending output — an ADD, not a replace (see
rerun_stage's own docstring in pipeline_control.py for the contrast: a
fresh/narrower/wider crawl goes through rerun_stage instead, which REPLACES
the whole output). Only offered while docs is the pending stage
(stages=["docs"] below).

Routed through integration_runner's real POST /docs/extend, not a direct
clients/retrieval_client.py call: that keeps integration_runner the one
complete, authoritative record of every retrieval action for a run, and
the one place last_output actually gets mutated. No local wrapper needed:
the impl points directly at integration_runner_client, since the tool's
own parameters already match the real endpoint's.
"""
import tool_calling
from clients import integration_runner_client


def get_tools() -> list["tool_calling.Tool"]:
    return [
        tool_calling.Tool(
            name="add_page_to_docs",
            stages=["docs"],
            description=(
                "Fetch one specific page the human already knows the URL of, and add its "
                "real content onto the docs stage's current pending output (retrieval's real "
                "POST /fetch/page, no crawling) — for when the crawl mostly succeeded but "
                "missed one known page. Does NOT replace or redo the existing crawl, it only "
                "appends; use rerun_stage instead for a fresh full crawl with different "
                "parameters. Only valid while docs is still pending review, not yet approved."
            ),
            parameters={
                "type": "object",
                "properties": {
                    "url": {"type": "string", "description": "The specific page URL to fetch and add."},
                    "force_refresh": {"type": "boolean", "description": "Bypass retrieval's page cache and refetch from scratch."},
                },
                "required": ["url"],
            },
            impl=integration_runner_client.add_page_to_docs,
        ),
    ]
