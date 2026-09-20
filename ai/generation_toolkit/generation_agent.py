"""Generic bounded-regenerate-loop generation: build a prompt, call the LLM,
strip a markdown code fence if the model added one anyway (real models
routinely do, despite being told not to — confirmed against real output),
and — if the caller supplies a real validator — check the result and fold
every one of the validator's issues back in as new constraints for the next
round (skipping any already recorded from an earlier round, so a
persistently-reported issue doesn't get repeated verbatim every round).
Bounded, so a persistently-invalid generation fails closed instead of
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
# last, still-invalid attempt) instead of looping forever. Raised from 3 to
# 6 after a real Acceleo generation run: the model misused four distinct
# reserved OCL/Acceleo keywords as plain identifiers (`context`, `and`,
# `or`, `not`) in one .mtl file, one real ERROR per round wasn't enough
# rounds to work through all four before hitting the old budget. 6 covers
# that real case (four fixes) plus headroom for one more round of
# regressions, without being unbounded. Costs nothing extra on a
# generation that already validates in round 1 (the common case for
# psm/atl today, confirmed against every real run so far), since the loop
# still exits the moment validate_fn reports valid - the cost only lands
# on a generation that keeps failing, where it's now up to 7 real LLM
# calls instead of 4 across all three of psm/atl/acceleo (none override
# this), since none has its own budget yet.
DEFAULT_MAX_REGENERATE_ROUNDS = 6

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


def _default_root_cause(validation: dict) -> list[str]:
    # ERROR only: an invalid result's own issues can carry WARNING entries
    # too (AtlValidator/EcoreValidator/GenModelBuilder/JavaCompilerCheck all
    # emit both severities into the same list; validity itself only ever
    # depends on whether an ERROR is present, see ValidationResult.of()) -
    # a warning isn't what made this invalid, so it isn't a real fix to ask
    # the model for.
    issues = [issue for issue in (validation.get("issues") or []) if issue.get("severity") == "ERROR"]
    if not issues:
        return ["The generated output failed validation for an unspecified reason; try a simpler structure."]
    return [f"Fix: {issue['message']}" for issue in issues]


def run_with_retry(
    system_prompt: str,
    parts: dict[str, str],
    *,
    constraints: list[str] | None = None,
    validate_fn: Callable[[str], dict] | None = None,
    root_cause_fn: Callable[[dict], list[str]] = _default_root_cause,
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
        # Every new fix, not just the first: a model repeating the same
        # category of mistake in several unrelated places (see this
        # module's own DEFAULT_MAX_REGENERATE_ROUNDS comment) needs to be
        # told about all of them to have a real chance of converging. Skips
        # a fix already recorded so a persistently-reported issue doesn't
        # bloat the prompt with the identical line every round.
        for fix in root_cause_fn(validation):
            if fix not in current_constraints:
                current_constraints.append(fix)

    return {"output": output, "prompt": prompt, "validation": validation, "rounds": round_num}
