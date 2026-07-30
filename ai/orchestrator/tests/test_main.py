"""
FastAPI endpoint tests for the orchestrator service.

Unlike a typical endpoint test, these mock only the network boundary — httpx.post,
standing in for ai-layer's /chat and retrieval's /fetch — and let the real
orchestrator.py state machine (run_stage_async, record_review, review, current_stage,
events) execute for real. This exercises the actual wiring between main.py's routes
and orchestrator.py's logic, which is exactly the seam this service's independence
from ai-layer/retrieval depends on.

run_stage_async() spawns a real background thread (not FastAPI's BackgroundTasks, see
orchestrator.py), so nothing guarantees it's finished by the time an endpoint's 202
response comes back — the opposite of the old BackgroundTasks-based design, where
TestClient happened to run a scheduled task to completion first. Every helper/test
here that needs a run's outcome calls orchestrator.wait_for_idle() (joining that
thread) BEFORE the enclosing `with patch("orchestrator.httpx.post", ...)` block exits,
never after, otherwise the thread's real work races against the mock being torn down.
test_endpoint_returns_before_background_thread_necessarily_finishes is the explicit,
minimal check for this, every other test's wait_for_idle() placement depends on it.

Every real action (a stage running, a review being recorded) triggers record_event(),
which automatically reacts via a real chat() call, so a single endpoint call can trigger
several httpx.post calls: the docs stage's real retrieval fetch, the real stage agent's
own chat() call, and one narration chat() call per event recorded — plus, for /nudge,
one more up front to decide which tool (if any) to call. _dispatcher() routes each
mocked httpx.post call by target/content rather than requiring every test to hand-count
and order them, since a background thread's calls can interleave with the calling
request's own in either order.

Tests verify:
  1. POST /start (docs-first now) starts the docs stage running in the background and
     returns 202 immediately; a downstream failure surfaces as a call_failed event via
     GET /events, not a 500; 409 if a stage is still running (it resets the pipeline by
     swapping in a fresh Orchestrator, so without this guard a restart mid-run would
     silently orphan the old run's background thread instead of rejecting the request).
  2. POST /review/{stage_id} records the review as an event; approving starts the next
     stage running in the background (202) or returns {"status": "complete"} directly on
     the last stage; rejecting returns {"status": "rerun", ...} directly, without
     starting anything; 400 on stage mismatch or missing correction, 409 if a stage is
     still running.
  3. POST /rerun/{stage_id} starts the current stage running again (202); accepts an
     optional body of structured overrides, but only for the docs stage (retrieval's
     real parameters); 400 for overrides on any other stage, or stage mismatch; 409 if
     busy.
  4. GET /events returns the full (or since_index-sliced) event log, current_stage, and
     busy.
  5. POST /nudge dispatches whatever tool call(s) the (mocked) LLM response returns,
     executing real orchestrator logic; any tool that starts a stage running does so via
     the same background-thread path as the REST endpoints, so nudge() itself only ever
     makes its one routing call, never blocking for a stage's duration; returns a
     clarification directly when no tool is called; converts a downstream error in its
     own routing call into a 500.
  6. POST /reset discards the current run with no new run to replace it, the empty-state
     counterpart to /start; 409 if a stage is still running, same reasoning as /start.
"""
import json
import threading
from unittest.mock import MagicMock, patch

import pytest
from fastapi.testclient import TestClient

import main
import orchestrator

client = TestClient(main.app)


@pytest.fixture(autouse=True)
def reset_orchestrator_state():
    """Each test drives the real Orchestrator singleton through its endpoints, so
    isolate them from each other by swapping in a fresh instance per test."""
    original = orchestrator._default
    orchestrator._default = orchestrator.Orchestrator()
    yield
    orchestrator._default = original


def fake_response(content=None, model="gemini/gemini-2.5-flash", tool_calls=None):
    resp = MagicMock()
    resp.raise_for_status.return_value = None
    resp.json.return_value = {"content": content, "model": model, "tool_calls": tool_calls}
    return resp


def _fake_fetch_response(pages=None, confidence=0.8):
    pages = pages or [{
        "url": "https://example.com/docs", "success": True, "status_code": 200,
        "markdown": "# Docs\nSome real content.", "links": [],
    }]
    resp = MagicMock()
    resp.raise_for_status.return_value = None
    resp.json.return_value = {
        "seed_url": "https://example.com/docs",
        "pages": pages,
        "meta": {"confidence": confidence, "pages_crawled": len(pages), "depth_reached": 1, "pending_links": []},
    }
    return resp


