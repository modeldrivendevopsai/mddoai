import pytest

from generation_toolkit.attachments.files import resolve_file_attachment
from generation_toolkit.attachments.uploads import UploadError, save_uploaded_file

_MAX_BYTES = 1024


def test_saves_a_real_file_and_returns_its_relative_path(tmp_path):
    stored_path = save_uploaded_file(tmp_path, "model.ecore", b"<ecore/>", _MAX_BYTES)

    assert (tmp_path / stored_path).read_bytes() == b"<ecore/>"


def test_saved_file_is_readable_through_resolve_file_attachment(tmp_path):
    stored_path = save_uploaded_file(tmp_path, "model.ecore", b"<ecore/>", _MAX_BYTES)

    assert resolve_file_attachment(stored_path, tmp_path) == "<ecore/>"


def test_stored_name_keeps_the_real_suffix(tmp_path):
    stored_path = save_uploaded_file(tmp_path, "model.ecore", b"x", _MAX_BYTES)

    assert stored_path.endswith(".ecore")


def test_two_uploads_with_the_same_filename_never_collide(tmp_path):
    first = save_uploaded_file(tmp_path, "model.ecore", b"first", _MAX_BYTES)
    second = save_uploaded_file(tmp_path, "model.ecore", b"second", _MAX_BYTES)

    assert first != second
    assert (tmp_path / first).read_bytes() == b"first"
    assert (tmp_path / second).read_bytes() == b"second"


def test_rejects_content_over_the_size_limit(tmp_path):
    with pytest.raises(UploadError):
        save_uploaded_file(tmp_path, "model.ecore", b"x" * (_MAX_BYTES + 1), _MAX_BYTES)


def test_strips_a_path_traversal_filename_down_to_its_real_basename(tmp_path):
    stored_path = save_uploaded_file(tmp_path, "../../etc/passwd", b"x", _MAX_BYTES)

    # The stored name is a real, safe basename under the root - the
    # attempted traversal in the client-supplied name never reaches the
    # filesystem at all, not even sanitized-in-place.
    assert "/" not in stored_path
    assert ".." not in stored_path
    assert (tmp_path / stored_path).is_file()


def test_sanitizes_shell_metacharacters_in_the_filename(tmp_path):
    stored_path = save_uploaded_file(tmp_path, "model.ecore; rm -rf /", b"x", _MAX_BYTES)

    assert (tmp_path / stored_path).is_file()
    assert ";" not in stored_path
    assert " " not in stored_path


def test_rejects_an_empty_filename(tmp_path):
    with pytest.raises(UploadError):
        save_uploaded_file(tmp_path, "", b"x", _MAX_BYTES)


def test_creates_the_uploads_root_if_it_does_not_exist_yet(tmp_path):
    fresh_root = tmp_path / "not-yet-created"

    stored_path = save_uploaded_file(fresh_root, "model.ecore", b"x", _MAX_BYTES)

    assert (fresh_root / stored_path).is_file()
