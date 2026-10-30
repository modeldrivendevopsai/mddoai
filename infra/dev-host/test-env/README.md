# Test environment

A second copy of the `ai/` stack on the dev host VM, for trying the latest
merged code in a browser without SSH. It runs the newest commit of `main` at
`TEST_HOSTNAME`, and only the GitHub users listed in `GITHUB_USERS` get past
the login. Both are set in its `.env`.

Setup stage `60-test-env.sh` installs it from this folder into
`/opt/mddoai-test` on the VM, switched off. The [dev host's README](../README.md)
covers the VM itself and the setup script.

## How it works

A request travels like this:

1. The browser goes to Cloudflare, which owns the name.
2. Cloudflare sends it down a tunnel that `cloudflared` on the VM opened outward, so no port is open on the VM.
3. `oauth2-proxy` checks the login cookie. Without one it sends the visitor to GitHub to log in, and lets them in only if their GitHub username is on the list.
4. The stack's `ui-host` serves the app, the same way the dev stack's does.

On the VM, everything lives in `/opt/mddoai-test`, owned by `dev`:

```
/opt/mddoai-test/
  .env           settings and secrets: host name, user list, tokens, ports
  ai-layer.env   the LLM API keys
  compose.yaml   the gateway: cloudflared and oauth2-proxy
  update.sh      pulls main, builds, starts
  repo/          the clone the stack runs from; never edit it by hand
  deployed       the commit that runs
```

- **One Compose project, `ai-test`,** run from `repo/ai`. It is the repository's `ai/` stack plus the gateway from `compose.yaml`. Its UI and `ai-layer` ports are on `127.0.0.1` only, next to the dev stack's. The gateway publishes no port.
- **`.env` is linked as `repo/ai/.env`**, so any `docker compose` command run in `/opt/mddoai-test/repo/ai` acts on the Test environment, never on the dev stack. `ai-layer.env` is linked as `repo/ai/ai-layer/.env`.
- **Its data** (runs, pipelines, platforms) is in `repo/ai/data`, separate from the dev stack's. Everyone on the list shares it.
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
- Nothing worth reaching listens on all addresses: each stack's `AI_LAYER_PORT` in its `ai/.env` keeps `ai-layer` on `127.0.0.1`, and the UI always is.

To try a branch before it is merged, run it on the dev environment instead.

## Setting it up

Done once, by people with access to each service. Steps 1 and 2 can happen in
either order.

1. **GitHub** (anyone with a GitHub account). Create an OAuth app:
   1. On GitHub, open your profile picture, then Settings, then Developer settings, then OAuth Apps, and register a new app. It can belong to a personal account, and no organization has to approve it. At login it asks each user for their email address and read-only access to their organizations, which `oauth2-proxy` always reads.
   2. Homepage URL: `https://<TEST_HOSTNAME>`. Authorization callback URL: `https://<TEST_HOSTNAME>/oauth2/callback`. Leave wildcard matching off.
   3. Generate a client secret. Keep the client ID and the secret for step 3; the secret is shown only once.
2. **Cloudflare** (someone who manages the domain's zone). Create a tunnel:
   1. In the Cloudflare dashboard, open Networking, then Tunnels, and create a tunnel.
   2. The dashboard shows an install command for a connector. Copy only the token from it, the long string; keep it for step 3. Do not run the command: the VM runs its own connector.
   3. On the tunnel's Routes tab, add one route of type Published application: subdomain and domain making up `<TEST_HOSTNAME>`, service URL `http://oauth2-proxy:4180`. Add no other routes: everything the tunnel serves must go through the login.
   4. Under the domain's DNS records, check that there is a CNAME record for that name pointing to `<tunnel ID>.cfargotunnel.com`, and add it if not. Do not change any other record.
3. **On the VM** (`dev`, for example in VS Code with `/opt/mddoai-test` open):
   1. In `.env`, fill in `TUNNEL_TOKEN`, `OAUTH2_CLIENT_ID` and `OAUTH2_CLIENT_SECRET`. The setup stage already filled in `OAUTH2_COOKIE_SECRET`.
   2. In `.env`, fill in `GITHUB_USERS`: the GitHub usernames that may log in, exactly as on their profiles (same capitals), separated by commas without spaces. Check the host name.
   3. In `ai-layer.env`, fill in the LLM API keys. To use the same keys as the dev stack: `cp ~/mddoai/ai/ai-layer/.env /opt/mddoai-test/ai-layer.env`.
   4. Check that the dev clone's `ai/.env` keeps `ai-layer` on `127.0.0.1` (`AI_LAYER_PORT=127.0.0.1:...`), as `ai/.env.example` does. Otherwise `cloudflared` could reach it.
4. Start it:

   ```sh
   sudo systemctl enable --now mddoai-test-update.timer
   journalctl -fu mddoai-test-update     # the first build takes several minutes
   ```

5. Open `https://<TEST_HOSTNAME>` in a private window. GitHub asks you to authorize the app once; skip its organization "Request" and "Grant" buttons. A GitHub account that is not on the list must be refused.

## Day-to-day

- **What it did:** `journalctl -u mddoai-test-update`. `cat /opt/mddoai-test/deployed` is the commit it runs. To pick up a new commit at once instead of at the next check: `sudo systemctl start mddoai-test-update`.
- **Switch it off:**

  ```sh
  sudo systemctl disable --now mddoai-test-update.timer
  sudo systemctl stop mddoai-test-update        # a run in progress
  cd /opt/mddoai-test/repo/ai && docker compose down
  ```

  Start it again with `sudo systemctl enable --now mddoai-test-update.timer`. Running the setup script again never switches it on or off.
- **Files in this folder changed:** run the setup script again, then `cd /opt/mddoai-test/repo/ai && docker compose up -d`. `update.sh` alone does not pick them up while `main` has no new commit.
- **Change a setting or rotate a secret:** edit `.env` or `ai-layer.env`, then run `cd /opt/mddoai-test/repo/ai && docker compose up -d`, which recreates the containers that use it.
  - A new tunnel token comes from the tunnel's page in the Cloudflare dashboard, and a new client secret from the OAuth app's page on GitHub.
  - Make a new cookie secret with `openssl rand -base64 32 | tr -- '+/' '-_'`. It logs everyone out.
- **Add or remove a user:** edit `GITHUB_USERS` in `.env`, then run the same `docker compose up -d`.
  - `oauth2-proxy` checks the list at login only, so someone removed keeps access until their cookie expires (`OAUTH2_COOKIE_EXPIRE` in `.env`). Rotate the cookie secret to end it at once.
  - Access is by username. When someone on the list renames or deletes their GitHub account, remove the old name at once: GitHub lets anyone claim a freed name.
  - A user needs a verified primary email address on GitHub, or the login refuses them.
  - Whoever owns the OAuth app can rotate or delete its secret, which stops all logins until `.env` has the new one. They cannot let anyone in.
- **Memory:** the Test environment needs about as much as the dev stack at idle; check with `docker stats`.
- **Disk:** after each build, `update.sh` removes the untagged images that rebuilds leave behind, the dev stack's too. Docker's build cache, shared with the dev stack, still grows; check with `docker system df`.
