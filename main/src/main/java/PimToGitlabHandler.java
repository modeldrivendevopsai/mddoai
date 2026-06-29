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

    public static void handle(String inputModelPath, String outputFolder) throws Exception {
        ResourceSet resourceSet = new ResourceSetImpl();

        EObject inputModel = ModelLoader.loadModel(inputModelPath, resourceSet, EObject.class);

        if (inputModel == null) {
            throw new ExitException(1, "Failed to load PIM model: " + inputModelPath);
        }

        if (inputModel.eClass().getEPackage() != PimMMPackage.eINSTANCE) {
            throw new ExitException(1, "Input model should be a PIM metamodel instance.");
        }

        File intermediateDir = new File("./test/generatedModels");
        if (!intermediateDir.exists()) {
            boolean created = intermediateDir.mkdirs();
            if (!created) {
                throw new ExitException(1, "Failed to create intermediate directory: ./test/generatedModels");
            }
        }

        String outputModelFilePath = "./test/generatedModels/PipelineGit.gitlabmm";
        EObject gitlabModel = TransformerExecutor.execute("pim2gitlab", inputModel, outputModelFilePath);

        if (gitlabModel == null) {
            throw new ExitException(1, "Transformation from PIM to GitLab model failed");
        }

        System.out.println("Platform Independent Model transformed to Platform Specific Model (GitLab Model)...");

        GeneratorExecutor.execute(gitlabModel, "gitlab", outputFolder);

        File[] files = new File(outputFolder).listFiles();
        if (files == null || files.length == 0) {
            throw new ExitException(1, "No files were generated in output folder: " + outputFolder);
        }

        System.out.println("GitLab YAML Code has been generated...");
    }
}
