package main.java.mddoai.generators;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import main.java.mddoai.utils.EMFUtils;
import main.java.mddoai.utils.JavaUtils;

import static main.java.mddoai.utils.EMFUtils.serializeModel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public abstract class AbstractGenerator<InputModel extends EObject, InputPackage extends EPackage> {
    private final InputPackage inputPackage;
    private final ResourceSet resourceSet;

    public AbstractGenerator(ResourceSet resourceSet, InputPackage inputPackage) {
        if (resourceSet == null) {
            throw new IllegalArgumentException("ResourceSet cannot be null");
        }
        if (inputPackage == null) {
            throw new IllegalArgumentException("InputPackage cannot be null");
        }
        
        this.resourceSet = resourceSet;
        this.inputPackage = inputPackage;
        registerPackages();
    }

    public void generate(InputModel inputModel, String outputFolder) throws IOException {
        if (inputModel == null) {
            throw new IllegalArgumentException("Input model cannot be null");
        }
        if (outputFolder == null || outputFolder.trim().isEmpty()) {
            throw new IllegalArgumentException("Output folder cannot be null or empty");
        }
        
        File outDir = new File(outputFolder);
        if (!outDir.exists()) {
            if (!outDir.mkdirs()) {
                throw new IOException("Failed to create output directory: " + outputFolder);
            }
        } else if (!outDir.isDirectory()) {
            throw new IOException("Output path exists but is not a directory: " + outputFolder);
        }
        
    	JavaUtils.cleanUp();
    	
        String tempDir = "intermediate";
        File intermediateDir = new File(tempDir);
        if (!intermediateDir.exists()) {
            if (!intermediateDir.mkdirs()) {
                throw new IOException("Failed to create intermediate directory: " + tempDir);
            }
        }
        
        String randomName = JavaUtils.getRandomName() + ".xmi";
        String filePath = Path.of(tempDir, randomName).toString();
        
        try {
            serializeModel(inputModel, filePath, getResourceSet());
            
            File tempFile = new File(filePath);
            if (!tempFile.exists() || tempFile.length() == 0) {
                throw new IOException("Failed to serialize model to " + filePath);
            }
            
            runGeneratorMain(List.of(filePath, outputFolder).toArray(String[]::new));
            
            Files.delete(Path.of(filePath));
        } catch (IOException e) {
            try {
                Files.deleteIfExists(Path.of(filePath));
            } catch (IOException cleanupEx) {
            }
            
            throw new IOException("Generation failed: " + e.getMessage(), e);
        }
    }

    protected abstract void runGeneratorMain(String[] args);

    public ResourceSet getResourceSet() {
        return resourceSet;
    }

    public InputPackage getInputPackage() {
        return inputPackage;
    }

    private void registerPackages() {
        EMFUtils.registerPackages(getResourceSet(), getInputPackage());
    }
}
