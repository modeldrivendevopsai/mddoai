package main.java;

import java.io.File;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import com.mddoai.metamodel.swarch.SwarchPackage;
import main.java.mddoai.generators.GeneratorExecutor;
import main.java.mddoai.loaders.ModelLoader;
import main.java.mddoai.transformers.TransformerExecutor;

public class SwarchToGitlabHandler {

    public static void handle(String inputModelPath, String outputFolder) {
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

            System.out.println("Software Architecture Input model transformed to Platform Independent Model...");

            outputModelFilePath = "./test/generatedModels/PipelineGit.gitlabmm";
            EObject gitlabModel = TransformerExecutor.execute("pim2gitlab", pimModel, outputModelFilePath);

            if (gitlabModel == null) {
                System.err.println("Transformation from PIM to GitLab model failed");
                System.exit(1);
            }

            System.out.println("Platform Independent Model transformed to Platform Specific Model (GitLab Model)...");

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
    }
}