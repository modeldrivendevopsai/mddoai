package main.java.mddoai.validation.swarch;

import main.java.mddoai.validation.ValidationIssue;
import main.java.mddoai.validation.ValidationResult;

import java.io.PrintStream;
import java.util.List;

/**
 * Thin process-boundary wrapper around SwarchValidator, for invocation as a
 * separate OS process (e.g. from ai/validator_agent's Python service).
 * Invoked directly with
 * {@code java -cp <lib>/* main.java.mddoai.validation.swarch.SwarchValidatorCli <path>}
 * rather than through the generated {@code cli} start script.
 *
 * Exit codes match the other validator CLIs' contract (the infra-failure vs
 * validation-failure boundary a caller relies on):
 *   0 - the validator ran to completion; stdout is one line of JSON. Returned
 *       whether the model is valid or not - validity is inside the JSON's
 *       "valid" field, NOT the exit code.
 *   2 - usage error (wrong arg count). Message on stderr.
 *   1 - unexpected exception invoking the validator (unreadable file, EMF
 *       init failure, etc.) - the tool itself broke, as distinct from the
 *       tool successfully reporting the model is invalid.
 */
public final class SwarchValidatorCli {

    private SwarchValidatorCli() {
    }

    public static void main(String[] args) {
        System.exit(run(args, System.out, System.err));
    }

    // Public (not just package-visible) because this repo's test packages
    // don't share a package with main.java.mddoai..., so package-private would
    // be untestable. Returns an int instead of calling System.exit directly, so
    // it's testable without spawning a process.
    public static int run(String[] args, PrintStream out, PrintStream err) {
        if (args.length != 1) {
            err.println("usage: SwarchValidatorCli <path-to-swarch-file>");
            return 2;
        }
        try {
            out.println(toJson(SwarchValidator.validate(args[0])));
            return 0;
        } catch (Exception e) {
            err.println("SwarchValidatorCli: unexpected error: " + e);
            e.printStackTrace(err);
            return 1;
        }
    }

    private static String toJson(ValidationResult result) {
        StringBuilder json = new StringBuilder();
        json.append("{\"valid\":").append(result.valid())
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

    // Handles every JSON-illegal character (0x00-0x1F), not just the ones an
    // EMF diagnostic message is likely to contain.
    private static String escape(String s) {
        StringBuilder out = new StringBuilder(s.length());
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '\\' -> out.append("\\\\");
                case '"' -> out.append("\\\"");
                case '\n' -> out.append("\\n");
                case '\r' -> out.append("\\r");
                case '\t' -> out.append("\\t");
                case '\b' -> out.append("\\b");
                case '\f' -> out.append("\\f");
                default -> {
                    if (c < 0x20) {
                        out.append(String.format("\\u%04x", (int) c));
                    } else {
                        out.append(c);
                    }
                }
            }
        }
        return out.toString();
    }
}
