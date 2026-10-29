# Helpers shared by connect.ps1 and every step. Dot-source it, don't run it.

$ErrorActionPreference = 'Stop'

$ConnectDir = Split-Path -Parent $PSScriptRoot
$DevHostDir = Split-Path -Parent $ConnectDir
$TerraformDir = Join-Path $DevHostDir 'terraform'
$SetupDir = Join-Path $DevHostDir 'setup'
$TestEnvDir = Join-Path $DevHostDir 'test-env'
$TfvarsFile = Join-Path $TerraformDir 'terraform.tfvars'
$SshConfigFile = Join-Path $HOME '.ssh\config'

# Runs a native command and stops on a non-zero exit code, which PowerShell
# 5.1 otherwise ignores. Success is judged by the exit code alone: when the
# caller redirects output, 5.1 turns each stderr line (warnings included) into
# an error record, which must not abort the step.
function Invoke-Native([scriptblock]$Command, [string]$What) {
  $previous = $ErrorActionPreference
  $ErrorActionPreference = 'Continue'
  # So a command that never ran (not installed) cannot pass on the exit code
  # of an earlier program.
  $global:LASTEXITCODE = -1
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
  $value = & terraform "-chdir=$TerraformDir" output -raw $Name
  if ($LASTEXITCODE -ne 0) { throw "terraform output $Name failed. Has terraform apply run?" }
  return ($value -join "`n")
}
