"""The shared `GET /available-files` HTTP surface every generation-capable
service exposes identically - the picker a "file" attachment offers. What
actually counts as an available file is real, per-service logic (each
service's own available_files.py), this only wires whatever that returns
into the one real endpoint shape.
"""
from typing import Callable

from fastapi import APIRouter


def build_files_router(list_available_files: Callable[[], list[str]]) -> tuple[APIRouter, Callable[[], dict]]:
    """Returns (router, available_files_endpoint): the router to mount, and
    the bound endpoint function itself, re-exported by callers whose own
    tests import it directly by name (matching this repo's established
    plain-function-call test convention for a synchronous GET endpoint)."""
    router = APIRouter()

    def available_files_endpoint():
        return {"files": list_available_files()}

    router.add_api_route("/available-files", available_files_endpoint, methods=["GET"])
    return router, available_files_endpoint
