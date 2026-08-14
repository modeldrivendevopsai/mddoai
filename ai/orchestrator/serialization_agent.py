"""The Serialization stage: turns docs_agent's raw, unstructured documentation
markdown into a structured artifact, labeled against the project's PIM
concepts, for the pim stage (and any future stage) to build on.

Three steps, matching the architecture diagram exactly (one LLM call, not
three): an LLM call extracts pipeline/job/task-shaped fragments from the raw
prose (_extract_fragments); each fragment is then labeled deterministically
against pim_agent's real PIM_CONCEPTS via ground() (_label_fragment, no LLM
call); the labeled fragments are assembled into markdown by plain string
formatting (_build_markdown, no LLM call). A fragment ground() can't match to
any of the 9 concepts is never dropped, it's kept under an "Unrecognized"
section — the point of this stage is complete, honest restructuring, not
lossy filtering.

Known limitation (Phase 0 MVP, matching pim_agent's own "not RAG" framing):
"matched" here means "ground() returned at least one metamodel-category
result," there's no score threshold, since GroundingExample doesn't expose
its match score (see reference_knowledge.py's _score, internal to that
module). A future change to expose match confidence would touch
GroundingExample's own documented "stable public contract" and deserves its
own review, not a drive-by change bundled into this stage.
"""
import json
import re

import orchestrator
from pim_agent import ground, PIM_CONCEPTS, concept_for_entry_title

_EXTRACTION_SYSTEM_PROMPT = (
    "You are the MDDOAI Serialization agent's extraction step. Given raw CI/CD "
    "platform documentation text, find every distinct pipeline, job, task, trigger, "
    "agent, or other structural CI/CD concept described in it. For each one, return "
    "a JSON object with three fields: \"type\" (a short free-text label for what kind "
    "of thing this is, e.g. \"pipeline\", \"job\", \"task\", \"trigger\", \"agent\"), "
    "\"name\" (its name, or a short descriptive label if the documentation doesn't "
    "give it one), and \"raw_text\" (the relevant excerpt describing it, taken "
    "verbatim from the documentation). Return ONLY a JSON array of these objects, no "
    "prose, no markdown code fences. If nothing structural is described, return an "
    "empty array []."
)


def _concept_context() -> str:
    """Builds the 'known PIM concepts' block fed into extraction by calling
    ground() once per PIM_CONCEPTS key, rather than hardcoding concept
    descriptions as prompt text — this tracks reference_knowledge.py
    automatically if its metamodel entries ever change."""
    lines = []
    for concept in PIM_CONCEPTS:
        matches = ground(concept, top_k=1)
        if matches:
            lines.append(f"- {concept}: {matches[0].content.splitlines()[0]}")
    return "\n".join(lines)


_CODE_FENCE_RE = re.compile(r"^```(?:json)?\s*\n(.*)\n```\s*$", re.DOTALL)


def _strip_code_fence(raw: str) -> str:
    """LLMs asked for "only JSON, no markdown" still routinely wrap it in a
    ```json ... ``` fence anyway (confirmed against a real model, not just a
    hypothetical). Unwrap one if present so a well-formed response doesn't
    trip json.loads() and fall into the unparsed-fragment fallback below."""
    match = _CODE_FENCE_RE.match(raw.strip())
    return match.group(1) if match else raw


def _extract_fragments(docs_text: str, model: str | None) -> list[dict]:
    user_content = f"Known PIM concepts for context:\n{_concept_context()}\n\n---\n\n{docs_text}"
    messages = [
        {"role": "system", "content": _EXTRACTION_SYSTEM_PROMPT},
        {"role": "user", "content": user_content},
    ]
    raw = orchestrator.chat(messages, model=model)["content"] or ""
    try:
        fragments = json.loads(_strip_code_fence(raw))
        if not isinstance(fragments, list):
            raise ValueError("extraction output was valid JSON but not a list")
        return fragments
    except (json.JSONDecodeError, ValueError):
        # Degrade gracefully rather than crash the stage: treat the whole
        # input as one unparsed fragment, still surfaced (as Unrecognized via
        # _label_fragment, since it won't ground to any real concept), never
        # silently dropped.
        return [{"type": "other", "name": "unparsed extraction output", "raw_text": docs_text}]


# ground() ranks matches across ALL categories (metamodel, process, reusability,
# limitation) and only *then* truncates to top_k - it has no category filter of its
# own. Calling it with a small top_k here would let this function's own
# metamodel-only filter see a pre-truncated slice: a fragment worded close to
# several non-metamodel entries can fill every available slot before the filter
# below ever gets a chance to see a real metamodel match ranked further down -
# a genuine match silently reported as unrecognized. Request every entry
# ground() could ever return instead, comfortably above the knowledge base's
# real size, so the metamodel filter runs over the full ranked list, not a
# pre-truncated one.
_GROUND_ALL_RESULTS = 1000


def _label_fragment(fragment: dict) -> dict:
    query = f"{fragment.get('name', '')} {fragment.get('raw_text', '')}"
    matches = [g for g in ground(query, top_k=_GROUND_ALL_RESULTS) if g.category == "metamodel"]
    concept = concept_for_entry_title(matches[0].title) if matches else None
    return {**fragment, "concept": concept, "matched": concept is not None}


_PAGE_COUNT_RE = re.compile(r"Fetched (\d+) page")


def _build_markdown(labeled_fragments: list[dict], page_count: str) -> str:
    by_concept: dict[str, list[dict]] = {concept: [] for concept in PIM_CONCEPTS}
    unrecognized: list[dict] = []
    for fragment in labeled_fragments:
        if fragment["matched"]:
            by_concept[fragment["concept"]].append(fragment)
        else:
            unrecognized.append(fragment)

    def render_section(title: str, fragments: list[dict]) -> str:
        if not fragments:
            return f"## {title}\n\n(none found)\n"
        items = "\n\n".join(
            f"- **{f.get('name', 'unnamed')}** ({f.get('type', 'other')})\n\n  ```\n  {f.get('raw_text', '')}\n  ```"
            for f in fragments
        )
        return f"## {title}\n\n{items}\n"

    sections = [render_section(concept, fragments) for concept, fragments in by_concept.items()]
    sections.append(render_section("Unrecognized", unrecognized))

    header = f"# Serialization: labeled documentation structure\n\nSource: docs stage output ({page_count}).\n"
    return header + "\n" + "\n".join(sections)


def serialization_agent(context: dict) -> str:
    """The serialization stage: reads context["docs_output"] (docs_agent's raw
    concatenated per-page markdown), extracts structural fragments, labels
    each against the 9 PIM concepts, and returns a structured markdown
    artifact with one section per concept plus Unrecognized."""
    docs_output = context.get("docs_output", "")
    page_count_match = _PAGE_COUNT_RE.search(docs_output)
    page_count = f"{page_count_match.group(1)} page(s)" if page_count_match else "page count unknown"

    fragments = _extract_fragments(docs_output, context.get("model"))
    labeled = [_label_fragment(fragment) for fragment in fragments]
    return _build_markdown(labeled, page_count)
