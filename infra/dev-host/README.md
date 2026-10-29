# Dev host

Infrastructure for the remote development VM on Hetzner Cloud. Developers edit
with VS Code Remote-SSH and reach the app through SSH port forwarding. The only
inbound port is SSH, and only from listed developer addresses. Members of the
project's GitHub organization can also open a Test environment in the browser,
through an outbound Cloudflare tunnel (see "Test environment").

The work is split in three:

- `terraform/` creates the machine, on your PC.
- `setup/` sets up what runs on it, on the VM. It is safe to run again whenever the setup changes.
- `connect/` connects your PC to it: firewall address, VS Code extension, SSH config. It is safe to run again whenever you want to connect.

## What Terraform creates

- **Firewall:** one inbound rule, TCP 22 from `ssh_allowed_cidrs`. Hetzner drops every other inbound packet.
- **IPv4 address:** a primary IP that is kept when the server is rebuilt, so the address in SSH configs stays valid. It has delete protection.
- **SSH key:** the developer's public key, registered with Hetzner.
- **Server:** plain Ubuntu 24.04 with delete and rebuild protection. Hetzner backups are off; set `backups = true` in `terraform.tfvars` to turn them on. It starts with only the `root` account.

The protection stops anyone deleting the server or the IP by accident, in the
Hetzner Console or with Terraform. Terraform does not lift it on its own:
destroying or replacing either one needs `-var protection=false`, as shown in
the steps below.

Login is by SSH key only. Hetzner installs the key for root and, because a key
is given, issues no root password. Terraform does not configure anything on the
server itself; switching password login off in the SSH settings is the setup
script's job, so all on-server setup lives in one place.

## What the setup script does

`setup/setup.sh` runs each file in `setup/stages/` in name order. Each stage
does one job and can also be run on its own:

- `10-base-packages.sh`: installs pending updates and basic tools.
- `20-dev-user.sh`: creates the `dev` user with passwordless sudo, and lets it log in with the same SSH key as root.
- `30-docker.sh`: installs Docker with the Compose and Buildx plugins from Docker's own apt repository, and adds `dev` to the `docker` group. That group is equivalent to root on this machine.
- `31-docker-logs.sh`: makes Docker rotate container logs. Docker does not by default.
- `40-claude-code.sh`: installs the Claude Code CLI for all users from Anthropic's signed apt repository, after checking the signing key's fingerprint.
- `50-github-host-keys.sh`: trusts GitHub's SSH host keys for every user, taken from GitHub's API, so cloning over SSH does not trust an unknown host on first use.
- `60-test-env.sh`: installs the Test environment, switched off (see "Test environment").
- `90-ssh-key-only.sh`: turns off password login over SSH for everyone, then checks sshd's global settings. Root can still log in with its key. If sshd rejects the new config, the stage puts the previous config back, so sshd can always start.

Config files the stages install live in `setup/files/`.

The user name defaults to `dev`. To use another name, set it in both places:
`dev_user` in `terraform.tfvars`, and `DEV_USER` for the script. Put
`DEV_USER=<name>` after `sudo`, as in `sudo DEV_USER=<name> bash setup.sh`.
Placed before `sudo`, it is dropped. The connect script passes it for you.

Ubuntu installs its own security updates automatically, but not updates from
the Docker and Claude Code repositories. Re-running the setup script (stage
10) or `sudo apt update && sudo apt upgrade` applies them.

## Access control

Who can reach the VM, and what each kind of access allows.

**Network.** The Hetzner firewall lets in one thing: SSH (TCP 22) from the
addresses in `ssh_allowed_cidrs`. It filters in Hetzner's network, before
traffic reaches the VM, so it also blocks ports that Docker publishes on the
VM. The dev stack is never reachable directly; developers reach it through SSH
port forwarding (see "Opening the app"). Outbound traffic is not restricted.
The Test environment uses that: its tunnel connects out to Cloudflare, so it
needs no inbound rule (see "Test environment").

