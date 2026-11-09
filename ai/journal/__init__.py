"""An append-only file of JSON records, one per line.

Adding a record writes only that record, so keeping a long history costs what
the new record costs. A crash can leave the last line half written. Reading
leaves such a line out and says so, so the caller can write the file again whole.
"""
import json
import os
import uuid
from pathlib import Path


def _line(record: dict) -> str:
    # Values JSON cannot hold are written as text, so one odd value never
    # stops a record from being kept. Characters are escaped, so any text
    # can be written.
    return json.dumps(record, default=str) + "\n"


class Journal:
    def __init__(self, path: Path):
        self.path = path

    def append(self, records: list[dict]) -> None:
        """Adds records to the end of the file, in one write."""
        if not records:
            return
        self.path.parent.mkdir(parents=True, exist_ok=True)
        with open(self.path, "ab") as handle:
            handle.write("".join(_line(record) for record in records).encode("utf-8"))

    def rewrite(self, records: list[dict]) -> None:
        """Replaces the file with records. They are written to a temp file that
        is renamed into place, so the file is never half written."""
        self.path.parent.mkdir(parents=True, exist_ok=True)
        temporary = self.path.with_name(f".{self.path.name}.{uuid.uuid4().hex}")
        try:
            temporary.write_text("".join(_line(record) for record in records), encoding="utf-8", newline="\n")
            os.replace(temporary, self.path)
        except BaseException:
            temporary.unlink(missing_ok=True)
            raise

    def read(self) -> tuple[list[dict], bool]:
        """The records, and whether the file ended cleanly. A last line cut short
        by a crash is left out, and the answer is False. A bad line anywhere
        else raises ValueError."""
        text = self.path.read_text(encoding="utf-8")
        lines = text.split("\n")
        clean = lines.pop() == ""
        records = []
        for number, line in enumerate(lines, start=1):
            if not line:
                continue
            try:
                record = json.loads(line)
            except ValueError as e:
                raise ValueError(f"line {number} is not JSON") from e
            if not isinstance(record, dict):
                raise ValueError(f"line {number} is not an object")
            records.append(record)
        return records, clean
