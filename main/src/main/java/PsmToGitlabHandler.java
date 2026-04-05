package main.java;

import java.io.File;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage;
import main.java.mddoai.generators.GeneratorExecutor;
import main.java.mddoai.loaders.ModelLoader;

public class PsmToGitlabHandler {

    public static void handle(String inputModelPath, String outputFolder) {
        ResourceSet resourceSet = new ResourceSetImpl();
        try {
            EObject inputModel = ModelLoader.loadModel(inputModelPath, resourceSet, EObject.class);

            if (inputModel == null) {
                System.err.println("Failed to load Gitlab model: " + inputModelPath);
                throw new ExitException(1);
            }

            if (inputModel.eClass().getEPackage() != GitlabMMPackage.eINSTANCE) {
                System.err.println("Input model should be a GitLab metamodel instance.");
                throw new ExitException(1);
            }

            GeneratorExecutor.execute(inputModel, "gitlab", outputFolder);

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