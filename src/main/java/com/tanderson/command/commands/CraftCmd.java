package com.tanderson.command.commands;

import com.tanderson.GameContext;
import com.tanderson.command.RegisteredCommand;
import com.tanderson.log.LogLevel;
import com.tanderson.systems.crafting.CraftingRepo;
import com.tanderson.items.CraftableItem;

@RegisteredCommand(aliases = {"craft", "c"})
public class CraftCmd implements Command {
    @Override
    public String execute(String[] args, GameContext context) {
        CraftingRepo repo = new CraftingRepo();

        CraftableItem itemToCraft = repo.getCraftableItem(Integer.parseInt(args[1]));

        if(itemToCraft == null){
            context.getLogger().warn("Attempted to craft an item, but it was not found.");
            return "Recipe not found";
        }

        context.getLogger().info("Attempting to craft item: " + itemToCraft.getName());
        return context.getCraftingManager().craftItem(itemToCraft);
    }
}
