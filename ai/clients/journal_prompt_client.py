"""The journal's prompt configs and uploaded attachments, for the agents that write
prompts. Each is kept under the agent's own name, such as "psm", so one journal serves
every agent and none sees another's.

What a config means, and whether it is valid, is the agent's to say: the journal keeps
what it is given, as versions. Where something may be missing, such as a config nobody
saved or constraints never kept, the answer is None and not an error.

A request the journal refuses raises AgentServiceError with its reason, as in journal_client.
"""
from clients.agent_service_errors import AgentServiceError
from clients.journal_client import _request, _seg

# A config can carry whole documentation dumps, and a save writes a few files.
CONFIG_TIMEOUT = 30.0
# An upload is a file of up to a few MB.
UPLOAD_TIMEOUT = 30.0


def _or_none(method: str, path: str, timeout: float = CONFIG_TIMEOUT, body: dict | None = None):
    """The answer's JSON, or None when the journal has nothing there."""
    try:
        return _request(method, path, timeout, body).json()
    except AgentServiceError as e:
        if e.status_code == 404:
            return None
        raise


def _config_path(agent: str, name: str) -> str:
    return f"/prompt-configs/{_seg(agent)}/{_seg(name)}"


def get_config(agent: str, name: str) -> dict | None:
    """The live config, or the shipped one when none was saved. None when there is neither."""
    return _or_none("GET", _config_path(agent, name))


def get_shipped_config(agent: str, name: str) -> dict | None:
    return _or_none("GET", f"{_config_path(agent, name)}/shipped")


def save_config(agent: str, name: str, config: dict) -> dict:
    """Keeps the config as a new version and returns it with its version. A config whose
    attachments are the live one's changes nothing, and the live one comes back."""
    return _request("PUT", _config_path(agent, name), CONFIG_TIMEOUT, {"config": config}).json()


def list_versions(agent: str, name: str) -> tuple[list[str], bool]:
    """The saved versions' ids, newest first, and whether a shipped config exists."""
    answer = _request("GET", f"{_config_path(agent, name)}/versions", CONFIG_TIMEOUT).json()
    return answer["versions"], answer["has_shipped"]


def get_version(agent: str, name: str, version: str) -> dict | None:
    return _or_none("GET", f"{_config_path(agent, name)}/versions/{_seg(version)}")


def get_constraints(agent: str, name: str) -> list[str] | None:
    """The learned constraints, or None when none were ever kept."""
    answer = _or_none("GET", f"{_config_path(agent, name)}/constraints")
    return None if answer is None else answer["constraints"]


def set_constraints(agent: str, name: str, constraints: list[str]) -> None:
    _request("PUT", f"{_config_path(agent, name)}/constraints", CONFIG_TIMEOUT, {"constraints": constraints})


def save_upload(agent: str, name: str, content: str) -> None:
    """Keeps an uploaded file under a name that is not taken. Refused with 409 when it is,
    and with 413 when the file is over the size an upload may be."""
    _request("PUT", f"/uploads/{_seg(agent)}/{_seg(name)}", UPLOAD_TIMEOUT, {"content": content})


def list_uploads(agent: str) -> list[str]:
    return _request("GET", f"/uploads/{_seg(agent)}", CONFIG_TIMEOUT).json()["files"]


def get_upload(agent: str, name: str) -> str | None:
    """The uploaded file's text, or None when there is no such file."""
    answer = _or_none("GET", f"/uploads/{_seg(agent)}/{_seg(name)}")
    return None if answer is None else answer["content"]
