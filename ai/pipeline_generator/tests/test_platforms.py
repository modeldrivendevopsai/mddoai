import pytest

from platforms import Platform


def test_a_platform_whose_output_is_one_plain_file_name_is_accepted():
    assert Platform("x", "X CI", "1.0.0", ".x-ci.yml", "XMM").output_filename == ".x-ci.yml"


@pytest.mark.parametrize("name", ["bamboo-specs/bamboo.yaml", "../x.yml", "/abs.yml", "", ".", ".."])
def test_a_platform_whose_output_has_a_folder_in_it_is_refused_when_it_is_read(name):
    # The history joins the name onto a folder, so such a platform would fail
    # on every generation: better to fail when it is read.
    with pytest.raises(ValueError, match="plain file name"):
        Platform("x", "X CI", "1.0.0", name, "XMM")
