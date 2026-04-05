package main.java;

import java.io.File;

public class InputValidator {

    public static void validate(String[] args) {
        if (args.length < 3) {
            System.err.println(
                    "Not enough arguments. Usage: <transformation_type> <input_model_path> <output_folder>");
            throw new ExitException(1);
        }

        String transformationType = args[0];
        String inputModelPath = args[1];
        String outputFolder = args[2];

        if (transformationType == null || transformationType.trim().isEmpty()) {
            System.err.println("Transformation type cannot be null or empty");
            throw new ExitException(1);
        }

        if (inputModelPath == null || inputModelPath.trim().isEmpty()) {
            System.err.println("Input model path cannot be null or empty");
            throw new ExitException(1);
        }

        if (outputFolder == null || outputFolder.trim().isEmpty()) {
            System.err.println("Output folder cannot be null or empty");
            throw new ExitException(1);
        }

        File inputFile = new File(inputModelPath);
        if (!inputFile.exists() || !inputFile.isFile()) {
            System.err.println("Input model file does not exist: " + inputModelPath);
            throw new ExitException(1);
        }

        File outputDir = new File(outputFolder);
        if (!outputDir.exists()) {
            boolean created = outputDir.mkdirs();
            if (!created) {
                System.err.println("Failed to create output directory: " + outputFolder);
                throw new ExitException(1);
            }
        } else if (!outputDir.isDirectory()) {
            System.err.println("Output path exists but is not a directory: " + outputFolder);
            throw new ExitException(1);
        }
    }
}