package main.java.mddoai.validation;

import java.io.PrintStream;
import java.util.List;

/**
 * Thin process-boundary wrapper around EcoreValidator, for invocation as a
 * separate OS process (e.g. from ai/integration-agent's Python service).
 * Deliberately NOT wired into Main.java's transformation switch (reserved
 * for #316) — this is its own entrypoint, invoked directly with
 * {@code java -cp <lib>/* main.java.mddoai.validation.EcoreValidatorCli <mode> <path>}
 * rather than through the generated {@code cli} start script.
 *
 * Exit codes (the infra-failure vs validation-failure boundary a caller
 * relies on):
 *   0 - the validator ran to completion; stdout is one line of JSON. Returned
 *       whether the model is valid or not — validity is inside the JSON's
 *       "valid" field, NOT the exit code.
 *   2 - usage error (wrong arg count / unknown mode). Message on stderr.
 *   1 - unexpected exception invoking the validator (unreadable file, EMF
 *       init failure, etc.) — the tool itself broke, as distinct from the
 *       tool successfully reporting the model is invalid.
 */
public final class EcoreValidatorCli {

    private EcoreValidatorCli() {
    }

    public static void main(String[] args) {
        System.exit(run(args, System.out, System.err));
    }

    // Public (not just package-visible) because this repo's test packages
    // (test.java.unit.java.mddoai...) don't share a package with main.java.mddoai...,
    // so package-private would be untestable. Returns an int instead of calling
    // System.exit directly, so it's testable without spawning a process.
    public static int run(String[] args, PrintStream out, PrintStream err) {
        if (args.length != 2 || !(args[0].equals("reflective") || args[0].equals("codegen"))) {
            err.println("usage: EcoreValidatorCli <reflective|codegen> <path-to-ecore-file>");
            return 2;
        }
        String mode = args[0];
        String path = args[1];
        try {
            ValidationResult result = mode.equals("reflective")
                    ? EcoreValidator.validateReflectively(path)
                    : EcoreValidator.validateViaCodegen(path);
            out.println(toJson(mode, result));
            return 0;
        } catch (Exception e) {
            err.println("EcoreValidatorCli: unexpected error: " + e);
            e.printStackTrace(err);
            return 1;
        }
    }

    private static String toJson(String mode, ValidationResult result) {
        StringBuilder json = new StringBuilder();
        json.append("{\"valid\":").append(result.valid())
                .append(",\"mode\":\"").append(escape(mode)).append("\"")
                .append(",\"issues\":[");
        List<ValidationIssue> issues = result.issues();
        for (int i = 0; i < issues.size(); i++) {
            if (i > 0) {
                json.append(",");
            }
            ValidationIssue issue = issues.get(i);
            json.append("{\"severity\":\"").append(escape(issue.severity().name())).append("\"")
                    .append(",\"message\":\"").append(escape(issue.message())).append("\"")
                    .append(",\"source\":")
                    .append(issue.source() == null ? "null" : "\"" + escape(issue.source()) + "\"")
                    .append("}");
        }
        json.append("]}");
        return json.toString();
    }

    private static String escape(String s) {
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "")
                .replace("\t", "\\t");
    }
}
