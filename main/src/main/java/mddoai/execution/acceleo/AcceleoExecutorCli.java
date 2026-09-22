package main.java.mddoai.execution.acceleo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

/**
 * Thin process-boundary wrapper around AcceleoExecutor, for invocation as a
 * separate OS process (e.g. from a Python service) - mirrors
 * {@link main.java.mddoai.validation.acceleo.AcceleoValidatorCli}'s own
 * shape one level up (execution instead of compile-only validation). See
 * {@link main.java.mddoai.execution.atl.AtlExecutorCli}'s own docstring for
 * why content flows through files, not argv, on the input side. The output
 * side is a directory, not a single file, since a real Acceleo template can
 * legitimately generate more than one file - each of AcceleoExecutor's own
 * result entries is written under &lt;output-dir&gt; at its own relative
 * path, creating parent directories as needed.
 *
 * Exit codes: identical contract to AtlExecutorCli - 0 with
 * {@code {"success":true,"files":[...]}} or
 * {@code {"success":false,"error":"..."}} for a real, describable failure;
 * 2 for a usage error; 1 for the tool itself breaking unexpectedly.
 */
public final class AcceleoExecutorCli {

    private AcceleoExecutorCli() {
    }

    public static void main(String[] args) {
        System.exit(run(args, System.out, System.err));
    }

    public static int run(String[] args, PrintStream out, PrintStream err) {
        if (args.length != 4) {
            err.println("usage: AcceleoExecutorCli <mtl-file> <psm-model-file> <target-ecore-file> <output-dir>");
            return 2;
        }
        String mtlFilePath = args[0];
        String psmModelFilePath = args[1];
        String targetEcoreFilePath = args[2];
        String outputDirPath = args[3];

        try {
            String mtlSource = Files.readString(Path.of(mtlFilePath), StandardCharsets.UTF_8);
            String psmModelXmi = Files.readString(Path.of(psmModelFilePath), StandardCharsets.UTF_8);
            String targetEcore = Files.readString(Path.of(targetEcoreFilePath), StandardCharsets.UTF_8);

            Map<String, String> generated;
            try {
                generated = AcceleoExecutor.execute(mtlSource, psmModelXmi, targetEcore);
            } catch (IllegalArgumentException | IOException e) {
                out.println("{\"success\":false,\"error\":\"" + escape(String.valueOf(e.getMessage())) + "\"}");
                return 0;
            }

            Path outputDir = Path.of(outputDirPath);
            StringBuilder filesJson = new StringBuilder();
            boolean first = true;
            for (Map.Entry<String, String> entry : generated.entrySet()) {
                Path target = outputDir.resolve(entry.getKey()).normalize();
                if (!target.startsWith(outputDir.normalize())) {
                    throw new IOException("generated file path escapes the output directory: " + entry.getKey());
                }
                Files.createDirectories(target.getParent());
                Files.writeString(target, entry.getValue(), StandardCharsets.UTF_8);
                if (!first) {
                    filesJson.append(",");
                }
                filesJson.append("\"").append(escape(entry.getKey())).append("\"");
                first = false;
            }

            out.println("{\"success\":true,\"files\":[" + filesJson + "]}");
            return 0;
        } catch (Exception e) {
            err.println("AcceleoExecutorCli: unexpected error: " + e);
            e.printStackTrace(err);
            return 1;
        }
    }

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
