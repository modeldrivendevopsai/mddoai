# Helpers shared by connect.ps1 and every step. Dot-source it, don't run it.

$ErrorActionPreference = 'Stop'

$ConnectDir = Split-Path -Parent $PSScriptRoot
$DevHostDir = Split-Path -Parent $ConnectDir
$TerraformDir = Join-Path $DevHostDir 'terraform'
$SetupDir = Join-Path $DevHostDir 'setup'
$TfvarsFile = Join-Path $TerraformDir 'terraform.tfvars'
$SshConfigFile = Join-Path $HOME '.ssh\config'

function Write-Log([string]$Message) {
  Write-Host $Message
}

# Runs a native command and stops on a non-zero exit code, which PowerShell
# 5.1 otherwise ignores. Success is judged by the exit code alone: when the
# caller redirects output, 5.1 turns each stderr line (warnings included) into
# an error record, which must not abort the step.
function Invoke-Native([scriptblock]$Command, [string]$What) {
  $previous = $ErrorActionPreference
  $ErrorActionPreference = 'Continue'
  try {
    & $Command
  } finally {
    $ErrorActionPreference = $previous
  }
  if ($LASTEXITCODE -ne 0) { throw "$What failed (exit code $LASTEXITCODE)." }
}

# Reads one Terraform output as text. Outputs come from the local state file,
# so this needs no API token.
function Get-TerraformOutput([string]$Name) {
  Push-Location $TerraformDir
  try {
    $value = & terraform output -raw $Name
    if ($LASTEXITCODE -ne 0) { throw "terraform output $Name failed. Has terraform apply run?" }
    return ($value -join "`n")
  } finally {
    Pop-Location
  }
}

# The Host alias and user from the ssh_config output, so later steps use the
# same names Terraform was given.
function Get-SshTarget {
  $config = Get-TerraformOutput 'ssh_config'
  $hostAlias = [regex]::Match($config, '(?m)^\s*Host\s+(\S+)').Groups[1].Value
  $user = [regex]::Match($config, '(?m)^\s*User\s+(\S+)').Groups[1].Value
  if (-not $hostAlias -or -not $user) { throw 'Could not read Host and User from the ssh_config output.' }
  return [pscustomobject]@{ Host = $hostAlias; User = $user; Config = $config }
}
