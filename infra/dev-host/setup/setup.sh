#!/usr/bin/env bash
# Sets up the dev host: runs every stage in stages/ in name order.
# Run as root on the VM. Idempotent: each stage skips work already done.
#
#   sudo bash setup.sh             # all stages
#   sudo bash stages/30-docker.sh  # one stage on its own
#
# DEV_USER (default: dev) names the developer's Linux user.
set -euo pipefail

SETUP_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
# shellcheck source=lib/common.sh
source "$SETUP_DIR/lib/common.sh"

require_root

for stage in "$SETUP_DIR"/stages/*.sh; do
  log "==> $(basename "$stage")"
  bash "$stage"
done

log "Setup finished."
if [[ -f /var/run/reboot-required ]]; then
  log "Updates need a reboot to take effect: sudo reboot"
fi
