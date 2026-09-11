package main.java.mddoai.validation.ecore;

import main.java.mddoai.validation.ValidationResult;

/**
 * {@link EcoreValidator#validateViaCodegen}'s result: the usual pass/fail plus
 * issues, and where the generated {@code .genmodel}/{@code src-gen}/{@code
 * classes-out} output was persisted on disk, when there's real output worth
 * keeping. Null when nothing was generated (validation failed before or during
 * genmodel/codegen setup) or when wrapping a {@link
 * EcoreValidator#validateReflectively} result, which never generates anything.
 */
public record EcoreCodegenResult(ValidationResult result, String generatedOutputPath) {

    public static EcoreCodegenResult of(ValidationResult result) {
        return new EcoreCodegenResult(result, null);
    }
}
