"""Generations in progress and just finished, so POST /generate can answer at
once and the caller can fetch the outcome when it is ready. A generation can
outlast a proxy's request timeout: each platform runs one after another, and
a published platform makes several calls to other services."""
import threading
import uuid

RUNNING = "running"
DONE = "done"
FAILED = "failed"

# Finished jobs kept for their callers to fetch. The browser that started a
# job fetches it within seconds of it finishing, so this only needs to cover
# a handful of open tabs (engineering guess). Running jobs are never dropped.
_MAX_FINISHED = 100


class GenerationJobs:
    """In memory only: a restart forgets every job, and a caller still
    waiting on one gets 404 and can generate again. A finished generation
    is also kept in the pipeline history, which does survive a restart."""

    def __init__(self, max_finished: int = _MAX_FINISHED):
        self._jobs: dict[str, dict] = {}
        self._lock = threading.Lock()
        self._max_finished = max_finished

    def create(self) -> str:
        job_id = uuid.uuid4().hex
        with self._lock:
            self._jobs[job_id] = {"state": RUNNING}
        return job_id

    def finish(self, job_id: str, result: dict) -> None:
        self._settle(job_id, {"state": DONE, **result})

    def fail(self, job_id: str, error: str) -> None:
        self._settle(job_id, {"state": FAILED, "error": error})

    def get(self, job_id: str) -> dict | None:
        with self._lock:
            job = self._jobs.get(job_id)
            return dict(job) if job is not None else None

    def _settle(self, job_id: str, outcome: dict) -> None:
        with self._lock:
            # Re-inserted so the dict's order is the order jobs finished in.
            self._jobs.pop(job_id, None)
            self._jobs[job_id] = outcome
            finished = [jid for jid, job in self._jobs.items() if job["state"] != RUNNING]
            for jid in finished[: max(0, len(finished) - self._max_finished)]:
                del self._jobs[jid]
