package com.tanderson.command.commands;

import com.tanderson.GameContext;
import com.tanderson.log.LogLevel;

public class ExitCmd implements Command {

    @Override
    public String execute(String[] args, GameContext context) {
        context.getLogger().log("Exiting game due to player command...", LogLevel.INFO);
        System.exit(0);
        return "exiting...";
    }
}
