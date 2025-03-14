package com.tanderson.craftingSystem;

import com.tanderson.items.Item;

import java.util.ArrayList;

public interface CraftingRecipe {
    ArrayList<CraftingIngredient> getRecipe();
    void setRecipe(ArrayList<CraftingIngredient> ingredients);

    void addIngredient(Item item, int amount);
    void addIngredient(Item item, int amount, boolean isConsumed);
    void addIngredient(CraftingIngredient ingredient);

    void removeIngredient(Item item);
    void removeIngredient(CraftingIngredient ingredient);
}
