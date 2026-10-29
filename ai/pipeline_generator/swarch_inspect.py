"""Reads a .swarch model's own attributes for display.

This is deliberately not validation: whether the file is a legitimate
Software Architecture model is validator_agent's job (the real EMF check).
This only answers "what does the file say", generically, straight from the
XML root's attributes, so it never duplicates the SWArch metamodel's own list
of fields and stays correct when that metamodel grows one.
"""
import re
import xml.etree.ElementTree as ET
from typing import TypedDict

# Rejected outright rather than handed to the XML parser: a DOCTYPE is how
# entity-expansion and external-entity attacks are delivered, and no real
# .swarch model has one.
_DOCTYPE = re.compile(r"<!DOCTYPE", re.IGNORECASE)


class Attribute(TypedDict):
    name: str
    value: str


class Inspection(TypedDict):
    element: str
    attributes: list[Attribute]


def inspect_swarch(content: str) -> Inspection | None:
    """The root element's name and its plain attributes, in file order.
    None when the content can't be read as XML at all (or carries a DOCTYPE);
    the real validator reports why, this just has nothing to show."""
    if _DOCTYPE.search(content):
        return None
    try:
        root = ET.fromstring(content.encode("utf-8"))
    except ET.ParseError:
        return None
    return {
        "element": root.tag.rpartition("}")[2],
        # Namespaced attributes (xmi:version, xsi:type, ...) are XMI plumbing,
        # not the architecture's own properties.
        "attributes": [
            {"name": name, "value": value}
            for name, value in root.attrib.items()
            if not name.startswith("{")
        ],
    }
