package com.tanderson.command.commands;

import com.tanderson.GameContext;

public class HelpCmd implements Command {

    @Override
    public String execute(String[] args, GameContext context) {
        String out = "Available commands:\n";

        out += "help - shows this help message\n";
        out += "hello - prints 'Hi!'\n";
        out += "mine / m - mines some ore!\n";
        out += "inv / i - shows inventory\n";
        out += "level / lvl - shows current Mining Level\n";
        out += "rand - shows random number\n";
        out += "loot - runs the ore table for debug testing\n";
        out += "drop *inventory index* *amount to drop* - drops an item in your inventory in the amount specified\n";
        out += "recipes / rs - shows available crafting recipes\n";
        out += "craft *recipe #* - crafts an item, presuming you have the resources\n";
        out += "exit / x / quit - exits the game";

        return out;
    }
}
