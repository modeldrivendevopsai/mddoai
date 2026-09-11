"""routes/files.py unit tests: available_files_endpoint() called directly
as a plain function, matching test_prompt_config.py's own convention. No
mocking needed - META_MODELS_DIR is the real, checked-in meta_models/
tree, the same real dependency comparison.py's own tests already exercise.
"""
from routes.files import available_files_endpoint


def test_available_files_finds_real_ecore_files():
    result = available_files_endpoint()

    assert any(path.endswith("githubMM.ecore") for path in result["files"])
    assert any(path.endswith("gitlabMM.ecore") for path in result["files"])


def test_available_files_paths_are_forward_slash_and_relative():
    result = available_files_endpoint()

    for path in result["files"]:
        assert "\\" not in path
        assert not path.startswith("/")
