"""The Serialization stage: turns docs_agent's raw, unstructured documentation
markdown into a structured artifact, labeled against the project's PIM
concepts, for the pim stage (and any future stage) to build on.

It keeps all of the documentation, and calls no model. Three steps:
1. The text is cut into its markdown sections (_split_into_fragments), at each
   heading outside a code fence. A page with no headings (a platform's JSON schema,
   say) is one section.
2. Each piece is labeled deterministically against pim_agent's real PIM concepts
   via pim_agent_client.ground() (_label_fragment).
3. The pieces are written out verbatim under their concept, with the ones that
   match none under "Unrecognized" (_build_markdown), by plain string formatting.

An earlier version asked a model to extract the relevant fragments, and it kept a
few hundred characters of a 60,000 character page (a platform's schema and syntax
page), so the stages after it never saw most of the documentation. Nothing reads
the labels yet (the pim stage is a fixed placeholder), so a model that labels the
pieces better is not worth its calls until a stage does. It would add labels only,
and never choose what is kept.

pim_agent is a real, separate service (own container, see ai/pim_agent/),
reached via clients/pim_agent_client.py, not a local package import — its
/concepts response is fetched once per stage run and threaded through the
helpers below, rather than re-fetched on every call.

Known limitation (Phase 0 MVP, matching pim_agent's own "not RAG" framing):
"matched" here means "ground() returned at least one metamodel-category
result," there's no score threshold, since pim_agent's /ground response
doesn't expose its match score.
"""
import re

from clients import pim_agent_client

# A markdown heading. A `#` line inside a code fence is code (a shell or YAML
# comment), not a heading.
_HEADING_RE = re.compile(r"^#{1,6}\s+(.+?)\s*$")
# A line that opens or closes a fenced code block, as in CommonMark: three or more
# backticks or tildes, indented by up to three spaces, then (for an opening
# fence) an info string.
_FENCE_RE = re.compile(r"^ {0,3}(`{3,}|~{3,})(.*)$")

# What the text before the first heading is called.
_PREAMBLE_NAME = "Documentation"

# Lines of a text, each keeping its newline. Only a newline ends a line: unlike
# str.splitlines, a form feed or a Unicode line separator inside scraped text
# does not, so it cannot put a heading in the middle of a line.
_LINE_RE = re.compile(r"[^\n]*\n|[^\n]+")


def _fence_after(line: str, open_fence: str | None) -> str | None:
    """The fence that is open once `line` has been read: the one that was open
    before it, closed by a fence of the same character that is at least as long,
    or a new one this line opens."""
    match = _FENCE_RE.match(line.rstrip("\r\n"))
    if not match:
        return open_fence
    marker, rest = match.groups()
    if open_fence is not None:
        closes = marker[0] == open_fence[0] and len(marker) >= len(open_fence) and not rest.strip()
        return None if closes else open_fence
    # A backtick fence cannot have a backtick in its info string: that is inline code.
    return None if marker[0] == "`" and "`" in rest else marker


def _split_into_fragments(docs_text: str) -> list[dict]:
    """The text as consecutive sections, cut at each markdown heading outside a
    code fence. Every line of docs_text is in exactly one fragment, in order, so
    nothing is lost. A fragment holding only whitespace is left out, since it
    carries nothing."""
    sections: list[list] = [[_PREAMBLE_NAME, []]]
    open_fence: str | None = None
    for line in _LINE_RE.findall(docs_text):
        heading = None if open_fence else _HEADING_RE.match(line.rstrip("\r\n"))
        if heading:
            sections.append([heading.group(1), [line]])
        else:
            sections[-1][1].append(line)
        open_fence = _fence_after(line, open_fence)

    return [
        {"name": name, "raw_text": "".join(lines)}
        for name, lines in sections
        if "".join(lines).strip()
    ]


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


def _concept_for_entry_title(concepts: dict[str, list[str]], title: str) -> str | None:
    """Reverse lookup: the concepts key whose grouped titles include the
    given metamodel-category entry title, or None if it's not a
    concept-labeling target. Mirrors pim_agent's own reference_knowledge.py
    concept_for_entry_title(), reimplemented client-side against the fetched
    /concepts response since that's the real, current data, not a
    locally-duplicated copy that could drift."""
    for concept, titles in concepts.items():
        if title in titles:
            return concept
    return None


def _label_fragment(fragment: dict, concepts: dict[str, list[str]]) -> dict:
    query = f"{fragment.get('name', '')} {fragment.get('raw_text', '')}"
    matches = [g for g in pim_agent_client.ground(query, top_k=_GROUND_ALL_RESULTS) if g["category"] == "metamodel"]
    concept = _concept_for_entry_title(concepts, matches[0]["title"]) if matches else None
    return {**fragment, "concept": concept, "matched": concept is not None}


_PAGE_COUNT_RE = re.compile(r"Fetched (\d+) page")

# The shortest fence markdown allows.
_MIN_FENCE_CHARS = 3


def _fenced(text: str) -> str:
    """text in a code fence, exactly as it is, except that its final newline is
    the one before the closing fence. The fence is longer than any run of
    backticks inside the text, so text that holds fences of its own cannot end
    this one early."""
    longest_run = max((len(run) for run in re.findall(r"`+", text)), default=0)
    fence = "`" * max(_MIN_FENCE_CHARS, longest_run + 1)
    return f"{fence}\n{text.removesuffix(chr(10))}\n{fence}"


def _build_markdown(labeled_fragments: list[dict], page_count: str, concepts: dict[str, list[str]]) -> str:
    by_concept: dict[str, list[dict]] = {concept: [] for concept in concepts}
    unrecognized: list[dict] = []
    for fragment in labeled_fragments:
        if fragment["matched"]:
            by_concept[fragment["concept"]].append(fragment)
        else:
            unrecognized.append(fragment)

    def render_section(title: str, fragments: list[dict]) -> str:
        if not fragments:
            return f"## {title}\n\n(none found)\n"
        items = "\n\n".join(f"### {f.get('name', 'unnamed')}\n\n{_fenced(f.get('raw_text', ''))}" for f in fragments)
        return f"## {title}\n\n{items}\n"

    sections = [render_section(concept, fragments) for concept, fragments in by_concept.items()]
    sections.append(render_section("Unrecognized", unrecognized))

    header = f"# Serialization: labeled documentation structure\n\nSource: docs stage output ({page_count}), all of it kept.\n"
    return header + "\n" + "\n".join(sections)


def serialization_agent(context: dict) -> str:
    """The serialization stage: reads context["docs_output"] (docs_agent's raw
    concatenated per-page markdown), cuts it into its markdown sections,
    labels each against pim_agent's real PIM concepts, and returns a structured
    markdown artifact with one section per concept plus Unrecognized, holding all
    of the documentation."""
    docs_output = context.get("docs_output", "")
    page_count_match = _PAGE_COUNT_RE.search(docs_output)
    page_count = f"{page_count_match.group(1)} page(s)" if page_count_match else "page count unknown"

    concepts = pim_agent_client.concepts()
    fragments = _split_into_fragments(docs_output)
    labeled = [_label_fragment(fragment, concepts) for fragment in fragments]
    return _build_markdown(labeled, page_count, concepts)
