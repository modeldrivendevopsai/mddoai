"""The registry reads the platforms through the journal, which here is the real
one over a copy of the committed platforms folder."""
import logging

import httpx
import pytest

from clients import journal_client
from platform_registry import PlatformsUnavailable
from platforms import Platform

PUBLISH = {
    "name": "Woodpecker CI",
    "output_filename": ".woodpecker.yaml",
    "output_model_name": "WoodMM",
    "metamodel": "metamodel text",
    "transformation": "transformation text",
    "template": "template text",
    "source_run_id": "run1",
}


def test_the_committed_platforms_are_listed(registry):
    gitlab = Platform("gitlab", "GitLab CI", "1.0.0", ".gitlab-ci.yml", "GitLabMM")

    assert gitlab in registry.latest()
    assert registry.get("gitlab") == gitlab


def test_a_platform_gives_back_its_three_files(registry):
    artifacts = registry.artifacts(registry.get("gitlab"))

    assert "module pim2gitlabmodel;" in artifacts.transformation
    assert "create OUT : GitLabMM from IN : PIM;" in artifacts.transformation
    assert "[module generate(" in artifacts.template
    assert "gitlabMM" in artifacts.metamodel


def test_the_swarch_to_pim_transformation_is_read(registry):
    transformation = registry.pim_transformation()

    assert "module swarch2pim" in transformation


def test_each_platform_is_offered_at_its_latest_version_by_name(registry):
    journal_client.publish_platform(**PUBLISH, version="1.0.0")
    journal_client.publish_platform(**{**PUBLISH, "template": "newer template"}, version="1.1.0")

    assert [(p.id, p.version) for p in registry.latest()] == [("gitlab", "1.0.0"), ("woodpecker-ci", "1.1.0")]
    assert registry.artifacts(registry.get("woodpecker-ci")).template == "newer template"


def test_the_listing_says_what_the_platform_list_shows_and_which_can_be_purged(registry):
    journal_client.publish_platform(**PUBLISH, version="1.0.0")
    journal_client.publish_platform(**PUBLISH, version="1.0.1")

    listing = {entry["id"]: entry for entry in registry.listing()}

    assert listing["gitlab"] == {
        "id": "gitlab",
        "name": "GitLab CI",
        "output_filename": ".gitlab-ci.yml",
        "version": "1.0.0",
        "versions": ["1.0.0"],
        "published": False,
    }
    assert listing["woodpecker-ci"]["version"] == "1.0.1"
    assert listing["woodpecker-ci"]["versions"] == ["1.0.0", "1.0.1"]
    assert listing["woodpecker-ci"]["published"] is True
    assert "files" not in listing["woodpecker-ci"] and "source_run_id" not in listing["woodpecker-ci"]


def test_an_unknown_platform_is_not_found(registry):
    assert registry.get("nothing") is None


def test_a_version_purged_after_the_platform_was_looked_up_is_an_error_when_its_files_are_read(registry):
    journal_client.publish_platform(**PUBLISH)
    platform = registry.get("woodpecker-ci")
    journal_client.purge_platform("woodpecker-ci")

    with pytest.raises(ValueError, match="no longer exists"):
        registry.artifacts(platform)


def test_a_platform_the_journal_describes_in_a_way_this_service_cannot_use_is_skipped_and_logged(registry, monkeypatch, caplog):
    good = {"id": "ok", "name": "Ok", "version": "1.0.0", "output_filename": "ok.yml", "output_model_name": "M"}
    bad = {**good, "id": "bad", "output_filename": "../x"}
    monkeypatch.setattr(journal_client, "list_platforms", lambda: [bad, good])

    with caplog.at_level(logging.WARNING):
        assert [p.id for p in registry.latest()] == ["ok"]

    assert "ignoring platform 'bad'" in caplog.text


@pytest.mark.parametrize("call", ["latest", "listing", "get", "artifacts", "pim_transformation"])
def test_a_journal_that_cannot_be_reached_is_platforms_unavailable(registry, monkeypatch, call):
    platform = Platform("gitlab", "GitLab CI", "1.0.0", ".gitlab-ci.yml", "GitLabMM")
    monkeypatch.setattr(journal_client.httpx, "request", lambda *args, **kwargs: (_ for _ in ()).throw(httpx.ConnectError("down")))
    arguments = {"get": ("gitlab",), "artifacts": (platform,)}.get(call, ())

    with pytest.raises(PlatformsUnavailable):
        getattr(registry, call)(*arguments)


def test_an_answer_that_is_not_the_shape_expected_is_platforms_unavailable(registry, monkeypatch):
    monkeypatch.setattr(journal_client, "list_platforms", lambda: [{"id": "x"}])

    with pytest.raises(PlatformsUnavailable):
        registry.listing()
