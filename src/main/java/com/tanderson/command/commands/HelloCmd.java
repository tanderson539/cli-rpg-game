package com.tanderson.command.commands;

import com.tanderson.GameContext;
import com.tanderson.log.LogLevel;

public class HelloCmd implements Command {
    @Override
    public String execute(String[] args, GameContext context) {
        context.getLogger().log("Hello command run.", LogLevel.INFO);
        return "Hi!";
    }
}
