"""psm_flow.run()'s routing logic: generate() for a platform with no real
.ecore yet, compare() for one that already has one. Both branches themselves
are mocked here (already covered by test_generation.py/test_comparison.py);
this only tests which one gets picked and how its result is reshaped.
"""
from unittest.mock import patch

import psm_flow


def test_routes_to_generation_for_unknown_platform():
    with patch("psm_flow.resolve_platform_metamodel", return_value=None) as mock_resolve, \
         patch("psm_flow.generate", return_value={
             "artifact": "<new-ecore/>", "prompt": {}, "validation": {"valid": True}, "rounds": 1,
         }) as mock_generate, \
         patch("psm_flow.compare") as mock_compare:
        result = psm_flow.run("TeamCity", "<pim/>", "docs")

    mock_resolve.assert_called_once_with("TeamCity")
    mock_generate.assert_called_once()
    mock_compare.assert_not_called()
    assert result["mode"] == "generation"
    assert result["artifact"] == "<new-ecore/>"


def test_forwards_stage_and_attempt_to_generate_on_the_generation_path():
    # Only the generation path ever validates (compare() never calls a
    # validator at all, see test_routes_to_knowledge_agent_for_known_platform
    # below), so stage/attempt only need to reach generate() here.
    with patch("psm_flow.resolve_platform_metamodel", return_value=None), \
         patch("psm_flow.generate", return_value={
             "artifact": "<new-ecore/>", "prompt": {}, "validation": {"valid": True}, "rounds": 1,
         }) as mock_generate:
        psm_flow.run("TeamCity", "<pim/>", "docs", run_id="run-123", stage="psm", attempt="attempt_1")

    assert mock_generate.call_args.kwargs.get("stage") == "psm"
    assert mock_generate.call_args.kwargs.get("attempt") == "attempt_1"


def test_forwards_mock_to_generate_on_the_generation_path():
    with patch("psm_flow.resolve_platform_metamodel", return_value=None), \
         patch("psm_flow.generate", return_value={
             "artifact": "<new-ecore/>", "prompt": {}, "validation": {"valid": True}, "rounds": 1,
         }) as mock_generate:
        psm_flow.run("TeamCity", "<pim/>", "docs", mock=True)

    assert mock_generate.call_args.kwargs.get("mock") is True


def test_routes_to_knowledge_agent_for_known_platform(tmp_path):
    existing = tmp_path / "gitlabMM.ecore"
    existing.write_text("<real-existing-ecore/>")

    with patch("psm_flow.resolve_platform_metamodel", return_value=str(existing)), \
         patch("psm_flow.generate") as mock_generate, \
         patch("psm_flow.compare", return_value=[]) as mock_compare:
        result = psm_flow.run("A GitLab CI platform", "<pim/>", "docs", model="gemini-flash")

    mock_generate.assert_not_called()
    mock_compare.assert_called_once_with("docs", str(existing), model="gemini-flash")
    assert result["mode"] == "knowledge"
    assert result["artifact"] == "<real-existing-ecore/>"
    assert result["gaps"] == []


def test_knowledge_path_resolves_a_file_attachment_the_human_uploaded(
    tmp_path, isolated_prompt_config_dir, isolated_attachment_uploads_dir
):
    # psm_flow.run()'s own knowledge-mode branch calls
    # prompt_resolution.render_prompt() directly (for the "prompt" field in
    # its own response), a real call site distinct from compare()'s own -
    # it must resolve a "file" attachment against comparison.files_root()
    # too, not just META_MODELS_DIR.
    import json

    existing = tmp_path / "gitlabMM.ecore"
    existing.write_text("<real-existing-ecore/>")
    (isolated_attachment_uploads_dir / "custom-guidance.md").write_text(
        "Flag anything using a deprecated GitLab CI keyword.", encoding="utf-8"
    )
    config = {
        "attachments": [
            {"id": "system", "name": "System prompt", "type": "text", "content": "You are the psm knowledge agent."},
            {"id": "custom", "name": "Custom guidance", "type": "file", "path": "custom-guidance.md"},
        ]
    }
    directory = isolated_prompt_config_dir / "comparison"
    directory.mkdir(parents=True)
    (directory / "default.default.json").write_text(json.dumps(config), encoding="utf-8")

    with patch("psm_flow.resolve_platform_metamodel", return_value=str(existing)), \
         patch("psm_flow.compare", return_value=[]):
        result = psm_flow.run("A GitLab CI platform", "<pim/>", "docs")

    assert result["prompt"]["custom"] == "Flag anything using a deprecated GitLab CI keyword."


def test_knowledge_agent_surfaces_gaps_as_informational_only(tmp_path):
    from comparison import Suggestion

    existing = tmp_path / "gitlabMM.ecore"
    existing.write_text("<real-existing-ecore/>")
    suggestion = Suggestion(kind="missing", target="RetryPolicy", description="no such property", source_excerpt=None)

    with patch("psm_flow.resolve_platform_metamodel", return_value=str(existing)), \
         patch("psm_flow.compare", return_value=[suggestion]):
        result = psm_flow.run("A GitLab CI platform", "<pim/>", "docs")

    # Existing artifact is returned unchanged - a gap is informational, never an
    # automatic edit to the real metamodel.
    assert result["artifact"] == "<real-existing-ecore/>"
    assert result["gaps"] == [{
        "kind": "missing", "target": "RetryPolicy", "description": "no such property", "source_excerpt": None,
    }]
