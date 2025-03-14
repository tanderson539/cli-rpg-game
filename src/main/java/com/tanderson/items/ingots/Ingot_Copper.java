package com.tanderson.items.ingots;

import com.tanderson.craftingSystem.BasicRecipe;
import com.tanderson.items.CraftableItem;
import com.tanderson.items.ores.Ore_Coal;
import com.tanderson.items.ores.Ore_Copper;
import com.tanderson.rds.ItemTableEntry;

public class Ingot_Copper extends CraftableItem implements ItemTableEntry {
    public Ingot_Copper() {
        super("Copper Ingot", true, createRecipe());
    }

    private static BasicRecipe createRecipe() {
        BasicRecipe recipe = new BasicRecipe(2);
        recipe.addIngredient(new Ore_Copper(), 2);
        recipe.addIngredient(new Ore_Coal(), 1);

        return recipe;
    }
}
