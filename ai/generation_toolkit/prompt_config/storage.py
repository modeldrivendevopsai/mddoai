"""Load and save a prompt config. A config is kept by the journal under the agent's own
name (such as "psm") and the config's name, so these functions take an agent and a
name and own no path.

A config is a plain dict: {"attachments": [...], "_version": str}. "_version" is
stamped by the journal when it keeps a save, never set by a caller. There is no
separate stored "system_prompt" field: a config's own first "text" attachment IS the
system message, resolved that way by resolution.py, so this module persists
"attachments" like any other key, with no opinion on what an entry in it means.

save_config strips any "learned_constraints" key it is handed before saving. The
constraints live in their own store (see learned_constraints.py), and every path that
produces a live config (a plain save, a revert, a restore) funnels through here.
Without this, a shipped config that still carries the older embedded shape would keep
writing that stale array back into every new version. Stripping alone would race the
one-time migration, which only runs the first time constraints are read, so save_config
migrates from the config it was given before it strips (see
learned_constraints.migrate_if_missing).
"""

from clients import journal_prompt_client
from generation_toolkit.attachments.resolve import resolve_attachments


class PromptConfigValidationError(ValueError):
    """A config failed its own dry-run resolution, an attachment's file
    doesn't exist, or a context key isn't real. Raised by save_config
    before anything is written, and reusable by references.check_references
    for the same check against an already-saved config."""


def load_config(agent: str, name: str) -> dict:
    """The live config for name, falling back to its shipped default, so an unsaved
    name still returns a real, working config. Raises FileNotFoundError only if
    neither exists, which should never happen once a default is committed."""
    config = journal_prompt_client.get_config(agent, name)
    if config is None:
        raise FileNotFoundError(f"no config found for {name!r} of {agent!r}, and no shipped default")
    return config


def save_config(
    agent: str,
    name: str,
    config: dict,
    context_values: dict[str, str],
    files_root: list,
) -> dict:
    """Validates config by actually resolving it, the same resolution a real generation
    call would do, and has the journal keep it as the live config and a new version.
    Raises PromptConfigValidationError, naming the real broken attachment, instead of
    keeping something that would only fail on the next real run. context_values and
    files_root are a realistic sample the caller already has on hand, not synthetic
    placeholders, so this dry run is a genuine check.

    A save with the live config's own attachments changes nothing: the journal returns
    the live config instead of minting a version for content nobody changed, so an
    autosaving UI that sends the same attachments twice does not pile up identical
    versions. Only "attachments" are compared, never "learned_constraints", which are
    stripped below and kept entirely separately."""
    try:
        resolve_attachments(config.get("attachments", []), context_values, files_root)
    except Exception as e:
        raise PromptConfigValidationError(str(e)) from e

    # A local import: learned_constraints.py already imports this module at
    # module level, so importing it back at module level here would be
    # circular.
    from . import learned_constraints

    if "learned_constraints" in config:
        learned_constraints.migrate_if_missing(agent, name, config["learned_constraints"])

    config = {key: value for key, value in config.items() if key != "learned_constraints"}
    return journal_prompt_client.save_config(agent, name, config)
