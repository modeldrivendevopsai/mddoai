# All values are set in terraform.tfvars; none has a default.

variable "hcloud_token" {
  description = "Hetzner API token, Read & Write, for the project the VM is in."
  type        = string
  sensitive   = true
}

variable "ssh_allowed_cidrs" {
  description = "Developer IPs allowed to reach SSH, in CIDR form, such as \"203.0.113.7/32\". Nothing else can reach the VM."
  type        = list(string)

  validation {
    condition     = length(var.ssh_allowed_cidrs) > 0 && alltrue([for c in var.ssh_allowed_cidrs : can(cidrhost(c, 0))])
    error_message = "List at least one address, each in CIDR form (for example 203.0.113.7/32)."
  }

  validation {
    condition     = !anytrue([for c in var.ssh_allowed_cidrs : can(cidrhost(c, 0)) && endswith(c, "/0")])
    error_message = "A /0 range opens SSH to the whole internet. List developer addresses instead."
  }
}

variable "ssh_public_key_path" {
  description = "Public half of the developer's SSH key. The private key is at the same path without .pub."
  type        = string

  validation {
    condition     = endswith(var.ssh_public_key_path, ".pub")
    error_message = "Point at the public key file, ending in .pub."
  }
}

variable "server_name" {
  description = "Name of the server in Hetzner, used for its SSH key, firewall and IP too, and the SSH host alias."
  type        = string
}

variable "server_type" {
  description = "Hetzner server type."
  type        = string
}

variable "location" {
  description = "Hetzner location. Changing it later replaces the server and its IP."
  type        = string
}

variable "image" {
  description = "Operating system image. Changing it later replaces the server."
  type        = string
}

variable "dev_user" {
  description = "Linux user the developer logs in as. The setup script must use the same name (DEV_USER)."
  type        = string

  validation {
    condition     = can(regex("^[a-z_][a-z0-9_-]*$", var.dev_user))
    error_message = "Use a plain Linux user name: lowercase letters, digits, - and _, not starting with a digit or -."
  }
}

variable "ui_port" {
  description = "The UI's port on the VM, forwarded to the same port on the developer's PC. It matches UI_PORT in the dev clone's ai/.env."
  type        = number
}

variable "protection" {
  description = "Delete and rebuild protection for the server and its IP. Terraform cannot delete or replace them while it is on."
  type        = bool
}

variable "backups" {
  description = "Hetzner daily backups of the server's disk. They cost extra."
  type        = bool
}
