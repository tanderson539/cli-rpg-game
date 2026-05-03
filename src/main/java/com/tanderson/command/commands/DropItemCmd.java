package com.tanderson.command.commands;

import com.tanderson.GameContext;
import com.tanderson.command.RegisteredCommand;
import com.tanderson.log.LogLevel;

@RegisteredCommand(aliases = {"drop", "d"})
public class DropItemCmd implements Command {
    @Override
    public String execute(String[] args, GameContext context) {

        if(args.length == 1 || args.length == 2){
            context.getLogger().warn("Command " + args[0] + " was given with too few arguments.");
            return "too few arguments, please type an inventory index and an amount";
        }else {
            try{
                int idx = Integer.parseInt(args[1]) - 1;
                int amount = Integer.parseInt(args[2]);

                context.getPlayer().getInventory().removeItem(idx, amount);
                context.getLogger().info("Player dropped an item successfully.");
                return "Dropped item successfully!";
            } catch (NumberFormatException e) {
                context.getLogger().error("Player attempted the drop command but did not provide integer arguments.");
                context.getLogger().error(e.getMessage());
                return "Input must Integers.";
            }
        }
    }
}
