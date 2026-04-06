package main.java;

public class ExitException extends RuntimeException {
    private final int code;

    public ExitException(int code) {
        super("Exit with code " + code);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

