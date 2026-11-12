"""This service's prompt settings: the name the journal keeps its prompt configs
and uploaded attachments under, and the limit on an upload.
"""
# The journal keeps this service's configs and uploads under this name, so one journal
# serves every agent and none sees another's. It is also the folder the shipped starting
# configs sit in, under the prompts folder.
AGENT = "psm"

# An engineering guess, not a measured limit: real .ecore/docs attachments
# in this service's own shipped defaults are a few KB; 5MB comfortably
# covers a large real metamodel or a documentation dump without letting an
# upload endpoint accept an unbounded amount of data.
MAX_UPLOAD_BYTES = 5 * 1024 * 1024
