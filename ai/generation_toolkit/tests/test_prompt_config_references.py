from generation_toolkit.prompt_config.references import check_references
from generation_toolkit.prompt_config.storage import save_config

_CONFIG = {
    "system_prompt": "x",
    "attachments": [
        {"id": "example", "name": "Example", "type": "file", "path": "example.ecore"},
        {"id": "pim", "name": "PIM", "type": "context", "key": "pim_artifact"},
    ],
}


def _seed(tmp_path):
    (tmp_path / "example.ecore").write_text("<ecore/>", encoding="utf-8")
    save_config(tmp_path, "generation", "default", _CONFIG, {"pim_artifact": "x"}, tmp_path)


def test_check_references_reports_nothing_broken_for_a_healthy_config(tmp_path):
    _seed(tmp_path)

    assert check_references(tmp_path, "generation", "default", {"pim_artifact": "x"}, tmp_path) == []


def test_check_references_reports_a_deleted_file_attachment(tmp_path):
    _seed(tmp_path)
    (tmp_path / "example.ecore").unlink()

    broken = check_references(tmp_path, "generation", "default", {"pim_artifact": "x"}, tmp_path)

    assert [b["id"] for b in broken] == ["example"]


def test_check_references_reports_a_context_key_that_stopped_existing(tmp_path):
    _seed(tmp_path)

    broken = check_references(tmp_path, "generation", "default", {}, tmp_path)

    assert [b["id"] for b in broken] == ["pim"]
