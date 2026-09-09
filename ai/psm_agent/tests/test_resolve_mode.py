"""main.py's GET /resolve-mode: psm_flow.run()'s own real routing decision
(resolve_platform_metamodel), exposed read-only. Called directly as a plain
function, matching this service's own route-test convention - no TestClient
needed, it's a plain sync function with no multipart/exception-handler
concern.
"""
from main import resolve_mode_endpoint


def test_resolves_generation_mode_for_a_brand_new_platform():
    result = resolve_mode_endpoint("A brand new CI/CD platform nobody has a metamodel for")

    assert result == {"mode": "generation", "metamodel_path": None}


def test_resolves_knowledge_mode_for_a_platform_with_a_real_metamodel():
    result = resolve_mode_endpoint("GitLab CI")

    assert result["mode"] == "knowledge"
    assert result["metamodel_path"] is not None
    assert result["metamodel_path"].endswith("gitlabMM.ecore")
