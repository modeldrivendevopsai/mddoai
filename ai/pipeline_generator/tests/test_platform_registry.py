import json

import pytest

from platform_registry import PlatformRegistry
from platforms import Platform
from settings import PLATFORM_SEEDS_DIR


def _write_version(directory, platform_id, version, *, name="X CI", output_filename=".x-ci.yml", meta=None):
    folder = directory / platform_id / str(version)
    folder.mkdir(parents=True)
    meta = meta if meta is not None else {
        "id": platform_id,
        "name": name,
        "version": version,
        "output_filename": output_filename,
        "output_model_name": "XMM",
    }
    (folder / "platform.json").write_text(json.dumps(meta), encoding="utf-8")
    (folder / "metamodel.ecore").write_text(f"metamodel {version}", encoding="utf-8")
    (folder / "transformation.atl").write_text(f"transformation {version}", encoding="utf-8")
    (folder / "template.mtl").write_text(f"template {version}", encoding="utf-8")
    return folder


@pytest.fixture
def folder(tmp_path):
    return tmp_path / "stored"


def test_the_shipped_platforms_are_stored_and_listed(folder):
    registry = PlatformRegistry(folder)

    registry.seed(PLATFORM_SEEDS_DIR)

    assert registry.latest() == [Platform("gitlab", "GitLab CI", 1, ".gitlab-ci.yml", "GitLabMM")]
    assert registry.get("gitlab") == registry.latest()[0]


def test_a_stored_platform_gives_back_its_three_files(folder):
    registry = PlatformRegistry(folder)
    registry.seed(PLATFORM_SEEDS_DIR)

    artifacts = registry.artifacts(registry.get("gitlab"))

    assert "module pim2gitlabmodel;" in artifacts.transformation
    assert "create OUT : GitLabMM from IN : PIM;" in artifacts.transformation
    assert "[module generate(" in artifacts.template
    assert "gitlabMM" in artifacts.metamodel


def test_seeding_never_overwrites_a_version_that_is_stored(folder):
    registry = PlatformRegistry(folder)
    registry.seed(PLATFORM_SEEDS_DIR)
    template = folder / "gitlab" / "1" / "template.mtl"
    template.write_text("changed by hand", encoding="utf-8")

    registry.seed(PLATFORM_SEEDS_DIR)

    assert template.read_text(encoding="utf-8") == "changed by hand"


def test_seeding_adds_a_version_that_is_missing_beside_the_ones_stored(folder, tmp_path):
    seeds = tmp_path / "seeds"
    _write_version(seeds, "x", 1)
    _write_version(seeds, "x", 2)
    _write_version(folder, "x", 1)
    (folder / "x" / "1" / "template.mtl").write_text("changed by hand", encoding="utf-8")
    registry = PlatformRegistry(folder)

    registry.seed(seeds)

    assert registry.get("x").version == 2
    assert (folder / "x" / "1" / "template.mtl").read_text(encoding="utf-8") == "changed by hand"
    assert not list((folder / "x").glob(".seeding-*"))


def test_each_platform_is_offered_at_its_latest_version_by_name(folder):
    _write_version(folder, "zeta", 1, name="Zeta CI")
    _write_version(folder, "alpha", 1, name="alpha old")
    _write_version(folder, "alpha", 2, name="Alpha CI")
    registry = PlatformRegistry(folder)

    assert [(p.id, p.version) for p in registry.latest()] == [("alpha", 2), ("zeta", 1)]
    assert registry.artifacts(registry.get("alpha")).template == "template 2"


def test_an_empty_or_missing_folder_offers_nothing(folder):
    assert PlatformRegistry(folder).latest() == []
    folder.mkdir()
    assert PlatformRegistry(folder).latest() == []
    assert PlatformRegistry(folder).get("gitlab") is None


@pytest.mark.parametrize(
    "meta",
    [
        "{not json",
        "[]",
        {"id": "x"},
        {"id": "other", "name": "X", "version": 1, "output_filename": ".x.yml", "output_model_name": "XMM"},
        {"id": "x", "name": "X", "version": 7, "output_filename": ".x.yml", "output_model_name": "XMM"},
        {"id": "x", "name": "X", "version": 1, "output_filename": "sub/x.yml", "output_model_name": "XMM"},
    ],
)
def test_a_version_without_a_usable_platform_json_is_never_listed(folder, meta):
    version_folder = _write_version(folder, "x", 1, meta=meta if isinstance(meta, dict) else {})
    if not isinstance(meta, dict):
        (version_folder / "platform.json").write_text(meta, encoding="utf-8")
    registry = PlatformRegistry(folder)

    assert registry.latest() == []
    assert registry.get("x") is None


def test_a_version_folder_without_platform_json_is_not_listed_but_an_earlier_version_still_is(folder):
    _write_version(folder, "x", 1)
    unfinished = _write_version(folder, "x", 2)
    (unfinished / "platform.json").unlink()

    assert PlatformRegistry(folder).get("x").version == 1


def test_a_leftover_staging_folder_is_ignored(folder):
    _write_version(folder, "x", 1)
    leftover = folder / "x" / ".seeding-abc"
    leftover.mkdir()
    (leftover / "platform.json").write_text("{}", encoding="utf-8")

    assert [p.version for p in PlatformRegistry(folder).latest()] == [1]


@pytest.mark.parametrize("platform_id", ["", "../x", "X", "a/b", "-x", "x" * 41])
def test_an_id_that_is_not_a_folder_name_this_registry_uses_is_never_read(folder, platform_id):
    _write_version(folder, "x", 1)

    assert PlatformRegistry(folder).get(platform_id) is None
