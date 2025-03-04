package com.tanderson.craftingSystem;

import com.tanderson.items.CraftableItem;
import com.tanderson.items.Item;
import com.tanderson.items.ItemRecord;
import com.tanderson.player.Inventory;

import java.util.HashMap;

public class CraftingManager {

    private final Inventory inventory;

    public CraftingManager(Inventory inventory) {
        this.inventory = inventory;
    }

    public boolean craftItem(CraftableItem item){
        if(!this.canCraft(item.getCraftingRecipe())){
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
        for (Item ingredient : recipe.getRecipe().keySet()) {
            int requiredAmount = recipe.getRecipe().get(ingredient);
            if (inventory.getItemRecord(ingredient).getAmount() < requiredAmount) {
                return false;
            }
        }
        return true;
    }

    private void consumeIngredients(HashMap<Item, Integer> recipe){
        for (Item item : recipe.keySet()) {
            int amount = recipe.get(item);
            inventory.removeItem(item, amount);
        }
    }
}
