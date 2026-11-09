# ai/data

The starting platforms and prompt configs, and a layout for the stack's other folders. Each folder is its own setting in `config.env` (see [Where files live](../README.md#where-files-live)), so it can be anywhere.

```
platforms/             the platforms, a folder per version such as 1.2.0 (a starting platform ships here)
prompts/<agent>/       each prompt agent's prompt configs (starting configs ship here)
attachments/<agent>/   files uploaded to an agent
workspace/             the models you pick from
runs/                  every platform integration run
chats/                 the chat of each run
validator-output/      the validators' compiled output
pipelines/             the pipelines generated so far
```

Git tracks the platform files and the starting prompt configs, as `.gitignore` here says. Everything else here is git-ignored.
