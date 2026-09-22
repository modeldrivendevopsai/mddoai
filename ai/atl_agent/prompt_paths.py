"""Where this service's own real, UI-editable prompt config (and its "file"
attachment's uploaded copies) lives on disk - mirrors psm_agent's own
prompt_paths.py, one real difference: REFERENCE_EXAMPLE_PATH, the one real
master-example ATL file this service reads, a real file this service owns
directly under its own reference_example/ (see that constant's own comment
for why, unlike psm_agent's whole META_MODELS_DIR tree, this needs no
Docker bind mount at all).

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

# The master example this stage's own default prompt attaches, the same
# role psm_agent's own master-example .ecore file plays for it: a real,
# working ATL transformation from PIM to a target platform, ported from
# this project's own validated ai-research experiments (cicd2gha.atl -
# ACICDTrip's real, working transformation from its own CICD metamodel to
# GitHub Actions), the same reference the paper's own "Step 2 (ATL)"
# results were produced against, matching the master-example convention
# already used for real, unchanged, across every platform in that
# experiment - reused directly here rather than this project's own
# hand-authored pim2gitlabmodel.atl, so this stage's default prompt teaches
# the same real ATL/OCL idioms (lazy-rule dispatch, tuple parameters,
# helper operations) the validated research actually relied on. Compiles
# clean via the real AtlValidator, confirmed directly - a real-execution
# check isn't meaningful for it the way it is for a PIM-sourced
# transformation, since its own real source metamodel is CICD, not this
# project's own fixed PIM, and AtlExecutor only ever loads a real PIM
# instance as its source.
#
# A real file this service owns directly (reference_example/cicd2gha.atl),
# not an external mount: unlike psm_agent's meta_models/ (real MDE-engine
# data this AI service doesn't own) or the pre-existing pim2gitlabmodel.atl
# this used to point at, this file exists solely as this stage's own
# prompt-engineering asset, so it lives, and ships, with this service like
# any other of its own source files - no separate Docker bind mount needed.
REFERENCE_EXAMPLE_PATH = Path(
    os.environ.get(
        "ATL_AGENT_REFERENCE_EXAMPLE_PATH",
        str(Path(__file__).resolve().parent / "reference_example" / "cicd2gha.atl"),
    )
)

# An engineering guess, not a measured limit: real .ecore/ATL attachments in
# this service's own shipped defaults are a few KB; 5MB comfortably covers
# a large real metamodel without letting an upload endpoint accept an
# unbounded amount of data. Matches psm_agent's own MAX_UPLOAD_BYTES.
MAX_UPLOAD_BYTES = int(os.environ.get("ATL_AGENT_MAX_UPLOAD_BYTES", str(5 * 1024 * 1024)))
