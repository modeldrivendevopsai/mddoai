import subprocess
import shutil

SYSTEM_PROMPT = (
    "You are the Chat Interface agent for MDDOAI, a model-driven DevOps tool. "
    "You help users describe their CI/CD pipeline generation goals and pass them to the orchestrator."
)

CLAUDE_BIN = shutil.which("claude") or "claude"


def ask_claude(prompt: str) -> str:
    result = subprocess.run(
        [
            CLAUDE_BIN,
            "-p", prompt,
            "--model", "claude-sonnet-4-6",
            "--system-prompt", SYSTEM_PROMPT,
            "--output-format", "text",
            "--no-session-persistence",
        ],
        capture_output=True,
        text=True,
    )
    if result.returncode != 0:
        raise RuntimeError(result.stderr.strip() or "claude CLI returned non-zero exit code")
    return result.stdout.strip()


def build_prompt(history: list[dict], new_message: str) -> str:
    parts = []
    for turn in history:
        role = "User" if turn["role"] == "user" else "Agent"
        parts.append(f"{role}: {turn['content']}")
    parts.append(f"User: {new_message}")
    return "\n".join(parts)


def main():
    conversation_history = []

    print("MDDOAI Chat Interface — type 'exit' to quit.\n")

    while True:
        try:
            user_input = input("You: ").strip()
        except (EOFError, KeyboardInterrupt):
            print("\nGoodbye.")
            break
        if user_input.lower() in {"exit", "quit"}:
            print("Goodbye.")
            break
        if not user_input:
            continue

        prompt = build_prompt(conversation_history, user_input)
        assistant_message = ask_claude(prompt)

        conversation_history.append({"role": "user", "content": user_input})
        conversation_history.append({"role": "assistant", "content": assistant_message})

        print(f"\nAgent: {assistant_message}\n")


if __name__ == "__main__":
    main()
