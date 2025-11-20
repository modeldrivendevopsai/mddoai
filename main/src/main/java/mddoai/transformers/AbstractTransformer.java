package main.java.mddoai.transformers;

import main.java.mddoai.utils.EMFUtils;
import main.java.mddoai.utils.JavaUtils;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.m2m.atl.core.*;
import org.eclipse.m2m.atl.core.emf.EMFExtractor;
import org.eclipse.m2m.atl.core.emf.EMFInjector;
import org.eclipse.m2m.atl.core.emf.EMFModelFactory;
import org.eclipse.m2m.atl.core.launch.ILauncher;
import org.eclipse.m2m.atl.engine.emfvm.launch.EMFVMLauncher;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTransformer<InputModel extends EObject, InputPackage extends EPackage, OutputModel extends EObject, OutputPackage extends EPackage> {
    private final ResourceSet resourceSet;
    private final InputPackage inputPackage;
    private final OutputPackage outputPackage;
    private IModel inputModel;
    private IModel outputModel;
    private final String atlFilePath;
    private final InputStream atlFileStream;
    private final String inputModelName;
    private final String outputModelName;
    private boolean deleteIntermediateFiles = false;

    protected AbstractTransformer(ResourceSet resourceSet, InputPackage inputPackage, OutputPackage outputPackage, String atlFilePath, String inputModelName, String outputModelName) throws IOException {
        if (resourceSet == null) {
            throw new IllegalArgumentException("ResourceSet cannot be null");
        }
        if (inputPackage == null) {
            throw new IllegalArgumentException("InputPackage cannot be null");
        }
        if (outputPackage == null) {
            throw new IllegalArgumentException("OutputPackage cannot be null");
        }
        if (atlFilePath == null || atlFilePath.trim().isEmpty()) {
            throw new IllegalArgumentException("ATL file path cannot be null or empty");
        }
        if (inputModelName == null || inputModelName.trim().isEmpty()) {
            throw new IllegalArgumentException("Input model name cannot be null or empty");
        }
        if (outputModelName == null || outputModelName.trim().isEmpty()) {
            throw new IllegalArgumentException("Output model name cannot be null or empty");
        }
        
        this.resourceSet = resourceSet;
        this.inputPackage = inputPackage;
        this.outputPackage = outputPackage;
        this.atlFilePath = atlFilePath;
        
        Path path = Path.of(atlFilePath);
        if (path.toFile().exists()) {
            this.atlFileStream = path.toUri().toURL().openStream();
        } else {
            InputStream stream = JavaUtils.getResourceAsStream(atlFilePath);
            if (stream == null) {
                throw new IOException("ATL file not found: " + atlFilePath);
            }
            this.atlFileStream = stream;
        }
        
        this.inputModelName = inputModelName;
        this.outputModelName = outputModelName;
        checkRegistry();
    }

    protected final void checkRegistry() {
        EMFUtils.registerPackages(getResourceSet(), getInputPackage(), getOutputPackage());
        EMFUtils.registerExtensionToFactoryMap(getResourceSet(), "ecore", new EcoreResourceFactoryImpl());
        EMFUtils.registerExtensionToFactoryMap(getResourceSet(), "xmi", new XMIResourceFactoryImpl());
    }

    public IModel getInputModel() {
        return inputModel;
    }

    public IModel getOutputModel() {
        return outputModel;
    }

    public OutputModel transform(InputModel inputModel) throws IOException {
        if (inputModel == null) {
            throw new IllegalArgumentException("Input model cannot be null");
        }
        
        String tempDir = "intermediate";
        File intermediateDir = new File(tempDir);
        if (!intermediateDir.exists()) {
            if (!intermediateDir.mkdirs()) {
                throw new IOException("Failed to create intermediate directory: " + tempDir);
            }
        }
        
        String inputModelFilePath = serializeModel(inputModel);
        
        String outputModelFilePath = inputModelFilePath + ".out.xmi";

        try {
            runATLTransformation(inputModelFilePath, outputModelFilePath);
            
            if (!new File(outputModelFilePath).exists()) {
                throw new IOException("Transformation failed: Output file was not created");
            }
            
            OutputModel outputModel = deserializeModel(outputModelFilePath);
            
            if (outputModel == null) {
                throw new IOException("Transformation failed: Output model is null");
            }

            if (deleteIntermediateFiles) {
                Files.delete(Path.of(inputModelFilePath));
                Files.delete(Path.of(outputModelFilePath));
            }

            JavaUtils.cleanUp();
            
            return outputModel;
        } catch (IOException e) {
            try {
                Files.deleteIfExists(Path.of(inputModelFilePath));
                Files.deleteIfExists(Path.of(outputModelFilePath));
            } catch (IOException cleanupEx) {
            }
            
            throw new IOException("Transformation failed: " + e.getMessage(), e);
        }
    }

    public ResourceSet getResourceSet() {
        return resourceSet;
    }

    public InputPackage getInputPackage() {
        return inputPackage;
    }

    public OutputPackage getOutputPackage() {
        return outputPackage;
    }

    private String serializeModel(InputModel model) throws IOException {
        String tempDir = "intermediate";
        String randomName = JavaUtils.getRandomName() + ".xmi";
        String filePath = Path.of(tempDir, randomName).toString();

        EMFUtils.serializeModel(model, filePath, getResourceSet());
        
        File outputFile = new File(filePath);
        if (!outputFile.exists() || outputFile.length() == 0) {
            throw new IOException("Failed to serialize model to " + filePath);
        }

        return filePath;
    }

    @SuppressWarnings("unchecked")
    private OutputModel deserializeModel(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("Model file does not exist: " + filePath);
        }
        if (file.length() == 0) {
            throw new IOException("Model file is empty: " + filePath);
        }
        
        Object model = EMFUtils.deserializeModel(filePath, getResourceSet());
        
        if (model == null) {
            throw new IOException("Failed to deserialize model from " + filePath);
        }
        
        try {
            return (OutputModel) model;
        } catch (ClassCastException e) {
            throw new IOException("Deserialized model is not of expected type", e);
        }
    }

    protected void loadModels(String inputModelFilePath) throws ATLCoreException { 
        ModelFactory modelFactory = new EMFModelFactory();
        EMFInjector injector = new EMFInjector();

        IReferenceModel inputMetamodel = modelFactory.newReferenceModel();
        injector.inject(inputMetamodel, getInputPackage().eResource());

        IReferenceModel outputMetamodel = modelFactory.newReferenceModel();
        injector.inject(outputMetamodel, getOutputPackage().eResource());

        inputModel = modelFactory.newModel(inputMetamodel);
        injector.inject(getInputModel(), inputModelFilePath);

        outputModel = modelFactory.newModel(outputMetamodel);
    }

    protected void runATLTransformation(String inputModelFilePath, String outputModelFilePath) throws IOException {
        try {
            loadModels(inputModelFilePath);

            ILauncher launcher = new EMFVMLauncher();
            launcher.initialize(new HashMap<>());
            launcher.addInModel(getInputModel(), "IN", getInModelName());
            launcher.addOutModel(getOutputModel(), "OUT", getOutModelName());

            Map<String, Object> options = new HashMap<>();

            options.put("step", true);
            options.put("printExecutionTime", true);
            
            InputStream atlStream = getATLFileStream();
            if (atlStream == null) {
                throw new IOException("ATL file stream is null: " + atlFilePath);
            }

            launcher.launch(ILauncher.RUN_MODE, new NullProgressMonitor(), options, atlStream);

            IExtractor extractor = new EMFExtractor();
            extractor.extract(getOutputModel(), outputModelFilePath);
            
            File outputFile = new File(outputModelFilePath);
            if (!outputFile.exists() || outputFile.length() == 0) {
                throw new IOException("ATL transformation failed to produce output file: " + outputModelFilePath);
            }
        } catch (ATLCoreException e) {
            throw new IOException("ATL transformation failed: " + e.getMessage(), e);
        }
    }

    protected InputStream getATLFileStream() {
        if (atlFilePath != null) {
            Path path = Path.of(atlFilePath);
            if (path.toFile().exists()) {
                try {
                    return path.toUri().toURL().openStream();
                } catch (IOException e) {
                    throw new RuntimeException("Failed to open ATL file: " + atlFilePath, e);
                }
            } else {
                InputStream stream = JavaUtils.getResourceAsStream(atlFilePath);
                if (stream == null) {
                    throw new RuntimeException("ATL file not found: " + atlFilePath);
                }
                return stream;
            }
        }
        return atlFileStream;
    }

    protected String getInModelName() {
        return inputModelName;
    }

    protected String getOutModelName() {
        return outputModelName;
    }
}
