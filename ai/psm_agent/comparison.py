"""PSM Knowledge Agent: compares serialized platform documentation against one of
MDDOAI's platform-specific metamodels (PSM) and reports missing or outdated parts.

Unlike pim_agent's ground(), this makes a real LLM call (via ai_layer_client.chat(),
the same ai-layer /chat convention every other agent in this repo uses) rather than
matching against a static knowledge list: the comparison itself, not just grounding
context, is the point of this agent.

There is no single "psmMM.ecore" in this repo. MDDOAI's PSM is realized per target
platform (gitlabMM.ecore, githubMM.ecore, and bitbucketMM.ecore once added), so the
metamodel to compare against is a parameter, not a fixed path. DEFAULT_PSM_METAMODEL_PATH
points at gitlabMM.ecore since MDDOAI targets GitLab specifically (see pim_agent's own
reusability notes), but any of the three can be passed explicitly (bitbucketMM.ecore's
path too, once that metamodel exists).

META_MODELS_DIR (env-configurable) is where meta_models/ (the real Java/Eclipse
metamodel source tree, outside ai/ entirely) is reachable from inside this
container. Defaults to the real relative repo path for local/non-Docker dev;
the real docker-compose entry sets it to the container's mount point.
"""

import json
import logging
import os
from dataclasses import dataclass
from pathlib import Path

from clients import ai_layer_client

logger = logging.getLogger(__name__)

_META_MODELS_DIR = os.environ.get(
    "META_MODELS_DIR", str(Path(__file__).resolve().parents[2] / "meta_models")
)
DEFAULT_PSM_METAMODEL_PATH = str(
    Path(_META_MODELS_DIR) / "com.mddoai.metamodel.gitlab" / "model" / "gitlabMM.ecore"
)

_SYSTEM_PROMPT = """You are the MDDOAI PSM (Platform-Specific Model) knowledge agent. You are \
given serialized platform documentation and a PSM metamodel definition (raw Ecore XML). \
Compare them and identify:
  - "missing": a concept, property, or relationship the documentation describes that the \
metamodel has no representation for.
  - "outdated": a concept the metamodel does represent, but in a way that no longer matches \
what the documentation describes (renamed field, changed type, changed relationship, etc).

Respond with ONLY a JSON array, no prose, no markdown code fences. Each element must be an \
object with exactly these keys:
  "kind": "missing" or "outdated"
  "target": the metamodel element or concept this concerns (a class, property, or concept name)
  "description": a detailed explanation of the gap or discrepancy
  "source_excerpt": the relevant snippet from the serialized docs supporting this finding, or \
null if none applies

If there are no gaps, return an empty JSON array: []"""


@dataclass
class Suggestion:
    """One suggestion about a missing or outdated part of the PSM metamodel, found by
    comparing it against serialized platform documentation. This shape is the stable
    public contract: implementation of compare() can change without breaking callers."""

    kind: str  # "missing" | "outdated"
    target: str  # what part of the PSM metamodel this concerns
    description: str
    source_excerpt: str | None  # relevant snippet from the serialized docs


def _extract_json_array(content: str) -> str | None:
    """Find the first '[' and scan forward tracking bracket depth (respecting JSON
    string literals and escapes, so a '[' or ']' inside a quoted value doesn't
    throw off the count) to find that '['s true matching ']'. Unlike a greedy
    regex, this is correct even when a stray bracket appears elsewhere in
    surrounding prose the LLM wasn't supposed to include."""
    start = content.find("[")
    if start == -1:
        return None
    depth = 0
    in_string = False
    escaped = False
    for i in range(start, len(content)):
        char = content[i]
        if in_string:
            if escaped:
                escaped = False
            elif char == "\\":
                escaped = True
            elif char == '"':
                in_string = False
            continue
        if char == '"':
            in_string = True
        elif char == "[":
            depth += 1
        elif char == "]":
            depth -= 1
            if depth == 0:
                return content[start:i + 1]
    return None


def _parse_suggestions(content: str) -> list[Suggestion]:
    array_text = _extract_json_array(content)
    if array_text is None:
        logger.warning("compare(): no JSON array in LLM response: %r", content[:300])
        return []
    try:
        items = json.loads(array_text)
    except json.JSONDecodeError:
        logger.warning("compare(): unparseable JSON from LLM: %r", array_text[:300])
        return []

    suggestions = []
    for item in items:
        if not isinstance(item, dict):
            continue
        try:
            suggestions.append(
                Suggestion(
                    kind=item["kind"],
                    target=item["target"],
                    description=item["description"],
                    source_excerpt=item.get("source_excerpt"),
                )
            )
        except KeyError:
            logger.warning("compare(): LLM suggestion missing a required key, dropped: %r", item)
    return suggestions


def compare(serialized_docs: str, psm_metamodel_path: str | None = None) -> list[Suggestion]:
    """Compare serialized docs against a PSM metamodel, return suggestions about
    missing or outdated parts.

    NOTE on `serialized_docs`: this is currently treated as opaque text. This
    function hands the raw string to the LLM as-is and lets it do the
    interpretation, the same posture the retrieval service's
    clean_page_content() takes toward raw markdown.

    `psm_metamodel_path` defaults to DEFAULT_PSM_METAMODEL_PATH (gitlabMM.ecore) when not
    given; pass githubMM.ecore's path explicitly to compare against a different platform's
    PSM (bitbucketMM.ecore's path too, once that metamodel exists).
    """
    metamodel_path = psm_metamodel_path or DEFAULT_PSM_METAMODEL_PATH
    metamodel_content = Path(metamodel_path).read_text()

    user_content = (
        f"PSM metamodel ({metamodel_path}):\n{metamodel_content}\n\n"
        f"Serialized platform documentation:\n{serialized_docs}"
    )
    messages = [
        {"role": "system", "content": _SYSTEM_PROMPT},
        {"role": "user", "content": user_content},
    ]
    response = ai_layer_client.chat(messages)
    content = response["content"] or ""
    return _parse_suggestions(content)
