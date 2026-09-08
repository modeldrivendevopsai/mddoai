from generation_toolkit.prompt_config.learned_constraints import add_learned_constraints, remove_learned_constraint
from generation_toolkit.prompt_config.storage import load_config, save_config

_BASE = {"system_prompt": "x", "attachments": []}


def test_add_learned_constraints_to_a_config_with_none_yet(tmp_path):
    save_config(tmp_path, "generation", "gitlab", _BASE, {}, tmp_path)

    add_learned_constraints(tmp_path, "generation", "gitlab", ["Fix: use camelCase"], {}, tmp_path)

    assert load_config(tmp_path, "generation", "gitlab")["learned_constraints"] == ["Fix: use camelCase"]


def test_add_learned_constraints_appends_to_existing_ones(tmp_path):
    save_config(tmp_path, "generation", "gitlab", {**_BASE, "learned_constraints": ["first"]}, {}, tmp_path)

    add_learned_constraints(tmp_path, "generation", "gitlab", ["second"], {}, tmp_path)

    assert load_config(tmp_path, "generation", "gitlab")["learned_constraints"] == ["first", "second"]


def test_add_learned_constraints_deduplicates(tmp_path):
    save_config(tmp_path, "generation", "gitlab", {**_BASE, "learned_constraints": ["first"]}, {}, tmp_path)

    add_learned_constraints(tmp_path, "generation", "gitlab", ["first", "second"], {}, tmp_path)

    assert load_config(tmp_path, "generation", "gitlab")["learned_constraints"] == ["first", "second"]


def test_add_learned_constraints_creates_a_new_version(tmp_path):
    saved = save_config(tmp_path, "generation", "gitlab", _BASE, {}, tmp_path)

    updated = add_learned_constraints(tmp_path, "generation", "gitlab", ["Fix: x"], {}, tmp_path)

    assert updated["_version"] != saved["_version"]


def test_remove_learned_constraint(tmp_path):
    save_config(tmp_path, "generation", "gitlab", {**_BASE, "learned_constraints": ["first", "second"]}, {}, tmp_path)

    remove_learned_constraint(tmp_path, "generation", "gitlab", "first", {}, tmp_path)

    assert load_config(tmp_path, "generation", "gitlab")["learned_constraints"] == ["second"]


def test_remove_learned_constraint_that_is_not_present_is_a_no_op(tmp_path):
    save_config(tmp_path, "generation", "gitlab", {**_BASE, "learned_constraints": ["first"]}, {}, tmp_path)

    remove_learned_constraint(tmp_path, "generation", "gitlab", "not-there", {}, tmp_path)

    assert load_config(tmp_path, "generation", "gitlab")["learned_constraints"] == ["first"]
