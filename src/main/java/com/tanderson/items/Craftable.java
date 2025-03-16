package com.tanderson.items;

import com.tanderson.systems.craftingSystem.BasicRecipe;

public interface Craftable {
    BasicRecipe getCraftingRecipe();
    void setCraftingRecipe(BasicRecipe recipe);

    String recipeToString();
}