def _dispatcher(agent_response_text="Generic stage output", fetch_pages=None, fetch_confidence=0.8,
                nudge_tool_calls=None, nudge_content=None):
    """Routes a mocked httpx.post call by target/content: a retrieval-shaped
    response for /fetch or /fetch/page; for ai-layer's /chat, tells apart a
    narration call (system prompt mentions "MDDOAI Orchestrator", no tools),
    /nudge's own routing call (same system prompt, WITH tools, only returns
    nudge_tool_calls if the caller configured them), and a real stage-agent
    call (anything else), so tests don't need to hand-count or hand-order
    every call a background thread and a request can jointly trigger."""
    def _dispatch(url, json=None, **kwargs):  # noqa: A002 (shadows builtin `json` module, matches httpx's own kwarg name)
        if url.endswith("/fetch") or url.endswith("/fetch/page"):
            return _fake_fetch_response(pages=fetch_pages, confidence=fetch_confidence)
        payload = json or {}
        system_content = payload.get("messages", [{}])[0].get("content", "")
        if "MDDOAI Orchestrator" in system_content:
            if payload.get("tools") and nudge_tool_calls is not None:
                return fake_response(content=nudge_content, tool_calls=nudge_tool_calls)
            return fake_response("Noted.")
        return fake_response(agent_response_text)
    return _dispatch


def start_pipeline(platform_description="A GitLab CI platform", seed_url="https://example.com/docs"):
    with patch("orchestrator.httpx.post", side_effect=_dispatcher()):
        response = client.post("/start", json={"platform_description": platform_description, "seed_url": seed_url})
        orchestrator.wait_for_idle()
    return response


def approve(stage_id, agent_response_text="Generic stage output"):
    with patch("orchestrator.httpx.post", side_effect=_dispatcher(agent_response_text)):
        response = client.post(f"/review/{stage_id}", json={"approved": True})
        orchestrator.wait_for_idle()
    return response


def _advance_to_psm(psm_output="PSM description"):
    """Starts the pipeline (lands on docs) and approves docs then pim,
    landing on psm with the given output. Every endpoint test that isn't
    specifically about the docs/pim stages builds on this instead of
    hand-rolling the docs fetch and pim approval."""
    start_pipeline()
    approve("docs")
    return approve("pim", agent_response_text=psm_output)


# --- background-thread execution (the load-bearing assumption) ------------------


def test_endpoint_returns_before_background_thread_necessarily_finishes():
    """run_stage_async() spawns a real background thread and returns right
    away, nothing guarantees it's finished by the time the HTTP response
    comes back — confirmed here by deliberately blocking that thread's first
    real call until after the response has already arrived and busy has been
    observed True. Every other test's wait_for_idle() call depends on this
    being true (that's why it must run inside the mock's `with` block, not
    after it)."""
    release = threading.Event()

    def _blocking_dispatch(url, json=None, **kwargs):
        release.wait(timeout=5)
        return _dispatcher()(url, json=json, **kwargs)

    with patch("orchestrator.httpx.post", side_effect=_blocking_dispatch):
        response = client.post(
            "/start", json={"platform_description": "desc", "seed_url": "https://example.com/docs"}
        )
        assert response.status_code == 202
        assert orchestrator.is_busy() is True
        release.set()
        orchestrator.wait_for_idle()

    assert orchestrator.is_busy() is False
    assert len(orchestrator.events()) > 0


# --- POST /start ----------------------------------------------------------------


def test_start_endpoint_schedules_docs_stage_and_returns_202():
    with patch("orchestrator.httpx.post", side_effect=_dispatcher()) as mock_post:
        response = client.post(
            "/start", json={"platform_description": "A GitLab CI platform", "seed_url": "https://example.com/docs"}
        )
        orchestrator.wait_for_idle()

    assert response.status_code == 202
    assert response.json() == {"status": "started", "stage": "docs"}
    mock_post.assert_any_call(f"{orchestrator.RETRIEVAL_URL}/fetch", json={"url": "https://example.com/docs"}, timeout=180.0)
    # the background run really happened, but it's still current, pending
    # review, approving/rejecting is what advances past it
    assert orchestrator.current_stage() == "docs"


