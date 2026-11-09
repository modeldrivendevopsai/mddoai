"""HTTP client for the journal: the service that keeps histories, run attempts
and published platforms on disk. A request the journal refuses (an unknown
name, a version that exists, a gap in a history) raises AgentServiceError with
its reason. An unreachable journal surfaces as a raw httpx error.

HistoryClient keeps a history there without sending the whole of it each time:
it remembers how much the journal already has, and sends only what is new.
"""
import copy
import json
import logging
import os
from collections.abc import Callable
from typing import TypeVar

import httpx

from clients.agent_service_errors import AgentServiceError, raise_for_business_error

logger = logging.getLogger(__name__)

JOURNAL_URL = os.environ.get("JOURNAL_URL", "http://localhost:8120")
# What a caller waits for the journal before it goes on without. A save is the
# new records only, over the compose network, so a few seconds is generous
# (engineering guess).
SAVE_TIMEOUT = float(os.environ.get("JOURNAL_SAVE_TIMEOUT", "10.0"))
# Reading a whole history can be several megabytes of stage output.
READ_TIMEOUT = float(os.environ.get("JOURNAL_READ_TIMEOUT", "60.0"))
# A publish only writes four small files.
PUBLISH_TIMEOUT = float(os.environ.get("JOURNAL_PUBLISH_TIMEOUT", "30.0"))

# The journal's answer to a platform file over its size limit. Sending it again
# cannot succeed, so it is a refusal like the business errors, not an outage.
_TOO_LARGE = 413
_GAP = 409

T = TypeVar("T")


def _send(method: str, path: str, timeout: float, body: dict | None = None) -> httpx.Response:
    """The request, with `body` as JSON. A value JSON cannot hold is sent as
    text, so one odd value never stops a record from being kept."""
    kwargs: dict = {}
    if body is not None:
        kwargs = {"content": json.dumps(body, default=str).encode("utf-8"), "headers": {"Content-Type": "application/json"}}
    return httpx.request(method, f"{JOURNAL_URL}{path}", timeout=timeout, **kwargs)


def _request(method: str, path: str, timeout: float = SAVE_TIMEOUT, body: dict | None = None) -> httpx.Response:
    response = _send(method, path, timeout, body)
    if response.status_code == _TOO_LARGE:
        raise AgentServiceError(_TOO_LARGE, "The platform's files are larger than the journal accepts.")
    raise_for_business_error(response)
    return response


# --- histories --------------------------------------------------------------------------


class HistoryClient:
    """The histories of one kind ("runs" or "chats"). Each is a list of events
    and some state fields.

    `save` sends what the journal does not have: the state fields that changed
    and the events after the ones it holds. It sends where those events start,
    so a save that is sent again after a lost answer changes nothing. A history
    the journal does not hold, or holds fewer events of than expected (a deleted
    file), is sent whole. A save that fails leaves what is remembered as it was,
    so the next one sends everything since the last good one.

    The caller makes sure two saves of one key never overlap."""

    def __init__(self, kind: str, what: str):
        """`what` names the kind of thing kept, for log lines."""
        self._kind = kind
        self._what = what
        # What the journal holds for each key: how many events, and its state.
        self._held: dict[str, tuple[int, dict]] = {}
        # Keys whose last save failed. A journal that stays down is logged once, not at every save.
        self._failing: set[str] = set()

    def save(self, key: str, events: list[dict], state: dict) -> None:
        """Brings the journal's history of `key` up to `events` and `state`.
        `events` only grows, and `state` has the same fields each time."""
        if not isinstance(key, str):
            raise ValueError(f"a history is kept under a text key, not {key!r}")
        held = self._held.get(key)
        # Fewer events than the journal holds means the history was not only added
        # to, so the journal's copy is replaced.
        if held is not None and len(events) >= held[0]:
            start, saved = held
            changed = {name: value for name, value in state.items() if saved.get(name) != value}
            response = _send(
                "POST",
                f"/histories/{self._kind}/{key}/append",
                SAVE_TIMEOUT,
                {"start": start, "state": changed, "events": events[start:]},
            )
            if response.status_code != _GAP:
                raise_for_business_error(response)
                self._held[key] = (len(events), copy.deepcopy(state))
                return
        _request("PUT", f"/histories/{self._kind}/{key}", body={"state": state, "events": events})
        self._held[key] = (len(events), copy.deepcopy(state))

    def save_quietly(self, key: str, events: list[dict], state: dict) -> None:
        """save() for a caller that must go on if the journal fails: the failure
        is logged, once until a save works again, and not raised."""
        try:
            self.save(key, events, state)
        except Exception:
            if key not in self._failing:
                self._failing.add(key)
                logger.exception("could not keep %s %s in the journal", self._what, key)
        else:
            self._failing.discard(key)

    def load_all(self, build: Callable[[str, list[dict], dict], T]) -> list[T]:
        """`build(key, events, state)` for each history the journal holds.
        `build` raises ValueError for one that is not what the caller keeps. A
        history that cannot be read or built is skipped with a log line."""
        keys = _request("GET", f"/histories/{self._kind}", READ_TIMEOUT).json()["keys"]
        built = []
        for key in keys:
            try:
                body = _request("GET", f"/histories/{self._kind}/{key}", READ_TIMEOUT).json()
                item = build(key, body["events"], body["state"])
            except (AgentServiceError, ValueError) as e:
                logger.warning("skipping %s %s: %s", self._what, key, e)
                continue
            self._held[key] = (len(body["events"]), copy.deepcopy(body["state"]))
            built.append(item)
        return built


