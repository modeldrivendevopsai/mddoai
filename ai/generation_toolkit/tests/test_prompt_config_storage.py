import json

import pytest

from generation_toolkit.attachments.files import PathSegmentError
from generation_toolkit.prompt_config.storage import PromptConfigValidationError, load_config, save_config

_SAMPLE = {"system_prompt": "You are helpful.", "attachments": [{"id": "a", "name": "A", "type": "text", "content": "x"}]}


def test_load_config_falls_back_to_the_shipped_default(tmp_path):
    (tmp_path / "generation").mkdir()
    (tmp_path / "generation" / "default.default.json").write_text(json.dumps(_SAMPLE), encoding="utf-8")

    config = load_config(tmp_path, "generation")

    assert config["system_prompt"] == "You are helpful."


def test_load_config_prefers_live_file_over_default(tmp_path):
    (tmp_path / "generation").mkdir()
    (tmp_path / "generation" / "default.default.json").write_text(
        json.dumps({**_SAMPLE, "system_prompt": "default"}), encoding="utf-8"
    )
    (tmp_path / "generation" / "default.json").write_text(
        json.dumps({**_SAMPLE, "system_prompt": "edited"}), encoding="utf-8"
    )

    assert load_config(tmp_path, "generation")["system_prompt"] == "edited"


def test_load_config_raises_when_nothing_exists(tmp_path):
    with pytest.raises(FileNotFoundError):
        load_config(tmp_path, "generation")


def test_load_config_rejects_a_path_traversal_name(tmp_path):
    # name is frequently a real, user-suppliable REST route parameter (see
    # psm_agent/routes/prompt_config.py) - this is the one real
    # path-construction choke point every prompt_config function goes
    # through, so this check protects every caller, not just this one.
    with pytest.raises(PathSegmentError):
        load_config(tmp_path, "../../etc/passwd")


def test_save_config_rejects_a_path_traversal_name(tmp_path):
    with pytest.raises(PathSegmentError):
        save_config(tmp_path, "../../etc/passwd", _SAMPLE, {}, tmp_path)


def test_load_config_rejects_a_bare_parent_directory_name(tmp_path):
    # No "/" here (a single ".." segment already passes the character-class
    # check on its own, "." is allowed) - the dedicated all-dots rejection
    # is what actually catches this one.
    with pytest.raises(PathSegmentError):
        load_config(tmp_path, "..")


def test_save_config_writes_live_file_and_stamps_a_version(tmp_path):
    saved = save_config(tmp_path, "generation", _SAMPLE, {}, tmp_path)

    assert saved["_version"]
    assert load_config(tmp_path, "generation")["_version"] == saved["_version"]


def test_save_config_keeps_an_immutable_history_copy(tmp_path):
    saved = save_config(tmp_path, "generation", _SAMPLE, {}, tmp_path)

    history_file = tmp_path / "generation" / "history" / f"default.{saved['_version']}.json"
    assert history_file.is_file()


def test_save_config_rejects_a_config_with_a_broken_file_attachment(tmp_path):
    broken = {
        "system_prompt": "x",
        "attachments": [{"id": "a", "name": "A", "type": "file", "path": "missing.ecore"}],
    }

    with pytest.raises(PromptConfigValidationError):
        save_config(tmp_path, "generation", broken, {}, tmp_path)

    assert not (tmp_path / "generation" / "default.json").exists()


def test_save_config_rejects_a_config_with_an_unknown_context_key(tmp_path):
    broken = {
        "system_prompt": "x",
        "attachments": [{"id": "a", "name": "A", "type": "context", "key": "nope"}],
    }

    with pytest.raises(PromptConfigValidationError):
        save_config(tmp_path, "generation", broken, {"pim_artifact": "x"}, tmp_path)
