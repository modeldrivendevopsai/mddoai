package main.java.mddoai.generators;

import main.java.mddoai.generators.wrappers.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage;

public class GeneratorFactory {

    @SuppressWarnings("unchecked")
    public static <T extends EObject> Generator<T> create(String type, ResourceSet rs) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Generator type cannot be null or empty");
        }
        
        if (rs == null) {
            throw new IllegalArgumentException("ResourceSet cannot be null");
        }
        
        try {
            return switch (type.toLowerCase()) {
                case "gitlab" -> (Generator<T>) new GenericGenerator<>(
                    rs,
                    GitlabMMPackage.eINSTANCE,
                    new GitLabGenerator()
                );
                default -> throw new IllegalArgumentException("Unknown generator type: " + type);
            };
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to create generator of type '" + type + "': " + e.getMessage(), e);
        }
    }
}
