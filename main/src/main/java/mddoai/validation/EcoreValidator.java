package main.java.mddoai.validation;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Headless validation of {@code .ecore} metamodels: whether a file is
 * structurally sound (reflective check), and whether it actually compiles to
 * real Java via EMF's own codegen path (deeper check). Never throws for a bad
 * input file — a bad file is reported as a failing {@link ValidationResult},
 * not an exception. Only programmer error (null/empty path) throws.
 */
public final class EcoreValidator {

    private EcoreValidator() {
    }

    public static ValidationResult validateReflectively(String ecoreFilePath) {
        requireNonBlank(ecoreFilePath);

        File file = new File(ecoreFilePath);
        if (!file.exists()) {
            return ValidationResult.of(List.of(new ValidationIssue(
                    ValidationIssue.Severity.ERROR, "File does not exist: " + ecoreFilePath, ecoreFilePath)));
        }

        List<ValidationIssue> issues = new ArrayList<>();
        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                .put("ecore", new EcoreResourceFactoryImpl());

        Resource resource = resourceSet.createResource(URI.createFileURI(file.getAbsolutePath()));
        try {
            resource.load(null);
        } catch (Exception e) {
            issues.add(new ValidationIssue(ValidationIssue.Severity.ERROR,
                    "Failed to parse .ecore file: " + e.getMessage(), ecoreFilePath));
            return ValidationResult.of(issues);
        }

        for (Resource.Diagnostic error : resource.getErrors()) {
            issues.add(new ValidationIssue(ValidationIssue.Severity.ERROR,
                    error.getMessage(), locate(ecoreFilePath, error.getLine())));
        }
        for (Resource.Diagnostic warning : resource.getWarnings()) {
            issues.add(new ValidationIssue(ValidationIssue.Severity.WARNING,
                    warning.getMessage(), locate(ecoreFilePath, warning.getLine())));
        }

        if (resource.getContents().isEmpty()) {
            issues.add(new ValidationIssue(ValidationIssue.Severity.ERROR,
                    "File has no content", ecoreFilePath));
            return ValidationResult.of(issues);
        }

        for (EObject root : resource.getContents()) {
            EcoreUtil.resolveAll(root);
            findDanglingReferences(root, ecoreFilePath, issues);
            org.eclipse.emf.common.util.Diagnostic structural = Diagnostician.INSTANCE.validate(root);
            flattenDiagnostic(structural, ecoreFilePath, issues);
        }

        return ValidationResult.of(issues);
    }

    public static ValidationResult validateViaCodegen(String ecoreFilePath) {
        requireNonBlank(ecoreFilePath);

        ValidationResult reflective = validateReflectively(ecoreFilePath);
        if (!reflective.valid()) {
            return reflective;
        }

        EPackage ePackage = loadSinglePackage(ecoreFilePath);
        if (ePackage == null) {
            List<ValidationIssue> issues = new ArrayList<>(reflective.issues());
            issues.add(new ValidationIssue(ValidationIssue.Severity.ERROR,
                    "Root content is not a single EPackage; cannot generate code", ecoreFilePath));
            return ValidationResult.of(issues);
        }

        List<ValidationIssue> issues = new ArrayList<>(reflective.issues());
        File workDir = null;
        try {
            workDir = java.nio.file.Files.createTempDirectory("ecore-validate-codegen").toFile();
            GenModelBuilder.Result genResult = GenModelBuilder.generate(ePackage, workDir, ecoreFilePath);
            issues.addAll(genResult.issues());
            if (genResult.hasError()) {
                return ValidationResult.of(issues);
            }
            issues.addAll(JavaCompilerCheck.compile(genResult.sourceDirectory(), ecoreFilePath));
        } catch (Exception e) {
            issues.add(new ValidationIssue(ValidationIssue.Severity.ERROR,
                    "Deeper validation failed: " + e, ecoreFilePath));
        } finally {
            if (workDir != null) {
                deleteRecursively(workDir);
            }
        }

        return ValidationResult.of(issues);
    }

    private static EPackage loadSinglePackage(String ecoreFilePath) {
        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                .put("ecore", new EcoreResourceFactoryImpl());
        Resource resource = resourceSet.createResource(URI.createFileURI(new File(ecoreFilePath).getAbsolutePath()));
        try {
            resource.load(null);
        } catch (Exception e) {
            return null;
        }
        if (resource.getContents().isEmpty() || !(resource.getContents().get(0) instanceof EPackage)) {
            return null;
        }
        EPackage ePackage = (EPackage) resource.getContents().get(0);
        EcoreUtil.resolveAll(ePackage);
        return ePackage;
    }

    private static void findDanglingReferences(EObject root, String sourceFile, List<ValidationIssue> issues) {
        checkReferences(root, sourceFile, issues);
        Iterator<EObject> contents = root.eAllContents();
        while (contents.hasNext()) {
            checkReferences(contents.next(), sourceFile, issues);
        }
    }

    private static void checkReferences(EObject owner, String sourceFile, List<ValidationIssue> issues) {
        for (EReference reference : owner.eClass().getEAllReferences()) {
            if (!owner.eIsSet(reference)) {
                continue;
            }
            Object value = owner.eGet(reference, false);
            if (value instanceof List<?> list) {
                for (Object element : list) {
                    reportIfProxy(owner, reference, element, sourceFile, issues);
                }
            } else {
                reportIfProxy(owner, reference, value, sourceFile, issues);
            }
        }
    }

    private static void reportIfProxy(EObject owner, EReference reference, Object value, String sourceFile, List<ValidationIssue> issues) {
        if (value instanceof InternalEObject internal && internal.eIsProxy()) {
            issues.add(new ValidationIssue(ValidationIssue.Severity.ERROR,
                    "Unresolved reference " + describe(owner) + "." + reference.getName()
                            + " -> " + internal.eProxyURI(),
                    describe(owner) + "/" + reference.getName()));
        }
    }

    private static void flattenDiagnostic(org.eclipse.emf.common.util.Diagnostic diagnostic, String sourceFile, List<ValidationIssue> issues) {
        if (diagnostic.getSeverity() == org.eclipse.emf.common.util.Diagnostic.ERROR) {
            issues.add(new ValidationIssue(ValidationIssue.Severity.ERROR, diagnostic.getMessage(), sourceFile));
        } else if (diagnostic.getSeverity() == org.eclipse.emf.common.util.Diagnostic.WARNING) {
            issues.add(new ValidationIssue(ValidationIssue.Severity.WARNING, diagnostic.getMessage(), sourceFile));
        }
        for (org.eclipse.emf.common.util.Diagnostic child : diagnostic.getChildren()) {
            flattenDiagnostic(child, sourceFile, issues);
        }
    }

    private static String describe(EObject object) {
        try {
            return EcoreUtil.getURI(object).fragment();
        } catch (Exception e) {
            return object.eClass().getName();
        }
    }

    private static String locate(String sourceFile, int line) {
        return line > 0 ? sourceFile + ":" + line : sourceFile;
    }

    private static void requireNonBlank(String path) {
        if (path == null || path.isBlank()) {
            throw new IllegalArgumentException("Ecore file path cannot be null or empty");
        }
    }

    private static void deleteRecursively(File file) {
        File[] children = file.listFiles();
        if (children != null) {
            for (File child : children) {
                deleteRecursively(child);
            }
        }
        file.delete();
    }
}
