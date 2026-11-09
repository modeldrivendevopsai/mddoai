# journal

A file of JSON records, one per line, that only grows. A service that keeps a long history on disk uses it, so that adding to the history writes the new records and not the whole history again.

It is a shared package, not a service: no port, no Dockerfile, no compose entry. A service that uses it copies it into its image.

- `append(records)` adds records to the end in one write.
- `rewrite(records)` replaces the file with `records`, through a temp file, so it is never half written.
- `read()` returns the records and whether the file ended cleanly. A crash can cut the last line short. That line is left out, and the caller should `rewrite` the file before it appends again. A bad line anywhere else raises `ValueError`.

What a record means, and what to do when a write fails, is up to the service.

Run its tests with `pytest` in this folder.
