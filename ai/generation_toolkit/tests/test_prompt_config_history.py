import time

import pytest

from generation_toolkit.prompt_config.history import (
    diff_versions,
    list_history,
    promote_live_to_default,
    restore_version,
    revert_to_default,
)
from generation_toolkit.prompt_config.storage import load_config, save_config

_BASE = {"system_prompt": "v1", "attachments": [{"id": "a", "name": "A", "type": "text", "content": "one"}]}


def test_list_history_is_newest_first(tmp_path):
    save_config(tmp_path, "generation", _BASE, {}, tmp_path)
    time.sleep(0.01)
    saved_second = save_config(tmp_path, "generation", {**_BASE, "system_prompt": "v2"}, {}, tmp_path)

    versions = list_history(tmp_path, "generation")

    assert versions[0] == saved_second["_version"]
    assert len(versions) == 2


def test_list_history_only_returns_this_names_own_versions(tmp_path):
    save_config(tmp_path, "generation", _BASE, {}, tmp_path)
    save_config(tmp_path, "comparison", _BASE, {}, tmp_path)

    assert len(list_history(tmp_path, "generation")) == 1
    assert len(list_history(tmp_path, "comparison")) == 1


def test_diff_versions_detects_a_changed_system_prompt(tmp_path):
    # _BASE's own first attachment ("a") is the config's system-prompt role
    # (see resolution.py) - a change to it needs no special case, it's
    # just that attachment's own id showing up in attachments_changed like
    # any other edited attachment.
    v1 = save_config(tmp_path, "generation", _BASE, {}, tmp_path)
    changed_system_prompt = {
        **_BASE,
        "attachments": [{**_BASE["attachments"][0], "content": "two"}, *_BASE["attachments"][1:]],
    }
    v2 = save_config(tmp_path, "generation", changed_system_prompt, {}, tmp_path)

    diff = diff_versions(tmp_path, "generation", v1["_version"], v2["_version"])

    assert diff["attachments_changed"] == ["a"]
    assert diff["attachments_added"] == []
    assert diff["attachments_removed"] == []


def test_diff_versions_detects_an_added_attachment(tmp_path):
    v1 = save_config(tmp_path, "generation", _BASE, {}, tmp_path)
    with_extra = {**_BASE, "attachments": [*_BASE["attachments"], {"id": "b", "name": "B", "type": "text", "content": "new"}]}
    v2 = save_config(tmp_path, "generation", with_extra, {}, tmp_path)

    diff = diff_versions(tmp_path, "generation", v1["_version"], v2["_version"])

    assert diff["attachments_added"] == ["b"]


def test_diff_versions_detects_a_changed_attachment_content(tmp_path):
    v1 = save_config(tmp_path, "generation", _BASE, {}, tmp_path)
    changed = {**_BASE, "attachments": [{"id": "a", "name": "A", "type": "text", "content": "different"}]}
    v2 = save_config(tmp_path, "generation", changed, {}, tmp_path)

    diff = diff_versions(tmp_path, "generation", v1["_version"], v2["_version"])

    assert diff["attachments_changed"] == ["a"]


def test_restore_version_brings_back_old_content_as_a_new_version(tmp_path):
    v1 = save_config(tmp_path, "generation", _BASE, {}, tmp_path)
    save_config(tmp_path, "generation", {**_BASE, "system_prompt": "v2"}, {}, tmp_path)

    restored = restore_version(tmp_path, "generation", v1["_version"], {}, tmp_path)

    assert restored["system_prompt"] == "v1"
    assert restored["_version"] != v1["_version"]  # a genuinely new version, not a rewind
    assert len(list_history(tmp_path, "generation")) == 3


def test_revert_to_default_uses_the_shipped_default(tmp_path):
    (tmp_path / "generation").mkdir()
    (tmp_path / "generation" / "default.default.json").write_text(
        '{"system_prompt": "shipped default", "attachments": []}', encoding="utf-8"
    )
    save_config(tmp_path, "generation", {**_BASE, "system_prompt": "edited"}, {}, tmp_path)

    reverted = revert_to_default(tmp_path, "generation", {}, tmp_path)

    assert reverted["system_prompt"] == "shipped default"


def test_revert_to_default_raises_when_no_default_exists_at_all(tmp_path):
    with pytest.raises(FileNotFoundError):
        revert_to_default(tmp_path, "generation", {}, tmp_path)


def test_promote_live_to_default_copies_the_live_config_over_the_default(tmp_path):
    (tmp_path / "generation").mkdir()
    (tmp_path / "generation" / "default.default.json").write_text(
        '{"system_prompt": "old default", "attachments": []}', encoding="utf-8"
    )
    save_config(tmp_path, "generation", {**_BASE, "system_prompt": "improved"}, {}, tmp_path)

    promote_live_to_default(tmp_path, "generation")

    default_content = (tmp_path / "generation" / "default.default.json").read_text(encoding="utf-8")
    assert "improved" in default_content


def test_promote_live_to_default_does_not_change_the_live_file(tmp_path):
    saved = save_config(tmp_path, "generation", _BASE, {}, tmp_path)

    promote_live_to_default(tmp_path, "generation")

    assert load_config(tmp_path, "generation")["_version"] == saved["_version"]


def test_promote_live_to_default_strips_the_version_stamp(tmp_path):
    save_config(tmp_path, "generation", _BASE, {}, tmp_path)

    default_config = promote_live_to_default(tmp_path, "generation")

    assert "_version" not in default_config
