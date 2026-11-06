# ai/data

A folder for the files the stack reads and keeps, one folder per kind. Nothing
requires you to use it: every folder is its own required setting in the
environment's `config.env` (see [ai/README.md](../README.md)'s "Where files
live"), so each can be anywhere. This is a layout to follow, and the starting
content some folders need.

```
platforms/             the platforms the generator generates for (a starting platform ships here)
prompts/<agent>/       each prompt agent's prompt configs (starting configs ship here)
attachments/<agent>/   files uploaded to an agent
workspace/             the models you pick from in "Generate a CI/CD pipeline"
runs/                  every platform integration run
validator-output/      the validators' compiled output
pipelines/             the pipelines generated so far
```

Git tracks the platform files (a platform you add under `platforms` is yours to
commit) and the starting prompt configs; `.gitignore` in this folder says exactly
which. Everything else here is yours or the stack's, and is ignored. To keep your
files elsewhere, point the settings somewhere else and copy the starting content
you want into it.
