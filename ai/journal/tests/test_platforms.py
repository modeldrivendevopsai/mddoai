import json
import logging

import pytest

from journal.platforms import (
    NotPurgeable,
    PlatformNotFound,
    PlatformStore,
    PublishError,
    VersionTaken,
    platform_id_for,
)

FILES = {"metamodel": "<ecore/>", "transformation": "module T;", "template": "[template]"}


@pytest.fixture
def folder(tmp_path):
    path = tmp_path / "platforms"
    path.mkdir()
    return path


@pytest.fixture
def store(folder):
    return PlatformStore(folder)


def _ship(folder, platform_id="gitlab", version="1.0.0", name="GitLab CI", output="gitlab.yml", **meta):
    """A version that came with the stack: no publish record."""
    version_folder = folder / platform_id / version
    version_folder.mkdir(parents=True)
    for filename, content in (("metamodel.ecore", "m"), ("transformation.atl", "t"), ("template.mtl", "x")):
        (version_folder / filename).write_text(content, encoding="utf-8")
    fields = {"id": platform_id, "name": name, "version": version, "output_filename": output, "output_model_name": "GitLabMM", **meta}
    (version_folder / "platform.json").write_text(json.dumps(fields), encoding="utf-8")


def _publish(store, name="Woodpecker CI", **overrides):
    fields = {
        "name": name,
        "output_filename": ".woodpecker.yaml",
        "output_model_name": "WoodpeckerMM",
        "source_run_id": "run1",
        **FILES,
    }
    return store.publish(**{**fields, **overrides})


# --- publishing ----------------------------------------------------------------------


def test_the_first_publish_is_version_one_point_zero_point_zero(store, folder):
    published = _publish(store)

    assert (published.id, published.version, published.published) == ("woodpecker-ci", "1.0.0", True)
    assert json.loads((folder / "woodpecker-ci" / "1.0.0" / "platform.json").read_text(encoding="utf-8")) == {
        "id": "woodpecker-ci",
        "name": "Woodpecker CI",
        "version": "1.0.0",
        "output_filename": ".woodpecker.yaml",
        "output_model_name": "WoodpeckerMM",
        "source_run_id": "run1",
        "published_at": published.published_at,
    }


def test_the_files_are_kept_exactly_as_sent_with_their_line_endings(store, folder):
    _publish(store, transformation="module T;\r\nrule A {}\n")

    assert store.files("woodpecker-ci", "1.0.0")["transformation"] == "module T;\r\nrule A {}\n"


def test_a_publish_without_a_version_is_the_patch_after_the_highest(store):
    _publish(store)
    _publish(store, version="1.4.2")

    assert _publish(store).version == "1.4.3"


def test_a_publish_can_choose_its_version(store):
    assert _publish(store, version="2.0.0").version == "2.0.0"


def test_a_version_that_exists_is_never_replaced(store):
    _publish(store, version="1.0.0", metamodel="first")

    with pytest.raises(VersionTaken):
        _publish(store, version="1.0.0", metamodel="second")

    assert store.files("woodpecker-ci", "1.0.0")["metamodel"] == "first"


@pytest.mark.parametrize("version", ["1", "1.2", "01.0.0", "1.0.0-rc1", "v1.0.0", "x"])
def test_a_version_that_is_not_major_minor_patch_is_refused(store, version):
    with pytest.raises(PublishError, match="1.2.0"):
        _publish(store, version=version)


def test_the_latest_is_the_highest_version_and_not_the_last_published(store):
    _publish(store, version="2.0.0")
    _publish(store, version="1.5.0")

    assert store.get("woodpecker-ci").version == "2.0.0"
    assert store.latest()[0].version == "2.0.0"


def test_versions_are_compared_as_numbers(store):
    _publish(store, version="1.9.0")
    _publish(store, version="1.10.0")

    assert store.get("woodpecker-ci").version == "1.10.0"
    assert [v.version for v in store.versions("woodpecker-ci")] == ["1.9.0", "1.10.0"]


