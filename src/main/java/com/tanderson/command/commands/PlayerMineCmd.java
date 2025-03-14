package com.tanderson.command.commands;

import com.tanderson.GameContext;

public class PlayerMineCmd implements Command {
    @Override
    public String execute(String[] args, GameContext context) {
        context.getPlayer().mine();

        return "";
    }
}
