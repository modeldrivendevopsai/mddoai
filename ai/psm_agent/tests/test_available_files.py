"""available_files.py unit tests: real filesystem scan against
META_MODELS_DIR (the real, checked-in meta_models/ tree), no mocking.
"""
from unittest.mock import patch

import available_files


def test_finds_real_metamodel_files():
    files = available_files.list_available_files()

    assert any(path.endswith("githubMM.ecore") for path in files)


def test_paths_are_relative_to_meta_models_dir_with_forward_slashes():
    files = available_files.list_available_files()

    for path in files:
        assert "\\" not in path
        assert not path.startswith("/")


def test_sorted_for_a_deterministic_listing():
    files = available_files.list_available_files()

    assert files == sorted(files)


def test_returns_empty_list_when_meta_models_dir_does_not_exist(tmp_path):
    with patch.object(available_files, "META_MODELS_DIR", str(tmp_path / "does-not-exist")):
        assert available_files.list_available_files() == []


def test_includes_real_uploaded_files(tmp_path, isolated_attachment_uploads_dir):
    (isolated_attachment_uploads_dir / "abc123-model.ecore").write_text("<ecore/>", encoding="utf-8")
    with patch.object(available_files, "META_MODELS_DIR", str(tmp_path / "does-not-exist")):
        assert available_files.list_available_files() == ["abc123-model.ecore"]


def test_returns_empty_list_when_neither_root_exists(tmp_path, isolated_attachment_uploads_dir):
    with patch.object(available_files, "META_MODELS_DIR", str(tmp_path / "no-metamodels")):
        assert available_files.list_available_files() == []
