package com.tanderson.command.commands;

import com.tanderson.GameContext;

public class PlayerInvCmd implements Command {
    @Override
    public String execute(String[] args, GameContext context) {
        return context.getPlayer().getInventory().printInventory();
    }
}
