"""serialization_agent.py unit tests: the serialization stage that sits
between docs and pim, turning docs_agent's raw markdown into a structured,
PIM-concept-labeled artifact.

Matches this suite's existing conventions: orchestrator.chat is mocked for
the one real LLM call (extraction), exactly like test_stage_agents.py mocks
it for every other stage agent; labeling (_label_fragment) calls the real,
unmocked ground()/concept_for_entry_title(), exactly like test_pim_agent.py
exercises ground() for real, since that's a pure/deterministic keyword
lookup, not something worth mocking.

Tests verify:
  1. _extract_fragments parses a well-formed JSON array response (including
     one wrapped in a ```json code fence, a real model's actual behavior
     even when told not to - see _strip_code_fence's own docstring), and
     degrades gracefully (one unparsed fragment, not a crash) on genuinely
     malformed JSON or JSON that isn't a list.
  2. _label_fragment matches real CI/CD vocabulary to the right PIM concept
     via the real ground(), and leaves genuinely unrelated text unmatched.
  3. _build_markdown always emits all 9 PIM_CONCEPTS headers plus a final
     Unrecognized section, even when empty, and places each fragment under
     its matched concept's header (or Unrecognized when unmatched).
  4. serialization_agent() end-to-end (chat mocked): reads context["docs_output"],
     forwards the chosen model, and returns markdown with the expected headers.
  5. Regression guard: PIM_CONCEPTS has exactly 9 keys, and every metamodel-
     category entry in reference_knowledge._KNOWLEDGE is mapped to one of them,
     so a future new metamodel entry can't silently go unlabeled.
  6. Issue #221's AC: a real Atlassian Bamboo documentation excerpt (a
     platform not seen during development, see tests/fixtures/bamboo_sample.md)
     runs through the real, unmocked extraction-output-shaped fragments and
     real ground()/_label_fragment/_build_markdown pipeline (only chat() -
     the extraction call - is mocked, with a fragment list plausible for a
     real LLM to have extracted from that excerpt). Confirms nothing is
     dropped (every extracted fragment survives into the output somewhere),
     and that fragments with a real, unambiguous PIM equivalent (a
     cron-scheduled trigger; script/plugin/checkout/artifact-shaped task
     steps) land under the correct concept header.
"""
import json
from pathlib import Path
from unittest.mock import patch

import orchestrator
import serialization_agent
from pim_agent import PIM_CONCEPTS, reference_knowledge
from helpers import ok_response

_FIXTURES = Path(__file__).parent / "fixtures"


# --- _extract_fragments -----------------------------------------------------


def test_extract_fragments_parses_valid_json_response():
    fragments_json = (
        '[{"type": "trigger", "name": "Nightly build", "raw_text": "Runs on a cron schedule"}]'
    )
    with patch.object(orchestrator, "chat", return_value=ok_response(fragments_json)):
        result = serialization_agent._extract_fragments("some raw docs text", model=None)

    assert result == [{"type": "trigger", "name": "Nightly build", "raw_text": "Runs on a cron schedule"}]


def test_extract_fragments_parses_json_wrapped_in_a_markdown_code_fence():
    # Regression test: a real model, asked for "only JSON, no markdown," was
    # observed wrapping its answer in a ```json ... ``` fence anyway. Without
    # unwrapping it first, json.loads() sees the fence markers as invalid
    # syntax and the whole response falls into the unparsed-fragment
    # fallback below, silently discarding a perfectly good extraction.
    fenced = (
        '```json\n'
        '[{"type": "trigger", "name": "Nightly build", "raw_text": "Runs on a cron schedule"}]\n'
        '```'
    )
    with patch.object(orchestrator, "chat", return_value=ok_response(fenced)):
        result = serialization_agent._extract_fragments("some raw docs text", model=None)

    assert result == [{"type": "trigger", "name": "Nightly build", "raw_text": "Runs on a cron schedule"}]


def test_extract_fragments_falls_back_to_one_fragment_on_malformed_json():
    with patch.object(orchestrator, "chat", return_value=ok_response("not valid json at all")):
        result = serialization_agent._extract_fragments("some raw docs text", model=None)

    assert len(result) == 1
    assert result[0]["raw_text"] == "some raw docs text"


def test_extract_fragments_falls_back_when_json_is_not_a_list():
    with patch.object(orchestrator, "chat", return_value=ok_response('{"not": "a list"}')):
        result = serialization_agent._extract_fragments("some raw docs text", model=None)

    assert len(result) == 1
    assert result[0]["raw_text"] == "some raw docs text"


