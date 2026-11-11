"""serialization_agent/serialization.py unit tests: the serialization stage
that sits between docs and pim, turning docs_agent's raw markdown into a
structured, PIM-concept-labeled artifact that holds all of it.

pim_agent is a real, separate service now (own container), reached via
clients/pim_agent_client.py — mocked at that boundary throughout this file,
matching this repo's "mock only the true I/O boundary" convention. The
autouse `real_ground` fixture below delegates pim_agent_client.ground()'s
mock to pim_agent's own real, unmocked ground() (pure/deterministic keyword
matching, already exercised for real in ai/pim_agent/tests/
test_reference_knowledge.py), so labeling tests here still exercise real
matching behavior over the mocked HTTP boundary, not a hand-faked stub.
No model is called by this stage.

Tests verify:
  1. _split_into_fragments cuts at markdown headings outside code fences
     (matching a closing fence to its opening one), only at newlines, keeps
     every line in order, and leaves out only whitespace.
  2. _label_fragment matches real CI/CD vocabulary to the right PIM concept
     via pim_agent's real (mocked-boundary) ground(), and leaves genuinely
     unrelated text unmatched.
  3. _build_markdown always emits all 9 PIM_CONCEPTS headers plus a final
     Unrecognized section, even when empty, places each fragment under its
     matched concept's header (or Unrecognized when unmatched), and keeps its
     text exactly as it is, even text that holds code fences of its own.
  4. serialization_agent() end-to-end: reads context["docs_output"], calls no
     model, and holds all of a long, heading-less page (the shape of a
     platform's JSON schema), which an earlier version that asked a model to
     extract fragments reduced to a few hundred characters.
  5. Regression guard: PIM_CONCEPTS has exactly 9 keys, and every metamodel-
     category entry in reference_knowledge._KNOWLEDGE is mapped to one of them,
     so a future new metamodel entry can't silently go unlabeled.
  6. Issue #221's AC: a real Atlassian Bamboo documentation excerpt (a
     platform not seen during development, see tests/fixtures/bamboo_sample.md)
     runs through the real, unmocked sectioning, ground(), labeling and
     markdown assembly, and every line of it is in the output.
"""
from dataclasses import asdict
from pathlib import Path
from unittest.mock import patch

import pytest

import serialization_agent
from serialization_agent import serialization
from clients import ai_layer_client, pim_agent_client
from pim_agent import PIM_CONCEPTS, reference_knowledge

_FIXTURES = Path(__file__).parent / "fixtures"

_CONCEPTS = {concept: list(titles) for concept, titles in PIM_CONCEPTS.items()}


def _real_ground_as_dicts(query, top_k=5):
    """pim_agent_client.ground() over the wire returns JSON dicts, not
    GroundingExample dataclasses; delegate to pim_agent's real ground() so
    tests here exercise real matching behavior, not a hand-faked stub."""
    return [asdict(g) for g in reference_knowledge.ground(query, top_k)]


@pytest.fixture(autouse=True)
def real_ground():
    with patch.object(pim_agent_client, "ground", side_effect=_real_ground_as_dicts):
        yield


def _lines(text):
    return [line.strip() for line in text.splitlines() if line.strip()]


# --- _split_into_fragments ---------------------------------------------------


def test_split_cuts_at_each_heading_and_keeps_the_text_before_the_first_one():
    docs = "Fetched 1 page(s).\n\n# Page\nintro\n## Steps\nrun it\n### Detail\nmore\n"

    fragments = serialization._split_into_fragments(docs)

    assert [f["name"] for f in fragments] == ["Documentation", "Page", "Steps", "Detail"]
    assert fragments[0]["raw_text"] == "Fetched 1 page(s).\n\n"
    assert fragments[2]["raw_text"] == "## Steps\nrun it\n"


def test_split_keeps_every_line_in_order():
    docs = "pre\n# A\na1\na2\n## B\nb1\n"

    fragments = serialization._split_into_fragments(docs)

    assert "".join(f["raw_text"] for f in fragments) == docs


def test_split_does_not_cut_at_a_hash_line_inside_a_code_fence():
    docs = "# Setup\n```bash\n# install it\nnpm ci\n```\n~~~\n# also code\n~~~\n## After\ntext\n"

    fragments = serialization._split_into_fragments(docs)

    assert [f["name"] for f in fragments] == ["Setup", "After"]
    assert "# install it" in fragments[0]["raw_text"] and "# also code" in fragments[0]["raw_text"]


