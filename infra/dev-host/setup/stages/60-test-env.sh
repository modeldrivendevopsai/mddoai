#!/usr/bin/env bash
# Installs the Test environment in TEST_DIR, switched off. To start it, fill in
# .env and ai-layer.env there, then run:
#
#   sudo systemctl enable --now mddoai-test-update.timer
#
# Its files come from the test-env/ folder next to setup/.
set -euo pipefail
# shellcheck source=../lib/common.sh
source "$(dirname "${BASH_SOURCE[0]}")/../lib/common.sh"
require_root

TEST_DIR=/opt/mddoai-test
REPO_URL=https://github.com/modeldrivendevopsai/mddoai.git
SRC="$SETUP_DIR/../test-env"

# All of it belongs to the developer's user, who runs update.sh.
install -d -m 0755 -o "$DEV_USER" -g "$DEV_USER" "$TEST_DIR"
install -m 0644 -o "$DEV_USER" -g "$DEV_USER" "$SRC/compose.yaml" "$TEST_DIR/"
install -m 0755 -o "$DEV_USER" -g "$DEV_USER" "$SRC/update.sh" "$TEST_DIR/"

if [[ ! -d "$TEST_DIR/repo/.git" ]]; then
  sudo -u "$DEV_USER" git clone --quiet "$REPO_URL" "$TEST_DIR/repo"
  log "Cloned $REPO_URL into $TEST_DIR/repo"
fi

# Created once and never overwritten, so the values filled in are kept.
if [[ ! -f "$TEST_DIR/.env" ]]; then
  install -m 0600 -o "$DEV_USER" -g "$DEV_USER" "$SRC/env.example" "$TEST_DIR/.env"
  # oauth2-proxy's documented way to make one.
  cookie_secret="$(openssl rand -base64 32 | tr -- '+/' '-_')"
  sed -i "s|^OAUTH2_COOKIE_SECRET=.*|OAUTH2_COOKIE_SECRET=$cookie_secret|" "$TEST_DIR/.env"
  log "Created $TEST_DIR/.env. Fill in its users and secrets."
fi
if [[ ! -f "$TEST_DIR/ai-layer.env" ]]; then
  install -m 0600 -o "$DEV_USER" -g "$DEV_USER" "$TEST_DIR/repo/ai/ai-layer/.env.example" "$TEST_DIR/ai-layer.env"
  log "Created $TEST_DIR/ai-layer.env. Fill in the LLM API keys."
fi

for unit in mddoai-test-update.service mddoai-test-update.timer; do
  sed -e "s|@DEV_USER@|$DEV_USER|" -e "s|@TEST_DIR@|$TEST_DIR|" "$SRC/$unit" >"$TMP_DIR/$unit"
  install_if_changed "$TMP_DIR/$unit" "/etc/systemd/system/$unit" 0644 || true
done
systemctl daemon-reload
