package com.tanderson.craftingSystem;

import com.tanderson.items.Item;

import java.util.ArrayList;

public class CraftingRecipe {
    private ArrayList<CraftingIngredient> ingredients;
    private int amountToCraft;

    public CraftingRecipe(int amountToCraft, ArrayList<CraftingIngredient> ingredients) {
        this.ingredients = ingredients;
        this.amountToCraft = amountToCraft;
    }

    public CraftingRecipe(int amountToCraft){
        this.ingredients = new ArrayList<>();
        this.amountToCraft = amountToCraft;
    }

    public void addIngredient(Item item, int amount){
        this.ingredients.add(new CraftingIngredient(item, amount, true));
    }

    public void addIngredient(Item item, int amount, boolean isConsumed){
        this.ingredients.add(new CraftingIngredient(item, amount, isConsumed));
    }

    public void addIngredient(CraftingIngredient ingredient){
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(Item item){
        for(int i = 0; i < this.ingredients.size(); i++){
            if(this.ingredients.get(i).getItem().equals(item)){
                this.ingredients.remove(i);
            }
        }
    }

    public ArrayList<CraftingIngredient> getRecipe() {
        return ingredients;
    }

    public void setRecipe(ArrayList<CraftingIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    public int getAmountToCraft() {
        return amountToCraft;
    }

    public void setAmountToCraft(int amountToCraft) {
        this.amountToCraft = amountToCraft;
    }
}
