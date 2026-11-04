package main.java.mddoai.validation.swarch;

import com.mddoai.metamodel.swarch.SwarchPackage;
import main.java.mddoai.utils.EMFUtils;
import main.java.mddoai.validation.ValidationIssue;
import main.java.mddoai.validation.ValidationResult;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Headless validation of {@code .swarch} Software Architecture model
 * instances: whether a file loads as a SWArch model at all, and whether it
 * satisfies the SWArch metamodel's own constraints (the same EMF
 * {@link Diagnostician} check Eclipse's own "Validate" menu action runs).
 * Never throws for a bad input file - a bad file is reported as a failing
 * {@link ValidationResult}, not an exception. Only programmer error
 * (null/empty path) throws.
 */
public final class SwarchValidator {

    private SwarchValidator() {
    }

    public static ValidationResult validate(String swarchFilePath) {
        requireNonBlank(swarchFilePath);

        File file = new File(swarchFilePath);
        if (!file.exists()) {
            return ValidationResult.of(List.of(new ValidationIssue(
                    ValidationIssue.Severity.ERROR, "File does not exist: " + swarchFilePath, swarchFilePath)));
        }

        List<ValidationIssue> issues = new ArrayList<>();
        Resource resource = load(file, swarchFilePath, issues);
        EObject root = resource == null ? null : firstRoot(resource, swarchFilePath, issues);
        if (root != null) {
            checkModel(root, swarchFilePath, issues);
        }
        return ValidationResult.of(issues);
    }

    /** The parsed resource, or null (with the reason in {@code issues}) if the file can't be parsed at all. */
    private static Resource load(File file, String sourceFile, List<ValidationIssue> issues) {
        EMFUtils.init();
        ResourceSet resourceSet = new ResourceSetImpl();
        // The content is XMI whatever the file happens to be called: without
        // a default factory, a name whose extension EMF has no factory for
        // (a copy saved as .txt, an upper-case .SWARCH) gets no resource at all.
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                .put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
        // A real caller submits this file's content over HTTP, so it is not
        // trusted: DOCTYPE declarations are rejected outright (XXE) and
        // network-scheme cross-references are blocked (SSRF), the same
        // hardening EcoreValidator applies to submitted .ecore content. The
        // load-options helper is named for .ecore but only sets generic XML
        // parser features.
        EMFUtils.hardenAgainstUntrustedContent(resourceSet);

        Resource resource = resourceSet.createResource(URI.createFileURI(file.getAbsolutePath()));
        try {
            resource.load(EMFUtils.hardenedEcoreLoadOptions());
        } catch (Exception e) {
            issues.add(new ValidationIssue(ValidationIssue.Severity.ERROR,
                    "Failed to parse .swarch file: " + scrubPath(e.getMessage(), resource, file), sourceFile));
            return null;
        }

        for (Resource.Diagnostic error : resource.getErrors()) {
            issues.add(new ValidationIssue(ValidationIssue.Severity.ERROR,
                    scrubPath(error.getMessage(), resource, file), locate(sourceFile, error.getLine())));
        }
        for (Resource.Diagnostic warning : resource.getWarnings()) {
            issues.add(new ValidationIssue(ValidationIssue.Severity.WARNING,
                    scrubPath(warning.getMessage(), resource, file), locate(sourceFile, warning.getLine())));
        }
        return resource;
    }

    /** The model's root element, or null (with the reason in {@code issues}) if the file holds none. */
    private static EObject firstRoot(Resource resource, String sourceFile, List<ValidationIssue> issues) {
        if (resource.getContents().isEmpty()) {
            issues.add(new ValidationIssue(ValidationIssue.Severity.ERROR, "File has no content", sourceFile));
            return null;
        }
        // The generation engine loads and resolves every top-level element, but
        // only the first is examined here, so a file with more than one cannot
        // be vouched for as valid.
        if (resource.getContents().size() > 1) {
            issues.add(new ValidationIssue(ValidationIssue.Severity.ERROR,
                    "File holds " + resource.getContents().size()
                            + " top-level elements; a Software Architecture model has exactly one", sourceFile));
        }
        EObject root = resource.getContents().get(0);
        EcoreUtil.resolveAll(root);
        return root;
    }

