package main.java.mddoai.validation;

/**
 * A single problem (or warning) found while validating a model or metamodel.
 * {@code source} carries a locator (e.g. an Ecore fragment path like
 * "pimMM.ecore#//PipelineBlock/agent", a javac "file:line", or an ATL
 * "file#line:col") so the message is specific enough to act on.
 */
public record ValidationIssue(Severity severity, String message, String source) {

    public ValidationIssue {
        if (severity == null) {
            throw new IllegalArgumentException("Severity cannot be null");
        }
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("Message cannot be null or blank");
        }
    }

    public enum Severity {
        WARNING,
        ERROR
    }
}