# --- _label_fragment ---------------------------------------------------------


def test_label_fragment_matches_real_ci_cd_vocabulary():
    fragment = {
        "type": "trigger",
        "name": "Scheduled build",
        "raw_text": "Runs on a cron expression schedule trigger",
    }

    labeled = serialization_agent._label_fragment(fragment)

    assert labeled["matched"] is True
    assert labeled["concept"] == "Trigger"


def test_label_fragment_leaves_unrelated_text_unmatched():
    fragment = {"type": "other", "name": "", "raw_text": "What's the weather like in Paris tomorrow"}

    labeled = serialization_agent._label_fragment(fragment)

    assert labeled["matched"] is False
    assert labeled["concept"] is None


def test_label_fragment_finds_a_metamodel_match_ranked_past_grounds_default_top_k():
    # Regression test: ground()'s default top_k=5 truncates across ALL categories
    # before _label_fragment ever gets to filter for "metamodel" - a fragment worded
    # close to the 5 process-category "Step N: ..." entries fills every default
    # top_k=5 slot with process hits, pushing the real "Step types" (metamodel) match
    # to rank 8. Without requesting a large top_k in _label_fragment, this fragment
    # would incorrectly come back unmatched despite a clear, real concept for it.
    fragment = {
        "type": "step",
        "name": "Build step",
        "raw_text": (
            "Command step build step init environment output script "
            "input platform-specific model"
        ),
    }

    labeled = serialization_agent._label_fragment(fragment)

    assert labeled["matched"] is True
    assert labeled["concept"] == "Steps"


# --- _build_markdown -----------------------------------------------------------


def test_build_markdown_always_includes_all_nine_concept_headers_and_unrecognized():
    markdown = serialization_agent._build_markdown([], "3 page(s)")

    for concept in PIM_CONCEPTS:
        assert f"## {concept}" in markdown
    assert "## Unrecognized" in markdown


def test_build_markdown_places_matched_fragment_under_its_concept_header():
    labeled = [{
        "type": "trigger", "name": "Nightly build", "raw_text": "cron schedule",
        "concept": "Trigger", "matched": True,
    }]

    markdown = serialization_agent._build_markdown(labeled, "1 page(s)")

    trigger_section = markdown.split("## Trigger")[1].split("## ")[0]
    assert "Nightly build" in trigger_section
    assert "cron schedule" in trigger_section


def test_build_markdown_places_unmatched_fragment_under_unrecognized():
    labeled = [{
        "type": "other", "name": "Mystery thing", "raw_text": "platform-specific oddity",
        "concept": None, "matched": False,
    }]

    markdown = serialization_agent._build_markdown(labeled, "1 page(s)")

    unrecognized_section = markdown.split("## Unrecognized")[1]
    assert "Mystery thing" in unrecognized_section
    assert "platform-specific oddity" in unrecognized_section


# --- serialization_agent() end-to-end -----------------------------------------


def test_serialization_agent_reads_docs_output_and_returns_labeled_markdown():
    docs_output = "Fetched 2 page(s) from https://example.com/docs, confidence 0.80.\n\n# Docs\nRuns on a cron schedule."
    fragments_json = (
        '[{"type": "trigger", "name": "Nightly build", "raw_text": "Runs on a cron schedule"}]'
    )
    with patch.object(orchestrator, "chat", return_value=ok_response(fragments_json)):
        result = serialization_agent.serialization_agent({"docs_output": docs_output})

    assert "## Trigger" in result
    assert "Nightly build" in result
    assert "2 page(s)" in result


