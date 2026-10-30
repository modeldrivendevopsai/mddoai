# Lets this PC's current public IPv4 address through the VM's firewall. It
# writes the address to terraform.tfvars and runs terraform apply, which shows
# the plan and asks before changing anything. If the apply does not complete,
# terraform.tfvars is put back as it was.
. (Join-Path $PSScriptRoot '..\lib\common.ps1')

# Answers with the caller's IPv4 address only, which is what the /32 firewall
# rule and the IPv4 SSH config need.
$IpLookupUrl = 'https://api.ipify.org'
# It normally answers in well under a second (engineering guess).
$IpLookupTimeoutSec = 20

$cidr = "$((Invoke-RestMethod $IpLookupUrl -TimeoutSec $IpLookupTimeoutSec).Trim())/32"
$original = [IO.File]::ReadAllText($TfvarsFile)
$line = [regex]::Match($original, '(?m)^ssh_allowed_cidrs\s*=\s*\[[^\]\r\n]*\]')
if (-not $line.Success) { throw "No one-line ssh_allowed_cidrs = [...] in $TfvarsFile." }
if ($line.Value.Contains("`"$cidr`"")) {
  Write-Host "terraform.tfvars already lists $cidr."
  return
}
# Replacing the only address is safe. With several, one may be another
# developer's, so the list is edited by hand instead.
if ([regex]::Matches($line.Value, '"[^"]*"').Count -ne 1) {
  throw "ssh_allowed_cidrs lists several addresses. Add $cidr in $TfvarsFile by hand, then run terraform apply."
}
$updated = $original.Remove($line.Index, $line.Length).Insert($line.Index, "ssh_allowed_cidrs = [`"$cidr`"]")

$applied = $false
try {
  [IO.File]::WriteAllText($TfvarsFile, $updated)
  Write-Host "Allowing $cidr. The plan should only update hcloud_firewall.dev."
  Invoke-Native { terraform "-chdir=$TerraformDir" apply } 'terraform apply'
  $applied = $true
} finally {
  # finally, not catch, so this also runs after Ctrl+C at the prompt.
  if (-not $applied) {
    [IO.File]::WriteAllText($TfvarsFile, $original)
    Write-Host 'The firewall was not updated, so terraform.tfvars was put back as it was.'
  }
}
