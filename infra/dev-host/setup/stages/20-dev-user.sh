#!/usr/bin/env bash
# Creates the developer's user with sudo, and lets them log in with the same
# SSH key(s) root was given when Hetzner created the server.
set -euo pipefail
# shellcheck source=../lib/common.sh
source "$(dirname "${BASH_SOURCE[0]}")/../lib/common.sh"
require_root

if id -u "$DEV_USER" >/dev/null 2>&1; then
  log "User $DEV_USER exists"
else
  useradd --create-home --shell /bin/bash --groups sudo "$DEV_USER"
  log "Created user $DEV_USER"
fi

# The account has no password (key login only), so sudo cannot ask for one.
sudoers_tmp="$TMP_DIR/sudoers"
printf '%s ALL=(ALL) NOPASSWD:ALL\n' "$DEV_USER" >"$sudoers_tmp"
visudo -cf "$sudoers_tmp" >/dev/null || die "Generated sudoers rule is invalid."
install_if_changed "$sudoers_tmp" "/etc/sudoers.d/90-$DEV_USER" 0440 || true

# Add root's keys to the user's authorized_keys, keeping any keys the user
# already has.
[[ -s /root/.ssh/authorized_keys ]] \
  || die "/root/.ssh/authorized_keys is missing or empty; there is no key to give $DEV_USER."
home_dir="$(getent passwd "$DEV_USER" | cut -d: -f6)"
keys_file="$home_dir/.ssh/authorized_keys"
install -d -m 0700 -o "$DEV_USER" -g "$DEV_USER" "$home_dir/.ssh"
touch "$keys_file"
added=0
# The || part keeps a last line that has no trailing newline.
while IFS= read -r key || [[ -n "$key" ]]; do
  [[ -z "$key" || "$key" == \#* ]] && continue
  if ! grep -qxF -- "$key" "$keys_file"; then
    printf '%s\n' "$key" >>"$keys_file"
    added=$((added + 1))
  fi
done </root/.ssh/authorized_keys
chown "$DEV_USER:$DEV_USER" "$keys_file"
chmod 0600 "$keys_file"
log "SSH keys added for $DEV_USER: $added"
