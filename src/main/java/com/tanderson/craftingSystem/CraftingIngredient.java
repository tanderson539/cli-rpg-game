package com.tanderson.craftingSystem;

import com.tanderson.items.Item;

public class CraftingIngredient {
    private Item item;
    private int amountRequired;
    private boolean isConsumed;

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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getAmountRequired() {
        return amountRequired;
    }

    public void setAmountRequired(int amountRequired) {
        this.amountRequired = amountRequired;
    }

    public boolean isConsumed() {
        return isConsumed;
    }

    public void setConsumed(boolean consumed) {
        isConsumed = consumed;
    }
}
