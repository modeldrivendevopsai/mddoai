"""PlatformRegistry: publishing versions, reading the latest, and refusing
what cannot be stored safely."""
import json
import threading
from pathlib import Path

import pytest

from platform_registry import Artifacts, PlatformRegistry, PublishError, platform_id_for

ARTIFACTS = Artifacts(metamodel="<ecore/>", transformation="create OUT : WoodMM from IN : PIM;", template="[module m]")


@pytest.fixture
def registry(tmp_path):
    return PlatformRegistry(tmp_path / "platforms")


def _publish(registry, name="Woodpecker CI", output_filename=".woodpecker/pipeline.yaml", artifacts=ARTIFACTS):
    return registry.publish(
        name=name,
        output_filename=output_filename,
        output_model_name="WoodMM",
        artifacts=artifacts,
        source_run_id="abc123",
        reserved_ids={"gitlab"},
    )


def test_a_published_platform_is_listed_and_its_files_read_back_exactly(registry, tmp_path):
    published = _publish(registry)

    assert published.id == "woodpecker-ci"
    assert published.version == 1
    assert registry.latest() == [published]
    assert registry.artifacts(published) == ARTIFACTS
    folder = tmp_path / "platforms" / "woodpecker-ci" / "1"
    assert sorted(path.name for path in folder.iterdir()) == ["metamodel.ecore", "platform.json", "template.mtl", "transformation.atl"]
    meta = json.loads((folder / "platform.json").read_text(encoding="utf-8"))
    assert meta["source_run_id"] == "abc123" and meta["output_model_name"] == "WoodMM"


def test_publishing_the_same_platform_again_adds_the_next_version_and_keeps_the_earlier_one(registry, tmp_path):
    first = _publish(registry)
    second = _publish(registry, artifacts=Artifacts("<ecore v2/>", ARTIFACTS.transformation, ARTIFACTS.template))

    assert (first.version, second.version) == (1, 2)
    assert registry.get_latest("woodpecker-ci") == second
    assert registry.artifacts(second).metamodel == "<ecore v2/>"
    assert registry.artifacts(first).metamodel == "<ecore/>"
    assert [p.version for p in registry.latest()] == [2]


def test_concurrent_publishes_never_take_the_same_version(registry):
    results = []

    def publish():
        results.append(_publish(registry).version)

    threads = [threading.Thread(target=publish) for _ in range(8)]
    for thread in threads:
        thread.start()
    for thread in threads:
        thread.join()

    assert sorted(results) == list(range(1, 9))


def test_a_version_without_its_platform_json_is_never_listed(registry, tmp_path):
    published = _publish(registry)
    unfinished = tmp_path / "platforms" / "woodpecker-ci" / "2"
    unfinished.mkdir()
    (unfinished / "metamodel.ecore").write_text("<half/>", encoding="utf-8")

    assert registry.get_latest("woodpecker-ci") == published
    assert _publish(registry).version == 3


def test_a_damaged_platform_json_is_skipped_not_fatal(registry, tmp_path):
    good = _publish(registry, name="Good")
    broken = tmp_path / "platforms" / "broken" / "1"
    broken.mkdir(parents=True)
    (broken / "platform.json").write_text("{not json", encoding="utf-8")

    assert registry.latest() == [good]


@pytest.mark.parametrize("name, expected", [("Woodpecker CI", "woodpecker-ci"), ("  Bamboo!! ", "bamboo"), ("Drone 2.0", "drone-2-0")])
def test_the_id_comes_from_the_name(name, expected):
    assert platform_id_for(name) == expected


@pytest.mark.parametrize("name", ["", "   ", "!!!", "x" * 101])
def test_a_name_that_gives_no_usable_id_or_is_too_long_is_refused(registry, name):
    with pytest.raises(PublishError):
        _publish(registry, name=name)
    assert registry.latest() == []


def test_a_built_in_platforms_id_cannot_be_taken(registry):
    with pytest.raises(PublishError, match="built-in"):
        _publish(registry, name="GitLab")


@pytest.mark.parametrize("output_filename", [
    "../x.yml", "/etc/x.yml", "a/../../x.yml", "a\\x.yml", "", "x" * 256,
    # git's own folder or pointer file, in the spellings a case-insensitive
    # or Windows disk reads as the same name
    ".git/config", ".GIT/hooks/pre-commit", "sub/.Git/config", ".git./config", "sub/.git",
])
def test_an_output_file_name_that_would_leave_its_folder_is_refused(registry, output_filename):
    with pytest.raises(PublishError, match="relative path"):
        _publish(registry, output_filename=output_filename)


def test_an_output_model_name_that_is_not_one_word_is_refused(registry):
    with pytest.raises(PublishError, match="single word"):
        registry.publish(name="X", output_filename="x.yml", output_model_name="Wood MM; rm", artifacts=ARTIFACTS,
                         source_run_id=None, reserved_ids=set())


@pytest.mark.parametrize("platform_id", ["../x", "X", "", "a/b", "-x"])
def test_an_id_that_is_not_one_this_class_makes_is_never_used_as_a_path(registry, platform_id):
    assert registry.get_latest(platform_id) is None


def test_a_version_folder_claimed_after_the_listing_was_read_is_skipped_not_reused(registry, tmp_path, monkeypatch):
    # Another process claims version 1 between this one listing the folder and
    # creating its own: the atomic mkdir fails, and the next number is taken.
    platform_folder = tmp_path / "platforms" / "woodpecker-ci"
    (platform_folder / "1").mkdir(parents=True)
    real_iterdir = Path.iterdir
    monkeypatch.setattr(Path, "iterdir", lambda self: iter([]) if self == platform_folder else real_iterdir(self))

    published = _publish(registry)

    assert published.version == 2
    assert list(real_iterdir(platform_folder / "1")) == []


def test_a_specific_version_is_found_after_newer_ones_are_published(registry):
    _publish(registry)
    _publish(registry)

    assert registry.get("woodpecker-ci", 1).version == 1
    assert registry.get("woodpecker-ci", 2).version == 2
    assert registry.get("woodpecker-ci", 3) is None
    assert registry.get("../x", 1) is None
    assert registry.get("woodpecker-ci", 0) is None


def test_an_empty_registry_lists_nothing(registry):
    assert registry.latest() == []
    assert registry.get_latest("woodpecker-ci") is None
