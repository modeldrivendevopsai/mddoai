"""Thin HTTP clients, one per sibling service this container calls: never
deployed standalone, no Dockerfile, no port, only ever imported in-process.
Callers use module-qualified access (`from clients import ai_layer_client`,
then `ai_layer_client.chat(...)`), matching this repo's existing convention.
"""
