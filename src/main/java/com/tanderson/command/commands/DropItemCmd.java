package com.tanderson.command.commands;

import com.tanderson.GameContext;

public class DropItemCmd implements Command {
    @Override
    public String execute(String[] args, GameContext context) {
        String out = "";

        if(args.length == 1 || args.length == 2){
            return "too few arguments, please type an inventory index and an amount";
        }else {
            try{
                int idx = Integer.parseInt(args[1]) - 1;
                int amount = Integer.parseInt(args[2]);

                context.getPlayer().getInventory().removeItem(idx, amount);
                out += "Dropped item successfully!";
            } catch (NumberFormatException e) {
                return "Input must Integers.";
            }
        }
        return out;
    }
}
