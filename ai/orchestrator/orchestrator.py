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


# --- Stage-based pipeline generation ---------------------------------------
#
# A PSM -> ATL -> Acceleo -> generation pipeline. Each stage has one agent
# that makes a real LLM call; a human reviews the stage's output and either
# approves it (advancing to the next stage) or rejects it with a correction
# that's recorded as a constraint for the stage's next run.

STAGES = ["psm", "atl", "acceleo", "generation"]

_STAGE_SYSTEM_PROMPTS = {
    "psm": (
        "You are the MDDOAI PSM (Platform-Specific Model) agent. Given a description of a "
        "CI/CD platform, produce a clear PSM-level description: express the platform's "
        "concepts (jobs, stages, triggers, artifacts, agents/runners) in MDDOAI's "
        "platform-specific metamodel terms. Be precise and structured."
    ),
    "atl": (
        "You are the MDDOAI ATL transformation agent. Given a PSM-level description, describe "
        "the ATL (ATLAS Transformation Language) transformation rules needed to map the "
        "platform-independent model to this PSM: name the rules, and describe their source "
        "and target patterns and the mapping logic between them."
    ),
    "acceleo": (
        "You are the MDDOAI Acceleo template agent. Given a description of ATL transformation "
        "rules, describe the Acceleo code-generation template needed to turn the transformed "
        "model into real pipeline configuration files: the template's structure, its key "
        "generation blocks, and the output files it targets."
    ),
    "generation": (
        "You are the MDDOAI generation summary agent. Given the PSM description, the ATL "
        "transformation rules, and the Acceleo template plan produced in the prior stages, "
        "produce a final, concise summary of the full pipeline generation plan, from the "
        "original platform input through to the generated CI/CD configuration."
    ),
}


def _constraints_note(context: dict, stage: str) -> str:
    constraints = context.get("constraints", {}).get(stage, [])
    if not constraints:
        return ""
    bullet_list = "\n".join(f"- {c}" for c in constraints)
    return f"\n\nApply these corrections from prior review:\n{bullet_list}"


def psm_agent(context: dict) -> str:
    platform_description = context.get("platform_description", "")
    user_content = platform_description + _constraints_note(context, "psm")
    messages = [
        {"role": "system", "content": _STAGE_SYSTEM_PROMPTS["psm"]},
        {"role": "user", "content": user_content},
    ]
    response = chat(messages)
    return response.choices[0].message.content


def atl_agent(context: dict) -> str:
    psm_output = context.get("psm_output", "")
    user_content = psm_output + _constraints_note(context, "atl")
    messages = [
        {"role": "system", "content": _STAGE_SYSTEM_PROMPTS["atl"]},
        {"role": "user", "content": user_content},
    ]
    response = chat(messages)
    return response.choices[0].message.content


def acceleo_agent(context: dict) -> str:
    atl_output = context.get("atl_output", "")
    user_content = atl_output + _constraints_note(context, "acceleo")
    messages = [
        {"role": "system", "content": _STAGE_SYSTEM_PROMPTS["acceleo"]},
        {"role": "user", "content": user_content},
    ]
    response = chat(messages)
    return response.choices[0].message.content


def gen_agent(context: dict) -> str:
    user_content = (
        f"PSM description:\n{context.get('psm_output', '')}\n\n"
        f"ATL transformation rules:\n{context.get('atl_output', '')}\n\n"
        f"Acceleo template plan:\n{context.get('acceleo_output', '')}"
    ) + _constraints_note(context, "generation")
    messages = [
        {"role": "system", "content": _STAGE_SYSTEM_PROMPTS["generation"]},
        {"role": "user", "content": user_content},
    ]
    response = chat(messages)
    return response.choices[0].message.content


stage_agents = {
    "psm": psm_agent,
    "atl": atl_agent,
    "acceleo": acceleo_agent,
    "generation": gen_agent,
}


def validate(output: str) -> bool:
    return is_good_enough(output)


class Orchestrator:
    """Tracks progress through STAGES and runs each stage's agent."""

    def __init__(self):
        self.current_stage_index = 0
        self.constraints: dict[str, list[str]] = {}
        self.last_context: dict = {}

    @property
    def current_stage(self) -> str | None:
        if self.current_stage_index >= len(STAGES):
            return None
        return STAGES[self.current_stage_index]

    def run_stage(self, context: dict) -> dict:
        stage = self.current_stage
        agent = stage_agents[stage]
        self.last_context = context
        # self.constraints is looked up fresh (not snapshotted at run_stage() call time),
        # so a correction recorded via add_constraint() after this call is picked up the
        # next time run_stage()/rerun_stage() runs this same stage.
        enriched_context = {**context, "constraints": self.constraints}
        output = agent(enriched_context)
        return {"stage": stage, "output": output, "valid": validate(output)}

    def rerun_stage(self) -> dict:
        """Re-run the current stage with the same context passed to the last
        run_stage() call, picking up any constraints recorded since then."""
        return self.run_stage(self.last_context)

    def add_constraint(self, stage: str, constraint: str) -> None:
        self.constraints.setdefault(stage, []).append(constraint)

    def advance_stage(self) -> str | None:
        self.current_stage_index += 1
        return self.current_stage

    def stage_result(self, stage_id: str, approved: bool, correction: str | None = None) -> dict:
        if approved:
            next_stage = self.advance_stage()
            if next_stage is None:
                return {"status": "complete"}
            return {"status": "advanced", "next_stage": next_stage}
        self.add_constraint(stage_id, correction)
        return {"status": "rerun", "stage": stage_id}


_default = Orchestrator()


def run_stage(context: dict) -> dict:
    return _default.run_stage(context)


def rerun_stage() -> dict:
    return _default.rerun_stage()


def add_constraint(stage: str, constraint: str) -> None:
    _default.add_constraint(stage, constraint)


def advance_stage() -> str | None:
    return _default.advance_stage()


def stage_result(stage_id: str, approved: bool, correction: str | None = None) -> dict:
    return _default.stage_result(stage_id, approved, correction)


def current_stage() -> str | None:
    return _default.current_stage


def reset_pipeline() -> None:
    """Start a fresh pipeline run: replace the default Orchestrator instance,
    dropping any progress and constraints from a prior run."""
    global _default
    _default = Orchestrator()
