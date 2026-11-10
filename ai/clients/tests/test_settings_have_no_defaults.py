"""A client takes its link from the environment, or the service stops. No client has a
link or any other setting with a default in its code."""
import ast
from pathlib import Path

import pytest

from clients.required_env import required_env

CLIENTS = sorted(Path(__file__).parent.parent.glob("*_client.py"))


def test_a_setting_that_is_set_is_returned(monkeypatch):
    monkeypatch.setenv("SOME_LINK", "  http://somewhere:1  ")

    assert required_env("SOME_LINK") == "http://somewhere:1"


def test_a_setting_that_is_unset_stops_the_service_and_names_itself(monkeypatch):
    monkeypatch.delenv("SOME_LINK", raising=False)

    with pytest.raises(KeyError, match="SOME_LINK"):
        required_env("SOME_LINK")


@pytest.mark.parametrize("value", ["", "   "], ids=["empty", "blank"])
def test_a_setting_that_is_empty_stops_the_service_and_says_so(monkeypatch, value):
    monkeypatch.setenv("SOME_LINK", value)

    with pytest.raises(KeyError, match="SOME_LINK is set but empty"):
        required_env("SOME_LINK")


def _reads_with_a_default(call: ast.Call) -> bool:
    function = ast.unparse(call.func)
    return function in ("os.environ.get", "os.getenv") and (len(call.args) > 1 or bool(call.keywords))


@pytest.mark.parametrize("client", CLIENTS, ids=lambda path: path.name)
def test_no_client_reads_a_setting_with_a_default(client):
    tree = ast.parse(client.read_text(encoding="utf-8"))
    offenders = [node.lineno for node in ast.walk(tree) if isinstance(node, ast.Call) and _reads_with_a_default(node)]

    assert offenders == [], f"{client.name} reads a setting with a default on line(s) {offenders}"


@pytest.mark.parametrize("client", CLIENTS, ids=lambda path: path.name)
def test_every_link_comes_from_required_env(client):
    tree = ast.parse(client.read_text(encoding="utf-8"))
    links = [
        node for node in tree.body
        if isinstance(node, ast.Assign) and any(isinstance(t, ast.Name) and t.id.endswith("_URL") for t in node.targets)
    ]

    assert links, f"{client.name} has no link setting"
    for node in links:
        assert ast.unparse(node.value).startswith("required_env("), f"{ast.unparse(node)} is not read with required_env"
