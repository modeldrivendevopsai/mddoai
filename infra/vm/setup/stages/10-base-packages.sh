#!/usr/bin/env bash
# Installs pending updates and the basic tools later stages need.
set -euo pipefail
# shellcheck source=../lib/common.sh
source "$(dirname "${BASH_SOURCE[0]}")/../lib/common.sh"
require_root

apt_update
apt-get upgrade "${APT_OPTS[@]}"
apt_install ca-certificates curl git gnupg
