locals {
  ssh_public_key = trimspace(file(pathexpand(var.ssh_public_key_path)))
}

resource "hcloud_ssh_key" "dev" {
  name       = var.server_name
  public_key = local.ssh_public_key

  lifecycle {
    precondition {
      condition     = length(split("\n", local.ssh_public_key)) == 1
      error_message = "The SSH public key file must hold exactly one key on one line."
    }
  }
}

# A separate primary IP outlives the server. Rebuilding the server (to check
# it can be recreated from scratch, or after a broken setup) keeps the same address, so SSH
# configs pointing at it stay valid. auto_delete stays off, as the provider
# recommends for a Terraform-managed IP.
resource "hcloud_primary_ip" "dev" {
  name              = var.server_name
  type              = "ipv4"
  location          = var.location
  auto_delete       = false
  delete_protection = var.protection
}

# Login is by SSH key only. Hetzner installs the key for root and, because a
# key is given, issues no root password. Configuring sshd itself is left to
# the setup script on purpose, so all on-server setup lives in one place.
resource "hcloud_server" "dev" {
  name         = var.server_name
  server_type  = var.server_type
  image        = var.image
  location     = var.location
  ssh_keys     = [hcloud_ssh_key.dev.id]
  firewall_ids = [hcloud_firewall.dev.id]
  backups      = var.backups

  delete_protection  = var.protection
  rebuild_protection = var.protection

  public_net {
    ipv4_enabled = true
    ipv4         = hcloud_primary_ip.dev.id
    ipv6_enabled = true
  }

  # The key list only takes effect when a server is created, and changing it
  # makes Terraform destroy and recreate the server, losing everything on its
  # disk. Ignore it on purpose. To apply a change, rebuild deliberately: lift
  # the protection first, then terraform apply -replace=hcloud_server.dev
  lifecycle {
    ignore_changes = [ssh_keys]
  }
}
