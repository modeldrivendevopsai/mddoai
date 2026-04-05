package main.java;

import main.java.mddoai.utils.EMFUtils;

public class Main {
    
    public static void main(String[] args) {
        try {
            run(args);
        } catch (ExitException e) {
            System.exit(e.getCode());
        }
    }

    public static void run(String[] args) {
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
                    throw new ExitException(1);
            }

        } catch (ExitException e) {
            throw e;
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
            throw new ExitException(1);
        }
    }
}