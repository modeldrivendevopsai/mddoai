import type { OrchestratorResponse } from "@/types"
import mockData from "@/mock/orchestrator.json"

interface MockTurn {
  turn: number
  message: string
  status?: "pending" | "complete"
}

const turns = (mockData as { turns: MockTurn[] }).turns

let turnIndex = 0

export async function sendMessage(
  _message: string
): Promise<OrchestratorResponse> {
  const turn = turns[Math.min(turnIndex, turns.length - 1)]
  turnIndex += 1

  return {
    message: turn.message,
    status: turn.status ?? "pending",
  }
}
