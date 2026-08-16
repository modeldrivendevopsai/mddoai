"""
pipeline_tools.py unit tests: MDDOAI's system prompt. The declared tools
this prompt refers to live in tools/ (see test_tools.py); add_page_to_docs's
own real behavior is tested in integration_runner's own test suite,
alongside the real logic backing it.
"""
import pipeline_tools


def test_system_prompt_lists_every_stage_in_order_with_its_description():
    prompt = pipeline_tools.get_system_prompt_template()

    assert "through 7 fixed stages" in prompt
    assert "1. docs: fetches the platform's real documentation" in prompt
    assert "7. generation: a final summary tying all prior stages together." in prompt


def test_system_prompt_has_a_current_stage_placeholder_to_fill_in():
    prompt = pipeline_tools.get_system_prompt_template()

    assert "{current_stage}" in prompt
    formatted = prompt.format(current_stage="atl")
    assert "is: atl" in formatted
