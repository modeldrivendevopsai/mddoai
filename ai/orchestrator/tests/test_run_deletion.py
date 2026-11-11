"""Deleting a past run: the REST route and the chat tools, which make the same
call. The runner is mocked (a separate service); the journal is the real one, in
this process, over folders of the test alone (see conftest.py's journal fixture)."""
from unittest.mock import patch

import pytest
from fastapi.testclient import TestClient

import chat_log
import main
import tool_calling
import tools
from chat_log import ChatLog
from chat_store import ChatStore
from clients import integration_runner_client, journal_client
from clients.integration_runner_client import IntegrationRunnerError

client = TestClient(main.app)

RUN_ID = "r1"


@pytest.fixture(autouse=True)
def _chats_in_the_journal():
    """A chat kept in the journal, as in a running service."""
    store = ChatStore()
    chat_log.load_saved(store)
    yield store
    chat_log._store = None
    chat_log._chat_logs.pop(RUN_ID, None)


def _a_chat_with_a_turn(store):
    chat = ChatLog(RUN_ID, store)
    chat.events.append({"type": "message", "stage": None, "text": "hello", "timestamp": 1.0})
    chat_log._chat_logs[RUN_ID] = chat
    store.save_quietly(chat)
    return chat


def _journal_holds_the_chat():
    return RUN_ID in journal_client._request("GET", "/histories/chats").json()["keys"]


def _runner_deletes(result=None):
    return patch.object(integration_runner_client, "delete_run", return_value=result or {"deleted": RUN_ID})


# --- the route -----------------------------------------------------------------------


def test_deleting_a_run_deletes_it_in_the_runner_and_removes_its_chat_here_and_in_the_journal(_chats_in_the_journal):
    _a_chat_with_a_turn(_chats_in_the_journal)
    assert _journal_holds_the_chat()

    with _runner_deletes() as runner:
        response = client.delete(f"/runs/{RUN_ID}")

    assert response.json() == {"deleted": RUN_ID}
    runner.assert_called_once_with(RUN_ID)
    assert RUN_ID not in chat_log._chat_logs
    assert not _journal_holds_the_chat()


@pytest.mark.parametrize(
    ("status", "reason"),
    [(404, "No run with id 'r1'"), (409, "That is the current run."), (409, "TeamCity 1.0.0 was published from this run, so the run is kept.")],
)
def test_a_refused_delete_passes_on_the_runners_status_and_reason_and_keeps_the_chat(_chats_in_the_journal, status, reason):
    _a_chat_with_a_turn(_chats_in_the_journal)

    with patch.object(integration_runner_client, "delete_run", side_effect=IntegrationRunnerError(status, reason)):
        response = client.delete(f"/runs/{RUN_ID}")

    assert (response.status_code, response.json()["detail"]) == (status, reason)
    assert RUN_ID in chat_log._chat_logs
    assert _journal_holds_the_chat()


def test_a_comment_still_being_written_when_the_run_is_deleted_does_not_put_the_chat_back(_chats_in_the_journal):
    chat = _a_chat_with_a_turn(_chats_in_the_journal)

    with _runner_deletes():
        client.delete(f"/runs/{RUN_ID}")
    # The narration thread held the chat all along, and finishes its comment now.
    chat.add([{"type": "message", "stage": "docs", "text": "late", "timestamp": 2.0}])

    assert not _journal_holds_the_chat()


def test_a_chat_the_journal_cannot_remove_does_not_undo_the_delete(_chats_in_the_journal):
    _a_chat_with_a_turn(_chats_in_the_journal)

    with _runner_deletes(), patch.object(chat_log, "remove_chat_log", side_effect=RuntimeError("journal down")):
        response = client.delete(f"/runs/{RUN_ID}")

    assert response.json() == {"deleted": RUN_ID}


def test_deleting_a_run_that_has_no_chat_is_fine():
    with _runner_deletes():
        assert client.delete("/runs/never-chatted").json() == {"deleted": RUN_ID}


# --- the chat tools ------------------------------------------------------------------


def _dispatch(name, arguments):
    return tool_calling.dispatch_tool(name, arguments, tools.get_tools())


def test_the_run_tools_are_offered_at_every_stage():
    offered = {tool.name for tool in tool_calling.load_tools("docs", tools.get_tools())}
    offered_later = {tool.name for tool in tool_calling.load_tools(None, tools.get_tools())}

    assert {"list_runs", "delete_run"} <= offered
    assert {"list_runs", "delete_run"} <= offered_later


def test_the_delete_tool_says_it_cannot_be_undone_when_to_use_it_and_what_it_refuses():
    delete = next(tool for tool in tools.get_tools() if tool.name == "delete_run")

    assert "cannot be undone" in delete.description
    assert "clearly asks" in delete.description
    assert "current run is refused" in delete.description and "platform was published" in delete.description


def test_the_list_tool_gives_what_is_needed_to_pick_a_run_and_nothing_more():
    runs = [
        {"run_id": "r2", "platform_name": "Woodpecker CI", "current_stage": "psm", "busy": False, "is_current": True, "started_at": 20.0},
        {"run_id": "r1", "platform_name": None, "current_stage": "docs", "busy": False, "is_current": False, "started_at": None},
    ]

    with patch.object(integration_runner_client, "list_runs", return_value=runs):
        result = _dispatch("list_runs", {})

    assert [r["run_id"] for r in result["runs"]] == ["r2", "r1"]
    assert set(result["runs"][0]) == {"run_id", "platform_name", "current_stage", "is_current", "started_at"}


def test_the_delete_tool_does_what_the_route_does(_chats_in_the_journal):
    _a_chat_with_a_turn(_chats_in_the_journal)

    with _runner_deletes() as runner:
        result = _dispatch("delete_run", {"run_id": RUN_ID})

    assert result == {"deleted": RUN_ID}
    runner.assert_called_once_with(RUN_ID)
    assert not _journal_holds_the_chat()
