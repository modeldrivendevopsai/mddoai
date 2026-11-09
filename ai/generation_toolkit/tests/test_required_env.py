"""required_env: a required setting is read as it is, and an unset or empty one
stops the service instead of becoming a default."""
import pytest

from generation_toolkit.required_env import required_env


def test_returns_the_value_without_surrounding_spaces(monkeypatch):
    monkeypatch.setenv("SOME_REQUIRED_SETTING", "  /data/prompts  ")

    assert required_env("SOME_REQUIRED_SETTING") == "/data/prompts"


def test_an_unset_setting_stops_naming_it(monkeypatch):
    monkeypatch.delenv("SOME_REQUIRED_SETTING", raising=False)

    with pytest.raises(KeyError, match="SOME_REQUIRED_SETTING"):
        required_env("SOME_REQUIRED_SETTING")


@pytest.mark.parametrize("value", ["", "   "])
def test_an_empty_setting_stops_naming_it(monkeypatch, value):
    monkeypatch.setenv("SOME_REQUIRED_SETTING", value)

    with pytest.raises(KeyError, match="SOME_REQUIRED_SETTING"):
        required_env("SOME_REQUIRED_SETTING")
