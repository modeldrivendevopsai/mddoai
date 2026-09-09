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


def test_files_root_accepts_a_list_and_tries_each_in_order(tmp_path):
    first_root = tmp_path / "first"
    second_root = tmp_path / "second"
    first_root.mkdir()
    second_root.mkdir()
    (second_root / "uploaded.ecore").write_text("<ecore/>", encoding="utf-8")

    assert resolve_file_attachment("uploaded.ecore", [first_root, second_root]) == "<ecore/>"


def test_files_root_list_rejects_when_no_root_has_the_file(tmp_path):
    first_root = tmp_path / "first"
    second_root = tmp_path / "second"
    first_root.mkdir()
    second_root.mkdir()

    with pytest.raises(AttachmentFileError):
        resolve_file_attachment("missing.ecore", [first_root, second_root])


def test_files_root_list_still_rejects_traversal_out_of_every_root(tmp_path):
    first_root = tmp_path / "first"
    second_root = tmp_path / "second"
    first_root.mkdir()
    second_root.mkdir()
    (tmp_path / "secret.txt").write_text("nope", encoding="utf-8")

    with pytest.raises(AttachmentFileError):
        resolve_file_attachment("../secret.txt", [first_root, second_root])


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
