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

    public String craftItem(CraftableItem item){
        if(!this.canCraft(item.getCraftingRecipe())){
            return "Can't craft " + item.getName();
        }

        if(!inventory.isInventoryFull()){
            this.consumeIngredients(item.getCraftingRecipe().getRecipe());
            int amountToCraft = item.getCraftingRecipe().getAmountToCraft();
            inventory.addItem(new ItemRecord(item, amountToCraft));
            return "Successfully crafted " + item.getName() + "!";
        }else{
            return "Inventory full, cannot craft item.";
        }
    }

    public boolean canCraft(BasicRecipe recipe){
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
