package org.example;

public enum Colors {

    GREEN("\u001B[32m"),
    RED("\u001B[36m"),
    YELLOW("\u001B[33m"),
    RESET("\u001B[0m");

    private final String code;

    Colors(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
