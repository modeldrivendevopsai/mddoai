import { describe, expect, it } from "vitest"
import { sendMessage } from "./orchestratorService"

describe("orchestratorService", () => {
  it("returns mock turns sequentially and signals completion", async () => {
    const first = await sendMessage("I want to integrate with GitHub Actions")
    expect(first).toEqual({
      message: "Can you clarify which CI/CD stages are in scope?",
      status: "pending",
    })

    const second = await sendMessage("Build and deploy")
    expect(second).toEqual({
      message: "Do you need rollback support on deployment failure?",
      status: "pending",
    })

    const third = await sendMessage("Yes")
    expect(third).toEqual({
      message: "Thank you. Your request has been queued for processing.",
      status: "complete",
    })
  })

  it("keeps returning the final turn once the conversation is complete", async () => {
    const response = await sendMessage("anything")
    expect(response.status).toBe("complete")
  })
})
