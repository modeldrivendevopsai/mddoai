"""Generations in progress and just finished, so POST /generate can answer at
once and the caller can fetch the outcome when it is ready. A generation can
outlast a proxy's request timeout: each platform runs one after another, and
a published platform makes several calls to other services."""
import logging
import threading
import uuid
from collections.abc import Callable
from concurrent.futures import ThreadPoolExecutor

logger = logging.getLogger(__name__)

RUNNING = "running"
DONE = "done"
FAILED = "failed"


class GenerationJobs:
    """In memory only: a restart forgets every job, and a caller still
    waiting on one gets 404 and can generate again. A finished generation
    is also kept in the pipeline history, which does survive a restart.

    Keeps the newest `max_finished` finished jobs; running jobs are never
    dropped. Runs at most `max_running` jobs at once, each generation
    starting the engine's JVM; the rest wait, reported as running. Its own
    threads, not FastAPI's BackgroundTasks: a job can take minutes, and
    would hold one of the threads Starlette shares with every request.
    `run` replaces that for tests, with one that runs the work at once."""

    def __init__(
        self, max_finished: int, max_running: int = 1, run: Callable[[Callable[[], None]], object] | None = None
    ):
        self._jobs: dict[str, dict] = {}
        self._lock = threading.Lock()
        self._max_finished = max_finished
        self._run = run or ThreadPoolExecutor(max_workers=max_running, thread_name_prefix="generation").submit

    def start(self, work: Callable[[], dict], error_message: str) -> str:
        """Runs work as a new job and returns its id. The job ends "done"
        with what work returns, or "failed" with error_message when work
        raises. The exception itself is logged, never returned: it can carry
        stack traces and server paths."""
        job_id = uuid.uuid4().hex
        with self._lock:
            self._jobs[job_id] = {"state": RUNNING}

        def task() -> None:
            try:
                result = work()
            except Exception:
                logger.exception("generation job %s failed", job_id)
                self._settle(job_id, {"state": FAILED, "error": error_message})
                return
            self._settle(job_id, {"state": DONE, **result})

        self._run(task)
        return job_id

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
