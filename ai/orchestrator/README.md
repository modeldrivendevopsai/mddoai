# orchestrator

Intent-based prompt orchestration for MDDOAI. Given a chat history, it classifies the latest
user message's intent, builds an intent-specific system prompt, and calls the `ai-layer`
router's `chat()` to get a response — retrying automatically if the response looks low quality.

## What it does

`orchestrate(messages)`:

1. Reads the latest `role: "user"` message from the conversation.
2. Classifies its intent into one of:
   - `pipeline_generation` — mentions pipeline, CI/CD, deploy, build, test stages, or generate.
   - `platform_question` — mentions platform, TeamCity, GitLab, GitHub Actions, Bamboo, or Azure DevOps.
   - `general_question` — anything else.
3. Prepends an intent-specific system prompt to the conversation.
4. Calls `router.router.chat()` (from `ai-layer`) with the enriched messages.
5. Returns the response content as a string.

## Retry loop

A low-quality response (a refusal, an "I don't know", or an empty reply) usually means the
system prompt needs tightening rather than the provider being down, so `orchestrate()` retries
the call before giving up:

- Up to `MAX_ATTEMPTS` (3) calls to `chat()`.
- After each attempt, `is_good_enough(response)` checks that the response is non-empty and
  doesn't contain obvious error markers (`"I cannot"`, `"I don't know"`, `"error"`, ...).
- A response that passes is returned immediately — no unnecessary extra calls.
- Every failed attempt is logged (`logging.warning`) with the attempt number and the system
  prompt used, so a human can see which intent's prompt needs curation.
- If all `MAX_ATTEMPTS` attempts fail the quality check, the last response is returned as-is
  with an appended note that human curation of the prompt is needed — callers always get
  *something* back, never an exception.

## Setup

```bash
pip install -r requirements.txt
```

This module imports `router.router.chat` from the sibling `ai-layer/` service, so it must be
run alongside a checkout that has `ai-layer/` configured (`.env` with at least one provider
key — see `ai-layer/README.md`).

## Use

```python
from orchestrator import orchestrate

response = orchestrate([{"role": "user", "content": "Generate a CI/CD pipeline for a Node app"}])
print(response)
```

`ai-layer/main.py`'s `POST /orchestrate` endpoint calls this directly.

## Test

```bash
cd orchestrator
pytest
```

No real API calls are made — `chat()` is mocked in tests.
