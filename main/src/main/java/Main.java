package mddoai;

import mddoai.utils.EMFUtils;

public class Main {
    
    public static void main(String[] args) {
        // The JVM only exits here, which our tests will safely ignore!
        int exitCode = run(args);
        if (exitCode != 0) {
            System.exit(exitCode);
        }
    }

    // We put all the testable logic here, returning standard exit codes
    public static int run(String[] args) {
        try {
            InputValidator.validate(args);

            EMFUtils.init();
            String transformationType = args[0];
            String inputModelPath = args[1];
            String outputFolder = args[2];

            switch (transformationType.toLowerCase()) {
                case "swarch2gitlab":
                    SwarchToGitlabHandler.handle(inputModelPath, outputFolder);
                    break;

                case "pim2gitlab":
                    PimToGitlabHandler.handle(inputModelPath, outputFolder);
                    break;

                case "psm2gitlab":
                    PsmToGitlabHandler.handle(inputModelPath, outputFolder);
                    break;

                default:
                    System.err.println("Incorrect transformation type was provided: " + transformationType);
                    System.err.println("Supported transformation types: swarch2gitlab, pim2gitlab, psm2gitlab");
                    return 1; // Return failure code instead of exiting
            }
            return 0; // Return success code

        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
            return 1; // Return failure code instead of exiting
        }
    }
}