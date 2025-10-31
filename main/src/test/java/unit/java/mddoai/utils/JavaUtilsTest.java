package test.java.unit.java.mddoai.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import main.java.mddoai.utils.JavaUtils;

public class JavaUtilsTest {
    
    @Test
    void testGetRandomName() {
        String name1 = JavaUtils.getRandomName();
        String name2 = JavaUtils.getRandomName();
        
        assertNotNull(name1);
        assertNotNull(name2);
        assertEquals(10, name1.length());
        assertEquals(10, name2.length());
        
        assertTrue(!name1.equals(name2));
        
        for (char c : name1.toCharArray()) {
            assertTrue((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'));
        }
    }
    
    @Test
    void testRandomNameUniqueness() {
        int count = 1000;
        Set<String> names = new HashSet<>();
        
        for (int i = 0; i < count; i++) {
            names.add(JavaUtils.getRandomName());
        }
        
        assertEquals(count, names.size());
    }
    
} 