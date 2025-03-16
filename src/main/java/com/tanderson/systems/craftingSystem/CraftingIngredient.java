package com.tanderson.systems.craftingSystem;

import com.tanderson.items.Item;

/**
 * Data Structure used in Crafting Recipes. Allows for ingredients in recipes to be consumed or not on crafting,
 * and allows modifiable items and amounts of that item required in crafting.
 */
public class CraftingIngredient {
    private Item item;
    private int amountRequired;
    private boolean isConsumed;

    /**
     * Main Constructor
     * @param item The item to associate with this CraftingIngredient
     * @param amountRequired The amount of the above item required in a recipe to craft an item.
     * @param isConsumed A boolean representing if the item and amount is consumed when crafting.
     */
    public CraftingIngredient(Item item, int amountRequired, boolean isConsumed) {
        this.item = item;
        this.amountRequired = amountRequired;
        this.isConsumed = isConsumed;
    }

    /**
     * Modifies the amountRequired variable by a specific integer amount, negative or positive.
     * @param amountToAdjust A positive or negative integer representing how much to adjust the amountRequired variable by.
     */
    public void modifyAmountRequired(int amountToAdjust) {
        this.amountRequired += amountToAdjust;
    }

    /**
     * Returns the Item associated with this ingredient.
     * @return The Item associated with this ingredient.
     */
    public Item getItem() {
        return item;
    }

    /**
     * Sets the Item associated with this ingredient.
     * @param item The Item associated with this ingredient.
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * Returns the amount of this ingredient required in crafting
     * @return The amount of this ingredient required in crafting
     */
    public int getAmountRequired() {
        return amountRequired;
    }

    /**
     * Sets the amount of this ingredient required in crafting
     * @param amountRequired The amount of this ingredient required in crafting
     */
    public void setAmountRequired(int amountRequired) {
        this.amountRequired = amountRequired;
    }

    /**
     * Returns a boolean representing if the item and amount is consumed when crafting.
     * @return A boolean representing if the item and amount is consumed when crafting.
     */
    public boolean isConsumed() {
        return isConsumed;
    }

    /**
     * Sets the boolean representing if the item and amount is consumed when crafting.
     * @param consumed A boolean representing if the item and amount is consumed when crafting.
     */
    public void setConsumed(boolean consumed) {
        isConsumed = consumed;
    }
}