def test_start_endpoint_records_call_failed_event_on_downstream_error_not_500():
    with patch("orchestrator.httpx.post", side_effect=RuntimeError("all providers exhausted")):
        response = client.post(
            "/start", json={"platform_description": "A GitLab CI platform", "seed_url": "https://example.com/docs"}
        )
        orchestrator.wait_for_idle()

    # /start itself always succeeds (202), it only starts the work
    assert response.status_code == 202
    events = orchestrator.events()
    failed = [e for e in events if e["type"] == "call_failed"]
    assert len(failed) == 1
    assert "all providers exhausted" in failed[0]["data"]["error"]


def test_start_endpoint_forwards_the_chosen_model_to_every_real_chat_call():
    with patch("orchestrator.httpx.post", side_effect=_dispatcher()) as mock_post:
        client.post(
            "/start",
            json={
                "platform_description": "A GitLab CI platform",
                "seed_url": "https://example.com/docs",
                "model": "gemini-flash",
            },
        )
        orchestrator.wait_for_idle()

    chat_calls = [c for c in mock_post.call_args_list if c.args[0].endswith("/chat")]
    assert chat_calls  # at least the narration call(s) triggered by /start
    assert all(c.kwargs["json"]["model"] == "gemini-flash" for c in chat_calls)


def test_start_endpoint_omits_model_when_none_chosen():
    with patch("orchestrator.httpx.post", side_effect=_dispatcher()) as mock_post:
        client.post(
            "/start", json={"platform_description": "A GitLab CI platform", "seed_url": "https://example.com/docs"}
        )
        orchestrator.wait_for_idle()

    chat_calls = [c for c in mock_post.call_args_list if c.args[0].endswith("/chat")]
    assert chat_calls
    assert all(c.kwargs["json"]["model"] is None for c in chat_calls)


def test_start_endpoint_returns_409_while_busy():
    """Unlike /review, /rerun, and /nudge, this endpoint used to have no busy
    guard: start_pipeline() swaps in a brand-new Orchestrator, so restarting
    (or double-clicking Start) mid-run didn't error, it silently orphaned the
    old run's background thread instead of rejecting the request."""
    start_pipeline()
    orchestrator._default.busy = True

    with patch("orchestrator.httpx.post") as mock_post:
        response = client.post(
            "/start", json={"platform_description": "A different platform", "seed_url": "https://example.com/other"}
        )

    assert response.status_code == 409
    mock_post.assert_not_called()
    # the busy run's own Orchestrator instance is untouched, nothing was reset
    assert orchestrator.current_stage() == "docs"


# --- POST /reset -------------------------------------------------------------------


def test_reset_endpoint_discards_the_current_run():
    start_pipeline()
    assert len(orchestrator.events()) > 0

    response = client.post("/reset")

    assert response.status_code == 200
    assert response.json() == {"status": "reset"}
    assert orchestrator.events() == []
    assert orchestrator.current_stage() == "docs"


def test_reset_endpoint_returns_409_while_busy():
    start_pipeline()
    orchestrator._default.busy = True

    response = client.post("/reset")

    assert response.status_code == 409
    # busy run wasn't discarded
    assert len(orchestrator.events()) > 0


# --- GET /providers ----------------------------------------------------------------


def test_providers_endpoint_proxies_ai_layer():
    payload = [{"name": "gemini-flash", "tier": "free"}, {"name": "claude-subscription", "tier": "subscription"}]
    mock_response = MagicMock()
    mock_response.raise_for_status.return_value = None
    mock_response.json.return_value = payload

    with patch("orchestrator.httpx.get", return_value=mock_response) as mock_get:
        response = client.get("/providers")

    mock_get.assert_called_once_with(f"{orchestrator.AI_LAYER_URL}/providers", timeout=10.0)
    assert response.status_code == 200
    assert response.json() == payload


# --- POST /model -------------------------------------------------------------------


def test_model_endpoint_changes_the_model_for_the_rest_of_the_run():
    response = client.post("/model", json={"model": "claude-subscription"})

    assert response.status_code == 200
    assert response.json() == {"model": "claude-subscription"}
    assert orchestrator.current_model() == "claude-subscription"


def test_model_endpoint_back_to_auto_with_null():
    client.post("/model", json={"model": "claude-subscription"})

    response = client.post("/model", json={"model": None})

    assert response.json() == {"model": None}
    assert orchestrator.current_model() is None


