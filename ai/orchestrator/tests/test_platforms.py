"""Listing and purging published platforms: the REST routes and the chat tools,
which make the same calls. The journal is the real one, in this process, over
folders of the test alone (see conftest.py's journal fixture)."""
import json
from unittest.mock import patch

import httpx
import pytest
from fastapi.testclient import TestClient

import main
import tool_calling
import tools
from clients import journal_client
from clients.agent_service_errors import AgentServiceError

client = TestClient(main.app)

PLATFORM = {
    "name": "Woodpecker CI",
    "output_filename": ".woodpecker.yaml",
    "output_model_name": "WoodMM",
    "metamodel": "<ecore/>",
    "transformation": "<atl/>",
    "template": "<mtl/>",
    "source_run_id": "run1",
}


def _publish(*versions):
    for version in versions:
        journal_client.publish_platform(**PLATFORM, version=version)


def _ship_gitlab(journal):
    """A platform that came with the stack: no publish record."""
    folder = journal / "platforms" / "gitlab" / "1.0.0"
    folder.mkdir(parents=True)
    for name in ("metamodel.ecore", "transformation.atl", "template.mtl"):
        (folder / name).write_text("x", encoding="utf-8")
    meta = {"id": "gitlab", "name": "GitLab CI", "version": "1.0.0", "output_filename": "g.yml", "output_model_name": "G"}
    (folder / "platform.json").write_text(json.dumps(meta), encoding="utf-8")


def _ids():
    return [p["id"] for p in journal_client.list_platforms()]


# --- the routes -----------------------------------------------------------------


def test_the_list_shows_each_platform_with_its_versions_and_whether_it_was_published(journal):
    _ship_gitlab(journal)
    _publish("1.0.0", "1.1.0")

    platforms = {p["id"]: p for p in client.get("/platforms").json()["platforms"]}

    assert (platforms["gitlab"]["versions"], platforms["gitlab"]["published"]) == (["1.0.0"], False)
    assert (platforms["woodpecker-ci"]["version"], platforms["woodpecker-ci"]["versions"], platforms["woodpecker-ci"]["published"]) == (
        "1.1.0", ["1.0.0", "1.1.0"], True,
    )


def test_one_version_can_be_purged_and_the_others_stay():
    _publish("1.0.0", "1.1.0")

    response = client.delete("/platforms/woodpecker-ci/versions/1.0.0")

    assert response.json() == {"purged": ["1.0.0"]}
    assert client.get("/platforms").json()["platforms"][0]["versions"] == ["1.1.0"]


def test_a_whole_platform_can_be_purged():
    _publish("1.0.0", "1.1.0")

    assert client.delete("/platforms/woodpecker-ci").json() == {"purged": ["1.0.0", "1.1.0"]}
    assert _ids() == []


def test_a_platform_or_version_that_is_not_there_is_a_404_with_the_journals_reason():
    _publish("1.0.0")

    missing = client.delete("/platforms/nothing")
    version = client.delete("/platforms/woodpecker-ci/versions/9.9.9")

    assert missing.status_code == 404 and "nothing" in missing.json()["detail"]
    assert version.status_code == 404 and "9.9.9" in version.json()["detail"]
    assert _ids() == ["woodpecker-ci"]


def test_a_platform_that_came_with_the_stack_is_a_409_and_stays(journal):
    _ship_gitlab(journal)

    whole = client.delete("/platforms/gitlab")
    one = client.delete("/platforms/gitlab/versions/1.0.0")

    assert whole.status_code == one.status_code == 409
    assert "came with the stack" in whole.json()["detail"]
    assert _ids() == ["gitlab"]


@pytest.mark.parametrize("call", [lambda: client.get("/platforms"), lambda: client.delete("/platforms/x"), lambda: client.delete("/platforms/x/versions/1.0.0")])
def test_a_journal_that_cannot_be_reached_is_a_502(call):
    def down(*args, **kwargs):
        raise httpx.ConnectError("refused")

    with patch.object(journal_client.httpx.request, "side_effect", down):
        response = call()

    assert response.status_code == 502
    assert "journal" not in response.text


# --- the chat tools -------------------------------------------------------------


def _dispatch(name, arguments):
    return tool_calling.dispatch_tool(name, arguments, tools.get_tools())


def test_the_platform_tools_are_offered_at_every_stage():
    offered = {tool.name for tool in tool_calling.load_tools("docs", tools.get_tools())}
    offered_later = {tool.name for tool in tool_calling.load_tools(None, tools.get_tools())}

    assert {"list_platforms", "purge_platform"} <= offered
    assert {"list_platforms", "purge_platform"} <= offered_later


