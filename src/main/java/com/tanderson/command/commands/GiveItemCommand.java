package com.tanderson.command.commands;

import com.tanderson.GameContext;
import com.tanderson.items.ItemFactory;
import com.tanderson.log.LogLevel;
import org.apache.commons.lang3.StringUtils;

public class GiveItemCommand implements Command {

    @Override
    public String execute(String[] args, GameContext context) {
        if(args.length < 2) return "Invalid arguments.";
        if(!StringUtils.isNumeric(args[1])) return "Invalid arguments.";

        try{
            context.getPlayer().getInventory().addItem(ItemFactory.createItem(Long.parseLong(args[1])));
        } catch (IllegalArgumentException e) {
            context.getLogger().log(e.getMessage(), LogLevel.ERROR);
            return "An Item with that ID does not exist.";
        }


        return "";
    }
}
