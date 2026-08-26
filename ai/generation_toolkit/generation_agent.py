"""Generic bounded-regenerate-loop generation: build a prompt, call the LLM,
strip a markdown code fence if the model added one anyway (real models
routinely do, despite being told not to — confirmed against real output),
and — if the caller supplies a real validator — check the result and fold
the validator's first issue back in as one new constraint for the next
round. Bounded, so a persistently-invalid generation fails closed instead of
looping forever on an LLM that keeps making the same category of mistake.

A stage with no real validator yet (see this package's own __init__.py)
simply omits `validate_fn`, which turns this into a plain single-shot
generate call with the same return shape — nothing else about calling this
needs to change once that stage gets a real validator to check against.
"""
import re
from typing import Callable

from clients import ai_layer_client

from .prompt_builder import build_prompt

# The paper (Karlovs-Karlovskis)'s iterative refinement protocol this
# repo's PSM Generation Agent is modeled on adds one constraint per round.
# Bounded so a persistently-invalid generation fails closed (returns its
# last, still-invalid attempt) instead of looping forever.
DEFAULT_MAX_REGENERATE_ROUNDS = 3

_CODE_FENCE_RE = re.compile(r"^```(?:\w+)?\s*\n(.*)\n```\s*$", re.DOTALL)


def strip_code_fence(raw: str) -> str:
    match = _CODE_FENCE_RE.match(raw.strip())
    return match.group(1) if match else raw.strip()


def _default_render(prompt: dict[str, str]) -> str:
    sections = [f"{key}:\n{value}" for key, value in prompt.items() if key != "constraints"]
    content = "\n\n".join(sections)
    if prompt.get("constraints"):
        content += f"\n\nApply these corrections from prior rounds:\n{prompt['constraints']}"
    return content


def _default_root_cause(validation: dict) -> str:
    issues = validation.get("issues") or []
    if not issues:
        return "The generated output failed validation for an unspecified reason; try a simpler structure."
    return f"Fix: {issues[0]['message']}"


def run_with_retry(
    system_prompt: str,
    parts: dict[str, str],
    *,
    constraints: list[str] | None = None,
    validate_fn: Callable[[str], dict] | None = None,
    root_cause_fn: Callable[[dict], str] = _default_root_cause,
    render_user_content: Callable[[dict[str, str]], str] = _default_render,
    max_regenerate_rounds: int = DEFAULT_MAX_REGENERATE_ROUNDS,
    model: str | None = None,
) -> dict:
    """Returns {"output": str, "prompt": dict, "validation": dict | None, "rounds": int}.
    `prompt` always reflects the round that actually produced `output`, so a caller
    showing "what was fed to the model" is always showing the truth. `validation` is
    None when `validate_fn` was never given (single-shot mode)."""
    current_constraints = list(constraints or [])
    output = ""
    prompt: dict[str, str] = {}
    validation: dict | None = None
    round_num = 1

    for round_num in range(1, max_regenerate_rounds + 2):
        prompt = build_prompt(parts, current_constraints)
        messages = [
            {"role": "system", "content": system_prompt},
            {"role": "user", "content": render_user_content(prompt)},
        ]
        response = ai_layer_client.chat(messages, model=model)
        output = strip_code_fence(response["content"] or "")

        if validate_fn is None:
            break
        validation = validate_fn(output)
        if validation.get("valid") or round_num > max_regenerate_rounds:
            break
        current_constraints.append(root_cause_fn(validation))

    return {"output": output, "prompt": prompt, "validation": validation, "rounds": round_num}
