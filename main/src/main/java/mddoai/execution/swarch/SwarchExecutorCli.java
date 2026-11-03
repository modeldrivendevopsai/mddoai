package main.java.mddoai.execution.swarch;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Thin process-boundary wrapper around SwarchExecutor, for invocation as a
 * separate OS process (e.g. from a Python service), in the same shape as
 * {@link main.java.mddoai.execution.pim.PimExecutorCli}. Content flows through
 * files, not argv, on both sides: the ATL source and the model can each be too
 * large for a command-line argument, and the PIM that comes out is written to a
 * given output file rather than printed, for the same reason.
 *
 * Usage: SwarchExecutorCli &lt;atl-file&gt; &lt;swarch-model-file&gt; &lt;output-file&gt;
 *
 * Exit codes:
 *   0 - the executor ran to completion; stdout is one line of JSON,
 *       {@code {"success":true}} with the PIM written to &lt;output-file&gt;,
 *       or {@code {"success":false,"error":"..."}} for a real, describable
 *       failure (bad input, a genuine ATL compile or runtime failure), not an
 *       infrastructure problem.
 *   2 - usage error (wrong arg count). Message on stderr.
 *   1 - unexpected exception invoking the executor: the tool itself broke, as
 *       distinct from it successfully reporting a real failure. An input file
 *       that cannot be read is this, not a reported failure.
 */
public final class SwarchExecutorCli {

    private SwarchExecutorCli() {
    }

    public static void main(String[] args) {
        System.exit(run(args, System.out, System.err));
    }

    // Public (not just package-visible) because this repo's test packages do
    // not share a package with main.java.mddoai..., so package-private would
    // be untestable.
    public static int run(String[] args, PrintStream out, PrintStream err) {
        if (args.length != 3) {
            err.println("usage: SwarchExecutorCli <atl-file> <swarch-model-file> <output-file>");
            return 2;
        }

        try {
            // Read before the executor runs, outside its failure handling.
            String atlSource = Files.readString(Path.of(args[0]), StandardCharsets.UTF_8);
            String swarchModelXmi = Files.readString(Path.of(args[1]), StandardCharsets.UTF_8);

            String pimXmi;
            try {
                pimXmi = SwarchExecutor.execute(atlSource, swarchModelXmi);
            } catch (IllegalArgumentException | IOException e) {
                return reportFailure(out, e);
            }
            Files.writeString(Path.of(args[2]), pimXmi, StandardCharsets.UTF_8);
            out.println("{\"success\":true}");
            return 0;
        } catch (Exception e) {
            err.println("SwarchExecutorCli: unexpected error: " + e);
            // Not e.printStackTrace(err) alone: an ATL VMException's own
            // override of that method pretty-prints its ATL stack frames, which
            // can itself throw, and diagnostic logging must never be able to
            // crash the process harder than the failure it is reporting.
            try {
                e.printStackTrace(err);
            } catch (RuntimeException loggingFailure) {
                err.println("SwarchExecutorCli: (also failed to print the real stack trace: " + loggingFailure + ")");
            }
            return 1;
        }
    }

    // A real, describable failure: reported on stdout, with exit code 0.
    private static int reportFailure(PrintStream out, Exception e) {
        out.println("{\"success\":false,\"error\":\"" + escape(String.valueOf(e.getMessage())) + "\"}");
        return 0;
    }

    // Every JSON-illegal character (0x00-0x1F) is escaped, not just the ones a
    // real error message is likely to contain.
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
