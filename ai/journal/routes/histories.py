from fastapi import APIRouter, Depends, HTTPException
from fastapi.responses import JSONResponse
from pydantic import BaseModel, Field

from journal.dependencies import Stores, get_stores
from journal.histories import BadHistory, HistoryGap, HistoryStore, UnknownHistory

router = APIRouter(prefix="/histories")


class WholeHistory(BaseModel):
    state: dict = Field(..., description="Every state field.")
    events: list[dict]


class Append(BaseModel):
    start: int = Field(..., ge=0, description="Where in the history the events begin. Events kept from there on are replaced.")
    state: dict = Field(default_factory=dict, description="The state fields that changed.")
    events: list[dict]


def _store(kind: str, stores: Stores) -> HistoryStore:
    store = stores.histories.get(kind)
    if store is None:
        raise HTTPException(status_code=404, detail=f"There is no kind of history '{kind}'.")
    return store


@router.get("/{kind}")
def list_keys(kind: str, stores: Stores = Depends(get_stores)):
    return {"keys": _store(kind, stores).keys()}


@router.get("/{kind}/{key}")
def read_history(kind: str, key: str, stores: Stores = Depends(get_stores)):
    try:
        events, state = _store(kind, stores).read(key)
    except UnknownHistory as e:
        raise HTTPException(status_code=404, detail=f"No {kind} history '{key}'.") from e
    except BadHistory as e:
        raise HTTPException(status_code=422, detail=f"The {kind} history '{key}' cannot be read: {e}") from e
    return {"state": state, "events": events}


@router.put("/{kind}/{key}")
def replace_history(kind: str, key: str, body: WholeHistory, stores: Stores = Depends(get_stores)):
    try:
        count = _store(kind, stores).replace(key, body.state, body.events)
    except UnknownHistory as e:
        raise HTTPException(status_code=404, detail=str(e)) from e
    return {"count": count}


@router.post("/{kind}/{key}/append")
def append_to_history(kind: str, key: str, body: Append, stores: Stores = Depends(get_stores)):
    try:
        count = _store(kind, stores).append(key, body.start, body.state, body.events)
    except UnknownHistory as e:
        raise HTTPException(status_code=404, detail=str(e)) from e
    except BadHistory as e:
        raise HTTPException(status_code=422, detail=f"The {kind} history '{key}' cannot be added to: {e}") from e
    except HistoryGap as e:
        return JSONResponse(status_code=409, content={"detail": str(e), "count": e.count})
    return {"count": count}
