package com.tanderson.log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConsoleLogger implements Logger {

    @Override
    public void log(String message, LogLevel level) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = new Date();
        String dateString = dateFormat.format(date);

        System.out.println(dateString + " [" + level.toString() + "] " + message);
    }
}