def test_model_endpoint_change_is_picked_up_by_the_next_real_stage_run():
    _advance_to_psm(psm_output="PSM v1")
    client.post("/model", json={"model": "cerebras-120b"})

    with patch("orchestrator.httpx.post", side_effect=_dispatcher("PSM v2")) as mock_post:
        response = client.post("/rerun/psm")
        orchestrator.wait_for_idle()

    assert response.status_code == 202
    chat_calls = [c for c in mock_post.call_args_list if c.args[0].endswith("/chat")]
    agent_calls = [c for c in chat_calls if "MDDOAI Orchestrator" not in c.kwargs["json"]["messages"][0]["content"]]
    assert agent_calls
    assert all(c.kwargs["json"]["model"] == "cerebras-120b" for c in agent_calls)


# --- GET /events ------------------------------------------------------------------


def test_events_endpoint_returns_full_log_current_stage_and_busy():
    start_pipeline()

    response = client.get("/events")

    assert response.status_code == 200
    body = response.json()
    assert len(body["events"]) > 0
    assert body["current_stage"] == "docs"
    assert body["busy"] is False
    assert body["model"] is None


def test_events_endpoint_reports_the_current_model():
    start_pipeline()
    client.post("/model", json={"model": "groq-llama"})

    response = client.get("/events")

    assert response.json()["model"] == "groq-llama"


def test_events_endpoint_since_index_slices_the_log():
    start_pipeline()
    full = client.get("/events").json()["events"]

    sliced = client.get(f"/events?since_index={len(full) - 1}").json()["events"]

    assert sliced == full[-1:]


# --- POST /review/{stage_id} -------------------------------------------------------


def test_review_endpoint_approving_schedules_next_stage_and_returns_202():
    start_pipeline()

    with patch("orchestrator.httpx.post", side_effect=_dispatcher("PIM description")):
        response = client.post("/review/docs", json={"approved": True})
        orchestrator.wait_for_idle()

    assert response.status_code == 202
    assert response.json() == {"status": "started", "stage": "pim"}
    # pim ran and completed for real, but it's pending review now too
    assert orchestrator.current_stage() == "pim"


def test_review_endpoint_returns_complete_status_on_last_stage_approval():
    _advance_to_psm()
    approve("psm", "ATL rules")
    approve("atl", "Acceleo template")

    with patch("orchestrator.httpx.post", side_effect=_dispatcher("Final summary")):
        response = client.post("/review/acceleo", json={"approved": True})
        orchestrator.wait_for_idle()

    assert response.status_code == 202
    assert orchestrator.current_stage() == "generation"

    # approving the last stage records a review_approved event (one narration
    # call) but starts nothing, "complete" has no next stage to run
    with patch("orchestrator.httpx.post", side_effect=_dispatcher()):
        final_response = client.post("/review/generation", json={"approved": True})

    assert final_response.status_code == 200
    assert final_response.json() == {"status": "complete"}


def test_review_endpoint_returns_rerun_status_with_correction_without_scheduling():
    _advance_to_psm()

    with patch("orchestrator.httpx.post", side_effect=_dispatcher()) as mock_post:
        response = client.post(
            "/review/psm", json={"approved": False, "correction": "Missing artifact retention policy"}
        )

    assert response.status_code == 200
    assert response.json() == {"status": "rerun", "stage": "psm"}
    # only the review's own narration call happened, no stage was started
    assert mock_post.call_count == 1


def test_review_endpoint_rejects_mismatched_stage_id():
    start_pipeline()

    with patch("orchestrator.httpx.post", side_effect=_dispatcher()) as mock_post:
        response = client.post("/review/atl", json={"approved": True})

    assert response.status_code == 400
    assert "atl" in response.json()["detail"]
    mock_post.assert_not_called()


def test_review_endpoint_rejects_missing_correction_when_not_approved():
    _advance_to_psm()

    with patch("orchestrator.httpx.post", side_effect=_dispatcher()) as mock_post:
        response = client.post("/review/psm", json={"approved": False})

    assert response.status_code == 400
    assert "correction" in response.json()["detail"].lower()
    mock_post.assert_not_called()


def test_review_endpoint_returns_409_while_busy():
    start_pipeline()
    orchestrator._default.busy = True

    with patch("orchestrator.httpx.post") as mock_post:
        response = client.post("/review/psm", json={"approved": True})

    assert response.status_code == 409
    mock_post.assert_not_called()


# --- POST /rerun/{stage_id} ---------------------------------------------------------


def test_rerun_endpoint_schedules_current_stage_again_and_returns_202():
    _advance_to_psm(psm_output="PSM v1")

    with patch("orchestrator.httpx.post", side_effect=_dispatcher("PSM v2")):
        response = client.post("/rerun/psm")
        orchestrator.wait_for_idle()

    assert response.status_code == 202
    assert response.json() == {"status": "started", "stage": "psm"}


