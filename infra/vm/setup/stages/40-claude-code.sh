#!/usr/bin/env bash
# Installs the Claude Code CLI for every user from Anthropic's signed apt
# repository (stable channel), following Claude Code's setup guide. Updates
# arrive with the normal apt upgrade.
set -euo pipefail
# shellcheck source=../lib/common.sh
source "$(dirname "${BASH_SOURCE[0]}")/../lib/common.sh"
require_root

# Fingerprint of the Claude Code release signing key, as published in Claude
# Code's setup guide. A downloaded key that does not match is rejected.
CLAUDE_KEY_FINGERPRINT="31DDDE24DDFAB679F42D7BD2BAA929FF1A7ECACE"
KEY_FILE=/etc/apt/keyrings/claude-code.asc

key_tmp="$TMP_DIR/claude-code.asc"
curl -fsSL https://downloads.claude.ai/keys/claude-code.asc -o "$key_tmp"
# The file must hold exactly one key, and it must be Anthropic's. A second key
# in the file would also be trusted by apt.
key_info="$(gpg --show-keys --with-colons "$key_tmp")"
key_count="$(grep -c '^pub:' <<<"$key_info" || true)"
[[ "$key_count" == "1" ]] || die "Claude Code key file holds $key_count keys, expected 1."
fingerprint="$(awk -F: '/^fpr:/ {print $10}' <<<"$key_info" | head -n 1)"
[[ "$fingerprint" == "$CLAUDE_KEY_FINGERPRINT" ]] \
  || die "Claude Code signing key fingerprint is '$fingerprint', expected $CLAUDE_KEY_FINGERPRINT."
install_if_changed "$key_tmp" "$KEY_FILE" 0644 || true

list_tmp="$TMP_DIR/claude-code.list"
echo "deb [signed-by=$KEY_FILE] https://downloads.claude.ai/claude-code/apt/stable stable main" >"$list_tmp"
install_if_changed "$list_tmp" /etc/apt/sources.list.d/claude-code.list 0644 || true

apt_update
apt_install claude-code
log "Claude Code: $(claude --version)"
