"""The real, shared prompt-config HTTP surface every generation-capable
service (psm_agent, atl_agent, acceleo_agent) exposes identically - each
service's own routes/prompt_config.py builds one PromptConfigRouter with
its own real config_dir/files_root/context_for and re-exports its bound
methods under the same names its own tests already import, rather than
duplicating every one of these endpoint bodies per service.

The one real per-service variation is `context_for`: which `name`s are
known and what sample context values each one resolves against. psm_agent
alone has two real config names ("generation"/"comparison", each with its
own context shape); atl_agent/acceleo_agent each have exactly one. This
class asks its caller to resolve that (raising the real 404 itself for an
unknown name), rather than guessing at a one-name-fits-all shape.

There is exactly one config per name, not a family of named presets: a
target platform's identity is supplied as plain runtime data (a
"context"-type attachment, resolved at call time), never as a separately
saved document, so onboarding a new platform never requires creating any
new config here.

`config_dir` and `files_root` are both zero-arg getters, not plain values,
and are called fresh on every request rather than captured once at
construction time: each service's own test suite isolates prompt-config
reads/writes by monkeypatching its own prompt_paths.PROMPT_CONFIG_DIR/
ATTACHMENT_UPLOADS_DIR module attributes for the duration of one test (see
each service's own conftest.py), which only works if this class re-reads
them through that same module reference on every call, not once up front.
"""
from typing import Callable

from fastapi import APIRouter, HTTPException
from pydantic import BaseModel

from ..prompt_builder import build_prompt
from ..prompt_config import history, learned_constraints, references, rendering, resolution, storage


class PromptConfigBody(BaseModel):
    # No system_prompt field: a config's own first "text" attachment IS
    # the system message (see resolution.resolve_for_call), built and
    # edited the same way as every other attachment, not a separate
    # required field a human can't remove or reorder. No learned_constraints
    # field either: those live in their own separate, never-reverted store
    # (see learned_constraints.py) - Save/Restore below only ever touch
    # attachments now.
    attachments: list[dict]


class LearnedConstraintsBody(BaseModel):
    constraints: list[str]


class RemoveLearnedConstraintBody(BaseModel):
    constraint: str


