import pytest

from journal import versions


@pytest.mark.parametrize("version,parts", [("1.0.0", (1, 0, 0)), ("0.0.1", (0, 0, 1)), ("12.345.6789", (12, 345, 6789)), ("999999.0.0", (999999, 0, 0))])
def test_a_version_is_three_numbers(version, parts):
    assert versions.parse(version) == parts


@pytest.mark.parametrize(
    "version",
    ["1", "1.2", "1.2.3.4", "01.2.3", "1.02.3", "1.2.03", "-1.2.3", "1.2.-3", "a.b.c", "1.2.3-beta", "1.2.3+build", "v1.2.3", " 1.2.3", "1.2.3 ", "", "1000000.0.0", None, 1, "1.2.3\n"],
)
def test_anything_else_is_not_a_version(version):
    with pytest.raises(ValueError):
        versions.parse(version)


def test_versions_are_ordered_by_their_numbers_and_not_as_text():
    ordered = ["0.9.0", "1.0.0", "1.0.2", "1.0.10", "1.2.0", "1.10.0", "2.0.0"]

    assert sorted(reversed(ordered), key=versions.parse) == ordered


def test_the_version_after_one_is_its_next_patch():
    assert versions.next_patch("1.2.9") == "1.2.10"
    assert versions.next_patch("0.0.0") == "0.0.1"


def test_the_first_version_is_one_point_zero_point_zero():
    assert versions.next_patch(None) == "1.0.0"
