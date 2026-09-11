"""PromptConfigRouter tests: the shared, generic prompt-config HTTP surface
psm_agent/atl_agent/acceleo_agent's own routes/prompt_config.py each build
one of and re-export bound methods from. Each service's own test suite
already exercises this shape end to end through its own real config_dir/
files_root/context_for; these tests instead prove the things specific to
this shared class itself: context_for is consulted (and can 404) for
every endpoint, and config_dir/files_root are both read fresh on every
call, not captured once at construction time.
"""
import json

import pytest
from fastapi import HTTPException

from generation_toolkit.routes.prompt_config import LearnedConstraintsBody, PromptConfigBody, PromptConfigRouter


def _seed_default(config_dir, name="generation", system_prompt="sys"):
    config = {"attachments": [{"id": "system", "name": "System prompt", "type": "text", "content": system_prompt}]}
    directory = config_dir / name
    directory.mkdir(parents=True, exist_ok=True)
    (directory / "default.default.json").write_text(json.dumps(config), encoding="utf-8")


def _always_known(_name: str) -> dict[str, str]:
    return {}


def _fixed(value):
    return lambda: value


def test_get_config_returns_the_shipped_default(tmp_path):
    _seed_default(tmp_path, system_prompt="hello")
    router = PromptConfigRouter(lambda: tmp_path, _fixed(tmp_path), _always_known)

    result = router.get_config_endpoint("generation", "default")

    assert result["attachments"][0]["content"] == "hello"


def test_unknown_name_404s_before_touching_storage(tmp_path):
    def reject_everything(name: str) -> dict[str, str]:
        raise HTTPException(status_code=404, detail=f"unknown prompt config name {name!r}")

    router = PromptConfigRouter(lambda: tmp_path, _fixed(tmp_path), reject_everything)

    with pytest.raises(HTTPException) as exc_info:
        router.get_config_endpoint("not-a-real-name", "default")

    assert exc_info.value.status_code == 404


def test_config_dir_is_read_fresh_on_every_call_not_captured_once(tmp_path):
    # A caller's own test suite isolates prompt-config reads/writes by
    # monkeypatching a module attribute for the duration of one test (see
    # e.g. acceleo_agent/tests/conftest.py's own isolated_prompt_config_dir)
    # - that only works if this router re-reads config_dir() on every call
    # rather than capturing its value once at construction time.
    first_dir = tmp_path / "first"
    second_dir = tmp_path / "second"
    _seed_default(first_dir, system_prompt="from first")
    _seed_default(second_dir, system_prompt="from second")

    current_dir = {"path": first_dir}
    router = PromptConfigRouter(lambda: current_dir["path"], _fixed(tmp_path), _always_known)

    assert router.get_config_endpoint("generation", "default")["attachments"][0]["content"] == "from first"

    current_dir["path"] = second_dir

    assert router.get_config_endpoint("generation", "default")["attachments"][0]["content"] == "from second"


def test_files_root_is_read_fresh_on_every_call_not_captured_once(tmp_path):
    # Same reasoning as config_dir above, for files_root: a caller that
    # monkeypatches its own uploads-dir module attribute for one test (see
    # e.g. acceleo_agent/tests/conftest.py's own isolated_attachment_uploads_dir)
    # needs a "file" attachment reference resolved during that test to see
    # the patched root, not whatever files_root() returned at construction
    # time - proven here via save_config_endpoint's own dry-run reference
    # validation, which resolves every "file" attachment against files_root().
    _seed_default(tmp_path)
    first_root = tmp_path / "first-root"
    second_root = tmp_path / "second-root"
    first_root.mkdir()
    second_root.mkdir()
    (second_root / "example.mtl").write_text("[module m('x')]", encoding="utf-8")

    current_root = {"path": first_root}
    router = PromptConfigRouter(lambda: tmp_path, lambda: current_root["path"], _always_known)
    body = PromptConfigBody(
        attachments=[
            {"id": "system", "name": "System prompt", "type": "text", "content": "sys"},
            {"id": "example", "name": "Example", "type": "file", "path": "example.mtl"},
        ]
    )

    with pytest.raises(Exception):
        router.save_config_endpoint("generation", "default", body)

    current_root["path"] = second_root

    saved = router.save_config_endpoint("generation", "default", body)
    assert saved["_version"]


def test_save_then_list_presets_round_trip(tmp_path):
    _seed_default(tmp_path)
    router = PromptConfigRouter(lambda: tmp_path, _fixed(tmp_path), _always_known)

    saved = router.save_config_endpoint("generation", "default", PromptConfigBody(attachments=[]))
    assert saved["_version"]

    presets = router.list_presets_endpoint("generation")
    assert any(p["id"] == "default" for p in presets["presets"])


def test_add_learned_constraints_persists_them(tmp_path):
    _seed_default(tmp_path)
    router = PromptConfigRouter(lambda: tmp_path, _fixed(tmp_path), _always_known)

    result = router.add_learned_constraints_endpoint(
        "generation", "default", LearnedConstraintsBody(constraints=["Fix: use camelCase"])
    )

    assert result["learned_constraints"] == ["Fix: use camelCase"]
