package test.java.integration.java.mddoai.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import main.java.mddoai.utils.JavaUtils;

public class JavaUtilsTest {
    
    @TempDir
    Path tempDir;
    
    @BeforeEach
    void setUp() throws IOException {
        Path intermediatePath = Paths.get("./intermediate");
        if (!Files.exists(intermediatePath)) {
            Files.createDirectories(intermediatePath);
        }
        
        Files.createFile(intermediatePath.resolve("test1.txt"));
        Files.createFile(intermediatePath.resolve("test2.txt"));
    }
    
    @AfterEach
    void tearDown() throws IOException {
        Path intermediatePath = Paths.get("./intermediate");
        if (Files.exists(intermediatePath)) {
            Files.list(intermediatePath).forEach(path -> {
                try {
                    Files.deleteIfExists(path);
                } catch (IOException e) {
                }
            });
            Files.deleteIfExists(intermediatePath);
        }
    }
    
    @Test
    void testCleanUp() throws IOException {
        Path intermediatePath = Paths.get("./intermediate");
        
        assertTrue(Files.exists(intermediatePath.resolve("test1.txt")));
        assertTrue(Files.exists(intermediatePath.resolve("test2.txt")));
        
        JavaUtils.cleanUp();
        
        assertTrue(!Files.exists(intermediatePath.resolve("test1.txt")));
        assertTrue(!Files.exists(intermediatePath.resolve("test2.txt")));
        
        assertTrue(Files.exists(intermediatePath));
    }
    
    @Test
    void testCleanUpWithNonExistentDirectory() {
        Path intermediatePath = Paths.get("./intermediate");
        try {
            Files.list(intermediatePath).forEach(path -> {
                try {
                    Files.deleteIfExists(path);
                } catch (IOException e) {
                }
            });
            Files.deleteIfExists(intermediatePath);
        } catch (IOException e) {
        }
        
        JavaUtils.cleanUp();
    }
} 