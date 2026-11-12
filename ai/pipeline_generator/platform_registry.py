"""The platforms this service generates for, as the journal keeps them.

Generation uses each platform's latest version. The journal is asked on every
call, so a platform published or purged applies to the next generation, and
this service holds no copy of the platform files.
"""
import logging
from dataclasses import dataclass

import httpx

from clients import journal_client
from clients.agent_service_errors import AgentServiceError
from platforms import Platform

logger = logging.getLogger(__name__)


class PlatformsUnavailable(Exception):
    """The journal, which keeps the platforms, cannot be reached or answered
    with something unusable. The message is for the service log."""


@dataclass(frozen=True)
class Artifacts:
    metamodel: str
    transformation: str
    template: str


def _platform(raw: dict) -> Platform:
    return Platform(
        id=raw["id"],
        name=raw["name"],
        version=raw["version"],
        output_filename=raw["output_filename"],
        output_model_name=raw["output_model_name"],
    )


class PlatformRegistry:
    def latest(self) -> list[Platform]:
        """Every platform's latest version, by name. A platform the journal
        describes in a way this service cannot use is skipped and logged."""
        found = []
        for raw in self._ask(journal_client.list_platforms):
            try:
                found.append(_platform(raw))
            except (KeyError, ValueError) as e:
                logger.warning("ignoring platform %r: %s", raw.get("id"), e)
        return found

    def listing(self) -> list[dict]:
        """What the platform list shows for each platform's latest version: its
        id, name, output file, version, every version it has, and whether it
        was published (and so can be purged) and not shipped with the stack."""
        return self._ask(self._listing)

    @staticmethod
    def _listing() -> list[dict]:
        return [
            {
                "id": raw["id"],
                "name": raw["name"],
                "output_filename": raw["output_filename"],
                "version": raw["version"],
                "versions": raw["versions"],
                "published": raw["published"],
            }
            for raw in journal_client.list_platforms()
        ]

    def get(self, platform_id: str) -> Platform | None:
        """A platform's latest version, or None when there is no such platform."""
        raw = self._ask(journal_client.get_platform, platform_id)
        if raw is None:
            return None
        try:
            return _platform(raw)
        except (KeyError, ValueError) as e:
            logger.warning("ignoring platform %r: %s", platform_id, e)
            return None

    def artifacts(self, platform: Platform) -> Artifacts:
        """The three files of the platform's version. Raises ValueError when
        that version has been purged since the platform was looked up."""
        raw = self._ask(journal_client.get_platform, platform.id, platform.version)
        if raw is None:
            raise ValueError(f"platform {platform.id} version {platform.version} no longer exists")
        files = raw["files"]
        return Artifacts(files["metamodel"], files["transformation"], files["template"])

    def pim_transformation(self) -> str:
        """The SWArch-to-PIM transformation, as ATL source text."""
        return self._ask(journal_client.get_pim_transformation)

    @staticmethod
    def _ask(call, *args):
        try:
            return call(*args)
        except (httpx.HTTPError, AgentServiceError, KeyError, TypeError, ValueError) as e:
            raise PlatformsUnavailable(f"the journal could not answer: {e}") from e
