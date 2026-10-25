"""Real end-to-end test: no mocking, spawns real java subprocesses against
the real Atl/AcceleoExecutorCli, running the project's own real,
hand-authored pim2gitlabmodel.atl and generate.mtl against the real, rich
sample PIM instance (11 jobs exercising all of MDDOAI's own PIM concepts).
This is a full-repo-checkout test, not something the deployed
execution-agent container can ever run (it only ever gets its own service
code plus main/'s compiled build output, never the wider monorepo's real
.atl/.mtl/.ecore source tree - see ai/docker-compose.yml's own
execution-agent volumes). Auto-skips when a Java runtime, the built main/
distribution, or that wider checkout aren't available, so the fast suite
(plain `pytest`) always runs standalone on any machine, and the real
in-container suite skips cleanly instead of crashing on a missing tree.
"""
import shutil
from pathlib import Path

import pytest
from fastapi.testclient import TestClient

import execution_runner
import main

# Four chained .parent calls, not .parents[3]: the real repo checkout this
# test needs (see the module docstring) is never present inside the
# deployed execution-agent container, where /app has no four real parent
# directories to index into - .parents[3] raised IndexError there,
# crashing this whole test file's collection instead of letting the
# skipif below skip it cleanly. Chained .parent never raises: short of a
# real checkout, it just clamps at the filesystem root, and the file
# existence check below skips accordingly.
REPO_ROOT = Path(__file__).resolve().parent.parent.parent.parent
# A real PIM model instance, main/'s own Java test fixture, also read
# directly here (and, via a Docker bind mount to this same real file) by
# the AI pipeline's own generation stage at runtime - a temporary stand-in
# until a real SWArch-driven PIM extraction replaces it there.
PIM_SAMPLE_INSTANCE_PATH = (
    REPO_ROOT / "main" / "src" / "test" / "resources" / "testCases" / "execution" / "gitlab" / "input.pimmm"
)
ATL_SOURCE_PATH = REPO_ROOT / "main" / "src" / "main" / "resources" / "transformations" / "pim2psm" / "pim2gitlabmodel.atl"
MTL_SOURCE_PATH = (
    REPO_ROOT / "code_generation" / "com.mddoai.codegeneration.gitlab.acceleo" / "src"
    / "com" / "mddoai" / "codegeneration" / "gitlab" / "acceleo" / "main" / "generate.mtl"
)
GITLAB_ECORE_PATH = REPO_ROOT / "meta_models" / "com.mddoai.metamodel.gitlab" / "model" / "gitlabMM.ecore"

# Checks the exact same LIB_DIR the real code path resolves (see
# execution_runner.py), not a separately-hardcoded copy of its default -
# matching validator_agent's own test_integration_real_jvm.py reasoning for
# why that matters. Also checks PIM_SAMPLE_INSTANCE_PATH itself exists, not
# just java/LIB_DIR: the deployed container has both of those (it ships its
# own Java runtime and mounts main/'s compiled output) but never the wider repo
# checkout this test's real source files live in, so java+LIB_DIR alone
# would wrongly conclude "run for real" there.
pytestmark = pytest.mark.skipif(
    not (shutil.which("java") and Path(execution_runner.LIB_DIR).exists() and PIM_SAMPLE_INSTANCE_PATH.exists()),
    reason="Java runtime + built main/ distribution + full repo checkout not available",
)

client = TestClient(main.app)


def test_real_atl_execution_produces_a_real_gitlab_pipeline_from_the_real_sample_pim():
    response = client.post("/execute/atl", json={
        "atl_source": ATL_SOURCE_PATH.read_text(encoding="utf-8"),
        "pim_model_xmi": PIM_SAMPLE_INSTANCE_PATH.read_text(encoding="utf-8"),
        "target_ecore": GITLAB_ECORE_PATH.read_text(encoding="utf-8"),
        "output_model_name": "GitLabMM",
    })

    assert response.status_code == 200
    output_xmi = response.json()["output_xmi"]
    assert "gitlabMM:Pipeline" in output_xmi
    assert "install-deps" in output_xmi
    assert "deploy-prod" in output_xmi


def test_real_atl_execution_of_a_non_pim_model_returns_422():
    response = client.post("/execute/atl", json={
        "atl_source": ATL_SOURCE_PATH.read_text(encoding="utf-8"),
        "pim_model_xmi": "<?xml version=\"1.0\"?><root/>",
        "target_ecore": GITLAB_ECORE_PATH.read_text(encoding="utf-8"),
        "output_model_name": "GitLabMM",
    })

    assert response.status_code == 422


