import json
import threading

from generation_toolkit.prompt_config._paths import constraints_path
from generation_toolkit.prompt_config.learned_constraints import (
    add_learned_constraints,
    load_constraints,
    remove_learned_constraint,
)
from generation_toolkit.prompt_config.storage import load_config, save_config

_BASE = {"system_prompt": "x", "attachments": []}


def test_add_learned_constraints_to_a_config_with_none_yet(tmp_path):
    save_config(tmp_path, "generation", _BASE, {}, tmp_path)

    result = add_learned_constraints(tmp_path, "generation", ["Fix: use camelCase"])

    assert result == ["Fix: use camelCase"]
    assert load_constraints(tmp_path, "generation") == ["Fix: use camelCase"]


def test_add_learned_constraints_appends_to_existing_ones(tmp_path):
    save_config(tmp_path, "generation", _BASE, {}, tmp_path)
    add_learned_constraints(tmp_path, "generation", ["first"])

    result = add_learned_constraints(tmp_path, "generation", ["second"])

    assert result == ["first", "second"]


def test_add_learned_constraints_deduplicates(tmp_path):
    save_config(tmp_path, "generation", _BASE, {}, tmp_path)
    add_learned_constraints(tmp_path, "generation", ["first"])

    result = add_learned_constraints(tmp_path, "generation", ["first", "second"])

    assert result == ["first", "second"]


def test_add_learned_constraints_never_touches_the_versioned_config(tmp_path):
    """Constraints live entirely outside storage.save_config's own
    versioned file now - this is the actual fix for reverting silently
    wiping accumulated constraints: there's no version of the config for a
    revert/restore to swap that constraints were ever a part of."""
    saved = save_config(tmp_path, "generation", _BASE, {}, tmp_path)

    add_learned_constraints(tmp_path, "generation", ["Fix: x"])

    assert load_config(tmp_path, "generation")["_version"] == saved["_version"]
    assert "learned_constraints" not in load_config(tmp_path, "generation")


def test_remove_learned_constraint(tmp_path):
    save_config(tmp_path, "generation", _BASE, {}, tmp_path)
    add_learned_constraints(tmp_path, "generation", ["first", "second"])

    result = remove_learned_constraint(tmp_path, "generation", "first")

    assert result == ["second"]
    assert load_constraints(tmp_path, "generation") == ["second"]


def test_remove_learned_constraint_that_is_not_present_is_a_no_op(tmp_path):
    save_config(tmp_path, "generation", _BASE, {}, tmp_path)
    add_learned_constraints(tmp_path, "generation", ["first"])

    result = remove_learned_constraint(tmp_path, "generation", "not-there")

    assert result == ["first"]


def test_load_constraints_is_empty_when_nothing_was_ever_added(tmp_path):
    save_config(tmp_path, "generation", _BASE, {}, tmp_path)

    assert load_constraints(tmp_path, "generation") == []


def test_load_constraints_migrates_once_from_an_older_embedded_shape(tmp_path):
    """Before constraints got their own store, they lived inside the
    versioned config's own "learned_constraints" key - an existing config
    saved that way must not silently lose them the first time this newer
    code reads it."""
    save_config(tmp_path, "generation", {**_BASE, "learned_constraints": ["already there"]}, {}, tmp_path)

    result = load_constraints(tmp_path, "generation")

    assert result == ["already there"]
    # Migrated for real, not just faked for this one read - the file this
    # module owns now exists on disk with that same content.
    on_disk = json.loads(constraints_path(tmp_path, "generation").read_text(encoding="utf-8"))
    assert on_disk == {"constraints": ["already there"]}


def test_concurrent_migration_read_and_add_never_lose_either_ones_write(tmp_path):
    """The real race the shared _write_lock (see _load_constraints_locked)
    closes: before constraints.json exists, an unlocked migration-on-read
    could land its own stale write for the legacy value AFTER a concurrent
    add's own locked, merged write - silently erasing what add just
    persisted. A barrier forces both to start their own read-or-migrate
    step at the same moment; with both funneled through the same lock, one
    fully completes before the other's own critical section can even
    begin, so the final state always has both values regardless of which
    one actually wins the race to start first."""
    save_config(tmp_path, "generation", {**_BASE, "learned_constraints": ["legacy"]}, {}, tmp_path)
    barrier = threading.Barrier(2)

    def reader():
        barrier.wait()
        load_constraints(tmp_path, "generation")

    def writer():
        barrier.wait()
        add_learned_constraints(tmp_path, "generation", ["new"])

    threads = [threading.Thread(target=reader), threading.Thread(target=writer)]
    for t in threads:
        t.start()
    for t in threads:
        t.join()

    assert load_constraints(tmp_path, "generation") == ["legacy", "new"]


def test_load_constraints_migration_does_not_recur_once_the_store_exists(tmp_path):
    """A first read migrates and writes constraints.json; a config saved
    again afterwards (still carrying the old embedded key, e.g. from a
    caller that round-trips whatever GET handed back) must not resurrect
    stale embedded constraints on a later read - the store, once it
    exists, is the one source of truth."""
    save_config(tmp_path, "generation", {**_BASE, "learned_constraints": ["stale"]}, {}, tmp_path)
    load_constraints(tmp_path, "generation")

    remove_learned_constraint(tmp_path, "generation", "stale")
    save_config(tmp_path, "generation", {**_BASE, "learned_constraints": ["stale"]}, {}, tmp_path)

    assert load_constraints(tmp_path, "generation") == []