def test_serialization_agent_forwards_the_chosen_model():
    with patch.object(orchestrator, "chat", return_value=ok_response("[]")) as mock_chat:
        serialization_agent.serialization_agent({"docs_output": "some docs", "model": "gemini-flash"})

    assert mock_chat.call_args.kwargs["model"] == "gemini-flash"


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
# The extraction call (orchestrator.chat) is mocked, same as every other test
# in this file, with a fragment list plausible for a real LLM to have pulled
# out of tests/fixtures/bamboo_sample.md. Labeling and markdown assembly are
# the real, unmocked code. Real, measured behavior against this fixture (see
# the PR that added this test for the manual ground() trace) shows the plain
# keyword-overlap scorer sometimes over-matches genuinely Bamboo-specific text
# (deployment environments, branch config, notifications) to an unrelated PIM
# concept via generic word overlap ("job", "environment", "plan"), rather than
# leaving it unmatched, because "matched" here means "any nonzero overlap,"
# per serialization_agent.py's own documented Phase 0 limitation (no score
# threshold). This test doesn't assert a specific label for that ambiguous
# text, asserting either "matched" or "unmatched" there would be asserting
# scorer internals, not this stage's real contract. What IS this stage's real
# contract, and what this test asserts: nothing is ever silently dropped, and
# fragments with a genuinely strong, unambiguous PIM vocabulary match land
# under the right header.
_BAMBOO_FRAGMENTS = [
    {
        "type": "pipeline",
        "name": "Build the rockets",
        "raw_text": (
            "A plan defines everything about your continuous integration build "
            "process in Bamboo. Every plan belongs to a project and has a single "
            "stage by default, but can be used to group jobs into multiple stages."
        ),
    },
    {
        "type": "job",
        "name": "Build",
        "raw_text": (
            "A job is a single build unit within a plan. One or more jobs can be "
            "organized into one or more stages. A job checks out the repository "
            "and processes a series of one or more tasks that are run "
            "sequentially on the same agent."
        ),
    },
    {
        "type": "task",
        "name": "Build tasks",
        "raw_text": (
            "tasks: script command mkdir echo sleep, test-parser plugin junit "
            "test-results checkout artifact"
        ),
    },
    {
        "type": "trigger",
        "name": "Default Job triggers",
        "raw_text": (
            "A plan can be triggered by a repository polling schedule, a "
            "scheduled cron-style trigger, or manually by a user through the "
            "Bamboo UI. triggers: polling: period 180"
        ),
    },
    {
        "type": "requirement",
        "name": "Job requirements",
        "raw_text": (
            "Job requirements specify capabilities a Bamboo agent must have in "
            "order to run a given job's tasks, for example a specific operating "
            "system or an installed tool such as Maven or a JDK version."
        ),
    },
    {
        "type": "variable",
        "name": "Plan variables",
        "raw_text": (
            "Provides for the definition of plan variables. Plan variables can "
            "be referenced inside task scripts using the bamboo variableName "
            "syntax, and can be overridden per-branch or per-execution."
        ),
    },
    {
        "type": "deployment",
        "name": "Deployment projects and environments",
        "raw_text": (
            "A deployment project takes a completed plan result and deploys it "
            "to one or more environments, such as QA or Production, in a "
            "defined release sequence."
        ),
    },
    {
        "type": "branch-config",
        "name": "Branch configuration",
        "raw_text": (
            "Bamboo can automatically create a plan branch when a matching VCS "
            "branch is pushed. Branch behaviour, such as whether to merge from "
            "a parent branch or push changes back on a successful build, is "
            "configured under branch-config."
        ),
    },
    {
        "type": "notification",
        "name": "Notifications and permissions",
        "raw_text": (
            "Specifies notifications of build results, sent to watchers, "
            "committers, or a configured chat integration on build completion "
            "or failure. Also specifies who has permission to view and "
            "configure the plan and its jobs."
        ),
    },
]


def test_bamboo_excerpt_extraction_and_labeling_drops_nothing_and_labels_known_concepts_correctly():
    docs_output = (_FIXTURES / "bamboo_sample.md").read_text()

    with patch.object(orchestrator, "chat", return_value=ok_response(json.dumps(_BAMBOO_FRAGMENTS))):
        markdown = serialization_agent.serialization_agent({"docs_output": docs_output})

    # Nothing dropped: every extracted fragment's name shows up somewhere in
    # the output, whether under a real concept header or Unrecognized.
    for fragment in _BAMBOO_FRAGMENTS:
        assert fragment["name"] in markdown, f"fragment {fragment['name']!r} vanished from the output"

    # A cron-scheduled trigger has a real, unambiguous PIM equivalent (Trigger
    # types: ScheduledTrigger carries a cron expression) - correctly labeled.
    trigger_section = markdown.split("## Trigger")[1].split("## ")[0]
    assert "Default Job triggers" in trigger_section

    # script/plugin/checkout/artifact task steps mirror the Step types
    # entry's own subclasses almost one-to-one - correctly labeled.
    steps_section = markdown.split("## Steps")[1].split("## ")[0]
    assert "Build tasks" in steps_section

    # All 9 concept headers plus Unrecognized are always present, even on a
    # platform never seen during development.
    for concept in PIM_CONCEPTS:
        assert f"## {concept}" in markdown
    assert "## Unrecognized" in markdown