def test_a_publish_under_the_name_of_another_platform_is_refused(store, folder):
    _ship(folder, platform_id="gitlab-ci", name="GitLab CI")

    with pytest.raises(PublishError, match="already the platform 'GitLab CI'"):
        _publish(store, name="gitlab ci!")


def test_a_publish_under_the_same_name_adds_a_version(store):
    _publish(store, name="Woodpecker CI")

    assert _publish(store, name="  Woodpecker CI ").version == "1.0.1"


@pytest.mark.parametrize(
    "overrides,match",
    [
        ({"name": ""}, "name"),
        ({"name": "x" * 101}, "name"),
        ({"name": "!!!"}, "letter or digit"),
        ({"output_filename": "a/b.yml"}, "one file name"),
        ({"output_filename": "a\\b.yml"}, "one file name"),
        ({"output_filename": ".."}, "one file name"),
        ({"output_filename": "x\x00y"}, "one file name"),
        ({"output_filename": "n" * 256}, "one file name"),
        ({"output_model_name": "two words"}, "single word"),
    ],
)
def test_a_publish_with_input_that_could_not_be_used_is_refused_and_creates_nothing(store, folder, overrides, match):
    with pytest.raises(PublishError, match=match):
        _publish(store, **overrides)

    assert list(folder.iterdir()) == []


def test_a_publish_that_fails_part_way_is_never_listed_and_its_number_is_not_reused(store, folder, monkeypatch):
    real = PlatformStore._write_meta

    def failing(version_folder, published):
        raise OSError("disk full")

    monkeypatch.setattr(PlatformStore, "_write_meta", staticmethod(failing))
    with pytest.raises(OSError):
        _publish(store)
    monkeypatch.setattr(PlatformStore, "_write_meta", staticmethod(real))

    assert store.latest() == []
    assert _publish(store).version == "1.0.1"


def test_a_publish_leaves_no_temporary_file_behind(store, folder):
    _publish(store)

    assert sorted(p.name for p in (folder / "woodpecker-ci" / "1.0.0").iterdir()) == [
        "metamodel.ecore",
        "platform.json",
        "template.mtl",
        "transformation.atl",
    ]


# --- reading --------------------------------------------------------------------------


def test_the_latest_of_each_platform_is_listed_by_name(store, folder):
    _ship(folder)
    _publish(store, name="Azure Pipelines")
    _publish(store, name="Azure Pipelines")

    assert [(p.name, p.version) for p in store.latest()] == [("Azure Pipelines", "1.0.1"), ("GitLab CI", "1.0.0")]


def test_a_named_version_is_read_and_an_unknown_one_is_not(store):
    _publish(store)
    _publish(store)

    assert store.get("woodpecker-ci", "1.0.0").version == "1.0.0"
    assert store.get("woodpecker-ci", "9.9.9") is None
    assert store.get("nothing") is None
    assert store.get("../x") is None


def test_a_version_with_a_file_missing_is_skipped_and_logged_and_the_one_before_is_used(store, folder, caplog):
    _publish(store)
    _publish(store)
    (folder / "woodpecker-ci" / "1.0.1" / "template.mtl").unlink()

    with caplog.at_level(logging.WARNING):
        assert store.get("woodpecker-ci").version == "1.0.0"

    assert "template.mtl is missing" in caplog.text


@pytest.mark.parametrize(
    "meta",
    ["not json", "[]", '{"id": "woodpecker-ci"}', '{"version": 1}'],
)
def test_a_version_whose_platform_json_is_unusable_is_skipped(store, folder, meta):
    _publish(store)
    (folder / "woodpecker-ci" / "1.0.0" / "platform.json").write_text(meta, encoding="utf-8")

    assert store.get("woodpecker-ci") is None


@pytest.mark.parametrize(
    "change",
    [{"id": "other"}, {"version": "9.9.9"}, {"version": 1}, {"output_filename": "../x"}, {"source_run_id": 5}, {"published_at": 5}],
)
def test_a_version_that_is_not_what_its_folder_says_is_skipped(store, folder, change):
    _publish(store)
    path = folder / "woodpecker-ci" / "1.0.0" / "platform.json"
    path.write_text(json.dumps({**json.loads(path.read_text(encoding="utf-8")), **change}), encoding="utf-8")

    assert store.get("woodpecker-ci") is None


