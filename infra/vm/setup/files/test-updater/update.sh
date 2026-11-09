#!/usr/bin/env bash
# Runs the Test environment on the newest commit of main. Setup installs this
# as /usr/local/bin/mddoai-test-update, outside the clone it updates, because
# a checkout replaces the clone's files and a script must not change while it
# runs. The timer runs it every few minutes. To run it now and see what it did:
#
#   sudo systemctl start mddoai-test-update
#   journalctl -u mddoai-test-update
#
# Usage: mddoai-test-update <folder of the Test clone>
#
# It resets that clone to a commit of main, so never point it at a clone with
# work in it.
set -euo pipefail

TEST_DIR="${1:?Usage: $0 <folder of the Test clone>}"
# The Test environment's folder in the clone, where docker compose runs.
ENV_DIR=infra/environments/test
cd "$TEST_DIR"

# main is the repository's default branch. One fetch, so a network failure
# stops the run instead of changing anything.
git fetch --quiet --prune origin
commit="$(git rev-parse origin/HEAD)"
# The clone stays on the commit that runs, so its HEAD is the running commit.
deployed="$(git rev-parse HEAD)"

# Nothing new, and the stack is up.
if [[ "$commit" == "$deployed" ]] && [[ -n "$(cd "$ENV_DIR" && docker compose ps --quiet --status running ui-host)" ]]; then
  exit 0
fi

echo "Starting $commit"
# The running containers mount files from the clone, so a failed checkout,
# build or start puts the clone back on the commit they run. A checkout that
# fails part way leaves a mix of both commits behind.
trap 'git checkout --quiet --force "$deployed"' ERR
git checkout --quiet --force "$commit"

cd "$ENV_DIR"
if [[ "$commit" != "$deployed" ]]; then
  docker compose build --quiet
  # The images this build replaced.
  docker image prune --force >/dev/null
fi
# On a new clone, nothing is built yet: up builds what is missing.
docker compose up --detach --wait --remove-orphans
trap - ERR
echo "Running $commit"
