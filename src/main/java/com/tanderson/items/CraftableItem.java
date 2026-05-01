package com.tanderson.items;

import com.tanderson.systems.crafting.CraftingIngredient;
import com.tanderson.systems.crafting.BasicRecipe;

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
        StringBuilder out = new StringBuilder(this.getName() + ": ");

        for(CraftingIngredient ingredient : recipe.getRecipe()){
            out.append(ingredient.getAmountRequired()).append("x ").append(ingredient.getItem().getName()).append(" ");
        }
        return out.toString();
    }

}
