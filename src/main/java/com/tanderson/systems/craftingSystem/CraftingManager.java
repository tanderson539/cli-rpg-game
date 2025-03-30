package com.tanderson.systems.craftingSystem;

import com.tanderson.items.CraftableItem;
import com.tanderson.items.ItemRecord;
import com.tanderson.player.Inventory;

import java.util.ArrayList;


/**
 * Helper class for handling the Crafting System. Contains methods for
 * checking if the player can craft an item and for actually crafting an item.
 */
public class CraftingManager {

    /**
     * The Player Inventory object.
     */
    private final Inventory inventory;

    public CraftingManager(Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Checks if the player inventory contains the items and their amounts needed to craft a specific recipe.
     * @param recipe The crafting recipe to check.
     * @return A boolean representing if the player has the items necessary to satisfy the recipe provided.
     */
    public boolean canCraft(BasicRecipe recipe){
        for (CraftingIngredient ingredient : recipe.getRecipe()) {
            int requiredAmount = ingredient.getAmountRequired();
            if (inventory.getAmountOfItem(ingredient.getItem()) < requiredAmount) {
                return false;
            }
        }
        return true;
    }

    /**
     * Takes a recipe object and removes the items and the amount needed for that recipe.
     * Does not check whether the player can craft the item or not, assumes canCraft() to be true.
     * @param recipe The crafting recipe to consume items for.
     */
    private void consumeIngredients(ArrayList<CraftingIngredient> recipe){
        for (CraftingIngredient ingredient : recipe) {
            if(ingredient.isConsumed()){
                inventory.removeItem(ingredient.getItem(), ingredient.getAmountRequired());
            }
        }
    }

    /**
     * Takes a CraftableItemm checks if the player can craft it, and if so, crafts the item, adds it to
     * the player inventory, and consumes its ingredients.
     * @param item A CraftableItem to craft
     * @return A string output to be printed to the screen.
     */
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
}
