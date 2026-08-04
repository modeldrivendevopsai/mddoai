package main.java.mddoai.validation;

import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenModelGeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Isolates GenModelFactory/Generator calls. Builds a GenModel from an
 * already-loaded EPackage and runs EMF's real headless codegen against it,
 * writing generated .java under {@code workDir/src-gen}.
 *
 * Three steps here are required only in standalone/headless mode and are
 * silent no-ops if skipped (no exception, just zero files written) — see the
 * comments inline. None of this is discoverable from EMF's own "typical
 * usage" javadoc example alone; confirmed empirically against a real .ecore.
 */
final class GenModelBuilder {

    private static final Object ADAPTER_REGISTRATION_LOCK = new Object();
    private static volatile boolean adapterRegistered = false;

    private GenModelBuilder() {
    }

    record Result(List<ValidationIssue> issues, boolean hasError, File sourceDirectory) {
    }

    static Result generate(EPackage ePackage, File workDir, String sourceFileForIssues) {
        ensureAdapterFactoryRegistered();

        // Unique per call: EcorePlugin's platform resource map (below) is process-global
        // state, so a fixed plugin ID would collide across concurrent/repeated calls.
        String modelPluginID = "validate." + workDir.getName();

        GenModel genModel = GenModelFactory.eINSTANCE.createGenModel();
        genModel.initialize(Collections.singleton(ePackage));
        // Project-relative path ("/pluginID/src-gen"), NOT an absolute filesystem path —
        // confirmed against the real pimMM.genmodel's own modelDirectory attribute.
        // Passing a real absolute path here throws IndexOutOfBoundsException deep inside
        // GenModelImpl.getModelProject() (confirmed empirically).
        genModel.setModelDirectory("/" + modelPluginID + "/src-gen");
        genModel.setModelPluginID(modelPluginID);
        genModel.setComplianceLevel(currentJdkComplianceLevel());
        genModel.setRootExtendsClass("org.eclipse.emf.ecore.impl.MinimalEObjectImpl$Container");
        genModel.setCanGenerate(true);
        genModel.reconcile();

        ResourceSet resourceSet = ePackage.eResource() != null
                ? ePackage.eResource().getResourceSet()
                : new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                .put("genmodel", new XMIResourceFactoryImpl());
        Resource genModelResource = resourceSet.createResource(
                URI.createFileURI(new File(workDir, "model.genmodel").getAbsolutePath()));
        genModelResource.getContents().add(genModel);

        // Generated output paths are platform:/resource/<modelPluginID>/... URIs; outside
        // Eclipse that scheme is unresolvable unless mapped to a real directory here. This
        // is the actual fix for "generate() returns a clean OK diagnostic but writes nothing."
        EcorePlugin.getPlatformResourceMap().put(modelPluginID,
                URI.createFileURI(workDir.getAbsolutePath() + "/"));
        try {
            org.eclipse.emf.codegen.ecore.generator.Generator generator =
                    new org.eclipse.emf.codegen.ecore.generator.Generator();
            generator.setInput(genModel);
            Diagnostic diagnostic = generator.generate(
                    genModel, GenBaseGeneratorAdapter.MODEL_PROJECT_TYPE, new BasicMonitor());

            List<ValidationIssue> issues = new ArrayList<>();
            boolean hasError = flatten(diagnostic, sourceFileForIssues, issues);
            return new Result(issues, hasError, new File(workDir, "src-gen"));
        } finally {
            EcorePlugin.getPlatformResourceMap().remove(modelPluginID);
        }
    }

    private static boolean flatten(Diagnostic diagnostic, String sourceFile, List<ValidationIssue> issues) {
        boolean hasError = false;
        if (diagnostic.getSeverity() >= Diagnostic.ERROR) {
            // The top-level child's own getException() is often null while a grandchild
            // carries the real Throwable — recursing (below) is what actually surfaces it.
            Throwable exception = diagnostic.getException();
            String message = diagnostic.getMessage() + (exception != null ? ": " + exception : "");
            issues.add(new ValidationIssue(ValidationIssue.Severity.ERROR, message, sourceFile));
            hasError = true;
        } else if (diagnostic.getSeverity() == Diagnostic.WARNING) {
            issues.add(new ValidationIssue(ValidationIssue.Severity.WARNING, diagnostic.getMessage(), sourceFile));
        }
        for (Diagnostic child : diagnostic.getChildren()) {
            hasError |= flatten(child, sourceFile, issues);
        }
        return hasError;
    }

    private static GenJDKLevel currentJdkComplianceLevel() {
        // Match the compliance level to the JDK actually running this validator, so the
        // deeper check never asks javac to target a level higher than what's available.
        String version = System.getProperty("java.specification.version");
        String literal = version.contains(".") ? version : version + ".0";
        GenJDKLevel level = GenJDKLevel.get(literal);
        return level != null ? level : GenJDKLevel.JDK170_LITERAL;
    }

    private static void ensureAdapterFactoryRegistered() {
        if (adapterRegistered) {
            return;
        }
        synchronized (ADAPTER_REGISTRATION_LOCK) {
            if (!adapterRegistered) {
                GeneratorAdapterFactory.Descriptor.Registry.INSTANCE.addDescriptor(
                        GenModelPackage.eNS_URI, GenModelGeneratorAdapterFactory.DESCRIPTOR);
                adapterRegistered = true;
            }
        }
    }
}
