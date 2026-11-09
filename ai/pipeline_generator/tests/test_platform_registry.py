import json
import threading
from pathlib import Path

import pytest

from platform_registry import Artifacts, PlatformRegistry, PublishError, platform_id_for
from platforms import Platform
from settings import PLATFORMS_DIR


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


def test_the_committed_platforms_are_listed():
    registry = PlatformRegistry(PLATFORMS_DIR)

    assert Platform("gitlab", "GitLab CI", 1, ".gitlab-ci.yml", "GitLabMM") in registry.latest()
    assert registry.get("gitlab") == Platform("gitlab", "GitLab CI", 1, ".gitlab-ci.yml", "GitLabMM")


def test_a_platform_gives_back_its_three_files():
    registry = PlatformRegistry(PLATFORMS_DIR)

    artifacts = registry.artifacts(registry.get("gitlab"))

    assert "module pim2gitlabmodel;" in artifacts.transformation
    assert "create OUT : GitLabMM from IN : PIM;" in artifacts.transformation
    assert "[module generate(" in artifacts.template
    assert "gitlabMM" in artifacts.metamodel


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
        {"id": "x", "name": "X", "version": 1, "output_filename": "..", "output_model_name": "XMM"},
        {"id": "x", "name": "X", "version": 1, "output_filename": "", "output_model_name": "XMM"},
        {"id": "x", "name": None, "version": 1, "output_filename": ".x.yml", "output_model_name": "XMM"},
        {"id": "x", "name": "X", "version": 1, "output_filename": ".x.yml", "output_model_name": None},
        {"id": "x", "name": "X", "version": "1", "output_filename": ".x.yml", "output_model_name": "XMM"},
        {"id": "x", "name": "X", "version": True, "output_filename": ".x.yml", "output_model_name": "XMM"},
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


def test_a_folder_that_is_not_a_version_number_is_ignored(folder):
    _write_version(folder, "x", 1)
    other = folder / "x" / "draft"
    other.mkdir()
    (other / "platform.json").write_text("{}", encoding="utf-8")

    assert [p.version for p in PlatformRegistry(folder).latest()] == [1]


@pytest.mark.parametrize("platform_id", ["", "../x", "X", "a/b", "-x", "x" * 41])
def test_an_id_that_is_not_a_folder_name_this_registry_uses_is_never_read(folder, platform_id):
    _write_version(folder, "x", 1)

    assert PlatformRegistry(folder).get(platform_id) is None


def test_a_version_missing_one_of_its_files_is_skipped_and_the_one_before_is_used(folder):
    _write_version(folder, "x", 1)
    damaged = _write_version(folder, "x", 2)
    (damaged / "template.mtl").unlink()
    registry = PlatformRegistry(folder)

    assert registry.get("x").version == 1
    assert [p.version for p in registry.latest()] == [1]


def test_a_version_that_is_skipped_is_logged_with_the_reason(folder, caplog):
    damaged = _write_version(folder, "x", 1)
    (damaged / "transformation.atl").unlink()

    PlatformRegistry(folder).latest()

    assert "transformation.atl is missing" in caplog.text


def test_the_committed_swarch_to_pim_transformation_is_read():
    transformation = PlatformRegistry(PLATFORMS_DIR).pim_transformation()

    assert "module swarch2pim;" in transformation
    assert "create OUT : PIMMM from IN : SWArchMM;" in transformation


def test_the_swarch_to_pim_transformation_is_read_on_every_call(folder):
    folder.mkdir()
    (folder / "swarch2pim.atl").write_text("first", encoding="utf-8")
    registry = PlatformRegistry(folder)
    assert registry.pim_transformation() == "first"

    (folder / "swarch2pim.atl").write_text("second", encoding="utf-8")

    assert registry.pim_transformation() == "second"


def test_a_missing_swarch_to_pim_transformation_is_an_error_not_an_empty_one(folder):
    folder.mkdir()

    with pytest.raises(OSError):
        PlatformRegistry(folder).pim_transformation()


def test_a_file_in_the_folder_is_not_a_platform(folder):
    _write_version(folder, "x", 1)
    (folder / "swarch2pim.atl").write_text("not a platform", encoding="utf-8")

    assert [p.id for p in PlatformRegistry(folder).latest()] == ["x"]


def test_a_missing_folder_is_logged_as_an_error_and_offers_nothing(folder, caplog):
    assert PlatformRegistry(folder).latest() == []
    assert "does not exist" in caplog.text


def test_a_folder_with_no_usable_platform_is_logged_as_an_error(folder, caplog):
    folder.mkdir()

    assert PlatformRegistry(folder).latest() == []
    assert "holds no usable platform" in caplog.text


# --- publishing ----------------------------------------------------------------

ARTIFACTS = Artifacts(metamodel="<ecore/>", transformation="create OUT : WoodMM from IN : PIM;", template="[module m]")


@pytest.fixture
def empty_registry(tmp_path):
    (tmp_path / "published").mkdir()
    return PlatformRegistry(tmp_path / "published")


