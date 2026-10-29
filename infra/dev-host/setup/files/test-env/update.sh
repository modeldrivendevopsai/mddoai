#!/usr/bin/env bash
# Runs the Test environment on the newest commit of its branch. The timer
# runs it every few minutes. To run it now and see what it did:
#
#   sudo systemctl start mddoai-test-update
#   journalctl -u mddoai-test-update
#
# A commit that fails to build leaves the previous containers running, and
# the next run tries again.
set -euo pipefail

SETTINGS=/opt/mddoai-test/.env
CLONE="$HOME/mddoai-test"
DEPLOYED="$HOME/mddoai-test.deployed"
source "$SETTINGS"

[[ -d "$CLONE" ]] || git clone --quiet "$REPO_URL" "$CLONE"
cd "$CLONE"

# The branch chosen with the "Test environment branch" workflow. TEST_BRANCH
# when none was chosen or the chosen one was deleted. One fetch, so a network
# failure stops the run instead of switching branch.
git fetch --quiet --prune origin
chosen="$(git show origin/test-env-config:branch 2>/dev/null)" || chosen="$TEST_BRANCH"
git rev-parse --quiet --verify "origin/$chosen^{commit}" >/dev/null || chosen="$TEST_BRANCH"
commit="$(git rev-parse "origin/$chosen")"

# Nothing new, and the stack is up.
if [[ "$commit" == "$(cat "$DEPLOYED" 2>/dev/null)" ]] && (cd ai && [[ -n "$(docker compose ps --quiet --status running ui-host)" ]]); then
  exit 0
fi

# Without these settings the stack would take the dev stack's ports.
if ! git grep --quiet UI_PORT "$commit" -- ai/docker-compose.yml; then
  echo "$chosen does not have the configurable stack ports, so it cannot run here." >&2
  exit 1
fi

echo "Starting $chosen at $commit"
git checkout --quiet --force "$commit"
cd ai
ln -sfn "$SETTINGS" .env
# The dev clone's LLM API keys.
ln -sfn "$HOME/mddoai/ai/ai-layer/.env" ai-layer/.env

if [[ "$commit" != "$(cat "$DEPLOYED" 2>/dev/null)" ]]; then
  docker compose build --quiet
  # The images this build replaced.
  docker image prune --force >/dev/null
fi
docker compose up --detach --wait --remove-orphans
echo "$commit" >"$DEPLOYED"
echo "Running $chosen at $commit"
