package com.tanderson.items.ingots;

import com.tanderson.craftingSystem.BasicRecipe;
import com.tanderson.items.CraftableItem;
import com.tanderson.items.ores.Ore_Coal;
import com.tanderson.items.ores.Ore_Silver;

public class Ingot_Silver extends CraftableItem {

    public Ingot_Silver() {
        super("Silver Ingot", true, createRecipe());
    }

    private static BasicRecipe createRecipe() {
        BasicRecipe recipe = new BasicRecipe(2);
        recipe.addIngredient(new Ore_Silver(), 2);
        recipe.addIngredient(new Ore_Coal(), 1);

        return recipe;
    }
}
