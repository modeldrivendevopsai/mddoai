from generation_toolkit.prompt_config.presets import list_preset_metadata, list_presets, preset_metadata, resolve_preset

_MINIMAL = {"system_prompt": "x", "attachments": []}


def test_list_presets_always_includes_default_even_with_no_directory(tmp_path):
    assert list_presets(tmp_path, "generation") == ["default"]


def test_list_presets_finds_a_live_preset_file(tmp_path):
    (tmp_path / "generation").mkdir()
    (tmp_path / "generation" / "gitlab.json").write_text("{}", encoding="utf-8")

    assert list_presets(tmp_path, "generation") == ["default", "gitlab"]


def test_list_presets_finds_a_default_only_preset_file(tmp_path):
    (tmp_path / "generation").mkdir()
    (tmp_path / "generation" / "azuredevops.default.json").write_text("{}", encoding="utf-8")

    assert list_presets(tmp_path, "generation") == ["azuredevops", "default"]


def test_list_presets_does_not_descend_into_history(tmp_path):
    history = tmp_path / "generation" / "history"
    history.mkdir(parents=True)
    (history / "default.20260101T000000.000000Z.json").write_text("{}", encoding="utf-8")

    assert list_presets(tmp_path, "generation") == ["default"]


def test_preset_metadata_defaults_label_to_the_id_and_hints_to_empty(tmp_path):
    (tmp_path / "generation").mkdir()
    (tmp_path / "generation" / "gitlab.default.json").write_text(
        '{"system_prompt": "x", "attachments": []}', encoding="utf-8"
    )

    metadata = preset_metadata(tmp_path, "generation", "gitlab")

    assert metadata == {"id": "gitlab", "label": "gitlab", "platform_hints": []}


def test_preset_metadata_reads_real_label_and_hints(tmp_path):
    (tmp_path / "generation").mkdir()
    (tmp_path / "generation" / "mygitlab.default.json").write_text(
        '{"system_prompt": "x", "attachments": [], "label": "GitLab CI/CD", "platform_hints": ["gitlab", "gitlab-ci"]}',
        encoding="utf-8",
    )

    metadata = preset_metadata(tmp_path, "generation", "mygitlab")

    assert metadata == {"id": "mygitlab", "label": "GitLab CI/CD", "platform_hints": ["gitlab", "gitlab-ci"]}


def test_list_preset_metadata_covers_every_real_preset(tmp_path):
    # A real deployment always ships generation/default.default.json (see
    # ai/psm_agent/prompts/), which is what backs the always-included
    # "default" entry list_presets() promises - seeded here to match that
    # real condition, without it "default" would have no file to read
    # metadata from.
    (tmp_path / "generation").mkdir()
    (tmp_path / "generation" / "default.default.json").write_text(
        '{"system_prompt": "x", "attachments": []}', encoding="utf-8"
    )
    (tmp_path / "generation" / "gitlab.default.json").write_text(
        '{"system_prompt": "x", "attachments": []}', encoding="utf-8"
    )

    ids = {m["id"] for m in list_preset_metadata(tmp_path, "generation")}

    assert ids == {"default", "gitlab"}


def test_resolve_preset_matches_via_its_own_id_with_no_hints_configured():
    presets = [{"id": "default", "platform_hints": []}, {"id": "gitlab", "platform_hints": []}]

    assert resolve_preset("GitLab CI/CD Pipelines", presets) == "gitlab"


def test_resolve_preset_matches_via_an_explicit_hint_unrelated_to_its_id():
    # The whole point: a preset's storage id is not what gets matched, its
    # own "platform_hints" metadata is - an arbitrarily-named preset still
    # matches correctly as long as its hints are set.
    presets = [{"id": "default", "platform_hints": []}, {"id": "experimental-v3", "platform_hints": ["gitlab"]}]

    assert resolve_preset("GitLab CI/CD Pipelines", presets) == "experimental-v3"


def test_resolve_preset_matches_despite_whitespace_in_the_description():
    presets = [{"id": "default", "platform_hints": []}, {"id": "azuredevops", "platform_hints": []}]

    assert resolve_preset("Azure DevOps Pipelines", presets) == "azuredevops"


def test_resolve_preset_falls_back_to_default_when_nothing_matches():
    presets = [{"id": "default", "platform_hints": []}, {"id": "gitlab", "platform_hints": []}]

    assert resolve_preset("some brand new platform", presets) == "default"


def test_resolve_preset_never_matches_default_itself_as_a_word_in_the_description():
    presets = [{"id": "default", "platform_hints": []}, {"id": "gitlab", "platform_hints": []}]

    assert resolve_preset("the default CI for GitLab projects", presets) == "gitlab"
