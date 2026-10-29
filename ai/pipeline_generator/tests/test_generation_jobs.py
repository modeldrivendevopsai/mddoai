from generation_jobs import GenerationJobs


def test_a_job_is_running_until_it_finishes_with_its_result():
    jobs = GenerationJobs()
    job_id = jobs.create()

    assert jobs.get(job_id) == {"state": "running"}
    jobs.finish(job_id, {"pipeline_id": "p1", "results": []})
    assert jobs.get(job_id) == {"state": "done", "pipeline_id": "p1", "results": []}


def test_a_failed_job_carries_its_error():
    jobs = GenerationJobs()
    job_id = jobs.create()

    jobs.fail(job_id, "it broke")

    assert jobs.get(job_id) == {"state": "failed", "error": "it broke"}


def test_an_unknown_job_is_none():
    assert GenerationJobs().get("nope") is None


def test_only_the_newest_finished_jobs_are_kept_and_running_ones_never_dropped():
    jobs = GenerationJobs(max_finished=2)
    still_running = jobs.create()
    finished = [jobs.create() for _ in range(3)]
    for job_id in finished:
        jobs.finish(job_id, {"results": []})

    assert jobs.get(finished[0]) is None
    assert jobs.get(finished[1]) is not None
    assert jobs.get(finished[2]) is not None
    assert jobs.get(still_running) == {"state": "running"}


def test_a_returned_job_is_a_copy():
    jobs = GenerationJobs()
    job_id = jobs.create()

    jobs.get(job_id)["state"] = "tampered"

    assert jobs.get(job_id) == {"state": "running"}
