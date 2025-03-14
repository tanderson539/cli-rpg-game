package com.tanderson.command.commands;

import com.tanderson.GameContext;

public class DevModeCmd implements Command {
    @Override
    public String execute(String[] args, GameContext context) {
        if (context.isDevMode()) {
            context.setDevMode(false);
            return "Dev mode disabled";
        } else {
            context.setDevMode(true);
            return "Dev mode enabled";
        }
    }
}
