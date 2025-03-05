package com.tanderson.craftingSystem;

import com.tanderson.items.CraftableItem;
import com.tanderson.items.ItemRecord;
import com.tanderson.player.Inventory;

import java.util.ArrayList;

public class CraftingManager {

    private final Inventory inventory;

    public CraftingManager(Inventory inventory) {
        this.inventory = inventory;
    }

    public boolean craftItem(CraftableItem item){
        if(!this.canCraft(item.getCraftingRecipe())){
            System.out.println("returning from craftItem because the cancraft method came back false");
            return false;
        }

        if(!inventory.isInventoryFull()){
            this.consumeIngredients(item.getCraftingRecipe().getRecipe());
            int amountToCraft = item.getCraftingRecipe().getAmountToCraft();
            inventory.addItem(new ItemRecord(item, amountToCraft));
        }else{
            System.out.println("Inventory full, cannot craft item.");
            return false;
        }

        return true;
    }

    public boolean canCraft(CraftingRecipe recipe){
        for (CraftingIngredient ingredient : recipe.getRecipe()) {
            int requiredAmount = ingredient.getAmountRequired();
            if (inventory.getAmountOfItem(ingredient.getItem()) < requiredAmount) {
                return false;
            }
        }
        return true;
    }

    private void consumeIngredients(ArrayList<CraftingIngredient> recipe){
        for (CraftingIngredient ingredient : recipe) {
            if(ingredient.isConsumed()){
                inventory.removeItem(ingredient.getItem(), ingredient.getAmountRequired());
            }
        }
    }
}
