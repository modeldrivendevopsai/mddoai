from fastapi.testclient import TestClient

import main
from platform_registry import PlatformRegistry


def test_starting_the_service_stores_the_platforms_it_ships_with(tmp_path, monkeypatch):
    fresh = PlatformRegistry(tmp_path / "fresh")
    monkeypatch.setattr(main, "get_registry", lambda: fresh)

    assert fresh.latest() == []
    with TestClient(main.app):
        assert [platform.id for platform in fresh.latest()] == ["gitlab"]
