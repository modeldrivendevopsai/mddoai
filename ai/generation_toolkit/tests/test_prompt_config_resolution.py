from generation_toolkit.prompt_config.resolution import render_prompt, resolve_for_call
from generation_toolkit.prompt_config.storage import save_config

_CONFIG = {
    "system_prompt": "x",
    "attachments": [{"id": "pim_ecore", "name": "PIM", "type": "context", "key": "pim_ecore"}],
    "learned_constraints": ["Use camelCase"],
}


def test_resolve_for_call_returns_config_and_resolved_parts(tmp_path):
    save_config(tmp_path, "generation", "default", _CONFIG, {"pim_ecore": ""}, tmp_path)

    config, parts = resolve_for_call(tmp_path, "generation", "default", {"pim_ecore": "<pim/>"}, tmp_path)

    assert config["system_prompt"] == "x"
    assert parts == {"pim_ecore": "<pim/>"}


def test_render_prompt_includes_persisted_learned_constraints(tmp_path):
    save_config(tmp_path, "generation", "default", _CONFIG, {"pim_ecore": ""}, tmp_path)

    prompt = render_prompt(tmp_path, "generation", "default", {"pim_ecore": "<pim/>"}, tmp_path)

    assert prompt["pim_ecore"] == "<pim/>"
    assert "Use camelCase" in prompt["constraints"]


def test_render_prompt_falls_back_to_the_shipped_default(tmp_path):
    (tmp_path / "generation").mkdir()
    (tmp_path / "generation" / "default.default.json").write_text(
        '{"system_prompt": "x", "attachments": []}', encoding="utf-8"
    )

    prompt = render_prompt(tmp_path, "generation", "default", {}, tmp_path)

    assert prompt["constraints"] == ""
