# Dev host

Infrastructure for the remote development VM on Hetzner Cloud. Developers edit
with VS Code Remote-SSH and reach the app through SSH port forwarding. The only
inbound port is SSH, and only from listed developer addresses. A list of
GitHub users can also open a Test environment in the browser, through an
outbound Cloudflare tunnel.

The work is split in four:

- `terraform/` creates the machine, on your PC.
- `setup/` sets up what runs on it, on the VM. It is safe to run again whenever the setup changes.
- `connect/` connects your PC to it: firewall address, VS Code extension, SSH config. It is safe to run again whenever you want to connect.
- `test-env/` is the Test environment that setup installs on the VM (see "Test environment").

## Versions

The design is the "Dev Host (Hetzner)" page of the
[architecture diagram](https://app.diagrams.net/#Hmodeldrivendevopsai%2Fmddoai%2Fdraw.io-diagrams%2FMDDOAI_Agentic_Architecture.drawio#%7B%22pageId%22%3A%22devhost-hetzner%22%7D).
It grows in versions, each adding to the one before:

1. **V1, solo dev box:** the VM, reached over SSH only. Done.
2. **V2, Test environment:** a second copy of the stack on the same VM, for reviewing branches in a browser, behind a Cloudflare tunnel and GitHub login. The branch it runs is picked with a GitHub workflow. Done; this is the current version.
3. **V3, public demo:** a demo of `main` that people outside the project can open.
4. **V4, other developers:** their own accounts and copies of the app on the same VM.

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
does one job, described at its top, and can also be run on its own. Together
they install updates and basic tools, create the `dev` user, install Docker
(with log rotation) and Claude Code, install the Test environment switched
off, and last turn off password login over SSH. Config files the stages
install live in `setup/files/`. `dev` gets passwordless sudo and joins the
`docker` group, which is equivalent to root on this machine. If sshd rejects
the new SSH config, the last stage puts the previous config back, so sshd can
always start.

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
needs no inbound rule.

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
| GitHub user on the Test environment's list | a GitHub account named in `GITHUB_USERS` | Use the Test environment in a browser, spending the stack's LLM credentials. No shell on the VM. |
| Anyone with write access to the repository | push access on GitHub | Run any code on the VM, as root in effect: they can push a branch and point the Test environment at it with the workflow, and the updater builds and starts whatever that branch's compose file says, as a user in the `docker` group. |
| Anyone else | nothing | Nothing: the firewall drops their traffic, and `oauth2-proxy` refuses a GitHub account that is not on the list. |

**Secrets and where they live.**

- **Hetzner API token:** only in the environment of the terminal that runs Terraform (see "Before you start"). It is never written to this folder, to the server, or to the server's start-up data. It can manage every resource in its Hetzner project, not just this VM, so it stays with the Terraform owner.
- **Terraform state:** on the Terraform owner's PC, gitignored (see "Terraform state").
- **GitHub access:** no GitHub key is stored on the VM. Git uses the developer's own key through SSH agent forwarding, only while they are connected (see "Working on the VM"). While a developer is connected, anyone with root on the VM can use their forwarded agent.
- **LLM API keys:** in the clone's `ai/ai-layer/.env` on the VM, which is gitignored. Keep it readable by its owner only (`chmod 600`). Anyone with root can still read it.
- **Test environment:** the Cloudflare tunnel token, the GitHub OAuth app's client secret and the login cookie secret in `/opt/mddoai-test/.env`, and its LLM API keys in `/opt/mddoai-test/ai-layer.env`. Both are readable by `dev` only; anyone with root can still read them. Like the dev stack, it also reads `dev`'s `~/.claude` folder.
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
   curl https://api.ipify.org
   # PowerShell: curl.exe https://api.ipify.org
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

5. Run the setup script as root. It needs `setup/` and `test-env/` side by side. From the repository root on your PC:

   ```sh
   ssh -i ~/.ssh/mddoai_dev root@<ip> "rm -rf dev-host && mkdir dev-host"
   scp -i ~/.ssh/mddoai_dev -r infra/dev-host/setup infra/dev-host/test-env root@<ip>:dev-host/
   ssh -i ~/.ssh/mddoai_dev root@<ip> "bash dev-host/setup/setup.sh"
   ```

   - The copy goes into the logged-in user's home folder.
   - The first command clears any copy from an earlier run. Without it, `scp` would copy into the old folder instead of replacing it, and files deleted from the repository would still run.
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

It calls the steps in `connect/steps/`, and each one skips work already done:

- `10-firewall-ip.ps1`: when your public IPv4 address is not in `ssh_allowed_cidrs` in `terraform.tfvars` yet, writes it there and runs `terraform apply`. Check that the plan only updates `hcloud_firewall.dev`, then answer `yes`. If the apply does not complete (an error, `no`, or Ctrl+C), `terraform.tfvars` is put back as it was.
- `20-vscode-extension.ps1`: installs VS Code's Remote - SSH extension.
- `30-ssh-config.ps1`: writes Terraform's `ssh_config` output to its own file, `~/.ssh/<server_name>.conf` (`mddoai-dev.conf` by default), and adds an `Include` line for it to the top of `~/.ssh/config`. The rest of `~/.ssh/config` is left as it is.
- `40-run-setup.ps1`: copies `setup/` and `test-env/` to the VM and runs the setup script there as `dev` with `sudo`. Runs only with `-RunSetup`.
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
2. Clone, and set your git identity on the VM. `-t` lets the VM's ssh ask once whether to trust GitHub's host key: answer `yes` only if the fingerprint it shows is on [GitHub's list of SSH key fingerprints](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/githubs-ssh-key-fingerprints).

   ```powershell
   ssh -t mddoai-dev "git clone git@github.com:modeldrivendevopsai/mddoai.git ~/mddoai"
   ssh mddoai-dev "git config --global user.name '<name>'; git config --global user.email '<email>'"
   ```

3. In VS Code, connected to the VM, open `/home/dev/mddoai` with File, Open Folder.
4. To use Claude Code on the VM, run `claude` in VS Code's terminal and sign in.

If `docker` says "permission denied" in a VS Code terminal right after the
setup script added you to the `docker` group, VS Code is still using its old
session. Run "Remote-SSH: Kill VS Code Server on Host" from the command
palette, then connect again.

## Test environment

A second copy of the `ai/` stack on this VM, which listed GitHub users open in
a browser through a Cloudflare tunnel. Setup stage `60-test-env.sh` installs it
switched off. [test-env/README.md](test-env/README.md) explains how it works,
how to set it up and how to choose the branch it runs.

## Day-to-day changes

- **Setup files changed:** run the setup script again as `dev` with `.\infra\dev-host\connect\connect.ps1 -RunSetup -NoOpen`. Without the connect script, the same thing by hand is:

  ```sh
  ssh mddoai-dev "rm -rf dev-host && mkdir dev-host"
  scp -r infra/dev-host/setup infra/dev-host/test-env mddoai-dev:dev-host/
  ssh mddoai-dev "sudo DEV_USER=dev bash dev-host/setup/setup.sh"
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
