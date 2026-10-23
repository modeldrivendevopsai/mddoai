# Lets this PC's current public IPv4 address through the VM's firewall.
# Whether that is already done is judged by Terraform's applied state, not by
# terraform.tfvars, so an apply that failed is retried on the next run. If the
# apply does not succeed, terraform.tfvars is put back as it was. The plan must
# update the firewall and nothing else.
param([string]$EnvFile)
. (Join-Path $PSScriptRoot '..\lib\common.ps1')

# Answers with the caller's IPv4 address only, which matches the firewall rule.
$IpLookupUrl = 'https://api.ipify.org'
# Seconds to wait for that answer. Engineering guess; it normally answers in
# well under a second.
$IpLookupTimeoutSec = 20

function Get-PublicIPv4 {
  $answer = "$(Invoke-RestMethod -Uri $IpLookupUrl -TimeoutSec $IpLookupTimeoutSec)".Trim()
  $parsed = $null
  $isIPv4 = [System.Net.IPAddress]::TryParse($answer, [ref]$parsed) -and
    $parsed.AddressFamily -eq [System.Net.Sockets.AddressFamily]::InterNetwork -and
    $parsed.ToString() -eq $answer
  if (-not $isIPv4) { throw "Unexpected answer from ${IpLookupUrl}: '$answer'." }
  return $answer
}

# The source addresses of the firewall as last applied, read from the local
# state file. Needs no API token.
function Get-AppliedCidrs {
  Push-Location $TerraformDir
  try {
    $json = & terraform show -json
    if ($LASTEXITCODE -ne 0) { throw 'terraform show failed.' }
  } finally {
    Pop-Location
  }
  $state = ($json -join "`n") | ConvertFrom-Json
  $firewall = @($state.values.root_module.resources | Where-Object { $_.address -eq 'hcloud_firewall.dev' })
  if ($firewall.Count -ne 1) { throw 'hcloud_firewall.dev is not in the Terraform state. Has terraform apply run?' }
  return @($firewall[0].values.rule | ForEach-Object { $_.source_ips })
}

# The ssh_allowed_cidrs line in terraform.tfvars, and the addresses on it.
function Get-TfvarsEntry([string]$Text) {
  $match = [regex]::Match($Text, '(?m)^ssh_allowed_cidrs\s*=\s*\[([^\]]*)\]')
  if (-not $match.Success) { throw "No ssh_allowed_cidrs line found in $TfvarsFile." }
  $cidrs = @([regex]::Matches($match.Groups[1].Value, '"([^"]+)"') | ForEach-Object { $_.Groups[1].Value })
  return [pscustomobject]@{ Match = $match; Cidrs = $cidrs }
}

# Sets HCLOUD_TOKEN for this run. A file given with -EnvFile wins over an
# already set variable, because naming a file is the more explicit choice.
function Set-Token([string]$Path) {
  if ($Path) {
    $line = Get-Content $Path | Where-Object { $_ -cmatch '^\s*(export\s+)?HCLOUD_TOKEN=' } | Select-Object -First 1
    if (-not $line) { throw "No HCLOUD_TOKEN= line in $Path." }
    $env:HCLOUD_TOKEN = ($line -creplace '^\s*(export\s+)?HCLOUD_TOKEN=', '').Trim().Trim('"', "'")
  }
  if (-not $env:HCLOUD_TOKEN) { throw 'The firewall needs updating, but HCLOUD_TOKEN is not set. Set it, or pass -EnvFile.' }
}

# Plans, checks that only the firewall changes, and applies that exact plan.
function Invoke-FirewallApply {
  $planFile = Join-Path ([IO.Path]::GetTempPath()) 'mddoai-dev-firewall.tfplan'
  Push-Location $TerraformDir
  try {
    Invoke-Native { terraform plan -input=false -no-color -out="$planFile" | Out-Null } 'terraform plan'
    $json = & terraform show -json $planFile
    if ($LASTEXITCODE -ne 0) { throw 'terraform show of the plan failed.' }
    $plan = ($json -join "`n") | ConvertFrom-Json
    $changes = @($plan.resource_changes | Where-Object { ($_.change.actions -join ',') -notin @('no-op', 'read') })
    if ($changes.Count -eq 0) { return }
    $onlyFirewall = $changes.Count -eq 1 -and $changes[0].address -eq 'hcloud_firewall.dev' -and ($changes[0].change.actions -join ',') -eq 'update'
    if (-not $onlyFirewall) {
      $summary = ($changes | ForEach-Object { "$($_.address)=$($_.change.actions -join '+')" }) -join ', '
      throw "Not applying: the plan should only update hcloud_firewall.dev, but it has: $summary"
    }
    Invoke-Native { terraform apply -input=false -no-color $planFile } 'terraform apply'
  } finally {
    Remove-Item $planFile -ErrorAction SilentlyContinue
    Pop-Location
  }
}

function Write-Tfvars([string]$Text) {
  [IO.File]::WriteAllText($TfvarsFile, $Text, (New-Object Text.UTF8Encoding($false)))
}

$cidr = "$(Get-PublicIPv4)/32"
$original = [IO.File]::ReadAllText($TfvarsFile)
$entry = Get-TfvarsEntry $original
$inTfvars = $entry.Cidrs -contains $cidr
$inState = (Get-AppliedCidrs) -contains $cidr
if ($inTfvars -and $inState) {
  Write-Log "Firewall already allows $cidr."
  return
}

$updated = $original
if (-not $inTfvars) {
  # With several addresses listed, there is no way to tell which one was this
  # PC's, so replacing one would risk locking out another developer.
  if ($entry.Cidrs.Count -ne 1) {
    throw "ssh_allowed_cidrs lists $($entry.Cidrs.Count) addresses. Add $cidr in $TfvarsFile by hand, then run terraform apply."
  }
  $m = $entry.Match
  $updated = $original.Substring(0, $m.Index) + "ssh_allowed_cidrs = [`"$cidr`"]" + $original.Substring($m.Index + $m.Length)
  Write-Log "Your IP is now $cidr (was $($entry.Cidrs[0])). Updating the firewall."
} else {
  Write-Log "terraform.tfvars lists $cidr, but the firewall does not have it yet. Applying."
}

$previousToken = $env:HCLOUD_TOKEN
$done = $false
try {
  Set-Token $EnvFile
  if ($updated -ne $original) { Write-Tfvars $updated }
  Invoke-FirewallApply
  $done = $true
} finally {
  if (-not $done -and $updated -ne $original) {
    Write-Tfvars $original
    Write-Log 'The firewall was not updated, so terraform.tfvars was put back as it was.'
  }
  # Leave the caller's session as it was, so a token read from -EnvFile does
  # not stay in the terminal after the script ends.
  if ($null -eq $previousToken) { Remove-Item Env:HCLOUD_TOKEN -ErrorAction SilentlyContinue } else { $env:HCLOUD_TOKEN = $previousToken }
}
