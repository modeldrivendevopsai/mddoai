output "ipv4_address" {
  description = "The VM's public IPv4 address. It stays the same when the server is rebuilt."
  value       = hcloud_primary_ip.dev.ip_address
}

output "ipv6_address" {
  description = "The VM's public IPv6 address. It changes when the server is rebuilt."
  value       = hcloud_server.dev.ipv6_address
}

locals {
  ui_forwards = join("\n", [for port in var.ui_ports : "  LocalForward ${port} 127.0.0.1:${port}"])
}

# ForwardAgent lets git on the VM use the developer's GitHub key from the
# ssh-agent on their PC while connected, so no GitHub key is stored on the VM.
output "ssh_config" {
  description = "A block to paste into ~/.ssh/config on the developer's PC."
  value       = <<-EOT
    Host ${var.server_name}
      HostName ${hcloud_primary_ip.dev.ip_address}
      User ${var.dev_user}
      IdentityFile "${trimsuffix(var.ssh_public_key_path, ".pub")}"
      IdentitiesOnly yes
      ForwardAgent yes
    ${local.ui_forwards}
  EOT
}

output "ssh_host" {
  description = "The SSH host alias the ssh_config output defines."
  value       = var.server_name
}

output "dev_user" {
  description = "The Linux user the ssh_config output logs in as."
  value       = var.dev_user
}
