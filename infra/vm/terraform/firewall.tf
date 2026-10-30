# The only way in: SSH from the developer addresses. The app is reached
# through SSH port forwarding, so no web port is opened. Hetzner drops
# inbound traffic that matches no rule.
resource "hcloud_firewall" "dev" {
  name = var.server_name

  rule {
    description = "SSH from developer addresses"
    direction   = "in"
    protocol    = "tcp"
    port        = "22"
    source_ips  = var.ssh_allowed_cidrs
  }
}
