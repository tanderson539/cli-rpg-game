package com.tanderson.items;

import com.tanderson.systems.crafting.CraftingIngredient;
import com.tanderson.systems.crafting.BasicRecipe;

public class CraftableItem extends Item implements Craftable{

    private BasicRecipe recipe;

    public CraftableItem(Long id, String name, boolean isStackable, BasicRecipe recipe){
        super(id, name, isStackable);
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
