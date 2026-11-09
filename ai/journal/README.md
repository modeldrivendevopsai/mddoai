# journal

How a service keeps a long history on disk, so that adding to the history writes the new part and not the whole history again.

It is a shared package, not a service: no port, no Dockerfile, no compose entry. A service that uses it copies it into its image.

## `History`

Many histories, one journal each. A history is a list of events and some state fields, such as a run or a chat.

- `save(key, events, state)` adds what is new since the last save: the state fields that changed, then the events after the ones already kept. A key not kept yet, or whose file has vanished, is written whole. If a save fails, the key is written whole at its next save, because what reached the disk is unknown.
- `save_quietly(key, events, state)` is `save` for a caller that must go on if the disk fails. The failure is logged, once until a save works again.
- `load_all(keys, build)` reads the journals and calls `build(key, events, state)` for each. `build` raises `ValueError` for a journal that is not what the caller keeps. A journal that cannot be read or built is skipped with a log line.

A key becomes a file or folder name, so it must be plain: letters, digits, `_` and `-`, up to 64 characters.

The caller makes sure two saves of one key never overlap. `History` knows nothing about what an event or a state field means, or where a key's journal lives: the caller passes `path_for(key)`. It does not know about any data root. Each caller is given its own folder.

## `Journal`

One file of JSON records, one per line, that only grows. `History` is built on it.

- `append(records)` adds records to the end in one write.
- `rewrite(records)` replaces the file with `records`, through a temp file, so it is never half written.
- `read()` returns the records and whether the file ended cleanly. A crash can cut the last line short. That line is left out, and the caller should `rewrite` the file before it appends again. A bad line anywhere else raises `ValueError`.

## Direction

Callers go through `History`, never through a file path. Run history may later be queried across runs, such as to show forks or find patterns, and the storage behind `History` may move to another kind of store. That swap, or putting the store behind a service, should not change a caller.

Run its tests with `pytest` in this folder.
