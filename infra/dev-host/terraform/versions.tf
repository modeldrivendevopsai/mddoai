terraform {
  # hcloud provider v1.69 dropped support for Terraform 1.14.
  required_version = ">= 1.15"

  required_providers {
    hcloud = {
      source  = "hetznercloud/hcloud"
      version = "~> 1.70"
    }
  }
}

# The API token comes from the HCLOUD_TOKEN environment variable, so it never
# sits in a tfvars file next to the code.
provider "hcloud" {}
