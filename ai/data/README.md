# ai/data

A folder for the files the stack reads and keeps, laid out as the inputs people
supply or configure and the outputs the services generate. Nothing requires you
to use it: every folder is its own required setting in the environment's
`config.env` (see [ai/README.md](../README.md)'s "Where files live"), so each can
be anywhere. This is a layout to follow, and the starting content some inputs
need.

```
input/
  platforms/       the platforms the generator generates for (shipped here)
  prompts/<agent>/ each prompt agent's prompt configs (starting configs shipped here)
  attachments/<agent>/   files uploaded to an agent
  workspace/       the models you pick from in "Generate a CI/CD pipeline"
output/
  runs/            every platform integration run
  validator-output/   the validators' compiled output
  pipelines/       the pipelines generated so far
```

Git tracks the platform files (a platform you add under `input/platforms` is yours to
commit) and the starting prompt configs; `.gitignore` in this folder says exactly which. Everything else here is yours or
the stack's, and is ignored. To keep your files elsewhere, point the settings
somewhere else and copy the starting content you want into it.
