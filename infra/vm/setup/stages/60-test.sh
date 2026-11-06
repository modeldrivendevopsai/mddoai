#!/usr/bin/env bash
# Installs the Test environment switched off: a clone of the repository in
# TEST_DIR, and the updater that keeps it on the newest main. To start it, fill
# in its config.env and LLM keys in the clone, then run:
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
UPDATER=/usr/local/bin/mddoai-test-update
REPO_URL=https://github.com/modeldrivendevopsai/mddoai.git
TEMPLATE="$SETUP_DIR/../../environments/templates/public/config.env"
# Where the stack looks for them, in the clone. Both are gitignored, so a
# checkout leaves them alone.
CONFIG="$TEST_DIR/infra/environments/test/config.env"
LLM_KEYS="$TEST_DIR/ai/ai-layer/.env"
# The folders the Test environment's config.env names, all under the clone's
# ai/data. The repository ships the starting platforms and prompt configs there,
# and git ignores the rest, so an update leaves it alone. The workspace is made
# for the developer, who adds models to it. Docker makes the others.
DATA_DIR="$TEST_DIR/ai/data"
WORKSPACE_DIR="$DATA_DIR/workspace"

# The clone belongs to the developer's user, who runs the updater.
install -d -m 0755 -o "$DEV_USER" -g "$DEV_USER" "$TEST_DIR"
if [[ ! -d "$TEST_DIR/.git" ]]; then
  sudo -u "$DEV_USER" git clone --quiet "$REPO_URL" "$TEST_DIR"
  log "Cloned $REPO_URL into $TEST_DIR"
fi

# Created once and never overwritten, so the values filled in are kept.
if [[ ! -f "$CONFIG" ]]; then
  install -D -m 0600 -o "$DEV_USER" -g "$DEV_USER" "$TEMPLATE" "$CONFIG"
  # oauth2-proxy's documented way to make one.
  cookie_secret="$(openssl rand -base64 32 | tr -- '+/' '-_')"
  sed -i "s|^OAUTH2_COOKIE_SECRET=.*|OAUTH2_COOKIE_SECRET=$cookie_secret|" "$CONFIG"
  log "Created $CONFIG. Fill it in."
fi
# The folders the stack requires. Each is set only when missing or empty, so a
# config.env made before they existed gets them too and one that already has a
# value keeps it. Compose refuses to start without them.
set_if_empty() {
  local name="$1" value="$2"
  grep -Eq "^${name}=[[:space:]\"']*[^[:space:]\"']" "$CONFIG" && return 0
  if grep -q "^${name}=" "$CONFIG"; then
    sed -i "s|^${name}=.*|${name}=${value}|" "$CONFIG"
  else
    [[ -z "$(tail -c1 "$CONFIG")" ]] || echo >>"$CONFIG"
    echo "${name}=${value}" >>"$CONFIG"
  fi
  log "Set $name in $CONFIG"
}
set_if_empty MDDOAI_WORKSPACE_DIR "$WORKSPACE_DIR"
set_if_empty MDDOAI_PLATFORMS_DIR "$DATA_DIR/platforms"
for agent in psm atl acceleo; do
  upper="${agent^^}"
  set_if_empty "MDDOAI_${upper}_PROMPTS_DIR" "$DATA_DIR/prompts/$agent"
  set_if_empty "MDDOAI_${upper}_ATTACHMENTS_DIR" "$DATA_DIR/attachments/$agent"
done
set_if_empty MDDOAI_RUNS_DIR "$DATA_DIR/runs"
set_if_empty MDDOAI_VALIDATOR_OUTPUT_DIR "$DATA_DIR/validator-output"
set_if_empty MDDOAI_PIPELINES_DIR "$DATA_DIR/pipelines"
# Docker would create a missing workspace owned by root, which the developer
# could not add models to, so setup makes it for them.
install -d -o "$DEV_USER" -g "$DEV_USER" "$WORKSPACE_DIR"
if [[ ! -f "$LLM_KEYS" ]]; then
  install -m 0600 -o "$DEV_USER" -g "$DEV_USER" "$TEST_DIR/ai/ai-layer/.env.example" "$LLM_KEYS"
  log "Created $LLM_KEYS. Fill in the LLM API keys."
fi

# Outside the clone, which every update replaces.
install_if_changed "$FILES_DIR/test-updater/update.sh" "$UPDATER" 0755 || true
for unit in mddoai-test-update.service mddoai-test-update.timer; do
  sed -e "s|@DEV_USER@|$DEV_USER|" -e "s|@UPDATER@|$UPDATER|" -e "s|@TEST_DIR@|$TEST_DIR|" "$FILES_DIR/test-updater/$unit" >"$TMP_DIR/$unit"
  install_if_changed "$TMP_DIR/$unit" "/etc/systemd/system/$unit" 0644 || true
done
systemctl daemon-reload
