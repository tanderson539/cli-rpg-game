package com.tanderson.items.ingots;

import com.tanderson.items.RegisteredItem;
import com.tanderson.systems.craftingSystem.BasicRecipe;
import com.tanderson.items.CraftableItem;
import com.tanderson.items.ores.Ore_Coal;
import com.tanderson.items.ores.Ore_Silver;

@RegisteredItem(id = 54L)
public class Ingot_Silver extends CraftableItem {

    public Ingot_Silver() {
        super(54L, "Silver Ingot", true, createRecipe());
    }

    private static BasicRecipe createRecipe() {
        BasicRecipe recipe = new BasicRecipe(2);
        recipe.addIngredient(new Ore_Silver(), 2);
        recipe.addIngredient(new Ore_Coal(), 1);

        return recipe;
    }
}
