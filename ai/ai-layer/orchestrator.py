from router.router import chat

_PIPELINE_KEYWORDS = ("pipeline", "ci/cd", "deploy", "build", "test stages", "generate")
_PLATFORM_KEYWORDS = ("platform", "teamcity", "gitlab", "github actions", "bamboo", "azure devops")

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


def orchestrate(messages: list[dict]) -> str:
    intent = classify_intent(_latest_user_message(messages))
    system_prompt = _SYSTEM_PROMPTS[intent]
    enriched_messages = [{"role": "system", "content": system_prompt}] + messages
    response = chat(enriched_messages)
    return response.choices[0].message.content