**SSH login.** Keys only, for every user: the setup script turns password login
off, and Hetzner issues no root password because a key is given.

| Account | Logs in with | Can do |
|---|---|---|
| `root` | the key Terraform registered | Everything. It keeps key login because a new or rebuilt server starts with only `root`, and the first setup run needs it. |
| `dev` | the same key, copied from root by the setup script | Everything as well: it has passwordless sudo, and it is in the `docker` group, which is equivalent to root. |

**Who controls what.**

| Role | Has | Can |
|---|---|---|
| Terraform owner | the Hetzner API token and the Terraform state, on their own PC | Create, resize and delete the server; change the firewall. |
| Developer with a key for `dev` | an SSH key and an allowed address | Log in, develop, run the stack, and use root through sudo or Docker. |
| Member of the GitHub organization | a GitHub account in the organization | Use the Test environment in a browser, spending the stack's LLM credentials. No shell on the VM. |
| Anyone with write access to the repository | push access on GitHub | Run any code on the VM, as root in effect: they can push a branch and point the Test environment at it with the workflow, and the updater builds and starts whatever that branch's compose file says, as a user in the `docker` group. |
| Anyone else | nothing | Nothing: the firewall drops their traffic, and `oauth2-proxy` refuses a GitHub account outside the organization. |

**Secrets and where they live.**

- **Hetzner API token:** only in the environment of the terminal that runs Terraform (see "Before you start"). It is never written to this folder, to the server, or to the server's start-up data. It can manage every resource in its Hetzner project, not just this VM, so it stays with the Terraform owner.
- **Terraform state:** on the Terraform owner's PC, gitignored (see "Terraform state").
- **GitHub access:** no GitHub key is stored on the VM. Git uses the developer's own key through SSH agent forwarding, only while they are connected (see "Working on the VM"). While a developer is connected, anyone with root on the VM can use their forwarded agent.
- **LLM API keys:** in the clone's `ai/ai-layer/.env` on the VM, which is gitignored. Keep it readable by its owner only (`chmod 600`). Anyone with root can still read it.
- **Test environment:** the Cloudflare tunnel token, the GitHub OAuth app's client secret and the login cookie secret, in `/opt/mddoai-test/.env`, readable by `dev` only. Anyone with root can still read it. The test stack uses the same LLM credentials as the dev stack: the dev clone's `ai/ai-layer/.env`, and `dev`'s own `~/.claude` folder (see "Test environment").
- **Claude Code:** each developer signs in on the VM themselves. The stack's `ai-layer` reads the `~/.claude` folder of the user who starts it, so sign in with a Claude Console account, not a personal subscription.

**Adding and removing access.** A developer gets access when the Terraform
owner adds their address to `ssh_allowed_cidrs` and their public key to `dev`'s
`authorized_keys` (see "Another developer" under "Day-to-day changes"). To
remove someone, delete their key line and their address, then apply.

## Server size

The default is `cx43`, on Hetzner's cost-optimized line. It is the cheapest
type with room for the full `ai/` Docker Compose stack plus the VS Code Server,
Claude Code and image builds.

