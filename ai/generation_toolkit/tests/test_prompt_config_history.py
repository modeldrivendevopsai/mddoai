import json
import time

import pytest

from generation_toolkit.prompt_config.history import SHIPPED_DEFAULT_VERSION, diff_versions, list_history, restore_version
from generation_toolkit.prompt_config.storage import save_config

_BASE = {"system_prompt": "v1", "attachments": [{"id": "a", "name": "A", "type": "text", "content": "one"}]}


def _seed_default(config_dir, name="generation", **fields):
    directory = config_dir / name
    directory.mkdir(parents=True, exist_ok=True)
    (directory / "default.default.json").write_text(json.dumps({"attachments": [], **fields}), encoding="utf-8")


def test_list_history_is_newest_first(tmp_path):
    save_config(tmp_path, "generation", _BASE, {}, tmp_path)
    time.sleep(0.01)
    saved_second = save_config(tmp_path, "generation", {**_BASE, "system_prompt": "v2"}, {}, tmp_path)

    versions = list_history(tmp_path, "generation")

    assert versions[0] == saved_second["_version"]


def test_list_history_always_ends_with_the_shipped_default(tmp_path):
    """The shipped, git-committed default is folded into this same
    timeline as its own oldest entry, not a separate concept - present
    even before any real save has ever happened, and always last regardless
    of how many real versions exist, as long as one is actually shipped."""
    _seed_default(tmp_path)

    assert list_history(tmp_path, "generation") == [SHIPPED_DEFAULT_VERSION]

    save_config(tmp_path, "generation", _BASE, {}, tmp_path)

    assert list_history(tmp_path, "generation")[-1] == SHIPPED_DEFAULT_VERSION


def test_list_history_omits_the_shipped_entry_when_no_default_exists(tmp_path):
    """A name with no default.default.json ever committed (mid-onboarding,
    or a config_dir reseeded without it) shouldn't advertise a "shipped"
    version a Restore or Diff call would only 404 on - see _load_snapshot's
    own FileNotFoundError for exactly that."""
    assert list_history(tmp_path, "generation") == []

    v1 = save_config(tmp_path, "generation", _BASE, {}, tmp_path)

    assert list_history(tmp_path, "generation") == [v1["_version"]]


def test_list_history_only_returns_this_names_own_versions(tmp_path):
    _seed_default(tmp_path, "generation")
    _seed_default(tmp_path, "comparison")
    save_config(tmp_path, "generation", _BASE, {}, tmp_path)
    save_config(tmp_path, "comparison", _BASE, {}, tmp_path)

    assert len(list_history(tmp_path, "generation")) == 2  # 1 real save + the shipped entry
    assert len(list_history(tmp_path, "comparison")) == 2


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


def test_diff_versions_accepts_the_shipped_default_as_either_side(tmp_path):
    _seed_default(tmp_path, attachments=[{"id": "a", "name": "A", "type": "text", "content": "shipped"}])
    v1 = save_config(tmp_path, "generation", {"attachments": [{"id": "a", "name": "A", "type": "text", "content": "edited"}]}, {}, tmp_path)

    diff = diff_versions(tmp_path, "generation", SHIPPED_DEFAULT_VERSION, v1["_version"])

    assert diff["attachments_changed"] == ["a"]


def test_diff_versions_raises_when_no_shipped_default_exists(tmp_path):
    save_config(tmp_path, "generation", _BASE, {}, tmp_path)

    with pytest.raises(FileNotFoundError):
        diff_versions(tmp_path, "generation", SHIPPED_DEFAULT_VERSION, SHIPPED_DEFAULT_VERSION)


def test_restore_version_brings_back_old_content_as_a_new_version(tmp_path):
    _seed_default(tmp_path)
    v1 = save_config(tmp_path, "generation", _BASE, {}, tmp_path)
    save_config(tmp_path, "generation", {**_BASE, "system_prompt": "v2"}, {}, tmp_path)

    restored = restore_version(tmp_path, "generation", v1["_version"], {}, tmp_path)

    assert restored["system_prompt"] == "v1"
    assert restored["_version"] != v1["_version"]  # a genuinely new version, not a rewind
    assert len(list_history(tmp_path, "generation")) == 4  # 2 real saves + this restore's own new save + shipped


def test_restore_version_with_the_shipped_default_is_revert_to_default(tmp_path):
    """"Revert to default" is not a separate function any more - it's just
    restore_version called with SHIPPED_DEFAULT_VERSION, going through the
    exact same save_config path (versioned, appears in history, never
    destructive) as restoring any other real version."""
    _seed_default(tmp_path, system_prompt="shipped default")
    save_config(tmp_path, "generation", {**_BASE, "system_prompt": "edited"}, {}, tmp_path)

    reverted = restore_version(tmp_path, "generation", SHIPPED_DEFAULT_VERSION, {}, tmp_path)

    assert reverted["system_prompt"] == "shipped default"
    assert reverted["_version"]  # still a real, versioned save, not a bare copy


def test_restore_version_raises_when_no_shipped_default_exists_at_all(tmp_path):
    with pytest.raises(FileNotFoundError):
        restore_version(tmp_path, "generation", SHIPPED_DEFAULT_VERSION, {}, tmp_path)
