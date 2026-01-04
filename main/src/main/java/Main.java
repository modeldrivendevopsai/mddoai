package main.java;

import java.io.File;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import com.mddoai.metamodel.swarch.SwarchPackage;
import com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage;
import com.mddoai.metamodel.pim.pimMM.PimMMPackage;

import main.java.mddoai.generators.GeneratorExecutor;
import main.java.mddoai.loaders.ModelLoader;
import main.java.mddoai.transformers.TransformerExecutor;
import main.java.mddoai.utils.EMFUtils;

public class Main {
    public static void main(String[] args) {
        try {
            if (args.length < 3) {
                System.err.println(
                        "Not enough arguments. Usage: <transformation_type> <input_model_path> <output_folder>");
                System.exit(1);
            }

            EMFUtils.init();
            String transformationType = args[0];
            String inputModelPath = args[1];
            String outputFolder = args[2];

            if (transformationType == null || transformationType.trim().isEmpty()) {
                System.err.println("Transformation type cannot be null or empty");
                System.exit(1);
            }

            if (inputModelPath == null || inputModelPath.trim().isEmpty()) {
                System.err.println("Input model path cannot be null or empty");
                System.exit(1);
            }

            if (outputFolder == null || outputFolder.trim().isEmpty()) {
                System.err.println("Output folder cannot be null or empty");
                System.exit(1);
            }

            File inputFile = new File(inputModelPath);
            if (!inputFile.exists() || !inputFile.isFile()) {
                System.err.println("Input model file does not exist: " + inputModelPath);
                System.exit(1);
            }

            File outputDir = new File(outputFolder);
            if (!outputDir.exists()) {
                boolean created = outputDir.mkdirs();
                if (!created) {
                    System.err.println("Failed to create output directory: " + outputFolder);
                    System.exit(1);
                }
            } else if (!outputDir.isDirectory()) {
                System.err.println("Output path exists but is not a directory: " + outputFolder);
                System.exit(1);
            }

            switch (transformationType.toLowerCase()) {
                case "swarch2gitlab":
                    ResourceSet resourceSet = new ResourceSetImpl();
                    try {
                        EObject inputModel = ModelLoader.loadModel(inputModelPath, resourceSet, EObject.class);

                        if (inputModel == null) {
                            System.err.println("Failed to load input model: " + inputModelPath);
                            System.exit(1);
                        }

                        if (inputModel.eClass().getEPackage() != SwarchPackage.eINSTANCE) {
                            System.err.println("Input model should be an SW Arch metamodel instance.");
                            System.exit(1);
                        }

                        File intermediateDir = new File("./test/generatedModels");
                        if (!intermediateDir.exists()) {
                            boolean created = intermediateDir.mkdirs();
                            if (!created) {
                                System.err.println("Failed to create intermediate directory: ./test/generatedModels");
                                System.exit(1);
                            }
                        }

                        String outputModelFilePath = "./test/generatedModels/PipelinePIM.pimmm";
                        EObject pimModel = TransformerExecutor.execute("swarch2pim", inputModel, outputModelFilePath);

                        if (pimModel == null) {
                            System.err.println("Transformation from Software Architecture to PIM failed");
                            System.exit(1);
                        }

                        System.out.println(
                                "Software Architecture Input model transformed to Platform Independent Model...");

                        outputModelFilePath = "./test/generatedModels/PipelineGit.gitlabmm";
                        EObject gitlabModel = TransformerExecutor.execute("pim2gitlab", pimModel, outputModelFilePath);

                        if (gitlabModel == null) {
                            System.err.println("Transformation from PIM to GitLab model failed");
                            System.exit(1);
                        }

                        System.out.println(
                                "Platform Independent Model transformed to Platform Specific Model (GitLab Model)...");

                        GeneratorExecutor.execute(gitlabModel, "gitlab", outputFolder);

                        File[] files = new File(outputFolder).listFiles();
                        if (files == null || files.length == 0) {
                            System.err.println("No files were generated in output folder: " + outputFolder);
                            System.exit(1);
                        }

                        System.out.println("GitLab YAML Code has been generated...");
                    } catch (Exception e) {
                        System.err.println("Error during transformation process: " + e.getMessage());
                        e.printStackTrace();
                        System.exit(1);
                    }
                    break;

                case "pim2gitlab":
                    ResourceSet resourceSet2 = new ResourceSetImpl();
                    try {
                        EObject inputModel = ModelLoader.loadModel(inputModelPath, resourceSet2, EObject.class);

                        if (inputModel == null) {
                            System.err.println("Failed to load PIM model: " + inputModelPath);
                            System.exit(1);
                        }

                        if (inputModel.eClass().getEPackage() != PimMMPackage.eINSTANCE) {
                            System.err.println("Input model should be an PIM metamodel instance.");
                            System.exit(1);
                        }

                        File intermediateDir = new File("./test/generatedModels");
                        if (!intermediateDir.exists()) {
                            boolean created = intermediateDir.mkdirs();
                            if (!created) {
                                System.err.println("Failed to create intermediate directory: ./test/generatedModels");
                                System.exit(1);
                            }
                        }

                        String outputModelFilePath = "./test/generatedModels/PipelineGit.gitlabmm";
                        EObject gitlabModel = TransformerExecutor.execute("pim2gitlab", inputModel,
                                outputModelFilePath);

                        if (gitlabModel == null) {
                            System.err.println("Transformation from PIM to GitLab model failed");
                            System.exit(1);
                        }

                        System.out.println(
                                "Platform Independent Model transformed to Platform Specific Model (GitLab Model)...");

                        GeneratorExecutor.execute(gitlabModel, "gitlab", outputFolder);

                        File[] files = new File(outputFolder).listFiles();
                        if (files == null || files.length == 0) {
                            System.err.println("No files were generated in output folder: " + outputFolder);
                            System.exit(1);
                        }

                        System.out.println("GitLab YAML Code has been generated...");
                    } catch (Exception e) {
                        System.err.println("Error during transformation process: " + e.getMessage());
                        e.printStackTrace();
                        System.exit(1);
                    }
                    break;

                case "psm2gitlab":
                    ResourceSet resourceSet3 = new ResourceSetImpl();
                    try {
                        EObject inputModel = ModelLoader.loadModel(inputModelPath, resourceSet3, EObject.class);

                        if (inputModel == null) {
                            System.err.println("Failed to load Gitlab model: " + inputModelPath);
                            System.exit(1);
                        }

                        if (inputModel.eClass().getEPackage() != GitlabMMPackage.eINSTANCE) {
                            System.err.println("Input model should be a GitLab metamodel instance.");
                            System.exit(1);
                        }

                        GeneratorExecutor.execute(inputModel, "gitlab", outputFolder);

                        File[] files = new File(outputFolder).listFiles();
                        if (files == null || files.length == 0) {
                            System.err.println("No files were generated in output folder: " + outputFolder);
                            System.exit(1);
                        }

                        System.out.println("GitLab YAML Code has been generated...");
                    } catch (Exception e) {
                        System.err.println("Error during transformation process: " + e.getMessage());
                        e.printStackTrace();
                        System.exit(1);
                    }
                    break;

                default:
                    System.err.println("Incorrect transformation type was provided: " + transformationType);
                    System.err.println("Supported transformation types: swarch2gitlab, pim2gitlab, psm2gitlab");
                    System.exit(1);
                    break;
            }
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
