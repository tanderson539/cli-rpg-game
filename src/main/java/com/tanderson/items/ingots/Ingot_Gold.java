package com.tanderson.items.ingots;

import com.tanderson.craftingSystem.CraftingRecipe;
import com.tanderson.items.CraftableItem;
import com.tanderson.items.ores.Ore_Coal;
import com.tanderson.items.ores.Ore_Gold;

public class Ingot_Gold extends CraftableItem {

    public Ingot_Gold() {
        super("Gold Ingot", true, createRecipe());
    }

    private static CraftingRecipe createRecipe() {
        CraftingRecipe recipe = new CraftingRecipe(2);
        recipe.addIngredient(new Ore_Gold(), 2);
        recipe.addIngredient(new Ore_Coal(), 1);

        return recipe;
    }
}
