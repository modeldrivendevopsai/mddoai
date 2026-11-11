"""journal_client against the real journal app, in the same process."""
import json

import pytest

from clients import journal_client as prompts
from clients.agent_service_errors import AgentServiceError
from clients.journal_testing import folders_in, journal_in_process

SHIPPED = {"attachments": [{"id": "system", "type": "text", "content": "shipped"}]}


@pytest.fixture
def folders(tmp_path):
    return folders_in(tmp_path)


@pytest.fixture(autouse=True)
def journal(folders):
    shipped = folders[3] / "psm" / "generation"
    shipped.mkdir(parents=True)
    (shipped / "default.default.json").write_text(json.dumps(SHIPPED), encoding="utf-8")
    with journal_in_process(folders):
        yield


def test_a_config_nobody_saved_is_the_shipped_one_and_one_that_is_not_there_is_none():
    assert prompts.get_config("psm", "generation") == SHIPPED
    assert prompts.get_config("psm", "comparison") is None
    assert prompts.get_shipped_config("psm", "comparison") is None


def test_a_saved_config_comes_back_stamped_and_is_the_live_one():
    saved = prompts.save_config("psm", "generation", {"attachments": []})

    assert saved["_version"]
    assert prompts.get_config("psm", "generation") == saved
    assert prompts.get_shipped_config("psm", "generation") == SHIPPED


def test_the_versions_are_listed_and_each_is_read_back():
    first = prompts.save_config("psm", "generation", {"attachments": [{"id": "a"}]})
    second = prompts.save_config("psm", "generation", {"attachments": [{"id": "b"}]})

    assert prompts.list_versions("psm", "generation") == ([second["_version"], first["_version"]], True)
    assert prompts.get_version("psm", "generation", first["_version"]) == first
    assert prompts.get_version("psm", "generation", "20200101T000000.000000Z-abcdef") is None


def test_constraints_are_none_until_kept_and_then_as_kept():
    assert prompts.get_constraints("psm", "generation") is None

    prompts.set_constraints("psm", "generation", ["one", "two"])

    assert prompts.get_constraints("psm", "generation") == ["one", "two"]


def test_an_upload_is_kept_listed_and_read_back_and_a_missing_one_is_none():
    prompts.save_upload("psm", "abc-model.ecore", "<ecore/>")

    assert prompts.list_uploads("psm") == ["abc-model.ecore"]
    assert prompts.list_uploads("atl") == []
    assert prompts.get_upload("psm", "abc-model.ecore") == "<ecore/>"
    assert prompts.get_upload("psm", "none.txt") is None


def test_an_upload_under_a_taken_name_is_refused_with_the_journals_reason():
    prompts.save_upload("psm", "a.txt", "first")

    with pytest.raises(AgentServiceError) as error:
        prompts.save_upload("psm", "a.txt", "second")

    assert error.value.status_code == 409
    assert prompts.get_upload("psm", "a.txt") == "first"


def test_a_name_with_a_slash_stays_inside_its_own_part_of_the_address():
    with pytest.raises(AgentServiceError):
        prompts.save_upload("psm", "a/b.txt", "x")
