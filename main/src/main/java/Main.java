package main.java;

import main.java.mddoai.utils.EMFUtils;

public class Main {
    public static void main(String[] args) {
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
                    throw new ExitException(1,
                        "Incorrect transformation type was provided: " + transformationType +
                        ". Supported transformation types: swarch2gitlab, pim2gitlab, psm2gitlab");
            }
        } catch (ExitException e) {
            System.err.println(e.getMessage());
            System.exit(e.getCode());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
