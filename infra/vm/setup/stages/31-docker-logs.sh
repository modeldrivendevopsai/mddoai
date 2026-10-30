#!/usr/bin/env bash
# Makes Docker rotate container logs. By default Docker does not, and the
# stack's chatty dev servers would slowly fill the disk. The config switches to
# Docker's "local" log driver, the one Docker recommends. Its defaults keep
# about 100 MB per container (5 compressed files of 20 MB each), and
# `docker logs` still works with it.
set -euo pipefail
# shellcheck source=../lib/common.sh
source "$(dirname "${BASH_SOURCE[0]}")/../lib/common.sh"
require_root

if install_if_changed "$FILES_DIR/docker-daemon.json" /etc/docker/daemon.json 0644; then
  # Restarting Docker stops running containers. It happens only when the
  # config changed. The new log settings apply to containers created after
  # this point; recreate the stack (docker compose up -d --force-recreate)
  # to move existing containers over.
  systemctl restart docker
  log "Docker restarted with the new log settings"
fi
