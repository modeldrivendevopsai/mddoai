package main.java.mddoai.validation;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Isolates the javax.tools invocation + classpath assembly for compiling
 * EMF-codegen-generated Java. Classpath is assembled from the already-loaded
 * EMF classes' own code-source locations, unioned with java.class.path as a
 * supplement — more robust than trusting java.class.path alone, since the
 * packaged app's manifest Class-Path: mechanism may not surface every jar
 * into that system property depending on how the JVM was launched.
 */
final class JavaCompilerCheck {

    private JavaCompilerCheck() {
    }

    static List<ValidationIssue> compile(File sourceDirectory, String sourceFileForIssues) {
        List<ValidationIssue> issues = new ArrayList<>();
        List<File> javaFiles = new ArrayList<>();
        collectJavaFiles(sourceDirectory, javaFiles);

        if (javaFiles.isEmpty()) {
            issues.add(new ValidationIssue(ValidationIssue.Severity.ERROR,
                    "Code generation produced no .java files to compile", sourceFileForIssues));
            return issues;
        }

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            issues.add(new ValidationIssue(ValidationIssue.Severity.ERROR,
                    "No system Java compiler available (running on a JRE, not a JDK)", sourceFileForIssues));
            return issues;
        }

        DiagnosticCollector<JavaFileObject> diagnosticCollector = new DiagnosticCollector<>();
        File classOutDir = new File(sourceDirectory.getParentFile(), "classes-out");
        classOutDir.mkdirs();

        try (StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnosticCollector, null, null)) {
            Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(javaFiles);
            List<String> options = List.of("-cp", assembleClasspath(), "-d", classOutDir.getAbsolutePath());
            JavaCompiler.CompilationTask task = compiler.getTask(
                    null, fileManager, diagnosticCollector, options, null, compilationUnits);
            task.call();
        } catch (Exception e) {
            issues.add(new ValidationIssue(ValidationIssue.Severity.ERROR,
                    "Failed to invoke Java compiler: " + e, sourceFileForIssues));
            return issues;
        }

        for (Diagnostic<? extends JavaFileObject> diagnostic : diagnosticCollector.getDiagnostics()) {
            if (diagnostic.getKind() == Diagnostic.Kind.ERROR) {
                issues.add(new ValidationIssue(ValidationIssue.Severity.ERROR, diagnostic.getMessage(null), locate(diagnostic)));
            } else if (diagnostic.getKind() == Diagnostic.Kind.WARNING
                    || diagnostic.getKind() == Diagnostic.Kind.MANDATORY_WARNING) {
                issues.add(new ValidationIssue(ValidationIssue.Severity.WARNING, diagnostic.getMessage(null), locate(diagnostic)));
            }
        }
        return issues;
    }

    private static String assembleClasspath() {
        Set<String> entries = new LinkedHashSet<>();
        for (Class<?> marker : new Class<?>[]{
                org.eclipse.emf.ecore.EObject.class,
                org.eclipse.emf.common.util.URI.class,
                org.eclipse.emf.ecore.impl.MinimalEObjectImpl.class,
                org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl.class
        }) {
            addCodeSourceLocation(marker, entries);
        }
        String javaClassPath = System.getProperty("java.class.path");
        if (javaClassPath != null) {
            Collections.addAll(entries, javaClassPath.split(File.pathSeparator));
        }
        return String.join(File.pathSeparator, entries);
    }

    private static void addCodeSourceLocation(Class<?> marker, Set<String> entries) {
        try {
            var location = marker.getProtectionDomain().getCodeSource().getLocation();
            entries.add(new File(location.toURI()).getAbsolutePath());
        } catch (Exception ignored) {
            // Best-effort: java.class.path is a supplementary fallback for exactly this case.
        }
    }

    private static void collectJavaFiles(File dir, List<File> out) {
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }
        for (File f : files) {
            if (f.isDirectory()) {
                collectJavaFiles(f, out);
            } else if (f.getName().endsWith(".java")) {
                out.add(f);
            }
        }
    }

    private static String locate(Diagnostic<? extends JavaFileObject> diagnostic) {
        JavaFileObject source = diagnostic.getSource();
        String file = source != null ? source.getName() : "unknown";
        return file + ":" + diagnostic.getLineNumber();
    }
}
