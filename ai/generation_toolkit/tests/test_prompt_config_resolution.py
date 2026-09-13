from generation_toolkit.prompt_config.learned_constraints import add_learned_constraints
from generation_toolkit.prompt_config.resolution import render_prompt, resolve_config, resolve_for_call
from generation_toolkit.prompt_config.storage import save_config

_CONFIG = {
    "attachments": [
        {"id": "system", "name": "System prompt", "type": "text", "content": "x"},
        {"id": "pim_ecore", "name": "PIM", "type": "context", "key": "pim_ecore"},
    ],
    "learned_constraints": ["Use camelCase"],
}


def test_resolve_for_call_derives_system_prompt_from_the_first_text_attachment(tmp_path):
    save_config(tmp_path, "generation", _CONFIG, {"pim_ecore": ""}, tmp_path)

    config, parts = resolve_for_call(tmp_path, "generation", {"pim_ecore": "<pim/>"}, tmp_path)

    assert config["system_prompt"] == "x"
    # The system-prompt-role attachment is consumed, not also resolved as
    # a regular part - it must never appear twice, once as the system
    # message and again inside the user message.
    assert parts == {"pim_ecore": "<pim/>"}


def test_resolve_for_call_gives_an_empty_system_prompt_when_the_first_attachment_is_not_text(tmp_path):
    config = {"attachments": [{"id": "pim_ecore", "name": "PIM", "type": "context", "key": "pim_ecore"}]}
    save_config(tmp_path, "generation", config, {"pim_ecore": ""}, tmp_path)

    resolved, parts = resolve_for_call(tmp_path, "generation", {"pim_ecore": "<pim/>"}, tmp_path)

    assert resolved["system_prompt"] == ""
    # Nothing is consumed as the system prompt here, so every attachment
    # still resolves into parts.
    assert parts == {"pim_ecore": "<pim/>"}


def test_resolve_for_call_gives_an_empty_system_prompt_for_an_empty_document(tmp_path):
    save_config(tmp_path, "generation", {"attachments": []}, {}, tmp_path)

    config, parts = resolve_for_call(tmp_path, "generation", {}, tmp_path)

    assert config["system_prompt"] == ""
    assert parts == {}


def test_resolve_for_call_merges_in_constraints_from_their_own_separate_store(tmp_path):
    """learned_constraints are no longer part of the versioned config file
    at all (see learned_constraints.py) - resolve_for_call is responsible
    for merging in the real, current ones from their own store, not
    reading a "learned_constraints" key that _CONFIG's own saved JSON may
    or may not still carry."""
    config_with_no_embedded_constraints = {"attachments": _CONFIG["attachments"]}
    save_config(tmp_path, "generation", config_with_no_embedded_constraints, {"pim_ecore": ""}, tmp_path)
    add_learned_constraints(tmp_path, "generation", ["Use camelCase"])

    config, _ = resolve_for_call(tmp_path, "generation", {"pim_ecore": "<pim/>"}, tmp_path)

    assert config["learned_constraints"] == ["Use camelCase"]


def test_render_prompt_includes_persisted_learned_constraints(tmp_path):
    save_config(tmp_path, "generation", {"attachments": _CONFIG["attachments"]}, {"pim_ecore": ""}, tmp_path)
    add_learned_constraints(tmp_path, "generation", ["Use camelCase"])

    prompt = render_prompt(tmp_path, "generation", {"pim_ecore": "<pim/>"}, tmp_path)

    assert prompt["pim_ecore"] == "<pim/>"
    assert "Use camelCase" in prompt["constraints"]


def test_resolve_config_resolves_a_given_config_without_touching_disk(tmp_path):
    """resolve_for_call above always loads from config_dir first - this is
    the entry point a "preview my current, unsaved draft" caller uses
    instead, against a config it already has in hand, with config_dir/name
    playing no role at all (nothing is saved or read for this config)."""
    config, parts = resolve_config(_CONFIG, {"pim_ecore": "<pim/>"}, tmp_path)

    assert config["system_prompt"] == "x"
    assert parts == {"pim_ecore": "<pim/>"}
    # tmp_path was never written to - resolve_config takes no config_dir at
    # all, so there's nothing on disk to find in the first place.
    assert not (tmp_path / "generation").exists()


def test_resolve_for_call_is_resolve_config_after_loading_from_disk(tmp_path):
    """The two real entry points (a live call loading its own saved config,
    a preview resolving a config a caller already has) must resolve
    identically given the same config - resolve_for_call is just
    resolve_config with a load_config in front of it, not a second,
    independently-maintained implementation of the same resolution."""
    save_config(tmp_path, "generation", _CONFIG, {"pim_ecore": ""}, tmp_path)

    from_disk = resolve_for_call(tmp_path, "generation", {"pim_ecore": "<pim/>"}, tmp_path)
    direct = resolve_config(_CONFIG, {"pim_ecore": "<pim/>"}, tmp_path)

    # from_disk's config carries save_config's own stamped "_version", the
    # one real difference a fresh save always adds - drop it before
    # comparing so this checks the resolution itself, not that bookkeeping.
    from_disk_config = {k: v for k, v in from_disk[0].items() if k != "_version"}
    assert from_disk_config == direct[0]
    assert from_disk[1] == direct[1]


def test_render_prompt_falls_back_to_the_shipped_default(tmp_path):
    (tmp_path / "generation").mkdir()
    (tmp_path / "generation" / "default.default.json").write_text(
        '{"attachments": [{"id": "system", "name": "System prompt", "type": "text", "content": "x"}]}',
        encoding="utf-8",
    )

    prompt = render_prompt(tmp_path, "generation", {}, tmp_path)

    assert prompt["constraints"] == ""
