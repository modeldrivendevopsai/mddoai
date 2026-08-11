"""PSM Knowledge Agent: compares serialized platform documentation against one of
MDDOAI's platform-specific metamodels (PSM) and reports missing or outdated parts.

Unlike pim_agent's ground(), this makes a real LLM call (via orchestrator.chat(),
the same ai-layer /chat convention every other agent in this repo uses) rather than
matching against a static knowledge list: the comparison itself, not just grounding
context, is the point of this agent.

There is no single "psmMM.ecore" in this repo. MDDOAI's PSM is realized per target
platform (gitlabMM.ecore, githubMM.ecore, bitbucketMM.ecore), so the metamodel to
compare against is a parameter, not a fixed path. DEFAULT_PSM_METAMODEL_PATH points
at gitlabMM.ecore since MDDOAI targets GitLab specifically (see pim_agent's own
reusability notes), but any of the three can be passed explicitly.
"""

import json
import logging
import re
from dataclasses import dataclass
from pathlib import Path

import orchestrator

logger = logging.getLogger(__name__)

_REPO_ROOT = Path(__file__).resolve().parents[3]
DEFAULT_PSM_METAMODEL_PATH = str(
    _REPO_ROOT / "meta_models" / "com.mddoai.metamodel.gitlab" / "model" / "gitlabMM.ecore"
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


def _parse_suggestions(content: str) -> list[Suggestion]:
    match = re.search(r"\[.*\]", content, re.DOTALL)
    if not match:
        logger.warning("compare(): no JSON array in LLM response: %r", content[:300])
        return []
    try:
        items = json.loads(match.group(0))
    except json.JSONDecodeError:
        logger.warning("compare(): unparseable JSON from LLM: %r", match.group(0)[:300])
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

    NOTE on `serialized_docs`: this is currently treated as opaque text. The "Serialization
    agent" that would parse raw platform documentation into a structured, labeled format
    (issue #221, Documentation Parser) does not exist yet, so there is no defined schema for
    "serialized docs" to validate or destructure against. This function hands the raw string
    to the LLM as-is and lets it do the interpretation, the same posture the retrieval
    service's clean_page_content() takes toward raw markdown. This is not a TODO to resolve
    now; it's a flag that this interface may need revisiting once #221 lands and the real
    output shape is known.

    `psm_metamodel_path` defaults to DEFAULT_PSM_METAMODEL_PATH (gitlabMM.ecore) when not
    given; pass githubMM.ecore's or bitbucketMM.ecore's path explicitly to compare against
    a different platform's PSM.
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
    response = orchestrator.chat(messages)
    content = response.choices[0].message.content or ""
    return _parse_suggestions(content)
