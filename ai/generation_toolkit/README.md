# generation_toolkit

Shared, stage-agnostic building blocks for a "build a prompt, call the LLM, validate, retry"
agent. Not a deployed service — no Dockerfile, no port, imported directly as a Python package by
whichever service's own stage agent needs this shape, the same way `clients/` (the other
not-itself-a-service folder under `ai/`) is imported for outbound HTTP calls.

`psm_agent`'s Generation Agent (`psm_agent/generation.py`) is the first real caller. Extracted
ahead of a second real consumer, on a stated, concrete direction, not a speculative one:
`integration_runner`'s remaining placeholder stages (`pim`, `atl`, `acceleo`, `generation`) are
still plain single-shot LLM prompts today, but whichever gets a real implementation next should
reuse this rather than rebuilding the same prompt-assembly and regenerate-loop pattern from
scratch.

## `prompt_builder.build_prompt(parts, constraints=None)`

Pure, deterministic assembly: `parts` (an ordered `dict[str, str]`, e.g.
`{"pim_ecore": ..., "psm_docs": ..., "psm_example": ...}`) pass through unchanged, plus a
`"constraints"` key rendered as a bullet list. No LLM call, no I/O — callers gather their own part
content (reading a file, formatting docs, folding in grounding text, etc.) before calling this.
The returned dict's shape (one key per part, in order, plus `"constraints"`) is also what a
generic prompt-tab UI can render directly, one tab per key (see
`chat-ui/.../PsmStagePanel.tsx`'s own `Tabs` usage).

## `generation_agent.run_with_retry(system_prompt, parts, ...)`

```python
result = run_with_retry(
    system_prompt,
    parts,                          # dict[str, str], passed straight to build_prompt()
    constraints=None,               # list[str] | None — corrections carried in from a prior run
    validate_fn=None,                # Callable[[str], dict] | None — omit for a single-shot call
    root_cause_fn=_default_root_cause,       # Callable[[dict], str] — validation result -> one new constraint
    render_user_content=_default_render,     # Callable[[dict[str,str]], str] — prompt dict -> the LLM's user message
    max_regenerate_rounds=DEFAULT_MAX_REGENERATE_ROUNDS,  # 3
    model=None,
)
# -> {"output": str, "prompt": dict, "validation": dict | None, "rounds": int}
```

Loop: build the prompt, call `ai-layer` via `clients/ai_layer_client.py`, strip a markdown code
fence if the model added one anyway (real models routinely do despite being told not to). If
`validate_fn` was given, call it on the output; if invalid and rounds remain, turn the result into
one new constraint (`root_cause_fn`, default: the validator's first issue, prefixed `"Fix: "`) and
rebuild the prompt for another round. Bounded, so a persistently-invalid generation fails closed
(returns its last, still-invalid attempt) instead of looping forever.

`validation` is `None` when `validate_fn` was never given — a stage with no real validator yet
just omits it and gets a plain single-shot call, no code path change needed once that stage grows
a real validator to check against later. `prompt` in the result always reflects the round that
actually produced `output`, so a caller showing "what was fed to the model" is always showing the
truth, not a stale first attempt.

## Test

```bash
cd generation_toolkit
pytest
```

Mocks `clients.ai_layer_client.chat`, the only real network call this package makes — matches
this repo's other agent tests' convention of mocking the network boundary, not internal logic.
