from fastapi import APIRouter, Depends

from platform_registry import PlatformRegistry
from routes.dependencies import get_registry

router = APIRouter(prefix="/platforms")


@router.get("")
def list_platforms(registry: PlatformRegistry = Depends(get_registry)):
    """The platforms pipelines can be generated for: each one's latest version."""
    return {"platforms": registry.listing()}
