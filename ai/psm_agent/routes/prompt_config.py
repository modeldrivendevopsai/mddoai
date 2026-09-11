"""Real, git-committable, UI-editable prompt config for this service's two
real LLM capabilities (generation.py's "generation" mode, comparison.py's
"comparison" mode) - see generation_toolkit.prompt_config for the actual
persistence mechanism and generation_toolkit.routes.prompt_config.PromptConfigRouter
for the shared, generic HTTP surface every generation-capable service
exposes identically over it.

Every real path (config_dir, files_root) and the real sample context
values a dry-run validation or a static preview needs both live here, not
in generation_toolkit itself, which stays fully generic: it takes a
config_dir and context_values from its caller, it has no idea this
service's own directory layout or which context keys generation.py/
comparison.py actually supply for a real call.
"""
from fastapi import HTTPException

from generation_toolkit.routes.prompt_config import (
    LearnedConstraintsBody,
    PromptConfigBody,
    PromptConfigRouter,
    RemoveLearnedConstraintBody,
)

from comparison import META_MODELS_DIR
import prompt_paths

# Every "file" attachment resolves against both real roots: the read-only,
# pre-existing repo metamodels, and a human's own uploaded files (see
# routes/uploads.py) - generation_toolkit.attachments.files.resolve_file_attachment's
# own multi-root support tries each in order, so a config can reference
# either kind of real file interchangeably. A function, not a module-level
# constant, and passed to PromptConfigRouter as-is (not called here):
# PromptConfigRouter calls it fresh on every request, the same reason its
# own config_dir argument is a getter - a test that monkeypatches
# prompt_paths.ATTACHMENT_UPLOADS_DIR for one test (see tests/conftest.py's
# own isolated_attachment_uploads_dir) needs this rebuilt from that same
# module reference on every call, not once at import time.
def _files_root() -> list:
    return [META_MODELS_DIR, prompt_paths.ATTACHMENT_UPLOADS_DIR]

# The real context keys generate()/compare() themselves supply for a real
# call (see generation.py's own context_values inside generate(),
# comparison.py's own inside _load_and_resolve()) - kept here, not
# re-derived, so a dry-run validation or a static preview checks against
# the same shape a real call actually uses. If either of those two
# functions' own context keys ever change, this mapping needs updating
# too - there's no single source of truth to read it from otherwise,
# since generation_toolkit itself is deliberately generic and has no
# concept of "psm_agent's own context keys."
_SAMPLE_CONTEXT_VALUES: dict[str, dict[str, str]] = {
    "generation": {"pim_ecore": "", "psm_docs": ""},
    "comparison": {"psm_metamodel": "", "serialized_docs": ""},
}


def _context_for(name: str) -> dict[str, str]:
    if name not in _SAMPLE_CONTEXT_VALUES:
        raise HTTPException(status_code=404, detail=f"unknown prompt config name {name!r}")
    return _SAMPLE_CONTEXT_VALUES[name]


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
