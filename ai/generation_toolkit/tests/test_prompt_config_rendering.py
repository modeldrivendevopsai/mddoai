from generation_toolkit.prompt_builder import build_prompt
from generation_toolkit.prompt_config.rendering import render_user_content

_CONFIG = {
    "system_prompt": "x",
    "attachments": [
        {"id": "pim_ecore", "name": "PIM artifact", "type": "context", "key": "pim_ecore"},
        {"id": "psm_docs", "name": "Target platform documentation", "type": "context", "key": "psm_docs"},
    ],
}


def test_uses_each_attachments_own_human_name_as_the_section_label():
    prompt = build_prompt({"pim_ecore": "<pim/>", "psm_docs": "docs text"})

    content = render_user_content(_CONFIG, prompt)

    assert "PIM artifact:\n<pim/>" in content
    assert "Target platform documentation:\ndocs text" in content
    assert "pim_ecore:" not in content


def test_falls_back_to_the_raw_key_for_an_id_with_no_matching_attachment():
    prompt = build_prompt({"mystery_key": "value"})

    content = render_user_content(_CONFIG, prompt)

    assert "mystery_key:\nvalue" in content


def test_appends_constraints_when_present():
    prompt = build_prompt({"pim_ecore": "x"}, constraints=["Fix: bad thing"])

    content = render_user_content(_CONFIG, prompt)

    assert content.endswith("Apply these corrections from prior rounds:\n- Fix: bad thing")


def test_omits_constraints_section_when_empty():
    prompt = build_prompt({"pim_ecore": "x"})

    content = render_user_content(_CONFIG, prompt)

    assert "corrections from prior rounds" not in content
