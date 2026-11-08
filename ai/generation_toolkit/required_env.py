"""Reading a required setting, for the stage agents' own settings modules."""
import os


def required_env(name: str) -> str:
    """A required setting. Unset or empty stops the service."""
    value = os.environ[name].strip()
    if not value:
        raise KeyError(f"{name} is set but empty")
    return value
