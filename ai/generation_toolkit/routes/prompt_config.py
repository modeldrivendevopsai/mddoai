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
from ..prompt_config import history, learned_constraints, presets, references, rendering, resolution, storage


class PromptConfigBody(BaseModel):
    # No system_prompt field: a config's own first "text" attachment IS
    # the system message (see resolution.resolve_for_call), built and
    # edited the same way as every other attachment, not a separate
    # required field a human can't remove or reorder.
    attachments: list[dict]
    learned_constraints: list[str] = []
    label: str | None = None
    platform_hints: list[str] = []


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
        self.router.add_api_route("/{name}/presets", self.list_presets_endpoint, methods=["GET"])
        self.router.add_api_route("/{name}/{preset}", self.get_config_endpoint, methods=["GET"])
        self.router.add_api_route("/{name}/{preset}", self.save_config_endpoint, methods=["PUT"])
        self.router.add_api_route("/{name}/{preset}/history", self.history_endpoint, methods=["GET"])
        self.router.add_api_route("/{name}/{preset}/diff", self.diff_endpoint, methods=["GET"])
        self.router.add_api_route("/{name}/{preset}/restore/{version}", self.restore_endpoint, methods=["POST"])
        self.router.add_api_route("/{name}/{preset}/revert", self.revert_endpoint, methods=["POST"])
        self.router.add_api_route(
            "/{name}/{preset}/promote-to-default", self.promote_to_default_endpoint, methods=["POST"]
        )
        self.router.add_api_route(
            "/{name}/{preset}/check-references", self.check_references_endpoint, methods=["GET"]
        )
        self.router.add_api_route(
            "/{name}/{preset}/learned-constraints", self.add_learned_constraints_endpoint, methods=["POST"]
        )
        self.router.add_api_route(
            "/{name}/{preset}/learned-constraints", self.remove_learned_constraint_endpoint, methods=["DELETE"]
        )
        self.router.add_api_route("/{name}/{preset}/preview", self.preview_endpoint, methods=["POST"])

    def list_presets_endpoint(self, name: str):
        self._context_for(name)
        return {"presets": presets.list_preset_metadata(self._config_dir(), name)}

    def get_config_endpoint(self, name: str, preset: str):
        self._context_for(name)
        try:
            return storage.load_config(self._config_dir(), name, preset)
        except FileNotFoundError as e:
            raise HTTPException(status_code=404, detail=str(e)) from e

    def save_config_endpoint(self, name: str, preset: str, body: PromptConfigBody):
        context_values = self._context_for(name)
        try:
            return storage.save_config(
                self._config_dir(), name, preset, body.model_dump(), context_values, self._files_root()
            )
        except storage.PromptConfigValidationError as e:
            raise HTTPException(status_code=400, detail=str(e)) from e

    def history_endpoint(self, name: str, preset: str):
        self._context_for(name)
        return {"versions": history.list_history(self._config_dir(), name, preset)}

    def diff_endpoint(self, name: str, preset: str, a: str, b: str):
        self._context_for(name)
        try:
            return history.diff_versions(self._config_dir(), name, preset, a, b)
        except FileNotFoundError as e:
            raise HTTPException(status_code=404, detail=str(e)) from e

    def restore_endpoint(self, name: str, preset: str, version: str):
        context_values = self._context_for(name)
        try:
            return history.restore_version(
                self._config_dir(), name, preset, version, context_values, self._files_root()
            )
        except FileNotFoundError as e:
            raise HTTPException(status_code=404, detail=str(e)) from e

    def revert_endpoint(self, name: str, preset: str):
        context_values = self._context_for(name)
        try:
            return history.revert_to_default(self._config_dir(), name, preset, context_values, self._files_root())
        except FileNotFoundError as e:
            raise HTTPException(status_code=404, detail=str(e)) from e

    def promote_to_default_endpoint(self, name: str, preset: str):
        self._context_for(name)
        try:
            return history.promote_live_to_default(self._config_dir(), name, preset)
        except FileNotFoundError as e:
            raise HTTPException(status_code=404, detail=str(e)) from e

    def check_references_endpoint(self, name: str, preset: str):
        context_values = self._context_for(name)
        try:
            broken = references.check_references(
                self._config_dir(), name, preset, context_values, self._files_root()
            )
        except FileNotFoundError as e:
            raise HTTPException(status_code=404, detail=str(e)) from e
        return {"broken": broken}

    def add_learned_constraints_endpoint(self, name: str, preset: str, body: LearnedConstraintsBody):
        context_values = self._context_for(name)
        try:
            return learned_constraints.add_learned_constraints(
                self._config_dir(), name, preset, body.constraints, context_values, self._files_root()
            )
        except FileNotFoundError as e:
            raise HTTPException(status_code=404, detail=str(e)) from e

    def remove_learned_constraint_endpoint(self, name: str, preset: str, body: RemoveLearnedConstraintBody):
        context_values = self._context_for(name)
        try:
            return learned_constraints.remove_learned_constraint(
                self._config_dir(), name, preset, body.constraint, context_values, self._files_root()
            )
        except FileNotFoundError as e:
            raise HTTPException(status_code=404, detail=str(e)) from e

    def preview_endpoint(self, name: str, preset: str):
        """The exact text a real call for this config would send the LLM,
        without spending a real call: resolution.resolve_for_call already
        resolves attachments, derives the real system message from the
        config's own first "text" attachment, and folds in
        learned_constraints identically regardless of which (name, preset)
        this is, so this endpoint needs no per-mode branching of its own.
        `attachments` carries each body attachment's own real resolved
        content, keyed by its id (the same id resolve_attachments() already
        uses) - resolution already reads a "file"/"context" attachment's
        real content to build user_content below, this just also hands
        that same already-resolved text back per-attachment, so a UI can
        show what a file/context chip actually contains without a second
        endpoint or its own path/key validation."""
        context_values = self._context_for(name)
        try:
            config, parts = resolution.resolve_for_call(
                self._config_dir(), name, preset, context_values, self._files_root()
            )
        except FileNotFoundError as e:
            raise HTTPException(status_code=404, detail=str(e)) from e

        prompt = build_prompt(parts, constraints=config.get("learned_constraints"))
        return {
            "system_prompt": config["system_prompt"],
            "user_content": rendering.render_user_content(config, prompt),
            "attachments": parts,
        }
