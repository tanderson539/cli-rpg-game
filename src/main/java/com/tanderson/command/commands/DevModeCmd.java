package com.tanderson.command.commands;

import com.tanderson.GameContext;
import com.tanderson.command.RegisteredSlashCommand;
import com.tanderson.log.LogLevel;

@RegisteredSlashCommand(aliases = {"dev"})
public class DevModeCmd implements Command {
    @Override
    public String execute(String[] args, GameContext context) {
        if (context.isDevMode()) {
            context.setDevMode(false);
            context.getLogger().log("Player disabled Dev Mode", LogLevel.INFO);
            return "Dev mode disabled";
        } else {
            context.setDevMode(true);
            context.getLogger().log("Player enabled Dev Mode", LogLevel.INFO);
            return "Dev mode enabled";
        }
    }
}
