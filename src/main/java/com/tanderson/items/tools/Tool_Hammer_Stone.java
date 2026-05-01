package com.tanderson.items.tools;

import com.tanderson.items.RegisteredItem;
import com.tanderson.systems.crafting.BasicRecipe;
import com.tanderson.items.CraftableItem;
import com.tanderson.items.ores.Ore_Stone;
import com.tanderson.items.wood.TreeBranch;

@RegisteredItem(id = 100L)
public class Tool_Hammer_Stone extends CraftableItem {

    public Tool_Hammer_Stone() {
        super(100L, "Stone Hammer", false, createRecipe());
    }

    private static BasicRecipe createRecipe() {
        BasicRecipe recipe = new BasicRecipe(1);
        recipe.addIngredient(new Ore_Stone(), 1);
        recipe.addIngredient(new TreeBranch(), 2);

        return recipe;
    }
}
