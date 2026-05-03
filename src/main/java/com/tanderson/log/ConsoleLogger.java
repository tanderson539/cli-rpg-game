package com.tanderson.log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConsoleLogger implements Logger {

    private LogLevel displayLevel;

    public ConsoleLogger(LogLevel displayLevel) {
        this.displayLevel = displayLevel;
    }

    private void log(String message, LogLevel level) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = new Date();
        String dateString = dateFormat.format(date);

        System.out.println(dateString + " [" + level.toString() + "] " + message);
    }

    @Override
    public void error(String message) {
        this.log(message, LogLevel.ERROR);
    }

    @Override
    public void info(String message) {
        this.log(message, LogLevel.INFO);
    }

    @Override
    public void warn(String message) {
        this.log(message, LogLevel.WARN);
    }

    @Override
    public void debug(String message) {
        this.log(message, LogLevel.DEBUG);
    }

    @Override
    public void trace(String message) {
        this.log(message, LogLevel.TRACE);
    }
}