def test_real_acceleo_execution_produces_real_gitlab_yaml_from_a_real_atl_produced_psm_instance():
    atl_response = client.post("/execute/atl", json={
        "atl_source": ATL_SOURCE_PATH.read_text(encoding="utf-8"),
        "pim_model_xmi": PIM_SAMPLE_INSTANCE_PATH.read_text(encoding="utf-8"),
        "target_ecore": GITLAB_ECORE_PATH.read_text(encoding="utf-8"),
        "output_model_name": "GitLabMM",
    })
    assert atl_response.status_code == 200
    psm_instance = atl_response.json()["output_xmi"]

    acceleo_response = client.post("/execute/acceleo", json={
        "mtl_source": MTL_SOURCE_PATH.read_text(encoding="utf-8"),
        "psm_model_xmi": psm_instance,
        "target_ecore": GITLAB_ECORE_PATH.read_text(encoding="utf-8"),
    })

    assert acceleo_response.status_code == 200
    generated_files = acceleo_response.json()["generated_files"]
    assert len(generated_files) >= 1
    generated_yaml = "\n".join(generated_files.values())
    assert "install-deps" in generated_yaml
    assert "docker-build" in generated_yaml
    assert "stages:" in generated_yaml


SWARCH2PIM_ATL_PATH = REPO_ROOT / "main" / "src" / "main" / "resources" / "transformations" / "swarch2pim" / "swarch2pim.atl"
E2E_DIR = REPO_ROOT / "main" / "src" / "test" / "resources" / "testCases" / "e2e"


def test_real_chain_from_a_swarch_model_reproduces_the_engines_own_expected_gitlab_yaml():
    # Every step run from its source text through this service (SWArch to
    # PIM, PIM to the GitLab model, the GitLab model to YAML) must give
    # exactly what the engine's own swarch2gitlab command is tested to give.
    pim = client.post("/execute/atl/swarch", json={
        "atl_source": SWARCH2PIM_ATL_PATH.read_text(encoding="utf-8"),
        "swarch_model_xmi": (E2E_DIR / "input1.swarch").read_text(encoding="utf-8"),
    })
    assert pim.status_code == 200, pim.text

    psm = client.post("/execute/atl", json={
        "atl_source": ATL_SOURCE_PATH.read_text(encoding="utf-8"),
        "pim_model_xmi": pim.json()["output_xmi"],
        "target_ecore": GITLAB_ECORE_PATH.read_text(encoding="utf-8"),
        "output_model_name": "GitLabMM",
    })
    assert psm.status_code == 200, psm.text

    yaml = client.post("/execute/acceleo", json={
        "mtl_source": MTL_SOURCE_PATH.read_text(encoding="utf-8"),
        "psm_model_xmi": psm.json()["output_xmi"],
        "target_ecore": GITLAB_ECORE_PATH.read_text(encoding="utf-8"),
    })
    assert yaml.status_code == 200, yaml.text

    # The GitLab template's source and the engine's precompiled copy of it
    # differ by one line that holds only spaces, so such lines are left out
    # of the comparison; every line with content must match exactly.
    def content_lines(text):
        return [line for line in text.replace("\r\n", "\n").split("\n") if line.strip() or line == ""]

    expected = (E2E_DIR / "expected1.yml").read_text(encoding="utf-8")
    actual = yaml.json()["generated_files"][".gitlab-ci.yml"]
    assert content_lines(actual) == content_lines(expected)


def test_real_swarch_execution_of_a_non_swarch_model_returns_422():
    response = client.post("/execute/atl/swarch", json={
        "atl_source": SWARCH2PIM_ATL_PATH.read_text(encoding="utf-8"),
        "swarch_model_xmi": PIM_SAMPLE_INSTANCE_PATH.read_text(encoding="utf-8"),
    })

    assert response.status_code == 422


def test_real_acceleo_execution_of_a_non_gitlab_model_returns_422():
    response = client.post("/execute/acceleo", json={
        "mtl_source": MTL_SOURCE_PATH.read_text(encoding="utf-8"),
        "psm_model_xmi": "<?xml version=\"1.0\"?><root/>",
        "target_ecore": GITLAB_ECORE_PATH.read_text(encoding="utf-8"),
    })

    assert response.status_code == 422
