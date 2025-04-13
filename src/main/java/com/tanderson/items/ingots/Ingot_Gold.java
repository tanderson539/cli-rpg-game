package com.tanderson.items.ingots;

import com.tanderson.items.RegisteredItem;
import com.tanderson.systems.craftingSystem.BasicRecipe;
import com.tanderson.items.CraftableItem;
import com.tanderson.items.ores.Ore_Coal;
import com.tanderson.items.ores.Ore_Gold;

@RegisteredItem(id = 52L)
public class Ingot_Gold extends CraftableItem {

    public Ingot_Gold() {
        super(52L, "Gold Ingot", true, createRecipe());
    }

    private static BasicRecipe createRecipe() {
        BasicRecipe recipe = new BasicRecipe(2);
        recipe.addIngredient(new Ore_Gold(), 2);
        recipe.addIngredient(new Ore_Coal(), 1);

        return recipe;
    }
}