# --- run attempts -------------------------------------------------------------------------


def reserve_attempt(run_id: str, stage: str) -> str:
    """Claims the next attempt of a stage in a run. Returns its name, such as "attempt_3"."""
    return _request("POST", f"/attempts/{run_id}", body={"stage": stage}).json()["attempt"]


def record_attempt(
    run_id: str,
    stage: str,
    attempt: str,
    *,
    filename: str,
    content: str,
    result: dict,
    prompt: dict | None = None,
    prompt_version: str | None = None,
    extra_files: dict[str, str] | None = None,
) -> None:
    """Records a reserved attempt: its artifact, the validator's result, and
    optionally its prompt and other files, which can sit in subfolders."""
    _request(
        "PUT",
        f"/attempts/{run_id}/{stage}/{attempt}",
        timeout=READ_TIMEOUT,
        body={
            "filename": filename,
            "content": content,
            "result": result,
            "prompt": prompt,
            "prompt_version": prompt_version,
            "extra_files": extra_files or {},
        },
    )


def discard_attempt(run_id: str, stage: str, attempt: str) -> None:
    """Removes an attempt that was reserved and never recorded."""
    _request("DELETE", f"/attempts/{run_id}/{stage}/{attempt}")


def get_manifest(run_id: str) -> list[dict]:
    return _request("GET", f"/attempts/{run_id}/manifest", READ_TIMEOUT).json()["attempts"]


def get_attempt(run_id: str, stage: str, attempt: str) -> dict:
    return _request("GET", f"/attempts/{run_id}/{stage}/{attempt}", READ_TIMEOUT).json()


# --- platforms ------------------------------------------------------------------------------


def list_platforms() -> list[dict]:
    """Every platform's latest version, each with the list of its versions."""
    return _request("GET", "/platforms", READ_TIMEOUT).json()["platforms"]


def get_platform(platform_id: str, version: str | None = None) -> dict | None:
    """A platform's version (its latest when none is named) with its files, or
    None when there is no such platform or version."""
    path = f"/platforms/{platform_id}" + (f"?version={version}" if version else "")
    try:
        return _request("GET", path, READ_TIMEOUT).json()
    except AgentServiceError as e:
        if e.status_code == 404:
            return None
        raise


def get_pim_transformation() -> str:
    """The SWArch-to-PIM transformation every platform starts with, as ATL text."""
    return _request("GET", "/platforms/pim-transformation", READ_TIMEOUT).json()["transformation"]


def publish_platform(
    *,
    name: str,
    output_filename: str,
    output_model_name: str,
    metamodel: str,
    transformation: str,
    template: str,
    source_run_id: str | None,
    version: str | None = None,
) -> dict:
    """Stores a platform as a new version, `version` if given and the patch
    after the highest one if not. Returns the published version."""
    return _request(
        "POST",
        "/platforms",
        timeout=PUBLISH_TIMEOUT,
        body={
            "name": name,
            "output_filename": output_filename,
            "output_model_name": output_model_name,
            "metamodel": metamodel,
            "transformation": transformation,
            "template": template,
            "source_run_id": source_run_id,
            "version": version,
        },
    ).json()


def purge_platform(platform_id: str, version: str | None = None) -> list[str]:
    """Removes one version of a published platform, or all of them when no
    version is named. Returns the versions removed."""
    path = f"/platforms/{platform_id}" + (f"/versions/{version}" if version else "")
    return _request("DELETE", path).json()["purged"]
