"""Keeping a long history on disk without writing it all again at every change."""
from .file import Journal
from .history import History

__all__ = ["Journal", "History"]
