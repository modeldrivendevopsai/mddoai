#!/usr/bin/env bash
# Runs the Test environment on the newest commit of its branch. The timer
# runs it every few minutes. To run it now and see what it did:
#
#   sudo systemctl start mddoai-test-update
#   journalctl -u mddoai-test-update
set -euo pipefail

# The settings, the keys, the clone and the record of the running commit all
# sit next to this script.
TEST_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
DEPLOYED="$TEST_DIR/deployed"
cd "$TEST_DIR/repo"

# The branch chosen with the repository's "Test environment branch" workflow,
# which writes its name to the file `branch` on the test-env-config branch.
# The repository's default branch when none was chosen or the chosen one was
# deleted. One fetch, so a network failure stops the run instead of switching.
git fetch --quiet --prune origin
chosen="$(git show origin/test-env-config:branch 2>/dev/null)" || chosen=""
if ! git rev-parse --quiet --verify "origin/$chosen^{commit}" >/dev/null; then
  chosen="$(git symbolic-ref --short refs/remotes/origin/HEAD)"
  chosen="${chosen#origin/}"
fi
commit="$(git rev-parse "origin/$chosen")"
deployed="$(cat "$DEPLOYED" 2>/dev/null || true)"

# Nothing new, and the stack is up.
if [[ "$commit" == "$deployed" ]] && (cd ai && [[ -n "$(docker compose ps --quiet --status running ui-host)" ]]); then
  exit 0
fi

echo "Starting $chosen at $commit"
git checkout --quiet --force "$commit"
# The running containers mount files from the clone, so a failed build or
# start puts the clone back on the commit they run.
trap '[[ -n "$deployed" ]] && git checkout --quiet --force "$deployed"' ERR
ln -sfn "$TEST_DIR/.env" ai/.env
ln -sfn "$TEST_DIR/ai-layer.env" ai/ai-layer/.env

cd ai
if [[ "$commit" != "$deployed" ]]; then
  docker compose build --quiet
  # The images this build replaced.
  docker image prune --force >/dev/null
fi
docker compose up --detach --wait --remove-orphans
trap - ERR
echo "$commit" >"$DEPLOYED"
echo "Running $chosen at $commit"
