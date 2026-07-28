from fastapi import FastAPI, HTTPException
from pydantic import BaseModel

from orchestrator import current_stage, judge, rerun_stage, reset_pipeline, run_stage, stage_result

app = FastAPI(title="MDDOAI Orchestrator")


class ReviewRequest(BaseModel):
    approved: bool
    correction: str | None = None


class StartRequest(BaseModel):
    platform_description: str


class JudgeRequest(BaseModel):
    message: str


@app.post("/start")
def start_endpoint(request: StartRequest):
    reset_pipeline()
    try:
        return run_stage({"platform_description": request.platform_description})
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))


@app.post("/rerun/{stage_id}")
def rerun_endpoint(stage_id: str):
    if stage_id != current_stage():
        raise HTTPException(
            status_code=400,
            detail=f"'{stage_id}' is not the current pending stage (current: {current_stage()!r}).",
        )
    try:
        return rerun_stage()
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))


@app.post("/judge")
def judge_endpoint(request: JudgeRequest):
    try:
        return judge(request.message)
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))


@app.post("/review/{stage_id}")
def review_endpoint(stage_id: str, request: ReviewRequest):
    if stage_id != current_stage():
        raise HTTPException(
            status_code=400,
            detail=f"'{stage_id}' is not the current pending stage (current: {current_stage()!r}).",
        )
    if not request.approved and not request.correction:
        raise HTTPException(status_code=400, detail="correction is required when approved is False.")
    return stage_result(stage_id, request.approved, request.correction)
