import json

import pytest

from clients.agent_service_errors import AgentServiceError
from generation_toolkit.prompt_config.history import list_history
from generation_toolkit.prompt_config.storage import PromptConfigValidationError, load_config, save_config
from helpers import AGENT

_SAMPLE = {"system_prompt": "You are helpful.", "attachments": [{"id": "a", "name": "A", "type": "text", "content": "x"}]}


def test_load_config_falls_back_to_the_shipped_default(config_dir, tmp_path):
    (config_dir / "generation").mkdir()
    (config_dir / "generation" / "default.default.json").write_text(json.dumps(_SAMPLE), encoding="utf-8")

    config = load_config(AGENT, "generation")

    assert config["system_prompt"] == "You are helpful."


def test_load_config_prefers_live_file_over_default(config_dir, tmp_path):
    (config_dir / "generation").mkdir()
    (config_dir / "generation" / "default.default.json").write_text(
        json.dumps({**_SAMPLE, "system_prompt": "default"}), encoding="utf-8"
    )
    (config_dir / "generation" / "default.json").write_text(
        json.dumps({**_SAMPLE, "system_prompt": "edited"}), encoding="utf-8"
    )

    assert load_config(AGENT, "generation")["system_prompt"] == "edited"


def test_load_config_raises_when_nothing_exists(tmp_path):
    with pytest.raises(FileNotFoundError):
        load_config(AGENT, "generation")


def test_load_config_finds_no_config_for_a_path_traversal_name(tmp_path):
    # A name is frequently a real, user-suppliable REST route parameter (see
    # psm_agent/routes/prompt_config.py). The journal is where a name becomes a folder,
    # so it is the one place that decides a name cannot name a config.
    with pytest.raises(FileNotFoundError):
        load_config(AGENT, "../../etc/passwd")


def test_save_config_is_refused_for_a_path_traversal_name(tmp_path):
    with pytest.raises(AgentServiceError) as error:
        save_config(AGENT, "../../etc/passwd", _SAMPLE, {}, tmp_path)

    assert error.value.status_code == 404


def test_a_bare_parent_directory_name_never_reaches_the_journal(tmp_path):
    # An HTTP library would collapse ".." out of the address, so the client refuses it.
    with pytest.raises(ValueError):
        load_config(AGENT, "..")


def test_save_config_writes_live_file_and_stamps_a_version(tmp_path):
    saved = save_config(AGENT, "generation", _SAMPLE, {}, tmp_path)

    assert saved["_version"]
    assert load_config(AGENT, "generation")["_version"] == saved["_version"]


def test_save_config_keeps_a_history_copy_of_every_version(tmp_path):
    saved = save_config(AGENT, "generation", _SAMPLE, {}, tmp_path)

    assert list_history(AGENT, "generation") == [saved["_version"]]


def test_save_config_is_a_no_op_when_attachments_are_unchanged(tmp_path):
    # The real backstop against version-history bloat: an autosaving UI can
    # end up calling save_config with the exact same attachments more than
    # once (a retry after a perceived timeout that actually landed, a
    # debounce firing again for a draft that settled back to its last-saved
    # shape, two tabs open on the same config) - "nothing to commit" must
    # behave like it does in any other real version-control system, not
    # silently mint an identical new version each time.
    first = save_config(AGENT, "generation", _SAMPLE, {}, tmp_path)

    second = save_config(AGENT, "generation", _SAMPLE, {}, tmp_path)

    assert second["_version"] == first["_version"]
    assert list_history(AGENT, "generation") == [first["_version"]]


def test_save_config_still_versions_a_genuine_change_to_attachments(tmp_path):
    first = save_config(AGENT, "generation", _SAMPLE, {}, tmp_path)

    changed = {**_SAMPLE, "attachments": [{**_SAMPLE["attachments"][0], "content": "y"}]}
    second = save_config(AGENT, "generation", changed, {}, tmp_path)

    assert second["_version"] != first["_version"]
    assert list_history(AGENT, "generation") == [second["_version"], first["_version"]]


def test_save_config_rejects_a_config_with_a_broken_file_attachment(config_dir, tmp_path):
    broken = {
        "system_prompt": "x",
        "attachments": [{"id": "a", "name": "A", "type": "file", "path": "missing.ecore"}],
    }

    with pytest.raises(PromptConfigValidationError):
        save_config(AGENT, "generation", broken, {}, tmp_path)

    assert not (config_dir / "generation" / "default.json").exists()


def test_save_config_rejects_a_config_with_an_unknown_context_key(tmp_path):
    broken = {
        "system_prompt": "x",
        "attachments": [{"id": "a", "name": "A", "type": "context", "key": "nope"}],
    }

    with pytest.raises(PromptConfigValidationError):
        save_config(AGENT, "generation", broken, {"pim_artifact": "x"}, tmp_path)


def test_save_config_never_writes_learned_constraints_into_the_live_file(tmp_path):
    saved = save_config(AGENT, "generation", {**_SAMPLE, "learned_constraints": ["x"]}, {}, tmp_path)

    assert "learned_constraints" not in saved
    assert "learned_constraints" not in load_config(AGENT, "generation")


def test_save_config_migrates_a_legacy_embedded_value_before_stripping_it(config_dir, tmp_path):
    """The exact race this guards against: a save (or, for real, a revert
    or restore) reaching a config that still carries the older embedded
    "learned_constraints" shape BEFORE anything has ever read (and thus
    migrated) it - this call's own strip must not become the reason that
    value is lost, since nothing else may ever get the chance to migrate it
    afterwards (learned_constraints.py's own load_constraints only ever
    reads disk, and this call already overwrote what was there)."""
    save_config(AGENT, "generation", {**_SAMPLE, "learned_constraints": ["already there"]}, {}, tmp_path)

    on_disk = json.loads((config_dir / "generation" / "constraints.json").read_text(encoding="utf-8"))
    assert on_disk == {"constraints": ["already there"]}


def test_save_config_does_not_overwrite_an_already_migrated_store(config_dir, tmp_path):
    """A later save carrying a stale or merely-echoed "learned_constraints"
    value (e.g. a caller round-tripping an earlier GET response) must never
    clobber real, already-accumulated constraints with it."""
    save_config(AGENT, "generation", {**_SAMPLE, "learned_constraints": ["real"]}, {}, tmp_path)

    save_config(AGENT, "generation", {**_SAMPLE, "learned_constraints": ["stale round-trip"]}, {}, tmp_path)

    on_disk = json.loads((config_dir / "generation" / "constraints.json").read_text(encoding="utf-8"))
    assert on_disk == {"constraints": ["real"]}
