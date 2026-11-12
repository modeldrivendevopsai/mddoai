"""The prompt configs the journal keeps: the live one, the shipped one, the saved
versions and the learned constraints, over the real app and temporary folders."""
import json

import pytest

from journal.prompt_configs import PromptConfigNotFound, PromptConfigStore

SHIPPED = {"attachments": [{"id": "system", "name": "System", "type": "text", "content": "shipped"}], "_version": "shipped"}


def _attachments(text: str) -> dict:
    return {"attachments": [{"id": "system", "name": "System", "type": "text", "content": text}]}


@pytest.fixture
def prompts(folders):
    """The prompts folder with a shipped config in it, as the repo carries one."""
    folder = folders[3] / "psm" / "generation"
    folder.mkdir(parents=True)
    (folder / "default.default.json").write_text(json.dumps(SHIPPED), encoding="utf-8")
    return folders[3]


@pytest.fixture
def store(prompts):
    return PromptConfigStore(prompts, max_versions=3)


# --- the live and the shipped config ----------------------------------------------------


def test_the_shipped_config_is_the_live_one_until_a_config_is_saved(client, prompts):
    assert client.get("/prompt-configs/psm/generation").json() == SHIPPED


def test_a_config_with_nothing_shipped_and_nothing_saved_is_a_404(client, prompts):
    assert client.get("/prompt-configs/psm/comparison").status_code == 404


def test_a_saved_config_is_the_live_one_and_stamped_with_its_version(client, prompts):
    saved = client.put("/prompt-configs/psm/generation", json={"config": _attachments("mine")}).json()

    assert saved["attachments"] == _attachments("mine")["attachments"]
    assert saved["_version"]
    assert client.get("/prompt-configs/psm/generation").json() == saved


def test_the_shipped_config_is_still_there_after_a_save(client, prompts):
    client.put("/prompt-configs/psm/generation", json={"config": _attachments("mine")})

    assert client.get("/prompt-configs/psm/generation/shipped").json() == SHIPPED


def test_a_save_of_the_attachments_that_are_already_live_changes_nothing(client, prompts):
    first = client.put("/prompt-configs/psm/generation", json={"config": _attachments("mine")}).json()

    again = client.put("/prompt-configs/psm/generation", json={"config": _attachments("mine")}).json()

    assert again == first
    assert client.get("/prompt-configs/psm/generation/versions").json()["versions"] == [first["_version"]]


def test_a_save_of_other_attachments_is_a_new_version(client, prompts):
    first = client.put("/prompt-configs/psm/generation", json={"config": _attachments("one")}).json()
    second = client.put("/prompt-configs/psm/generation", json={"config": _attachments("two")}).json()

    assert second["_version"] != first["_version"]
    assert client.get("/prompt-configs/psm/generation/versions").json()["versions"] == [second["_version"], first["_version"]]


def test_two_saves_in_a_row_never_share_a_version(client, prompts):
    ids = {client.put("/prompt-configs/psm/generation", json={"config": _attachments(str(n))}).json()["_version"] for n in range(5)}

    assert len(ids) == 5


# --- versions ------------------------------------------------------------------------------


def test_the_versions_are_listed_newest_first_and_say_whether_a_shipped_config_exists(client, prompts):
    a = client.put("/prompt-configs/psm/generation", json={"config": _attachments("a")}).json()["_version"]
    b = client.put("/prompt-configs/psm/generation", json={"config": _attachments("b")}).json()["_version"]

    assert client.get("/prompt-configs/psm/generation/versions").json() == {"versions": [b, a], "has_shipped": True}
    assert client.get("/prompt-configs/psm/comparison/versions").json() == {"versions": [], "has_shipped": False}


def test_a_version_is_read_back_as_it_was_saved(client, prompts):
    a = client.put("/prompt-configs/psm/generation", json={"config": _attachments("a")}).json()
    client.put("/prompt-configs/psm/generation", json={"config": _attachments("b")})

    assert client.get(f"/prompt-configs/psm/generation/versions/{a['_version']}").json() == a


def test_an_unknown_version_is_a_404(client, prompts):
    assert client.get("/prompt-configs/psm/generation/versions/20200101T000000.000000Z-abcdef").status_code == 404


def test_only_the_newest_versions_are_kept(store):
    ids = [store.save("psm", "generation", _attachments(str(n)))["_version"] for n in range(5)]

    assert store.versions("psm", "generation") == list(reversed(ids))[:3]


def test_the_shipped_config_is_never_pruned(store):
    for n in range(5):
        store.save("psm", "generation", _attachments(str(n)))

    assert store.shipped("psm", "generation") == SHIPPED


# --- learned constraints ---------------------------------------------------------------------


def test_the_constraints_are_a_404_until_some_are_kept_and_then_read_back_as_kept(client, prompts):
    assert client.get("/prompt-configs/psm/generation/constraints").status_code == 404

    client.put("/prompt-configs/psm/generation/constraints", json={"constraints": ["one", "two"]})

    assert client.get("/prompt-configs/psm/generation/constraints").json() == {"constraints": ["one", "two"]}


def test_a_save_or_a_restore_of_the_config_leaves_the_constraints_alone(client, prompts):
    client.put("/prompt-configs/psm/generation/constraints", json={"constraints": ["keep me"]})

    client.put("/prompt-configs/psm/generation", json={"config": _attachments("changed")})

    assert client.get("/prompt-configs/psm/generation/constraints").json() == {"constraints": ["keep me"]}


# --- names -------------------------------------------------------------------------------------


@pytest.mark.parametrize("path", ["../generation", "..", "a b", "x" * 65])
def test_a_name_that_could_not_be_a_folder_is_a_404(client, prompts, path):
    assert client.get(f"/prompt-configs/psm/{path}").status_code in (404, 422)
    assert client.get(f"/prompt-configs/{path}/generation").status_code in (404, 422)


@pytest.mark.parametrize("version", ["..", ".", "a/b", "a\b", ""])
def test_a_version_that_steps_out_of_the_folder_is_refused(store, version):
    with pytest.raises(PromptConfigNotFound):
        store.version("psm", "generation", version)


def test_nothing_is_written_outside_the_prompts_folder_by_a_bad_name(client, folders):
    client.put("/prompt-configs/psm/..%2F..%2Fescape", json={"config": _attachments("x")})

    assert not (folders[3].parent / "escape").exists()


def test_a_save_without_a_config_is_a_422(client, prompts):
    assert client.put("/prompt-configs/psm/generation", json={}).status_code == 422


def test_the_store_says_not_found_in_its_own_words(store):
    with pytest.raises(PromptConfigNotFound):
        store.live("psm", "comparison")
