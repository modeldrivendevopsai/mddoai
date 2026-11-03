#!/usr/bin/env bash
# Installs the Test environment in TEST_DIR, switched off. To start it, fill in
# its config.env and LLM keys in the clone, then run:
#
#   sudo systemctl enable --now mddoai-test-update.timer
#
# Its updater comes from files/test-updater/, and its config.env from
# infra/environments/templates/public/, copied in the same layout as the
# repository.
set -euo pipefail
# shellcheck source=../lib/common.sh
source "$(dirname "${BASH_SOURCE[0]}")/../lib/common.sh"
require_root

TEST_DIR=/opt/mddoai-test
REPO_URL=https://github.com/modeldrivendevopsai/mddoai.git
TEMPLATE="$SETUP_DIR/../../environments/templates/public/config.env"
# Where the stack looks for them, in the clone. Both are gitignored, so a
# checkout leaves them alone.
CONFIG="$TEST_DIR/repo/infra/environments/test/config.env"
LLM_KEYS="$TEST_DIR/repo/ai/ai-layer/.env"

# All of it belongs to the developer's user, who runs update.sh.
install -d -m 0755 -o "$DEV_USER" -g "$DEV_USER" "$TEST_DIR"
install -m 0755 -o "$DEV_USER" -g "$DEV_USER" "$FILES_DIR/test-updater/update.sh" "$TEST_DIR/"

if [[ ! -d "$TEST_DIR/repo/.git" ]]; then
  sudo -u "$DEV_USER" git clone --quiet "$REPO_URL" "$TEST_DIR/repo"
  log "Cloned $REPO_URL into $TEST_DIR/repo"
fi

# Created once and never overwritten, so the values filled in are kept.
if [[ ! -f "$CONFIG" ]]; then
  install -D -m 0600 -o "$DEV_USER" -g "$DEV_USER" "$TEMPLATE" "$CONFIG"
  # oauth2-proxy's documented way to make one.
  cookie_secret="$(openssl rand -base64 32 | tr -- '+/' '-_')"
  sed -i "s|^OAUTH2_COOKIE_SECRET=.*|OAUTH2_COOKIE_SECRET=$cookie_secret|" "$CONFIG"
  log "Created $CONFIG. Fill it in."
fi
if [[ ! -f "$LLM_KEYS" ]]; then
  install -m 0600 -o "$DEV_USER" -g "$DEV_USER" "$TEST_DIR/repo/ai/ai-layer/.env.example" "$LLM_KEYS"
  log "Created $LLM_KEYS. Fill in the LLM API keys."
fi

for unit in mddoai-test-update.service mddoai-test-update.timer; do
  sed -e "s|@DEV_USER@|$DEV_USER|" -e "s|@TEST_DIR@|$TEST_DIR|" "$FILES_DIR/test-updater/$unit" >"$TMP_DIR/$unit"
  install_if_changed "$TMP_DIR/$unit" "/etc/systemd/system/$unit" 0644 || true
done
systemctl daemon-reload
