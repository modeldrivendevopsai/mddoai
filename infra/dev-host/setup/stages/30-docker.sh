#!/usr/bin/env bash
# Installs Docker Engine with the Compose and Buildx plugins from Docker's own
# apt repository, following Docker's install guide for Ubuntu. Later updates
# arrive with the normal apt upgrade.
set -euo pipefail
# shellcheck source=../lib/common.sh
source "$(dirname "${BASH_SOURCE[0]}")/../lib/common.sh"
require_root

# Download to scratch space first, so an interrupted download never leaves a
# broken key where apt reads it.
key_tmp="$TMP_DIR/docker.asc"
curl -fsSL https://download.docker.com/linux/ubuntu/gpg -o "$key_tmp"
install_if_changed "$key_tmp" /etc/apt/keyrings/docker.asc 0644 || true

# shellcheck source=/dev/null
source /etc/os-release
sources_tmp="$TMP_DIR/docker.sources"
cat >"$sources_tmp" <<EOF
Types: deb
URIs: https://download.docker.com/linux/ubuntu
Suites: ${UBUNTU_CODENAME:-$VERSION_CODENAME}
Components: stable
Architectures: $(dpkg --print-architecture)
Signed-By: /etc/apt/keyrings/docker.asc
EOF
install_if_changed "$sources_tmp" /etc/apt/sources.list.d/docker.sources 0644 || true

apt_update
apt_install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
systemctl enable --now docker

# Lets the developer run docker without sudo. Membership of the docker group
# is equivalent to root on this machine.
usermod -aG docker "$DEV_USER"
log "Docker: $(docker --version); $(docker compose version)"
