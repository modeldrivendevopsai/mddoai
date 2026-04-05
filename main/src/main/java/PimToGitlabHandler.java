package main.java;

import java.io.File;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import com.mddoai.metamodel.pim.pimMM.PimMMPackage;
import main.java.mddoai.generators.GeneratorExecutor;
import main.java.mddoai.loaders.ModelLoader;
import main.java.mddoai.transformers.TransformerExecutor;

public class PimToGitlabHandler {

    public static void handle(String inputModelPath, String outputFolder) {
        ResourceSet resourceSet = new ResourceSetImpl();
        try {
            EObject inputModel = ModelLoader.loadModel(inputModelPath, resourceSet, EObject.class);

            if (inputModel == null) {
                System.err.println("Failed to load PIM model: " + inputModelPath);
                throw new ExitException(1);
            }

            if (inputModel.eClass().getEPackage() != PimMMPackage.eINSTANCE) {
                System.err.println("Input model should be an PIM metamodel instance.");
                throw new ExitException(1);
            }

            File intermediateDir = new File("./test/generatedModels");
            if (!intermediateDir.exists()) {
                boolean created = intermediateDir.mkdirs();
                if (!created) {
                    System.err.println("Failed to create intermediate directory: ./test/generatedModels");
                    throw new ExitException(1);
                }
            }

            String outputModelFilePath = "./test/generatedModels/PipelineGit.gitlabmm";
            EObject gitlabModel = TransformerExecutor.execute("pim2gitlab", inputModel, outputModelFilePath);

            if (gitlabModel == null) {
                System.err.println("Transformation from PIM to GitLab model failed");
                throw new ExitException(1);
            }

            System.out.println("Platform Independent Model transformed to Platform Specific Model (GitLab Model)...");

            GeneratorExecutor.execute(gitlabModel, "gitlab", outputFolder);

            File[] files = new File(outputFolder).listFiles();
            if (files == null || files.length == 0) {
                System.err.println("No files were generated in output folder: " + outputFolder);
                throw new ExitException(1);
            }

            System.out.println("GitLab YAML Code has been generated...");
        } catch (Exception e) {
            System.err.println("Error during transformation process: " + e.getMessage());
            e.printStackTrace();
            throw new ExitException(1);
        }
    }
}