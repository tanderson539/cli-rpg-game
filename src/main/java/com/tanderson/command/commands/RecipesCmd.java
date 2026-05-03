package com.tanderson.command.commands;

import com.tanderson.GameContext;
import com.tanderson.command.RegisteredCommand;
import com.tanderson.log.LogLevel;
import com.tanderson.systems.crafting.CraftingRepo;

@RegisteredCommand(aliases = {"rs", "recipes"})
public class RecipesCmd implements Command {
    @Override
    public String execute(String[] args, GameContext context) {
        context.getLogger().info("Player ran recipes command.");
        CraftingRepo repo = new CraftingRepo();
        return repo.printCraftableItemList();
    }
}
