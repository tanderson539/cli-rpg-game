package com.tanderson.items.ingots;

import com.tanderson.craftingSystem.BasicRecipe;
import com.tanderson.items.CraftableItem;
import com.tanderson.items.ores.Ore_Coal;
import com.tanderson.items.ores.Ore_Gold;

public class Ingot_Gold extends CraftableItem {

    public Ingot_Gold() {
        super("Gold Ingot", true, createRecipe());
    }

    private static BasicRecipe createRecipe() {
        BasicRecipe recipe = new BasicRecipe(2);
        recipe.addIngredient(new Ore_Gold(), 2);
        recipe.addIngredient(new Ore_Coal(), 1);

        return recipe;
    }
}