def test_split_matches_a_closing_fence_to_its_opening_one():
    # A tilde fence line inside a backtick fence, and a bare ``` inside a four-backtick fence, do not close them.
    docs = "# A\n```md\n~~~\n# not a heading\n```\n## B\ntext\n# C\n````\n```\n# still code\n````\n## D\nend\n"

    fragments = serialization._split_into_fragments(docs)

    assert [f["name"] for f in fragments] == ["A", "B", "C", "D"]
    assert "# not a heading" in fragments[0]["raw_text"] and "# still code" in fragments[2]["raw_text"]


def test_split_does_not_treat_inline_code_that_starts_a_line_as_a_fence():
    docs = "# A\n```code``` is inline.\n# B\ntext\n"

    assert [f["name"] for f in serialization._split_into_fragments(docs)] == ["A", "B"]


def test_split_cuts_only_at_newlines_not_at_other_line_separators():
    # A Unicode line separator and a form feed inside scraped text are not line ends.
    docs = "# A\nbefore # not a heading\fx\n## B\ntext\n"

    fragments = serialization._split_into_fragments(docs)

    assert [f["name"] for f in fragments] == ["A", "B"]
    assert "".join(f["raw_text"] for f in fragments) == docs


def test_split_keeps_windows_line_endings():
    docs = "# A\r\nline\r\n## B\r\ntext\r\n"

    fragments = serialization._split_into_fragments(docs)

    assert [f["name"] for f in fragments] == ["A", "B"]
    assert "".join(f["raw_text"] for f in fragments) == docs


def test_split_leaves_out_only_a_fragment_that_is_all_whitespace():
    docs = "\n\n# Only\ntext\n"

    assert [f["name"] for f in serialization._split_into_fragments(docs)] == ["Only"]


def test_split_of_nothing_is_nothing():
    assert serialization._split_into_fragments("") == []


# --- _label_fragment ---------------------------------------------------------


def test_label_fragment_matches_real_ci_cd_vocabulary():
    fragment = {
        "name": "Scheduled build",
        "raw_text": "Runs on a cron expression schedule trigger",
    }

    labeled = serialization._label_fragment(fragment, _CONCEPTS)

    assert labeled["matched"] is True
    assert labeled["concept"] == "Trigger"


def test_label_fragment_leaves_unrelated_text_unmatched():
    fragment = {"name": "", "raw_text": "What's the weather like in Paris tomorrow"}

    labeled = serialization._label_fragment(fragment, _CONCEPTS)

    assert labeled["matched"] is False
    assert labeled["concept"] is None


def test_label_fragment_finds_a_metamodel_match_ranked_past_grounds_default_top_k():
    # Regression test: ground()'s default top_k truncates across ALL categories
    # before _label_fragment ever gets to filter for "metamodel" - a fragment worded
    # close to several process-category "Step N: ..." entries can fill every
    # available slot with process hits, pushing a real "Step types" (metamodel)
    # match further down the ranking than the default ever reaches. Without
    # requesting a large top_k in _label_fragment, this fragment would incorrectly
    # come back unmatched despite a clear, real concept for it.
    fragment = {
        "name": "Build step",
        "raw_text": (
            "Command step build step init environment output script "
            "input platform-specific model"
        ),
    }

    labeled = serialization._label_fragment(fragment, _CONCEPTS)

    assert labeled["matched"] is True
    assert labeled["concept"] == "Steps"


# --- _build_markdown -----------------------------------------------------------


def test_build_markdown_always_includes_all_nine_concept_headers_and_unrecognized():
    markdown = serialization._build_markdown([], "3 page(s)", _CONCEPTS)

    for concept in PIM_CONCEPTS:
        assert f"## {concept}" in markdown
    assert "## Unrecognized" in markdown


def test_build_markdown_places_matched_fragment_under_its_concept_header():
    labeled = [{"name": "Nightly build", "raw_text": "cron schedule", "concept": "Trigger", "matched": True}]

    markdown = serialization._build_markdown(labeled, "1 page(s)", _CONCEPTS)

    trigger_section = markdown.split("## Trigger")[1].split("\n## ")[0]
    assert "Nightly build" in trigger_section
    assert "cron schedule" in trigger_section


def test_build_markdown_places_unmatched_fragment_under_unrecognized():
    labeled = [{"name": "Mystery thing", "raw_text": "platform-specific oddity", "concept": None, "matched": False}]

    markdown = serialization._build_markdown(labeled, "1 page(s)", _CONCEPTS)

    unrecognized_section = markdown.split("## Unrecognized")[1]
    assert "Mystery thing" in unrecognized_section
    assert "platform-specific oddity" in unrecognized_section


