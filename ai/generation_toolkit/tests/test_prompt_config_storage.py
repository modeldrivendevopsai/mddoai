import json

import pytest

from generation_toolkit.attachments.files import PathSegmentError
from generation_toolkit.prompt_config._paths import constraints_path
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


def test_save_config_never_writes_learned_constraints_into_the_live_file(tmp_path):
    saved = save_config(tmp_path, "generation", {**_SAMPLE, "learned_constraints": ["x"]}, {}, tmp_path)

    assert "learned_constraints" not in saved
    assert "learned_constraints" not in load_config(tmp_path, "generation")


def test_save_config_migrates_a_legacy_embedded_value_before_stripping_it(tmp_path):
    """The exact race this guards against: a save (or, for real, a revert
    or restore) reaching a config that still carries the older embedded
    "learned_constraints" shape BEFORE anything has ever read (and thus
    migrated) it - this call's own strip must not become the reason that
    value is lost, since nothing else may ever get the chance to migrate it
    afterwards (learned_constraints.py's own load_constraints only ever
    reads disk, and this call already overwrote what was there)."""
    save_config(tmp_path, "generation", {**_SAMPLE, "learned_constraints": ["already there"]}, {}, tmp_path)

    on_disk = json.loads(constraints_path(tmp_path, "generation").read_text(encoding="utf-8"))
    assert on_disk == {"constraints": ["already there"]}


def test_save_config_does_not_overwrite_an_already_migrated_store(tmp_path):
    """A later save carrying a stale or merely-echoed "learned_constraints"
    value (e.g. a caller round-tripping an earlier GET response) must never
    clobber real, already-accumulated constraints with it."""
    save_config(tmp_path, "generation", {**_SAMPLE, "learned_constraints": ["real"]}, {}, tmp_path)

    save_config(tmp_path, "generation", {**_SAMPLE, "learned_constraints": ["stale round-trip"]}, {}, tmp_path)

    on_disk = json.loads(constraints_path(tmp_path, "generation").read_text(encoding="utf-8"))
    assert on_disk == {"constraints": ["real"]}
