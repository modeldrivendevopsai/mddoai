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

from generation_toolkit.prompt_config.history import SHIPPED_DEFAULT_VERSION
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

    result = router.get_config_endpoint("generation")

    assert result["attachments"][0]["content"] == "hello"


def test_unknown_name_404s_before_touching_storage(tmp_path):
    def reject_everything(name: str) -> dict[str, str]:
        raise HTTPException(status_code=404, detail=f"unknown prompt config name {name!r}")

    router = PromptConfigRouter(lambda: tmp_path, _fixed(tmp_path), reject_everything)

    with pytest.raises(HTTPException) as exc_info:
        router.get_config_endpoint("not-a-real-name")

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

    assert router.get_config_endpoint("generation")["attachments"][0]["content"] == "from first"

    current_dir["path"] = second_dir

    assert router.get_config_endpoint("generation")["attachments"][0]["content"] == "from second"


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
        router.save_config_endpoint("generation", body)

    current_root["path"] = second_root

    saved = router.save_config_endpoint("generation", body)
    assert saved["_version"]


def test_save_then_get_config_round_trip(tmp_path):
    _seed_default(tmp_path)
    router = PromptConfigRouter(lambda: tmp_path, _fixed(tmp_path), _always_known)

    saved = router.save_config_endpoint("generation", PromptConfigBody(attachments=[]))
    assert saved["_version"]

    assert router.get_config_endpoint("generation")["_version"] == saved["_version"]


def test_add_learned_constraints_persists_them(tmp_path):
    _seed_default(tmp_path)
    router = PromptConfigRouter(lambda: tmp_path, _fixed(tmp_path), _always_known)

    result = router.add_learned_constraints_endpoint(
        "generation", LearnedConstraintsBody(constraints=["Fix: use camelCase"])
    )

    assert result["learned_constraints"] == ["Fix: use camelCase"]


def test_get_config_endpoint_includes_current_learned_constraints(tmp_path):
    _seed_default(tmp_path)
    router = PromptConfigRouter(lambda: tmp_path, _fixed(tmp_path), _always_known)
    router.add_learned_constraints_endpoint("generation", LearnedConstraintsBody(constraints=["Fix: x"]))

    assert router.get_config_endpoint("generation")["learned_constraints"] == ["Fix: x"]


def test_save_config_endpoint_does_not_let_learned_constraints_through_the_body(tmp_path):
    """PromptConfigBody has no learned_constraints field any more - Save
    only ever touches attachments. A caller's own request JSON might still
    carry an old "learned_constraints" key (e.g. a client round-tripping
    whatever GET handed it), and it must be silently ignored, not written
    into the versioned config, and never mistaken for a real edit to the
    separate constraints store."""
    _seed_default(tmp_path)
    router = PromptConfigRouter(lambda: tmp_path, _fixed(tmp_path), _always_known)
    router.add_learned_constraints_endpoint("generation", LearnedConstraintsBody(constraints=["existing"]))

    body = PromptConfigBody.model_validate({"attachments": [], "learned_constraints": ["ignored"]})
    saved = router.save_config_endpoint("generation", body)

    # The real, separate store still has exactly what was actually added
    # through the real endpoint above - untouched by this save.
    assert saved["learned_constraints"] == ["existing"]


def test_restoring_the_shipped_default_preserves_learned_constraints(tmp_path):
    """The actual bug this whole separate store exists to fix: reverting
    the prompt's own text/attachments back to the shipped default (now just
    restore_endpoint called with SHIPPED_DEFAULT_VERSION, not a separate
    /revert endpoint) must never discard constraints a human already
    promoted - they're a different, permanent kind of state, not part of
    what "default" means."""
    _seed_default(tmp_path)
    router = PromptConfigRouter(lambda: tmp_path, _fixed(tmp_path), _always_known)
    router.save_config_endpoint("generation", PromptConfigBody(attachments=[{"id": "a", "name": "a", "type": "text", "content": "edited"}]))
    router.add_learned_constraints_endpoint("generation", LearnedConstraintsBody(constraints=["Fix: keep me"]))

    reverted = router.restore_endpoint("generation", SHIPPED_DEFAULT_VERSION)

    assert reverted["learned_constraints"] == ["Fix: keep me"]


def test_history_endpoint_includes_the_shipped_default_as_the_oldest_entry(tmp_path):
    _seed_default(tmp_path)
    router = PromptConfigRouter(lambda: tmp_path, _fixed(tmp_path), _always_known)

    versions = router.history_endpoint("generation")["versions"]

    assert versions == [SHIPPED_DEFAULT_VERSION]


def test_restore_endpoint_preserves_learned_constraints(tmp_path):
    _seed_default(tmp_path)
    router = PromptConfigRouter(lambda: tmp_path, _fixed(tmp_path), _always_known)
    saved = router.save_config_endpoint("generation", PromptConfigBody(attachments=[]))
    router.add_learned_constraints_endpoint("generation", LearnedConstraintsBody(constraints=["Fix: keep me"]))

    restored = router.restore_endpoint("generation", saved["_version"])

    assert restored["learned_constraints"] == ["Fix: keep me"]
