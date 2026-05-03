package com.tanderson.command.commands;

import com.tanderson.GameContext;
import com.tanderson.command.RegisteredCommand;
import com.tanderson.log.LogLevel;

@RegisteredCommand(aliases = {"exit", "quit", "x"})
public class ExitCmd implements Command {

    @Override
    public String execute(String[] args, GameContext context) {
        context.getLogger().info("Exiting game due to player command...");
        System.exit(0);
        return "exiting...";
    }
}
