"""routes/prompt_config.py unit tests: each endpoint called directly as a
plain function (no TestClient needed), the same convention psm_agent's own
routes/test_prompt_config.py uses. Every test requests the
isolated_prompt_config_dir fixture (see conftest.py) so nothing here ever
touches the real, git-committed ai/acceleo_agent/prompts/ directory.

This service has only one real config name, "generation" - _context_for
404s on anything else, tested below.
"""
import json

import pytest
from fastapi import HTTPException

from routes.prompt_config import (
    LearnedConstraintsBody,
    PromptConfigBody,
    RemoveLearnedConstraintBody,
    add_learned_constraints_endpoint,
    check_references_endpoint,
    diff_endpoint,
    get_config_endpoint,
    history_endpoint,
    list_presets_endpoint,
    preview_endpoint,
    promote_to_default_endpoint,
    remove_learned_constraint_endpoint,
    restore_endpoint,
    revert_endpoint,
    save_config_endpoint,
)


def _system_prompt(content: str) -> dict:
    return {"id": "system", "name": "System prompt", "type": "text", "content": content}


_MINIMAL_BODY = PromptConfigBody(attachments=[])


def _seed_default(isolated_prompt_config_dir, system_prompt="sys", attachments=None):
    config = {"attachments": [_system_prompt(system_prompt), *(attachments or [])]}
    directory = isolated_prompt_config_dir / "generation"
    directory.mkdir(parents=True, exist_ok=True)
    (directory / "default.default.json").write_text(json.dumps(config), encoding="utf-8")
    return config


def test_get_config_returns_the_shipped_default(isolated_prompt_config_dir):
    _seed_default(isolated_prompt_config_dir, system_prompt="hello")

    result = get_config_endpoint("generation", "default")

    assert result["attachments"][0]["content"] == "hello"


def test_get_config_404s_for_an_unknown_name(isolated_prompt_config_dir):
    with pytest.raises(HTTPException) as exc_info:
        get_config_endpoint("not-a-real-name", "default")

    assert exc_info.value.status_code == 404


def test_save_config_persists_and_returns_a_stamped_version(isolated_prompt_config_dir):
    _seed_default(isolated_prompt_config_dir)

    result = save_config_endpoint("generation", "default", _MINIMAL_BODY)

    assert result["_version"]
    assert get_config_endpoint("generation", "default")["_version"] == result["_version"]


def test_save_config_rejects_a_broken_attachment_with_400(isolated_prompt_config_dir):
    _seed_default(isolated_prompt_config_dir)
    broken = PromptConfigBody(
        attachments=[_system_prompt("x"), {"id": "a", "name": "A", "type": "file", "path": "missing.mtl"}]
    )

    with pytest.raises(HTTPException) as exc_info:
        save_config_endpoint("generation", "default", broken)

    assert exc_info.value.status_code == 400


def test_list_presets_includes_default(isolated_prompt_config_dir):
    _seed_default(isolated_prompt_config_dir)

    result = list_presets_endpoint("generation")

    assert any(p["id"] == "default" for p in result["presets"])


def test_history_lists_every_saved_version(isolated_prompt_config_dir):
    _seed_default(isolated_prompt_config_dir)
    save_config_endpoint("generation", "default", _MINIMAL_BODY)
    save_config_endpoint("generation", "default", _MINIMAL_BODY)

    result = history_endpoint("generation", "default")

    assert len(result["versions"]) == 2


def test_diff_reports_no_changes_between_a_version_and_itself(isolated_prompt_config_dir):
    _seed_default(isolated_prompt_config_dir)
    saved = save_config_endpoint("generation", "default", _MINIMAL_BODY)

    result = diff_endpoint("generation", "default", saved["_version"], saved["_version"])

    assert result["attachments_changed"] == []
    assert result["attachments_added"] == []
    assert result["attachments_removed"] == []


def test_restore_brings_back_an_old_version_as_a_new_one(isolated_prompt_config_dir):
    _seed_default(isolated_prompt_config_dir)
    v1 = save_config_endpoint("generation", "default", PromptConfigBody(attachments=[_system_prompt("v1")]))
    save_config_endpoint("generation", "default", PromptConfigBody(attachments=[_system_prompt("v2")]))

    restored = restore_endpoint("generation", "default", v1["_version"])

    assert restored["attachments"][0]["content"] == "v1"
    assert restored["_version"] != v1["_version"]


def test_revert_restores_the_shipped_default(isolated_prompt_config_dir):
    _seed_default(isolated_prompt_config_dir, system_prompt="shipped")
    save_config_endpoint("generation", "default", PromptConfigBody(attachments=[_system_prompt("edited")]))

    reverted = revert_endpoint("generation", "default")

    assert reverted["attachments"][0]["content"] == "shipped"


def test_promote_to_default_updates_the_shipped_file(isolated_prompt_config_dir):
    _seed_default(isolated_prompt_config_dir, system_prompt="old default")
    save_config_endpoint("generation", "default", PromptConfigBody(attachments=[_system_prompt("new and improved")]))

    promote_to_default_endpoint("generation", "default")

    default_file = isolated_prompt_config_dir / "generation" / "default.default.json"
    assert "new and improved" in default_file.read_text(encoding="utf-8")


def test_check_references_reports_nothing_broken_for_a_healthy_config(isolated_prompt_config_dir):
    _seed_default(isolated_prompt_config_dir)

    result = check_references_endpoint("generation", "default")

    assert result["broken"] == []


def test_add_learned_constraints_then_remove(isolated_prompt_config_dir):
    _seed_default(isolated_prompt_config_dir)

    added = add_learned_constraints_endpoint(
        "generation", "default", LearnedConstraintsBody(constraints=["Fix: use camelCase"])
    )
    assert added["learned_constraints"] == ["Fix: use camelCase"]

    removed = remove_learned_constraint_endpoint(
        "generation", "default", RemoveLearnedConstraintBody(constraint="Fix: use camelCase")
    )
    assert removed["learned_constraints"] == []


def test_preview_returns_real_rendered_text_and_per_attachment_content(isolated_prompt_config_dir):
    _seed_default(
        isolated_prompt_config_dir,
        system_prompt="the real system prompt",
        attachments=[{"id": "psm_ecore", "name": "PSM metamodel", "type": "context", "key": "psm_ecore"}],
    )

    result = preview_endpoint("generation", "default")

    assert result["system_prompt"] == "the real system prompt"
    assert "PSM metamodel:" in result["user_content"]
    assert result["attachments"] == {"psm_ecore": ""}
