from generation_toolkit.prompt_builder import build_prompt


def test_parts_pass_through_unchanged():
    prompt = build_prompt({"a": "content a", "b": "content b"})

    assert prompt["a"] == "content a"
    assert prompt["b"] == "content b"


def test_part_order_is_preserved():
    prompt = build_prompt({"z": "1", "a": "2", "m": "3"})

    assert list(prompt.keys()) == ["z", "a", "m", "constraints"]


def test_constraints_render_as_bullet_list():
    prompt = build_prompt({"a": "x"}, constraints=["Fix: missing RetryPolicy", "Fix: rename Job.stage"])

    assert prompt["constraints"] == "- Fix: missing RetryPolicy\n- Fix: rename Job.stage"


def test_no_constraints_is_empty_string():
    assert build_prompt({"a": "x"})["constraints"] == ""


def test_empty_constraints_list_is_empty_string():
    assert build_prompt({"a": "x"}, constraints=[])["constraints"] == ""