def _publish(registry, name="Woodpecker CI", output_filename=".woodpecker.yaml", artifacts=ARTIFACTS):
    return registry.publish(
        name=name,
        output_filename=output_filename,
        output_model_name="WoodMM",
        artifacts=artifacts,
        source_run_id="abc123",
    )


def test_a_published_platform_is_listed_and_its_files_read_back_exactly(empty_registry, tmp_path):
    published = _publish(empty_registry)

    assert published.id == "woodpecker-ci"
    assert published.version == 1
    assert empty_registry.latest() == [published]
    assert empty_registry.artifacts(published) == ARTIFACTS
    folder = tmp_path / "published" / "woodpecker-ci" / "1"
    assert sorted(path.name for path in folder.iterdir()) == ["metamodel.ecore", "platform.json", "template.mtl", "transformation.atl"]
    meta = json.loads((folder / "platform.json").read_text(encoding="utf-8"))
    assert meta["source_run_id"] == "abc123" and meta["output_model_name"] == "WoodMM"


def test_publishing_the_same_platform_again_adds_the_next_version_and_keeps_the_earlier_one(empty_registry):
    first = _publish(empty_registry)
    second = _publish(empty_registry, artifacts=Artifacts("<ecore v2/>", ARTIFACTS.transformation, ARTIFACTS.template))

    assert (first.version, second.version) == (1, 2)
    assert empty_registry.get("woodpecker-ci") == second
    assert empty_registry.artifacts(second).metamodel == "<ecore v2/>"
    assert empty_registry.artifacts(first).metamodel == "<ecore/>"
    assert [platform.version for platform in empty_registry.latest()] == [2]


def test_concurrent_publishes_never_take_the_same_version(empty_registry):
    results = []

    def publish():
        results.append(_publish(empty_registry).version)

    threads = [threading.Thread(target=publish) for _ in range(8)]
    for thread in threads:
        thread.start()
    for thread in threads:
        thread.join()

    assert sorted(results) == list(range(1, 9))


def test_a_version_without_its_platform_json_is_never_listed(empty_registry, tmp_path):
    published = _publish(empty_registry)
    unfinished = tmp_path / "published" / "woodpecker-ci" / "2"
    unfinished.mkdir()
    (unfinished / "metamodel.ecore").write_text("<half/>", encoding="utf-8")

    assert empty_registry.get("woodpecker-ci") == published
    assert _publish(empty_registry).version == 3


@pytest.mark.parametrize("name, expected", [("Woodpecker CI", "woodpecker-ci"), ("  Bamboo!! ", "bamboo"), ("Drone 2.0", "drone-2-0")])
def test_the_id_comes_from_the_name(name, expected):
    assert platform_id_for(name) == expected


@pytest.mark.parametrize("name", ["", "   ", "!!!", "x" * 101])
def test_a_name_that_gives_no_usable_id_or_is_too_long_is_refused(empty_registry, name):
    with pytest.raises(PublishError):
        _publish(empty_registry, name=name)
    assert empty_registry.latest() == []


@pytest.mark.parametrize("output_filename", [
    "../x.yml", "/etc/x.yml", "a/x.yml", "a\\x.yml", "C:x.yml", "a\x00b.yml", "a\nb.yml", "", "x" * 256,
])
def test_an_output_file_name_that_is_not_one_plain_file_name_is_refused(empty_registry, output_filename):
    with pytest.raises(PublishError, match="one file name"):
        _publish(empty_registry, output_filename=output_filename)


def test_an_output_model_name_that_is_not_one_word_is_refused(empty_registry):
    with pytest.raises(PublishError, match="single word"):
        empty_registry.publish(name="X", output_filename="x.yml", output_model_name="Wood MM; rm", artifacts=ARTIFACTS, source_run_id=None)


def test_a_version_folder_claimed_after_the_listing_was_read_is_skipped_not_reused(empty_registry, tmp_path, monkeypatch):
    # Another process claims version 1 between this one listing the folder and
    # creating its own: the atomic mkdir fails, and the next number is taken.
    platform_folder = tmp_path / "published" / "woodpecker-ci"
    (platform_folder / "1").mkdir(parents=True)
    real_iterdir = Path.iterdir
    monkeypatch.setattr(Path, "iterdir", lambda self: iter([]) if self == platform_folder else real_iterdir(self))

    published = _publish(empty_registry)

    assert published.version == 2
    assert list(real_iterdir(platform_folder / "1")) == []


def test_publishing_under_a_name_that_belongs_to_another_platform_is_refused(empty_registry):
    _publish(empty_registry, name="GitLab")

    with pytest.raises(PublishError, match="already the platform 'GitLab'"):
        _publish(empty_registry, name="gitlab")
    assert [platform.version for platform in empty_registry.latest()] == [1]


def test_a_missing_platforms_folder_is_not_created_by_publishing(tmp_path):
    with pytest.raises(OSError):
        _publish(PlatformRegistry(tmp_path / "missing"))

    assert not (tmp_path / "missing").exists()