class PromptConfigRouter:
    def __init__(
        self,
        config_dir: Callable[[], object],
        files_root: Callable[[], object],
        context_for: Callable[[str], dict[str, str]],
    ):
        self._config_dir = config_dir
        self._files_root = files_root
        self._context_for = context_for

        self.router = APIRouter(prefix="/prompt-config")
        self.router.add_api_route("/{name}", self.get_config_endpoint, methods=["GET"])
        self.router.add_api_route("/{name}", self.save_config_endpoint, methods=["PUT"])
        self.router.add_api_route("/{name}/history", self.history_endpoint, methods=["GET"])
        self.router.add_api_route("/{name}/diff", self.diff_endpoint, methods=["GET"])
        # "Revert to default" is just restoring history.SHIPPED_DEFAULT_VERSION
        # through this same route - no separate /revert route or endpoint,
        # and no /promote-to-default at all any more (see history.py's own
        # docstring for why). One real restore path, not two.
        self.router.add_api_route("/{name}/restore/{version}", self.restore_endpoint, methods=["POST"])
        self.router.add_api_route(
            "/{name}/check-references", self.check_references_endpoint, methods=["GET"]
        )
        self.router.add_api_route(
            "/{name}/learned-constraints", self.add_learned_constraints_endpoint, methods=["POST"]
        )
        self.router.add_api_route(
            "/{name}/learned-constraints", self.remove_learned_constraint_endpoint, methods=["DELETE"]
        )
        self.router.add_api_route("/{name}/preview", self.preview_endpoint, methods=["POST"])

    def _with_constraints(self, name: str, config: dict) -> dict:
        """Every endpoint below that hands a "config" back to a human
        merges in the real, current learned_constraints (from their own
        separate store, see learned_constraints.with_current_constraints)
        before returning it - a UI that only ever reads this field off a
        get/save/restore response should never see it go missing or stale
        just because those actions no longer store it themselves."""
        return learned_constraints.with_current_constraints(self._config_dir(), name, config)

    def get_config_endpoint(self, name: str):
        self._context_for(name)
        try:
            config = storage.load_config(self._config_dir(), name)
        except FileNotFoundError as e:
            raise HTTPException(status_code=404, detail=str(e)) from e
        return self._with_constraints(name, config)

    def save_config_endpoint(self, name: str, body: PromptConfigBody):
        context_values = self._context_for(name)
        try:
            saved = storage.save_config(
                self._config_dir(), name, body.model_dump(), context_values, self._files_root()
            )
        except storage.PromptConfigValidationError as e:
            raise HTTPException(status_code=400, detail=str(e)) from e
        return self._with_constraints(name, saved)

    def history_endpoint(self, name: str):
        self._context_for(name)
        return {"versions": history.list_history(self._config_dir(), name)}

    def diff_endpoint(self, name: str, a: str, b: str):
        self._context_for(name)
        try:
            return history.diff_versions(self._config_dir(), name, a, b)
        except FileNotFoundError as e:
            raise HTTPException(status_code=404, detail=str(e)) from e

    def restore_endpoint(self, name: str, version: str):
        context_values = self._context_for(name)
        try:
            restored = history.restore_version(self._config_dir(), name, version, context_values, self._files_root())
        except FileNotFoundError as e:
            raise HTTPException(status_code=404, detail=str(e)) from e
        return self._with_constraints(name, restored)

    def check_references_endpoint(self, name: str):
        context_values = self._context_for(name)
        try:
            broken = references.check_references(self._config_dir(), name, context_values, self._files_root())
        except FileNotFoundError as e:
            raise HTTPException(status_code=404, detail=str(e)) from e
        return {"broken": broken}

    def add_learned_constraints_endpoint(self, name: str, body: LearnedConstraintsBody):
        self._context_for(name)
        try:
            constraints = learned_constraints.add_learned_constraints(self._config_dir(), name, body.constraints)
        except FileNotFoundError as e:
            raise HTTPException(status_code=404, detail=str(e)) from e
        return {"learned_constraints": constraints}

    def remove_learned_constraint_endpoint(self, name: str, body: RemoveLearnedConstraintBody):
        self._context_for(name)
        try:
            constraints = learned_constraints.remove_learned_constraint(self._config_dir(), name, body.constraint)
        except FileNotFoundError as e:
            raise HTTPException(status_code=404, detail=str(e)) from e
        return {"learned_constraints": constraints}

    def preview_endpoint(self, name: str, body: PromptConfigBody | None = None):
        """The exact text a real call for this config would send the LLM,
        without spending a real call: resolution.resolve_config/
        resolve_for_call already resolve attachments, derive the real
        system message from the config's own first "text" attachment, and
        fold in learned_constraints identically regardless of which name
        this is, so this endpoint needs no per-mode branching of its own.

        `body`, when given, is resolved directly instead of re-loading the
        saved config from disk - a UI previewing its own current, unsaved
        draft wants to see what THAT would send, not what the last Save
        happened to leave on disk (the two can differ for as long as an
        edit sits unsaved). Optional, not required, so a caller with
        nothing but `name` (or an older client) still gets the saved
        config's own preview exactly as before.

        `attachments` carries each body attachment's own real resolved
        content, keyed by its id (the same id resolve_attachments() already
        uses) - resolution already reads a "file"/"context" attachment's
        real content to build user_content below, this just also hands
        that same already-resolved text back per-attachment, so a UI can
        show what a file/context chip actually contains without a second
        endpoint or its own path/key validation.

        learned_constraints always come from their own separate, current
        store (see learned_constraints.load_constraints) regardless of
        which branch below runs - they apply to every real call no matter
        which draft of the attachments/text is being previewed, so a given
        `body` never needs (and no longer even can) carry its own copy of
        them."""
        context_values = self._context_for(name)
        try:
            if body is not None:
                draft = self._with_constraints(name, body.model_dump())
                config, parts = resolution.resolve_config(draft, context_values, self._files_root())
            else:
                config, parts = resolution.resolve_for_call(self._config_dir(), name, context_values, self._files_root())
        except FileNotFoundError as e:
            raise HTTPException(status_code=404, detail=str(e)) from e

        prompt = build_prompt(parts, constraints=config.get("learned_constraints"))
        return {
            "system_prompt": config["system_prompt"],
            "user_content": rendering.render_user_content(config, prompt),
            "attachments": parts,
        }
