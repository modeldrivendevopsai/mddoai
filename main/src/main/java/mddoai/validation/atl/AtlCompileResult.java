package main.java.mddoai.validation.atl;

import main.java.mddoai.validation.ValidationResult;

/**
 * {@link AtlValidator#validate}'s result: the usual pass/fail plus issues,
 * and where the compiled {@code .asm} bytecode was persisted on disk, when
 * compilation actually produced one. Null when nothing was generated (the
 * input file didn't exist, or the compiler failed before writing anything).
 */
public record AtlCompileResult(ValidationResult result, String generatedOutputPath) {

    public static AtlCompileResult of(ValidationResult result) {
        return new AtlCompileResult(result, null);
    }
}
