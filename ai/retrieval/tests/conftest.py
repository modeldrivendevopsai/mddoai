"""Pytest setup for this suite: the clients need their links, which a service takes from its environment."""
import clients.testing_urls  # noqa: F401

# Settings a service takes from its environment, which Docker Compose sets in a container.
import os

os.environ.setdefault("LOG_LEVEL", "INFO")
