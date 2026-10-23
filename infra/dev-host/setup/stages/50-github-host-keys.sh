#!/usr/bin/env bash
# Trusts GitHub's SSH host keys for every user, taken from GitHub's own API,
# so the first `git clone git@github.com:...` does not have to trust whatever
# answers. Only github.com lines in the system-wide known_hosts are replaced.
set -euo pipefail
# shellcheck source=../lib/common.sh
source "$(dirname "${BASH_SOURCE[0]}")/../lib/common.sh"
require_root

KNOWN_HOSTS=/etc/ssh/ssh_known_hosts

meta="$TMP_DIR/github-meta.json"
curl -fsSL https://api.github.com/meta -o "$meta"
keys="$(python3 -c 'import json,sys; print("\n".join("github.com " + k for k in json.load(open(sys.argv[1]))["ssh_keys"]))' "$meta")"
[[ -n "$keys" ]] || die "GitHub's API returned no SSH host keys."

new_file="$TMP_DIR/ssh_known_hosts"
{
  if [[ -f "$KNOWN_HOSTS" ]]; then grep -v '^github\.com ' "$KNOWN_HOSTS" || true; fi
  printf '%s\n' "$keys"
} >"$new_file"
install_if_changed "$new_file" "$KNOWN_HOSTS" 0644 || true
ssh-keygen -lf "$KNOWN_HOSTS" | grep -F 'github.com' || die "No GitHub host keys in $KNOWN_HOSTS."
