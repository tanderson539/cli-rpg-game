package com.tanderson.items;

import com.tanderson.craftingSystem.CraftingRecipe;

public class CraftableItem extends Item{

    private CraftingRecipe recipe;

    public CraftableItem(String name, boolean isStackable, CraftingRecipe recipe){
        super(name, isStackable);
        this.recipe = recipe;
    }


    public CraftingRecipe getCraftingRecipe() {
        return recipe;
    }

    public void setCraftingRecipe(CraftingRecipe recipe) {
        this.recipe = recipe;
    }

    public String recipeToString(){
        String out = this.getName() + ": ";

        for(Item item : recipe.getRecipe().keySet()){
            out += recipe.getRecipe().get(item) + "x " + item.getName() + " ";
        }
        return out;
    }

}
