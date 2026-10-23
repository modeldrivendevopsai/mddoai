# Copies setup/ to the VM and runs it there as the developer's user, with sudo.
# Needs the ssh_config entry, so it runs after that step.
. (Join-Path $PSScriptRoot '..\lib\common.ps1')

$target = Get-SshTarget
$remoteDir = 'dev-host-setup'

# Clear any earlier copy first: scp would otherwise copy into the old folder,
# and stage files deleted from the repository would still run.
Invoke-Native { ssh $target.Host "rm -rf $remoteDir" } 'Clearing the old setup copy'
Invoke-Native { scp -q -r $SetupDir "$($target.Host):$remoteDir" } 'Copying setup to the VM'
# DEV_USER goes after sudo, which would otherwise drop it, so setup keeps
# working on the same user instead of creating the default one.
Invoke-Native { ssh $target.Host "sudo DEV_USER=$($target.User) bash $remoteDir/setup.sh" } 'Running the setup script'
