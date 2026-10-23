# Writes Terraform's ssh_config output into ~/.ssh/config, between marker
# lines. Running it again replaces that block; the rest of the file is kept.
#
# The block goes before the first Host or Match line. ssh uses the first value
# it finds for each setting, so a `Host *` placed earlier would otherwise
# override it, and lines before the first Host apply to every host.
. (Join-Path $PSScriptRoot '..\lib\common.ps1')

$target = Get-SshTarget
$begin = "# >>> $($target.Host) (written by infra/dev-host/connect) >>>"
$end = "# <<< $($target.Host) <<<"

$text = ''
if (Test-Path $SshConfigFile) { $text = [IO.File]::ReadAllText($SshConfigFile) }
$newline = if ($text -match "`r`n" -or -not $text) { "`r`n" } else { "`n" }

# The block, its line ending, and the blank line written after it.
$blockPattern = '(?ms)^' + [regex]::Escape($begin) + '.*?^' + [regex]::Escape($end) + '[ \t]*(\r?\n)?(\r?\n)?'
$outside = [regex]::Replace($text, $blockPattern, '')
# A hand-written entry for the same alias would compete with this block, so
# refuse instead of guessing which one ssh uses.
if ($outside -match ('(?m)^\s*Host\s+(.*\s)?' + [regex]::Escape($target.Host) + '(\s|$)')) {
  throw "$SshConfigFile already has a 'Host $($target.Host)' entry outside the managed block. Remove it, then run again."
}

$body = ($target.Config.Trim() -split '\r?\n') -join $newline
$block = $begin + $newline + $body + $newline + $end + $newline

$firstHost = [regex]::Match($outside, '(?mi)^[ \t]*(Host|Match)\s')
if ($firstHost.Success) {
  $updated = $outside.Substring(0, $firstHost.Index) + $block + $newline + $outside.Substring($firstHost.Index)
} elseif ($outside.Trim()) {
  $updated = $outside.TrimEnd() + $newline + $newline + $block
} else {
  $updated = $block
}

if ($updated -eq $text) {
  Write-Log "$SshConfigFile is up to date."
  return
}
New-Item -ItemType Directory -Force (Split-Path $SshConfigFile) | Out-Null
# OpenSSH reads the file as plain text, so write it without a byte order mark.
[IO.File]::WriteAllText($SshConfigFile, $updated, (New-Object Text.UTF8Encoding($false)))
Write-Log "Updated the $($target.Host) entry in $SshConfigFile."
