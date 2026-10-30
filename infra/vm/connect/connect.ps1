# Connects this PC to the VM: lets your current IP through the firewall,
# installs VS Code's Remote - SSH extension, writes the SSH config entry, and
# opens VS Code on the VM. Idempotent: each step skips work already done.
#
#   .\infra\vm\connect\connect.ps1                   # everything
#   .\infra\vm\connect\connect.ps1 -RunSetup         # also (re)run the VM setup script
#   .\infra\vm\connect\connect.ps1 -NoOpen           # don't open VS Code
param(
  [switch]$RunSetup,
  [switch]$NoOpen
)
$ErrorActionPreference = 'Stop'
$steps = Join-Path $PSScriptRoot 'steps'

Write-Host '==> 10-firewall-ip'
& (Join-Path $steps '10-firewall-ip.ps1')
Write-Host '==> 20-vscode-extension'
& (Join-Path $steps '20-vscode-extension.ps1') -Optional:$NoOpen
Write-Host '==> 30-ssh-config'
& (Join-Path $steps '30-ssh-config.ps1')
if ($RunSetup) {
  Write-Host '==> 40-run-setup'
  & (Join-Path $steps '40-run-setup.ps1')
}
if (-not $NoOpen) {
  Write-Host '==> 50-open-vscode'
  & (Join-Path $steps '50-open-vscode.ps1')
}
Write-Host 'Done.'
