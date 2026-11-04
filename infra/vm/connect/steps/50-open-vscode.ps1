# Opens VS Code connected to the VM, in the developer's home folder.
. (Join-Path $PSScriptRoot '..\lib\common.ps1')

$sshHost = Get-TerraformOutput 'ssh_host'
$folder = "/home/$(Get-TerraformOutput 'dev_user')"
Write-Host "Opening VS Code on ${sshHost}:$folder"
Invoke-Native { code --remote "ssh-remote+$sshHost" $folder } 'Opening VS Code'
