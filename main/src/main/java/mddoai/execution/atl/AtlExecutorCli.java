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
 * Two forms: the default runs a PIM model through a transformation into a
 * target platform's PSM; {@code --from-swarch} runs a Software Architecture
 * model through a transformation into a PIM (see {@link #FROM_SWARCH_FLAG}).
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

    // Selects AtlExecutor.executeFromSwarch: a Software Architecture model in,
    // a PIM out, so no target metamodel or output model name is passed.
    public static final String FROM_SWARCH_FLAG = "--from-swarch";

    private AtlExecutorCli() {
    }

    public static void main(String[] args) {
        System.exit(run(args, System.out, System.err));
    }

    // Public (not just package-visible) for the same reason as
    // AtlValidatorCli.run: this repo's test packages don't share a package
    // with main.java.mddoai..., so package-private would be untestable.
    public static int run(String[] args, PrintStream out, PrintStream err) {
        boolean fromSwarch = args.length == 4 && FROM_SWARCH_FLAG.equals(args[0]);
        if (!fromSwarch && args.length != 5) {
            err.println("usage: AtlExecutorCli <atl-file> <pim-model-file> <target-ecore-file> <output-model-name> <output-file>");
            err.println("   or: AtlExecutorCli " + FROM_SWARCH_FLAG + " <atl-file> <swarch-model-file> <output-file>");
            return 2;
        }

        try {
            String outputXmi;
            try {
                outputXmi = fromSwarch ? runFromSwarch(args) : runFromPim(args);
            } catch (ReportedFailure failure) {
                return reportFailure(out, (Exception) failure.getCause());
            }
            // Both forms name the output file last.
            Files.writeString(Path.of(args[args.length - 1]), outputXmi, StandardCharsets.UTF_8);
            out.println("{\"success\":true}");
            return 0;
        } catch (Exception e) {
            err.println("AtlExecutorCli: unexpected error: " + e);
            // Not e.printStackTrace(err) directly: an ATL VMException's own
            // override of that method pretty-prints its real ATL stack
            // frames, which can itself throw (a real, confirmed NPE from
            // EMFModelAdapter.getNameOf() when a frame references an
            // invalid classifier) - diagnostic logging must never be able
            // to crash the process harder than the failure it's reporting.
            // AtlExecutor's own execute() already converts the real ATL
            // runtime-failure case (ATLExecutionException) into a clean
            // IOException the catch above handles, so reaching here at all
            // means something genuinely unexpected happened.
            try {
                e.printStackTrace(err);
            } catch (RuntimeException loggingFailure) {
                err.println("AtlExecutorCli: (also failed to print the real stack trace: " + loggingFailure + ")");
            }
            return 1;
        }
    }

    // Both forms read their inputs before the executor runs, outside its own
    // failure handling: an unreadable input file is the tool breaking (exit
    // 1), not a describable failure of the transformation. Only what the
    // executor itself throws becomes a ReportedFailure.
    private static String runFromPim(String[] args) throws IOException, ReportedFailure {
        String atlSource = Files.readString(Path.of(args[0]), StandardCharsets.UTF_8);
        String pimModelXmi = Files.readString(Path.of(args[1]), StandardCharsets.UTF_8);
        String targetEcore = Files.readString(Path.of(args[2]), StandardCharsets.UTF_8);
        try {
            return AtlExecutor.execute(atlSource, pimModelXmi, targetEcore, args[3]);
        } catch (IllegalArgumentException | IOException e) {
            throw new ReportedFailure(e);
        }
    }

    private static String runFromSwarch(String[] args) throws IOException, ReportedFailure {
        String atlSource = Files.readString(Path.of(args[1]), StandardCharsets.UTF_8);
        String swarchModelXmi = Files.readString(Path.of(args[2]), StandardCharsets.UTF_8);
        try {
            return AtlExecutor.executeFromSwarch(atlSource, swarchModelXmi);
        } catch (IllegalArgumentException | IOException e) {
            throw new ReportedFailure(e);
        }
    }

    // The executor's own describable failure, carried out of the helpers above.
    private static final class ReportedFailure extends Exception {
        ReportedFailure(Exception cause) {
            super(cause);
        }
    }

    // A real, describable failure (bad input, a genuine ATL compile or
    // runtime failure): reported on stdout, with exit code 0.
    private static int reportFailure(PrintStream out, Exception e) {
        out.println("{\"success\":false,\"error\":\"" + escape(String.valueOf(e.getMessage())) + "\"}");
        return 0;
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
