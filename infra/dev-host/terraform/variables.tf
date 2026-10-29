variable "ssh_allowed_cidrs" {
  description = "Developer addresses allowed to reach SSH, in CIDR form: \"203.0.113.7/32\" for one IPv4 address. Nothing else can reach the VM."
  type        = list(string)

  validation {
    condition     = length(var.ssh_allowed_cidrs) > 0 && alltrue([for c in var.ssh_allowed_cidrs : can(cidrhost(c, 0))])
    error_message = "List at least one address, each in CIDR form (for example 203.0.113.7/32)."
  }

  validation {
    condition     = !anytrue([for c in var.ssh_allowed_cidrs : can(cidrhost(c, 0)) && endswith(c, "/0")])
    error_message = "A /0 range opens SSH to the whole internet and defeats the firewall. List developer addresses instead."
  }
}

variable "ssh_public_key_path" {
  description = "Public half of the developer's SSH key. Hetzner installs it for root on the new server. The private key is expected at the same path without .pub."
  type        = string
  default     = "~/.ssh/mddoai_dev.pub"

  validation {
    condition     = endswith(var.ssh_public_key_path, ".pub")
    error_message = "Point at the public key file, ending in .pub."
  }
}

variable "server_name" {
  description = "Name of the server in Hetzner, and the SSH host alias in the ssh_config output. Also used to name the SSH key, firewall and IP."
  type        = string
  default     = "mddoai-dev"
}

# The VM runs the full ai/ Docker Compose stack plus the VS Code Server,
# Claude Code and image builds. cx43, on Hetzner's cost-optimized line, is the
# cheapest type with room for that, but it is not always orderable.
variable "server_type" {
  description = "Hetzner server type."
  type        = string
  default     = "cx43"
}

variable "location" {
  description = "Hetzner location name. Not every server type is offered in every location. Changing it later replaces the server and the IP."
  type        = string
  default     = "nbg1"
}

variable "image" {
  description = "Operating system image. Changing it later replaces the server."
  type        = string
  default     = "ubuntu-24.04"
}

variable "dev_user" {
  description = "Linux user the developer logs in as once the server is set up. The ssh_config output logs in as this user. The setup script creates it and must be given the same name (DEV_USER) if this is changed."
  type        = string
  default     = "dev"

  validation {
    condition     = can(regex("^[a-z_][a-z0-9_-]*$", var.dev_user))
    error_message = "Use a plain Linux user name: lowercase letters, digits, - and _, not starting with a digit or -."
  }
}

# The UI is a host page plus remote panels that the browser loads from their
# own ports, so every one of them must be forwarded, not only the host's.
# The defaults match the ports the ai/ Docker Compose stack publishes.
variable "ui_ports" {
  description = "Ports of the app's UI on the VM (the ui-host page and each UI remote), forwarded to the same ports on the developer's PC by the ssh_config output."
  type        = list(number)
  default     = [5173, 5174, 5175, 5176, 5177, 5178, 5179, 5180, 5181, 5182]
}

# Hetzner refuses to delete a protected resource, and Terraform does not lift
# the protection on its own. Destroying or replacing the server or the IP
# needs an apply with protection = false first.
variable "protection" {
  description = "Delete protection for the server and its IP, plus rebuild protection for the server, so neither can be removed by accident in the Hetzner Console."
  type        = bool
  default     = true
}

variable "backups" {
  description = "Hetzner daily backups of the server's disk. Off by default; turning them on costs extra, as a share of the server price."
  type        = bool
  default     = false
}
