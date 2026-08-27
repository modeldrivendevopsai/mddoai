"""
FastAPI endpoint tests for the orchestrator service.

Real end-to-end test of the two-service split, not hand-mocked JSON shapes:
clients/integration_runner_client's httpx calls are routed to a REAL,
in-process integration_runner.main.app via httpx.ASGITransport — this
exercises integration_runner's actual FastAPI validation, busy guards, and
stage staleness checks too, the same ones a real deployed integration_runner
container would enforce, not orchestrator's own guesses about what they'd
say. clients/ai_layer_client's, clients/retrieval_client's, and
clients/validator_agent_client's httpx calls (the true external network
boundary) are still mocked directly, same as every other test in this
repo — the last of those three only matters once a test reaches pim/psm/atl
/acceleo, which now call validator_agent_client for their own real (mock)
content instead of ai_layer_client (see integration_runner/stages/pim/
agent.py etc.).

Narration is fully decoupled from the request/response cycle now (see
chat_log.py): recording a stage event on integration_runner never triggers
a chat() call by itself, narration only happens lazily, in the background,
the next time something polls GET /events. Most tests here never poll
/events, so they never see a narration call at all — only the stage agent's
own real chat()/retrieval call (mocked). Tests that DO poll /events (and
therefore want to assert on the resulting narration) wait for chat_log's
own background thread explicitly (_wait_for_narration()), separately from
integration_runner's own stage-run thread (integration_runner.runs.wait_for_idle()).

Tests verify:
  1. POST /start starts the docs stage running in the background and
     returns 202 immediately; a downstream failure surfaces as a
     call_failed event via GET /events, not a 500; 409 if a stage is still
     running (integration_runner's own busy guard, not orchestrator's).
  2. POST /review/{stage_id} records the review; approving starts the next
     stage running (202) or returns {"status": "complete"} on the last
     stage; rejecting returns {"status": "rerun", ...} without starting
     anything; 400 on stage mismatch or missing correction, 409 if busy.
  3. POST /rerun/{stage_id} starts the current stage running again (202);
     accepts overrides only for the docs stage; 400 for overrides on any
     other stage, or stage mismatch; 409 if busy.
  4. GET /events returns the merged (mirrored + narrated) event log,
     current_stage, and busy, sourced from integration_runner over HTTP.
  5. POST /message dispatches whatever tool call(s) the (mocked) LLM
     response returns; returns a clarification directly when no tool is
     called; converts a downstream error into a 500.
  6. POST /reset replaces the current run with a fresh, blank one; 409 if busy.
  7. POST /resume/{run_id} makes a past run current again; 404 for an
     unknown run_id; 409 if busy.
"""
import asyncio
from unittest.mock import MagicMock, patch

import httpx
import pytest
from fastapi.testclient import TestClient

import chat_log
import integration_runner.main
import main
from clients import ai_layer_client, integration_runner_client, retrieval_client, serialization_agent_client, validator_agent_client
from integration_runner import runs as ir_runs
from integration_runner.pipeline import IntegrationRun

client = TestClient(main.app)

# Routes integration_runner_client's httpx calls to a REAL, in-process
# integration_runner.main.app instead of a real network connection —
# httpx.ASGITransport runs the actual ASGI app in-process, no real socket.
# ASGITransport only implements the async transport interface (there is no
# sync equivalent), but integration_runner_client itself is synchronous
# (matching every other client in this repo), so each call gets its own
# fresh httpx.AsyncClient + asyncio.run() rather than sharing one client
# across event loops, which httpx's async client doesn't support safely.
def _routed_to_integration_runner(method, url, **kwargs):
    path = url[len(integration_runner_client.INTEGRATION_RUNNER_URL):]

    async def _do_request():
        async with httpx.AsyncClient(
            transport=httpx.ASGITransport(app=integration_runner.main.app), base_url="http://ir-test"
        ) as ac:
            return await ac.request(method, path, **kwargs)

    return asyncio.run(_do_request())


@pytest.fixture(autouse=True)
def real_integration_runner():
    """Each test drives a real integration_runner.main.app through HTTP, so
    isolate them from each other by resetting its own run registry between
    tests (reaching into integration_runner.runs directly, the same state
    integration_runner's own tests reset — orchestrator no longer holds any
    of this itself)."""
    original_default = ir_runs._default
    original_runs = dict(ir_runs._runs)
    ir_runs._default = IntegrationRun()
    ir_runs._runs = {ir_runs._default.run_id: ir_runs._default}
    with patch.object(integration_runner_client, "httpx") as mock_httpx:
        mock_httpx.request.side_effect = _routed_to_integration_runner
        yield
    ir_runs._default = original_default
    ir_runs._runs = original_runs


