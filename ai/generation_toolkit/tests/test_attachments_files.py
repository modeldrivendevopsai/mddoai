import pytest

from generation_toolkit.attachments.files import (
    AttachmentFileError,
    PathSegmentError,
    resolve_file_attachment,
    validate_path_segment,
)


def test_reads_a_real_file_under_the_root(tmp_path):
    (tmp_path / "docs.md").write_text("real content", encoding="utf-8")

    assert resolve_file_attachment("docs.md", tmp_path) == "real content"


def test_reads_a_real_file_in_a_subdirectory(tmp_path):
    sub = tmp_path / "model"
    sub.mkdir()
    (sub / "githubMM.ecore").write_text("<ecore/>", encoding="utf-8")

    assert resolve_file_attachment("model/githubMM.ecore", tmp_path) == "<ecore/>"


def test_rejects_parent_directory_traversal(tmp_path):
    (tmp_path.parent / "secret.txt").write_text("nope", encoding="utf-8")

    with pytest.raises(AttachmentFileError):
        resolve_file_attachment("../secret.txt", tmp_path)


def test_rejects_a_bare_dot_segment(tmp_path):
    (tmp_path / "docs.md").write_text("real content", encoding="utf-8")

    with pytest.raises(AttachmentFileError):
        resolve_file_attachment("./docs.md", tmp_path)


def test_rejects_an_absolute_path(tmp_path):
    with pytest.raises(AttachmentFileError):
        resolve_file_attachment("/etc/passwd", tmp_path)


def test_rejects_disallowed_characters(tmp_path):
    with pytest.raises(AttachmentFileError):
        resolve_file_attachment("docs.md; rm -rf /", tmp_path)


def test_rejects_a_path_that_does_not_exist(tmp_path):
    with pytest.raises(AttachmentFileError):
        resolve_file_attachment("missing.md", tmp_path)


def test_rejects_a_directory_instead_of_a_file(tmp_path):
    (tmp_path / "adir").mkdir()

    with pytest.raises(AttachmentFileError):
        resolve_file_attachment("adir", tmp_path)


def test_validate_path_segment_accepts_a_real_run_id():
    assert validate_path_segment("run-123.abc_DEF") == "run-123.abc_DEF"


def test_validate_path_segment_rejects_a_path_separator():
    with pytest.raises(PathSegmentError):
        validate_path_segment("psm/attempt_1")


def test_validate_path_segment_rejects_disallowed_characters():
    with pytest.raises(PathSegmentError):
        validate_path_segment("run 1; rm -rf /")


def test_validate_path_segment_rejects_a_bare_dot_segment():
    with pytest.raises(PathSegmentError):
        validate_path_segment("..")
