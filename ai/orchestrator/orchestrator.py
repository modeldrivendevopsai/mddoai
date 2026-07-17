import json
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

_ERROR_MARKERS = ("i cannot", "i don't know", "i do not know", "an error occurred", "sorry, an error")


def is_good_enough(response: str) -> bool:
    """Non-empty and free of obvious refusal/error markers."""
    if not response or not response.strip():
        return False
    lowered = response.lower()
    return not any(marker in lowered for marker in _ERROR_MARKERS)


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
        self.last_output: str | None = None

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
        self.last_output = output
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
            approved_output = self.last_output
            next_stage = self.advance_stage()
            if next_stage is None:
                return {"status": "complete"}
            next_context = {**self.last_context, f"{stage_id}_output": approved_output}
            return self.run_stage(next_context)
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


def start_pipeline(platform_description: str) -> dict:
    """Reset the pipeline and start fresh at the psm stage for a new platform
    description. Same behavior POST /orchestrate/start uses."""
    reset_pipeline()
    return run_stage({"platform_description": platform_description})


# --- Outer judgment layer ----------------------------------------------------
#
# Maps a free-form human message ("the ATL stage output is wrong, please fix it
# with X") onto the pipeline functions above via real LLM tool calling — not
# keyword matching. The LLM decides which tool(s) to call and with what
# arguments; judge() just executes whatever it decides and reports the result.

TOOLS = [
    {
        "type": "function",
        "function": {
            "name": "run_stage",
            "description": (
                "Start or continue the CURRENT pending pipeline stage using the given context. "
                "Use this to kick off the pipeline from scratch (context should include "
                "'platform_description') or to re-run the current stage with fresh/updated "
                "input. Do NOT use this for approving, rejecting, or redoing existing output — "
                "use stage_result or rerun_stage for that."
            ),
            "parameters": {
                "type": "object",
                "properties": {
                    "context": {
                        "type": "object",
                        "description": (
                            "Input for the current stage's agent. For the psm stage this should "
                            "include 'platform_description'. Later stages build their context "
                            "automatically and rarely need this supplied manually."
                        ),
                    }
                },
                "required": ["context"],
            },
        },
    },
    {
        "type": "function",
        "function": {
            "name": "rerun_stage",
            "description": (
                "Re-run the CURRENT pending stage's agent, reusing the context from its last "
                "run and folding in any constraints recorded via add_constraint since then. Use "
                "this when the user wants the current stage redone right now — e.g. 'redo the "
                "ATL stage', or immediately after calling add_constraint to apply a correction "
                "the user just gave."
            ),
            "parameters": {"type": "object", "properties": {}},
        },
    },
    {
        "type": "function",
        "function": {
            "name": "stage_result",
            "description": (
                "Record a human review decision for a named stage. approved=true advances the "
                "pipeline and immediately runs the next stage — use this when the user "
                "approves/accepts a stage's output. approved=false with a correction records "
                "the correction for later (it does NOT rerun immediately) — use this only when "
                "the user explicitly rejects a stage without asking for an immediate redo; if "
                "they want it fixed right now, prefer add_constraint followed by rerun_stage "
                "instead."
            ),
            "parameters": {
                "type": "object",
                "properties": {
                    "stage_id": {
                        "type": "string",
                        "enum": STAGES,
                        "description": "Which stage this decision applies to.",
                    },
                    "approved": {
                        "type": "boolean",
                        "description": "true to approve and advance, false to reject.",
                    },
                    "correction": {
                        "type": ["string", "null"],
                        "description": "Required when approved is false: the human's correction.",
                    },
                },
                "required": ["stage_id", "approved"],
            },
        },
    },
    {
        "type": "function",
        "function": {
            "name": "add_constraint",
            "description": (
                "Record a correction against a stage without rerunning it yet. The correction "
                "is automatically folded into that stage's prompt the next time it runs (via "
                "rerun_stage, or the pipeline advancing into it). Call this immediately before "
                "rerun_stage when the user gives feedback and wants a stage fixed now — e.g. "
                "'the ATL rules are wrong, use kebab-case names' -> "
                "add_constraint('atl', 'Use kebab-case rule names') then rerun_stage()."
            ),
            "parameters": {
                "type": "object",
                "properties": {
                    "stage": {
                        "type": "string",
                        "enum": STAGES,
                        "description": "Which stage this correction applies to.",
                    },
                    "constraint": {
                        "type": "string",
                        "description": "The human's correction/instruction for this stage.",
                    },
                },
                "required": ["stage", "constraint"],
            },
        },
    },
    {
        "type": "function",
        "function": {
            "name": "start_pipeline",
            "description": (
                "Reset the pipeline and start fresh at the psm stage for a NEW platform "
                "description, discarding all progress, approved stages, and recorded "
                "constraints from the current run. Use this when the user wants to start over "
                "or switch to a different platform entirely — e.g. 'let's do this for GitLab "
                "instead' or 'start over with a new platform' — NOT for continuing or redoing "
                "the current pipeline (use run_stage or rerun_stage for that)."
            ),
            "parameters": {
                "type": "object",
                "properties": {
                    "platform_description": {
                        "type": "string",
                        "description": "Description of the new CI/CD platform to generate a pipeline for.",
                    }
                },
                "required": ["platform_description"],
            },
        },
    },
]

