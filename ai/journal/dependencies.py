"""The stores the routes depend on, built once from the settings. Routes ask for
them through FastAPI's dependency injection, so a test can point them at
temporary folders."""
import shutil
from dataclasses import dataclass
from pathlib import Path
from typing import NamedTuple

from . import settings
from .attempts import AttemptStore
from .histories import HistoryStore
from .pipelines import PipelineStore
from .platforms import PlatformStore
from .prompt_configs import PromptConfigStore
from .uploads import UploadStore

# The kinds of history, each in its own folder.
RUNS = "runs"
CHATS = "chats"

_RUN_JOURNAL = "run.jsonl"
_CHAT_SUFFIX = ".jsonl"


@dataclass
class Stores:
    histories: dict[str, HistoryStore]
    attempts: AttemptStore
    platforms: PlatformStore
    prompt_configs: PromptConfigStore
    uploads: UploadStore
    pipelines: PipelineStore


def _run_keys(runs_dir: Path) -> list[str]:
    if not runs_dir.is_dir():
        return []
    return [folder.name for folder in runs_dir.iterdir() if (folder / _RUN_JOURNAL).is_file()]


def _chat_keys(chats_dir: Path) -> list[str]:
    if not chats_dir.is_dir():
        return []
    return [path.name.removesuffix(_CHAT_SUFFIX) for path in chats_dir.glob(f"*{_CHAT_SUFFIX}")]


class Folders(NamedTuple):
    """The folders the journal keeps its files in, one for each kind."""

    runs: Path
    chats: Path
    platforms: Path
    prompts: Path
    attachments: Path
    pipelines: Path

    @classmethod
    def under(cls, base: Path) -> "Folders":
        """One folder of each kind inside `base`, named for the kind. For tests."""
        return cls(*(base / name for name in cls._fields))


def build_stores(folders: Folders) -> Stores:
    return Stores(
        histories={
            # A run is a folder: its journal and the files of its attempts, so a run goes whole.
            RUNS: HistoryStore(
                lambda key: folders.runs / key / _RUN_JOURNAL,
                lambda: _run_keys(folders.runs),
                lambda key: shutil.rmtree(folders.runs / key),
            ),
            CHATS: HistoryStore(
                lambda key: folders.chats / f"{key}{_CHAT_SUFFIX}", lambda: _chat_keys(folders.chats)
            ),
        },
        attempts=AttemptStore(folders.runs),
        platforms=PlatformStore(folders.platforms),
        prompt_configs=PromptConfigStore(folders.prompts, settings.MAX_PROMPT_VERSIONS),
        uploads=UploadStore(folders.attachments, settings.MAX_UPLOAD_BYTES),
        pipelines=PipelineStore(folders.pipelines, settings.MAX_PIPELINES),
    )


_stores: Stores | None = None


def get_stores() -> Stores:
    global _stores
    if _stores is None:
        _stores = build_stores(
            Folders(
                settings.RUNS_DIR,
                settings.CHATS_DIR,
                settings.PLATFORMS_DIR,
                settings.PROMPTS_DIR,
                settings.ATTACHMENTS_DIR,
                settings.PIPELINES_DIR,
            )
        )
    return _stores


def use_folders(folders: Folders) -> None:
    """Keeps everything in these folders from now on, with nothing remembered
    from before. For tests."""
    global _stores
    _stores = build_stores(folders)
