"""Ties together loading a saved prompt config and resolving its
attachments into build_prompt()'s own ordered parts dict - the one
sequence every real caller of a config-driven prompt needs before it can
either call the LLM for real (a stage's own generate()/compare()-style
function) or show a human a static preview of what it would send.
Extracted here, not duplicated per caller: this sequence carries zero
stage-specific knowledge (it only needs a config_dir, name, context_values,
and files_root, all supplied by the caller), the same "loosely coupled,
same functions, different inputs" shape the rest of this package already
follows.
"""
from pathlib import Path

from generation_toolkit.attachments.resolve import resolve_attachments
from generation_toolkit.prompt_builder import build_prompt

from . import storage


def resolve_for_call(
    config_dir: str | Path,
    name: str,
    context_values: dict[str, str],
    files_root: str | Path | list[str | Path],
) -> tuple[dict, dict[str, str]]:
    """Loads name's real config and resolves its attachments against
    context_values. Returns (config, parts): parts is the ordered dict a
    caller passes straight to generation_agent.run_with_retry() (it calls
    build_prompt() itself once per round), or to build_prompt() directly
    for a one-shot, no-retry render.

    There's no separate "system_prompt" field stored on disk: the config's
    own first attachment, if it's a "text" one, IS the system message - a
    human builds it the same way as every other block (add it, write it,
    drag it), not a special pre-existing field they can't remove or
    reorder. The returned `config` still carries a real "system_prompt"
    key, synthesized here, so every existing caller (generation.py's
    run_with_retry(config["system_prompt"], ...), comparison.py's own
    system message) keeps working unchanged - this is the one place that
    derivation happens, not duplicated per caller. `parts` only resolves
    the REMAINING attachments (the ones that become the user message), so
    the system-prompt-role attachment is never double-counted into both
    messages."""
    config = storage.load_config(config_dir, name)
    attachments = config.get("attachments", [])
    system_prompt = ""
    body_attachments = attachments
    if attachments and attachments[0].get("type") == "text":
        system_prompt = attachments[0].get("content") or ""
        body_attachments = attachments[1:]
    parts = resolve_attachments(body_attachments, context_values, files_root)
    return {**config, "system_prompt": system_prompt}, parts


def render_prompt(
    config_dir: str | Path,
    name: str,
    context_values: dict[str, str],
    files_root: str | Path | list[str | Path],
) -> dict:
    """The real prompt (build_prompt()'s own shape) a real call for this
    config would start with, without spending a real LLM call and without
    any live, run-scoped constraints layered on top (those only exist once
    an actual run is happening) - just this config's own persisted
    learned_constraints. Used by a prompt-config preview capability and by
    a caller that wants to display "what would be sent" without a retry
    loop (e.g. a knowledge-mode drift check, which never retries at all)."""
    config, parts = resolve_for_call(config_dir, name, context_values, files_root)
    return build_prompt(parts, constraints=config.get("learned_constraints"))
