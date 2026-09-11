"""The shared, generic HTTP surface every generation-capable service
(psm_agent, atl_agent, acceleo_agent) exposes identically over
generation_toolkit.prompt_config/.attachments: the prompt-config CRUD
router, the attachment-upload endpoint, and the available-files listing
endpoint. Each service's own routes/ package stays the thin, service-
specific adapter (real paths, real per-service context values) that wires
one of these factories to its own config_dir/files_root/uploads_dir, the
same split generation_toolkit itself already draws elsewhere (generic
mechanics here, service-specific wiring in each service's own routes/).
"""
