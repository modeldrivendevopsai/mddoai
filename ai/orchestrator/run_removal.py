"""Deleting a past run: the one place the REST route and the chat tool both go
through, so they do exactly the same. The runner removes the run and its files,
and refuses the current run and a run a platform was published from. Only once it
has, the run's chat goes too, so a refused run keeps its chat."""
import logging

import chat_log
from clients import integration_runner_client

logger = logging.getLogger(__name__)


def remove_run(run_id: str) -> dict:
    result = integration_runner_client.delete_run(run_id)
    try:
        chat_log.remove_chat_log(run_id)
    except Exception:
        # The run is already gone, and a chat left behind belongs to nothing and is never shown.
        logger.exception("run %s was deleted but its chat could not be removed from the journal", run_id)
    return result
