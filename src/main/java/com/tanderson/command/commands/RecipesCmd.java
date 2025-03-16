package com.tanderson.command.commands;

import com.tanderson.GameContext;
import com.tanderson.systems.craftingSystem.CraftingRepo;

public class RecipesCmd implements Command {
    @Override
    public String execute(String[] args, GameContext context) {
        CraftingRepo repo = new CraftingRepo();
        return repo.printCraftableItemList();
    }
}
