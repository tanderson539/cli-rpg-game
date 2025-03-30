package com.tanderson.command.commands;

import com.tanderson.GameContext;
import com.tanderson.log.LogLevel;

public class PlayerInvCmd implements Command {
    @Override
    public String execute(String[] args, GameContext context) {
        context.getLogger().log("Player ran inv command.", LogLevel.INFO);
        return context.getPlayer().getInventory().printInventory();
    }
}
