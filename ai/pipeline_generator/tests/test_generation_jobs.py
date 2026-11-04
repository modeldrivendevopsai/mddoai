import threading

from generation_jobs import GenerationJobs


def _run_now(task):
    task()


def test_a_job_is_running_until_its_work_finishes_then_done_with_the_result():
    pending = []
    jobs = GenerationJobs(max_finished=10, run=pending.append)
    job_id = jobs.start(lambda: {"pipeline_id": "p1", "results": []}, "it failed")

    assert jobs.get(job_id) == {"state": "running"}
    pending[0]()
    assert jobs.get(job_id) == {"state": "done", "pipeline_id": "p1", "results": []}


def test_work_that_raises_fails_the_job_with_the_message_not_the_exception():
    def work():
        raise RuntimeError("boom at /secret/path")

    jobs = GenerationJobs(max_finished=10, run=_run_now)
    job_id = jobs.start(work, "it failed")

    assert jobs.get(job_id) == {"state": "failed", "error": "it failed"}


def test_by_default_the_work_runs_on_its_own_thread():
    release, finished = threading.Event(), threading.Event()

    def work():
        release.wait(timeout=5)
        finished.set()
        return {"results": []}

    jobs = GenerationJobs(max_finished=10)
    job_id = jobs.start(work, "it failed")

    assert jobs.get(job_id) == {"state": "running"}
    release.set()
    assert finished.wait(timeout=5)


def test_beyond_max_running_a_job_waits_for_a_running_one_to_end():
    release, second_started = threading.Event(), threading.Event()

    def first():
        release.wait(timeout=5)
        return {"results": []}

    def second():
        second_started.set()
        return {"results": []}

    jobs = GenerationJobs(max_finished=10, max_running=1)
    jobs.start(first, "it failed")
    waiting = jobs.start(second, "it failed")

    assert not second_started.wait(timeout=0.2)
    assert jobs.get(waiting) == {"state": "running"}
    release.set()
    assert second_started.wait(timeout=5)


def test_an_unknown_job_is_none():
    assert GenerationJobs(max_finished=10).get("nope") is None


def test_only_the_newest_finished_jobs_are_kept_and_running_ones_never_dropped():
    pending = []
    jobs = GenerationJobs(max_finished=2, run=pending.append)
    still_running = jobs.start(lambda: {"results": []}, "it failed")
    finished = [jobs.start(lambda: {"results": []}, "it failed") for _ in range(3)]
    for task in pending[1:]:
        task()

    assert jobs.get(finished[0]) is None
    assert jobs.get(finished[1]) is not None
    assert jobs.get(finished[2]) is not None
    assert jobs.get(still_running) == {"state": "running"}


def test_a_returned_job_is_a_copy():
    jobs = GenerationJobs(max_finished=10, run=lambda task: None)
    job_id = jobs.start(lambda: {"results": []}, "it failed")

    jobs.get(job_id)["state"] = "tampered"

    assert jobs.get(job_id) == {"state": "running"}
