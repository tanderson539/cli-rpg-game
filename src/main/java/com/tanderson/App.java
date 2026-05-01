package com.tanderson;

import com.tanderson.log.ConsoleLogger;

public class App {
    public static void main(String[] args) {
        ConsoleLogger logger = new ConsoleLogger();
        Engine engine = new Engine(logger);
        engine.gameInit();
    }
}
