import pytest

from generation_toolkit.attachments.resolve import UnknownContextKeyError, resolve_attachments


def test_text_attachment_resolves_to_its_own_content():
    attachments = [{"id": "a", "name": "A", "type": "text", "content": "hello"}]

    assert resolve_attachments(attachments, {}, "/unused") == {"a": "hello"}


def test_context_attachment_resolves_from_context_values():
    attachments = [{"id": "pim_ecore", "name": "PIM", "type": "context", "key": "pim_artifact"}]

    result = resolve_attachments(attachments, {"pim_artifact": "<pim/>"}, "/unused")

    assert result == {"pim_ecore": "<pim/>"}


def test_context_attachment_raises_on_unknown_key():
    attachments = [{"id": "x", "name": "X", "type": "context", "key": "nope"}]

    with pytest.raises(UnknownContextKeyError):
        resolve_attachments(attachments, {"pim_artifact": "<pim/>"}, "/unused")


def test_file_attachment_reads_a_real_file(tmp_path):
    (tmp_path / "example.ecore").write_text("<ecore/>", encoding="utf-8")
    attachments = [{"id": "psm_example", "name": "Example", "type": "file", "path": "example.ecore"}]

    result = resolve_attachments(attachments, {}, tmp_path)

    assert result == {"psm_example": "<ecore/>"}


def test_order_is_preserved():
    attachments = [
        {"id": "b", "name": "B", "type": "text", "content": "2"},
        {"id": "a", "name": "A", "type": "text", "content": "1"},
    ]

    assert list(resolve_attachments(attachments, {}, "/unused").keys()) == ["b", "a"]


def test_unknown_type_raises():
    attachments = [{"id": "x", "name": "X", "type": "bogus"}]

    with pytest.raises(ValueError):
        resolve_attachments(attachments, {}, "/unused")


def test_empty_attachment_list_resolves_to_empty_parts():
    assert resolve_attachments([], {"pim_artifact": "x"}, "/unused") == {}
