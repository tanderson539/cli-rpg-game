package com.tanderson.command.commands;

import com.tanderson.GameContext;
import com.tanderson.log.LogLevel;

public class DropItemCmd implements Command {
    @Override
    public String execute(String[] args, GameContext context) {
        String out = "";

        if(args.length == 1 || args.length == 2){
            context.getLogger().log("Command " + args[0] + " was given with too few arguments.", LogLevel.WARN);
            return "too few arguments, please type an inventory index and an amount";
        }else {
            try{
                int idx = Integer.parseInt(args[1]) - 1;
                int amount = Integer.parseInt(args[2]);

                context.getPlayer().getInventory().removeItem(idx, amount);
                out += "Dropped item successfully!";
                context.getLogger().log("Player dropped an item successfully.", LogLevel.INFO);
            } catch (NumberFormatException e) {
                context.getLogger().log("Player attempted the drop command but did not provide integer arguments.", LogLevel.ERROR);
                return "Input must Integers.";
            }
        }
        return out;
    }
}
