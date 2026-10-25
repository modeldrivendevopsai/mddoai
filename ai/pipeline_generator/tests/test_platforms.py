import pytest

from platforms import PLATFORMS, Platform


@pytest.mark.parametrize("name", [".x-ci.yml", "bamboo-specs/bamboo.yaml", ".woodpecker/pipeline.yaml"])
def test_a_platform_whose_output_is_a_relative_path_inside_the_folder_is_accepted(name):
    # Some CI systems read their file from a subfolder.
    assert Platform("x", "X CI", "swarch2x", name, ()).output_filename == name


@pytest.mark.parametrize("name", ["../x.yml", "/abs.yml", "a/../../x.yml", "a//x.yml", "a\\x.yml", "", "./x.yml"])
def test_a_platform_whose_output_would_leave_its_folder_is_refused_when_it_is_defined(name):
    # Better to fail when the platform is defined than on every save.
    with pytest.raises(ValueError, match="relative path"):
        Platform("x", "X CI", "swarch2x", name, ())


def test_every_built_in_platform_passes_that_rule():
    for platform in PLATFORMS.values():
        assert platform.runner == "engine" and platform.version is None
