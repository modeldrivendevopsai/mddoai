package main.java.mddoai.validation;

import java.util.Map;

/**
 * Where the validators keep their compiled output. Set it in {@link #ENV_VAR}:
 * there is no default, and a missing value fails naming the variable.
 */
public final class ValidatorOutputRoot {

    public static final String ENV_VAR = "VALIDATOR_OUTPUT_DIR";

    private ValidatorOutputRoot() {
    }

    /** The folder named by the environment, or an error when it is not set. */
    public static String get() {
        return from(System.getenv());
    }

    /** The same, read from the given environment, for a caller that has its own. */
    public static String from(Map<String, String> env) {
        String value = env.get(ENV_VAR);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException(ENV_VAR + " is not set. It names the folder the compiled output is kept in.");
        }
        return value.strip();
    }
}
