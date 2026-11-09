"""The tools that act on the platforms already published: see which there are,
and purge one. Both go straight to the journal, the same calls the
GET /platforms and DELETE /platforms routes make, so the chat does exactly what
the page's button does. Not tied to a stage: platforms outlive any one run."""
import tool_calling
from clients import journal_client


def list_platforms() -> dict:
    """Each platform at its latest version, with all its versions and whether it
    was published from a run, which is what can be purged."""
    return {
        "platforms": [
            {key: platform[key] for key in ("id", "name", "version", "versions", "published")}
            for platform in journal_client.list_platforms()
        ]
    }


def purge_platform(platform_id: str, version: str | None = None) -> dict:
    """Removes one version of a published platform, or all of its versions when
    none is named."""
    return {"platform_id": platform_id, "purged": journal_client.purge_platform(platform_id, version or None)}


def get_tools() -> list["tool_calling.Tool"]:
    return [
        tool_calling.Tool(
            name="list_platforms",
            description=(
                "List the platforms pipelines can be generated for: each one's id, name, latest "
                "version, every version it has, and whether it was published from a run "
                "(published: true). Only a published platform can be purged. Use this to find the "
                "exact id and version before purging, or when the human asks what is published."
            ),
            parameters={"type": "object", "properties": {}, "required": []},
            impl=list_platforms,
        ),
        tool_calling.Tool(
            name="purge_platform",
            description=(
                "Permanently remove a published platform, or one version of it, so it is no longer "
                "offered for generating pipelines. It cannot be undone, so use it only when the human "
                "clearly asks to remove, purge or delete a specific platform or version, for example "
                "to clear a test platform, and say afterwards exactly which versions were removed. "
                "A platform that came with the stack is refused. Use list_platforms first if you do "
                "not know the exact id. Do NOT use it to undo a run or a stage."
            ),
            parameters={
                "type": "object",
                "properties": {
                    "platform_id": {"type": "string", "description": "The platform's id from list_platforms, such as woodpecker-ci."},
                    "version": {
                        "type": "string",
                        "description": "One version to remove, such as 1.2.0. Omit to remove every version of the platform.",
                    },
                },
                "required": ["platform_id"],
            },
            impl=purge_platform,
        ),
    ]
