# Infrastructure

- `vm/` is the development VM on Hetzner Cloud: Terraform to create it, the setup script, and the script that connects your PC. See [vm/README.md](vm/README.md).
- `environments/` holds one folder per copy of the app, and `environments/templates/`, the two templates they are made from.

## Environments

Every environment runs the same stack, `ai/docker-compose.yml`, which is never
copied. Each environment's folder is a copy of one of the two templates in
`templates/`:

- `templates/dev/` is for an environment reached only from its own machine. `dev/`, a developer's own stack on the VM or a PC, is a copy of it.
- `templates/public/` is for an environment served under a public name behind a GitHub login. It adds `gateway.yaml`, the two containers that let listed users in. `test/` is a copy of it; see [environments/test/README.md](environments/test/README.md).

In each folder:

- `compose.yaml` pulls in the stack, filled in from `config.env`.
- `config.env` holds everything that belongs to that environment: ports, host names, who can log in, and its secrets. Only the templates' copies, with no values, are committed. Each environment's own `config.env` is gitignored and stays on the machine that runs it.

The stack also reads some services' own `.env` files under `ai/` in each
clone, such as `ai/ai-layer/.env` with the LLM API keys. Each service's
`.env.example` says what goes in it.

To add an environment, copy the template that fits, name the copy after the
environment, fill in its `config.env` and commit its other files. A change to
a template's other files must be copied into the environments made from it.

Run `docker compose` inside an environment's folder. The Compose project is
named after the folder, so its containers, networks and volumes are separate
from every other environment's. Two clones on one machine share the project,
so run the Test environment's commands only in its own clone (see its README).
The dev environment:

```sh
cd infra/environments/dev
cp ../templates/dev/config.env config.env    # once, then fill it in
docker compose up -d --build
```

On the VM, its UI port is forwarded to your PC while you are connected (see
"Opening the app" in the VM's README).

A change to `config.env` is made on the machine, then applied with
`docker compose up -d` in the environment's folder.
