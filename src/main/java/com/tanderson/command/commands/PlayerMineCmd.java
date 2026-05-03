package com.tanderson.command.commands;

import com.tanderson.GameContext;
import com.tanderson.command.RegisteredCommand;
import com.tanderson.log.LogLevel;

@RegisteredCommand(aliases = {"mine", "m"})
public class PlayerMineCmd implements Command {
    @Override
    public String execute(String[] args, GameContext context) {
        context.getPlayer().mine();
        context.getLogger().info("Ran mine command.");

        return "";
    }
}
