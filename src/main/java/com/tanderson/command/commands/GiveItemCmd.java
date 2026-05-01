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
        if(args.length < 2) return "Invalid arguments.";
        if(!StringUtils.isNumeric(args[1])) return "Invalid arguments.";

        int amount = 1;

        if(args.length >= 3 && StringUtils.isNumeric(args[2])) amount = Integer.parseInt(args[2]);

        try{
            context.getPlayer().getInventory().addItem(ItemFactory.createItem(Long.parseLong(args[1])), amount);
        } catch (IllegalArgumentException e) {
            context.getLogger().log(e.getMessage(), LogLevel.ERROR);
            return "An Item with that ID does not exist.";
        }

        return "";
    }
}
