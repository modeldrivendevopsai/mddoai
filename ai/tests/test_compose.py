"""Checks the compose file against itself and the environment templates."""
import re
from pathlib import Path

import pytest
import yaml

AI_DIR = Path(__file__).resolve().parents[1]
COMPOSE_FILE = AI_DIR / "docker-compose.yml"
TEMPLATES_DIR = AI_DIR.parent / "infra" / "environments" / "templates"

# The settings that name a place inside a container.
LOCATION_SETTING = re.compile(r".*_(DIR|PATH)$")

# The settings compose refuses to start without, written ${NAME:?}.
REQUIRED_SETTING = re.compile(r"\$\{([A-Z0-9_]+):\?")

# A host folder is one required setting of its own, never shared.
HOST_FOLDER_SETTING = re.compile(r"^\$\{(MDDOAI_[A-Z0-9_]+):\?\}$")

# Folders that must already hold content, so Docker must not create them.
MUST_HOLD_CONTENT_SETTING = re.compile(r"^\$\{MDDOAI_(WORKSPACE|PLATFORMS|[A-Z0-9]+_PROMPTS)_DIR:\?\}$")
READ_ONLY_FOLDER_SETTING = re.compile(r"^\$\{MDDOAI_(WORKSPACE|PLATFORMS)_DIR:\?\}$")

# The host setting passed into a container: the workspace name Browse shows.
ALLOWED_HOST_VALUE_IN_ENVIRONMENT = {("pipeline-generator", "GENERATOR_WORKSPACE_LABEL"): "${MDDOAI_WORKSPACE_DIR:?}"}

# How a service's code asks for a folder setting it cannot start without.
REQUIRED_IN_CODE = re.compile(r'(?:_required|required_env)\("([A-Z0-9_]+)"\)')


def _services() -> dict:
    return yaml.safe_load(COMPOSE_FILE.read_text(encoding="utf-8"))["services"]


def _environment(service: dict) -> dict[str, str]:
    environment = service.get("environment", [])
    if isinstance(environment, dict):
        return {name: str(value) for name, value in environment.items()}
    # An entry with no "=" passes a variable through from the host: it has no value here.
    return {name: value for name, _, value in (entry.partition("=") for entry in environment) if _}


def _mount_targets(service: dict) -> list[str]:
    targets = []
    for volume in service.get("volumes", []):
        if isinstance(volume, dict):
            targets.append(volume["target"])
            continue
        # Short syntax is "source:target[:mode]"; the source may hold colons (${HOME:-...}).
        match = re.search(r":(/[^:]*)", volume)
        assert match, f"cannot read the target of the mount {volume!r}"
        targets.append(match.group(1))
    return targets


def _is_at_or_under(path: str, folder: str) -> bool:
    return path == folder or path.startswith(folder.rstrip("/") + "/")


def _location_settings() -> list[tuple[str, str, str]]:
    return [
        (name, setting, value)
        for name, service in _services().items()
        for setting, value in _environment(service).items()
        if LOCATION_SETTING.match(setting)
    ]


def _host_folder_mounts() -> list[tuple[str, str]]:
    return [
        (name, volume["source"])
        for name, service in _services().items()
        for volume in service.get("volumes", [])
        if isinstance(volume, dict) and volume.get("source", "").startswith("${MDDOAI_")
    ]


def _mounts_matching(pattern) -> list[tuple[str, str]]:
    return [(name, source) for name, source in _host_folder_mounts() if pattern.match(source)]


def test_there_are_location_settings_to_check():
    assert _location_settings()


@pytest.mark.parametrize(
    "service_name,setting,value", _location_settings(), ids=lambda argument: str(argument)
)
def test_every_location_a_service_is_told_about_is_an_absolute_path_inside_one_of_its_mounts(service_name, setting, value):
    targets = _mount_targets(_services()[service_name])

    assert value.startswith("/"), f"{service_name}: {setting}={value!r} is empty or not an absolute path"
    assert any(_is_at_or_under(value, target) for target in targets), (
        f"{service_name}: {setting}={value} is not at or under any of its mounts {targets}"
    )


def test_there_are_host_folder_mounts_to_check():
    assert _host_folder_mounts()


@pytest.mark.parametrize("service_name,source", _host_folder_mounts(), ids=lambda argument: str(argument))
def test_a_host_folder_is_exactly_one_required_setting_of_its_own(service_name, source):
    assert HOST_FOLDER_SETTING.match(source), f"{service_name}: {source} is not a single ${{MDDOAI_..._DIR:?}} setting"


def _template_settings(template: Path) -> set[str]:
    names = set()
    for line in template.read_text(encoding="utf-8").splitlines():
        line = line.strip()
        if line and not line.startswith("#") and "=" in line:
            names.add(line.split("=", 1)[0].strip())
    return names


@pytest.mark.parametrize("template", sorted(TEMPLATES_DIR.glob("*/config.env")), ids=lambda path: path.parent.name)
def test_every_template_asks_for_every_setting_compose_requires(template):
    required = set(REQUIRED_SETTING.findall(COMPOSE_FILE.read_text(encoding="utf-8")))

    missing = required - _template_settings(template)

    assert not missing, f"{template.parent.name}/config.env does not ask for: {sorted(missing)}"


