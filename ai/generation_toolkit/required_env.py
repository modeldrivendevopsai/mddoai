"""Reading a required setting, for the stage agents' own settings modules."""
import os


def required_env(name: str) -> str:
    """The value of a required setting. Unset or empty stops the service: an
    empty folder setting would otherwise be read as the current folder."""
    value = os.environ[name].strip()
    if not value:
        raise KeyError(f"{name} is set but empty")
    return value
