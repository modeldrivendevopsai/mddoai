import pytest

from platforms import PLATFORMS, Platform


def test_a_platform_whose_output_is_one_plain_file_name_is_accepted():
    assert Platform("x", "X CI", "swarch2x", ".x-ci.yml", ()).output_filename == ".x-ci.yml"


@pytest.mark.parametrize("name", ["bamboo-specs/bamboo.yaml", "../x.yml", "/abs.yml"])
def test_a_platform_whose_output_has_a_folder_in_it_is_refused_when_it_is_defined(name):
    # The engine runner and the history join the name onto a folder, so such a
    # platform would fail on every generation: better to fail when it is defined.
    with pytest.raises(ValueError, match="plain file name"):
        Platform("x", "X CI", "swarch2x", name, ())


def test_every_platform_the_service_offers_passes_that_rule():
    for platform in PLATFORMS.values():
        assert "/" not in platform.output_filename
