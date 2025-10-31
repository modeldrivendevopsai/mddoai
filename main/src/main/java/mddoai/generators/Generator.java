package main.java.mddoai.generators;

public interface Generator<T> {
    void generate(T model, String outputDir) throws Exception;
}