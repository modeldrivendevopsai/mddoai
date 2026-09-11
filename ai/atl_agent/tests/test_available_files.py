"""available_files.py unit tests: real filesystem scan against
REFERENCE_EXAMPLE_PATH (the real, checked-in pim2gitlabmodel.atl), no
mocking for the "does the real file exist" cases.
"""
from unittest.mock import patch

import available_files


def test_finds_the_real_master_example():
    files = available_files.list_available_files()

    assert "pim2gitlabmodel.atl" in files


def test_sorted_for_a_deterministic_listing():
    files = available_files.list_available_files()

    assert files == sorted(files)


def test_returns_empty_list_when_reference_does_not_exist(tmp_path):
    with patch("prompt_paths.REFERENCE_EXAMPLE_PATH", tmp_path / "does-not-exist.atl"):
        assert available_files.list_available_files() == []


def test_includes_real_uploaded_files(tmp_path, isolated_attachment_uploads_dir):
    (isolated_attachment_uploads_dir / "abc123-example.atl").write_text("module m;", encoding="utf-8")
    with patch("prompt_paths.REFERENCE_EXAMPLE_PATH", tmp_path / "does-not-exist.atl"):
        assert available_files.list_available_files() == ["abc123-example.atl"]


def test_returns_empty_list_when_neither_root_exists(tmp_path, isolated_attachment_uploads_dir):
    with patch("prompt_paths.REFERENCE_EXAMPLE_PATH", tmp_path / "does-not-exist.atl"):
        assert available_files.list_available_files() == []
