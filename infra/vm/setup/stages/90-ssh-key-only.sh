#!/usr/bin/env bash
# Makes SSH accept keys only: no passwords, for root or anyone else. Root can
# still log in with its key. A config sshd rejects is rolled back, so this
# step cannot leave sshd unable to start.
# Runs last, after the developer's user exists.
set -euo pipefail
# shellcheck source=../lib/common.sh
source "$(dirname "${BASH_SOURCE[0]}")/../lib/common.sh"
require_root

DROP_IN=/etc/ssh/sshd_config.d/00-key-only.conf

# sshd -t and sshd -T need sshd's runtime directory, which only exists while
# the service runs.
install -d -m 0755 /run/sshd

previous="$TMP_DIR/previous.conf"
had_previous=false
if [[ -f "$DROP_IN" ]]; then
  cp -p "$DROP_IN" "$previous"
  had_previous=true
fi

# The 00- prefix in DROP_IN makes these settings win: sshd uses the first
# value it reads, and reads sshd_config.d in name order.
if install_if_changed "$FILES_DIR/sshd-key-only.conf" "$DROP_IN" 0644; then
  # Check the config before reloading. If sshd rejects it, put the previous
  # state back, because sshd checks its config on every start and would
  # refuse to start with a broken file in place.
  if ! sshd -t; then
    if $had_previous; then cp -p "$previous" "$DROP_IN"; else rm -f "$DROP_IN"; fi
    die "sshd rejected the new config; the previous config was restored."
  fi
  systemctl try-reload-or-restart ssh.service
fi

# Confirm the global settings sshd uses, whatever other config files say.
# || true: if grep finds nothing, the checks below report it instead of the
# stage stopping without a message.
effective="$(sshd -T | grep -E '^(passwordauthentication|kbdinteractiveauthentication|permitrootlogin) ' || true)"
log "$effective"
grep -qx 'passwordauthentication no' <<<"$effective" || die "Password login is still on."
grep -qx 'kbdinteractiveauthentication no' <<<"$effective" || die "Keyboard-interactive login is still on."
grep -qx 'permitrootlogin without-password' <<<"$effective" \
  || grep -qx 'permitrootlogin prohibit-password' <<<"$effective" \
  || die "Root can still log in with a password."
