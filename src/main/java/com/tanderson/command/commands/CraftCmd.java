package com.tanderson.command.commands;

import com.tanderson.GameContext;
import com.tanderson.log.LogLevel;
import com.tanderson.systems.craftingSystem.CraftingRepo;
import com.tanderson.items.CraftableItem;


public class CraftCmd implements Command {
    @Override
    public String execute(String[] args, GameContext context) {
        CraftingRepo repo = new CraftingRepo();

        CraftableItem itemToCraft = repo.getCraftableItem(Integer.parseInt(args[1]));

        if(itemToCraft == null){
            context.getLogger().log("Attempted to craft an item, but it was not found.", LogLevel.WARN);
            return "Recipe not found";
        }

        context.getLogger().log("Attempting to craft item: " + itemToCraft.getName(), LogLevel.INFO);
        return context.getCraftingManager().craftItem(itemToCraft);
    }
}