def test_rerun_endpoint_accepts_overrides_for_docs_stage():
    start_pipeline(seed_url="https://example.com/wrong-docs")

    with patch("orchestrator.httpx.post", side_effect=_dispatcher()) as mock_post:
        response = client.post("/rerun/docs", json={"overrides": {"seed_url": "https://example.com/correct-docs"}})
        orchestrator.wait_for_idle()

    assert response.status_code == 202
    fetch_calls = [c for c in mock_post.call_args_list if c.args[0].endswith("/fetch")]
    assert fetch_calls[0].kwargs["json"]["url"] == "https://example.com/correct-docs"


def test_rerun_endpoint_rejects_overrides_for_non_docs_stage():
    _advance_to_psm()

    with patch("orchestrator.httpx.post") as mock_post:
        response = client.post("/rerun/psm", json={"overrides": {"hint": "doesn't apply to psm"}})

    assert response.status_code == 400
    assert "docs" in response.json()["detail"]
    mock_post.assert_not_called()


def test_rerun_endpoint_with_no_body_replays_last_context():
    _advance_to_psm(psm_output="PSM v1")

    with patch("orchestrator.httpx.post", side_effect=_dispatcher("PSM v2")):
        response = client.post("/rerun/psm")
        orchestrator.wait_for_idle()

    assert response.status_code == 202


def test_rerun_endpoint_rejects_mismatched_stage_id():
    start_pipeline()

    with patch("orchestrator.httpx.post") as mock_post:
        response = client.post("/rerun/atl")

    assert response.status_code == 400
    assert "atl" in response.json()["detail"]
    mock_post.assert_not_called()


def test_rerun_endpoint_returns_409_while_busy():
    start_pipeline()
    orchestrator._default.busy = True

    with patch("orchestrator.httpx.post") as mock_post:
        response = client.post("/rerun/psm")

    assert response.status_code == 409
    mock_post.assert_not_called()


# --- POST /nudge ------------------------------------------------------------------
#
# A tool that starts a stage running (rerun_stage, run_stage, stage_result-
# approve, start_pipeline) does so via orchestrator.run_stage_async(), the
# same background-thread path a REST call uses, so /nudge's own response
# only ever carries {"status": "started", ...}, never the finished stage's
# output, that shows up in GET /events (or orchestrator.events() here) once
# the thread completes.


def test_nudge_endpoint_dispatches_rerun_stage_and_returns_started_status():
    _advance_to_psm(psm_output="PSM output")

    dispatcher = _dispatcher(
        "PSM v2", nudge_tool_calls=[{"function": {"name": "rerun_stage", "arguments": "{}"}}]
    )
    with patch("orchestrator.httpx.post", side_effect=dispatcher):
        response = client.post("/nudge", json={"message": "redo the psm stage"})
        orchestrator.wait_for_idle()

    assert response.status_code == 200
    body = response.json()
    assert body["tool_called"] == "rerun_stage"
    assert body["result"] == {"status": "started", "stage": "psm"}
    completed = [e for e in orchestrator.events() if e["type"] == "call_completed"][-1]
    assert completed["data"] == {"stage": "psm", "output": "PSM v2", "valid": True}


def test_nudge_endpoint_returns_clarification_when_no_tool_called():
    start_pipeline()

    with patch(
        "orchestrator.httpx.post",
        return_value=fake_response("Could you clarify which stage you mean?"),
    ):
        response = client.post("/nudge", json={"message": "hello there"})

    assert response.status_code == 200
    assert response.json() == {
        "tool_called": None,
        "result": None,
        "message": "Could you clarify which stage you mean?",
        "model": "gemini/gemini-2.5-flash",
    }


def test_nudge_endpoint_returns_500_on_downstream_error():
    with patch("orchestrator.httpx.post", side_effect=RuntimeError("all providers exhausted")):
        response = client.post("/nudge", json={"message": "redo the psm stage"})

    assert response.status_code == 500
    assert response.json()["detail"] == "all providers exhausted"


def test_nudge_endpoint_returns_409_while_busy():
    start_pipeline()
    orchestrator._default.busy = True

    with patch("orchestrator.httpx.post") as mock_post:
        response = client.post("/nudge", json={"message": "anything"})

    assert response.status_code == 409
    mock_post.assert_not_called()
