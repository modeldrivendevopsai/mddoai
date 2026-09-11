"""Real, git-committable, UI-editable prompt config for this service's one
real LLM capability (generation.py's generate()) - see
generation_toolkit.prompt_config for the actual persistence mechanism and
generation_toolkit.routes.prompt_config.PromptConfigRouter for the shared,
generic HTTP surface every generation-capable service exposes identically
over it. This service only ever has one real config name, "generation" -
_context_for below 404s on anything else.

Every real path (config_dir, files_root) and the real sample context
values a dry-run validation or a static preview needs both live here, not
in generation_toolkit itself, which stays fully generic: it takes a
config_dir and context_values from its caller, it has no idea this
service's own directory layout or which context keys generation.py
actually supplies for a real call.
"""
from fastapi import HTTPException

from generation_toolkit.routes.prompt_config import (
    LearnedConstraintsBody,
    PromptConfigBody,
    PromptConfigRouter,
    RemoveLearnedConstraintBody,
)

from generation import CONFIG_NAME
import prompt_paths

# Every "file" attachment resolves against both real roots: the one real
# master-example transformation's own directory, and a human's own
# uploaded files (see routes/uploads.py) - generation_toolkit.attachments.files.resolve_file_attachment's
# own multi-root support tries each in order. Built locally from
# prompt_paths, not imported from generation.py's own copy: each module
# that needs it builds its own from the same public source, rather than
# one reaching into another's underscore-prefixed constant. A function,
# not a module-level constant, and passed to PromptConfigRouter as-is (not
# called here): PromptConfigRouter calls it fresh on every request, the
# same reason its own config_dir argument is a getter - a test that
# monkeypatches prompt_paths.ATTACHMENT_UPLOADS_DIR for one test (see
# tests/conftest.py's own isolated_attachment_uploads_dir) needs this
# rebuilt from that same module reference on every call, not once at
# import time.
def _files_root() -> list:
    return [prompt_paths.REFERENCE_EXAMPLE_PATH.parent, prompt_paths.ATTACHMENT_UPLOADS_DIR]

# The real context keys generate() itself supplies for a real call (see
# generation.py's own context_values inside generate()) - kept here, not
# re-derived, so a dry-run validation or a static preview checks against
# the same shape a real call actually uses.
_SAMPLE_CONTEXT_VALUES: dict[str, str] = {"pim_ecore": "", "psm_ecore": ""}


def _context_for(name: str) -> dict[str, str]:
    if name != CONFIG_NAME:
        raise HTTPException(status_code=404, detail=f"unknown prompt config name {name!r}")
    return _SAMPLE_CONTEXT_VALUES


_prompt_config = PromptConfigRouter(lambda: prompt_paths.PROMPT_CONFIG_DIR, _files_root, _context_for)
router = _prompt_config.router

# Re-exported under the same names this service's own tests already import
# directly (see tests/routes/test_prompt_config.py) - each is a bound
# method on the shared router above, not a duplicate implementation.
list_presets_endpoint = _prompt_config.list_presets_endpoint
get_config_endpoint = _prompt_config.get_config_endpoint
save_config_endpoint = _prompt_config.save_config_endpoint
history_endpoint = _prompt_config.history_endpoint
diff_endpoint = _prompt_config.diff_endpoint
restore_endpoint = _prompt_config.restore_endpoint
revert_endpoint = _prompt_config.revert_endpoint
promote_to_default_endpoint = _prompt_config.promote_to_default_endpoint
check_references_endpoint = _prompt_config.check_references_endpoint
add_learned_constraints_endpoint = _prompt_config.add_learned_constraints_endpoint
remove_learned_constraint_endpoint = _prompt_config.remove_learned_constraint_endpoint
preview_endpoint = _prompt_config.preview_endpoint
