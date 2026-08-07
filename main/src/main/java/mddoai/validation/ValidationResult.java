package main.java.mddoai.validation;

import java.util.List;

/**
 * Pass/fail plus the list of diagnostics that led to it. {@code valid} is true
 * iff no issue has ERROR severity — warnings alone don't fail validation.
 */
public record ValidationResult(boolean valid, List<ValidationIssue> issues) {

    public ValidationResult {
        if (issues == null) {
            throw new IllegalArgumentException("Issues cannot be null");
        }
        issues = List.copyOf(issues);
    }

    public static ValidationResult ok() {
        return new ValidationResult(true, List.of());
    }

    public static ValidationResult of(List<ValidationIssue> issues) {
        boolean hasError = issues.stream()
                .anyMatch(issue -> issue.severity() == ValidationIssue.Severity.ERROR);
        return new ValidationResult(!hasError, issues);
    }
}
