package com.tanderson.command.commands;

import com.tanderson.GameContext;
import com.tanderson.craftingSystem.CraftingRepo;
import com.tanderson.items.CraftableItem;

public class CraftCmd implements Command {
    @Override
    public String execute(String[] args, GameContext context) {
        CraftingRepo repo = new CraftingRepo();

        CraftableItem itemToCraft = repo.getCraftableItem(Integer.parseInt(args[1]));

        if(itemToCraft == null){
            return "Recipe not found";
        }

        return context.getCraftingManager().craftItem(itemToCraft);
    }
}
