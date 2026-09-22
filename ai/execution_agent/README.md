# execution_agent

Wraps `main/`'s new headless model/code executors as an HTTP service, so other `ai/` services can actually *run* a real ATL transformation or Acceleo template without needing a JVM of their own: `/execute/atl` runs a real `.atl` transformation against a real model instance and returns the real transformed output; `/execute/acceleo` runs a real `.mtl` template against a real model instance and returns the real generated file(s). The real caller today is `integration_runner`'s own `generation` stage (`clients/execution_agent_client.py`), which needs the real final CI/CD YAML, not a summary of one.

## Why a separate service, not new routes on `validator_agent`

`validator_agent` already bridges Python to this same JVM/EMF/ATL/Acceleo world, and it might look like `/execute/*` belongs there as a sibling to `/validate/*`. It doesn't, for reasons that came out of an actual review of both services' real contracts, not a naming preference:

- `validator_agent`'s own stated scope (see its README) is specifically about *validator-type* proliferation, "a future validator type would add another sibling `/validate/<type>` route", never about any-JVM-capability proliferation.
- `ai/CLAUDE.md`'s own documented exception licensing `validator_agent` to reach into `main/` is scoped, by name and path, to `main/src/main/java/mddoai/validation/**/*ValidatorCli.java`. `AtlExecutorCli`/`AcceleoExecutorCli` live under a sibling top-level package, `mddoai.execution`, not under `mddoai.validation` at all, stretching that exception to cover them would mean quietly rewriting what it says, not applying it.
- The real request/response contracts genuinely differ, not just the verb: validating posts draft DSL text and gets back a validity/issues report; executing needs a full model instance *and* a target metamodel as additional real input, and returns the real transformed output, not a report about it. That's a different resource, not the same one with an extra HTTP verb.
- The infra cost of a second JVM-backed service is small: it mounts the exact same `main-build-output` volume `gradle-builder` already produces for `validator-agent`, read-only, with no separate Gradle build of its own.

This mirrors `validator_agent`'s own real architecture deliberately (see below), not a new one invented for this service.

## Why a subprocess, not an embedded JVM

Same reasoning as `validator_agent`'s own README: a fresh `java` process per call, not a long-lived embedded one (JPype/Py4J). `AtlExecutor`/`AcceleoExecutor` do even *more* EMF global-registry manipulation per call than the validators do (dynamically loading and registering a fresh target metamodel every time), so the same "never reuse a JVM across calls" argument applies at least as strongly here.

## How a request flows

1. A caller `POST`s real content (not a file path, this service shares no filesystem with its callers) to `/execute/atl` or `/execute/acceleo`: the real `.atl`/`.mtl` source, a real model instance (XMI text), and the target platform's own real metamodel (Ecore text).
2. `execution_runner.py` writes each of those to a temp file, then runs `java -cp <lib>/* <FQN of the matching *ExecutorCli> ...` as a subprocess, along with a temp output file (ATL) or output directory (Acceleo, which can legitimately generate more than one file).
3. The Java side prints one line of JSON to stdout: `{"success":true}` (ATL) or `{"success":true,"files":[...]}` (Acceleo) with the real output already written to the given output path, or `{"success":false,"error":"..."}` for a real, describable failure (bad input, a genuine ATL/Acceleo compile or runtime error), never an infrastructure problem, since a nonzero exit code is reserved for that instead.
4. `execution_runner.py` reads the real output back off disk, and the response is built from that content, not from re-parsing the subprocess's own stdout.

| Outcome | HTTP status |
|---|---|
| Real execution succeeded | `200`, the real output (or generated files) in the response body |
| A real, describable execution failure (bad input, a genuine ATL/Acceleo error) | `422`, `detail` carries the real message |
| Content exceeds `MAX_CONTENT_BYTES` | `413` |
| An infrastructure failure (subprocess crashed, timed out, produced unparseable output) | `500` |

## Local development

```
cd main && ./gradlew installDist
cd ../ai/execution_agent
pip install -r requirements.txt
EXECUTION_LIB_DIR=../../main/build/install/com.mddoai/lib uvicorn main:app --reload --port 8090
```

`pytest` alone (no env vars) runs the fast, fully-mocked suite on any machine. The real end-to-end test (`tests/test_integration_real_jvm.py`) auto-skips unless a JDK and the built `main/` distribution are both available.