def _services_whose_code_requires_settings() -> dict[str, set[str]]:
    """The settings each service's code requires. A service's folder is named like its
    compose service, with underscores for hyphens."""
    found = {}
    for folder in sorted(AI_DIR.iterdir()):
        if not folder.is_dir():
            continue
        names = set()
        for path in folder.rglob("*.py"):
            if {"tests", ".venv", "__pycache__", "node_modules"} & set(path.parts):
                continue
            names.update(REQUIRED_IN_CODE.findall(path.read_text(encoding="utf-8")))
        if names:
            found[folder.name.replace("_", "-")] = names
    return found


def test_there_are_services_whose_code_requires_settings():
    assert _services_whose_code_requires_settings()


@pytest.mark.parametrize("service_name,required", sorted(_services_whose_code_requires_settings().items()), ids=lambda argument: str(argument))
def test_compose_gives_each_service_every_setting_its_code_requires(service_name, required):
    assert service_name in _services(), f"{service_name} has code that requires settings but no compose service"

    missing = required - set(_environment(_services()[service_name]))

    assert not missing, f"{service_name} is not given: {sorted(missing)}"


def test_every_mount_from_a_host_setting_is_written_in_long_form():
    short = [
        (name, volume)
        for name, service in _services().items()
        for volume in service.get("volumes", [])
        if isinstance(volume, str) and "${MDDOAI_" in volume
    ]

    assert not short, f"write these as a mapping, so the checks can read them: {short}"


@pytest.mark.parametrize("service_name,source", _mounts_matching(MUST_HOLD_CONTENT_SETTING), ids=lambda argument: str(argument))
def test_a_folder_that_must_already_hold_content_is_never_created_by_docker(service_name, source):
    mount = next(v for v in _services()[service_name]["volumes"] if isinstance(v, dict) and v.get("source") == source)

    assert mount.get("bind", {}).get("create_host_path") is False, f"{service_name}: {source} must set create_host_path: false"


@pytest.mark.parametrize("service_name,source", _mounts_matching(READ_ONLY_FOLDER_SETTING), ids=lambda argument: str(argument))
def test_the_workspace_and_the_platforms_are_mounted_read_only(service_name, source):
    mount = next(v for v in _services()[service_name]["volumes"] if isinstance(v, dict) and v.get("source") == source)

    assert mount.get("read_only") is True, f"{service_name}: {source} must be read_only"


def test_no_host_setting_is_passed_into_a_container_except_the_display_name():
    carried = {
        (name, setting): value
        for name, service in _services().items()
        for setting, value in _environment(service).items()
        if "MDDOAI_" in value
    }

    assert carried == ALLOWED_HOST_VALUE_IN_ENVIRONMENT, f"unexpected host settings in a container: {carried}"


@pytest.mark.parametrize("service_name", sorted(_services()))
def test_no_config_env_folder_of_a_service_is_inside_another_of_its_folders(service_name):
    targets = [
        volume["target"]
        for volume in _services()[service_name].get("volumes", [])
        if isinstance(volume, dict) and volume.get("source", "").startswith("${MDDOAI_")
    ]

    nested = [(a, b) for a in targets for b in targets if a != b and _is_at_or_under(a, b)]

    assert not nested, f"{service_name}: {nested}"


def _env_file_entries() -> list[tuple[str, str, bool]]:
    """Every env_file of every service as (service, path, required)."""
    entries = []
    for name, service in _services().items():
        env_files = service.get("env_file", [])
        if isinstance(env_files, (str, dict)):
            env_files = [env_files]
        for entry in env_files:
            if isinstance(entry, str):
                entries.append((name, entry, True))
            else:
                entries.append((name, entry["path"], entry.get("required", True)))
    return entries


def test_the_ai_layers_keys_file_is_its_own_required_setting_and_must_exist():
    entries = [entry for entry in _env_file_entries() if entry[0] == "ai-layer"]

    assert len(entries) == 1, entries
    _, path, required = entries[0]
    assert HOST_FOLDER_SETTING.match(path), f"{path} is not a single ${{MDDOAI_..:?}} setting"
    assert required is True, "the keys file must exist: the service cannot start without keys"


def test_no_host_setting_is_used_for_two_things():
    settings = [HOST_FOLDER_SETTING.match(source).group(1) for _, source in _host_folder_mounts() if HOST_FOLDER_SETTING.match(source)]
    settings += [HOST_FOLDER_SETTING.match(path).group(1) for _, path, _ in _env_file_entries() if HOST_FOLDER_SETTING.match(path)]

    assert len(settings) == len(set(settings)), f"shared: {sorted(s for s in set(settings) if settings.count(s) > 1)}"


@pytest.mark.parametrize("service_name,required", sorted(_services_whose_code_requires_settings().items()), ids=lambda argument: str(argument))
def test_each_required_location_is_the_target_of_its_own_host_folder_mount(service_name, required):
    service = _services()[service_name]
    targets = [
        volume["target"]
        for volume in service.get("volumes", [])
        if isinstance(volume, dict) and volume.get("source", "").startswith("${MDDOAI_")
    ]
    values = {
        setting: _environment(service).get(setting)
        for setting in required
        if (service_name, setting) not in ALLOWED_HOST_VALUE_IN_ENVIRONMENT
    }

    wrong = {setting: value for setting, value in values.items() if value not in targets}

    assert not wrong, f"{service_name}: not the target of one of its host folder mounts: {wrong}"
    assert len(set(values.values())) == len(values), f"{service_name}: two settings name the same folder: {values}"
