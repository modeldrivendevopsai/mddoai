"""Reading a setting that has no default. The environment must provide it: a service
that starts without it stops, naming the setting, instead of quietly using a value
from the code. Unset raises a KeyError naming the setting, and empty or blank says
so, the same as every other service's own copy of this."""
import os


def required_env(name: str) -> str:
    value = os.environ[name].strip()
    if not value:
        raise KeyError(f"{name} is set but empty")
    return value