def test_files_of_an_unknown_version_are_not_found(store):
    with pytest.raises(PlatformNotFound):
        store.files("woodpecker-ci", "1.0.0")


def test_the_swarch_to_pim_transformation_is_read_from_beside_the_platforms(store, folder):
    (folder / "swarch2pim.atl").write_text("module S;", encoding="utf-8")

    assert store.pim_transformation() == "module S;"


def test_a_missing_platforms_folder_lists_nothing_and_says_so(tmp_path, caplog):
    with caplog.at_level(logging.ERROR):
        assert PlatformStore(tmp_path / "none").latest() == []

    assert "does not exist" in caplog.text


# --- purging --------------------------------------------------------------------------


def test_one_published_version_can_be_purged_and_the_others_stay(store, folder):
    _publish(store)
    _publish(store)

    assert store.purge("woodpecker-ci", "1.0.0") == ["1.0.0"]

    assert [v.version for v in store.versions("woodpecker-ci")] == ["1.0.1"]
    assert not (folder / "woodpecker-ci" / "1.0.0").exists()


def test_purging_the_last_version_removes_the_platform_folder_too(store, folder):
    _publish(store)

    store.purge("woodpecker-ci", "1.0.0")

    assert not (folder / "woodpecker-ci").exists()
    assert store.latest() == []


def test_a_whole_published_platform_can_be_purged(store, folder):
    _publish(store)
    _publish(store)
    _ship(folder)

    assert store.purge("woodpecker-ci") == ["1.0.0", "1.0.1"]

    assert [p.id for p in store.latest()] == ["gitlab"]


def test_a_purged_version_number_can_be_published_again(store):
    _publish(store, version="1.0.0", metamodel="old")
    store.purge("woodpecker-ci", "1.0.0")

    _publish(store, version="1.0.0", metamodel="new")

    assert store.files("woodpecker-ci", "1.0.0")["metamodel"] == "new"


def test_a_version_that_came_with_the_stack_is_never_purged(store, folder):
    _ship(folder)

    with pytest.raises(NotPurgeable):
        store.purge("gitlab", "1.0.0")
    with pytest.raises(NotPurgeable):
        store.purge("gitlab")

    assert store.get("gitlab").version == "1.0.0"


def test_a_platform_with_one_shipped_version_loses_nothing_when_all_are_purged(store, folder):
    _ship(folder, platform_id="mixed", name="Mixed", version="1.0.0")
    _publish(store, name="Mixed")

    with pytest.raises(NotPurgeable):
        store.purge("mixed")

    assert [v.version for v in store.versions("mixed")] == ["1.0.0", "1.0.1"]


def test_a_publish_that_stopped_part_way_can_be_purged(store, folder, monkeypatch):
    monkeypatch.setattr(PlatformStore, "_write_meta", staticmethod(lambda *args: (_ for _ in ()).throw(OSError("full"))))
    with pytest.raises(OSError):
        _publish(store)
    monkeypatch.undo()

    assert store.purge("woodpecker-ci", "1.0.0") == ["1.0.0"]
    assert not (folder / "woodpecker-ci").exists()


@pytest.mark.parametrize("platform_id,version", [("nothing", None), ("nothing", "1.0.0"), ("../x", None), ("woodpecker-ci", "9.9.9"), ("woodpecker-ci", "x"), ("woodpecker-ci", "../x")])
def test_purging_something_that_is_not_there_is_not_found_and_removes_nothing(store, folder, platform_id, version):
    _publish(store)

    with pytest.raises(PlatformNotFound):
        store.purge(platform_id, version)

    assert (folder / "woodpecker-ci" / "1.0.0").is_dir()


def test_the_id_of_a_name_is_lowercase_letters_digits_and_dashes():
    assert platform_id_for("  Azure  Pipelines (v2)! ") == "azure-pipelines-v2"
