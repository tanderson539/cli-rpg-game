package com.tanderson.command.commands;

import com.tanderson.GameContext;
import com.tanderson.command.RegisteredSlashCommand;
import com.tanderson.items.ItemFactory;
import com.tanderson.log.LogLevel;
import org.apache.commons.lang3.StringUtils;

@RegisteredSlashCommand(aliases = {"give"})
public class GiveItemCmd implements Command {

    @Override
    public String execute(String[] args, GameContext context) {
        if(args.length != 3) return "Invalid arguments. Too few or too many.";
        if(!StringUtils.isNumeric(args[2])) return "Invalid arguments. Amount must be an integer.";

        String item_id = args[1];

        int amount = 0;

        try {
            amount = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            return "Invalid arguments. Amount must be an integer.";
        }

        try{
            context.getPlayer().getInventory().addItem(ItemFactory.createItem(item_id), amount);
        } catch (IllegalArgumentException e) {
            context.getLogger().error(e.getMessage());
            return "An Item with that ID does not exist.";
        }

        return "";
    }
}
