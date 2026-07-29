"""A generic, reusable LLM tool-calling reply engine: given a chat function,
a system prompt, an event, and (optionally) a list of Tools, build a reply.
With no tools it can only comment; given tools it can also decide to act.

Has zero knowledge of MDDOAI, pipelines, stages, or any specific project:
nothing here refers to a stage, a docs crawl, or any of MDDOAI's own tool
names. A Tool bundles its own LLM-facing schema and real Python
implementation as one object, supplied by the caller, so this module only
ever turns (chat_fn, system_prompt, event, history, tools) into a reply.
MDDOAI's actual system prompt and its 7 real tools live in pipeline_tools.py,
not here, that's what keeps this file honestly reusable.
"""
import json
from dataclasses import dataclass
from typing import Callable


@dataclass
class Tool:
    """One callable ability: its LLM-facing schema and its real Python
    implementation as a single object. stages=None means available
    regardless of which pipeline stage is current; otherwise a list of
    stage names this tool is restricted to. "stages" is itself a generic
    optional-scoping concept here, not something this module assumes means
    "pipeline stage" specifically."""

    name: str
    description: str
    parameters: dict
    impl: Callable[..., dict]
    stages: list[str] | None = None

    def schema(self) -> dict:
        return {
            "type": "function",
            "function": {"name": self.name, "description": self.description, "parameters": self.parameters},
        }


def load_tools(stage: str | None, tools: list[Tool]) -> list[Tool]:
    """The subset of tools whose optional stages restriction includes the
    given stage (or that have none)."""
    return [tool for tool in tools if tool.stages is None or stage in tool.stages]


def dispatch_tool(name: str, arguments: dict, tools: list[Tool]):
    for tool in tools:
        if tool.name == name:
            return tool.impl(**arguments)
    raise ValueError(f"Unknown tool: {name}")


def build_reply(
    chat_fn,
    system_prompt: str,
    event: dict,
    history: list[dict] | None,
    tools: list[Tool] | None,
) -> dict:
    messages = [{"role": "system", "content": system_prompt}]
    for past in history or []:
        messages.append({"role": "user", "content": json.dumps(past)})
    messages.append({"role": "user", "content": json.dumps(event)})

    if tools:
        response = chat_fn(messages, tools=[tool.schema() for tool in tools], tool_choice="auto")
    else:
        response = chat_fn(messages)
    tool_calls = response.get("tool_calls") or []

    if not tool_calls:
        # Someone is waiting on a reply either way, but the two fallbacks differ:
        # with tools available and none called, a clarifying question is useful;
        # narrating (no tools) has no one to ask, so a plain filler is enough.
        fallback = "Could you clarify which stage and what you'd like done?" if tools else "(no reply)"
        return {"tool_called": None, "result": None, "message": response.get("content") or fallback}

    steps = []
    for call in tool_calls:
        function = call["function"]
        try:
            arguments = json.loads(function.get("arguments") or "{}")
        except json.JSONDecodeError:
            arguments = {}
        try:
            result = dispatch_tool(function["name"], arguments, tools)
        except Exception as e:
            result = {"error": str(e)}
        steps.append({"tool": function["name"], "arguments": arguments, "result": result})

    return {"tool_called": steps[-1]["tool"], "result": steps[-1]["result"], "steps": steps}
