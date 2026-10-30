# Helpers shared by setup.sh and every stage. Source it, don't run it.

SETUP_DIR="${SETUP_DIR:-$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)}"
FILES_DIR="$SETUP_DIR/files"
DEV_USER="${DEV_USER:-dev}"
export SETUP_DIR FILES_DIR DEV_USER

# Scratch space for this process, removed when it exits, even after an error.
TMP_DIR="$(mktemp -d)"
trap 'rm -rf "$TMP_DIR"' EXIT

# apt must never stop to ask a question, and must keep config files we
# changed when a package update ships a new default.
export DEBIAN_FRONTEND=noninteractive
# Ubuntu runs needrestart after package updates. List mode only reports
# services running outdated code instead of stopping to ask what to restart.
export NEEDRESTART_MODE=l
# Seconds to wait for another apt run to finish. Right after boot, Ubuntu's
# own daily apt jobs often hold the lock for a few minutes. Engineering guess.
APT_LOCK_TIMEOUT=600
APT_OPTS=(-y -o "DPkg::Lock::Timeout=$APT_LOCK_TIMEOUT"
  -o Dpkg::Options::=--force-confdef -o Dpkg::Options::=--force-confold)

log() {
  printf '%s\n' "$*"
}

die() {
  printf 'ERROR: %s\n' "$*" >&2
  exit 1
}

# The same rule Terraform's dev_user variable enforces. It also keeps the name
# free of dots: sudo silently ignores files in sudoers.d whose name has one.
[[ $DEV_USER =~ ^[a-z_][a-z0-9_-]*$ ]] || die "DEV_USER '$DEV_USER' is not a plain Linux user name."

require_root() {
  [[ $EUID -eq 0 ]] || die "Run as root (sudo bash $0)."
}

apt_update() {
  apt-get -o "DPkg::Lock::Timeout=$APT_LOCK_TIMEOUT" update
}

apt_install() {
  apt-get install "${APT_OPTS[@]}" "$@"
}

# Copies src to dest with the given mode, only if the content differs.
# Returns 0 when the file changed, 1 when it was already up to date, so
# callers can restart a service only when needed. Callers use it in `if` or
# with `|| true`, where bash ignores `set -e`, so failures exit explicitly.
install_if_changed() {
  local src="$1" dest="$2" mode="$3"
  if [[ -f "$dest" ]] && cmp -s "$src" "$dest"; then
    return 1
  fi
  install -D -m "$mode" "$src" "$dest" || die "Could not install $dest."
  log "Updated $dest"
  return 0
}