def test_the_purge_tool_says_it_cannot_be_undone_and_when_to_use_it():
    purge = next(tool for tool in tools.get_tools() if tool.name == "purge_platform")

    assert "cannot be undone" in purge.description
    assert "clearly asks" in purge.description
    assert "never as a request to purge" in purge.description
    assert purge.parameters["required"] == ["platform_id"]
    assert set(purge.parameters["properties"]) == {"platform_id", "version", "all_versions"}


def test_listing_through_the_tool_gives_ids_versions_and_whether_each_was_published(journal):
    _ship_gitlab(journal)
    _publish("1.0.0", "1.1.0")

    listed = _dispatch("list_platforms", {})["platforms"]

    assert {p["id"]: (p["version"], p["versions"], p["published"]) for p in listed} == {
        "gitlab": ("1.0.0", ["1.0.0"], False),
        "woodpecker-ci": ("1.1.0", ["1.0.0", "1.1.0"], True),
    }
    assert all(set(p) == {"id", "name", "version", "versions", "published"} for p in listed)


def test_purging_one_version_through_the_tool_does_what_the_delete_route_does():
    _publish("1.0.0", "1.1.0", "1.2.0")

    through_tool = _dispatch("purge_platform", {"platform_id": "woodpecker-ci", "version": "1.0.0"})
    through_route = client.delete("/platforms/woodpecker-ci/versions/1.1.0").json()

    assert through_tool == {"platform_id": "woodpecker-ci", "purged": ["1.0.0"]}
    assert through_route == {"purged": ["1.1.0"]}
    assert [p["versions"] for p in journal_client.list_platforms()] == [["1.2.0"]]


def test_purging_with_all_versions_through_the_tool_removes_the_whole_platform():
    _publish("1.0.0", "1.1.0")

    result = _dispatch("purge_platform", {"platform_id": "woodpecker-ci", "all_versions": True})

    assert result == {"platform_id": "woodpecker-ci", "purged": ["1.0.0", "1.1.0"]}
    assert _ids() == []


@pytest.mark.parametrize("arguments", [{}, {"version": ""}, {"all_versions": False}, {"version": "", "all_versions": False}])
def test_the_tool_removes_nothing_when_it_is_not_told_which_versions(arguments):
    _publish("1.0.0", "1.1.0")

    with pytest.raises(ValueError, match="Say which version"):
        _dispatch("purge_platform", {"platform_id": "woodpecker-ci", **arguments})

    assert [p["versions"] for p in journal_client.list_platforms()] == [["1.0.0", "1.1.0"]]


def test_the_tool_removes_nothing_when_given_a_version_and_all_versions_together():
    _publish("1.0.0", "1.1.0")

    with pytest.raises(ValueError, match="not both"):
        _dispatch("purge_platform", {"platform_id": "woodpecker-ci", "version": "1.0.0", "all_versions": True})

    assert [p["versions"] for p in journal_client.list_platforms()] == [["1.0.0", "1.1.0"]]


@pytest.mark.parametrize("platform_id", ["..", ".", "../attempts/run1/psm/attempt_1"])
def test_an_id_that_tries_to_address_something_else_removes_nothing(platform_id):
    reserved = journal_client.reserve_attempt("run1", "psm")

    with pytest.raises((ValueError, AgentServiceError)):
        _dispatch("purge_platform", {"platform_id": platform_id, "all_versions": True})

    assert journal_client.reserve_attempt("run1", "psm") != reserved


def test_the_tool_does_not_remove_a_platform_that_came_with_the_stack(journal):
    _ship_gitlab(journal)

    with pytest.raises(AgentServiceError, match="came with the stack"):
        _dispatch("purge_platform", {"platform_id": "gitlab", "all_versions": True})

    assert _ids() == ["gitlab"]


def test_a_refusal_reaches_the_model_as_a_readable_error_and_not_as_a_failed_reply():
    def chat(messages, model=None, tools=None, tool_choice=None):
        call = {"function": {"name": "purge_platform", "arguments": json.dumps({"platform_id": "nothing", "all_versions": True})}}
        return {"content": None, "tool_calls": [call], "model": "m"}

    reply = tool_calling.build_reply(chat, "system", {"type": "user_message"}, [], tools.get_tools())

    assert reply["tool_called"] == "purge_platform"
    assert "nothing" in reply["result"]["error"]
