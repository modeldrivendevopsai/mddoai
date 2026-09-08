"""Promoting a per-run correction into a preset's own permanent baseline.

Two different, related things are both called "constraints" in this
codebase, and this module is the bridge between them:
  - A per-run constraint (integration_runner's own IntegrationRun.constraints)
    is a live correction a human adds during one run's retries, reset the
    next time that run resets. It never touches a saved config on its own.
  - A "learned" constraint (this module) lives inside the saved config
    itself, under its own "learned_constraints" key, and is applied to
    EVERY future run that loads this (name, preset), not just the run that
    discovered it.

This mirrors the real, validated MDDOAI research methodology directly: a
constraint discovered while generating one platform's metamodel was
carried forward as a starting constraint for the next platform's own
prompt, once it had actually proven itself, and that carried-forward
constraint library measurably cut round counts across platforms. A
constraint that proved itself during one real run (a human decides which
ones, this module doesn't guess) gets promoted here so the next run of
that preset, or a brand new platform starting from the same preset, starts
with that lesson already applied instead of rediscovering it from a fresh
retry loop.

Whichever stage-owned function assembles its final constraints list for a
real generation call (e.g. psm_agent/generation.py's own generate()) is
responsible for combining config["learned_constraints"] with that run's
own live corrections before calling generation_toolkit.generation_agent.run_with_retry,
this module only manages the saved, permanent side.
"""
from pathlib import Path

from . import storage


def add_learned_constraints(
    config_dir: str | Path,
    name: str,
    preset: str,
    constraints: list[str],
    context_values: dict[str, str],
    files_root: str | Path,
) -> dict:
    """Appends `constraints` to this preset's own saved
    "learned_constraints" list, deduplicated (a constraint promoted twice
    stays a single entry) but otherwise order-preserving, and saves the
    result via storage.save_config, so promoting a constraint is versioned
    and revertible exactly like any other edit to this config."""
    config = storage.load_config(config_dir, name, preset)
    existing = config.get("learned_constraints", [])
    merged = list(existing)
    for constraint in constraints:
        if constraint not in merged:
            merged.append(constraint)
    updated = {**config, "learned_constraints": merged}
    return storage.save_config(config_dir, name, preset, updated, context_values, files_root)


def remove_learned_constraint(
    config_dir: str | Path,
    name: str,
    preset: str,
    constraint: str,
    context_values: dict[str, str],
    files_root: str | Path,
) -> dict:
    """The reverse of add_learned_constraints: a promoted constraint that
    turns out to be wrong, or no longer relevant, is removed the same
    versioned way, not left to accumulate forever."""
    config = storage.load_config(config_dir, name, preset)
    remaining = [c for c in config.get("learned_constraints", []) if c != constraint]
    updated = {**config, "learned_constraints": remaining}
    return storage.save_config(config_dir, name, preset, updated, context_values, files_root)
