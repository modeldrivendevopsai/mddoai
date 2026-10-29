# Copies setup/ and test-env/ to the VM and runs the setup script there as the
# developer's user, with sudo. Needs the ssh_config entry, so it runs after
# that step.
. (Join-Path $PSScriptRoot '..\lib\common.ps1')

$sshHost = Get-TerraformOutput 'ssh_host'
$devUser = Get-TerraformOutput 'dev_user'
$remoteDir = 'dev-host'

# Clear any earlier copy first: scp would otherwise copy into the old folder,
# and files deleted from the repository would still run.
Invoke-Native { ssh $sshHost "rm -rf $remoteDir && mkdir $remoteDir" } 'Clearing the old copy'
Invoke-Native { scp -q -r $SetupDir $TestEnvDir "${sshHost}:$remoteDir/" } 'Copying setup and test-env to the VM'
# DEV_USER goes after sudo, which would otherwise drop it, so setup keeps
# working on the same user instead of creating the default one.
Invoke-Native { ssh $sshHost "sudo DEV_USER=$devUser bash $remoteDir/setup/setup.sh" } 'Running the setup script'
