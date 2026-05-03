package com.tanderson.command.commands;

import com.tanderson.GameContext;
import com.tanderson.command.RegisteredCommand;
import com.tanderson.log.LogLevel;

@RegisteredCommand(aliases = {"hello", "hi", "hey", "sup"})
public class HelloCmd implements Command {
    @Override
    public String execute(String[] args, GameContext context) {
        context.getLogger().info("Hello command run.");
        return "Hi!";
    }
}
