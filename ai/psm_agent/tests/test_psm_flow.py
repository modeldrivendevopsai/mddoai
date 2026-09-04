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