def test_build_markdown_keeps_text_that_holds_code_fences_of_its_own():
    text = "Use this:\n```yaml\nsteps:\n  - name: build\n```\nand that:\n````\nnested ``` inside\n````\n"
    labeled = [{"name": "Example", "raw_text": text, "concept": None, "matched": False}]

    markdown = serialization._build_markdown(labeled, "1 page(s)", _CONCEPTS)

    # The text is inside the fence exactly as it is, and the fence is longer than any run of backticks inside it.
    assert f"`````\n{text.removesuffix(chr(10))}\n`````" in markdown


def test_build_markdown_keeps_leading_blank_lines_and_trailing_spaces():
    text = "\n\n  indented first line\nlast line with trailing spaces   \n"
    labeled = [{"name": "Spaced", "raw_text": text, "concept": None, "matched": False}]

    markdown = serialization._build_markdown(labeled, "1 page(s)", _CONCEPTS)

    assert f"```\n{text.removesuffix(chr(10))}\n```" in markdown


# --- serialization_agent() end-to-end -----------------------------------------


def test_serialization_agent_reads_docs_output_and_returns_labeled_markdown():
    docs_output = "Fetched 2 page(s) from https://example.com/docs, confidence 0.80.\n\n# Docs\n## Nightly build\nRuns on a cron schedule trigger."
    with patch.object(pim_agent_client, "concepts", return_value=_CONCEPTS):
        result = serialization_agent.serialization_agent({"docs_output": docs_output})

    assert "## Trigger" in result
    assert "Nightly build" in result
    assert "2 page(s)" in result


def test_a_long_page_without_headings_is_kept_whole():
    # The shape of a platform's JSON schema: tens of thousands of characters, no heading. An earlier version
    # asked a model for the relevant fragments and kept a few hundred characters of 60,000.
    schema = "{\n" + "".join(f'  "definition_{i}": {{"type": "object", "description": "step setting number {i}"}},\n' for i in range(700)) + "}\n"
    docs_output = f"Fetched 1 page(s) from https://example.com/schema.json, confidence 0.61.\n\n# https://example.com/schema.json\n{schema}"
    assert len(docs_output) > 40_000

    with patch.object(pim_agent_client, "concepts", return_value=_CONCEPTS):
        result = serialization_agent.serialization_agent({"docs_output": docs_output})

    assert len(result) >= len(docs_output)
    assert '"definition_0"' in result and '"definition_699"' in result
    assert all(line in result for line in _lines(docs_output))


def test_serialization_calls_no_model():
    # The one client every stage reaches a model through must never be called.
    docs_output = "# Docs\n## Triggers\nA cron schedule trigger.\n"
    with patch.object(pim_agent_client, "concepts", return_value=_CONCEPTS), \
         patch.object(ai_layer_client, "chat", side_effect=AssertionError("a model was called")) as chat:
        serialization_agent.serialization_agent({"docs_output": docs_output, "model": "gemini-flash"})

    chat.assert_not_called()


# --- PIM_CONCEPTS regression guard --------------------------------------------


def test_pim_concepts_has_exactly_nine_entries():
    assert len(PIM_CONCEPTS) == 9


def test_every_metamodel_entry_is_mapped_to_a_pim_concept():
    metamodel_titles = [
        entry.title for entry in reference_knowledge._KNOWLEDGE if entry.category == "metamodel"
    ]
    assert metamodel_titles  # sanity: there are metamodel entries to check

    for title in metamodel_titles:
        assert reference_knowledge.concept_for_entry_title(title) is not None, (
            f"metamodel entry {title!r} isn't mapped to any PIM_CONCEPTS key"
        )


# --- Issue #221 AC: Atlassian Bamboo (a platform not seen during development) ---
#
# Sectioning, labeling and markdown assembly are the real, unmocked code (ground() is delegated to the
# real implementation via the autouse real_ground fixture). The plain keyword-overlap scorer
# sometimes over-matches genuinely Bamboo-specific text to an unrelated PIM concept via generic word
# overlap, because "matched" means "any nonzero overlap" (see this module's documented Phase 0
# limitation), so this test does not assert which concept a particular section lands under. What
# IS this stage's real contract, and what it asserts: nothing is ever dropped.


def test_bamboo_excerpt_is_kept_whole_under_the_nine_concept_headers_and_unrecognized():
    docs_output = (_FIXTURES / "bamboo_sample.md").read_text()

    with patch.object(pim_agent_client, "concepts", return_value=_CONCEPTS):
        markdown = serialization_agent.serialization_agent({"docs_output": docs_output})

    missing = [line for line in _lines(docs_output) if line not in markdown]
    assert not missing, f"lines of the documentation vanished from the output: {missing[:3]}"
    for concept in PIM_CONCEPTS:
        assert f"## {concept}" in markdown
    assert "## Unrecognized" in markdown