    private static void checkModel(EObject root, String sourceFile, List<ValidationIssue> issues) {
        if (root.eClass().getEPackage() != SwarchPackage.eINSTANCE) {
            issues.add(new ValidationIssue(ValidationIssue.Severity.ERROR,
                    "Root element is a '" + root.eClass().getName()
                            + "', not a Software Architecture model (expected a SWArch metamodel instance)",
                    sourceFile));
            return;
        }
        flattenDiagnostic(Diagnostician.INSTANCE.validate(root), sourceFile, issues);
        findBlankRequiredStrings(root, sourceFile, issues);
    }

    // The metamodel's own multiplicity constraint (lowerBound=1) is satisfied by
    // an empty string, but a blank value for a required text feature (an
    // architecture with no name, no language) can never produce a meaningful
    // pipeline - reported here, driven by the metamodel's own definition of
    // which features are required rather than a hardcoded list.
    private static void findBlankRequiredStrings(EObject root, String sourceFile, List<ValidationIssue> issues) {
        for (EAttribute attribute : root.eClass().getEAllAttributes()) {
            if (!attribute.isRequired() || attribute.isMany()
                    || attribute.getEAttributeType() != EcorePackage.Literals.ESTRING) {
                continue;
            }
            Object value = root.eGet(attribute);
            if (value instanceof String text && text.isBlank()) {
                issues.add(new ValidationIssue(ValidationIssue.Severity.ERROR,
                        "The required feature '" + attribute.getName() + "' of '"
                                + root.eClass().getName() + "' must not be empty",
                        sourceFile));
            }
        }
    }

    // Only leaf diagnostics are reported: the aggregate "Diagnosis of <object>"
    // parent EMF wraps around them carries no information of its own, and its
    // text embeds the object's internal identity (implementation class, hash,
    // and the full file URI).
    private static void flattenDiagnostic(org.eclipse.emf.common.util.Diagnostic diagnostic,
                                          String sourceFile, List<ValidationIssue> issues) {
        if (!diagnostic.getChildren().isEmpty()) {
            for (org.eclipse.emf.common.util.Diagnostic child : diagnostic.getChildren()) {
                flattenDiagnostic(child, sourceFile, issues);
            }
            return;
        }
        ValidationIssue.Severity severity = switch (diagnostic.getSeverity()) {
            case org.eclipse.emf.common.util.Diagnostic.ERROR -> ValidationIssue.Severity.ERROR;
            case org.eclipse.emf.common.util.Diagnostic.WARNING -> ValidationIssue.Severity.WARNING;
            default -> null;
        };
        if (severity != null) {
            issues.add(new ValidationIssue(severity, readableMessage(diagnostic), sourceFile));
        }
    }

    // EMF words a constraint violation around the object's internal label
    // (implementation class name, identity hash, file URI); swapped for the
    // metamodel class's own name, which is what a person reading the message
    // actually knows the object as.
    private static String readableMessage(org.eclipse.emf.common.util.Diagnostic diagnostic) {
        String message = diagnostic.getMessage();
        for (Object datum : diagnostic.getData()) {
            if (datum instanceof EObject object) {
                message = message.replace(Diagnostician.INSTANCE.getObjectLabel(object), object.eClass().getName());
            }
        }
        return message;
    }

    // EMF's own load messages embed the file's full URI, which for a real
    // caller is a server-side temp path that means nothing to them (and is
    // not something to hand back over HTTP) - replaced by the bare filename.
    // A missing message becomes a fixed phrase, since an issue may not be blank.
    private static String scrubPath(String message, Resource resource, File file) {
        if (message == null || message.isBlank()) {
            return "no details available";
        }
        return message
                .replace(resource.getURI().toString(), file.getName())
                .replace(file.getAbsolutePath(), file.getName());
    }

    private static String locate(String sourceFile, int line) {
        return line > 0 ? sourceFile + ":" + line : sourceFile;
    }

    private static void requireNonBlank(String path) {
        if (path == null || path.isBlank()) {
            throw new IllegalArgumentException("Swarch file path cannot be null or empty");
        }
    }
}
