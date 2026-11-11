"""The stores the routes depend on, built once from the settings. Routes ask for
them through FastAPI's dependency injection, so a test can point them at
temporary folders."""
import shutil
from dataclasses import dataclass
from pathlib import Path

from . import settings
from .attempts import AttemptStore
from .histories import HistoryStore
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


def _run_keys(runs_dir: Path) -> list[str]:
    if not runs_dir.is_dir():
        return []
    return [folder.name for folder in runs_dir.iterdir() if (folder / _RUN_JOURNAL).is_file()]


def _chat_keys(chats_dir: Path) -> list[str]:
    if not chats_dir.is_dir():
        return []
    return [path.name.removesuffix(_CHAT_SUFFIX) for path in chats_dir.glob(f"*{_CHAT_SUFFIX}")]


def build_stores(runs_dir: Path, chats_dir: Path, platforms_dir: Path, prompts_dir: Path, attachments_dir: Path) -> Stores:
    return Stores(
        histories={
            # A run is a folder: its journal and the files of its attempts, so a run goes whole.
            RUNS: HistoryStore(
                lambda key: runs_dir / key / _RUN_JOURNAL,
                lambda: _run_keys(runs_dir),
                lambda key: shutil.rmtree(runs_dir / key),
            ),
            CHATS: HistoryStore(lambda key: chats_dir / f"{key}{_CHAT_SUFFIX}", lambda: _chat_keys(chats_dir)),
        },
        attempts=AttemptStore(runs_dir),
        platforms=PlatformStore(platforms_dir),
        prompt_configs=PromptConfigStore(prompts_dir, settings.MAX_PROMPT_VERSIONS),
        uploads=UploadStore(attachments_dir, settings.MAX_UPLOAD_BYTES),
    )


_stores: Stores | None = None


def get_stores() -> Stores:
    global _stores
    if _stores is None:
        _stores = build_stores(
            settings.RUNS_DIR, settings.CHATS_DIR, settings.PLATFORMS_DIR, settings.PROMPTS_DIR, settings.ATTACHMENTS_DIR
        )
    return _stores


def use_folders(runs_dir: Path, chats_dir: Path, platforms_dir: Path, prompts_dir: Path, attachments_dir: Path) -> None:
    """Keeps everything in these folders from now on, with nothing remembered
    from before. For tests."""
    global _stores
    _stores = build_stores(runs_dir, chats_dir, platforms_dir, prompts_dir, attachments_dir)
