package test.java.unit.java.mddoai;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import main.java.ExitException;

public class ExitExceptionTest {

    @Test
    void testConstructorStoresCode() {
        ExitException e = new ExitException(1);
        assertEquals(1, e.getCode());
    }

    @Test
    void testConstructorWithZeroCode() {
        ExitException e = new ExitException(0);
        assertEquals(0, e.getCode());
    }

    @Test
    void testConstructorWithNegativeCode() {
        ExitException e = new ExitException(-1);
        assertEquals(-1, e.getCode());
    }

    @Test
    void testIsRuntimeException() {
        ExitException e = new ExitException(1);
        assertInstanceOf(RuntimeException.class, e);
    }

    @Test
    void testExceptionMessageContainsCode() {
        ExitException e = new ExitException(42);
        assertTrue(e.getMessage().contains("42"));
    }

    @Test
    void testMultipleInstancesHaveIndependentCodes() {
        ExitException e1 = new ExitException(1);
        ExitException e2 = new ExitException(99);
        assertEquals(1, e1.getCode());
        assertEquals(99, e2.getCode());
    }
}
