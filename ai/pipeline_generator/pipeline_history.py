"""The pipelines a person has generated, kept so the "Your pipelines" screen can bring
them back to their work. The journal keeps them: this class decides what identifies a
generation, and reads and writes through the journal's API.

A pipeline is identified by its model input (the workspace path it was loaded from, else
the file name), and the newest generation for an input replaces the earlier one, so the
list has one row per input.
"""
import logging
import re

import httpx

from clients import journal_client
from clients.agent_service_errors import AgentServiceError

logger = logging.getLogger(__name__)

# The shape of an id the journal gives, so a value of any other shape is not asked for.
_ID_PATTERN = re.compile(r"[0-9a-f]{32}")


class PipelineHistory:
    def record_generation(
        self,
        *,
        filename: str,
        content: str,
        source_path: str | None,
        generated: list[dict],
    ) -> str | None:
        """Records what was just generated, so it can be reopened. `source_path`
        is the workspace path the model was loaded from, or None for an upload.
        Returns the id, or None if there was nothing to record or recording
        failed: the pipelines were generated either way and still go back to
        the caller."""
        if not generated:
            return None
        # A model is identified by where it was loaded from, or else by its file
        # name. The prefix keeps an upload that happens to be called like a
        # workspace path from replacing that path's pipeline.
        key = f"path:{source_path}" if source_path else f"upload:{filename}"
        try:
            return journal_client.record_pipeline(
                key=key, swarch_name=filename, source_path=source_path, content=content, results=generated
            )
        except (AgentServiceError, httpx.HTTPError) as e:
            logger.error("could not record the generated pipeline: %s", e)
            return None

    def summaries(self) -> list[dict]:
        """Every pipeline, newest first, without the bulky parts."""
        return journal_client.list_pipelines()

    def get(self, pipeline_id: str) -> dict | None:
        """One pipeline in full, or None if it is unknown."""
        if not _ID_PATTERN.fullmatch(pipeline_id):
            return None
        return journal_client.get_pipeline(pipeline_id)
