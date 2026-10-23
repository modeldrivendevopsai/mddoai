# Opens VS Code connected to the VM, in the developer's home folder.
. (Join-Path $PSScriptRoot '..\lib\common.ps1')

$target = Get-SshTarget
$folder = "/home/$($target.User)"
Write-Log "Opening VS Code on $($target.Host):$folder"
Invoke-Native { code --remote "ssh-remote+$($target.Host)" $folder } 'Opening VS Code'
