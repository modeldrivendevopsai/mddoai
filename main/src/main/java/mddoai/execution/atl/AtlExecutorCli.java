package main.java.mddoai.execution.atl;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Thin process-boundary wrapper around AtlExecutor, for invocation as a
 * separate OS process (e.g. from a Python service) - mirrors
 * {@link main.java.mddoai.validation.atl.AtlValidatorCli}'s own shape
 * exactly, one level up (execution instead of compile-only validation).
 * Content flows through files, not argv, on both sides: the real .atl
 * source, PIM model instance, and target Ecore text can each be too large
 * for a command-line argument, and the real output (a full transformed
 * model, XMI text) is written to a given output file rather than printed,
 * for the same reason.
 *
 * Exit codes:
 *   0 - the executor ran to completion; stdout is one line of JSON,
 *       {@code {"success":true}} with the real output written to
 *       &lt;output-file&gt;, or {@code {"success":false,"error":"..."}} for
 *       a real, describable failure (bad input, a genuine ATL compile or
 *       runtime failure) - not an infrastructure problem.
 *   2 - usage error (wrong arg count). Message on stderr.
 *   1 - unexpected exception invoking the executor - the tool itself
 *       broke, as distinct from it successfully reporting a real failure.
 */
public final class AtlExecutorCli {

    private AtlExecutorCli() {
    }

    public static void main(String[] args) {
        System.exit(run(args, System.out, System.err));
    }

    // Public (not just package-visible) for the same reason as
    // AtlValidatorCli.run: this repo's test packages don't share a package
    // with main.java.mddoai..., so package-private would be untestable.
    public static int run(String[] args, PrintStream out, PrintStream err) {
        if (args.length != 5) {
            err.println("usage: AtlExecutorCli <atl-file> <pim-model-file> <target-ecore-file> <output-model-name> <output-file>");
            return 2;
        }
        String atlFilePath = args[0];
        String pimModelFilePath = args[1];
        String targetEcoreFilePath = args[2];
        String outputModelName = args[3];
        String outputFilePath = args[4];

        try {
            String atlSource = Files.readString(Path.of(atlFilePath), StandardCharsets.UTF_8);
            String pimModelXmi = Files.readString(Path.of(pimModelFilePath), StandardCharsets.UTF_8);
            String targetEcore = Files.readString(Path.of(targetEcoreFilePath), StandardCharsets.UTF_8);

            String outputXmi;
            try {
                outputXmi = AtlExecutor.execute(atlSource, pimModelXmi, targetEcore, outputModelName);
            } catch (IllegalArgumentException | IOException e) {
                out.println("{\"success\":false,\"error\":\"" + escape(String.valueOf(e.getMessage())) + "\"}");
                return 0;
            }

            Files.writeString(Path.of(outputFilePath), outputXmi, StandardCharsets.UTF_8);
            out.println("{\"success\":true}");
            return 0;
        } catch (Exception e) {
            err.println("AtlExecutorCli: unexpected error: " + e);
            e.printStackTrace(err);
            return 1;
        }
    }

    // Same rationale as AtlValidatorCli.escape: handle every JSON-illegal
    // character (0x00-0x1F), not just the ones a real error message is
    // likely to contain.
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