_JUDGE_SYSTEM_PROMPT_TEMPLATE = """You are the MDDOAI Orchestrator judge. MDDOAI turns a CI/CD \
platform description into generated pipeline tooling through four fixed stages, always in \
this order:

  1. psm        — a PSM (Platform-Specific Model) description of the platform.
  2. atl        — the ATL transformation rules needed to build that PSM.
  3. acceleo    — the Acceleo code-generation template for that ATL.
  4. generation — a final summary tying all three stages together.

The current pending stage — the one whose output a human is reviewing right now — is: \
{current_stage}.

You have five tools that operate on this pipeline: run_stage, rerun_stage, stage_result, \
add_constraint, start_pipeline (see their descriptions for what each does). Given the user's \
message, decide which tool(s) to call, and in what order, to carry out their request:
  - Feedback the user wants applied immediately ("fix X", "the ATL output is wrong, do Y \
instead") -> call add_constraint for that stage, then rerun_stage.
  - A plain redo with no specific correction ("redo the psm stage") -> call rerun_stage alone.
  - Approval ("looks good", "approve the acceleo stage") -> call stage_result(approved=true).
  - A rejection that should just be recorded, not rerun yet -> call \
stage_result(approved=false, correction=...).
  - Starting over or switching to a different platform entirely ("let's do this for GitLab \
instead", "start over with a new platform") -> call start_pipeline with the new platform \
description. Do NOT use rerun_stage or run_stage for this — those act on the current \
in-progress pipeline, not a fresh one.
  - Anything that doesn't map to a pipeline action -> don't call any tool; ask a clarifying \
question in your reply instead."""


def _dispatch_tool(name: str, arguments: dict):
    if name == "run_stage":
        return run_stage(arguments.get("context", {}))
    if name == "rerun_stage":
        return rerun_stage()
    if name == "stage_result":
        return stage_result(arguments["stage_id"], arguments["approved"], arguments.get("correction"))
    if name == "add_constraint":
        add_constraint(arguments["stage"], arguments["constraint"])
        return None
    if name == "start_pipeline":
        return start_pipeline(arguments["platform_description"])
    raise ValueError(f"Unknown tool: {name}")


def judge(user_message: str) -> dict:
    """Let the LLM decide which pipeline tool(s) to call for a free-form human
    message, execute them, and report what happened."""
    stage = current_stage()
    stage_description = stage if stage is not None else "none — the pipeline hasn't been started"
    system_prompt = _JUDGE_SYSTEM_PROMPT_TEMPLATE.format(current_stage=stage_description)
    messages = [
        {"role": "system", "content": system_prompt},
        {"role": "user", "content": user_message},
    ]
    response = chat(messages, tools=TOOLS, tool_choice="auto")
    message = response.choices[0].message
    tool_calls = message.tool_calls or []

    if not tool_calls:
        return {
            "tool_called": None,
            "result": None,
            "message": message.content or (
                "I couldn't map that to a pipeline action — could you clarify which stage and "
                "what you'd like done?"
            ),
        }

    steps = []
    for call in tool_calls:
        try:
            arguments = json.loads(call.function.arguments or "{}")
        except json.JSONDecodeError:
            arguments = {}
        try:
            result = _dispatch_tool(call.function.name, arguments)
        except Exception as e:
            result = {"error": str(e)}
        steps.append({"tool": call.function.name, "arguments": arguments, "result": result})

    return {
        "tool_called": steps[-1]["tool"],
        "result": steps[-1]["result"],
        "steps": steps,
    }
