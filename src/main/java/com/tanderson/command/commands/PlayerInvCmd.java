package com.tanderson.command.commands;

import com.tanderson.GameContext;
import com.tanderson.command.RegisteredCommand;
import com.tanderson.log.LogLevel;

@RegisteredCommand(aliases = {"inventory", "inv", "i"})
public class PlayerInvCmd implements Command {
    @Override
    public String execute(String[] args, GameContext context) {
        context.getLogger().info("Player ran inv command.");
        return context.getPlayer().getInventory().printInventory();
    }
}
