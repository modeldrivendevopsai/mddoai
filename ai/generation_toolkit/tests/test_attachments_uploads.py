import pytest

from generation_toolkit.attachments.files import resolve_file_attachment
from generation_toolkit.attachments.uploads import UploadedFiles, UploadError, save_uploaded_file
from helpers import AGENT

_MAX_BYTES = 1024


def test_saves_a_real_file_and_returns_its_stored_name():
    stored = save_uploaded_file(AGENT, "model.ecore", b"<ecore/>", _MAX_BYTES)

    assert UploadedFiles(AGENT).read(stored) == "<ecore/>"


def test_saved_file_is_readable_through_resolve_file_attachment(tmp_path):
    stored = save_uploaded_file(AGENT, "model.ecore", b"<ecore/>", _MAX_BYTES)

    assert resolve_file_attachment(stored, [tmp_path, UploadedFiles(AGENT)]) == "<ecore/>"


def test_stored_name_keeps_the_real_suffix():
    stored = save_uploaded_file(AGENT, "model.ecore", b"x", _MAX_BYTES)

    assert stored.endswith(".ecore")


def test_two_uploads_with_the_same_filename_never_collide():
    first = save_uploaded_file(AGENT, "model.ecore", b"first", _MAX_BYTES)
    second = save_uploaded_file(AGENT, "model.ecore", b"second", _MAX_BYTES)

    assert first != second
    assert UploadedFiles(AGENT).read(first) == "first"
    assert UploadedFiles(AGENT).read(second) == "second"


def test_rejects_content_over_the_size_limit():
    with pytest.raises(UploadError):
        save_uploaded_file(AGENT, "model.ecore", b"x" * (_MAX_BYTES + 1), _MAX_BYTES)


def test_rejects_content_that_is_not_utf8_text():
    with pytest.raises(UploadError):
        save_uploaded_file(AGENT, "model.bin", b"\xff\xfe\x00", _MAX_BYTES)


def test_strips_a_path_traversal_filename_down_to_its_real_basename():
    stored = save_uploaded_file(AGENT, "../../etc/passwd", b"x", _MAX_BYTES)

    # The stored name is a real, safe basename - the attempted traversal in the
    # client-supplied name never reaches the journal at all, not even sanitized-in-place.
    assert "/" not in stored
    assert ".." not in stored
    assert stored in UploadedFiles(AGENT).names()


def test_sanitizes_shell_metacharacters_in_the_filename():
    stored = save_uploaded_file(AGENT, "model.ecore; rm -rf /", b"x", _MAX_BYTES)

    assert stored in UploadedFiles(AGENT).names()
    assert ";" not in stored
    assert " " not in stored


def test_rejects_an_empty_filename():
    with pytest.raises(UploadError):
        save_uploaded_file(AGENT, "", b"x", _MAX_BYTES)


def test_uploads_are_kept_per_agent():
    stored = save_uploaded_file(AGENT, "model.ecore", b"x", _MAX_BYTES)

    assert UploadedFiles("another-agent").names() == []
    assert UploadedFiles("another-agent").read(stored) is None
