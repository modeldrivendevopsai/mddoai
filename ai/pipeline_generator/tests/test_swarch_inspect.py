from pathlib import Path

from swarch_inspect import inspect_swarch

FIXTURES = Path(__file__).parent / "fixtures"


def test_reads_the_root_element_and_its_attributes_in_file_order():
    content = (FIXTURES / "valid.swarch").read_text(encoding="utf-8")

    inspection = inspect_swarch(content)

    assert inspection["element"] == "SoftwareArchitecture"
    assert [attribute["name"] for attribute in inspection["attributes"]] == [
        "name", "type", "build_system", "language", "unit_test", "health_check", "lint_check",
    ]
    assert {"name": "language", "value": "python"} in inspection["attributes"]


def test_excludes_namespaced_xmi_plumbing_attributes():
    content = (FIXTURES / "valid.swarch").read_text(encoding="utf-8")

    names = [attribute["name"] for attribute in inspect_swarch(content)["attributes"]]

    assert not any("version" in name or "{" in name for name in names)


def test_keeps_an_empty_attribute_value_as_an_empty_string():
    content = (FIXTURES / "valid.swarch").read_text(encoding="utf-8")

    assert {"name": "lint_check", "value": ""} in inspect_swarch(content)["attributes"]


def test_malformed_xml_has_nothing_to_show():
    assert inspect_swarch('<swarch:SoftwareArchitecture name="x"') is None


def test_not_xml_at_all_has_nothing_to_show():
    assert inspect_swarch("just some text") is None


def test_a_doctype_is_never_handed_to_the_parser():
    content = (
        '<?xml version="1.0"?>\n<!DOCTYPE a [ <!ENTITY x "boom"> ]>\n'
        '<swarch:SoftwareArchitecture xmlns:swarch="urn:x" name="&x;"/>'
    )

    assert inspect_swarch(content) is None


def test_a_lowercase_doctype_is_rejected_too():
    assert inspect_swarch('<!doctype a><a name="x"/>') is None
