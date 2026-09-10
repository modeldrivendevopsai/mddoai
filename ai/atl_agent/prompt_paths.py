"""Where this service's own real, UI-editable prompt config (and its "file"
attachment's uploaded copies) lives on disk - mirrors psm_agent's own
prompt_paths.py, one real difference: REFERENCE_EXAMPLE_PATH, the one real
master-example ATL file this service reads (read-only), instead of a whole
META_MODELS_DIR tree - see ai/CLAUDE.md's folder-boundaries section for why
a single real file, not a directory, is mounted here.

Sibling to this service's own source, matching integration_runner's
RUNS_DIR / psm_agent's own PROMPT_CONFIG_DIR pattern for "a real,
no-external-mount-required default, overridable for Docker" - see
ai/docker-compose.yml for the real bind mounts that make a save (or a real
file upload) through the running service land on the host's actual git
checkout.
"""
import os
from pathlib import Path

PROMPT_CONFIG_DIR = Path(os.environ.get("ATL_AGENT_PROMPT_CONFIG_DIR", str(Path(__file__).resolve().parent / "prompts")))

# Separate from PROMPT_CONFIG_DIR (config JSON, meant to be git-committed):
# a real human's file upload is not that, a writable directory of its own,
# so an upload never needs write access to the config directory itself.
ATTACHMENT_UPLOADS_DIR = Path(
    os.environ.get("ATL_AGENT_ATTACHMENT_UPLOADS_DIR", str(Path(__file__).resolve().parent / "attachments"))
)

# The one real, pre-existing repo file this service reads: main/'s own
# real, working PIM->GitLab ATL transformation - the master example this
# stage's own default prompt attaches, the same role psm_agent's own
# master-example .ecore file plays for it. Defaults to the real relative
# repo path (same "real default for local/non-Docker dev" reasoning as
# comparison.py's own META_MODELS_DIR); the real docker-compose entry
# bind-mounts the same real file, read-only, at a container-local path and
# overrides this to point at it - a single file, not a whole directory
# mount (narrower than psm_agent's own META_MODELS_DIR), since this is the
# one real file this service ever needs, not a browsable tree.
REFERENCE_EXAMPLE_PATH = Path(
    os.environ.get(
        "ATL_AGENT_REFERENCE_EXAMPLE_PATH",
        str(
            Path(__file__).resolve().parents[2]
            / "main" / "src" / "main" / "resources" / "transformations" / "pim2psm" / "pim2gitlabmodel.atl"
        ),
    )
)

# An engineering guess, not a measured limit: real .ecore/ATL attachments in
# this service's own shipped defaults are a few KB; 5MB comfortably covers
# a large real metamodel without letting an upload endpoint accept an
# unbounded amount of data. Matches psm_agent's own MAX_UPLOAD_BYTES.
MAX_UPLOAD_BYTES = int(os.environ.get("ATL_AGENT_MAX_UPLOAD_BYTES", str(5 * 1024 * 1024)))
