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

    public static void handle(String inputModelPath, String outputFolder) throws Exception {
        ResourceSet resourceSet = new ResourceSetImpl();

        EObject inputModel = ModelLoader.loadModel(inputModelPath, resourceSet, EObject.class);

        if (inputModel == null) {
            throw new ExitException(1, "Failed to load input model: " + inputModelPath);
        }

        if (inputModel.eClass().getEPackage() != SwarchPackage.eINSTANCE) {
            throw new ExitException(1, "Input model should be an SW Arch metamodel instance.");
        }

        File intermediateDir = new File("./test/generatedModels");
        if (!intermediateDir.exists()) {
            boolean created = intermediateDir.mkdirs();
            if (!created) {
                throw new ExitException(1, "Failed to create intermediate directory: ./test/generatedModels");
            }
        }

        String pimOutputPath = "./test/generatedModels/PipelinePIM.pimmm";
        EObject pimModel = TransformerExecutor.execute("swarch2pim", inputModel, pimOutputPath);

        if (pimModel == null) {
            throw new ExitException(1, "Transformation from Software Architecture to PIM failed");
        }

        System.out.println("Software Architecture Input model transformed to Platform Independent Model...");

        String gitlabOutputPath = "./test/generatedModels/PipelineGit.gitlabmm";
        EObject gitlabModel = TransformerExecutor.execute("pim2gitlab", pimModel, gitlabOutputPath);

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