- Hetzner limits how many cost-optimized servers it offers, and they are sometimes not orderable. Check before applying (see "Before you start").
- The regular performance line (`cpx` types) has types of the same size with bigger disks, at a much higher price. Switch with `server_type` only if the budget allows.
- Check the price, size and disk in the [Hetzner Console](https://console.hetzner.com/) before applying. Backups, if turned on, add a share of the server price. The IPv4 address is billed separately, including while no server uses it.
- Measure with `docker stats` once the stack runs before deciding to resize.
- Changing `server_type` later resizes the server, at the price current on that day.
  - Terraform first powers the server off without a clean shutdown, like pulling the plug. Stop the stack first (`docker compose down`), and expect a few minutes of downtime.
  - The resize also grows the disk, and a server with a grown disk cannot move back to a smaller type.

## Before you start

1. Install [Terraform](https://developer.hashicorp.com/terraform/install) 1.15 or newer.
2. Create an API token in the [Hetzner Console](https://console.hetzner.com/): open the project, then Security, API tokens, Generate API token, with Read & Write permission. It is shown only once.
3. Load the token into your terminal. Terraform reads it from the environment, never from a file in this folder. These commands prompt for it, so it does not end up in your shell history:

   ```sh
   read -rs HCLOUD_TOKEN && export HCLOUD_TOKEN
   # PowerShell: $env:HCLOUD_TOKEN = [Net.NetworkCredential]::new('', (Read-Host 'Hetzner token' -AsSecureString)).Password
   ```

   It lasts until you close that terminal. Run the Terraform commands below in the same one.
4. Create an SSH key for the VM on your PC:

   ```sh
   ssh-keygen -t ed25519 -f ~/.ssh/mddoai_dev
   # PowerShell: ssh-keygen -t ed25519 -f "$HOME\.ssh\mddoai_dev"
   ```

5. Find your public IPv4 address. It must be IPv4, because the SSH config connects over IPv4:

   ```sh
   curl -4 https://ifconfig.me
   # PowerShell: curl.exe -4 https://ifconfig.me
   ```

6. Check that the server type can be ordered. Replace `<server_type>` with the type you will use, `cx43` by default:

   ```sh
   curl -s -H "Authorization: Bearer $HCLOUD_TOKEN" "https://api.hetzner.cloud/v1/server_types?name=<server_type>"
   # PowerShell: curl.exe -s -H "Authorization: Bearer $env:HCLOUD_TOKEN" "https://api.hetzner.cloud/v1/server_types?name=<server_type>"
   ```

   In the reply, each entry under `locations` has a location `name` and an `available` flag. Pick a `location` where it is `true`. Hetzner treats the flag as an indication, not a guarantee.

## Create the VM

1. Go to the Terraform folder and copy the example settings:

   ```sh
   cd infra/dev-host/terraform
   cp terraform.tfvars.example terraform.tfvars
   ```

2. Put your IPv4 address in `terraform.tfvars` as `"<ip>/32"`, and the `location` you picked.
3. Create everything:

   ```sh
   terraform init
   terraform plan
   terraform apply
   ```

   If something cannot be created, the resources created before it stay, and the IPv4 address is billed even without a server. Either fix the cause and apply again, or remove everything as described in "Tearing down". Two common causes:
   - The server type is not available after all (`resource_unavailable`). Try again later, or in another location.
   - Hetzner refuses the SSH key because the same public key is already registered in the project under another name. Delete that copy in the Hetzner Console under Security, SSH keys, or use a different key.

4. Get the server's address, and check that you can reach it as root. In the commands below, `<ip>` is this address:

   ```sh
   terraform output -raw ipv4_address
   ssh -i ~/.ssh/mddoai_dev root@<ip> "echo ok"
   ```

5. Run the setup script as root. From the repository root on your PC:

   ```sh
   ssh -i ~/.ssh/mddoai_dev root@<ip> "rm -rf dev-host-setup"
   scp -i ~/.ssh/mddoai_dev -r infra/dev-host/setup root@<ip>:dev-host-setup
   ssh -i ~/.ssh/mddoai_dev root@<ip> "bash dev-host-setup/setup.sh"
   ```

   - The copy goes into the logged-in user's home folder.
   - The first command clears any copy from an earlier run. Without it, `scp` would copy into the old folder instead of replacing it, and stage files deleted from the repository would still run.
   - The script stops at the first failing step. Fix the cause and run it again.
   - If it ends with "Updates need a reboot", run `ssh -i ~/.ssh/mddoai_dev root@<ip> reboot`.

6. Connect your PC with the connect script (see "Connecting your PC"). It logs in as `dev`, so it works only after the setup script.
7. Clone the repository on the VM (see "Working on the VM").

## Connecting your PC

`connect/connect.ps1` is a PowerShell script for Windows. It reads Terraform's
outputs and state, so it is for the person who runs Terraform for this VM (see
"Terraform state"). Run it from the repository root, the first time and
whenever SSH stops connecting:

```powershell
.\infra\dev-host\connect\connect.ps1
```

If PowerShell says running scripts is disabled, allow local scripts for your
user once with `Set-ExecutionPolicy -Scope CurrentUser RemoteSigned`.

It runs each file in `connect/steps/` in order, and each one skips work already done:

- `10-firewall-ip.ps1`: when your public IPv4 address is not in `ssh_allowed_cidrs` in `terraform.tfvars` yet, writes it there and runs `terraform apply`. Check that the plan only updates `hcloud_firewall.dev`, then answer `yes`. If the apply does not complete (an error, `no`, or Ctrl+C), `terraform.tfvars` is put back as it was.
- `20-vscode-extension.ps1`: installs VS Code's Remote - SSH extension.
- `30-ssh-config.ps1`: writes Terraform's `ssh_config` output to its own file, `~/.ssh/<server_name>.conf` (`mddoai-dev.conf` by default), and adds an `Include` line for it to the top of `~/.ssh/config`. The rest of `~/.ssh/config` is left as it is.
- `40-run-setup.ps1`: copies `setup/` to the VM and runs it there as `dev` with `sudo`. Runs only with `-RunSetup`.
- `50-open-vscode.ps1`: opens VS Code connected to the VM, in `/home/dev`. Skipped with `-NoOpen`.

Notes:

- The firewall step needs the Hetzner token only when your address changed. It uses `HCLOUD_TOKEN` from your terminal (see "Before you start"), or reads it from a file you pass with `-EnvFile <path>`. In that file, the line must start with `HCLOUD_TOKEN=`. Afterwards your terminal's `HCLOUD_TOKEN` is as it was before.
- The firewall step needs `ssh_allowed_cidrs` on one line, as in `terraform.tfvars.example`. It replaces the address only when that line lists exactly one. With several, one may be another developer's, so it stops and asks you to edit the list by hand.
- If `~/.ssh/config` has another `Host mddoai-dev` entry, delete it. ssh ignores its settings, except `LocalForward` lines, which add to the ones in the included file.
- The script needs the `code` command on your PATH. VS Code's Windows setup adds it; restart the terminal after installing. With `-NoOpen`, a missing `code` is only a warning.
- The SSH config comes from Terraform's state. After changing a variable that affects it (for example `ui_ports`), run `terraform apply` before the connect script.
- On the first connection, VS Code installs its server on the VM, which takes about a minute. It may also ask for the remote platform once: pick Linux.

Other developers without the Terraform state can connect by hand: the Terraform
owner adds their address to `ssh_allowed_cidrs` and sends them the
`terraform output -raw ssh_config` block to put in their own `~/.ssh/config`.

### Opening the app

The SSH config forwards every port in `ui_ports` from the VM to the same port
on your PC: the UI's host page and each UI remote it loads. While you are
connected, open the host page's port (the first in `ui_ports`) on `localhost`
in your browser. If one of those ports is already in use on your PC, ssh warns
and skips that forward; stop the local program using it.

## Working on the VM

The repository is a normal git clone on the VM, separate from the one on your
PC. Both sync through GitHub.

Git on the VM reaches GitHub with your own GitHub SSH key, through agent
forwarding: the key stays on your PC, and the VM asks your PC's ssh-agent to
sign each login while you are connected. When you are not connected, git on
the VM cannot reach GitHub.

One-time setup:

1. On your PC, have an SSH key that is added to your GitHub account, and load it into the Windows ssh-agent. If the `ssh-agent` service is not running, start it once as administrator with `Set-Service ssh-agent -StartupType Automatic; Start-Service ssh-agent`.

   ```powershell
   ssh-add $HOME\.ssh\<your-github-key>
   ssh -T git@github.com   # should greet you by your GitHub user name
   ```

   Only load keys you are happy for the VM to use while you are connected: anyone with root on the VM can use the forwarded agent during that time.
2. Clone, and set your git identity on the VM:

   ```powershell
   ssh mddoai-dev "git clone git@github.com:modeldrivendevopsai/mddoai.git ~/mddoai"
   ssh mddoai-dev "git config --global user.name '<name>'; git config --global user.email '<email>'"
   ```

3. In VS Code, connected to the VM, open `/home/dev/mddoai` with File, Open Folder.
4. To use Claude Code on the VM, run `claude` in VS Code's terminal and sign in.

If `docker` says "permission denied" in a VS Code terminal right after the
setup script added you to the `docker` group, VS Code is still using its old
session. Run "Remote-SSH: Kill VS Code Server on Host" from the command
palette, then connect again.

## Test environment

A second copy of the `ai/` stack on the same VM, for reviewing work in a
browser without SSH. It runs the newest commit of one branch, and members of
the project's GitHub organization open it at `TEST_HOSTNAME` (set in
`/opt/mddoai-test/.env`). Nobody else gets past the login.

How a request reaches it:

1. The browser goes to Cloudflare, which owns the name.
2. Cloudflare sends it down a tunnel that `cloudflared` on the VM opened outward, so no port is open on the VM.
3. `oauth2-proxy` checks the login cookie. Without one it sends the visitor to GitHub, and lets them in only if GitHub says they are a member of the organization.
4. The test stack's `ui-host` serves the app, the same way the dev stack's does.

What runs:

- **One Compose project, `ai-test`,** from its own clone of the repository (`/home/dev/mddoai-test`):
  - the repository's `ai/` stack, with its UI and `ai-layer` ports on `127.0.0.1` only, next to the dev stack's;
  - the gateway, `cloudflared` and `oauth2-proxy`, from `/opt/mddoai-test/compose.yaml`. Neither publishes a port.

  All its settings and secrets are in `/opt/mddoai-test/.env`, linked as the clone's `ai/.env`. So any `docker compose` command run in `/home/dev/mddoai-test/ai` acts on the Test environment, never on the dev stack. It has its own data folder in the clone. It shares the dev clone's `ai/ai-layer/.env`, and like the dev stack it reads `dev`'s `~/.claude` folder, so organization members spend the same LLM credentials.
- **The updater:** `mddoai-test-update.timer` runs `/opt/mddoai-test/update.sh` as `dev` shortly after boot, then a few minutes after each run ends. Each run:
  1. fetches the repository and reads which branch to run (see "Day-to-day");
  2. stops there if that branch has no new commit and the stack is up;
  3. otherwise checks out the newest commit in the clone, rebuilds when it is new, and starts the stack.

  A run that cannot reach GitHub changes nothing. A commit that fails to build leaves the previous containers running. Every run after that tries the build again until a new commit arrives, which costs CPU on the shared VM.

Everyone in the organization shares this one stack: they see the same runs,
pipelines and platforms.

The tunnel's routes are set in the Cloudflare dashboard, not in this folder,
and `cloudflared` can reach any port listening on the VM's own addresses, not
only those Docker publishes (sshd too). So a second route added there would
serve that port to the internet with no login. Two things keep that from
mattering:

- The tunnel has exactly one route, to `oauth2-proxy` ("Setting it up", step 2). Only people who manage the domain on Cloudflare can change it.
- Nothing worth reaching listens on all addresses, once the dev stack's `ai-layer` is moved to `127.0.0.1` ("Setting it up", step 3).

### Setting it up

Done once, by people with access to each service. Steps 1 and 2 can happen in
either order.

1. **GitHub** (an owner of the organization). Create an OAuth app that the organization owns:
   1. In the organization's Settings, open Developer settings, then OAuth Apps, and register a new app. Create it there, not under a personal account. GitHub lets apps the organization owns read its data. New organizations block other OAuth apps by default, and then `oauth2-proxy` cannot see who is a member.
   2. Homepage URL: `https://<TEST_HOSTNAME>`. Authorization callback URL: `https://<TEST_HOSTNAME>/oauth2/callback`.
   3. Generate a client secret. Keep the client ID and the secret for step 3; the secret is shown only once.
2. **Cloudflare** (someone who manages the domain's zone). Create a tunnel:
   1. In the Cloudflare dashboard, open Networking, then Tunnels, and create a tunnel.
   2. The dashboard shows an install command for a connector. Copy only the token from it, the long string; keep it for step 3. Do not run the command: the VM runs its own connector.
   3. On the tunnel's Routes tab, add one route of type Published application: subdomain and domain making up `<TEST_HOSTNAME>`, service URL `http://oauth2-proxy:4180`. Add no other routes: everything the tunnel serves must go through the login.
   4. Under the domain's DNS records, check that there is a CNAME record for that name pointing to `<tunnel ID>.cfargotunnel.com`, and add it if not. Do not change any other record.
3. **On the VM** (`dev`):
   1. Open `/opt/mddoai-test/.env` with `nano /opt/mddoai-test/.env` (`dev` owns it). Fill in the three values: `TUNNEL_TOKEN`, `OAUTH2_CLIENT_ID`, `OAUTH2_CLIENT_SECRET`. The setup stage already filled in `OAUTH2_COOKIE_SECRET`.
   2. Check the fallback branch (`TEST_BRANCH`), the host name and the organization. The fallback branch must have the configurable stack ports (see "Day-to-day").
   3. Put `AI_LAYER_PORT=127.0.0.1:8000` in the dev clone's `ai/.env`, then restart the dev stack. Otherwise the dev stack's `ai-layer` listens on all addresses, where `cloudflared` could reach it. Tools on the VM still reach it on `localhost:8000`. This needs a dev clone branch that has the configurable stack ports.
4. Start it:

   ```sh
   sudo systemctl enable --now mddoai-test-update.timer
   journalctl -fu mddoai-test-update     # the first build takes several minutes
   ```

5. Open `https://<TEST_HOSTNAME>` in a private window. GitHub asks you to authorize the app once. A GitHub account outside the organization must be refused.

### Day-to-day

- **Review another branch:** anyone with write access to the repository can switch it, without logging in to the VM. The workflow can run only once its file is on the default branch.
  1. On GitHub, open Actions, then the "Test environment branch" workflow, then Run workflow. Leave the branch the workflow itself runs from on the default branch.
  2. Type the branch to review and run it. The workflow refuses a branch that does not exist or does not have the configurable stack ports.
  3. The Test environment switches at its next check, within minutes, and rebuilds.

  The workflow writes the name to the file `branch` on the `test-env-config` branch, and `update.sh` reads it from there. `TEST_BRANCH` in `/opt/mddoai-test/.env` is the fallback: it runs until someone chooses a branch, and again when the chosen branch is deleted, as happens when its pull request is merged. Once a branch was chosen, editing `TEST_BRANCH` changes nothing while that branch exists; run the workflow with `main` to go back. On the VM, `sudo systemctl start mddoai-test-update` switches at once instead of at the next check.

  The branch must contain the configurable stack ports (`UI_PORT`, `AI_LAYER_PORT` and `UI_ALLOWED_HOSTS` in `ai/docker-compose.yml`). Without them the test stack would take the dev stack's ports, so `update.sh` refuses such a commit before checking it out, and the running containers stay as they are.
- **What it did:** `journalctl -u mddoai-test-update`. `cat ~/mddoai-test.deployed` is the commit it runs.
- **Switch it off:**

  ```sh
  sudo systemctl disable --now mddoai-test-update.timer
  sudo systemctl stop mddoai-test-update        # a run in progress
  cd ~/mddoai-test/ai && docker compose down
  ```

  Start it again with `sudo systemctl enable --now mddoai-test-update.timer`. Running the setup script again never switches it on or off.
- **Rotate a secret:** replace it in `/opt/mddoai-test/.env`, then run `cd ~/mddoai-test/ai && docker compose up -d`, which recreates the containers that use it. Get a new tunnel token from the tunnel's page in the Cloudflare dashboard, and a new client secret from the OAuth app's page on GitHub. Make a new cookie secret with `openssl rand -base64 32 | tr -- '+/' '-_'`; it logs everyone out. The LLM API keys are the dev clone's: change them there, then run the same `docker compose up -d`.
- **Someone leaves the organization:** `oauth2-proxy` checks membership at login only, so their login lasts until the cookie expires (`OAUTH2_PROXY_COOKIE_EXPIRE` in `compose.yaml`). Rotate the cookie secret to end it at once.
- **Memory:** the test stack needs about as much as the dev stack at idle; check with `docker stats`.
- **Disk:** after each build, `update.sh` removes the untagged images that rebuilds leave behind, the dev stack's too. Docker's build cache, shared with the dev stack, still grows; check with `docker system df`.

## Day-to-day changes

- **Setup files changed:** run the setup script again as `dev` with `.\infra\dev-host\connect\connect.ps1 -RunSetup -NoOpen`. Without the connect script, the same thing by hand is:

  ```sh
  ssh mddoai-dev "rm -rf dev-host-setup"
  scp -r infra/dev-host/setup mddoai-dev:dev-host-setup
  ssh mddoai-dev "sudo DEV_USER=dev bash dev-host-setup/setup.sh"
  ```

  If `31-docker-logs.sh` changes the log settings, it restarts Docker, which stops running containers. Existing containers keep their old log settings until they are recreated with `docker compose up -d --force-recreate`.
- **Your IP changed:** SSH and VS Code time out. Run the connect script, which updates the firewall. Without it: replace your address in `ssh_allowed_cidrs` and run `terraform apply`; the plan should only update `hcloud_firewall.dev`. Being locked out meanwhile does not affect anything on the server.
- **Another developer:** add their address to `ssh_allowed_cidrs` and apply. They log in as the same `dev` user with a key you add to `/home/dev/.ssh/authorized_keys`; separate Linux users per developer are not set up by this folder.
- **Rebuild the server from scratch** (to check it can be recreated, or after a broken setup):
  - This deletes everything on the server's disk. If backups are on, Hetzner deletes the old server's backups with it. Take a snapshot in the Hetzner Console first. Snapshots are kept until you delete them.
  - Run, in order:

    ```sh
    terraform apply -var protection=false   # lift the protection
    terraform apply -var protection=false -replace=hcloud_server.dev
    terraform apply                         # turn the protection back on
    ```

  - The IPv4 address stays the same. The SSH host key changes, so remove the old entry with `ssh-keygen -R <ip>`.
  - The new server has only `root` again, so `ssh mddoai-dev` fails until you repeat steps 4 to 7 of "Create the VM".

### Changes that replace the server

Read every `terraform plan` before applying. A line saying a resource "must be
replaced" means it is deleted and created again. While the protection is on,
Hetzner refuses the delete and the apply stops with a "protected" error.

- **`image` or `location`:** replaces the server, with the same data loss as a rebuild. A new `location` also replaces the IPv4 address, so the address changes.
- **The SSH key file:** ignored on the existing server on purpose, because applying it would mean recreating the server. It takes effect on the next rebuild. A new key file still replaces the key registered with Hetzner, and the plan shows that; it does not touch the server.

## Terraform state

The state file (`terraform.tfstate`) stays on the PC that runs Terraform and is
gitignored. It can contain secrets and details of the server. Only one person
should run Terraform for this VM. If the state file is lost, the resources keep
running and can be brought back under Terraform with `terraform import`.

`.terraform.lock.hcl` is committed, as HashiCorp recommends, so everyone uses
the same provider version.

## Tearing down

Destroying the server deletes its disk, and any Hetzner backups of it, so take
a snapshot or copy anything you need first. Then lift the protection and delete
the server, firewall, SSH key and IPv4 address:

```sh
terraform apply -var protection=false
terraform destroy -var protection=false
```

Read both plans before confirming. The first should only change protection
settings, and the second should only destroy resources this folder created.
