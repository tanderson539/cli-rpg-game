package com.tanderson.command.commands;

import com.tanderson.GameContext;
import com.tanderson.log.LogLevel;
import com.tanderson.systems.craftingSystem.CraftingRepo;

public class RecipesCmd implements Command {
    @Override
    public String execute(String[] args, GameContext context) {
        context.getLogger().log("Player ran recipes command.", LogLevel.INFO);
        CraftingRepo repo = new CraftingRepo();
        return repo.printCraftableItemList();
    }
}
