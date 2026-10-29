# Installs VS Code's Remote - SSH extension on this PC, if it is missing.
# With -Optional, a missing `code` command is a warning instead of an error,
# for runs that do not open VS Code.
param([switch]$Optional)
. (Join-Path $PSScriptRoot '..\lib\common.ps1')

$extension = 'ms-vscode-remote.remote-ssh'
if (-not (Get-Command code -ErrorAction SilentlyContinue)) {
  $message = "The 'code' command is not on PATH. VS Code's Windows setup adds it; restart the terminal after installing, or reinstall VS Code."
  if ($Optional) { Write-Warning $message; return }
  throw $message
}

$installed = & code --list-extensions
if ($installed -contains $extension) {
  Write-Host "VS Code extension $extension is installed."
  return
}
Invoke-Native { code --install-extension $extension } "Installing $extension"
