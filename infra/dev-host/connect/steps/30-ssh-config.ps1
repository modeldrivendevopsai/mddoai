# Writes Terraform's ssh_config output to its own file, ~/.ssh/<host>.conf,
# and makes ~/.ssh/config include it. Running it again rewrites that file;
# the rest of ~/.ssh/config is never changed.
. (Join-Path $PSScriptRoot '..\lib\common.ps1')

$target = Get-SshTarget
$hostFile = Join-Path (Split-Path $SshConfigFile) "$($target.Host).conf"
New-Item -ItemType Directory -Force (Split-Path $SshConfigFile) | Out-Null
# OpenSSH reads plain text, so no byte order mark.
[IO.File]::WriteAllText($hostFile, $target.Config, (New-Object Text.UTF8Encoding($false)))

$include = "Include $($target.Host).conf"
$config = if (Test-Path $SshConfigFile) { [IO.File]::ReadAllText($SshConfigFile) } else { '' }
if ($config -match "(?m)^$([regex]::Escape($include))\s*$") {
  Write-Log "$SshConfigFile already includes $hostFile."
  return
}
# At the top: ssh uses the first value it finds for each setting, and an
# Include placed after a Host line would apply to that host only.
[IO.File]::WriteAllText($SshConfigFile, "$include`r`n`r`n$config", (New-Object Text.UTF8Encoding($false)))
Write-Log "Added '$include' to the top of $SshConfigFile."
