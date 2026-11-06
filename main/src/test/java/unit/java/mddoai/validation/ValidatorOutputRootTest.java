package test.java.unit.java.mddoai.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

import main.java.mddoai.validation.ValidatorOutputRoot;

class ValidatorOutputRootTest {

    @Test
    void returnsTheFolderTheEnvironmentNames() {
        assertEquals("/data/output", ValidatorOutputRoot.from(Map.of(ValidatorOutputRoot.ENV_VAR, "/data/output")));
    }

    @Test
    void trimsSpacesAroundTheFolder() {
        assertEquals("/data/output", ValidatorOutputRoot.from(Map.of(ValidatorOutputRoot.ENV_VAR, "  /data/output  ")));
    }

    @Test
    void failsNamingTheVariableWhenItIsNotSet() {
        IllegalStateException error =
                assertThrows(IllegalStateException.class, () -> ValidatorOutputRoot.from(Map.of()));

        assertTrue(error.getMessage().contains(ValidatorOutputRoot.ENV_VAR));
    }

    @Test
    void doesNotAcceptABlankValueEither() {
        assertThrows(
                IllegalStateException.class, () -> ValidatorOutputRoot.from(Map.of(ValidatorOutputRoot.ENV_VAR, "  ")));
    }
}
