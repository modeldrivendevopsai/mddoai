#!/usr/bin/env bash
# Installs the Test environment: a second copy of the ai/ stack that follows
# one branch, behind GitHub organization login and a Cloudflare tunnel. It
# does not start it. That waits for the secrets in /opt/mddoai-test/.env and
#
#   sudo systemctl enable --now mddoai-test-update.timer
set -euo pipefail
# shellcheck source=../lib/common.sh
source "$(dirname "${BASH_SOURCE[0]}")/../lib/common.sh"
require_root

TEST_DIR=/opt/mddoai-test
SRC="$FILES_DIR/test-env"

install -d -m 0755 "$TEST_DIR"
install -m 0644 "$SRC/compose.yaml" "$TEST_DIR/"
install -m 0755 "$SRC/update.sh" "$TEST_DIR/"
sed "s/@DEV_USER@/$DEV_USER/" "$SRC/mddoai-test-update.service" >/etc/systemd/system/mddoai-test-update.service
install -m 0644 "$SRC/mddoai-test-update.timer" /etc/systemd/system/
systemctl daemon-reload

# Created once and never overwritten, so the values filled in are kept.
if [[ ! -f "$TEST_DIR/.env" ]]; then
  install -m 0600 -o "$DEV_USER" -g "$DEV_USER" "$SRC/env.example" "$TEST_DIR/.env"
  # oauth2-proxy's documented way to make one.
  cookie_secret="$(openssl rand -base64 32 | tr -- '+/' '-_')"
  sed -i "s|^OAUTH2_COOKIE_SECRET=.*|OAUTH2_COOKIE_SECRET=$cookie_secret|" "$TEST_DIR/.env"
  log "Created $TEST_DIR/.env. Fill in its secrets, then: sudo systemctl enable --now mddoai-test-update.timer"
fi
