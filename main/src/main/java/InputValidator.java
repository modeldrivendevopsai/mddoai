package main.java;

import java.io.File;

public class InputValidator {

    public static void validate(String[] args) {
        if (args.length < 3) {
            throw new ExitException(1,
                    "Not enough arguments. Usage: <transformation_type> <input_model_path> <output_folder>");
        }

        String transformationType = args[0];
        String inputModelPath = args[1];
        String outputFolder = args[2];

        if (transformationType == null || transformationType.trim().isEmpty()) {
            throw new ExitException(1, "Transformation type cannot be null or empty");
        }

        if (inputModelPath == null || inputModelPath.trim().isEmpty()) {
            throw new ExitException(1, "Input model path cannot be null or empty");
        }

        if (outputFolder == null || outputFolder.trim().isEmpty()) {
            throw new ExitException(1, "Output folder cannot be null or empty");
        }

        File inputFile = new File(inputModelPath);
        if (!inputFile.exists() || !inputFile.isFile()) {
            throw new ExitException(1, "Input model file does not exist: " + inputModelPath);
        }

        File outputDir = new File(outputFolder);
        if (!outputDir.exists()) {
            boolean created = outputDir.mkdirs();
            if (!created) {
                throw new ExitException(1, "Failed to create output directory: " + outputFolder);
            }
        } else if (!outputDir.isDirectory()) {
            throw new ExitException(1, "Output path exists but is not a directory: " + outputFolder);
        }
    }
}
