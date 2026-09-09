from generation_toolkit.prompt_config.resolution import render_prompt, resolve_for_call
from generation_toolkit.prompt_config.storage import save_config

_CONFIG = {
    "attachments": [
        {"id": "system", "name": "System prompt", "type": "text", "content": "x"},
        {"id": "pim_ecore", "name": "PIM", "type": "context", "key": "pim_ecore"},
    ],
    "learned_constraints": ["Use camelCase"],
}


def test_resolve_for_call_derives_system_prompt_from_the_first_text_attachment(tmp_path):
    save_config(tmp_path, "generation", "default", _CONFIG, {"pim_ecore": ""}, tmp_path)

    config, parts = resolve_for_call(tmp_path, "generation", "default", {"pim_ecore": "<pim/>"}, tmp_path)

    assert config["system_prompt"] == "x"
    # The system-prompt-role attachment is consumed, not also resolved as
    # a regular part - it must never appear twice, once as the system
    # message and again inside the user message.
    assert parts == {"pim_ecore": "<pim/>"}


def test_resolve_for_call_gives_an_empty_system_prompt_when_the_first_attachment_is_not_text(tmp_path):
    config = {"attachments": [{"id": "pim_ecore", "name": "PIM", "type": "context", "key": "pim_ecore"}]}
    save_config(tmp_path, "generation", "default", config, {"pim_ecore": ""}, tmp_path)

    resolved, parts = resolve_for_call(tmp_path, "generation", "default", {"pim_ecore": "<pim/>"}, tmp_path)

    assert resolved["system_prompt"] == ""
    # Nothing is consumed as the system prompt here, so every attachment
    # still resolves into parts.
    assert parts == {"pim_ecore": "<pim/>"}


def test_resolve_for_call_gives_an_empty_system_prompt_for_an_empty_document(tmp_path):
    save_config(tmp_path, "generation", "default", {"attachments": []}, {}, tmp_path)

    config, parts = resolve_for_call(tmp_path, "generation", "default", {}, tmp_path)

    assert config["system_prompt"] == ""
    assert parts == {}


def test_render_prompt_includes_persisted_learned_constraints(tmp_path):
    save_config(tmp_path, "generation", "default", _CONFIG, {"pim_ecore": ""}, tmp_path)

    prompt = render_prompt(tmp_path, "generation", "default", {"pim_ecore": "<pim/>"}, tmp_path)

    assert prompt["pim_ecore"] == "<pim/>"
    assert "Use camelCase" in prompt["constraints"]


def test_render_prompt_falls_back_to_the_shipped_default(tmp_path):
    (tmp_path / "generation").mkdir()
    (tmp_path / "generation" / "default.default.json").write_text(
        '{"attachments": [{"id": "system", "name": "System prompt", "type": "text", "content": "x"}]}',
        encoding="utf-8",
    )

    prompt = render_prompt(tmp_path, "generation", "default", {}, tmp_path)

    assert prompt["constraints"] == ""
