package com.tanderson.log;

public interface Logger {
    void error(String message);
    void info(String message);
    void warn(String message);
    void debug(String message);
    void trace(String message);
}
