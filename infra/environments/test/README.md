# Test environment

A second copy of the `ai/` stack on the VM, for trying the latest
merged code in a browser without SSH. It runs the newest commit of `main` at
`PUBLIC_HOSTNAME`, and only the GitHub users listed in `GITHUB_USERS` get past
the login. Both are set in its `config.env`, which stays on the VM like every
environment's (see [Environments](../../README.md#environments)).

This folder is a copy of `templates/public/`, plus this README. Setup stage
`60-test.sh` installs the Test environment in `/opt/mddoai-test` on the VM,
switched off: a clone of the repository, plus an updater and its timer from
`infra/vm/setup/files/test-updater/`.
The [VM's README](../../vm/README.md) covers the VM itself and the setup
script.

## How it works

A request travels like this:

1. The browser goes to Cloudflare, which owns the name.
2. Cloudflare sends it down a tunnel that `cloudflared` on the VM opened outward, so no port is open on the VM.
3. `oauth2-proxy` checks the login cookie. Without one it sends the visitor to GitHub to log in, and lets them in only if their GitHub username is on the list.
4. The stack's `ui-host` serves the app, the same way the dev environment's does.

On the VM, everything lives in `/opt/mddoai-test`, owned by `dev`:

```
/opt/mddoai-test/
  update.sh      pulls main, builds, starts
  deployed       the commit that runs
  repo/          the clone the stack runs from
    infra/environments/test/config.env   settings and secrets, gitignored
    ai/ai-layer/.env                     the LLM API keys, gitignored
```

Edit nothing in the clone except those gitignored files: every update
replaces the rest with `main`. A fresh clone starts without them, so run setup
again and fill them in.

- **One Compose project, `test`,** named after this folder and run from it in the clone. `compose.yaml` pulls in the repository's `ai/docker-compose.yml` and this folder's `gateway.yaml`, both filled in from `config.env`. `gateway.yaml` only adds `cloudflared` and `oauth2-proxy`; it changes nothing in the stack. The UI and `ai-layer` ports are on `127.0.0.1` only, next to the dev environment's. The gateway publishes no port.
- **The secrets** (tunnel token, OAuth client secret, cookie secret) are in `config.env`, readable by `dev` only. Anyone with root can still read them.
- **Its data** is separate from the dev environment's, and everyone on the list shares it. Integration runs and build caches are in the project's own Docker volumes. Uploaded attachments are in the clone, in each agent's `attachments/` folder under `ai/`, which is gitignored.
- **The updater:** `mddoai-test-update.timer` runs `update.sh` as `dev` shortly after boot, then a few minutes after each run ends. Each run:
  1. fetches the repository;
  2. stops there if `main` has no new commit and the stack is up;
  3. otherwise checks out the newest commit, rebuilds when it is new, and starts the stack.

  A run that cannot reach GitHub changes nothing. If a build fails, the previous containers keep running and the clone stays on their commit. If the start fails, some containers may already be replaced, and the clone goes back to the previous commit. Either way, every run after that tries again until a new commit arrives, which costs CPU on the shared VM.
- **Prompt edits are reset.** Prompt changes saved through the app's UI are written into the clone, so each new commit replaces them with the version in `main`.

The tunnel's routes are set in the Cloudflare dashboard, not in this folder,
and `cloudflared` can reach any port listening on the VM's own addresses, not
only those Docker publishes (sshd too). So a second route added there would
serve that port to the internet with no login. Two things keep that from
mattering:

- The tunnel has exactly one route, to `oauth2-proxy` ("Setting it up", step 2). Only people who manage the domain on Cloudflare can change it.
- Nothing worth reaching listens on all addresses: every environment's `config.env` keeps `ai-layer` on `127.0.0.1` (`AI_LAYER_PORT`), and the UI is always on `127.0.0.1`.

To try a branch before it is merged, run it on the dev environment instead.

## Setting it up

Done once, by people with access to each service. First choose
`PUBLIC_HOSTNAME`, the name it will be served under, on a domain managed in
Cloudflare. Steps 1 and 2 can then happen in either order.

1. **GitHub** (anyone with a GitHub account). Create an OAuth app:
   1. On GitHub, open your profile picture, then Settings, then Developer settings, then OAuth Apps, and register a new app. It can belong to a personal account, and no organization has to approve it. At login it asks each user for their email address and read-only access to their organizations, which `oauth2-proxy` always reads.
   2. Homepage URL: `https://<PUBLIC_HOSTNAME>`. Authorization callback URL: `https://<PUBLIC_HOSTNAME>/oauth2/callback`. Leave wildcard matching off.
   3. Generate a client secret. Keep it and the client ID for step 3; the secret is shown only once.
2. **Cloudflare** (someone who manages the domain's zone). Create a tunnel:
   1. In the Cloudflare dashboard, open Networking, then Tunnels, and create a tunnel.
   2. The dashboard shows an install command for a connector. Copy only the token from it, the long string; keep it for step 3. Do not run the command: the VM runs its own connector.
   3. On the tunnel's Routes tab, add one route of type Published application: subdomain and domain making up `<PUBLIC_HOSTNAME>`, service URL `http://oauth2-proxy:4180`. Add no other routes: everything the tunnel serves must go through the login.
   4. Under the domain's DNS records, check that there is a CNAME record for that name pointing to `<tunnel ID>.cfargotunnel.com`, and add it if not. Do not change any other record.
3. **On the VM** (`dev`, for example in VS Code with `/opt/mddoai-test` open):
   1. Fill in every line of `repo/infra/environments/test/config.env`, following its comments. Setup copied it from `templates/public/` with a fresh cookie secret.
      - `UI_PORT` and `AI_LAYER_PORT` must differ from the dev environment's.
      - `TUNNEL_TOKEN` is the tunnel token, `OAUTH2_CLIENT_ID` and `OAUTH2_CLIENT_SECRET` come from the OAuth app.
      - `GITHUB_USERS` holds the GitHub usernames that may log in, exactly as on their profiles (same capitals), separated by commas without spaces.
   2. In `repo/ai/ai-layer/.env`, fill in the LLM API keys. To use the same keys as the dev environment: `cp ~/mddoai/ai/ai-layer/.env /opt/mddoai-test/repo/ai/ai-layer/.env`.
4. Start it:

   ```sh
   sudo systemctl enable --now mddoai-test-update.timer
   journalctl -fu mddoai-test-update     # the first build takes several minutes
   ```

5. Open `https://<PUBLIC_HOSTNAME>` in a private window. GitHub asks you to authorize the app once; skip its organization "Request" and "Grant" buttons. A GitHub account that is not on the list must be refused.

## Day-to-day

Run `docker compose` commands by hand only in the Test clone's copy of this
folder, `/opt/mddoai-test/repo/infra/environments/test`. The same folder in
another clone names the same Compose project, so commands there would act on
the Test environment.

- **What it did:** `journalctl -u mddoai-test-update`. `cat /opt/mddoai-test/deployed` is the commit it runs. To pick up a new commit at once instead of at the next check: `sudo systemctl start mddoai-test-update`.
- **Switch it off:**

  ```sh
  sudo systemctl disable --now mddoai-test-update.timer
  sudo systemctl stop mddoai-test-update        # a run in progress
  cd /opt/mddoai-test/repo/infra/environments/test && docker compose down
  ```

  Start it again with `sudo systemctl enable --now mddoai-test-update.timer`. Running the setup script again never switches it on or off.
- **Updater changed** (`infra/vm/setup/files/test-updater/`): run the setup script again.
- **Change a setting or rotate a secret:** edit `config.env` in the clone's copy of this folder, then run `docker compose up -d` there. It recreates the containers that use it.
  - A new tunnel token comes from the tunnel's page in the Cloudflare dashboard, and a new client secret from the OAuth app's page on GitHub.
  - Make a new cookie secret with `openssl rand -base64 32 | tr -- '+/' '-_'`. It logs everyone out.
  - For the LLM keys, edit `/opt/mddoai-test/repo/ai/ai-layer/.env` the same way.
- **Files in this folder changed:** `compose.yaml` and `gateway.yaml` are read from the clone, so they follow `main`. A new setting in `templates/public/config.env` must be added to `config.env` by hand.
- **Add or remove a user:** change `GITHUB_USERS` in `config.env`, like any other setting.
  - `oauth2-proxy` checks the list at login only, so someone removed keeps access until their cookie expires (`OAUTH2_COOKIE_EXPIRE`). To end it sooner, make a new cookie secret once the removal is running.
  - Access is by username. When someone on the list renames or deletes their GitHub account, remove the old name at once: GitHub lets anyone claim a freed name.
  - A user needs a verified primary email address on GitHub, or the login refuses them.
  - Whoever owns the OAuth app can rotate or delete its secret, which stops all logins until `config.env` has the new one. They cannot let anyone in.
- **Memory:** the Test environment needs about as much as the dev environment at idle; check with `docker stats`.
- **Disk:** after each build, `update.sh` removes the untagged images that rebuilds leave behind, the dev environment's too. Docker's build cache, shared with the dev environment, still grows; check with `docker system df`.
