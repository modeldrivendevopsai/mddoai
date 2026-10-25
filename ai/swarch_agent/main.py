import logging
import os

import httpx
from fastapi import FastAPI, HTTPException
from pydantic import BaseModel, Field

from conversion import InvalidModelError, TransformationFailedError, TransformationUnavailableError, to_pim
from settings import MAX_MODEL_BYTES

logging.basicConfig(
    level=os.environ.get("LOG_LEVEL", "INFO").upper(),
    format="%(asctime)s %(levelname)s %(name)s: %(message)s",
)
logger = logging.getLogger(__name__)

app = FastAPI(title="MDDOAI SWArch Agent")


class PimRequest(BaseModel):
    content: str = Field(..., min_length=1, description="A Software Architecture model, a .swarch file's XMI text.")


@app.get("/health")
def health():
    return {"status": "ok"}


@app.post("/pim")
def pim_endpoint(request: PimRequest):
    """The PIM a Software Architecture model turns into, as XMI text."""
    content_bytes = len(request.content.encode("utf-8"))
    if content_bytes > MAX_MODEL_BYTES:
        raise HTTPException(status_code=413, detail=f"content exceeds {MAX_MODEL_BYTES} bytes")

    logger.info("POST /pim bytes=%d", content_bytes)
    try:
        pim = to_pim(request.content)
    except InvalidModelError as e:
        raise HTTPException(status_code=422, detail={"message": str(e), "issues": e.issues})
    except TransformationFailedError:
        raise HTTPException(
            status_code=422,
            detail={
                "message": "The SWArch-to-PIM transformation failed on this model. The service log has the details.",
                "issues": [],
            },
        )
    except TransformationUnavailableError:
        raise HTTPException(status_code=500, detail="The SWArch-to-PIM transformation is not available.")
    except httpx.HTTPError as e:
        logger.error("POST /pim: a sibling service failed: %s", e)
        raise HTTPException(status_code=502, detail="A service this one depends on is unavailable, try again shortly.")
    logger.info("POST /pim done pim_bytes=%d", len(pim.encode("utf-8")))
    return {"pim": pim}
