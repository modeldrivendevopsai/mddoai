import logging
import sys
from pathlib import Path

# router.router lives in the sibling ai-layer/ service, not inside this module,
# so it has to be added to sys.path explicitly before the import below resolves.
_AI_LAYER_DIR = Path(__file__).resolve().parent.parent / "ai-layer"
if str(_AI_LAYER_DIR) not in sys.path:
    sys.path.insert(0, str(_AI_LAYER_DIR))

from router.router import chat  # noqa: E402

logger = logging.getLogger(__name__)

MAX_ATTEMPTS = 3

_PIPELINE_KEYWORDS = ("pipeline", "ci/cd", "deploy", "build", "test stages", "generate")
_PLATFORM_KEYWORDS = ("platform", "teamcity", "gitlab", "github actions", "bamboo", "azure devops")
_ERROR_MARKERS = ("i cannot", "i don't know", "i do not know", "error")

_SYSTEM_PROMPTS = {
    "pipeline_generation": (
        "You are the MDDOAI pipeline generation assistant. Help the user define their "
        "CI/CD pipeline requirements clearly so they can be passed to the generation agents."
    ),
    "platform_question": (
        "You are the MDDOAI platform expert. Help the user understand supported CI/CD "
        "platforms and their capabilities."
    ),
    "general_question": "You are the MDDOAI assistant. Help the user with their DevOps questions.",
}


def _latest_user_message(messages: list[dict]) -> str:
    for message in reversed(messages):
        if message.get("role") == "user":
            return message.get("content", "")
    return ""


def classify_intent(text: str) -> str:
    lowered = text.lower()
    if any(keyword in lowered for keyword in _PIPELINE_KEYWORDS):
        return "pipeline_generation"
    if any(keyword in lowered for keyword in _PLATFORM_KEYWORDS):
        return "platform_question"
    return "general_question"


def is_good_enough(response: str) -> bool:
    """Non-empty and free of obvious refusal/error markers."""
    if not response or not response.strip():
        return False
    lowered = response.lower()
    return not any(marker in lowered for marker in _ERROR_MARKERS)


def orchestrate(messages: list[dict]) -> str:
    intent = classify_intent(_latest_user_message(messages))
    system_prompt = _SYSTEM_PROMPTS[intent]
    enriched_messages = [{"role": "system", "content": system_prompt}] + messages

    last_content = ""
    for attempt in range(1, MAX_ATTEMPTS + 1):
        response = chat(enriched_messages)
        last_content = response.choices[0].message.content
        if is_good_enough(last_content):
            return last_content
        logger.warning(
            "orchestrate attempt %d/%d did not pass the quality check (prompt: %r)",
            attempt, MAX_ATTEMPTS, system_prompt,
        )

    return (
        f"{last_content}\n\n"
        f"[This response did not pass automatic quality checks after {MAX_ATTEMPTS} attempts "
        "— human curation of the prompt is recommended.]"
    )
