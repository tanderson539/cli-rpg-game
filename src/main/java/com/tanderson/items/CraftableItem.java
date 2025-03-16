package com.tanderson.items;

import com.tanderson.systems.craftingSystem.CraftingIngredient;
import com.tanderson.systems.craftingSystem.BasicRecipe;

public class CraftableItem extends Item implements Craftable{

    private BasicRecipe recipe;

    public CraftableItem(String name, boolean isStackable, BasicRecipe recipe){
        super(name, isStackable);
        this.recipe = recipe;
    }


    public BasicRecipe getCraftingRecipe() {
        return recipe;
    }

    public void setCraftingRecipe(BasicRecipe recipe) {
        this.recipe = recipe;
    }

    public String recipeToString(){
        String out = this.getName() + ": ";

        for(CraftingIngredient ingredient : recipe.getRecipe()){
            out += ingredient.getAmountRequired() + "x " + ingredient.getItem().getName() + " ";
        }
        return out;
    }

}