def _fake_httpx_response(content=None, model="gemini/gemini-2.5-flash", tool_calls=None):
    resp = MagicMock()
    resp.raise_for_status.return_value = None
    resp.json.return_value = {"content": content, "model": model, "tool_calls": tool_calls}
    return resp


def _fake_validate_response(valid=True, issues=None):
    # pim/psm/atl/acceleo now call validator_agent_client for real content
    # instead of ai_layer_client (see integration_runner/stages/pim/agent.py
    # etc.) — approve() below needs this the same way it needs
    # _fake_httpx_response for ai_layer_client, or approving pim/psm/atl
    # makes a genuinely unmocked network call to a real validator-agent
    # that may not be running on the test machine.
    resp = MagicMock()
    resp.raise_for_status.return_value = None
    resp.json.return_value = {"valid": valid, "issues": issues or [], "duration_ms": 1}
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


def _wait_for_narration(run_id: str) -> None:
    thread = chat_log.get_chat_log(run_id)._last_thread
    if thread is not None:
        thread.join(timeout=5)


def start_pipeline(platform_description="A GitLab CI platform", seed_url="https://example.com/docs"):
    with patch.object(retrieval_client, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_fetch_response()
        response = client.post("/start", json={"platform_description": platform_description, "seed_url": seed_url})
        ir_runs.wait_for_idle()
    return response


def approve(stage_id, agent_response_text="Generic stage output"):
    # Whichever stage this approval starts running next might be an
    # LLM-prompt one (ai_layer_client) or a mock-validated one
    # (validator_agent_client) — both mocked here since the caller doesn't
    # know or care which, same reasoning as _fake_httpx_response's own
    # "Generic stage output" default.
    with patch.object(ai_layer_client, "httpx") as mock_chat_httpx, \
            patch.object(validator_agent_client, "httpx") as mock_validate_httpx:
        mock_chat_httpx.post.return_value = _fake_httpx_response(agent_response_text)
        mock_validate_httpx.post.return_value = _fake_validate_response()
        response = client.post(f"/review/{stage_id}", json={"approved": True})
        ir_runs.wait_for_idle()
    return response


def _advance_to_psm():
    """Starts the pipeline (lands on docs) and approves docs then
    serialization then pim, landing on psm. Every endpoint test that isn't
    specifically about the docs/serialization/pim stages builds on this
    instead of hand-rolling the docs fetch and the serialization/pim
    approvals. No output parameter (unlike a plain approve() call): psm's
    own real output is fixed mock content now, not something a caller gets
    to choose (see integration_runner/stages/psm/agent.py) — same is true
    of pim, whose approval is what actually starts psm's real run below.

    Approving docs starts serialization's real run, which calls
    serialization_agent_client.serialize() (a separate service) — needs its
    own mock, or serialization never actually completes and the next
    approval is rejected."""
    start_pipeline()
    with patch.object(serialization_agent_client, "serialize", return_value="Serialized docs"):
        approve("docs")
    approve("serialization")
    return approve("pim")


# --- POST /start ----------------------------------------------------------------


def test_start_endpoint_schedules_docs_stage_and_returns_202():
    with patch.object(retrieval_client, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_fetch_response()
        response = client.post(
            "/start", json={"platform_description": "A GitLab CI platform", "seed_url": "https://example.com/docs"}
        )
        ir_runs.wait_for_idle()

    assert response.status_code == 202
    assert response.json() == {"status": "started", "stage": "docs"}
    mock_httpx.post.assert_any_call(
        f"{retrieval_client.RETRIEVAL_URL}/fetch", json={"url": "https://example.com/docs"},
        timeout=retrieval_client.RETRIEVAL_TIMEOUT,
    )
    # the background run really happened, but it's still current, pending
    # review, approving/rejecting is what advances past it
    assert ir_runs.current().current_stage == "docs"


def test_start_endpoint_records_call_failed_event_on_downstream_error_not_500():
    with patch.object(retrieval_client, "httpx") as mock_httpx:
        mock_httpx.post.side_effect = RuntimeError("all providers exhausted")
        response = client.post(
            "/start", json={"platform_description": "A GitLab CI platform", "seed_url": "https://example.com/docs"}
        )
        ir_runs.wait_for_idle()

    # /start itself always succeeds (202), it only starts the work
    assert response.status_code == 202
    failed = [e for e in ir_runs.current().events if e["type"] == "call_failed"]
    assert len(failed) == 1
    assert "all providers exhausted" in failed[0]["data"]["error"]


def test_start_endpoint_forwards_the_chosen_model_to_the_stage_run():
    with patch.object(retrieval_client, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_fetch_response()
        client.post(
            "/start",
            json={
                "platform_description": "A GitLab CI platform",
                "seed_url": "https://example.com/docs",
                "model": "gemini-flash",
            },
        )
        ir_runs.wait_for_idle()

    assert ir_runs.current().model == "gemini-flash"


def test_start_endpoint_forwards_docs_options_to_the_real_fetch_call():
    with patch.object(retrieval_client, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_fetch_response()
        client.post(
            "/start",
            json={
                "platform_description": "A GitLab CI platform",
                "seed_url": "https://example.com/docs",
                "hint": "focus on syntax",
                "exclude_urls": ["https://example.com/blog"],
                "max_pages": 5,
                "max_depth": 2,
                "force_refresh": True,
            },
        )
        ir_runs.wait_for_idle()

    fetch_calls = [c for c in mock_httpx.post.call_args_list if c.args[0].endswith("/fetch")]
    assert fetch_calls[0].kwargs["json"] == {
        "url": "https://example.com/docs", "hint": "focus on syntax",
        "exclude_urls": ["https://example.com/blog"], "max_pages": 5, "max_depth": 2, "force_refresh": True,
    }


def test_start_endpoint_with_mock_skips_the_real_fetch_call():
    with patch.object(retrieval_client, "httpx") as mock_httpx:
        client.post(
            "/start",
            json={
                "platform_description": "A GitLab CI platform",
                "seed_url": "https://example.com/docs",
                "mock": True,
            },
        )
        ir_runs.wait_for_idle()

    mock_httpx.post.assert_not_called()
    events = ir_runs.current().events
    assert any("MOCKED" in (e.get("data") or {}).get("output", "") for e in events)


def test_start_endpoint_omits_model_when_none_chosen():
    with patch.object(retrieval_client, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_fetch_response()
        client.post(
            "/start", json={"platform_description": "A GitLab CI platform", "seed_url": "https://example.com/docs"}
        )
        ir_runs.wait_for_idle()

    assert ir_runs.current().model is None


def test_start_endpoint_returns_409_while_busy():
    """integration_runner's own busy guard, not orchestrator's — start_pipeline()
    may reuse the current run in place or swap in a brand-new one (see its
    own docstring), but never while it's busy, so restarting (or
    double-clicking Start) mid-run must error, not silently kick off a
    second run against a still-executing background thread."""
    start_pipeline()
    ir_runs.current().busy = True

    response = client.post(
        "/start", json={"platform_description": "A different platform", "seed_url": "https://example.com/other"}
    )

    assert response.status_code == 409
    # the busy run's own IntegrationRun instance is untouched, nothing was reset
    assert ir_runs.current().current_stage == "docs"


# --- POST /reset -------------------------------------------------------------------


def test_reset_endpoint_discards_the_current_run():
    start_pipeline()
    assert len(ir_runs.current().events) > 0

    response = client.post("/reset")

    assert response.status_code == 200
    assert response.json() == {"status": "reset"}
    assert ir_runs.current().events == []
    assert ir_runs.current().current_stage == "docs"


def test_reset_endpoint_returns_409_while_busy():
    start_pipeline()
    ir_runs.current().busy = True

    response = client.post("/reset")

    assert response.status_code == 409
    # busy run wasn't discarded
    assert len(ir_runs.current().events) > 0


# --- POST /resume/{run_id} ----------------------------------------------------------


def test_resume_endpoint_makes_a_past_run_current_again():
    start_pipeline(platform_description="Old run")
    old_run_id = ir_runs.current_run_id()
    start_pipeline(platform_description="Current run")
    assert old_run_id != ir_runs.current_run_id()

    response = client.post(f"/resume/{old_run_id}")

    assert response.status_code == 200
    assert response.json() == {"run_id": old_run_id, "current_stage": "docs"}
    assert ir_runs.current_run_id() == old_run_id
    # the resumed run's own events are untouched, picked up exactly as they were
    assert len(ir_runs.current().events) > 0


def test_resume_endpoint_returns_404_for_an_unknown_run_id():
    response = client.post("/resume/no-such-run")

    assert response.status_code == 404


def test_resume_endpoint_returns_409_while_busy():
    start_pipeline(platform_description="Old run")
    old_run_id = ir_runs.current_run_id()
    start_pipeline(platform_description="Current run")
    ir_runs.current().busy = True

    response = client.post(f"/resume/{old_run_id}")

    assert response.status_code == 409
    assert ir_runs.current_run_id() != old_run_id


# --- GET /providers ----------------------------------------------------------------


def test_providers_endpoint_proxies_ai_layer():
    payload = [{"name": "gemini-flash", "tier": "free"}, {"name": "claude-subscription", "tier": "subscription"}]
    mock_response = MagicMock()
    mock_response.raise_for_status.return_value = None
    mock_response.json.return_value = payload

    with patch.object(ai_layer_client, "httpx") as mock_httpx:
        mock_httpx.get.return_value = mock_response
        response = client.get("/providers")

    mock_httpx.get.assert_called_once_with(f"{ai_layer_client.AI_LAYER_URL}/providers", timeout=10.0)
    assert response.status_code == 200
    assert response.json() == payload


# --- POST /model -------------------------------------------------------------------


def test_model_endpoint_changes_the_model_for_the_rest_of_the_run():
    response = client.post("/model", json={"model": "claude-subscription"})

    assert response.status_code == 200
    assert response.json() == {"model": "claude-subscription"}
    assert ir_runs.current().model == "claude-subscription"


def test_model_endpoint_back_to_auto_with_null():
    client.post("/model", json={"model": "claude-subscription"})

    response = client.post("/model", json={"model": None})

    assert response.json() == {"model": None}
    assert ir_runs.current().model is None


def test_model_endpoint_change_is_picked_up_by_the_next_real_stage_run():
    # generation, not psm: the only remaining stage whose own real call
    # (ai_layer_client.chat) actually reads context["model"] — psm/atl/
    # acceleo call validator_agent_client instead now, which has no concept
    # of a chosen model at all (see integration_runner/stages/psm/agent.py
    # etc.).
    _advance_to_psm()
    approve("psm", "ATL rules")
    approve("atl", "Acceleo template")
    approve("acceleo", "Final summary v1")
    client.post("/model", json={"model": "cerebras-120b"})

    with patch.object(ai_layer_client, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_httpx_response("Final summary v2")
        response = client.post("/rerun/generation")
        ir_runs.wait_for_idle()

    assert response.status_code == 202
    agent_calls = [c for c in mock_httpx.post.call_args_list]
    assert agent_calls
    assert all(c.kwargs["json"]["model"] == "cerebras-120b" for c in agent_calls)


# --- GET /events ------------------------------------------------------------------


def test_events_endpoint_returns_full_log_current_stage_and_busy():
    start_pipeline()
    with patch.object(ai_layer_client, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_httpx_response("Noted.")
        events_response = client.get("/events")
        _wait_for_narration(ir_runs.current_run_id())

    assert events_response.status_code == 200
    body = events_response.json()
    assert len(body["events"]) > 0
    assert body["current_stage"] == "docs"
    assert body["busy"] is False
    assert body["model"] is None
    assert body["is_current"] is True


def test_events_endpoint_reports_the_current_model():
    start_pipeline()
    client.post("/model", json={"model": "groq-oss-120b"})

    response = client.get("/events")

    assert response.json()["model"] == "groq-oss-120b"


def test_events_endpoint_since_index_slices_the_log():
    start_pipeline()
    full = client.get("/events").json()["events"]

    sliced = client.get(f"/events?since_index={len(full) - 1}").json()["events"]

    assert sliced == full[-1:]


def test_events_endpoint_reads_a_past_run_by_id_not_just_default():
    start_pipeline(platform_description="Old run")
    old_run_id = ir_runs.current_run_id()
    start_pipeline(platform_description="Current run")

    response = client.get(f"/events?run_id={old_run_id}")

    assert response.status_code == 200
    assert response.json()["current_stage"] == "docs"
    assert response.json()["is_current"] is False
    assert old_run_id != ir_runs.current_run_id()


def test_events_endpoint_returns_404_for_an_unknown_run_id():
    response = client.get("/events?run_id=no-such-run")

    assert response.status_code == 404


# --- GET /runs --------------------------------------------------------------------


def test_runs_endpoint_lists_history_newest_first_with_current_flag():
    start_pipeline(platform_description="First platform")
    first_id = ir_runs.current_run_id()
    start_pipeline(platform_description="Second platform")
    second_id = ir_runs.current_run_id()

    response = client.get("/runs")

    assert response.status_code == 200
    run_list = response.json()
    assert run_list[0]["run_id"] == second_id
    assert run_list[0]["is_current"] is True
    assert run_list[1]["run_id"] == first_id
    assert run_list[1]["is_current"] is False
    assert run_list[1]["platform_name"] == "First platform"


# --- POST /review/{stage_id} -------------------------------------------------------


def test_review_endpoint_approving_schedules_next_stage_and_returns_202():
    start_pipeline()

    with patch.object(serialization_agent_client, "serialize", return_value="Serialized docs"):
        response = client.post("/review/docs", json={"approved": True})
        ir_runs.wait_for_idle()

    assert response.status_code == 202
    assert response.json() == {"status": "started", "stage": "serialization"}
    # serialization ran and completed for real, but it's pending review now too
    assert ir_runs.current().current_stage == "serialization"
    assert ir_runs.current().last_completed_stage == "serialization"


def test_review_endpoint_returns_complete_status_on_last_stage_approval():
    _advance_to_psm()
    approve("psm", "ATL rules")
    approve("atl", "Acceleo template")

    with patch.object(ai_layer_client, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_httpx_response("Final summary")
        response = client.post("/review/acceleo", json={"approved": True})
        ir_runs.wait_for_idle()

    assert response.status_code == 202
    assert ir_runs.current().current_stage == "generation"

    # approving the last stage has no next stage to run
    final_response = client.post("/review/generation", json={"approved": True})

    assert final_response.status_code == 200
    assert final_response.json() == {"status": "complete"}


def test_review_endpoint_returns_rerun_status_with_correction_without_scheduling():
    _advance_to_psm()

    with patch.object(ai_layer_client, "httpx") as mock_httpx:
        response = client.post(
            "/review/psm", json={"approved": False, "correction": "Missing artifact retention policy"}
        )

    assert response.status_code == 200
    assert response.json() == {"status": "rerun", "stage": "psm"}
    # rejecting doesn't start a stage run — no real chat()/retrieval call at all
    mock_httpx.post.assert_not_called()


def test_review_endpoint_rejects_mismatched_stage_id():
    start_pipeline()

    with patch.object(ai_layer_client, "httpx") as mock_httpx:
        response = client.post("/review/atl", json={"approved": True})

    assert response.status_code == 400
    assert "atl" in response.json()["detail"]
    mock_httpx.post.assert_not_called()


def test_review_endpoint_rejects_missing_correction_when_not_approved():
    _advance_to_psm()

    with patch.object(ai_layer_client, "httpx") as mock_httpx:
        response = client.post("/review/psm", json={"approved": False})

    assert response.status_code == 400
    assert "correction" in response.json()["detail"].lower()
    mock_httpx.post.assert_not_called()


def test_review_endpoint_returns_409_while_busy():
    start_pipeline()
    ir_runs.current().busy = True

    with patch.object(ai_layer_client, "httpx") as mock_httpx:
        response = client.post("/review/psm", json={"approved": True})

    assert response.status_code == 409
    mock_httpx.post.assert_not_called()


# --- POST /rerun/{stage_id} ---------------------------------------------------------


def test_rerun_endpoint_schedules_current_stage_again_and_returns_202():
    _advance_to_psm()

    # psm's own real rerun calls validator_agent_client now, not
    # ai_layer_client (see integration_runner/stages/psm/agent.py).
    with patch.object(validator_agent_client, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_validate_response()
        response = client.post("/rerun/psm")
        ir_runs.wait_for_idle()

    assert response.status_code == 202
    assert response.json() == {"status": "started", "stage": "psm"}


def test_rerun_endpoint_accepts_overrides_for_docs_stage():
    start_pipeline(seed_url="https://example.com/wrong-docs")

    with patch.object(retrieval_client, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_fetch_response()
        response = client.post("/rerun/docs", json={"overrides": {"seed_url": "https://example.com/correct-docs"}})
        ir_runs.wait_for_idle()

    assert response.status_code == 202
    fetch_calls = [c for c in mock_httpx.post.call_args_list if c.args[0].endswith("/fetch")]
    assert fetch_calls[0].kwargs["json"]["url"] == "https://example.com/correct-docs"


def test_rerun_endpoint_rejects_overrides_for_non_docs_stage():
    _advance_to_psm()

    with patch.object(ai_layer_client, "httpx") as mock_httpx:
        response = client.post("/rerun/psm", json={"overrides": {"hint": "doesn't apply to psm"}})

    assert response.status_code == 400
    assert "docs" in response.json()["detail"]
    mock_httpx.post.assert_not_called()


def test_rerun_endpoint_with_no_body_replays_last_context():
    _advance_to_psm()

    with patch.object(validator_agent_client, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_validate_response()
        response = client.post("/rerun/psm")
        ir_runs.wait_for_idle()

    assert response.status_code == 202


def test_rerun_endpoint_rejects_mismatched_stage_id():
    start_pipeline()

    with patch.object(ai_layer_client, "httpx") as mock_httpx:
        response = client.post("/rerun/atl")

    assert response.status_code == 400
    assert "atl" in response.json()["detail"]
    mock_httpx.post.assert_not_called()


def test_rerun_endpoint_returns_409_while_busy():
    start_pipeline()
    ir_runs.current().busy = True

    with patch.object(ai_layer_client, "httpx") as mock_httpx:
        response = client.post("/rerun/psm")

    assert response.status_code == 409
    mock_httpx.post.assert_not_called()


# --- POST /message ------------------------------------------------------------------
#
# A tool that starts a stage running (rerun_stage, run_stage, stage_result-
# approve, start_pipeline) does so via integration_runner's own REST
# endpoints, the same background-thread path a direct REST call uses, so
# /message's own response only ever carries {"status": "started", ...},
# never the finished stage's output — that shows up via GET /events once
# integration_runner's thread completes.


def test_message_endpoint_dispatches_rerun_stage_and_returns_started_status():
    _advance_to_psm()

    tool_calls = [{"function": {"name": "rerun_stage", "arguments": "{}"}}]
    with patch.object(ai_layer_client, "httpx") as mock_httpx:
        mock_httpx.post.side_effect = [
            _fake_httpx_response(None, tool_calls=tool_calls),  # send_message()'s own routing call
        ]
        response = client.post("/message", json={"message": "redo the psm stage"})

    assert response.status_code == 200
    body = response.json()
    assert body["tool_called"] == "rerun_stage"
    assert body["result"] == {"status": "started", "stage": "psm"}


def test_message_endpoint_returns_clarification_when_no_tool_called():
    start_pipeline()

    with patch.object(ai_layer_client, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_httpx_response("Could you clarify which stage you mean?")
        response = client.post("/message", json={"message": "hello there"})

    assert response.status_code == 200
    assert response.json() == {
        "tool_called": None,
        "result": None,
        "message": "Could you clarify which stage you mean?",
        "model": "gemini/gemini-2.5-flash",
    }


def test_message_endpoint_returns_500_on_downstream_error():
    with patch.object(ai_layer_client, "httpx") as mock_httpx:
        mock_httpx.post.side_effect = RuntimeError("all providers exhausted")
        response = client.post("/message", json={"message": "redo the psm stage"})

    assert response.status_code == 500
    assert response.json()["detail"] == "all providers exhausted"


def test_message_endpoint_has_no_busy_guard_but_a_dispatched_mutation_still_gets_the_real_409():
    """Deliberately different from every mutating endpoint above: a message
    that doesn't need a tool (a status question, small talk) should still
    get a reply even while a stage is busy, so /message itself never 409s.
    A tool call that WOULD mutate state still hits integration_runner's
    real busy guard, surfaced as that step's own {"error": ...} result
    rather than blocking the whole message (see main.py's own docstring
    for this endpoint)."""
    start_pipeline()
    ir_runs.current().busy = True

    tool_calls = [{"function": {"name": "rerun_stage", "arguments": "{}"}}]
    with patch.object(ai_layer_client, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_httpx_response(None, tool_calls=tool_calls)
        response = client.post("/message", json={"message": "redo it"})

    assert response.status_code == 200
    body = response.json()
    assert body["tool_called"] == "rerun_stage"
    assert "still running" in body["result"]["error"]
