"""Where this service's own real, UI-editable prompt config (and its "file"
attachment's uploaded copies) lives on disk - mirrors atl_agent's own
prompt_paths.py, one real difference: REFERENCE_EXAMPLE_PATH points at the
real master-example Acceleo template instead of an ATL transformation - see
that constant's own comment for why this is a real file this service owns
directly, needing no Docker bind mount.

Sibling to this service's own source, matching integration_runner's
RUNS_DIR / psm_agent's own PROMPT_CONFIG_DIR pattern for "a real,
no-external-mount-required default, overridable for Docker" - see
ai/docker-compose.yml for the real bind mounts that make a save (or a real
file upload) through the running service land on the host's actual git
checkout.
"""
import os
from pathlib import Path

PROMPT_CONFIG_DIR = Path(os.environ.get("ACCELEO_AGENT_PROMPT_CONFIG_DIR", str(Path(__file__).resolve().parent / "prompts")))

# Separate from PROMPT_CONFIG_DIR (config JSON, meant to be git-committed):
# a real human's file upload is not that, a writable directory of its own,
# so an upload never needs write access to the config directory itself.
ATTACHMENT_UPLOADS_DIR = Path(
    os.environ.get("ACCELEO_AGENT_ATTACHMENT_UPLOADS_DIR", str(Path(__file__).resolve().parent / "attachments"))
)

# The master example this stage's own default prompt attaches, the same
# role psm_agent's own master-example .ecore file plays for it: a real,
# working Acceleo template, ported from this project's own validated
# ai-research experiments (gha_generate.mtl - ACICDTrip's real, working
# GitHub Actions code-generation template), the same reference the paper's
# own "Step 3 (Acceleo)" results were produced against, matching the
# master-example convention already used for real, unchanged, across every
# platform in that experiment - reused directly here rather than this
# project's own hand-authored GitLab template, so this stage's default
# prompt teaches the same real Acceleo/OCL idioms the validated research
# actually relied on. In particular, its own real generateTrigger/
# __generateExpression/generateLiteral templates dispatch on many
# expression/type kinds via bracketed [if]/[elseif]/[/if] (needing only one
# closing bracket for the whole chain), not a raw OCL if/then/else/endif
# query expression (which needs one endif per branch, easy to miscount past
# a handful) - confirmed for real this is exactly the idiom a generation
# reaches for once it has a real worked example of it, having previously
# never produced anything but the harder-to-get-right query form. Adapted
# from the original in one respect: its own 7 invoke() calls into an
# external Java service class from that other project (never available in
# this build) are rewritten here as real, equivalent OCL/EMF-enum-literal
# logic instead, so the reference itself is genuinely compilable in this
# project, not merely copied - confirmed for real via AcceleoValidator
# against this project's own real, already-tracked GitHub Actions PSM
# metamodel (meta_models/com.mddoai.metamodel.github/model/githubMM.ecore,
# the same one psm_agent already treats as a known platform's fixed
# metamodel) rather than vendoring a second copy of it here.
#
# A real file this service owns directly (reference_example/generate.mtl),
# not an external mount: unlike psm_agent's meta_models/ (real MDE-engine
# data this AI service doesn't own) or the pre-existing GitLab template
# this used to point at, this file exists solely as this stage's own
# prompt-engineering asset, so it lives, and ships, with this service like
# any other of its own source files - no separate Docker bind mount needed.
REFERENCE_EXAMPLE_PATH = Path(
    os.environ.get(
        "ACCELEO_AGENT_REFERENCE_EXAMPLE_PATH",
        str(Path(__file__).resolve().parent / "reference_example" / "generate.mtl"),
    )
)

# An engineering guess, not a measured limit: real .ecore/docs attachments
# in this service's own shipped defaults are a few KB; 5MB comfortably
# covers a large real metamodel or a documentation dump without letting an
# upload endpoint accept an unbounded amount of data. Matches psm_agent's
# own MAX_UPLOAD_BYTES.
MAX_UPLOAD_BYTES = int(os.environ.get("ACCELEO_AGENT_MAX_UPLOAD_BYTES", str(5 * 1024 * 1024)))
