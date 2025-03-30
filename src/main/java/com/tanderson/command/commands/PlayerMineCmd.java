package com.tanderson.command.commands;

import com.tanderson.GameContext;
import com.tanderson.log.LogLevel;

public class PlayerMineCmd implements Command {
    @Override
    public String execute(String[] args, GameContext context) {
        context.getPlayer().mine();
        context.getLogger().log("Ran mine command.", LogLevel.INFO);

        return "";
    }
}
