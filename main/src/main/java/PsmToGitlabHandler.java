package main.java;

import java.io.File;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage;
import main.java.mddoai.generators.GeneratorExecutor;
import main.java.mddoai.loaders.ModelLoader;

public class PsmToGitlabHandler {

    public static void handle(String inputModelPath, String outputFolder) throws Exception {
        ResourceSet resourceSet = new ResourceSetImpl();

        EObject inputModel = ModelLoader.loadModel(inputModelPath, resourceSet, EObject.class);

        if (inputModel == null) {
            throw new ExitException(1, "Failed to load GitLab model: " + inputModelPath);
        }

        if (inputModel.eClass().getEPackage() != GitlabMMPackage.eINSTANCE) {
            throw new ExitException(1, "Input model should be a GitLab metamodel instance.");
        }

        GeneratorExecutor.execute(inputModel, "gitlab", outputFolder);

        File[] files = new File(outputFolder).listFiles();
        if (files == null || files.length == 0) {
            throw new ExitException(1, "No files were generated in output folder: " + outputFolder);
        }

        System.out.println("GitLab YAML Code has been generated...");
    }
}
