package main.java.mddoai.validation.acceleo;

import main.java.mddoai.validation.ValidationResult;

/**
 * {@link AcceleoValidator#validate}'s result: the usual pass/fail plus
 * issues, and where the compiled {@code .emtl} module (and anything else
 * Acceleo's compiler wrote alongside it) was persisted on disk, when
 * compilation actually produced something. Null when nothing was generated.
 */
public record AcceleoCompileResult(ValidationResult result, String generatedOutputPath) {

    public static AcceleoCompileResult of(ValidationResult result) {
        return new AcceleoCompileResult(result, null);
    }
}
